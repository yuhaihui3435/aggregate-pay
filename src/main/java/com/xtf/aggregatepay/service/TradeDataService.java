package com.xtf.aggregatepay.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.mail.MailUtil;
import com.alibaba.fastjson.JSON;
import com.xtf.aggregatepay.Consts;
import com.xtf.aggregatepay.client.TradeClient;
import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.core.LogicException;
import com.xtf.aggregatepay.dao.TradeDataDao;
import com.xtf.aggregatepay.dto.TradeResp;
import com.xtf.aggregatepay.entity.*;
import com.xtf.aggregatepay.util.APUtil;
import com.xtf.aggregatepay.util.EhcacheUtil;
import com.xtf.aggregatepay.util.Sha256;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 简介
 * <p>
 * 项目名称:   [aggregate-pay]
 * 包:        [com.xtf.aggregatepay.service]
 * 类名称:    [TradeDataService]
 * 类描述:    []
 * 创建人:    [于海慧]
 * 创建时间:  [2018/9/8]
 * 修改人:    []
 * 修改时间:  []
 * 修改备注:  []
 * 版本:     [v1.0]
 */
@Service
@Log4j2
@Transactional
public class TradeDataService extends BaseService<TradeData> {

    @Autowired
    private TradeDataDao tradeDataDao;
    @Autowired
    private ChannelInfoService channelInfoService;
    @Value("${tradeCallback.url}")
    private String tradeCallbackUrl;
    @Autowired
    private TradeClient tradeClient;
    @Autowired
    private ChannelDayStatisticsService channelDayStatisticsService;

    /**
     * 实时计算商户当日总交易额度，交易状态为 完成，处理中的
     * @param merNum
     * @return
     */
    public BigDecimal merSumTradeAmountNow(String merNum){
        return tradeDataDao.sumTradeAmount(merNum);
    }

    /**
     * 主扫交易处理
     * @param tradeData
     * @param merInfo
     * @return
     */
    @Transactional
    public TradeData zscan(TradeData tradeData, MerInfo merInfo){
        log.info("开始进行主扫处理,金额风控检查");
        //金额是否超过最大设置检查
        ChannelInfo channelInfo=channelInfoService.findByCode(merInfo.getChannelCode());
        BigDecimal maxAmount=channelInfo.getCeilingOfSingle();
        BigDecimal maxAmountOfDay=channelInfo.getCeilingOfDay();
        BigDecimal tradeAmount=new BigDecimal(tradeData.getTradeAmount()).divide(new BigDecimal(100));
        BigDecimal merSumAmountNow=merSumTradeAmountNow(merInfo.getMercNum());
        if(tradeAmount.compareTo(maxAmount)!=-1)throw new LogicException("单笔交易金额超过上限,上限为:"+maxAmount);
        if(merSumAmountNow!=null&&merSumAmountNow.compareTo(maxAmountOfDay)!=-1)throw new LogicException("交易金额已经超过今日上限,上限为："+maxAmountOfDay);
        if(Consts.BIZ_TYPE.valueOf(tradeData.getBizType())==null)throw new LogicException("交易方式错误");
        //设置交易相关数据
        log.info("开始交易数据整理");
        tradeData.setCallBackUrl(tradeCallbackUrl);
        tradeData.setTradeType(Consts.TRADE_TYPE.XXZSCAN.getVal());
        tradeData.setAgentNo(APUtil.getAgentNum());
        String str=JSON.toJSONString(tradeData);
        Map<String,String> param=JSON.parseObject(str,Map.class);
        param.remove("downCallBackUrl");
        String sign=Sha256.sha256ByAgentKey(param,APUtil.getAgentKey());
        param.put("sign",sign);
        log.info("订单号 {} ，交易数据为:{}",tradeData.getMerOrder(),param);
        TradeResp tradeResp=tradeClient.addTrade(JSON.toJSONString(param));
        BeanUtils.copyProperties(tradeResp,tradeData);
        tradeData.setChannelCode(merInfo.getChannelCode());
        tradeData.setOrderStatus(Consts.TRADE_STATUS.PROCESSING.getKey());
        insertAutoKey(tradeData);
        return tradeData;
    }

    /**
     * 依据商户号，订单号，交易金额查询订单
     * @param merNo
     * @param merOrder
     * @return
     */
    public TradeData queryByMerchantNoAndMerOrder(String merNo,String merOrder){
        return tplOne(TradeData.builder().merOrder(merOrder).merchantNo(merNo).build());
    }

    /**
     * 通知下游客户交易结果
     * @param map
     */
    public void notifyDown(String downCallbackUrl,Map map){
        tradeClient.downCallback(downCallbackUrl,map);
    }

    /**
     * 订单状态查询
     * @param tradeData
     * @return
     */
    public TradeData queryOrderStatus(TradeData tradeData){
        log.info("订单状态查询，商户号 {} ,订单号 {}",tradeData.getMerchantNo(),tradeData.getMerOrder());
        Map<String,String> param=new HashMap<>();
        param.put("merchantNo",tradeData.getMerchantNo());
        param.put("agentNo",tradeData.getAgentNo());
        param.put("merOrder",tradeData.getMerOrder());
        param.put("bizType",tradeData.getBizType());
        param.put("tradeType", Consts.TRADE_TYPE.QUREY.getVal());
        String sign=Sha256.sha256ByAgentKey(param,APUtil.getAgentKey());
        param.put("sign",sign);
        TradeResp tradeResp=tradeClient.queryOrderStatus(JSON.toJSONString(param));
        if(tradeResp.getResCode().equals(Consts.TRADE_STATUS.CLOSEFAILURE.name()))
            tradeData.setOrderStatus(Consts.TRADE_STATUS.CLOSEFAILURE.getKey());
        else
            tradeData.setOrderStatus(tradeResp.getOrderStatus());
        updateTplById(tradeData);
        log.info("订单状态查询，商户号 $} ,订单号 {}，订单状态为 {}",tradeData.getMerchantNo(),tradeData.getMerOrder(),tradeData.getOrderStatus());
        return tradeData;
    }

    public void staticsChannelTradeInDay(String staticsDate,String bizType,String settleWay){

        if(StrUtil.isBlank(settleWay))throw new LogicException("渠道交易统计操作，结算方式必填");
        List<ChannelInfo> channelInfoList=tradeDataDao.staticsTradeByChannel(staticsDate,bizType,settleWay);
//        StringBuilder stringBuilder=new StringBuilder();
//        stringBuilder.append("<table>").append("<tr>").append("<td>统计日期</td><td>渠道编号</td><td>渠道名称</td><td>结算方式</td><td>交易金额</td><td>交易数量</td>").append("</tr>");
        channelInfoList.stream().forEach(channelInfo -> {
            if(channelInfo.get("totalTradeAmount")!=null) {
                ChannelDayStatistics channelDayStatistics = channelDayStatisticsService.tplOne(ChannelDayStatistics.builder().statisticsDay(DateUtil.parse(staticsDate, "yyyy-MM-dd")).settlyType(settleWay).build());
                if (channelDayStatistics != null) channelDayStatisticsService.del(channelDayStatistics.getId());
                String cRateCode = null;
                String rateCode = null;
                int zs = 0;
                if (settleWay.equals(Consts.SETTLEWAY.T1.name())) {
                    cRateCode = channelInfo.getT1RateCode();
                    rateCode = APUtil.getT1RateCode();
                } else {
                    cRateCode = channelInfo.getTsRateCode();
                    rateCode = APUtil.getTsRateCode();
                }
                log.info("商户结算方式{},商户费率编号{}", settleWay, cRateCode);
                channelDayStatistics = new ChannelDayStatistics();
                channelDayStatistics.setChannelCode(channelInfo.getCode());
                channelDayStatistics.setSettlyType(settleWay);
                channelDayStatistics.setTradeAmount((BigDecimal) channelInfo.get("totalTradeAmount"));
                channelDayStatistics.setTradeNum((Integer) channelInfo.get("totalTradeNum"));
                BigDecimal b1 = APUtil.getRate(cRateCode).subtract(APUtil.getRate(rateCode));
                BigDecimal b2 = APUtil.getZs(cRateCode).subtract(APUtil.getZs(rateCode));
                BigDecimal b3 = b1.multiply(channelDayStatistics.getTradeAmount().divide(new BigDecimal(100)));//交易手续费
                BigDecimal b4 = b2.multiply(new BigDecimal(channelDayStatistics.getTradeNum()));//交易增收
                channelDayStatistics.setProfit(b3.add(b4));
                channelDayStatistics.setStatisticsDay(DateUtil.parse(staticsDate, "yyyy-MM-dd"));
                channelDayStatisticsService.insertAutoKey(channelDayStatistics);

//                stringBuilder.append("<tr>").append("<td>").append(staticsDate).append("</td>").append("<td>").append(channelInfo.getCode()).append("</td>")
//                        .append("<td>").append(channelInfo.getName()).append("</td>")
//                        .append("<td>").append(settleWay).append("</td>")
//                        .append("<td>").append(channelDayStatistics.getTradeAmount()).append("</td>")
//                        .append("<td>").append(channelDayStatistics.getTradeNum()).append("</td>");

                log.info("{}交易金额{},交易笔数{},交易手续费{},交易增收{},profit={}", staticsDate, channelDayStatistics.getTradeAmount(), channelDayStatistics.getTradeNum(), b3, b4, channelDayStatistics.getProfit());
            }
        });





    }
    @Scheduled(cron = "0 1 0 * * ?")
    public void channelT1TaskSchedule(){
        log.info("渠道T1统计开始");
        DateTime dateTime=DateUtil.yesterday();
        staticsChannelTradeInDay("2018-09-12",null,Consts.SETTLEWAY.T1.name());
        log.info("渠道T1统计结束");
    }

    @Scheduled(cron = "0 1 0 * * ?")
    public void channelTsTaskSchedule(){
        log.info("渠道Ts统计开始");
        DateTime dateTime=DateUtil.yesterday();
        staticsChannelTradeInDay(dateTime.toDateStr(),null,Consts.SETTLEWAY.Ts.name());
        log.info("渠道Ts统计结束");
    }


}

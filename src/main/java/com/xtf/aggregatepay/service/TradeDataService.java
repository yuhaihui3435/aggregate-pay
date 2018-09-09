package com.xtf.aggregatepay.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xtf.aggregatepay.Consts;
import com.xtf.aggregatepay.client.TradeClient;
import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.core.LogicException;
import com.xtf.aggregatepay.dao.TradeDataDao;
import com.xtf.aggregatepay.dto.TradeResp;
import com.xtf.aggregatepay.entity.ChannelInfo;
import com.xtf.aggregatepay.entity.MerInfo;
import com.xtf.aggregatepay.entity.TradeData;
import com.xtf.aggregatepay.util.APUtil;
import com.xtf.aggregatepay.util.Sha256;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.HashMap;
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
        if(merSumAmountNow.compareTo(maxAmountOfDay)!=-1)throw new LogicException("交易金额已经超过今日上限,上限为："+maxAmountOfDay);
        if(Consts.BIZ_TYPE.valueOf(tradeData.getBizType())==null)throw new LogicException("交易方式错误");
        //设置交易相关数据
        log.info("开始交易数据整理");
        tradeData.setCallBackUrl(tradeCallbackUrl);
        tradeData.setTradeType(Consts.TRADE_TYPE.XXZSCAN.getVal());
        tradeData.setAgentNo(APUtil.getAgentNum());
        String sign=Sha256.sha256ByAgentKey(tradeData,APUtil.getAgentKey());
        JSONObject jsonObject=(JSONObject) JSON.toJSON(tradeData);
        jsonObject.put("sign",sign);
        String param=jsonObject.toJSONString();
        log.info("订单号 ${} ，交易数据为:[${}]",tradeData.getMerOrder(),param);
        TradeResp tradeResp=tradeClient.addTrade(param);
        BeanUtil.copyProperties(tradeResp,tradeData,CopyOptions.create().setIgnoreNullValue(true));
        insertAutoKey(tradeData);
        return tradeData;
    }

    /**
     * 依据商户号，订单号，交易金额查询订单
     * @param merNo
     * @param merOrder
     * @param amount
     * @return
     */
    public TradeData queryByMerchantNoAndMerOrderAndAmount(String merNo,String merOrder,String amount){
        return tplOne(TradeData.builder().merOrder(merOrder).merchantNo(merNo).tradeAmount(amount).build());
    }

    /**
     * 通知下游客户交易结果
     * @param map
     */
    public void notifyDown(Map map){
        tradeClient.downCallback(map);
    }

    /**
     * 订单状态查询
     * @param tradeData
     * @return
     */
    public TradeData queryOrderStatus(TradeData tradeData){
        log.info("订单状态查询，商户号 ${} ,订单号 ${}",tradeData.getMerchantNo(),tradeData.getMerOrder());
        String param=JSONObject.toJSONString(tradeData);
        TradeResp tradeResp=tradeClient.queryOrderStatus(param);
        tradeData.setOrderStatus(tradeResp.getOrderStatus());
        updateTplById(tradeData);
        log.info("订单状态查询，商户号 ${} ,订单号 ${}，订单状态为 ${}",tradeData.getMerchantNo(),tradeData.getMerOrder(),tradeData.getOrderStatus());
        return tradeData;
    }

}

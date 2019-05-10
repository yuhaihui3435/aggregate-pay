package com.xtf.aggregatepay.service;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.RandomUtil;
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
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

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
    @Value("${notify.trade.email}")
    private String notifyTradeEmail;
    @Value("${trade.txt.path}")
    private String txtPath;
    @Autowired
    private TradeClient tradeClient;
    @Autowired
    private ChannelDayStatisticsService channelDayStatisticsService;
    @Autowired
    private MerInfoService merInfoService;
    @Autowired
    private ChannelBrokerageService channelBrokerageService;
    @Autowired
    private MerUsingService merUsingService;
    @Autowired
    private ProductService productService;
    @Autowired
    private TradeSettleService tradeSettleService;


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
        log.info("开始进行主扫处理");



        //金额是否超过最大设置检查
        ChannelInfo channelInfo=channelInfoService.findByCode(merInfo.getChannelCode());
        String startAt=channelInfo.getStartAt();
        String endAt=channelInfo.getEndAt();
        //如果有关闭时间控制
        if(StrUtil.isNotBlank(startAt)&&StrUtil.isNotBlank(endAt)){
            String today=DateUtil.today();
            endAt=today+" "+(endAt.length()==4?"0"+endAt:endAt);
            startAt=today+" "+(startAt.length()==4?"0"+startAt:startAt);
            try {
                DateTime startDT = DateUtil.parse(startAt);
                DateTime endDT = DateUtil.parse(endAt);
                DateTime now=DateUtil.parse(DateUtil.now());
                if(DateUtil.isIn(now,startDT,endDT)){
                    throw new LogicException("支付通道已经停止工作");
                }
            }catch (Exception e){
                if(e instanceof LogicException)throw  e;
                log.error("在线时间段解析出现了错误,原因:{}",e.getMessage());
            }
        }
        //支付通道在线状态
        if(StrUtil.isNotBlank(channelInfo.getOnline())&&channelInfo.getOnline().equals("n"))throw new LogicException("支付通道已经停止工作");
        if(channelInfo.getMinimumLimit().compareTo(new BigDecimal(tradeData.getTradeAmount()).divide(new BigDecimal(100)))==1){
            throw new LogicException("交易金额小于最小限额要求，交易金额不能小于"+channelInfo.getMinimumLimit().toString());
        }
        if(channelInfo.getType().equals("1"))
            merQuotaCheck(merInfo.getChannelCode(),merInfo.getMercNum(),new BigDecimal(tradeData.getTradeAmount()));
        BigDecimal tradeAmount=new BigDecimal(tradeData.getTradeAmount()).divide(new BigDecimal(100));
        if(Consts.BIZ_TYPE.valueOf(tradeData.getBizType())==null)throw new LogicException("交易方式错误");
        //设置交易相关数据
        log.info("开始交易数据整理");
        //挑选商户号
        String orginMerNo=tradeData.getMerchantNo();
        if(channelInfo!=null&&channelInfo.getType().equals("2")){
            MerInfo merInfo1=null;
            for(int i=0;i<5;i++){
                log.info("执行第{}次筛选商户处理",i+1);
                merInfo1=merInfoService.pickMerInfo(channelInfo.getCode(),tradeAmount);
                if(merInfo1!=null){
                    try{
                        merQuotaCheck(channelInfo.getCode(),merInfo1.getMercNum(),new BigDecimal(tradeData.getTradeAmount()));
                        break;
                    }catch (LogicException e){
                        log.error("筛选后的商户{} 风控未通过,继续筛选", merInfo1.getMercNum());
                        merInfo1=null;
                    }
                }
            }
            if(merInfo1==null)throw new LogicException("获取支付链接失败，请重试");
            log.info("筛选到的商户编号为 {}",merInfo1.getMercNum());
            tradeData.setMerchantNo(merInfo1.getMercNum());
            Product product=productService.pickProduct(tradeData.getMerchantNo(),tradeAmount);
            tradeData.setProductName(product==null?tradeData.getProductName():RandomUtil.randomString(merInfo1.getMercName(),2)+product.getProductName());
        }
        tradeData.setCallBackUrl(tradeCallbackUrl);
        tradeData.setTradeType(Consts.TRADE_TYPE.XXZSCAN.getVal());
        tradeData.setAgentNo(APUtil.getAgentNum());
        String str=JSON.toJSONString(tradeData);
        Map<String,String> param=JSON.parseObject(str,Map.class);
        param.remove("downCallBackUrl");
        param.remove("tails");
        param.remove("clientCode");

        String sign=Sha256.sha256ByAgentKey(param,APUtil.getAgentKey());
        param.put("sign",sign);
        log.info("订单号 {} ，交易数据为:{}",tradeData.getMerOrder(),param);
        TradeResp tradeResp=tradeClient.addTrade(JSON.toJSONString(param));
        BeanUtils.copyProperties(tradeResp,tradeData);
        tradeData.setChannelCode(merInfo.getChannelCode());
        tradeData.setOrderStatus(Consts.TRADE_STATUS.PROCESSING.getKey());
        insertAutoKey(tradeData);
        merUsingService.insertAutoKey(MerUsing.builder().merNo(tradeData.getMerchantNo()).orderNo(tradeData.getMerOrder()).useTime(new Date()).build());
        if(channelInfo.getType().equals("2")) {
            tradeData.setMerchantNo(orginMerNo);//将原始商户编号回传给下游
        }
        return tradeData;
    }

    /**
     * 依据商户号，订单号，交易金额查询订单
     * @param merNo
     * @param merOrder
     * @return
     */
    public TradeData queryByMerchantNoAndMerOrderAndClientCode(String merNo,String merOrder,String clientCode){
        MerInfo merInfo=merInfoService.findByMercNum(merNo);
        List<TradeData> tradeDataList=null;
        if(StrUtil.isBlank(clientCode)) {
             tradeDataList = sqlManager.lambdaQuery(TradeData.class).andEq(TradeData::getChannelCode, merInfo.getChannelCode()).andEq(TradeData::getMerOrder, merOrder).andIsNull(TradeData::getDeleteTime).select();
            return tradeDataList.isEmpty()?null:tradeDataList.get(0);
        }
        else {
            tradeDataList = sqlManager.lambdaQuery(TradeData.class).andEq(TradeData::getChannelCode, merInfo.getChannelCode()).andEq(TradeData::getMerOrder, merOrder).andIsNull(TradeData::getDeleteTime).andEq(TradeData::getClientCode,clientCode).select();
            return tradeDataList.isEmpty()?null:tradeDataList.get(0);
        }
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
        else if(tradeResp.getResCode().equals(Consts.TRADE_STATUS.SCAN_PAY_FAILD.name()))
            tradeData.setOrderStatus(Consts.TRADE_STATUS.SCAN_PAY_FAILD.getKey());
        else if(tradeResp.getResCode().equals(Consts.TRADE_STATUS.SUCCESS.name())) {

            tradeSettleService.addTradeSettle(tradeData.getMerOrder(), new BigDecimal(tradeData.getTradeAmount()).divide(new BigDecimal(100)));
            //清理商户账户占用表数据
            MerUsing merUsing = merUsingService.tplOne(MerUsing.builder().merNo(tradeData.getMerchantNo()).orderNo(tradeData.getMerOrder()).build());
            if (merUsing != null) merUsingService.del(merUsing.getId());
            String downCallbackUrl = tradeData.getDownCallBackUrl();
            if(StrUtil.isNotBlank(downCallbackUrl)) {
                log.info("同步交易状态后，通知下游回调，交易支付成功,订单号:{}",tradeData.getMerOrder());
                MerInfo merInfo = merInfoService.findByMercNum(tradeData.getMerchantNo());
                Map<String, String> map = new HashMap<>();
                map.put("merNo", tradeData.getMerchantNo());
                map.put("merOrder", tradeData.getMerOrder());
                map.put("tradeAmount", tradeData.getTradeAmount());
                map.put("orderStatus", tradeData.getOrderStatus());
                ApCode apCode = (ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(), merInfo.getApCode());
                String resp_sign = Sha256.sha256ByAgentKey(map, apCode.getApKey());
                map.put("sign", resp_sign);
                notifyDown(downCallbackUrl, map);
            }
        }
        else
            tradeData.setOrderStatus(tradeResp.getOrderStatus());
        updateTplById(tradeData);
        log.info("订单状态查询，商户号 {} ,订单号 {}，订单状态为 {}",tradeData.getMerchantNo(),tradeData.getMerOrder(),tradeData.getOrderStatus());
        return tradeData;
    }

    public void staticsChannelTradeInDay(String staticsDate,String bizType,String settleWay){

        if(StrUtil.isBlank(settleWay))throw new LogicException("渠道交易统计操作，结算方式必填");
        List<ChannelInfo> channelInfoList=tradeDataDao.staticsTradeByChannel(staticsDate,bizType,settleWay);
        StringBuilder stringBuilder=new StringBuilder();
        channelInfoList.stream().forEach(channelInfo -> {
            if(channelInfo.get("totaltradeamount")!=null) {
                ChannelDayStatistics channelDayStatistics = channelDayStatisticsService.tplOne(ChannelDayStatistics.builder().channelCode(channelInfo.getCode()).statisticsDay(DateUtil.parse(staticsDate, "yyyy-MM-dd")).settlyType(settleWay).build());
                if (channelDayStatistics != null) channelDayStatisticsService.del(channelDayStatistics.getId());
                String cRateCode = null;
                String rate = null;
                int zs = 0;
                if (settleWay.equals(Consts.SETTLEWAY.T1.name())) {
                    cRateCode = channelInfo.getT1RateCode();
                    rate = APUtil.getT1Rate();
                } else {
                    cRateCode = channelInfo.getTsRateCode();
                    rate = APUtil.getTsRate();
                }
                log.info("商户结算方式{},商户费率编号{}", settleWay, cRateCode);
                channelDayStatistics = new ChannelDayStatistics();
                channelDayStatistics.setChannelCode(channelInfo.getCode());
                channelDayStatistics.setSettlyType(settleWay);
                channelDayStatistics.setTradeAmount((BigDecimal) channelInfo.get("totaltradeamount"));
                channelDayStatistics.setTradeNum(((Long) channelInfo.get("totaltradenum")).intValue());
                BigDecimal b1 = APUtil.getRate(cRateCode).subtract(new BigDecimal(rate));
                BigDecimal b2 = APUtil.getZs(cRateCode).subtract(new BigDecimal(APUtil.getTsZs()));
                BigDecimal b3 = b1.multiply(channelDayStatistics.getTradeAmount().divide(new BigDecimal(100)));//交易手续费
                b3=b3.setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal b4 = b2.multiply(new BigDecimal(channelDayStatistics.getTradeNum()));//交易增收
                b4=b4.setScale(2,BigDecimal.ROUND_HALF_UP);
                channelDayStatistics.setProfit(b3.add(b4));
                channelDayStatistics.setStatisticsDay(DateUtil.parse(staticsDate, "yyyy-MM-dd"));
                channelDayStatisticsService.insertAutoKey(channelDayStatistics);
                MailUtil.send(channelInfo.getEmail(),"每日渠道交易数据",channelInfo.getCode()+","+staticsDate+","+channelDayStatistics.getTradeAmount()+","+channelDayStatistics.getTradeNum(),false);
//                stringBuilder.append("<tr>").append("<td>").append(staticsDate).append("</td>").append("<td>").append(channelInfo.getCode()).append("</td>")
//                        .append("<td>").append(channelInfo.getName()).append("</td>")
//                        .append("<td>").append(settleWay).append("</td>")
//                        .append("<td>").append(channelDayStatistics.getTradeAmount()).append("</td>")
//                        .append("<td>").append(channelDayStatistics.getTradeNum()).append("</td>");
                stringBuilder.append(channelInfo.getCode()).append(",").append(staticsDate).append(",").append(channelDayStatistics.getTradeAmount()).append(",").append(channelDayStatistics.getTradeNum()).append(",").append(channelDayStatistics.getProfit()).append("</br>");
                log.info("{}交易金额{},交易笔数{},交易手续费{},交易增收{},profit={}", staticsDate, channelDayStatistics.getTradeAmount(), channelDayStatistics.getTradeNum(), b3, b4, channelDayStatistics.getProfit());
            }
        });


        sendChannelTradeEmail(stringBuilder);



    }
//    @Scheduled(cron = "0 1 0 * * ?")
    public void channelT1TaskSchedule(){
        log.info("渠道T1统计开始");
        DateTime dateTime=DateUtil.yesterday();
        staticsChannelTradeInDay(dateTime.toDateStr(),null,Consts.SETTLEWAY.T1.name());
        log.info("渠道T1统计结束");
    }

//    @Scheduled(cron = "0 1 0 * * ?")
    public void channelTsTaskSchedule(){
        log.info("渠道Ts统计开始");
        DateTime dateTime=DateUtil.yesterday();
        staticsChannelTradeInDay(dateTime.toDateStr(),null,Consts.SETTLEWAY.Ts.name());
        log.info("渠道Ts统计结束");
    }


//    @Scheduled(cron = "0 1 0 * * ?")
    public void channelBrokerageTaskSchedule(){
        log.info("渠道佣金统计开始");
        DateTime dateTime=DateUtil.yesterday();
        staticsChannelTradeBrokerageInDay(null,dateTime.toDateStr());
        log.info("渠道佣金统计结束");
    }



    public void staticsChannelTradeBrokerageInDay(Integer channelId,String staticsDate){
        log.info("渠道佣金计算开始");
        Map<String,ChannelBrokerage> emailData=new HashMap<>();
        if(channelId==null){
            List<ChannelInfo> channelInfos=channelInfoService.tpl(ChannelInfo.builder().status(Consts.STATUS.NORMAL.getVal()).build());
            channelInfos.stream().forEach(channelInfo -> {
                emailData.put(channelInfo.getCode(),calBrokerage(channelInfo,staticsDate));

            });
        }else{
            ChannelInfo channelInfo=channelInfoService.one(channelId);
            emailData.put(channelInfo.getCode(),calBrokerage(channelInfo,staticsDate));
        }
        try {
            sendBrokerageEmail(emailData);
        }catch (Exception e){
            log.error("邮件发送失败 {}",e.getMessage());
        }
    }

    private ChannelBrokerage calBrokerage(ChannelInfo channelInfo,String staticsDate){
        List<TradeData> tradeDataList=tradeDataDao.staticsTradeByMerInfo(channelInfo.getId(),staticsDate);
        log.info("渠道{},于{},一共产生了{}交易",channelInfo.getCode(),staticsDate,tradeDataList.size());
        BigDecimal brokerage=new BigDecimal(0);

        for(TradeData tradeData:tradeDataList) {
            MerInfo merInfo = merInfoService.findByMercNum(tradeData.getMerchantNo());
            BigDecimal b1 = APUtil.getRate(merInfo.getRateCode());//商户费率
            BigDecimal b2 = APUtil.getZs(merInfo.getRateCode());//商户增收
            BigDecimal b3 = null, b4 = null;//渠道费率，渠道增收
            BigDecimal tAmount = new BigDecimal(tradeData.getTradeAmount()).divide(new BigDecimal(100));
            if (merInfo.getSettleWay().equals(Consts.SETTLEWAY.Ts.name())) {
                b3 = APUtil.getRate(channelInfo.getTsRateCode());//渠道费率
                b4 = APUtil.getZs(channelInfo.getTsRateCode());//渠道增收
            } else {
                b3 = APUtil.getRate(channelInfo.getT1RateCode());
                b4 = APUtil.getZs(channelInfo.getT1RateCode());
            }

            brokerage = brokerage.add(tAmount.multiply(b1.subtract(b3))).add(b2.subtract(b4));
        }
        brokerage=brokerage.setScale(2, BigDecimal.ROUND_HALF_UP);
        log.info("渠道{},于{}，一共产生了交易佣金{}",channelInfo.getCode(),staticsDate,brokerage.toString());
        ChannelBrokerage channelBrokerage=channelBrokerageService.tplOne(ChannelBrokerage.builder().brokerageDay(staticsDate).channelCode(channelInfo.getCode()).build());
        if(channelBrokerage!=null)channelBrokerageService.del(channelBrokerage.getId());

        channelBrokerage=ChannelBrokerage.builder().channelCode(channelInfo.getCode()).brokerageAmount(brokerage.toString()).brokerageDay(staticsDate).createdTime(new Date()).build();
        channelBrokerageService.insertAutoKey(channelBrokerage);
        return channelBrokerage;
    }

    public void staticsMerTrade(String sDate){
        List<MerInfo> merInfos=merInfoService.tpl(MerInfo.builder().dataStatus(Consts.STATUS.NORMAL.getVal()).status(Consts.MER_STATUS.SHTG.name()).build());
        merInfos.stream().forEach(merInfo -> {
            List<TradeData> list=tradeDataDao.selectByMerMumAndOrderStatusAndEDate(merInfo.getMercNum(),Consts.SYS_COMMON_SUCCESS_CODE,sDate);

            List<StringBuilder> stringBuilders=new ArrayList<>();
            stringBuilders.add(new StringBuilder().append("订单编号").append("|").append("订单金额").append("|").append("支付方式").append("|").append("银行订单号").append("|").append("订单时间"));
            list.stream().forEach(tradeData -> {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append(tradeData.getMerOrder()).append("|").append(new BigDecimal(tradeData.getTradeAmount()).divide(new BigDecimal(100)))
                        .append("|").append(tradeData.getBizType()).append("|").append(tradeData.getBankOrder()).append("|").append(tradeData.getTimeEnd());
                stringBuilders.add(stringBuilder);
            });

            if(!list.isEmpty()) {
                BigDecimal bigDecimal = tradeDataDao.sumTradeAmountByDate(merInfo.getMercNum(), sDate);
                bigDecimal = (bigDecimal == null) ? new BigDecimal("0") : bigDecimal;
                StringBuilder stringBuilder = new StringBuilder("总交易金额" + bigDecimal.divide(new BigDecimal(100))).append("交易数量" + list.size());
                stringBuilders.add(stringBuilder);
                File file = FileUtil.newFile(txtPath + merInfo.getMercNum() + "_" + sDate + "_" + System.currentTimeMillis() + ".txt");
                FileUtil.appendUtf8Lines(stringBuilders, file);
                MailUtil.send(merInfo.getEmail(), sDate + "交易数据", "查看附件", false, file);
            }

        });
    }

    private void sendBrokerageEmail(Map<String,ChannelBrokerage> map){
        List<String> targetEmail=StrUtil.splitTrim(notifyTradeEmail,";");
        StringBuilder stringBuilder=new StringBuilder();
        for (Map.Entry<String, ChannelBrokerage> entry : map.entrySet()) {
            String key = entry.getKey().toString();
            ChannelBrokerage value = entry.getValue();
            stringBuilder.append(key).append(",").append(value.getBrokerageAmount()).append(",").append(value.getBrokerageDay()).append("</br>");
        }
        MailUtil.sendHtml(targetEmail,"每日渠道分润数据",stringBuilder.toString());
    }


    private void sendChannelTradeEmail(StringBuilder stringBuilder){
        List<String> targetEmail=StrUtil.splitTrim(notifyTradeEmail,";");
        MailUtil.sendHtml(targetEmail,"每日渠道交易利润数据",stringBuilder.toString());
    }
    //渠道下交易查询
    public PageQuery queryTradeByChannelInDateAndStatus(PageQuery pageQuery,String channelCode, String sDate, String eDate, String status){
        ChannelInfo channelInfo=channelInfoService.findByCode(channelCode);
        if(channelInfo==null)throw new LogicException("渠道编号不存在");
        HashMap query=new HashMap();
        query.put("channelId",channelInfo.getId());
        query.put("sDate",sDate);
        query.put("eDate",eDate);
        query.put("status",status);
        pageQuery.setParas(query);
        return page("tradeData.selectTradeForChannelByInDateAndStatus",pageQuery);
    }



    /**
     * 公众号
     * @param tradeData
     * @param merInfo
     * @return
     */
    @Transactional
    public TradeData zscan10(TradeData tradeData, MerInfo merInfo){
        log.info("开始公众号1.0处理");
        //金额是否超过最大设置检查

        ChannelInfo channelInfo=channelInfoService.findByCode(merInfo.getChannelCode());

        String startAt=channelInfo.getStartAt();
        String endAt=channelInfo.getEndAt();
        //如果有在线时间控制
        if(StrUtil.isNotBlank(startAt)&&StrUtil.isNotBlank(endAt)){
            String today=DateUtil.today();
            endAt=today+" "+(endAt.length()==4?"0"+endAt:endAt);
            startAt=today+" "+(startAt.length()==4?"0"+startAt:startAt);
            try {
                DateTime startDT = DateUtil.parse(startAt);
                DateTime endDT = DateUtil.parse(endAt);
                DateTime now=DateUtil.parse(DateUtil.now());
                if(DateUtil.isIn(now,startDT,endDT)){
                    throw new LogicException("支付通道已经停止工作");
                }
            }catch (Exception e){
                if(e instanceof LogicException)throw  e;
                log.error("在线时间段解析出现了错误,原因:{}",e.getMessage());
            }
        }
        //支付通道在线状态
        if(StrUtil.isNotBlank(channelInfo.getOnline())&&channelInfo.getOnline().equals("n"))throw new LogicException("支付通道已经停止工作");

//        if(StrUtil.isBlank(tradeData.getPageBackUrl()))tradeData.setPageBackUrl("http://47.75.135.105/api/gzaliSuccess");
        tradeData.setPageBackUrl("http://47.75.135.105/api/gzaliSuccess");
        if(channelInfo.getMinimumLimit().compareTo(new BigDecimal(tradeData.getTradeAmount()).divide(new BigDecimal(100)))==1){
            throw new LogicException("交易金额小于最小限额要求，交易金额不能小于"+channelInfo.getMinimumLimit().toString());
        }
        if(channelInfo.getType().equals("1"))
            merQuotaCheck(merInfo.getChannelCode(),merInfo.getMercNum(),new BigDecimal(tradeData.getTradeAmount()));
        BigDecimal tradeAmount=new BigDecimal(tradeData.getTradeAmount()).divide(new BigDecimal(100));
        if(Consts.BIZ_TYPE.valueOf(tradeData.getBizType())==null)throw new LogicException("交易方式错误");
        //设置交易相关数据
        log.info("开始交易数据整理");
        //挑选商户号
        String orginMerNo=tradeData.getMerchantNo();

        if(channelInfo!=null&&channelInfo.getType().equals("2")&&merInfo.getPick()!=null&&merInfo.getPick().equals("0")){
            MerInfo merInfo1=null;
            for(int i=0;i<5;i++){
                log.info("执行第{}次筛选商户处理",i+1);
                merInfo1=merInfoService.pickMerInfo(channelInfo.getCode(),tradeAmount);
                if(merInfo1!=null){
                    try{
                        merQuotaCheck(channelInfo.getCode(),merInfo1.getMercNum(),new BigDecimal(tradeData.getTradeAmount()));
                        break;
                    }catch (LogicException e){
                        log.error("筛选后的商户{} 风控未通过,继续筛选", merInfo1.getMercNum());
                        merInfo1=null;
                    }
                }
            }
            if(merInfo1==null)throw new LogicException("获取二维码失败，请重试");
            log.info("筛选到的商户编号为 {}",merInfo1.getMercNum());
            tradeData.setMerchantNo(merInfo1.getMercNum());
            Product product=productService.pickProduct(tradeData.getMerchantNo(),tradeAmount);
            tradeData.setProductName(product==null?tradeData.getProductName():RandomUtil.randomString(merInfo1.getMercName(),2)+product.getProductName());
        }
        tradeData.setCallBackUrl(tradeCallbackUrl);
        tradeData.setTradeType(Consts.TRADE_TYPE.GZZZ.getVal());
        tradeData.setAgentNo(APUtil.getAgentNum());
        String str=JSON.toJSONString(tradeData);
        Map<String,String> param=JSON.parseObject(str,Map.class);
        param.remove("downCallBackUrl");
        param.remove("tails");
        param.put("version","1.0");
        param.remove("clientCode");

        String sign=Sha256.sha256ByAgentKey(param,APUtil.getAgentKey());
        param.put("sign",sign);
        log.info("订单号 {} ，交易数据为:{}",tradeData.getMerOrder(),param);
        TradeResp tradeResp=tradeClient.addTrade(JSON.toJSONString(param));
        BeanUtils.copyProperties(tradeResp,tradeData);
        tradeData.setChannelCode(merInfo.getChannelCode());
        tradeData.setOrderStatus(Consts.TRADE_STATUS.PROCESSING.getKey());
        insertAutoKey(tradeData);
        merUsingService.insertAutoKey(MerUsing.builder().merNo(tradeData.getMerchantNo()).orderNo(tradeData.getMerOrder()).useTime(new Date()).build());
        if(channelInfo.getType().equals("2")) {
            tradeData.setMerchantNo(orginMerNo);//将原始商户编号回传给下游
        }
        return tradeData;
    }

    /**
     * 商户当前额度检查
     * @param merNum
     */
    private void merQuotaCheck(String channelCode,String merNum,BigDecimal tradeAmount){
        log.info("{}开始金额风控检查",merNum);
        ChannelInfo channelInfo=channelInfoService.findByCode(channelCode);
        BigDecimal maxAmount=channelInfo.getCeilingOfSingle();
        BigDecimal maxAmountOfDay=channelInfo.getCeilingOfDay();
        tradeAmount=tradeAmount.divide(new BigDecimal(100));
        BigDecimal merSumAmountNow=merSumTradeAmountNow(merNum);
        if(merSumAmountNow!=null) {
            log.info("当前得交易总额度为{}", merSumAmountNow.divide(new BigDecimal(100)));
        }
        if(tradeAmount.compareTo(maxAmount)!=-1)throw new LogicException("单笔交易金额超过上限,上限为:"+maxAmount);
        if(merSumAmountNow!=null&&merSumAmountNow.divide(new BigDecimal(100)).compareTo(maxAmountOfDay)!=-1)throw new LogicException("交易金额已经超过今日上限,上限为："+maxAmountOfDay);
    }

    /**
     *
     * 每半小时同步交易状态
     *
     */
    @Scheduled(cron = "0 0,30 * * * ?")
    private void syncTradeStatus() {
        List<TradeData> tradeDatas = sqlManager.lambdaQuery(TradeData.class).andEq(TradeData::getOrderStatus, "processing").andIsNull(TradeData::getDeleteTime).select();
        log.info("于{}同步交易状态，本次同步记录数为{}",DateUtil.now(),tradeDatas.size());
        TradeData tradeData = null;
        for (int i = 0; i < tradeDatas.size(); i++) {
            tradeData = tradeDatas.get(i);
            queryOrderStatus(tradeData);
        }
    }


    public static void main(String[] args) {

    }
}

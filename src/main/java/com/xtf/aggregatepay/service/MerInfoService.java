package com.xtf.aggregatepay.service;

import cn.hutool.json.JSONUtil;
import com.xtf.aggregatepay.Consts;
import com.xtf.aggregatepay.client.MerchantClient;
import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.core.LogicException;
import com.xtf.aggregatepay.entity.*;
import com.xtf.aggregatepay.util.APUtil;
import com.xtf.aggregatepay.util.Sha256;
import lombok.extern.log4j.Log4j2;
import org.beetl.sql.core.db.KeyHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class MerInfoService extends BaseService<MerInfo> {

    @Autowired
    private ChannelInfoService channelInfoService;
    @Autowired
    private MerchantClient merchantClient;
    @Autowired
    private MerBankInfoService merBankInfoService;
    @Autowired
    private MerPicService merPicService;

    /**
     *
     * @param appMerCode
     * @return
     */
    public MerInfo findByAppMerCode(String appMerCode){
        return tplOne(MerInfo.builder().appMercCode(appMerCode).build());
    }

    public MerInfo findByMercNum(String mercNum){
        return tplOne(MerInfo.builder().mercNum(mercNum).build());
    }

    public MerInfo checkMerInfoStatus(String mercNum){
        MerInfo merInfo=findByMercNum(mercNum);
        ChannelInfo channelInfo=channelInfoService.findByCode(merInfo.getChannelCode());
        if(channelInfo==null)
            throw new LogicException("上级渠道状态异常，被停用");

        if(merInfo.getDataStatus().equals(Consts.STATUS.STOP.getVal())){
            throw  new LogicException("商户状态异常，被停用");
        }
        if(!merInfo.getStatus().equals(Consts.MER_STATUS.SHTG)){
            throw  new LogicException("商户未通过审核");
        }
        return merInfo;
    }

    /**
     * 商户进件入网请求
     * @param merInfo
     * @param merBankInfo
     * @param picTypeStringMap
     * @return
     */
    @Transactional
    public MerInfo addMerInfo(MerInfo merInfo, MerBankInfo merBankInfo, Map<Consts.PicType,String> picTypeStringMap){
        log.info("开始向渠道上送商户进件入网请求,商户的基本信息为 ${}",merInfo.toString());
        log.info("查看商户对应的渠道商信息,渠道号 ${}",merInfo.getChannelCode());
        ChannelInfo channelInfo=channelInfoService.findByCode(merInfo.getChannelCode());
        if(channelInfo==null)throw new LogicException("新增商户信息失败，原因：渠道商不存在或被停用");
        //依据商户的结算方式，从商户所属渠道那边获取对应的交易费率
        if(merInfo.getSettleWay().equals(Consts.SETTLEWAY.T1.name())){
            merInfo.setRateCode(channelInfo.getT1RateCode());
            merInfo.setRate(channelInfo.getT1Rate());
        }else if(merInfo.getSettleWay().equals(Consts.SETTLEWAY.TS.name())){
            merInfo.setRateCode(channelInfo.getTsRateCode());
            merInfo.setRate(channelInfo.getTsRate());
        }else{
            log.error("商户结算方式不正确，当前值为 ${}",merInfo.getSettleWay());
            throw new LogicException("结算方式设置不正确，请设置为T1或者TS");
        }

        merBankInfo.setCardType("0");//借记卡
        merInfo.setIncomeType(Consts.INCOMETYPE.normal.name());
        //整理商户图片信息
        Map<String,String> picMap=new HashMap<>();
        picTypeStringMap.forEach((k,v)->{
           picMap.put(k.getStr(),v);
        });
        //检查商户必填的图片信息
        if(!picMap.containsKey(Consts.PicType.LICENSE.getStr()))throw new LogicException("新增商户信息失败，原因：缺少营业执照照片");
        if(!picMap.containsKey(Consts.PicType.CARD.getStr()))throw new LogicException("新增商户信息失败，原因：缺少身份证正面照片");
        if(!picMap.containsKey(Consts.PicType.BACKCARD.getStr()))throw new LogicException("新增商户信息失败，原因：缺少身份证背面照片");

        String agentNum=APUtil.getAgentNum();
        String agentKey=APUtil.getAgentKey();
        String tradeFlowNo=String.valueOf(System.nanoTime());//交易流水号
        Map<String,Object> param=new HashMap<>();
        param.put("agentNum",agentNum);
        param.put("tradeFlowNo",tradeFlowNo);
        param.put("merchantInfo",merInfo);
        param.put("merchantBankcard",merBankInfo);
        param.put("merImg",picMap);
        param.put("rateCode",merInfo.getRateCode());
        param.put("settleWay", merInfo.getSettleWay());
        Map<String,String> product=new HashMap<>();
        product.put("scan","ALIPAY,WECHATPAY");
        param.put("product",product);

        log.info("商户进件数据准备完成,内容为  ${}",JSONUtil.toJsonStr(param));
        log.info("开始进行sha256计算，获取sign");
        String sign=Sha256.sha256ByAgentKey(param,agentKey);
        log.info("SIGN结果= ${}",sign);
        param.put("sign",sign);

        String merNum=merchantClient.addMerInfo(param);
        merInfo.setMercNum(merNum);
        merInfo.setDataStatus(Consts.STATUS.NORMAL.getVal());
        merInfo.setStatus(Consts.MER_STATUS.DDSH.name());
        KeyHolder keyHolder=insertReturnKey(merInfo);
        Integer merId=keyHolder.getInt();
        merBankInfo.setMerId(merId);
        merBankInfoService.insertAutoKey(merBankInfo);
        return merInfo;
    }

    /**
     * 商户状态查询，如果状态是等待审核，则去通道同步商户状态
     * @param merNum
     * @return
     */
    public MerInfo queryMerInfoStatus(String merNum){
        log.info("商户号 ${} 进行商户状态查询",merNum);
        MerInfo query=MerInfo.builder().mercNum(merNum).dataStatus(Consts.STATUS.NORMAL.getVal()).build();
        MerInfo merInfo=tplOne(query);
        if(merInfo==null)throw new LogicException("商户信息不存在，或被禁用");
        if(merInfo.getStatus().equals(Consts.MER_STATUS.DDSH)||merInfo.getStatus().equals(Consts.MER_STATUS.SHZ)){
            String agentNum=APUtil.getAgentNum();
            String agentKey=APUtil.getAgentKey();
            Map<String,Object> param=new HashMap<>();
            param.put(Consts.AGENT.agentNum.name(),agentNum);
            param.put("mercNum",merNum);
            String sign=Sha256.sha256ByAgentKey(param,agentKey);
            param.put("sign",sign);
            log.info("商户号 ${} 开始向渠道发送商户状态查询请求",merNum);
            String status=merchantClient.queryMerStatus(param);
            log.info("商户号 ${} 开始向渠道发送商户状态查询请求结果为 ${}",merNum,status);
            if(!Consts.MER_STATUS.DDSH.name().equals(status)){
                log.info("商户号 ${} 状态发生了变化，更新商户状态",merNum);
                merInfo.setStatus(status);
                update(merInfo);
            }
        }
        return merInfo;
    }



}

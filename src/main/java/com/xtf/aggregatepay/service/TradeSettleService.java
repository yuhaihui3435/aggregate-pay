package com.xtf.aggregatepay.service;

import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.entity.*;
import com.xtf.aggregatepay.util.APUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
public class TradeSettleService extends BaseService<TradeSettle> {
    @Autowired
    private ClientInfoService clientInfoService;
    @Autowired
    private ChannelInfoService channelInfoService;
    @Autowired
    private TradeDataService tradeDataService;
    @Autowired
    private ChannelSubInfoService channelSubInfoService;

    public void addTradeSettle(String merOrder, BigDecimal amount){
        BigDecimal yb=new BigDecimal(100);
        BigDecimal l=new BigDecimal(0);
        TradeData tradeData=tradeDataService.tplOne(TradeData.builder().merOrder(merOrder).build());
        String channelCode=tradeData.getChannelCode();
        String clientCode=tradeData.getClientCode();

        TradeSettle tradeSettle=tplOne(TradeSettle.builder().tradeno(merOrder).build());
        if(tradeSettle!=null)return;

        ChannelInfo channelInfo=channelInfoService.findByCode(channelCode);
        BigDecimal channelTsrate=APUtil.getRate(channelInfo.getTsRateCode());
        ClientInfo clientInfo=clientInfoService.tplOne(ClientInfo.builder().clientCode(clientCode).build());
        if(clientInfo==null){
            log.error("{}客户信息未查询到",clientCode);
            return;
        }
        ChannelSubInfo channelSubInfo=channelSubInfoService.one(clientInfo.getChannelSubId());
        if(channelSubInfo==null){
            log.error("客户{}对应的代理商信息未查询到",clientCode);
            return;
        }
        BigDecimal clientAmount=clientInfo.getTsRate()==null?l:clientInfo.getTsRate().divide(yb).multiply(amount).setScale(2,BigDecimal.ROUND_HALF_UP);
        clientAmount=amount.subtract(clientAmount);

        BigDecimal channelSubAmount=channelSubInfo.getTsrate()==null?l:(clientInfo.getTsRate().subtract(channelSubInfo.getTsrate())).multiply(amount).divide(yb).setScale(2,BigDecimal.ROUND_HALF_UP);
//        channelSubAmount=amount.subtract(channelSubAmount);


        BigDecimal channelAmount=amount.multiply(channelTsrate).add(new BigDecimal(0.2)).setScale(2,BigDecimal.ROUND_HALF_UP);

        log.info("交易编号{},交易金额{},客户金额{},代理商金额{},其他{}",merOrder,amount,clientAmount,channelSubAmount,channelAmount);

        tradeSettle=TradeSettle.builder().tradeno(merOrder).channelid(channelInfo.getId()).channelsubid(channelSubInfo.getId())
                .clientid(clientInfo.getId()).tradesettleamount(amount).carryamount(clientAmount).carrysubamount(channelSubAmount)
                .settlesubrate(channelSubInfo.getTsrate()).settleclientrate(clientInfo.getTsRate()).tradebaseamount(channelAmount)
                .cat(new Date()).build();



        insertAutoKey(tradeSettle);

    }

}

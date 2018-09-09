package com.xtf.aggregatepay.service;

import com.xtf.aggregatepay.Consts;
import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.entity.ChannelInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChannelInfoService extends BaseService<ChannelInfo> {

    public ChannelInfo findByCode(String code){
        ChannelInfo channelInfo=ChannelInfo.builder().code(code).status(Consts.STATUS.NORMAL.getVal()).build();
        return tplOne(channelInfo);
    }
}

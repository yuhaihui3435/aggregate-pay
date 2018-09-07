package com.xtf.aggregatepay.service;

import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.entity.MerInfo;

public class MerInfoService extends BaseService<MerInfo> {



    public MerInfo findByAppMerCode(String appMerCode){
        MerInfo merInfo=new MerInfo();
        merInfo.setAppMercCode(appMerCode);
        return tplOne(merInfo);
    }

}

package com.xtf.aggregatepay.service;

import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.entity.ApCode;
import com.xtf.aggregatepay.util.EhcacheUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ApCodeService extends BaseService<ApCode> {


    public void apCodeCache(){
        EhcacheUtil.getInstance().removeAll(ApCode.class.getSimpleName());
        List<ApCode> apCodeList =all();
        apCodeList.stream().forEach(apCode -> {
            EhcacheUtil.getInstance().put(ApCode.class.getSimpleName(),apCode.getApCode(),apCode);
        });
    }

}

package com.xtf.aggregatepay.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.xtf.aggregatepay.Consts;
import com.xtf.aggregatepay.client.MerchantClient;
import com.xtf.aggregatepay.core.BaseController;
import com.xtf.aggregatepay.core.BaseEntity;
import com.xtf.aggregatepay.core.LogicException;
import com.xtf.aggregatepay.dto.ApiReq;
import com.xtf.aggregatepay.dto.ApiResp;
import com.xtf.aggregatepay.entity.ApCode;
import com.xtf.aggregatepay.entity.MerInfo;
import com.xtf.aggregatepay.service.ApCodeService;
import com.xtf.aggregatepay.service.MerInfoService;
import com.xtf.aggregatepay.util.Sha256;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@RequestMapping(value = "/api")
public class ApiController extends BaseController{
    @Autowired
    private MerInfoService merInfoService;
    @Autowired
    private ApCodeService apCodeService;

    @PostMapping(value = "/addMercInfo")
    public ApiResp addMercInfo(ApiReq apiReq){
        String json=apiReq.getJsonData();
        String req_sign=apiReq.getSign();
        MerInfo merInfo= JSONObject.parseObject(json,MerInfo.class);
        String ac=merInfo.getApCode();
        ApCode apCode=new ApCode();
        apCode.setApCode(ac);
        apCode=apCodeService.tplOne(apCode);
        String sign=Sha256.sha256ByAgentKey(json,apCode.getApKey());
        if(req_sign.equals(sign)){
            merInfoService.insertAutoKey(merInfo);
        }else{

        }

        ApiResp apiResp=new ApiResp(Consts.SYS_COMMON_SUCCESS_CODE,"商户新增成功,等待审核");
        return apiResp;
    }




}

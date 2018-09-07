package com.xtf.aggregatepay.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xtf.aggregatepay.Consts;
import com.xtf.aggregatepay.client.MerchantClient;
import com.xtf.aggregatepay.core.BaseController;
import com.xtf.aggregatepay.core.BaseEntity;
import com.xtf.aggregatepay.core.LogicException;
import com.xtf.aggregatepay.dto.ApiReq;
import com.xtf.aggregatepay.dto.ApiResp;
import com.xtf.aggregatepay.dto.HttpResp;
import com.xtf.aggregatepay.entity.ApCode;
import com.xtf.aggregatepay.entity.MerBankInfo;
import com.xtf.aggregatepay.entity.MerInfo;
import com.xtf.aggregatepay.entity.MerPic;
import com.xtf.aggregatepay.service.ApCodeService;
import com.xtf.aggregatepay.service.MerBankInfoService;
import com.xtf.aggregatepay.service.MerInfoService;
import com.xtf.aggregatepay.service.MerPicService;
import com.xtf.aggregatepay.util.EhcacheUtil;
import com.xtf.aggregatepay.util.Sha256;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Log4j2
@RequestMapping(value = "/api")
public class ApiController extends BaseController{
    @Autowired
    private MerInfoService merInfoService;
    @Autowired
    private ApCodeService apCodeService;
    @Autowired
    private MerBankInfoService merBankInfoService;
    @Value("${pic.path}")
    private String picPath;
    @Autowired
    private MerchantClient merchantClient;
    @Autowired
    private MerPicService merPicService;

    @PostMapping(value = "/addMercInfo")
    public ApiResp<MerInfo> addMercInfo(ApiReq apiReq){
        ApiResp apiResp=null;
        String json=apiReq.getJsonData();
        String req_sign=apiReq.getSign();
        MerInfo merInfo= JSONObject.parseObject(json,MerInfo.class);
        String ac=merInfo.getApCode();
        ApCode apCode=(ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(),ac);
        String sign=Sha256.sha256ByAgentKey(json,apCode.getApKey());
        if(req_sign.equals(sign)){
            merInfo.setAppMercCode(StrUtil.uuid().replaceAll("-",""));
            merInfoService.insertAutoKey(merInfo);
        }else{
            apiResp=new ApiResp(Consts.SYS_COMMON_FAIL_CODE,"报文签名信息解析不一致");
            return apiResp;
        }
        String reSign=Sha256.signBySha256(merInfo,apCode.getApKey());
        apiResp=new ApiResp<MerInfo>(Consts.SYS_COMMON_SUCCESS_CODE,"商户基本信息新增成功,请上传银行卡和证件信息");
        apiResp.setJsonData(merInfo);
        apiResp.setSign(reSign);
        return apiResp;
    }
    @PostMapping(value = "/addMercBankInfo")
    public ApiResp<MerBankInfo> addMerBankInfo(@RequestBody ApiReq apiReq, @RequestParam String appMerCode){
        ApiResp<MerBankInfo> apiResp=null;
        String json=apiReq.getJsonData();
        String req_sign=apiReq.getSign();
        MerBankInfo merBankInfo=JSONObject.parseObject(json,MerBankInfo.class);
        if(StrUtil.isBlank(appMerCode)){
            apiResp=new ApiResp(Consts.SYS_COMMON_FAIL_CODE,"商户银行卡信息新增失败，原因：appMerCode没有找到");
            return apiResp;
        }
        MerInfo merInfo=merInfoService.findByAppMerCode(appMerCode);
        String ac=merInfo.getApCode();
        ApCode apCode=(ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(),ac);
        String sign=Sha256.sha256ByAgentKey(json,apCode.getApKey());
        if(req_sign.equals(sign)){
            merBankInfo.setMerId(merInfo.getId());
            merBankInfoService.insertAutoKey(merBankInfo);
        }else{
            apiResp=new ApiResp(Consts.SYS_COMMON_FAIL_CODE,"报文签名信息解析不一致");
            return apiResp;
        }
        String reSign=Sha256.signBySha256(merBankInfo,apCode.getApKey());
        apiResp=new ApiResp<MerBankInfo>(Consts.SYS_COMMON_SUCCESS_CODE,"商户银行卡信息新增成功。");
        apiResp.setJsonData(merBankInfo);
        apiResp.setSign(reSign);
        return apiResp;
    }

    @PostMapping(value = "/picUpload")
    public ApiResp<String> addMerPic(@RequestParam("file") MultipartFile file, Map<String, Object> map) throws IOException {
        String appMerCode=(String)map.get("appMerCode");
        ApiResp<String> apiResp=null;
        if(StrUtil.isBlank(appMerCode)){
            apiResp=new ApiResp(Consts.SYS_COMMON_FAIL_CODE,"图片上传失败，原因：appMerCode没有找到");
            return apiResp;
        }
        String picType=(String)map.get("picType");
        if(StrUtil.isBlank(picType)){
            apiResp=new ApiResp(Consts.SYS_COMMON_FAIL_CODE,"图片上传失败，原因：picType没有找到");
            return apiResp;
        }
        MerInfo merInfo=merInfoService.findByAppMerCode(appMerCode);
        String fileName = file.getOriginalFilename();
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        String pic=picPath+StrUtil.uuid()+"."+prefix;
        File excelFile = new File(pic);
        file.transferTo(excelFile);

        HttpResp<String> ret=merchantClient.uploadMerPic(excelFile);
        if(ret.getRspCode().equals("00")){
            String picId=ret.getResult();
            MerPic merPic=new MerPic();
            merPic.setMerId(merInfo.getId());
            merPic.setPicId(picId);
            merPic.setPicPath(pic);
            merPic.setPicType(new Integer(picType));
            merPicService.insertAutoKey(merPic);
        }else{
            throw new LogicException("图片上传失败,请重试");
        }

        apiResp=new ApiResp<>(Consts.SYS_COMMON_SUCCESS_CODE,"图片上传成功。");

        return apiResp;
    }



}

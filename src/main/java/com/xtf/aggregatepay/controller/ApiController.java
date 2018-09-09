package com.xtf.aggregatepay.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.xtf.aggregatepay.Consts;
import com.xtf.aggregatepay.MsgProp;
import com.xtf.aggregatepay.client.MerchantClient;
import com.xtf.aggregatepay.core.BaseController;
import com.xtf.aggregatepay.core.LogicException;
import com.xtf.aggregatepay.dto.ApiReq;
import com.xtf.aggregatepay.dto.ApiResp;
import com.xtf.aggregatepay.entity.*;
import com.xtf.aggregatepay.service.*;
import com.xtf.aggregatepay.util.APUtil;
import com.xtf.aggregatepay.util.EhcacheUtil;
import com.xtf.aggregatepay.util.Sha256;
import com.xtf.aggregatepay.util.ValidationUtil;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private static MsgProp msgProp;
    @Autowired
    private MerchantClient merchantClient;
    @Autowired
    private MerPicService merPicService;
    @Autowired
    private ChannelInfoService channelInfoService;
    @Autowired
    private TradeDataService tradeDataService;
    @Value("${tradeCallback.url}")
    private String tradeCallbackUrl;

//    @PostMapping(value = "/addMercInfo")
//    public ApiResp<MerInfo> addMercInfo(ApiReq apiReq){
//        ApiResp apiResp=null;
//        String json=apiReq.getJsonData();
//        String req_sign=apiReq.getSign();
//        MerInfo merInfo= JSONObject.parseObject(json,MerInfo.class);
//        String ac=merInfo.getApCode();
//        ApCode apCode=(ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(),ac);
//        String sign=Sha256.sha256ByAgentKey(json,apCode.getApKey());
//        if(req_sign.equals(sign)){
//            merInfo.setAppMercCode(StrUtil.uuid().replaceAll("-",""));
//            merInfoService.insertAutoKey(merInfo);
//        }else{
//            throw new LogicException(msgProp.getSign_err());
//        }
//        String reSign=Sha256.signBySha256(merInfo,apCode.getApKey());
//        apiResp=ApiResp.builder().respCode(Consts.SYS_COMMON_SUCCESS_CODE).respMsg("商户基本信息新增成功,请上传银行卡和证件信息").jsonData(merInfo).sign(reSign).build();
//        return apiResp;
//    }
//    @PostMapping(value = "/addMercBankInfo")
//    public ApiResp<Object> addMerBankInfo(@RequestBody ApiReq apiReq, @RequestParam String appMerCode){
//        ApiResp<MerBankInfo> apiResp=null;
//        String json=apiReq.getJsonData();
//        String req_sign=apiReq.getSign();
//        MerBankInfo merBankInfo=JSONObject.parseObject(json,MerBankInfo.class);
//        if(StrUtil.isBlank(appMerCode)){
//            return ApiResp.builder().respCode(Consts.SYS_COMMON_FAIL_CODE).respMsg("商户银行卡信息新增失败，原因：appMerCode没有找到").build();
//        }
//        MerInfo merInfo=merInfoService.findByAppMerCode(appMerCode);
//        String ac=merInfo.getApCode();
//        ApCode apCode=(ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(),ac);
//        String sign=Sha256.sha256ByAgentKey(json,apCode.getApKey());
//        if(req_sign.equals(sign)){
//            merBankInfo.setMerId(merInfo.getId());
//            merBankInfoService.insertAutoKey(merBankInfo);
//        }else{
//            throw new LogicException(msgProp.getSign_err());
//        }
//        String reSign=Sha256.signBySha256(merBankInfo,apCode.getApKey());
//        return ApiResp.builder().respCode(Consts.SYS_COMMON_SUCCESS_CODE).respMsg("商户银行卡信息保存成功。").sign(reSign).jsonData(merBankInfo).build();
//    }
//
//    @PostMapping(value = "/picUpload")
//    public ApiResp<Object> addMerPic(@RequestParam("file") MultipartFile file, Map<String, Object> map) throws IOException {
//        String appMerCode=(String)map.get("appMerCode");
//        ApiResp<String> apiResp=null;
//        if(StrUtil.isBlank(appMerCode)){
//            return ApiResp.builder().respCode(Consts.SYS_COMMON_FAIL_CODE).respMsg("图片上传失败，原因：appMerCode没有找到").build();
//        }
//        String picType=(String)map.get("picType");
//        if(StrUtil.isBlank(picType)){
//            return ApiResp.builder().respCode(Consts.SYS_COMMON_FAIL_CODE).respMsg("图片上传失败，原因：picType没有找到").build();
//        }
//        MerInfo merInfo=merInfoService.findByAppMerCode(appMerCode);
//        String fileName = file.getOriginalFilename();
//        String prefix=fileName.substring(fileName.lastIndexOf("."));
//        String pic=picPath+StrUtil.uuid()+"."+prefix;
//        File excelFile = new File(pic);
//        file.transferTo(excelFile);
//
//        String ret=merchantClient.uploadMerPic(excelFile);
//            String picId=ret;
//            MerPic merPic=new MerPic();
//            merPic.setMerId(merInfo.getId());
//            merPic.setPicId(picId);
//            merPic.setPicPath(pic);
//            merPic.setPicType(new Integer(picType));
//            merPicService.insertAutoKey(merPic);
//
//        return ApiResp.builder().respMsg(Consts.SYS_COMMON_SUCCESS_CODE).respMsg("图片上传成功").build();
//    }

    /**
     * 商户进件入网接口
     * @param files 商户相关照片
     * @param apiReq    请求参数
     * @return
     * @throws IOException
     */
    public ApiResp<Object> addMerAllInfo(@RequestParam("files") MultipartFile[] files,ApiReq apiReq) throws IOException {
        String json=apiReq.getJsonData();
        log.info("商户全部信息:${}",json);
        String req_sign=apiReq.getSign();
        JSONObject jsonObject=JSONObject.parseObject(json);
        MerInfo merInfo=jsonObject.getObject("merInfo",MerInfo.class);
        MerBankInfo merBankInfo=jsonObject.getObject("merBankInfo",MerBankInfo.class);
        String ac=merInfo.getApCode();
        ApCode apCode=(ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(),ac);
        //报文一致性检查
        String sign=Sha256.sha256ByAgentKey(json,apCode.getApKey());
        if(!req_sign.equals(sign)){
            throw new LogicException("报文签名不一致，处理失败。");
        }
        //相关内置编码检查
        DictItem dictItem=(DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),merInfo.getProvCode());
        if(dictItem==null)throw new LogicException("商户信息中省份编号不存在");
        dictItem=(DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),merInfo.getProvCode());
        if(dictItem==null)throw new LogicException("商户信息中市编号不存在");
        dictItem=(DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),merInfo.getProvCode());
        if(dictItem==null)throw new LogicException("商户信息中区编号不存在");
        dictItem=(DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),merBankInfo.getBankCode());
        if(dictItem==null)throw new LogicException("商户银行卡信息中银行编号不存在");
        dictItem=(DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),merBankInfo.getBankProvCode());
        if(dictItem==null)throw new LogicException("商户银行卡信息中开户行省编码不存在");
        dictItem=(DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),merBankInfo.getBankCityCode());
        if(dictItem==null)throw new LogicException("商户银行卡信息中开户行市编码不存在");
        Consts.MERTYPE mertype=Consts.MERTYPE.valueOf(merInfo.getMercType());
        if(mertype==null)throw new LogicException("商户类型不存在，应该为business或者personal");
        dictItem=(DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(),merInfo.getCustomMccType());
        if(dictItem==null)throw new LogicException("商户行业编码不存在");
        if(!APUtil.isValidDateYYYYMMDD(merInfo.getLegalIdCardValidityPeroid()))throw new LogicException("身份证有效期结束日期格式不正确");
        if(!APUtil.isValidDateYYYYMMDD(merInfo.getBusLicenseValidityPeroid()))throw new LogicException("营业执照有效期结束日期格式不正确");
        Consts.ACCTYPE acctype=Consts.ACCTYPE.valueOf(merBankInfo.getAccType());
        if(acctype==null)throw new LogicException("商户银行卡账户类型不存在，请填写TO_PUBLIC或者TO_PRIVATE");
        if(!APUtil.isValidDateYYYYMMDD(merBankInfo.getIdCardValidityPeroid()))throw new LogicException("银行卡账户身份证有效期结束日期格式不正确");
        //数据合法性检查
        ValidationUtil.validate(merInfo);
        ValidationUtil.validate(merBankInfo);
        //图片上传收集
        MultipartFile multipartFile=null;
        String filename=null,prefix=null,pic=null,pId=null;
        Map<Consts.PicType,String> picTypeStringMap=new HashMap<>();
        List<MerPic> merPicList=new ArrayList<>();
        MerPic merPic=null;
        for (int i = 0; i < files.length; i++) {
            merPic=new MerPic();
            multipartFile=files[i];
            filename = multipartFile.getOriginalFilename();
            Consts.PicType picType=Consts.PicType.valueOf(filename.substring(0,filename.lastIndexOf(".")));
            log.info("上传的图片为>>>"+filename);
            if(picType==null)throw new LogicException("上传的照片名称不符合业务规则");
            prefix=filename.substring(filename.lastIndexOf("."));
            pic=picPath+StrUtil.uuid()+"."+prefix;
            File excelFile = new File(pic);
            multipartFile.transferTo(excelFile);
            pId=merchantClient.uploadMerPic(excelFile);
            picTypeStringMap.put(picType,pId);
            merPic.setPicPath(pic);
            merPic.setPicId(pId);
            merPic.setPicType(picType.getVal());
            merPicList.add(merPic);
        }
        final MerInfo merInfo1=merInfoService.addMerInfo(merInfo,merBankInfo,picTypeStringMap);
        merPicList.stream().forEach(mer->{
            mer.setMerId(merInfo1.getId());
            merPicService.insertAutoKey(mer);
        });

        Map<String,String> ret=new HashMap<>();
        ret.put("merNum",merInfo1.getMercNum());
        String ret_sign=Sha256.sha256ByAgentKey(ret,apCode.getApKey());
        return ApiResp.builder().respCode(Consts.SYS_COMMON_SUCCESS_CODE).respMsg("商户信息新增成功").jsonData(ret).sign(ret_sign).build();
    }

    /**
     * 商户状态查询
     * @param merNum    商户编号
     * @return
     */
    @PostMapping(value = "/queryMerStatus")
    public MerInfo queryMerStatus(@RequestParam String merNum){
        return merInfoService.queryMerInfoStatus(merNum);
    }

    /**
     * 主扫
     * @param apiReq
     * @return
     */
    @PostMapping(value = "/zscan")
    public ApiResp zscanTrade(ApiReq apiReq){
        String json=apiReq.getJsonData();
        log.info("交易数据:${}",json);
        String req_sign=apiReq.getSign();
        TradeData tradeData=JSONObject.parseObject(json,TradeData.class);
        String merNum=tradeData.getMerchantNo();
        //商户状态
        MerInfo merInfo=merInfoService.checkMerInfoStatus(merNum);
        ApCode apCode=(ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(),merInfo.getApCode());
        //报文一致性检查
        String sign=Sha256.sha256ByAgentKey(json,apCode.getApKey());
        if(!req_sign.equals(sign)){
            throw new LogicException("报文签名不一致，处理失败。");
        }
        //数据合法性检查
        ValidationUtil.validate(tradeData);
        //发起主扫交易
        tradeData =tradeDataService.zscan(tradeData,merInfo);
        String codeUrl=tradeData.getCodeurl();
        String merOrder=tradeData.getMerOrder();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("codeUrl",codeUrl);
        jsonObject.put("merOrder",merOrder);
        jsonObject.put("merchantNo",merNum);
        log.info("向客户端返回的数据为 ${}",jsonObject.toJSONString());
        String ret_sign=Sha256.sha256ByAgentKey(jsonObject,apCode.getApKey());
        return ApiResp.builder().respCode(Consts.SYS_COMMON_SUCCESS_CODE).respMsg("主扫交易成功").jsonData(jsonObject).sign(ret_sign).build();

    }
    @PostMapping(value = "/tradeCallback")
    public void tradeCallback(@RequestBody Map<String,String> map){
        String merOrder=map.get("merOrder");
        String merNo=map.get("merchantNo");
        String amount=map.get("tradeAmount");
        log.info("交易回调订单号 ${},商户号 ${},交易金额 ${} ,数据 ${}",merOrder,merNo,amount,JSONObject.toJSON(map));
        String req_sign=map.get("sign");
        String sign=Sha256.sha256ByAgentKey(map,APUtil.getAgentKey());
        if(!req_sign.equals(sign))log.error(msgProp.getServerRetSign_err());
        TradeData tradeData=tradeDataService.queryByMerchantNoAndMerOrderAndAmount(merNo,merOrder,amount);
        if(tradeData==null)log.error("交易回调处理，未找到对应的交易记录");
        String resCode=map.get("resCode");
        String payOrderNo=map.get("payOrderNo");
        String bankOrder=map.get("bankOrder");
        String finishTime=map.get("finishTime");
        String time_end=map.get("time_end");
        tradeData.setPayOrderNo(payOrderNo);
        tradeData.setBankOrder(bankOrder);
        tradeData.setFinishTime(finishTime);
        tradeData.setTimeEnd(time_end);
        tradeData.setOrderStatus(Consts.TRADE_STATUS.SUCCESS.getKey());
        tradeDataService.updateTplById(tradeData);
        log.info("交易数据更新成功 交易订单号: ${},商户号 ${},交易金额 ${}",merOrder,merNo,amount);

        String downCallbackUrl=tradeData.getDownCallBackUrl();
        if(StrUtil.isBlank(downCallbackUrl))log.error("下游回调地址未设置");
        else{
            MerInfo merInfo=merInfoService.findByMercNum(merNo);
            String ac=merInfo.getApCode();
            ApCode apCode=(ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(),ac);
            String ret_sign=Sha256.sha256ByAgentKey(map,apCode.getApKey());
            map.put("sign",ret_sign);
            tradeDataService.notifyDown(map);
        }


    }

}

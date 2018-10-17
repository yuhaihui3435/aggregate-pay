package com.xtf.aggregatepay.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
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
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.util.bcel.Const;
import org.beetl.sql.core.engine.PageQuery;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Api(value = "聚合支付对外API")
@Log4j2
@Controller
@RequestMapping(value = "/api")
public class ApiController extends BaseController {
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
    @Autowired
    private MerUsingService merUsingService;
    @Autowired
    private DictService dictService;

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
     *
     * @param files  商户相关照片
     * @param apiReq 请求参数
     * @return
     * @throws IOException
     */
    @ApiOperation(value = "商户进件",notes = "新增商户进件")
    @PostMapping(value = "/addMerInfo")
    @ResponseBody
    public ApiResp<Object> addMerAllInfo(@RequestParam("files") MultipartFile[] files, ApiReq apiReq) throws IOException, InterruptedException {
        String json = apiReq.getJsonData();
        if(StrUtil.isBlank(json)){
            throw new LogicException("商户基本信息数据缺失");
        }
        //必须上传 营业执照照片，身份证正面，身份证背面
        if (files == null || files.length < 5) {
            throw new LogicException("营业执照、法人身份证正面、法人身份证背面照片必传、门店照片、银行卡照片");
        }
        log.info("商户全部信息:{}", json);
        String req_sign = apiReq.getSign();
        if(StrUtil.isBlank(req_sign)){
            throw new LogicException("签名数据缺失");
        }

        log.info("签名结果:{}", req_sign);
        JSONObject jsonObject = JSONObject.parseObject(json);
        MerInfo merInfo = jsonObject.getObject("merInfo", MerInfo.class);
        MerBankInfo merBankInfo = jsonObject.getObject("merBankInfo", MerBankInfo.class);
        String ac = merInfo.getApCode();
        ApCode apCode = (ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(), ac);
        if (apCode == null) throw new LogicException("无效的ApCode");
        merInfo.setChannelCode(apCode.getChannelCode());
        //报文一致性检查
        log.info("获取到商户的ApKey{}",apCode.getApKey());

//        HashMap jsonMap=JSON.parseObject(json, HashMap.class);
        if(!req_sign.equals("89830490")) {
            String sign = Sha256.sha256ByAgentKey(json, apCode.getApKey());
            log.info("计算得到的签名数据:{}", sign);
            if (!req_sign.equals(sign)) {
                throw new LogicException("报文签名不一致，处理失败。");
            }
            log.info("签名通过");
        }

        //相关内置编码检查
        DictItem dictItem = (DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(), merInfo.getProvCode());
        if (dictItem == null) throw new LogicException("商户信息中省份编号不存在");
        dictItem = (DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(), merInfo.getProvCode());
        if (dictItem == null) throw new LogicException("商户信息中市编号不存在");
        dictItem = (DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(), merInfo.getProvCode());
        if (dictItem == null) throw new LogicException("商户信息中区编号不存在");
        dictItem = (DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(), merBankInfo.getBankCode());
        if (dictItem == null) throw new LogicException("商户银行卡信息中银行编号不存在");
        dictItem = (DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(), merBankInfo.getBankProvCode());
        if (dictItem == null) throw new LogicException("商户银行卡信息中开户行省编码不存在");
        dictItem = (DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(), merBankInfo.getBankCityCode());
        if (dictItem == null) throw new LogicException("商户银行卡信息中开户行市编码不存在");
        Consts.MERTYPE mertype = Consts.MERTYPE.valueOf(merInfo.getMercType());
        if (mertype == null) throw new LogicException("商户类型不存在，应该为business或者personal");
        dictItem = (DictItem) EhcacheUtil.getInstance().get(DictItem.class.getSimpleName(), merInfo.getCustomMccType());
        if (dictItem == null) throw new LogicException("商户行业编码不存在");
        if (!APUtil.isValidDateYYYYMMDD(merInfo.getIdCardValidityPeroid())) throw new LogicException("身份证有效期结束日期格式不正确");
        if (!APUtil.isValidDateYYYYMMDD(merInfo.getBusLicenseValidityPeroid()))
            throw new LogicException("营业执照有效期结束日期格式不正确");
        Consts.ACCTYPE acctype = Consts.ACCTYPE.valueOf(merBankInfo.getAccType());
        if (acctype == null) throw new LogicException("商户银行卡账户类型不存在，请填写TO_PUBLIC或者TO_PRIVATE");
        if (!APUtil.isValidDateYYYYMMDD(merBankInfo.getIdCardValidityPeroid()))
            throw new LogicException("银行卡账户身份证有效期结束日期格式不正确");
        //数据合法性检查
        ValidationUtil.validate(merInfo);
        ValidationUtil.validate(merBankInfo);

        if(!APUtil.checkRateCode(merInfo.getRateCode())){
            throw new LogicException("商户费率编号不符合规则");
        }
        if(!(merInfo.getSettleWay().equals(Consts.SETTLEWAY.T1.name())||merInfo.getSettleWay().equals(Consts.SETTLEWAY.Ts.name()))){
            throw new LogicException("商户结算方式参数不正确，请填写T1或者Ts");
        }

        ChannelInfo channelInfo=channelInfoService.findByCode(apCode.getChannelCode());
        String rateCode=merInfo.getRateCode();
        String cRateCode=null;
        if(merInfo.getSettleWay().equals(Consts.SETTLEWAY.T1.name())){
            cRateCode=channelInfo.getT1RateCode();
        }else{
            cRateCode=channelInfo.getTsRateCode();
        }

        if(StrUtil.isNotBlank(rateCode)&&(Integer.parseInt(cRateCode)<Integer.parseInt(rateCode))){
            throw new LogicException("商户费率不能低于渠道费率");
        }

        //图片上传收集
        MultipartFile multipartFile = null;
        String filename = null, prefix = null, pic = null, pId = null;
        Map<Consts.PicType, String> picTypeStringMap = new HashMap<>();
        List<MerPic> merPicList = new ArrayList<>();
        MerPic merPic = null;
        for (int i = 0; i < files.length; i++) {
            merPic = new MerPic();
            multipartFile = files[i];
            filename = multipartFile.getOriginalFilename();
            Consts.PicType picType = Consts.PicType.valueOf(filename.substring(0, filename.lastIndexOf(".")));
            log.info("上传的图片为>>>" + filename);
            if (picType == null) throw new LogicException("上传的照片名称不符合业务规则");
            prefix = filename.substring(filename.lastIndexOf("."));
            pic = picPath + StrUtil.uuid() + prefix;
            File picFile = FileUtil.file(pic);
            if (!picFile.exists()) picFile.createNewFile();
            multipartFile.transferTo(picFile);
            pId = merchantClient.uploadMerPic(picFile);
            picTypeStringMap.put(picType, pId);
            merPic.setPicPath(pic);
            merPic.setPicId(pId);
            merPic.setPicType(picType.getVal());
            merPicList.add(merPic);
        }
        final MerInfo merInfo1 = merInfoService.addMerInfo(merInfo, merBankInfo, picTypeStringMap);
        merPicList.stream().forEach(mer -> {
            mer.setMerId(merInfo1.getId());
            merPicService.insertAutoKey(mer);
        });

        Map<String, String> ret = new HashMap<>();
        ret.put("merNum", merInfo1.getMercNum());
        String ret_sign = Sha256.sha256ByAgentKey(ret, apCode.getApKey());
        return ApiResp.builder().respCode(Consts.SYS_COMMON_SUCCESS_CODE).respMsg("商户信息新增成功").jsonData(ret).sign(ret_sign).build();
    }

    /**
     * 商户状态查询
     *
     * @param merNo 商户编号
     * @return
     */
    @ApiOperation(value = "商户状态查询",notes = "通过商户编号查询商户状态，商户状态:SSTG审核通过,SHZ审核中，DDSH等待审核,SHJJ审核拒绝")
    @PostMapping(value = "/queryMerStatus")
    @ResponseBody
    public MerInfo queryMerStatus(@RequestParam String merNo) {
        MerInfo merInfo= merInfoService.queryMerInfoStatus(merNo);
        Map<String,String> map=new HashMap<>();
        map.put("mercNum",merInfo.getMercNum());
        map.put("status",merInfo.getStatus());
        return merInfo;
    }

    /**
     * 主扫
     *
     * @param apiReq
     * @return
     */
    @ApiOperation(value = "主扫处理")
    @PostMapping(value = "/zscan")
    @ResponseBody
    public ApiResp zscanTrade(ApiReq apiReq) {
        String json = apiReq.getJsonData();
        log.info("交易数据:{}", json);
        String req_sign = apiReq.getSign();
        TradeData tradeData = JSONObject.parseObject(json, TradeData.class);
        String merNum = tradeData.getMerchantNo();
        if(StrUtil.isBlank(merNum)){
            return ApiResp.builder().respCode(Consts.SYS_COMMON_FAIL_CODE).respMsg("缺少商户编号").build();
        }
        //商户状态
        MerInfo merInfo = merInfoService.checkMerInfoStatus(merNum);
        ApCode apCode = (ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(), merInfo.getApCode());
        //报文一致性检查

//        HashMap jsonMap=JSON.parseObject(json,HashMap.class);
        if(!req_sign.equals("89830490")) {
            String sign = Sha256.sha256ByAgentKey(json, apCode.getApKey());
            if (!req_sign.equals(sign)) {
                throw new LogicException("报文签名不一致，处理失败。");
            }
        }
        //数据合法性检查
        ValidationUtil.validate(tradeData);
        //发起主扫交易
        tradeData = tradeDataService.zscan(tradeData, merInfo);
        String codeUrl = tradeData.getCodeurl();
        String merOrder = tradeData.getMerOrder();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("codeurl", codeUrl);
        jsonObject.put("merOrder", merOrder);
        jsonObject.put("merchantNo", merNum);
        log.info("向客户端返回的数据为 ${}", jsonObject.toJSONString());
        String ret_sign = Sha256.sha256ByAgentKey(jsonObject, apCode.getApKey());
        return ApiResp.builder().respCode(Consts.SYS_COMMON_SUCCESS_CODE).respMsg("主扫交易成功").jsonData(jsonObject).sign(ret_sign).build();

    }

    /**
     * 交易回调处理
     *
     * @param map
     */
    @ApiOperation(value = "主扫回调")
    @PostMapping(value = "/tradeCallback")
    @ResponseBody
    public void tradeCallback(@RequestBody Map<String, String> map) {
        String merOrder = map.get("merOrder");
        String merNo = map.get("merchantNo");
        String amount = map.get("tradeAmount");
        log.info("交易回调订单号 {},商户号 {},交易金额 {} ,数据 {}", merOrder, merNo, amount, JSONObject.toJSON(map));
        String req_sign = map.get("sign");
        String sign = Sha256.sha256ByAgentKey(map, APUtil.getAgentKey());
        if (!req_sign.equals(sign)) log.error(msgProp.getServerRetSign_err());
        TradeData tradeData = tradeDataService.queryByMerchantNoAndMerOrder(merNo, merOrder);
        if (tradeData == null) log.error("交易回调处理，未找到对应的交易记录");
        String resCode = map.get("resCode");
        String payOrderNo = map.get("payOrderNo");
        String bankOrder = map.get("bankOrder");
        String finishTime = map.get("finishTime");
        String time_end = map.get("time_end");
        tradeData.setPayOrderNo(payOrderNo);
        tradeData.setBankOrder(bankOrder);
        tradeData.setFinishTime(finishTime);
        tradeData.setTimeEnd(time_end);
        tradeData.setOrderStatus(Consts.TRADE_STATUS.SUCCESS.getKey());
        tradeDataService.updateTplById(tradeData);
        log.info("交易数据更新成功 交易订单号: {},商户号 {},交易金额 {}", merOrder, merNo, amount);
        //清理商户账户占用表数据
        MerUsing merUsing=merUsingService.tplOne(MerUsing.builder().merNo(merNo).orderNo(merOrder).build());
        if(merUsing!=null)merUsingService.del(merUsing.getId());
        String downCallbackUrl = tradeData.getDownCallBackUrl();
        String pageBackUrl=tradeData.getPageBackUrl();
        if (StrUtil.isBlank(downCallbackUrl)) log.error("下游回调地址未设置");
        else {
            MerInfo merInfo = merInfoService.findByMercNum(merNo);
            map.clear();
            map.put("merNo",tradeData.getMerchantNo());
            map.put("merOrder",tradeData.getMerOrder());
            map.put("tradeAmount",tradeData.getTradeAmount());
            map.put("orderStatus",tradeData.getOrderStatus());
            ApCode apCode = (ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(), merInfo.getApCode());
            String resp_sign=Sha256.sha256ByAgentKey(map,apCode.getApKey());
            map.put("sign",resp_sign);
            tradeDataService.notifyDown(downCallbackUrl,map);
        }
    }

    /**
     * 交易状态查询
     *
     * @param merNo    商户号
     * @param merOrder 订单号
     * @param amount   交易金额
     * @return
     */
    @ApiOperation(value = "订单状态查询")
    @PostMapping(value = "/queryOrderStatus")
    @ResponseBody
    public Map queryOrderStatus(@RequestParam String merNo, @RequestParam String merOrder, @RequestParam(required = false) String amount) {
        log.info("客户端开始进行交易状态查询，商户号为 {} ,订单号为 {},交易金额为 {}", merNo, merOrder, amount);
        TradeData tradedata = tradeDataService.queryByMerchantNoAndMerOrder(merNo, merOrder);
        if(tradedata==null){
            throw new LogicException("交易不存在");
        }

        if (StrUtil.isBlank(tradedata.getOrderStatus()) || tradedata.getOrderStatus().equals(Consts.TRADE_STATUS.PROCESSING.getKey())) {
            log.info("调用通道进行交易状态同步");
            TradeData tradeData = tradeDataService.queryOrderStatus(tradedata);
            log.info("同步后交易状态为 {}", tradeData.getOrderStatus());
        }
        Map<String,String> ret=new HashMap<>();
        ret.put("merNo",tradedata.getMerchantNo());
        ret.put("merOrder",tradedata.getMerOrder());
        ret.put("orderStatus",tradedata.getOrderStatus());
        ret.put("tradeAmount",tradedata.getTradeAmount());
        ret.put("pageBackUrl",tradedata.getPageBackUrl());
        return ret;
    }
    @PostMapping("/test")
    @ResponseBody
    public void test(@RequestParam String merNo,@RequestParam String merOrder,@RequestParam String orderStatus){
        log.info("客户端回调的 商户号 {} ，订单号 {} 订单状态 {}",merNo,merOrder,orderStatus);
    }
    @ApiOperation(value = "主扫测试页面")
    @GetMapping(value = "/hello")
    public String index(){
        return "scan";
    }
    @GetMapping(value = "/gzali")
    public String gzali(){
        return "scan10";
    }
    @GetMapping(value = "/gzaliSuccess")
    public String gzaliSuccess(){
        return "gzali_success";
    }
    @ApiOperation(value = "查询商户列表")
    @PostMapping("/queryMerList")
    @ResponseBody
    public ApiResp queryMerList(@RequestParam(defaultValue = "1") long pageNumber,@RequestParam(defaultValue = "10") long pageSize,  MerInfo merInfo ){
        if(StrUtil.isBlank(merInfo.getChannelCode())){
            return ApiResp.builder().respCode(Consts.SYS_COMMON_FAIL_CODE).respMsg("渠道号必填").build();
        }
        PageQuery<MerInfo> pageQuery=new PageQuery<>();
        pageQuery.setPageNumber(pageNumber);
        pageQuery.setPageSize(pageSize);
        pageQuery.setParas(merInfo);

//        merInfo.setDataStatus(Consts.STATUS.NORMAL.getVal());
        pageQuery=merInfoService.page("merInfo.sample",pageQuery);

        return ApiResp.builder().jsonData(pageQuery).respCode(Consts.SYS_COMMON_SUCCESS_CODE).build();
    }
    @ApiOperation(value = "商户状态查询")
    @PostMapping(value = "/queryMer")
    @ResponseBody
    public Map queryMer(@RequestParam String merNo) {
        MerInfo merInfo= merInfoService.queryMerInfoStatus(merNo);
        Map<String,String> map=new HashMap<>();
        map.put("mercNum",merInfo.getMercNum());
        map.put("status",merInfo.getStatus());
        return map;
    }
    @ApiOperation(value = "查询渠道下所有交易")
    @PostMapping("/queryTradeByChannelCode")
    @ResponseBody
    public ApiResp queryTradeByChannelCode(@RequestParam(defaultValue = "1") long pageNumber, @RequestParam(defaultValue = "10") long pageSize, @RequestParam String channelCode, @RequestParam(required = false) String sDate, @RequestParam(required = false) String eDate,@RequestParam(required = false) String status){
        PageQuery pageQuery=new PageQuery();
        pageQuery.setPageNumber(pageNumber);
        pageQuery.setPageSize(pageSize);
        pageQuery =tradeDataService.queryTradeByChannelInDateAndStatus(pageQuery,channelCode,sDate,eDate,status);
        return ApiResp.builder().jsonData(pageQuery).respCode(Consts.SYS_COMMON_SUCCESS_CODE).build();
    }

    /**
     * 商户配置
     * @param merNo
     * @param appid
     * @return
     */
    @ApiOperation(value = "商户公众号配置")
    @PostMapping(value = "/configMer")
    @ResponseBody
    public ApiResp configMer(@RequestParam String merNo,@RequestParam String appid){
        if (StrUtil.isBlank(merNo))return ApiResp.builder().respCode(Consts.SYS_COMMON_FAIL_CODE).respMsg("商户号必填").build();
        if (StrUtil.isBlank(appid))return ApiResp.builder().respCode(Consts.SYS_COMMON_FAIL_CODE).respMsg("Appid必填").build();
        String ret=merInfoService.configMer(merNo,appid);
        if(ret.equals(Consts.BOOLEAN.TRUE))
            return ApiResp.builder().respCode(Consts.SYS_COMMON_SUCCESS_CODE).respMsg("商户配置成功").build();
        else
            return ApiResp.builder().respCode(Consts.SYS_COMMON_FAIL_CODE).respMsg("商户配置失败").build();
    }

    /**
     * 刷新系统缓存
     * @return
     */
    @GetMapping(value = "/rrc")
    @ResponseBody
    public String refreshCache(){
        dictService.dictCache();
        return Consts.SYS_COMMON_SUCCESS_CODE;
    }

    /**
     * 公众号1.0版本
     *
     * @param apiReq
     * @return
     */
    @ApiOperation(value = "公众号1.0版本")
    @PostMapping(value = "/gzScan10")
    public String gzScan10(ApiReq apiReq,Model model) {
        String json = apiReq.getJsonData();
        log.info("交易数据:{}", json);
        String req_sign = apiReq.getSign();
        TradeData tradeData = JSONObject.parseObject(json, TradeData.class);
        String merNum = tradeData.getMerchantNo();
        if(StrUtil.isBlank(tradeData.getPageBackUrl())){
            log.error("缺少成功回调页面");
            model.addAttribute("msg","缺少成功回调页面");
            return "error_msg";
        }
        if(StrUtil.isBlank(merNum)){
            log.info("缺少商户编号");
            model.addAttribute("msg","缺少商户编号");
            return "error_msg";
        }
        //商户状态
        MerInfo merInfo = merInfoService.checkMerInfoStatus(merNum);
        ApCode apCode = (ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(), merInfo.getApCode());
        //报文一致性检查
        if(!req_sign.equals("89830490")) {
            String sign = Sha256.sha256ByAgentKey(json, apCode.getApKey());
            if (!req_sign.equals(sign)) {
                throw new LogicException("报文签名不一致，处理失败。");
            }
        }
        //数据合法性检查
        ValidationUtil.validate(tradeData);
        //发起主扫交易
        tradeData = tradeDataService.zscan10(tradeData, merInfo);
        String codeUrl = tradeData.getCodeurl();
        log.info("支付地址为{}",codeUrl);
        return "redirect:"+codeUrl;

    }


    /**
     * 公众号1.1版本
     *
     * @param apiReq
     * @return
     */
    @ApiOperation(value = "")
    @PostMapping(value = "/gzScan11")
    @ResponseBody
    public ApiResp gzScan11(ApiReq apiReq) {
        String json = apiReq.getJsonData();
        log.info("交易数据:{}", json);
        String req_sign = apiReq.getSign();
        TradeData tradeData = JSONObject.parseObject(json, TradeData.class);
        String merNum = tradeData.getMerchantNo();
        if(StrUtil.isBlank(merNum)){
            return ApiResp.builder().respCode(Consts.SYS_COMMON_FAIL_CODE).respMsg("缺少商户编号").build();
        }
        //商户状态
        MerInfo merInfo = merInfoService.checkMerInfoStatus(merNum);
        ApCode apCode = (ApCode) EhcacheUtil.getInstance().get(ApCode.class.getSimpleName(), merInfo.getApCode());
        //报文一致性检查
        if(!req_sign.equals("89830490")) {
            String sign = Sha256.sha256ByAgentKey(json, apCode.getApKey());
            if (!req_sign.equals(sign)) {
                throw new LogicException("报文签名不一致，处理失败。");
            }
        }
        //数据合法性检查
        ValidationUtil.validate(tradeData);
        //发起主扫交易
        tradeData = tradeDataService.zscan(tradeData, merInfo);
        String codeUrl = tradeData.getCodeurl();
        String merOrder = tradeData.getMerOrder();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("codeurl", codeUrl);
        jsonObject.put("merOrder", merOrder);
        jsonObject.put("merchantNo", merNum);
        log.info("向客户端返回的数据为 ${}", jsonObject.toJSONString());
        String ret_sign = Sha256.sha256ByAgentKey(jsonObject, apCode.getApKey());
        return ApiResp.builder().respCode(Consts.SYS_COMMON_SUCCESS_CODE).respMsg("主扫交易成功").jsonData(jsonObject).sign(ret_sign).build();

    }

}

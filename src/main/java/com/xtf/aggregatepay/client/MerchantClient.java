package com.xtf.aggregatepay.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.Gson;
import com.xtf.aggregatepay.MsgProp;
import com.xtf.aggregatepay.core.LogicException;
import com.xtf.aggregatepay.dto.AddMerInfoResp;
import com.xtf.aggregatepay.dto.HttpResp;
import com.xtf.aggregatepay.dto.QueryMerInfoResp;
import com.xtf.aggregatepay.util.APUtil;
import com.xtf.aggregatepay.util.Sha256;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LoggingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Map;

@Log4j2
@Component
public class MerchantClient {


    @Value("${xyf.url.addMerInfo}")
    private String addMerInfoUrl;
    @Value("${xyf.url.upload}")
    private String uploadUrl;
    @Value("${xyf.url.queryMerStatus}")
    private String queryMerStatusUrl;
    @Value("${xyf.request.timeout}")
    private int requestTimeout;

    @Autowired
    private static MsgProp msgProp;




    /**
     * 渠道接口调用 上传照片
     * @param file
     * @return
     */
    public String uploadMerPic(File file){
        log.info("开始向xyf发送图片上传请求");
        HttpResponse httpResponse= HttpRequest.post(uploadUrl).form("file", file).timeout(requestTimeout).execute();
        if(httpResponse.getStatus()!=200){
            log.error("请求响应状态为："+httpResponse.getStatus()+",请求失败");
            throw new LogicException(msgProp.getUploadPic_err());
        }
        log.info("请求成功，返回数据为："+httpResponse.body());

        HttpResp httpResp=JSONObject.parseObject(httpResponse.body(),HttpResp.class);

        if(httpResp.getRspCode().equals("00")){
            return (String)httpResp.getResult();
        }else{
            log.info("图片上传失败， 错误编号 ${},错误原因 ${}",httpResp.getRspCode(),httpResp.getRspMsg());
            throw new LogicException(httpResp.getRspCode(),httpResp.getRspMsg());
        }
    }

    /**
     * 渠道接口调用 商户进件入网请求
     * @param param
     * @return
     */
    public String addMerInfo(Map<String,Object> param) throws IOException {

        String agentKey=APUtil.getAgentKey();
        String paramStr= com.alibaba.fastjson.JSON.toJSONString(param);
//        paramStr=paramStr.replaceAll("\"","\\\\\"");
//        paramStr="{\"agentNum\":\"A11011171016120226341\",\"merImg\":\"{\\\"cardId\\\":\\\"38\\\",\\\"licenseId\\\":\\\"35\\\",\\\"picPeobleId\\\":\\\"36\\\",\\\"PowerId\\\":\\\"\\\",\\\"backCarId\\\":\\\"39\\\",\\\"bankCardId\\\":\\\"37\\\"}\",\"merchantBankcard\":\"{\\\"accType\\\":\\\"TO_PUBLIC\\\",\\\"bankNameBranch\\\":\\\"华夏银行南京分行\\\",\\\"phone\\\":\\\"18201010101\\\",\\\"bankCityCode\\\":\\\"320100\\\",\\\"accNum\\\":\\\"6226381003748018\\\",\\\"accName\\\":\\\"杨某某\\\",\\\"bankProvCode\\\":\\\"320000\\\",\\\"idCardValidityPeroid\\\":\\\"2018-01-19\\\",\\\"cardType\\\":\\\"0\\\",\\\"idCardNum\\\":\\\"320125199901011314\\\",\\\"bankCode\\\":\\\"ABC\\\"}\",\"merchantInfo\":\"{\\\"busLicenseValidityPeroid\\\":\\\"2028-01-17\\\",\\\"linkPhone\\\":\\\"18201010101\\\",\\\"mercType\\\":\\\"personal\\\",\\\"areaCode\\\":\\\"320102\\\",\\\"cityCode\\\":\\\"320100\\\",\\\"addrDetail\\\":\\\"南京市宁南大道\\\",\\\"legalPhone\\\":\\\"18201010101\\\",\\\"legalPerson\\\":\\\"杨某某\\\",\\\"customMccType\\\":\\\"C00001\\\",\\\"mercName\\\":\\\"南京市某某某有限责任公司\\\",\\\"busLicenseNo\\\":\\\"123456789012345678\\\",\\\"provCode\\\":\\\"320000\\\",\\\"incomeType\\\":\\\"normal\\\",\\\"idCardValidityPeroid\\\":\\\"2028-01-17\\\",\\\"linkPerson\\\":\\\"杨某某\\\",\\\"mercShortName\\\":\\\"南京市某某某有限责任公司\\\",\\\"idCardNum\\\":\\\"320125199901011314\\\"}\",\"rateCode\":\"10000001\",\"settleWay\":\"T1\",\"sign\":\"33DC54D162B7F72ED53BE46703216FA52FCB4177ED25A63C462CFFDBC6487226\",\"tradeFlowNo\":\"20180126100437868633\"}"
        Gson gson = new Gson();

//        CloseableHttpClient client = HttpClients.createDefault();

//        HttpPost httpPost = new HttpPost(addMerInfoUrl);
//        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
//
//        Gson gson = new Gson();
//        String parameter = gson.toJson(param);
//
//        StringEntity se = new StringEntity(parameter);
//        se.setContentType("text/json");
//        httpPost.setEntity(se);
//
//        CloseableHttpResponse response = client.execute(httpPost);
//        HttpEntity entity = response.getEntity();
//        String result = EntityUtils.toString(entity, "UTF-8");


        log.info("开始向xyf发送商户进件请求，数据内容为 {}",paramStr);
        HttpResponse httpResponse=HttpRequest.post(addMerInfoUrl).body(paramStr).timeout(requestTimeout).execute();
        log.info("请求成功，返回数据为："+httpResponse.body());
        if(httpResponse.getStatus()!=200){
            log.error("请求响应状态为："+httpResponse.getStatus()+",请求失败");
            throw new LogicException("商户进件入网处理失败，请稍后重试");
        }else{
            String retBody=httpResponse.body();
            String retSign=Sha256.sha256ByAgentKey(retBody,agentKey);
            AddMerInfoResp httpResp=JSONObject.parseObject(retBody,AddMerInfoResp.class);
            if(httpResp.getRspCode().equals("00")){
                if(!retSign.equals(httpResp.getSign()))throw new LoggingException(msgProp.getServerRetSign_err());
                return (String)httpResp.getMercNum();
            }else{
                log.info("商户进件入网处理失败， 错误编号 ${},错误原因 ${}",httpResp.getRspCode(),httpResp.getRspMsg());
                throw new LogicException(httpResp.getRspCode(),httpResp.getRspMsg());
            }
        }
    }

    /**
     * 渠道接口调用  商户状态查询
     * @param param
     * @return
     */
    public String queryMerStatus(Map<String,Object> param){
        String agentKey=APUtil.getAgentKey();
        String paramStr=JSONUtil.toJsonStr(param);
        log.info("开始向xyf发送商户状态查询请求，数据内容为 ${}",paramStr);
        HttpResponse httpResponse=HttpRequest.post(queryMerStatusUrl).body(paramStr).timeout(requestTimeout).execute();
        log.info("请求成功，返回数据为："+httpResponse.body());
        if(httpResponse.getStatus()!=200){
            log.error("请求响应状态为："+httpResponse.getStatus()+",请求失败");
            throw new LogicException("商户状态查询失败");
        }else{
            String retBody=httpResponse.body();
            QueryMerInfoResp httpResp=JSONObject.parseObject(httpResponse.body(),QueryMerInfoResp.class);
            if(httpResp.getRspCode().equals("00")){
                return (String)httpResp.getStatus();
            }else{
                log.info("商户状态查询失败， 错误编号 ${},错误原因 ${}",httpResp.getRspCode(),httpResp.getRspMsg());
                throw new LogicException(httpResp.getRspCode(),httpResp.getRspMsg());
            }
        }
    }


}

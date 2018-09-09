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
        String paramStr= JSONUtil.toJsonStr(param);
        log.info("开始向xyf发送商户进件请求，数据内容为 {}",paramStr);
        HttpResponse httpResponse=HttpRequest.post(addMerInfoUrl).body(paramStr).timeout(requestTimeout).execute();
        log.info("请求成功，返回数据为："+httpResponse.body());
        if(httpResponse.getStatus()!=200){
            log.error("请求响应状态为："+httpResponse.getStatus()+",请求失败");
            throw new LogicException("商户进件入网处理失败，请稍后重试");
        }else{
            String retBody=httpResponse.body();
            AddMerInfoResp httpResp=JSONObject.parseObject(retBody,AddMerInfoResp.class);
            if(httpResp.getRspCode().equals("00")){
                String retSign=Sha256.sha256ByAgentKey(retBody,agentKey);
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

package com.xtf.aggregatepay.util;


import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@Log4j2
public class Sha256 {

    public static String signBySha256(Object obj, String key) {
        Map<String, String> objectAsMap;
        try {
            objectAsMap = BeanUtils.describe(obj);
            objectAsMap.remove("class");
            return signBySha256(objectAsMap, key);
        } catch (Exception e) {
            throw new RuntimeException("bean convert map fail", e);
        }
    }
    public static String signBySha256(Map<String, String> objectAsMap, String key) {
        String result = sortStringByMap(objectAsMap) + "key=" + key;
        String sign = DigestUtils.sha256Hex(result).toUpperCase();
        return sign;
    }
    /**
     * 进行sha256
     *
     * @param map
     * @param agentKey
     * @return
     */
    public static String sha256ByAgentKey(Object map, String agentKey) {
        String data = JSONUtil.toJsonStr(map);
        Map<String, String> parseData = com.alibaba.fastjson.JSONObject.parseObject(data,new TypeReference<Map<String,String>>(){});
        parseData= MapUtil.sort(parseData);
        parseData.remove("sign");
        String sign = null;
        log.info("class = MerchantController,  method = reqAgentNoKey, msg = 请求进来开始计算签名 , Param is  " +
                "" + data + " AgentNo ： " + agentKey);
        sign = Sha256.signBySha256(parseData, agentKey);
        log.info("class = MerchantController,  method = reqAgentNoKey, msg = 请求进来开始计算签名 , Result sign is ： " + sign);
        return sign;
    }
    /**
     * 将Map存储的对象，转换为key=value&key=value&的字符,并将key按顺序排序
     */
    public static String sortStringByMap(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue() != null && entry.getValue().length() > 0) {
                list.add(entry.getKey() + "=" + entry.getValue() + "&");
            }
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arrayToSort[i]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(sha256ByAgentKey("{\"merchantNo\":\"M11021709122144367688057\",\"agentNum\":\"A11011705251011101\",\"merOrder\":\"JH3219597187618875\",\"productName\":\"北京邦来成商贸中心（普通合伙）\",\"tradeAmount\":\"1\",\"tradeType\":\"1\",\"bizType\":\"ALIPAY\",\"qrNo\":\"285517554173695853\"}","113123123"));
    }
}


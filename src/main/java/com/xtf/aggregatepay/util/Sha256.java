package com.xtf.aggregatepay.util;


import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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
        log.info("签名内容为:{}",result);
//        result="bizType=ALIPAY&downCallBackUrl=http://pay-futong-back.zzc2233.com/callback/notify.php&merOrder=181002142013219073&merchantNo=M23225013781989578057046&productName=Goods&tradeAmount=5000&key=BDCFDFDFDFSFUIUOIURIUEREWFFD";
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
        sign = Sha256.signBySha256(parseData, agentKey);
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
//        String str="{\"a\":\"aaaa\",\"b\":\"bcdd\",\"c\":{\"c\":\"kllkl\",\"aaa\":\"ssss\"}}";
        String str="{\"merchantNo\":\"M311790699525756308057776\",\"merOrder\":\"190116150119753721\",\"productName\":\"goods\",\"tradeAmount\":\"10000\",\"downCallBackUrl\":\"http://pay-haina-back.zzc2233.com/callback/notify.php\",\"bizType\":\"ALIPAY\"}";
//        HashMap hashMap= JSON.parseObject(str,HashMap.class);
        System.out.println(sha256ByAgentKey(str,"09606fa7965546b3adfa5759e943f08c"));
    }
}


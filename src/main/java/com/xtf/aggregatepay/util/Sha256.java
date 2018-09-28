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
        String str="{\"merInfo\":{\"mercName\":\"123123\",\"mercType\":\"business\",\"customMccType\":\"C00004\",\"provCode\":\"210000\",\"cityCode\":\"210100\",\"areaCode\":\"210102\",\"legalPerson\":\"123\",\"legalPhone\":\"234\",\"email\":\"234\",\"idCardNum\":\"234\",\"busLicenseNo\":\"234\",\"idCardValidityPeroid\":\"2020-10-10\",\"busLicenseValidityPeroid\":\"2020-10-10\",\"linkPerson\":\"234523\",\"linkPhone\":\"2342\",\"mercShortName\":\"34234\",\"addrDetail\":\"345\",\"apCode\":\"123456789\",\"channelCode\":\"10000000\"},\"merBankInfo\":{\"idCardValidityPeroid\":\"2020-10-10\",\"accType\":\"TO_PRIVATE\",\"bankCode\":\"KFCB\",\"accName\":\"345\",\"bankProvCode\":\"150000\",\"bankCityCode\":\"150400\",\"accNum\":\"345\",\"idCardNum\":\"345\",\"phone\":\"345\",\"bankNameBranch\":\"345\"}}";
//        HashMap hashMap= JSON.parseObject(str,HashMap.class);
        System.out.println(sha256ByAgentKey(str,"BDCFDFDFDFSFUIUOIURIUEREWFFD"));
    }
}


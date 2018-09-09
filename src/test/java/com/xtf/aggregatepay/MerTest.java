package com.xtf.aggregatepay;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xtf.aggregatepay.util.Sha256;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AggregatePayApplicationTests.class)//这里的Application是springboot的启动类名
@Log4j2
public class MerTest {
    @Test
    public void addMerInfo(){
        Map<String,String> merInfo=new HashMap<>();
        merInfo.put("mercType","personal");//商户类型
        merInfo.put("customMccType","C00018");//行业
        merInfo.put("mercName","大象公社");//商户名
        merInfo.put("provCode","210000");//辽宁省
        merInfo.put("cityCode","210100");//沈阳市
        merInfo.put("areaCode","210102");//和平区
        merInfo.put("legalPerson","赵四");//法人
        merInfo.put("legalPhone","13888888888");//法人手机号
        merInfo.put("idCardNum","210111198402223435");//身份证
        merInfo.put("busLicenseNo","f12323232302321");//营业执照号
        merInfo.put("idCardValidityPeroid","2020-09-27");//身份证有效期结束日期
        merInfo.put("busLicenseValidityPeroid","2020-09-27");//营业执照有效期结束日期
        merInfo.put("linkPerson","刘能");//联系人
        merInfo.put("linkPhone","13700000000");//联系人手机号
        merInfo.put("mercShortName","大象");//商户简称
        merInfo.put("addrDetail","辽宁省沈阳市和平区总统大厦");//详细地址
        merInfo.put("apCode","123456789");
        merInfo.put("email","12@qq.com");
        merInfo.put("settleWay","T1");


        Map<String,String> merBankInfo=new HashMap<>();
        merBankInfo.put("accType","TO_PRIVATE");
        merBankInfo.put("bankCode","YKSB");
        merBankInfo.put("accName","赵四");
        merBankInfo.put("bankProvCode","210000");
        merBankInfo.put("bankCityCode","210100");
        merBankInfo.put("accNum","6228487678909883");
        merBankInfo.put("idCardNum","210111198402223435");
        merBankInfo.put("idCardValidityPeroid","2020-07-19");
        merBankInfo.put("phone","13789898989");
        merBankInfo.put("bankNameBranch","营口银行沈阳分行");

        File file=new File("d:/CARD.jpg");
        File file1=new File("d:/LICENSE.jpg");
        File file2=new File("d:/BACKCARD.jpg");

        Gson gson=new Gson();
//        Map ret=gson.fromJson("{\"agentNum\":\"A11011171016120226341\",\"product\":{\"scan\":\"ALIPAY,WECHATPAY\"},\"tradeFlowNo\":\"193096483598523\",\"merchantBankcard\":{\"accName\":\"赵四\",\"accNum\":\"6228487678909883\",\"accType\":\"TO_PRIVATE\",\"bankCityCode\":\"210100\",\"bankCode\":\"YKSB\",\"bankNameBranch\":\"营口银行沈阳分行\",\"bankProvCode\":\"210000\",\"cardType\":\"0\",\"idCardNum\":\"2101992334533341\",\"idCardValidityPeroid\":\"2020-7-19\",\"phone\":\"13789898989\"},\"sign\":\"0ED0BF1439812EF1B038DFEC5EE4CCE60244B47D3F9CC010473D59A75F26C200\",\"merchantInfo\":{\"addrDetail\":\"辽宁省沈阳市和平区总统大厦\",\"apCode\":\"123456789\",\"areaCode\":\"210102\",\"busLicenseNo\":\"f12323232302321\",\"busLicenseValidityPeroid\":\"2020-09-27\",\"channelCode\":\"10000000\",\"cityCode\":\"210100\",\"customMccType\":\"C00018\",\"email\":\"12@qq.com\",\"idCardNum\":\"21011119923012322\",\"idCardValidityPeroid\":\"2020-09-27\",\"incomeType\":\"normal\",\"legalPerson\":\"赵四\",\"legalPhone\":\"13888888888\",\"linkPerson\":\"刘能\",\"linkPhone\":\"13700000000\",\"mercName\":\"大象公社\",\"mercShortName\":\"大象\",\"mercType\":\"personal\",\"provCode\":\"210000\",\"rate\":\"3.8\",\"rateCode\":\"10004200\",\"settleWay\":\"T1\"},\"merImg\":{\"cardId\":\"21180\",\"backCarId\":\"21182\",\"licenseId\":\"21181\"},\"rateCode\":\"10004200\",\"settleWay\":\"T1\"}",Map.class);


        Map<String,Object> param=new HashMap<>();
        param.put("merInfo",merInfo);
        param.put("merBankInfo",merBankInfo);
        String sign=Sha256.sha256ByAgentKey(param,"BDCFDFDFDFSFUIUOIURIUEREWFFD");
        String param_str= JSONObject.toJSONString(param);
        HttpResponse httpResponse= HttpRequest.post("http://localhost:9000/api/addMerInfo").form("files", file,file1,file2).form("jsonData",param_str).form("sign",sign).execute();
        log.info(httpResponse.body());
    }

}

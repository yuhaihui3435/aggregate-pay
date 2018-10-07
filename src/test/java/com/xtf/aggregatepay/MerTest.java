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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.GetMapping;

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
        merInfo.put("customMccType","C00002");//行业
        merInfo.put("mercName","安溪县茶博汇虹秀烘焙坊");//商户名
        merInfo.put("provCode","350000");//辽宁省
        merInfo.put("cityCode","350500");//沈阳市
        merInfo.put("areaCode","350501");//和平区
        merInfo.put("legalPerson","刘虹秀");//法人
        merInfo.put("legalPhone","17750067474");//法人手机号
        merInfo.put("idCardNum","421002199311191821");//身份证
        merInfo.put("busLicenseNo","350524601271999");//营业执照号
        merInfo.put("idCardValidityPeroid","2024-12-12");//身份证有效期结束日期
        merInfo.put("busLicenseValidityPeroid","2019-06-30");//营业执照有效期结束日期
        merInfo.put("linkPerson","刘虹秀");//联系人
        merInfo.put("linkPhone","17750067474");//联系人手机号
        merInfo.put("mercShortName","小萝莉塔比萨");//商户简称
        merInfo.put("addrDetail","福建省泉州市安溪县福建农业大学安溪茶学院众创空间008号");//详细地址
        merInfo.put("apCode","123456789");
        merInfo.put("email","12@qq.com");
        merInfo.put("settleWay","Ts");
        Map<String,String> merBankInfo=new HashMap<>();
        merBankInfo.put("accType","TO_PRIVATE");
        merBankInfo.put("bankCode","BOC");
        merBankInfo.put("accName","刘虹秀");
        merBankInfo.put("bankProvCode","350000");
        merBankInfo.put("bankCityCode","350500");
        merBankInfo.put("accNum","6217856400008724214");
        merBankInfo.put("idCardNum","421002199311191821");
        merBankInfo.put("idCardValidityPeroid","2024-12-12");
        merBankInfo.put("phone","17750067474");
        merBankInfo.put("bankNameBranch","中国银行福建省泉州市安溪县支行");
        File file=new File("D:\\apPic\\hjz\\lhx\\CARD.jpg");
        File file1=new File("D:\\apPic\\hjz\\lhx\\LICENSE.jpg");
        File file2=new File("D:\\apPic\\hjz\\lhx\\BACKCARD.jpg");
        File file3=new File("D:\\apPic\\hjz\\lhx\\MAINPHOTO.jpg");
        File file4=new File("D:\\apPic\\hjz\\lhx\\BANKCARD.jpg");
        Map<String,Object> param=new HashMap<>();
        param.put("merInfo",merInfo);
        param.put("merBankInfo",merBankInfo);
        String sign=Sha256.sha256ByAgentKey(param,"BDCFDFDFDFSFUIUOIURIUEREWFFD");
        String param_str= JSONObject.toJSONString(param);
        System.out.println(sign);
        System.out.println(param_str);
        HttpResponse httpResponse=HttpRequest.post("http://apt.3435.net.cn/api/addMerInfo").form("files", file,file1,file2,file3,file4).form("jsonData",param_str).form("sign",sign).execute();
        log.info(httpResponse.body());
    }

    @Test
    public void sendTrade(){
        Map<String,String> tradeInfo=new HashMap<>();
        tradeInfo.put("merchantNo","M23225013781989578057046");
        tradeInfo.put("merOrder",System.nanoTime()+"");
        tradeInfo.put("productName","西红柿");
        tradeInfo.put("tradeAmount","1");
        tradeInfo.put("downCallBackUrl","http://ap.3435.net.cn/api/test");
        tradeInfo.put("bizType","WECHATPAY");
        String sign=Sha256.sha256ByAgentKey(tradeInfo,"BDCFDFDFDFSFUIUOIURIUEREWFFD");
        String param_str= JSONObject.toJSONString(tradeInfo);
        HttpResponse httpResponse= HttpRequest.post("http://localhost:9000/api/zscan").form("jsonData",param_str).form("sign",sign).execute();
        log.info(httpResponse.body());

    }
    @Test
    public void queryMerStatus(){
        HttpResponse httpResponse= HttpRequest.post("http://localhost:8085/api/queryMerStatus").form("merNo","M208915613817392528057660").execute();
        log.info(httpResponse.body());
    }
    @Test
    public void queryOrderStatus(){
        HttpResponse httpResponse= HttpRequest.post("http://localhost:9000/api/queryOrderStatus").form("merNo","M23225013781989578057046").form("merOrder","8413659444954").form("amount","1").execute();
        log.info(httpResponse.body());
    }

    @Test
    public void queryMerList(){
        HttpResponse httpResponse= HttpRequest.post("http://localhost:8086/api/queryMerList").form("pageNumber",1).form("channelCode","10000000").execute();
        log.info(httpResponse.body());
    }

    @Test
    public void callBack(){
        Map map=new HashMap();
        map.put("merNo","M23225013781989578057046");
        map.put("merOrder","181006121731937712");
        map.put("orderStatus","success");
        map.put("tradeAmount","100");
        String sign=Sha256.sha256ByAgentKey(map,"BDCFDFDFDFSFUIUOIURIUEREWFFD");
        HttpResponse httpResponse= HttpRequest.post("http://pay-futong-back.zzc2233.com/callback/notify.php").form("merNo","M23225013781989578057046").form("tradeAmount","100").form("merOrder","181006121731937712").form("orderStatus","success").form("sign",sign).execute();
        log.info(httpResponse.body());
    }

}

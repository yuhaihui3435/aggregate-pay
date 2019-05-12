package com.xtf.aggregatepay;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.xtf.aggregatepay.service.TradeDataService;
import com.xtf.aggregatepay.util.Sha256;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

//    @Autowired
//    private TradeDataService tradeDataService;

    /**
     *
     * 新增商户
     *
     */
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
        merInfo.put("apCode","100000000");
        merInfo.put("email","12@qq.com");
        merInfo.put("settleWay","Ts");
        merInfo.put("rateCode","20002902");
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
        File file=new File("/Users/yuhaihui/gencode/PICPEOBLE.jpg");
        File file1=new File("/Users/yuhaihui/gencode/LICENSE.jpg");
        File file2=new File("/Users/yuhaihui/gencode/BACKCARD.jpg");
        File file5=new File("/Users/yuhaihui/gencode/CARD.jpg");
        File file3=new File("/Users/yuhaihui/gencode/MAINPHOTO.jpg");
        File file4=new File("/Users/yuhaihui/gencode/BANKCARD.jpg");
        File file6=new File("/Users/yuhaihui/gencode/POWER.jpg");
        File file7=new File("/Users/yuhaihui/gencode/PROTOCOLPHOTO.jpg");
        File file8=new File("/Users/yuhaihui/gencode/ORGPHOTO.jpg");
        File file9=new File("/Users/yuhaihui/gencode/CHECKOUTPICTURE.jpg");
        File file10=new File("/Users/yuhaihui/gencode/ACCOUNTOPENIMAG.jpg");
        File file11=new File("/Users/yuhaihui/gencode/STOREFRONTPICTURE.jpg");
        File file12=new File("/Users/yuhaihui/gencode/REDSHIELDPICTURE.jpg");
        File file13=new File("/Users/yuhaihui/gencode/POWERID.jpg");

        Map<String,Object> param=new HashMap<>();
        param.put("merInfo",merInfo);
        param.put("merBankInfo",merBankInfo);
        String sign="89830490";
        String param_str= JSONObject.toJSONString(param);
        System.out.println(sign);
        System.out.println(param_str);
        HttpResponse httpResponse=HttpRequest.post("http://47.75.135.105/api/addMerInfo").form("files", file,file1,file2,file3,file4,file5,file6,file7,file8,file9,file10,file11,file12,file13).form("jsonData",param_str).form("sign",sign).execute();
        log.info(httpResponse.body());
    }

    /**
     *
     * 微信支付宝二维码交易
     *
     */
    @Test
    public void sendTrade(){
        Map<String,String> tradeInfo=new HashMap<>();
        tradeInfo.put("merchantNo","M227128906438301138057125");
        tradeInfo.put("merOrder",System.nanoTime()+"");
        tradeInfo.put("productName","西红柿");
        tradeInfo.put("tradeAmount","10000");
        tradeInfo.put("clientCode","1111111");
        tradeInfo.put("downCallBackUrl","http://47.75.135.105/api/test");
        tradeInfo.put("bizType","WECHATPAY");
        String sign=Sha256.sha256ByAgentKey(tradeInfo,"BDCFDFDFDFSFUIUOIURIUEREWFFD");
        String param_str= JSONObject.toJSONString(tradeInfo);
        HttpResponse httpResponse= HttpRequest.post("http://47.75.135.105/api/zscan").form("jsonData",param_str).form("sign","89830490").execute();
        log.info(httpResponse.body());

    }

    /**
     *
     * 商户状态查询
     *
     */
    @Test
    public void queryMerStatus(){
        HttpResponse httpResponse= HttpRequest.post("http://47.75.135.105/api/queryMerStatus").form("merNo","M277362232953462468057511").execute();
        log.info(httpResponse.body());
    }

    /**
     *
     * 订单状态查询
     *
     */
    @Test
    public void queryOrderStatus(){
        HttpResponse httpResponse= HttpRequest.post("http://47.75.135.105/api/queryOrderStatus").form("merNo","M23225013781989578057046").form("merOrder","181214134324787974").form("clientCode","11111").form("amount","1").execute();
        log.info(httpResponse.body());
    }

    /**
     *
     *
     *
     */
    @Test
    public void queryMerList(){
        HttpResponse httpResponse= HttpRequest.post("http://localhost:8085/api/queryMerList").form("pageNumber",1).form("channelCode","10000002").execute();
        log.info(httpResponse.body());
    }

    @Test
    public void callBack(){
        Map map=new HashMap();
        map.put("merNo","M227128906438301138057125");
        map.put("merOrder","181009181644747628");
        map.put("orderStatus","success");
        map.put("tradeAmount","133");
        String sign=Sha256.sha256ByAgentKey(map,"09606fa7965546b3adfa5759e943f08c");
        HttpResponse httpResponse= HttpRequest.post("http://pay-futong-back.zzc2233.com/callback/notify.php").form("merNo","M227128906438301138057125").form("tradeAmount","133").form("merOrder","181009181644747628").form("orderStatus","success").form("sign",sign).execute();
        log.info(httpResponse.body());
    }

    @Test
    public void statics(){
//        tradeDataService.staticsChannelTradeInDay("2018-10-09",null,Consts.SETTLEWAY.Ts.name());
//        tradeDataService.staticsChannelTradeBrokerageInDay(null,"2018-10-09");
//        tradeDataService.staticsMerTrade("2018-10-09");
    }

    @Test
    public void configMer(){
        HttpResponse httpResponse= HttpRequest.post("http://47.75.135.105/api/configMer").form("merNo","M233213761533718288057483").form("appid","2018101461639880").execute();
        log.info(httpResponse.body());
    }

    /**
     *
     * h5支付宝交易
     *
     */
    @Test
    public void sendTrade10(){
        Map<String,String> tradeInfo=new HashMap<>();
        tradeInfo.put("merchantNo","M227128906438301138057125");
        tradeInfo.put("merOrder",System.nanoTime()+"");
        tradeInfo.put("productName","西红柿");
        tradeInfo.put("tradeAmount","10000");
        tradeInfo.put("clientCode","1111111");
        tradeInfo.put("downCallBackUrl","http://47.75.135.105/api/test");
        tradeInfo.put("pageBackUrl","http://www.baidu.com");
        tradeInfo.put("bizType","ALIPAY");
        String param_str= JSONObject.toJSONString(tradeInfo);
        HttpResponse httpResponse= HttpRequest.post("http://47.75.135.105/api/gzScan10").form("jsonData",param_str).form("sign","89830490").execute();
        log.info(httpResponse.body());

    }
    @Test
    public void t(){
        HttpResponse httpResponse= HttpRequest.post("http://59.110.154.24:8080/alipay/xtfAlipayNotify").
                form("merNo", "M233213761533718288057483").
                form("merOrder", "1541399745375293000").
                form("amount", "150").
                form("orderStatus","success").
                form("sign","ssssssssssssssssss").execute();
        log.info(httpResponse.body());
    }

}

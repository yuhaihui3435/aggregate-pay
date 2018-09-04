package com.xtf.aggregatepay.client;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSONObject;
import com.xtf.aggregatepay.MsgProp;
import com.xtf.aggregatepay.core.LogicException;
import com.xtf.aggregatepay.dto.HttpResp;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class TradeClient {
    @Autowired
    private static MsgProp msgProp;

    public static HttpResp uploadPic(String picPath){
        log.error("开始向xyf发送图片上传请求");
        HttpResponse httpResponse=HttpRequest.post("http://test.newyfu.com:8057/upload").form("file",FileUtil.file(picPath)).timeout(2000).execute();
        if(httpResponse.getStatus()!=200){
            log.error("请求响应状态为："+httpResponse.getStatus()+",请求失败");
            throw new LogicException(msgProp.getUploadPic());
        }
        log.info("请求成功，返回数据为："+httpResponse.body());
        return JSONObject.parseObject(httpResponse.body(),HttpResp.class);

    }

    public static void main(String[] args) {
        HttpResp httpResp=uploadPic("C:\\Users\\yuhai\\Pictures\\me\\dft-avatar.jpg");
        log.info("完成");
    }

}

package com.xtf.aggregatepay;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource("classpath:msg.properties")
public class MsgProp {
    @Value("${err.uploadPic}")
    private String uploadPic;


}

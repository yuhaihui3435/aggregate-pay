package com.xtf.aggregatepay.dto;

import lombok.Data;

@Data
public class HttpResp<T> {
    private String rspCode;
    private String rspMsg;
    private String sign;
    private T result;
    private String resCode;
    private String resMsg;
}

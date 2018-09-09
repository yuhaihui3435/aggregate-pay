package com.xtf.aggregatepay.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ApiResp<T> {
    private String respCode;
    private String respMsg;
    private T jsonData;
    private String sign;

}

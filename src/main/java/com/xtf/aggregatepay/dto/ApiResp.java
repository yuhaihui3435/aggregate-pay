package com.xtf.aggregatepay.dto;

public class ApiResp {

    public ApiResp(){

    }

    public ApiResp(String rspCode,String rspMsg){
        this.rspCode=rspCode;
        this.rspMsg=rspMsg;
    }
    private String rspCode;
    private String rspMsg;
    private String jsonData;
    private String sign;

}

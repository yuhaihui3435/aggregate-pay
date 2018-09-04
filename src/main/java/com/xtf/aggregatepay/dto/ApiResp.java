package com.xtf.aggregatepay.dto;

import com.xtf.aggregatepay.Consts;
import lombok.Data;

@Data
public class ApiResp {

    public ApiResp(){

    }

    public ApiResp(String respCode,String respMsg){
        this.respCode=respCode;
        this.respMsg=respMsg;
    }
    private String respCode;
    private String respMsg;
    private String jsonData;
    private String sign;

    public void err(String msg){
        this.respCode=Consts.SYS_COMMON_FAIL_CODE;
        this.respMsg=msg;
    }

    public void suc(String msg){
        this.respCode=Consts.SYS_COMMON_SUCCESS_CODE;
        this.respMsg=msg;
    }

}

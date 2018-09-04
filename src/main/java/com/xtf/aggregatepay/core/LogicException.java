package com.xtf.aggregatepay.core;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Format;

public class LogicException extends RuntimeException {

    private Logger logger = LoggerFactory.getLogger(LogicException.class);


    protected String errMsg;

    protected String errCode;

    public String getErrMsg() {
        return errMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public LogicException(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg=errMsg;
        logger.error("系统遇到如下异常，异常码：{}>>>异常信息：{}", errCode, errMsg);
    }

    public LogicException(String errMsg) {
        this.errCode="999999";
        this.errMsg=errMsg;
        logger.error("系统遇到如下异常，异常码：{}>>>异常信息：{}", errCode, errMsg);
    }

    public String getPrettyExceptionMsg(){
        return String.format("业务异常:编号>>【%s】,信息>>【%s】",this.errCode,this.errMsg);
    }
}

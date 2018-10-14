package com.xtf.aggregatepay.dto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "请求基本格式" )
public class ApiReq {
    @ApiModelProperty(value = "json数据")
    private String jsonData;
    @ApiModelProperty(value = "签名数据")
    private String sign;
}

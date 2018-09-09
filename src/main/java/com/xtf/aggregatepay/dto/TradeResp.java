package com.xtf.aggregatepay.dto;

import lombok.Data;

/**
 * 简介
 * <p>
 * 项目名称:   [aggregate-pay]
 * 包:        [com.xtf.aggregatepay.dto]
 * 类名称:    [TradeResp]
 * 类描述:    []
 * 创建人:    [于海慧]
 * 创建时间:  [2018/9/8]
 * 修改人:    []
 * 修改时间:  []
 * 修改备注:  []
 * 版本:     [v1.0]
 */
@Data
public class TradeResp extends HttpResp {
    private String payOrderNo;
    private String codeUrl;
    private String tradeAmount;
    private String merOrder;
    private String merchantNo;
    private String orderStatus;
}

package com.xtf.aggregatepay.core;

import lombok.Data;

/**
 * 简介
 * <p>
 * 项目名称:   [aggregate-pay]
 * 包:        [com.xtf.aggregatepay.core]
 * 类名称:    [ValidationException]
 * 类描述:    []
 * 创建人:    [于海慧]
 * 创建时间:  [2018/9/8]
 * 修改人:    []
 * 修改时间:  []
 * 修改备注:  []
 * 版本:     [v1.0]
 */
@Data
public class ValidationException extends Exception{
    public ValidationException(String msg){
        super(msg);
    }
}

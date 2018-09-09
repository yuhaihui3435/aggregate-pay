package com.xtf.aggregatepay.dao;

import com.xtf.aggregatepay.entity.TradeData;
import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * 简介
 * <p>
 * 项目名称:   [aggregate-pay]
 * 包:        [com.xtf.aggregatepay.dao]
 * 类名称:    [TradeDataDao]
 * 类描述:    []
 * 创建人:    [于海慧]
 * 创建时间:  [2018/9/8]
 * 修改人:    []
 * 修改时间:  []
 * 修改备注:  []
 * 版本:     [v1.0]
 */
@Repository
public interface TradeDataDao extends BaseMapper<TradeData> {

    BigDecimal sumTradeAmount(@Param("merNum") String merNum);

}

package com.xtf.aggregatepay.dao;

import com.xtf.aggregatepay.core.BaseDao;
import com.xtf.aggregatepay.entity.MerInfo;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface MerInfoDao extends BaseDao<MerInfo> {

    List<MerInfo> pickMerInfo(String channelCode, BigDecimal price);
}

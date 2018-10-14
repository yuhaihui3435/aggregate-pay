package com.xtf.aggregatepay.dao;

import com.xtf.aggregatepay.core.BaseDao;
import com.xtf.aggregatepay.entity.MerInfo;
import org.beetl.sql.core.annotatoin.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
@Repository
public interface MerInfoDao extends BaseDao<MerInfo> {

    List<MerInfo> pickMerInfo(@Param("channelCode") String channelCode, @Param("price") BigDecimal price, @Param("merUseCount") Integer merUseCount,@Param("merTimeInterval") Integer merTimeInterval);
}

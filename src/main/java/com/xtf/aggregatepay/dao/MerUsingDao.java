package com.xtf.aggregatepay.dao;

import com.xtf.aggregatepay.core.BaseDao;
import com.xtf.aggregatepay.entity.MerUsing;
import org.beetl.sql.core.annotatoin.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MerUsingDao extends BaseDao<MerUsing> {

    void delExpiredData(@Param("interval") int interval);
}

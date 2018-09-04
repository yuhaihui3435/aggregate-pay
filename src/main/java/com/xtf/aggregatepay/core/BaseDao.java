package com.xtf.aggregatepay.core;

import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseDao<T> extends BaseMapper<T> {
}

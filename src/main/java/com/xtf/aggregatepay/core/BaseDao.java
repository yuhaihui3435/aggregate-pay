package com.xtf.aggregatepay.core;

import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

public interface BaseDao<T> extends BaseMapper<T> {
}

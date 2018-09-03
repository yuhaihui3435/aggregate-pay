package com.xtf.aggregatepay.service;

import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.dao.DictDao;
import com.xtf.aggregatepay.entity.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DictService extends BaseService<Dict> {
    @Autowired
    private DictItemService dictItemService;

    public void addInCache(){

    }


}

package com.xtf.aggregatepay;

import com.xtf.aggregatepay.entity.Dict;
import com.xtf.aggregatepay.entity.DictItem;
import com.xtf.aggregatepay.service.DictItemService;
import com.xtf.aggregatepay.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 简介
 * <p>
 * 项目名称:   [aggregate-pay]
 * 包:        [com.xtf.aggregatepay]
 * 类名称:    [AfterStartupRunner]
 * 类描述:    []
 * 创建人:    [于海慧]
 * 创建时间:  [2018/9/3]
 * 修改人:    []
 * 修改时间:  []
 * 修改备注:  []
 * 版本:     [v1.0]
 */
@Component
public class AfterStartupRunner implements ApplicationRunner
{
    @Autowired
    private EhCacheCacheManager ehCacheCacheManager;
    @Autowired
    private DictService dictService;
    @Autowired
    private DictItemService dictItemService;


    @Override
    public void run(ApplicationArguments args) throws Exception {

    }


    private void addInCache(){
        List<Dict> dictList=dictService.all();
        Cache cache=ehCacheCacheManager.getCache(Dict.class.getSimpleName());
        dictList.stream().forEach(dict -> cache.put(dict.getDictCode(),dictItemService.findByDictId(dict.getId())));
    }
}

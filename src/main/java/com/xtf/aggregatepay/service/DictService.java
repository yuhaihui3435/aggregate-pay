package com.xtf.aggregatepay.service;

import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.dao.DictDao;
import com.xtf.aggregatepay.entity.Dict;
import com.xtf.aggregatepay.entity.DictItem;
import com.xtf.aggregatepay.util.EhcacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class DictService extends BaseService<Dict> {

    protected static final Logger logger = LoggerFactory.getLogger(DictService.class);

    @Autowired
    private DictItemService dictItemService;

    public void addDict(Dict dict){
        insert(dict);
        dictCache();
    }

    public void updateDict(Dict dict){
        update(dict);
        dictCache();
    }

    public void delDict(Integer id){
        del(id);
        dictCache();
    }

    //数据字段缓存，key=数据字典code，value=字段条目集合
    public void dictCache(){
        EhcacheUtil.getInstance().removeAll(Dict.class.getSimpleName());
        List<Dict> dictList=all();
        dictList.stream().forEach(dict -> dictItemCache(dict));
    }
    //数据字典条目缓存 key=条目code value=条目数据
    private void dictItemCache(Dict dict){
        List<DictItem> dictItems=dictItemService.findByDictId(dict.getId());
        EhcacheUtil.getInstance().put(Dict.class.getSimpleName(),dict.getDictCode(),dictItems);
        dictItems.stream().forEach(dictItem -> EhcacheUtil.getInstance().put(DictItem.class.getSimpleName(),dictItem.getDictItemCode(),dictItem));
    }

}

package com.xtf.aggregatepay.service;

import com.xtf.aggregatepay.core.BaseService;
import com.xtf.aggregatepay.entity.DictItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 简介
 * <p>
 * 项目名称:   [aggregate-pay]
 * 包:        [com.xtf.aggregatepay.service]
 * 类名称:    [DictItemService]
 * 类描述:    []
 * 创建人:    [于海慧]
 * 创建时间:  [2018/9/3]
 * 修改人:    []
 * 修改时间:  []
 * 修改备注:  []
 * 版本:     [v1.0]
 */
@Service
public class DictItemService extends BaseService<DictItem> {

    public List<DictItem> findByDictId(Integer dictId){
        DictItem dictItem=new DictItem();
        dictItem.setDictId(dictId);
        return tpl(dictItem);
    }
}

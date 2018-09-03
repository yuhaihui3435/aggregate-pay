package com.xtf.aggregatepay.core;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class BaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    public List<T> all(){
        return baseMapper.all();
    }

    public T one(Object id){
        return baseMapper.single(id);
    }

    public long count(){
        return baseMapper.allCount();
    }

    public void insert(T entity){
        baseMapper.insert(entity);
    }

    public void insertAutoKey(T entity){
        baseMapper.insert(entity,true);
    }

    public void insertTpl(T entity){
        baseMapper.insertTemplate(entity);
    }

    public void inserTplAutoKey(T entity){
        baseMapper.insertTemplate(entity,true);
    }

    public void insertBatch(List<T> entitys){
        baseMapper.insertBatch(entitys);
    }

    public KeyHolder insertReturnKey(T entity){
        return baseMapper.insertReturnKey(entity);
    }

    public int update(T entity){
        return baseMapper.updateById(entity);
    }

    public int updateTplByuId(T entity){
        return baseMapper.updateTemplateById(entity);
    }

    public int del(Object id){
        return  baseMapper.deleteById(id);
    }



}

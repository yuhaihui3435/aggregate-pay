package com.xtf.aggregatepay.core;

import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import java.util.List;

public abstract class BaseService<T> {

    @Autowired
    protected EhCacheCacheManager ehCacheCacheManager;
    @Autowired
    private BaseMapper<T> baseMapper;
    @Autowired
    private SQLManager sqlManager;

    public List<T> all(){
        return baseMapper.all();
    }

    public List<T> all(int start,int size){
        return baseMapper.all(start,size);
    }

    public List<T> tpl(T entity){
        return baseMapper.template(entity);
    }

    public <T>T tplOne(T entity){
        return baseMapper.templateOne(entity);
    }

    public List<T> tpl(T entity,int start,int size){
        return baseMapper.template(entity,start,size);
    }

    public long tplCount(T entity){
        return baseMapper.templateCount(entity);
    }

    public void page(PageQuery<T> pageQuery){
        baseMapper.templatePage(pageQuery);
    }

    public <T> PageQuery<T> page(String sqlId,Class<T> clazz,PageQuery<T> pageQuery ){
        return sqlManager.pageQuery(sqlId,clazz,pageQuery);
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

    public T lock(Object id){
        return baseMapper.lock(id);
    }





}

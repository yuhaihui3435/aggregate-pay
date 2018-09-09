package com.xtf.aggregatepay.core;

import com.xtf.aggregatepay.entity.ApCode;
import lombok.extern.log4j.Log4j2;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
@Log4j2
public abstract class BaseService<T> {


    @Autowired
    protected BaseDao<T> baseDao;
    @Autowired
    protected SQLManager sqlManager;


    public List<T> all(){
        return baseDao.all();
    }

    public List<T> all(int start,int size){
        return baseDao.all(start,size);
    }

    public List<T> tpl(T entity){
        return baseDao.template(entity);
    }

    public <T>T tplOne(T entity){
        return (T) baseDao.templateOne(entity);
    }

    public List<T> tpl(T entity,int start,int size){
        return baseDao.template(entity,start,size);
    }

    public long tplCount(T entity){
        return baseDao.templateCount(entity);
    }

    public void page(PageQuery<T> pageQuery){
        baseDao.templatePage(pageQuery);
    }

    public <T> PageQuery<T> page(String sqlId,Class<T> clazz,PageQuery<T> pageQuery ){
        return sqlManager.pageQuery(sqlId,clazz,pageQuery);
    }

    public T one(Object id){
        return (T) baseDao.single(id);
    }

    public long count(){
        return baseDao.allCount();
    }
    @Transactional
    public void insert(T entity){
        setCreateTime(entity);
        baseDao.insert(entity);
    }
    @Transactional
    public void insertAutoKey(T entity){
        setCreateTime(entity);
        baseDao.insert(entity,true);
    }
    @Transactional
    public void insertTpl(T entity){
        setCreateTime(entity);
        baseDao.insertTemplate(entity);
    }
    @Transactional
    public void inserTplAutoKey(T entity){
        setCreateTime(entity);
        baseDao.insertTemplate(entity,true);
    }
    @Transactional
    public void insertBatch(List<T> entitys){
        entitys.stream().forEach(entity->setCreateTime(entity));
        baseDao.insertBatch(entitys);
    }
    @Transactional
    public KeyHolder insertReturnKey(T entity){
        setCreateTime(entity);
        return baseDao.insertReturnKey(entity);
    }
    @Transactional
    public int update(T entity){
        setUpdateTime(entity);
        return baseDao.updateById(entity);
    }
    @Transactional
    public int updateTplById(T entity){
        setUpdateTime(entity);
        return baseDao.updateTemplateById(entity);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    @Transactional
    public int logicDel(Object id){
        T entity=one(id);
        setDeleteTime(entity);
        return update(entity);
    }

    /**
     * 真实删除
     * @param id
     * @return
     */
    @Transactional
    public int del(Object id){
        return  baseDao.deleteById(id);
    }

    public T lock(Object id){
        return (T) baseDao.lock(id);
    }

    private void setCreateTime(T entity){
        try {
            Method method=entity.getClass().getMethod("setCreateTime", Date.class);
            method.invoke(entity,new Date());
        } catch (NoSuchMethodException e) {
            log.warn("缺少setCreateTime方法");
        } catch (IllegalAccessException e) {
            log.warn("调用方法错误");
        } catch (InvocationTargetException e) {
            log.warn("调用方法错误");
        }
    }

    private void setUpdateTime(T entity){
        try {
            Method method=entity.getClass().getMethod("setUpdateTime", Date.class);
            method.invoke(entity,new Date());
        } catch (NoSuchMethodException e) {
            log.warn("缺少setUpdateTime方法");
        } catch (IllegalAccessException e) {
            log.warn("调用方法错误");
        } catch (InvocationTargetException e) {
            log.warn("调用方法错误");
        }
    }

    private void setDeleteTime(T entity){
        try {
            Method method=entity.getClass().getMethod("deleteTime", Date.class);
            method.invoke(entity,new Date());
        } catch (NoSuchMethodException e) {
            log.warn("缺少deleteTime方法");
        } catch (IllegalAccessException e) {
            log.warn("调用方法错误");
        } catch (InvocationTargetException e) {
            log.warn("调用方法错误");
        }
    }


}

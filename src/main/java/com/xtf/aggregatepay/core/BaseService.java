package com.xtf.aggregatepay.core;

import lombok.extern.log4j.Log4j2;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.engine.PageQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
@Log4j2
public abstract class BaseService<T> {

    @Autowired
    protected SQLManager sqlManager;

    public Class<T> getTClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public List<T> all(){
        return sqlManager.all(getTClass());
    }

    public List<T> all(int start,int size){
        return sqlManager.all(getTClass(),start,size);
    }

    public List<T> tpl(T entity){
        List<T> ret=sqlManager.template(entity);
        return sqlManager.template(entity);
    }

    public <T>T tplOne(T entity){
        return  sqlManager.templateOne(entity);
    }

    public List<T> tpl(T entity,int start,int size){
        return sqlManager.template(entity,start,size);
    }

    public long tplCount(T entity){
        return sqlManager.templateCount(entity);
    }


    public <T> PageQuery<T> page(String sqlId,PageQuery<T> pageQuery ){
        return sqlManager.pageQuery(sqlId, (Class<T>) getTClass(),pageQuery);
    }

    public T one(Object id){
        return (T) sqlManager.single(getTClass(),id);
    }

    public long count(){
        return sqlManager.allCount(getTClass());
    }

    public void insert(T entity){
        setCreateTime(entity);
        sqlManager.insert(entity);
    }
    public void insertAutoKey(T entity){
        setCreateTime(entity);
        sqlManager.insert(entity,true);
    }
    public void insertTpl(T entity){
        setCreateTime(entity);
        sqlManager.insertTemplate(entity);
    }
    public void inserTplAutoKey(T entity){
        setCreateTime(entity);
        sqlManager.insertTemplate(entity,true);
    }
    public void insertBatch(List<T> entitys){
        entitys.stream().forEach(entity->setCreateTime(entity));
        sqlManager.insertBatch(getTClass(),entitys);
    }
    public int update(T entity){
        setUpdateTime(entity);
        return sqlManager.updateById(entity);
    }
    public int updateTplById(T entity){
        setUpdateTime(entity);
        return sqlManager.updateTemplateById(entity);
    }

    /**
     * 逻辑删除
     * @param id
     * @return
     */
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
    public int del(Object id){
        return  sqlManager.deleteById(getTClass(),id);
    }

    public T lock(Object id){
        return (T) sqlManager.lock(getTClass(),id);
    }

    private void setCreateTime(T entity){
        try {
            Method method=entity.getClass().getMethod("setCreatedTime", Date.class);
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
            Method method=entity.getClass().getMethod("setUpdatedTime", Date.class);
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
            Method method=entity.getClass().getMethod("setDeleteTime", Date.class);
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

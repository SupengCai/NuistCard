/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.linklife.domain.base;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.linklife.common.utils.ReflectUtil;
import com.linklife.repository.base.IRepository;

/**
 * BaseDomain.java 领域实体的基类，所有领域实体均需要继承此类。 <T> 继承BaseDomain的领域实体自身。 <U> 对应此领域实体的资源仓库类，实体将实体领域操作委托给资源仓库类来完成
 * 
 * @author caisupeng
 */
@SuppressWarnings("unchecked")
public class BaseDomain<T, U> implements Cloneable {
    protected Log log = LogFactory.getLog("BaseDomain");

    @Autowired
    @Qualifier("baseRepository")
    private IRepository<T, U> repository;
    
    /**
     * 取得对应的实体仓库。如果对应的领域对象非常复杂，需要使用自己的Repository，<br>
     * 则在子类中重写这个方法，获取指定的Repository。
     * 
     * @return 实体仓库
     */
    public IRepository<T, U> acquiredTargetRepository() {
        return repository;
    }
    
    public void mockRepository(IRepository<T, U> repository) {
        this.repository = repository;
    }

    /**
     * 根据主键删除对应记录
     * 
     * @param t 主键
     * @return 删除记录数
     */
    public int deleteByPrimaryKey() {
        try {
            return acquiredTargetRepository().deleteByPrimaryKey((T) prepareForPrimaryKey());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 插入记录
     * 
     * @param t 主键
     * @return 插入记录数
     */
    public void insert() {
        try {
            acquiredTargetRepository().insert((T) this);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 插入记录,只插入对应记录
     * 
     * @param t 主键
     * @return 插入记录数
     */
    public void insertSelective() {
        try {
            acquiredTargetRepository().insertSelective((T) this);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键检索对应记录
     * 
     * @param t 主键
     */
    public T selectByPrimaryKey() {
        try {
            T t = acquiredTargetRepository().selectByPrimaryKey((T) prepareForPrimaryKey());
            if (t != null) {
            	fillSelectResult(t);
                return (T) this;
            }
            else {
                return null;
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据主键更新对应记录（只更新有值的属性）
     * 
     * @return 更新记录条数
     */
    public int updateByPrimaryKeySelective() {
        try {
            return acquiredTargetRepository().updateByPrimaryKeySelective((T) this);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 查询指定条件下的所有的该对象的集合
     * 
     * @param u 封装的条件信息
     * @return 返回符合条件的该对象的集合
     */
    @SuppressWarnings("rawtypes")
    public List<T> selectByExample(U u) {
        try {
            List<T> ts = acquiredTargetRepository().selectByExample(u);
            List result = new ArrayList();
            for (T t : ts) {
                Object obj = ReflectUtil.newInstance(this);
                ReflectUtil.copyPropertis(t, obj);
                result.add(obj);
            }
            return result;
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 统计指定条件的数据的数量
     * 
     * @param u 封装的条件信息
     * @return 返回统计的数量
     */
    public int countByExample(U u) {
        try {
            return acquiredTargetRepository().countByExample(u);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除当前对象对应的条件中的数据
     * 
     * @param u 封装的条件信息
     * @return 返回成功删除的数量
     */
    public int deleteByExample(U u) {
        try {
            return acquiredTargetRepository().deleteByExample(u);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新指定条件中的指定字段信息。其中，如果需要指定对哪个对象进行更新，请使用其中的构造方法如： <code>new AdmOrganizeExample(organize, organizeExample)</code>
     * 
     * @param u 封装的对象要更新的值，与批量更新的范围条件
     * @return 返回更新的结果数量
     */
    public int updateByExampleSelective(U u) {
        try {
            return acquiredTargetRepository().updateByExampleSelective(u);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 根据取得内容更新自身
     * 
     * @param t 主键
     */
    protected Object prepareForPrimaryKey() {
        return this;
    }

    public Object clone() {
        try {
            Object obj = super.clone();
            return obj;
        }
        catch (CloneNotSupportedException e) {
            return null;
        }
    }
    
    /**
     * 根据取得内容更新自身
     * 
     * @param t 主键
     * @throws Exception 
     * @throws SecurityException 
     * @throws NoSuchMethodException 
     */
    protected void fillSelectResult(T t)  {
        ReflectUtil.copyPropertis(t, this);
    }
}

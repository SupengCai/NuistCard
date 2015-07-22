/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.linklife.repository.base;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * IRepository.java
 * 
 * <pre>
 * Domain领域基础存储仓库接口类
 * </pre>
 * 
 * @author caisupeng
 */
public interface IRepository<T, U> {
    /**
     * 根据主键进行删除指定的记录。
     * 
     * @param t 对应的领域对象或领域Model。
     * @return 返回操作成功的数量
     * @throws SQLException
     */
    public int deleteByPrimaryKey(T t) throws SQLException;

    /**
     * 插入一个对象。
     * 
     * @param t 对应的领域对象或领域Model。
     * @throws SQLException
     */
    public void insert(T t) throws SQLException;

    /**
     * 插入一个对象中提供的值。
     * 
     * @param t
     * @throws SQLException
     */
    public void insertSelective(T t) throws SQLException;

    /**
     * 根据对象的主键查询对应的一个领域对象。
     * 
     * @param t
     * @return
     * @throws SQLException
     */
    public T selectByPrimaryKey(T t) throws SQLException;

    /**
     * 根据主键更新对象中给定的属性的值。
     * 
     * @param t
     * @return
     * @throws SQLException
     */
    public int updateByPrimaryKeySelective(T t) throws SQLException;

    /**
     * 查询指定的条件对应的数量。
     * 
     * @param u
     * @return
     * @throws SQLException
     */
    public int countByExample(U u) throws SQLException;

    /**
     * 根据指定的条件进行删除。
     * 
     * @param u
     * @return
     * @throws SQLException
     */
    public int deleteByExample(U u) throws SQLException;

    /**
     * 查询符合给定条件的所有数据！
     * 
     * @param <T> 返回的对象信息
     * @param <U> 查询的条件
     * @param u
     * @return
     * @throws SQLException
     */
    public List<T> selectByExample(U u) throws SQLException;

    /**
     * 更新符合给定的条件的所有数据（只更新给值的部分）
     * 
     * @param u 给定的条件。条件对象中包含需要更新的实体对象！
     * @return
     * @throws SQLException
     */
    public int updateByExampleSelective(U u) throws SQLException;

    /**
     * 查询用户自定义的列表中记录总数。即查询Site_List_Collection_SqlMap.xml中配置的对应的SQL。
     * 
     * @param t
     * @param sqlId
     * @return
     * @throws SQLException
     */
    public int selectListCount(T t, String sqlId) throws SQLException;

    /**
     * 获取SQL执行的执行者。
     * 
     * @return
     */
    public SqlMapClient getSqlMapClient();

}

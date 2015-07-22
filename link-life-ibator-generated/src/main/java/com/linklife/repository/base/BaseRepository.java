/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.linklife.repository.base;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

/**
 * BaseRepository.java
 * 
 * <pre>
 * Domain领域基础存储仓库类
 * </pre>
 * 
 * @author caisupeng
 */
@Repository
public class BaseRepository<T, U> implements IRepository<T, U> {

    private static final String DELETE_BY_PRIMARYKEY = ".ibatorgenerated_deleteByPrimaryKey";
    private static final String INSERT = ".ibatorgenerated_insert";
    private static final String INSERT_SELECTIVE = ".ibatorgenerated_insertSelective";
    private static final String SELECT_BY_PRIMARYKEY = ".ibatorgenerated_selectByPrimaryKey";
    private static final String UPDATE_BY_PRIMARYKEY_SELECTIVE = ".ibatorgenerated_updateByPrimaryKeySelective";
    private static final String COUNT_BY_EXAMPLE = ".ibatorgenerated_countByExample";
    private static final String DELETE_BY_EXAMPLE = ".ibatorgenerated_deleteByExample";
    private static final String SELECT_BY_EXAMPLE = ".ibatorgenerated_selectByExample";
    private static final String UPDATE_BY_EXAMPLE_SELECTIVE = ".ibatorgenerated_updateByExampleSelective";


    @Autowired
    @Qualifier("sqlMapClient")
    protected SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    public SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }

    public String acquireSqlMapName(T t) {
        SqlMap sqlMap = t.getClass().getAnnotation(SqlMap.class);
        if (sqlMap == null) {
            sqlMap = t.getClass().getSuperclass().getAnnotation(SqlMap.class);
        }
        return sqlMap.Name();
    }

    private String acquireSqlMapNameByExample(U u) {
        return u.getClass().getAnnotation(SqlMap.class).Name();
    }

    public int deleteByPrimaryKey(T t) throws SQLException {
        return sqlMapClient.delete(acquireSqlMapName(t) + DELETE_BY_PRIMARYKEY, t);
    }

    public void insert(T t) throws SQLException {
        sqlMapClient.insert(acquireSqlMapName(t) + INSERT, t);
    }

    public void insertSelective(T t) throws SQLException {
        sqlMapClient.insert(acquireSqlMapName(t) + INSERT_SELECTIVE, t);
    }

    @SuppressWarnings("unchecked")
    public T selectByPrimaryKey(T t) throws SQLException {
        return (T) sqlMapClient.queryForObject(acquireSqlMapName(t) + SELECT_BY_PRIMARYKEY, t);
    }

    public int updateByPrimaryKeySelective(T t) throws SQLException {
        return sqlMapClient.update(acquireSqlMapName(t) + UPDATE_BY_PRIMARYKEY_SELECTIVE, t);
    }

    public int countByExample(U u) throws SQLException {
        return (Integer) sqlMapClient.queryForObject(acquireSqlMapNameByExample(u) + COUNT_BY_EXAMPLE, u);
    }

    public int deleteByExample(U u) throws SQLException {
        return sqlMapClient.delete(acquireSqlMapNameByExample(u) + DELETE_BY_EXAMPLE, u);
    }

    @SuppressWarnings("unchecked")
    public List<T> selectByExample(U u) throws SQLException {
        return sqlMapClient.queryForList(acquireSqlMapNameByExample(u) + SELECT_BY_EXAMPLE, u);
    }
    
    public int selectListCount(T t, String sqlId) throws SQLException {
        return (Integer) sqlMapClient.queryForObject(acquireSqlMapName(t) + "." + sqlId + "_count", t);
    }
    
    public int updateByExampleSelective(U u) throws SQLException {
        return sqlMapClient.update(acquireSqlMapNameByExample(u) + UPDATE_BY_EXAMPLE_SELECTIVE, u);
    }
}

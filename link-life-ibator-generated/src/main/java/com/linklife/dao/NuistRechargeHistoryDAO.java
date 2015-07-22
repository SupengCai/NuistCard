package com.linklife.dao;

import com.linklife.domain.ibator.NuistRechargeHistory;
import com.linklife.domain.ibator.NuistRechargeHistoryExample;
import java.sql.SQLException;
import java.util.List;

public interface NuistRechargeHistoryDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	int countByExample( NuistRechargeHistoryExample example ) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	int deleteByExample( NuistRechargeHistoryExample example ) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	int deleteByPrimaryKey( Integer rechargeId ) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	Integer insert( NuistRechargeHistory record ) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	Integer insertSelective( NuistRechargeHistory record ) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	List selectByExample( NuistRechargeHistoryExample example ) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	NuistRechargeHistory selectByPrimaryKey( Integer rechargeId ) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	int updateByExampleSelective( NuistRechargeHistory record, NuistRechargeHistoryExample example ) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	int updateByExample( NuistRechargeHistory record, NuistRechargeHistoryExample example ) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	int updateByPrimaryKeySelective( NuistRechargeHistory record ) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_recharge_history
	 * @ibatorgenerated  Mon Feb 23 22:18:02 CST 2015
	 */
	int updateByPrimaryKey( NuistRechargeHistory record ) throws SQLException;
}
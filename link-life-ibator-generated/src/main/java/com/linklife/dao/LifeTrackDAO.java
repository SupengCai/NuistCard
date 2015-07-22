package com.linklife.dao;

import com.linklife.domain.ibator.LifeTrack;
import com.linklife.domain.ibator.LifeTrackExample;
import java.sql.SQLException;
import java.util.List;

public interface LifeTrackDAO {

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	int countByExample(LifeTrackExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	int deleteByExample(LifeTrackExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	int deleteByPrimaryKey(Integer trackId) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	Integer insert(LifeTrack record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	Integer insertSelective(LifeTrack record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	List selectByExample(LifeTrackExample example) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	LifeTrack selectByPrimaryKey(Integer trackId) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	int updateByExampleSelective(LifeTrack record, LifeTrackExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	int updateByExample(LifeTrack record, LifeTrackExample example)
			throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	int updateByPrimaryKeySelective(LifeTrack record) throws SQLException;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table life_track
	 * @ibatorgenerated  Sun Aug 24 14:36:06 CST 2014
	 */
	int updateByPrimaryKey(LifeTrack record) throws SQLException;
}
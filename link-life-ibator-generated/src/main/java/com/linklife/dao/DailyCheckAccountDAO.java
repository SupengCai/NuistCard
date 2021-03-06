package com.linklife.dao;

import com.linklife.domain.ibator.DailyCheckAccount;
import com.linklife.domain.ibator.DailyCheckAccountExample;
import java.sql.SQLException;
import java.util.List;

public interface DailyCheckAccountDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    int countByExample(DailyCheckAccountExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    int deleteByExample(DailyCheckAccountExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    int deleteByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    Integer insert(DailyCheckAccount record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    Integer insertSelective(DailyCheckAccount record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    List selectByExample(DailyCheckAccountExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    DailyCheckAccount selectByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    int updateByExampleSelective(DailyCheckAccount record, DailyCheckAccountExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    int updateByExample(DailyCheckAccount record, DailyCheckAccountExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    int updateByPrimaryKeySelective(DailyCheckAccount record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_account
     *
     * @ibatorgenerated Wed Jun 03 21:04:57 CST 2015
     */
    int updateByPrimaryKey(DailyCheckAccount record) throws SQLException;
}
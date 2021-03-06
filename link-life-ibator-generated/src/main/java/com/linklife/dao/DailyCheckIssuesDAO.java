package com.linklife.dao;

import com.linklife.domain.ibator.DailyCheckIssues;
import com.linklife.domain.ibator.DailyCheckIssuesExample;
import java.sql.SQLException;
import java.util.List;

public interface DailyCheckIssuesDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    int countByExample(DailyCheckIssuesExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    int deleteByExample(DailyCheckIssuesExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    int deleteByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    Integer insert(DailyCheckIssues record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    Integer insertSelective(DailyCheckIssues record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    List selectByExample(DailyCheckIssuesExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    DailyCheckIssues selectByPrimaryKey(Integer id) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    int updateByExampleSelective(DailyCheckIssues record, DailyCheckIssuesExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    int updateByExample(DailyCheckIssues record, DailyCheckIssuesExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    int updateByPrimaryKeySelective(DailyCheckIssues record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_issues
     *
     * @ibatorgenerated Wed Jun 03 21:05:26 CST 2015
     */
    int updateByPrimaryKey(DailyCheckIssues record) throws SQLException;
}
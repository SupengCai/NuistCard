package com.linklife.dao;

import com.linklife.domain.ibator.NuistCourse;
import com.linklife.domain.ibator.NuistCourseExample;
import java.sql.SQLException;
import java.util.List;

public interface NuistCourseDAO {
    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    int countByExample(NuistCourseExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    int deleteByExample(NuistCourseExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    int deleteByPrimaryKey(Integer courseId) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    Integer insert(NuistCourse record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    Integer insertSelective(NuistCourse record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    List selectByExample(NuistCourseExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    NuistCourse selectByPrimaryKey(Integer courseId) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    int updateByExampleSelective(NuistCourse record, NuistCourseExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    int updateByExample(NuistCourse record, NuistCourseExample example) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    int updateByPrimaryKeySelective(NuistCourse record) throws SQLException;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table nuist_course
     *
     * @ibatorgenerated Sun Jul 12 13:22:43 CST 2015
     */
    int updateByPrimaryKey(NuistCourse record) throws SQLException;
}
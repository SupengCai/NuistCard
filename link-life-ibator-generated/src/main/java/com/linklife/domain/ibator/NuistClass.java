package com.linklife.domain.ibator;

import com.linklife.domain.base.BaseDomain;
import com.linklife.repository.base.SqlMap;

@SqlMap( Name = "nuist_class", Class = NuistClass.class )
public class NuistClass<T, U> extends BaseDomain<T, U>  {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column nuist_class.class_id
     *
     * @ibatorgenerated Sun Jul 12 21:12:30 CST 2015
     */
    private Integer classId;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column nuist_class.class_name
     *
     * @ibatorgenerated Sun Jul 12 21:12:30 CST 2015
     */
    private String className;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database column nuist_class.education
     *
     * @ibatorgenerated Sun Jul 12 21:12:30 CST 2015
     */
    private String education;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column nuist_class.class_id
     *
     * @return the value of nuist_class.class_id
     *
     * @ibatorgenerated Sun Jul 12 21:12:30 CST 2015
     */
    public Integer getClassId() {
        return classId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column nuist_class.class_id
     *
     * @param classId the value for nuist_class.class_id
     *
     * @ibatorgenerated Sun Jul 12 21:12:30 CST 2015
     */
    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column nuist_class.class_name
     *
     * @return the value of nuist_class.class_name
     *
     * @ibatorgenerated Sun Jul 12 21:12:30 CST 2015
     */
    public String getClassName() {
        return className;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column nuist_class.class_name
     *
     * @param className the value for nuist_class.class_name
     *
     * @ibatorgenerated Sun Jul 12 21:12:30 CST 2015
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method returns the value of the database column nuist_class.education
     *
     * @return the value of nuist_class.education
     *
     * @ibatorgenerated Sun Jul 12 21:12:30 CST 2015
     */
    public String getEducation() {
        return education;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method sets the value of the database column nuist_class.education
     *
     * @param education the value for nuist_class.education
     *
     * @ibatorgenerated Sun Jul 12 21:12:30 CST 2015
     */
    public void setEducation(String education) {
        this.education = education;
    }
}
package com.linklife.domain.ibator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DailyCheckUpdateExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    public DailyCheckUpdateExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    protected DailyCheckUpdateExample(DailyCheckUpdateExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table daily_check_update
     *
     * @ibatorgenerated Wed Jun 03 21:06:52 CST 2015
     */
    public static class Criteria {
        protected List criteriaWithoutValue;

        protected List criteriaWithSingleValue;

        protected List criteriaWithListValue;

        protected List criteriaWithBetweenValue;

        protected Criteria() {
            super();
            criteriaWithoutValue = new ArrayList();
            criteriaWithSingleValue = new ArrayList();
            criteriaWithListValue = new ArrayList();
            criteriaWithBetweenValue = new ArrayList();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List list = new ArrayList();
            list.add(value1);
            list.add(value2);
            Map map = new HashMap();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return this;
        }

        public Criteria andIdIn(List values) {
            addCriterion("id in", values, "id");
            return this;
        }

        public Criteria andIdNotIn(List values) {
            addCriterion("id not in", values, "id");
            return this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return this;
        }

        public Criteria andTypeIn(List values) {
            addCriterion("type in", values, "type");
            return this;
        }

        public Criteria andTypeNotIn(List values) {
            addCriterion("type not in", values, "type");
            return this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return this;
        }

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return this;
        }

        public Criteria andContentIn(List values) {
            addCriterion("content in", values, "content");
            return this;
        }

        public Criteria andContentNotIn(List values) {
            addCriterion("content not in", values, "content");
            return this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return this;
        }

        public Criteria andDataIsNull() {
            addCriterion("data is null");
            return this;
        }

        public Criteria andDataIsNotNull() {
            addCriterion("data is not null");
            return this;
        }

        public Criteria andDataEqualTo(Date value) {
            addCriterion("data =", value, "data");
            return this;
        }

        public Criteria andDataNotEqualTo(Date value) {
            addCriterion("data <>", value, "data");
            return this;
        }

        public Criteria andDataGreaterThan(Date value) {
            addCriterion("data >", value, "data");
            return this;
        }

        public Criteria andDataGreaterThanOrEqualTo(Date value) {
            addCriterion("data >=", value, "data");
            return this;
        }

        public Criteria andDataLessThan(Date value) {
            addCriterion("data <", value, "data");
            return this;
        }

        public Criteria andDataLessThanOrEqualTo(Date value) {
            addCriterion("data <=", value, "data");
            return this;
        }

        public Criteria andDataIn(List values) {
            addCriterion("data in", values, "data");
            return this;
        }

        public Criteria andDataNotIn(List values) {
            addCriterion("data not in", values, "data");
            return this;
        }

        public Criteria andDataBetween(Date value1, Date value2) {
            addCriterion("data between", value1, value2, "data");
            return this;
        }

        public Criteria andDataNotBetween(Date value1, Date value2) {
            addCriterion("data not between", value1, value2, "data");
            return this;
        }
    }
}
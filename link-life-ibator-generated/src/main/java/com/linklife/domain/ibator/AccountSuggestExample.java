package com.linklife.domain.ibator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * AccountSuggest.java
 * </p>
 * 
 * <pre>
 * 用户建议表对应实体类
 * </pre>
 * 
 * @author caisupeng
 */
public class AccountSuggestExample {
    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
     */
    protected String orderByClause;

    /**
     * This field was generated by Apache iBATIS ibator.
     * This field corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
     */
    protected List oredCriteria;

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
     */
    public AccountSuggestExample() {
        oredCriteria = new ArrayList();
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
     */
    protected AccountSuggestExample(AccountSuggestExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
     */
    public List getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
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
     * This method corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by Apache iBATIS ibator.
     * This method corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
     */
    public void clear() {
        oredCriteria.clear();
    }

    /**
     * This class was generated by Apache iBATIS ibator.
     * This class corresponds to the database table account_suggest
     *
     * @ibatorgenerated Sun Aug 24 16:58:06 CST 2014
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

        public Criteria andSuggestIdIsNull() {
            addCriterion("suggest_id is null");
            return this;
        }

        public Criteria andSuggestIdIsNotNull() {
            addCriterion("suggest_id is not null");
            return this;
        }

        public Criteria andSuggestIdEqualTo(Integer value) {
            addCriterion("suggest_id =", value, "suggestId");
            return this;
        }

        public Criteria andSuggestIdNotEqualTo(Integer value) {
            addCriterion("suggest_id <>", value, "suggestId");
            return this;
        }

        public Criteria andSuggestIdGreaterThan(Integer value) {
            addCriterion("suggest_id >", value, "suggestId");
            return this;
        }

        public Criteria andSuggestIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("suggest_id >=", value, "suggestId");
            return this;
        }

        public Criteria andSuggestIdLessThan(Integer value) {
            addCriterion("suggest_id <", value, "suggestId");
            return this;
        }

        public Criteria andSuggestIdLessThanOrEqualTo(Integer value) {
            addCriterion("suggest_id <=", value, "suggestId");
            return this;
        }

        public Criteria andSuggestIdIn(List values) {
            addCriterion("suggest_id in", values, "suggestId");
            return this;
        }

        public Criteria andSuggestIdNotIn(List values) {
            addCriterion("suggest_id not in", values, "suggestId");
            return this;
        }

        public Criteria andSuggestIdBetween(Integer value1, Integer value2) {
            addCriterion("suggest_id between", value1, value2, "suggestId");
            return this;
        }

        public Criteria andSuggestIdNotBetween(Integer value1, Integer value2) {
            addCriterion("suggest_id not between", value1, value2, "suggestId");
            return this;
        }

        public Criteria andAcountIdIsNull() {
            addCriterion("acount_id is null");
            return this;
        }

        public Criteria andAcountIdIsNotNull() {
            addCriterion("acount_id is not null");
            return this;
        }

        public Criteria andAcountIdEqualTo(Integer value) {
            addCriterion("acount_id =", value, "acountId");
            return this;
        }

        public Criteria andAcountIdNotEqualTo(Integer value) {
            addCriterion("acount_id <>", value, "acountId");
            return this;
        }

        public Criteria andAcountIdGreaterThan(Integer value) {
            addCriterion("acount_id >", value, "acountId");
            return this;
        }

        public Criteria andAcountIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("acount_id >=", value, "acountId");
            return this;
        }

        public Criteria andAcountIdLessThan(Integer value) {
            addCriterion("acount_id <", value, "acountId");
            return this;
        }

        public Criteria andAcountIdLessThanOrEqualTo(Integer value) {
            addCriterion("acount_id <=", value, "acountId");
            return this;
        }

        public Criteria andAcountIdIn(List values) {
            addCriterion("acount_id in", values, "acountId");
            return this;
        }

        public Criteria andAcountIdNotIn(List values) {
            addCriterion("acount_id not in", values, "acountId");
            return this;
        }

        public Criteria andAcountIdBetween(Integer value1, Integer value2) {
            addCriterion("acount_id between", value1, value2, "acountId");
            return this;
        }

        public Criteria andAcountIdNotBetween(Integer value1, Integer value2) {
            addCriterion("acount_id not between", value1, value2, "acountId");
            return this;
        }

        public Criteria andSuggestIsNull() {
            addCriterion("suggest is null");
            return this;
        }

        public Criteria andSuggestIsNotNull() {
            addCriterion("suggest is not null");
            return this;
        }

        public Criteria andSuggestEqualTo(String value) {
            addCriterion("suggest =", value, "suggest");
            return this;
        }

        public Criteria andSuggestNotEqualTo(String value) {
            addCriterion("suggest <>", value, "suggest");
            return this;
        }

        public Criteria andSuggestGreaterThan(String value) {
            addCriterion("suggest >", value, "suggest");
            return this;
        }

        public Criteria andSuggestGreaterThanOrEqualTo(String value) {
            addCriterion("suggest >=", value, "suggest");
            return this;
        }

        public Criteria andSuggestLessThan(String value) {
            addCriterion("suggest <", value, "suggest");
            return this;
        }

        public Criteria andSuggestLessThanOrEqualTo(String value) {
            addCriterion("suggest <=", value, "suggest");
            return this;
        }

        public Criteria andSuggestLike(String value) {
            addCriterion("suggest like", value, "suggest");
            return this;
        }

        public Criteria andSuggestNotLike(String value) {
            addCriterion("suggest not like", value, "suggest");
            return this;
        }

        public Criteria andSuggestIn(List values) {
            addCriterion("suggest in", values, "suggest");
            return this;
        }

        public Criteria andSuggestNotIn(List values) {
            addCriterion("suggest not in", values, "suggest");
            return this;
        }

        public Criteria andSuggestBetween(String value1, String value2) {
            addCriterion("suggest between", value1, value2, "suggest");
            return this;
        }

        public Criteria andSuggestNotBetween(String value1, String value2) {
            addCriterion("suggest not between", value1, value2, "suggest");
            return this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return this;
        }

        public Criteria andAddTimeIn(List values) {
            addCriterion("add_time in", values, "addTime");
            return this;
        }

        public Criteria andAddTimeNotIn(List values) {
            addCriterion("add_time not in", values, "addTime");
            return this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return this;
        }

        public Criteria andAddIpIsNull() {
            addCriterion("add_ip is null");
            return this;
        }

        public Criteria andAddIpIsNotNull() {
            addCriterion("add_ip is not null");
            return this;
        }

        public Criteria andAddIpEqualTo(String value) {
            addCriterion("add_ip =", value, "addIp");
            return this;
        }

        public Criteria andAddIpNotEqualTo(String value) {
            addCriterion("add_ip <>", value, "addIp");
            return this;
        }

        public Criteria andAddIpGreaterThan(String value) {
            addCriterion("add_ip >", value, "addIp");
            return this;
        }

        public Criteria andAddIpGreaterThanOrEqualTo(String value) {
            addCriterion("add_ip >=", value, "addIp");
            return this;
        }

        public Criteria andAddIpLessThan(String value) {
            addCriterion("add_ip <", value, "addIp");
            return this;
        }

        public Criteria andAddIpLessThanOrEqualTo(String value) {
            addCriterion("add_ip <=", value, "addIp");
            return this;
        }

        public Criteria andAddIpLike(String value) {
            addCriterion("add_ip like", value, "addIp");
            return this;
        }

        public Criteria andAddIpNotLike(String value) {
            addCriterion("add_ip not like", value, "addIp");
            return this;
        }

        public Criteria andAddIpIn(List values) {
            addCriterion("add_ip in", values, "addIp");
            return this;
        }

        public Criteria andAddIpNotIn(List values) {
            addCriterion("add_ip not in", values, "addIp");
            return this;
        }

        public Criteria andAddIpBetween(String value1, String value2) {
            addCriterion("add_ip between", value1, value2, "addIp");
            return this;
        }

        public Criteria andAddIpNotBetween(String value1, String value2) {
            addCriterion("add_ip not between", value1, value2, "addIp");
            return this;
        }
    }
}
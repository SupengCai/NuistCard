package com.linklife.domain.ibator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NuistTradeHistoryExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	public NuistTradeHistoryExample() {

		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	protected NuistTradeHistoryExample( NuistTradeHistoryExample example ) {

		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	public void setOrderByClause( String orderByClause ) {

		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	public String getOrderByClause() {

		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	public List getOredCriteria() {

		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	public void or( Criteria criteria ) {

		oredCriteria.add( criteria );
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	public Criteria createCriteria() {

		Criteria criteria = createCriteriaInternal();
		if( oredCriteria.size() == 0 ) {
			oredCriteria.add( criteria );
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	protected Criteria createCriteriaInternal() {

		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
	 */
	public void clear() {

		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table nuist_trade_history
	 * @ibatorgenerated  Sun Jul 12 10:32:21 CST 2015
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

			return criteriaWithoutValue.size() > 0 || criteriaWithSingleValue.size() > 0 || criteriaWithListValue.size() > 0
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


		protected void addCriterion( String condition ) {

			if( condition == null ) {
				throw new RuntimeException( "Value for condition cannot be null" );
			}
			criteriaWithoutValue.add( condition );
		}


		protected void addCriterion( String condition, Object value, String property ) {

			if( value == null ) {
				throw new RuntimeException( "Value for " + property + " cannot be null" );
			}
			Map map = new HashMap();
			map.put( "condition", condition );
			map.put( "value", value );
			criteriaWithSingleValue.add( map );
		}


		protected void addCriterion( String condition, List values, String property ) {

			if( values == null || values.size() == 0 ) {
				throw new RuntimeException( "Value list for " + property + " cannot be null or empty" );
			}
			Map map = new HashMap();
			map.put( "condition", condition );
			map.put( "values", values );
			criteriaWithListValue.add( map );
		}


		protected void addCriterion( String condition, Object value1, Object value2, String property ) {

			if( value1 == null || value2 == null ) {
				throw new RuntimeException( "Between values for " + property + " cannot be null" );
			}
			List list = new ArrayList();
			list.add( value1 );
			list.add( value2 );
			Map map = new HashMap();
			map.put( "condition", condition );
			map.put( "values", list );
			criteriaWithBetweenValue.add( map );
		}


		public Criteria andTradeIdIsNull() {

			addCriterion( "trade_id is null" );
			return this;
		}


		public Criteria andTradeIdIsNotNull() {

			addCriterion( "trade_id is not null" );
			return this;
		}


		public Criteria andTradeIdEqualTo( Integer value ) {

			addCriterion( "trade_id =", value, "tradeId" );
			return this;
		}


		public Criteria andTradeIdNotEqualTo( Integer value ) {

			addCriterion( "trade_id <>", value, "tradeId" );
			return this;
		}


		public Criteria andTradeIdGreaterThan( Integer value ) {

			addCriterion( "trade_id >", value, "tradeId" );
			return this;
		}


		public Criteria andTradeIdGreaterThanOrEqualTo( Integer value ) {

			addCriterion( "trade_id >=", value, "tradeId" );
			return this;
		}


		public Criteria andTradeIdLessThan( Integer value ) {

			addCriterion( "trade_id <", value, "tradeId" );
			return this;
		}


		public Criteria andTradeIdLessThanOrEqualTo( Integer value ) {

			addCriterion( "trade_id <=", value, "tradeId" );
			return this;
		}


		public Criteria andTradeIdIn( List values ) {

			addCriterion( "trade_id in", values, "tradeId" );
			return this;
		}


		public Criteria andTradeIdNotIn( List values ) {

			addCriterion( "trade_id not in", values, "tradeId" );
			return this;
		}


		public Criteria andTradeIdBetween( Integer value1, Integer value2 ) {

			addCriterion( "trade_id between", value1, value2, "tradeId" );
			return this;
		}


		public Criteria andTradeIdNotBetween( Integer value1, Integer value2 ) {

			addCriterion( "trade_id not between", value1, value2, "tradeId" );
			return this;
		}


		public Criteria andAccountIdIsNull() {

			addCriterion( "account_id is null" );
			return this;
		}


		public Criteria andAccountIdIsNotNull() {

			addCriterion( "account_id is not null" );
			return this;
		}


		public Criteria andAccountIdEqualTo( Integer value ) {

			addCriterion( "account_id =", value, "accountId" );
			return this;
		}


		public Criteria andAccountIdNotEqualTo( Integer value ) {

			addCriterion( "account_id <>", value, "accountId" );
			return this;
		}


		public Criteria andAccountIdGreaterThan( Integer value ) {

			addCriterion( "account_id >", value, "accountId" );
			return this;
		}


		public Criteria andAccountIdGreaterThanOrEqualTo( Integer value ) {

			addCriterion( "account_id >=", value, "accountId" );
			return this;
		}


		public Criteria andAccountIdLessThan( Integer value ) {

			addCriterion( "account_id <", value, "accountId" );
			return this;
		}


		public Criteria andAccountIdLessThanOrEqualTo( Integer value ) {

			addCriterion( "account_id <=", value, "accountId" );
			return this;
		}


		public Criteria andAccountIdIn( List values ) {

			addCriterion( "account_id in", values, "accountId" );
			return this;
		}


		public Criteria andAccountIdNotIn( List values ) {

			addCriterion( "account_id not in", values, "accountId" );
			return this;
		}


		public Criteria andAccountIdBetween( Integer value1, Integer value2 ) {

			addCriterion( "account_id between", value1, value2, "accountId" );
			return this;
		}


		public Criteria andAccountIdNotBetween( Integer value1, Integer value2 ) {

			addCriterion( "account_id not between", value1, value2, "accountId" );
			return this;
		}


		public Criteria andUserNumberIsNull() {

			addCriterion( "user_number is null" );
			return this;
		}


		public Criteria andUserNumberIsNotNull() {

			addCriterion( "user_number is not null" );
			return this;
		}


		public Criteria andUserNumberEqualTo( String value ) {

			addCriterion( "user_number =", value, "userNumber" );
			return this;
		}


		public Criteria andUserNumberNotEqualTo( String value ) {

			addCriterion( "user_number <>", value, "userNumber" );
			return this;
		}


		public Criteria andUserNumberGreaterThan( String value ) {

			addCriterion( "user_number >", value, "userNumber" );
			return this;
		}


		public Criteria andUserNumberGreaterThanOrEqualTo( String value ) {

			addCriterion( "user_number >=", value, "userNumber" );
			return this;
		}


		public Criteria andUserNumberLessThan( String value ) {

			addCriterion( "user_number <", value, "userNumber" );
			return this;
		}


		public Criteria andUserNumberLessThanOrEqualTo( String value ) {

			addCriterion( "user_number <=", value, "userNumber" );
			return this;
		}


		public Criteria andUserNumberLike( String value ) {

			addCriterion( "user_number like", value, "userNumber" );
			return this;
		}


		public Criteria andUserNumberNotLike( String value ) {

			addCriterion( "user_number not like", value, "userNumber" );
			return this;
		}


		public Criteria andUserNumberIn( List values ) {

			addCriterion( "user_number in", values, "userNumber" );
			return this;
		}


		public Criteria andUserNumberNotIn( List values ) {

			addCriterion( "user_number not in", values, "userNumber" );
			return this;
		}


		public Criteria andUserNumberBetween( String value1, String value2 ) {

			addCriterion( "user_number between", value1, value2, "userNumber" );
			return this;
		}


		public Criteria andUserNumberNotBetween( String value1, String value2 ) {

			addCriterion( "user_number not between", value1, value2, "userNumber" );
			return this;
		}


		public Criteria andShopIdIsNull() {

			addCriterion( "shop_id is null" );
			return this;
		}


		public Criteria andShopIdIsNotNull() {

			addCriterion( "shop_id is not null" );
			return this;
		}


		public Criteria andShopIdEqualTo( Integer value ) {

			addCriterion( "shop_id =", value, "shopId" );
			return this;
		}


		public Criteria andShopIdNotEqualTo( Integer value ) {

			addCriterion( "shop_id <>", value, "shopId" );
			return this;
		}


		public Criteria andShopIdGreaterThan( Integer value ) {

			addCriterion( "shop_id >", value, "shopId" );
			return this;
		}


		public Criteria andShopIdGreaterThanOrEqualTo( Integer value ) {

			addCriterion( "shop_id >=", value, "shopId" );
			return this;
		}


		public Criteria andShopIdLessThan( Integer value ) {

			addCriterion( "shop_id <", value, "shopId" );
			return this;
		}


		public Criteria andShopIdLessThanOrEqualTo( Integer value ) {

			addCriterion( "shop_id <=", value, "shopId" );
			return this;
		}


		public Criteria andShopIdIn( List values ) {

			addCriterion( "shop_id in", values, "shopId" );
			return this;
		}


		public Criteria andShopIdNotIn( List values ) {

			addCriterion( "shop_id not in", values, "shopId" );
			return this;
		}


		public Criteria andShopIdBetween( Integer value1, Integer value2 ) {

			addCriterion( "shop_id between", value1, value2, "shopId" );
			return this;
		}


		public Criteria andShopIdNotBetween( Integer value1, Integer value2 ) {

			addCriterion( "shop_id not between", value1, value2, "shopId" );
			return this;
		}


		public Criteria andShopNameIsNull() {

			addCriterion( "shop_name is null" );
			return this;
		}


		public Criteria andShopNameIsNotNull() {

			addCriterion( "shop_name is not null" );
			return this;
		}


		public Criteria andShopNameEqualTo( String value ) {

			addCriterion( "shop_name =", value, "shopName" );
			return this;
		}


		public Criteria andShopNameNotEqualTo( String value ) {

			addCriterion( "shop_name <>", value, "shopName" );
			return this;
		}


		public Criteria andShopNameGreaterThan( String value ) {

			addCriterion( "shop_name >", value, "shopName" );
			return this;
		}


		public Criteria andShopNameGreaterThanOrEqualTo( String value ) {

			addCriterion( "shop_name >=", value, "shopName" );
			return this;
		}


		public Criteria andShopNameLessThan( String value ) {

			addCriterion( "shop_name <", value, "shopName" );
			return this;
		}


		public Criteria andShopNameLessThanOrEqualTo( String value ) {

			addCriterion( "shop_name <=", value, "shopName" );
			return this;
		}


		public Criteria andShopNameLike( String value ) {

			addCriterion( "shop_name like", value, "shopName" );
			return this;
		}


		public Criteria andShopNameNotLike( String value ) {

			addCriterion( "shop_name not like", value, "shopName" );
			return this;
		}


		public Criteria andShopNameIn( List values ) {

			addCriterion( "shop_name in", values, "shopName" );
			return this;
		}


		public Criteria andShopNameNotIn( List values ) {

			addCriterion( "shop_name not in", values, "shopName" );
			return this;
		}


		public Criteria andShopNameBetween( String value1, String value2 ) {

			addCriterion( "shop_name between", value1, value2, "shopName" );
			return this;
		}


		public Criteria andShopNameNotBetween( String value1, String value2 ) {

			addCriterion( "shop_name not between", value1, value2, "shopName" );
			return this;
		}


		public Criteria andShopAreaIsNull() {

			addCriterion( "shop_area is null" );
			return this;
		}


		public Criteria andShopAreaIsNotNull() {

			addCriterion( "shop_area is not null" );
			return this;
		}


		public Criteria andShopAreaEqualTo( Integer value ) {

			addCriterion( "shop_area =", value, "shopArea" );
			return this;
		}


		public Criteria andShopAreaNotEqualTo( Integer value ) {

			addCriterion( "shop_area <>", value, "shopArea" );
			return this;
		}


		public Criteria andShopAreaGreaterThan( Integer value ) {

			addCriterion( "shop_area >", value, "shopArea" );
			return this;
		}


		public Criteria andShopAreaGreaterThanOrEqualTo( Integer value ) {

			addCriterion( "shop_area >=", value, "shopArea" );
			return this;
		}


		public Criteria andShopAreaLessThan( Integer value ) {

			addCriterion( "shop_area <", value, "shopArea" );
			return this;
		}


		public Criteria andShopAreaLessThanOrEqualTo( Integer value ) {

			addCriterion( "shop_area <=", value, "shopArea" );
			return this;
		}


		public Criteria andShopAreaIn( List values ) {

			addCriterion( "shop_area in", values, "shopArea" );
			return this;
		}


		public Criteria andShopAreaNotIn( List values ) {

			addCriterion( "shop_area not in", values, "shopArea" );
			return this;
		}


		public Criteria andShopAreaBetween( Integer value1, Integer value2 ) {

			addCriterion( "shop_area between", value1, value2, "shopArea" );
			return this;
		}


		public Criteria andShopAreaNotBetween( Integer value1, Integer value2 ) {

			addCriterion( "shop_area not between", value1, value2, "shopArea" );
			return this;
		}


		public Criteria andTradeAmountIsNull() {

			addCriterion( "trade_amount is null" );
			return this;
		}


		public Criteria andTradeAmountIsNotNull() {

			addCriterion( "trade_amount is not null" );
			return this;
		}


		public Criteria andTradeAmountEqualTo( Double value ) {

			addCriterion( "trade_amount =", value, "tradeAmount" );
			return this;
		}


		public Criteria andTradeAmountNotEqualTo( Double value ) {

			addCriterion( "trade_amount <>", value, "tradeAmount" );
			return this;
		}


		public Criteria andTradeAmountGreaterThan( Double value ) {

			addCriterion( "trade_amount >", value, "tradeAmount" );
			return this;
		}


		public Criteria andTradeAmountGreaterThanOrEqualTo( Double value ) {

			addCriterion( "trade_amount >=", value, "tradeAmount" );
			return this;
		}


		public Criteria andTradeAmountLessThan( Double value ) {

			addCriterion( "trade_amount <", value, "tradeAmount" );
			return this;
		}


		public Criteria andTradeAmountLessThanOrEqualTo( Double value ) {

			addCriterion( "trade_amount <=", value, "tradeAmount" );
			return this;
		}


		public Criteria andTradeAmountIn( List values ) {

			addCriterion( "trade_amount in", values, "tradeAmount" );
			return this;
		}


		public Criteria andTradeAmountNotIn( List values ) {

			addCriterion( "trade_amount not in", values, "tradeAmount" );
			return this;
		}


		public Criteria andTradeAmountBetween( Double value1, Double value2 ) {

			addCriterion( "trade_amount between", value1, value2, "tradeAmount" );
			return this;
		}


		public Criteria andTradeAmountNotBetween( Double value1, Double value2 ) {

			addCriterion( "trade_amount not between", value1, value2, "tradeAmount" );
			return this;
		}


		public Criteria andTradeTypeIsNull() {

			addCriterion( "trade_type is null" );
			return this;
		}


		public Criteria andTradeTypeIsNotNull() {

			addCriterion( "trade_type is not null" );
			return this;
		}


		public Criteria andTradeTypeEqualTo( Integer value ) {

			addCriterion( "trade_type =", value, "tradeType" );
			return this;
		}


		public Criteria andTradeTypeNotEqualTo( Integer value ) {

			addCriterion( "trade_type <>", value, "tradeType" );
			return this;
		}


		public Criteria andTradeTypeGreaterThan( Integer value ) {

			addCriterion( "trade_type >", value, "tradeType" );
			return this;
		}


		public Criteria andTradeTypeGreaterThanOrEqualTo( Integer value ) {

			addCriterion( "trade_type >=", value, "tradeType" );
			return this;
		}


		public Criteria andTradeTypeLessThan( Integer value ) {

			addCriterion( "trade_type <", value, "tradeType" );
			return this;
		}


		public Criteria andTradeTypeLessThanOrEqualTo( Integer value ) {

			addCriterion( "trade_type <=", value, "tradeType" );
			return this;
		}


		public Criteria andTradeTypeIn( List values ) {

			addCriterion( "trade_type in", values, "tradeType" );
			return this;
		}


		public Criteria andTradeTypeNotIn( List values ) {

			addCriterion( "trade_type not in", values, "tradeType" );
			return this;
		}


		public Criteria andTradeTypeBetween( Integer value1, Integer value2 ) {

			addCriterion( "trade_type between", value1, value2, "tradeType" );
			return this;
		}


		public Criteria andTradeTypeNotBetween( Integer value1, Integer value2 ) {

			addCriterion( "trade_type not between", value1, value2, "tradeType" );
			return this;
		}


		public Criteria andTradeTimeIsNull() {

			addCriterion( "trade_time is null" );
			return this;
		}


		public Criteria andTradeTimeIsNotNull() {

			addCriterion( "trade_time is not null" );
			return this;
		}


		public Criteria andTradeTimeEqualTo( Date value ) {

			addCriterion( "trade_time =", value, "tradeTime" );
			return this;
		}


		public Criteria andTradeTimeNotEqualTo( Date value ) {

			addCriterion( "trade_time <>", value, "tradeTime" );
			return this;
		}


		public Criteria andTradeTimeGreaterThan( Date value ) {

			addCriterion( "trade_time >", value, "tradeTime" );
			return this;
		}


		public Criteria andTradeTimeGreaterThanOrEqualTo( Date value ) {

			addCriterion( "trade_time >=", value, "tradeTime" );
			return this;
		}


		public Criteria andTradeTimeLessThan( Date value ) {

			addCriterion( "trade_time <", value, "tradeTime" );
			return this;
		}


		public Criteria andTradeTimeLessThanOrEqualTo( Date value ) {

			addCriterion( "trade_time <=", value, "tradeTime" );
			return this;
		}


		public Criteria andTradeTimeIn( List values ) {

			addCriterion( "trade_time in", values, "tradeTime" );
			return this;
		}


		public Criteria andTradeTimeNotIn( List values ) {

			addCriterion( "trade_time not in", values, "tradeTime" );
			return this;
		}


		public Criteria andTradeTimeBetween( Date value1, Date value2 ) {

			addCriterion( "trade_time between", value1, value2, "tradeTime" );
			return this;
		}


		public Criteria andTradeTimeNotBetween( Date value1, Date value2 ) {

			addCriterion( "trade_time not between", value1, value2, "tradeTime" );
			return this;
		}
	}
}
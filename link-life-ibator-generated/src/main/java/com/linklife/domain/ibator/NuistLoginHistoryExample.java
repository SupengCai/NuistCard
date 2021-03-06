package com.linklife.domain.ibator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NuistLoginHistoryExample {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	protected String orderByClause;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	protected List oredCriteria;

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public NuistLoginHistoryExample() {

		oredCriteria = new ArrayList();
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	protected NuistLoginHistoryExample( NuistLoginHistoryExample example ) {

		this.orderByClause = example.orderByClause;
		this.oredCriteria = example.oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public void setOrderByClause( String orderByClause ) {

		this.orderByClause = orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public String getOrderByClause() {

		return orderByClause;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public List getOredCriteria() {

		return oredCriteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public void or( Criteria criteria ) {

		oredCriteria.add( criteria );
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public Criteria createCriteria() {

		Criteria criteria = createCriteriaInternal();
		if( oredCriteria.size() == 0 ) {
			oredCriteria.add( criteria );
		}
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	protected Criteria createCriteriaInternal() {

		Criteria criteria = new Criteria();
		return criteria;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
	 */
	public void clear() {

		oredCriteria.clear();
	}

	/**
	 * This class was generated by Apache iBATIS ibator. This class corresponds to the database table nuist_login_history
	 * @ibatorgenerated  Tue Jan 27 09:10:44 CST 2015
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


		public Criteria andSigninIdIsNull() {

			addCriterion( "signin_id is null" );
			return this;
		}


		public Criteria andSigninIdIsNotNull() {

			addCriterion( "signin_id is not null" );
			return this;
		}


		public Criteria andSigninIdEqualTo( Integer value ) {

			addCriterion( "signin_id =", value, "signinId" );
			return this;
		}


		public Criteria andSigninIdNotEqualTo( Integer value ) {

			addCriterion( "signin_id <>", value, "signinId" );
			return this;
		}


		public Criteria andSigninIdGreaterThan( Integer value ) {

			addCriterion( "signin_id >", value, "signinId" );
			return this;
		}


		public Criteria andSigninIdGreaterThanOrEqualTo( Integer value ) {

			addCriterion( "signin_id >=", value, "signinId" );
			return this;
		}


		public Criteria andSigninIdLessThan( Integer value ) {

			addCriterion( "signin_id <", value, "signinId" );
			return this;
		}


		public Criteria andSigninIdLessThanOrEqualTo( Integer value ) {

			addCriterion( "signin_id <=", value, "signinId" );
			return this;
		}


		public Criteria andSigninIdIn( List values ) {

			addCriterion( "signin_id in", values, "signinId" );
			return this;
		}


		public Criteria andSigninIdNotIn( List values ) {

			addCriterion( "signin_id not in", values, "signinId" );
			return this;
		}


		public Criteria andSigninIdBetween( Integer value1, Integer value2 ) {

			addCriterion( "signin_id between", value1, value2, "signinId" );
			return this;
		}


		public Criteria andSigninIdNotBetween( Integer value1, Integer value2 ) {

			addCriterion( "signin_id not between", value1, value2, "signinId" );
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


		public Criteria andTimeIsNull() {

			addCriterion( "time is null" );
			return this;
		}


		public Criteria andTimeIsNotNull() {

			addCriterion( "time is not null" );
			return this;
		}


		public Criteria andTimeEqualTo( Date value ) {

			addCriterion( "time =", value, "time" );
			return this;
		}


		public Criteria andTimeNotEqualTo( Date value ) {

			addCriterion( "time <>", value, "time" );
			return this;
		}


		public Criteria andTimeGreaterThan( Date value ) {

			addCriterion( "time >", value, "time" );
			return this;
		}


		public Criteria andTimeGreaterThanOrEqualTo( Date value ) {

			addCriterion( "time >=", value, "time" );
			return this;
		}


		public Criteria andTimeLessThan( Date value ) {

			addCriterion( "time <", value, "time" );
			return this;
		}


		public Criteria andTimeLessThanOrEqualTo( Date value ) {

			addCriterion( "time <=", value, "time" );
			return this;
		}


		public Criteria andTimeIn( List values ) {

			addCriterion( "time in", values, "time" );
			return this;
		}


		public Criteria andTimeNotIn( List values ) {

			addCriterion( "time not in", values, "time" );
			return this;
		}


		public Criteria andTimeBetween( Date value1, Date value2 ) {

			addCriterion( "time between", value1, value2, "time" );
			return this;
		}


		public Criteria andTimeNotBetween( Date value1, Date value2 ) {

			addCriterion( "time not between", value1, value2, "time" );
			return this;
		}


		public Criteria andSigninIpIsNull() {

			addCriterion( "signin_ip is null" );
			return this;
		}


		public Criteria andSigninIpIsNotNull() {

			addCriterion( "signin_ip is not null" );
			return this;
		}


		public Criteria andSigninIpEqualTo( String value ) {

			addCriterion( "signin_ip =", value, "signinIp" );
			return this;
		}


		public Criteria andSigninIpNotEqualTo( String value ) {

			addCriterion( "signin_ip <>", value, "signinIp" );
			return this;
		}


		public Criteria andSigninIpGreaterThan( String value ) {

			addCriterion( "signin_ip >", value, "signinIp" );
			return this;
		}


		public Criteria andSigninIpGreaterThanOrEqualTo( String value ) {

			addCriterion( "signin_ip >=", value, "signinIp" );
			return this;
		}


		public Criteria andSigninIpLessThan( String value ) {

			addCriterion( "signin_ip <", value, "signinIp" );
			return this;
		}


		public Criteria andSigninIpLessThanOrEqualTo( String value ) {

			addCriterion( "signin_ip <=", value, "signinIp" );
			return this;
		}


		public Criteria andSigninIpLike( String value ) {

			addCriterion( "signin_ip like", value, "signinIp" );
			return this;
		}


		public Criteria andSigninIpNotLike( String value ) {

			addCriterion( "signin_ip not like", value, "signinIp" );
			return this;
		}


		public Criteria andSigninIpIn( List values ) {

			addCriterion( "signin_ip in", values, "signinIp" );
			return this;
		}


		public Criteria andSigninIpNotIn( List values ) {

			addCriterion( "signin_ip not in", values, "signinIp" );
			return this;
		}


		public Criteria andSigninIpBetween( String value1, String value2 ) {

			addCriterion( "signin_ip between", value1, value2, "signinIp" );
			return this;
		}


		public Criteria andSigninIpNotBetween( String value1, String value2 ) {

			addCriterion( "signin_ip not between", value1, value2, "signinIp" );
			return this;
		}


		public Criteria andCountryIsNull() {

			addCriterion( "country is null" );
			return this;
		}


		public Criteria andCountryIsNotNull() {

			addCriterion( "country is not null" );
			return this;
		}


		public Criteria andCountryEqualTo( String value ) {

			addCriterion( "country =", value, "country" );
			return this;
		}


		public Criteria andCountryNotEqualTo( String value ) {

			addCriterion( "country <>", value, "country" );
			return this;
		}


		public Criteria andCountryGreaterThan( String value ) {

			addCriterion( "country >", value, "country" );
			return this;
		}


		public Criteria andCountryGreaterThanOrEqualTo( String value ) {

			addCriterion( "country >=", value, "country" );
			return this;
		}


		public Criteria andCountryLessThan( String value ) {

			addCriterion( "country <", value, "country" );
			return this;
		}


		public Criteria andCountryLessThanOrEqualTo( String value ) {

			addCriterion( "country <=", value, "country" );
			return this;
		}


		public Criteria andCountryLike( String value ) {

			addCriterion( "country like", value, "country" );
			return this;
		}


		public Criteria andCountryNotLike( String value ) {

			addCriterion( "country not like", value, "country" );
			return this;
		}


		public Criteria andCountryIn( List values ) {

			addCriterion( "country in", values, "country" );
			return this;
		}


		public Criteria andCountryNotIn( List values ) {

			addCriterion( "country not in", values, "country" );
			return this;
		}


		public Criteria andCountryBetween( String value1, String value2 ) {

			addCriterion( "country between", value1, value2, "country" );
			return this;
		}


		public Criteria andCountryNotBetween( String value1, String value2 ) {

			addCriterion( "country not between", value1, value2, "country" );
			return this;
		}


		public Criteria andCountryIdIsNull() {

			addCriterion( "country_id is null" );
			return this;
		}


		public Criteria andCountryIdIsNotNull() {

			addCriterion( "country_id is not null" );
			return this;
		}


		public Criteria andCountryIdEqualTo( String value ) {

			addCriterion( "country_id =", value, "countryId" );
			return this;
		}


		public Criteria andCountryIdNotEqualTo( String value ) {

			addCriterion( "country_id <>", value, "countryId" );
			return this;
		}


		public Criteria andCountryIdGreaterThan( String value ) {

			addCriterion( "country_id >", value, "countryId" );
			return this;
		}


		public Criteria andCountryIdGreaterThanOrEqualTo( String value ) {

			addCriterion( "country_id >=", value, "countryId" );
			return this;
		}


		public Criteria andCountryIdLessThan( String value ) {

			addCriterion( "country_id <", value, "countryId" );
			return this;
		}


		public Criteria andCountryIdLessThanOrEqualTo( String value ) {

			addCriterion( "country_id <=", value, "countryId" );
			return this;
		}


		public Criteria andCountryIdLike( String value ) {

			addCriterion( "country_id like", value, "countryId" );
			return this;
		}


		public Criteria andCountryIdNotLike( String value ) {

			addCriterion( "country_id not like", value, "countryId" );
			return this;
		}


		public Criteria andCountryIdIn( List values ) {

			addCriterion( "country_id in", values, "countryId" );
			return this;
		}


		public Criteria andCountryIdNotIn( List values ) {

			addCriterion( "country_id not in", values, "countryId" );
			return this;
		}


		public Criteria andCountryIdBetween( String value1, String value2 ) {

			addCriterion( "country_id between", value1, value2, "countryId" );
			return this;
		}


		public Criteria andCountryIdNotBetween( String value1, String value2 ) {

			addCriterion( "country_id not between", value1, value2, "countryId" );
			return this;
		}


		public Criteria andAreaIsNull() {

			addCriterion( "area is null" );
			return this;
		}


		public Criteria andAreaIsNotNull() {

			addCriterion( "area is not null" );
			return this;
		}


		public Criteria andAreaEqualTo( String value ) {

			addCriterion( "area =", value, "area" );
			return this;
		}


		public Criteria andAreaNotEqualTo( String value ) {

			addCriterion( "area <>", value, "area" );
			return this;
		}


		public Criteria andAreaGreaterThan( String value ) {

			addCriterion( "area >", value, "area" );
			return this;
		}


		public Criteria andAreaGreaterThanOrEqualTo( String value ) {

			addCriterion( "area >=", value, "area" );
			return this;
		}


		public Criteria andAreaLessThan( String value ) {

			addCriterion( "area <", value, "area" );
			return this;
		}


		public Criteria andAreaLessThanOrEqualTo( String value ) {

			addCriterion( "area <=", value, "area" );
			return this;
		}


		public Criteria andAreaLike( String value ) {

			addCriterion( "area like", value, "area" );
			return this;
		}


		public Criteria andAreaNotLike( String value ) {

			addCriterion( "area not like", value, "area" );
			return this;
		}


		public Criteria andAreaIn( List values ) {

			addCriterion( "area in", values, "area" );
			return this;
		}


		public Criteria andAreaNotIn( List values ) {

			addCriterion( "area not in", values, "area" );
			return this;
		}


		public Criteria andAreaBetween( String value1, String value2 ) {

			addCriterion( "area between", value1, value2, "area" );
			return this;
		}


		public Criteria andAreaNotBetween( String value1, String value2 ) {

			addCriterion( "area not between", value1, value2, "area" );
			return this;
		}


		public Criteria andAreaIdIsNull() {

			addCriterion( "area_id is null" );
			return this;
		}


		public Criteria andAreaIdIsNotNull() {

			addCriterion( "area_id is not null" );
			return this;
		}


		public Criteria andAreaIdEqualTo( String value ) {

			addCriterion( "area_id =", value, "areaId" );
			return this;
		}


		public Criteria andAreaIdNotEqualTo( String value ) {

			addCriterion( "area_id <>", value, "areaId" );
			return this;
		}


		public Criteria andAreaIdGreaterThan( String value ) {

			addCriterion( "area_id >", value, "areaId" );
			return this;
		}


		public Criteria andAreaIdGreaterThanOrEqualTo( String value ) {

			addCriterion( "area_id >=", value, "areaId" );
			return this;
		}


		public Criteria andAreaIdLessThan( String value ) {

			addCriterion( "area_id <", value, "areaId" );
			return this;
		}


		public Criteria andAreaIdLessThanOrEqualTo( String value ) {

			addCriterion( "area_id <=", value, "areaId" );
			return this;
		}


		public Criteria andAreaIdLike( String value ) {

			addCriterion( "area_id like", value, "areaId" );
			return this;
		}


		public Criteria andAreaIdNotLike( String value ) {

			addCriterion( "area_id not like", value, "areaId" );
			return this;
		}


		public Criteria andAreaIdIn( List values ) {

			addCriterion( "area_id in", values, "areaId" );
			return this;
		}


		public Criteria andAreaIdNotIn( List values ) {

			addCriterion( "area_id not in", values, "areaId" );
			return this;
		}


		public Criteria andAreaIdBetween( String value1, String value2 ) {

			addCriterion( "area_id between", value1, value2, "areaId" );
			return this;
		}


		public Criteria andAreaIdNotBetween( String value1, String value2 ) {

			addCriterion( "area_id not between", value1, value2, "areaId" );
			return this;
		}


		public Criteria andRegionIsNull() {

			addCriterion( "region is null" );
			return this;
		}


		public Criteria andRegionIsNotNull() {

			addCriterion( "region is not null" );
			return this;
		}


		public Criteria andRegionEqualTo( String value ) {

			addCriterion( "region =", value, "region" );
			return this;
		}


		public Criteria andRegionNotEqualTo( String value ) {

			addCriterion( "region <>", value, "region" );
			return this;
		}


		public Criteria andRegionGreaterThan( String value ) {

			addCriterion( "region >", value, "region" );
			return this;
		}


		public Criteria andRegionGreaterThanOrEqualTo( String value ) {

			addCriterion( "region >=", value, "region" );
			return this;
		}


		public Criteria andRegionLessThan( String value ) {

			addCriterion( "region <", value, "region" );
			return this;
		}


		public Criteria andRegionLessThanOrEqualTo( String value ) {

			addCriterion( "region <=", value, "region" );
			return this;
		}


		public Criteria andRegionLike( String value ) {

			addCriterion( "region like", value, "region" );
			return this;
		}


		public Criteria andRegionNotLike( String value ) {

			addCriterion( "region not like", value, "region" );
			return this;
		}


		public Criteria andRegionIn( List values ) {

			addCriterion( "region in", values, "region" );
			return this;
		}


		public Criteria andRegionNotIn( List values ) {

			addCriterion( "region not in", values, "region" );
			return this;
		}


		public Criteria andRegionBetween( String value1, String value2 ) {

			addCriterion( "region between", value1, value2, "region" );
			return this;
		}


		public Criteria andRegionNotBetween( String value1, String value2 ) {

			addCriterion( "region not between", value1, value2, "region" );
			return this;
		}


		public Criteria andRegionIdIsNull() {

			addCriterion( "region_id is null" );
			return this;
		}


		public Criteria andRegionIdIsNotNull() {

			addCriterion( "region_id is not null" );
			return this;
		}


		public Criteria andRegionIdEqualTo( String value ) {

			addCriterion( "region_id =", value, "regionId" );
			return this;
		}


		public Criteria andRegionIdNotEqualTo( String value ) {

			addCriterion( "region_id <>", value, "regionId" );
			return this;
		}


		public Criteria andRegionIdGreaterThan( String value ) {

			addCriterion( "region_id >", value, "regionId" );
			return this;
		}


		public Criteria andRegionIdGreaterThanOrEqualTo( String value ) {

			addCriterion( "region_id >=", value, "regionId" );
			return this;
		}


		public Criteria andRegionIdLessThan( String value ) {

			addCriterion( "region_id <", value, "regionId" );
			return this;
		}


		public Criteria andRegionIdLessThanOrEqualTo( String value ) {

			addCriterion( "region_id <=", value, "regionId" );
			return this;
		}


		public Criteria andRegionIdLike( String value ) {

			addCriterion( "region_id like", value, "regionId" );
			return this;
		}


		public Criteria andRegionIdNotLike( String value ) {

			addCriterion( "region_id not like", value, "regionId" );
			return this;
		}


		public Criteria andRegionIdIn( List values ) {

			addCriterion( "region_id in", values, "regionId" );
			return this;
		}


		public Criteria andRegionIdNotIn( List values ) {

			addCriterion( "region_id not in", values, "regionId" );
			return this;
		}


		public Criteria andRegionIdBetween( String value1, String value2 ) {

			addCriterion( "region_id between", value1, value2, "regionId" );
			return this;
		}


		public Criteria andRegionIdNotBetween( String value1, String value2 ) {

			addCriterion( "region_id not between", value1, value2, "regionId" );
			return this;
		}


		public Criteria andCityIsNull() {

			addCriterion( "city is null" );
			return this;
		}


		public Criteria andCityIsNotNull() {

			addCriterion( "city is not null" );
			return this;
		}


		public Criteria andCityEqualTo( String value ) {

			addCriterion( "city =", value, "city" );
			return this;
		}


		public Criteria andCityNotEqualTo( String value ) {

			addCriterion( "city <>", value, "city" );
			return this;
		}


		public Criteria andCityGreaterThan( String value ) {

			addCriterion( "city >", value, "city" );
			return this;
		}


		public Criteria andCityGreaterThanOrEqualTo( String value ) {

			addCriterion( "city >=", value, "city" );
			return this;
		}


		public Criteria andCityLessThan( String value ) {

			addCriterion( "city <", value, "city" );
			return this;
		}


		public Criteria andCityLessThanOrEqualTo( String value ) {

			addCriterion( "city <=", value, "city" );
			return this;
		}


		public Criteria andCityLike( String value ) {

			addCriterion( "city like", value, "city" );
			return this;
		}


		public Criteria andCityNotLike( String value ) {

			addCriterion( "city not like", value, "city" );
			return this;
		}


		public Criteria andCityIn( List values ) {

			addCriterion( "city in", values, "city" );
			return this;
		}


		public Criteria andCityNotIn( List values ) {

			addCriterion( "city not in", values, "city" );
			return this;
		}


		public Criteria andCityBetween( String value1, String value2 ) {

			addCriterion( "city between", value1, value2, "city" );
			return this;
		}


		public Criteria andCityNotBetween( String value1, String value2 ) {

			addCriterion( "city not between", value1, value2, "city" );
			return this;
		}


		public Criteria andCityIdIsNull() {

			addCriterion( "city_id is null" );
			return this;
		}


		public Criteria andCityIdIsNotNull() {

			addCriterion( "city_id is not null" );
			return this;
		}


		public Criteria andCityIdEqualTo( String value ) {

			addCriterion( "city_id =", value, "cityId" );
			return this;
		}


		public Criteria andCityIdNotEqualTo( String value ) {

			addCriterion( "city_id <>", value, "cityId" );
			return this;
		}


		public Criteria andCityIdGreaterThan( String value ) {

			addCriterion( "city_id >", value, "cityId" );
			return this;
		}


		public Criteria andCityIdGreaterThanOrEqualTo( String value ) {

			addCriterion( "city_id >=", value, "cityId" );
			return this;
		}


		public Criteria andCityIdLessThan( String value ) {

			addCriterion( "city_id <", value, "cityId" );
			return this;
		}


		public Criteria andCityIdLessThanOrEqualTo( String value ) {

			addCriterion( "city_id <=", value, "cityId" );
			return this;
		}


		public Criteria andCityIdLike( String value ) {

			addCriterion( "city_id like", value, "cityId" );
			return this;
		}


		public Criteria andCityIdNotLike( String value ) {

			addCriterion( "city_id not like", value, "cityId" );
			return this;
		}


		public Criteria andCityIdIn( List values ) {

			addCriterion( "city_id in", values, "cityId" );
			return this;
		}


		public Criteria andCityIdNotIn( List values ) {

			addCriterion( "city_id not in", values, "cityId" );
			return this;
		}


		public Criteria andCityIdBetween( String value1, String value2 ) {

			addCriterion( "city_id between", value1, value2, "cityId" );
			return this;
		}


		public Criteria andCityIdNotBetween( String value1, String value2 ) {

			addCriterion( "city_id not between", value1, value2, "cityId" );
			return this;
		}


		public Criteria andIspIsNull() {

			addCriterion( "isp is null" );
			return this;
		}


		public Criteria andIspIsNotNull() {

			addCriterion( "isp is not null" );
			return this;
		}


		public Criteria andIspEqualTo( String value ) {

			addCriterion( "isp =", value, "isp" );
			return this;
		}


		public Criteria andIspNotEqualTo( String value ) {

			addCriterion( "isp <>", value, "isp" );
			return this;
		}


		public Criteria andIspGreaterThan( String value ) {

			addCriterion( "isp >", value, "isp" );
			return this;
		}


		public Criteria andIspGreaterThanOrEqualTo( String value ) {

			addCriterion( "isp >=", value, "isp" );
			return this;
		}


		public Criteria andIspLessThan( String value ) {

			addCriterion( "isp <", value, "isp" );
			return this;
		}


		public Criteria andIspLessThanOrEqualTo( String value ) {

			addCriterion( "isp <=", value, "isp" );
			return this;
		}


		public Criteria andIspLike( String value ) {

			addCriterion( "isp like", value, "isp" );
			return this;
		}


		public Criteria andIspNotLike( String value ) {

			addCriterion( "isp not like", value, "isp" );
			return this;
		}


		public Criteria andIspIn( List values ) {

			addCriterion( "isp in", values, "isp" );
			return this;
		}


		public Criteria andIspNotIn( List values ) {

			addCriterion( "isp not in", values, "isp" );
			return this;
		}


		public Criteria andIspBetween( String value1, String value2 ) {

			addCriterion( "isp between", value1, value2, "isp" );
			return this;
		}


		public Criteria andIspNotBetween( String value1, String value2 ) {

			addCriterion( "isp not between", value1, value2, "isp" );
			return this;
		}


		public Criteria andIspIdIsNull() {

			addCriterion( "isp_id is null" );
			return this;
		}


		public Criteria andIspIdIsNotNull() {

			addCriterion( "isp_id is not null" );
			return this;
		}


		public Criteria andIspIdEqualTo( String value ) {

			addCriterion( "isp_id =", value, "ispId" );
			return this;
		}


		public Criteria andIspIdNotEqualTo( String value ) {

			addCriterion( "isp_id <>", value, "ispId" );
			return this;
		}


		public Criteria andIspIdGreaterThan( String value ) {

			addCriterion( "isp_id >", value, "ispId" );
			return this;
		}


		public Criteria andIspIdGreaterThanOrEqualTo( String value ) {

			addCriterion( "isp_id >=", value, "ispId" );
			return this;
		}


		public Criteria andIspIdLessThan( String value ) {

			addCriterion( "isp_id <", value, "ispId" );
			return this;
		}


		public Criteria andIspIdLessThanOrEqualTo( String value ) {

			addCriterion( "isp_id <=", value, "ispId" );
			return this;
		}


		public Criteria andIspIdLike( String value ) {

			addCriterion( "isp_id like", value, "ispId" );
			return this;
		}


		public Criteria andIspIdNotLike( String value ) {

			addCriterion( "isp_id not like", value, "ispId" );
			return this;
		}


		public Criteria andIspIdIn( List values ) {

			addCriterion( "isp_id in", values, "ispId" );
			return this;
		}


		public Criteria andIspIdNotIn( List values ) {

			addCriterion( "isp_id not in", values, "ispId" );
			return this;
		}


		public Criteria andIspIdBetween( String value1, String value2 ) {

			addCriterion( "isp_id between", value1, value2, "ispId" );
			return this;
		}


		public Criteria andIspIdNotBetween( String value1, String value2 ) {

			addCriterion( "isp_id not between", value1, value2, "ispId" );
			return this;
		}


		public Criteria andExplorerVersionIsNull() {

			addCriterion( "explorer_version is null" );
			return this;
		}


		public Criteria andExplorerVersionIsNotNull() {

			addCriterion( "explorer_version is not null" );
			return this;
		}


		public Criteria andExplorerVersionEqualTo( String value ) {

			addCriterion( "explorer_version =", value, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionNotEqualTo( String value ) {

			addCriterion( "explorer_version <>", value, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionGreaterThan( String value ) {

			addCriterion( "explorer_version >", value, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionGreaterThanOrEqualTo( String value ) {

			addCriterion( "explorer_version >=", value, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionLessThan( String value ) {

			addCriterion( "explorer_version <", value, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionLessThanOrEqualTo( String value ) {

			addCriterion( "explorer_version <=", value, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionLike( String value ) {

			addCriterion( "explorer_version like", value, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionNotLike( String value ) {

			addCriterion( "explorer_version not like", value, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionIn( List values ) {

			addCriterion( "explorer_version in", values, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionNotIn( List values ) {

			addCriterion( "explorer_version not in", values, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionBetween( String value1, String value2 ) {

			addCriterion( "explorer_version between", value1, value2, "explorerVersion" );
			return this;
		}


		public Criteria andExplorerVersionNotBetween( String value1, String value2 ) {

			addCriterion( "explorer_version not between", value1, value2, "explorerVersion" );
			return this;
		}
	}
}
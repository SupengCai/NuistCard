package com.linklife.domain.ibator;

import java.math.BigDecimal;

import com.linklife.domain.base.BaseDomain;
import com.linklife.repository.base.SqlMap;

import java.util.Date;

/**
 * <p>
 * NuistInfo.java
 * </p>
 * 
 * <pre>
 * 南信大一卡�?�用户信息表对应实体�??
 * </pre>
 * 
 * @author caisupeng
 */
@SqlMap( Name = "nuist_info", Class = NuistInfo.class )
public class NuistInfo<T, U> extends BaseDomain<T, U> {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.account_id
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private Integer accountId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.username
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private String username;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.usernumber
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private String usernumber;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.cardid
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private String cardid;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.balance
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private BigDecimal balance;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.bandcard
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private String bandcard;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.status
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private String status;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.education
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private String education;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.class_id
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private Integer classId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.period
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private String period;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.code
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private String code;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.add_location
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private String addLocation;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.add_time
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private Date addTime;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_info.add_city
	 * 
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	private String addCity;


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.account_id
	 * 
	 * @return the value of nuist_info.account_id
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public Integer getAccountId() {

		return accountId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.account_id
	 * 
	 * @param accountId
	 *            the value for nuist_info.account_id
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setAccountId( Integer accountId ) {

		this.accountId = accountId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.username
	 * 
	 * @return the value of nuist_info.username
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public String getUsername() {

		return username;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.username
	 * 
	 * @param username
	 *            the value for nuist_info.username
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setUsername( String username ) {

		this.username = username;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.usernumber
	 * 
	 * @return the value of nuist_info.usernumber
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public String getUsernumber() {

		return usernumber;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.usernumber
	 * 
	 * @param usernumber
	 *            the value for nuist_info.usernumber
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setUsernumber( String usernumber ) {

		this.usernumber = usernumber;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.cardid
	 * 
	 * @return the value of nuist_info.cardid
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public String getCardid() {

		return cardid;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.cardid
	 * 
	 * @param cardid
	 *            the value for nuist_info.cardid
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setCardid( String cardid ) {

		this.cardid = cardid;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.balance
	 * 
	 * @return the value of nuist_info.balance
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public BigDecimal getBalance() {

		return balance;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.balance
	 * 
	 * @param balance
	 *            the value for nuist_info.balance
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setBalance( BigDecimal balance ) {

		this.balance = balance;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.bandcard
	 * 
	 * @return the value of nuist_info.bandcard
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public String getBandcard() {

		return bandcard;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.bandcard
	 * 
	 * @param bandcard
	 *            the value for nuist_info.bandcard
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setBandcard( String bandcard ) {

		this.bandcard = bandcard;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.status
	 * 
	 * @return the value of nuist_info.status
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public String getStatus() {

		return status;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.status
	 * 
	 * @param status
	 *            the value for nuist_info.status
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setStatus( String status ) {

		this.status = status;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.education
	 * 
	 * @return the value of nuist_info.education
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public String getEducation() {

		return education;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.education
	 * 
	 * @param education
	 *            the value for nuist_info.education
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setEducation( String education ) {

		this.education = education;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.class_id
	 * 
	 * @return the value of nuist_info.class_id
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public Integer getClassId() {

		return classId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.class_id
	 * 
	 * @param classId
	 *            the value for nuist_info.class_id
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setClassId( Integer classId ) {

		this.classId = classId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.period
	 * 
	 * @return the value of nuist_info.period
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public String getPeriod() {

		return period;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.period
	 * 
	 * @param period
	 *            the value for nuist_info.period
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setPeriod( String period ) {

		this.period = period;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.code
	 * 
	 * @return the value of nuist_info.code
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public String getCode() {

		return code;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.code
	 * 
	 * @param code
	 *            the value for nuist_info.code
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setCode( String code ) {

		this.code = code;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.add_location
	 * 
	 * @return the value of nuist_info.add_location
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public String getAddLocation() {

		return addLocation;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.add_location
	 * 
	 * @param addLocation
	 *            the value for nuist_info.add_location
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setAddLocation( String addLocation ) {

		this.addLocation = addLocation;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.add_time
	 * 
	 * @return the value of nuist_info.add_time
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public Date getAddTime() {

		return addTime;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.add_time
	 * 
	 * @param addTime
	 *            the value for nuist_info.add_time
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setAddTime( Date addTime ) {

		this.addTime = addTime;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_info.add_city
	 * 
	 * @return the value of nuist_info.add_city
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public String getAddCity() {

		return addCity;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_info.add_city
	 * 
	 * @param addCity
	 *            the value for nuist_info.add_city
	 * @ibatorgenerated Tue Jul 14 21:09:31 CST 2015
	 */
	public void setAddCity( String addCity ) {

		this.addCity = addCity;
	}
}
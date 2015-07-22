package com.linklife.domain.ibator;

import com.linklife.domain.base.BaseDomain;
import com.linklife.repository.base.SqlMap;

/**
 * <p>
 * NuistLogin.java
 * </p>
 * 
 * <pre>
 * 南信大一卡�?�登录表对应实体�??
 * </pre>
 * 
 * @author caisupeng
 */
@SqlMap( Name = "nuist_login", Class = NuistLogin.class )
public class NuistLogin<T, U> extends BaseDomain<T, U> {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column nuist_login.usernumber
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	private String usernumber;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column nuist_login.account_id
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	private Integer accountId;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column nuist_login.password
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	private String password;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column nuist_login.statistics_type
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	private Integer statisticsType;
	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds to the database column nuist_login.score_type
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	private Integer scoreType;

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column nuist_login.usernumber
	 * @return  the value of nuist_login.usernumber
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	public String getUsernumber() {

		return usernumber;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column nuist_login.usernumber
	 * @param usernumber  the value for nuist_login.usernumber
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	public void setUsernumber( String usernumber ) {

		this.usernumber = usernumber;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column nuist_login.account_id
	 * @return  the value of nuist_login.account_id
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	public Integer getAccountId() {

		return accountId;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column nuist_login.account_id
	 * @param accountId  the value for nuist_login.account_id
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	public void setAccountId( Integer accountId ) {

		this.accountId = accountId;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column nuist_login.password
	 * @return  the value of nuist_login.password
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	public String getPassword() {

		return password;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column nuist_login.password
	 * @param password  the value for nuist_login.password
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	public void setPassword( String password ) {

		this.password = password;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column nuist_login.statistics_type
	 * @return  the value of nuist_login.statistics_type
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	public Integer getStatisticsType() {

		return statisticsType;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column nuist_login.statistics_type
	 * @param statisticsType  the value for nuist_login.statistics_type
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	public void setStatisticsType( Integer statisticsType ) {

		this.statisticsType = statisticsType;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method returns the value of the database column nuist_login.score_type
	 * @return  the value of nuist_login.score_type
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	public Integer getScoreType() {

		return scoreType;
	}

	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the value of the database column nuist_login.score_type
	 * @param scoreType  the value for nuist_login.score_type
	 * @ibatorgenerated  Sun Jul 12 10:23:21 CST 2015
	 */
	public void setScoreType( Integer scoreType ) {

		this.scoreType = scoreType;
	}
}
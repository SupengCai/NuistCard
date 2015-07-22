package com.linklife.domain;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.model.DailyCheckAccountModel;
import com.linklife.domain.model.DailyCheckIssuesModel;
import com.linklife.domain.model.DailyCheckUpdateModel;

/**
 * <p>
 * Nuist.java
 * </p>
 * 
 * <pre>
 * 南信大一卡通数据封装
 * </pre>
 * 
 * @author caisupeng
 */
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class DailyCheck {

	/** 用户登录相关业务类 */
	@Autowired
	private DailyCheckAccountModel dailyCheckAccountModel;

	/** 用户信息相关业务类 */
	@Autowired
	private DailyCheckIssuesModel dailyCheckIssuesModel;

	/** 用户登录记录相关业务类 */
	@Autowired
	private DailyCheckUpdateModel dailyCheckUpdateModel;


	public DailyCheckAccountModel getDailyCheckAccountModel() {

		return dailyCheckAccountModel;
	}


	public void setDailyCheckAccountModel( DailyCheckAccountModel dailyCheckAccountModel ) {

		this.dailyCheckAccountModel = dailyCheckAccountModel;
	}


	public DailyCheckIssuesModel getDailyCheckIssuesModel() {

		return dailyCheckIssuesModel;
	}


	public void setDailyCheckIssuesModel( DailyCheckIssuesModel dailyCheckIssuesModel ) {

		this.dailyCheckIssuesModel = dailyCheckIssuesModel;
	}


	public DailyCheckUpdateModel getDailyCheckUpdateModel() {

		return dailyCheckUpdateModel;
	}


	public void setDailyCheckUpdateModel( DailyCheckUpdateModel dailyCheckUpdateModel ) {

		this.dailyCheckUpdateModel = dailyCheckUpdateModel;
	}

}

package com.linklife.domain;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.model.AccountInfoModel;
import com.linklife.domain.model.AccountLoginModel;
import com.linklife.domain.model.AccountSuggestModel;
import com.linklife.domain.model.LifeTrackModel;
import com.linklife.domain.model.LoginHistoryModel;

/**
 * <p>
 * Account.java
 * </p>
 * 
 * <pre>
 * 用户相关数据封装
 * </pre>
 * 
 * @author caisupeng
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = false)
public class Account {

	 /** 用户登录相关业务类 */
	@Autowired
	private AccountLoginModel accountLoginModel;

	 /** 用户信息相关业务类 */
	@Autowired
	private AccountInfoModel accountInfoModel;
	
	/** 用户登录历史相关业务类 */
	@Autowired
	private LoginHistoryModel loginHistoryModel;
	
	 /** 用户生活轨迹相关业务类 */
	@Autowired
	private LifeTrackModel lifeTrackModel;
	
	 /** 用户建议相关业务类 */
	@Autowired
	private AccountSuggestModel accountSuggestModel;

	
	public AccountLoginModel getAccountLoginModel() {
		return accountLoginModel;
	}

	public void setAccountLoginModel(AccountLoginModel accountLoginModel) {
		this.accountLoginModel = accountLoginModel;
	}

	public AccountInfoModel getAccountInfoModel() {
		return accountInfoModel;
	}

	public void setAccountInfoModel(AccountInfoModel accountInfoModel) {
		this.accountInfoModel = accountInfoModel;
	}

	public LoginHistoryModel getLoginHistoryModel() {
		return loginHistoryModel;
	}

	public void setLoginHistoryModel(LoginHistoryModel loginHistoryModel) {
		this.loginHistoryModel = loginHistoryModel;
	}

	public AccountSuggestModel getAccountSuggestModel() {
		return accountSuggestModel;
	}

	public void setAccountSuggestModel(AccountSuggestModel accountSuggestModel) {
		this.accountSuggestModel = accountSuggestModel;
	}

	public LifeTrackModel getLifeTrackModel() {
		return lifeTrackModel;
	}

	public void setLifeTrackModel(LifeTrackModel lifeTrackModel) {
		this.lifeTrackModel = lifeTrackModel;
	}

}

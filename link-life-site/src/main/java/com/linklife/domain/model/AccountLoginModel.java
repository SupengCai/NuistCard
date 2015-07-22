package com.linklife.domain.model;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.AccountLogin;
import com.linklife.domain.ibator.AccountLoginExample;

/**
 * <p>
 * AccountLoginModel.java
 * </p>
 * 
 * <pre>
 * 用户登录表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings("rawtypes")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = false)
public class AccountLoginModel extends AccountLogin<AccountLogin, AccountLoginExample> {

	protected static Log log = LogFactory.getLog("AccountLoginModel");
	
	/** 用户姓名 */
	private String name;
	
	/** 用户密码*/
	private String accountPsw;

	/**
     * 根据Email搜索数据
     *
     * @return AccountLogin
     */
	public AccountLogin selectByEmail() {
		try {
			AccountLogin t = (AccountLogin) acquiredTargetRepository()
					.getSqlMapClient().queryForObject(
							"account_login.selectByEmail", this);
			if (t != null) {
				fillSelectResult(t);
				return this;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
	}

	/**
     * 填充用户登录信息 Id name 等
     *
     * @return
     */
	public void fillAccountLogin() {
		if (!StringUtils.isEmpty(this.accountPsw))
			this.setPassword(getAccountPsw());
		this.setStatus(Byte.parseByte("1"));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountPsw() {
		return accountPsw;
	}

	public void setAccountPsw(String accountPsw) {
		this.accountPsw = accountPsw;
	}
}

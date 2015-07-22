package com.linklife.domain.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.AccountSuggest;
import com.linklife.domain.ibator.AccountSuggestExample;

/**
 * <p>
 * AccountLoginModel.java
 * </p>
 * 
 * <pre>
 * 用户建议表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings( "rawtypes" )
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class AccountSuggestModel extends AccountSuggest<AccountSuggest, AccountSuggestExample> {

	/**
	 * 填充用户建议表相关信息
	 *
	 * @param AccountSuggestModel
	 * @return
	 */
	public void fillAccountSuggest( AccountSuggestModel accountSuggestModel, String remoteAddr, int accountId ) {

		setSuggest( accountSuggestModel.getSuggest() );
		setAddTime( new Date() );
		setAddIp( remoteAddr );
		setAcountId( accountId );
	}
}

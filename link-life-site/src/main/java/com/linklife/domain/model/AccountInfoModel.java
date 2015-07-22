package com.linklife.domain.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.AccountInfo;
import com.linklife.domain.ibator.AccountInfoExample;

/**
 * <p>
 * AccountInfoModel.java
 * </p>
 * 
 * <pre>
 * 用户信息表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings( "rawtypes" )
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class AccountInfoModel extends AccountInfo<AccountInfo, AccountInfoExample> {

	protected static Log log = LogFactory.getLog( "AccountInfoModel" );

	private String email;


	/**
	 * 搜索所有用户数据
	 *
	 * @return AccountLogin
	 */
	@SuppressWarnings( "unchecked" )
	public List<AccountInfoModel> selectAll() {

		List<AccountInfoModel> friendsList = new ArrayList<AccountInfoModel>();
		try {
			friendsList = acquiredTargetRepository().getSqlMapClient().queryForList( "account_info.selectAll" );// queryForObject("account_info.selectAll",
																												// this);
			if( friendsList.isEmpty() ) {
				return null;
			}
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
		return friendsList;
	}


	/**
	 * 根据Email搜索数据
	 *
	 * @return AccountInfo
	 */
	public AccountInfo selectByEmail( AccountLoginModel accountLoginModel ) {

		try {
			AccountInfo t = ( AccountInfo )acquiredTargetRepository().getSqlMapClient().queryForObject( "account_info.selectByEmail", accountLoginModel );
			if( t != null ) {
				return t;
			} else {
				return null;
			}
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	/**
	 * 填充用户信息 Id name 等
	 *
	 * @param AccountLoginModel
	 * @return
	 */
	public void fillAccountInfo( AccountLoginModel accountLoginModel, String remoteAddr ) {

		AccountLoginModel newAccount = ( AccountLoginModel )accountLoginModel.selectByEmail();
		this.setAccountId( newAccount.getAccountId() );
		if( StringUtils.isNotEmpty( accountLoginModel.getName() ) )
			this.setName( accountLoginModel.getName() );
		fillAddInfo( remoteAddr );
		fillEditInfo( remoteAddr );
		fillAuthority();
	}


	/**
	 * 填充添加用户相关信息
	 *
	 * @return
	 */
	public void fillAddInfo( String remoteAddr ) {

		this.setAddTime( new Date() );
		this.setAddLocation( remoteAddr );
	}


	/**
	 * 填充修改用户相关信息
	 *
	 * @return
	 */
	public void fillEditInfo( String remoteAddr ) {

		this.setEditTime( new Date() );
		this.setEditLocation( remoteAddr );
	}


	/**
	 * 填充用户权限相关信息
	 *
	 * @param account
	 * @param model
	 * @param alertModel
	 *            返回通知模板页的信息填充
	 * @return
	 */
	public void fillAuthority() {

		this.setAuthority( "PRIV_COMMON" );
	}


	public String getEmail() {

		return email;
	}


	public void setEmail( String email ) {

		this.email = email;
	}

}

package com.linklife.domain.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.NuistLogin;
import com.linklife.domain.ibator.NuistLoginExample;

/**
 * <p>
 * NuistLoginModel.java
 * </p>
 * 
 * <pre>
 * 南信一卡通登陆表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings( "rawtypes" )
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class NuistLoginModel extends NuistLogin<NuistLogin, NuistLoginExample> {

	protected static Log log = LogFactory.getLog( "NuistLoginModel" );

	// 认证码
	private String obj;

	private boolean rememberme;


	public String getObj() {

		return obj;
	}


	public void setObj( String obj ) {

		this.obj = obj;
	}


	public boolean getRememberme() {

		return rememberme;
	}


	public void setRememberme( boolean rememberme ) {

		this.rememberme = rememberme;
	}


	/**
	 * 根据学号搜索用户信息
	 * 
	 * @return NuistInfo
	 */
	public NuistLoginModel selectByUserNumber() {

		try {
			NuistLogin t = ( NuistLogin )acquiredTargetRepository().getSqlMapClient().queryForObject( "nuist_login.selectByUserNumber", this );
			if( t != null ) {
				fillSelectResult( t );
				return this;
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
	 * 根据统计类型搜索用户
	 * 
	 * @return NuistInfo
	 */
	@SuppressWarnings( "unchecked" )
	public List<NuistLogin> selectByStatisticsType( int type ) {

		try {
			List<NuistLogin> nuistLoginList = ( List<NuistLogin> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_login.selectByStatisticsType", type );
			return nuistLoginList;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	/**
	 * 根据统计类型搜索用户
	 * 
	 * @return NuistInfo
	 */
	@SuppressWarnings( "unchecked" )
	public List<NuistLoginModel> selectByStatisticsAndEducationType( int type ) {

		try {
			List<NuistLoginModel> nuistLoginList = ( List<NuistLoginModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_login.selectByStatisticsAndEducationType", type );
			return nuistLoginList;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}
	
	/**
	 * 根据成绩统计类型搜索用户
	 * 
	 * @return NuistInfo
	 */
	@SuppressWarnings( "unchecked" )
	public List<NuistLoginModel> selectByScoreAndEducationType( int type ) {

		try {
			List<NuistLoginModel> nuistLoginList = ( List<NuistLoginModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_login.selectByScoreAndEducationType", type );
			return nuistLoginList;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}
}

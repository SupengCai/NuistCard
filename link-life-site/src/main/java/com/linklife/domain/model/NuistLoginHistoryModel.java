package com.linklife.domain.model;

import java.util.Date;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.NuistLoginHistory;
import com.linklife.domain.ibator.NuistLoginHistoryExample;
import com.linklife.web.httpapi.TaoBaoIPAPI;

/**
 * <p>
 * NuistLoginModel.java
 * </p>
 * 
 * <pre>
 * 南信一卡通登陆记录表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings( "rawtypes" )
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class NuistLoginHistoryModel extends NuistLoginHistory<NuistLoginHistory, NuistLoginHistoryExample> {

	public void insertLoginHistory( int accountId, String remoteAddr, String explorer ) {

		this.setAccountId( accountId );
		this.setTime( new Date() );
		this.setSigninIp( remoteAddr );
		this.setExplorerVersion( explorer );

		// 获取淘宝IP API的JSON数据
		JSONObject json = TaoBaoIPAPI.getIPInfoByIP( remoteAddr );
		this.fillLoginIPInfo( json );
		this.insert();
	}


	/**
	 * 填充淘宝IP API提供的用户登录信息
	 * 
	 * @param json
	 */
	private void fillLoginIPInfo( JSONObject json ) {

		if( json.getInt( "code" ) == 0 ) {
			JSONObject jsonData = json.getJSONObject( "data" );
			this.setCountry( jsonData.getString( "country" ) );
			this.setCountryId( jsonData.getString( "country_id" ) );
			this.setArea( jsonData.getString( "area" ) );
			this.setAreaId( jsonData.getString( "area_id" ) );
			this.setRegion( jsonData.getString( "region" ) );
			this.setRegionId( jsonData.getString( "region_id" ) );
			this.setCity( jsonData.getString( "city" ) );
			this.setCityId( jsonData.getString( "city_id" ) );
			this.setIsp( jsonData.getString( "isp" ) );
			this.setIspId( jsonData.getString( "isp_id" ) );
		}

	}
}
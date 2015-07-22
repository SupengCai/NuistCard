package com.linklife.domain.model;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.NuistShop;
import com.linklife.domain.ibator.NuistShopExample;

/**
 * <p>
 * NuistTradeModel.java
 * </p>
 * 
 * <pre>
 * 南信一卡通用户交易记录表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings( "rawtypes" )
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class NuistShopModel extends NuistShop<NuistShop, NuistShopExample> {

	/**
	 * 根据商铺名称搜索商铺信息
	 * 
	 * @return NuistInfo
	 */
	public NuistShopModel selectByShopName() {

		try {
			NuistShop t = ( NuistShop )acquiredTargetRepository().getSqlMapClient().queryForObject( "nuist_shop.selectByShopName", this );
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
	 * 根据商铺名称填充商铺区域
	 * 
	 * @return NuistInfo
	 */
	public void setShopAreaByName( String shopName ) {

		if( shopName.contains( "东" ) )
			setShopArea( 1 );
		else if( shopName.contains( "中" ) )
			setShopArea( 2 );
		else if( shopName.contains( "西" ) )
			setShopArea( 3 );
		else
			setShopArea( 0 );

	}


	/**
	 * 根据商铺名称填充商铺区域
	 * 
	 * @return NuistInfo
	 */
	public void setShopAreaByName() {

		String shopName = getShopName();
		if( shopName.contains( "东" ) )
			setShopArea( 1 );
		else if( shopName.contains( "中" ) )
			setShopArea( 2 );
		else if( shopName.contains( "西" ) )
			setShopArea( 3 );
		else
			setShopArea( 0 );

	}
}

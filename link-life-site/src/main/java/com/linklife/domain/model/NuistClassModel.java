package com.linklife.domain.model;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.NuistClass;
import com.linklife.domain.ibator.NuistClassExample;

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
public class NuistClassModel extends NuistClass<NuistClass, NuistClassExample> {

	/**
	 * 根据课程名称搜索课程信息
	 * 
	 * @return NuistInfo
	 */
	public NuistClassModel selectByShopName() {

		try {
			NuistClass t = ( NuistClass )acquiredTargetRepository().getSqlMapClient().queryForObject( "nuist_class.selectByClassName", this );
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

}

package com.linklife.domain.model;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.DailyCheckAccount;
import com.linklife.domain.ibator.DailyCheckAccountExample;
import com.linklife.domain.ibator.NuistInfo;

/**
 * <p>
 * NuistSuggestModel.java
 * </p>
 * 
 * <pre>
 * 南信一卡通用户反馈表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings( "rawtypes" )
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class DailyCheckAccountModel extends DailyCheckAccount<DailyCheckAccount, DailyCheckAccountExample> {

	public DailyCheckAccount selectByAccount(){
		try {
			DailyCheckAccount t = ( DailyCheckAccount )acquiredTargetRepository().getSqlMapClient().queryForObject( "daily_check_account.selectByAccount", this );
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

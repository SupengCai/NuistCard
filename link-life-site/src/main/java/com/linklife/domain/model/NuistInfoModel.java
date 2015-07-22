package com.linklife.domain.model;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.NuistInfo;
import com.linklife.domain.ibator.NuistInfoExample;
import com.linklife.web.httpapi.NuistCardAPI;
import com.linklife.web.httpapi.TaoBaoIPAPI;

/**
 * <p>
 * NuistInfoModel.java
 * </p>
 * 
 * <pre>
 * 南信一卡通用户信息表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings( "rawtypes" )
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class NuistInfoModel extends NuistInfo<NuistInfo, NuistInfoExample> {

	protected static Log log = LogFactory.getLog( "NuistInfoModel" );


	/**
	 * 根据学号搜索用户信息
	 * 
	 * @return NuistInfo
	 */
	public NuistInfo selectByUserNumber() {

		try {
			NuistInfo t = ( NuistInfo )acquiredTargetRepository().getSqlMapClient().queryForObject( "nuist_info.selectByUserNumber", this );
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
	 * 填充南信大一卡通用户信息
	 *
	 * @param trs
	 * @param remoteAddr
	 */
	public void fillNuistInfo( Elements trs, String remoteAddr ) {

		Elements tds_username = trs.get( 0 ).select( "td" );// 姓名
		Elements tds_usernumber = trs.get( 1 ).select( "td" );// 学号
		Elements tds_cardid = trs.get( 2 ).select( "td" );// 学号
		Elements tds_balance = trs.get( 3 ).select( "td" );// 余额
		Elements tds_balance1 = trs.get( 5 ).select( "td" );// 过度余额1
		Elements tds_balance2 = trs.get( 6 ).select( "td" );// 过度余额2
		Elements tds_bankcard = trs.get( 4 ).select( "td" );// 银行卡
		Elements tds_statusloss = trs.get( 7 ).select( "td" );// 挂失
		Elements tds_statusfrozen = trs.get( 8 ).select( "td" );// 冻结
		Elements tds_education = trs.get( 9 ).select( "td" );// 学历
		Elements tds_period = trs.get( 10 ).select( "td" );// 年级
		String balance = String.format(
				"%.2f",
				Double.parseDouble( tds_balance.get( 1 ).text() ) + Double.parseDouble( tds_balance1.get( 1 ).text() )
						+ Double.parseDouble( tds_balance2.get( 1 ).text() ) );
		this.setUsername( tds_username.get( 1 ).text() );
		this.setUsernumber( tds_usernumber.get( 1 ).text() );
		this.setCardid( tds_cardid.get( 1 ).text() );
		this.setBalance( new BigDecimal( balance ) );
		this.setBandcard( tds_bankcard.get( 1 ).text() );
		this.setStatus( tds_statusloss.get( 1 ).text() );
		if( NuistCardAPI.NUIST_CARD_STATUS_NORMAL.equals( this.getStatus() ) )
			this.setStatus( tds_statusfrozen.get( 1 ).text() );
		this.setEducation( tds_education.get( 1 ).text() );
		this.setPeriod( tds_period.get( 1 ).text() );
		this.setAddLocation( remoteAddr );
		this.setAddCity( TaoBaoIPAPI.getCityByIP( remoteAddr ) );
		this.setAddTime( new Date() );
	}


	/**
	 * 更新南信大一卡通用户信息
	 *
	 * @param irepository
	 * @param trs
	 * @return
	 */
	public boolean updateNuistInfo( Elements trs ) {

		boolean changed = false;
		if(trs.isEmpty())
			return changed;
		Elements tds_balance = trs.get( 3 ).select( "td" );// 余额
		Elements tds_balance1 = trs.get( 5 ).select( "td" );// 过度余额1
		Elements tds_balance2 = trs.get( 6 ).select( "td" );// 过度余额2
		Elements tds_bankcard = trs.get( 4 ).select( "td" );// 银行卡
		Elements tds_statusloss = trs.get( 7 ).select( "td" );// 挂失
		Elements tds_statusfrozen = trs.get( 8 ).select( "td" );// 冻结

		String balance = String.format(
				"%.2f",
				Double.parseDouble( tds_balance.get( 1 ).text() ) + Double.parseDouble( tds_balance1.get( 1 ).text() )
						+ Double.parseDouble( tds_balance2.get( 1 ).text() ) );
		if( !balance.equals( this.getBalance().toString() ) ) {
			this.setBalance( new BigDecimal( balance ) );
			changed = true;
		}
		if( !tds_bankcard.get( 1 ).text().equals( this.getBandcard() ) ) {
			this.setBandcard( tds_bankcard.get( 1 ).text() );
			changed = true;
		}
		if( !tds_statusloss.get( 1 ).text().equals( NuistCardAPI.NUIST_CARD_STATUS_NORMAL ) ) {
			this.setStatus( tds_statusloss.get( 1 ).text() );
			changed = true;
		}
		if( !tds_statusfrozen.get( 1 ).text().equals( this.getStatus() ) ) {
			this.setStatus( tds_statusfrozen.get( 1 ).text() );
			changed = true;
		}
		return changed;
	}
}

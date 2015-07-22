package com.linklife.domain.model;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.NuistShop;
import com.linklife.domain.ibator.NuistShopExample;
import com.linklife.domain.ibator.NuistTradeHistory;
import com.linklife.domain.ibator.NuistTradeHistoryExample;
import com.linklife.repository.base.IRepository;
import com.linklife.web.httpapi.NuistCardAPI;

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
public class NuistTradeModel extends NuistTradeHistory<NuistTradeHistory, NuistTradeHistoryExample> {

	/**
	 * 填充南信大一卡通用户历史账单
	 *
	 * @param tbs
	 */
	public static void insertNuistTradeByTables( Elements tbs, String userNumber, int accountId,
			IRepository<NuistTradeHistory, NuistTradeHistoryExample> iRepository,
			IRepository<NuistShop, NuistShopExample> iRepositoryShop ) {

		SimpleDateFormat sdf = new SimpleDateFormat( "yyyy/MM/dd HH:mm:ss" );
		NuistTradeModel nuistTradeModel = new NuistTradeModel();
		nuistTradeModel.setAccountId( accountId );
		nuistTradeModel.setUserNumber( userNumber );
		nuistTradeModel.mockRepository( iRepository );

		for( int i = 0; i < tbs.size(); i++ ) {
			Elements trs = tbs.get( i ).select( "tr" );
			Elements tdsTime = trs.get( 0 ).select( "td" );// 交易时间
			Elements tdsShop = trs.get( 1 ).select( "td" );// 商户名称
			Elements tdsType = trs.get( 2 ).select( "td" );// 交易名称
			Elements tdsAmount = trs.get( 3 ).select( "td" );// 交易金额

			nuistTradeModel.setShopName( tdsShop.get( 1 ).text() );// 商铺名称
			nuistTradeModel.setShopArea( null);
			nuistTradeModel.setShopId( null );
			
			String tradeType = tdsType.get( 1 ).text().trim(); // 交易类型
			switch( tradeType ) {
			case NuistCardAPI.NUIST_CARD_TRADE_TYPE_CONSUME:
				NuistShopModel nuistShopModel = new NuistShopModel();
				nuistShopModel.mockRepository( iRepositoryShop );
				nuistShopModel.setShopName( nuistTradeModel.getShopName() );
				nuistShopModel.selectByShopName();
				if( null == nuistShopModel.getShopId() ) {
					//插入新商铺
					nuistShopModel.setShopAreaByName();
					nuistShopModel.insert();
					//重新检索并填充数据
					nuistShopModel.selectByShopName();
					nuistTradeModel.setShopId( nuistShopModel.getShopId() );
					nuistTradeModel.setShopArea( nuistShopModel.getShopArea() );
				} else {
					nuistTradeModel.setShopId( nuistShopModel.getShopId() );
					nuistTradeModel.setShopArea( nuistShopModel.getShopArea() );
				}
				nuistTradeModel.setTradeType( 1 );
				break;
			case NuistCardAPI.NUIST_CARD_TRADE_TYPE_BANK:
				nuistTradeModel.setTradeType( 0 );
				break;
			// case NuistCardAPI.NUIST_CARD_TRADE_TYPE_STREAM:
			// nuistTradeModel.setTradeType( 0 );
			// break;
			default:
				continue;
			}
			// if( NuistCardAPI.NUIST_CARD_TRADE_TYPE_CONSUME.equals( tradeType
			// ) ) {
			// nuistTradeModel.setTradeType( 1 );
			// } else if( NuistCardAPI.NUIST_CARD_TRADE_TYPE_BANK.equals(
			// tradeType ) || NuistCardAPI.NUIST_CARD_TRADE_TYPE_STREAM.equals(
			// tradeType ) ) {
			// nuistTradeModel.setTradeType( 0 );
			// }

			nuistTradeModel.setTradeAmount( Math.abs( Double.parseDouble( tdsAmount.get( 1 ).text() ) ) );// 交易金额

			try {
				String time = tdsTime.get( 1 ).text();// 交易时间
				Date date;
				date = sdf.parse( time );
				nuistTradeModel.setTradeTime( date );
			} catch( ParseException e1 ) {
				e1.printStackTrace();
			}
			// 插入数据
			nuistTradeModel.insert();
		}
	}


	/**
	 * 根据学号和交易时间搜索用户交易记录
	 * 
	 * @return NuistInfo
	 */
	public NuistTradeModel selectByUserNumberAndTradeTime() {

		try {
			NuistTradeModel t = ( NuistTradeModel )acquiredTargetRepository().getSqlMapClient().queryForObject(
					"nuist_trade_history.selectByUserNumberAndTradeTime", this );
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


	@SuppressWarnings( "unchecked" )
	public List<HashMap<String, Double>> query4AmountRank() {

		try {
			List<HashMap<String, Double>> list = ( List<HashMap<String, Double>> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4AmountRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<HashMap<String, Integer>> query4TimeRank() {

		try {
			List<HashMap<String, Integer>> list = ( List<HashMap<String, Integer>> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4TimeRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<HashMap<String, Double>> query4MyAmountRank( String userNumber ) {

		try {
			List<HashMap<String, Double>> list = ( List<HashMap<String, Double>> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4MyAmountRank", userNumber );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<HashMap<String, Integer>> query4MyTimeRank( String userNumber ) {

		try {
			List<HashMap<String, Integer>> list = ( List<HashMap<String, Integer>> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4MyTimeRank", userNumber );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4EastRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList( "nuist_trade_history.query4EastRank",
					this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4CenterRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4CenterRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4WestRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList( "nuist_trade_history.query4WestRank",
					this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4MyEastRank( String userNumber ) {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4MyEastRank", userNumber );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4MyCenterRank( String userNumber ) {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4MyCenterRank", userNumber );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4MyWestRank( String userNumber ) {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4MyWestRank", userNumber );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	public int query4People() {

		try {
			int data = ( int )acquiredTargetRepository().getSqlMapClient().queryForObject( "nuist_trade_history.query4People" );
			return data;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	public int query4DataSize() {

		try {
			int data = ( int )acquiredTargetRepository().getSqlMapClient().queryForObject( "nuist_trade_history.query4DataSize" );
			return data;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4EastAmountRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4EastAmountRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4CenterAmountRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4CenterAmountRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4WestAmountRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4WestAmountRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4EastPopularityRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4EastPopularityRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4CenterPopularityRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4CenterPopularityRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4WestPopularityRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4WestPopularityRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4EastPerConsumeRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4EastPerConsumeRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4CenterPerConsumeRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4CenterPerConsumeRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4WestPerConsumeRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4WestPerConsumeRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4EastCostMostRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4EastCostMostRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4CenterCostMostRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4CenterCostMostRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}


	@SuppressWarnings( "unchecked" )
	public List<AreaRankModel> query4WestCostMostRank() {

		try {
			List<AreaRankModel> list = ( List<AreaRankModel> )acquiredTargetRepository().getSqlMapClient().queryForList(
					"nuist_trade_history.query4WestCostMostRank", this );
			return list;
		} catch( SQLException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
	}
}

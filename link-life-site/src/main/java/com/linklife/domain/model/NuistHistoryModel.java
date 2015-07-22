package com.linklife.domain.model;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang.StringUtils;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSON;
import com.linklife.web.httpapi.HttpUtils;
import com.linklife.web.httpapi.NuistCardAPI;

/**
 * <p>
 * NuistHistoryModel.java
 * </p>
 * 
 * <pre>
 * 接收用户消费账单Table项
 * </pre>
 * 
 * @author caisupeng
 */
public class NuistHistoryModel {

	/** 当前时间的历史数据查询url参数 */
	public static String URL_PARAMS_WEEK;
	public static String URL_PARAMS_TERM;
	public static String URL_PARAMS_ALL = "?beginTime=2008/01/01&endTime=2020/12/30";
	public static String URL_PARAMS_TODAY;
	public static String URL_PARAMS_MONTH;
	public static int daysOfWeek;
	public static int daysOfTerm;
	public static Date monday;
	public static int dayOfWeek[];

	/** 用户历史数据容器 */
	public static ConcurrentHashMap<String, HistoryDataRecorder> historyDataMap = new ConcurrentHashMap<String, HistoryDataRecorder>();

	static {
		URL_PARAMS_WEEK = getWeekUrlParams();
		URL_PARAMS_TERM = getTermUrlParams();
		URL_PARAMS_TODAY = getTodayUrlParams();
		URL_PARAMS_MONTH = getMonthUrlParams();
		defaultDays();
		createDayOfWeek();
	}


	/**
	 * 历史账单类型枚举
	 * 
	 * @author caisupeng
	 *
	 */
	public static enum HistoryType {
		WEEK, TERM, ALL;
	}


	/**
	 * 根据historyType类型，获取JSON历史数据
	 *
	 * @param userNumber
	 * @param historyType
	 * @return
	 */
	public static String getHistoryJson( String userNumber, int historyType ) {

		String result = "";
		if( historyDataMap.containsKey( userNumber ) && historyDataMap.get( userNumber ).amountHistorys[ historyType ].consumeAmount > 0 )// 存在缓存

			result = JSON.toJSONString( historyDataMap.get( userNumber ).amountHistorys[ historyType ] );
		else {// 根据历史数据类型创建缓存
			switch( historyType ) {
			case 0:// Week
				result = getWeekJson( userNumber );
				break;
			case 1:// term
				result = getTermJson( userNumber );
				break;
			case 2:// all
				result = getAllJson( userNumber );
				break;
			default:
				break;
			}
		}
		return result;
	}


	/**
	 * 获取本周JSON历史数据
	 *
	 * @param userNumber
	 * @return
	 */
	private static String getWeekJson( String userNumber ) {

		HistoryDataRecorder historyDataRecorder = getHistoryDataRecorder( userNumber, HistoryType.WEEK );
		Elements tbsWeek = NuistCardAPI.getNuistTable( userNumber, NuistHistoryModel.URL_PARAMS_WEEK );// 获取用户历史数据tables
		Elements tbsToday = NuistCardAPI.getNuistTable( userNumber, null );// 获取用户当天历史数据tables
		AmountHistory amountHistory = historyDataRecorder.amountHistorys[ 0 ];// 全部数据
		tablesHandle( tbsWeek, amountHistory, HistoryType.WEEK );
		tablesHandle( tbsToday, amountHistory, HistoryType.WEEK );

		// 计算平均数
		dataAverage( amountHistory, daysOfWeek );
		// 保留两位小数，取绝对值
		dataParse( amountHistory );
		historyDataMap.put( userNumber, historyDataRecorder );
		return JSON.toJSONString( amountHistory );
	}


	/**
	 * 本学期获取JSON历史数据
	 *
	 * @param userNumber
	 * @return
	 */
	private static String getTermJson( String userNumber ) {

		HistoryDataRecorder historyDataRecorder = getHistoryDataRecorder( userNumber, HistoryType.TERM );
		Elements tbsTerm = NuistCardAPI.getNuistTable( userNumber, NuistHistoryModel.URL_PARAMS_TERM );// 获取用户历史数据tables
		Elements tbsToday = NuistCardAPI.getNuistTable( userNumber, null );// 获取用户当天历史数据tables
		AmountHistory amountHistory = historyDataRecorder.amountHistorys[ 1 ];// 全部数据
		tablesHandle( tbsTerm, amountHistory, HistoryType.TERM );
		tablesHandle( tbsToday, amountHistory, HistoryType.TERM );

		// 计算平均数
		dataAverage( amountHistory, daysOfTerm );
		// 保留两位小数，取绝对值
		dataParse( amountHistory );
		historyDataMap.put( userNumber, historyDataRecorder );
		return JSON.toJSONString( amountHistory );
	}


	/**
	 * 获取全部JSON历史数据
	 *
	 * @param userNumber
	 * @return
	 */
	private static String getAllJson( String userNumber ) {

		HistoryDataRecorder historyDataRecorder = getHistoryDataRecorder( userNumber, HistoryType.ALL );
		int startYear = HttpUtils.getStartYearFromMap( userNumber );// 获取用户年级
		AmountHistory amountHistory = historyDataRecorder.amountHistorys[ 2 ];// 全部数据
		int index = 0;
		do {
			try {
				addAllJsonByStartYear( amountHistory, userNumber, startYear, startYear + index );
			} catch( Exception e ) {
				continue;
			}
			index++;
		} while( index < 5 );

		// 计算平均数
		try {
			long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
			DateFormat dftime = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
			Date start = dftime.parse( startYear + "-09-01 00:00:00" );
			int daysOfAll = ( int )( ( new Date().getTime() - start.getTime() ) / nd ) + 1;
			dataAverage( amountHistory, daysOfAll );
		} catch( ParseException e ) {
			e.printStackTrace();
		}
		for( int i = 0; i < 5; i++ )
			amountHistory.categories[ i ] = ( startYear + i ) + "年";
		// 保留两位小数，取绝对值
		dataParse( amountHistory );
		historyDataMap.put( userNumber, historyDataRecorder );
		return JSON.toJSONString( amountHistory );
	}


	/**
	 * 获取全部JSON历史数据
	 *
	 * @param amountHistory
	 * @param userNumber
	 * @param startYear
	 * @param year
	 * @return
	 */
	private static void addAllJsonByStartYear( AmountHistory amountHistory, String userNumber, int startYear, int year ) {

		try {
			Elements tbs = NuistCardAPI.getNuistTable( userNumber, "?beginTime=" + year + "/01/01&endTime=" + year + "/12/31" );// 获取用户历史数据tables
			for( int i = 0; i < tbs.size(); i++ ) {
				Elements trs = tbs.get( i ).select( "tr" );
				Elements tdsTime = trs.get( 0 ).select( "td" );// 交易时间
				Elements tdsShop = trs.get( 1 ).select( "td" );// 商户名称
				Elements tdsType = trs.get( 2 ).select( "td" );// 交易名称
				Elements tdsAmount = trs.get( 3 ).select( "td" );// 交易名称

				int index = getIndexByYear( startYear, Integer.parseInt( tdsTime.get( 1 ).text().substring( 0, 4 ) ) );// 获取本table中参数索引号

				dataStatistic( tdsType.get( 1 ).text(), tdsShop.get( 1 ).text(), amountHistory, index, Double.parseDouble( tdsAmount.get( 1 ).text() ),
						HistoryType.ALL );
			}
		} catch( Exception e ) {
			throw new RuntimeException( e.getMessage() );
		}
	}


	private static void tablesHandle( Elements tbs, AmountHistory amountHistory, HistoryType historyType ) {

		for( int i = 0; i < tbs.size(); i++ ) {
			Elements trs = tbs.get( i ).select( "tr" );
			Elements tdsTime = trs.get( 0 ).select( "td" );// 交易时间
			Elements tdsShop = trs.get( 1 ).select( "td" );// 商户名称
			Elements tdsType = trs.get( 2 ).select( "td" );// 交易名称
			Elements tdsAmount = trs.get( 3 ).select( "td" );// 交易名称
			int index = 0;
			switch( historyType ) {
			case WEEK:
				index = getIndexByDay( Integer.parseInt( tdsTime.get( 1 ).text().split( "/" )[ 2 ].split( " " )[ 0 ] ) );// 获取本table中参数索引号
				break;
			case TERM:
				index = getIndexByMonth( Integer.parseInt( tdsTime.get( 1 ).text().split( "/" )[ 1 ] ) );// 获取本table中参数索引号
				break;
			case ALL:
				break;

			}

			dataStatistic( tdsType.get( 1 ).text(), tdsShop.get( 1 ).text(), amountHistory, index, Double.parseDouble( tdsAmount.get( 1 ).text() ), historyType );
		}
	}


	/**
	 * 向对应容器填充历史数据 tradeType:交易类型 area:交易区域 amountHistory:数据容器 index:数据所在容器的索引
	 * amount:交易额度
	 *
	 * @param tradeType
	 * @param area
	 * @param amountHistory
	 * @param amount
	 * @return
	 */
	private static void dataStatistic( String tradeType, String shopName, AmountHistory amountHistory, int index, double amount, HistoryType historyType ) {

		switch( tradeType ) {// 交易类型分类
		case NuistCardAPI.NUIST_CARD_TRADE_TYPE_BANK:
			amountHistory.rechargeTime++;
			amountHistory.rechargeAmount += amount;
			amountHistory.rechargeTimes[ index ]++;
			amountHistory.rechargeAmounts[ index ] += amount;
			break;
		// case NuistCardAPI.NUIST_CARD_TRADE_TYPE_STREAM:
		// amountHistory.rechargeTime++;
		// amountHistory.rechargeAmount += amount;
		// amountHistory.rechargeTimes[ index ]++;
		// amountHistory.rechargeAmounts[ index ] += amount;
		// break;
		case NuistCardAPI.NUIST_CARD_TRADE_TYPE_CONSUME:
			amountHistory.consumeTime++;
			amountHistory.consumeAmount += amount;
			amountHistory.consumeTimes[ index ]++;
			amountHistory.consumeAmounts[ index ] += amount;

			switch( shopName.substring( 0, 1 ) ) {// 账单区域统计
			case NuistCardAPI.NUIST_CARD_TRADE_AREA_EAST:
				amountHistory.areaConsumeAmounts[ 0 ] += amount;
				amountHistory.areaConsumeTimes[ 0 ]++;
				amountHistory.updateShopMap( NuistCardAPI.NUIST_CARD_TRADE_AREA_EAST, shopName, amount );
				break;
			case NuistCardAPI.NUIST_CARD_TRADE_AREA_CENTRAL:
				amountHistory.areaConsumeAmounts[ 1 ] += amount;
				amountHistory.areaConsumeTimes[ 1 ]++;
				amountHistory.updateShopMap( NuistCardAPI.NUIST_CARD_TRADE_AREA_CENTRAL, shopName, amount );
				break;
			case NuistCardAPI.NUIST_CARD_TRADE_AREA_WEST:
				amountHistory.areaConsumeAmounts[ 2 ] += amount;
				amountHistory.areaConsumeTimes[ 2 ]++;
				amountHistory.updateShopMap( NuistCardAPI.NUIST_CARD_TRADE_AREA_WEST, shopName, amount );
				break;
			default:
				if( shopName.equals( "京客茶饮" ) || shopName.equals( "缤纷鲜果" ) || shopName.equals( "美美花艺果品店" ) ) {// 东院
					amountHistory.areaConsumeAmounts[ 0 ] += amount;
					amountHistory.areaConsumeTimes[ 0 ]++;
					amountHistory.updateShopMap( NuistCardAPI.NUIST_CARD_TRADE_AREA_EAST, shopName, amount );
					break;
				} else if( shopName.equals( "海林水果" ) || shopName.equals( "梦梦蛋糕房（中苑南门商铺）" ) ) {// 中院
					amountHistory.areaConsumeAmounts[ 1 ] += amount;
					amountHistory.areaConsumeTimes[ 1 ]++;
					amountHistory.updateShopMap( NuistCardAPI.NUIST_CARD_TRADE_AREA_CENTRAL, shopName, amount );
					break;
				} else if( shopName.equals( "公主馋港式甜品小站" ) ) {// 西院
					amountHistory.areaConsumeAmounts[ 2 ] += amount;
					amountHistory.areaConsumeTimes[ 2 ]++;
					amountHistory.updateShopMap( NuistCardAPI.NUIST_CARD_TRADE_AREA_WEST, shopName, amount );
					break;
				}
				break;
			}
			break;
		default:
			break;
		}
	}


	/**
	 * 对容器中的数据进行处理，保留两位小数，取绝对值
	 *
	 * @param amountHistory
	 */
	private static void dataParse( AmountHistory amountHistory ) {

		DecimalFormat df = new DecimalFormat( "#.00" );
		amountHistory.consumeAmount = Math.abs( Double.parseDouble( df.format( amountHistory.consumeAmount ) ) );
		amountHistory.averageConsume = Math.abs( Double.parseDouble( df.format( amountHistory.averageConsume ) ) );
		amountHistory.rechargeAmount = Double.parseDouble( df.format( amountHistory.rechargeAmount ) );
		for( int i = 0; i < amountHistory.consumeAmounts.length; i++ ) {
			amountHistory.consumeAmounts[ i ] = Math.abs( Double.parseDouble( df.format( amountHistory.consumeAmounts[ i ] ) ) );
			amountHistory.rechargeAmounts[ i ] = Math.abs( Double.parseDouble( df.format( amountHistory.rechargeAmounts[ i ] ) ) );
		}

		for( int i = 0; i < 3; i++ ) {
			amountHistory.areaConsumeAmounts[ i ] = Math.abs( Double.parseDouble( df.format( amountHistory.areaConsumeAmounts[ i ] ) ) );
			amountHistory.averageAreaConsume[ i ] = Math.abs( Double.parseDouble( df.format( amountHistory.averageAreaConsume[ i ] ) ) );
			amountHistory.averageAreaConsumeTimes[ i ] = Math.abs( Double.parseDouble( df.format( amountHistory.averageAreaConsumeTimes[ i ] ) ) );
		}
	}


	/**
	 * 计算容器中数据的平均值
	 *
	 * @param amountHistory
	 * @param days
	 */
	private static void dataAverage( AmountHistory amountHistory, int days ) {

		amountHistory.averageConsume = amountHistory.consumeAmount / days;
		for( int i = 0; i < 3; i++ ) {
			amountHistory.averageAreaConsume[ i ] = amountHistory.areaConsumeAmounts[ i ] / days;
			amountHistory.averageAreaConsumeTimes[ i ] = ( double )amountHistory.areaConsumeTimes[ i ] / days;
		}
	}


	/**
	 * 想Map获取容器对象
	 *
	 * @param userNumber
	 * @param historyType
	 */
	public static HistoryDataRecorder getHistoryDataRecorder( String userNumber, HistoryType historyType ) {

		if( historyDataMap.containsKey( userNumber ) )
			return historyDataMap.get( userNumber );
		else {
			HistoryDataRecorder historyDataRecorder = new HistoryDataRecorder();
			NuistHistoryModel.historyDataMap.put( userNumber, historyDataRecorder );
			return historyDataRecorder;
		}

	}


	/**
	 * 根据年级和数据年份计算索引数
	 *
	 * @param startYear
	 * @param year
	 * @return
	 */
	private static int getIndexByYear( int startYear, int year ) {

		int index = 0;
		if( startYear + 1 == year )
			index = 1;
		else if( startYear + 2 == year )
			index = 2;
		else if( startYear + 3 == year )
			index = 3;
		else if( startYear + 4 == year )
			index = 4;
		return index;
	}


	/**
	 * 根据数据日期计算索引
	 *
	 * @param day
	 * @return
	 */
	public static int getIndexByDay( int day ) {

		int index = -1;
		if( day == dayOfWeek[ 0 ] )
			index = 0;
		else if( day == dayOfWeek[ 1 ] )
			index = 1;
		else if( day == dayOfWeek[ 2 ] )
			index = 2;
		else if( day == dayOfWeek[ 3 ] )
			index = 3;
		else if( day == dayOfWeek[ 4 ] )
			index = 4;
		else if( day == dayOfWeek[ 5 ] )
			index = 5;
		else if( day == dayOfWeek[ 6 ] )
			index = 6;

		return index;
	}


	/**
	 * 根据数据月份计算索引
	 *
	 * @param month
	 * @return
	 */
	private static int getIndexByMonth( int month ) {

		int index = 0;
		switch( month ) {
		case 1:
			index = 4;
			break;
		case 2:
			index = 5;
			break;
		case 3:
			index = 0;
			break;
		case 4:
			index = 1;
			break;
		case 5:
			index = 2;
			break;
		case 6:
			index = 3;
			break;
		case 7:
			index = 4;
			break;
		case 8:
			index = 5;
			break;
		case 9:
			index = 0;
			break;
		case 10:
			index = 1;
			break;
		case 11:
			index = 2;
			break;
		case 12:
			index = 3;
			break;

		default:
			index = -1;
			break;
		}

		return index;
	}


	/**
	 * 生成获取当天对应的获取本周数据的URL参数链接
	 *
	 */
	private static String getWeekUrlParams() {

		Calendar calendar = Calendar.getInstance();
		// 当前日期
		int year = calendar.get( Calendar.YEAR );
		int month = calendar.get( Calendar.MONTH ) + 1;
		int day = calendar.get( Calendar.DAY_OF_MONTH );

		// 周一日期
		int daysBefore = calendar.get( Calendar.DAY_OF_WEEK ) - 2;
		if( daysBefore == -1 )
			daysBefore = 6;
		calendar.add( Calendar.DAY_OF_MONTH, -daysBefore );
		monday = calendar.getTime();
		return "?beginTime=" + calendar.get( Calendar.YEAR ) + '/' + ( calendar.get( Calendar.MONTH ) + 1 ) + '/' + calendar.get( Calendar.DAY_OF_MONTH )
				+ "&endTime=" + year + '/' + month + '/' + day;
	}


	/**
	 * 生成获取当天对应的学期数据的URL参数链接
	 *
	 */
	private static String getTermUrlParams() {

		String urlParams = "";
		Calendar calendar = Calendar.getInstance();
		// 当前日期
		int year = calendar.get( Calendar.YEAR );
		int month = calendar.get( Calendar.MONTH ) + 1;

		if( month > 2 && month < 9 )
			urlParams = "?beginTime=" + year + "/3/1" + "&endTime=" + year + "/8/31";
		else if( month > 0 && month < 3 )
			urlParams = "?beginTime=" + ( year - 1 ) + "/9/1" + "&endTime=" + year + "/2/28";
		else
			urlParams = "?beginTime=" + year + "/9/1" + "&endTime=" + ( year + 1 ) + "/2/28";

		return urlParams;
	}


	/**
	 * 生成获取当天对应的URL参数链接
	 *
	 */
	public static String getTodayUrlParams() {

		Calendar calendar = Calendar.getInstance();
		// 当前日期
		int year = calendar.get( Calendar.YEAR );
		int month = calendar.get( Calendar.MONTH ) + 1;
		int day = calendar.get( Calendar.DAY_OF_MONTH );

		// 昨天日期
		calendar.add( Calendar.DAY_OF_MONTH, -1 );
		int year_yesterday = calendar.get( Calendar.YEAR );
		int month_yesterday = calendar.get( Calendar.MONTH ) + 1;
		int day_yesterday = calendar.get( Calendar.DAY_OF_MONTH );
		return "?beginTime=" + year_yesterday + "/" + month_yesterday + "/" + day_yesterday + "&endTime=" + year + "/" + month + "/" + day;
	}


	public static String getMonthUrlParams() {

		String urlParams = "";
		Calendar calendar = Calendar.getInstance();
		// 当前日期
		int year = calendar.get( Calendar.YEAR );
		int month = calendar.get( Calendar.MONTH ) + 1;

		if( month == 1 )
			urlParams = "?beginTime=" + ( year - 1 ) + "/12/1" + "&endTime=" + year + "1/1";
		else
			urlParams = "?beginTime=" + year + "/" + ( month - 1 ) + "/1" + "&endTime=" + year + "/" + month + "/1";

		return urlParams;
	}


	/**
	 * 初始化用于计算平均数的天数
	 *
	 */
	private static void defaultDays() {

		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		DateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get( Calendar.YEAR );
		int month = calendar.get( Calendar.MONTH ) + 1;
		Date week = null;
		Date term = null;

		// 当前时间
		Date now = new Date();

		// 周起始时间
		int daysBefore = calendar.get( Calendar.DAY_OF_WEEK ) - 2;
		if( daysBefore == -1 )
			daysBefore = 6;
		calendar.add( Calendar.DAY_OF_MONTH, -daysBefore );
		week = calendar.getTime();

		// 学期起始时间
		calendar = Calendar.getInstance();
		try {
			if( month > 2 && month < 9 )

				term = df.parse( year + "-03-01 00:00:00" );

			else
				term = df.parse( ( year - 1 ) + "-09-01 00:00:00" );
		} catch( ParseException e ) {
			e.printStackTrace();
		}
		daysOfWeek = ( int )( ( now.getTime() - week.getTime() ) / nd ) + 1;
		daysOfTerm = ( int )( ( now.getTime() - term.getTime() ) / nd ) + 1;
	}


	/**
	 * 初始化当天日期转换索引号的数组
	 *
	 */
	private static void createDayOfWeek() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime( monday );
		dayOfWeek = new int[ 7 ];
		dayOfWeek[ 0 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		dayOfWeek[ 1 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		dayOfWeek[ 2 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		dayOfWeek[ 3 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		dayOfWeek[ 4 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		dayOfWeek[ 5 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		dayOfWeek[ 6 ] = calendar.get( Calendar.DAY_OF_MONTH );
	}
}

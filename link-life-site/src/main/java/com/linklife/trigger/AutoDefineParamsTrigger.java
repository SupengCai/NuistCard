/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.linklife.trigger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.linklife.domain.model.NuistHistoryModel;

/**
 * <p>
 * AutoDefineParamsTrigger.java
 * </p>
 * 
 * <pre>
 * 定时更新系统静态参数
 * </pre>
 * 
 * @author caisupeng
 */
public class AutoDefineParamsTrigger {

	protected static Log log = LogFactory.getLog( "AutoDefineParamsTrigger" );


	public void execute() {

		NuistHistoryModel.URL_PARAMS_WEEK = getWeekUrlParams();
		NuistHistoryModel.URL_PARAMS_TERM = getTermUrlParams();
		NuistHistoryModel.URL_PARAMS_MONTH = getMonthUrlParams();
		defaultDays();
		createDayOfWeek();
		System.out.println( NuistHistoryModel.URL_PARAMS_TERM + " :" + NuistHistoryModel.URL_PARAMS_WEEK + " :" + NuistHistoryModel.daysOfTerm + " :"
				+ NuistHistoryModel.daysOfWeek + " :" + NuistHistoryModel.monday );
	}


	private String getWeekUrlParams() {

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
		NuistHistoryModel.monday = calendar.getTime();
		return "?beginTime=" + calendar.get( Calendar.YEAR ) + '/' + ( calendar.get( Calendar.MONTH ) + 1 ) + '/' + calendar.get( Calendar.DAY_OF_MONTH )
				+ "&endTime=" + year + '/' + month + '/' + day;
	}


	private String getTermUrlParams() {

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


	private String getMonthUrlParams() {

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


	private void defaultDays() {

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
		NuistHistoryModel.daysOfWeek = ( int )( ( now.getTime() - week.getTime() ) / nd ) + 1;
		NuistHistoryModel.daysOfTerm = ( int )( ( now.getTime() - term.getTime() ) / nd ) + 1;
	}


	private void createDayOfWeek() {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime( NuistHistoryModel.monday );
		NuistHistoryModel.dayOfWeek = new int[ 7 ];
		NuistHistoryModel.dayOfWeek[ 0 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		NuistHistoryModel.dayOfWeek[ 1 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		NuistHistoryModel.dayOfWeek[ 2 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		NuistHistoryModel.dayOfWeek[ 3 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		NuistHistoryModel.dayOfWeek[ 4 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		NuistHistoryModel.dayOfWeek[ 5 ] = calendar.get( Calendar.DAY_OF_MONTH );
		calendar.add( Calendar.DAY_OF_MONTH, +1 );
		NuistHistoryModel.dayOfWeek[ 6 ] = calendar.get( Calendar.DAY_OF_MONTH );
	}
}

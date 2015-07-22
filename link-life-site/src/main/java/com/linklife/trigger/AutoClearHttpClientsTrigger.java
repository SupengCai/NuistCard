/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.linklife.trigger;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.linklife.domain.model.HistoryDataRecorder;
import com.linklife.domain.model.NuistHistoryModel;
import com.linklife.web.httpapi.HttpClientRecorder;
import com.linklife.web.httpapi.HttpUtils;

/**
 * <p>
 * AutoClearHttpClientsTrigger.java
 * </p>
 * 
 * <pre>
 * 定时清理存储httpClient的HashMap
 * </pre>
 * 
 * @author caisupeng
 */
public class AutoClearHttpClientsTrigger {

	protected static Log log = LogFactory.getLog( "AutoClearHttpClientsTrigger" );


	public void execute() {

		// 获取临界时间
		Calendar calendar = Calendar.getInstance();
		// 15分钟
		calendar.add( Calendar.MINUTE, -15 );
		Date deadLine_quater = calendar.getTime();
		// 30分钟
		// calendar.add( Calendar.MINUTE, -30 );
		// Date deadLine_half = calendar.getTime();

		// 清除httpClient
		for( Map.Entry<String, HttpClientRecorder> e : HttpUtils.httpClientsMap.entrySet() ) {
			// System.out.println( "键:" + e.getKey() + ", 值:" + e.getValue() );
			HttpClientRecorder httpClientRecorder = e.getValue();
			if( deadLine_quater.after( httpClientRecorder.getCreateDate() ) ) {
				try {
					// 关闭过期HttpClient
					httpClientRecorder.getHttpClient().close();
					// 过期HttpClient移除容器
					HttpUtils.httpClientsMap.remove( e.getKey() );
				} catch( IOException e1 ) {
					e1.printStackTrace();
					log.error( e1.getMessage(), e1 );
				}
			}
		}

		// 清除历史数据
		for( Map.Entry<String, HistoryDataRecorder> e : NuistHistoryModel.historyDataMap.entrySet() ) {
			HistoryDataRecorder historyDataRecorder = e.getValue();

			// 清除过期历史数据
			if( deadLine_quater.after( historyDataRecorder.getCreateDate() ) )
				NuistHistoryModel.historyDataMap.remove( e.getKey() );
		}
	}
}

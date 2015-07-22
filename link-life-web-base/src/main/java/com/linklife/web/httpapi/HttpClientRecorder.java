package com.linklife.web.httpapi;

import java.util.Date;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * <p>
 * HttpClientRecorder.java
 * </p>
 * 
 * <pre>
 * 用户httpClient链接暂存
 * </pre>
 * 
 * @author caisupeng
 */
public class HttpClientRecorder {

	/** HttpClient生成时间 */
	private Date createDate;
	/** HttpClient本体 */
	private CloseableHttpClient httpClient;

	private int startYear;


	public HttpClientRecorder() {

		this.createDate = new Date();
		this.httpClient = HttpClients.createDefault();
	}


	public Date getCreateDate() {

		return createDate;
	}


	public void setCreateDate( Date createDate ) {

		this.createDate = createDate;
	}


	public CloseableHttpClient getHttpClient() {

		return httpClient;
	}


	public void setHttpClient( CloseableHttpClient httpClient ) {

		this.httpClient = httpClient;
	}


	public int getStartYear() {

		return startYear;
	}


	public void setStartYear( int startYear ) {

		this.startYear = startYear;
	}

}

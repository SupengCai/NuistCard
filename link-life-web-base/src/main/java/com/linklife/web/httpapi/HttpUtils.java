package com.linklife.web.httpapi;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.linklife.web.base.utils.RegexUtil;

/**
 * <p>
 * HttpUtils.java
 * </p>
 * 
 * <pre>
 * HttpAPI调用泛用工具
 * </pre>
 * 
 * @author caisupeng
 */
public class HttpUtils {

	protected static Log log = LogFactory.getLog( "HttpUtils" );

	private static PoolingHttpClientConnectionManager cm;
	private static CloseableHttpClient httpClient = null;

	/** 浏览器类型 */
	public final static String EXPLORER_CHROME = "Chrome";
	public final static String EXPLORER_FIREFOX = "火狐";
	public final static String EXPLORER_WECHAT = "微信";
	public final static String EXPLORER_UCBROWSER = "UC";
	public final static String EXPLORER_SAFARI = "Safari";
	public final static String EXPLORER_MQQBROWSER = "QQ浏览器";
	public final static String EXPLORER_QQ = "QQ";
	public final static String EXPLORER_SOGOUMOBILEBROWSER = "搜狗";
	public final static String EXPLORER_OTHERS = "其他";

	/** 设备类型 */
	public final static String DEVICE_IPHONE = "iPhone";

	/** 请求和超时时间 */
	private static RequestConfig requestConfig;
	/** httpClient容器 */
	public static ConcurrentHashMap<String, HttpClientRecorder> httpClientsMap = new ConcurrentHashMap<String, HttpClientRecorder>();

	static {
		cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal( 2 );// 连接池最大并发连接数
		cm.setDefaultMaxPerRoute( 2 );// 单路由最大并发数
		httpClient = HttpClients.custom().setConnectionManager( cm ).build();
		requestConfig = RequestConfig.custom().setSocketTimeout( 10000 ).setConnectTimeout( 10000 ).build();
	}


	/**
	 * 从http连接池获取httpClient
	 * 
	 * @return
	 */
	public static CloseableHttpClient getHttpClient() {

		return httpClient;
	}


	/**
	 * 从容器获取httpClient
	 * 
	 * @param userNumber
	 * @return
	 */
	public static HttpClientRecorder getHttpClientFromMap( String userNumber ) {

		if( StringUtils.isEmpty( userNumber ) )
			return null;

		if( httpClientsMap.containsKey( userNumber ) )
			return httpClientsMap.get( userNumber );
		else {
			HttpClientRecorder httpClientRecorder = new HttpClientRecorder();
			httpClientsMap.put( userNumber, httpClientRecorder );
			return httpClientRecorder;
		}
	}


	public static int getStartYearFromMap( String userNumber ) {

		if( StringUtils.isEmpty( userNumber ) )
			return -1;

		return httpClientsMap.get( userNumber ).getStartYear();
	}


	/**
	 * 获取配置后的HttpPost
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static HttpPost getHttpPost( String url, List<NameValuePair> postParamsList ) throws UnsupportedEncodingException {

		// 创建httppost
		HttpPost httpPost = new HttpPost( url );
		// 填充post参数
		UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity( postParamsList, "UTF-8" );
		httpPost.setEntity( uefEntity );
		httpPost.setConfig( requestConfig );
		return httpPost;
	}


	/**
	 * 获取配置后的HttpPost
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static HttpGet getHttpGet( String url ) throws UnsupportedEncodingException {

		// 创建httpget
		HttpGet httpGet = new HttpGet( url );
		httpGet.setConfig( requestConfig );
		return httpGet;
	}


	/**
	 * 从http连接池获取httpClient
	 * 
	 * @return
	 */
	public static void clearConnections() {

		cm.closeExpiredConnections();
		cm.closeIdleConnections( 30, TimeUnit.SECONDS );
	}


	/**
	 * 发送json数据的post请求
	 * 
	 * @param httpClient
	 * @param url
	 * @param jsonObject
	 * @return
	 */
	public static CloseableHttpResponse postJson( CloseableHttpClient httpClient, String url, JSONObject jsonObject ) {

		HttpPost post = new HttpPost( url );
		post.setEntity( postJsonEntity( "{\"coord_type\":\"3\",\"geotable_id\":\"75311\",\"latitude\":\"45\",\"ak\":\"BV4fuXdUHi3FQLqY44XBNaZG\",\"longitude\":\"90\"}" ) );
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute( post );
		} catch( ClientProtocolException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		} catch( IOException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
		return response;
	}


	/**
	 * 发送get请求，获取json对象
	 * 
	 * @param httpClient
	 * @param url
	 * @return
	 */
	public static JSONObject getJson( CloseableHttpClient httpClient, String url ) {

		return responceToJson( get( httpClient, url ) );
	}


	/**
	 * 发送get请求
	 * 
	 * @param httpClient
	 * @param url
	 * @return
	 */
	public static CloseableHttpResponse get( CloseableHttpClient httpClient, String url ) {

		CloseableHttpResponse response = null;
		HttpGet get = new HttpGet( url );
		try {
			response = httpClient.execute( get );
		} catch( Exception e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		} finally {

		}
		return response;
	}


	/**
	 * 从CloseableHttpResponse中获取json数据
	 * 
	 * @param res
	 * @return
	 */
	@SuppressWarnings( "deprecation" )
	public static JSONObject responceToJson( CloseableHttpResponse response ) {

		JSONObject jsonObject = null;
		try {
			if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ) {
				HttpEntity entity = response.getEntity();
				jsonObject = new JSONObject( new JSONTokener( new InputStreamReader( entity.getContent(), HTTP.UTF_8 ) ) );
				EntityUtils.consume( entity );
				response.close();
			}
		} catch( Exception e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		} finally {

		}
		return jsonObject;
	}


	/**
	 * 设置json形式的post请求
	 * 
	 * @param jsonString
	 * @return
	 */
	private static StringEntity postJsonEntity( String jsonString ) {

		StringEntity entity = null;
		try {
			entity = new StringEntity( jsonString );
		} catch( UnsupportedEncodingException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
		return entity;
	}


	/**
	 * 发送json数据的post请求
	 * 
	 * @param httpClient
	 * @param url
	 * @param params
	 * @return
	 */
	@SuppressWarnings( "deprecation" )
	public static CloseableHttpResponse postJson( CloseableHttpClient httpClient, String url, List<BasicNameValuePair> params ) {

		HttpPost localHttpPost = new HttpPost( url );
		CloseableHttpResponse response = null;
		try {
			localHttpPost.setEntity( new UrlEncodedFormEntity( params, HTTP.UTF_8 ) );
			response = httpClient.execute( localHttpPost );
		} catch( Exception e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
		return response;
	}


	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public static CloseableHttpResponse post( CloseableHttpClient httpClient, String url, List<NameValuePair> formparams ) {

		// 创建httppost
		HttpPost post = new HttpPost( url );

		CloseableHttpResponse response = null;
		try {
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity( formparams, "UTF-8" );
			post.setEntity( uefEntity );
			response = httpClient.execute( post );
		} catch( ClientProtocolException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		} catch( UnsupportedEncodingException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		} catch( IOException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
		return response;
	}


	/**
	 * 根据request获取浏览器版本信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getExplorerVersion( String userAgent ) {

		return RegexUtil.explorerFilter( userAgent );
	}


	/**
	 * 根据request获取浏览器版本信息
	 * 
	 * @param request
	 * @return
	 */
	public static String getDevice( String userAgent ) {

		return RegexUtil.deviceFilter( userAgent );
	}


	/**
	 * 设置Map中用户记录的年级
	 * 
	 * @param userNumber
	 * @param startYear
	 */
	public void setStartYear( String userNumber, int startYear ) {

		httpClientsMap.get( userNumber ).setStartYear( startYear );
	}
}

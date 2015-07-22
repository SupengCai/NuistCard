package com.linklife.web.base.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * <p>
 * HttpAPI.java
 * </p>
 * 
 * <pre>
 * 用于使用各HTTP API
 * </pre>
 * 
 * @author caisupeng
 */
public class HttpAPI {

	protected static Log log = LogFactory.getLog( "HttpAPI" );

	/** 淘宝ip API查询 */
	public static final String TAOBAO_IPINFO_SEARCH_API = "http://ip.taobao.com/service/getIpInfo.php?ip=";

	/** 百度Web Place suggestion API 关键字提示 */
	public static final String BAIDU_PLACE_SUGGESTION_API = "http://api.map.baidu.com/place/v2/suggestion/?ak=fByIyk938Uo4I8lOYd79Afc5&output=json";

	/** 百度LBS Place查询 */
	public static final String BAIDU_PLACE_SEARCH_LBS = "http://api.map.baidu.com/place/v2/search?ak=fByIyk938Uo4I8lOYd79Afc5&output=json";

	/** 百度LBS Track云存储 */
	public final static String BD_LBS_CLOUD = "http://api.map.baidu.com/geodata/v3";

	/** 南信大一卡通 */
	public final static String NUIST_CARD_API = "http://ucard.nuist.edu.cn:8070";

	/** 百度LBS Track云存储分页大小 */
	public final static String BD_LBS_CLOUD_PAGE_ZISE = "&page_size=30";

	/** 阿里返回IP不合法 */
	public final static String TAOBAO_IPINFO_SEARCH_API_INVAILD_IP = "invaild ip.";

	// POST
	public final static String BD_LBS_CLOUD_TABLE_CREATE_API = BD_LBS_CLOUD + "/geotable/create";
	public final static String BD_LBS_CLOUD_TABLE_UPDATE_API = BD_LBS_CLOUD + "/geotable/delete";
	public final static String BD_LBS_CLOUD_TABLE_DELETE_API = BD_LBS_CLOUD + "/geotable/update";

	public final static String NUIST_CARD_LOGIN_API = NUIST_CARD_API + "/Account/Login";
	public final static String NUIST_CARD_RECHARGE_API = NUIST_CARD_API + "/SynCard/Manage/TransferPost";
	public final static String NUIST_CARD_INFO_API = NUIST_CARD_API + "/SynCard/Manage/BasicInfo";

	// GET
	public final static String BD_LBS_CLOUD_TABLE_DETAIL_API = BD_LBS_CLOUD + "/geotable/detail";
	public final static String BD_LBS_CLOUD_TABLE_LIST_API = BD_LBS_CLOUD + "/geotable/list";

	public final static String BD_LBS_CLOUD_COLUMN_CREATE_API = BD_LBS_CLOUD + "/column/create";
	public final static String BD_LBS_CLOUD_COLUMN_DETAIL_API = BD_LBS_CLOUD + "/column/detail";
	public final static String BD_LBS_CLOUD_COLUMN_UPDATE_API = BD_LBS_CLOUD + "/column/update";
	public final static String BD_LBS_CLOUD_COLUMN_DELETE_API = BD_LBS_CLOUD + "/column/delete";
	public final static String BD_LBS_CLOUD_COLUMN_LIST_API = BD_LBS_CLOUD + "/column/list";

	public final static String BD_LBS_CLOUD_POI_CREATE_API = BD_LBS_CLOUD + "/poi/create";
	public final static String BD_LBS_CLOUD_POI_DETAIL_API = BD_LBS_CLOUD + "/poi/detail";
	public final static String BD_LBS_CLOUD_POI_UPDATE_API = BD_LBS_CLOUD + "/poi/update";
	public final static String BD_LBS_CLOUD_POI_DELETE_API = BD_LBS_CLOUD + "/poi/delete";
	public final static String BD_LBS_CLOUD_POI_LIST_API = BD_LBS_CLOUD + "/poi/list";

	/** httpclient */
	private CloseableHttpClient httpClient = HttpClients.createDefault();


	public CloseableHttpClient getHttpClient() {

		return httpClient;
	}


	public void setHttpClient( CloseableHttpClient httpClient ) {

		this.httpClient = httpClient;
	}


	/**
	 * 发送json数据的post请求
	 * 
	 * @param String
	 * @param JSONObject
	 * @return HttpResponse
	 */
	public CloseableHttpResponse postJson( String url, JSONObject jsonObject ) {

		HttpPost post = new HttpPost( url );
		// post.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
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
	 * @param String
	 * @return JSONObject
	 */
	public JSONObject getJson( String url ) {

		return responceToJson( get( url ) );
	}


	/**
	 * 发送get请求
	 * 
	 * @param String
	 * @return CloseableHttpResponse
	 */
	public CloseableHttpResponse get( String url ) {

		CloseableHttpResponse response = null;
		HttpGet get = new HttpGet( url );
		try {
			response = httpClient.execute( get );
		} catch( Exception e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
		return response;
	}


	/**
	 * 从CloseableHttpResponse中获取json数据
	 * 
	 * @param CloseableHttpResponse
	 * @return JSONObject
	 */
	@SuppressWarnings( "deprecation" )
	public static JSONObject responceToJson( CloseableHttpResponse res ) {

		JSONObject jsonObject = null;
		try {
			if( res.getStatusLine().getStatusCode() == HttpStatus.SC_OK ) {
				HttpEntity entity = res.getEntity();
				jsonObject = new JSONObject( new JSONTokener( new InputStreamReader( entity.getContent(), HTTP.UTF_8 ) ) );
			}
		} catch( Exception e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
		return jsonObject;
	}


	/**
	 * 生成百度LSB Place搜索url
	 * 
	 * @param String
	 * @param String
	 * @return String
	 */
	public static String getPlaceLBSUrl( String region, String query ) {

		return BAIDU_PLACE_SEARCH_LBS + "&region=" + region + "&query=" + query;
	}


	/**
	 * 设置json形式的post请求
	 * 
	 * @param String
	 * @return StringEntity
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
	 * @param String
	 * @param JSONObject
	 * @return CloseableHttpResponse
	 */
	@SuppressWarnings( "deprecation" )
	public CloseableHttpResponse postJson( String url, List<BasicNameValuePair> params ) {

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
	 * 根据IP地址获取用户带宽信息
	 * 
	 * @param String
	 * @return String
	 */
	public static JSONObject getIPInfoByIP( String ipAddress ) {

		// 获取淘宝IP API的JSON数据
		HttpAPI httpApi = new HttpAPI();
		JSONObject json = httpApi.getJson( HttpAPI.TAOBAO_IPINFO_SEARCH_API + ipAddress );
		try {
			httpApi.getHttpClient().close();
		} catch( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
		return json;
	}


	/**
	 * 根据IP地址获取用户城市
	 * 
	 * @param String
	 * @return String
	 */
	public static String getCityByIP( String ipAddress ) {

		// 获取淘宝IP API的JSON数据
		String result = "";
		HttpAPI httpApi = new HttpAPI();
		JSONObject json = httpApi.getJson( HttpAPI.TAOBAO_IPINFO_SEARCH_API + ipAddress );
		try {
			httpApi.getHttpClient().close();
		} catch( IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
		if( json.get( "data" ) instanceof JSONObject )
			result = json.getJSONObject( "data" ).getString( "city" );
		return result;
	}


	/**
	 * 发送 post请求访问本地应用并根据传递参数不同返回不同结果
	 */
	public CloseableHttpResponse post( String url, List<NameValuePair> formparams ) {

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
	 * 南信一卡通登陆
	 * 
	 * @param List
	 *            <NameValuePair>
	 * @return boolean
	 */
	public int loginNuistCard( List<NameValuePair> postParamsList ) {

		int loginStatus = 0;
		CloseableHttpResponse response = null;

		try {
			// 创建httppost
			HttpPost httpPost = new HttpPost( NUIST_CARD_LOGIN_API );

			// 填充post参数
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity( postParamsList, "UTF-8" );
			httpPost.setEntity( uefEntity );

			// 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout( 10000 ).setConnectTimeout( 10000 ).build();
			httpPost.setConfig( requestConfig );

			// 执行Post请求
			response = httpClient.execute( httpPost );
			JSONObject json = HttpAPI.responceToJson( response );

			// 登陆成功
			if( json.getBoolean( "success" ) ) {
				loginStatus = HttpStatus.SC_OK;
			}

			// 释放请求
			HttpEntity entity = response.getEntity();
			EntityUtils.consume( entity );
			response.close();

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
			loginStatus = HttpStatus.SC_REQUEST_TIMEOUT;
		}

		return loginStatus;
	}


	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	public Elements getNuistCardInfo() {

		Elements trs = null;
		CloseableHttpResponse response = null;

		try {
			// 创建httppost
			HttpGet httpGet = new HttpGet( HttpAPI.NUIST_CARD_INFO_API );

			// 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout( 10000 ).setConnectTimeout( 10000 ).build();
			httpGet.setConfig( requestConfig );

			// 执行Post请求
			response = httpClient.execute( httpGet );
			HttpEntity infoEntity = response.getEntity();

			// 获取成功
			if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ) {
				// 筛选用户信息
				String html = EntityUtils.toString( infoEntity );
				Document doc = Jsoup.parse( html );
				trs = doc.select( "table" ).select( "tr" );
				System.out.println( html );
			}
			EntityUtils.consume( infoEntity );
			response.close();

		} catch( ParseException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		} catch( IOException e ) {
			e.printStackTrace();
			log.error( e.getMessage(), e );
			throw new RuntimeException( e.getMessage() );
		}
		return trs;
	}


	/**
	 * 南信一卡通充值
	 * 
	 * @param List
	 *            <NameValuePair>
	 * @return
	 */
	public int rechargeNuistCard( List<NameValuePair> postParamsList ) {

		int loginStatus = 0;
		CloseableHttpResponse response = null;

		try {
			// 创建httppost
			HttpPost httpPost = new HttpPost( NUIST_CARD_RECHARGE_API );

			// 填充post参数
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity( postParamsList, "UTF-8" );
			httpPost.setEntity( uefEntity );

			// 设置请求和传输超时时间
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout( 10000 ).setConnectTimeout( 10000 ).build();
			httpPost.setConfig( requestConfig );

			// 执行Post请求
			response = httpClient.execute( httpPost );
			HttpEntity infoEntity = response.getEntity();

			// 获取信息
			if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ) {
				// 筛选用户信息
				JSONObject json = HttpAPI.responceToJson( response );
				// 充值成功
				if( json.getBoolean( "success" ) ) {
					loginStatus = HttpStatus.SC_OK;
				}
			}
			EntityUtils.consume( infoEntity );
			response.close();

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
			loginStatus = HttpStatus.SC_REQUEST_TIMEOUT;
		}

		return loginStatus;
	}
}

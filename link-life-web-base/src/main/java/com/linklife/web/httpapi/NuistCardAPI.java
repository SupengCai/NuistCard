package com.linklife.web.httpapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * <p>
 * NuistCardAPI.java
 * </p>
 * 
 * <pre>
 * 南信大一卡通API调用
 * </pre>
 * 
 * @author caisupeng
 */
public class NuistCardAPI {

	protected static Log log = LogFactory.getLog( "NuistCardAPI" );

	/** 南信大一卡通 */
	public final static String NUIST_CARD_API = "http://ucard.nuist.edu.cn:8070";

	// /** 登陆成功 */
	public static final String REQUEST_STATE = "success";
	public static final String REQUEST_MSG = "msg";
	public static final String REQUEST_OBJ = "obj";

	// /** 成绩查询 */
	public static final String SCORE_REQUEST_YEAR = "XN";
	public static final String SCORE_REQUEST_TERM = "XQ";
	public static final String SCORE_REQUEST_COURSE = "KCMC";
	public static final String SCORE_REQUEST_SCORE = "CJ";
	public static final String SCORE_REQUEST_CREDIT = "XF";


	// /** 登陆失败 */
	// public static final int REQUEST_FAILURE = 420;
	// /** 登陆超时 */
	// public static final int REQUEST_TIMEOUT = 408;

	/** 登陆状态 */
	public enum RequestStateEnum {
		REQUEST_SUCCESS, REQUEST_FAILURE, REQUEST_TIMEOUT
	}

	/** 请求类型 */
	public enum RequestDataType {
		REQUEST_STATE, REQUEST_MSG
	}


	// POST
	public final static String NUIST_CARD_LOGIN_API = NUIST_CARD_API + "/Account/Login";
	public final static String NUIST_CARD_RECHARGE_API = NUIST_CARD_API + "/SynCard/Manage/TransferPost";
	public final static String NUIST_CARD_CHANGEPWD_API = NUIST_CARD_API + "/SynCard/Manage/ChangeQueryPwd";
	public final static String NUIST_CARD_LOST_API = NUIST_CARD_API + "/SynCard/Manage/CardLost";
	public final static String NUIST_CARD_UNLOST_API = NUIST_CARD_API + "/SynCard/Manage/CardUnLost";
	// Mobile POST
	public final static String NUIST_CARD_MOBILE_LOGIN = NUIST_CARD_API + "/Api/Account/SignInForMobile";
	public final static String NUIST_CARD_MOBILE_SCORE = NUIST_CARD_API + "/Api/Score/Query";

	// GET
	public final static String NUIST_CARD_INFO_API = NUIST_CARD_API + "/SynCard/Manage/BasicInfo";
	public final static String NUIST_CARD_History_API = NUIST_CARD_API + "/SynCard/Manage/TrjnHistory";
	public final static String NUIST_CARD_TODAY_History_API = NUIST_CARD_API + "/SynCard/Manage/CurrentDayTrjn";

	// API常量
	/** 用户信息Table项 */
	public static final String NUIST_CARD_USER_NAME = "姓名";
	public static final String NUIST_CARD_USER_NUMBER = "学工号";
	public static final String NUIST_CARD_CARD_ID = "校园卡账号";
	public static final String NUIST_CARD_BALANCE = "校园卡余额";
	public static final String NUIST_CARD_BAND_CARD = "银行卡号";
	public static final String NUIST_CARD_STATUS_LOSS = "挂失状态";
	public static final String NUIST_CARD_STATUS_FROZEN = "冻结状态";
	public static final String NUIST_CARD_EDUCATION = "身份类型";
	public static final String NUIST_CARD_PERIOD = "部门名称";
	public static final String NUIST_CARD_STATUS_NORMAL = "正常卡";

	/** 用户历史账单Table项 */
	public static final String NUIST_CARD_TRADE_TIME = "交易时间";
	public static final String NUIST_CARD_TRADE_SHOP = "商户名称";
	public static final String NUIST_CARD_TRADE_TYPE = "交易名称";
	public static final String NUIST_CARD_TRADE_AMOUNT = "交易金额";
	public static final String NUIST_CARD_TRADE_BALANCE = "银行卡号";

	/** 用户交易类型项 */
	public static final String NUIST_CARD_TRADE_TYPE_CHANGEPWD = "持卡人修改密码";
	public static final String NUIST_CARD_TRADE_TYPE_BANK = "银行转帐";
	public static final String NUIST_CARD_TRADE_TYPE_CONSUME = "持卡人消费";
	public static final String NUIST_CARD_TRADE_TYPE_STREAM = "补助流水";

	/** 用户交易类型项 */
	public static final String NUIST_CARD_TRADE_AREA_EAST = "东";
	public static final String NUIST_CARD_TRADE_AREA_CENTRAL = "中";
	public static final String NUIST_CARD_TRADE_AREA_WEST = "西";


	/**
	 * 获取一卡通用户信息
	 * 
	 * @return
	 */
	public Elements getNuistCardInfo( String userNumber ) {

		Elements trs = null;

		try {
			CloseableHttpResponse response = null;
			HttpClientRecorder httpClientRecorder = HttpUtils.getHttpClientFromMap( userNumber );

			// 创建httppost
			HttpGet httpGet = HttpUtils.getHttpGet( NUIST_CARD_INFO_API );

			// 执行Post请求
			response = httpClientRecorder.getHttpClient().execute( httpGet );
			HttpEntity infoEntity = response.getEntity();

			// 获取成功
			if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ) {
				// 筛选用户信息
				String html = EntityUtils.toString( infoEntity );
				Document doc = Jsoup.parse( html );
				trs = doc.select( "table" ).select( "tr" );
				// System.out.println( html );
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
		} finally {
		}
		return trs;
	}


	/**
	 * 获取一卡通用户历史流水信息
	 * 
	 * @return
	 */
	public static Elements getNuistTable( String userNumber, String urlParams ) {

		Elements tbs = null;

		try {
			CloseableHttpResponse response = null;
			HttpClientRecorder httpClientRecorder = HttpUtils.getHttpClientFromMap( userNumber );

			// 创建httppost
			HttpGet httpGet = null;
			if( urlParams != null )
				httpGet = HttpUtils.getHttpGet( NUIST_CARD_History_API + urlParams );
			else
				httpGet = HttpUtils.getHttpGet( NUIST_CARD_TODAY_History_API );
			// RequestConfig requestConfig =
			// RequestConfig.custom().setSocketTimeout( 2000
			// ).setConnectTimeout( 2000 ).build();// 设置请求和传输超时时间
			// httpGet.setConfig( requestConfig );

			// 执行Post请求
			response = httpClientRecorder.getHttpClient().execute( httpGet );
			HttpEntity infoEntity = response.getEntity();

			// 获取成功
			if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ) {
				// 筛选用户信息
				String html = EntityUtils.toString( infoEntity );
				Document doc = Jsoup.parse( html );
				tbs = doc.select( "table" );
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
		} finally {
		}
		return tbs;
	}


	/**
	 * 伪造httpHeader
	 * 
	 * @param httpPost
	 * @return
	 */
	public static void setHttpHeader( HttpPost httpPost ) {

		httpPost.setHeader( "Accept", "*/*" );
		httpPost.setHeader( "Accept-Encoding", "gzip, deflate" );
		httpPost.setHeader( "Accept-Language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4" );
		httpPost.setHeader( "Connection", "keep-alive" );
		httpPost.setHeader( "Content-Length", "138" );
		// httpPost.setHeader( "Content-Type",
		// "application/x-www-form-urlencoded; charset=UTF-8" );
		httpPost.setHeader( "Cookie", "ASP.NET_SessionId=dvszztnmm4tgu0vwn22nccxq" );
		httpPost.setHeader( "Host", "ucard.nuist.edu.cn:8070" );
		httpPost.setHeader( "Origin", "http://ucard.nuist.edu.cn:8070" );
		httpPost.setHeader( "Referer",
				"http://ucard.nuist.edu.cn:8070/Account/Login?next=aHR0cDovL3VjYXJkLm51aXN0LmVkdS5jbjo4MDcwL1N5bkNhcmQvTWFuYWdlL1RyYW5zZmVy" );
		httpPost.setHeader( "User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36" );
		httpPost.setHeader( "X-Requested-With", "XMLHttpRequest" );
	}


	/**
	 * 提交Post请求
	 * 
	 * @param userNumber
	 * @param postParamsList
	 * @return
	 */
	public static JSONObject nuistPost4JSON( String userNumber, String url, List<NameValuePair> postParamsList ) {

		JSONObject json = new JSONObject();
		try {
			CloseableHttpResponse response = null;
			HttpClientRecorder httpClientRecorder = HttpUtils.getHttpClientFromMap( userNumber );

			// 创建httppost
			HttpPost httpPost = HttpUtils.getHttpPost( url, postParamsList );

			// 执行Post请求
			response = httpClientRecorder.getHttpClient().execute( httpPost );
			HttpEntity infoEntity = response.getEntity();

			// 获取信息
			if( response.getStatusLine().getStatusCode() == HttpStatus.SC_OK ) {
				// 筛选用户信息
				json = HttpUtils.responceToJson( response );
				// 成功
				// if( json.getBoolean( "success" ) ) {
				// requestStatus = RequestStateEnum.REQUEST_SUCCESS;
				// }
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
			return json;
			//throw new RuntimeException( e.getMessage() );
		} finally {
		}
		return json;
	}


	public static boolean nuistPostJSON4RequesrtState( String userNumber, String url, List<NameValuePair> postParamsList ) {
		boolean result=false;
		try {
			result = nuistPost4JSON( userNumber, url, postParamsList ).getBoolean( REQUEST_STATE );
		} catch( Exception e ) {
			return false;
		}
		return result;
	}


	public static String nuistPostJSON4RequesrtMsg( String userNumber, String url, List<NameValuePair> postParamsList ) {

		return nuistPost4JSON( userNumber, url, postParamsList ).getString( REQUEST_MSG );
	}
}

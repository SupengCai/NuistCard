package com.linklife.web.httpapi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;

/**
 * <p>
 * TaoBaoIPAPI.java
 * </p>
 * 
 * <pre>
 * 淘宝IP地址库API调用
 * </pre>
 * 
 * @author caisupeng
 */
public class TaoBaoIPAPI {

	protected static Log log = LogFactory.getLog( "TaoBaoIPAPI" );

	/** 淘宝ip API查询 */
	public static final String TAOBAO_IPINFO_SEARCH_API = "http://ip.taobao.com/service/getIpInfo.php?ip=";
	/** 阿里返回IP不合法 */
	public final static String TAOBAO_IPINFO_SEARCH_API_INVAILD_IP = "invaild ip.";


	// POST

	// GET

	/**
	 * 根据IP地址获取用户带宽信息
	 * 
	 * @param String
	 * @return String
	 */
	public static JSONObject getIPInfoByIP( String ipAddress ) {

		// 获取淘宝IP API的JSON数据
		return HttpUtils.getJson( HttpUtils.getHttpClient(), TaoBaoIPAPI.TAOBAO_IPINFO_SEARCH_API + ipAddress );
	}


	/**
	 * 根据IP地址获取用户城市
	 * 
	 * @param String
	 * @return String
	 */
	public static String getCityByIP( String ipAddress ) {

		// 获取淘宝IP API的JSON数据
		JSONObject json = HttpUtils.getJson( HttpUtils.getHttpClient(), TAOBAO_IPINFO_SEARCH_API + ipAddress );
		return json.getInt( "code" ) == 0 ? json.getJSONObject( "data" ).getString( "city" ) : "";
	}
}

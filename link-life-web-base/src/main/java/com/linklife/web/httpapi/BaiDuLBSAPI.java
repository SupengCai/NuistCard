package com.linklife.web.httpapi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * <p>
 * BaiDuLBSAPI.java
 * </p>
 * 
 * <pre>
 * 百度LBS服务API调用
 * </pre>
 * 
 * @author caisupeng
 */
public class BaiDuLBSAPI {

	protected static Log log = LogFactory.getLog( "BaiDuLBSAPI" );

	/** 百度Web Place suggestion API 关键字提示 */
	public static final String BAIDU_PLACE_SUGGESTION_API = "http://api.map.baidu.com/place/v2/suggestion/?ak=fByIyk938Uo4I8lOYd79Afc5&output=json";

	/** 百度LBS Place查询 */
	public static final String BAIDU_PLACE_SEARCH_LBS = "http://api.map.baidu.com/place/v2/search?ak=fByIyk938Uo4I8lOYd79Afc5&output=json";

	/** 百度LBS Track云存储 */
	public final static String BAIDU_LBS_CLOUD = "http://api.map.baidu.com/geodata/v3";

	// POST
	public final static String BAIDU_LBS_CLOUD_TABLE_CREATE_API = BAIDU_LBS_CLOUD + "/geotable/create";
	public final static String BAIDU_LBS_CLOUD_TABLE_UPDATE_API = BAIDU_LBS_CLOUD + "/geotable/delete";
	public final static String BAIDU_LBS_CLOUD_TABLE_DELETE_API = BAIDU_LBS_CLOUD + "/geotable/update";

	// GET
	public final static String BAIDU_LBS_CLOUD_TABLE_DETAIL_API = BAIDU_LBS_CLOUD + "/geotable/detail";
	public final static String BAIDU_LBS_CLOUD_TABLE_LIST_API = BAIDU_LBS_CLOUD + "/geotable/list";

	public final static String BAIDU_LBS_CLOUD_COLUMN_CREATE_API = BAIDU_LBS_CLOUD + "/column/create";
	public final static String BAIDU_LBS_CLOUD_COLUMN_DETAIL_API = BAIDU_LBS_CLOUD + "/column/detail";
	public final static String BAIDU_LBS_CLOUD_COLUMN_UPDATE_API = BAIDU_LBS_CLOUD + "/column/update";
	public final static String BAIDU_LBS_CLOUD_COLUMN_DELETE_API = BAIDU_LBS_CLOUD + "/column/delete";
	public final static String BAIDU_LBS_CLOUD_COLUMN_LIST_API = BAIDU_LBS_CLOUD + "/column/list";

	public final static String BAIDU_LBS_CLOUD_POI_CREATE_API = BAIDU_LBS_CLOUD + "/poi/create";
	public final static String BAIDU_LBS_CLOUD_POI_DETAIL_API = BAIDU_LBS_CLOUD + "/poi/detail";
	public final static String BAIDU_LBS_CLOUD_POI_UPDATE_API = BAIDU_LBS_CLOUD + "/poi/update";
	public final static String BAIDU_LBS_CLOUD_POI_DELETE_API = BAIDU_LBS_CLOUD + "/poi/delete";
	public final static String BAIDU_LBS_CLOUD_POI_LIST_API = BAIDU_LBS_CLOUD + "/poi/list";


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
}

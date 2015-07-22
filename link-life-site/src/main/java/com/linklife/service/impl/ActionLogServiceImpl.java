package com.linklife.service.impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linklife.domain.ActionLog;
import com.linklife.domain.ibator.AccountInfo;
import com.linklife.domain.model.LifeTrackModel;
import com.linklife.domain.model.SearchHistoryModel;
import com.linklife.web.base.utils.ReflectUtils;
import com.linklife.web.httpapi.BaiDuLBSAPI;
import com.linklife.web.httpapi.HttpUtils;

/**
 * <p>
 * ActionLogServiceImpl.java
 * </p>
 * 
 * <pre>
 * 用户操作记录相关业务接口的实现类
 * </pre>
 * 
 * @author caisupeng
 */
@Service
public class ActionLogServiceImpl {

	/** 用户操作记录领域类 */
	@Autowired
	private ActionLog actionLog;


	/**
	 * 记录用户搜索相关信息
	 * 
	 * @param SearchHistoryModel
	 * @return
	 */
	public void insertSearchHistory( SearchHistoryModel searchHistoryModel ) {

		searchHistoryModel.mockRepository( actionLog.getSearchHistoryModel().acquiredTargetRepository() );
		searchHistoryModel.fillSearchInfo();
		searchHistoryModel.insert();
	}


	/**
	 * 记录用户生活信息
	 * 
	 * @param SearchHistoryModel
	 * @return
	 */
	@SuppressWarnings( { "unused", "rawtypes" } )
	public void insertLifeTrack( LifeTrackModel lifeTrackModel, AccountInfo accountInfo ) {

		JSONObject json = null;
		lifeTrackModel.mockRepository( actionLog.getLifeTrackModel().acquiredTargetRepository() );
		lifeTrackModel.fillTrackInfo();
		lifeTrackModel.setName( accountInfo.getName() );
		lifeTrackModel.insert();

		// 将生活轨迹上传百度LBS
		CloseableHttpClient httpClient = HttpUtils.getHttpClient();
		CloseableHttpResponse res = HttpUtils.postJson( httpClient, BaiDuLBSAPI.BAIDU_LBS_CLOUD_POI_CREATE_API, ReflectUtils.getPOIList( lifeTrackModel ) );
		json = HttpUtils.responceToJson( res );
	}

}

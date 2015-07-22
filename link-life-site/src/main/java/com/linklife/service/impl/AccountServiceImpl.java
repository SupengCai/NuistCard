package com.linklife.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.http.impl.client.CloseableHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.linklife.domain.Account;
import com.linklife.domain.ibator.AccountInfo;
import com.linklife.domain.ibator.AccountLogin;
import com.linklife.domain.model.AccountInfoModel;
import com.linklife.domain.model.AccountLoginModel;
import com.linklife.domain.model.AccountSuggestModel;
import com.linklife.domain.model.LoginHistoryModel;
import com.linklife.web.base.utils.ContextUtils;
import com.linklife.web.httpapi.BaiDuLBSAPI;
import com.linklife.web.httpapi.HttpUtils;
import com.linklife.web.httpapi.TaoBaoIPAPI;

/**
 * <p>
 * AccountServiceImpl.java
 * </p>
 * 
 * <pre>
 * 用户信息相关业务接口的实现类
 * </pre>
 * 
 * @author caisupeng
 */
@Service
public class AccountServiceImpl {

	/** 用户领域类 */
	@Autowired
	private Account account;


	/**
	 * 通过邮箱查找用户对象
	 * 
	 * @param String
	 * @return 返回查找结果
	 */
	@SuppressWarnings( "rawtypes" )
	public AccountLogin getByEmail( String email ) {

		account.getAccountLoginModel().setEmail( email );
		return account.getAccountLoginModel().selectByEmail();
	}


	/**
	 * 通过邮箱查找用户信息对象
	 * 
	 * @param String
	 * @return 返回查找结果
	 */
	@SuppressWarnings( "rawtypes" )
	public AccountInfo getAccountInfoByEmail( String email ) {

		account.getAccountLoginModel().setEmail( email );
		return account.getAccountInfoModel().selectByEmail( account.getAccountLoginModel() );
	}


	/**
	 * 将用户信息添加至model
	 * 
	 * @param String
	 * @return 返回查找结果
	 */
	@SuppressWarnings( "rawtypes" )
	public Model setAccountInfoToModel( Model model, AccountInfo accountInfo ) {

		model.addAttribute( "name", accountInfo.getName() );
		model.addAttribute( "birthday", ( new SimpleDateFormat( "MM-dd" ) ).format( accountInfo.getBirthday() ) );
		model.addAttribute( "lastLoginLocation", accountInfo.getLastLoginLocation() );
		return model;
	}


	/**
	 * 存储用户登录信息
	 * 
	 * @return
	 */
	@SuppressWarnings( "rawtypes" )
	public void insertLoginHistoryById( String explorer ) {

		LoginHistoryModel loginHistoryModel = account.getLoginHistoryModel();
		// 获取用户id
		AccountInfo accountInfo = getAccountInfoByEmail( ContextUtils.getCurrentUserEmail() );

		// 填充用户登录信息
		loginHistoryModel.insertLoginHistory( accountInfo.getAccountId(), ContextUtils.getCurrentUserIp(), explorer );
	}


	/**
	 * 获取用户登录城市
	 * 
	 * @return
	 */
	public String getCurrentUserCity() {

		// 获取用户ip
		String ipAddress = ContextUtils.getCurrentUserIp();
		// 获取淘宝IP API的JSON数据
		return TaoBaoIPAPI.getCityByIP( ipAddress );
	}


	/**
	 * 获取用户信息
	 * 
	 * @return
	 */
	@SuppressWarnings( "rawtypes" )
	public AccountInfo getCurrentAccountInfo() {

		// 获取用户email
		String email = ContextUtils.getCurrentUserEmail();
		AccountInfo a = getAccountInfoByEmail( email );
		return a;
	}


	/**
	 * 关键字搜索POI
	 * 
	 * @param String
	 * @return int
	 */
	public JSONArray searchPlace( String region, String query ) {

		JSONArray jsonArray = null;
		JSONObject json = null;

		// 获取百度LSB Place搜索数据
		CloseableHttpClient httpClient = HttpUtils.getHttpClient();
		json = HttpUtils.getJson( httpClient, BaiDuLBSAPI.getPlaceLBSUrl( region, query ) );
		if( json.getInt( "status" ) == 0 && "ok".equals( json.getString( "message" ) ) ) {
			jsonArray = json.getJSONArray( "results" );
		}

		return jsonArray;
	}


	/**
	 * 添加新用户
	 * 
	 * @param AccountLoginModel
	 * @return int
	 */
	public int addAccount( AccountLoginModel accountLoginModel, String remoteAddr ) {

		accountLoginModel.mockRepository( account.getAccountLoginModel().acquiredTargetRepository() );
		accountLoginModel.fillAccountLogin();
		accountLoginModel.insert();
		insertAccountInfo( accountLoginModel, remoteAddr );
		return 1;
	}


	/**
	 * 添加新用户的信息
	 * 
	 * @param AccountLoginModel
	 * @return
	 */
	private void insertAccountInfo( AccountLoginModel accountLoginModel, String remoteAddr ) {

		account.getAccountInfoModel().fillAccountInfo( accountLoginModel, remoteAddr );
		account.getAccountInfoModel().insert();
	}


	/**
	 * 添加用户建议的信息
	 * 
	 * @param AccountLoginModel
	 * @return
	 */
	public void insertAccountSuggest( AccountSuggestModel accountSuggestModel, String remoteAddr ) {

		int accountId = 0;
		if( !ContextUtils.getCurrentUserEmail().isEmpty() )
			accountId = getAccountInfoByEmail( ContextUtils.getCurrentUserEmail() ).getAccountId();
		account.getAccountSuggestModel().fillAccountSuggest( accountSuggestModel, remoteAddr, accountId );
		account.getAccountSuggestModel().insert();
	}


	/**
	 * 获取所有用户列表
	 * 
	 * @param AccountLoginModel
	 * @return
	 */
	public List<AccountInfoModel> listFriends() {

		return account.getAccountInfoModel().selectAll();
	}


	/**
	 * 获取搜索地址提示JSON
	 * 
	 * @param AccountLoginModel
	 * @return
	 */
	public String getPlaceSuggestion( String region, String query ) {

		String result = "";
		JSONObject json = null;
		// 获取淘宝IP API的JSON数据
		CloseableHttpClient httpClient = HttpUtils.getHttpClient();
		json = HttpUtils.getJson( httpClient, BaiDuLBSAPI.BAIDU_PLACE_SUGGESTION_API + "&region=" + region + "&query=" + query );
		// 填充用户登录信息
		if( json.getInt( "status" ) == 0 ) {
			JSONArray jsonArray = json.getJSONArray( "result" );
			result = jsonArray.toString();
		}
		return result;
	}
}

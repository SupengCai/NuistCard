package com.linklife.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linklife.domain.ibator.AccountInfo;
import com.linklife.domain.ibator.AlertModel;
import com.linklife.domain.model.AccountInfoModel;
import com.linklife.domain.model.AccountSuggestModel;
import com.linklife.domain.model.LifeTrackModel;
import com.linklife.domain.model.SearchHistoryModel;
import com.linklife.service.impl.AccountServiceImpl;
import com.linklife.service.impl.ActionLogServiceImpl;
import com.linklife.web.base.utils.CodeConvert;
import com.linklife.web.base.utils.ContextUtils;
import com.linklife.web.base.utils.ReflectUtils;

/**
 * <p>
 * AccountController.java
 * </p>
 * 
 * <pre>
 * 用户相关控制层
 * </pre>
 * 
 * @author caisupeng
 */
@Controller
@RequestMapping( "/account" )
public class AccountController {

	/** 用户相关业务类 */
	@Autowired
	private AccountServiceImpl accountServiceImpl;

	/** 用户搜索相关业务类 */
	@Autowired
	private ActionLogServiceImpl actionLogServiceImpl;


	/**
	 * 用户主页
	 * 
	 * @return
	 */
	@RequestMapping( "/index" )
	public String index( Model model ) {
		// 获取个人信息
		fillAccountInfo4Model( model );
		return "/info";
	}


	/**
	 * Life-Link测试
	 * 
	 * @return
	 */
	@RequestMapping( "/test" )
	public String test( Model model ) {
		model.addAttribute( "city", accountServiceImpl.getCurrentUserCity() );
		return "/test";
	}


	/**
	 * 好友列表
	 * 
	 * @return
	 */
	@RequestMapping( "/list" )
	public String list( Model model ) {
		// 获取所有好友信息
		List<AccountInfoModel> friendslist = accountServiceImpl.listFriends();
		model.addAttribute( "top", "true" );
		model.addAttribute( "friendslist", friendslist );
		model.addAttribute( "timemap", getTimeRangeMap() );
		// 获取个人信息
		fillAccountInfo4Model( model );
		return "/list";
	}

	/**
	 * 修改个人信息
	 * 
	 * @return
	 */
	@RequestMapping( "/infoEdit" )
	public String infoEdit( Model model ) {
		// 获取个人信息
		fillAccountInfo4Model( model );
		return "/infoEdit";
	}


	/**
	 * 好友生活轨迹
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping( "/track" )
	public String track( LifeTrackModel lifeTrackModel ) {
		// 返回一周内好友生活信息json数据,或前端js走POI
		return "_infopage";
	}

	/**
	 * 给开发者的建议
	 * 
	 * @return
	 */
	@RequestMapping( value = "/suggest", method = RequestMethod.POST )
	public String suggest( AccountSuggestModel accountSuggestModel, AlertModel alertModel, Model model, HttpServletRequest request ) {
		accountServiceImpl.insertAccountSuggest( accountSuggestModel, request.getRemoteAddr() );
		alertModel.setTitle( "发送成功" );
		alertModel.setContent( "我们将在后续开发中采纳您的意见" );
		model.addAttribute( "AlertModel", alertModel );
		return "_infopage";
	}

	/**
	 * 生活轨迹LBS
	 * 
	 * @param String
	 * @return String
	 */
	@SuppressWarnings( "rawtypes" )
	@RequestMapping( value = "/track", method = RequestMethod.POST )
	public String track( LifeTrackModel lifeTrackModel, Model model, AlertModel alertModel ) {
		AccountInfo accountInfo = accountServiceImpl.getAccountInfoByEmail( ContextUtils.getCurrentUserEmail() );
		actionLogServiceImpl.insertLifeTrack( lifeTrackModel, accountInfo );
		alertModel.setTitle( "分享成功" );
		alertModel.setContent( "好友将看到您的动态" );
		model.addAttribute( "AlertModel", alertModel );
		return "_infopage";
	}

	/**
	 * 关键字查询
	 * 
	 * @param String
	 * @return String
	 */
	@ResponseBody
	@RequestMapping( value = "/search", method = RequestMethod.POST )
	public String search( @RequestBody SearchHistoryModel searchHistoryModel ) {

		actionLogServiceImpl.insertSearchHistory( searchHistoryModel );
		String json = accountServiceImpl.searchPlace( searchHistoryModel.getCity(), searchHistoryModel.getKeyword() ).toString();
		return json == null ? "" : CodeConvert.parseToUTF8( json );
	}
	
	/**
	 * 获得当前时间
	 * 
	 * @param String
	 * @return String
	 */
	private Map<String, String> getTimeRangeMap() {
		Map<String, String> map = new HashMap<String, String>();
		Date time = new Date();
		String maxtime = ReflectUtils.formatter.format( time );
		String mintime = ReflectUtils.formatter.format( new Date( time.getTime() - 1 * 24 * 60 * 60 * 1000 ) );
		map.put( "maxtime", maxtime );
		map.put( "mintime", mintime );
		return map;
	}
	
	/**
	 * 向Model填充用户信息
	 * 
	 * @param Model
	 */
	@SuppressWarnings( "rawtypes" )
	private void fillAccountInfo4Model( Model model ) {
		SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
		AccountInfo accountInfo = accountServiceImpl.getCurrentAccountInfo();
		model.addAttribute( "name", accountInfo.getName() );
		model.addAttribute( "email", ContextUtils.getCurrentUserEmail());
		model.addAttribute( "lastLoginLocation", accountInfo.getLastLoginLocation() );
		if(accountInfo.getBirthday()!=null)
			model.addAttribute( "birthday", dateFormat.format( accountInfo.getBirthday() ) );
	}
}

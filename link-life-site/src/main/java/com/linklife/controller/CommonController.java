package com.linklife.controller;

import java.text.SimpleDateFormat;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linklife.domain.ibator.AccountInfo;
import com.linklife.domain.ibator.AlertModel;
import com.linklife.domain.model.AccountLoginModel;
import com.linklife.domain.model.AccountSuggestModel;
import com.linklife.domain.model.SearchHistoryModel;
import com.linklife.service.impl.AccountServiceImpl;
import com.linklife.service.impl.ActionLogServiceImpl;
import com.linklife.service.impl.NuistServiceImpl;
import com.linklife.web.base.utils.CodeConvert;
import com.linklife.web.base.utils.ContextUtils;
import com.linklife.web.base.utils.RegexUtil;
import com.linklife.web.httpapi.CookieTool;
import com.linklife.web.httpapi.TaoBaoIPAPI;

/**
 * <p>
 * CommonController.java
 * </p>
 * 
 * <pre>
 * 用户相关控制层
 * </pre>
 * 
 * @author caisupeng
 */
@Controller
public class CommonController {

	/** 用户相关业务类 */
	@Autowired
	private AccountServiceImpl accountServiceImpl;

	/** 用户搜索相关业务类 */
	@Autowired
	private ActionLogServiceImpl actionLogServiceImpl;

	@Autowired
	private NuistServiceImpl nuistServiceImpl;


	/**
	 * Life-Link主页
	 * 
	 * @return
	 */
	@SuppressWarnings( "rawtypes" )
	@RequestMapping( "/index" )
	public String index( HttpServletRequest request, HttpServletResponse response, Model model, Integer signout ) {

		// model.addAttribute( "common", "true" );
		// model.addAttribute( "bottom", "true" );
		// model.addAttribute( "top", "true" );
		// model.addAttribute( "city", TaoBaoIPAPI.getCityByIP(
		// request.getRemoteAddr() ) );//
		// if( !ContextUtils.getCurrentUserEmail().isEmpty() ) {
		//
		// // 获取浏览器及设备信息
		// String userAgent = request.getHeader( "User-Agent" ).toString();
		// String exlporer = RegexUtil.explorerFilter( userAgent );
		// String device = RegexUtil.deviceFilter( userAgent );
		//
		// accountServiceImpl.insertLoginHistoryById( exlporer );
		// AccountInfo accountInfo = accountServiceImpl.getAccountInfoByEmail(
		// ContextUtils.getCurrentUserEmail() );
		//
		// //填充model
		// model.addAttribute( "name", accountInfo.getName() );
		// if( StringUtils.isEmpty( exlporer ) )
		// model.addAttribute( "explorer", exlporer );
		// if( StringUtils.isEmpty( device ) )
		// model.addAttribute( "device", device );
		// if( accountInfo.getBirthday() != null )
		// model.addAttribute( "birthday", ( new SimpleDateFormat( "MM-dd" )
		// ).format( accountInfo.getBirthday() ) );
		// }
		if( signout != null ) {
			CookieTool.clearSigninCookie( response );
			Cookie cookiePwd = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_PWD );
			Cookie cookieAct = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_ACCOUNT );
			Cookie cookieRem = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_REMEMBER );
			if( null != cookiePwd && null != cookieAct ) {
				model.addAttribute( "account", cookieAct.getValue() );
				model.addAttribute( "password", cookiePwd.getValue() );
				if( null != cookieRem )
					model.addAttribute( "rememberme", cookieRem.getValue() );
			}
		} else {
			Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

			if( null != cookie ) {
				nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
				nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
				model.addAttribute( "signined", true );
			} else {
				Cookie cookiePwd = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_PWD );
				Cookie cookieAct = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_ACCOUNT );
				Cookie cookieRem = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_REMEMBER );
				if( null != cookiePwd && null != cookieAct ) {
					model.addAttribute( "account", cookieAct.getValue() );
					model.addAttribute( "password", cookiePwd.getValue() );
					if( null != cookieRem )
						model.addAttribute( "rememberme", cookieRem.getValue() );
				}
			}
		}
		return "index";
	}


	/**
	 * acegi登陆错误跳转
	 *
	 * @param account
	 * @param model
	 * @param alertModel
	 *            返回通知模板页的信息填充
	 * @return
	 */
	@RequestMapping( "/loginerror" )
	public String loginerror( Model model, AlertModel alertModel ) {

		alertModel.setTitle( "登录失败" );
		alertModel.setContent( "邮箱或密码不正确哦~" );
		model.addAttribute( "AlertModel", alertModel );
		return "/infopage";
	}


	/**
	 * 登陆界面
	 * 
	 * @return
	 */
	@RequestMapping( value = "/regist" )
	public String login( Model model ) {

		return "/login";
	}


	/**
	 * 新建用户
	 *
	 * @param account
	 * @param model
	 * @param alertModel
	 *            返回通知模板页的信息填充
	 * @return
	 */
	@RequestMapping( value = "/new", method = RequestMethod.POST )
	public String addAccount( AccountLoginModel accountLoginModel, Model model, AlertModel alertModel, HttpServletRequest request ) {

		if( accountServiceImpl.addAccount( accountLoginModel, request.getRemoteAddr() ) == 1 ) {
			alertModel.setTitle( "恭喜" );
			alertModel.setContent( "可以用提交的信息登录哦~" );
		} else {
			alertModel.setTitle( "抱歉" );
			alertModel.setContent( "添加失败，稍后再试试吧~" );
		}
		model.addAttribute( "AlertModel", alertModel );
		return "/infopage";
	}


	/**
	 * 新建用户时邮箱认证
	 *
	 * @param account
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/emailvalid", method = RequestMethod.POST )
	public Object userNameValidAjax( String email ) {

		String result = "false";
		if( accountServiceImpl.getByEmail( email ) == null )
			result = "true";
		return result;
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

		if( !ContextUtils.getCurrentUserEmail().isEmpty() )
			actionLogServiceImpl.insertSearchHistory( searchHistoryModel );
		String json = accountServiceImpl.searchPlace( searchHistoryModel.getCity(), searchHistoryModel.getKeyword() ).toString();
		return json == null ? "" : CodeConvert.parseToUTF8( json );
	}


	/**
	 * 给开发者的建议
	 * 
	 * @return
	 */
	@RequestMapping( value = "/suggest" )
	public String suggest( Model model ) {

		model.addAttribute( "common", "true" );
		return "/suggest";
	}


	/**
	 * 搜索地点提示
	 * 
	 * @param String
	 * @return String
	 */
	@ResponseBody
	@RequestMapping( value = "/placeSuggestion" )
	public String placeSuggestion( String region, String query ) {

		return CodeConvert.parseToUTF8( accountServiceImpl.getPlaceSuggestion( region, query ) );
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
		return "/infopage";
	}
}

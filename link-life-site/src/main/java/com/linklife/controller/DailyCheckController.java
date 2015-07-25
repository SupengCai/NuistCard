package com.linklife.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.linklife.domain.ibator.DailyCheckAccount;
import com.linklife.domain.model.DailyCheckForm;
import com.linklife.service.impl.DailyCheckServiceImpl;

/**
 * <p>
 * DailyCheckController.java
 * </p>
 * 
 * <pre>
 * DailyCheck控制层
 * </pre>
 * 
 * @author caisupeng
 */
@Controller
@RequestMapping( "/dailycheck" )
public class DailyCheckController {

	/** 南信大一卡通相关业务类 */
	@Autowired
	private DailyCheckServiceImpl dailyCheckServiceImpl;

	protected static Log log = LogFactory.getLog( "DailyCheckController" );


	/**
	 * 南信一卡通登录页面
	 * 
	 * @return
	 */
	@RequestMapping( "/index" )
	public String index( HttpServletRequest request, HttpServletResponse response, Model model, Integer signout ) {

		// testAutoImp autoImp=new testAutoImp();
		// autoImp.test();
		// if( signout != null )
		// CookieTool.clearSigninCookie( response );
		// else {
		// Cookie cookie = CookieTool.getCookieByName( request,
		// CookieTool.COOKIE_USER_NUMBER );
		//
		// if( null != cookie ) {
		// dailyCheckServiceImpl.setUserInfoModel( cookie.getValue(), model );
		// dailyCheckServiceImpl.fillExplorerAndDevice( model,
		// request.getHeader( "User-Agent" ).toString() );
		// model.addAttribute( "signined", true );
		// }
		// }
		return "dailycheck/index";
	}


	/**
	 * 南信一卡通登录页面
	 * 
	 * @return
	 */
	@RequestMapping( "/register" )
	public String register( HttpServletRequest request, HttpServletResponse response, Model model, Integer signout ) {

		return "dailycheck/register";
	}

	/**
	 * 南信一卡通登录页面
	 * 
	 * @return
	 */
	@RequestMapping( "/update" )
	public String update( HttpServletRequest request, HttpServletResponse response, Model model, Integer signout ) {

		return "dailycheck/update";
	}

	/**
	 * 南信一卡通成绩查询
	 * 
	 * @return
	 */
	@SuppressWarnings( "rawtypes" )
	@RequestMapping( value = "/register", method = RequestMethod.POST )
	public String register( HttpServletRequest request, HttpServletResponse response, DailyCheckAccount dailyCheckAccount ) {

		if( dailyCheckServiceImpl.register( dailyCheckAccount ) )
			return "dailycheck/index";
		else
			return "dailycheck/register";

	}

	/**
	 * 南信一卡通成绩查询
	 * 
	 * @return
	 */
	@RequestMapping( value = "/signin", method = RequestMethod.POST )
	public String signin( HttpServletRequest request, HttpServletResponse response, DailyCheckForm dailyCheckForm ) {

		if( dailyCheckServiceImpl.signin( dailyCheckForm ) ){
		//保存cookies
			return "dailycheck/update";
		}
		else
			return "dailycheck/index";

	}
}

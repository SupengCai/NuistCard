package com.linklife.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.linklife.domain.ibator.NuistInfo;
import com.linklife.domain.ibator.NuistLogin;
import com.linklife.domain.model.CardUnlostModel;
import com.linklife.domain.model.ChangePWDModel;
import com.linklife.domain.model.NuistHistoryModel;
import com.linklife.domain.model.NuistLoginModel;
import com.linklife.domain.model.RechargeModel;
import com.linklife.service.impl.NuistServiceImpl;
import com.linklife.web.httpapi.CookieTool;

/**
 * <p>
 * NuistController.java
 * </p>
 * 
 * <pre>
 * 南信大控制层
 * </pre>
 * 
 * @author caisupeng
 */
@Controller
@RequestMapping( "/nuist" )
public class NuistController {

	/** 南信大一卡通相关业务类 */
	@Autowired
	private NuistServiceImpl nuistServiceImpl;

	protected static Log log = LogFactory.getLog( "NuistController" );


	/**
	 * 南信一卡通登录页面
	 * 
	 * @return
	 */
	@RequestMapping( "/index" )
	public String index( HttpServletRequest request, HttpServletResponse response, Model model, Integer signout ) {

		// testAutoImp autoImp=new testAutoImp();
		// autoImp.test();
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
				checkFirstPage( request, response, model );
			}else{
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
		return "nuist/index";
	}


	/**
	 * 南信一卡通用户信息页面
	 * 
	 * @return
	 */
	@SuppressWarnings( "rawtypes" )
	@RequestMapping( "/info" )
	public String info( HttpServletRequest request, HttpServletResponse response, Model model ) {

		String result = "redirect:index";

		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {

			// 获取用户信息
			String userNumber = cookie.getValue();
			NuistInfo nuistInfo = nuistServiceImpl.getNuistInfoModelByUserNumber( userNumber );
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
			model.addAttribute( "NuistInfo", nuistInfo );
			result = "nuist/info";
			checkFirstPage( request, response, model );
		}
		return result;
	}


	/**
	 * 南信一卡通用户充值页面
	 * 
	 * @return
	 */
	@RequestMapping( "/recharge" )
	public String recharge( HttpServletRequest request, HttpServletResponse response, Model model ) {

		String result = "redirect:index";
		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
			result = "nuist/recharge";
			checkFirstPage( request, response, model );
		}

		return result;
	}


	/**
	 * 南信一卡通用户修改密码页面
	 * 
	 * @return
	 */
	@RequestMapping( "/changepwd" )
	public String changepwd( HttpServletRequest request, HttpServletResponse response, Model model ) {

		String result = "redirect:index";
		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
			result = "nuist/changepwd";
			checkFirstPage( request, response, model );
		}

		return result;
	}


	/**
	 * 南信一卡通用户历史账单页面
	 * 
	 * @return
	 */
	@RequestMapping( "/history" )
	public String history( HttpServletRequest request, HttpServletResponse response, Model model ) {

		String result = "redirect:index";
		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {
			model.addAttribute( "userNumber", cookie.getValue() );
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
			result = "nuist/history";
			checkFirstPage( request, response, model );

			// 获取历史数据
			// nuistServiceImpl.setAllHistoryMap( cookie.getValue() );
		}

		return result;
	}


	/**
	 * 南信一卡通排行榜页面
	 * 
	 * @return
	 */
	@RequestMapping( "/ranklist" )
	public String ranklist( HttpServletRequest request, HttpServletResponse response, Model model ) {

		String result = "redirect:index";
		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {
			String userNumber = cookie.getValue();
			model.addAttribute( "userNumber", userNumber );
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
			nuistServiceImpl.fillStatisticData( userNumber, model );
			checkFirstPage( request, response, model );
			result = "nuist/ranklist";
		}
		return result;
	}


	/**
	 * 南信一卡通用户挂失页面
	 * 
	 * @return
	 */
	@RequestMapping( "/cardlost" )
	public String cardlost( HttpServletRequest request, HttpServletResponse response, Model model ) {

		String result = "redirect:index";
		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
			result = "nuist/cardlost";
			checkFirstPage( request, response, model );
		}

		return result;
	}


	/**
	 * 南信一卡通免责声明
	 * 
	 * @return
	 */
	@RequestMapping( "/declare" )
	public String declare( HttpServletRequest request, HttpServletResponse response, Model model ) {

		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
		}
		return "nuist/declare";
	}


	/**
	 * 南信一卡通成绩查询
	 * 
	 * @return
	 */
	@RequestMapping( "/score" )
	public String score( HttpServletRequest request, Model model ) {

		String result = "redirect:index";
		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {
			model.addAttribute( "userNumber", cookie.getValue() );
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
			result = "nuist/score";
		}

		return result;
	}


	/**
	 * 南信一卡通成绩查询
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/score", method = RequestMethod.POST )
	public String score( HttpServletRequest request, HttpServletResponse response, String pageIndex ) {

		return nuistServiceImpl.getNuistScoreJson( pageIndex, request, response );
	}


	/**
	 * 南信一卡通关于开发者
	 * 
	 * @return
	 */
	@RequestMapping( "/developer" )
	public String developer( HttpServletRequest request, HttpServletResponse response, Model model ) {

		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
		}
		return "nuist/develpoer";
	}


	/**
	 * 南信一卡通公众号二维码
	 * 
	 * @return
	 */
	@RequestMapping( "/wechatQR" )
	public String weichatRQ( HttpServletRequest request, HttpServletResponse response, Model model ) {

		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
		}
		return "nuist/wechatQR";
	}


	/**
	 * 南信一卡通免责声明
	 * 
	 * @return
	 */
	@RequestMapping( "/suggest" )
	public String suggest( HttpServletRequest request, HttpServletResponse response, Model model ) {

		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {
			nuistServiceImpl.setUserInfoModel( cookie.getValue(), model );
			nuistServiceImpl.fillExplorerAndDevice( model, request.getHeader( "User-Agent" ).toString() );
		}
		return "nuist/suggest";
	}


	/**
	 * 南信一卡通用户充值
	 * 
	 * @return
	 */
	@SuppressWarnings( "rawtypes" )
	@ResponseBody
	@RequestMapping( value = "/recharge", method = RequestMethod.POST )
	public String recharge( HttpServletRequest request, RechargeModel rechargeModel ) {

		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {

			// 获取用户信息
			String userNumber = cookie.getValue();
			NuistLogin nuistLogin = nuistServiceImpl.getNuistLoginModelByUserNumber( userNumber );
			return nuistServiceImpl.recharge( nuistLogin, rechargeModel, request ).toString();
		}
		return "";
	}


	/**
	 * 南信一卡通用户修改密码
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/changepwd", method = RequestMethod.POST )
	public String changepwd( HttpServletRequest request, ChangePWDModel changePWDModel ) {

		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {

			return nuistServiceImpl.chargePWD( cookie.getValue(), changePWDModel ).toString();
		}
		return "";
	}


	/**
	 * 南信一卡通用户反馈
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/suggest", method = RequestMethod.POST )
	public void suggest( HttpServletRequest request, String suggest ) {

		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );
		String userNumber = "";
		if( null != cookie )
			userNumber = cookie.getValue();
		nuistServiceImpl.insertSuggest( userNumber, suggest );
	}


	/**
	 * 南信一卡通用户挂失
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/cardlost", method = RequestMethod.POST )
	public String cardlost( HttpServletRequest request, String Password ) {

		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {

			return nuistServiceImpl.cardLost( cookie.getValue(), Password ).toString();
		}
		return "";
	}


	/**
	 * 南信一卡通用户挂失
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/cardunlost", method = RequestMethod.POST )
	public String cardunlost( HttpServletRequest request, CardUnlostModel cardUnlostModel ) {

		Cookie cookie = CookieTool.getCookieByName( request, CookieTool.COOKIE_USER_NUMBER );

		if( null != cookie ) {

			return nuistServiceImpl.cardUnlost( cookie.getValue(), cardUnlostModel ).toString();
		}
		return "";
	}


	/**
	 * 南信一卡通用户登陆
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@ResponseBody
	@RequestMapping( value = "/signin", method = RequestMethod.POST )
	public String login( NuistLoginModel nuistLoginModel, HttpServletRequest request, HttpServletResponse response ) throws UnsupportedEncodingException {

		return nuistServiceImpl.login( nuistLoginModel, request, response ).toString();
	}


	/**
	 * 账单ajax请求
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping( "/historystatistic" )
	public String historystatistic( String userNumber, int historyType ) {

		return nuistServiceImpl.getHistoryJson( userNumber, historyType );
	}


	/**
	 * 参与数据分析请求
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping( "/statistictype" )
	public String statistictype( String userNumber, int statisticType ) {

		nuistServiceImpl.updateStatisticType( userNumber, statisticType );
		return "0";
	}


	/**
	 * 南信一卡通前端测试页面
	 *
	 * @return
	 */
	@RequestMapping( "/test" )
	public String test() {

		System.out.println( NuistHistoryModel.getMonthUrlParams() );
		// String date = "2012/9/3 10:00:29";
		// String[] weekDays = { "周一", "周二", "周三", "周四", "周五", "周六", "周天" };
		// ?beginTime=2008/01/01&endTime=2020/12/30
		// Calendar calendar = Calendar.getInstance();
		// // 当前日期
		// // int year = calendar.get( Calendar.YEAR );
		// // int month = calendar.get( Calendar.MONTH ) + 1;
		// // int day = calendar.get( Calendar.DAY_OF_MONTH );
		//
		// // 周一日期
		// int daysBefore = calendar.get( Calendar.DAY_OF_WEEK ) - 2;
		// if( daysBefore == -1 )
		// daysBefore = 6;
		// calendar.add( Calendar.DAY_OF_MONTH, -daysBefore );
		// Date date = calendar.getTime();
		// String Monday = calendar.get( Calendar.YEAR ) + "/" + ( calendar.get(
		// Calendar.MONTH ) + 1 ) + '/' + calendar.get( Calendar.DAY_OF_MONTH );
		// String urlparams = "?beginTime=" + calendar.get( Calendar.YEAR ) +
		// '/' + ( calendar.get( Calendar.MONTH ) + 1 ) + '/'
		// + calendar.get( Calendar.DAY_OF_MONTH ) + "&endTime=" + year + '/' +
		// month + '/' + day;
		// System.out.println( "Monday:" + Monday );
		// System.out.println( NuistHistoryModel.daysOfTerm + "," +
		// NuistHistoryModel.daysOfWeek + "," + NuistHistoryModel.URL_PARAMS_ALL
		// + ","
		// + NuistHistoryModel.URL_PARAMS_TERM + "," +
		// NuistHistoryModel.URL_PARAMS_WEEK );
		// System.out.println( "urlparams:" +
		// NuistHistoryModel.getTermUrlParams() );

		// System.out.println( date.toString() );
		// String desult = "2015/2/2";
		// for( int i = 0; i < dayOfWeek; i++ )
		// System.out.println( weekDays[ i ] );

		// System.out.println( "Monday" + NuistHistoryModel.monday );
		// String aString = "2012/9/3 11:24:34";
		// System.out.println( aString );
		// System.out.println( aString.split( "/" )[ 2 ].split( " " )[ 0 ] );
		// System.out.println( "dayOfWeek index:" +
		// NuistHistoryModel.getIndexByDay( 4 ) );
		// for( int i = 0; i < 7; i++ ) {
		// System.out.println( NuistHistoryModel.dayOfWeek[ i ] );
		// }
		// RegexUtil.dayFilter( aString );
		return "nuist/test";
	}


	/**
	 * 处理提示框显示判断
	 * 
	 */
	private void checkFirstPage( HttpServletRequest request, HttpServletResponse response, Model model ) {

		Cookie cookiePage = CookieTool.getCookieByName( request, CookieTool.COOKIE_FIRST_PAGE );
		if( null != cookiePage ) {
			CookieTool.clearCookie( response, CookieTool.COOKIE_FIRST_PAGE );
			model.addAttribute( "firstPage", true );
		}
	}


	/**
	 * 南信一卡通前端测试页面
	 *
	 * @return
	 */
	@RequestMapping( "/caisupeng" )
	public String caisupeng() {

		nuistServiceImpl.updateStatisticData();

		return "nuist/test";
	}
}

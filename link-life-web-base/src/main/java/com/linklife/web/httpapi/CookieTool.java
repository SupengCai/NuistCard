package com.linklife.web.httpapi;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieTool {

	/** Cookies类型 */
	public static final String COOKIE_USER_NUMBER = "USER_NUMBER";
	public static final String COOKIE_USER_ACCOUNT = "USER_ACCOUNT";
	public static final String COOKIE_USER_PWD = "USER_PWD";
	public static final String COOKIE_USER_REMEMBER = "USER_REMEMBER";
	public static final String COOKIE_FIRST_PAGE = "FIRST_PAGE";
	public static final String COOKIE_API_OBJ = "API_OBJ";


	/**
	 * 设置cookie（接口方法）
	 * 
	 * @author 刘鹏
	 * @param response
	 * @param name
	 *            cookie名字
	 * @param value
	 *            cookie值
	 * @param maxAge
	 *            cookie生命周期 以秒为单位
	 */
	public static void addCookie( HttpServletResponse response, String name, String value, int maxAge ) {

		Cookie cookie = new Cookie( name, value );
		cookie.setPath( "/" );
		if( maxAge > 0 ) {
			cookie.setMaxAge( maxAge );
		}
		response.addCookie( cookie );
	}


	/**
	 * 根据名字获取cookie（接口方法）
	 * 
	 * @author 刘鹏
	 * @param request
	 * @param name
	 *            cookie名字
	 * @return
	 */
	public static Cookie getCookieByName( HttpServletRequest request, String name ) {

		Map<String, Cookie> cookieMap = ReadCookieMap( request );
		if( cookieMap.containsKey( name ) ) {
			Cookie cookie = ( Cookie )cookieMap.get( name );
			return cookie;
		} else {
			return null;
		}
	}


	/**
	 * 根据名字清除cookie（接口方法）
	 * 
	 * @author caisupeng
	 * @param response
	 * @param cookie
	 */
	public static void clearSigninCookie( HttpServletResponse response ) {

		Cookie cookie = new Cookie( COOKIE_USER_NUMBER, null );
		if( cookie != null ) {
			cookie.setMaxAge( 0 );
			cookie.setPath( "/" );
			response.addCookie( cookie );
		}
		clearCookie( response, COOKIE_API_OBJ );
	}


	/**
	 * 根据名字清除cookie（接口方法）
	 * 
	 * @author caisupeng
	 * @param response
	 * @param cookie
	 */
	public static void clearCookie( HttpServletResponse response, String key ) {

		Cookie cookie = new Cookie( key, null );
		if( cookie != null ) {
			cookie.setMaxAge( 0 );
			cookie.setPath( "/" );
			response.addCookie( cookie );
		}
	}


	/**
	 * 将cookie封装到Map里面（非接口方法）
	 * 
	 * @author 刘鹏
	 * @param request
	 * @return
	 */
	private static Map<String, Cookie> ReadCookieMap( HttpServletRequest request ) {

		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if( null != cookies ) {
			for( Cookie cookie : cookies ) {
				cookieMap.put( cookie.getName(), cookie );
			}
		}
		return cookieMap;
	}
}

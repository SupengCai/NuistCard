package com.linklife.web.base.utils;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.ui.WebAuthenticationDetails;
import org.acegisecurity.userdetails.UserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * ContextUtils.java
 * </p>
 * 
 * <pre>
 * 获取上下文信息
 * </pre>
 * 
 * @author caisupeng
 */
public class ContextUtils {

	protected static Log log = LogFactory.getLog( "ContextUtils" );


	/**
	 * 从acegi上下文中获取当前用户名
	 * 
	 * @return String
	 */
	public static String getCurrentUserEmail() {

		String userName = "anonymous";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if( auth == null || auth.getPrincipal() == null ) {
		} else if( auth.getPrincipal() instanceof UserDetails ) {
			userName = ( ( UserDetails )auth.getPrincipal() ).getUsername();
		} else {
			userName = auth.getPrincipal().toString();
		}
		return "anonymous".equals( userName ) ? "" : userName;
	}


	/**
	 * 从acegi上下文中获取当前用户登录IP
	 * 
	 * @return String
	 */
	public static String getCurrentUserIp() {

		String userIp = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if( auth.getDetails() instanceof WebAuthenticationDetails ) {
			WebAuthenticationDetails detail = ( WebAuthenticationDetails )auth.getDetails();
			userIp = detail.getRemoteAddress();
		}
		return userIp;
	}

}

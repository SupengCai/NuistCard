package com.linklife.web.base.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.linklife.web.httpapi.HttpUtils;

/**
 * <p>
 * RegexUtil.java
 * </p>
 * 
 * @author caisupeng
 */
public class RegexUtil {

	/** 待过滤字符串 */
	private String regexStr;
	/** 过滤'img'标签内容 */
	public static final Pattern HTML_TAG_IMG_SHORT = Pattern.compile( "<img[^>]*?>" );
	public static final Pattern HTML_TAG_IMG = Pattern.compile( "<img[^>]*?>.*?</img>" );
	/** 过滤'input'标签内容 */
	public static final Pattern HTML_TAG_INPUT_SHORT = Pattern.compile( "<input[^>]*?>" );
	public static final Pattern HTML_TAG_INPUT = Pattern.compile( "<input[^>]*?>.*?</input>" );
	/** 过滤'a'标签内容 */
	public static final Pattern HTML_TAG_A = Pattern.compile( "<a[^>]*?>.*?</a>" );
	/** 过滤网址 */
	public static final Pattern HTML_HTTP = Pattern.compile( "http:[//a-zA-Z\\.?&=0-9]*" );
	public static final Pattern HTML_HTTPS = Pattern.compile( "https:[//a-zA-Z\\.?&=0-9]*" );
	public static final Pattern HTML_WWW = Pattern.compile( "(www.[a-zA-Z\\.?&=0-9]*)" );

	/** 过滤浏览器 */
	public static final Pattern EXPLORER_CHROME = Pattern.compile( "Chrome" );
	public static final Pattern EXPLORER_FIREFOX = Pattern.compile( "Firefox" );
	public static final Pattern EXPLORER_WECHAT = Pattern.compile( "MicroMessenger" );
	public static final Pattern EXPLORER_UCBROWSER = Pattern.compile( "UCBrowser" );
	public static final Pattern EXPLORER_SAFARI = Pattern.compile( "Safari" );
	public static final Pattern EXPLORER_MQQBROWSER = Pattern.compile( "MQQBrowser" );
	public static final Pattern EXPLORER_QQ = Pattern.compile( "QQ" );
	public static final Pattern EXPLORER_SOGOUMOBILEBROWSER = Pattern.compile( "SogouMobileBrowser" );
	/** 过滤iPhone */
	public static final Pattern DEVICE_IPHONE = Pattern.compile( "iPhone" );
	/** 匹配日期 */
	public static final Pattern DATE_OF_DAY = Pattern.compile( "/.*\\s" );


	public RegexUtil() {

		this.regexStr = "";
	}


	public String getRegexStr() {

		return regexStr;
	}


	public void setRegexStr( String regexStr ) {

		this.regexStr = regexStr;
	}


	/**
	 * 过滤字符串中'img'标签内容
	 * 
	 * @param htmlStr
	 * @return 返回过滤后的字符串
	 */
	public static String imgTagFilter( String htmlStr ) {

		if( StringUtils.isEmpty( htmlStr ) ) {
			return "";
		}
		Matcher mat = HTML_TAG_IMG.matcher( htmlStr );
		return mat.replaceAll( "" );
	}


	/**
	 * 过滤字符串中'img'标签内容
	 * 
	 * @param htmlStr
	 * @return 返回过滤后的字符串
	 */
	public static String shortImgTagFilter( String htmlStr ) {

		if( StringUtils.isEmpty( htmlStr ) ) {
			return "";
		}
		Matcher mat = HTML_TAG_IMG_SHORT.matcher( htmlStr );
		return mat.replaceAll( "" );
	}


	/**
	 * 过滤字符串中'input'标签内容
	 * 
	 * @param htmlStr
	 * @return 返回过滤后的字符串
	 */
	public static String inputTagFilter( String htmlStr ) {

		if( StringUtils.isEmpty( htmlStr ) ) {
			return "";
		}
		Matcher mat = HTML_TAG_INPUT.matcher( htmlStr );
		return mat.replaceAll( "" );
	}


	/**
	 * 过滤字符串中'input'标签内容
	 *
	 * @param htmlStr
	 * @return 返回过滤后的字符串
	 */
	public static String shortInputTagFilter( String htmlStr ) {

		if( StringUtils.isEmpty( htmlStr ) ) {
			return "";
		}
		Matcher mat = HTML_TAG_INPUT_SHORT.matcher( htmlStr );
		return mat.replaceAll( "" );
	}


	/**
	 * 过滤字符串中'a'标签内容
	 * 
	 * @param htmlStr
	 * @return 返回过滤后的字符串
	 */
	public static String aTagFilter( String htmlStr ) {

		if( StringUtils.isEmpty( htmlStr ) ) {
			return "";
		}
		Matcher mat = HTML_TAG_A.matcher( htmlStr );
		return mat.replaceAll( "" );
	}


	/**
	 * 过滤网址
	 *
	 * @return this
	 */
	public static String httpFilter( String htmlStr ) {

		if( StringUtils.isEmpty( htmlStr ) ) {
			return "";
		}
		Matcher mat = HTML_HTTP.matcher( htmlStr );
		return mat.replaceAll( "" );
	}


	public static String httpsFilter( String htmlStr ) {

		if( StringUtils.isEmpty( htmlStr ) ) {
			return "";
		}
		Matcher mat = HTML_HTTPS.matcher( htmlStr );
		return mat.replaceAll( "" );
	}


	public static String wwwFilter( String htmlStr ) {

		if( StringUtils.isEmpty( htmlStr ) ) {
			return "";
		}
		Matcher mat = HTML_WWW.matcher( htmlStr );
		return mat.replaceAll( "" );
	}


	/**
	 * 过滤字符串中'img'标签内容
	 * 
	 * @return this
	 */
	public RegexUtil imgTagFilter() {

		if( StringUtils.isEmpty( regexStr ) ) {
			return this;
		}
		Matcher mat = HTML_TAG_IMG.matcher( regexStr );
		this.regexStr = mat.replaceAll( "" );
		return this;
	}


	/**
	 * 过滤字符串中'input'标签内容
	 * 
	 * @return this
	 */
	public RegexUtil inputTagFilter() {

		if( StringUtils.isEmpty( regexStr ) ) {
			return this;
		}
		Matcher mat = HTML_TAG_INPUT.matcher( regexStr );
		this.regexStr = mat.replaceAll( "" );
		return this;
	}


	/**
	 * 过滤字符串中'a'标签内容
	 * 
	 * @return this
	 */
	public RegexUtil aTagFilter() {

		if( StringUtils.isEmpty( regexStr ) ) {
			return this;
		}
		Matcher mat = HTML_TAG_A.matcher( regexStr );
		this.regexStr = mat.replaceAll( "" );
		return this;
	}


	/**
	 * 过滤字符串中浏览器版本
	 * 
	 * @return this
	 */
	public static String explorerFilter( String userAgent ) {

		if( StringUtils.isEmpty( userAgent ) ) {
			return "";
		}
		Matcher mat = EXPLORER_WECHAT.matcher( userAgent );
		if( mat.find() )
			return HttpUtils.EXPLORER_WECHAT;
		mat = EXPLORER_CHROME.matcher( userAgent );
		if( mat.find() )
			return HttpUtils.EXPLORER_CHROME;
		mat = EXPLORER_FIREFOX.matcher( userAgent );
		if( mat.find() )
			return HttpUtils.EXPLORER_FIREFOX;
		mat = EXPLORER_UCBROWSER.matcher( userAgent );
		if( mat.find() )
			return HttpUtils.EXPLORER_UCBROWSER;
		mat = EXPLORER_SAFARI.matcher( userAgent );
		if( mat.find() )
			return HttpUtils.EXPLORER_SAFARI;
		mat = EXPLORER_MQQBROWSER.matcher( userAgent );
		if( mat.find() )
			return HttpUtils.EXPLORER_MQQBROWSER;
		mat = EXPLORER_QQ.matcher( userAgent );
		if( mat.find() )
			return HttpUtils.EXPLORER_QQ;
		mat = EXPLORER_SOGOUMOBILEBROWSER.matcher( userAgent );
		if( mat.find() )
			return HttpUtils.EXPLORER_SOGOUMOBILEBROWSER;
		return "";
	}


	/**
	 * 过滤字符串中设备类型
	 * 
	 * @return this
	 */
	public static String deviceFilter( String userAgent ) {

		if( StringUtils.isEmpty( userAgent ) ) {
			return "";
		}
		Matcher mat = DEVICE_IPHONE.matcher( userAgent );
		if( mat.find() )
			return HttpUtils.DEVICE_IPHONE;
		return "";
	}


	/**
	 * 获取字符串中日期
	 * 
	 * @return this
	 */
	// public static String dayFilter( String date ) {
	//
	// if( StringUtils.isEmpty( date ) ) {
	// return "";
	// }
	// Matcher mat = DATE_OF_DAY.matcher( date );
	// if( mat.find() )
	// return "有";
	// return "";
	// }
}

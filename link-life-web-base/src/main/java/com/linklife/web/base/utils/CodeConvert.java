package com.linklife.web.base.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class CodeConvert {
	
	protected static Log log = LogFactory.getLog("CodeConvert");
	
	/**
     * 返回response时编码数据，防止中文乱码
     * 
     * @param String
     * @param JSONObject
     * @return HttpResponse
     */
	public static String parseToUTF8(String str) {
		try {
			str=new String(str.getBytes(), "ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
		return str;
	}
	
	public static String parseToZh(String str) {
		try {
			str=new String(str.getBytes(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage());
		}
		return str;
	}
	
}

package com.linklife.web.base.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.message.BasicNameValuePair;

import com.linklife.common.utils.ReflectUtil;
import com.linklife.domain.ibator.LifeTrack;

/**
 * <p>
 * ReflectUtils.java
 * </p>
 * 
 * <pre>
 * 反射相关工具类
 * </pre>
 * 
 * @author caisupeng
 */
public class ReflectUtils extends ReflectUtil{

	protected static Log log = LogFactory.getLog("ReflectUtils");
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
     * 填充LBS POI数据
     * 
     * @param T
     * @return List<BasicNameValuePair> 
     */
	public static <T> List<BasicNameValuePair> getPOIList(T t) {
		List<BasicNameValuePair> params=new ArrayList<BasicNameValuePair>();
		if (t != null && t instanceof LifeTrack) {
			// 获取模板类
			Class<?> clzT = t.getClass();
			// 获取实体类的所有属性，返回Field数组
			Field[] fields = clzT.getDeclaredFields();
			// 获取父类模板类
			Class<?> superT = t.getClass().getSuperclass();
			// 获取父类的所有属性，返回Field数组
			Field[] superfields = superT.getDeclaredFields();
			
			try {
				// 遍历T类型中所有属性
				for (Field field : fields) {
					Object obj=t.getClass().getMethod("get" + getMethodName(field.getName())).invoke(t);
					params.add(new BasicNameValuePair(field.getName(),obj.toString()));
				}
				// 遍历父类型中所有属性
				for (Field field : superfields) {
					Object obj=t.getClass().getMethod("get" + getMethodName(field.getName())).invoke(t);
					if (obj instanceof Date) {
						obj=formatter.format(obj);
					}
					params.add(new BasicNameValuePair(field.getName(),obj.toString()));
				}
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				throw new RuntimeException(e.getMessage());
			} catch (SecurityException e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				throw new RuntimeException(e.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage(), e);
				throw new RuntimeException(e.getMessage());
			}
		}
		return params;
	}
	
	/**
     * 把字符串的第一个字母大写(效率最高)
     * 
     * @param String
     * @return String 
     */
	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
}

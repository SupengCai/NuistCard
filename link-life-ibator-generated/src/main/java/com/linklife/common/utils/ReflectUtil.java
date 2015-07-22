package com.linklife.common.utils;

import java.lang.reflect.Field;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.linklife.domain.base.BaseDomain;

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
public class ReflectUtil {

	protected static Log log = LogFactory.getLog("ReflectUtil");

	public static <T, U> void copyPropertis(T t, U u) {
		if (t != null && u != null && t instanceof BaseDomain) {
			// 获取模板类
			Class<?> clzT = t.getClass();
			// 获取实体类的所有属性，返回Field数组
			Field[] fieldsT = clzT.getDeclaredFields();
			
			try {
				// 遍历T类型中所有属性
				for (Field fieldT : fieldsT) {
					Object obj=t.getClass().getMethod("get" + getMethodName(fieldT.getName())).invoke(t);
					if(obj!=null)
						t.getClass().getMethod("set" + getMethodName(fieldT.getName()),new Class[]{obj.getClass()}).invoke(u,obj);
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
	}
	
	public static <T,U> Object newInstance(BaseDomain<T,U> t){
		//T obj=new T();
		return t;
		
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

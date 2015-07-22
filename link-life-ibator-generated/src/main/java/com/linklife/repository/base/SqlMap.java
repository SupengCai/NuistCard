/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.linklife.repository.base;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * SqlMap.java 映射表属性
 * 
 * <pre>
 * 为表添加ibatis sqlmapID自定义注解
 * </pre>
 * 
 * @author yangxin
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface SqlMap {

    public String Name();

    @SuppressWarnings("rawtypes")
    public Class Class();
}

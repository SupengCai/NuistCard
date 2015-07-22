package com.linklife.domain.model;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.NuistSuggest;
import com.linklife.domain.ibator.NuistSuggestExample;

/**
 * <p>
 * NuistSuggestModel.java
 * </p>
 * 
 * <pre>
 * 南信一卡通用户反馈表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings( "rawtypes" )
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class NuistSuggestModel extends NuistSuggest<NuistSuggest, NuistSuggestExample> {

}

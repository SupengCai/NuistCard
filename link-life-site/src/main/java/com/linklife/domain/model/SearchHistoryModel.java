package com.linklife.domain.model;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.ibator.SearchHistory;
import com.linklife.domain.ibator.SearchHistoryExample;
import com.linklife.web.base.utils.ContextUtils;

/**
 * <p>
 * SearchHistoryModel.java
 * </p>
 * 
 * <pre>
 * 用户搜索表封装类
 * </pre>
 * 
 * @author caisupeng
 */
@SuppressWarnings("rawtypes")
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = false)
public class SearchHistoryModel  extends SearchHistory<SearchHistory, SearchHistoryExample>{

	/**
     * 填充用户搜索相关信息
     *
     * @param SearchHistoryModel
     * @return
     */
	public void fillSearchInfo() {
		if (StringUtils.isEmpty(getCity()))
			setCity("南京市");
		setEmail(ContextUtils.getCurrentUserEmail());
		setSearchIp(ContextUtils.getCurrentUserIp());
		setTime(new Date());
	}
	 

}

package com.linklife.domain;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.model.LifeTrackModel;
import com.linklife.domain.model.SearchHistoryModel;

/**
 * <p>
 * ActionLog.java
 * </p>
 * 
 * <pre>
 * 用户操作记录领域封装类
 * </pre>
 * 
 * @author caisupeng
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = false)
public class ActionLog {

	 /** 用户搜索记录相关业务类 */
	@Autowired
	private SearchHistoryModel searchHistoryModel;
	
	 /** 用户生活轨迹相关业务类 */
	@Autowired
	private LifeTrackModel lifeTrackModel;

	public SearchHistoryModel getSearchHistoryModel() {
		return searchHistoryModel;
	}

	public void setSearchHistoryModel(SearchHistoryModel searchHistoryModel) {
		this.searchHistoryModel = searchHistoryModel;
	}

	public LifeTrackModel getLifeTrackModel() {
		return lifeTrackModel;
	}

	public void setLifeTrackModel(LifeTrackModel lifeTrackModel) {
		this.lifeTrackModel = lifeTrackModel;
	}

}

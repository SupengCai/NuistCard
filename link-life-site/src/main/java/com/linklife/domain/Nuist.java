package com.linklife.domain;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.linklife.domain.model.NuistClassModel;
import com.linklife.domain.model.NuistCourseModel;
import com.linklife.domain.model.NuistInfoModel;
import com.linklife.domain.model.NuistLoginHistoryModel;
import com.linklife.domain.model.NuistLoginModel;
import com.linklife.domain.model.NuistRechargeHistoryModel;
import com.linklife.domain.model.NuistScoreHistoryModel;
import com.linklife.domain.model.NuistShopModel;
import com.linklife.domain.model.NuistSuggestModel;
import com.linklife.domain.model.NuistTradeModel;

/**
 * <p>
 * Nuist.java
 * </p>
 * 
 * <pre>
 * 南信大一卡通数据封装
 * </pre>
 * 
 * @author caisupeng
 */
@Component
@Scope( BeanDefinition.SCOPE_PROTOTYPE )
@Configurable( autowire = Autowire.BY_TYPE, dependencyCheck = false )
public class Nuist {

	/** 用户登录相关业务类 */
	@Autowired
	private NuistLoginModel nuistLoginModel;

	/** 用户信息相关业务类 */
	@Autowired
	private NuistInfoModel nuistInfoModel;

	/** 用户登录记录相关业务类 */
	@Autowired
	private NuistLoginHistoryModel nuistLoginHistoryModel;

	/** 用户交易记录相关业务类 */
	@Autowired
	private NuistTradeModel NuistTradeModel;

	/** 用户登录反馈相关业务类 */
	@Autowired
	private NuistSuggestModel nuistsuggestmodel;

	/** 用户充值记录关业务类 */
	@Autowired
	private NuistRechargeHistoryModel nuistRechargeHistoryModel;

	/** 用户成绩记录相关业务类 */
	@Autowired
	private NuistScoreHistoryModel nuistScoreHistoryModel;

	/** 商铺相关业务类 */
	@Autowired
	private NuistShopModel nuistShopModel;

	/** 课程相关业务类 */
	@Autowired
	private NuistCourseModel nuistCourseModel;

	/** 专业相关业务类 */
	@Autowired
	private NuistClassModel nuistClassModel;


	public NuistLoginModel getNuistLoginModel() {

		return nuistLoginModel;
	}


	public void setNuistLoginModel( NuistLoginModel nuistLoginModel ) {

		this.nuistLoginModel = nuistLoginModel;
	}


	public NuistInfoModel getNuistInfoModel() {

		return nuistInfoModel;
	}


	public void setNuistInfoModel( NuistInfoModel nuistInfoModel ) {

		this.nuistInfoModel = nuistInfoModel;
	}


	public NuistLoginHistoryModel getNuistLoginHistoryModel() {

		return nuistLoginHistoryModel;
	}


	public void setNuistLoginHistoryModel( NuistLoginHistoryModel nuistLoginHistoryModel ) {

		this.nuistLoginHistoryModel = nuistLoginHistoryModel;
	}


	public NuistSuggestModel getNuistsuggestmodel() {

		return nuistsuggestmodel;
	}


	public void setNuistsuggestmodel( NuistSuggestModel nuistsuggestmodel ) {

		this.nuistsuggestmodel = nuistsuggestmodel;
	}


	public NuistTradeModel getNuistTradeModel() {

		return NuistTradeModel;
	}


	public void setNuistTradeModel( NuistTradeModel nuistTradeModel ) {

		NuistTradeModel = nuistTradeModel;
	}


	public NuistRechargeHistoryModel getNuistRechargeHistoryModel() {

		return nuistRechargeHistoryModel;
	}


	public void setNuistRechargeHistoryModel( NuistRechargeHistoryModel nuistRechargeHistoryModel ) {

		this.nuistRechargeHistoryModel = nuistRechargeHistoryModel;
	}


	public NuistScoreHistoryModel getNuistScoreHistoryModel() {

		return nuistScoreHistoryModel;
	}


	public void setNuistScoreHistoryModel( NuistScoreHistoryModel nuistScoreHistoryModel ) {

		this.nuistScoreHistoryModel = nuistScoreHistoryModel;
	}


	public NuistShopModel getNuistShopModel() {

		return nuistShopModel;
	}


	public void setNuistShopModel( NuistShopModel nuistShopModel ) {

		this.nuistShopModel = nuistShopModel;
	}


	public NuistCourseModel getNuistCourseModel() {

		return nuistCourseModel;
	}


	public void setNuistCourseModel( NuistCourseModel nuistCourseModel ) {

		this.nuistCourseModel = nuistCourseModel;
	}


	public NuistClassModel getNuistClassModel() {

		return nuistClassModel;
	}


	public void setNuistClassModel( NuistClassModel nuistClassModel ) {

		this.nuistClassModel = nuistClassModel;
	}

}

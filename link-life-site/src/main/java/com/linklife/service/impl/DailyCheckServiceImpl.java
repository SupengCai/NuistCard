package com.linklife.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linklife.domain.DailyCheck;
import com.linklife.domain.ibator.DailyCheckAccount;
import com.linklife.domain.model.DailyCheckAccountModel;
import com.linklife.domain.model.DailyCheckForm;

/**
 * <p>
 * AccountServiceImpl.java
 * </p>
 * 
 * <pre>
 * 南信大一卡通相关业务接口的实现类
 * </pre>
 * 
 * @author caisupeng
 */
@Service
public class DailyCheckServiceImpl {

	/** 用户领域类 */
	@Autowired
	private DailyCheck dailyCheck;


	@SuppressWarnings( "rawtypes" )
	public boolean register( DailyCheckAccount model ) {

		DailyCheckAccountModel dailyCheckAccountModel = dailyCheck.getDailyCheckAccountModel();
		dailyCheckAccountModel.setName( model.getName() );
		dailyCheckAccountModel.setAccount( model.getAccount() );
		dailyCheckAccountModel.setPassword( model.getPassword() );
		dailyCheckAccountModel.setState( 0 );
		try {
			dailyCheckAccountModel.insert();
		} catch( Exception e ) {
			System.out.println( e );
			return false;
		}
		return true;
	}


	@SuppressWarnings( "rawtypes" )
	public boolean signin( DailyCheckForm model ) {

		DailyCheckAccountModel dailyCheckAccountModel = dailyCheck.getDailyCheckAccountModel();
		dailyCheckAccountModel.setAccount( model.getAccount() );
		DailyCheckAccount modelAccount = dailyCheckAccountModel.selectByAccount();
		if( null != modelAccount && modelAccount.getPassword().equals( model.getPassword() ) )
			return true;
		else
			return false;

	}
}
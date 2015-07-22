/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package com.linklife.trigger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.linklife.service.impl.NuistServiceImpl;
import com.linklife.service.impl.NuistServiceImpl.AutoRecordType;

/**
 * <p>
 * AutoRecordHistoryDataTrigger.java
 * </p>
 * 
 * <pre>
 * 定时更新用户历史交易数据
 * </pre>
 * 
 * @author caisupeng
 */
@Service
public class AutoRecordHistoryDataTrigger {

	/** 南信大一卡通相关业务类 */
	@Autowired
	private NuistServiceImpl nuistServiceImpl;

	protected static Log log = LogFactory.getLog( "AutoRecordHistoryDataTrigger" );


	public void execute() {

		nuistServiceImpl.autoRecordScore( AutoRecordType.NewUser );
		nuistServiceImpl.autoRecordData( AutoRecordType.NewUser );
		nuistServiceImpl.updateStatisticData();

	}

}

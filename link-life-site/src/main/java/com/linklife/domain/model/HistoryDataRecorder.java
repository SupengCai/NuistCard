package com.linklife.domain.model;

import java.util.Date;

import com.linklife.domain.model.NuistHistoryModel.HistoryType;

/**
 * <p>
 * HistoryDataRecorder.java
 * </p>
 * 
 * <pre>
 * 用户消费历史数据暂存
 * </pre>
 * 
 * @author caisupeng
 */
public class HistoryDataRecorder {

	/** 历史数据生成时间 */
	private Date createDate;
	/** 计算后的历史数据 0:Week数据 1:学期数据2:All数据 */
	public AmountHistory amountHistorys[];


	public HistoryDataRecorder() {

		this.createDate = new Date();
		this.amountHistorys = new AmountHistory[ 3 ];
		this.amountHistorys[ 0 ] = new AmountHistory( HistoryType.WEEK );
		this.amountHistorys[ 1 ] = new AmountHistory( HistoryType.TERM );
		this.amountHistorys[ 2 ] = new AmountHistory( HistoryType.ALL );
	}


	public Date getCreateDate() {

		return createDate;
	}


	public void setCreateDate( Date createDate ) {

		this.createDate = createDate;
	}

}

package com.linklife.domain.model;

import java.security.cert.CertStoreSpi;
import java.util.HashMap;
import java.util.List;

import com.linklife.domain.model.NuistHistoryModel.HistoryType;
import com.linklife.web.httpapi.NuistCardAPI;

/**
 * <p>
 * AmountHistory.java
 * </p>
 * 
 * <pre>
 * 根据日期统计一卡通消费金额
 * </pre>
 * 
 * @author caisupeng
 */
public class AmountHistory {

	private int historyType;// 记录类型：1 周记录

	public double consumeAmounts[];// 单位消费金额，总和=consumAmount
	public int consumeTimes[];// 单位消费次数，总和=consumTime
	public double consumeAmount;// 总消费金额数
	public int consumeTime;// 总消费次数
	public double rechargeAmounts[];// 单位充值金额
	public int rechargeTimes[];// 单位充值次数
	public double rechargeAmount;// 总充值金额数
	public int rechargeTime;// 总充值次数
	public double averageConsume;
	public String categories[];
	// 区域
	public double areaConsumeAmounts[];// 区域消费金额，总和=consumAmount 0:东院 1:中院 2:西院
	public double averageAreaConsume[];// 区域日均消费金额
	public double averageAreaConsumeTimes[];// 区域日均消费次数
	public int areaConsumeTimes[];// 区域消费次数，总和=consumTime 0:东院 1:中院 2:西院
	// 商铺
	public HashMap<String, AreaRankModel> eastShopMap;
	public HashMap<String, AreaRankModel> centerShopMap;
	public HashMap<String, AreaRankModel> westShopMap;
	public List<AreaRankModel> centerShopList;

	public AmountHistory( HistoryType type ) {

		// 区域数据
		this.consumeAmounts = new double[] { 0, 0, 0 };
		this.consumeTimes = new int[] { 0, 0, 0 };
		this.averageAreaConsume = new double[] { 0, 0, 0 };
		this.areaConsumeAmounts = new double[] { 0, 0, 0 };
		this.areaConsumeTimes = new int[] { 0, 0, 0 };
		this.averageAreaConsumeTimes = new double[] { 0, 0, 0 };
		this.eastShopMap = new HashMap<String, AreaRankModel>();
		this.centerShopMap = new HashMap<String, AreaRankModel>();
		this.westShopMap = new HashMap<String, AreaRankModel>();

		// 金额数据
		switch( type ) {
		case ALL:
			this.consumeTimes = new int[] { 0, 0, 0, 0, 0 };
			this.consumeAmounts = new double[] { 0, 0, 0, 0, 0 };
			this.rechargeAmounts = new double[] { 0, 0, 0, 0, 0 };
			this.rechargeTimes = new int[] { 0, 0, 0, 0, 0 };
			this.categories = new String[ 5 ];
			break;
		case TERM:
			this.consumeTimes = new int[] { 0, 0, 0, 0, 0, 0 };
			this.consumeAmounts = new double[] { 0, 0, 0, 0, 0, 0 };
			this.rechargeAmounts = new double[] { 0, 0, 0, 0, 0, 0 };
			this.rechargeTimes = new int[] { 0, 0, 0, 0, 0, 0 };
			break;
		case WEEK:
			this.consumeTimes = new int[] { 0, 0, 0, 0, 0, 0, 0 };
			this.consumeAmounts = new double[] { 0, 0, 0, 0, 0, 0, 0 };
			this.rechargeAmounts = new double[] { 0, 0, 0, 0, 0, 0, 0 };
			this.rechargeTimes = new int[] { 0, 0, 0, 0, 0, 0, 0 };
			break;
		default:
			break;
		}
	}


	public void updateShopMap( String area, String shopName, double amount ) {
		
		AreaRankModel areaRankModel = null;
		switch( area ) {
		case NuistCardAPI.NUIST_CARD_TRADE_AREA_EAST:
			if( eastShopMap.containsKey( shopName ) )
				areaRankModel = eastShopMap.get( shopName );
			else
				areaRankModel = new AreaRankModel();
			areaRankModel.setAmounts( areaRankModel.getAmounts() + amount );
			areaRankModel.setTimes( areaRankModel.getTimes() + 1 );
			eastShopMap.put( shopName, areaRankModel );
			break;
		case NuistCardAPI.NUIST_CARD_TRADE_AREA_CENTRAL:
			if( centerShopMap.containsKey( shopName ) )
				areaRankModel = centerShopMap.get( shopName );
			else
				areaRankModel = new AreaRankModel();
			areaRankModel.setAmounts( areaRankModel.getAmounts() + amount );
			areaRankModel.setTimes( areaRankModel.getTimes() + 1 );
			centerShopMap.put( shopName, areaRankModel );
			break;
		case NuistCardAPI.NUIST_CARD_TRADE_AREA_WEST:
			if( westShopMap.containsKey( shopName ) )
				areaRankModel = westShopMap.get( shopName );
			else
				areaRankModel = new AreaRankModel();
			areaRankModel.setAmounts( areaRankModel.getAmounts() + amount );
			areaRankModel.setTimes( areaRankModel.getTimes() + 1 );
			westShopMap.put( shopName, areaRankModel );
			break;
		default:
			break;
		}
	}


	public int getHistoryType() {

		return historyType;
	}


	public void setHistoryType( int historyType ) {

		this.historyType = historyType;
	}

}

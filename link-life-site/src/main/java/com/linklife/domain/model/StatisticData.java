package com.linklife.domain.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatisticData {

	public static int people;
	public static int dataSize;
	public static List<HashMap<String, Double>> amountRank;
	public static List<HashMap<String, Integer>> timeRank;
	public static List<AreaRankModel> eastRank;
	public static List<AreaRankModel> centerRank;
	public static List<AreaRankModel> westRank;
	public static List<AreaRankModel> eastAmountRank;
	public static List<AreaRankModel> centerAmountRank;
	public static List<AreaRankModel> westAmountRank;
	public static List<AreaRankModel> eastPopularityRank;
	public static List<AreaRankModel> centerPopularityRank;
	public static List<AreaRankModel> westPopularityRank;
	public static List<AreaRankModel> eastPerConsumeRank;
	public static List<AreaRankModel> centerPerConsumeRank;
	public static List<AreaRankModel> westPerConsumeRank;
	public static List<AreaRankModel> eastCostMostRank;
	public static List<AreaRankModel> centerCostMostRank;
	public static List<AreaRankModel> westCostMostRank;

	static {
		people = 0;
		dataSize = 0;
		amountRank = new ArrayList<HashMap<String, Double>>();
		timeRank = new ArrayList<HashMap<String, Integer>>();
	}

}

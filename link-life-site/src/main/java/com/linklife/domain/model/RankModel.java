package com.linklife.domain.model;

public class RankModel {

	private String shopName;
	private double amounts;
	private int times;
	private double average;
	private int people;
	private double perbill;


	public String getShopName() {

		return shopName;
	}


	public void setShopName( String shopName ) {

		this.shopName = shopName;
	}


	public double getAmounts() {

		return amounts;
	}


	public void setAmounts( double amounts ) {

		this.amounts = amounts;
	}


	public int getTimes() {

		return times;
	}


	public void setTimes( int times ) {

		this.times = times;
	}


	public double getAverage() {

		return average;
	}


	public void setAverage( double average ) {

		this.average = average;
	}


	public int getPeople() {

		return people;
	}


	public void setPeople( int people ) {

		this.people = people;
	}


	public double getPerbill() {

		return perbill;
	}


	public void setPerbill( double perbill ) {

		this.perbill = perbill;
	}

}

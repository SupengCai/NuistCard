package com.linklife.domain.ibator;

import com.linklife.domain.base.BaseDomain;
import com.linklife.repository.base.SqlMap;

@SqlMap( Name = "nuist_shop", Class = NuistShop.class )
public class NuistShop<T, U> extends BaseDomain<T, U> {

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_shop.shop_id
	 *
	 * @ibatorgenerated Sun Jul 12 13:22:20 CST 2015
	 */
	private Integer shopId;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_shop.shop_name
	 *
	 * @ibatorgenerated Sun Jul 12 13:22:20 CST 2015
	 */
	private String shopName;

	/**
	 * This field was generated by Apache iBATIS ibator. This field corresponds
	 * to the database column nuist_shop.shop_area
	 *
	 * @ibatorgenerated Sun Jul 12 13:22:20 CST 2015
	 */
	private Integer shopArea;


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_shop.shop_id
	 *
	 * @return the value of nuist_shop.shop_id
	 *
	 * @ibatorgenerated Sun Jul 12 13:22:20 CST 2015
	 */
	public Integer getShopId() {

		return shopId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_shop.shop_id
	 *
	 * @param shopId
	 *            the value for nuist_shop.shop_id
	 *
	 * @ibatorgenerated Sun Jul 12 13:22:20 CST 2015
	 */
	public void setShopId( Integer shopId ) {

		this.shopId = shopId;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_shop.shop_name
	 *
	 * @return the value of nuist_shop.shop_name
	 *
	 * @ibatorgenerated Sun Jul 12 13:22:20 CST 2015
	 */
	public String getShopName() {

		return shopName;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_shop.shop_name
	 *
	 * @param shopName
	 *            the value for nuist_shop.shop_name
	 *
	 * @ibatorgenerated Sun Jul 12 13:22:20 CST 2015
	 */
	public void setShopName( String shopName ) {

		this.shopName = shopName;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method returns
	 * the value of the database column nuist_shop.shop_area
	 *
	 * @return the value of nuist_shop.shop_area
	 *
	 * @ibatorgenerated Sun Jul 12 13:22:20 CST 2015
	 */
	public Integer getShopArea() {

		return shopArea;
	}


	/**
	 * This method was generated by Apache iBATIS ibator. This method sets the
	 * value of the database column nuist_shop.shop_area
	 *
	 * @param shopArea
	 *            the value for nuist_shop.shop_area
	 *
	 * @ibatorgenerated Sun Jul 12 13:22:20 CST 2015
	 */
	public void setShopArea( Integer shopArea ) {

		this.shopArea = shopArea;
	}
}
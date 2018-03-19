package com.wanma.model;

import java.util.List;

/**
 *  确认订单
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-24 上午11:28:06 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class AffirmOrderList {

	private String prodBrand;//店铺ID
	private String prodBrandName;//店铺名称

	private List<OrdersProduct> orders;

	
	public String getProdBrand() {
		return prodBrand;
	}

	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}

	public String getProdBrandName() {
		return prodBrandName;
	}

	public void setProdBrandName(String prodBrandName) {
		this.prodBrandName = prodBrandName;
	}

	public List<OrdersProduct> getOrders() {
		return orders;
	}

	public void setOrders(List<OrdersProduct> orders) {
		this.orders = orders;
	}

	
	
}

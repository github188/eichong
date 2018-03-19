package com.wanma.model;

import java.util.List;

/**
 *  我的订单
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-19 下午05:02:39 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class MyOrder {

	private String orderId;//订单ID
	private String orderState;//1-待支付 2-待安装 3-已完成 
	private String userId;//用户ID
	private String orderShopName;//订单店铺名称
	private String orderType;//1-购物订单 2-安装订单
	private String commodityTotal;//商品总数
	private String commodityTotalPrice;//商品合计价格
	
	private String commodityTime;//订单时间
	
	private List<Orders> orders;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderShopName() {
		return orderShopName;
	}

	public void setOrderShopName(String orderShopName) {
		this.orderShopName = orderShopName;
	}

	public String getCommodityTotal() {
		return commodityTotal;
	}

	public void setCommodityTotal(String commodityTotal) {
		this.commodityTotal = commodityTotal;
	}

	public String getCommodityTotalPrice() {
		return commodityTotalPrice;
	}

	public void setCommodityTotalPrice(String commodityTotalPrice) {
		this.commodityTotalPrice = commodityTotalPrice;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommodityTime() {
		return commodityTime;
	}

	public void setCommodityTime(String commodityTime) {
		this.commodityTime = commodityTime;
	}
	
	
}

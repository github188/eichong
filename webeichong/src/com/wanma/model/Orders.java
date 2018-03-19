package com.wanma.model;

public class Orders {

	private String commodityId;
	private String commodityImage;//商品图片
	private String commodityName;//商品名称
	private String commodityProperty;//商品属性
	private String commodityProductPrice;//产品价格
	private String commodityCount;//产品数量
	
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getCommodityImage() {
		return commodityImage;
	}
	public void setCommodityImage(String commodityImage) {
		this.commodityImage = commodityImage;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getCommodityProperty() {
		return commodityProperty;
	}
	public void setCommodityProperty(String commodityProperty) {
		this.commodityProperty = commodityProperty;
	}
	public String getCommodityProductPrice() {
		return commodityProductPrice;
	}
	public void setCommodityProductPrice(String commodityProductPrice) {
		this.commodityProductPrice = commodityProductPrice;
	}
	public String getCommodityCount() {
		return commodityCount;
	}
	public void setCommodityCount(String commodityCount) {
		this.commodityCount = commodityCount;
	}
}

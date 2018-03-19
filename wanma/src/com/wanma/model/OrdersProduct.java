package com.wanma.model;

public class OrdersProduct {

	private String shcaProductid;//产品ID
	private String prodProductimage;//产品图片
	private String prodProductName;//产品名称
	private String shcaCount;//产品数量
	private String prodMarketprice;// 市场价格
	private String prodDiscountprice;//折扣价格
	
	
	public String getShcaProductid() {
		return shcaProductid;
	}
	public void setShcaProductid(String shcaProductid) {
		this.shcaProductid = shcaProductid;
	}
	public String getProdProductimage() {
		return prodProductimage;
	}
	public void setProdProductimage(String prodProductimage) {
		this.prodProductimage = prodProductimage;
	}
	public String getProdProductName() {
		return prodProductName;
	}
	public void setProdProductName(String prodProductName) {
		this.prodProductName = prodProductName;
	}
	public String getShcaCount() {
		return shcaCount;
	}
	public void setShcaCount(String shcaCount) {
		this.shcaCount = shcaCount;
	}
	public String getProdMarketprice() {
		return prodMarketprice;
	}
	public void setProdMarketprice(String prodMarketprice) {
		this.prodMarketprice = prodMarketprice;
	}
	public String getProdDiscountprice() {
		return prodDiscountprice;
	}
	public void setProdDiscountprice(String prodDiscountprice) {
		this.prodDiscountprice = prodDiscountprice;
	}
	
	
}

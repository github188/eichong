package com.wanma.model;

import java.util.List;
import java.util.Map;

/**
 * @Description: 订单中商品详情
 * @author songjf
 * @createTime：2015-3-24 下午04:20:28
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
/**
 * 听管理
 * 
 * @Description:
 * @author songjf
 * @createTime：2015-3-24 下午04:31:32
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public class OrderInfoList {

	private String productId; // 商品id
	private String productName;// 商品名称
	private String productImage="";// 商品图片
	private Integer productNum; // 商品数量
	private Double productPrice;// 商品价格

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

}

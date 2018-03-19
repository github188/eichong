package com.wanma.model;

import java.util.List;
import java.util.Map;

import com.pub.model.Entity;

/**
 * 
 * tbl_OrderDetail表
 * 
 * @author songjf
 * 
 */
/**
 * 听管理
  * @Description:
  * @author songjf 
  * @createTime：2015-3-27 上午10:04:38 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class TblOrderdetail extends Entity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4620963489579322777L;
	private java.lang.Integer pkOrderdetail; // 主键
	private java.lang.Integer ordeParentid; // 父订单Id
	private java.lang.Integer ordeProductid; // 产品Id
	private java.lang.String ordeProductname; // 产品名称
	private java.math.BigDecimal ordePrice; // 单价
	private java.lang.Integer ordeQuantity; // 数量
	private java.math.BigDecimal ordeTotalamount; // 总价
	private java.util.Date ordeCreatedate; // 创建时间
	private java.util.Date ordeUpdatedate; // 修改时间

	// 以下字段不与数据库对应
	private java.lang.String prodProductCode; // 商品编号
	private java.util.Date shopdate; // 购买时间
	private java.lang.String prodProductimage = "";// 商品图片
	private java.lang.Integer prodBrand; // 品牌id
	private java.lang.String prodBrandName; // 品牌名称
	private List<Map<String, Object>> productList;
	private java.lang.String prodUser;//商品所属用户

	/**
	 * 获取主键属性
	 * 
	 * @return pkOrderdetail
	 */
	public java.lang.Integer getPkOrderdetail() {
		return pkOrderdetail;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkOrderdetail
	 */
	public void setPkOrderdetail(java.lang.Integer pkOrderdetail) {
		this.pkOrderdetail = pkOrderdetail;
	}

	/**
	 * 获取父订单Id属性
	 * 
	 * @return ordeParentid
	 */
	public java.lang.Integer getOrdeParentid() {
		return ordeParentid;
	}

	/**
	 * 设置父订单Id属性
	 * 
	 * @param ordeParentid
	 */
	public void setOrdeParentid(java.lang.Integer ordeParentid) {
		this.ordeParentid = ordeParentid;
	}

	/**
	 * 获取产品Id属性
	 * 
	 * @return ordeProductid
	 */
	public java.lang.Integer getOrdeProductid() {
		return ordeProductid;
	}

	/**
	 * 设置产品Id属性
	 * 
	 * @param ordeProductid
	 */
	public void setOrdeProductid(java.lang.Integer ordeProductid) {
		this.ordeProductid = ordeProductid;
	}

	/**
	 * 获取产品名称属性
	 * 
	 * @return ordeProductname
	 */
	public java.lang.String getOrdeProductname() {
		return ordeProductname;
	}

	/**
	 * 设置产品名称属性
	 * 
	 * @param ordeProductname
	 */
	public void setOrdeProductname(java.lang.String ordeProductname) {
		this.ordeProductname = ordeProductname;
	}

	/**
	 * 获取单价属性
	 * 
	 * @return ordePrice
	 */
	public java.math.BigDecimal getOrdePrice() {
		return ordePrice;
	}

	/**
	 * 设置单价属性
	 * 
	 * @param ordePrice
	 */
	public void setOrdePrice(java.math.BigDecimal ordePrice) {
		this.ordePrice = ordePrice;
	}

	/**
	 * 获取数量属性
	 * 
	 * @return ordeQuantity
	 */
	public java.lang.Integer getOrdeQuantity() {
		return ordeQuantity;
	}

	/**
	 * 设置数量属性
	 * 
	 * @param ordeQuantity
	 */
	public void setOrdeQuantity(java.lang.Integer ordeQuantity) {
		this.ordeQuantity = ordeQuantity;
	}

	/**
	 * 获取总价属性
	 * 
	 * @return ordeTotalamount
	 */
	public java.math.BigDecimal getOrdeTotalamount() {
		return ordeTotalamount;
	}

	/**
	 * 设置总价属性
	 * 
	 * @param ordeTotalamount
	 */
	public void setOrdeTotalamount(java.math.BigDecimal ordeTotalamount) {
		this.ordeTotalamount = ordeTotalamount;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return ordeCreatedate
	 */
	public java.util.Date getOrdeCreatedate() {
		return ordeCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param ordeCreatedate
	 */
	public void setOrdeCreatedate(java.util.Date ordeCreatedate) {
		this.ordeCreatedate = ordeCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return ordeUpdatedate
	 */
	public java.util.Date getOrdeUpdatedate() {
		return ordeUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param ordeUpdatedate
	 */
	public void setOrdeUpdatedate(java.util.Date ordeUpdatedate) {
		this.ordeUpdatedate = ordeUpdatedate;
	}

	/**
	 * 获取商品编号属性
	 * 
	 * @param prodProductCode
	 */
	public java.lang.String getProdProductCode() {
		return prodProductCode;
	}

	/**
	 * 设置商品编号属性
	 * 
	 * @param prodProductCode
	 */
	public void setProdProductCode(java.lang.String prodProductCode) {
		this.prodProductCode = prodProductCode;
	}

	/**
	 * 获取购买时间属性
	 * 
	 * @param shopdate
	 */
	public java.util.Date getShopdate() {
		return shopdate;
	}

	/**
	 * 设置购买时间属性
	 * 
	 * @param shopdate
	 */
	public void setShopdate(java.util.Date shopdate) {
		this.shopdate = shopdate;
	}

	/**
	 * 获取品牌id属性
	 * 
	 * @param prodBrand
	 */
	public java.lang.Integer getProdBrand() {
		return prodBrand;
	}

	/**
	 * 设置品牌id属性
	 * 
	 * @param prodBrand
	 */
	public void setProdBrand(java.lang.Integer prodBrand) {
		this.prodBrand = prodBrand;
	}

	/**
	 * 获取商品图片属性
	 * 
	 * @param prodProductimage
	 */
	public java.lang.String getProdProductimage() {
		return prodProductimage;
	}

	/**
	 * 设置商品图片属性
	 * 
	 * @param prodProductimage
	 */
	public void setProdProductimage(java.lang.String prodProductimage) {
		this.prodProductimage = prodProductimage;
	}

	/**
	 * 获取品牌属性
	 * 
	 * @param prodBrandName
	 */
	public java.lang.String getProdBrandName() {
		return prodBrandName;
	}

	/**
	 * 设置品牌属性
	 * 
	 * @param prodBrandName
	 */
	public void setProdBrandName(java.lang.String prodBrandName) {
		this.prodBrandName = prodBrandName;
	}

	public List<Map<String, Object>> getProductList() {
		return productList;
	}

	public void setProductList(List<Map<String, Object>> productList) {
		this.productList = productList;
	}
	
	public java.lang.String getProdUser() {
		return prodUser;
	}

	public void setProdUser(java.lang.String prodUser) {
		this.prodUser = prodUser;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblOrderdetail");
		sb.append("{pkOrderdetail=").append(pkOrderdetail);
		sb.append(", ordeParentid=").append(ordeParentid);
		sb.append(", ordeProductid=").append(ordeProductid);
		sb.append(", ordeProductname=").append(ordeProductname);
		sb.append(", ordePrice=").append(ordePrice);
		sb.append(", ordeQuantity=").append(ordeQuantity);
		sb.append(", ordeTotalamount=").append(ordeTotalamount);
		sb.append(", ordeCreatedate=").append(ordeCreatedate);
		sb.append(", ordeUpdatedate=").append(ordeUpdatedate);
		sb.append('}');
		return sb.toString();
	}
}
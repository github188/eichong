package com.wanma.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 
 * tbl_ShoppingCart表
 * 
 * @author songjf
 * 
 */
/**
 * 听管理
 * 
 * @Description:
 * @author songjf
 * @createTime：2015-3-21 下午01:41:14
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public class TblShoppingcart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1350428411401942259L;
	private java.lang.Integer pkShoppingcart; // 主键
	private java.lang.Integer shcaUserid; // 用户Id
	private java.lang.Integer shcaProductid; // 产品Id
	private java.lang.Integer shcaCount; // 数量
	private java.util.Date shcaCreatedate; // 数量
	private java.util.Date shcaUpdatedate; // 修改时间

	// 以下字段不与数据库对应
	private java.lang.Integer prodBrand; // 品牌id
	private java.lang.String prodBrandName; // 品牌名称
	private List<Map<String, Object>> productList; // 商品list
	private java.lang.String prodProductName; // 商品名称
	private java.math.BigDecimal prodDiscountprice; // 成本价格*折扣率
	private java.math.BigDecimal prodMarketprice; // 市场价格
	private java.lang.Integer prodStockquantity; // 库存数量

	// private java.math.BigDecimal prodMarketprice; // 市场价格
	// private java.math.BigDecimal prodDiscountprice; // 折扣价格
	// private java.lang.String prodProductimage; // 商品图片
	// private java.lang.String prCaName; // 分类名称
	// private java.math.BigDecimal totalPrice;// 总价格

	/**
	 * 获取主键属性
	 * 
	 * @return pkShoppingcart
	 */
	public java.lang.Integer getPkShoppingcart() {
		return pkShoppingcart;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkShoppingcart
	 */
	public void setPkShoppingcart(java.lang.Integer pkShoppingcart) {
		this.pkShoppingcart = pkShoppingcart;
	}

	/**
	 * 获取用户Id属性
	 * 
	 * @return shcaUserid
	 */
	public java.lang.Integer getShcaUserid() {
		return shcaUserid;
	}

	/**
	 * 设置用户Id属性
	 * 
	 * @param shcaUserid
	 */
	public void setShcaUserid(java.lang.Integer shcaUserid) {
		this.shcaUserid = shcaUserid;
	}

	/**
	 * 获取产品Id属性
	 * 
	 * @return shcaProductid
	 */
	public java.lang.Integer getShcaProductid() {
		return shcaProductid;
	}

	/**
	 * 设置产品Id属性
	 * 
	 * @param shcaProductid
	 */
	public void setShcaProductid(java.lang.Integer shcaProductid) {
		this.shcaProductid = shcaProductid;
	}

	/**
	 * 获取数量属性
	 * 
	 * @return shcaCount
	 */
	public java.lang.Integer getShcaCount() {
		return shcaCount;
	}

	/**
	 * 设置数量属性
	 * 
	 * @param shcaCount
	 */
	public void setShcaCount(java.lang.Integer shcaCount) {
		this.shcaCount = shcaCount;
	}

	/**
	 * 获取数量属性
	 * 
	 * @return shcaCreatedate
	 */
	public java.util.Date getShcaCreatedate() {
		return shcaCreatedate;
	}

	/**
	 * 设置数量属性
	 * 
	 * @param shcaCreatedate
	 */
	public void setShcaCreatedate(java.util.Date shcaCreatedate) {
		this.shcaCreatedate = shcaCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return shcaUpdatedate
	 */
	public java.util.Date getShcaUpdatedate() {
		return shcaUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param shcaUpdatedate
	 */
	public void setShcaUpdatedate(java.util.Date shcaUpdatedate) {
		this.shcaUpdatedate = shcaUpdatedate;
	}

	// public java.lang.String getProdProductName() {
	// return prodProductName;
	// }
	//
	// public void setProdProductName(java.lang.String prodProductName) {
	// this.prodProductName = prodProductName;
	// }
	//
	// public java.math.BigDecimal getProdMarketprice() {
	// return prodMarketprice;
	// }
	//
	// public void setProdMarketprice(java.math.BigDecimal prodMarketprice) {
	// this.prodMarketprice = prodMarketprice;
	// }
	//
	// public java.math.BigDecimal getProdDiscountprice() {
	// return prodDiscountprice;
	// }
	//
	// public void setProdDiscountprice(java.math.BigDecimal prodDiscountprice)
	// {
	// this.prodDiscountprice = prodDiscountprice;
	// }
	//
	// public java.lang.String getProdProductimage() {
	// return prodProductimage;
	// }
	//
	// public void setProdProductimage(java.lang.String prodProductimage) {
	// this.prodProductimage = prodProductimage;
	// }
	//
	// public java.lang.String getPrCaName() {
	// return prCaName;
	// }
	//
	// public void setPrCaName(java.lang.String prCaName) {
	// this.prCaName = prCaName;
	// }
	//
	// public java.math.BigDecimal getTotalPrice() {
	// return totalPrice;
	// }
	//
	// public void setTotalPrice(java.math.BigDecimal totalPrice) {
	// this.totalPrice = totalPrice;
	// }

	public java.lang.Integer getProdBrand() {
		return prodBrand;
	}

	public void setProdBrand(java.lang.Integer prodBrand) {
		this.prodBrand = prodBrand;
	}

	public java.lang.String getProdBrandName() {
		return prodBrandName;
	}

	public void setProdBrandName(java.lang.String prodBrandName) {
		this.prodBrandName = prodBrandName;
	}

	public List<Map<String, Object>> getProductList() {
		return productList;
	}

	public void setProductList(List<Map<String, Object>> productList) {
		this.productList = productList;
	}

	public java.lang.String getProdProductName() {
		return prodProductName;
	}

	public void setProdProductName(java.lang.String prodProductName) {
		this.prodProductName = prodProductName;
	}

	public java.math.BigDecimal getProdDiscountprice() {
		return prodDiscountprice;
	}

	public void setProdDiscountprice(java.math.BigDecimal prodDiscountprice) {
		this.prodDiscountprice = prodDiscountprice;
	}

	public java.math.BigDecimal getProdMarketprice() {
		return prodMarketprice;
	}

	public void setProdMarketprice(java.math.BigDecimal prodMarketprice) {
		this.prodMarketprice = prodMarketprice;
	}
	
	public java.lang.Integer getProdStockquantity() {
		return prodStockquantity;
	}

	public void setProdStockquantity(java.lang.Integer prodStockquantity) {
		this.prodStockquantity = prodStockquantity;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblShoppingcart");
		sb.append("{pkShoppingcart=").append(pkShoppingcart);
		sb.append(", shcaUserid=").append(shcaUserid);
		sb.append(", shcaProductid=").append(shcaProductid);
		sb.append(", shcaCount=").append(shcaCount);
		sb.append(", shcaCreatedate=").append(shcaCreatedate);
		sb.append(", shcaUpdatedate=").append(shcaUpdatedate);
		sb.append('}');
		return sb.toString();
	}
}
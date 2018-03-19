package com.wanma.model;

import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_Product表
 * 
 * @author songjf
 * 
 */
public class TblProduct extends BasicListAndMutiFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8840297633634841496L;
	private java.lang.Integer pkProduct; //
	private java.lang.String prodProductcode; // 商品编号
	private java.lang.String prodProductname; // 产品名称
	private java.lang.Integer prodCategoryid; // 分类Id(从表tbl_ProductCategory中获取)
	private java.math.BigDecimal prodProductprice; // 成本价格
	private java.math.BigDecimal prodProductdiscount; // 折扣率
	private java.math.BigDecimal prodDiscountprice; // 成本价格*折扣率
	private java.math.BigDecimal prodMarketprice; // 市场价格
	private java.lang.Integer prodStockquantity; // 库存数量
	private java.lang.Integer prodProductlsadd; // 1：待上架 2：上架
	private java.lang.Integer prodSoldquantity; // 已售数量
	private java.lang.Integer prodBrowsenum; // 浏览次数
	private java.lang.Integer prodProducttag; // 1：国家补贴 2：免税置购
	private java.lang.String prodQrcodepic; // 二维码图片
	private java.lang.String prodDetailimage; // 详细图片
	private java.lang.String prodProductimage; // 商品图片
	private java.lang.String prodRemark; // 备注
	private java.lang.String prodParameters; // 基本参数
	private java.lang.String prodArtificialservice; // 人工服务
	private java.util.Date prodCreatedate; // 创建时间
	private java.util.Date prodUpdatedate; // 修改时间
	private java.lang.Integer prodStatus; // 1：删除  2：正常
	private java.lang.String prodFeature;// 产品特色
	private java.lang.Integer prodChargingMode; // 充电模式
	private java.lang.String priceSort;// 价格 1-默认 2-按最优排序
	private java.lang.String soldQuantity;// 销售量排序 1-默认 2-按最优排序
	private java.lang.Integer prodBrand; // 所属品牌
	
	private java.lang.Integer prodPowerInterface; // 接口标准
	private java.lang.Integer prodCarType; // 电动车类型 1纯电动车 2插电式混合动力车
	private java.lang.Integer prodSubsidies; // 政府补贴 1有 2无
	private java.lang.Integer prodBattery; // 电池类型
	private java.math.BigDecimal prodOdometer;//续航里程
	private java.lang.String prodChargingTime;//充电时间
	private java.lang.String prodUser;//所属用户
	
	
	private String prodpicType; 		 //图片类型
	public String getProdpicType() {
		return prodpicType;
	}

	public void setProdpicType(String prodpicType) {
		this.prodpicType = prodpicType;
	}

	//与数据库没有联系
	private String caName;  //分类名称
	private String pkCategory;  //所属类型ID
	
	public String getPkCategory() {
		return pkCategory;
	}

	public void setPkCategory(String pkCategory) {
		this.pkCategory = pkCategory;
	}

	public String getCaName() {
		return caName;
	}

	public void setCaName(String caName) {
		this.caName = caName;
	}

	/**
	 * 获取属性
	 * 
	 * @return pkProduct
	 */
	
	public java.lang.Integer getPkProduct() {
		return pkProduct;
	}

	public java.lang.Integer getProdStatus() {
		return prodStatus;
	}

	public void setProdStatus(java.lang.Integer prodStatus) {
		this.prodStatus = prodStatus;
	}

	/**
	 * 设置属性
	 * 
	 * @param pkProduct
	 */
	public void setPkProduct(java.lang.Integer pkProduct) {
		this.pkProduct = pkProduct;
	}

	/**
	 * 获取商品编号属性
	 * 
	 * @return prodProductcode
	 */
	public java.lang.String getProdProductcode() {
		return prodProductcode;
	}

	public java.lang.Integer getProdChargingMode() {
		return prodChargingMode;
	}

	public void setProdChargingMode(java.lang.Integer prodChargingMode) {
		this.prodChargingMode = prodChargingMode;
	}

	/**
	 * 设置商品编号属性
	 * 
	 * @param prodProductcode
	 */
	public void setProdProductcode(java.lang.String prodProductcode) {
		this.prodProductcode = prodProductcode;
	}

	/**
	 * 获取产品名称属性
	 * 
	 * @return prodProductname
	 */
	public java.lang.String getProdProductname() {
		return prodProductname;
	}

	/**
	 * 设置产品名称属性
	 * 
	 * @param prodProductname
	 */
	public void setProdProductname(java.lang.String prodProductname) {
		this.prodProductname = prodProductname;
	}

	/**
	 * 获取分类Id(从表tbl_ProductCategory中获取)属性
	 * 
	 * @return prodCategoryid
	 */
	public java.lang.Integer getProdCategoryid() {
		return prodCategoryid;
	}

	/**
	 * 设置分类Id(从表tbl_ProductCategory中获取)属性
	 * 
	 * @param prodCategoryid
	 */
	public void setProdCategoryid(java.lang.Integer prodCategoryid) {
		this.prodCategoryid = prodCategoryid;
	}

	/**
	 * 获取成本价格属性
	 * 
	 * @return prodProductprice
	 */
	public java.math.BigDecimal getProdProductprice() {
		return prodProductprice;
	}

	/**
	 * 设置成本价格属性
	 * 
	 * @param prodProductprice
	 */
	public void setProdProductprice(java.math.BigDecimal prodProductprice) {
		this.prodProductprice = prodProductprice;
	}

	/**
	 * 获取折扣率属性
	 * 
	 * @return prodProductdiscount
	 */
	public java.math.BigDecimal getProdProductdiscount() {
		return prodProductdiscount;
	}

	/**
	 * 设置折扣率属性
	 * 
	 * @param prodProductdiscount
	 */
	public void setProdProductdiscount(java.math.BigDecimal prodProductdiscount) {
		this.prodProductdiscount = prodProductdiscount;
	}

	/**
	 * 获取成本价格*折扣率属性
	 * 
	 * @return prodDiscountprice
	 */
	public java.math.BigDecimal getProdDiscountprice() {
		return prodDiscountprice;
	}

	/**
	 * 设置成本价格*折扣率属性
	 * 
	 * @param prodDiscountprice
	 */
	public void setProdDiscountprice(java.math.BigDecimal prodDiscountprice) {
		this.prodDiscountprice = prodDiscountprice;
	}

	/**
	 * 获取市场价格属性
	 * 
	 * @return prodMarketprice
	 */
	public java.math.BigDecimal getProdMarketprice() {
		return prodMarketprice;
	}

	/**
	 * 设置市场价格属性
	 * 
	 * @param prodMarketprice
	 */
	public void setProdMarketprice(java.math.BigDecimal prodMarketprice) {
		this.prodMarketprice = prodMarketprice;
	}

	/**
	 * 获取库存数量属性
	 * 
	 * @return prodStockquantity
	 */
	public java.lang.Integer getProdStockquantity() {
		return prodStockquantity;
	}

	/**
	 * 设置库存数量属性
	 * 
	 * @param prodStockquantity
	 */
	public void setProdStockquantity(java.lang.Integer prodStockquantity) {
		this.prodStockquantity = prodStockquantity;
	}

	/**
	 * 获取1：待上架 2：上架属性
	 * 
	 * @return prodProductlsadd
	 */
	public java.lang.Integer getProdProductlsadd() {
		return prodProductlsadd;
	}

	/**
	 * 设置1：待上架 2：上架属性
	 * 
	 * @param prodProductlsadd
	 */
	public void setProdProductlsadd(java.lang.Integer prodProductlsadd) {
		this.prodProductlsadd = prodProductlsadd;
	}

	public java.lang.String getProdFeature() {
		return prodFeature;
	}

	public void setProdFeature(java.lang.String prodFeature) {
		this.prodFeature = prodFeature;
	}

	/**
	 * 获取已售数量属性
	 * 
	 * @return prodSoldquantity
	 */
	public java.lang.Integer getProdSoldquantity() {
		return prodSoldquantity;
	}

	/**
	 * 设置已售数量属性
	 * 
	 * @param prodSoldquantity
	 */
	public void setProdSoldquantity(java.lang.Integer prodSoldquantity) {
		this.prodSoldquantity = prodSoldquantity;
	}

	/**
	 * 获取浏览次数属性
	 * 
	 * @return prodBrowsenum
	 */
	public java.lang.Integer getProdBrowsenum() {
		return prodBrowsenum;
	}

	/**
	 * 设置浏览次数属性
	 * 
	 * @param prodBrowsenum
	 */
	public void setProdBrowsenum(java.lang.Integer prodBrowsenum) {
		this.prodBrowsenum = prodBrowsenum;
	}

	/**
	 * 获取1：国家补贴 2：免税置购属性
	 * 
	 * @return prodProducttag
	 */
	public java.lang.Integer getProdProducttag() {
		return prodProducttag;
	}

	/**
	 * 设置1：国家补贴 2：免税置购属性
	 * 
	 * @param prodProducttag
	 */
	public void setProdProducttag(java.lang.Integer prodProducttag) {
		this.prodProducttag = prodProducttag;
	}

	/**
	 * 获取二维码图片属性
	 * 
	 * @return prodQrcodepic
	 */
	public java.lang.String getProdQrcodepic() {
		return prodQrcodepic;
	}

	/**
	 * 设置二维码图片属性
	 * 
	 * @param prodQrcodepic
	 */
	public void setProdQrcodepic(java.lang.String prodQrcodepic) {
		this.prodQrcodepic = prodQrcodepic;
	}

	/**
	 * 获取详细图片属性
	 * 
	 * @return prodDetailimage
	 */
	public java.lang.String getProdDetailimage() {
		return prodDetailimage;
	}

	/**
	 * 设置详细图片属性
	 * 
	 * @param prodDetailimage
	 */
	public void setProdDetailimage(java.lang.String prodDetailimage) {
		this.prodDetailimage = prodDetailimage;
	}

	/**
	 * 获取商品图片属性
	 * 
	 * @return prodProductimage
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
	 * 获取备注属性
	 * 
	 * @return prodRemark
	 */
	public java.lang.String getProdRemark() {
		return prodRemark;
	}

	/**
	 * 设置备注属性
	 * 
	 * @param prodRemark
	 */
	public void setProdRemark(java.lang.String prodRemark) {
		this.prodRemark = prodRemark;
	}

	/**
	 * 获取基本参数属性
	 * 
	 * @param prodParameters
	 */
	public java.lang.String getProdParameters() {
		return prodParameters;
	}

	/**
	 * 设置基本参数属性
	 * 
	 * @param prodParameters
	 */
	public void setProdParameters(java.lang.String prodParameters) {
		this.prodParameters = prodParameters;
	}

	/**
	 * 获取人工服务属性
	 * 
	 * @return prodArtificialservice
	 */
	public java.lang.String getProdArtificialservice() {
		return prodArtificialservice;
	}

	/**
	 * 设置人工服务属性
	 * 
	 * @param prodArtificialservice
	 */
	public void setProdArtificialservice(java.lang.String prodArtificialservice) {
		this.prodArtificialservice = prodArtificialservice;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return prodCreatedate
	 */
	public java.util.Date getProdCreatedate() {
		return prodCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param prodCreatedate
	 */
	public void setProdCreatedate(java.util.Date prodCreatedate) {
		this.prodCreatedate = prodCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return prodUpdatedate
	 */
	public java.util.Date getProdUpdatedate() {
		return prodUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param prodUpdatedate
	 */
	public void setProdUpdatedate(java.util.Date prodUpdatedate) {
		this.prodUpdatedate = prodUpdatedate;
	}

	public java.lang.String getPriceSort() {
		return priceSort;
	}

	public void setPriceSort(java.lang.String priceSort) {
		this.priceSort = priceSort;
	}

	public java.lang.String getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(java.lang.String soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public java.lang.Integer getProdBrand() {
		return prodBrand;
	}

	public void setProdBrand(java.lang.Integer prodBrand) {
		this.prodBrand = prodBrand;
	}
	
	public java.lang.Integer getProdPowerInterface() {
		return prodPowerInterface;
	}

	public void setProdPowerInterface(java.lang.Integer prodPowerInterface) {
		this.prodPowerInterface = prodPowerInterface;
	}

	public java.lang.Integer getProdCarType() {
		return prodCarType;
	}

	public void setProdCarType(java.lang.Integer prodCarType) {
		this.prodCarType = prodCarType;
	}

	public java.lang.Integer getProdSubsidies() {
		return prodSubsidies;
	}

	public void setProdSubsidies(java.lang.Integer prodSubsidies) {
		this.prodSubsidies = prodSubsidies;
	}
	
	public java.lang.Integer getProdBattery() {
		return prodBattery;
	}

	public void setProdBattery(java.lang.Integer prodBattery) {
		this.prodBattery = prodBattery;
	}

	public java.math.BigDecimal getProdOdometer() {
		return prodOdometer;
	}

	public void setProdOdometer(java.math.BigDecimal prodOdometer) {
		this.prodOdometer = prodOdometer;
	}

	public java.lang.String getProdChargingTime() {
		return prodChargingTime;
	}

	public void setProdChargingTime(java.lang.String prodChargingTime) {
		this.prodChargingTime = prodChargingTime;
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
		sb.append("TblProduct");
		sb.append("{pkProduct=").append(pkProduct);
		sb.append(", prodProductcode=").append(prodProductcode);
		sb.append(", prodProductname=").append(prodProductname);
		sb.append(", prodCategoryid=").append(prodCategoryid);
		sb.append(", prodProductprice=").append(prodProductprice);
		sb.append(", prodProductdiscount=").append(prodProductdiscount);
		sb.append(", prodDiscountprice=").append(prodDiscountprice);
		sb.append(", prodMarketprice=").append(prodMarketprice);
		sb.append(", prodStockquantity=").append(prodStockquantity);
		sb.append(", prodProductlsadd=").append(prodProductlsadd);
		sb.append(", prodSoldquantity=").append(prodSoldquantity);
		sb.append(", prodBrowsenum=").append(prodBrowsenum);
		sb.append(", prodProducttag=").append(prodProducttag);
		sb.append(", prodQrcodepic=").append(prodQrcodepic);
		sb.append(", prodDetailimage=").append(prodDetailimage);
		sb.append(", prodProductimage=").append(prodProductimage);
		sb.append(", prodRemark=").append(prodRemark);
		sb.append(", prodParameters=").append(prodParameters);
		sb.append(", prodArtificialservice=").append(prodArtificialservice);
		sb.append(", prodCreatedate=").append(prodCreatedate);
		sb.append(", prodUpdatedate=").append(prodUpdatedate);
		sb.append('}');
		return sb.toString();
	}
}
package com.wanma.model;

import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_AppointmentInstallationOrder表
 * 
 * @author songjf
 * 
 */
public class TblAppointmentinstallationorder extends BasicListAndMutiFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3012862166332279228L;
	private java.lang.Integer pkAppointmentinstallationorder; // 主键
	private java.lang.String alorInstallationordercode; // 预约安装订单编号
	private java.lang.String alorInstallationorderproductid; // 商品名称
	private java.lang.String alorInstallationorderproductname; // 商品编号
	private java.lang.Integer alorUserid; // 用户ID
	private java.lang.String alorTellogin; // 手机帐号
	private java.lang.Long alorMoney; // 金额
	private java.lang.Integer alorInstallationorderstatus; // 1：待支付 2 支付成功 3
															// 操作完成
	private java.util.Date alorBuyingtime; // 购买时间
	private java.lang.String aiorInstallationaddress; // 安装地址
	private java.lang.String alorInstallationperson; // 联系人
	private java.lang.String alorLnstallationtel; // 联系电话
	private java.lang.Integer alorCommoditytotal; // 商品总数
	private java.lang.String alorCommodityshops; // 所属商铺
	private String aLorOrderType;				 //订单支付类型
	private String aLorOrderId;					 //订单ID

	public String getaLorOrderId() {
		return aLorOrderId;
	}

	public void setaLorOrderId(String aLorOrderId) {
		this.aLorOrderId = aLorOrderId;
	}

	public String getaLorOrderType() {
		return aLorOrderType;
	}

	public void setaLorOrderType(String aLorOrderType) {
		this.aLorOrderType = aLorOrderType;
	}

	// 以下字段不与数据库对应
	private java.lang.Integer pkProduct; // 商品id
	private java.lang.Integer pkOrder; // 订单id
	private String factNames;	//真实姓名
	private String factName;	//真实姓名

	public String getFactNames() {
		return factNames;
	}

	public void setFactNames(String factNames) {
		this.factNames = factNames;
	}

	public String getFactName() {
		return factName;
	}

	public void setFactName(String factName) {
		this.factName = factName;
	}

	/**
	 * 获取主键属性
	 * 
	 * @return pkAppointmentinstallationorder
	 */
	public java.lang.Integer getPkAppointmentinstallationorder() {
		return pkAppointmentinstallationorder;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkAppointmentinstallationorder
	 */
	public void setPkAppointmentinstallationorder(
			java.lang.Integer pkAppointmentinstallationorder) {
		this.pkAppointmentinstallationorder = pkAppointmentinstallationorder;
	}

	/**
	 * 获取预约安装订单编号属性
	 * 
	 * @return alorInstallationordercode
	 */
	public java.lang.String getAlorInstallationordercode() {
		return alorInstallationordercode;
	}

	/**
	 * 设置预约安装订单编号属性
	 * 
	 * @param alorInstallationordercode
	 */
	public void setAlorInstallationordercode(
			java.lang.String alorInstallationordercode) {
		this.alorInstallationordercode = alorInstallationordercode;
	}

	/**
	 * 获取商品名称属性
	 * 
	 * @return alorInstallationorderproductid
	 */
	public java.lang.String getAlorInstallationorderproductid() {
		return alorInstallationorderproductid;
	}

	/**
	 * 设置商品名称属性
	 * 
	 * @param alorInstallationorderproductid
	 */
	public void setAlorInstallationorderproductid(
			java.lang.String alorInstallationorderproductid) {
		this.alorInstallationorderproductid = alorInstallationorderproductid;
	}

	/**
	 * 获取商品编号属性
	 * 
	 * @return alorInstallationorderproductname
	 */
	public java.lang.String getAlorInstallationorderproductname() {
		return alorInstallationorderproductname;
	}

	/**
	 * 设置商品编号属性
	 * 
	 * @param alorInstallationorderproductname
	 */
	public void setAlorInstallationorderproductname(
			java.lang.String alorInstallationorderproductname) {
		this.alorInstallationorderproductname = alorInstallationorderproductname;
	}

	/**
	 * 获取用户ID属性
	 * 
	 * @return alorUserid
	 */
	public java.lang.Integer getAlorUserid() {
		return alorUserid;
	}

	/**
	 * 设置用户ID属性
	 * 
	 * @param alorUserid
	 */
	public void setAlorUserid(java.lang.Integer alorUserid) {
		this.alorUserid = alorUserid;
	}

	/**
	 * 获取手机帐号属性
	 * 
	 * @return alorTellogin
	 */
	public java.lang.String getAlorTellogin() {
		return alorTellogin;
	}

	/**
	 * 设置手机帐号属性
	 * 
	 * @param alorTellogin
	 */
	public void setAlorTellogin(java.lang.String alorTellogin) {
		this.alorTellogin = alorTellogin;
	}

	/**
	 * 获取金额属性
	 * 
	 * @return alorMoney
	 */
	public java.lang.Long getAlorMoney() {
		return alorMoney;
	}

	/**
	 * 设置金额属性
	 * 
	 * @param alorMoney
	 */
	public void setAlorMoney(java.lang.Long alorMoney) {
		this.alorMoney = alorMoney;
	}

	/**
	 * 获取1：待支付 2 支付成功 3 操作完成属性
	 * 
	 * @return alorInstallationorderstatus
	 */
	public java.lang.Integer getAlorInstallationorderstatus() {
		return alorInstallationorderstatus;
	}

	/**
	 * 设置1：待支付 2 支付成功 3 操作完成属性
	 * 
	 * @param alorInstallationorderstatus
	 */
	public void setAlorInstallationorderstatus(
			java.lang.Integer alorInstallationorderstatus) {
		this.alorInstallationorderstatus = alorInstallationorderstatus;
	}

	/**
	 * 获取购买时间属性
	 * 
	 * @return alorBuyingtime
	 */
	public java.util.Date getAlorBuyingtime() {
		return alorBuyingtime;
	}

	/**
	 * 设置购买时间属性
	 * 
	 * @param alorBuyingtime
	 */
	public void setAlorBuyingtime(java.util.Date alorBuyingtime) {
		this.alorBuyingtime = alorBuyingtime;
	}

	/**
	 * 获取安装地址属性
	 * 
	 * @return aiorInstallationaddress
	 */
	public java.lang.String getAiorInstallationaddress() {
		return aiorInstallationaddress;
	}

	/**
	 * 设置安装地址属性
	 * 
	 * @param aiorInstallationaddress
	 */
	public void setAiorInstallationaddress(
			java.lang.String aiorInstallationaddress) {
		this.aiorInstallationaddress = aiorInstallationaddress;
	}

	/**
	 * 获取联系人属性
	 * 
	 * @return alorInstallationperson
	 */
	public java.lang.String getAlorInstallationperson() {
		return alorInstallationperson;
	}

	/**
	 * 设置联系人属性
	 * 
	 * @param alorInstallationperson
	 */
	public void setAlorInstallationperson(
			java.lang.String alorInstallationperson) {
		this.alorInstallationperson = alorInstallationperson;
	}

	/**
	 * 获取联系电话属性
	 * 
	 * @return alorLnstallationtel
	 */
	public java.lang.String getAlorLnstallationtel() {
		return alorLnstallationtel;
	}

	/**
	 * 设置联系电话属性
	 * 
	 * @param alorLnstallationtel
	 */
	public void setAlorLnstallationtel(java.lang.String alorLnstallationtel) {
		this.alorLnstallationtel = alorLnstallationtel;
	}

	/**
	 * 获取商品总数属性
	 * 
	 * @return alorCommoditytotal
	 */
	public java.lang.Integer getAlorCommoditytotal() {
		return alorCommoditytotal;
	}

	/**
	 * 设置商品总数属性
	 * 
	 * @param alorCommoditytotal
	 */
	public void setAlorCommoditytotal(java.lang.Integer alorCommoditytotal) {
		this.alorCommoditytotal = alorCommoditytotal;
	}

	/**
	 * 获取所属商铺属性
	 * 
	 * @return alorCommodityshops
	 */
	public java.lang.String getAlorCommodityshops() {
		return alorCommodityshops;
	}

	/**
	 * 设置所属商铺属性
	 * 
	 * @param alorCommodityshops
	 */
	public void setAlorCommodityshops(java.lang.String alorCommodityshops) {
		this.alorCommodityshops = alorCommodityshops;
	}

	public java.lang.Integer getPkProduct() {
		return pkProduct;
	}

	public void setPkProduct(java.lang.Integer pkProduct) {
		this.pkProduct = pkProduct;
	}

	public java.lang.Integer getPkOrder() {
		return pkOrder;
	}

	public void setPkOrder(java.lang.Integer pkOrder) {
		this.pkOrder = pkOrder;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblAppointmentinstallationorder");
		sb.append("{pkAppointmentinstallationorder=").append(
				pkAppointmentinstallationorder);
		sb.append(", alorInstallationordercode=").append(
				alorInstallationordercode);
		sb.append(", alorInstallationorderproductid=").append(
				alorInstallationorderproductid);
		sb.append(", alorInstallationorderproductname=").append(
				alorInstallationorderproductname);
		sb.append(", alorUserid=").append(alorUserid);
		sb.append(", alorTellogin=").append(alorTellogin);
		sb.append(", alorMoney=").append(alorMoney);
		sb.append(", alorInstallationorderstatus=").append(
				alorInstallationorderstatus);
		sb.append(", alorBuyingtime=").append(alorBuyingtime);
		sb.append(", aiorInstallationaddress=").append(aiorInstallationaddress);
		sb.append(", alorInstallationperson=").append(alorInstallationperson);
		sb.append(", alorLnstallationtel=").append(alorLnstallationtel);
		sb.append(", alorCommoditytotal=").append(alorCommoditytotal);
		sb.append(", alorCommodityshops=").append(alorCommodityshops);
		sb.append('}');
		return sb.toString();
	}
}
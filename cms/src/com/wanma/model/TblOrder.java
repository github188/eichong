package com.wanma.model;

import com.pub.model.Entity;

/**
 * 
 * tbl_Order表
 * 
 * @author songjf
 * 
 */
public class TblOrder extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8116429509803532644L;
	private java.lang.Integer pkOrder; // 主键
	private java.lang.String ordeCode; // 编号
	private java.lang.Double ordeTotalamount; // 总金额
	private java.lang.Integer ordeUserid; // 用户Id
	private java.lang.Integer ordeStatus; // 1：待支付 2 支付成功 3 操作完成
	private java.lang.Integer ordeOrdertype; // 1：购物消费
	private java.lang.String ordeRemark; // 备注
	private java.lang.String ordeReceiveingname; // 收货人姓名
	private java.lang.String ordeReceiveingaddress; // 收货地址
	private java.lang.String ordeReceiveingcontact; // 联系方式
	private java.util.Date ordeCreatedate; // 创建时间
	private java.util.Date ordeUpdatedate; // 修改时间
	private java.lang.Integer ordeDeliveryway; // 1：包邮 2：不包邮
	private java.lang.String ordeExpress; // 快递公司
	private String ordeTypeOrder;			//订单支付类型
	private java.lang.Integer ordeCommodityTotal;//购买商品总数
	
	public String getOrdeTypeOrder() {
		return ordeTypeOrder;
	}

	public void setOrdeTypeOrder(String ordeTypeOrder) {
		this.ordeTypeOrder = ordeTypeOrder;
	}

	//不与数据库交互
	private String prName;	//产品名称
	private String prCode;	//产品编号
	private String uiName;	//用户名称
	private String uiPhone;	//手机账号
	private String olTotal;	//总价
	private String olPrice;	//单价
	private String olQuan;	//数量
	private String comName; //企业名称
	private String installAdress; //安装地址
	private String start_create_date; //成交开始时间
	private String end_create_date;	  //成交结束时间
	
	public String getStart_create_date() {
		return start_create_date;
	}

	public void setStart_create_date(String start_create_date) {
		this.start_create_date = start_create_date;
	}

	public String getEnd_create_date() {
		return end_create_date;
	}

	public void setEnd_create_date(String end_create_date) {
		this.end_create_date = end_create_date;
	}

	public String getInstallAdress() {
		return installAdress;
	}

	public void setInstallAdress(String installAdress) {
		this.installAdress = installAdress;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	public String getPrName() {
		return prName;
	}

	public void setPrName(String prName) {
		this.prName = prName;
	}

	public String getPrCode() {
		return prCode;
	}

	public void setPrCode(String prCode) {
		this.prCode = prCode;
	}

	public String getUiName() {
		return uiName;
	}

	public void setUiName(String uiName) {
		this.uiName = uiName;
	}

	public String getUiPhone() {
		return uiPhone;
	}

	public void setUiPhone(String uiPhone) {
		this.uiPhone = uiPhone;
	}

	public String getOlTotal() {
		return olTotal;
	}

	public void setOlTotal(String olTotal) {
		this.olTotal = olTotal;
	}

	public String getOlPrice() {
		return olPrice;
	}

	public void setOlPrice(String olPrice) {
		this.olPrice = olPrice;
	}

	public String getOlQuan() {
		return olQuan;
	}

	public void setOlQuan(String olQuan) {
		this.olQuan = olQuan;
	}

	/**
	 * 获取主键属性
	 * 
	 * @return pkOrder
	 */
	public java.lang.Integer getPkOrder() {
		return pkOrder;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkOrder
	 */
	public void setPkOrder(java.lang.Integer pkOrder) {
		this.pkOrder = pkOrder;
	}

	/**
	 * 获取编号属性
	 * 
	 * @return ordeCode
	 */
	public java.lang.String getOrdeCode() {
		return ordeCode;
	}

	/**
	 * 设置编号属性
	 * 
	 * @param ordeCode
	 */
	public void setOrdeCode(java.lang.String ordeCode) {
		this.ordeCode = ordeCode;
	}

	/**
	 * 获取总金额属性
	 * 
	 * @return ordeTotalamount
	 */
	public java.lang.Double getOrdeTotalamount() {
		return ordeTotalamount;
	}

	/**
	 * 设置总金额属性
	 * 
	 * @param ordeTotalamount
	 */
	public void setOrdeTotalamount(java.lang.Double ordeTotalamount) {
		this.ordeTotalamount = ordeTotalamount;
	}

	/**
	 * 获取用户Id属性
	 * 
	 * @return ordeUserid
	 */
	public java.lang.Integer getOrdeUserid() {
		return ordeUserid;
	}

	/**
	 * 设置用户Id属性
	 * 
	 * @param ordeUserid
	 */
	public void setOrdeUserid(java.lang.Integer ordeUserid) {
		this.ordeUserid = ordeUserid;
	}

	/**
	 * 获取1：待支付 2 支付成功 3 操作完成属性
	 * 
	 * @return ordeStatus
	 */
	public java.lang.Integer getOrdeStatus() {
		return ordeStatus;
	}

	/**
	 * 设置1：待支付 2 支付成功 3 操作完成属性
	 * 
	 * @param ordeStatus
	 */
	public void setOrdeStatus(java.lang.Integer ordeStatus) {
		this.ordeStatus = ordeStatus;
	}

	/**
	 * 获取1：购物消费属性
	 * 
	 * @return ordeOrdertype
	 */
	public java.lang.Integer getOrdeOrdertype() {
		return ordeOrdertype;
	}

	/**
	 * 设置1：购物消费属性
	 * 
	 * @param ordeOrdertype
	 */
	public void setOrdeOrdertype(java.lang.Integer ordeOrdertype) {
		this.ordeOrdertype = ordeOrdertype;
	}

	/**
	 * 获取备注属性
	 * 
	 * @return ordeRemark
	 */
	public java.lang.String getOrdeRemark() {
		return ordeRemark;
	}

	/**
	 * 设置备注属性
	 * 
	 * @param ordeRemark
	 */
	public void setOrdeRemark(java.lang.String ordeRemark) {
		this.ordeRemark = ordeRemark;
	}

	/**
	 * 获取收货人姓名属性
	 * 
	 * @return ordeReceiveingname
	 */
	public java.lang.String getOrdeReceiveingname() {
		return ordeReceiveingname;
	}

	/**
	 * 设置收货人姓名属性
	 * 
	 * @param ordeReceiveingname
	 */
	public void setOrdeReceiveingname(java.lang.String ordeReceiveingname) {
		this.ordeReceiveingname = ordeReceiveingname;
	}

	/**
	 * 获取收货地址属性
	 * 
	 * @return ordeReceiveingaddress
	 */
	public java.lang.String getOrdeReceiveingaddress() {
		return ordeReceiveingaddress;
	}

	/**
	 * 设置收货地址属性
	 * 
	 * @param ordeReceiveingaddress
	 */
	public void setOrdeReceiveingaddress(java.lang.String ordeReceiveingaddress) {
		this.ordeReceiveingaddress = ordeReceiveingaddress;
	}

	/**
	 * 获取联系方式属性
	 * 
	 * @return ordeReceiveingcontact
	 */
	public java.lang.String getOrdeReceiveingcontact() {
		return ordeReceiveingcontact;
	}

	/**
	 * 设置联系方式属性
	 * 
	 * @param ordeReceiveingcontact
	 */
	public void setOrdeReceiveingcontact(java.lang.String ordeReceiveingcontact) {
		this.ordeReceiveingcontact = ordeReceiveingcontact;
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
	 * 获取1：包邮 2：不包邮属性
	 * 
	 * @return ordeDeliveryway
	 */
	public java.lang.Integer getOrdeDeliveryway() {
		return ordeDeliveryway;
	}

	/**
	 * 设置1：包邮 2：不包邮属性
	 * 
	 * @param ordeDeliveryway
	 */
	public void setOrdeDeliveryway(java.lang.Integer ordeDeliveryway) {
		this.ordeDeliveryway = ordeDeliveryway;
	}

	/**
	 * 获取快递公司属性
	 * 
	 * @return ordeExpress
	 */
	public java.lang.String getOrdeExpress() {
		return ordeExpress;
	}

	/**
	 * 设置快递公司属性
	 * 
	 * @param ordeExpress
	 */
	public void setOrdeExpress(java.lang.String ordeExpress) {
		this.ordeExpress = ordeExpress;
	}
	
	/**
	 * 获取购买商品总数属性
	 * 
	 * @param ordeCommodityTotal
	 */
	public java.lang.Integer getOrdeCommodityTotal() {
		return ordeCommodityTotal;
	}

	/**
	 * 设置购买商品总数属性
	 * 
	 * @param ordeCommodityTotal
	 */
	public void setOrdeCommodityTotal(java.lang.Integer ordeCommodityTotal) {
		this.ordeCommodityTotal = ordeCommodityTotal;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblOrder");
		sb.append("{pkOrder=").append(pkOrder);
		sb.append(", ordeCode=").append(ordeCode);
		sb.append(", ordeTotalamount=").append(ordeTotalamount);
		sb.append(", ordeUserid=").append(ordeUserid);
		sb.append(", ordeStatus=").append(ordeStatus);
		sb.append(", ordeOrdertype=").append(ordeOrdertype);
		sb.append(", ordeRemark=").append(ordeRemark);
		sb.append(", ordeReceiveingname=").append(ordeReceiveingname);
		sb.append(", ordeReceiveingaddress=").append(ordeReceiveingaddress);
		sb.append(", ordeReceiveingcontact=").append(ordeReceiveingcontact);
		sb.append(", ordeCreatedate=").append(ordeCreatedate);
		sb.append(", ordeUpdatedate=").append(ordeUpdatedate);
		sb.append(", ordeDeliveryway=").append(ordeDeliveryway);
		sb.append(", ordeExpress=").append(ordeExpress);
		sb.append('}');
		return sb.toString();
	}
}
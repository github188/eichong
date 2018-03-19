package com.wanma.model;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

public class TblPayOrder extends BasicListAndMutiFile{
	
	/**
	 * 版本号
	 */
	private static final long serialVersionUID = 1L;
	private String pkPayOrder;		//主键ID
	private String payoUserId;		//对应用户表主键ID
	private String payoGroupId;		//对应分组表主键ID
	private String payoTypePay;		//对应消费表主键ID
	private String payoPayNo;		//订单编号
	private String payoPayMoney;	//支付金额
	private String payoStatus;		//支付状态
	private String payoOrderType;	//订单支付类型
	
	public String getPayoOrderType() {
		return payoOrderType;
	}
	public void setPayoOrderType(String payoOrderType) {
		this.payoOrderType = payoOrderType;
	}
	//不与数据库交互
	private String facname;		//用户真实姓名
	private String grname;		//分组名称
	
	public String getFacname() {
		return facname;
	}
	public void setFacname(String facname) {
		this.facname = facname;
	}
	public String getGrname() {
		return grname;
	}
	public void setGrname(String grname) {
		this.grname = grname;
	}
	public String getPkPayOrder() {
		return pkPayOrder;
	}
	public void setPkPayOrder(String pkPayOrder) {
		this.pkPayOrder = pkPayOrder;
	}
	public String getPayoUserId() {
		return payoUserId;
	}
	public void setPayoUserId(String payoUserId) {
		this.payoUserId = payoUserId;
	}
	public String getPayoGroupId() {
		return payoGroupId;
	}
	public void setPayoGroupId(String payoGroupId) {
		this.payoGroupId = payoGroupId;
	}
	public String getPayoTypePay() {
		return payoTypePay;
	}
	public void setPayoTypePay(String payoTypePay) {
		this.payoTypePay = payoTypePay;
	}
	public String getPayoPayNo() {
		return payoPayNo;
	}
	public void setPayoPayNo(String payoPayNo) {
		this.payoPayNo = payoPayNo;
	}
	public String getPayoPayMoney() {
		return payoPayMoney;
	}
	public void setPayoPayMoney(String payoPayMoney) {
		this.payoPayMoney = payoPayMoney;
	}
	public String getPayoStatus() {
		return payoStatus;
	}
	public void setPayoStatus(String payoStatus) {
		this.payoStatus = payoStatus;
	}
	
}

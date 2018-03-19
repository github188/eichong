package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_UserAddress表
 * 
 * @author songjf
 * 
 */
public class TblUseraddress implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6284226905852532372L;
	private java.lang.Integer pkUseraddress; //
	private java.lang.Integer pradUserid; // 用户id
	private java.lang.String pradUsername; // 用户姓名
	private java.lang.String pradPhonenumber; // 手机号
	private java.lang.String pradPostalCode; // 邮箱
	private java.lang.String pradAddress; // 收货地址
	private java.lang.String pradStreet; // 街道
	private java.lang.String pradIsDefault;// 是否为默认地址 0否 1是
	private java.util.Date pradCreatedate; // 创建时间
	private java.util.Date pradUpdatedate; // 修改时间

	// 以下字段不予数据库对应
	private java.lang.Integer orderId;// 订单id

	/**
	 * 获取属性
	 * 
	 * @return pkUseraddress
	 */
	public java.lang.Integer getPkUseraddress() {
		return pkUseraddress;
	}

	/**
	 * 设置属性
	 * 
	 * @param pkUseraddress
	 */
	public void setPkUseraddress(java.lang.Integer pkUseraddress) {
		this.pkUseraddress = pkUseraddress;
	}

	/**
	 * 获取用户id属性
	 * 
	 * @return pradUserid
	 */
	public java.lang.Integer getPradUserid() {
		return pradUserid;
	}

	/**
	 * 设置用户id属性
	 * 
	 * @param pradUserid
	 */
	public void setPradUserid(java.lang.Integer pradUserid) {
		this.pradUserid = pradUserid;
	}

	/**
	 * 获取用户姓名属性
	 * 
	 * @return pradUsername
	 */
	public java.lang.String getPradUsername() {
		return pradUsername;
	}

	/**
	 * 设置用户姓名属性
	 * 
	 * @param pradUsername
	 */
	public void setPradUsername(java.lang.String pradUsername) {
		this.pradUsername = pradUsername;
	}

	/**
	 * 获取手机号属性
	 * 
	 * @return pradPhonenumber
	 */
	public java.lang.String getPradPhonenumber() {
		return pradPhonenumber;
	}

	/**
	 * 设置手机号属性
	 * 
	 * @param pradPhonenumber
	 */
	public void setPradPhonenumber(java.lang.String pradPhonenumber) {
		this.pradPhonenumber = pradPhonenumber;
	}

	/**
	 * 获取邮箱属性
	 * 
	 * @return pradPostalCode
	 */
	public java.lang.String getPradPostalCode() {
		return pradPostalCode;
	}

	/**
	 * 设置邮箱属性
	 * 
	 * @param pradPostalCode
	 */
	public void setPradPostalCode(java.lang.String pradPostalCode) {
		this.pradPostalCode = pradPostalCode;
	}

	/**
	 * 获取收货地址属性
	 * 
	 * @return pradAddress
	 */
	public java.lang.String getPradAddress() {
		return pradAddress;
	}

	/**
	 * 设置收货地址属性
	 * 
	 * @param pradAddress
	 */
	public void setPradAddress(java.lang.String pradAddress) {
		this.pradAddress = pradAddress;
	}

	/**
	 * 获取街道属性
	 * 
	 * @return pradStreet
	 */
	public java.lang.String getPradStreet() {
		return pradStreet;
	}

	/**
	 * 设置街道属性
	 * 
	 * @param pradStreet
	 */
	public void setPradStreet(java.lang.String pradStreet) {
		this.pradStreet = pradStreet;
	}

	/**
	 * 获取是否默认属性
	 * 
	 * @param pradIsDefault
	 */
	public java.lang.String getPradIsDefault() {
		return pradIsDefault;
	}

	/**
	 * 设置是否默认属性
	 * 
	 * @param pradIsDefault
	 */
	public void setPradIsDefault(java.lang.String pradIsDefault) {
		this.pradIsDefault = pradIsDefault;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return pradCreatedate
	 */
	public java.util.Date getPradCreatedate() {
		return pradCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param pradCreatedate
	 */
	public void setPradCreatedate(java.util.Date pradCreatedate) {
		this.pradCreatedate = pradCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return pradUpdatedate
	 */
	public java.util.Date getPradUpdatedate() {
		return pradUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param pradUpdatedate
	 */
	public void setPradUpdatedate(java.util.Date pradUpdatedate) {
		this.pradUpdatedate = pradUpdatedate;
	}

	/**
	 * 获取订单id属性
	 * 
	 * @param orderId
	 */
	public java.lang.Integer getOrderId() {
		return orderId;
	}

	/**
	 * 设置订单id属性
	 * 
	 * @param orderId
	 */
	public void setOrderId(java.lang.Integer orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblUseraddress");
		sb.append("{pkUseraddress=").append(pkUseraddress);
		sb.append(", pradUserid=").append(pradUserid);
		sb.append(", pradUsername=").append(pradUsername);
		sb.append(", pradPhonenumber=").append(pradPhonenumber);
		sb.append(", pradPostalCode=").append(pradPostalCode);
		sb.append(", pradAddress=").append(pradAddress);
		sb.append(", pradStreet=").append(pradStreet);
		sb.append(", pradCreatedate=").append(pradCreatedate);
		sb.append(", pradUpdatedate=").append(pradUpdatedate);
		sb.append('}');
		return sb.toString();
	}
}
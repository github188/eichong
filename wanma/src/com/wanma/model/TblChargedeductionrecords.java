package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_ChargeDeductionRecords表
 * 
 * @author songjf
 * 
 */
public class TblChargedeductionrecords {
	private java.lang.Integer pkChargedeductionrecords; //
	private java.lang.Integer cdreElectricpileid; // 电桩ID(tbl_ElectricPile表中获取)
	private java.lang.String cdreUsingmachinecode; // 使用终端机器编码
	private java.lang.Integer cdreCharginginterfaceidentifier; // 充电接口标识
	private java.math.BigDecimal cdreDebitamount; // 扣款金额
	private java.math.BigDecimal cdreBalance; // 账户余额
	private java.lang.String cdreSuccess; // 1 成功 0 失败
	private java.lang.String cdreFailure; // 扣款失败原因
	private java.lang.String cdreCode; // 充电订单编号与充电消费订单是主外键关系
	private java.lang.String cdreTransactionnumber; // 交易流水号

	/**
	 * 获取属性
	 * 
	 * @return pkChargedeductionrecords
	 */
	public java.lang.Integer getPkChargedeductionrecords() {
		return pkChargedeductionrecords;
	}

	/**
	 * 设置属性
	 * 
	 * @param pkChargedeductionrecords
	 */
	public void setPkChargedeductionrecords(
			java.lang.Integer pkChargedeductionrecords) {
		this.pkChargedeductionrecords = pkChargedeductionrecords;
	}

	/**
	 * 获取电桩ID(tbl_ElectricPile表中获取)属性
	 * 
	 * @return cdreElectricpileid
	 */
	public java.lang.Integer getCdreElectricpileid() {
		return cdreElectricpileid;
	}

	/**
	 * 设置电桩ID(tbl_ElectricPile表中获取)属性
	 * 
	 * @param cdreElectricpileid
	 */
	public void setCdreElectricpileid(java.lang.Integer cdreElectricpileid) {
		this.cdreElectricpileid = cdreElectricpileid;
	}

	/**
	 * 获取使用终端机器编码属性
	 * 
	 * @return cdreUsingmachinecode
	 */
	public java.lang.String getCdreUsingmachinecode() {
		return cdreUsingmachinecode;
	}

	/**
	 * 设置使用终端机器编码属性
	 * 
	 * @param cdreUsingmachinecode
	 */
	public void setCdreUsingmachinecode(java.lang.String cdreUsingmachinecode) {
		this.cdreUsingmachinecode = cdreUsingmachinecode;
	}

	/**
	 * 获取充电接口标识属性
	 * 
	 * @return cdreCharginginterfaceidentifier
	 */
	public java.lang.Integer getCdreCharginginterfaceidentifier() {
		return cdreCharginginterfaceidentifier;
	}

	/**
	 * 设置充电接口标识属性
	 * 
	 * @param cdreCharginginterfaceidentifier
	 */
	public void setCdreCharginginterfaceidentifier(
			java.lang.Integer cdreCharginginterfaceidentifier) {
		this.cdreCharginginterfaceidentifier = cdreCharginginterfaceidentifier;
	}

	/**
	 * 获取扣款金额属性
	 * 
	 * @return cdreDebitamount
	 */
	public java.math.BigDecimal getCdreDebitamount() {
		return cdreDebitamount;
	}

	/**
	 * 设置扣款金额属性
	 * 
	 * @param cdreDebitamount
	 */
	public void setCdreDebitamount(java.math.BigDecimal cdreDebitamount) {
		this.cdreDebitamount = cdreDebitamount;
	}

	/**
	 * 获取账户余额属性
	 * 
	 * @return cdreBalance
	 */
	public java.math.BigDecimal getCdreBalance() {
		return cdreBalance;
	}

	/**
	 * 设置账户余额属性
	 * 
	 * @param cdreBalance
	 */
	public void setCdreBalance(java.math.BigDecimal cdreBalance) {
		this.cdreBalance = cdreBalance;
	}

	/**
	 * 获取1 成功 0 失败属性
	 * 
	 * @return cdreSuccess
	 */
	public java.lang.String getCdreSuccess() {
		return cdreSuccess;
	}

	/**
	 * 设置1 成功 0 失败属性
	 * 
	 * @param cdreSuccess
	 */
	public void setCdreSuccess(java.lang.String cdreSuccess) {
		this.cdreSuccess = cdreSuccess;
	}

	/**
	 * 获取扣款失败原因属性
	 * 
	 * @return cdreFailure
	 */
	public java.lang.String getCdreFailure() {
		return cdreFailure;
	}

	/**
	 * 设置扣款失败原因属性
	 * 
	 * @param cdreFailure
	 */
	public void setCdreFailure(java.lang.String cdreFailure) {
		this.cdreFailure = cdreFailure;
	}

	/**
	 * 获取充电订单编号与充电消费订单是主外键关系属性
	 * 
	 * @return cdreCode
	 */
	public java.lang.String getCdreCode() {
		return cdreCode;
	}

	/**
	 * 设置充电订单编号与充电消费订单是主外键关系属性
	 * 
	 * @param cdreCode
	 */
	public void setCdreCode(java.lang.String cdreCode) {
		this.cdreCode = cdreCode;
	}

	/**
	 * 获取交易流水号属性
	 * 
	 * @return cdreTransactionnumber
	 */
	public java.lang.String getCdreTransactionnumber() {
		return cdreTransactionnumber;
	}

	/**
	 * 设置交易流水号属性
	 * 
	 * @param cdreTransactionnumber
	 */
	public void setCdreTransactionnumber(java.lang.String cdreTransactionnumber) {
		this.cdreTransactionnumber = cdreTransactionnumber;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblChargedeductionrecords");
		sb.append("{pkChargedeductionrecords=")
				.append(pkChargedeductionrecords);
		sb.append(", cdreElectricpileid=").append(cdreElectricpileid);
		sb.append(", cdreUsingmachinecode=").append(cdreUsingmachinecode);
		sb.append(", cdreCharginginterfaceidentifier=").append(
				cdreCharginginterfaceidentifier);
		sb.append(", cdreDebitamount=").append(cdreDebitamount);
		sb.append(", cdreBalance=").append(cdreBalance);
		sb.append(", cdreSuccess=").append(cdreSuccess);
		sb.append(", cdreFailure=").append(cdreFailure);
		sb.append(", cdreCode=").append(cdreCode);
		sb.append(", cdreTransactionnumber=").append(cdreTransactionnumber);
		sb.append('}');
		return sb.toString();
	}
}
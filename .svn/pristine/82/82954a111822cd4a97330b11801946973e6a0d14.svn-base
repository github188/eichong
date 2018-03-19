package com.wanma.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.wanma.support.common.BasicListAndMutiFile;

public class TblReconciliation extends BasicListAndMutiFile implements Serializable {
	private static final long serialVersionUID = 1L;
	private int reconciliationId;//对账id
	private String reconciliationNo;//对账流水号
	private String checkBeginTime;//对账开始时间
	private String checkEndTime;//对账结束时间
	private BigDecimal  totalOrderPower;//对账总电量
	private BigDecimal totalOrderMoney;//对账总金额
	private String gmtCreate;//创建时间
	private int cpyId;//对账公司Id
	private int checkStatus;//对账状态(0:对账失败 1：对帐成功,默认为0)
	private int disputeOrder;//争议订单数量(默认为0)
	private BigDecimal disputePower;//对账返回总电量(默认为0)
	private BigDecimal disputeMoney;//对账返回总金额(默认为0)
	
	
	public int getReconciliationId() {
		return reconciliationId;
	}
	public void setReconciliationId(int reconciliationId) {
		this.reconciliationId = reconciliationId;
	}
	public String getReconciliationNo() {
		return reconciliationNo;
	}
	public void setReconciliationNo(String reconciliationNo) {
		this.reconciliationNo = reconciliationNo;
	}
	public String getCheckBeginTime() {
		return checkBeginTime;
	}
	public void setCheckBeginTime(String checkBeginTime) {
		this.checkBeginTime = checkBeginTime;
	}
	public String getCheckEndTime() {
		return checkEndTime;
	}
	public void setCheckEndTime(String checkEndTime) {
		this.checkEndTime = checkEndTime;
	}
	public BigDecimal getTotalOrderPower() {
		return totalOrderPower;
	}
	public void setTotalOrderPower(BigDecimal totalOrderPower) {
		this.totalOrderPower = totalOrderPower;
	}
	public BigDecimal getTotalOrderMoney() {
		return totalOrderMoney;
	}
	public void setTotalOrderMoney(BigDecimal totalOrderMoney) {
		this.totalOrderMoney = totalOrderMoney;
	}
	public String getGmtCreate() {
		return gmtCreate;
	}
	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}
	public int getCpyId() {
		return cpyId;
	}
	public void setCpyId(int cpyId) {
		this.cpyId = cpyId;
	}
	public int getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}
	public int getDisputeOrder() {
		return disputeOrder;
	}
	public void setDisputeOrder(int disputeOrder) {
		this.disputeOrder = disputeOrder;
	}
	public BigDecimal getDisputePower() {
		return disputePower;
	}
	public void setDisputePower(BigDecimal disputePower) {
		this.disputePower = disputePower;
	}
	public BigDecimal getDisputeMoney() {
		return disputeMoney;
	}
	public void setDisputeMoney(BigDecimal disputeMoney) {
		this.disputeMoney = disputeMoney;
	}
	@Override
	public String toString() {
		return "TblReconciliation [reconciliationId=" + reconciliationId
				+ ", reconciliationNo=" + reconciliationNo
				+ ", checkBeginTime=" + checkBeginTime + ", checkEndTime="
				+ checkEndTime + ", totalOrderPower=" + totalOrderPower
				+ ", totalOrderMoney=" + totalOrderMoney + ", gmtCreate="
				+ gmtCreate + ", cpyId=" + cpyId + ", checkStatus="
				+ checkStatus + ", disputeOrder=" + disputeOrder
				+ ", disputePower=" + disputePower + ", disputeMoney="
				+ disputeMoney + "]";
	}
	
	
	
	
}

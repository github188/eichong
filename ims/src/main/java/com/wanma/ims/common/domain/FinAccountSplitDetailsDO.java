package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

/**
 * 分账明细
 * 
 * @author bingo
 * @date 2017-11-27
 */
public class FinAccountSplitDetailsDO extends BasicModel {

	private static final long serialVersionUID = 7691578324978307965L;

	/** 分账明细id */
	private Long pkId;

	/** 公司id */
	private Long cpyId;

	/** 资金账户id */
	private Long accountId;

	/** 分账方式（0：服务费&电费分成；1：电量*单价；2：优惠券） */
	private Integer splitMode;

	/** 服务费分账金额(保留小数点后四位) */
	private Double serviceChargeSplitAmt;

	/** 电费分账金额(保留小数点后四位) */
	private Double electricityChargeSplitAmt;

	/** 电量分账金额(保留小数点后四位) */
	private Double electricPowerSplitAmt;

	/** 优惠券分账金额(保留小数点后四位) */
	private Double couponSplitAmt;

	/** 充电消费订单id */
	private String chargingOrderId;

	private String chargingOrderCode; // 订单编码

	private String quantityElectricity; // 总电量

	private String totalMoney; //总金额

	private String chargeMoney; //充电电费

	private String serviceMoney; //充电服务费

	private String couponMoney; //优惠金额

	private Integer chargingOrderStatus; // 订单状态 1：待支付 ,2：支付成功 ,3: 完成操作

	private String endChargeTime; //充电结束时间

	private String cpyName;//公司名称

	private String startDate; // 开始时间
	private String endDate; // 结束时间

	private String orderStatusName;//订单状态名称

	private String internalData;//内部数据

	public Long getPkId() {
		return pkId;
	}

	public void setPkId(Long pkId) {
		this.pkId = pkId;
	}

	public Long getCpyId() {
		return cpyId;
	}

	public void setCpyId(Long cpyId) {
		this.cpyId = cpyId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Integer getSplitMode() {
		return splitMode;
	}

	public void setSplitMode(Integer splitMode) {
		this.splitMode = splitMode;
	}

	public Double getServiceChargeSplitAmt() {
		return serviceChargeSplitAmt;
	}

	public void setServiceChargeSplitAmt(Double serviceChargeSplitAmt) {
		this.serviceChargeSplitAmt = serviceChargeSplitAmt;
	}

	public Double getElectricityChargeSplitAmt() {
		return electricityChargeSplitAmt;
	}

	public void setElectricityChargeSplitAmt(Double electricityChargeSplitAmt) {
		this.electricityChargeSplitAmt = electricityChargeSplitAmt;
	}

	public Double getElectricPowerSplitAmt() {
		return electricPowerSplitAmt;
	}

	public void setElectricPowerSplitAmt(Double electricPowerSplitAmt) {
		this.electricPowerSplitAmt = electricPowerSplitAmt;
	}

	public Double getCouponSplitAmt() {
		return couponSplitAmt;
	}

	public void setCouponSplitAmt(Double couponSplitAmt) {
		this.couponSplitAmt = couponSplitAmt;
	}

	public String getChargingOrderId() {
		return chargingOrderId;
	}

	public void setChargingOrderId(String chargingOrderId) {
		this.chargingOrderId = chargingOrderId;
	}

	public String getChargingOrderCode() {
		return chargingOrderCode;
	}

	public void setChargingOrderCode(String chargingOrderCode) {
		this.chargingOrderCode = chargingOrderCode;
	}

	public String getQuantityElectricity() {
		return quantityElectricity;
	}

	public void setQuantityElectricity(String quantityElectricity) {
		this.quantityElectricity = quantityElectricity;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getChargeMoney() {
		return chargeMoney;
	}

	public void setChargeMoney(String chargeMoney) {
		this.chargeMoney = chargeMoney;
	}

	public String getServiceMoney() {
		return serviceMoney;
	}

	public void setServiceMoney(String serviceMoney) {
		this.serviceMoney = serviceMoney;
	}

	public String getCouponMoney() {
		return couponMoney;
	}

	public void setCouponMoney(String couponMoney) {
		this.couponMoney = couponMoney;
	}

	public Integer getChargingOrderStatus() {
		return chargingOrderStatus;
	}

	public void setChargingOrderStatus(Integer chargingOrderStatus) {
		this.chargingOrderStatus = chargingOrderStatus;
	}

	public String getEndChargeTime() {
		return endChargeTime;
	}

	public void setEndChargeTime(String endChargeTime) {
		this.endChargeTime = endChargeTime;
	}

	public String getCpyName() {
		return cpyName;
	}

	public void setCpyName(String cpyName) {
		this.cpyName = cpyName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}

	public String getInternalData() {
		return internalData;
	}

	public void setInternalData(String internalData) {
		this.internalData = internalData;
	}
}

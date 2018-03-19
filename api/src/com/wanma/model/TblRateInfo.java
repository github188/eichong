package com.wanma.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

public class TblRateInfo extends BasicListAndMutiFile  implements Serializable {

	private static final long serialVersionUID = 3055687264484769401L;
	
	private int pk_RateInformation;
	private String raIn_EffectiveDates; //生效日期
	private String raIn_ExpiryDate; //失效日期
	private BigDecimal raIn_FreezingMoney; //预冻结金额
	private BigDecimal raIn_MinFreezingMoney; //最小冻结金额
	private String raIn_QuantumDate; //json格式的费率
	private BigDecimal raIn_ReservationRate; //预约单价
	private BigDecimal raIn_ServiceCharge; //服务费
	private BigDecimal raIn_WarnMoney; //告警余额
	private String userId; //关联用户id，p_m_user表的id
	private String raIn_AreaId; 
	public int getPk_RateInformation() {
		return pk_RateInformation;
	}
	public void setPk_RateInformation(int pk_RateInformation) {
		this.pk_RateInformation = pk_RateInformation;
	}
	
	public String getRaIn_EffectiveDates() {
		return raIn_EffectiveDates;
	}
	public void setRaIn_EffectiveDates(String raIn_EffectiveDates) {
		this.raIn_EffectiveDates = raIn_EffectiveDates;
	}
	public String getRaIn_ExpiryDate() {
		return raIn_ExpiryDate;
	}
	public void setRaIn_ExpiryDate(String raIn_ExpiryDate) {
		this.raIn_ExpiryDate = raIn_ExpiryDate;
	}
	public BigDecimal getRaIn_FreezingMoney() {
		return raIn_FreezingMoney;
	}
	public void setRaIn_FreezingMoney(BigDecimal raIn_FreezingMoney) {
		this.raIn_FreezingMoney = raIn_FreezingMoney;
	}
	public BigDecimal getRaIn_MinFreezingMoney() {
		return raIn_MinFreezingMoney;
	}
	public void setRaIn_MinFreezingMoney(BigDecimal raIn_MinFreezingMoney) {
		this.raIn_MinFreezingMoney = raIn_MinFreezingMoney;
	}
	public String getRaIn_QuantumDate() {
		return raIn_QuantumDate;
	}
	public void setRaIn_QuantumDate(String raIn_QuantumDate) {
		this.raIn_QuantumDate = raIn_QuantumDate;
	}
	public BigDecimal getRaIn_ReservationRate() {
		return raIn_ReservationRate;
	}
	public void setRaIn_ReservationRate(BigDecimal raIn_ReservationRate) {
		this.raIn_ReservationRate = raIn_ReservationRate;
	}
	public BigDecimal getRaIn_ServiceCharge() {
		return raIn_ServiceCharge;
	}
	public void setRaIn_ServiceCharge(BigDecimal raIn_ServiceCharge) {
		this.raIn_ServiceCharge = raIn_ServiceCharge;
	}
	public BigDecimal getRaIn_WarnMoney() {
		return raIn_WarnMoney;
	}
	public void setRaIn_WarnMoney(BigDecimal raIn_WarnMoney) {
		this.raIn_WarnMoney = raIn_WarnMoney;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRaIn_AreaId() {
		return raIn_AreaId;
	}
	public void setRaIn_AreaId(String raIn_AreaId) {
		this.raIn_AreaId = raIn_AreaId;
	}
	@Override
	public String toString() {
		return "TblRateInfo [pk_RateInformation=" + pk_RateInformation
				+ ", raIn_EffectiveDates=" + raIn_EffectiveDates
				+ ", raIn_ExpiryDate=" + raIn_ExpiryDate
				+ ", raIn_FreezingMoney=" + raIn_FreezingMoney
				+ ", raIn_MinFreezingMoney=" + raIn_MinFreezingMoney
				+ ", raIn_QuantumDate=" + raIn_QuantumDate
				+ ", raIn_ReservationRate=" + raIn_ReservationRate
				+ ", raIn_ServiceCharge=" + raIn_ServiceCharge
				+ ", raIn_WarnMoney=" + raIn_WarnMoney + ", userId=" + userId
				+ ", raIn_AreaId=" + raIn_AreaId + "]";
	}
	
	
}
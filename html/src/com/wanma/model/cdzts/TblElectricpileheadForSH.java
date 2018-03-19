package com.wanma.model.cdzts;

import java.io.Serializable;
import java.math.BigDecimal;

import com.wanma.support.common.BasicListAndMutiFile;

/**
 * 
 * tbl_ElectricPileHead表
 * 
 * @author mew
 *
 */
public class TblElectricpileheadForSH extends BasicListAndMutiFile implements Serializable {
	private static final long serialVersionUID = -1509939657521446269L;
	private Integer pkElectricpilehead; // 主键
	private Integer pkElectricpile; // 外键
	private String epheElectricpileheadname; // 枪头名称
	private Integer epheElectricpileHeadId;//枪头ID
	private Integer epheElectricpileheadstate; // 电桩枪头状态（0空闲中，3预约中，6充电中，9停用中）
	private String totalChargeDl;// 累计充电度量
	private String totalChargeTime;// 累计充电时间，单位秒
	private String totalChargeAmt;// 累计充电费用
	private String lowTemperature;// 最低温度
	private String highTemperature;// 最高温度
	private String outputVoltage;// 充电输出电压
	private String outputCurrent;// 充电输出电流
	private String soc;// SOC(电池容量)
	private String ephNum;
	private Integer havaCarPlaceLock;
	private Integer haveRadar;
	
	private Integer pkPowerStation;	//站ID
	private BigDecimal raInReservationRate; // 预约单价
	private BigDecimal raInServiceCharge; // 服务费
	private String raInQuantumDate;
	private Integer psId; // 站点外键
	private String[] psIds; // 站点外键组

	/**
	 * 获取主键属性
	 *
	 * @return pkElectricpilehead
	 */
	public Integer getPkElectricpilehead() {
		return pkElectricpilehead;
	}

	/**
	 * 设置主键属性
	 *
	 * @param pkElectricpilehead
	 */
	public void setPkElectricpilehead(Integer pkElectricpilehead) {
		this.pkElectricpilehead = pkElectricpilehead;
	}

	/**
	 * 获取外键属性
	 *
	 * @return pkElectricpile
	 */
	public Integer getPkElectricpile() {
		return pkElectricpile;
	}

	/**
	 * 设置外键属性
	 *
	 * @param pkElectricpile
	 */
	public void setPkElectricpile(Integer pkElectricpile) {
		this.pkElectricpile = pkElectricpile;
	}

	/**
	 * 获取枪头名称属性
	 *
	 * @return epheElectricpileheadname
	 */
	public String getEpheElectricpileheadname() {
		return epheElectricpileheadname;
	}

	/**
	 * 设置枪头名称属性
	 *
	 * @param epheElectricpileheadname
	 */
	public void setEpheElectricpileheadname(
			String epheElectricpileheadname) {
		this.epheElectricpileheadname = epheElectricpileheadname;
	}

	/**
	 * 获取电桩枪头状态（0空闲中，3预约中，6充电中，9停用中）属性
	 *
	 * @return epheElectricpileheadstate
	 */
	public Integer getEpheElectricpileheadstate() {
		return epheElectricpileheadstate;
	}

	/**
	 * 设置电桩枪头状态（0空闲中，3预约中，6充电中，9停用中）属性
	 *
	 * @param epheElectricpileheadstate
	 */
	public void setEpheElectricpileheadstate(
			Integer epheElectricpileheadstate) {
		this.epheElectricpileheadstate = epheElectricpileheadstate;
	}

	public String getTotalChargeDl() {
		return totalChargeDl;
	}

	public void setTotalChargeDl(String totalChargeDl) {
		this.totalChargeDl = totalChargeDl;
	}

	public String getTotalChargeTime() {
		return totalChargeTime;
	}

	public void setTotalChargeTime(String totalChargeTime) {
		this.totalChargeTime = totalChargeTime;
	}

	public String getTotalChargeAmt() {
		return totalChargeAmt;
	}

	public void setTotalChargeAmt(String totalChargeAmt) {
		this.totalChargeAmt = totalChargeAmt;
	}

	public String getLowTemperature() {
		return lowTemperature;
	}

	public void setLowTemperature(String lowTemperature) {
		this.lowTemperature = lowTemperature;
	}

	public String getHighTemperature() {
		return highTemperature;
	}

	public void setHighTemperature(String highTemperature) {
		this.highTemperature = highTemperature;
	}

	public String getOutputVoltage() {
		return outputVoltage;
	}

	public void setOutputVoltage(String outputVoltage) {
		this.outputVoltage = outputVoltage;
	}

	public String getOutputCurrent() {
		return outputCurrent;
	}

	public void setOutputCurrent(String outputCurrent) {
		this.outputCurrent = outputCurrent;
	}

	public String getSoc() {
		return soc;
	}

	public void setSoc(String soc) {
		this.soc = soc;
	}

	public String getEphNum() {
		return ephNum;
	}

	public void setEphNum(String ephNum) {
		this.ephNum = ephNum;
	}

	public Integer getHavaCarPlaceLock() {
		return havaCarPlaceLock;
	}

	public void setHavaCarPlaceLock(Integer havaCarPlaceLock) {
		this.havaCarPlaceLock = havaCarPlaceLock;
	}

	public Integer getHaveRadar() {
		return haveRadar;
	}

	public void setHaveRadar(Integer haveRadar) {
		this.haveRadar = haveRadar;
	}

	public BigDecimal getRaInReservationRate() {
		return raInReservationRate;
	}

	public void setRaInReservationRate(BigDecimal raInReservationRate) {
		this.raInReservationRate = raInReservationRate;
	}

	public BigDecimal getRaInServiceCharge() {
		return raInServiceCharge;
	}

	public void setRaInServiceCharge(BigDecimal raInServiceCharge) {
		this.raInServiceCharge = raInServiceCharge;
	}

	public String getRaInQuantumDate() {
		return raInQuantumDate;
	}

	public void setRaInQuantumDate(String raInQuantumDate) {
		this.raInQuantumDate = raInQuantumDate;
	}
	
	

	public Integer getPkPowerStation() {
		return pkPowerStation;
	}

	public void setPkPowerStation(Integer pkPowerStation) {
		this.pkPowerStation = pkPowerStation;
	}
	
	

	public Integer getEpheElectricpileHeadId() {
		return epheElectricpileHeadId;
	}

	public void setEpheElectricpileHeadId(Integer epheElectricpileHeadId) {
		this.epheElectricpileHeadId = epheElectricpileHeadId;
	}

	public Integer getPsId() {
		return psId;
	}

	public void setPsId(Integer psId) {
		this.psId = psId;
	}

	public String[] getPsIds() {
		return psIds;
	}

	public void setPsIds(String[] psIds) {
		this.psIds = psIds;
	}

	@Override
	public String toString() {
		return "TblElectricpilehead [pkElectricpilehead=" + pkElectricpilehead
				+ ", pkElectricpile=" + pkElectricpile
				+ ", epheElectricpileheadname=" + epheElectricpileheadname
				+ ", epheElectricpileheadstate=" + epheElectricpileheadstate
				+ ", totalChargeDl=" + totalChargeDl + ", totalChargeTime="
				+ totalChargeTime + ", totalChargeAmt=" + totalChargeAmt
				+ ", lowTemperature=" + lowTemperature + ", highTemperature="
				+ highTemperature + ", outputVoltage=" + outputVoltage
				+ ", outputCurrent=" + outputCurrent + ", soc=" + soc
				+ ", ephNum=" + ephNum + ", havaCarPlaceLock="
				+ havaCarPlaceLock + ", haveRadar=" + haveRadar
				+ ", raInReservationRate=" + raInReservationRate
				+ ", raInServiceCharge=" + raInServiceCharge
				+ ", raInQuantumDate=" + raInQuantumDate + "]";
	}

}
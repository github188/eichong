package com.wanma.ims.common.domain;

import java.util.Date;
import java.util.List;

import com.wanma.ims.common.domain.base.BasicModel;

public class ElectricParamDO extends BasicModel {

	/**
	 * 电桩参数设置ElectricPileParamDO
	 */
	private static final long serialVersionUID = 3225704607490022588L;

	private String electricPileCode; // 充电桩编码
	private Long powerStationId; // 充电点ID
	private List<Long> powerStationIdList; // 充电点ID
	private String powerStationName; // 充电点名称
	private String provinceCode; // 省
	private String cityCode; // 市
	private String areaCode; // 区
	private Integer epNum; // 编号
	private String chargeMode; // 充电方式
	private String commonStatus; // 链接状态
	private Integer marker; // 制造商

	// 电桩配置参数
	private Integer id; // 参数主键
	private Integer argId; // 参数值ID 离线充电次数：3
	private String issuedStatus; // 下发状态
	// private Integer status; // 开关 1.开 0.关
	private String argValue; // 参数值 1.开发 0.关
	// SOC
	private String socValue; // 参数值 1.开发 0.关
	// 离线充电数
	private String offlineNum; // 离线充电次数 argValue
	// 电桩配置参数 定时
	private Date chargeTime; // 时间
	private String time; // 时间

	public String getElectricPileCode() {
		return electricPileCode;
	}

	public void setElectricPileCode(String electricPileCode) {
		this.electricPileCode = electricPileCode;
	}

	public Long getPowerStationId() {
		return powerStationId;
	}

	public void setPowerStationId(Long powerStationId) {
		this.powerStationId = powerStationId;
	}

	public String getPowerStationName() {
		return powerStationName;
	}

	public void setPowerStationName(String powerStationName) {
		this.powerStationName = powerStationName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public Integer getEpNum() {
		return epNum;
	}

	public void setEpNum(Integer epNum) {
		this.epNum = epNum;
	}

	public String getChargeMode() {
		return chargeMode;
	}

	public void setChargeMode(String chargeMode) {
		this.chargeMode = chargeMode;
	}

	public String getCommonStatus() {
		return commonStatus;
	}

	public void setCommonStatus(String commonStatus) {
		this.commonStatus = commonStatus;
	}

	public Integer getMarker() {
		return marker;
	}

	public void setMarker(Integer marker) {
		this.marker = marker;
	}

	public List<Long> getPowerStationIdList() {
		return powerStationIdList;
	}

	public void setPowerStationIdList(List<Long> powerStationIdList) {
		this.powerStationIdList = powerStationIdList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getArgId() {
		return argId;
	}

	public void setArgId(Integer argId) {
		this.argId = argId;
	}

	public String getArgValue() {
		return argValue;
	}

	public void setArgValue(String argValue) {
		this.argValue = argValue;
	}

	public String getOfflineNum() {
		return offlineNum;
	}

	public void setOfflineNum(String offlineNum) {
		this.offlineNum = offlineNum;
	}

	public Date getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(Date chargeTime) {
		this.chargeTime = chargeTime;
	}

	public String getIssuedStatus() {
		return issuedStatus;
	}

	public void setIssuedStatus(String issuedStatus) {
		this.issuedStatus = issuedStatus;
	}

	public String getSocValue() {
		return socValue;
	}

	public void setSocValue(String socValue) {
		this.socValue = socValue;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}

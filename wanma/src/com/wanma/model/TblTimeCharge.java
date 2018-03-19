package com.wanma.model;

import java.io.Serializable;
import java.sql.Time;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

public class TblTimeCharge extends BasicListAndMutiFile  implements Serializable {
	private java.lang.Integer pkElectricpile; // 主键
	private java.lang.String elpiElectricpilename; // 电桩名称
	private java.lang.String postName; // 充电点名称
	private java.lang.String elpiElectricpilecode; // 桩体编号
	private java.lang.String elPiMakerName; // 电桩制造商（万马新能源，南京循道，北京三优，上海普天）
	private java.lang.String  elPiOwnProvince;//所属省份
	private java.lang.String  elPiOwnCity;//所属城市
	private java.lang.String  elPiOwnCounty;//所属地区代码
	private java.lang.String  elPiOwnProvinceCode;//所属省份
	private java.lang.String  elPiOwnCityCode;//所属城市
	private java.lang.String  elPiOwnCountyCode;//所属地区代码
	private java.lang.Integer  status;//状态
	private java.lang.Integer  issuedStatus;//下发给电桩状态（0：未下发定时；1：下发定时成功；2：下发定时失败；）
	private Time chargeTime;//定时充电时间（HH:mm:ss）
	private java.lang.Integer pkTimingChargeId; // 主键
	private String elpiUserid;//用户id
	private String userLevel;//用户等级
	
	
	public String getElpiUserid() {
		return elpiUserid;
	}
	public void setElpiUserid(String elpiUserid) {
		this.elpiUserid = elpiUserid;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public java.lang.Integer getPkElectricpile() {
		return pkElectricpile;
	}
	public void setPkElectricpile(java.lang.Integer pkElectricpile) {
		this.pkElectricpile = pkElectricpile;
	}
	public java.lang.String getElpiElectricpilename() {
		return elpiElectricpilename;
	}
	public void setElpiElectricpilename(java.lang.String elpiElectricpilename) {
		this.elpiElectricpilename = elpiElectricpilename;
	}
	public java.lang.String getPostName() {
		return postName;
	}
	public void setPostName(java.lang.String postName) {
		this.postName = postName;
	}
	public java.lang.String getElpiElectricpilecode() {
		return elpiElectricpilecode;
	}
	public void setElpiElectricpilecode(java.lang.String elpiElectricpilecode) {
		this.elpiElectricpilecode = elpiElectricpilecode;
	}
	
	public java.lang.String getElPiMakerName() {
		return elPiMakerName;
	}
	public void setElPiMakerName(java.lang.String elPiMakerName) {
		this.elPiMakerName = elPiMakerName;
	}
	public java.lang.String getElPiOwnProvince() {
		return elPiOwnProvince;
	}
	public void setElPiOwnProvince(java.lang.String elPiOwnProvince) {
		this.elPiOwnProvince = elPiOwnProvince;
	}
	public java.lang.String getElPiOwnCity() {
		return elPiOwnCity;
	}
	public void setElPiOwnCity(java.lang.String elPiOwnCity) {
		this.elPiOwnCity = elPiOwnCity;
	}
	public java.lang.String getElPiOwnCounty() {
		return elPiOwnCounty;
	}
	public void setElPiOwnCounty(java.lang.String elPiOwnCounty) {
		this.elPiOwnCounty = elPiOwnCounty;
	}
	public java.lang.String getElPiOwnProvinceCode() {
		return elPiOwnProvinceCode;
	}
	public void setElPiOwnProvinceCode(java.lang.String elPiOwnProvinceCode) {
		this.elPiOwnProvinceCode = elPiOwnProvinceCode;
	}
	public java.lang.String getElPiOwnCityCode() {
		return elPiOwnCityCode;
	}
	public void setElPiOwnCityCode(java.lang.String elPiOwnCityCode) {
		this.elPiOwnCityCode = elPiOwnCityCode;
	}
	public java.lang.String getElPiOwnCountyCode() {
		return elPiOwnCountyCode;
	}
	public void setElPiOwnCountyCode(java.lang.String elPiOwnCountyCode) {
		this.elPiOwnCountyCode = elPiOwnCountyCode;
	}
	public java.lang.Integer getStatus() {
		return status;
	}
	public void setStatus(java.lang.Integer status) {
		this.status = status;
	}
	public java.lang.Integer getIssuedStatus() {
		return issuedStatus;
	}
	public void setIssuedStatus(java.lang.Integer issuedStatus) {
		this.issuedStatus = issuedStatus;
	}
	
	
	public Time getChargeTime() {
		return chargeTime;
	}
	public void setChargeTime(Time chargeTime) {
		this.chargeTime = chargeTime;
	}
	public java.lang.Integer getPkTimingChargeId() {
		return pkTimingChargeId;
	}
	public void setPkTimingChargeId(java.lang.Integer pkTimingChargeId) {
		this.pkTimingChargeId = pkTimingChargeId;
	}
	@Override
	public String toString() {
		return "TblTimeCharge [pkElectricpile=" + pkElectricpile
				+ ", elpiElectricpilename=" + elpiElectricpilename
				+ ", postName=" + postName + ", elpiElectricpilecode="
				+ elpiElectricpilecode + ", elPiMakerName=" + elPiMakerName
				+ ", elPiOwnProvince=" + elPiOwnProvince + ", elPiOwnCity="
				+ elPiOwnCity + ", elPiOwnCounty=" + elPiOwnCounty
				+ ", elPiOwnProvinceCode=" + elPiOwnProvinceCode
				+ ", elPiOwnCityCode=" + elPiOwnCityCode
				+ ", elPiOwnCountyCode=" + elPiOwnCountyCode + ", status="
				+ status + ", issuedStatus=" + issuedStatus + ", chargeTime="
				+ chargeTime + "]";
	}
	

	
	
}

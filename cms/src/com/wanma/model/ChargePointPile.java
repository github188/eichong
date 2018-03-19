package com.wanma.model;

/**
 * 电桩实体bean
 * @author lhy
 *
 */
public class ChargePointPile {
	
	private java.lang.String pkId; // 主键
	private java.lang.String name; // 充电点名称
	private java.lang.String address; // 充点电地址
	private java.lang.String type; // 充点电类型
	private java.lang.Integer pileCount; // 电桩数量
	private java.lang.Integer linkCount; // 联网数量
	private java.lang.Integer headCount; // 枪头数量

	private java.lang.Integer freeHeadCount; // 空闲数量
	private java.lang.Integer faultCount; // 故障数
	private java.lang.Integer carSpacesCount; // 车位空闲数
	private java.lang.Integer bespokeCount; // 已预约总数
	private java.lang.Integer chargingCount; // 正在充电总数
	private java.lang.Integer shareCount; // 公共桩体数
	private java.lang.Integer ownerCount; // 专属桩体数
	public java.lang.String getPkId() {
		return pkId;
	}
	public void setPkId(java.lang.String pkId) {
		this.pkId = pkId;
	}
	public java.lang.String getName() {
		return name;
	}
	public void setName(java.lang.String name) {
		this.name = name;
	}
	public java.lang.String getAddress() {
		return address;
	}
	public void setAddress(java.lang.String address) {
		this.address = address;
	}
	public java.lang.String getType() {
		return type;
	}
	public void setType(java.lang.String type) {
		this.type = type;
	}
	public java.lang.Integer getPileCount() {
		return pileCount;
	}
	public void setPileCount(java.lang.Integer pileCount) {
		this.pileCount = pileCount;
	}
	public java.lang.Integer getLinkCount() {
		return linkCount;
	}
	public void setLinkCount(java.lang.Integer linkCount) {
		this.linkCount = linkCount;
	}
	public java.lang.Integer getHeadCount() {
		return headCount;
	}
	public void setHeadCount(java.lang.Integer headCount) {
		this.headCount = headCount;
	}
	public java.lang.Integer getFreeHeadCount() {
		return freeHeadCount;
	}
	public void setFreeHeadCount(java.lang.Integer freeHeadCount) {
		this.freeHeadCount = freeHeadCount;
	}
	public java.lang.Integer getFaultCount() {
		return faultCount;
	}
	public void setFaultCount(java.lang.Integer faultCount) {
		this.faultCount = faultCount;
	}
	public java.lang.Integer getCarSpacesCount() {
		return carSpacesCount;
	}
	public void setCarSpacesCount(java.lang.Integer carSpacesCount) {
		this.carSpacesCount = carSpacesCount;
	}
	public java.lang.Integer getBespokeCount() {
		return bespokeCount;
	}
	public void setBespokeCount(java.lang.Integer bespokeCount) {
		this.bespokeCount = bespokeCount;
	}
	public java.lang.Integer getChargingCount() {
		return chargingCount;
	}
	public void setChargingCount(java.lang.Integer chargingCount) {
		this.chargingCount = chargingCount;
	}
	public java.lang.Integer getShareCount() {
		return shareCount;
	}
	public void setShareCount(java.lang.Integer shareCount) {
		this.shareCount = shareCount;
	}
	public java.lang.Integer getOwnerCount() {
		return ownerCount;
	}
	public void setOwnerCount(java.lang.Integer ownerCount) {
		this.ownerCount = ownerCount;
	}
	
}

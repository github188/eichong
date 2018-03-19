package com.wanma.model;

import java.util.List;

public class PowerStationElictric {

	private String elictricPicId;//电桩ID
	private String elictricPicName;//电桩名称
	private String electricPileImage;// 电桩图片
	private String electricPileUserName;// 电桩用途
	private String electricPileState;// 电桩状态
	private String electricPileConnStatus;//电桩连接状态
	private String electricPileNo;// 电桩编号
	private String electricPileChargingMode;// 充电类型
	private String raInReservationRate;//电桩预约单价
	private String raInServiceCharge;//电桩服务费
	private String electricNum;//桩体标识(排序号)
	private String phone;//联系电话
	private String elPiElectricPileAddress;//联系电话

	private String electricPowerInterface;//电桩接口方式
	private String electricPowerSize;//电桩额定功率
	private String picPowerSum;//枪口数量
	private String elpiState;//电桩状态
	private String elictricPicImage;//枪口数量
	private String elPiOnlineTime;//服务费
	
	private List<PowerElectricPileHead>  pileHeadList;//枪口详情
	
	
	public String getElpiState() {
		return elpiState;
	}
	public void setElpiState(String elpiState) {
		this.elpiState = elpiState;
	}
	public String getElictricPicId() {
		return elictricPicId;
	}
	public void setElictricPicId(String elictricPicId) {
		this.elictricPicId = elictricPicId;
	}

	public String getPicPowerSum() {
		return picPowerSum;
	}
	public void setPicPowerSum(String picPowerSum) {
		this.picPowerSum = picPowerSum;
	}
	public List<PowerElectricPileHead> getPileHeadList() {
		return pileHeadList;
	}
	public void setPileHeadList(List<PowerElectricPileHead> pileHeadList) {
		this.pileHeadList = pileHeadList;
	}
	public String getElictricPicName() {
		return elictricPicName;
	}
	public void setElictricPicName(String elictricPicName) {
		this.elictricPicName = elictricPicName;
	}
	public String getElictricPicImage() {
		return elictricPicImage;
	}
	public void setElictricPicImage(String elictricPicImage) {
		this.elictricPicImage = elictricPicImage;
	}
	public String getElectricPileImage() {
		return electricPileImage;
	}
	public void setElectricPileImage(String electricPileImage) {
		this.electricPileImage = electricPileImage;
	}
	public String getElectricPileUserName() {
		return electricPileUserName;
	}
	public void setElectricPileUserName(String electricPileUserName) {
		this.electricPileUserName = electricPileUserName;
	}
	public String getElectricPileState() {
		return electricPileState;
	}
	public void setElectricPileState(String electricPileState) {
		this.electricPileState = electricPileState;
	}
	public String getElectricPileNo() {
		return electricPileNo;
	}
	public void setElectricPileNo(String electricPileNo) {
		this.electricPileNo = electricPileNo;
	}
	public String getElectricPileChargingMode() {
		return electricPileChargingMode;
	}
	public void setElectricPileChargingMode(String electricPileChargingMode) {
		this.electricPileChargingMode = electricPileChargingMode;
	}
	public String getRaInReservationRate() {
		return raInReservationRate;
	}
	public void setRaInReservationRate(String raInReservationRate) {
		this.raInReservationRate = raInReservationRate;
	}
	public String getRaInServiceCharge() {
		return raInServiceCharge;
	}
	public void setRaInServiceCharge(String raInServiceCharge) {
		this.raInServiceCharge = raInServiceCharge;
	}
	public String getElectricPowerInterface() {
		return electricPowerInterface;
	}
	public void setElectricPowerInterface(String electricPowerInterface) {
		this.electricPowerInterface = electricPowerInterface;
	}
	public String getElectricPowerSize() {
		return electricPowerSize;
	}
	public void setElectricPowerSize(String electricPowerSize) {
		this.electricPowerSize = electricPowerSize;
	}
	public String getElectricNum() {
		return electricNum;
	}
	public void setElectricNum(String electricNum) {
		this.electricNum = electricNum;
	}
	public String getElectricPileConnStatus() {
		return electricPileConnStatus;
	}
	public void setElectricPileConnStatus(String electricPileConnStatus) {
		this.electricPileConnStatus = electricPileConnStatus;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getElPiElectricPileAddress() {
		return elPiElectricPileAddress;
	}
	public void setElPiElectricPileAddress(String elPiElectricPileAddress) {
		this.elPiElectricPileAddress = elPiElectricPileAddress;
	}
	public String getElPiOnlineTime() {
		return elPiOnlineTime;
	}
	public void setElPiOnlineTime(String elPiOnlineTime) {
		this.elPiOnlineTime = elPiOnlineTime;
	}
	
	
	
}

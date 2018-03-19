/**
 * FileName:Feedback.java
 * Author: Administrator
 * Create: 2014年8月14日
 * Last Modified: 2014年8月14日
 * Version: V1.0 
 */
package com.wanma.model;


/**
 * 反馈表
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月14日
 */
public class ElectricPileMap  {
	
	 private String electricId;//电桩/充电树/电站 ID
	 private String electricType;//1：充电桩/充电树     2：电站 3电动自行车
	 private String electricState;//1:上线  2：离线
	 private String Longitude;//经度
	 private String  Latitude;//维度

	 private String electricName;//充电桩/充电站 名称
	 private String electricAddress;//充电桩/充电站 地址
	 private String electricImage;//电桩/电站图片
	 private String electricPileSum;//电桩数量
	 
	 private String headNum;	//枪头数量
	 private String freeHeadNum;//空闲枪头数量
	 private String useHeadNum; //使用中枪头数量
	 private String companyType;//公司类型0其他，1爱充网，2国网，3特斯拉
	 
	public String getElectricId() {
		return electricId;
	}
	public void setElectricId(String electricId) {
		this.electricId = electricId;
	}
	public String getElectricType() {
		return electricType;
	}
	public void setElectricType(String electricType) {
		this.electricType = electricType;
	}
	public String getElectricState() {
		return electricState;
	}
	public void setElectricState(String electricState) {
		this.electricState = electricState;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public String getElectricName() {
		return electricName;
	}
	public void setElectricName(String electricName) {
		this.electricName = electricName;
	}
	public String getElectricAddress() {
		return electricAddress;
	}
	public void setElectricAddress(String electricAddress) {
		this.electricAddress = electricAddress;
	}
	public String getElectricImage() {
		return electricImage;
	}
	public void setElectricImage(String electricImage) {
		this.electricImage = electricImage;
	}
	public String getElectricPileSum() {
		return electricPileSum;
	}
	public void setElectricPileSum(String electricPileSum) {
		this.electricPileSum = electricPileSum;
	}
	public String getHeadNum() {
		return headNum;
	}
	public void setHeadNum(String headNum) {
		this.headNum = headNum;
	}
	public String getFreeHeadNum() {
		return freeHeadNum;
	}
	public void setFreeHeadNum(String freeHeadNum) {
		this.freeHeadNum = freeHeadNum;
	}
	public String getUseHeadNum() {
		return (new Integer(headNum)-new Integer(freeHeadNum))+"";
	}
	public void setUseHeadNum(String useHeadNum) {
		this.useHeadNum = useHeadNum;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}
	
	 
}

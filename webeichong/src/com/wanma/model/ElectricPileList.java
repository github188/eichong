package com.wanma.model;

/**
 *  电桩查找(列表模式)  
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-13 下午05:06:07 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class ElectricPileList {

	 private String  electricId;//电桩/充电树/电站 ID
	 private String  electricType;//1：充电桩/充电树     2：电站
	 private String  electricName;//电桩/电站名称
	 private String  electricPileSum;//电桩数量
	 private String  electricAddress;//电桩/电站地址
	 private String  electricImage;//电桩/电站图片
	 private String  electricDistance;//电桩/电站距离
	 private String  electricUse;//电桩用途
	 
	 private String  electriChargingMode;//电桩充电方式
	 private String  electricPowerInterface;//电桩接口方式
	 private String  electricPowerSize;//电桩额定功率
	 private String  electricMaxElectricity;//最大电流
	 private String  electricLongitude;//经度
	 private String  electricLatitude;//维度
	 private String serviceCharge;//服务费
	 private String commentStart;//星级评分
	 private String companyType;//公司类型0其他，1爱充网，2国网，3特斯拉
	 
	 private String headNum;	//枪头数量
	 private String freeHeadNum;//空闲枪头数量
	 private String useHeadNum; //使用中枪头数量
	 
	public String getServiceCharge() {
		return serviceCharge;
	}
	public void setServiceCharge(String serviceCharge) {
		this.serviceCharge = serviceCharge;
	}
	public String getCommentStart() {
		return commentStart;
	}
	public void setCommentStart(String commentStart) {
		this.commentStart = commentStart;
	}
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
	public String getElectricName() {
		return electricName;
	}
	public void setElectricName(String electricName) {
		this.electricName = electricName;
	}
	public String getElectricPileSum() {
		return electricPileSum;
	}
	public void setElectricPileSum(String electricPileSum) {
		this.electricPileSum = electricPileSum;
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
	public String getElectricDistance() {
		return electricDistance;
	}
	public void setElectricDistance(String electricDistance) {
		this.electricDistance = electricDistance;
	}
	public String getElectricUse() {
		return electricUse;
	}
	public void setElectricUse(String electricUse) {
		this.electricUse = electricUse;
	}
	public String getElectriChargingMode() {
		return electriChargingMode;
	}
	public void setElectriChargingMode(String electriChargingMode) {
		this.electriChargingMode = electriChargingMode;
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
	public String getElectricMaxElectricity() {
		return electricMaxElectricity;
	}
	public void setElectricMaxElectricity(String electricMaxElectricity) {
		this.electricMaxElectricity = electricMaxElectricity;
	}
	public String getElectricLongitude() {
		return electricLongitude;
	}
	public void setElectricLongitude(String electricLongitude) {
		this.electricLongitude = electricLongitude;
	}
	public String getElectricLatitude() {
		return electricLatitude;
	}
	public void setElectricLatitude(String electricLatitude) {
		this.electricLatitude = electricLatitude;
	}
	public String getCompanyType() {
		return companyType;
	}
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
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
	 
}


package com.wanma.model;

import java.util.List;

import com.pub.model.Entity;

/**
 *  电桩查找(列表模式)  
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-13 下午05:06:07 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class ElectricPileMonitor extends Entity{

	 private String  electricId;//电桩/充电树/充电点 ID
	 private String  electricType;//1：充电桩/充电树     2：充电点
	 private String  electricName;//电桩/充电点名称
	 private String  electricPileSum;//电桩数量
	 private String  electricAddress;//电桩/充电点地址
	 private String  electricImage;//电桩/充电点图片
	 private String  electricDistance;//电桩/充电点距离
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
	 private List<ElectricPileMonitor> ElectricPileMonitor;
	 private List<ElectricPileMonitor> ElectricPileMonitorMapList;
	 private List<TblElectricpilehead> headList;
	 private ElectricPileDetail pileDetail;
	 private Object monitorData;
	 private int isFree;
	 private int isBespeak;
	 private int isCharge;
	 private int isError;
	 private int pileStatus;
	 private String fengzhiHtml;//费率html
	 private String elPiOwnerCompany;
	
	public String getElPiOwnerCompany() {
		return elPiOwnerCompany;
	}
	public void setElPiOwnerCompany(String elPiOwnerCompany) {
		this.elPiOwnerCompany = elPiOwnerCompany;
	}
	public List<TblElectricpilehead> getHeadList() {
		return headList;
	}
	public void setHeadList(List<TblElectricpilehead> headList) {
		this.headList = headList;
	}
	public ElectricPileDetail getPileDetail() {
		return pileDetail;
	}
	public void setPileDetail(ElectricPileDetail pileDetail) {
		this.pileDetail = pileDetail;
	}
	public List<ElectricPileMonitor> getElectricPileMonitorMapList() {
		return ElectricPileMonitorMapList;
	}
	public void setElectricPileMonitorMapList(
			List<ElectricPileMonitor> electricPileMonitorMapList) {
		ElectricPileMonitorMapList = electricPileMonitorMapList;
	}
	public List<ElectricPileMonitor> getElectricPileMonitor() {
		return ElectricPileMonitor;
	}
	public void setElectricPileMonitor(List<ElectricPileMonitor> electricPileMonitor) {
		ElectricPileMonitor = electricPileMonitor;
	}
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
		Integer headNum = this.headNum != null ? new Integer(this.headNum): 0;
		Integer freeHeadNum = this.freeHeadNum != null ?new Integer(this.freeHeadNum):0;
		return headNum-freeHeadNum+"";
	}
	
	public int getIsBespeak() {
		return isBespeak;
	}
	public void setIsBespeak(int isBespeak) {
		this.isBespeak = isBespeak;
	}
	public int getIsCharge() {
		return isCharge;
	}
	public void setIsCharge(int isCharge) {
		this.isCharge = isCharge;
	}
	public int getIsError() {
		return isError;
	}
	public void setIsError(int isError) {
		this.isError = isError;
	}
	public Object getMonitorData() {
		return monitorData;
	}
	public void setMonitorData(Object monitorData) {
		this.monitorData = monitorData;
	}
	public int getIsFree() {
		return isFree;
	}
	public void setIsFree(int isFree) {
		this.isFree = isFree;
	}
	public int getPileStatus() {
		return pileStatus;
	}
	public void setPileStatus(int pileStatus) {
		this.pileStatus = pileStatus;
	}
	public String getFengzhiHtml() {
		return fengzhiHtml;
	}
	public void setFengzhiHtml(String fengzhiHtml) {
		this.fengzhiHtml = fengzhiHtml;
	}
	 
}

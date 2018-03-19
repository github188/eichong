package com.wanma.model;

import java.util.Date;

import com.pub.model.Entity;

public class TblApplyPartner extends Entity {
	private static final long serialVersionUID = 1L;
	private Integer pkApplyPartner;
	private String apPaServiceQQ;
	private String apPaUserName;
	private String apPaUserPhone;
	private Date apPaCreatedate;
	private Date apPaUpdatedate;
	private String apPaDealReason; // 驳回原因
	private String apPaProcessState;
	private String apPaPointName;
	private String apPaStationAddr;
	private String apPaStationType;
	private String apPaInstallation;
	private String apPaPileNum;
	private String apPaOpenTime;
	private String apPaChargingCost;
	private String apPaCarTypeMatch;
	private String apPaUserEmail;
	private String apPaCooperationMode;
	private String apPaPartnerType;

	private String apPaStationTypeText;
	private String apPaAcPileNum;// 交流充电桩数量
	private String apPaDcPileNum;// 直流充电桩数量
	private String apPaOpenTimeWorkDay;
	private String apPaOpenTimeHoliday;
	private String apPaChargingFee;
	private String apPaServiceFee;
	private String apPaParkingFee;
	private String apPaCompanyName;
	// 以下字段不与数据库对应
	private String createDateStarte;
	private String createDateEnd;
	private String updateDateStart;
	private String updateDateEnd;
	private String apPaProvinceAddress;
	private String apPaCityAddress;
	private String apPaAreaAddress;
	private String apPaStreetAddress;
	private String apPaStationTypeOther;
	private String apPaPersonImage;

	public Integer getPkApplyPartner() {
		return pkApplyPartner;
	}

	public void setPkApplyPartner(Integer pkApplyPartner) {
		this.pkApplyPartner = pkApplyPartner;
	}

	public String getApPaServiceQQ() {
		return apPaServiceQQ;
	}

	public void setApPaServiceQQ(String apPaServiceQQ) {
		this.apPaServiceQQ = apPaServiceQQ;
	}

	public String getApPaUserName() {
		return apPaUserName;
	}

	public void setApPaUserName(String apPaUserName) {
		this.apPaUserName = apPaUserName;
	}

	public String getApPaUserPhone() {
		return apPaUserPhone;
	}

	public void setApPaUserPhone(String apPaUserPhone) {
		this.apPaUserPhone = apPaUserPhone;
	}

	public Date getApPaCreatedate() {
		return apPaCreatedate;
	}

	public void setApPaCreatedate(Date apPaCreatedate) {
		this.apPaCreatedate = apPaCreatedate;
	}

	public Date getApPaUpdatedate() {
		return apPaUpdatedate;
	}

	public void setApPaUpdatedate(Date apPaUpdatedate) {
		this.apPaUpdatedate = apPaUpdatedate;
	}

	public String getApPaDealReason() {
		return apPaDealReason;
	}

	public void setApPaDealReason(String apPaDealReason) {
		this.apPaDealReason = apPaDealReason;
	}

	public String getApPaProcessState() {
		return apPaProcessState;
	}

	public void setApPaProcessState(String apPaProcessState) {
		this.apPaProcessState = apPaProcessState;
	}

	public String getApPaPointName() {
		return apPaPointName;
	}

	public void setApPaPointName(String apPaPointName) {
		this.apPaPointName = apPaPointName;
	}

	public String getApPaStationAddr() {
		return apPaStationAddr;
	}

	public void setApPaStationAddr(String apPaStationAddr) {
		this.apPaStationAddr = apPaStationAddr;
	}

	public String getApPaStationType() {
		return apPaStationType;
	}

	public void setApPaStationType(String apPaStationType) {
		this.apPaStationType = apPaStationType;
	}

	public String getApPaInstallation() {
		return apPaInstallation;
	}

	public void setApPaInstallation(String apPaInstallation) {
		this.apPaInstallation = apPaInstallation;
	}

	public String getApPaPileNum() {
		return apPaPileNum;
	}

	public void setApPaPileNum(String apPaPileNum) {
		this.apPaPileNum = apPaPileNum;
	}

	public String getApPaOpenTime() {
		return apPaOpenTime;
	}

	public void setApPaOpenTime(String apPaOpenTime) {
		this.apPaOpenTime = apPaOpenTime;
	}

	public String getApPaChargingCost() {
		return apPaChargingCost;
	}

	public void setApPaChargingCost(String apPaChargingCost) {
		this.apPaChargingCost = apPaChargingCost;
	}

	public String getApPaCarTypeMatch() {
		return apPaCarTypeMatch;
	}

	public void setApPaCarTypeMatch(String apPaCarTypeMatch) {
		this.apPaCarTypeMatch = apPaCarTypeMatch;
	}

	public String getApPaUserEmail() {
		return apPaUserEmail;
	}

	public void setApPaUserEmail(String apPaUserEmail) {
		this.apPaUserEmail = apPaUserEmail;
	}

	public String getApPaCooperationMode() {
		return apPaCooperationMode;
	}

	public void setApPaCooperationMode(String apPaCooperationMode) {
		this.apPaCooperationMode = apPaCooperationMode;
	}

	public String getApPaPartnerType() {
		return apPaPartnerType;
	}

	public void setApPaPartnerType(String apPaPartnerType) {
		this.apPaPartnerType = apPaPartnerType;
	}

	public String getApPaStationTypeText() {
		return apPaStationTypeText;
	}

	public void setApPaStationTypeText(String apPaStationTypeText) {
		this.apPaStationTypeText = apPaStationTypeText;
	}

	public String getApPaAcPileNum() {
		return apPaAcPileNum;
	}

	public void setApPaAcPileNum(String apPaAcPileNum) {
		this.apPaAcPileNum = apPaAcPileNum;
	}

	public String getApPaDcPileNum() {
		return apPaDcPileNum;
	}

	public void setApPaDcPileNum(String apPaDcPileNum) {
		this.apPaDcPileNum = apPaDcPileNum;
	}

	public String getApPaOpenTimeWorkDay() {
		return apPaOpenTimeWorkDay;
	}

	public void setApPaOpenTimeWorkDay(String apPaOpenTimeWorkDay) {
		this.apPaOpenTimeWorkDay = apPaOpenTimeWorkDay;
	}

	public String getApPaOpenTimeHoliday() {
		return apPaOpenTimeHoliday;
	}

	public void setApPaOpenTimeHoliday(String apPaOpenTimeHoliday) {
		this.apPaOpenTimeHoliday = apPaOpenTimeHoliday;
	}

	public String getApPaChargingFee() {
		return apPaChargingFee;
	}

	public void setApPaChargingFee(String apPaChargingFee) {
		this.apPaChargingFee = apPaChargingFee;
	}

	public String getApPaServiceFee() {
		return apPaServiceFee;
	}

	public void setApPaServiceFee(String apPaServiceFee) {
		this.apPaServiceFee = apPaServiceFee;
	}

	public String getApPaParkingFee() {
		return apPaParkingFee;
	}

	public void setApPaParkingFee(String apPaParkingFee) {
		this.apPaParkingFee = apPaParkingFee;
	}

	public String getCreateDateStarte() {
		return createDateStarte;
	}

	public void setCreateDateStarte(String createDateStarte) {
		this.createDateStarte = createDateStarte;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public String getUpdateDateStart() {
		return updateDateStart;
	}

	public void setUpdateDateStart(String updateDateStart) {
		this.updateDateStart = updateDateStart;
	}

	public String getUpdateDateEnd() {
		return updateDateEnd;
	}

	public void setUpdateDateEnd(String updateDateEnd) {
		this.updateDateEnd = updateDateEnd;
	}

	public String getApPaProvinceAddress() {
		return apPaProvinceAddress;
	}

	public void setApPaProvinceAddress(String apPaProvinceAddress) {
		this.apPaProvinceAddress = apPaProvinceAddress;
	}

	public String getApPaCityAddress() {
		return apPaCityAddress;
	}

	public void setApPaCityAddress(String apPaCityAddress) {
		this.apPaCityAddress = apPaCityAddress;
	}

	public String getApPaAreaAddress() {
		return apPaAreaAddress;
	}

	public void setApPaAreaAddress(String apPaAreaAddress) {
		this.apPaAreaAddress = apPaAreaAddress;
	}

	public String getApPaStreetAddress() {
		return apPaStreetAddress;
	}

	public void setApPaStreetAddress(String apPaStreetAddress) {
		this.apPaStreetAddress = apPaStreetAddress;
	}

	public String getApPaStationTypeOther() {
		return apPaStationTypeOther;
	}

	public void setApPaStationTypeOther(String apPaStationTypeOther) {
		this.apPaStationTypeOther = apPaStationTypeOther;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getApPaPersonImage() {
		return apPaPersonImage;
	}

	public void setApPaPersonImage(String apPaPersonImage) {
		this.apPaPersonImage = apPaPersonImage;
	}

	public String getApPaCompanyName() {
		return apPaCompanyName;
	}

	public void setApPaCompanyName(String apPaCompanyName) {
		this.apPaCompanyName = apPaCompanyName;
	}

	@Override
	public String toString() {
		return "TblApplyPartner [pkApplyPartner=" + pkApplyPartner
				+ ", apPaServiceQQ=" + apPaServiceQQ + ", apPaUserName="
				+ apPaUserName + ", apPaUserPhone=" + apPaUserPhone
				+ ", apPaCreatedate=" + apPaCreatedate + ", apPaUpdatedate="
				+ apPaUpdatedate + ", apPaDealReason=" + apPaDealReason
				+ ", apPaProcessState=" + apPaProcessState + ", apPaPointName="
				+ apPaPointName + ", apPaStationAddr=" + apPaStationAddr
				+ ", apPaStationType=" + apPaStationType
				+ ", apPaInstallation=" + apPaInstallation + ", apPaPileNum="
				+ apPaPileNum + ", apPaOpenTime=" + apPaOpenTime
				+ ", apPaChargingCost=" + apPaChargingCost
				+ ", apPaCarTypeMatch=" + apPaCarTypeMatch + ", apPaUserEmail="
				+ apPaUserEmail + ", apPaCooperationMode="
				+ apPaCooperationMode + ", apPaPartnerType=" + apPaPartnerType
				+ ", apPaStationTypeText=" + apPaStationTypeText
				+ ", apPaAcPileNum=" + apPaAcPileNum + ", apPaDcPileNum="
				+ apPaDcPileNum + ", apPaOpenTimeWorkDay="
				+ apPaOpenTimeWorkDay + ", apPaOpenTimeHoliday="
				+ apPaOpenTimeHoliday + ", apPaChargingFee=" + apPaChargingFee
				+ ", apPaServiceFee=" + apPaServiceFee + ", apPaParkingFee="
				+ apPaParkingFee + ", apPaCompanyName=" + apPaCompanyName
				+ ", createDateStarte=" + createDateStarte + ", createDateEnd="
				+ createDateEnd + ", updateDateStart=" + updateDateStart
				+ ", updateDateEnd=" + updateDateEnd + ", apPaProvinceAddress="
				+ apPaProvinceAddress + ", apPaCityAddress=" + apPaCityAddress
				+ ", apPaAreaAddress=" + apPaAreaAddress
				+ ", apPaStreetAddress=" + apPaStreetAddress
				+ ", apPaStationTypeOther=" + apPaStationTypeOther
				+ ", apPaPersonImage=" + apPaPersonImage + "]";
	}

}

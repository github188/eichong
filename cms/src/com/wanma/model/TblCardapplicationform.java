package com.wanma.model;

import java.util.Date;

import com.pub.model.Entity;

public class TblCardapplicationform extends Entity {
	private static final long serialVersionUID = -2344081692000738049L;
	private Long pkCardapplicationform;// 主键
	private Long cafUserId;// 用户主表ID
	private Long cafPhone;// 用户电话手机号码
	private String cafRealName;// 收件人姓名
	private String cafEmail;// 用户邮箱
	private String cafAddress;// 收件人地址
	private String cafIdcard;// 普通用户身份证号
	private Integer cafSex;// /普通用户性别 (1:男 2：女)
	private String cafBirthday;// 用户生日
	private Integer cafCarCompanyId;// 用户汽车品牌ID
	private String cafCarCompanyName;// 用户汽车品牌
	private Integer cafCarTypeId;// 用户汽车车型ID
	private String cafCarTypeName;// 用户汽车品牌
	private String cafVehicleNumber;// 车架号
	private String cafPlateNum; // 车牌号
	private String cafUsercard;// 充电卡号
	private Integer cafStatus;// 状态 0：申请中，1：申请成功 , 2:申请失败
	private Integer reportLossStatus;//1：挂失
	private Date reportLossDate;// 挂失时间
	private String startTime;//申请开始时间
	private String endTime;//申请结束时间
	
	
	//表单填充
	private String userAccount;//用户帐号
	public Long getPkCardapplicationform() {
		return pkCardapplicationform;
	}
	public void setPkCardapplicationform(Long pkCardapplicationform) {
		this.pkCardapplicationform = pkCardapplicationform;
	}
	public Long getCafUserId() {
		return cafUserId;
	}
	public void setCafUserId(Long cafUserId) {
		this.cafUserId = cafUserId;
	}
	public Long getCafPhone() {
		return cafPhone;
	}
	public void setCafPhone(Long cafPhone) {
		this.cafPhone = cafPhone;
	}
	public String getCafRealName() {
		return cafRealName;
	}
	public void setCafRealName(String cafRealName) {
		this.cafRealName = cafRealName;
	}
	public String getCafEmail() {
		return cafEmail;
	}
	public void setCafEmail(String cafEmail) {
		this.cafEmail = cafEmail;
	}
	public String getCafAddress() {
		return cafAddress;
	}
	public void setCafAddress(String cafAddress) {
		this.cafAddress = cafAddress;
	}
	public String getCafIdcard() {
		return cafIdcard;
	}
	public void setCafIdcard(String cafIdcard) {
		this.cafIdcard = cafIdcard;
	}
	public Integer getCafSex() {
		return cafSex;
	}
	public void setCafSex(Integer cafSex) {
		this.cafSex = cafSex;
	}
	public String getCafBirthday() {
		return cafBirthday;
	}
	public void setCafBirthday(String cafBirthday) {
		this.cafBirthday = cafBirthday;
	}
	public Integer getCafCarCompanyId() {
		return cafCarCompanyId;
	}
	public void setCafCarCompanyId(Integer cafCarCompanyId) {
		this.cafCarCompanyId = cafCarCompanyId;
	}
	public Integer getCafCarTypeId() {
		return cafCarTypeId;
	}
	public void setCafCarTypeId(Integer cafCarTypeId) {
		this.cafCarTypeId = cafCarTypeId;
	}
	public String getCafVehicleNumber() {
		return cafVehicleNumber;
	}
	public void setCafVehicleNumber(String cafVehicleNumber) {
		this.cafVehicleNumber = cafVehicleNumber;
	}
	public String getCafPlateNum() {
		return cafPlateNum;
	}
	public void setCafPlateNum(String cafPlateNum) {
		this.cafPlateNum = cafPlateNum;
	}
	public String getCafUsercard() {
		return cafUsercard;
	}
	public void setCafUsercard(String cafUsercard) {
		this.cafUsercard = cafUsercard;
	}
	public Integer getCafStatus() {
		return cafStatus;
	}
	public void setCafStatus(Integer cafStatus) {
		this.cafStatus = cafStatus;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getCafCarCompanyName() {
		return cafCarCompanyName;
	}
	public void setCafCarCompanyName(String cafCarCompanyName) {
		this.cafCarCompanyName = cafCarCompanyName;
	}
	public String getCafCarTypeName() {
		return cafCarTypeName;
	}
	public void setCafCarTypeName(String cafCarTypeName) {
		this.cafCarTypeName = cafCarTypeName;
	}
	public Integer getReportLossStatus() {
		return reportLossStatus;
	}
	public void setReportLossStatus(Integer reportLossStatus) {
		this.reportLossStatus = reportLossStatus;
	}
	public Date getReportLossDate() {
		return reportLossDate;
	}
	public void setReportLossDate(Date reportLossDate) {
		this.reportLossDate = reportLossDate;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}

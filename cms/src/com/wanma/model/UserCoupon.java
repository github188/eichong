package com.wanma.model;

import com.pub.model.Entity;

public class UserCoupon extends Entity    {
    private String userPhone;//用户手机号
    private String userName;//用户姓名
    private String couponCode;//兑换码
    private Integer cpStatus;//优惠券状态
	private String cpBeginDate; // 获取时间
	private String cpUpdatedate; // 使用时间
	private String cpEndDate; // 使用时间
	private String actType; // 活动类型
	private String actName; // 活动名称
	private String pkActivity; // 活动主键
	private Integer cpUserId; // 活动主键
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	
	public Integer getCpStatus() {
		return cpStatus;
	}
	public void setCpStatus(Integer cpStatus) {
		this.cpStatus = cpStatus;
	}
	public String getCpBeginDate() {
		return cpBeginDate;
	}
	public void setCpBeginDate(String cpBeginDate) {
		this.cpBeginDate = cpBeginDate;
	}
	public String getCpUpdatedate() {
		return cpUpdatedate;
	}
	public void setCpUpdatedate(String cpUpdatedate) {
		this.cpUpdatedate = cpUpdatedate;
	}
	public String getActType() {
		return actType;
	}
	public void setActType(String actType) {
		this.actType = actType;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}
	
	public String getPkActivity() {
		return pkActivity;
	}
	public void setPkActivity(String pkActivity) {
		this.pkActivity = pkActivity;
	}
	
	public String getCpEndDate() {
		return cpEndDate;
	}
	public void setCpEndDate(String cpEndDate) {
		this.cpEndDate = cpEndDate;
	}
	
	public Integer getCpUserId() {
		return cpUserId;
	}
	public void setCpUserId(Integer cpUserId) {
		this.cpUserId = cpUserId;
	}
	@Override
	public String toString() {
		return "UserCoupon [userPhone=" + userPhone + ", userName=" + userName
				+ ", couponCode=" + couponCode + ", cpStatus=" + cpStatus
				+ ", cpBeginDate=" + cpBeginDate + ", cpUpdatedate="
				+ cpUpdatedate + ", cpEndDate=" + cpEndDate + ", actType="
				+ actType + ", actName=" + actName + ", pkActivity="
				+ pkActivity + ", cpUserId=" + cpUserId + "]";
	}

	
	
    
}
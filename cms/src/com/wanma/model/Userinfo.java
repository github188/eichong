package com.wanma.model;

/**
 *  用户信息 
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-23 下午04:08:23 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class Userinfo {

	private String pkUserId;//用户ID
	private String userImage;//用户头像
	private String userRealName;//用户真实姓名
	private String userSex;//用户性别  0 男 1 女属性
	private String userBrithy;//出生日期
	private String userIcNo;///IC卡号
	private String userTel;//手机号
	private String userMail;//邮箱
	private String userDriveNo;//驾驶证号
	private String userCarType;//品牌车型
	private String userCarTypeName; //品牌车型名称
	private String userIntegral;//用户积分
	private String userType;//用户类型 1-普通 2-商户
	private String  usinUserstatus;//  4 待审批 5 个体商家
	private String userNickName; //用户昵称
	
	public String getUserNickName() {
		return userNickName;
	}
	public void setUserNickName(String userNickName) {
		this.userNickName = userNickName;
	}
	public String getUserCarTypeName() {
		return userCarTypeName;
	}
	public void setUserCarTypeName(String userCarTypeName) {
		this.userCarTypeName = userCarTypeName;
	}
	public String getPkUserId() {
		return pkUserId;
	}
	public void setPkUserId(String pkUserId) {
		this.pkUserId = pkUserId;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserSex() {
		return userSex;
	}
	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}
	public String getUserBrithy() {
		return userBrithy;
	}
	public void setUserBrithy(String userBrithy) {
		this.userBrithy = userBrithy;
	}
	public String getUserIcNo() {
		return userIcNo;
	}
	public void setUserIcNo(String userIcNo) {
		this.userIcNo = userIcNo;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getUserDriveNo() {
		return userDriveNo;
	}
	public void setUserDriveNo(String userDriveNo) {
		this.userDriveNo = userDriveNo;
	}
	public String getUserCarType() {
		return userCarType;
	}
	public void setUserCarType(String userCarType) {
		this.userCarType = userCarType;
	}
	public String getUserIntegral() {
		return userIntegral;
	}
	public void setUserIntegral(String userIntegral) {
		this.userIntegral = userIntegral;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUsinUserstatus() {
		return usinUserstatus;
	}
	public void setUsinUserstatus(String usinUserstatus) {
		this.usinUserstatus = usinUserstatus;
	}
	
	
	
}

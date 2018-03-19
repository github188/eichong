package com.wanma.ims.controller.vo;

import com.wanma.ims.common.domain.UserAdminDO;


public class UserAdminVO {
   
	private String userId;
	private String userAccount;
	private String provinceCode;
	private String cityCode;
	private String userStatus;
	private String cpyId;
	private String cpyName;
	private String roleId;
	private String roleName;
	private String userLeval;
	private String userLevalStr;
	
	public UserAdminVO(UserAdminDO userAdminDO){
		this.userId = userAdminDO.getUserId()+"";
		this.userAccount = userAdminDO.getUserAccount();
		this.provinceCode = userAdminDO.getProvinceCode();
		this.cityCode = userAdminDO.getProvinceCode();
		if(userAdminDO.getUserStatus() == 1){
			this.userStatus = "正常";
		}else{
			this.userStatus = "禁用";
		}
		this.cpyId = userAdminDO.getCpyId()+"";
		this.cpyName = userAdminDO.getCpyName();
		this.roleId = userAdminDO.getRoleId();
		this.roleName = userAdminDO.getRoleName();
		this.userLeval = userAdminDO.getUserLevel()+"";
		if(userAdminDO.getUserLevel() == 2){
			this.userLevalStr = "系统管理员";
		}else{
			this.userLevalStr = "业务管理员";
		}
	}
	public String getUserAccount() {
		return userAccount;
	}
	
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}
	
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
	
	public String getCityCode() {
		return cityCode;
	}
	
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	public String getUserStatus() {
		return userStatus;
	}
	
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
	public String getCpyId() {
		return cpyId;
	}
	
	public void setCpyId(String cpyId) {
		this.cpyId = cpyId;
	}
	
	public String getCpyName() {
		return cpyName;
	}
	
	public void setCpyName(String cpyName) {
		this.cpyName = cpyName;
	}
	
	public String getRoleId() {
		return roleId;
	}
	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public String getUserLeval() {
		return userLeval;
	}
	
	public void setUserLeval(String userLeval) {
		this.userLeval = userLeval;
	}
	
	public String getUserLevalStr() {
		return userLevalStr;
	}
	
	public void setUserLevalStr(String userLevalStr) {
		this.userLevalStr = userLevalStr;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}

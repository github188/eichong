package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

public class UserRelaDO extends BasicModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3047542352225805198L;
	private Long relaId;
	private Long userId;//卡用户ID
	private Long userDefaultId;//卡初始化用户ID
	public Long getRelaId() {
		return relaId;
	}
	public void setRelaId(Long relaId) {
		this.relaId = relaId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserDefaultId() {
		return userDefaultId;
	}
	public void setUserDefaultId(Long userDefaultId) {
		this.userDefaultId = userDefaultId;
	}
	
	
	
	
	
}

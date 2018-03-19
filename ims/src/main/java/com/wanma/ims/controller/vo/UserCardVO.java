package com.wanma.ims.controller.vo;

import com.wanma.ims.common.domain.UserCardDO;
import com.wanma.ims.common.domain.UserDO;

public class UserCardVO  {
	private Long ucId;// 主键
	private String ucInternalCardNumber;// 内卡号
	private String ucExternalCardNumber;// 外卡号
	private Integer ucCpyId;// 渠道ID
	private Integer ucStatus;// 状态 0：正常，1：挂失
	private String cpyCompanyname;//渠道公司名称
	private Long accountId;// 卡账户ID
	private Long ucUserId;// 卡用户ID
	private Long newUserId;//绑定的用户ID
	private String levelName;//等级名称
	public Long getUcId() {
		return ucId;
	}
	public void setUcId(Long ucId) {
		this.ucId = ucId;
	}
	public String getUcInternalCardNumber() {
		return ucInternalCardNumber;
	}
	public void setUcInternalCardNumber(String ucInternalCardNumber) {
		this.ucInternalCardNumber = ucInternalCardNumber;
	}
	public String getUcExternalCardNumber() {
		return ucExternalCardNumber;
	}
	public void setUcExternalCardNumber(String ucExternalCardNumber) {
		this.ucExternalCardNumber = ucExternalCardNumber;
	}
	public Integer getUcCpyId() {
		return ucCpyId;
	}
	public void setUcCpyId(Integer ucCpyId) {
		this.ucCpyId = ucCpyId;
	}
	public Integer getUcStatus() {
		return ucStatus;
	}
	public void setUcStatus(Integer ucStatus) {
		this.ucStatus = ucStatus;
	}
	public String getCpyCompanyname() {
		return cpyCompanyname;
	}
	public void setCpyCompanyname(String cpyCompanyname) {
		this.cpyCompanyname = cpyCompanyname;
	}
	public Long getAccountId() {
		return accountId;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	
	public Long getUcUserId() {
		return ucUserId;
	}
	public void setUcUserId(Long ucUserId) {
		this.ucUserId = ucUserId;
	}
	
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	public Long getNewUserId() {
		return newUserId;
	}
	public void setNewUserId(Long newUserId) {
		this.newUserId = newUserId;
	}
	public static UserCardVO valueOf(UserCardDO userCardDO) {
		UserCardVO userCardVO = new UserCardVO();
		userCardVO.setUcId(userCardDO.getUcId());
		userCardVO.setAccountId(userCardDO.getAccountId());
		userCardVO.setCpyCompanyname(userCardDO.getCpyCompanyname());
		userCardVO.setUcCpyId(userCardDO.getUcCpyId());
		userCardVO.setUcInternalCardNumber(userCardDO.getUcInternalCardNumber());
		userCardVO.setUcExternalCardNumber(userCardDO.getUcExternalCardNumber());
		userCardVO.setUcStatus(userCardDO.getUcStatus());
		userCardVO.setUcUserId(userCardDO.getUcUserId());
		userCardVO.setLevelName(userCardDO.getLevelName());
		userCardVO.setNewUserId(userCardDO.getNewUserId());
		return userCardVO;
        
    }
	
}
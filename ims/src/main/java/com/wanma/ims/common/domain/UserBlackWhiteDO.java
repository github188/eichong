package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

/**
 * 黑白名单-用户DO 
 */
public class UserBlackWhiteDO extends BasicModel{

	private static final long serialVersionUID = -1149616844618657333L;
    //主键
	private Long id;
	//渠道ID
	private Long cpyId;
	//用户ID
	private Long userId;
	//桩编码
	private String electricPileCode;
	//类型 0.白名单 1.黑名单
	private Integer type;
	//用户类型 保留字段
	private Integer userType;

	/** 数量 */
	private Long counts;
	
	private String userAccount;// 用户帐号
	
	private String cpyName;// 公司名称

	private String typeName; //类型 0.白名单 1.黑名单
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Long getCpyId() {
		return cpyId;
	}
	
	public void setCpyId(Long cpyId) {
		this.cpyId = cpyId;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getElectricPileCode() {
		return electricPileCode;
	}

	public void setElectricPileCode(String electricPileCode) {
		this.electricPileCode = electricPileCode;
	}

	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getUserType() {
		return userType;
	}
	
	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserBlackWhiteDO [id=" + id + ", cpyId=" + cpyId + ", userId=" + userId + ", electricPileCode=" + electricPileCode + ", type=" + type + ", userType=" + userType
				+ ", toString()=" + super.toString() + "]";
	}

	public Long getCounts() {
		return counts;
	}

	public void setCounts(Long counts) {
		this.counts = counts;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getCpyName() {
		return cpyName;
	}

	public void setCpyName(String cpyName) {
		this.cpyName = cpyName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}

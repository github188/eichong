package com.wanma.ims.common.domain.bo;



/**
* <p>Description: 用户首页中总资产的类</p>
* @author bingo
* @date 2017-6-22
 */
public class AccountBalanceBO {

	/** 用户ID */
    private Long userId;

    /** 渠道类型 */
    private Integer cpyType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getCpyType() {
		return cpyType;
	}

	public void setCpyType(Integer cpyType) {
		this.cpyType = cpyType;
	}
}

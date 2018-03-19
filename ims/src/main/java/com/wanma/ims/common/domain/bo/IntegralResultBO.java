package com.wanma.ims.common.domain.bo;



/**
 * 积分返回对象
 * @author bingo
 * @date 2017-08-30
 */
public class IntegralResultBO{
	private Long integralValue;//积分

	private int couponCount;//优惠券数量
	
	private int choiceCount;//抽奖次数

	public Long getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Long integralValue) {
		this.integralValue = integralValue;
	}

	public int getCouponCount() {
		return couponCount;
	}

	public void setCouponCount(int couponCount) {
		this.couponCount = couponCount;
	}

	public int getChoiceCount() {
		return choiceCount;
	}

	public void setChoiceCount(int choiceCount) {
		this.choiceCount = choiceCount;
	}
}
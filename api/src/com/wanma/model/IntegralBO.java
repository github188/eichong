package com.wanma.model;

import java.io.Serializable;
import java.util.Date;


/**
 * 积分临时对象
 * @author bingo
 * @date 2017-08-23
 */
public class IntegralBO implements Serializable{
	private static final long serialVersionUID = -2082061510679985986L;

	//积分活动ID
	private Long integralActivityId;
	
	//金额
	private Double moneyInvolved;
	
	//充电消费订单Id
	private Long chargingOrderId;
	
	//积分变化方向（0：增加；1：减少）
	private Integer direction;
	
	//积分值
	private Long integralValue;
	
	//优惠码
	private String cpCouponcode;
	
	//用户ID
	private Long userId;
	
	//电桩ID
	private Long electricPileId;
	
	//时间
	private Date completeDate;
	
	//优惠券品种Id
	private Integer couponVarietyId;
	
	// 创建人
	private String creator;
	
	public Long getIntegralActivityId() {
		return integralActivityId;
	}

	public void setIntegralActivityId(Long integralActivityId) {
		this.integralActivityId = integralActivityId;
	}

	public Double getMoneyInvolved() {
		return moneyInvolved;
	}

	public void setMoneyInvolved(Double moneyInvolved) {
		this.moneyInvolved = moneyInvolved;
	}

	public Long getChargingOrderId() {
		return chargingOrderId;
	}

	public void setChargingOrderId(Long chargingOrderId) {
		this.chargingOrderId = chargingOrderId;
	}

	public Integer getDirection() {
		return direction;
	}

	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	public Long getIntegralValue() {
		return integralValue;
	}

	public void setIntegralValue(Long integralValue) {
		this.integralValue = integralValue;
	}

	public String getCpCouponcode() {
		return cpCouponcode;
	}

	public void setCpCouponcode(String cpCouponcode) {
		this.cpCouponcode = cpCouponcode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getElectricPileId() {
		return electricPileId;
	}

	public void setElectricPileId(Long electricPileId) {
		this.electricPileId = electricPileId;
	}

	public Date getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public Integer getCouponVarietyId() {
		return couponVarietyId;
	}

	public void setCouponVarietyId(Integer couponVarietyId) {
		this.couponVarietyId = couponVarietyId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}
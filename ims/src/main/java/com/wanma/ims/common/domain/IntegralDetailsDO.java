package com.wanma.ims.common.domain;

import java.util.Date;

import com.wanma.ims.common.domain.base.BasicModel;


/**
 * 积分明细表
 * @author bingo
 * @date 2017-08-11
 */
public class IntegralDetailsDO extends BasicModel{

	private static final long serialVersionUID = 2829295894729588675L;

	/** 积分明细ID */
	private Long pkId;
	
	/** 积分ID  */
	private Long integralId;
	
	/** 积分活动ID   */
	private Long integralActivityId;
	
	/** 涉及金额 */
	private Double moneyInvolved;
	
	/** 充电消费订单Id */
	private String chargingOrderId;
	
	/** 积分变化方向（0：获取；1：消耗）  */
	private Integer direction;
	
	/** 积分值 */
	private Long integralValue;
	
	/** 积分创建时间  */
	private Date integralDate;

	private Long userId;//用户ID
	
	private Long electricPileId;//电桩ID
	
	private String activityName; //活动名称
	
	private String directionName; //积分变化方向名称
	
	public Long getPkId() {
		return pkId;
	}

	public void setPkId(Long pkId) {
		this.pkId = pkId;
	}

	public Long getIntegralId() {
		return integralId;
	}

	public void setIntegralId(Long integralId) {
		this.integralId = integralId;
	}

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

	public String getChargingOrderId() {
		return chargingOrderId;
	}

	public void setChargingOrderId(String chargingOrderId) {
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

	public Date getIntegralDate() {
		return integralDate;
	}

	public void setIntegralDate(Date integralDate) {
		this.integralDate = integralDate;
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

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getDirectionName() {
		return directionName;
	}

	public void setDirectionName(String directionName) {
		this.directionName = directionName;
	}
}
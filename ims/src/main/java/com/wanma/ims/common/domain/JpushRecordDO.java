package com.wanma.ims.common.domain;


import com.wanma.ims.common.domain.base.BasicModel;

/**
 * 激光推送记录
 * @author bingo
 * @date 2017-12-14
 */
public class JpushRecordDO extends BasicModel {

	private static final long serialVersionUID = 3274058902410345235L;

	/** 激光推送记录id */
	private Long pkId;

	/** 充电消费订单id */
	private String chargingOrderId;

	/** 订单状态（0：开始充电；1：结算。） */
	private Integer chargingOrderStatus;

	public Long getPkId() {
		return pkId;
	}

	public void setPkId(Long pkId) {
		this.pkId = pkId;
	}

	public String getChargingOrderId() {
		return chargingOrderId;
	}

	public void setChargingOrderId(String chargingOrderId) {
		this.chargingOrderId = chargingOrderId;
	}

	public Integer getChargingOrderStatus() {
		return chargingOrderStatus;
	}

	public void setChargingOrderStatus(Integer chargingOrderStatus) {
		this.chargingOrderStatus = chargingOrderStatus;
	}
}
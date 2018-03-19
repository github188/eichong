/** 
 * FileName BackDataObject.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/11/12
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */
package com.wanma.net;

import java.io.Serializable;
import java.util.Date;

/**
 * FileName BackDataObject.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/11/12
 * 
 * 网络返回参数类
 */
public class BackDataObject implements Serializable {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 7279972240008476705L;

	/** 业务ID */
	private String bussinessId;
	/** 状态ID */
	private short status;
	/** 信息 */
	private String msg;
	/** 电桩编号 */
	private String electricPieNumber;
	/** 实际预约结束时间 */
	private Date bespokeEndTime;
	/** 用户账户 **/
	private String userPhone;
	//用户id
	private int userId;
	//
	private int bespType;
	//当次预约/续约需要扣的钱
	private String spendMoney;
	
	
	public String getSpendMoney() {
		return spendMoney;
	}

	public void setSpendMoney(String spendMoney) {
		this.spendMoney = spendMoney;
	}

	public int getBespType() {
		return bespType;
	}

	public void setBespType(int bespType) {
		this.bespType = bespType;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * 业务ID的取得。
	 * 
	 * @return 业务ID
	 */
	public String getBussinessId() {
		return bussinessId;
	}

	/**
	 * 业务ID的取得。
	 * 
	 * @param pBussinessId
	 *            业务ID
	 */
	public void setBussinessId(String pBussinessId) {
		this.bussinessId = pBussinessId;
	}

	/**
	 * 状态ID的取得。
	 * 
	 * @return 状态ID
	 */
	public short getStatus() {
		return status;
	}

	/**
	 * 状态ID的设定。
	 * 
	 * @param pStatus
	 *            状态ID
	 */
	public void setStatus(short pStatus) {
		this.status = pStatus;
	}

	/**
	 * 信息的取得。
	 * 
	 * @return 信息
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * 信息的设定。
	 * 
	 * @param pMsg
	 *            信息
	 */
	public void setMsg(String pMsg) {
		this.msg = pMsg;
	}

	/**
	 * 电桩编号 的取得。
	 * 
	 * @return 电桩编号
	 */
	public String getElectricPieNumber() {
		return electricPieNumber;
	}

	/**
	 * 电桩编号 的设定。
	 * 
	 * @param pElectricPieNumber
	 *            电桩编号
	 */
	public void setElectricPieNumber(String pElectricPieNumber) {
		this.electricPieNumber = pElectricPieNumber;
	}
	
	/**
	 * 实际预约结束时间 的取得。
	 * 
	 * @param bespokeEndTime
	 *           实际预约结束时间 
	 */
	public Date getBespokeEndTime() {
		return bespokeEndTime;
	}

	/**
	 * 实际预约结束时间 的设定。
	 * 
	 * @param bespokeEndTime
	 *           实际预约结束时间 
	 */
	public void setBespokeEndTime(Date bespokeEndTime) {
		this.bespokeEndTime = bespokeEndTime;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	

}

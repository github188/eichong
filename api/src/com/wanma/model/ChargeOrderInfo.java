package com.wanma.model;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

public class ChargeOrderInfo extends BasicListAndMutiFile{

	private String electricityCount;//充电电数
	private String electricityMoney;//充电金额
	private String electricityTime;//充电时间
	private String electricityServiceMoney;//充电服务费
	private String electricityState;//充电状态
	private String electricityQuantity;//充电电流
	private String electricityTotalMoney;//总费用
	public String getElectricityCount() {
		return electricityCount;
	}
	public void setElectricityCount(String electricityCount) {
		this.electricityCount = electricityCount;
	}
	public String getElectricityMoney() {
		return electricityMoney;
	}
	public void setElectricityMoney(String electricityMoney) {
		this.electricityMoney = electricityMoney;
	}
	public String getElectricityTime() {
		return electricityTime;
	}
	public void setElectricityTime(String electricityTime) {
		this.electricityTime = electricityTime;
	}
	public String getElectricityServiceMoney() {
		return electricityServiceMoney;
	}
	public void setElectricityServiceMoney(String electricityServiceMoney) {
		this.electricityServiceMoney = electricityServiceMoney;
	}
	public String getElectricityState() {
		return electricityState;
	}
	public void setElectricityState(String electricityState) {
		this.electricityState = electricityState;
	}
	public String getElectricityQuantity() {
		return electricityQuantity;
	}
	public void setElectricityQuantity(String electricityQuantity) {
		this.electricityQuantity = electricityQuantity;
	}
	public String getElectricityTotalMoney() {
		return electricityTotalMoney;
	}
	public void setElectricityTotalMoney(String electricityTotalMoney) {
		this.electricityTotalMoney = electricityTotalMoney;
	}
	
	
	
}

package com.wanma.model.echarts;

import java.io.Serializable;

public class ConsumptionEchartsModel implements Serializable,EchartsParamModel {
	/**
	 * 消费图表model
	 */
	private static final long serialVersionUID = 1L;
	private String month;
	private double count;
	private double amount;
	private String consumptionType;
	private String begin_date;
	private String end_date;
	private String type;
	private String onlyData;
	private String firstLoadFlag;
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public String getBegin_date() {
		return begin_date;
	}
	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOnlyData() {
		return onlyData;
	}
	public void setOnlyData(String onlyData) {
		this.onlyData = onlyData;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getConsumptionType() {
		return consumptionType;
	}
	public void setConsumptionType(String consumptionType) {
		this.consumptionType = consumptionType;
	}
	public String getFirstLoadFlag() {
		return firstLoadFlag;
	}
	public void setFirstLoadFlag(String firstLoadFlag) {
		this.firstLoadFlag = firstLoadFlag;
	}
	@Override
	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}
}

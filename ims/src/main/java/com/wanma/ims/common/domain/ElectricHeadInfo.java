package com.wanma.ims.common.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ElectricHeadInfo implements Serializable{

	private static final long serialVersionUID = -339464547338474648L;
	private String epCode;//电桩编号
		private Integer headId;//枪头编号
		private Integer chargeStatus;//枪头状态  3:充电 10：等待充电 
		private String  chargeCode;//订单编号
		private Long userId;//用户id
		private String beginChargeTime;//开始充电时间
		private Long rateInfoId;//费率id
		private String chargeFrozenAmt;//预冻金额
		private String vin;//vin码
		private String batteryType;//电池类型
		private String batterycapacity;//电池容量
		private String powerHighestTemperature;//电源单体最高温度
		private String chargeTime;//本次已充电时间
		private String restTime;//剩余充电时间
		private List<String> errorList;//错误类型 可能有多种错误存在一个list里面 
		private String soc;
		private String carTotalVoltage;//车端总电压
		private String bpHighestVoltage;//单体最高电压和组号
		private String bpHighestTemperature;//单体电池最高温度
		private String bpLowestTemperature;//单体电池最低温度
		private String voltageValue;//输出电压
		private String currentValue;//输出电流
		private String powerValue;//输出功率
		private String presentChargeValue;//输出总电量
		private List<Map<String,Object>> threePhaseVoltage;//三相电压曲线
		private List<Map<String,Object>> threePhaseCurrent;//三相电流曲线
		private List<Map<String,Object>>  outputVoltage;//输出电压曲线
		private List<Map<String,Object>>  outputCurrent;//输出电流曲线
		private List<Map<String,Object>>  outputPower;//功率
		private List<Map<String,Object>>  temperature;//温度曲线

	public String getEpCode() {
		return epCode;
	}

	public void setEpCode(String epCode) {
		this.epCode = epCode;
	}

	public Integer getHeadId() {
		return headId;
	}

	public void setHeadId(Integer headId) {
		this.headId = headId;
	}

	public Integer getChargeStatus() {
		return chargeStatus;
	}

	public void setChargeStatus(Integer chargeStatus) {
		this.chargeStatus = chargeStatus;
	}

	public String getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(String chargeCode) {
		this.chargeCode = chargeCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getBeginChargeTime() {
		return beginChargeTime;
	}

	public void setBeginChargeTime(String beginChargeTime) {
		this.beginChargeTime = beginChargeTime;
	}

	public Long getRateInfoId() {
		return rateInfoId;
	}

	public void setRateInfoId(Long rateInfoId) {
		this.rateInfoId = rateInfoId;
	}

	public String getChargeFrozenAmt() {
		return chargeFrozenAmt;
	}

	public void setChargeFrozenAmt(String chargeFrozenAmt) {
		this.chargeFrozenAmt = chargeFrozenAmt;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getBatteryType() {
		return batteryType;
	}

	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	public String getBatterycapacity() {
		return batterycapacity;
	}

	public void setBatterycapacity(String batterycapacity) {
		this.batterycapacity = batterycapacity;
	}

	public String getPowerHighestTemperature() {
		return powerHighestTemperature;
	}

	public void setPowerHighestTemperature(String powerHighestTemperature) {
		this.powerHighestTemperature = powerHighestTemperature;
	}

	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	public String getRestTime() {
		return restTime;
	}

	public void setRestTime(String restTime) {
		this.restTime = restTime;
	}

	public List<String> getErrorList() {
		return errorList;
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	public String getSoc() {
		return soc;
	}

	public void setSoc(String soc) {
		this.soc = soc;
	}

	public String getCarTotalVoltage() {
		return carTotalVoltage;
	}

	public void setCarTotalVoltage(String carTotalVoltage) {
		this.carTotalVoltage = carTotalVoltage;
	}

	public String getBpHighestVoltage() {
		return bpHighestVoltage;
	}

	public void setBpHighestVoltage(String bpHighestVoltage) {
		this.bpHighestVoltage = bpHighestVoltage;
	}

	public String getBpHighestTemperature() {
		return bpHighestTemperature;
	}

	public void setBpHighestTemperature(String bpHighestTemperature) {
		this.bpHighestTemperature = bpHighestTemperature;
	}

	public String getBpLowestTemperature() {
		return bpLowestTemperature;
	}

	public void setBpLowestTemperature(String bpLowestTemperature) {
		this.bpLowestTemperature = bpLowestTemperature;
	}

	public String getVoltageValue() {
		return voltageValue;
	}

	public void setVoltageValue(String voltageValue) {
		this.voltageValue = voltageValue;
	}

	public String getCurrentValue() {
		return currentValue;
	}

	public void setCurrentValue(String currentValue) {
		this.currentValue = currentValue;
	}

	public String getPowerValue() {
		return powerValue;
	}

	public void setPowerValue(String powerValue) {
		this.powerValue = powerValue;
	}

	public String getPresentChargeValue() {
		return presentChargeValue;
	}

	public void setPresentChargeValue(String presentChargeValue) {
		this.presentChargeValue = presentChargeValue;
	}

	public List<Map<String, Object>> getThreePhaseVoltage() {
		return threePhaseVoltage;
	}

	public void setThreePhaseVoltage(List<Map<String, Object>> threePhaseVoltage) {
		this.threePhaseVoltage = threePhaseVoltage;
	}

	public List<Map<String, Object>> getThreePhaseCurrent() {
		return threePhaseCurrent;
	}

	public void setThreePhaseCurrent(List<Map<String, Object>> threePhaseCurrent) {
		this.threePhaseCurrent = threePhaseCurrent;
	}

	public List<Map<String, Object>> getOutputVoltage() {
		return outputVoltage;
	}

	public void setOutputVoltage(List<Map<String, Object>> outputVoltage) {
		this.outputVoltage = outputVoltage;
	}

	public List<Map<String, Object>> getOutputCurrent() {
		return outputCurrent;
	}

	public void setOutputCurrent(List<Map<String, Object>> outputCurrent) {
		this.outputCurrent = outputCurrent;
	}

	public List<Map<String, Object>> getOutputPower() {
		return outputPower;
	}

	public void setOutputPower(List<Map<String, Object>> outputPower) {
		this.outputPower = outputPower;
	}

	public List<Map<String, Object>> getTemperature() {
		return temperature;
	}

	public void setTemperature(List<Map<String, Object>> temperature) {
		this.temperature = temperature;
	}

	@Override
	public String toString() {
		return "ElectricHeadInfo{" +
				"epCode='" + epCode + '\'' +
				", headId=" + headId +
				", chargeStatus=" + chargeStatus +
				", chargeCode='" + chargeCode + '\'' +
				", userId=" + userId +
				", beginChargeTime='" + beginChargeTime + '\'' +
				", rateInfoId=" + rateInfoId +
				", chargeFrozenAmt='" + chargeFrozenAmt + '\'' +
				", vin='" + vin + '\'' +
				", batteryType='" + batteryType + '\'' +
				", batterycapacity='" + batterycapacity + '\'' +
				", powerHighestTemperature='" + powerHighestTemperature + '\'' +
				", chargeTime='" + chargeTime + '\'' +
				", restTime='" + restTime + '\'' +
				", errorList=" + errorList +
				", soc='" + soc + '\'' +
				", carTotalVoltage='" + carTotalVoltage + '\'' +
				", bpHighestVoltage='" + bpHighestVoltage + '\'' +
				", bpHighestTemperature='" + bpHighestTemperature + '\'' +
				", bpLowestTemperature='" + bpLowestTemperature + '\'' +
				", voltageValue='" + voltageValue + '\'' +
				", currentValue='" + currentValue + '\'' +
				", powerValue='" + powerValue + '\'' +
				", presentChargeValue='" + presentChargeValue + '\'' +
				", threePhaseVoltage=" + threePhaseVoltage +
				", threePhaseCurrent=" + threePhaseCurrent +
				", outputVoltage=" + outputVoltage +
				", outputCurrent=" + outputCurrent +
				", outputPower=" + outputPower +
				", temperature=" + temperature +
				'}';
	}
}

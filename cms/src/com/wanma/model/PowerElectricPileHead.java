package com.wanma.model;

public class PowerElectricPileHead {

	private String pileHeadId;
	private String pileHeadName;
	private String pileHeadState;//电桩枪头状态（0空闲中，3预约中，6充电中，9停用中）
	
	private String outputVoltage;
	private String outputCurrent;
	
	
	public String getPileHeadName() {
		return pileHeadName;
	}
	public void setPileHeadName(String pileHeadName) {
		this.pileHeadName = pileHeadName;
	}
	public String getPileHeadState() {
		return pileHeadState;
	}
	public void setPileHeadState(String pileHeadState) {
		this.pileHeadState = pileHeadState;
	}
	public String getPileHeadId() {
		return pileHeadId;
	}
	public void setPileHeadId(String pileHeadId) {
		this.pileHeadId = pileHeadId;
	}
	public String getOutputVoltage() {
		return outputVoltage;
	}
	public void setOutputVoltage(String outputVoltage) {
		this.outputVoltage = outputVoltage;
	}
	public String getOutputCurrent() {
		return outputCurrent;
	}
	public void setOutputCurrent(String outputCurrent) {
		this.outputCurrent = outputCurrent;
	}
	
	
	
	
	
	
}

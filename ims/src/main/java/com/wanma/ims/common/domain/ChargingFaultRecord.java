package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

/**
 * ccu充电故障记录
 */
public class ChargingFaultRecord extends BasicModel {
	private Long pkChargingFaultRecord;
	private String cFReUsingMachineCode;//使用终端机器编码
	private Integer cFReEphNo;//枪头编号
	private Integer cFReElectricPileID;//电桩ID
	private String cFReElectricPileName;//电桩名称
	private String powerStationName;//电站名称
	private Integer epNum;//编号
	private String cFReChargingSarttime;//故障开始时间
	private String cFReTransactionNumber;//订单编号
	private String cFReFaultCause;//故障原因
	private String cFReCreatedate;//创建时间
	private String cFReUpdatedate;//修改时间
	private String provinceCode;
	private String cityCode;
	private String areaCode;
	private Long powerStationId;

	public Long getPkChargingFaultRecord() {
		return pkChargingFaultRecord;
	}

	public void setPkChargingFaultRecord(Long pkChargingFaultRecord) {
		this.pkChargingFaultRecord = pkChargingFaultRecord;
	}

	public Integer getcFReEphNo() {
		return cFReEphNo;
	}

	public void setcFReEphNo(Integer cFReEphNo) {
		this.cFReEphNo = cFReEphNo;
	}

	public String getcFReUsingMachineCode() {
		return cFReUsingMachineCode;
	}

	public void setcFReUsingMachineCode(String cFReUsingMachineCode) {
		this.cFReUsingMachineCode = cFReUsingMachineCode;
	}

	public String getcFReCreatedate() {
		return cFReCreatedate;
	}

	public void setcFReCreatedate(String cFReCreatedate) {
		this.cFReCreatedate = cFReCreatedate;
	}

	public Integer getcFReElectricPileID() {
		return cFReElectricPileID;
	}

	public void setcFReElectricPileID(Integer cFReElectricPileID) {
		this.cFReElectricPileID = cFReElectricPileID;
	}

	public String getcFReChargingSarttime() {
		return cFReChargingSarttime;
	}

	public void setcFReChargingSarttime(String cFReChargingSarttime) {
		this.cFReChargingSarttime = cFReChargingSarttime;
	}

	public String getcFReTransactionNumber() {
		return cFReTransactionNumber;
	}

	public void setcFReTransactionNumber(String cFReTransactionNumber) {
		this.cFReTransactionNumber = cFReTransactionNumber;
	}

	public String getcFReFaultCause() {
		return cFReFaultCause;
	}

	public void setcFReFaultCause(String cFReFaultCause) {
		this.cFReFaultCause = cFReFaultCause;
	}

	public String getcFReElectricPileName() {
		return cFReElectricPileName;
	}

	public void setcFReElectricPileName(String cFReElectricPileName) {
		this.cFReElectricPileName = cFReElectricPileName;
	}

	public String getcFReUpdatedate() {
		return cFReUpdatedate;
	}

	public void setcFReUpdatedate(String cFReUpdatedate) {
		this.cFReUpdatedate = cFReUpdatedate;
	}

	public String getPowerStationName() {
		return powerStationName;
	}

	public void setPowerStationName(String powerStationName) {
		this.powerStationName = powerStationName;
	}

	public Integer getEpNum() {
		return epNum;
	}

	public void setEpNum(Integer epNum) {
		this.epNum = epNum;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Long getPowerStationId() {
		return powerStationId;
	}

	public void setPowerStationId(Long powerStationId) {
		this.powerStationId = powerStationId;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	@Override
	public String toString() {
		return "ChargingFaultRecord{" +
				"pkChargingFaultRecord=" + pkChargingFaultRecord +
				", cFReUsingMachineCode='" + cFReUsingMachineCode + '\'' +
				", cFReEphNo=" + cFReEphNo +
				", cFReElectricPileID=" + cFReElectricPileID +
				", cFReElectricPileName='" + cFReElectricPileName + '\'' +
				", powerStationName='" + powerStationName + '\'' +
				", epNum=" + epNum +
				", cFReChargingSarttime='" + cFReChargingSarttime + '\'' +
				", cFReTransactionNumber='" + cFReTransactionNumber + '\'' +
				", cFReFaultCause='" + cFReFaultCause + '\'' +
				", cFReCreatedate='" + cFReCreatedate + '\'' +
				", cFReUpdatedate='" + cFReUpdatedate + '\'' +
				", provinceCode='" + provinceCode + '\'' +
				", cityCode='" + cityCode + '\'' +
				", areaCode='" + areaCode + '\'' +
				", powerStationId=" + powerStationId +
				'}';
	}
}
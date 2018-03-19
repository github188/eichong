package com.wanma.ims.common.domain;

import java.util.List;

import com.wanma.ims.common.domain.base.BasicModel;

public class ConcentratorDO extends BasicModel {

	/**
	 * 集中器管理
	 */
	private static final long serialVersionUID = 409809817115502525L;
	private Long concentratorId;//集中器ID
	private String concentratorName;//集中器名称
	private String simMac;//SIM卡号
	private String simCode;//SIM卡编号(电话号码)
	private Integer simType;//SIM来源(1：联通,2：电信,3：移动)
	private Integer state;//集中器状态(0：离线,1：上线 2：无效)
	private Long typeSpanId;//产品ID
	private Long coctUserId;// 所属用户ID
	private Integer isDel;//是否删除 0.否 1.是
	
	private List<ElectricPileDO> pileList;//绑定电桩列表
	private String creatTime;//创建时间

	public Long getConcentratorId() {
		return concentratorId;
	}

	public void setConcentratorId(Long concentratorId) {
		this.concentratorId = concentratorId;
	}

	public String getConcentratorName() {
		return concentratorName;
	}

	public void setConcentratorName(String concentratorName) {
		this.concentratorName = concentratorName;
	}

	public String getSimMac() {
		return simMac;
	}

	public void setSimMac(String simMac) {
		this.simMac = simMac;
	}

	public String getSimCode() {
		return simCode;
	}

	public void setSimCode(String simCode) {
		this.simCode = simCode;
	}

	public Integer getSimType() {
		return simType;
	}

	public void setSimType(Integer simType) {
		this.simType = simType;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getTypeSpanId() {
		return typeSpanId;
	}

	public void setTypeSpanId(Long typeSpanId) {
		this.typeSpanId = typeSpanId;
	}

	public Long getCoctUserId() {
		return coctUserId;
	}

	public void setCoctUserId(Long coctUserId) {
		this.coctUserId = coctUserId;
	}

	public List<ElectricPileDO> getPileList() {
		return pileList;
	}

	public void setPileList(List<ElectricPileDO> pileList) {
		this.pileList = pileList;
	}

	public Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(Integer isDel) {
		this.isDel = isDel;
	}

	public String getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}



	
	
}

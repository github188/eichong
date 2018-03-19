package com.wanma.dubbox.model;

import java.util.List;

import com.wanma.dubbox.model.common.BasicModel;

public class TblConcentrator extends BasicModel {
	private static final long serialVersionUID = -8473307898520072535L;
	private String name; // 集中器名称
	private String simMac; // SIM卡号
	private String simCd; // SIM卡编号
	private Integer simTp; // SIM来源(1：联通,2：电信,3：移动)
	private Integer ste; // 集中器状态(0：离线,2：上线)
	private Long uid; // 所属用户ID
	private String uname; // 用户姓名

	private String elids;// 集中器绑定相关电桩，电桩id用逗号隔开

	private List<TblElectricPile> electricList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSimMac() {
		return simMac;
	}

	public void setSimMac(String simMac) {
		this.simMac = simMac;
	}

	public String getSimCd() {
		return simCd;
	}

	public void setSimCd(String simCd) {
		this.simCd = simCd;
	}

	public Integer getSimTp() {
		return simTp;
	}

	public void setSimTp(Integer simTp) {
		this.simTp = simTp;
	}

	public Integer getSte() {
		return ste;
	}

	public void setSte(Integer ste) {
		this.ste = ste;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getElids() {
		return elids;
	}

	public void setElids(String elids) {
		this.elids = elids;
	}

	public List<TblElectricPile> getElectricList() {
		return electricList;
	}

	public void setElectricList(List<TblElectricPile> electricList) {
		this.electricList = electricList;
	}

}
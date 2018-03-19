package com.wanma.dubbox.model;

import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;

public class TblApplyEp extends BasicModel {
	private static final long serialVersionUID = 1L;
	private Integer atp; // 申请类型 1免费建桩2自费建桩
	private Integer ctp; // 分类 1个人2企业
	private String name; // 联系人姓名
	private String pho; // 联系人电话
	private String pcd; // 省份代码
	private String ccd; // 城市代码
	private String acd; // 区县代码
	private String adr; // 具体地址
	private Integer hpk; // 是否自有车位 1是2否
	private Integer pnum; // 车位数量 1少于等于10个 2大于10个
	private Integer etype; // 设备需求 1直流2交流3交直流都有
	private Integer igre; // 物业是否同意安装 1同意2不同意
	private Integer irpt; // 电力是否通过报备 1通过2未通过
	private Integer igrd; // 安装地点 1地面2地下
	private Integer uid; // 申请用户id
	private Integer sts; // 申请状态 1提交申请2勘探现场3现场施工4建桩成功
	private Integer org; // 用户来源 1富士康;2吉利;3绿地;4;浙誉;5.车纷享; 以后根据情况再扩展或修改

	public Integer getAtp() {
		return atp;
	}

	public void setAtp(Integer atp) {
		this.atp = atp;
	}

	public Integer getCtp() {
		return ctp;
	}

	public void setCtp(Integer ctp) {
		this.ctp = ctp;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPho() {
		return pho;
	}

	public void setPho(String pho) {
		this.pho = pho;
	}

	public String getPcd() {
		return pcd;
	}

	public void setPcd(String pcd) {
		this.pcd = pcd;
	}

	public String getCcd() {
		return ccd;
	}

	public void setCcd(String ccd) {
		this.ccd = ccd;
	}

	public String getAcd() {
		return acd;
	}

	public void setAcd(String acd) {
		this.acd = acd;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public Integer getHpk() {
		return hpk;
	}

	public void setHpk(Integer hpk) {
		this.hpk = hpk;
	}

	public Integer getPnum() {
		return pnum;
	}

	public void setPnum(Integer pnum) {
		this.pnum = pnum;
	}

	public Integer getEtype() {
		return etype;
	}

	public void setEtype(Integer etype) {
		this.etype = etype;
	}

	public Integer getIgre() {
		return igre;
	}

	public void setIgre(Integer igre) {
		this.igre = igre;
	}

	public Integer getIrpt() {
		return irpt;
	}

	public void setIrpt(Integer irpt) {
		this.irpt = irpt;
	}

	public Integer getIgrd() {
		return igrd;
	}

	public void setIgrd(Integer igrd) {
		this.igrd = igrd;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getSts() {
		return sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}


	public Integer getOrg() {
		return org;
	}

	public void setOrg(Integer org) {
		this.org = org;
	}

}

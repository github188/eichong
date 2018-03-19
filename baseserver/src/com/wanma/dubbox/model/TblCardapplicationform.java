package com.wanma.dubbox.model;

import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;

public class TblCardapplicationform extends BasicModel {
	private static final long serialVersionUID = -2344081692000738049L;
	private Long uid;// 用户主表ID
	private Long pho;// 用户电话手机号码
	private String name;// 收件人姓名
	private String eml;// 用户邮箱
	private String adr;// 收件人地址
	private String ic;// 普通用户身份证号
	private Integer sex;// /普通用户性别 (1:男 2：女)
	private String btd;// 用户生日
	private Integer carCom;// 用户汽车品牌ID
	private Integer carTp;// 用户汽车车型ID
	private String vln;// 车架号
	private String pln; // 车牌号
	private String cdNum;// 充电卡号
	private Integer sts;// 状态 0：申请中，1：申请成功 , 2:申请失败

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Long getPho() {
		return pho;
	}

	public void setPho(Long pho) {
		this.pho = pho;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEml() {
		return eml;
	}

	public void setEml(String eml) {
		this.eml = eml;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getIc() {
		return ic;
	}

	public void setIc(String ic) {
		this.ic = ic;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getBtd() {
		return btd;
	}

	public void setBtd(String btd) {
		this.btd = btd;
	}

	public Integer getCarCom() {
		return carCom;
	}

	public void setCarCom(Integer carCom) {
		this.carCom = carCom;
	}

	public Integer getCarTp() {
		return carTp;
	}

	public void setCarTp(Integer carTp) {
		this.carTp = carTp;
	}

	public String getVln() {
		return vln;
	}

	public void setVln(String vln) {
		this.vln = vln;
	}

	public String getPln() {
		return pln;
	}

	public void setPln(String pln) {
		this.pln = pln;
	}

	public String getCdNum() {
		return cdNum;
	}

	public void setCdNum(String cdNum) {
		this.cdNum = cdNum;
	}

	public Integer getSts() {
		return sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

}

package com.wanma.model;

import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;


public class Cooperate extends BasicListAndMutiFile implements Serializable {

private static final long serialVersionUID = -6776678213353581144L;
private String companyNumber;//公司标识
private String companyName;//公司名称
private String pkCompanyId;//公司唯一标识

private Integer powerStationNum;//充电点数量
private Integer pileNum;//充电桩数量
public String getCompanyNumber() {
	return companyNumber;
}
public void setCompanyNumber(String companyNumber) {
	this.companyNumber = companyNumber;
}
public String getCompanyName() {
	return companyName;
}
public void setCompanyName(String companyName) {
	this.companyName = companyName;
}
public String getPkCompanyId() {
	return pkCompanyId;
}
public void setPkCompanyId(String pkCompanyId) {
	this.pkCompanyId = pkCompanyId;
}
public Integer getPowerStationNum() {
	return powerStationNum;
}
public void setPowerStationNum(Integer powerStationNum) {
	this.powerStationNum = powerStationNum;
}
public Integer getPileNum() {
	return pileNum;
}
public void setPileNum(Integer pileNum) {
	this.pileNum = pileNum;
}


	
}

package com.wanma.model;

import java.math.BigDecimal;
import java.util.List;

public class PowerStationElictric {

	private String elictricPicId;//电桩ID
	private String elictricPicName;//电桩名称
	private String picPowerSum;//枪口数量
	private String elpiState;//电桩状态
	private String elictricPicImage;//枪口数量
	private BigDecimal raIn_ReservationRate;//预约单价
	private String elpiChargingmode;
	private String elpiPowersize;
	private String elpiPowerinterface;
	private String elpiElectricpilecode; //电桩编号
	private BigDecimal raIn_ServiceCharge;
	private int ep_num;
	private int comm_status;
	private String epaddr; //电桩地址
	private String ownerCompany;//电桩所属 0其他，1爱充网，2国网，3特斯拉
	private int rateId; //桩关联的费率id
	private BigDecimal currentRate; //当前电费
	private int haveLine; //桩是否有槍
	
	public int getHaveLine() {
		return haveLine;
	}
	public void setHaveLine(int haveLine) {
		this.haveLine = haveLine;
	}
	public BigDecimal getCurrentRate() {
		return currentRate;
	}
	public void setCurrentRate(BigDecimal currentRate) {
		this.currentRate = currentRate;
	}
	public String getOwnerCompany() {
		return ownerCompany;
	}
	public void setOwnerCompany(String ownerCompany) {
		this.ownerCompany = ownerCompany;
	}
	public String getEpaddr() {
		return epaddr;
	}
	public void setEpaddr(String epaddr) {
		this.epaddr = epaddr;
	}
	public int getComm_status() {
		return comm_status;
	}
	public void setComm_status(int comm_status) {
		this.comm_status = comm_status;
	}
	public int getEp_num() {
		return ep_num;
	}
	public void setEp_num(int ep_num) {
		this.ep_num = ep_num;
	}
	public BigDecimal getRaIn_ServiceCharge() {
		return raIn_ServiceCharge;
	}
	public void setRaIn_ServiceCharge(BigDecimal raIn_ServiceCharge) {
		this.raIn_ServiceCharge = raIn_ServiceCharge;
	}
	public String getElpiElectricpilecode() {
		return elpiElectricpilecode;
	}
	public void setElpiElectricpilecode(String elpiElectricpilecode) {
		this.elpiElectricpilecode = elpiElectricpilecode;
	}
	public String getElpiChargingmode() {
		return elpiChargingmode;
	}
	public void setElpiChargingmode(String elpiChargingmode) {
		this.elpiChargingmode = elpiChargingmode;
	}
	public String getElpiPowersize() {
		return elpiPowersize;
	}
	public void setElpiPowersize(String elpiPowersize) {
		this.elpiPowersize = elpiPowersize;
	}
	public String getElpiPowerinterface() {
		return elpiPowerinterface;
	}
	public void setElpiPowerinterface(String elpiPowerinterface) {
		this.elpiPowerinterface = elpiPowerinterface;
	}
	private List<PowerElectricPileHead>  pileHeadList;//枪口详情
	
	
	public BigDecimal getRaIn_ReservationRate() {
		return raIn_ReservationRate;
	}
	public void setRaIn_ReservationRate(BigDecimal raIn_ReservationRate) {
		this.raIn_ReservationRate = raIn_ReservationRate;
	}
	public String getElpiState() {
		return elpiState;
	}
	public void setElpiState(String elpiState) {
		this.elpiState = elpiState;
	}
	public String getElictricPicId() {
		return elictricPicId;
	}
	public void setElictricPicId(String elictricPicId) {
		this.elictricPicId = elictricPicId;
	}

	public String getPicPowerSum() {
		return picPowerSum;
	}
	public void setPicPowerSum(String picPowerSum) {
		this.picPowerSum = picPowerSum;
	}
	public List<PowerElectricPileHead> getPileHeadList() {
		return pileHeadList;
	}
	public void setPileHeadList(List<PowerElectricPileHead> pileHeadList) {
		this.pileHeadList = pileHeadList;
	}
	public String getElictricPicName() {
		return elictricPicName;
	}
	public void setElictricPicName(String elictricPicName) {
		this.elictricPicName = elictricPicName;
	}
	public String getElictricPicImage() {
		return elictricPicImage;
	}
	public void setElictricPicImage(String elictricPicImage) {
		this.elictricPicImage = elictricPicImage;
	}
	public int getRateId() {
		return rateId;
	}
	public void setRateId(int rateId) {
		this.rateId = rateId;
	}
	
	
	
}

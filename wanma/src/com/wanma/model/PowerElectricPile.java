package com.wanma.model;

import java.util.List;

/**
 * 电桩实体bean
 * @author wangfei
 *
 */
public class PowerElectricPile {

	private static final long serialVersionUID = 3050775264484769401L;
	private java.lang.String pkElectricpile; // 主键
	private java.lang.String elpiElectricpilename; // 电桩名称
	private java.lang.String elpiElectricpilecode; // 桩体编号
	private java.lang.String elpiElectricpileaddress; // 地址

	private java.lang.Integer elpiPowernumber; // 电桩枪口数量
	private java.lang.String elpiState; // 电桩状态（0-草稿 5-提交审核 3-已驳回 10-离线15-上线）
	private java.lang.String elpiType; // 电桩类型，配置参数内容的ID （落地式，壁挂式，拉杆式，便携式）
	private java.lang.Integer elpiPoweruser; // 电桩用途，配置参数内容的ID（电动车，电动自行车，多功能（电动车、电动自行车、手机USB））
	private java.lang.Integer elpiChargingmode; // 电桩充电方式，配置参数内容的ID
												// （直流充电桩，交流充电桩）
	private java.lang.Integer elpiPowersize; // 电桩额定功率，配置参数内容的ID（3.5kw，7kw，20kw，50kw，75kw）
	private java.lang.Integer elpiPowerinterface; // 电桩接口方式，配置参数内容的ID（国标、欧标、美标）7国标，19美标，20欧标
	private java.lang.Integer elpiMaker; // 电桩制造商，配置参数内容的ID
											// （万马新能源，南京循道，北京三优，上海普天）
	private java.lang.String elpiOutputvoltage; // 电桩额定输出电压
	private java.lang.String elpiInputvoltage; // 电桩额定输入电压
	private java.lang.String elpiOutputcurrent; // 电桩额定输出电流
	private java.lang.String elpiInputcurrent; // 电桩额定输入电流
	private java.lang.String comm_status;//连接状态
	private List<PowerElectricPileHead> list;
	
	
	public java.lang.String getComm_status() {
		return comm_status;
	}

	public void setComm_status(java.lang.String comm_status) {
		this.comm_status = comm_status;
	}

	

	public java.lang.String getPkElectricpile() {
		return pkElectricpile;
	}

	public void setPkElectricpile(java.lang.String pkElectricpile) {
		this.pkElectricpile = pkElectricpile;
	}

	public java.lang.String getElpiElectricpilename() {
		return elpiElectricpilename;
	}

	public void setElpiElectricpilename(java.lang.String elpiElectricpilename) {
		this.elpiElectricpilename = elpiElectricpilename;
	}

	public java.lang.String getElpiElectricpilecode() {
		return elpiElectricpilecode;
	}

	public void setElpiElectricpilecode(java.lang.String elpiElectricpilecode) {
		this.elpiElectricpilecode = elpiElectricpilecode;
	}

	public java.lang.String getElpiElectricpileaddress() {
		return elpiElectricpileaddress;
	}

	public void setElpiElectricpileaddress(java.lang.String elpiElectricpileaddress) {
		this.elpiElectricpileaddress = elpiElectricpileaddress;
	}

	public java.lang.Integer getElpiPowernumber() {
		return elpiPowernumber;
	}

	public void setElpiPowernumber(java.lang.Integer elpiPowernumber) {
		this.elpiPowernumber = elpiPowernumber;
	}

	public java.lang.String getElpiState() {
		return elpiState;
	}

	public void setElpiState(java.lang.String elpiState) {
		this.elpiState = elpiState;
	}

	public java.lang.String getElpiType() {
		return elpiType;
	}

	public void setElpiType(java.lang.String elpiType) {
		this.elpiType = elpiType;
	}

	public java.lang.Integer getElpiPoweruser() {
		return elpiPoweruser;
	}

	public void setElpiPoweruser(java.lang.Integer elpiPoweruser) {
		this.elpiPoweruser = elpiPoweruser;
	}

	public java.lang.Integer getElpiChargingmode() {
		return elpiChargingmode;
	}

	public void setElpiChargingmode(java.lang.Integer elpiChargingmode) {
		this.elpiChargingmode = elpiChargingmode;
	}

	public java.lang.Integer getElpiPowersize() {
		return elpiPowersize;
	}

	public void setElpiPowersize(java.lang.Integer elpiPowersize) {
		this.elpiPowersize = elpiPowersize;
	}

	public java.lang.Integer getElpiPowerinterface() {
		return elpiPowerinterface;
	}

	public void setElpiPowerinterface(java.lang.Integer elpiPowerinterface) {
		this.elpiPowerinterface = elpiPowerinterface;
	}

	public java.lang.Integer getElpiMaker() {
		return elpiMaker;
	}

	public void setElpiMaker(java.lang.Integer elpiMaker) {
		this.elpiMaker = elpiMaker;
	}

	public java.lang.String getElpiOutputvoltage() {
		return elpiOutputvoltage;
	}

	public void setElpiOutputvoltage(java.lang.String elpiOutputvoltage) {
		this.elpiOutputvoltage = elpiOutputvoltage;
	}

	public java.lang.String getElpiInputvoltage() {
		return elpiInputvoltage;
	}

	public void setElpiInputvoltage(java.lang.String elpiInputvoltage) {
		this.elpiInputvoltage = elpiInputvoltage;
	}

	public java.lang.String getElpiOutputcurrent() {
		return elpiOutputcurrent;
	}

	public void setElpiOutputcurrent(java.lang.String elpiOutputcurrent) {
		this.elpiOutputcurrent = elpiOutputcurrent;
	}

	public java.lang.String getElpiInputcurrent() {
		return elpiInputcurrent;
	}

	public void setElpiInputcurrent(java.lang.String elpiInputcurrent) {
		this.elpiInputcurrent = elpiInputcurrent;
	}

	public List<PowerElectricPileHead> getList() {
		return list;
	}

	public void setList(List<PowerElectricPileHead> list) {
		this.list = list;
	}
	
	
	
}

package com.wanma.model;

import java.math.BigDecimal;
import java.util.Date;

import com.pub.model.Entity;

/**
 * 
 * tbl_Bespoke表
 * 
 * @author songjf
 * 
 */
/**
 * 听管理
  * @Description:
  * @author songjf 
  * @createTime：2015-4-13 下午02:14:09 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
public class TblChargingOrder extends Entity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8542850474403929470L;
	private String pkChargingorder; // 主键
	private String chorCode; // 充电订单编号
	private String chorAppointmencode; // 预约流水号
	private String chorPilenumber; // 桩体编号
	private String chorUserid; // 用户ID(企业ID)
	private int chorType; // 用户类型
	private String chorMoeny; // (收益)金额
	private String chorQuantityelectricity; // 电量
	private String chorTimequantum; // 时间段
	private int chorMuzzle; //枪口编号
	private String chorChargingstatus; //订单状态
	private String chorTranstype; //交易类型
	private Date chorCreatedate; //创建时间
	private Date chorUpdatedate; // 修改时间
	private String chorUsername; // 用户姓名(企业姓名)
	private String chorTransactionnumber; // 交易流水号
	private int chorOrdertype; // 1支付宝 2银联
	private BigDecimal chorChargemoney;// 充电金额
	private BigDecimal chorServicemoney;//充电服务费金额
	private BigDecimal chOr_tipPower;//尖时段用电度数
	private BigDecimal chOr_peakPower;//峰时段用电度数
	private BigDecimal chOr_usualPower;//平时段用电度数
	private BigDecimal chOr_valleyPower;//谷时段用电度数
	
	private String beginChargetime;//充电开始时间
	private String endChargetime;//充电结束时间
	
	private  int chOrUserOrigin;
	private int pkUserCard;
	private String couponMoney;//优惠券抵扣金额
	private String couponCondition;//优惠券使用条件
	private String limitation;//电桩限制
	
	//不与数据库进行交互
	private String eleCode;		
	private String userPhone;//用户手机
	private String usName;
	private String goName;
	private String eleheadName;
	private String usPhone;
	private String comName;
	private String chorUser;
	private String loginUserId;
	private String userLevel;
	private String chargePointName;	 //充电点名称
	private String chargePointAddress;//充电点地址
	private String puhiInvoiceStatus;//开票状态
	private String ownerShip;//所有权
	private String elPiRateInformationId;//费率ID
	private String chReQuantumDate;//费率ID
	private String chReJPrice;//费率ID
	private String chReFPrice;//费率ID
	private String chRePPrice;//费率ID
	private String chReGPrice;//费率ID
	private String chReServiceCharge;//服务费
	
	
	private String chargingstatusName;//订单状态1：待支付 ,2：支付成功 ,3: 完成操作,4: 异常订单
	
	
	
	
	public int getPkUserCard() {
		return pkUserCard;
	}
	public void setPkUserCard(int pkUserCard) {
		this.pkUserCard = pkUserCard;
	}
	public int getChOrUserOrigin() {
		return chOrUserOrigin;
	}
	public void setChOrUserOrigin(int chOrUserOrigin) {
		this.chOrUserOrigin = chOrUserOrigin;
	}
	public String getEndChargetime() {
		return endChargetime;
	}
	public void setEndChargetime(String endChargetime) {
		this.endChargetime = endChargetime;
	}
	public String getBeginChargetime() {
		return beginChargetime;
	}
	public void setBeginChargetime(String beginChargetime) {
		this.beginChargetime = beginChargetime;
	}
	public BigDecimal getChOr_tipPower() {
		return chOr_tipPower;
	}
	public void setChOr_tipPower(BigDecimal chOr_tipPower) {
		this.chOr_tipPower = chOr_tipPower;
	}
	public BigDecimal getChOr_peakPower() {
		return chOr_peakPower;
	}
	public void setChOr_peakPower(BigDecimal chOr_peakPower) {
		this.chOr_peakPower = chOr_peakPower;
	}
	public BigDecimal getChOr_usualPower() {
		return chOr_usualPower;
	}
	public void setChOr_usualPower(BigDecimal chOr_usualPower) {
		this.chOr_usualPower = chOr_usualPower;
	}
	public BigDecimal getChOr_valleyPower() {
		return chOr_valleyPower;
	}
	public void setChOr_valleyPower(BigDecimal chOr_valleyPower) {
		this.chOr_valleyPower = chOr_valleyPower;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getUsPhone() {
		return usPhone;
	}
	public void setUsPhone(String usPhone) {
		this.usPhone = usPhone;
	}
	public String getEleCode() {
		return eleCode;
	}
	public void setEleCode(String eleCode) {
		this.eleCode = eleCode;
	}
	public String getUsName() {
		return usName;
	}
	public void setUsName(String usName) {
		this.usName = usName;
	}
	public String getGoName() {
		return goName;
	}
	public void setGoName(String goName) {
		this.goName = goName;
	}
	public String getEleheadName() {
		return eleheadName;
	}
	public void setEleheadName(String eleheadName) {
		this.eleheadName = eleheadName;
	}
	public String getPkChargingorder() {
		return pkChargingorder;
	}
	public void setPkChargingorder(String pkChargingorder) {
		this.pkChargingorder = pkChargingorder;
	}
	public String getChorCode() {
		return chorCode;
	}
	public void setChorCode(String chorCode) {
		this.chorCode = chorCode;
	}
	public String getChorAppointmencode() {
		return chorAppointmencode;
	}
	public void setChorAppointmencode(String chorAppointmencode) {
		this.chorAppointmencode = chorAppointmencode;
	}
	public String getChorPilenumber() {
		return chorPilenumber;
	}
	public void setChorPilenumber(String chorPilenumber) {
		this.chorPilenumber = chorPilenumber;
	}
	public String getChorUserid() {
		return chorUserid;
	}
	public void setChorUserid(String chorUserid) {
		this.chorUserid = chorUserid;
	}
	public int getChorType() {
		return chorType;
	}
	public void setChorType(int chorType) {
		this.chorType = chorType;
	}
	public String getChorMoeny() {
		return chorMoeny;
	}
	public void setChorMoeny(String chorMoeny) {
		this.chorMoeny = chorMoeny;
	}
	public String getChorQuantityelectricity() {
		return chorQuantityelectricity;
	}
	public void setChorQuantityelectricity(String chorQuantityelectricity) {
		this.chorQuantityelectricity = chorQuantityelectricity;
	}
	public String getChorTimequantum() {
		return chorTimequantum;
	}
	public void setChorTimequantum(String chorTimequantum) {
		this.chorTimequantum = chorTimequantum;
	}
	
	public int getChorMuzzle() {
		return chorMuzzle;
	}
	public void setChorMuzzle(int chorMuzzle) {
		this.chorMuzzle = chorMuzzle;
	}
	public String getChorChargingstatus() {
		return chorChargingstatus;
	}
	public void setChorChargingstatus(String chorChargingstatus) {
		this.chorChargingstatus = chorChargingstatus;
	}
	public String getChorTranstype() {
		return chorTranstype;
	}
	public void setChorTranstype(String chorTranstype) {
		this.chorTranstype = chorTranstype;
	}
	public Date getChorCreatedate() {
		return chorCreatedate;
	}
	public void setChorCreatedate(Date chorCreatedate) {
		this.chorCreatedate = chorCreatedate;
	}
	public Date getChorUpdatedate() {
		return chorUpdatedate;
	}
	public void setChorUpdatedate(Date chorUpdatedate) {
		this.chorUpdatedate = chorUpdatedate;
	}
	public String getChorUsername() {
		return chorUsername;
	}
	public void setChorUsername(String chorUsername) {
		this.chorUsername = chorUsername;
	}
	public String getChorTransactionnumber() {
		return chorTransactionnumber;
	}
	public void setChorTransactionnumber(String chorTransactionnumber) {
		this.chorTransactionnumber = chorTransactionnumber;
	}
	public int getChorOrdertype() {
		return chorOrdertype;
	}
	public void setChorOrdertype(int chorOrdertype) {
		this.chorOrdertype = chorOrdertype;
	}
	public BigDecimal getChorChargemoney() {
		return chorChargemoney;
	}
	public void setChorChargemoney(BigDecimal chorChargemoney) {
		this.chorChargemoney = chorChargemoney;
	}
	public BigDecimal getChorServicemoney() {
		return chorServicemoney;
	}
	public void setChorServicemoney(BigDecimal chorServicemoney) {
		this.chorServicemoney = chorServicemoney;
	}
	public String getChorUser() {
		return chorUser;
	}
	public void setChorUser(String chorUser) {
		this.chorUser = chorUser;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String longinUserId) {
		this.loginUserId = longinUserId;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	
	public String getChargePointName() {
		return chargePointName;
	}
	public void setChargePointName(String chargePointName) {
		this.chargePointName = chargePointName;
	}
	public String getChargePointAddress() {
		return chargePointAddress;
	}
	public void setChargePointAddress(String chargePointAddress) {
		this.chargePointAddress = chargePointAddress;
	}
	public String getPuhiInvoiceStatus() {
		return puhiInvoiceStatus;
	}
	public void setPuhiInvoiceStatus(String puhiInvoiceStatus) {
		this.puhiInvoiceStatus = puhiInvoiceStatus;
	}
	public String getOwnerShip() {
		return ownerShip;
	}
	public void setOwnerShip(String ownerShip) {
		this.ownerShip = ownerShip;
	}
	public String getElPiRateInformationId() {
		return elPiRateInformationId;
	}
	public void setElPiRateInformationId(String elPiRateInformationId) {
		this.elPiRateInformationId = elPiRateInformationId;
	}
	public String getChReQuantumDate() {
		return chReQuantumDate;
	}
	public void setChReQuantumDate(String chReQuantumDate) {
		this.chReQuantumDate = chReQuantumDate;
	}
	public String getChReJPrice() {
		return chReJPrice;
	}
	public void setChReJPrice(String chReJPrice) {
		this.chReJPrice = chReJPrice;
	}
	public String getChReFPrice() {
		return chReFPrice;
	}
	public void setChReFPrice(String chReFPrice) {
		this.chReFPrice = chReFPrice;
	}
	public String getChRePPrice() {
		return chRePPrice;
	}
	public void setChRePPrice(String chRePPrice) {
		this.chRePPrice = chRePPrice;
	}
	public String getChReGPrice() {
		return chReGPrice;
	}
	public void setChReGPrice(String chReGPrice) {
		this.chReGPrice = chReGPrice;
	}

	public String getChReServiceCharge() {
		return chReServiceCharge;
	}
	public void setChReServiceCharge(String chReServiceCharge) {
		this.chReServiceCharge = chReServiceCharge;
	}
	
	public String getCouponMoney() {
		return couponMoney;
	}
	public void setCouponMoney(String couponMoney) {
		this.couponMoney = couponMoney;
	}
	public String getCouponCondition() {
		return couponCondition;
	}
	public void setCouponCondition(String couponCondition) {
		this.couponCondition = couponCondition;
	}
	public String getLimitation() {
		return limitation;
	}
	public void setLimitation(String limitation) {
		this.limitation = limitation;
	}
	
	
	public String getChargingstatusName() {//1：待支付 ,2：支付成功 ,3: 完成操作,4: 异常订单
		switch (new Integer(chorChargingstatus)) {
		case 1:
			return "待支付";
		case 2:
			return "支付成功";
		case 3:
			return "完成操作";
		case 4:
			return "异常订单";
		default:
			return "未知";
		}
	}
	public void setChargingstatusName(String chargingstatusName) {
		this.chargingstatusName = chargingstatusName;
	}
	@Override
	public String toString() {
		return "TblChargingOrder [pkChargingorder=" + pkChargingorder
				+ ", chorCode=" + chorCode + ", chorAppointmencode="
				+ chorAppointmencode + ", chorPilenumber=" + chorPilenumber
				+ ", chorUserid=" + chorUserid + ", chorType=" + chorType
				+ ", chorMoeny=" + chorMoeny + ", chorQuantityelectricity="
				+ chorQuantityelectricity + ", chorTimequantum="
				+ chorTimequantum + ", chorMuzzle=" + chorMuzzle
				+ ", chorChargingstatus=" + chorChargingstatus
				+ ", chorTranstype=" + chorTranstype + ", chorCreatedate="
				+ chorCreatedate + ", chorUpdatedate=" + chorUpdatedate
				+ ", chorUsername=" + chorUsername + ", chorTransactionnumber="
				+ chorTransactionnumber + ", chorOrdertype=" + chorOrdertype
				+ ", chorChargemoney=" + chorChargemoney
				+ ", chorServicemoney=" + chorServicemoney + ", chOr_tipPower="
				+ chOr_tipPower + ", chOr_peakPower=" + chOr_peakPower
				+ ", chOr_usualPower=" + chOr_usualPower
				+ ", chOr_valleyPower=" + chOr_valleyPower
				+ ", beginChargetime=" + beginChargetime + ", endChargetime="
				+ endChargetime + ", chOrUserOrigin=" + chOrUserOrigin
				+ ", pkUserCard=" + pkUserCard + ", couponMoney=" + couponMoney
				+ ", couponCondition=" + couponCondition + ", limitation="
				+ limitation + ", eleCode=" + eleCode + ", userPhone="
				+ userPhone + ", usName=" + usName + ", goName=" + goName
				+ ", eleheadName=" + eleheadName + ", usPhone=" + usPhone
				+ ", comName=" + comName + ", chorUser=" + chorUser
				+ ", loginUserId=" + loginUserId + ", userLevel=" + userLevel
				+ ", chargePointName=" + chargePointName
				+ ", chargePointAddress=" + chargePointAddress
				+ ", puhiInvoiceStatus=" + puhiInvoiceStatus + ", ownerShip="
				+ ownerShip + ", elPiRateInformationId="
				+ elPiRateInformationId + ", chReQuantumDate="
				+ chReQuantumDate + ", chReJPrice=" + chReJPrice
				+ ", chReFPrice=" + chReFPrice + ", chRePPrice=" + chRePPrice
				+ ", chReGPrice=" + chReGPrice + "]";
	}
	
	
	
	
}
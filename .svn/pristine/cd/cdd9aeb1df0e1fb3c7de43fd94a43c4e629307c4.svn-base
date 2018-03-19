package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 费率管理 
 * @author bingo
 * @date 2017-06-26
 */
public class RateInfoDO extends BasicModel {

	private static final long serialVersionUID = 483346985370298951L;
	private Integer pkId; //历史表主键
	private Long pk_RateInformation;//费率表主键
	private Integer raIn_ModelId;//计费模型ID
	private String raIn_Name;//费率名称
	private Date raIn_EffectiveDates;//生效日期
	private Date raIn_ExpiryDate;//失效日期
	private BigDecimal raIn_FreezingMoney; //预冻结金额
	private BigDecimal raIn_MinFreezingMoney; //最小冻结金额
	private String raIn_QuantumDate; //json格式的费率 {"st":"0","et":"120","mark":"1"} st开始时间单位分钟，et结束时间单位分钟，mark类型，1尖2峰3平4谷
	private Date raIn_StartQuantumDate;//开始时间段
	private Date raIn_EndQuantumDate;//结束时间段
	private BigDecimal raIn_TimeMarker;//时段标志
	private BigDecimal raIn_TipTimeTariffPrice;//尖时段电价
	private BigDecimal raIn_TipTimeTariffMoney;//尖时段服务费
	private BigDecimal raIn_PeakElectricityPrice;//峰时段电价
	private BigDecimal raIn_PeakElectricityMoney;//峰时间段服务费
	private BigDecimal raIn_UsualPrice;//平时段电价
	private BigDecimal raIn_UsualMoney;//平时段服务费
	private BigDecimal raIn_ValleyTimePrice;//谷时段电价
	private BigDecimal raIn_ValleyTimeMoney;//谷时段服务费
	private BigDecimal raIn_ReservationRate;//预约单价
	private BigDecimal raIn_ServiceCharge;//服务费
	private BigDecimal raIn_WarnMoney;//告警余额
	private String raIn_area_id;//区县编号
	private String raIn_city_id;//市级编号
	private String raIn_province_id;//省级编号
	private String raIn_remarks;//备注
	private Integer raIn_type;//1:费率2位，2：费率4位
	private String creator_id;//添加费率的用户，p_m_user表的id
	private Date raIn_Createdate;//创建时间
	private Date raIn_Updatedate;//修改时间
	
	private String rateInfoIds;//桩ID拼接字符串
	
	//统一价
	private BigDecimal uniformPrice;

	private BigDecimal qryPrice;
	
	public Integer getPkId() {
		return pkId;
	}
	public void setPkId(Integer pkId) {
		this.pkId = pkId;
	}
	public Long getPk_RateInformation() {
		return pk_RateInformation;
	}
	public void setPk_RateInformation(Long pk_RateInformation) {
		this.pk_RateInformation = pk_RateInformation;
	}
	public Integer getRaIn_ModelId() {
		return raIn_ModelId;
	}
	public void setRaIn_ModelId(Integer raIn_ModelId) {
		this.raIn_ModelId = raIn_ModelId;
	}
	public String getRaIn_Name() {
		return raIn_Name;
	}
	public void setRaIn_Name(String raIn_Name) {
		this.raIn_Name = raIn_Name;
	}
	public Date getRaIn_EffectiveDates() {
		return raIn_EffectiveDates;
	}
	public void setRaIn_EffectiveDates(Date raIn_EffectiveDates) {
		this.raIn_EffectiveDates = raIn_EffectiveDates;
	}
	public Date getRaIn_ExpiryDate() {
		return raIn_ExpiryDate;
	}
	public void setRaIn_ExpiryDate(Date raIn_ExpiryDate) {
		this.raIn_ExpiryDate = raIn_ExpiryDate;
	}
	public BigDecimal getRaIn_FreezingMoney() {
		return raIn_FreezingMoney;
	}
	public void setRaIn_FreezingMoney(BigDecimal raIn_FreezingMoney) {
		this.raIn_FreezingMoney = raIn_FreezingMoney;
	}
	public BigDecimal getRaIn_MinFreezingMoney() {
		return raIn_MinFreezingMoney;
	}
	public void setRaIn_MinFreezingMoney(BigDecimal raIn_MinFreezingMoney) {
		this.raIn_MinFreezingMoney = raIn_MinFreezingMoney;
	}
	public String getRaIn_QuantumDate() {
		return raIn_QuantumDate;
	}
	public void setRaIn_QuantumDate(String raIn_QuantumDate) {
		this.raIn_QuantumDate = raIn_QuantumDate;
	}
	public Date getRaIn_StartQuantumDate() {
		return raIn_StartQuantumDate;
	}
	public void setRaIn_StartQuantumDate(Date raIn_StartQuantumDate) {
		this.raIn_StartQuantumDate = raIn_StartQuantumDate;
	}
	public Date getRaIn_EndQuantumDate() {
		return raIn_EndQuantumDate;
	}
	public void setRaIn_EndQuantumDate(Date raIn_EndQuantumDate) {
		this.raIn_EndQuantumDate = raIn_EndQuantumDate;
	}
	public BigDecimal getRaIn_TimeMarker() {
		return raIn_TimeMarker;
	}
	public void setRaIn_TimeMarker(BigDecimal raIn_TimeMarker) {
		this.raIn_TimeMarker = raIn_TimeMarker;
	}
	public BigDecimal getRaIn_TipTimeTariffPrice() {
		return raIn_TipTimeTariffPrice;
	}
	public void setRaIn_TipTimeTariffPrice(BigDecimal raIn_TipTimeTariffPrice) {
		this.raIn_TipTimeTariffPrice = raIn_TipTimeTariffPrice;
	}
	public BigDecimal getRaIn_TipTimeTariffMoney() {
		return raIn_TipTimeTariffMoney;
	}
	public void setRaIn_TipTimeTariffMoney(BigDecimal raIn_TipTimeTariffMoney) {
		this.raIn_TipTimeTariffMoney = raIn_TipTimeTariffMoney;
	}
	public BigDecimal getRaIn_PeakElectricityPrice() {
		return raIn_PeakElectricityPrice;
	}
	public void setRaIn_PeakElectricityPrice(BigDecimal raIn_PeakElectricityPrice) {
		this.raIn_PeakElectricityPrice = raIn_PeakElectricityPrice;
	}
	public BigDecimal getRaIn_PeakElectricityMoney() {
		return raIn_PeakElectricityMoney;
	}
	public void setRaIn_PeakElectricityMoney(BigDecimal raIn_PeakElectricityMoney) {
		this.raIn_PeakElectricityMoney = raIn_PeakElectricityMoney;
	}
	public BigDecimal getRaIn_UsualPrice() {
		return raIn_UsualPrice;
	}
	public void setRaIn_UsualPrice(BigDecimal raIn_UsualPrice) {
		this.raIn_UsualPrice = raIn_UsualPrice;
	}
	public BigDecimal getRaIn_UsualMoney() {
		return raIn_UsualMoney;
	}
	public void setRaIn_UsualMoney(BigDecimal raIn_UsualMoney) {
		this.raIn_UsualMoney = raIn_UsualMoney;
	}
	public BigDecimal getRaIn_ValleyTimePrice() {
		return raIn_ValleyTimePrice;
	}
	public void setRaIn_ValleyTimePrice(BigDecimal raIn_ValleyTimePrice) {
		this.raIn_ValleyTimePrice = raIn_ValleyTimePrice;
	}
	public BigDecimal getRaIn_ValleyTimeMoney() {
		return raIn_ValleyTimeMoney;
	}
	public void setRaIn_ValleyTimeMoney(BigDecimal raIn_ValleyTimeMoney) {
		this.raIn_ValleyTimeMoney = raIn_ValleyTimeMoney;
	}
	public BigDecimal getRaIn_ReservationRate() {
		return raIn_ReservationRate;
	}
	public void setRaIn_ReservationRate(BigDecimal raIn_ReservationRate) {
		this.raIn_ReservationRate = raIn_ReservationRate;
	}
	public BigDecimal getRaIn_ServiceCharge() {
		return raIn_ServiceCharge;
	}
	public void setRaIn_ServiceCharge(BigDecimal raIn_ServiceCharge) {
		this.raIn_ServiceCharge = raIn_ServiceCharge;
	}
	public BigDecimal getRaIn_WarnMoney() {
		return raIn_WarnMoney;
	}
	public void setRaIn_WarnMoney(BigDecimal raIn_WarnMoney) {
		this.raIn_WarnMoney = raIn_WarnMoney;
	}
	public String getRaIn_area_id() {
		return raIn_area_id;
	}
	public void setRaIn_area_id(String raIn_area_id) {
		this.raIn_area_id = raIn_area_id;
	}
	public String getRaIn_city_id() {
		return raIn_city_id;
	}
	public void setRaIn_city_id(String raIn_city_id) {
		this.raIn_city_id = raIn_city_id;
	}
	public String getRaIn_province_id() {
		return raIn_province_id;
	}
	public void setRaIn_province_id(String raIn_province_id) {
		this.raIn_province_id = raIn_province_id;
	}
	public String getRaIn_remarks() {
		return raIn_remarks;
	}
	public void setRaIn_remarks(String raIn_remarks) {
		this.raIn_remarks = raIn_remarks;
	}
	public Integer getRaIn_type() {
		return raIn_type;
	}
	public void setRaIn_type(Integer raIn_type) {
		this.raIn_type = raIn_type;
	}
	public String getCreator_id() {
		return creator_id;
	}
	public void setCreator_id(String creator_id) {
		this.creator_id = creator_id;
	}
	public Date getRaIn_Createdate() {
		return raIn_Createdate;
	}
	public void setRaIn_Createdate(Date raIn_Createdate) {
		this.raIn_Createdate = raIn_Createdate;
	}
	public Date getRaIn_Updatedate() {
		return raIn_Updatedate;
	}
	public void setRaIn_Updatedate(Date raIn_Updatedate) {
		this.raIn_Updatedate = raIn_Updatedate;
	}
	public BigDecimal getUniformPrice() {
		return uniformPrice;
	}
	public void setUniformPrice(BigDecimal uniformPrice) {
		this.uniformPrice = uniformPrice;
	}
	public String getRateInfoIds() {
		return rateInfoIds;
	}
	public void setRateInfoIds(String rateInfoIds) {
		this.rateInfoIds = rateInfoIds;
	}

	public BigDecimal getQryPrice() {
		return qryPrice;
	}

	public void setQryPrice(BigDecimal qryPrice) {
		this.qryPrice = qryPrice;
	}
}
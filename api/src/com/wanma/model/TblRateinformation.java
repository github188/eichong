package com.wanma.model;

/**
 * 
 * tbl_RateInformation表
 * 
 * @author songjf
 * 
 */
public class TblRateinformation {
	private java.lang.Integer pkRateinformation; // 主键
	private java.lang.Integer rainModelid; // 计费模型ID
	private java.util.Date rainEffectivedates; // 生效日期
	private java.util.Date rainExpirydate; // 失效日期
	private java.math.BigDecimal rainFreezingmoney; // 预冻结金额
	private java.math.BigDecimal rainMinfreezingmoney; // 最小冻结金额
	private java.lang.String rainQuantumdate; // 采用JSON形式存储实际格式
	private java.util.Date rainStartquantumdate; // 开始时间段
	private java.util.Date rainEndquantumdate; // 结束时间段
	private java.lang.Integer rainTimemarker; // 时段标志
	private java.math.BigDecimal rainTiptimetariff; // 尖时段电价
	private java.math.BigDecimal rainPeakelectricityprice; // 峰时段电价
	private java.math.BigDecimal rainUsualprice; // 平时段电价
	private java.math.BigDecimal rainValleytimeprice; // 谷时段电价
	private java.math.BigDecimal rainReservationrate; // 服务费率
	private java.math.BigDecimal rainServicecharge; // 服务费
	private java.math.BigDecimal rainWarnmoney; // 告警余额

	/**
	 * 获取主键属性
	 * 
	 * @return pkRateinformation
	 */
	public java.lang.Integer getPkRateinformation() {
		return pkRateinformation;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkRateinformation
	 */
	public void setPkRateinformation(java.lang.Integer pkRateinformation) {
		this.pkRateinformation = pkRateinformation;
	}

	/**
	 * 获取计费模型ID属性
	 * 
	 * @return rainModelid
	 */
	public java.lang.Integer getRainModelid() {
		return rainModelid;
	}

	/**
	 * 设置计费模型ID属性
	 * 
	 * @param rainModelid
	 */
	public void setRainModelid(java.lang.Integer rainModelid) {
		this.rainModelid = rainModelid;
	}

	/**
	 * 获取生效日期属性
	 * 
	 * @return rainEffectivedates
	 */
	public java.util.Date getRainEffectivedates() {
		return rainEffectivedates;
	}

	/**
	 * 设置生效日期属性
	 * 
	 * @param rainEffectivedates
	 */
	public void setRainEffectivedates(java.util.Date rainEffectivedates) {
		this.rainEffectivedates = rainEffectivedates;
	}

	/**
	 * 获取失效日期属性
	 * 
	 * @return rainExpirydate
	 */
	public java.util.Date getRainExpirydate() {
		return rainExpirydate;
	}

	/**
	 * 设置失效日期属性
	 * 
	 * @param rainExpirydate
	 */
	public void setRainExpirydate(java.util.Date rainExpirydate) {
		this.rainExpirydate = rainExpirydate;
	}

	/**
	 * 获取预冻结金额属性
	 * 
	 * @return rainFreezingmoney
	 */
	public java.math.BigDecimal getRainFreezingmoney() {
		return rainFreezingmoney;
	}

	/**
	 * 设置预冻结金额属性
	 * 
	 * @param rainFreezingmoney
	 */
	public void setRainFreezingmoney(java.math.BigDecimal rainFreezingmoney) {
		this.rainFreezingmoney = rainFreezingmoney;
	}

	/**
	 * 获取最小冻结金额属性
	 * 
	 * @return rainMinfreezingmoney
	 */
	public java.math.BigDecimal getRainMinfreezingmoney() {
		return rainMinfreezingmoney;
	}

	/**
	 * 设置最小冻结金额属性
	 * 
	 * @param rainMinfreezingmoney
	 */
	public void setRainMinfreezingmoney(
			java.math.BigDecimal rainMinfreezingmoney) {
		this.rainMinfreezingmoney = rainMinfreezingmoney;
	}

	/**
	 * 获取采用JSON形式存储实际格式属性
	 * 
	 * @return rainQuantumdate
	 */
	public java.lang.String getRainQuantumdate() {
		return rainQuantumdate;
	}

	/**
	 * 设置采用JSON形式存储实际格式属性
	 * 
	 * @param rainQuantumdate
	 */
	public void setRainQuantumdate(java.lang.String rainQuantumdate) {
		this.rainQuantumdate = rainQuantumdate;
	}

	/**
	 * 获取开始时间段属性
	 * 
	 * @return rainStartquantumdate
	 */
	public java.util.Date getRainStartquantumdate() {
		return rainStartquantumdate;
	}

	/**
	 * 设置开始时间段属性
	 * 
	 * @param rainStartquantumdate
	 */
	public void setRainStartquantumdate(java.util.Date rainStartquantumdate) {
		this.rainStartquantumdate = rainStartquantumdate;
	}

	/**
	 * 获取结束时间段属性
	 * 
	 * @return rainEndquantumdate
	 */
	public java.util.Date getRainEndquantumdate() {
		return rainEndquantumdate;
	}

	/**
	 * 设置结束时间段属性
	 * 
	 * @param rainEndquantumdate
	 */
	public void setRainEndquantumdate(java.util.Date rainEndquantumdate) {
		this.rainEndquantumdate = rainEndquantumdate;
	}

	/**
	 * 获取时段标志属性
	 * 
	 * @return rainTimemarker
	 */
	public java.lang.Integer getRainTimemarker() {
		return rainTimemarker;
	}

	/**
	 * 设置时段标志属性
	 * 
	 * @param rainTimemarker
	 */
	public void setRainTimemarker(java.lang.Integer rainTimemarker) {
		this.rainTimemarker = rainTimemarker;
	}

	/**
	 * 获取尖时段电价属性
	 * 
	 * @return rainTiptimetariff
	 */
	public java.math.BigDecimal getRainTiptimetariff() {
		return rainTiptimetariff;
	}

	/**
	 * 设置尖时段电价属性
	 * 
	 * @param rainTiptimetariff
	 */
	public void setRainTiptimetariff(java.math.BigDecimal rainTiptimetariff) {
		this.rainTiptimetariff = rainTiptimetariff;
	}

	/**
	 * 获取峰时段电价属性
	 * 
	 * @return rainPeakelectricityprice
	 */
	public java.math.BigDecimal getRainPeakelectricityprice() {
		return rainPeakelectricityprice;
	}

	/**
	 * 设置峰时段电价属性
	 * 
	 * @param rainPeakelectricityprice
	 */
	public void setRainPeakelectricityprice(
			java.math.BigDecimal rainPeakelectricityprice) {
		this.rainPeakelectricityprice = rainPeakelectricityprice;
	}

	/**
	 * 获取平时段电价属性
	 * 
	 * @return rainUsualprice
	 */
	public java.math.BigDecimal getRainUsualprice() {
		return rainUsualprice;
	}

	/**
	 * 设置平时段电价属性
	 * 
	 * @param rainUsualprice
	 */
	public void setRainUsualprice(java.math.BigDecimal rainUsualprice) {
		this.rainUsualprice = rainUsualprice;
	}

	/**
	 * 获取谷时段电价属性
	 * 
	 * @return rainValleytimeprice
	 */
	public java.math.BigDecimal getRainValleytimeprice() {
		return rainValleytimeprice;
	}

	/**
	 * 设置谷时段电价属性
	 * 
	 * @param rainValleytimeprice
	 */
	public void setRainValleytimeprice(java.math.BigDecimal rainValleytimeprice) {
		this.rainValleytimeprice = rainValleytimeprice;
	}

	/**
	 * 获取谷时段电价属性
	 * 
	 * @return rainReservationrate
	 */
	public java.math.BigDecimal getRainReservationrate() {
		return rainReservationrate;
	}

	/**
	 * 设置谷时段电价属性
	 * 
	 * @param rainReservationrate
	 */
	public void setRainReservationrate(java.math.BigDecimal rainReservationrate) {
		this.rainReservationrate = rainReservationrate;
	}

	/**
	 * 获取服务费属性
	 * 
	 * @return rainServicecharge
	 */
	public java.math.BigDecimal getRainServicecharge() {
		return rainServicecharge;
	}

	/**
	 * 设置服务费属性
	 * 
	 * @param rainServicecharge
	 */
	public void setRainServicecharge(java.math.BigDecimal rainServicecharge) {
		this.rainServicecharge = rainServicecharge;
	}
	
	/**
	 * 获取告警余额属性
	 * 
	 * @param rainWarnmoney
	 */
	public java.math.BigDecimal getRainWarnmoney() {
		return rainWarnmoney;
	}
	
	/**
	 * 设置告警余额属性
	 * 
	 * @param rainWarnmoney
	 */
	public void setRainWarnmoney(java.math.BigDecimal rainWarnmoney) {
		this.rainWarnmoney = rainWarnmoney;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblRateinformation");
		sb.append("{pkRateinformation=").append(pkRateinformation);
		sb.append(", rainModelid=").append(rainModelid);
		sb.append(", rainEffectivedates=").append(rainEffectivedates);
		sb.append(", rainExpirydate=").append(rainExpirydate);
		sb.append(", rainFreezingmoney=").append(rainFreezingmoney);
		sb.append(", rainMinfreezingmoney=").append(rainMinfreezingmoney);
		sb.append(", rainQuantumdate=").append(rainQuantumdate);
		sb.append(", rainStartquantumdate=").append(rainStartquantumdate);
		sb.append(", rainEndquantumdate=").append(rainEndquantumdate);
		sb.append(", rainTimemarker=").append(rainTimemarker);
		sb.append(", rainTiptimetariff=").append(rainTiptimetariff);
		sb.append(", rainPeakelectricityprice=").append(
				rainPeakelectricityprice);
		sb.append(", rainUsualprice=").append(rainUsualprice);
		sb.append(", rainValleytimeprice=").append(rainValleytimeprice);
		sb.append(", rainReservationrate=").append(rainReservationrate);
		sb.append(", rainServicecharge=").append(rainServicecharge);
		sb.append('}');
		return sb.toString();
	}
}
package com.wanma.model;

import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_CarInfo表
 * 
 * @author mew
 * 
 */
public class TblCarinfo extends BasicListAndMutiFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8504782827678496759L;
	private java.lang.Integer pkCarinfo; // 主键
	private java.lang.String carinfoStylename; // 电动车类型名称
	private java.math.BigDecimal carinfoMaxodometer; // 电动车最大续航里程
	private java.math.BigDecimal carinfoBatterycapacity; // 电动车电池容量
	private java.util.Date carinfoCreatedate; // 创建时间
	private java.util.Date carinfoUpdatedate; // 修改时间
	private java.lang.String carinfoRemark; // 备注
	private java.lang.String carinfoBrandicon; // 电动车品牌图标

	private java.lang.String carinfoCompanyId; // 电动车厂家

	private java.lang.Integer carInfocarstatus; // 状态(0启用，1禁用)
	
	
	
	
	
	public java.lang.String getCarinfoCompanyId() {
		return carinfoCompanyId;
	}




	public void setCarinfoCompanyId(java.lang.String carinfoCompanyId) {
		this.carinfoCompanyId = carinfoCompanyId;
	}




	/**
	 * 获取状态属性
	 * 
	 * @return carInfocarstatus
	 */
	public java.lang.Integer getCarInfocarstatus() {
		return carInfocarstatus;
	}




	/**
	 * 设置状态属性
	 * 
	 * @return carInfocarstatus
	 */
	public void setCarInfocarstatus(java.lang.Integer carInfocarstatus) {
		this.carInfocarstatus = carInfocarstatus;
	}

	/**
	 * 获取主键属性
	 * 
	 * @return pkCarinfo
	 */
	public java.lang.Integer getPkCarinfo() {
		return pkCarinfo;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkCarinfo
	 */
	public void setPkCarinfo(java.lang.Integer pkCarinfo) {
		this.pkCarinfo = pkCarinfo;
	}

	/**
	 * 获取电动车类型名称属性
	 * 
	 * @return carinfoStylename
	 */
	public java.lang.String getCarinfoStylename() {
		return carinfoStylename;
	}

	/**
	 * 设置电动车类型名称属性
	 * 
	 * @param carinfoStylename
	 */
	public void setCarinfoStylename(java.lang.String carinfoStylename) {
		this.carinfoStylename = carinfoStylename;
	}

	/**
	 * 获取电动车最大续航里程属性
	 * 
	 * @return carinfoMaxodometer
	 */
	public java.math.BigDecimal getCarinfoMaxodometer() {
		return carinfoMaxodometer;
	}

	/**
	 * 设置电动车最大续航里程属性
	 * 
	 * @param carinfoMaxodometer
	 */
	public void setCarinfoMaxodometer(java.math.BigDecimal carinfoMaxodometer) {
		this.carinfoMaxodometer = carinfoMaxodometer;
	}

	/**
	 * 获取电动车电池容量属性
	 * 
	 * @return carinfoBatterycapacity
	 */
	public java.math.BigDecimal getCarinfoBatterycapacity() {
		return carinfoBatterycapacity;
	}

	/**
	 * 设置电动车电池容量属性
	 * 
	 * @param carinfoBatterycapacity
	 */
	public void setCarinfoBatterycapacity(
			java.math.BigDecimal carinfoBatterycapacity) {
		this.carinfoBatterycapacity = carinfoBatterycapacity;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return carinfoCreatedate
	 */
	public java.util.Date getCarinfoCreatedate() {
		return carinfoCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param carinfoCreatedate
	 */
	public void setCarinfoCreatedate(java.util.Date carinfoCreatedate) {
		this.carinfoCreatedate = carinfoCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return carinfoUpdatedate
	 */
	public java.util.Date getCarinfoUpdatedate() {
		return carinfoUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param carinfoUpdatedate
	 */
	public void setCarinfoUpdatedate(java.util.Date carinfoUpdatedate) {
		this.carinfoUpdatedate = carinfoUpdatedate;
	}

	/**
	 * 获取备注属性
	 * 
	 * @return carinfoRemark
	 */
	public java.lang.String getCarinfoRemark() {
		return carinfoRemark;
	}

	/**
	 * 设置备注属性
	 * 
	 * @param carinfoRemark
	 */
	public void setCarinfoRemark(java.lang.String carinfoRemark) {
		this.carinfoRemark = carinfoRemark;
	}

	/**
	 * 获取电动车品牌图标属性
	 * 
	 * @return carinfoBrandicon
	 */
	public java.lang.String getCarinfoBrandicon() {
		return carinfoBrandicon;
	}

	/**
	 * 设置电动车品牌图标属性
	 * 
	 * @param carinfoBrandicon
	 */
	public void setCarinfoBrandicon(java.lang.String carinfoBrandicon) {
		this.carinfoBrandicon = carinfoBrandicon;
	}





	@Override
	public String toString() {
		return "TblCarinfo [pkCarinfo=" + pkCarinfo + ", carinfoStylename="
				+ carinfoStylename + ", carinfoMaxodometer="
				+ carinfoMaxodometer + ", carinfoBatterycapacity="
				+ carinfoBatterycapacity + ", carinfoCreatedate="
				+ carinfoCreatedate + ", carinfoUpdatedate="
				+ carinfoUpdatedate + ", carinfoRemark=" + carinfoRemark
				+ ", carinfoBrandicon=" + carinfoBrandicon				
				+ ", carinfoCompanyId=" + carinfoCompanyId
				+ ", carInfocarstatus=" + carInfocarstatus + "]";
	}


}
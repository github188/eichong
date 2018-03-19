package com.wanma.model;

import java.util.Date;

import com.pub.model.Entity;

/** 汽车品牌对象 */
public class TblCarcompany extends Entity {
	private Long pkCarcompany;
	private String carcompanyName;
	private Date carcompanyCreateDate;
	private Date carcompanyUpdateDate;
	private String carcompanyRemark;

	public Long getPkCarcompany() {
		return pkCarcompany;
	}

	public void setPkCarcompany(Long pkCarcompany) {
		this.pkCarcompany = pkCarcompany;
	}

	public String getCarcompanyName() {
		return carcompanyName;
	}

	public void setCarcompanyName(String carcompanyName) {
		this.carcompanyName = carcompanyName;
	}

	public Date getCarcompanyCreateDate() {
		return carcompanyCreateDate;
	}

	public void setCarcompanyCreateDate(Date carcompanyCreateDate) {
		this.carcompanyCreateDate = carcompanyCreateDate;
	}

	public Date getCarcompanyUpdateDate() {
		return carcompanyUpdateDate;
	}

	public void setCarcompanyUpdateDate(Date carcompanyUpdateDate) {
		this.carcompanyUpdateDate = carcompanyUpdateDate;
	}

	public String getCarcompanyRemark() {
		return carcompanyRemark;
	}

	public void setCarcompanyRemark(String carcompanyRemark) {
		this.carcompanyRemark = carcompanyRemark;
	}

	@Override
	public String toString() {
		return "TblCarcompany [pkCarcompany=" + pkCarcompany
				+ ", carcompanyName=" + carcompanyName
				+ ", carcompanyCreateDate=" + carcompanyCreateDate
				+ ", carcompanyUpdateDate=" + carcompanyUpdateDate
				+ ", carcompanyRemark=" + carcompanyRemark + "]";
	}

}

package com.wanma.model;

import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * Tbl_CarCompnay表
 * 
 * @author mew
 * 
 */
public class TblCarCompnay extends BasicListAndMutiFile implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1L;
	private java.lang.Integer pkCarCompany; // 主键
	private java.lang.String carCompanyName; // 电动车厂家名称备注电动车最大续航里程
	private java.util.Date carCompanyCreateDate; // 创建时间
	private java.util.Date carCompanyUpdatedate; // 修改时间
	private String carCompanyRemark;
	public java.lang.Integer getPkCarCompany() {
		return pkCarCompany;
	}
	public void setPkCarCompany(java.lang.Integer pkCarCompany) {
		this.pkCarCompany = pkCarCompany;
	}
	public java.lang.String getCarCompanyName() {
		return carCompanyName;
	}
	public void setCarCompanyName(java.lang.String carCompanyName) {
		this.carCompanyName = carCompanyName;
	}
	public java.util.Date getCarCompanyCreateDate() {
		return carCompanyCreateDate;
	}
	public void setCarCompanyCreateDate(java.util.Date carCompanyCreateDate) {
		this.carCompanyCreateDate = carCompanyCreateDate;
	}
	public java.util.Date getCarCompanyUpdatedate() {
		return carCompanyUpdatedate;
	}
	public void setCarCompanyUpdatedate(java.util.Date carCompanyUpdatedate) {
		this.carCompanyUpdatedate = carCompanyUpdatedate;
	}
	public String getCarCompanyRemark() {
		return carCompanyRemark;
	}
	public void setCarCompanyRemark(String carCompanyRemark) {
		this.carCompanyRemark = carCompanyRemark;
	}
	@Override
	public String toString() {
		return "TblCarCompnay [pkCarCompany=" + pkCarCompany
				+ ", carCompanyName=" + carCompanyName
				+ ", carCompanyCreateDate=" + carCompanyCreateDate
				+ ", carCompanyUpdatedate=" + carCompanyUpdatedate
				+ ", carCompanyRemark=" + carCompanyRemark + "]";
	}
	
	
	
}
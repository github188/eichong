package com.wanma.model;


public class TblRecruit {
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private java.lang.Integer pkRecruit; //主键
	private java.lang.String recJob; // 职位
	private java.lang.String recPlace; // 工作地点
	private java.lang.Integer recNumber; // 人数
	private java.util.Date recCreatedate; // 创建时间
	private java.util.Date recUpdatedate; // 修改时间
	private java.lang.String recUsepk; // 发布人
	private java.lang.String recJobdescribe; // 职位描述
	private java.lang.String recJobrequire; // 职位要求
	public java.lang.Integer getPkRecruit() {
		return pkRecruit;
	}
	public void setPkRecruit(java.lang.Integer pkRecruit) {
		this.pkRecruit = pkRecruit;
	}
	public java.lang.String getRecJob() {
		return recJob;
	}
	public void setRecJob(java.lang.String recJob) {
		this.recJob = recJob;
	}
	public java.lang.String getRecPlace() {
		return recPlace;
	}
	public void setRecPlace(java.lang.String recPlace) {
		this.recPlace = recPlace;
	}
	public java.lang.Integer getRecNumber() {
		return recNumber;
	}
	public void setRecNumber(java.lang.Integer recNumber) {
		this.recNumber = recNumber;
	}
	public java.util.Date getRecCreatedate() {
		return recCreatedate;
	}
	public void setRecCreatedate(java.util.Date recCreatedate) {
		this.recCreatedate = recCreatedate;
	}
	public java.util.Date getRecUpdatedate() {
		return recUpdatedate;
	}
	public void setRecUpdatedate(java.util.Date recUpdatedate) {
		this.recUpdatedate = recUpdatedate;
	}
	public java.lang.String getRecUsepk() {
		return recUsepk;
	}
	public void setRecUsepk(java.lang.String recUsepk) {
		this.recUsepk = recUsepk;
	}
	public java.lang.String getRecJobdescribe() {
		return recJobdescribe;
	}
	public void setRecJobdescribe(java.lang.String recJobdescribe) {
		this.recJobdescribe = recJobdescribe;
	}
	public java.lang.String getRecJobrequire() {
		return recJobrequire;
	}
	public void setRecJobrequire(java.lang.String recJobrequire) {
		this.recJobrequire = recJobrequire;
	}
	
	@Override
	public String toString() {
		return "TblRecuit [pkRecruit=" + pkRecruit + ", recJob=" + recJob
				+ ", recPlace=" + recPlace + ", recNumber=" + recNumber
				+ ", recCreatedate=" + recCreatedate + ", recUpdatedate="
				+ recUpdatedate + ", recUsepk=" + recUsepk
				+ ", recJobdescribe=" + recJobdescribe + ", recJobrequire="
				+ recJobrequire + "]";
	}

	

}

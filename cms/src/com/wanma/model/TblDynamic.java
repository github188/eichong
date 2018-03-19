package com.wanma.model;

import com.pub.model.Entity;

public class TblDynamic extends Entity{
	private static final long serialVersionUID = 1L;
	private java.lang.Integer pkRelease; // 主键
	private java.lang.String releTitle; // 发布标题
	private java.lang.String briefIntroduction; // 简介
	private java.lang.String releContent; // 发布内容
	private java.util.Date releCreatedate; // 创建时间
	private java.util.Date releUpdatedate; // 修改时间
	private java.lang.String releUsepk; // 发布人
	private java.lang.String releType; // 发布类型（1活动中心2企业动态3行业动态）
	private Integer releImg;// 是否首图
	private Integer validFlag;// 是否有效
	private Integer releOrder;// 排序号
	
	private Integer totalCount;//总访问量
	private Integer webCount;//web端访问量
	private Integer iosCount;//ios访问量
	private Integer androidCount;//android访问量
	// 表单填充字段
	private String imageUrl;// 图片路径

	public java.lang.Integer getPkRelease() {
		return pkRelease;
	}

	public void setPkRelease(java.lang.Integer pkRelease) {
		this.pkRelease = pkRelease;
	}

	public java.lang.String getReleTitle() {
		return releTitle;
	}

	public void setReleTitle(java.lang.String releTitle) {
		this.releTitle = releTitle;
	}

	public java.lang.String getReleContent() {
		return releContent;
	}

	public void setReleContent(java.lang.String releContent) {
		this.releContent = releContent;
	}

	public java.util.Date getReleCreatedate() {
		return releCreatedate;
	}

	public void setReleCreatedate(java.util.Date releCreatedate) {
		this.releCreatedate = releCreatedate;
	}

	public java.util.Date getReleUpdatedate() {
		return releUpdatedate;
	}

	public void setReleUpdatedate(java.util.Date releUpdatedate) {
		this.releUpdatedate = releUpdatedate;
	}

	public java.lang.String getReleUsepk() {
		return releUsepk;
	}

	public void setReleUsepk(java.lang.String releUsepk) {
		this.releUsepk = releUsepk;
	}

	public java.lang.String getReleType() {
		return releType;
	}

	public void setReleType(java.lang.String releType) {
		this.releType = releType;
	}

	public Integer getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Integer validFlag) {
		this.validFlag = validFlag;
	}

	public Integer getReleImg() {
		return releImg;
	}

	public void setReleImg(Integer releImg) {
		this.releImg = releImg;
	}

	public Integer getReleOrder() {
		return releOrder;
	}

	public void setReleOrder(Integer releOrder) {
		this.releOrder = releOrder;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public java.lang.String getBriefIntroduction() {
		return briefIntroduction;
	}

	public void setBriefIntroduction(java.lang.String briefIntroduction) {
		this.briefIntroduction = briefIntroduction;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getWebCount() {
		return webCount;
	}

	public void setWebCount(Integer webCount) {
		this.webCount = webCount;
	}

	public Integer getIosCount() {
		return iosCount;
	}

	public void setIosCount(Integer iosCount) {
		this.iosCount = iosCount;
	}

	public Integer getAndroidCount() {
		return androidCount;
	}

	public void setAndroidCount(Integer androidCount) {
		this.androidCount = androidCount;
	}

	@Override
	public String toString() {
		return "TblDynamic [pkRelease=" + pkRelease + ", releTitle="
				+ releTitle + ", releContent=" + releContent
				+ ", releCreatedate=" + releCreatedate + ", releUpdatedate="
				+ releUpdatedate + ", releUsepk=" + releUsepk + ", releType="
				+ releType + ", releImg=" + releImg + ", validFlag="
				+ validFlag + ", releOrder=" + releOrder + ", imageUrl="
				+ imageUrl + "]";
	}

}

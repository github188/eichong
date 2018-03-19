package com.wanma.model;

/**
 * 
 * tbl_HomePage表
 * 
 * @author songjf
 * 
 */
public class TblHomepage {
	private java.lang.Integer pkHomepage; // 主键
	private java.lang.String hopaHomepagename; // 首页广告名称
	private java.lang.String hopaImage; // 首页图片
	private java.lang.Integer hopaSequence; // 轮播顺序
	private java.util.Date hopaCreatedate; // 创建时间
	private java.util.Date hopaUpdatedate; // 修改时间
	private java.lang.Integer hopaStatus; // 0：显示 -1 已删除

	/**
	 * 获取主键属性
	 * 
	 * @return pkHomepage
	 */
	public java.lang.Integer getPkHomepage() {
		return pkHomepage;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkHomepage
	 */
	public void setPkHomepage(java.lang.Integer pkHomepage) {
		this.pkHomepage = pkHomepage;
	}

	/**
	 * 获取首页广告名称属性
	 * 
	 * @return hopaHomepagename
	 */
	public java.lang.String getHopaHomepagename() {
		return hopaHomepagename;
	}

	/**
	 * 设置首页广告名称属性
	 * 
	 * @param hopaHomepagename
	 */
	public void setHopaHomepagename(java.lang.String hopaHomepagename) {
		this.hopaHomepagename = hopaHomepagename;
	}

	/**
	 * 获取首页图片属性
	 * 
	 * @return hopaImage
	 */
	public java.lang.String getHopaImage() {
		return hopaImage;
	}

	/**
	 * 设置首页图片属性
	 * 
	 * @param hopaImage
	 */
	public void setHopaImage(java.lang.String hopaImage) {
		this.hopaImage = hopaImage;
	}

	/**
	 * 获取轮播顺序属性
	 * 
	 * @return hopaSequence
	 */
	public java.lang.Integer getHopaSequence() {
		return hopaSequence;
	}

	/**
	 * 设置轮播顺序属性
	 * 
	 * @param hopaSequence
	 */
	public void setHopaSequence(java.lang.Integer hopaSequence) {
		this.hopaSequence = hopaSequence;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return hopaCreatedate
	 */
	public java.util.Date getHopaCreatedate() {
		return hopaCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param hopaCreatedate
	 */
	public void setHopaCreatedate(java.util.Date hopaCreatedate) {
		this.hopaCreatedate = hopaCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return hopaUpdatedate
	 */
	public java.util.Date getHopaUpdatedate() {
		return hopaUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param hopaUpdatedate
	 */
	public void setHopaUpdatedate(java.util.Date hopaUpdatedate) {
		this.hopaUpdatedate = hopaUpdatedate;
	}

	/**
	 * 获取0：显示 -1 已删除属性
	 * 
	 * @return hopaStatus
	 */
	public java.lang.Integer getHopaStatus() {
		return hopaStatus;
	}

	/**
	 * 设置0：显示 -1 已删除属性
	 * 
	 * @param hopaStatus
	 */
	public void setHopaStatus(java.lang.Integer hopaStatus) {
		this.hopaStatus = hopaStatus;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblHomepage");
		sb.append("{pkHomepage=").append(pkHomepage);
		sb.append(", hopaHomepagename=").append(hopaHomepagename);
		sb.append(", hopaImage=").append(hopaImage);
		sb.append(", hopaSequence=").append(hopaSequence);
		sb.append(", hopaCreatedate=").append(hopaCreatedate);
		sb.append(", hopaUpdatedate=").append(hopaUpdatedate);
		sb.append(", hopaStatus=").append(hopaStatus);
		sb.append('}');
		return sb.toString();
	}
}
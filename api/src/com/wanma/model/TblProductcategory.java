package com.wanma.model;

import java.io.Serializable;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 
 * tbl_ProductCategory表
 * 
 * @author mew
 * 
 */
public class TblProductcategory extends BasicListAndMutiFile implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Integer pkProductcategory; //
	private java.lang.String prcaName; // 名称
	private java.lang.Integer prcaParentid; // 一级分类
	private java.util.Date prcaCreatedate; // 创建时间
	private java.util.Date prcaUpdatedate; // 创建时间
	private java.lang.String prcaImage; //

	/**
	 * 获取属性
	 * 
	 * @return pkProductcategory
	 */
	public java.lang.Integer getPkProductcategory() {
		return pkProductcategory;
	}

	/**
	 * 设置属性
	 * 
	 * @param pkProductcategory
	 */
	public void setPkProductcategory(java.lang.Integer pkProductcategory) {
		this.pkProductcategory = pkProductcategory;
	}

	/**
	 * 获取名称属性
	 * 
	 * @return prcaName
	 */
	public java.lang.String getPrcaName() {
		return prcaName;
	}

	/**
	 * 设置名称属性
	 * 
	 * @param prcaName
	 */
	public void setPrcaName(java.lang.String prcaName) {
		this.prcaName = prcaName;
	}

	/**
	 * 获取一级分类属性
	 * 
	 * @return prcaParentid
	 */
	public java.lang.Integer getPrcaParentid() {
		return prcaParentid;
	}

	/**
	 * 设置一级分类属性
	 * 
	 * @param prcaParentid
	 */
	public void setPrcaParentid(java.lang.Integer prcaParentid) {
		this.prcaParentid = prcaParentid;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return prcaCreatedate
	 */
	public java.util.Date getPrcaCreatedate() {
		return prcaCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param prcaCreatedate
	 */
	public void setPrcaCreatedate(java.util.Date prcaCreatedate) {
		this.prcaCreatedate = prcaCreatedate;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return prcaUpdatedate
	 */
	public java.util.Date getPrcaUpdatedate() {
		return prcaUpdatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param prcaUpdatedate
	 */
	public void setPrcaUpdatedate(java.util.Date prcaUpdatedate) {
		this.prcaUpdatedate = prcaUpdatedate;
	}

	/**
	 * 获取属性
	 * 
	 * @return prcaImage
	 */
	public java.lang.String getPrcaImage() {
		return prcaImage;
	}

	/**
	 * 设置属性
	 * 
	 * @param prcaImage
	 */
	public void setPrcaImage(java.lang.String prcaImage) {
		this.prcaImage = prcaImage;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblProductcategory");
		sb.append("{pkProductcategory=").append(pkProductcategory);
		sb.append(", prcaName=").append(prcaName);
		sb.append(", prcaParentid=").append(prcaParentid);
		sb.append(", prcaCreatedate=").append(prcaCreatedate);
		sb.append(", prcaUpdatedate=").append(prcaUpdatedate);
		sb.append(", prcaImage=").append(prcaImage);
		sb.append('}');
		return sb.toString();
	}
}
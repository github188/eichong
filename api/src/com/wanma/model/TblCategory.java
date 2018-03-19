package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_Category表
 * @author songjf
 *
 */
public class TblCategory implements Serializable {
	private java.lang.Integer pkCategory; // 
	private java.lang.String cateName; // 商品名称
	private java.util.Date cateCreatedate; // 创建时间
	private java.util.Date cateUpdatedate; // 修改时间

	/**
     * 获取属性
     *
     * @return pkCategory
     */
	public java.lang.Integer getPkCategory() {
		return pkCategory;
	}
	
	/**
	 * 设置属性
	 *
	 * @param pkCategory
	 */
	public void setPkCategory(java.lang.Integer pkCategory) {
		this.pkCategory = pkCategory;
	}
	
	/**
     * 获取商品名称属性
     *
     * @return cateName
     */
	public java.lang.String getCateName() {
		return cateName;
	}
	
	/**
	 * 设置商品名称属性
	 *
	 * @param cateName
	 */
	public void setCateName(java.lang.String cateName) {
		this.cateName = cateName;
	}
	
	/**
     * 获取创建时间属性
     *
     * @return cateCreatedate
     */
	public java.util.Date getCateCreatedate() {
		return cateCreatedate;
	}
	
	/**
	 * 设置创建时间属性
	 *
	 * @param cateCreatedate
	 */
	public void setCateCreatedate(java.util.Date cateCreatedate) {
		this.cateCreatedate = cateCreatedate;
	}
	
	/**
     * 获取修改时间属性
     *
     * @return cateUpdatedate
     */
	public java.util.Date getCateUpdatedate() {
		return cateUpdatedate;
	}
	
	/**
	 * 设置修改时间属性
	 *
	 * @param cateUpdatedate
	 */
	public void setCateUpdatedate(java.util.Date cateUpdatedate) {
		this.cateUpdatedate = cateUpdatedate;
	}
	

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TblCategory");
        sb.append("{pkCategory=").append(pkCategory);
        sb.append(", cateName=").append(cateName);
        sb.append(", cateCreatedate=").append(cateCreatedate);
        sb.append(", cateUpdatedate=").append(cateUpdatedate);
		sb.append('}');
        return sb.toString();
    }
}
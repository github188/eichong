package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_Footprint表
 * @author mew
 *
 */
public class TblFootprint implements Serializable {
	private java.lang.Integer pkFootprint; // 主键
	private java.lang.String foprContent; // 足迹内容
	private java.util.Date foprCreatedate; // 收益时间
	private java.util.Date foprUpdatedate; // 修改时间
	private java.lang.Integer foprUserId; // 用户ID
	/**
     * 获取主键属性
     *
     * @return pkFootprint
     */
	public java.lang.Integer getPkFootprint() {
		return pkFootprint;
	}
	
	/**
	 * 设置主键属性
	 *
	 * @param pkFootprint
	 */
	public void setPkFootprint(java.lang.Integer pkFootprint) {
		this.pkFootprint = pkFootprint;
	}
	
	/**
     * 获取足迹内容属性
     *
     * @return foprContent
     */
	public java.lang.String getFoprContent() {
		return foprContent;
	}
	
	/**
	 * 设置足迹内容属性
	 *
	 * @param foprContent
	 */
	public void setFoprContent(java.lang.String foprContent) {
		this.foprContent = foprContent;
	}
	
	/**
     * 获取收益时间属性
     *
     * @return foprCreatedate
     */
	public java.util.Date getFoprCreatedate() {
		return foprCreatedate;
	}
	
	/**
	 * 设置收益时间属性
	 *
	 * @param foprCreatedate
	 */
	public void setFoprCreatedate(java.util.Date foprCreatedate) {
		this.foprCreatedate = foprCreatedate;
	}
	
	/**
     * 获取修改时间属性
     *
     * @return foprUpdatedate
     */
	public java.util.Date getFoprUpdatedate() {
		return foprUpdatedate;
	}
	
	/**
	 * 设置修改时间属性
	 *
	 * @param foprUpdatedate
	 */
	public void setFoprUpdatedate(java.util.Date foprUpdatedate) {
		this.foprUpdatedate = foprUpdatedate;
	}
	

	 

	public java.lang.Integer getFoprUserId() {
		return foprUserId;
	}

	public void setFoprUserId(java.lang.Integer foprUserId) {
		this.foprUserId = foprUserId;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TblFootprint");
        sb.append("{pkFootprint=").append(pkFootprint);
        sb.append(", foprContent=").append(foprContent);
        sb.append(", foprCreatedate=").append(foprCreatedate);
        sb.append(", foprUpdatedate=").append(foprUpdatedate);
		sb.append('}');
        return sb.toString();
    }
}
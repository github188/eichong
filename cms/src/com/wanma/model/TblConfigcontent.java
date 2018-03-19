package com.wanma.model;

import com.pub.model.Entity;

/**
 * 
 * tbl_ConfigContent表
 * 
 * @author songjf
 * 
 */
public class TblConfigcontent extends Entity {
	private static final long serialVersionUID = -422259671195590694L;
	private java.lang.Integer pkConfigcontent; //
	private java.lang.Integer cocoConfigparameterid; //
	private java.lang.String cocoContent; // 内容
	private java.lang.String coCoMemo; // 备注
	private java.lang.Integer cocoConfigpstatus; // 状态(0启用，1禁用)
	private java.util.Date cocoCreatedate; // 创建时间
	private java.util.Date cocoUpdatedate; // 修改时间

	/**
	 * 获取属性
	 * 
	 * @return pkConfigcontent
	 */
	public java.lang.Integer getPkConfigcontent() {
		return pkConfigcontent;
	}

	/**
	 * 设置属性
	 * 
	 * @param pkConfigcontent
	 */
	public void setPkConfigcontent(java.lang.Integer pkConfigcontent) {
		this.pkConfigcontent = pkConfigcontent;
	}

	public java.lang.String getCoCoMemo() {
		return coCoMemo;
	}

	public void setCoCoMemo(java.lang.String coCoMemo) {
		this.coCoMemo = coCoMemo;
	}

	/**
	 * 获取属性
	 * 
	 * @return cocoConfigparameterid
	 */
	public java.lang.Integer getCocoConfigparameterid() {
		return cocoConfigparameterid;
	}

	/**
	 * 设置属性
	 * 
	 * @param cocoConfigparameterid
	 */
	public void setCocoConfigparameterid(java.lang.Integer cocoConfigparameterid) {
		this.cocoConfigparameterid = cocoConfigparameterid;
	}

	/**
	 * 获取属性
	 * 
	 * @return cocoContent
	 */
	public java.lang.String getCocoContent() {
		return cocoContent;
	}

	/**
	 * 设置属性
	 * 
	 * @param cocoContent
	 */
	public void setCocoContent(java.lang.String cocoContent) {
		this.cocoContent = cocoContent;
	}

	/**
	 * 获取状态(0启用，1禁用)属性
	 * 
	 * @return cocoConfigpstatus
	 */
	public java.lang.Integer getCocoConfigpstatus() {
		return cocoConfigpstatus;
	}

	/**
	 * 设置状态(0启用，1禁用)属性
	 * 
	 * @param cocoConfigpstatus
	 */
	public void setCocoConfigpstatus(java.lang.Integer cocoConfigpstatus) {
		this.cocoConfigpstatus = cocoConfigpstatus;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return cocoCreatedate
	 */
	public java.util.Date getCocoCreatedate() {
		return cocoCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param cocoCreatedate
	 */
	public void setCocoCreatedate(java.util.Date cocoCreatedate) {
		this.cocoCreatedate = cocoCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return cocoUpdatedate
	 */
	public java.util.Date getCocoUpdatedate() {
		return cocoUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param cocoUpdatedate
	 */
	public void setCocoUpdatedate(java.util.Date cocoUpdatedate) {
		this.cocoUpdatedate = cocoUpdatedate;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblConfigcontent");
		sb.append("{pkConfigcontent=").append(pkConfigcontent);
		sb.append(", cocoConfigparameterid=").append(cocoConfigparameterid);
		sb.append(", cocoContent=").append(cocoContent);
		sb.append(", cocoConfigpstatus=").append(cocoConfigpstatus);
		sb.append(", cocoCreatedate=").append(cocoCreatedate);
		sb.append(", cocoUpdatedate=").append(cocoUpdatedate);
		sb.append(", coCoMemo=").append(coCoMemo);
		sb.append('}');
		return sb.toString();
	}
}
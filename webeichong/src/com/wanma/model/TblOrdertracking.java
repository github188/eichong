package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_OrderTracking表
 * 
 * @author songjf
 * 
 */
public class TblOrdertracking implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1387417815575404763L;
	private java.lang.Integer pkOrdertracking; // 主键
	private java.lang.Integer ortrId; // 订单ID
	private java.lang.Integer ortrStatus; // 订单状态
	private java.util.Date ortrUpdatedate; // 修改时间
	private java.util.Date ortrCreatedate; // 创建时间

	/**
	 * 获取主键属性
	 * 
	 * @return pkOrdertracking
	 */
	public java.lang.Integer getPkOrdertracking() {
		return pkOrdertracking;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkOrdertracking
	 */
	public void setPkOrdertracking(java.lang.Integer pkOrdertracking) {
		this.pkOrdertracking = pkOrdertracking;
	}

	/**
	 * 获取订单ID属性
	 * 
	 * @return ortrId
	 */
	public java.lang.Integer getOrtrId() {
		return ortrId;
	}

	/**
	 * 设置订单ID属性
	 * 
	 * @param ortrId
	 */
	public void setOrtrId(java.lang.Integer ortrId) {
		this.ortrId = ortrId;
	}

	/**
	 * 获取订单状态属性
	 * 
	 * @return ortrStatus
	 */
	public java.lang.Integer getOrtrStatus() {
		return ortrStatus;
	}

	/**
	 * 设置订单状态属性
	 * 
	 * @param ortrStatus
	 */
	public void setOrtrStatus(java.lang.Integer ortrStatus) {
		this.ortrStatus = ortrStatus;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return ortrUpdatedate
	 */
	public java.util.Date getOrtrUpdatedate() {
		return ortrUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param ortrUpdatedate
	 */
	public void setOrtrUpdatedate(java.util.Date ortrUpdatedate) {
		this.ortrUpdatedate = ortrUpdatedate;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return ortrCreatedate
	 */
	public java.util.Date getOrtrCreatedate() {
		return ortrCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param ortrCreatedate
	 */
	public void setOrtrCreatedate(java.util.Date ortrCreatedate) {
		this.ortrCreatedate = ortrCreatedate;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblOrdertracking");
		sb.append("{pkOrdertracking=").append(pkOrdertracking);
		sb.append(", ortrId=").append(ortrId);
		sb.append(", ortrStatus=").append(ortrStatus);
		sb.append(", ortrUpdatedate=").append(ortrUpdatedate);
		sb.append(", ortrCreatedate=").append(ortrCreatedate);
		sb.append('}');
		return sb.toString();
	}
}
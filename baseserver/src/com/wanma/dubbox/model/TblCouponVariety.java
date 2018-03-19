package com.wanma.dubbox.model;

import java.io.Serializable;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 
 * tbl_couponVariety表
 * 
 */
public class TblCouponVariety extends BasicModel implements
		Serializable {
	private static final long serialVersionUID = 6493777629243709217L;
	private java.lang.String name; // 活动名称
	private java.lang.Integer eLmt; // 电桩限制（1-仅限交流电桩，2-仅限直流电桩，3-不限充电桩）
	private java.lang.Integer sts; // 状态（0-上架，1-下架）
	private java.lang.String val; // 优惠券面值
	private java.lang.String cdt; // 优惠券使用条件
	private java.lang.Integer term; // 有效期（天）
	private java.lang.String cuid; // 创建人
	private java.lang.String upUid; // 操作人
	private java.lang.String remark; // 备注

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Integer geteLmt() {
		return eLmt;
	}

	public void seteLmt(java.lang.Integer eLmt) {
		this.eLmt = eLmt;
	}

	public java.lang.Integer getSts() {
		return sts;
	}

	public void setSts(java.lang.Integer sts) {
		this.sts = sts;
	}

	public java.lang.String getVal() {
		return val;
	}

	public void setVal(java.lang.String val) {
		this.val = val;
	}

	public java.lang.String getCdt() {
		return cdt;
	}

	public void setCdt(java.lang.String cdt) {
		this.cdt = cdt;
	}

	public java.lang.Integer getTerm() {
		return term;
	}

	public void setTerm(java.lang.Integer term) {
		this.term = term;
	}

	public java.lang.String getCuid() {
		return cuid;
	}

	public void setCuid(java.lang.String cuid) {
		this.cuid = cuid;
	}

	public java.lang.String getUpUid() {
		return upUid;
	}

	public void setUpUid(java.lang.String upUid) {
		this.upUid = upUid;
	}

	public java.lang.String getRemark() {
		return remark;
	}

	public void setRemark(java.lang.String remark) {
		this.remark = remark;
	}


}
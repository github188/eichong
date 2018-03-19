package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_JPush表
 * 
 * @author songjf
 * 
 */
public class TblJpush implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7036526644140696261L;
	private java.lang.Integer pkJpush; //
	private java.lang.Integer jpushUserinfo; // 用户id
	private java.lang.String jpushRegistrationid; // 唯一设备标示
	private java.lang.String jpushDevicetype; // 设备类型 1安卓 2ios
	private java.util.Date jpushCreatedate; // 创建时间
	private java.util.Date jpushUpdatedate; // 修改时间

	/**
	 * 获取属性
	 * 
	 * @return pkJpush
	 */
	public java.lang.Integer getPkJpush() {
		return pkJpush;
	}

	/**
	 * 设置属性
	 * 
	 * @param pkJpush
	 */
	public void setPkJpush(java.lang.Integer pkJpush) {
		this.pkJpush = pkJpush;
	}

	/**
	 * 获取用户id属性
	 * 
	 * @return jpushUserinfo
	 */
	public java.lang.Integer getJpushUserinfo() {
		return jpushUserinfo;
	}

	/**
	 * 设置用户id属性
	 * 
	 * @param jpushUserinfo
	 */
	public void setJpushUserinfo(java.lang.Integer jpushUserinfo) {
		this.jpushUserinfo = jpushUserinfo;
	}

	/**
	 * 获取唯一设备标示属性
	 * 
	 * @return jpushRegistrationid
	 */
	public java.lang.String getJpushRegistrationid() {
		return jpushRegistrationid;
	}

	/**
	 * 设置唯一设备标示属性
	 * 
	 * @param jpushRegistrationid
	 */
	public void setJpushRegistrationid(java.lang.String jpushRegistrationid) {
		this.jpushRegistrationid = jpushRegistrationid;
	}

	/**
	 * 获取设备类型 1安卓 2ios属性
	 * 
	 * @return jpushDevicetype
	 */
	public java.lang.String getJpushDevicetype() {
		return jpushDevicetype;
	}

	/**
	 * 设置设备类型 1安卓 2ios属性
	 * 
	 * @param jpushDevicetype
	 */
	public void setJpushDevicetype(java.lang.String jpushDevicetype) {
		this.jpushDevicetype = jpushDevicetype;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return jpushCreatedate
	 */
	public java.util.Date getJpushCreatedate() {
		return jpushCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param jpushCreatedate
	 */
	public void setJpushCreatedate(java.util.Date jpushCreatedate) {
		this.jpushCreatedate = jpushCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return jpushUpdatedate
	 */
	public java.util.Date getJpushUpdatedate() {
		return jpushUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param jpushUpdatedate
	 */
	public void setJpushUpdatedate(java.util.Date jpushUpdatedate) {
		this.jpushUpdatedate = jpushUpdatedate;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblJpush");
		sb.append("{pkJpush=").append(pkJpush);
		sb.append(", jpushUserinfo=").append(jpushUserinfo);
		sb.append(", jpushRegistrationid=").append(jpushRegistrationid);
		sb.append(", jpushDevicetype=").append(jpushDevicetype);
		sb.append(", jpushCreatedate=").append(jpushCreatedate);
		sb.append(", jpushUpdatedate=").append(jpushUpdatedate);
		sb.append('}');
		return sb.toString();
	}
}
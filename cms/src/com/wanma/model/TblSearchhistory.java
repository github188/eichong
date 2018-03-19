package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_SearchHistory表
 * @author mew
 *
 */
public class TblSearchhistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3881219025371065151L;
	private java.lang.Integer pkSearchhistory; // 主键
	private java.lang.String sehiSearchhistory; // 搜索名称
	private java.lang.Integer sehiStatus; // 状态
	private java.util.Date sehiCreatedate; // 创建时间
	private java.util.Date sehiUpdatedate; // 修改时间

	/**
     * 获取主键属性
     *
     * @return pkSearchhistory
     */
	public java.lang.Integer getPkSearchhistory() {
		return pkSearchhistory;
	}
	
	/**
	 * 设置主键属性
	 *
	 * @param pkSearchhistory
	 */
	public void setPkSearchhistory(java.lang.Integer pkSearchhistory) {
		this.pkSearchhistory = pkSearchhistory;
	}
	
	/**
     * 获取搜索名称属性
     *
     * @return sehiSearchhistory
     */
	public java.lang.String getSehiSearchhistory() {
		return sehiSearchhistory;
	}
	
	/**
	 * 设置搜索名称属性
	 *
	 * @param sehiSearchhistory
	 */
	public void setSehiSearchhistory(java.lang.String sehiSearchhistory) {
		this.sehiSearchhistory = sehiSearchhistory;
	}
	
	/**
     * 获取状态属性
     *
     * @return sehiStatus
     */
	public java.lang.Integer getSehiStatus() {
		return sehiStatus;
	}
	
	/**
	 * 设置状态属性
	 *
	 * @param sehiStatus
	 */
	public void setSehiStatus(java.lang.Integer sehiStatus) {
		this.sehiStatus = sehiStatus;
	}
	
	/**
     * 获取创建时间属性
     *
     * @return sehiCreatedate
     */
	public java.util.Date getSehiCreatedate() {
		return sehiCreatedate;
	}
	
	/**
	 * 设置创建时间属性
	 *
	 * @param sehiCreatedate
	 */
	public void setSehiCreatedate(java.util.Date sehiCreatedate) {
		this.sehiCreatedate = sehiCreatedate;
	}
	
	/**
     * 获取修改时间属性
     *
     * @return sehiUpdatedate
     */
	public java.util.Date getSehiUpdatedate() {
		return sehiUpdatedate;
	}
	
	/**
	 * 设置修改时间属性
	 *
	 * @param sehiUpdatedate
	 */
	public void setSehiUpdatedate(java.util.Date sehiUpdatedate) {
		this.sehiUpdatedate = sehiUpdatedate;
	}
	

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TblSearchhistory");
        sb.append("{pkSearchhistory=").append(pkSearchhistory);
        sb.append(", sehiSearchhistory=").append(sehiSearchhistory);
        sb.append(", sehiStatus=").append(sehiStatus);
        sb.append(", sehiCreatedate=").append(sehiCreatedate);
        sb.append(", sehiUpdatedate=").append(sehiUpdatedate);
		sb.append('}');
        return sb.toString();
    }
}
package com.wanma.model;



import java.io.Serializable;

/**
 * 
 * tbl_Version表
 * @author songjf
 *
 */
public class TblVersion implements Serializable {
	private static final long serialVersionUID = 1L;
	private java.lang.Integer pkVersion; // 主键
	private java.lang.String versNumber; // 版本号
	private java.lang.Integer versType; // 1：安卓 2 IOS
	private java.lang.String versUrl; // 版本地址
	private int vers_auto_update;
	private java.util.Date versCreatedate; // 创建时间
	private java.util.Date versUpdatedate; // 修改时间

	
	public int getVers_auto_update() {
		return vers_auto_update;
	}

	public void setVers_auto_update(int vers_auto_update) {
		this.vers_auto_update = vers_auto_update;
	}

	/**
     * 获取主键属性
     *
     * @return pkVersion
     */
	public java.lang.Integer getPkVersion() {
		return pkVersion;
	}
	
	/**
	 * 设置主键属性
	 *
	 * @param pkVersion
	 */
	public void setPkVersion(java.lang.Integer pkVersion) {
		this.pkVersion = pkVersion;
	}
	
	/**
     * 获取版本号属性
     *
     * @return versNumber
     */
	public java.lang.String getVersNumber() {
		return versNumber;
	}
	
	/**
	 * 设置版本号属性
	 *
	 * @param versNumber
	 */
	public void setVersNumber(java.lang.String versNumber) {
		this.versNumber = versNumber;
	}
	
	/**
     * 获取1：安卓 2 IOS属性
     *
     * @return versType
     */
	public java.lang.Integer getVersType() {
		return versType;
	}
	
	/**
	 * 设置1：安卓 2 IOS属性
	 *
	 * @param versType
	 */
	public void setVersType(java.lang.Integer versType) {
		this.versType = versType;
	}
	
	/**
     * 获取版本地址属性
     *
     * @return versUrl
     */
	public java.lang.String getVersUrl() {
		return versUrl;
	}
	
	/**
	 * 设置版本地址属性
	 *
	 * @param versUrl
	 */
	public void setVersUrl(java.lang.String versUrl) {
		this.versUrl = versUrl;
	}
	
	/**
     * 获取创建时间属性
     *
     * @return versCreatedate
     */
	public java.util.Date getVersCreatedate() {
		return versCreatedate;
	}
	
	/**
	 * 设置创建时间属性
	 *
	 * @param versCreatedate
	 */
	public void setVersCreatedate(java.util.Date versCreatedate) {
		this.versCreatedate = versCreatedate;
	}
	
	/**
     * 获取修改时间属性
     *
     * @return versUpdatedate
     */
	public java.util.Date getVersUpdatedate() {
		return versUpdatedate;
	}
	
	/**
	 * 设置修改时间属性
	 *
	 * @param versUpdatedate
	 */
	public void setVersUpdatedate(java.util.Date versUpdatedate) {
		this.versUpdatedate = versUpdatedate;
	}
	

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TblVersion");
        sb.append("{pkVersion=").append(pkVersion);
        sb.append(", versNumber=").append(versNumber);
        sb.append(", versType=").append(versType);
        sb.append(", versUrl=").append(versUrl);
        sb.append(", versCreatedate=").append(versCreatedate);
        sb.append(", versUpdatedate=").append(versUpdatedate);
		sb.append('}');
        return sb.toString();
    }
}
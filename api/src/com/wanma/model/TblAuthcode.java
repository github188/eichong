package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_AuthCode表
 * @author mew
 *
 */
public class TblAuthcode implements Serializable {
	private java.lang.Integer pkAuthcode; // 
	private java.lang.String auPhone; // 手机号
	private java.lang.String auAuthcode; // 验证码
	private java.util.Date auCreatedate; // 创建时间
	private java.util.Date auUpdatedate; // 修改时间

	/**
     * 获取属性
     *
     * @return pkAuthcode
     */
	public java.lang.Integer getPkAuthcode() {
		return pkAuthcode;
	}
	
	/**
	 * 设置属性
	 *
	 * @param pkAuthcode
	 */
	public void setPkAuthcode(java.lang.Integer pkAuthcode) {
		this.pkAuthcode = pkAuthcode;
	}
	
	/**
     * 获取手机号属性
     *
     * @return auPhone
     */
	public java.lang.String getAuPhone() {
		return auPhone;
	}
	
	/**
	 * 设置手机号属性
	 *
	 * @param auPhone
	 */
	public void setAuPhone(java.lang.String auPhone) {
		this.auPhone = auPhone;
	}
	
	/**
     * 获取验证码属性
     *
     * @return auAuthcode
     */
	public java.lang.String getAuAuthcode() {
		return auAuthcode;
	}
	
	/**
	 * 设置验证码属性
	 *
	 * @param auAuthcode
	 */
	public void setAuAuthcode(java.lang.String auAuthcode) {
		this.auAuthcode = auAuthcode;
	}
	
	/**
     * 获取创建时间属性
     *
     * @return auCreatedate
     */
	public java.util.Date getAuCreatedate() {
		return auCreatedate;
	}
	
	/**
	 * 设置创建时间属性
	 *
	 * @param auCreatedate
	 */
	public void setAuCreatedate(java.util.Date auCreatedate) {
		this.auCreatedate = auCreatedate;
	}
	
	/**
     * 获取修改时间属性
     *
     * @return auUpdatedate
     */
	public java.util.Date getAuUpdatedate() {
		return auUpdatedate;
	}
	
	/**
	 * 设置修改时间属性
	 *
	 * @param auUpdatedate
	 */
	public void setAuUpdatedate(java.util.Date auUpdatedate) {
		this.auUpdatedate = auUpdatedate;
	}
	

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TblAuthcode");
        sb.append("{pkAuthcode=").append(pkAuthcode);
        sb.append(", auPhone=").append(auPhone);
        sb.append(", auAuthcode=").append(auAuthcode);
        sb.append(", auCreatedate=").append(auCreatedate);
        sb.append(", auUpdatedate=").append(auUpdatedate);
		sb.append('}');
        return sb.toString();
    }
}
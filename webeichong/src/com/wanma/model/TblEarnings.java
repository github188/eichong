package com.wanma.model;

import java.io.Serializable;

/**
 * 
 * tbl_Earnings表
 * @author mew
 *
 */
public class TblEarnings implements Serializable {
	private java.lang.Integer pkEarnings; // 主键
	private java.lang.Integer earType; // 收益类型
	private java.util.Date earPurchasehistorytime; // 收益时间
	private java.math.BigDecimal earMonetary; // 收益金额
	private java.lang.String earConsumerremark; // 收益金额
	private java.util.Date earCreatedate; // 收益时间
	private java.util.Date earUpdatedate; // 修改时间
	private java.lang.String earContent; // 收益内容
	private java.lang.Integer earUserid; // 用户ID

	/**
     * 获取主键属性
     *
     * @return pkEarnings
     */
	public java.lang.Integer getPkEarnings() {
		return pkEarnings;
	}
	
	/**
	 * 设置主键属性
	 *
	 * @param pkEarnings
	 */
	public void setPkEarnings(java.lang.Integer pkEarnings) {
		this.pkEarnings = pkEarnings;
	}
	
	/**
     * 获取收益类型属性
     *
     * @return earType
     */
	public java.lang.Integer getEarType() {
		return earType;
	}
	
	/**
	 * 设置收益类型属性
	 *
	 * @param earType
	 */
	public void setEarType(java.lang.Integer earType) {
		this.earType = earType;
	}
	
	/**
     * 获取收益时间属性
     *
     * @return earPurchasehistorytime
     */
	public java.util.Date getEarPurchasehistorytime() {
		return earPurchasehistorytime;
	}
	
	/**
	 * 设置收益时间属性
	 *
	 * @param earPurchasehistorytime
	 */
	public void setEarPurchasehistorytime(java.util.Date earPurchasehistorytime) {
		this.earPurchasehistorytime = earPurchasehistorytime;
	}
	
	/**
     * 获取收益金额属性
     *
     * @return earMonetary
     */
	public java.math.BigDecimal getEarMonetary() {
		return earMonetary;
	}
	
	/**
	 * 设置收益金额属性
	 *
	 * @param earMonetary
	 */
	public void setEarMonetary(java.math.BigDecimal earMonetary) {
		this.earMonetary = earMonetary;
	}
	
	/**
     * 获取收益金额属性
     *
     * @return earConsumerremark
     */
	public java.lang.String getEarConsumerremark() {
		return earConsumerremark;
	}
	
	/**
	 * 设置收益金额属性
	 *
	 * @param earConsumerremark
	 */
	public void setEarConsumerremark(java.lang.String earConsumerremark) {
		this.earConsumerremark = earConsumerremark;
	}
	
	/**
     * 获取收益时间属性
     *
     * @return earCreatedate
     */
	public java.util.Date getEarCreatedate() {
		return earCreatedate;
	}
	
	/**
	 * 设置收益时间属性
	 *
	 * @param earCreatedate
	 */
	public void setEarCreatedate(java.util.Date earCreatedate) {
		this.earCreatedate = earCreatedate;
	}
	
	/**
     * 获取修改时间属性
     *
     * @return earUpdatedate
     */
	public java.util.Date getEarUpdatedate() {
		return earUpdatedate;
	}
	
	/**
	 * 设置修改时间属性
	 *
	 * @param earUpdatedate
	 */
	public void setEarUpdatedate(java.util.Date earUpdatedate) {
		this.earUpdatedate = earUpdatedate;
	}
	
	/**
     * 获取收益内容属性
     *
     * @return earContent
     */
	public java.lang.String getEarContent() {
		return earContent;
	}
	
	/**
	 * 设置收益内容属性
	 *
	 * @param earContent
	 */
	public void setEarContent(java.lang.String earContent) {
		this.earContent = earContent;
	}
	
	/**
     * 获取用户ID属性
     *
     * @return earUserid
     */
	public java.lang.Integer getEarUserid() {
		return earUserid;
	}
	
	/**
	 * 设置用户ID属性
	 *
	 * @param earUserid
	 */
	public void setEarUserid(java.lang.Integer earUserid) {
		this.earUserid = earUserid;
	}
	

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TblEarnings");
        sb.append("{pkEarnings=").append(pkEarnings);
        sb.append(", earType=").append(earType);
        sb.append(", earPurchasehistorytime=").append(earPurchasehistorytime);
        sb.append(", earMonetary=").append(earMonetary);
        sb.append(", earConsumerremark=").append(earConsumerremark);
        sb.append(", earCreatedate=").append(earCreatedate);
        sb.append(", earUpdatedate=").append(earUpdatedate);
        sb.append(", earContent=").append(earContent);
        sb.append(", earUserid=").append(earUserid);
		sb.append('}');
        return sb.toString();
    }
}
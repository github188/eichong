package com.wanma.model;


/**
 * 
 * tbl_ChargingRecord表
 * @author songjf
 *
 */
public class TblChargingrecord {
	private java.lang.Integer pkChargingrecord; // 主键
	private java.lang.Integer chreElectricpileid; // 电桩ID(tbl_ElectricPile中获取)
	private java.lang.String chreUsingmachinecode; // 使用终端机器编码
	private java.lang.String chreTransactionnumber; // 交易流水号
	private java.lang.String chreReservationnumber; // 预约号
	private java.util.Date chreStartdate; // 充电开始时间
	private java.lang.Integer chreChargingnumber; // 充电抢编号
	private java.lang.Integer chreChargingmethod; // 1:自动充满 2:按金额充 3:按里程4:按度数
	private Integer chreResttime; // 充满电剩余时间
	private java.util.Date  chreEnddate; // 
	private java.lang.String chreCode; // 充电订单编号与充电消费订单是主外键关系
	private java.lang.String chreBeginshowsnumber; // 开始充电表低示数
	private java.lang.String chreEndshowsnumber; // 结束充电表低示数
	private String userPhone;//用户手机号
	
	/**
     * 获取主键属性
     *
     * @return pkChargingrecord
     */
	public java.lang.Integer getPkChargingrecord() {
		return pkChargingrecord;
	}
	
	/**
	 * 设置主键属性
	 *
	 * @param pkChargingrecord
	 */
	public void setPkChargingrecord(java.lang.Integer pkChargingrecord) {
		this.pkChargingrecord = pkChargingrecord;
	}
	
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	/**
     * 获取电桩ID(tbl_ElectricPile中获取)属性
     *
     * @return chreElectricpileid
     */
	public java.lang.Integer getChreElectricpileid() {
		return chreElectricpileid;
	}
	
	/**
	 * 设置电桩ID(tbl_ElectricPile中获取)属性
	 *
	 * @param chreElectricpileid
	 */
	public void setChreElectricpileid(java.lang.Integer chreElectricpileid) {
		this.chreElectricpileid = chreElectricpileid;
	}
	
	/**
     * 获取使用终端机器编码属性
     *
     * @return chreUsingmachinecode
     */
	public java.lang.String getChreUsingmachinecode() {
		return chreUsingmachinecode;
	}
	
	/**
	 * 设置使用终端机器编码属性
	 *
	 * @param chreUsingmachinecode
	 */
	public void setChreUsingmachinecode(java.lang.String chreUsingmachinecode) {
		this.chreUsingmachinecode = chreUsingmachinecode;
	}
	
	/**
     * 获取交易流水号属性
     *
     * @return chreTransactionnumber
     */
	public java.lang.String getChreTransactionnumber() {
		return chreTransactionnumber;
	}
	
	/**
	 * 设置交易流水号属性
	 *
	 * @param chreTransactionnumber
	 */
	public void setChreTransactionnumber(java.lang.String chreTransactionnumber) {
		this.chreTransactionnumber = chreTransactionnumber;
	}
	
	/**
     * 获取预约号属性
     *
     * @return chreReservationnumber
     */
	public java.lang.String getChreReservationnumber() {
		return chreReservationnumber;
	}
	
	/**
	 * 设置预约号属性
	 *
	 * @param chreReservationnumber
	 */
	public void setChreReservationnumber(java.lang.String chreReservationnumber) {
		this.chreReservationnumber = chreReservationnumber;
	}
	
	/**
     * 获取充电开始时间属性
     *
     * @return chreStartdate
     */
	public java.util.Date getChreStartdate() {
		return chreStartdate;
	}
	
	/**
	 * 设置充电开始时间属性
	 *
	 * @param chreStartdate
	 */
	public void setChreStartdate(java.util.Date chreStartdate) {
		this.chreStartdate = chreStartdate;
	}
	
	/**
     * 获取充电抢编号属性
     *
     * @return chreChargingnumber
     */
	public java.lang.Integer getChreChargingnumber() {
		return chreChargingnumber;
	}
	
	/**
	 * 设置充电抢编号属性
	 *
	 * @param chreChargingnumber
	 */
	public void setChreChargingnumber(java.lang.Integer chreChargingnumber) {
		this.chreChargingnumber = chreChargingnumber;
	}
	
	/**
     * 获取1:自动充满 2:按金额充 3:按里程4:按度数属性
     *
     * @return chreChargingmethod
     */
	public java.lang.Integer getChreChargingmethod() {
		return chreChargingmethod;
	}
	
	/**
	 * 设置1:自动充满 2:按金额充 3:按里程4:按度数属性
	 *
	 * @param chreChargingmethod
	 */
	public void setChreChargingmethod(java.lang.Integer chreChargingmethod) {
		this.chreChargingmethod = chreChargingmethod;
	}
	
	
	public Integer getChreResttime() {
		return chreResttime;
	}

	public void setChreResttime(Integer chreResttime) {
		this.chreResttime = chreResttime;
	}

	/**
     * 获取属性
     *
     * @return chreEnddate
     */
	public java.util.Date getChreEnddate() {
		return chreEnddate;
	}
	
	/**
	 * 设置属性
	 *
	 * @param chreEnddate
	 */
	public void setChreEnddate(java.util.Date chreEnddate) {
		this.chreEnddate = chreEnddate;
	}
	
	/**
     * 获取充电订单编号与充电消费订单是主外键关系属性
     *
     * @return chreCode
     */
	public java.lang.String getChreCode() {
		return chreCode;
	}
	
	/**
	 * 设置充电订单编号与充电消费订单是主外键关系属性
	 *
	 * @param chreCode
	 */
	public void setChreCode(java.lang.String chreCode) {
		this.chreCode = chreCode;
	}
	
	/**
     * 获取开始充电表低示数属性
     *
     * @return chreBeginshowsnumber
     */
	public java.lang.String getChreBeginshowsnumber() {
		return chreBeginshowsnumber;
	}
	
	/**
	 * 设置开始充电表低示数属性
	 *
	 * @param chreBeginshowsnumber
	 */
	public void setChreBeginshowsnumber(java.lang.String chreBeginshowsnumber) {
		this.chreBeginshowsnumber = chreBeginshowsnumber;
	}
	
	/**
     * 获取结束充电表低示数属性
     *
     * @return chreEndshowsnumber
     */
	public java.lang.String getChreEndshowsnumber() {
		return chreEndshowsnumber;
	}
	
	/**
	 * 设置结束充电表低示数属性
	 *
	 * @param chreEndshowsnumber
	 */
	public void setChreEndshowsnumber(java.lang.String chreEndshowsnumber) {
		this.chreEndshowsnumber = chreEndshowsnumber;
	}
	

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TblChargingrecord");
        sb.append("{pkChargingrecord=").append(pkChargingrecord);
        sb.append(", chreElectricpileid=").append(chreElectricpileid);
        sb.append(", chreUsingmachinecode=").append(chreUsingmachinecode);
        sb.append(", chreTransactionnumber=").append(chreTransactionnumber);
        sb.append(", chreReservationnumber=").append(chreReservationnumber);
        sb.append(", chreStartdate=").append(chreStartdate);
        sb.append(", chreChargingnumber=").append(chreChargingnumber);
        sb.append(", chreChargingmethod=").append(chreChargingmethod);
        sb.append(", chreResttime=").append(chreResttime);
        sb.append(", chreEnddate=").append(chreEnddate);
        sb.append(", chreCode=").append(chreCode);
        sb.append(", chreBeginshowsnumber=").append(chreBeginshowsnumber);
        sb.append(", chreEndshowsnumber=").append(chreEndshowsnumber);
		sb.append('}');
        return sb.toString();
    }
}
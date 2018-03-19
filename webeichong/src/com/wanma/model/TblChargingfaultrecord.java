package com.wanma.model;

/**
 * 
 * tbl_ChargingFaultRecord表
 * @author songjf
 *
 */
/**
 * 听管理
 * 
 * @Description:
 * @author songjf
 * @createTime：2015-4-14 下午09:15:54
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public class TblChargingfaultrecord {
	private java.lang.Integer pkChargingfaultrecord; // 主键
	private java.lang.String cfreUsingmachinecode; // 使用终端机器编码
	private int cFRe_EphNo;//枪头编号
	private java.lang.Integer cfreElectricpileid; // 电桩ID(tbl_ElectricPile表中获取)
	private java.lang.String cfreElectricpilename; // 电桩名称
	private java.util.Date cfreChargingsarttime; // 故障开始时间
	private java.lang.String cfreFaultcause; // 故障原因
	private java.util.Date cfreCreatedate; // 创建时间
	private java.util.Date cfreUpdatedate; // 修改时间
	private java.lang.String cfreTransactionnumber; // 交易流水号

	public int getcFRe_EphNo() {
		return cFRe_EphNo;
	}

	public void setcFRe_EphNo(int cFRe_EphNo) {
		this.cFRe_EphNo = cFRe_EphNo;
	}

	/**
	 * 获取主键属性
	 * 
	 * @return pkChargingfaultrecord
	 */
	public java.lang.Integer getPkChargingfaultrecord() {
		return pkChargingfaultrecord;
	}

	/**
	 * 设置主键属性
	 * 
	 * @param pkChargingfaultrecord
	 */
	public void setPkChargingfaultrecord(java.lang.Integer pkChargingfaultrecord) {
		this.pkChargingfaultrecord = pkChargingfaultrecord;
	}

	/**
	 * 获取使用终端机器编码属性
	 * 
	 * @return cfreUsingmachinecode
	 */
	public java.lang.String getCfreUsingmachinecode() {
		return cfreUsingmachinecode;
	}

	/**
	 * 设置使用终端机器编码属性
	 * 
	 * @param cfreUsingmachinecode
	 */
	public void setCfreUsingmachinecode(java.lang.String cfreUsingmachinecode) {
		this.cfreUsingmachinecode = cfreUsingmachinecode;
	}

	/**
	 * 获取电桩ID(tbl_ElectricPile表中获取)属性
	 * 
	 * @return cfreElectricpileid
	 */
	public java.lang.Integer getCfreElectricpileid() {
		return cfreElectricpileid;
	}

	/**
	 * 设置电桩ID(tbl_ElectricPile表中获取)属性
	 * 
	 * @param cfreElectricpileid
	 */
	public void setCfreElectricpileid(java.lang.Integer cfreElectricpileid) {
		this.cfreElectricpileid = cfreElectricpileid;
	}

	/**
	 * 获取电桩名称属性
	 * 
	 * @return cfreElectricpilename
	 */
	public java.lang.String getCfreElectricpilename() {
		return cfreElectricpilename;
	}

	/**
	 * 设置电桩名称属性
	 * 
	 * @param cfreElectricpilename
	 */
	public void setCfreElectricpilename(java.lang.String cfreElectricpilename) {
		this.cfreElectricpilename = cfreElectricpilename;
	}

	/**
	 * 获取故障开始时间属性
	 * 
	 * @return cfreChargingsarttime
	 */
	public java.util.Date getCfreChargingsarttime() {
		return cfreChargingsarttime;
	}

	/**
	 * 设置故障开始时间属性
	 * 
	 * @param cfreChargingsarttime
	 */
	public void setCfreChargingsarttime(java.util.Date cfreChargingsarttime) {
		this.cfreChargingsarttime = cfreChargingsarttime;
	}

	/**
	 * 获取故障原因属性
	 * 
	 * @return cfreFaultcause
	 */
	public java.lang.String getCfreFaultcause() {
		return cfreFaultcause;
	}

	/**
	 * 设置故障原因属性
	 * 
	 * @param cfreFaultcause
	 */
	public void setCfreFaultcause(java.lang.String cfreFaultcause) {
		this.cfreFaultcause = cfreFaultcause;
	}

	/**
	 * 获取创建时间属性
	 * 
	 * @return cfreCreatedate
	 */
	public java.util.Date getCfreCreatedate() {
		return cfreCreatedate;
	}

	/**
	 * 设置创建时间属性
	 * 
	 * @param cfreCreatedate
	 */
	public void setCfreCreatedate(java.util.Date cfreCreatedate) {
		this.cfreCreatedate = cfreCreatedate;
	}

	/**
	 * 获取修改时间属性
	 * 
	 * @return cfreUpdatedate
	 */
	public java.util.Date getCfreUpdatedate() {
		return cfreUpdatedate;
	}

	/**
	 * 设置修改时间属性
	 * 
	 * @param cfreUpdatedate
	 */
	public void setCfreUpdatedate(java.util.Date cfreUpdatedate) {
		this.cfreUpdatedate = cfreUpdatedate;
	}

	/**
	 * 获取交易流水号属性
	 * 
	 * @param cfreTransactionnumber
	 */
	public java.lang.String getCfreTransactionnumber() {
		return cfreTransactionnumber;
	}

	/**
	 * 设置交易流水号属性
	 * 
	 * @param cfreTransactionnumber
	 */
	public void setCfreTransactionnumber(java.lang.String cfreTransactionnumber) {
		this.cfreTransactionnumber = cfreTransactionnumber;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("TblChargingfaultrecord");
		sb.append("{pkChargingfaultrecord=").append(pkChargingfaultrecord);
		sb.append(", cfreUsingmachinecode=").append(cfreUsingmachinecode);
		sb.append(", cfreElectricpileid=").append(cfreElectricpileid);
		sb.append(", cfreElectricpilename=").append(cfreElectricpilename);
		sb.append(", cfreChargingsarttime=").append(cfreChargingsarttime);
		sb.append(", cfreFaultcause=").append(cfreFaultcause);
		sb.append(", cfreCreatedate=").append(cfreCreatedate);
		sb.append(", cfreUpdatedate=").append(cfreUpdatedate);
		sb.append(", cfreTransactionnumber=").append(cfreTransactionnumber);
		sb.append('}');
		return sb.toString();
	}
}
package com.wanma.dubbox.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * tbl_ChargeInfo表
 * 
 * @author mew
 * 
 */
public class TblChargeinfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7716571659290445926L;
	private String mcode; // 使用端机器编号
	private Integer pid; // 充电点ID(tbl_PowerStation表中获取)
	private Integer intidef; // 充电接口标识
	private Integer linkSts; // 0：关 1 开
	private Integer cwSts; // 0：离线 1：故障 2：待机 3：工作 4：欠压故障 5：过压故障 6：过电流故障
	private BigDecimal ontV; // 充电输出电压
	private BigDecimal outC; // 充电输出电流
	private Integer outSts; // 0 关 1 开
	private BigDecimal talDg; // 有功总电度
	private Integer talTim; // 累计充电时间
	private Integer type; // 1：交流电 2：直流电
	private Integer uid; // 用户id

	public String getMcode() {
		return mcode;
	}

	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getIntidef() {
		return intidef;
	}

	public void setIntidef(Integer intidef) {
		this.intidef = intidef;
	}

	public Integer getLinkSts() {
		return linkSts;
	}

	public void setLinkSts(Integer linkSts) {
		this.linkSts = linkSts;
	}

	public Integer getCwSts() {
		return cwSts;
	}

	public void setCwSts(Integer cwSts) {
		this.cwSts = cwSts;
	}

	public BigDecimal getOntV() {
		return ontV;
	}

	public void setOntV(BigDecimal ontV) {
		this.ontV = ontV;
	}

	public BigDecimal getOutC() {
		return outC;
	}

	public void setOutC(BigDecimal outC) {
		this.outC = outC;
	}

	public Integer getOutSts() {
		return outSts;
	}

	public void setOutSts(Integer outSts) {
		this.outSts = outSts;
	}

	public BigDecimal getTalDg() {
		return talDg;
	}

	public void setTalDg(BigDecimal talDg) {
		this.talDg = talDg;
	}

	public Integer getTalTim() {
		return talTim;
	}

	public void setTalTim(Integer talTim) {
		this.talTim = talTim;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
}
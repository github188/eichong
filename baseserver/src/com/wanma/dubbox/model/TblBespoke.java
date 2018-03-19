package com.wanma.dubbox.model;

import java.math.BigDecimal;
import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;

public class TblBespoke extends BasicModel  {
    /**
	 * 
	 */
	private static final long serialVersionUID = -8473307898520072535L;

    private Integer eId;//电桩ID

    private String time;//预约时间

    private String remark;//预约描述

    private String times;//预约时间段

    private BigDecimal price;//预约单价

    private Integer sts;//预约状态 （1：取消预约 2：预约结束(订单完成) 3：续预约中 4：预约中 5:预约确认中 6：预约失败 7:订单完成未结算）


    private Integer hid;//枪口ID

    private Integer user;//用户id

    private String payCd;//预约订单编号

    private BigDecimal froAmt;//实际金额

    private Date begTim;//开始时间

    private Date endTim;//结束时间

    private Date rltyTim;//实际预约结束时间

    private Integer ordTp;//订单支付类型(0,未支付;1:订单结束)

    private String clitip;//手机后台IP

    private Byte uOrg;//用户来源（ 1:富士康; 2:吉利; 3:绿地; 4:浙誉; 5:车纷享; 以后根据情况再扩展或修改）

    private BigDecimal occFroAmt;//冻结金额

    private Integer pMode;//付费标识（1:先付费;2:后付费）

    private Integer orgNo;//充电伙伴标识，1000:爱充自己，其它
    
    /**
     * 关联信息外键字段数组
     */
    private String[] eids;
    private String[] hids;
    private String[] payCds;

	public Integer geteId() {
		return eId;
	}

	public void seteId(Integer eId) {
		this.eId = eId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getSts() {
		return sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

	
	public Integer getHid() {
		return hid;
	}

	public void setHid(Integer hid) {
		this.hid = hid;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

	public String getPayCd() {
		return payCd;
	}

	public void setPayCd(String payCd) {
		this.payCd = payCd;
	}

	public BigDecimal getFroAmt() {
		return froAmt;
	}

	public void setFroAmt(BigDecimal froAmt) {
		this.froAmt = froAmt;
	}

	public Date getBegTim() {
		return begTim;
	}

	public void setBegTim(Date begTim) {
		this.begTim = begTim;
	}

	public Date getEndTim() {
		return endTim;
	}

	public void setEndTim(Date endTim) {
		this.endTim = endTim;
	}

	public Date getRltyTim() {
		return rltyTim;
	}

	public void setRltyTim(Date rltyTim) {
		this.rltyTim = rltyTim;
	}

	public Integer getOrdTp() {
		return ordTp;
	}

	public void setOrdTp(Integer ordTp) {
		this.ordTp = ordTp;
	}

	public String getClitip() {
		return clitip;
	}

	public void setClitip(String clitip) {
		this.clitip = clitip;
	}

	public Byte getuOrg() {
		return uOrg;
	}

	public void setuOrg(Byte uOrg) {
		this.uOrg = uOrg;
	}

	public BigDecimal getOccFroAmt() {
		return occFroAmt;
	}

	public void setOccFroAmt(BigDecimal occFroAmt) {
		this.occFroAmt = occFroAmt;
	}

	public Integer getpMode() {
		return pMode;
	}

	public void setpMode(Integer pMode) {
		this.pMode = pMode;
	}

	public Integer getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(Integer orgNo) {
		this.orgNo = orgNo;
	}

	public String[] getHids() {
		return hids;
	}

	public void setHids(String[] hids) {
		this.hids = hids;
	}

	public String[] getEids() {
		return eids;
	}

	public void setEids(String[] eids) {
		this.eids = eids;
	}

	public String[] getPayCds() {
		return payCds;
	}

	public void setPayCds(String[] payCds) {
		this.payCds = payCds;
	}
    

   
}
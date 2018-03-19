package com.wanma.dubbox.model;

import java.io.Serializable;
import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;

/*优惠券表*/
public class TblCoupon extends BasicModel implements Serializable {
	private static final long serialVersionUID = 7196892269574600387L;

	private Integer actId;// 活动表主键

	private Integer tpId;// 优惠券品种主键

	private Short sts;// 优惠券状态（1-已使用）

	private Short elmt;// 电桩限制（1-仅限交流电桩，2-仅限直流电装，3-不限充电桩）

	private Integer val;// 优惠券面值

	private Integer cdt;// 优惠券使用条件

	private String code;// 优惠码

	private Integer uid;// 持有人（用户ID）

	private Date bgDate;// 生效时间

	private Date edate;// 失效时间

	private Integer stsTmp;// 状态：1:未兑换,2:已兑换,3:已使用,4:已过期

	private String[] actIds;// 活动表主键集
	private String[] tpIds;// 优惠券品种主键集

	public Integer getActId() {
		return actId;
	}

	public void setActId(Integer actId) {
		this.actId = actId;
	}

	public Integer getTpId() {
		return tpId;
	}

	public void setTpId(Integer tpId) {
		this.tpId = tpId;
	}

	public Short getSts() {
		return sts;
	}

	public void setSts(Short sts) {
		this.sts = sts;
	}

	public Short getElmt() {
		return elmt;
	}

	public void setElmt(Short elmt) {
		this.elmt = elmt;
	}

	public Integer getVal() {
		return val;
	}

	public void setVal(Integer val) {
		this.val = val;
	}

	public Integer getCdt() {
		return cdt;
	}

	public void setCdt(Integer cdt) {
		this.cdt = cdt;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Date getBgDate() {
		return bgDate;
	}

	public void setBgDate(Date bgDate) {
		this.bgDate = bgDate;
	}

	public Date getEdate() {
		return edate;
	}

	public void setEdate(Date edate) {
		this.edate = edate;
	}

	public Integer getStsTmp() {
		if (stsTmp == null) {
			if (sts != null && "1".equals(sts.toString())) {
				stsTmp = 3;
			} else if (uid == 0) {
				stsTmp = 0;
			} else {
				long t = System.currentTimeMillis();
				if (edate.getTime() > t)
					stsTmp = 2;
				else
					stsTmp = 4;
			}
		}
		return stsTmp;
	}

	public void setStsTmp(Integer stsTmp) {
		this.stsTmp = stsTmp;
	}

	public String[] getActIds() {
		return actIds;
	}

	public void setActIds(String[] actIds) {
		this.actIds = actIds;
	}

	public String[] getTpIds() {
		return tpIds;
	}

	public void setTpIds(String[] tpIds) {
		this.tpIds = tpIds;
	}
}
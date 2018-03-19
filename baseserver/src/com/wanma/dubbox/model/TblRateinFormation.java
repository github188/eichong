package com.wanma.dubbox.model;

import java.math.BigDecimal;
import java.util.Date;
import com.wanma.dubbox.model.common.BasicModel;

public class TblRateinFormation extends BasicModel {
	private static final long serialVersionUID = -5816461521036402859L;

	private Integer mdId;// 计费模型ID

	private Date effDate;// 生效日期

	private Date expDate;// 失效日期

	private BigDecimal freeMny;// 预冻结金额

	private BigDecimal minFreeMny;// 最小冻结金额

	private String qunDate;// 时间段

	private Date squnDate;// 开始时间段

	private Date endQunDate;// 结束时间段

	private Integer timMker;// 时段标志

	private BigDecimal tptimTrff;// 尖时段电价

	private BigDecimal pekEctyPrc;// 峰时段电价

	private BigDecimal uslPrc;// 平时段电价

	private BigDecimal vlyTimPrc;// 谷时段电价

	private BigDecimal rvtRat;// 预约单价

	private BigDecimal sviCg;// 服务费

	private BigDecimal wrnMny;// 告警余额

	private String uid;// 添加费率的用户，p_m_user表的id

	private String remark;// 备注

	private Integer udtUs;// 修改人（保存历史数据专用）

	public Integer getMdId() {
		return mdId;
	}

	public void setMdId(Integer mdId) {
		this.mdId = mdId;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}

	public BigDecimal getFreeMny() {
		return freeMny;
	}

	public void setFreeMny(BigDecimal freeMny) {
		this.freeMny = freeMny;
	}

	public BigDecimal getMinFreeMny() {
		return minFreeMny;
	}

	public void setMinFreeMny(BigDecimal minFreeMny) {
		this.minFreeMny = minFreeMny;
	}

	public String getQunDate() {
		return qunDate;
	}

	public void setQunDate(String qunDate) {
		this.qunDate = qunDate;
	}

	public Date getSqunDate() {
		return squnDate;
	}

	public void setSqunDate(Date squnDate) {
		this.squnDate = squnDate;
	}

	public Date getEndQunDate() {
		return endQunDate;
	}

	public void setEndQunDate(Date endQunDate) {
		this.endQunDate = endQunDate;
	}

	public Integer getTimMker() {
		return timMker;
	}

	public void setTimMker(Integer timMker) {
		this.timMker = timMker;
	}

	public BigDecimal getTptimTrff() {
		return tptimTrff;
	}

	public void setTptimTrff(BigDecimal tptimTrff) {
		this.tptimTrff = tptimTrff;
	}

	public BigDecimal getPekEctyPrc() {
		return pekEctyPrc;
	}

	public void setPekEctyPrc(BigDecimal pekEctyPrc) {
		this.pekEctyPrc = pekEctyPrc;
	}

	public BigDecimal getUslPrc() {
		return uslPrc;
	}

	public void setUslPrc(BigDecimal uslPrc) {
		this.uslPrc = uslPrc;
	}

	public BigDecimal getVlyTimPrc() {
		return vlyTimPrc;
	}

	public void setVlyTimPrc(BigDecimal vlyTimPrc) {
		this.vlyTimPrc = vlyTimPrc;
	}

	public BigDecimal getRvtRat() {
		return rvtRat;
	}

	public void setRvtRat(BigDecimal rvtRat) {
		this.rvtRat = rvtRat;
	}

	public BigDecimal getSviCg() {
		return sviCg;
	}

	public void setSviCg(BigDecimal sviCg) {
		this.sviCg = sviCg;
	}

	public BigDecimal getWrnMny() {
		return wrnMny;
	}

	public void setWrnMny(BigDecimal wrnMny) {
		this.wrnMny = wrnMny;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getUdtUs() {
		return udtUs;
	}

	public void setUdtUs(Integer udtUs) {
		this.udtUs = udtUs;
	}
}
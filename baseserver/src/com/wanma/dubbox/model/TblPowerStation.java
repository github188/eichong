package com.wanma.dubbox.model;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wanma.dubbox.model.common.BasicModel;

public class TblPowerStation extends BasicModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5957893563425279125L;

	private String name;// 电站名称

	private String adr;// 地址

	private BigDecimal lgt;// 经度

	private BigDecimal ltt;// 纬度

	private String pho;// 联系电话

	private Integer ste;// 电站状态（0草稿，3已驳回，5提交审核，10离线，12分享申请中，15上线）

	private String remark;// 备注

	private Integer powUs;// 电桩用途(电动车、自行车)

	private Integer isApot;// 电桩是否支持预约

	private String reason;// 审核驳回原因

	private String olTime;// 开放时间

	private String uname;// 电站所属用户

	private Integer cuId;// 电站创建人

	private String elids;// 电站绑定相关电桩，电桩id用逗号隔开

	private String pileCount;// 电桩总数
	private List<TblElectricPile> electricList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public BigDecimal getLgt() {
		return lgt;
	}

	public void setLgt(BigDecimal lgt) {
		this.lgt = lgt;
	}

	public BigDecimal getLtt() {
		return ltt;
	}

	public void setLtt(BigDecimal ltt) {
		this.ltt = ltt;
	}

	public String getPho() {
		return pho;
	}

	public void setPho(String pho) {
		this.pho = pho;
	}

	public Integer getSte() {
		return ste;
	}

	public void setSte(Integer ste) {
		this.ste = ste;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPowUs() {
		return powUs;
	}

	public void setPowUs(Integer powUs) {
		this.powUs = powUs;
	}

	public Integer getIsApot() {
		return isApot;
	}

	public void setIsApot(Integer isApot) {
		this.isApot = isApot;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getOlTime() {
		return olTime;
	}

	public void setOlTime(String olTime) {
		this.olTime = olTime;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public Integer getCuId() {
		return cuId;
	}

	public void setCuId(Integer cuId) {
		this.cuId = cuId;
	}

	public String getElids() {
		return elids;
	}

	public void setElids(String elids) {
		this.elids = elids;
	}

	public String getPileCount() {
		if (StringUtils.isNotBlank(elids))
			return String.valueOf(elids.split(",").length);
		return "0";
	}

	public void setPileCount(String pileCount) {
		this.pileCount = pileCount;
	}

	public List<TblElectricPile> getElectricList() {
		return electricList;
	}

	public void setElectricList(List<TblElectricPile> electricList) {
		this.electricList = electricList;
	}

}
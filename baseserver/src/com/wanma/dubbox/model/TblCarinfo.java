package com.wanma.dubbox.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 
 * tbl_CarInfo表
 * 
 * @author mew
 * 
 */
public class TblCarinfo extends BasicModel implements Serializable {
	private static final long serialVersionUID = -8504782827678496759L;
	private String name; // 电动车类型名称
	private BigDecimal maxOdo; // 电动车最大续航里程
	private BigDecimal bttCty; // 电动车电池容量
	private String remark; // 备注
	private String icon; // 电动车品牌图标
	private Integer comId; // 电动车品牌名称，根据配置参数内容表获取品牌名称
	private String cgTim; // 充电时间
	private Integer bttTp;// 电池类型
	private Integer cgMod; // 充电模式
	private Integer inf; // 接口标准
	private Integer sts; // 状态(0启用，1禁用)
	private String brandName; // 汽车厂家名称

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMaxOdo() {
		return maxOdo;
	}

	public void setMaxOdo(BigDecimal maxOdo) {
		this.maxOdo = maxOdo;
	}

	public BigDecimal getBttCty() {
		return bttCty;
	}

	public void setBttCty(BigDecimal bttCty) {
		this.bttCty = bttCty;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
	}

	public String getCgTim() {
		return cgTim;
	}

	public void setCgTim(String cgTim) {
		this.cgTim = cgTim;
	}

	public Integer getBttTp() {
		return bttTp;
	}

	public void setBttTp(Integer bttTp) {
		this.bttTp = bttTp;
	}

	public Integer getCgMod() {
		return cgMod;
	}

	public void setCgMod(Integer cgMod) {
		this.cgMod = cgMod;
	}

	public Integer getInf() {
		return inf;
	}

	public void setInf(Integer inf) {
		this.inf = inf;
	}

	public Integer getSts() {
		return sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
}
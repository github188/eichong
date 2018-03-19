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
public class TblProvince extends BasicModel implements Serializable {
	private static final long serialVersionUID = -8504782827678496759L;
	private String name; //省份名称
	private String creUser; // 创建人
	private String luUser; //最后修改人
	private BigDecimal jdEty; // 尖段电费
	private BigDecimal fdEty; // 峰段电费
	private BigDecimal pdEty; // 平段电费
	private BigDecimal gdEty;//谷段电费
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getJdEty() {
		return jdEty;
	}
	public void setJdEty(BigDecimal jdEty) {
		this.jdEty = jdEty;
	}
	public BigDecimal getFdEty() {
		return fdEty;
	}
	public void setFdEty(BigDecimal fdEty) {
		this.fdEty = fdEty;
	}
	public BigDecimal getPdEty() {
		return pdEty;
	}
	public void setPdEty(BigDecimal pdEty) {
		this.pdEty = pdEty;
	}
	public BigDecimal getGdEty() {
		return gdEty;
	}
	public void setGdEty(BigDecimal gdEty) {
		this.gdEty = gdEty;
	}
	public String getCreUser() {
		return creUser;
	}
	public void setCreUser(String creUser) {
		this.creUser = creUser;
	}
	public String getLuUser() {
		return luUser;
	}
	public void setLuUser(String luUser) {
		this.luUser = luUser;
	}
}
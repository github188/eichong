package com.wanma.dubbox.model;

import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 
 * tbl_gateservice表
 * @author lihy
 * 2016-08-08	
 */
public class TblGateservice extends BasicModel {
	private static final long serialVersionUID = -8942748927479087124L;
	private String name; // 名称
	private String ip; // IP地址
	private Integer port; //端口号
	private Integer faiCount;// 失败次数
	private Integer ste; //状态
	private Integer cuserId; //创建人
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public Integer getFaiCount() {
		return faiCount;
	}
	public void setFaiCount(Integer faiCount) {
		this.faiCount = faiCount;
	}
	public Integer getSte() {
		return ste;
	}
	public void setSte(Integer ste) {
		this.ste = ste;
	}
	public Integer getCuserId() {
		return cuserId;
	}
	public void setCuserId(Integer cuserId) {
		this.cuserId = cuserId;
	}
}
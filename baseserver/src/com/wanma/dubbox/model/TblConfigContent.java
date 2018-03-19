package com.wanma.dubbox.model;

import com.wanma.dubbox.model.common.BasicModel;

public class TblConfigContent extends BasicModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8972944533313703051L;

	private Integer pramid;// 配置参数ID

	private String ctt;// 内容

	private Integer sts;// 状态(0启用，1禁用)

	private String mem;// 备注

	public Integer getPramid() {
		return pramid;
	}

	public void setPramid(Integer pramid) {
		this.pramid = pramid;
	}

	public String getCtt() {
		return ctt;
	}

	public void setCtt(String ctt) {
		this.ctt = ctt;
	}

	public Integer getSts() {
		return sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

	public String getMem() {
		return mem;
	}

	public void setMem(String mem) {
		this.mem = mem;
	}

}
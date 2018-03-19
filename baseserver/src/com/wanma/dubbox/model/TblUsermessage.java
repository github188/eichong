package com.wanma.dubbox.model;


import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;


public class TblUsermessage extends BasicModel {
	private static final long serialVersionUID = -962729075555088781L;
	private Integer uid;
	private Integer froUid;
	private String froUname;
	private String ttl;
	private String txt;
	private Integer sts;
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getFroUid() {
		return froUid;
	}
	public void setFroUid(Integer froUid) {
		this.froUid = froUid;
	}
	public String getFroUname() {
		return froUname;
	}
	public void setFroUname(String froUname) {
		this.froUname = froUname;
	}
	public String getTtl() {
		return ttl;
	}
	public void setTtl(String ttl) {
		this.ttl = ttl;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public Integer getSts() {
		return sts;
	}
	public void setSts(Integer sts) {
		this.sts = sts;
	}
}

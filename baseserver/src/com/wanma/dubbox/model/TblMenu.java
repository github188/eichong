package com.wanma.dubbox.model;

import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 菜单实体数据模型
 * 
 * @version V1.0
 * @author lhy
 */
public class TblMenu extends BasicModel {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = -8855032796679657161L;

	/** 菜单分类：1菜单权限，2按钮权限 */
	private String type;

	/** 显示内容 */
	private String txt;

	/** 排序 */
	private Long stNum;

	/** 链接 */
	private String url;

	/** 父菜单ID */
	private String pid;

	/** 画面显示标签 */
	private String rel;

	/** 创建人 */
	private String creUs;
	/** 修改人 */
	private String uptUs;

	/** 备注 */
	private String nt;

	private int hasChild;// 子菜单数量

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Long getStNum() {
		return stNum;
	}

	public void setStNum(Long stNum) {
		this.stNum = stNum;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getNt() {
		return nt;
	}

	public void setNt(String nt) {
		this.nt = nt;
	}

	public String getCreUs() {
		return creUs;
	}

	public void setCreUs(String creUs) {
		this.creUs = creUs;
	}

	public String getUptUs() {
		return uptUs;
	}

	public void setUptUs(String uptUs) {
		this.uptUs = uptUs;
	}

	public int getHasChild() {
		return hasChild;
	}

	public void setHasChild(int hasChild) {
		this.hasChild = hasChild;
	}
}

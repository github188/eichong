package com.wanma.dubbox.model;

import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 
 * tbl_HomePage表
 * 
 * @author songjf
 * 
 */
public class TblHomepage extends BasicModel {
	private static final long serialVersionUID = 4968325924573588560L;
	private String name; // 首页广告名称
	private String img; // 首页图片
	private String url; // 链接
	private Integer seq; // 轮播顺序
	private Integer sts; // 0：显示 -1 已删除
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public Integer getSts() {
		return sts;
	}
	public void setSts(Integer sts) {
		this.sts = sts;
	}
}
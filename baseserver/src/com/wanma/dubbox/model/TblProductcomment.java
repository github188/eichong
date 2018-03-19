package com.wanma.dubbox.model;

import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 
 * tbl_ProductComment表
 * 
 * @author songjf
 * 
 */
public class TblProductcomment extends BasicModel {
	private static final long serialVersionUID = -8530134350066229713L;
	private Integer prdctId; // 产品Id(tbl_Product表中获取)
	private Integer uid; // 用户Id(tbl_user_normal_view表中获取)
	private String uname; // 用户名
	private Date ctime; // 评论时间
	private Double star; // 星级
	private String pic; // 评论图片
	private Integer odrId; // 订单号
	private Integer type; // 1：桩体评价 2：充电点评价 3：商城商品评价:
	private Integer sts; // 0：显示 -1 已删除
	private String txt; // 评论内容

	public Integer getPrdctId() {
		return prdctId;
	}

	public void setPrdctId(Integer prdctId) {
		this.prdctId = prdctId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public java.util.Date getCtime() {
		return ctime;
	}

	public void setCtime(java.util.Date ctime) {
		this.ctime = ctime;
	}

	public Double getStar() {
		return star;
	}

	public void setStar(Double star) {
		this.star = star;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getOdrId() {
		return odrId;
	}

	public void setOdrId(Integer odrId) {
		this.odrId = odrId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSts() {
		return sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

}
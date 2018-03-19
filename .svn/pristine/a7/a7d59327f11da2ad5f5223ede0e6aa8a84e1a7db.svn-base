package com.wanma.dubbox.model;

import java.io.Serializable;
import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 广告管理
 * 
 * @Description:
 * @updator： lhy
 * @version：V3.2.0
 */
public class TblAdvertisement extends BasicModel implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private String name;// 广告名称
	private Integer type;// 广告类型,1: 闪屏 2 :插屏
	private Integer igt;// 是否跳转,0 :否 1: 是
	private String desc;// 广告说明
	private String url;// URL地址
	private Integer sts;// 是否上架状态,0: 上架 1: 下架
	private Integer time;// 闪屏曝光时间
	private Integer lct;// 插屏button位置 ,1: 左一button 2: 左二button 3:右二button
						// 4:右一button
	private Date bgTm;// 广告开始时间
	private Date edTm;// 广告结束时间
	private Integer uid;// 管理员信息表 user_id

	private String picFid; // 广告图片关联键
	private String picListFid; // 广告动态列表图片关联键
	private String picUrl; // 广告图片地址
	private String lstPicUrl; // 广告动态列表图片地址
	private Integer advSts;// 广告状态 0: 未开始 1: 进行中 2: 已结束

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIgt() {
		return igt;
	}

	public void setIgt(Integer igt) {
		this.igt = igt;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSts() {
		return sts;
	}

	public void setSts(Integer sts) {
		this.sts = sts;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public Integer getLct() {
		return lct;
	}

	public void setLct(Integer lct) {
		this.lct = lct;
	}


	public Date getBgTm() {
		return bgTm;
	}

	public void setBgTm(Date bgTm) {
		this.bgTm = bgTm;
	}

	public Date getEdTm() {
		return edTm;
	}

	public void setEdTm(Date edTm) {
		this.edTm = edTm;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getPicFid() {
		return picFid;
	}

	public void setPicFid(String picFid) {
		this.picFid = picFid;
	}

	public String getPicListFid() {
		return picListFid;
	}

	public void setPicListFid(String picListFid) {
		this.picListFid = picListFid;
	}
	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getLstPicUrl() {
		return lstPicUrl;
	}

	public void setLstPicUrl(String lstPicUrl) {
		this.lstPicUrl = lstPicUrl;
	}

	public Integer getAdvSts() {
		if (bgTm != null && edTm != null) {
			double t = System.currentTimeMillis();
			if (t > edTm.getTime())
				advSts = 2;
			else if (t < bgTm.getTime())
				advSts = 0;
			else
				advSts = 1;
		}
		return advSts;
	}

	public void setAdvSts(Integer advSts) {
		this.advSts = advSts;
	}

}

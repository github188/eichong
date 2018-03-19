package com.wanma.model;

import java.io.Serializable;
import java.util.Date;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * 广告管理
 * 
 * @Description:
 * @updator： lhy
 * @version：V3.2.0
 */
public class TblAdvertisement extends BasicListAndMutiFile implements
		Serializable {
	private static final long serialVersionUID = 1L;
	private Integer pkAdId;// 主键
	private String adName;// 广告名称
	private Integer adType;// 广告类型,1: 闪屏 2 :插屏
	private Integer adIsGoto;// 是否跳转,0 :否 1: 是
	private String adDesc;// 广告说明
	private String adURL;// URL地址
	private Integer adStatus;// 是否上架状态,0: 上架 1: 下架
	private Integer adTime;// 闪屏曝光时间
	private Integer adLocation;// 插屏button位置 ,1: 左一button 2: 左二button 3:右二button
								// 4:右一button
	private Date adCreatedate;// 创建时间
	private Date adUpdatedate;// 更新时间
	private Date beginAdTime;// 广告开始时间
	private Date endAdTime;// 广告结束时间
	private Integer fkUserId;// 管理员信息表 user_id

	private String advPicFileId; // 广告图片关联键
	private String advListFileId; // 广告动态列表图片关联键
	private String advPicUrl; // 广告图片地址
	private String advListPicUrl; // 广告动态列表图片地址
	private Integer advSts;// 广告状态 0: 未开始 1: 进行中 2: 已结束
	private String uName;//操作人姓名

	public String getuName() {
		return uName;
	}

	public void setuName(String uName) {
		this.uName = uName;
	}

	public Integer getPkAdId() {
		return pkAdId;
	}

	public void setPkAdId(Integer pkAdId) {
		this.pkAdId = pkAdId;
	}

	public String getAdName() {
		return adName;
	}

	public void setAdName(String adName) {
		this.adName = adName;
	}

	public Integer getAdType() {
		return adType;
	}

	public void setAdType(Integer adType) {
		this.adType = adType;
	}

	public Integer getAdIsGoto() {
		return adIsGoto;
	}

	public void setAdIsGoto(Integer adIsGoto) {
		this.adIsGoto = adIsGoto;
	}

	public String getAdDesc() {
		return adDesc;
	}

	public void setAdDesc(String adDesc) {
		this.adDesc = adDesc;
	}

	public String getAdURL() {
		return adURL;
	}

	public void setAdURL(String adURL) {
		this.adURL = adURL;
	}

	public Integer getAdStatus() {
		return adStatus;
	}

	public void setAdStatus(Integer adStatus) {
		this.adStatus = adStatus;
	}

	public Integer getAdTime() {
		return adTime;
	}

	public void setAdTime(Integer adTime) {
		this.adTime = adTime;
	}

	public Integer getAdLocation() {
		return adLocation;
	}

	public void setAdLocation(Integer adLocation) {
		this.adLocation = adLocation;
	}

	public Date getAdCreatedate() {
		return adCreatedate;
	}

	public void setAdCreatedate(Date adCreatedate) {
		this.adCreatedate = adCreatedate;
	}

	public Date getAdUpdatedate() {
		return adUpdatedate;
	}

	public void setAdUpdatedate(Date adUpdatedate) {
		this.adUpdatedate = adUpdatedate;
	}

	public Date getBeginAdTime() {
		return beginAdTime;
	}

	public void setBeginAdTime(Date beginAdTime) {
		this.beginAdTime = beginAdTime;
	}

	public Date getEndAdTime() {
		return endAdTime;
	}

	public void setEndAdTime(Date endAdTime) {
		this.endAdTime = endAdTime;
	}

	public Integer getFkUserId() {
		return fkUserId;
	}

	public void setFkUserId(Integer fkUserId) {
		this.fkUserId = fkUserId;
	}

	public String getAdvPicFileId() {
		return advPicFileId;
	}

	public void setAdvPicFileId(String advPicFileId) {
		this.advPicFileId = advPicFileId;
	}

	public String getAdvListFileId() {
		return advListFileId;
	}

	public void setAdvListFileId(String advListFileId) {
		this.advListFileId = advListFileId;
	}

	public String getAdvPicUrl() {
		return advPicUrl;
	}

	public void setAdvPicUrl(String advPicUrl) {
		this.advPicUrl = advPicUrl;
	}

	public String getAdvListPicUrl() {
		return advListPicUrl;
	}

	public void setAdvListPicUrl(String advListPicUrl) {
		this.advListPicUrl = advListPicUrl;
	}

	public Integer getAdvSts() {
		if (beginAdTime != null && endAdTime != null) {
			double t = System.currentTimeMillis();
			if (t > endAdTime.getTime())
				advSts = 2;
			else if (t < beginAdTime.getTime())
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

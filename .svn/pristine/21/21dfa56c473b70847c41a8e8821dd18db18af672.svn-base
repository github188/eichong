package com.wanma.ims.common.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.wanma.ims.common.domain.base.BasicModel;

public class AdvertisementDO extends BasicModel {
	/**
	 * 插屏 闪屏
	 */
	private static final long serialVersionUID = 4917318877003418998L;
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
	private Date beginAdTimeDate;// 广告开始时间
	private Date endAdTimeDate;// 广告结束时间
	private String beginAdTime;// 广告开始时间
	private String endAdTime;// 广告结束时间
	private Integer fkUserId;// 基表 user_id
	private String userAccount;// 基表 user_account

	private String advPicFileId; // 广告图片关联键
	private String advListFileId; // 广告动态列表图片关联键
	private String advPicUrl; // 广告图片地址
	private String advListPicUrl; // 广告动态列表图片地址
	private Integer advSts;// 广告状态 0: 未开始 1: 进行中 2: 已结束
	
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

	public Date getBeginAdTimeDate() {
		return beginAdTimeDate;
	}

	public void setBeginAdTimeDate(Date beginAdTimeDate) {
		this.beginAdTimeDate = beginAdTimeDate;
	}

	public Date getEndAdTimeDate() {
		return endAdTimeDate;
	}

	public void setEndAdTimeDate(Date endAdTimeDate) {
		this.endAdTimeDate = endAdTimeDate;
	}

	public String getBeginAdTime() {
		return beginAdTime;
	}

	public void setBeginAdTime(String beginAdTime) {
		this.beginAdTime = beginAdTime;
	}

	public String getEndAdTime() {
		return endAdTime;
	}

	public void setEndAdTime(String endAdTime) {
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
		if (beginAdTimeDate != null && endAdTimeDate != null) {
			double t = System.currentTimeMillis();
			if (t > endAdTimeDate.getTime() || adStatus == 1)
				advSts = 2;
			else if (t < beginAdTimeDate.getTime())
				advSts = 0;
			else
				advSts = 1;
		}
		return advSts;
	}

	public void setAdvSts(Integer advSts) {
		this.advSts = advSts;
	}
	
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	@Override
	public String toString() {
		return "AdvertisementDO [pkAdId=" + pkAdId + ", adName=" + adName
				+ ", adType=" + adType + ", adIsGoto=" + adIsGoto + ", adDesc="
				+ adDesc + ", adURL=" + adURL + ", adStatus=" + adStatus
				+ ", adTime=" + adTime + ", adLocation=" + adLocation
				+ ", adCreatedate=" + adCreatedate + ", adUpdatedate="
				+ adUpdatedate + ", beginAdTimeDate=" + beginAdTimeDate
				+ ", endAdTimeDate=" + endAdTimeDate + ", beginAdTime="
				+ beginAdTime + ", endAdTime=" + endAdTime + ", fkUserId="
				+ fkUserId + ", userAccount=" + userAccount + ", advPicFileId="
				+ advPicFileId + ", advListFileId=" + advListFileId
				+ ", advPicUrl=" + advPicUrl + ", advListPicUrl="
				+ advListPicUrl + ", advSts=" + advSts + "]";
	}

	
	
	
}
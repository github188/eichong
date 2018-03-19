package com.wanma.model;

import java.io.Serializable;
import java.util.Date;

import com.bluemobi.product.model.common.BasicListAndMutiFile;

/**
 * banner管理
 */
public class TblBanner extends BasicListAndMutiFile implements
		Serializable {
		private java.lang.Integer pkBannerId;//主键
		private java.lang.String bannerDesc;//备注
		private java.lang.String bannerUrl;//URL地址
		private java.lang.Integer bannerStatus;//状态
		private java.lang.Integer bannerSort;//播放顺序
		private java.util.Date bannerCreatedate;//创建时间
		private java.util.Date bannerUpdatedate;//更新时间
		private java.util.Date bannerBeginTime;//开始时间
		private java.util.Date bannerEndTime;//结束时间
		private java.lang.String bannerProvinceCode;//电站所属省份代码
		private java.lang.String bannerCityCode;//电站所属城市代码
		private java.lang.String bannerCountyCode;//电站所属区县代码
		
		//非数据库字段
		private java.lang.String bannerPicUrl;//banner图片地址
		private java.lang.String bannerRegion;// 地区
		private java.lang.String bannerPicFileId;// 广告图片关键词
		private java.lang.String bannerStatusType;//状态
		public java.lang.Integer getPkBannerId() {
			return pkBannerId;
		}
		public void setPkBannerId(java.lang.Integer pkBannerId) {
			this.pkBannerId = pkBannerId;
		}
		public java.lang.String getBannerDesc() {
			return bannerDesc;
		}
		public void setBannerDesc(java.lang.String bannerDesc) {
			this.bannerDesc = bannerDesc;
		}
		public java.lang.String getBannerUrl() {
			return bannerUrl;
		}
		public void setBannerUrl(java.lang.String bannerUrl) {
			this.bannerUrl = bannerUrl;
		}
		public java.lang.Integer getBannerStatus() {
			return bannerStatus;
		}
		public void setBannerStatus(java.lang.Integer bannerStatus) {
			this.bannerStatus = bannerStatus;
		}
		public java.lang.Integer getBannerSort() {
			return bannerSort;
		}
		public void setBannerSort(java.lang.Integer bannerSort) {
			this.bannerSort = bannerSort;
		}
		public java.util.Date getBannerCreatedate() {
			return bannerCreatedate;
		}
		public void setBannerCreatedate(java.util.Date bannerCreatedate) {
			this.bannerCreatedate = bannerCreatedate;
		}
		public java.util.Date getBannerUpdatedate() {
			return bannerUpdatedate;
		}
		public void setBannerUpdatedate(java.util.Date bannerUpdatedate) {
			this.bannerUpdatedate = bannerUpdatedate;
		}
		public java.util.Date getBannerBeginTime() {
			return bannerBeginTime;
		}
		public void setBannerBeginTime(java.util.Date bannerBeginTime) {
			this.bannerBeginTime = bannerBeginTime;
		}
		public java.util.Date getBannerEndTime() {
			return bannerEndTime;
		}
		public void setBannerEndTime(java.util.Date bannerEndTime) {
			this.bannerEndTime = bannerEndTime;
		}
		public java.lang.String getBannerProvinceCode() {
			return bannerProvinceCode;
		}
		public void setBannerProvinceCode(java.lang.String bannerProvinceCode) {
			this.bannerProvinceCode = bannerProvinceCode;
		}
		public java.lang.String getBannerCityCode() {
			return bannerCityCode;
		}
		public void setBannerCityCode(java.lang.String bannerCityCode) {
			this.bannerCityCode = bannerCityCode;
		}
		public java.lang.String getBannerCountyCode() {
			return bannerCountyCode;
		}
		public void setBannerCountyCode(java.lang.String bannerCountyCode) {
			this.bannerCountyCode = bannerCountyCode;
		}
		
		
		public java.lang.String getBannerPicUrl() {
			return bannerPicUrl;
		}
		public void setBannerPicUrl(java.lang.String bannerPicUrl) {
			this.bannerPicUrl = bannerPicUrl;
		}
		public java.lang.String getBannerRegion() {
			return bannerRegion;
		}
		public void setBannerRegion(java.lang.String bannerRegion) {
			this.bannerRegion = bannerRegion;
		}
		
		public java.lang.String getBannerPicFileId() {
			return bannerPicFileId;
		}
		public void setBannerPicFileId(java.lang.String bannerPicFileId) {
			this.bannerPicFileId = bannerPicFileId;
		}
		public java.lang.String getBannerStatusType() {
			return bannerStatusType;
		}
		public void setBannerStatusType(java.lang.String bannerStatusType) {
			this.bannerStatusType = bannerStatusType;
		}
		@Override
		public String toString() {
			return "TblBanner [pkBannerId=" + pkBannerId + ", bannerDesc="
					+ bannerDesc + ", bannerUrl=" + bannerUrl
					+ ", bannerStatus=" + bannerStatus + ", bannerSort="
					+ bannerSort + ", bannerCreatedate=" + bannerCreatedate
					+ ", bannerUpdatedate=" + bannerUpdatedate
					+ ", bannerBeginTime=" + bannerBeginTime
					+ ", bannerEndTime=" + bannerEndTime
					+ ", bannerProvinceCode=" + bannerProvinceCode
					+ ", bannerCityCode=" + bannerCityCode
					+ ", bannerCountyCode=" + bannerCountyCode
					+ ", bannerPicUrl=" + bannerPicUrl + ", bannerRegion="
					+ bannerRegion + ", bannerPicFileId=" + bannerPicFileId
					+ ", bannerStatusType=" + bannerStatusType + "]";
		}
		
		
		
		

}

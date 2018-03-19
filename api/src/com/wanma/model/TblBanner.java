package com.wanma.model;

import java.util.Date;

public class TblBanner {
    private Integer pkBlId;

    private String blDesc;

    private String blUrl;

    private Short blStatus;

    private Short blSort;

    private Date blCreatedate;

    private Date blUpdatedate;

    private Date blBeginTime;

    private Date blEndTime;

    private String blProvincecode;

    private String blCitycode;

    private String blCountycode;
    
    private String bannerPicUrl;
    

    public String getBannerPicUrl() {
		return bannerPicUrl;
	}

	public void setBannerPicUrl(String bannerPicUrl) {
		this.bannerPicUrl = bannerPicUrl;
	}

	public Integer getPkBlId() {
        return pkBlId;
    }

    public void setPkBlId(Integer pkBlId) {
        this.pkBlId = pkBlId;
    }

    public String getBlDesc() {
        return blDesc;
    }

    public void setBlDesc(String blDesc) {
        this.blDesc = blDesc == null ? null : blDesc.trim();
    }

    public String getBlUrl() {
        return blUrl;
    }

    public void setBlUrl(String blUrl) {
        this.blUrl = blUrl == null ? null : blUrl.trim();
    }

    public Short getBlStatus() {
        return blStatus;
    }

    public void setBlStatus(Short blStatus) {
        this.blStatus = blStatus;
    }

    public Short getBlSort() {
        return blSort;
    }

    public void setBlSort(Short blSort) {
        this.blSort = blSort;
    }

    public Date getBlCreatedate() {
        return blCreatedate;
    }

    public void setBlCreatedate(Date blCreatedate) {
        this.blCreatedate = blCreatedate;
    }

    public Date getBlUpdatedate() {
        return blUpdatedate;
    }

    public void setBlUpdatedate(Date blUpdatedate) {
        this.blUpdatedate = blUpdatedate;
    }

    public Date getBlBeginTime() {
        return blBeginTime;
    }

    public void setBlBeginTime(Date blBeginTime) {
        this.blBeginTime = blBeginTime;
    }

    public Date getBlEndTime() {
        return blEndTime;
    }

    public void setBlEndTime(Date blEndTime) {
        this.blEndTime = blEndTime;
    }

    public String getBlProvincecode() {
        return blProvincecode;
    }

    public void setBlProvincecode(String blProvincecode) {
        this.blProvincecode = blProvincecode == null ? null : blProvincecode.trim();
    }

    public String getBlCitycode() {
        return blCitycode;
    }

    public void setBlCitycode(String blCitycode) {
        this.blCitycode = blCitycode == null ? null : blCitycode.trim();
    }

    public String getBlCountycode() {
        return blCountycode;
    }

    public void setBlCountycode(String blCountycode) {
        this.blCountycode = blCountycode == null ? null : blCountycode.trim();
    }
}
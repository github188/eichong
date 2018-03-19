package com.wanma.model;

import java.util.Date;

public class TblMessageInfo {
    private Integer pkMeiId;

    private String meiName;

    private String meiContent;

    private Short meiStatus;

    private Short meiType;

    private Date meiBeginTime;

    private Date meiEndTime;

    private Date meiCreatedate;

    private Date meiUpdatedate;

    private String meiProvincecode;

    private String meiCitycode;

    private String meiCountycode;
    
   

  
	public Integer getPkMeiId() {
        return pkMeiId;
    }

    public void setPkMeiId(Integer pkMeiId) {
        this.pkMeiId = pkMeiId;
    }

    public String getMeiName() {
        return meiName;
    }

    public void setMeiName(String meiName) {
        this.meiName = meiName == null ? null : meiName.trim();
    }

    public String getMeiContent() {
        return meiContent;
    }

    public void setMeiContent(String meiContent) {
        this.meiContent = meiContent == null ? null : meiContent.trim();
    }

    public Short getMeiStatus() {
        return meiStatus;
    }

    public void setMeiStatus(Short meiStatus) {
        this.meiStatus = meiStatus;
    }

    public Short getMeiType() {
        return meiType;
    }

    public void setMeiType(Short meiType) {
        this.meiType = meiType;
    }

    public Date getMeiBeginTime() {
        return meiBeginTime;
    }

    public void setMeiBeginTime(Date meiBeginTime) {
        this.meiBeginTime = meiBeginTime;
    }

 
    public Date getMeiEndTime() {
		return meiEndTime;
	}

	public void setMeiEndTime(Date meiEndTime) {
		this.meiEndTime = meiEndTime;
	}

	public Date getMeiCreatedate() {
        return meiCreatedate;
    }

    public void setMeiCreatedate(Date meiCreatedate) {
        this.meiCreatedate = meiCreatedate;
    }

    public Date getMeiUpdatedate() {
        return meiUpdatedate;
    }

    public void setMeiUpdatedate(Date meiUpdatedate) {
        this.meiUpdatedate = meiUpdatedate;
    }

    public String getMeiProvincecode() {
        return meiProvincecode;
    }

    public void setMeiProvincecode(String meiProvincecode) {
        this.meiProvincecode = meiProvincecode == null ? null : meiProvincecode.trim();
    }

    public String getMeiCitycode() {
        return meiCitycode;
    }

    public void setMeiCitycode(String meiCitycode) {
        this.meiCitycode = meiCitycode == null ? null : meiCitycode.trim();
    }

    public String getMeiCountycode() {
        return meiCountycode;
    }

    public void setMeiCountycode(String meiCountycode) {
        this.meiCountycode = meiCountycode == null ? null : meiCountycode.trim();
    }
}
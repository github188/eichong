package com.wanma.model;

import java.util.Date;

public class TblAppButtonList {
    private Integer pkAblId;

    private String ablName;

    private String ablDesc;

    private Short ablStatus;

    private Short ablType;

    private Short ablAction;

    private Short ablSort;

    private Date ablCreatedate;

    private Date ablUpdatedate;
    
    private String ablUrl;
    
    private String buttonPicUrl;
    
    

    public String getButtonPicUrl() {
		return buttonPicUrl;
	}

	public void setButtonPicUrl(String buttonPicUrl) {
		this.buttonPicUrl = buttonPicUrl;
	}

	public String getAblUrl() {
		return ablUrl;
	}

	public void setAblUrl(String ablUrl) {
		this.ablUrl = ablUrl;
	}

	public Integer getPkAblId() {
        return pkAblId;
    }

    public void setPkAblId(Integer pkAblId) {
        this.pkAblId = pkAblId;
    }

    public String getAblName() {
        return ablName;
    }

    public void setAblName(String ablName) {
        this.ablName = ablName == null ? null : ablName.trim();
    }

    public String getAblDesc() {
        return ablDesc;
    }

    public void setAblDesc(String ablDesc) {
        this.ablDesc = ablDesc == null ? null : ablDesc.trim();
    }

    public Short getAblStatus() {
        return ablStatus;
    }

    public void setAblStatus(Short ablStatus) {
        this.ablStatus = ablStatus;
    }

    public Short getAblType() {
        return ablType;
    }

    public void setAblType(Short ablType) {
        this.ablType = ablType;
    }

    public Short getAblAction() {
        return ablAction;
    }

    public void setAblAction(Short ablAction) {
        this.ablAction = ablAction;
    }

    public Short getAblSort() {
        return ablSort;
    }

    public void setAblSort(Short ablSort) {
        this.ablSort = ablSort;
    }

    public Date getAblCreatedate() {
        return ablCreatedate;
    }

    public void setAblCreatedate(Date ablCreatedate) {
        this.ablCreatedate = ablCreatedate;
    }

    public Date getAblUpdatedate() {
        return ablUpdatedate;
    }

    public void setAblUpdatedate(Date ablUpdatedate) {
        this.ablUpdatedate = ablUpdatedate;
    }
}
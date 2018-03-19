package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

import java.util.Date;

public class VersionDO extends BasicModel {

    private Integer pkVersion; // 主键
    private String versNumber; // 版本号
    private Integer versType; // 旧版本 1：安卓 2 IOS 新版本类型更改为：1: 爱充APP 2 IOS 3: 政企APP
    private String versUrl; // 版本地址
    private String versRemark;            //版本描述
    private Date versCreatedate; // 创建时间
    private Date versUpdatedate; // 修改时间
    private Integer isAutoUpdate;//是否强制更新 1 是 0 否

    public Integer getPkVersion() {
        return pkVersion;
    }

    public void setPkVersion(Integer pkVersion) {
        this.pkVersion = pkVersion;
    }

    public String getVersNumber() {
        return versNumber;
    }

    public void setVersNumber(String versNumber) {
        this.versNumber = versNumber;
    }

    public Integer getVersType() {
        return versType;
    }

    public void setVersType(Integer versType) {
        this.versType = versType;
    }

    public String getVersUrl() {
        return versUrl;
    }

    public void setVersUrl(String versUrl) {
        this.versUrl = versUrl;
    }

    public String getVersRemark() {
        return versRemark;
    }

    public void setVersRemark(String versRemark) {
        this.versRemark = versRemark;
    }

    public Date getVersCreatedate() {
        return versCreatedate;
    }

    public void setVersCreatedate(Date versCreatedate) {
        this.versCreatedate = versCreatedate;
    }

    public Date getVersUpdatedate() {
        return versUpdatedate;
    }

    public void setVersUpdatedate(Date versUpdatedate) {
        this.versUpdatedate = versUpdatedate;
    }

    public Integer getIsAutoUpdate() {
        return isAutoUpdate;
    }

    public void setIsAutoUpdate(Integer isAutoUpdate) {
        this.isAutoUpdate = isAutoUpdate;
    }
}
package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

/**
 * 用户vin码关系表
 *
 * @author mb
 * @date 2018-1-18
 */
public class UserVinRelaDO  {
        private Long pkId;
        private Long vinId;//vin码主键
        private Long userId;//用户主键
    private String cvVinCode; //VIN码
    private String cvLicenseNumber;//车牌号

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }

    public Long getVinId() {
        return vinId;
    }

    public void setVinId(Long vinId) {
        this.vinId = vinId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCvVinCode() {
        return cvVinCode;
    }

    public void setCvVinCode(String cvVinCode) {
        this.cvVinCode = cvVinCode;
    }

    public String getCvLicenseNumber() {
        return cvLicenseNumber;
    }

    public void setCvLicenseNumber(String cvLicenseNumber) {
        this.cvLicenseNumber = cvLicenseNumber;
    }
}

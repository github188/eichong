package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

import java.util.Date;

public class PartnerDO extends BasicModel {
    private static final long serialVersionUID = 1L;
    private Integer partnerId;
    private String partnerName;
    private String partnerKey;
    private String partnerToken;
    private java.util.Date registerdate;
    private Integer partnerClientId;
    private Integer partnerUpdateCycleType;
    private Integer partnerUpdateCycleValue;
    private String partnerClientIp;
    private Integer partnerClientPort;
    private String partnerClientKey;
    private java.util.Date createdate;
    private java.util.Date updatedate;
    private Integer valid;
    private Integer paymod;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getPartnerId() {
        return partnerId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public String getPartnerKey() {
        return partnerKey;
    }

    public String getPartnerToken() {
        return partnerToken;
    }

    public Date getRegisterdate() {
        return registerdate;
    }

    public Integer getPartnerClientId() {
        return partnerClientId;
    }

    public Integer getPartnerUpdateCycleType() {
        return partnerUpdateCycleType;
    }

    public Integer getPartnerUpdateCycleValue() {
        return partnerUpdateCycleValue;
    }

    public String getPartnerClientIp() {
        return partnerClientIp;
    }

    public Integer getPartnerClientPort() {
        return partnerClientPort;
    }

    public String getPartnerClientKey() {
        return partnerClientKey;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public Integer getPaymod() {
        return paymod;
    }

    public void setPartnerId(Integer partnerId) {
        this.partnerId = partnerId;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey;
    }

    public void setPartnerToken(String partnerToken) {
        this.partnerToken = partnerToken;
    }

    public void setRegisterdate(Date registerdate) {
        this.registerdate = registerdate;
    }

    public void setPartnerClientId(Integer partnerClientId) {
        this.partnerClientId = partnerClientId;
    }

    public void setPartnerUpdateCycleType(Integer partnerUpdateCycleType) {
        this.partnerUpdateCycleType = partnerUpdateCycleType;
    }

    public void setPartnerUpdateCycleValue(Integer partnerUpdateCycleValue) {
        this.partnerUpdateCycleValue = partnerUpdateCycleValue;
    }

    public void setPartnerClientIp(String partnerClientIp) {
        this.partnerClientIp = partnerClientIp;
    }

    public void setPartnerClientPort(Integer partnerClientPort) {
        this.partnerClientPort = partnerClientPort;
    }

    public void setPartnerClientKey(String partnerClientKey) {
        this.partnerClientKey = partnerClientKey;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public void setPaymod(Integer paymod) {
        this.paymod = paymod;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }
}

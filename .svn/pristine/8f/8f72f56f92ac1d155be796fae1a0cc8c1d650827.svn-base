package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

import java.util.Date;

/**
 * 地锁实体类
 * xyc
 */
public class ParkingLockDO extends BasicModel {
    /**
     * 主键
     */
    private Long id;

    /**
     * 地锁编码
     */
    private String code;

    /**
     * 地锁状态 0 正常，1 正在使用，2 故障
     */
    private Integer status;

    /**
     * 地锁平台 0 电喵 1 慧泊金
     */
    private Long parkingLockPlatform;

    /**
     * 充电点Id
     */
    private Long powerStationId;

    /**
     * 地锁所在的省
     */
    private String provinceCode;

    /**
     * 地锁所在的市
     */
    private String cityCode;

    /**
     * 地锁所在的区
     */
    private String areaCode;

    /**
     * 地锁的详细地址
     */
    private String address;

    //以下为平台字段
    /**
     * 平台对接的地锁key
     */
    private String platformLockKey;

    /**
     * 获取平台信息后的更新时间，避免第三方平台推送问题导致新信息被就信息覆盖
     */
    private Date platformModifyTime;

    /**
     * 平台返回的地锁状态
     */
    private String platformStatus;

    /**
     * 平台返回的错误码
     */
    private String platformErrorCode;

    /**
     * 平台返回的错误信息
     */
    private String platformErrorMsg;

    /**
     * 以下为非持久化字段
     * 充电点名称
     */
    private String powerStationName;

    /**
     * 中文地锁状态
     */
    private String chStatus;

    /**
     * 平台名称
     */
    private String platformName;

    /**
     * 自动升锁时间 单位：分钟
     */
    private Integer autoRiseLockTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getParkingLockPlatform() {
        return parkingLockPlatform;
    }

    public void setParkingLockPlatform(Long parkingLockPlatform) {
        this.parkingLockPlatform = parkingLockPlatform;
    }

    public Long getPowerStationId() {
        return powerStationId;
    }

    public void setPowerStationId(Long powerStationId) {
        this.powerStationId = powerStationId;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPlatformLockKey() {
        return platformLockKey;
    }

    public void setPlatformLockKey(String platformLockKey) {
        this.platformLockKey = platformLockKey;
    }

    public Date getPlatformModifyTime() {
        return platformModifyTime;
    }

    public void setPlatformModifyTime(Date platformModifyTime) {
        this.platformModifyTime = platformModifyTime;
    }

    public String getPlatformStatus() {
        return platformStatus;
    }

    public void setPlatformStatus(String platformStatus) {
        this.platformStatus = platformStatus;
    }

    public String getPlatformErrorCode() {
        return platformErrorCode;
    }

    public void setPlatformErrorCode(String platformErrorCode) {
        this.platformErrorCode = platformErrorCode;
    }

    public String getPlatformErrorMsg() {
        return platformErrorMsg;
    }

    public void setPlatformErrorMsg(String platformErrorMsg) {
        this.platformErrorMsg = platformErrorMsg;
    }

    public String getPowerStationName() {
        return powerStationName;
    }

    public void setPowerStationName(String powerStationName) {
        this.powerStationName = powerStationName;
    }

    public String getChStatus() {
        return chStatus;
    }

    public void setChStatus(String chStatus) {
        this.chStatus = chStatus;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Integer getAutoRiseLockTime() {
        return autoRiseLockTime;
    }

    public void setAutoRiseLockTime(Integer autoRiseLockTime) {
        this.autoRiseLockTime = autoRiseLockTime;
    }
}
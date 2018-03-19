package com.wanma.ims.common.domain;

import com.wanma.ims.common.domain.base.BasicModel;

/**
 * 电桩制造商实体类
 */
public class PileMakerDO extends BasicModel {
    private Long id;//主键

    private String makerName;//制造商名字

    private String makerRemark;//制造商标识

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMakerName() {
        return makerName;
    }

    public void setMakerName(String makerName) {
        this.makerName = makerName;
    }

    public String getMakerRemark() {
        return makerRemark;
    }

    public void setMakerRemark(String makerRemark) {
        this.makerRemark = makerRemark;
    }
}
package com.wanma.ims.common.domain;

import java.io.Serializable;
import java.util.List;


public class CityDO implements Serializable {

    /**
     * 市实体类
     */
    private static final long serialVersionUID = -6305063005878776129L;
    private String provinceId;
    private String cityId;
    private String cityName;
    //以下为在获取充电范围树时使用的字段
    private String name;//树节点名

    private Boolean isSelected;//是否被勾选

    private List<PowerStationDO> list;//子节点集合
    private Long id;
    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsSelected() {
        return isSelected;
    }

    public void setIsSelected(Boolean isSelected) {
        this.isSelected = isSelected;
    }

    public List<PowerStationDO> getList() {
        return list;
    }

    public void setList(List<PowerStationDO> list) {
        this.list = list;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

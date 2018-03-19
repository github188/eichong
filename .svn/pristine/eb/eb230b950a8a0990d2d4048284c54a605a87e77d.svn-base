package com.wanma.ims.controller.vo;

import com.wanma.ims.common.domain.CityDO;

import java.util.List;

/**
 * Created by xyc on 2017/8/7.
 * 充电范围VO
 */
public class CompanyChargeRelaVO {

    private String name;

    private Boolean isSelected;

    private List<CityDO> list;

    public static CompanyChargeRelaVO valueOf(List<CityDO> cityList) {
        CompanyChargeRelaVO vo = new CompanyChargeRelaVO();
        vo.setName("全国");
        vo.setIsSelected(true);
        vo.setList(cityList);
        for (CityDO city : cityList) {
            if (!city.getIsSelected()) {
                vo.setIsSelected(false);
                return vo;
            }
        }
        return vo;
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

    public List<CityDO> getList() {
        return list;
    }

    public void setList(List<CityDO> list) {
        this.list = list;
    }
}

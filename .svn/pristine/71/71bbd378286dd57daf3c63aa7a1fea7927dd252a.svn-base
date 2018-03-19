package com.wanma.ims.service;

import com.wanma.ims.common.domain.CarCompanyDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;

import java.util.List;

/**
 * Created by xyc on 2017/7/25.
 * 电动车品牌逻辑处理接口
 */
public interface CarCompanyService {

    /**
     * 获取电动车品牌列表
     */
    List<CarCompanyDO> getCarCompanyList(CarCompanyDO searchModel);

    /**
     * 获取电动车品牌总数
     */
    long countCarCompany(CarCompanyDO searchModel);

    /**
     * 获取所有电动车品牌
     */
    List<CarCompanyDO> getAllCarCompany();

    /**
     * 根据电动车品牌Id获取电动车品牌
     */
    CarCompanyDO getCarCompanyById(Long carCompanyId);

    /**
     * 新增电动车品牌
     */
    BaseResultDTO addCarCompany(CarCompanyDO carCompany, UserDO loginUser);

    /**
     * 修改电动车品牌
     */
    BaseResultDTO modifyCarCompany(CarCompanyDO carCompany, UserDO loginUser);

    /**
     * 删除电动车品牌
     */
    BaseResultDTO delCarCompany(String carCompanyIds, UserDO loginUser);

}

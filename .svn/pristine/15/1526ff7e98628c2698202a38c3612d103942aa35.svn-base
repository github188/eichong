package com.wanma.ims.service;

import com.wanma.ims.common.domain.CarInfoDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;

import java.util.List;

/**
 * Created by xyc on 2017/7/25.
 * 电动车型号逻辑处理接口
 */
public interface CarInfoService {

    /**
     * 获取电动车型号列表
     */
    List<CarInfoDO> getCarInfoList(CarInfoDO searchModel);

    /**
     * 获取电动车型号总数
     */
    long countCarInfo(CarInfoDO searchModel);

    /**
     * 获取单个电动车型号
     */
    CarInfoDO getCarInfoById(Long carInfoId);

    /**
     * 新增电动车型号
     */
    BaseResultDTO addCarInfo(CarInfoDO carInfo, UserDO loginUser);

    /**
     * 修改电动车型号
     */
    BaseResultDTO modifyCarInfo(CarInfoDO carInfo, UserDO loginUser);

    /**
     * 删除电动车型号
     */
    BaseResultDTO delCarInfo(String carInfoIds, UserDO loginUser);

}

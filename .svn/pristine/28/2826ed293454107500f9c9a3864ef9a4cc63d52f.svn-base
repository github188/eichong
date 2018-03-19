package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.CarInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarInfoMapper {
    int deleteCarInfoById(Long id);

    int insertCarInfo(CarInfoDO carInfo);

    CarInfoDO selectCarInfoById(Long id);

    List<CarInfoDO> selectCarInfoByIds(@Param("ids") List<Long> ids);

    List<CarInfoDO> getCarInfoList(CarInfoDO searchModel);

    long countCarInfo(CarInfoDO searchModel);

    int updateCarInfoByIdSelective(CarInfoDO carInfo);

    int updateCarInfoById(CarInfoDO carInfo);
}
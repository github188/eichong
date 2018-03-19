package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.CarVinRelaDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CarVinRelaMapper {

    public List<CarVinRelaDO> getCarVinRelaList(CarVinRelaDO carVinRelaDO);

    public Long getCarVinRelaCount(CarVinRelaDO carVinRelaDO);

    public Integer addCarVinRela(CarVinRelaDO carVinRelaDO);

    public Integer batchAddCarVinRela(List<CarVinRelaDO> list);

    public Integer removeCarVinRela(CarVinRelaDO carVinRelaDO);

    List<CarVinRelaDO> selectLicenseNumberByVinCode(@Param("vinCodes") List<String> vinCodes);
}

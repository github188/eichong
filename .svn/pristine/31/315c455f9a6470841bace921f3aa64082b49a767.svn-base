package com.wanma.ims.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wanma.ims.common.domain.AreaDO;
import com.wanma.ims.common.domain.CityDO;
import com.wanma.ims.common.domain.ProvinceDO;

public interface InitialMapper {

    /**
     * 省下拉框
     */
    List<ProvinceDO> selectProvinceList(List<String> list);

    /**
     * 市下拉框
     */
    List<CityDO> selectCityList(@Param("list")List<String> list,@Param("provinceCode")String provinceCode);

    /**
     * 区下拉框
     */
    List<AreaDO> selectAreaList(@Param("list")List<String> list,@Param("cityCode")String cityCode);
    
    
    String selectProvinceByName(String provinceName);
    
    String selectCityByName(String cityName);
    
    String selectProvinceById(String provinceId);
    
    String selectCityById(String cityId);
    
    String selectAreaById(String areaId);
    
    List<CityDO> selectCityListByName(@Param("cityName") String cityName);
}

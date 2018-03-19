package com.wanma.ims.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by xyc on 2017/10/12.
 * 充电点统计mapper
 */
public interface PowerStationStatisticMapper {

    List<Map<String, Object>> selectElectricPileMap();

    List<Map<String, Object>> selectPowerStationMap(Map<String, Object> searchModel);

    long countPowerStationMap(Map<String, Object> searchModel);

    List<Map<String, Object>> selectPowerStationPileHeadDetail(Map<String, Object> searchModel);

    long countPowerStationPileHeadDetail(Map<String, Object> searchModel);

    List<Map<String, Object>> selectPowerStationPileHeadNum(@Param("powerStationId") Long powerStationId);

    List<Map<String, Object>> selectPowerStationCityMap();
}

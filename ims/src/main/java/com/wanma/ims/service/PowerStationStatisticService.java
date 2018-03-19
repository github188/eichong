package com.wanma.ims.service;

import java.util.List;
import java.util.Map;

/**
 * 充电点统计接口
 */
public interface PowerStationStatisticService {

    /**
     * 获取电桩数地图
     */
    List<Map<String, Object>> getElectricPileMap();

    long countPowerStationMap(Map<String, Object> searchModel);

    /**
     * 获取充电点地图
     */
    List<Map<String, Object>> getPowerStationMap(Map<String, Object>searchModel);

    /**
     * 获取单个充电点电桩枪头详情
     */
    List<Map<String, Object>> getPowerStationPileHeadDetail(Map<String, Object> searchModel);

    long countPowerStationPileHeadDetail(Map<String, Object> searchModel);

    /**
     * 获取单个充电点电桩枪头数量
     */
    List<Map<String, Object>> getPowerStationPileHeadNum(Long powerStationId);

    /**
     * 获取充电点电桩数地图
     */
    List<Map<String, Object>> getPowerStationCityMap();
}

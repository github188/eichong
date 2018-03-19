package com.wanma.ims.service.impl;

import com.wanma.ims.mapper.PowerStationStatisticMapper;
import com.wanma.ims.service.PowerStationStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by xyc on 2017/10/12.
 * 充电点统计逻辑实现类
 */
@Service
public class PowerStationStatisticServiceImpl implements PowerStationStatisticService {
    @Autowired
    private PowerStationStatisticMapper powerStationStatisticMapper;

    @Override
    public List<Map<String, Object>> getElectricPileMap() {
        return powerStationStatisticMapper.selectElectricPileMap();
    }

    @Override
    public List<Map<String, Object>> getPowerStationMap(Map<String, Object> searchModel) {
        return powerStationStatisticMapper.selectPowerStationMap(searchModel);
    }

    @Override
    public long countPowerStationMap(Map<String, Object> searchModel) {
        return powerStationStatisticMapper.countPowerStationMap(searchModel);
    }

    @Override
    public List<Map<String, Object>> getPowerStationPileHeadDetail(Map<String, Object> searchModel) {
        return powerStationStatisticMapper.selectPowerStationPileHeadDetail(searchModel);
    }

    @Override
    public long countPowerStationPileHeadDetail(Map<String, Object> searchModel) {
        return powerStationStatisticMapper.countPowerStationPileHeadDetail(searchModel);
    }

    @Override
    public List<Map<String, Object>> getPowerStationPileHeadNum(Long powerStationId) {
        return powerStationStatisticMapper.selectPowerStationPileHeadNum(powerStationId);
    }

    @Override
    public List<Map<String, Object>> getPowerStationCityMap() {
        return powerStationStatisticMapper.selectPowerStationCityMap();
    }
}

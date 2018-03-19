package com.wanma.ims.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.PowerStationDO;

public interface ElectricPileMapper {

    List<ElectricPileDO> selectElectricPileList(ElectricPileDO searchModel);

    long countElectricPile(ElectricPileDO searchModel);

    int deleteByElectricId(Long electricId);

    int insertElectricPile(ElectricPileDO electricPile);

    String getMaxElectricPileCode(@Param("areaCode") String areaCode);

    ElectricPileDO selectByElectricPileId(Long electricId);

    ElectricPileDO selectByElectricPileCode(@Param("code") String code);

    List<ElectricPileDO> selectByElectricPileIds(@Param("ids") List<Long> electricIds);

    List<ElectricPileDO> selectByPowerStationIdsForEpRela(@Param("ids") List<Long> powerStationIds);

    List<ElectricPileDO> selectByPowerStationIds(@Param("ids") List<Long> powerStationIds);

    int updateByElectricIdSelective(ElectricPileDO electricPile);

    int updatePowerStationId(ElectricPileDO electricPile);

    int updateConcentratorId(ElectricPileDO electricPile);

    int updateElectricByForeignKey(Map<String, Long> map);

    List<PowerStationDO> countByPowerStationIds(@Param("powerStationIds") List<Long> powerStationIds,@Param("status")Integer status);

    ElectricPileDO getElectricPileAndCpyIdInfo(@Param("code") String code);

    ElectricPileDO getDataForBatch(String electricPileCode);
}
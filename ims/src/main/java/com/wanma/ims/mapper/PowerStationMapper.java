package com.wanma.ims.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wanma.ims.common.domain.PowerStationDO;


public interface PowerStationMapper {

	public Long countPowerStationList(PowerStationDO powerStationDO);
	
	public List<PowerStationDO> selectPowerStationList(PowerStationDO powerStationDO);
	
	public PowerStationDO selectPowerStationById(@Param("powerStationId") Long powerStationId);
	
	public List<Long> selectPowerStationIdByName(@Param("powerStationName") String powerStationName);
	
	public Integer insertPowerStation(PowerStationDO powerStationDO);
	
	public Integer updatePowerStation(PowerStationDO powerStationDO);
	
	public Integer deletePowerStationById(Long powerStationId);

	public List<Map<String, String>> getPowerStationForList(
			PowerStationDO powerStationDO);
}

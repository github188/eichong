package com.wanma.ims.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wanma.ims.common.domain.CountAdminEpRelaDO;


public interface CountAdminEpRelaMapper {
	
	public List<Long> selectPowerStationIdsByAdminId(Long adminId);
	
	public List<Long> selectElectricPileIdsByAdminId(Long adminId);
	
	public Long countAdminEPList(CountAdminEpRelaDO countAdminEpRelaDO);
	
	public List<CountAdminEpRelaDO> selectCountAdminEPList(CountAdminEpRelaDO countAdminEpRelaDO);
	
	public Integer insertCountAdminEpRela(CountAdminEpRelaDO countAdminEpRelaDO);
	
	public Long batchInsertCountAdminEpRela(List<CountAdminEpRelaDO> list);
	
	public Integer updateCountAdminEpRela(Map<String,Object> params);
	
	public CountAdminEpRelaDO selectElectricPileByAdminIdAndEpCode(@Param("adminId") Long adminId,@Param("electricPileCode") String electricPileCode);
	
	public CountAdminEpRelaDO selectElectricPileByAdminIdAndPsId(@Param("adminId") Long adminId,@Param("powerStationId") Long powerStationId);
	
	public int deleteElectricPileByAdminIdAndPsId(@Param("adminId") Long adminId,@Param("powerStationId") Long powerStationId);
	
	
}

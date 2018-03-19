package com.wanma.app.dao;

import org.apache.ibatis.annotations.Param;

public interface CompanyChargeRelaMapper {
    long countByCpyIdAndPowerStationId(@Param("cpyId") Long cpyId, @Param("powerStationId") Long powerStationId);
}
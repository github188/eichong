package com.wanma.app.dao;


import com.wanma.model.ParkingLockTrade;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface ParkingLockTradeMapper {
    int deleteById(Long id);

    int insertParkingLockTrade(ParkingLockTrade parkingLockTrade);

    ParkingLockTrade selectById(Long id);

    ParkingLockTrade selectNoEndTrade(ParkingLockTrade parkingLockTrade);

    int updateTradeNoById(@Param("id") Long id, @Param("tradeNo") String tradeNo);

    int updateEndTimeAndStatusById(@Param("id") Long id, @Param("parkingLockEndTime") Date parkingLockEndTime);
}
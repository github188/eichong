package com.wanma.app.dao;

import com.wanma.model.ParkingLock;
import org.apache.ibatis.annotations.Param;

public interface ParkingLockMapper {
    int deleteById(Long id);

    int insertParkingLock(ParkingLock parkingLock);

    ParkingLock selectById(Long id);

    ParkingLock selectByCodeAndPlatform(@Param("code") String code, @Param("parkingLockPlatform") Long parkingLockPlatform);

    int updateByIdSelective(ParkingLock parkingLock);

    int updateLockPlatformInfo(ParkingLock parkingLock);
}
package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.ParkingLockDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParkingLockMapper {
    int deleteById(Long id);

    int insertParkingLock(ParkingLockDO parkingLock);

    ParkingLockDO selectById(Long id);

    ParkingLockDO selectLockDetailById(Long id);

    ParkingLockDO selectByPlatformLockKey(@Param("parkingLockPlatform") Long parkingLockPlatform, @Param("platformLockKey") String platformLockKey);

    List<ParkingLockDO> selectList(ParkingLockDO parkingLock);

    long count(ParkingLockDO parkingLock);

    int updateByIdSelective(ParkingLockDO parkingLock);

    int updateLockPlatformInfo(ParkingLockDO parkingLock);
}
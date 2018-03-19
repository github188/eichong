package com.wanma.ims.service.parkinglock;

import com.wanma.ims.common.domain.ParkingLockDO;

/**
 * Created by xyc on 2018/2/2.
 * 地锁平台逻辑处理接口
 */
public interface PlatformService {
    boolean asyncModifyLockStatus(String result, Long parkingLockPlatform);

    boolean getLockStatus(ParkingLockDO parkingLock);

    boolean lowerLock(ParkingLockDO parkingLock);

    boolean riseLock(ParkingLockDO parkingLock);
}

package com.wanma.app.service.parkinglock;

import java.util.Map;

/**
 * 地锁逻辑处理接口
 */
public interface ParkingLockService {

    /**
     * 判断用户是否可开锁
     */
    Map<String, String> lowerParkingLock(Long userId, String parkingLockCode, Long parkingLockPlatform);

}

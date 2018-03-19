package com.wanma.app.service.parkinglock;

import com.wanma.model.ParkingLock;

/**
 * Created by xyc on 2018/2/1.
 * 地锁平台逻辑处理接口
 */
public interface PlatformService {

    boolean checkLockIsNormal(ParkingLock parkingLock);

    boolean lowerLock(ParkingLock parkingLock);
}

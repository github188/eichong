package com.wanma.ims.service.parkinglock;

import com.wanma.ims.common.domain.ParkingLockDO;

/**
 * Created by xyc on 2018/1/29.
 * 地锁平台接口
 */
public interface Platform {

    ParkingLockDO getConvertLock(String result);

    void operatingLock(ParkingLockDO parkingLock, String operating) throws Exception;

    String getPlatformName();
}

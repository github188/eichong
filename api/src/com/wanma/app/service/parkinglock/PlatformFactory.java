package com.wanma.app.service.parkinglock;

import com.wanma.app.service.parkinglock.evcat.EvCatPlatform;
import com.wanma.app.service.parkinglock.wiparking.WiParkingPlatform;

/**
 * Created by xyc on 2018/1/29.
 * 地锁平台工厂
 */
public class PlatformFactory {

    public static final Long PARKING_LOCK_PLATFORM_EVCAT = 0L;

    public static final Long PARKING_LOCK_PLATFORM_WIPARKING = 1L;

    public static Platform getPlatform(Long parkingLockPlatform) {
        if (PARKING_LOCK_PLATFORM_EVCAT.equals(parkingLockPlatform)) {
            return new EvCatPlatform();
        } else if (PARKING_LOCK_PLATFORM_WIPARKING.equals(parkingLockPlatform)) {
            return new WiParkingPlatform();
        }
        return null;
    }

}

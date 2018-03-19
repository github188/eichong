package com.wanma.ims.service.parkinglock;

import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.service.parkinglock.evcat.EvCatPlatform;
import com.wanma.ims.service.parkinglock.wiparking.WiParkingPlatform;

/**
 * Created by xyc on 2018/1/29.
 * 地锁平台工厂
 */
public class PlatformFactory {

    public static Platform getPlatform(Long parkingLockPlatform) {
        if (WanmaConstants.PARKING_LOCK_PLATFORM_EVCAT.equals(parkingLockPlatform)) {
            return new EvCatPlatform();
        } else if (WanmaConstants.PARKING_LOCK_PLATFORM_WIPARKING.equals(parkingLockPlatform)) {
            return new WiParkingPlatform();
        }
        return null;
    }
}

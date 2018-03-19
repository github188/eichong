package com.wanma.app.service.parkinglock.evcat;

import com.wanma.app.service.parkinglock.AbstractPlatform;
import com.wanma.common.ParkingLockConstants;
import com.wanma.model.ParkingLock;

/**
 * Created by xyc on 2018/1/29.
 * 电喵地锁平台逻辑实现类
 */
public class EvCatPlatform extends AbstractPlatform {

    @Override
    protected String operatingConvert(String operating) {
        return "";
    }

    @Override
    protected boolean post(ParkingLock parkingLock, String operating) {
        return false;
    }

    @Override
    public String getPlatformName() {
        return ParkingLockConstants.PARKING_LOCK_PLATFORM_EVCAT;
    }
}

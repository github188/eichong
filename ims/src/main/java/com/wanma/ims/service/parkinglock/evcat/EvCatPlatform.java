package com.wanma.ims.service.parkinglock.evcat;


import com.wanma.ims.common.domain.ParkingLockDO;
import com.wanma.ims.service.parkinglock.AbstractPlatform;
import com.wanma.ims.service.parkinglock.constant.ParkingLockConstants;

/**
 * Created by xyc on 2018/1/29.
 * 电喵地锁平台逻辑实现类
 */
public class EvCatPlatform extends AbstractPlatform {

    @Override
    protected ParkingLockDO parkingLockConvert(String result) {
        return null;
    }

    @Override
    protected String operatingConvert(String operating) {
        return "";
    }

    @Override
    protected boolean post(ParkingLockDO parkingLock, String operating) {
        return false;
    }

    @Override
    public String getPlatformName() {
        return ParkingLockConstants.PARKING_LOCK_PLATFORM_EVCAT;
    }
}

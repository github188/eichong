package com.wanma.app.service.parkinglock.impl;

import com.alibaba.fastjson.JSONObject;
import com.wanma.app.dao.ParkingLockMapper;
import com.wanma.app.service.parkinglock.Platform;
import com.wanma.app.service.parkinglock.PlatformFactory;
import com.wanma.app.service.parkinglock.PlatformService;
import com.wanma.app.service.parkinglock.constant.ParkingLockOperating;
import com.wanma.app.service.parkinglock.constant.PlatformErrorCode;
import com.wanma.common.ParkingLockConstants;
import com.wanma.model.ParkingLock;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xyc on 2018/2/1.
 * 地锁平台逻辑处理类
 */
@Service
public class PlatformServiceImpl implements PlatformService {
    private static Logger LOGGER = Logger.getLogger(PlatformService.class);

    @Autowired
    private ParkingLockMapper parkingLockMapper;

    @Override
    public boolean checkLockIsNormal(ParkingLock parkingLock) {
        Platform platform = PlatformFactory.getPlatform(parkingLock.getParkingLockPlatform());
        if (platform == null) {
            LOGGER.error(this.getClass() + "-checkLockIsNormal is error, create platform is fail|parkingLock=" + JSONObject.toJSON(parkingLock).toString());
            return false;
        }

        try {
            platform.operatingLock(parkingLock, ParkingLockOperating.GET_LOCK_STATUS);
            if (modifyLockToPlatform(parkingLock)) {
                return ParkingLockConstants.PARKING_LOCK_NORMAL.equals(parkingLock.getStatus());
            }
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-checkLockIsNormal is error|lockId=" + parkingLock.getId(), e);
        }

        parkingLock.setStatus(ParkingLockConstants.PARKING_LOCK_ERROR);
        return false;
    }

    @Override
    public boolean lowerLock(ParkingLock parkingLock) {
        Platform platform = PlatformFactory.getPlatform(parkingLock.getParkingLockPlatform());
        if (platform == null) {
            LOGGER.error(this.getClass() + "-lowerLock is error, create platform is fail|parkingLock=" + JSONObject.toJSON(parkingLock).toString());
            return false;
        }

        try {
            platform.operatingLock(parkingLock, ParkingLockOperating.LOWER_LOCK);
            if (PlatformErrorCode.isTransferSuccess(parkingLock.getPlatformErrorCode(), parkingLock.getPlatformErrorMsg())) {
                parkingLock.setStatus(ParkingLockConstants.PARKING_LOCK_USING);
            }

            if (modifyLockToPlatform(parkingLock)) {
                return ParkingLockConstants.PARKING_LOCK_USING.equals(parkingLock.getStatus());
            }
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-lowerLock is error|lockId=" + parkingLock.getId(), e);
        }
        return false;
    }

    private boolean modifyLockToPlatform(ParkingLock parkingLock) {
        ParkingLock oldLock = parkingLockMapper.selectById(parkingLock.getId());
        if (parkingLock.getPlatformModifyTime().getTime() > oldLock.getPlatformModifyTime().getTime()) {
            if (parkingLockMapper.updateLockPlatformInfo(parkingLock) < 1) {
                LOGGER.error(this.getClass() + "-modifyLockToPlatform is error, update to db error|parkingLockId=" + parkingLock.getId() + "|platformInfo=" + "");
                return false;
            }
        }
        return true;
    }
}

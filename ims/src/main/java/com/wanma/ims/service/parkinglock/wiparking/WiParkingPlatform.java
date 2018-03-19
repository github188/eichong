package com.wanma.ims.service.parkinglock.wiparking;


import com.wanma.ims.common.domain.ParkingLockDO;
import com.wanma.ims.service.parkinglock.AbstractPlatform;
import com.wanma.ims.service.parkinglock.constant.ParkingLockConstants;
import com.wanma.ims.service.parkinglock.constant.ParkingLockOperating;
import com.wanma.ims.service.parkinglock.constant.PlatformConfig;
import com.wanma.ims.service.parkinglock.constant.PlatformErrorCode;
import com.wanma.ims.util.HttpRequestUtil;
import com.wanma.ims.util.SerializationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xyc on 2018/1/29.
 * 慧泊金地锁平台逻辑实现类
 */
public class WiParkingPlatform extends AbstractPlatform {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    protected ParkingLockDO parkingLockConvert(String result) {
        ParkingLockDO parkingLock = new ParkingLockDO();
        if (fillParkingLock(result, parkingLock)) {
            return parkingLock;
        }
        return null;
    }

    @Override
    protected String operatingConvert(String operating) {
        Map<String, String> operatingMap = new HashMap<>(10);
        operatingMap.put(ParkingLockOperating.GET_LOCK_STATUS, WiParkingLockOperating.LOCK_STATUS);
        operatingMap.put(ParkingLockOperating.LOWER_LOCK, WiParkingLockOperating.LOWER_LOCK);
        operatingMap.put(ParkingLockOperating.RISE_LOCK, WiParkingLockOperating.RISE_LOCK);
        return operatingMap.get(operating);
    }

    @Override
    protected boolean post(ParkingLockDO parkingLock, String operating) {
        String platformResp;
        try {

            platformResp = doPost(parkingLock.getPlatformLockKey(), operating);
            if (null == platformResp || platformResp.isEmpty()) {
                LOGGER.error(getPlatformName() + "platformResp is null...");
                return false;
            }

            return fillParkingLock(platformResp, parkingLock);
        } catch (Exception e) {
            LOGGER.error(this.getClass() + "-post is error|lockId=" + parkingLock.getId() + "|operating=" + operating, e);
            return false;
        }
    }

    protected String doPost(String lockCode, String operating) throws Exception {
        String responseText;
        String url = PlatformConfig.WIPARKING_PLATFORM_URL + "/zhidianzhuangs/app/controLock/controLocks.do";

        Map<String, String> map = new HashMap<>(1);
        map.put("info", SerializationUtil.gson2String(WiParkingRequest.valueOf(lockCode, operating)));

        try {
            responseText = HttpRequestUtil.post(url, map);
            LOGGER.debug("WiParkingPlatform doPost responseText is " + responseText);
        } catch (Exception e) {
            LOGGER.error(getPlatformName() + "-doPost is error |url=" + url + "|lockCode=" + lockCode + "|operating=" + operating, e);
            throw e;
        }

        LOGGER.debug(getPlatformName() + "-doPost responseText|url=" + url + "|lockCode=" + lockCode + "|operating=" + operating + "|responseText=" + responseText);
        return responseText;
    }

    private boolean fillParkingLock(String platformResp, ParkingLockDO lock) {
        WiParkingResult result = SerializationUtil.gson2Object(platformResp, WiParkingResult.class);
        lock.setPlatformModifyTime(new Date());
        lock.setPlatformErrorCode(PlatformErrorCode.conventSuccessCode(result.getErrCode() + ""));
        lock.setPlatformErrorMsg(PlatformErrorCode.conventSuccessMsg(result.getMsg()));

        if (result.getResultData() != null) {
            lock.setStatus(statusConvert(result.getResultData().getLockStatus()));
            lock.setPlatformStatus(result.getResultData().getLockStatus() + "");
            lock.setPlatformLockKey(result.getResultData().getSpaceId() + "");
        }

        if (PlatformErrorCode.isTransferSuccess(lock.getPlatformErrorCode(), lock.getPlatformErrorMsg())) {
            lock.setAutoRiseLockTime(2);
        } else {
            lock.setStatus(ParkingLockConstants.PARKING_LOCK_ERROR);
            lock.setPlatformStatus(WiParkingContants.LOCK_STATUS_UNKNOWN + "");
        }

        return true;
    }

    private Integer statusConvert(Integer platformLockStatus) {
        if (WiParkingContants.LOCK_STATUS_LOWER.equals(platformLockStatus)) {
            return ParkingLockConstants.PARKING_LOCK_USING;
        }
        if (WiParkingContants.LOCK_STATUS_RISE.equals(platformLockStatus)) {
            return ParkingLockConstants.PARKING_LOCK_NORMAL;
        }

        return ParkingLockConstants.PARKING_LOCK_ERROR;
    }

    @Override
    public String getPlatformName() {
        return ParkingLockConstants.PARKING_LOCK_PLATFORM_WIPARKING;
    }
}

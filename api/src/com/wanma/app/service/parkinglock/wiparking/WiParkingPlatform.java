package com.wanma.app.service.parkinglock.wiparking;

import com.alibaba.fastjson.JSON;
import com.wanma.app.service.parkinglock.AbstractPlatform;
import com.wanma.app.service.parkinglock.constant.ParkingLockOperating;
import com.wanma.app.service.parkinglock.constant.PlatformConfig;
import com.wanma.app.service.parkinglock.constant.PlatformErrorCode;
import com.wanma.app.util.HttpRequestUtil;
import com.wanma.common.ParkingLockConstants;
import com.wanma.model.ParkingLock;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xyc on 2018/1/29.
 * 慧泊金地锁平台逻辑实现类
 */
public class WiParkingPlatform extends AbstractPlatform {
    private static Logger LOGGER = Logger.getLogger(WiParkingPlatform.class);

    @Override
    protected String operatingConvert(String operating) {
        Map<String, String> operatingMap = new HashMap<>(10);
        operatingMap.put(ParkingLockOperating.GET_LOCK_STATUS, WiParkingLockOperating.LOCK_STATUS);
        operatingMap.put(ParkingLockOperating.LOWER_LOCK, WiParkingLockOperating.LOWER_LOCK);
        operatingMap.put(ParkingLockOperating.RISE_LOCK, WiParkingLockOperating.RISE_LOCK);
        return operatingMap.get(operating);
    }

    @Override
    protected boolean post(ParkingLock parkingLock, String operating) {
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
        map.put("info", JSON.toJSONString(WiParkingRequest.valueOf(lockCode, operating)));

        try {
            responseText = HttpRequestUtil.post(url, map);
        } catch (Exception e) {
            LOGGER.error(getPlatformName() + "-doPost is error |url=" + url + "|lockCode=" + lockCode + "|operating=" + operating, e);
            throw e;
        }

        LOGGER.debug(getPlatformName() + "-doPost responseText|url=" + url + "|lockCode=" + lockCode + "|operating=" + operating + "|responseText=" + responseText);
        return responseText;
    }

    private boolean fillParkingLock(String platformResp, ParkingLock lock) {
        WiParkingResult result = JSON.parseObject(platformResp, WiParkingResult.class);
        lock.setPlatformModifyTime(new Date());
        lock.setPlatformErrorCode(PlatformErrorCode.conventSuccessCode(result.getErrCode() + ""));
        lock.setPlatformErrorMsg(PlatformErrorCode.conventSuccessMsg(result.getMsg()));

        if (PlatformErrorCode.isTransferSuccess(lock.getPlatformErrorCode(), lock.getPlatformErrorMsg())) {
            if (result.getResultData() == null) {
                return true;
            }
            lock.setStatus(statusConvert(result.getResultData().getLockStatus()));
            lock.setPlatformStatus(result.getResultData().getLockStatus() + "");
            lock.setPlatformLockKey(result.getResultData().getSpaceId() + "");
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

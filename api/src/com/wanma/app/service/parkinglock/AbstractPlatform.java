package com.wanma.app.service.parkinglock;

import com.wanma.common.ThreadUtil;
import com.wanma.model.ParkingLock;
import org.apache.log4j.Logger;

/**
 * Created by xyc on 2018/1/29.
 * 地锁平台抽象类
 */
public abstract class AbstractPlatform implements Platform {
    private static Logger LOGGER = Logger.getLogger(AbstractPlatform.class);
    /**
     * 调用第三方地锁平台失败时重试次数
     */
    public static final int RETRY_TIMES = 3;

    @Override
    public void operatingLock(ParkingLock parkingLock, String operating) throws Exception {
        String platformOperating = operatingConvert(operating);
        if (null == platformOperating || "".equals(platformOperating)) {
            LOGGER.error(this.getClass() + "-operatingLock is error, get platformOperating is null|lockId=" + parkingLock.getId() + "|");
            throw new Exception("get platformOperating is null");
        }

        boolean res;

        for (int i = 0; i < RETRY_TIMES; i++) {
            res = post(parkingLock, platformOperating);
            if (res) {
                break;
            }

            long operatingLockFailedSleepTime = 200L;
            LOGGER.error("operatingLock is error,retry after " + operatingLockFailedSleepTime + " milliseconds sleep |retryTimes=" + i);
            ThreadUtil.safeSleep(operatingLockFailedSleepTime, getPlatformName() + " operatingLock is error,sleep waiting error,waiting retry");
        }
    }

    protected abstract String operatingConvert(String operating);

    protected abstract boolean post(ParkingLock parkingLock, String operating);
}

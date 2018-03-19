package com.wanma.app.util;

import java.util.Date;

/**
 * Created by xyc on 2018/1/25.
 * 地锁订单号生成工具
 */
public class ParkingLockTradeNoGenerate {
    /**
     * 版本号,生成规则为2位版本号(01)加上8位时间(20180125)加上2位parkingLockId长度(02)加上parkingLockId(123456)加上tradeId(123456)
     */
    public static final String VERSIONS_01 = "01";

    public static String generateTradeNo(Long parkingLockId, Long id) {
        String tradeNo;
        String generateTime = DateUtil.toDateFormat(new Date(), "yyyyMMdd");
        Integer idLength = parkingLockId.toString().length();
        String parkingLockIdLength = "0";
        if (idLength < 10) {
            parkingLockIdLength += idLength.toString();
        } else {
            parkingLockIdLength = idLength.toString();
        }
        tradeNo = VERSIONS_01 + generateTime + parkingLockIdLength + parkingLockId + id;
        return tradeNo;
    }
}

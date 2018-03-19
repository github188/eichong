package com.sgcc.service;

import java.util.Date;

/**
 * Created by zangyaoyi on 2017/6/1.
 */
public interface SGCCService {
    //同步充电站信息
    void syncStationInfo(String accessToken,Date startTime, Date endTime);

    //同步充电站统计信息
    void syncStationStatsInfo(String accessToken,Date startTime,Date endTime);

    //同步充电设备信息
    void syncEquipmentInfo(String accessToken,Date startTime,Date endTime);

    //同步充电设备统计信息
    void syncEquipmentStatsInfo(String accessToken,Date startTime,Date endTime);

    //同步充电设备接口信息
    void syncConnectorInfo(String accessToken,Date startTime,Date endTime);
}

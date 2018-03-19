package com.wanma.model.sgcc;

import com.alibaba.fastjson.annotation.JSONField;

import java.math.BigDecimal;

/**
 * Created by zangyaoyi on 2017/6/5.
 * 同步充电站统计信息
 */
public class SyncStationStatsInfo {
    private String StationID;//充电站ID
    private String StatsDate;//统计日期
    private BigDecimal StationElectricity;//充电站累计电量
    private BigDecimal StationChargeTime;//充电站累计电量

    @JSONField(name = "StationID")
    public String getStationID() {
        return StationID;
    }

    public void setStationID(String stationID) {
        StationID = stationID;
    }
    @JSONField(name = "StatsDate")
    public String getStatsDate() {
        return StatsDate;
    }

    public void setStatsDate(String statsDate) {
        StatsDate = statsDate;
    }

    @JSONField(name = "StationElectricity")
    public BigDecimal getStationElectricity() {
        return StationElectricity;
    }

    public void setStationElectricity(BigDecimal stationElectricity) {
        StationElectricity = stationElectricity;
    }

    @JSONField(name = "StationChargeTime")
    public BigDecimal getStationChargeTime() {
        return StationChargeTime;
    }

    public void setStationChargeTime(BigDecimal stationChargeTime) {
        StationChargeTime = stationChargeTime;
    }
}

package com.wanma.service;

public interface SimplePointService {

	String getPowerStationInfo(String org);

	String getChargePileInfo(String org);

	String getBindGunInfo(String org);

	String getSingleStationInfo(String stationId);

}

package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.OrderDO;

import java.util.List;
import java.util.Map;

public interface DataCenterMapper {

	Map<String, Object> getHistoryDataForOrder(Map<String, String> params);

	List<Map<String, String>> getProvinceScope();

	List<Map<String, String>> getCityScope();

	List<Map<String, String>> getCpyList(String cityCode);

	String getElectircCount(Map<String, String> params);

	String getElectircHeadCount(Map<String, String> params);

	String getPowerStationCount(Map<String, String> params);

	String getChargeCountByEp(Map<String, String> params);

	String getUserNomalCount(Map<String, String> params);

	String getConsumAmount(Map<String, String> params);

	String getChargeCountByUser(Map<String, String> params);

	List<Map<String,Object>> getMapDataForOrder(Map<String, Object> params);

	List<Map<String,Object>> getMapDataForElectric(Map<String, Object> params);

	List<Map<String,Object>> getMapDataForUser(Map<String, Object> params);

	List<Map<String,Object>> getMapDataForUserLevel3(Map<String, Object> params);

	Map<String, Object> getUserInfoForRealTimeById(Long userId);

	Map<String,String> getNomalUserInfoById(Long userId);

	Map<String,String> getCpyUserInfoById(Long userId);

	Map<String,String> getCardUserInfoById(Long userId);

	List<Map<String, String>> getUserCardForRealTime(Map<String, String> map);

	String getOnlineStateCount(Map<String, Object> searchModel);

	String getPileDisconnectCount(Map<String, Object> searchModel);

	String getParkingLockState(Map<String, Object> searchModel);

	Map<String,Object> getPowerStationOrderHistoryData(Map<String, Object> searchModel);

	Map<String,Object> getRealTimeDateForOrder(Map<String, String> params);

	String getErrorCountToday(Map<String, String> params);

	String getEpErrorCountByPsId(Map<String, Object> searchModel);

	String getOrderErrorCountByPsId(Map<String, Object> searchModel);

	Map<String,Object> getRealTimeDateForUser(Map<String, String> params);

	String getNewUserToday(Map<String, String> params);

	List<Map<String,Object>> getUserChargeRank(Map<String, String> params);

	List<Map<String,Object>> getChargeCount5Days(Map<String, String> params);

	Map<String, Object> getDetailStaticData(String epCode);

	String getPowerStationNameByEpCode(String epCode);

	Map<String,String> getOrderByPileId(Long id);
}

package com.wanma.ims.service;

import com.wanma.ims.common.domain.UserDO;
import net.sf.json.JSONObject;

import java.util.List;
import java.util.Map;

public interface DataCenterService {
	/**
	 * 充电历史数据 电量维度
	 * @param params
	 * @return
	 */
	Map<String, Object> getHistoryDataForOrder(Map<String, String> params);
	/**
	 * 省份-城市-公司下拉选择 
	 */
	List<Map<String, Object>> getSelectScope();
	/**
	 * 充电历史数据 电桩维度
	 * @param params
	 * @return
	 */
	Map<String, Object> getHistoryDataForElectric(Map<String, String> params);
	/**
	 * 充电历史数据 用户维度
	 * @param params
	 * @return
	 */
	Map<String, Object> getHistoryDataForUser(Map<String, String> params);
	/**
	 * 地图数据 电量维度
	 * @param params
	 * @return
	 */

	List<Map<String, Object>> getMapDataForOrder(Map<String, Object> params);
	/**
	 * 地图数据 电桩维度
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getMapDataForElectric(Map<String, Object> params);
	/**
	 * 地图数据 用户维度
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getMapDataForUser(Map<String, Object> params);

	/**
	 * 充电实时信息 转义
	 * @param returnJson
	 * @return
	 */
	Map<String,Object> getHeadRealTimeInfo(JSONObject returnJson);

	/**
	 * 获取电站中 电桩/枪头在线状态
	 * @param searchModel
	 * @return
	 */
	Map<String, Object> getPowerStationPileHeadOnlineState(Map<String, Object> searchModel);
	/**
	 * 获取电站中 地锁状态
	 * @param searchModel
	 * @return
	 */
	Map<String, Object> getParkingLockState(Map<String, Object> searchModel);

	/**
	 * 获取充电点历史数据
	 * @param searchModel
	 * @return
	 */
	Map<String, Object> getPowerStationHistoryData(Map<String, Object> searchModel);

	/**
	 * 开始充电记录列表
	 * @param key
	 * @return
	 */
	List<Map<String,Object>> getStartChargeRecord(String key);

	/**
	 * 充电电量曲线
	 * @param key
	 * @return
	 */
	Map<String, Object> getChargePowerCurve(String key);

	/**
	 * 实时充电数据 电量维度
	 * @param params
	 * @return
	 */
	Map<String,Object> getRealTimeDateForOrder(Map<String, String> params);
	/**
	 * 实时充电数据 电桩维度
	 * @param params
	 * @return
	 */
	Map<String,Object> getRealTimeDateForElectric(Map<String, String> params);
	/**
	 * 实时充电数据 用户维度
	 * @param params
	 * @return
	 */
	Map<String,Object> getRealTimeDateForUser(Map<String, String> params);

	/**
	 *用户充电排行榜
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> getUserChargeRank(Map<String, String> params);

	/**
	 *近5日充电量
	 * @param params
	 * @return
	 */
	Map<String, Object>  getChargeCount5Days(Map<String, String> params);

	/**
	 * 充电点详情静态数据
	 * @param epCode
	 * @param login
	 * @return
	 */
	Map<String,Object> getDetailStaticData(String epCode, UserDO login);
}

package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.ElectricMap;
import com.wanma.model.RateInfo;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblPartner;
import com.wanma.model.TblPowerstation;

public interface TblPowerstationMapper {

	 int getCount(TblPowerstation model);

	/**
	 * 查询List
	 * @param model
	 * @return
	 */
	 List<TblPowerstation> getList(TblPowerstation model);

	/**
	 * 根据城市编号查询充电点信息
	 * @param model
	 * @return
	 */
	 List<Map<String, String>> getPointsInfoByCityCode(TblPowerstation model);

	/**
	 * 查询站点详情
	 * @param model
	 * @return
	 */
	 Map<String, Object> selectDetail(TblPowerstation model);

	/**
	 * 根据最近查询时间查询电站列表
	 * @param param
	 * @return
	 */
	 List<TblPowerstation> getNewlyUpdatedList(Map<String, Object> param);

	 int getNewlyUpdatedCount(Map<String, Object> param);

	/**
	 * 根据充电站id查询电站以及电桩信息
	 * 
	 * @param params
	 * @return
	 */

	 Map<String, Object> getAmapChargePowerstation(Map<String, Object> params);

	/**
	 * 查询电站详情
	 * @param params
	 * @return
	 */
	 Map<String, String> getChargePowerstationList(Map<String, Object> params);

	/**
	 * 查询全国范围内的充电点
	 * @param params
	 * @return
	 */
	 List<ElectricMap> getPowerstationMapBysearch(Map<String, Object> params);

	 RateInfo getRateInfo(RateInfo de);
	 /**
	  * 根据电站id查询电桩枪口以及电压信息 
	  * @param map
	  * @return
	  */
	 List<Map<String, Object>> getElecticByPonitId(Map<String, Object> map);

	/**
	 * 查询全部电站数量
	 * @param param
	 * @return
	 */
	 int getPowerStationCount(Map<String, Object> param);
	/**
	 * 查询全部电站
	 * @param param
	 * @return
	 */
	 List<TblPowerstation> getPowerStationList(Map<String, Object> param);
	/**
	 * 查询最近更新电站数量
	 * @param param
	 * @return
	 */
	 int getUpdatedCount(Map<String, Object> param);
	/**
	 * 查询最近更新的电站
	 * @param param
	 * @return
	 */
	 List<TblPowerstation> getUpdatedList(Map<String, Object> param);

	/**
	 * 通过组织机构编号查询密钥签名信息
	 * @param map
	 * @return
	 */
	 Map<String,Object > getPartnerKeyList(Map<String, Object> map);

	/**
	 * 通过公司标识查询相关信息
	 * @param map
	 * @return
	 */
	 Map<String,String > getcpyNumberById(Map<String, String> map);

	 TblPartner getPartnerList(String operatorID);
	
	
}

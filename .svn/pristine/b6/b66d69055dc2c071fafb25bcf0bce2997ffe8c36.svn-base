/**     
 * @Title:  TblElectricpileService.java   
 * @Package com.wanma.service   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年11月19日 下午4:24:10   
 * @version V1.0     
 */  
package com.wanma.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblPartner;
import com.wanma.model.TblPowerstation;

/**
 * @author lhy
 *
 */
public interface TblPowerstationService {
	 int getCount(TblPowerstation model);
	
	 List<TblPowerstation> getList(TblPowerstation model);

	 int getNewlyUpdatedCount(Map<String, Object> param);
	
	 List<TblPowerstation> getNewlyUpdatedList(Map<String, Object> param);
	
	/**
	 * 根据城市编号查询充电点信息
	 * @param model
	 * @return
	 */
	 List<Map<String,String>> getPointsInfoByCityCode(TblPowerstation model);

	
	/**
	 * 查询站点详情
	 * @param model
	 * @return
	 */
	 Map<String,Object> selectDetail(TblPowerstation model);


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
	 Map<String, Object> getPartnerKeyList(Map<String, Object> map);

	/**
	 * 通过公司标识查询相关信息
	 * @param map
	 * @return
	 */
	 Map<String,String > getcpyNumberById(Map<String, String> map);

	 TblPartner getPartnerList(String operatorID);
	
	
}

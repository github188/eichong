/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblPowerstation;


/**
 * 反馈表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface TblPowerstationMapper {

	/**
	 * 获取充电点数据
	 * @param feedback
	 */
	public List<?> getPowerstation(TblPowerstation tblPowerstation);
	/**
	 * 新的快速检索获取充电点列表
	 * @param params
	 * @return
	 */
	public List<Map<String, String>> getSearchPowerStationList(Map<String, String> params);
	/**
	 * 新接口获取充电点列表数据
	 * @param tblPowerstation
	 * @return
	 */
	public List<?> getPowerstationN(TblPowerstation tblPowerstation);
	/**
	 * 获取地图模式充电点数据
	 * @param feedback
	 */
	public List<?> getPowerstationForMap(TblPowerstation tblPowerstation);
	
	/**
	 * 通过主键获取电桩信息
	 * @param feedback
	 */
	public List<TblPowerstation> getPowerstationById(TblPowerstation tblPowerstation);
	
	/**
	 * 分享页查询充电点信息
	 * @param feedback
	 */
	public TblPowerstation getSharPowerstation(TblPowerstation tblPowerstation);
	
	/**
	 * <!--分享页查询充电点电桩数量-->
	 * @param feedback
	 */
	public long searchCount(TblPowerstation tblPowerstation);
	
	public List<Map<String, Object>> getAllPsList();
}

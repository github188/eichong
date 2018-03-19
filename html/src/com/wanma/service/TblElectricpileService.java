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

import com.wanma.model.TblElectricpile;
import com.wanma.model.TcbElectric;

/**
 * @author bc
 *
 */
public interface TblElectricpileService {
	
	public TblElectricpile getElectricpileDetail(TblElectricpile tblElectricpile);
	public List<TblElectricpile> getElectricpileList(TblElectricpile tblElectricpile);
	public List<TblElectricpile> getElectricpileListForMap(TblElectricpile tblElectricpile);
	public Integer getElectricpileCountForMap(TblElectricpile tblElectricpile);
	public void update(TblElectricpile tblElectricpile);
	public void updateByCode(TblElectricpile tblElectricpile);
	public List<Map<String,Object>> selectDetailList(Map<String, Object> data);
	public TblElectricpile selectOne(TblElectricpile model);
	public List<TcbElectric> getElectricpileListByPsId(Integer pkPowerstation);
	/**
	 * 获取桩体信息
	 * @param map
	 * @return
	 */
	public Map<String, Object> getElectricpileInfo(Map<String, Object> map);
	/**
	 * 
	 * 获取当前时间对应的尖峰平谷那个段
	 * @param jsonRate 格式化的费率字符串
	 * @return 1尖2峰3平4谷
	 * @return
	 */
	public String getCurrentPowerRateMark(String jsonRate);
	/**
	 * 查询统计信息--电站累计电量
	 * @param model
	 * @return
	 */
	public String getStationMeterNum(Map<String, Object> model);
	/**
	 * 查询统计信息--电站内电桩、枪头累计电量
	 * @param model
	 * @return
	 */
	public List<Map<String, Object>> getEleMeterNum(Map<String, Object> model);
	/**
	 * 根据电站id查询电桩信息
	 * @param tblElectricpile
	 * @return
	 */
	public List<TblElectricpile> getElectricPileByPowerStationId(TblElectricpile tblElectricpile);
	
	/**
	 * 查询电桩详情
	 * @param pkPowerstation
	 * @return
	 */
	public List<TcbElectric> getElectricList(Map<String, Object> model);
	
	/**
	 * 校验电站是否在白名单里
	 * @param mapl
	 * @return
	 */
	public int checkPowerStation(Map<String, Object> mapl);
	
	/**
	 * 查询枪头电量
	 * @param hmap
	 * @return
	 */
	public List<Map<String, Object>> getHeadMeterNum(Map<String, Object> hmap);
	
}

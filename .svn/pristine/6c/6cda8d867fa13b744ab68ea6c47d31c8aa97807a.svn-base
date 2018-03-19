package com.wanma.service;

import java.util.List;
import java.util.Map;


/**
 * @Description: 监控首页
 * @author 
 */
public interface MonitorHomeService {

	/**
	 * 
	 * 监控首页
	 * (统计：)
	 * @return
	 */
	public Map<String,Object> home(Map<String,Object> param);
	
	/**
	 * 
	 * 监控充电点信息
	 * (高德地图)
	 * @return
	 */
	public int countChargePointMap(Map<String,Object> params);
	
	/**
	 * 
	 * 监控充电点信息
	 * (高德地图)
	 * @return
	 */
	public List<Map<String, Object>> queryChargePointMap(Map<String,Object> params);
	
	/**
	 * 
	 * 监控点详细信息
	 * (充电点信息->桩信息->枪信息)
	 * @return
	 */
	public List<Map<String,Object>> queryChargePoint4Detail(Map<String,Object> param);
	
	/**
	 * 
	 * 累计充电次数、累计充电时长、累计充电度数
	 * @param pId 充电点Id
	 * @return
	 */
	public Map<String,Object> queryChargeCount(Map<String,Object> params);
	
	/**
	 * 
	 * 今日充电次数、今日充电时长、今日充电度数
	 * @param pId 充电点Id
	 * @return
	 */
	public Map<String,Object> queryChargeToday(Map<String,Object> params);
	
	/**
	 * 
	 * 电桩总数、枪口总数、直流桩数、交流桩数
	 * @param pId 充电点Id
	 * @return
	 */
	public Map<String,Object> queryElecCount(Map<String,Object> params);
	/**
	 * 
	 * 充电、空闲、故障、离线
	 * @param pId 充电点Id
	 * @return
	 */
	public Map<String,Object> queryElecHeadFaultCount(Map<String,Object> params);
	/**
	 * 
	 * （枪） 今日、本月、今年、历史--充电次数、充电度数、充电时长
	 * @param pId 充电点Id
	 * @return
	 */
	public Map<String,Object> queryElecHeadChargeCount(Map<String,Object> params);
	
	/**
	 * 电桩的额定功率
	 */
	public Map<String,Object> queryElecRatPower(String headId);
}

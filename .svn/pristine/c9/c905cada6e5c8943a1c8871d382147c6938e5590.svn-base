package com.wanma.service;

import java.util.List;
import java.util.Map;


/**
 * @Description: 充电统计
 * @author 
 */
public interface StatisticChargeService {

	/**
	 * 
	 * 充电统计
	 * (统计：v1 充电度数 ,v2 充电时长(小时),v3充电次数 )
	 * @return
	 */
	public Map<String,Object> queryChargeDataCount(Map<String,Object> param);
	
	/**
	 * 
	 * 充电统计
	 * (线形图：充电度数)
	 * @return
	 */
	public List<Map<String, Object>> queryChargeDataLine(Map<String,Object> params);
	
	/**
	 * 
	 * 充电统计
	 * (饼图：交直流充电占比)
	 * @return
	 */
	public Map<String,Object> queryChargeDataPie(Map<String,Object> param);
	
	/**
	 * 
	 * 充电统计count
	 * 
	 * @return
	 */
	public Integer countChargeDataDetail(Map<String,Object> params);
	/**
	 * 
	 * 充电统计 明细
	 * (v1 充电度数 ,v2 充电时长(小时),v3充电次数 )
	 * @return
	 */
	public List<Map<String,Object>> queryChargeDataDetail(Map<String,Object> params);
}

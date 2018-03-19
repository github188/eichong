package com.wanma.app.service;

import java.util.List;
import java.util.Map;

public interface IntegralService {

	/**
	 * 获取可兑换列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getconvertibleList(
			Map<String, Object> params);

	/*
	 * 获取用户当日签到次数 
	 * @param uId
	 */
	public int getUserTodaySignCount(int uId);
	
	/*
	 * 获得五天内签到列表
	*/
	public List<Map<String,Object>> getSignList(int uId);
	
	/**
	 * @Title: getUserPointById
	 * @Description: 根据id获取用户积分详情
	 * @param 用户ID
	 * @return
	 */
	public List<Map<String,Object>> getUserPointById(Map<String, Object> params);
	
	
	public List<Map<String,Object>> getdrawableList(Map<String, Object> params);
	
	public int canDrawByActivityId(Map<String, Object> params);
	/**
	 * @Title: getUserPointById
	 * @Description: 获取充电 充值 赠送积分的比率 
	 * @param 省市ID
	 * @return
	 */
	public List<Map<String,Object>> getpointpresentrate(Map<String, Object> params);
	
	public List<Map<String,Object>>  findactivitylist(Map<String, Object> params);
	
	public long  getuserpointById(int userId);
	
	
}

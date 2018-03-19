package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

public interface IntegralMapper {

	/**
	 * 获得可兑换列表
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getconvertibleList(
			Map<String, Object> params);

	public int getUserTodaySignCount(int uId);
	
	public List<Map<String,Object>> getSignList(int uId);
	public List<Map<String,Object>> getUserPointDetailByUid(Map<String, Object> params);
	public List<Map<String,Object>> getdrawableList(Map<String, Object> params);
	/**
	 * 
	 * 
	 * @param params
	 * @return
	 */
	public int canDrawByActivityId(Map<String, Object> params);
	
	/*
	 * 获取积分赠送比率 充值和充电
	 */
	public List<Map<String,Object>> getpointpresentrate(Map<String,Object>params);
	
	
	/*
	 * 发现活动列表
	 */
	public List<Map<String,Object>> findactivitylist (Map<String,Object>params);
	
	/*
	 * 总积分
	 */
	public long getuserpointById(int uId);
	
	//    <select id="usermodinfogetpointcount" parameterType="int" resultType="int">
	public int  usermodinfogetpointcount(int Uid);
	
	//标记已分享
	public void marksharegetpointbyuId(int uid);
	

}

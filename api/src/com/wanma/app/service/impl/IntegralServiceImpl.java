package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.IntegralMapper;
import com.wanma.app.service.IntegralService;

@Service
public class IntegralServiceImpl implements IntegralService {
	@Autowired
	private IntegralMapper integralMapper;

	/**
	 * 积分兑换券
	 * 
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getconvertibleList(
			Map<String, Object> params) {
		return integralMapper.getconvertibleList(params);
	}
	@Override
	public int getUserTodaySignCount(int uId)
	{
	    return integralMapper.getUserTodaySignCount(uId);
	}
	
	/*
	 * 获得五天内签到列表
	*/
	@Override
	public List<Map<String,Object>> getSignList(int uId)
	{
		return integralMapper.getSignList(uId);
	}
	
	
	/**
	 * @Title: getUserPointById
	 * @Description: 根据id获取用户积分详情
	 * @param 用户ID
	 * @return
	 */
	public List<Map<String,Object>> getUserPointById(Map<String, Object> params)
	{
		return integralMapper.getUserPointDetailByUid( params);
	}
	/**
	 * @Title: getdrawableList
	 * @Description:  积分可抽奖列表 
	 * @param 用户ID   地区id  
	 * @return
	 */
	public List<Map<String,Object>> getdrawableList(Map<String, Object> params)
	{
		return integralMapper.getdrawableList(params);
		
	}
	
	public int canDrawByActivityId(Map<String, Object> params)
	{
		return integralMapper.canDrawByActivityId(params);
	
	}

	public List<Map<String,Object>> getpointpresentrate(Map<String, Object> params)
	{
		return integralMapper.getpointpresentrate(params);
	}
	
	public List<Map<String,Object>>  findactivitylist(Map<String, Object> params)
	{
		return integralMapper.findactivitylist(params);
	}

	
	public long  getuserpointById(int userId)
	{
		return integralMapper.getuserpointById(userId);
	}
}

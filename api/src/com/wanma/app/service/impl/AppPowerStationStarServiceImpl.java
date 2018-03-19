package com.wanma.app.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.PowerStationCommentMapper;
import com.wanma.app.dao.PowerStationStarMapper;

@Service
public class AppPowerStationStarServiceImpl {
	@Autowired
	private PowerStationStarMapper pssMapper;
	
	/**
	 * 保存电站星级评价
	 * @param param
	 */
	public void insertPsStar(Map<String, Object> param){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String date = dateFormat.format( now ); 
		param.put("createdate", date);
		
		pssMapper.insert(param);
	}
	
	/**
	 * 根据用户id和站id获取用户对站的星评数
	 * @param param
	 * @return
	 */
	public int getStarNumByUserIdPsId(Map<String, Object> param){
		return pssMapper.getStarNumByUserIdPsId(param);
	}
}

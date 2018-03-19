package com.wanma.web.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.web.dao.PowerStationStarMapper;

@Service
public class WebPowerStationStarServiceImpl {
	@Autowired
	private PowerStationStarMapper pssMapper;
	
	/**
	 * 保存电站星级评价
	 * @param param
	 */
	public void insertPsStar(Map<String, Object> param){		
		pssMapper.insert(param);
	}
	
	public int  getPsCommentStar(Map<String, Object> params){
	      return  pssMapper.getPsCommentStar(params);
	}
}

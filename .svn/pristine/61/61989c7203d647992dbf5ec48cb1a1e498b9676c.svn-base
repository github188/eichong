package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppCarGarageMapper;

@Service
public class AppCarGarageServiceImpl {
	
	/**
	 * 获取修理厂列表
	 * @return
	 */
	public List<Map<String, Object>> getCarGarageList(Map<String, Object> params){
		return appCarGarageMapper.getCarGarageList(params);
	}
	
	@Autowired
	AppCarGarageMapper appCarGarageMapper;
}

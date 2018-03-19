package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppRescueMapper;

@Service
public class AppRescueServiceImpl {
	
	/**
	 * 获取保险公司列表
	 * @return
	 */
	public List<Map<String, Object>> getCarGarageList(){
		return appRescueMapper.getRescueList();
	}
	
	@Autowired
	AppRescueMapper appRescueMapper;
}

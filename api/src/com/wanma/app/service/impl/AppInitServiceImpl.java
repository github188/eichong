package com.wanma.app.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppConfigContentMapper;
import com.wanma.app.dao.TblPowerstationMapper;

@Service
public class AppInitServiceImpl {
	public List<Map<String, Object>> getAllConfigList(){
		return appConfigContentMapper.getAllConfigContent();
	}
	
	public List<Map<String, Object>> getAllPsList(){
		return tblPowerStationMapper.getAllPsList();
	}
	
	@Autowired
	private AppConfigContentMapper appConfigContentMapper;
	@Autowired
	private TblPowerstationMapper tblPowerStationMapper;
}

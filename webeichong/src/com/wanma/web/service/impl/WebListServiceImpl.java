package com.wanma.web.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.WebList;
import com.wanma.web.dao.WebListMapper;
import com.wanma.web.service.WebListService;

@Service
public class WebListServiceImpl implements WebListService {
	
	@Autowired
	WebListMapper listMapper;

	@Override
	public List<WebList> findAll() {
		// TODO Auto-generated method stub
		return listMapper.findAll();
	}

	@Override
	public List<WebList> findByType(String releType) {
	/*	Map map=new HashMap();
		map.put("releType", releType);*/
		// TODO Auto-generated method stub
		return listMapper.findByType(releType);
	}

	@Override
	public WebList findByPk(int pkRelease) {
		// TODO Auto-generated method stub
		return listMapper.findByPk(pkRelease);
	}

	@Override
	public List<WebList> getAllByType(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return listMapper.getAllByType(params);
	}

	@Override
	public long countWebList(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return listMapper.countWebList(params);
	}

}

package com.wanma.app.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.CmsCarCompanyMapper;
import com.wanma.app.service.CmsCarCompanyService;
@Service
public class CmsCarCompanyServiceImpl implements CmsCarCompanyService {
	
	@Autowired
	private CmsCarCompanyMapper carCompanyMapper;

	@Override
	public void insertCarCompany(Map<String, Object> params) {
		carCompanyMapper.insertCarCompany(params);

	}

	@Override
	public void updateCarCompany(Map<String, Object> params) {
		carCompanyMapper.updateCarCompany(params);

	}

	@Override
	public void deleteCarCompanyById(Map<String, Object> params) {
		carCompanyMapper.deleteCarCompanyById(params);
		
	}

	@Override
	public List<HashMap<String, Object>> findCarCompanyList(
			Map<String, Object> params) {
		return carCompanyMapper.findCarCompanyList(params);
	}

	@Override
	public long findCarCompanyCount(Map<String, Object> params) {
		return carCompanyMapper.findCarCompanyCount(params);
	}

	@Override
	public HashMap<String, Object> findCarCompanyById(Map<String, Object> params) {
		return carCompanyMapper.findCarCompanyById(params);
	}

	@Override
	public HashMap<String, Object> getByProperty(Map<String, Object> params) {
		return carCompanyMapper.getByProperty(params);
	}


}

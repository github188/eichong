package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsCarCompanyMapper;
import com.wanma.model.TblCarcompany;
import com.wanma.service.CmsCarCompanyService;
@Service
public class CmsCarCompanyServiceImpl implements CmsCarCompanyService {
	
	@Autowired
	private CmsCarCompanyMapper carCompanyMapper;

	@Override
	public void insertCarCompany(TblCarcompany params) {
		carCompanyMapper.insertCarCompany(params);

	}

	@Override
	public void updateCarCompany(TblCarcompany params) {
		carCompanyMapper.updateCarCompany(params);

	}

	@Override
	public void deleteCarCompanyById(TblCarcompany params) {
		carCompanyMapper.deleteCarCompanyById(params);
		
	}

	@Override
	public List<TblCarcompany> findCarCompanyList(
			TblCarcompany params) {
		return carCompanyMapper.findCarCompanyList(params);
	}

	@Override
	public long findCarCompanyCount(TblCarcompany params) {
		return carCompanyMapper.findCarCompanyCount(params);
	}

	@Override
	public TblCarcompany findCarCompanyById(TblCarcompany params) {
		return carCompanyMapper.findCarCompanyById(params);
	}

	@Override
	public TblCarcompany getByProperty(TblCarcompany params) {
		return carCompanyMapper.getByProperty(params);
	}


}

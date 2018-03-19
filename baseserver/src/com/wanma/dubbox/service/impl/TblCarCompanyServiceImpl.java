package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblCarCompanyMapper;
import com.wanma.dubbox.model.TblCarCompany;
import com.wanma.dubbox.service.TblCarCompanyService;
/**
 * 电桩数据查询接口
 * @author lhy
 *
 */
@Service
public class TblCarCompanyServiceImpl implements TblCarCompanyService {

	@Autowired
	TblCarCompanyMapper tblCarCompanyMapper;

	@Override
	public int delete(TblCarCompany model) {
		return tblCarCompanyMapper.delete(model);
	}

	@Override
	public int insert(TblCarCompany record) {
		return tblCarCompanyMapper.insert(record);
	}

	@Override
	public TblCarCompany selectOne(TblCarCompany model) {
		return tblCarCompanyMapper.selectOne(model);
	}

	@Override
	public int update(TblCarCompany record) {
		return tblCarCompanyMapper.update(record);
	}

	@Override
	public List<TblCarCompany> getList(TblCarCompany model) {
		return tblCarCompanyMapper.getList(model);
	}

	@Override
	public int getCount(TblCarCompany model) {
		return tblCarCompanyMapper.getCount(model);
	}

	
}
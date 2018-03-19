package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsCarTypeMapper;
import com.wanma.model.TblCarinfo;
import com.wanma.service.CmsCarTypeService;

@Service
public class CmsCarTypeServiceImpl implements CmsCarTypeService{

	/** 用户表操作用DAO */
	@Autowired
	private CmsCarTypeMapper cartypeDao;
	
	/**
	 * 查询电动车类型
	 * 
	 */
	@Override
	public List<TblCarinfo> searchCarList(TblCarinfo tblCarinfo) {
		// TODO Auto-generated method stub
		return cartypeDao.searchCarList(tblCarinfo);
	}

}

/** 
 * FileName UserService.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/6/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblThrWarningMapper;
import com.wanma.model.TblThrWarning;
import com.wanma.service.CmsWarningService;


/**
 * FileName UserService.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/6/9
 * 
 * 充电卡用户业务处理实现类
 */
@Service
public class CmsWarningServiceImpl implements  CmsWarningService {
	
	/** 用户表操作用DAO */
	@Autowired
	private TblThrWarningMapper tblWarningMapper;

	@Override
	public long getWarningCount(TblThrWarning tblWarning) {
		// TODO Auto-generated method stub
		return tblWarningMapper.getWarningCount(tblWarning);
	}

	@Override
	public List<TblThrWarning> getWarningList(TblThrWarning tblWarning) {
		// TODO Auto-generated method stub
		return tblWarningMapper.getWarningList(tblWarning);
	}

	@Override
	public List<TblThrWarning> getCompanyType() {
		// TODO Auto-generated method stub
		return tblWarningMapper.getCompanyType();
	}

	@Override
	public void insertWarning(TblThrWarning tblWarning) {
		 tblWarningMapper.insertWarning(tblWarning);
		
	}

	/*@Override
	public long getAccountNumber(TblThrWarning tblWarning) {
		// TODO Auto-generated method stub
		return tblWarningMapper.getAccountNumber(tblWarning);
	}
*/
	@Override
	public long getWarningCout(TblThrWarning tblWarning) {
		// TODO Auto-generated method stub
		return tblWarningMapper.getWarningCout(tblWarning);
	}

	@Override
	public void updateWarning(TblThrWarning tblWarning) {
		 tblWarningMapper.updateWarning(tblWarning);
	}

	@Override
	public void updateWarningFlag(String id) {
		tblWarningMapper.updateWarningFlag(id);
		
	}
	
}

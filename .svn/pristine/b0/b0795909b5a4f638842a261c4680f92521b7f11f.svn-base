package com.bluemobi.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.db.dao.IJdbcDao;
import com.bluemobi.db.service.IJdbcService;

@Service
public class JdbcService implements IJdbcService {

	
	@Autowired
	private IJdbcDao jdbcDao;

	@Override
	public List<Object[]> find(String sql) {
		
		return jdbcDao.find(sql);
		
	}

	@Override
	public List<Object> findOneColumn(String sql) {
		
		return jdbcDao.findOneColumn(sql);
		
	}
}

package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblCityMapper;
import com.wanma.dubbox.model.TblCity;
import com.wanma.dubbox.service.TblCityService;
/**
 * 城市数据查询接口
 * @author gx
 *
 */
@Service
public class TblCityServiceImpl implements TblCityService {

	@Autowired
	TblCityMapper tblCityMapper;
	
	@Override
	public TblCity selectOne(TblCity model) {
		return tblCityMapper.selectOne(model);
	}

	@Override
	public int update(TblCity record) {
		return tblCityMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblCity model) {
		return tblCityMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblCity> getList(TblCity model) {
		return tblCityMapper.getList(model);
	}
	
}
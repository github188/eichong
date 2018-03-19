package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblProvinceMapper;
import com.wanma.dubbox.model.TblProvince;
import com.wanma.dubbox.service.TblProvinceService;
/**
 * 省份数据查询接口
 * @author lhy
 *
 */
@Service
public class TblProvinceServiceImpl implements TblProvinceService {

	@Autowired
	TblProvinceMapper tblProvinceMapper;
	@Override
	public int insert(TblProvince record) {
		return tblProvinceMapper.insert(record);
	}

	@Override
	public TblProvince selectOne(TblProvince model) {
		return tblProvinceMapper.selectOne(model);
	}

	@Override
	public int update(TblProvince record) {
		return tblProvinceMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblProvince model) {
		return tblProvinceMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblProvince> getList(TblProvince model) {
		return tblProvinceMapper.getList(model);
	}
	
}
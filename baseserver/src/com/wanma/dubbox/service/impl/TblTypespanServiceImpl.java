package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblTypespanMapper;
import com.wanma.dubbox.model.TblTypespan;
import com.wanma.dubbox.service.TblTypespanService;
/**
 * 产品型号数据查询接口
 * @author lhy
 *
 */
@Service
public class TblTypespanServiceImpl implements TblTypespanService {

	@Autowired
	TblTypespanMapper TblTypespanMapper;
	@Override
	public int insert(TblTypespan record) {
		return TblTypespanMapper.insert(record);
	}

	@Override
	public TblTypespan selectOne(TblTypespan model) {
		return TblTypespanMapper.selectOne(model);
	}

	@Override
	public int update(TblTypespan record) {
		return TblTypespanMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblTypespan model) {
		return TblTypespanMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblTypespan> getList(TblTypespan model) {
		return TblTypespanMapper.getList(model);
	}
	
}
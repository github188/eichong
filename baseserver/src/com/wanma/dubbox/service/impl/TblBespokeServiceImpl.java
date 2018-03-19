package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblBespokeMapper;
import com.wanma.dubbox.model.TblBespoke;
import com.wanma.dubbox.service.TblBespokeService;
/**
 * 电桩数据查询接口
 * @author lhy
 *
 */
@Service
public class TblBespokeServiceImpl implements TblBespokeService {

	@Autowired
	TblBespokeMapper tblBespokeMapper;

	@Override
	public int delete(TblBespoke model) {
		return tblBespokeMapper.delete(model);
	}

	@Override
	public int insert(TblBespoke record) {
		return tblBespokeMapper.insert(record);
	}

	@Override
	public TblBespoke selectOne(TblBespoke model) {
		return tblBespokeMapper.selectOne(model);
	}

	@Override
	public int update(TblBespoke record) {
		return tblBespokeMapper.update(record);
	}

	@Override
	public List<TblBespoke> getList(TblBespoke model) {
		return tblBespokeMapper.getList(model);
	}

	@Override
	public int getCount(TblBespoke model) {
		return tblBespokeMapper.getCount(model);
	}

	
}
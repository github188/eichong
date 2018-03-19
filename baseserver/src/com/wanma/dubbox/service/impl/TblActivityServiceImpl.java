package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblActivityMapper;
import com.wanma.dubbox.model.TblActivity;
import com.wanma.dubbox.service.TblActivityService;

/**
 * 城市数据查询接口
 * 
 * @author gx
 *
 */
@Service
public class TblActivityServiceImpl implements TblActivityService {

	@Autowired
	TblActivityMapper TblActivityMapper;

	@Override
	public TblActivity selectOne(TblActivity model) {
		return TblActivityMapper.selectOne(model);
	}

	@Override
	public int delete(TblActivity record) {
		return TblActivityMapper.delete(record);
	}

	/**
	 * 获取数据条数
	 * 
	 * @throws Exception
	 */
	@Override
	public int getCount(TblActivity model) {
		return TblActivityMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * 
	 * @throws Exception
	 */
	@Override
	public List<TblActivity> getList(TblActivity model) {
		return TblActivityMapper.getList(model);
	}

}
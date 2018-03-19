package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblCardapplicationformMapper;
import com.wanma.dubbox.model.TblCardapplicationform;
import com.wanma.dubbox.service.TblCardapplicationformService;
/**
 * 充电卡申请数据查询接口
 * @author lhy
 *
 */
@Service
public class TblCardapplicationformServiceImpl implements TblCardapplicationformService {

	@Autowired
	TblCardapplicationformMapper TblCardapplicationformMapper;
	@Override
	public int insert(TblCardapplicationform record) {
		return TblCardapplicationformMapper.insert(record);
	}

	@Override
	public TblCardapplicationform selectOne(TblCardapplicationform model) {
		return TblCardapplicationformMapper.selectOne(model);
	}

	@Override
	public int update(TblCardapplicationform record) {
		return TblCardapplicationformMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblCardapplicationform model) {
		return TblCardapplicationformMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblCardapplicationform> getList(TblCardapplicationform model) {
		return TblCardapplicationformMapper.getList(model);
	}
	
}
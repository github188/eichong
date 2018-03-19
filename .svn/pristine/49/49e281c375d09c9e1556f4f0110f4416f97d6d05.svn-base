package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblApplyEpMapper;
import com.wanma.dubbox.model.TblApplyEp;
import com.wanma.dubbox.service.TblApplyEpService;
/**
 * 充电卡申请数据查询接口
 * @author lhy
 *
 */
@Service
public class TblApplyEpServiceImpl implements TblApplyEpService {

	@Autowired
	TblApplyEpMapper TblApplyEpMapper;
	@Override
	public int insert(TblApplyEp record) {
		return TblApplyEpMapper.insert(record);
	}

	@Override
	public TblApplyEp selectOne(TblApplyEp model) {
		return TblApplyEpMapper.selectOne(model);
	}

	@Override
	public int update(TblApplyEp record) {
		return TblApplyEpMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblApplyEp model) {
		return TblApplyEpMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblApplyEp> getList(TblApplyEp model) {
		return TblApplyEpMapper.getList(model);
	}
	
}
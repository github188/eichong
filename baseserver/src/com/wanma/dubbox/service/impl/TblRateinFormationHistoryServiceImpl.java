package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblRateinFormationHistoryMapper;
import com.wanma.dubbox.model.TblRateinFormation;
import com.wanma.dubbox.model.TblRateinFormationHistory;
import com.wanma.dubbox.service.TblRateinFormationHistoryService;

/**
 * 费率历史数据查询接口
 * @author lhy
 *
 */
@Service
public class TblRateinFormationHistoryServiceImpl implements TblRateinFormationHistoryService {
	@Autowired
	private TblRateinFormationHistoryMapper tblRateinFormationHistoryMapper;

	@Override
	public int insert(TblRateinFormation record) {
		return tblRateinFormationHistoryMapper.insert(record);
	}

	@Override
	public int update(TblRateinFormationHistory record) {
		return tblRateinFormationHistoryMapper.update(record);
	}

	@Override
	public int delete(TblRateinFormationHistory model) {
		return tblRateinFormationHistoryMapper.delete(model);
	}

	@Override
	public TblRateinFormationHistory selectOne(TblRateinFormationHistory model) {
		return tblRateinFormationHistoryMapper.selectOne(model);
	}

	@Override
	public int getCount(TblRateinFormationHistory model) {
		return tblRateinFormationHistoryMapper.getCount(model);
	}

	@Override
	public List<TblRateinFormationHistory> getList(TblRateinFormationHistory model) {
		return tblRateinFormationHistoryMapper.getList(model);
	}

}

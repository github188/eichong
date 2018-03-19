package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblRateinFormationMapper;
import com.wanma.dubbox.model.TblRateinFormation;
import com.wanma.dubbox.service.TblRateinFormationService;

/**
 * 费率数据查询接口
 * 
 * @author lhy
 *
 */
@Service
public class TblRateinFormationServiceImpl implements TblRateinFormationService {
	@Autowired
	private TblRateinFormationMapper tblRateinFormationMapper;

	@Override
	public int insert(TblRateinFormation record) {
		return tblRateinFormationMapper.insert(record);
	}

	@Override
	public int update(TblRateinFormation record) {
		return tblRateinFormationMapper.update(record);
	}

	@Override
	public int delete(TblRateinFormation model) {
		if (model.getId() == null && model.getPkIds() == null)
			return 0;
		return tblRateinFormationMapper.delete(model);
	}

	@Override
	public TblRateinFormation selectOne(TblRateinFormation model) {
		return tblRateinFormationMapper.selectOne(model);
	}

	@Override
	public int getCount(TblRateinFormation model) {
		return tblRateinFormationMapper.getCount(model);
	}

	@Override
	public List<TblRateinFormation> getList(TblRateinFormation model) {
		return tblRateinFormationMapper.getList(model);
	}

}

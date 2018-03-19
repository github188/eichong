package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblConfigMapper;
import com.wanma.dubbox.model.TblConfig;
import com.wanma.dubbox.service.TblConfigService;

/**
 * 字典配置接口实现
 *
 */
@Service
public class TblConfigServiceImpl implements TblConfigService {
	@Autowired
	TblConfigMapper tblConfigMapper;

	@Override
	public int delete(TblConfig model) {
		return tblConfigMapper.delete(model);
	}

	@Override
	public int insert(TblConfig model) {
		return tblConfigMapper.insert(model);
	}

	@Override
	public TblConfig selectOne(TblConfig model) {
		return tblConfigMapper.selectOne(model);
	}

	@Override
	public int update(TblConfig model) {
		return tblConfigMapper.update(model);
	}

	@Override
	public List<TblConfig> getList(TblConfig model) {
		return tblConfigMapper.getList(model);
	}

	@Override
	public int getCount(TblConfig model) {
		return tblConfigMapper.getCount(model);
	}

}

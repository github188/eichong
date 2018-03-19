package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblConcentratorMapper;
import com.wanma.dubbox.model.TblConcentrator;
import com.wanma.dubbox.service.TblConcentratorService;

/**
 * 集中器数据查询接口
 * 
 * @author lhy
 *
 */
@Service
public class TblConcentratorServiceImpl implements TblConcentratorService {

	@Autowired
	private TblConcentratorMapper tblConcentratorMapper;

	@Override
	public int insert(TblConcentrator record) {
		return tblConcentratorMapper.insert(record);
	}

	@Override
	public TblConcentrator selectOne(TblConcentrator model) {
		return tblConcentratorMapper.selectOne(model);
	}

	/**
	 * 获取列表
	 * 
	 * @throws Exception
	 */
	@Override
	public List<TblConcentrator> getList(TblConcentrator model) {
		return tblConcentratorMapper.getList(model);
	}

	@Override
	public int update(TblConcentrator model) {
		return tblConcentratorMapper.update(model);
	}

	@Override
	public int delete(TblConcentrator model) {
		return tblConcentratorMapper.delete(model);
	}

	@Override
	public int getCount(TblConcentrator model) {
		return tblConcentratorMapper.getCount(model);
	}

	@Override
	public int updateMore(TblConcentrator model) throws Exception {
		if (model.getPkIds() == null)
			throw new Exception("缺少过滤字段值，请至少指定一个字段！");
		return tblConcentratorMapper.updateMore(model);
	}

}

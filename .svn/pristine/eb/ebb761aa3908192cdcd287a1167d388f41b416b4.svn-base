package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblCarinfoMapper;
import com.wanma.dubbox.model.TblCarinfo;
import com.wanma.dubbox.service.TblCarinfoService;
/**
 * 电动车品牌数据查询接口
 * @author lhy
 *
 */
@Service
public class TblCarinfoServiceImpl implements TblCarinfoService {

	@Autowired
	TblCarinfoMapper tblCarinfoMapper;

	@Override
	public int delete(TblCarinfo record) {
		return tblCarinfoMapper.delete(record);
	}

	@Override
	public int insert(TblCarinfo record) {
		return tblCarinfoMapper.insert(record);
	}

	@Override
	public TblCarinfo selectOne(TblCarinfo model) {
		return tblCarinfoMapper.selectOne(model);
	}

	@Override
	public int update(TblCarinfo record) {
		return tblCarinfoMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblCarinfo model) {
		return tblCarinfoMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblCarinfo> getList(TblCarinfo model) {
		return tblCarinfoMapper.getList(model);
	}
	
}
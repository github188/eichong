package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblPilemakerMapper;
import com.wanma.dubbox.model.TblPilemaker;
import com.wanma.dubbox.service.TblPilemakerService;
/**
 * 电桩制造厂商数据查询接口
 * @author lhy
 *
 */
@Service
public class TblPilemakerServiceImpl implements TblPilemakerService {

	@Autowired
	TblPilemakerMapper tblPilemakerMapper;

	@Override
	public int insert(TblPilemaker model) {
		return tblPilemakerMapper.insert(model);
	}

	@Override
	public int update(TblPilemaker model) {
		return tblPilemakerMapper.update(model);
	}

	@Override
	public int delete(TblPilemaker model) {
		return tblPilemakerMapper.delete(model);
	}
	
	@Override
	public TblPilemaker selectOne(TblPilemaker model) {
		return tblPilemakerMapper.selectOne(model);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblPilemaker model) {
		return tblPilemakerMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblPilemaker> getList(TblPilemaker model) {
		return tblPilemakerMapper.getList(model);
	}
	
}
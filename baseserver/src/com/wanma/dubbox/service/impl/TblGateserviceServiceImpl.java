package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblGateserviceMapper;
import com.wanma.dubbox.model.TblGateservice;
import com.wanma.dubbox.service.TblGateserviceService;
/**
 * gate服务器数据查询接口
 * @author lhy
 *
 */
@Service
public class TblGateserviceServiceImpl implements TblGateserviceService {

	@Autowired
	TblGateserviceMapper tblGateserviceMapper;

	@Override
	public int insert(TblGateservice model) {
		return tblGateserviceMapper.insert(model);
	}

	@Override
	public int update(TblGateservice model) {
		return tblGateserviceMapper.update(model);
	}

	@Override
	public int delete(TblGateservice model) {
		return tblGateserviceMapper.delete(model);
	}
	
	@Override
	public TblGateservice selectOne(TblGateservice model) {
		return tblGateserviceMapper.selectOne(model);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblGateservice model) {
		return tblGateserviceMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblGateservice> getList(TblGateservice model) {
		return tblGateserviceMapper.getList(model);
	}
	
}
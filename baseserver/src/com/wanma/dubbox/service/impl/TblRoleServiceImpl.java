package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblRoleMapper;
import com.wanma.dubbox.model.TblRole;
import com.wanma.dubbox.service.TblRoleService;
/**
 * 角色数据查询接口
 * @author lhy
 *
 */
@Service
public class TblRoleServiceImpl implements TblRoleService {

	@Autowired
	TblRoleMapper TblRoleMapper;

	@Override
	public int delete(TblRole record) {
		return TblRoleMapper.delete(record);
	}

	@Override
	public int insert(TblRole record) {
		return TblRoleMapper.insert(record);
	}

	@Override
	public TblRole selectOne(TblRole model) {
		return TblRoleMapper.selectOne(model);
	}

	@Override
	public int update(TblRole record) {
		return TblRoleMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblRole model) {
		return TblRoleMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblRole> getList(TblRole model) {
		return TblRoleMapper.getList(model);
	}
	
}
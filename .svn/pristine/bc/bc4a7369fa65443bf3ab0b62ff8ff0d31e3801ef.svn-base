package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblUserRoleMapper;
import com.wanma.dubbox.model.TblUserRole;
import com.wanma.dubbox.service.TblUserRoleService;

/**
 * 角色数据查询接口
 * 
 * @author lhy
 *
 */
@Service
public class TblUserRoleServiceImpl implements TblUserRoleService {

	@Autowired
	TblUserRoleMapper tblUserRoleMapper;

	/**
	 * 获取列表
	 * 
	 * @throws Exception
	 */
	@Override
	public List<TblUserRole> getList(TblUserRole model) {
		return tblUserRoleMapper.getList(model);
	}

	@Override
	public int delete(TblUserRole model) throws Exception {
		if (model.getRid() == null && model.getUid() == null)
			throw new Exception("角色id和用户id全为空！");
		return tblUserRoleMapper.delete(model);
	}

	@Override
	public int insert(TblUserRole model) throws Exception {
		return tblUserRoleMapper.insert(model);
	}

}
package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblMenuRoleMapper;
import com.wanma.dubbox.model.TblMenuRole;
import com.wanma.dubbox.service.TblMenuRoleService;
/**
 * 角色数据查询接口
 * @author lhy
 *
 */
@Service
public class TblMenuRoleServiceImpl implements TblMenuRoleService {

	@Autowired
	TblMenuRoleMapper tblMenuRoleMapper;
	
	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblMenuRole> getList(TblMenuRole model) {
		return tblMenuRoleMapper.getList(model);
	}

	@Override
	public int delete(TblMenuRole model) throws Exception {
		if(model.getRid() == null && model.getMid() == null)
			throw new Exception("菜单id和用户id全为空！");
		return tblMenuRoleMapper.delete(model);
	}
	
}
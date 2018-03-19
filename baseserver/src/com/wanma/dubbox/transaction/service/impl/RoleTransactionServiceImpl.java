package com.wanma.dubbox.transaction.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.dubbox.dao.TblRoleMapper;
import com.wanma.dubbox.model.TblMenuRole;
import com.wanma.dubbox.model.TblRole;
import com.wanma.dubbox.model.TblUserRole;
import com.wanma.dubbox.service.TblMenuRoleService;
import com.wanma.dubbox.service.TblRoleService;
import com.wanma.dubbox.service.TblUserRoleService;
import com.wanma.dubbox.transaction.service.RoleTransactionService;

/**
 * 角色事务控制接口
 * 
 * @author lhy
 *
 */
public class RoleTransactionServiceImpl implements RoleTransactionService {
	@Autowired
	TblRoleService tblRoleService;
	@Autowired
	TblRoleMapper roleMapper;
	@Autowired
	TblMenuRoleService menuRoleService;
	@Autowired
	TblUserRoleService userRoleService;

	@Override
	public void insert(TblRole model, String menuIds) {
		roleMapper.insert(model);
		roleMapper.modifyRoleMenuRelation(model.getId(), menuIds);
	}

	@Override
	public void update(TblRole model, String menuIds) {
		roleMapper.update(model);
		roleMapper.modifyRoleMenuRelation(model.getId(), menuIds);
	}

	@Override
	public void delete(TblRole model) throws Exception {
		roleMapper.delete(model);
		TblUserRole urModel = new TblUserRole();
		urModel.setRid(model.getId());
		userRoleService.delete(urModel);
		TblMenuRole mrModel = new TblMenuRole();
		mrModel.setRid(model.getId());
		menuRoleService.delete(mrModel);
	}
}

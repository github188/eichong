package com.wanma.dubbox.transaction.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.dubbox.model.TblUser;
import com.wanma.dubbox.model.TblUserAdmin;
import com.wanma.dubbox.model.TblUserBusiness;
import com.wanma.dubbox.model.TblUserNormal;
import com.wanma.dubbox.model.TblUserRole;
import com.wanma.dubbox.service.TblUserAdminService;
import com.wanma.dubbox.service.TblUserBusinessService;
import com.wanma.dubbox.service.TblUserNormalService;
import com.wanma.dubbox.service.TblUserRoleService;
import com.wanma.dubbox.service.TblUserService;
import com.wanma.dubbox.transaction.service.UserTransactionService;

/**
 * 用户事务控制接口
 * 
 * @author lhy
 *
 */
public class UserTransactionServiceImpl implements UserTransactionService {
	@Autowired
	private TblUserService userService;
	@Autowired
	private TblUserNormalService userNormalService;
	@Autowired
	private TblUserBusinessService tblUserBusinessService;
	@Autowired
	private TblUserRoleService userRoleService;
	@Autowired
	private TblUserAdminService tblUserAdminService;

	@Override
	public void insertNormalUser(TblUser user,
			TblUserNormal normalUser) {
		userService.insert(user);
		normalUser.setId(user.getId());
		userNormalService.insert(normalUser);
	}

	@Override
	public void updateNormalUser(TblUser user, TblUserNormal normalUser)
			throws Exception {
		userService.update(user);
		userNormalService.update(normalUser);
		String[] rolids = normalUser.getRolIds();
		if (rolids != null && rolids.length > 0) {
			TblUserRole urModel = new TblUserRole();
			urModel.setUid(user.getId().toString());
			userRoleService.delete(urModel);
			urModel.setRid(rolids[0]);
			userRoleService.insert(urModel);
		}

	}

	@Override
	public void insertUnitUser(TblUser user,
			TblUserNormal normalUser) throws Exception {
		userService.insert(user);
		normalUser.setId(user.getId());
		userNormalService.insert(normalUser);
		String[] rolids = normalUser.getRolIds();
		if (rolids != null && rolids.length > 0) {
			TblUserRole urModel = new TblUserRole();
			urModel.setUid(user.getId().toString());
			urModel.setRid(rolids[0]);
			userRoleService.insert(urModel);
		}
	}

	@Override
	public void updateUnitUser(TblUser user, TblUserNormal normalUser)
			throws Exception {
		userService.update(user);
		userNormalService.update(normalUser);
		String[] rolids = normalUser.getRolIds();
		if (rolids != null && rolids.length > 0) {
			TblUserRole urModel = new TblUserRole();
			urModel.setUid(user.getId().toString());
			userRoleService.delete(urModel);
			urModel.setRid(rolids[0]);
			userRoleService.insert(urModel);
		}
	}

	@Override
	public void deleteUnitUser(TblUser user) throws Exception {
		userService.delete(user);
		TblUserRole urModel = new TblUserRole();
		urModel.setUid(user.getId().toString());
		userRoleService.delete(urModel);
	}

	@Override
	public void insertBusinessUser(TblUser user,
			TblUserBusiness buModel) throws Exception {
		userService.insert(user);
		buModel.setId(user.getId());
		tblUserBusinessService.insert(buModel);
		String[] rolids = buModel.getRolIds();
		if (rolids != null && rolids.length > 0) {
			TblUserRole urModel = new TblUserRole();
			urModel.setUid(user.getId().toString());
			urModel.setRid(rolids[0]);
			userRoleService.insert(urModel);
		}
	}

	@Override
	public void updateBusinessUser(TblUser user, TblUserBusiness buModel)
			throws Exception {
		tblUserBusinessService.update(buModel);
		String[] rolids = buModel.getRolIds();
		if (rolids != null && rolids.length > 0) {
			TblUserRole urModel = new TblUserRole();
			urModel.setUid(buModel.getId().toString());
			userRoleService.delete(urModel);
			urModel.setRid(rolids[0]);
			userRoleService.insert(urModel);
		}
	}

	@Override
	public void deleteBusinessUser(TblUser user) throws Exception {
		userService.delete(user);
		TblUserRole urModel = new TblUserRole();
		urModel.setUid(user.getId().toString());
		userRoleService.delete(urModel);
	}

	@Override
	public void insertAdminUser(TblUser user,
			TblUserAdmin admuModel) throws Exception {
		userService.insert(user);
		admuModel.setId(user.getId());
		tblUserAdminService.insert(admuModel);
		String[] rolids = admuModel.getRolIds();
		if (rolids != null && rolids.length > 0) {
			for (String id : rolids) {
				TblUserRole urModel = new TblUserRole();
				urModel.setUid(user.getId().toString());
				urModel.setRid(id);
				userRoleService.insert(urModel);
			}
		}
	}

	@Override
	public void updateAdminUser(TblUser user, TblUserAdmin admuModel)
			throws Exception {
		tblUserAdminService.update(admuModel);
		String[] rolids = admuModel.getRolIds();
		if (rolids != null && rolids.length > 0) {
			TblUserRole urModel = new TblUserRole();
			urModel.setUid(admuModel.getId().toString());
			userRoleService.delete(urModel);
			for (String id : rolids) {
				urModel.setRid(id);
				userRoleService.insert(urModel);
			}
		}
	}

	@Override
	public void deleteAdminUser(TblUser user) throws Exception {
		userService.delete(user);
		TblUserRole urModel = new TblUserRole();
		urModel.setUid(user.getId().toString());
		userRoleService.delete(urModel);
	}

}

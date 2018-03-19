package com.wanma.dubbox.transaction.service;


import com.wanma.dubbox.model.TblUser;
import com.wanma.dubbox.model.TblUserAdmin;
import com.wanma.dubbox.model.TblUserBusiness;
import com.wanma.dubbox.model.TblUserNormal;

public interface UserTransactionService {
	void insertNormalUser(TblUser user, TblUserNormal normalUser);

	void updateNormalUser(TblUser user, TblUserNormal normalUser)
			throws Exception;

	void insertUnitUser(TblUser user, TblUserNormal normalUser)
			throws Exception;

	void updateUnitUser(TblUser user, TblUserNormal normalUser)
			throws Exception;

	void deleteUnitUser(TblUser user) throws Exception;

	void insertBusinessUser(TblUser user,
			TblUserBusiness buModel) throws Exception;

	void updateBusinessUser(TblUser user, TblUserBusiness buModel)
			throws Exception;

	void deleteBusinessUser(TblUser user) throws Exception;

	void insertAdminUser(TblUser user, TblUserAdmin admuModel)
			throws Exception;

	void updateAdminUser(TblUser user, TblUserAdmin admuModel) throws Exception;

	void deleteAdminUser(TblUser user) throws Exception;
}

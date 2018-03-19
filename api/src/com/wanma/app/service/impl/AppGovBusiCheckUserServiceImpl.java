package com.wanma.app.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppGovBusiCheckMapper;
import com.wanma.app.service.AppGovBusiCheckUserService;
import com.wanma.model.TblJpush;
import com.wanma.model.TblUser;
import com.wanma.model.TblUserinfo;

@Service
public class AppGovBusiCheckUserServiceImpl implements AppGovBusiCheckUserService {
	@Autowired
	private AppGovBusiCheckMapper checkMapper;

	@Override
	public Map<String, Object> checkUser(TblUser tbluser) {
		return checkMapper.checkUser(tbluser);
	}

	@Override
	public Map<String, Object> getUserById(int userId) {
		return checkMapper.get(userId);
	}

	@Override
	public void updateUserDeviceId(Map<String, Object> params) {
		checkMapper.updateUserDeviceId(params);
		
	}

	@Override
	public TblJpush getByuserInfo(Integer jpushUserInfo) {
		return checkMapper.getByuserInfo(jpushUserInfo);
	}

	@Override
	public int userIsExist(String usinPhone) {
		return checkMapper.userIsExist(usinPhone);
	}

	@Override
	public int resetPasswrod(Map<String, Object> params) {
		return checkMapper.resetPasswrod(params);
	}

	@Override
	public int userPhone(String usinPhone) {
		return checkMapper.userPhone(usinPhone);
	}

}

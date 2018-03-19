package com.wanma.app.service;

import com.wanma.model.GovBusiUser;


public interface GovBusiUserService {

	GovBusiUser getGovBusiUserInfo(String userId);

	int getPhoneCount(String usinPhone);

	int bindPhoneNumber(String userId, String usinPhone);

	int getUserById(String userId);

}

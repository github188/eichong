package com.wanma.app.dao;

import com.wanma.model.GovBusiUser;

public interface GovBusiUserMapper {

	GovBusiUser getUserBaseInfo(String userId);

	GovBusiUser getUserAccount(String userId);

	GovBusiUser getChargingRecord(String userId);

	GovBusiUser getAccountPc(String userId);

	int getPhoneCount(String usinPhone);

	int bindPhoneNumber(GovBusiUser info);

	int getUserById(String userId);

}

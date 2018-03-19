package com.wanma.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblActivity;

public interface CmsActivityMapper {

	long getActivityCount(TblActivity tblActivity);

	List<TblActivity> getActivityList(TblActivity tblActivity);

	void changeActStatus(Map<String, Object> params);

	void addMainActivity(TblActivity tblActivity);

	void addScheActivity(TblActivity item);

	void generateCode(List<Map<String, Object>> list);

	long checkActUnique(TblActivity tblActivity);

	List<TblActivity> getChannelType();

	int checkCommonUnique(Map<String, String> map);

	List<TblActivity> getActivitySelect(Integer state);

	public HashMap<String, Object> getAllCouponCount(TblActivity activity);

	public HashMap<String, Object> getUsedCouponCount(TblActivity activity);

	HashMap<String, Object> getActDate(TblActivity activity);

	String getUserIdByUseraccount(String userAccount);
	
	
}
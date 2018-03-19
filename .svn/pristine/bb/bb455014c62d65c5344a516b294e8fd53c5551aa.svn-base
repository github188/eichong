package com.wanma.dao;

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
	
	
}
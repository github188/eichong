package com.wanma.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wanma.model.TblActivity;



public interface CmsActivityService {

	long getActivityCount(TblActivity tblActivity);

	List<TblActivity> getActivityList(TblActivity tblActivity);

	void modifyActStatus(Map<String, Object> params);
	
	boolean addActivity(TblActivity tblActivity, HttpServletRequest request);

	boolean addUserActivity(TblActivity tblActivity, HttpServletRequest request);

	long checkActUnique(TblActivity tblActivity);

	List<TblActivity> getChannelType();
	
}

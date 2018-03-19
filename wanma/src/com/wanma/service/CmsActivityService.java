package com.wanma.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wanma.model.TblActivity;
import com.wanma.model.TblCarVin;



public interface CmsActivityService {

	long getActivityCount(TblActivity tblActivity);

	List<TblActivity> getActivityList(TblActivity tblActivity);

	void modifyActStatus(Map<String, Object> params);
	
	boolean addActivity(TblActivity tblActivity, HttpServletRequest request);

	boolean addUserActivity(TblActivity tblActivity, HttpServletRequest request, ArrayList<String> userAccount);

	long checkActUnique(TblActivity tblActivity);

	List<TblActivity> getChannelType();

	int checkCommonUnique(Map<String, String> map);

	long getCarvinCount(TblCarVin tblCarVin);

	List<TblCarVin> getCarvinList(TblCarVin tblCarVin);

	void deteleCarvin(String id);

	void addCarVin(List<TblCarVin> pileList);

	List<TblCarVin> getCarVinList_export(TblCarVin tblCarVin);
	long checkVinCode(String vincode);

	public HashMap<String, Object> getAllCouponCount(TblActivity activity);

	public HashMap<String, Object> getUsedCouponCount(TblActivity activity);

	public HashMap<String, Object> getActDate(TblActivity activity);
	public Map<String, Object> splitDate(Date bgDate,Date edDate,int term,
			List<Map<String, Object>> dataList0,List<Map<String, Object>> dataList1) throws Exception;
	
	void updateRepeatVinCode(TblCarVin tblCarVin);

	TblCarVin findOne(TblCarVin tblCarVin);

	String getUserIdByUseraccount(String userAccount);
}

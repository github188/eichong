package com.wanma.dao;


import java.util.List;
import java.util.Map;

import com.wanma.model.TblActivity;
import com.wanma.model.TblCoupon;


public interface CmsCouponDetailMapper {

	long getCouponCount(TblCoupon tblCoupon);

	List<TblCoupon> getCouponList(TblCoupon tblCoupon);

	List<TblCoupon> getCouponType();

	List<TblCoupon>  getActivityType(TblCoupon tblCoupon);

	long selectCoupon(String id);

	void deteleCouponDetail(String id);

	void changeEndDate(Map<String, Object> params);
	
	List<Map<String, Object>> getCreateCountData(TblActivity activity);

	List<Map<String, Object>> getUseCountData(TblActivity activity);

	List<TblCoupon> getCouponList_export(TblCoupon tblCoupon);
	
	
	
}

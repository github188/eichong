package com.wanma.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblCoupon;


public interface CmsCouponDetailMapper {

	long getCouponCount(TblCoupon tblCoupon);

	List<TblCoupon> getCouponList(TblCoupon tblCoupon);

	List<TblCoupon> getCouponType();

	List<TblCoupon> getActivityType();

	long selectCoupon(String id);

	void deteleCouponDetail(String id);

	void changeEndDate(Map<String, Object> params);
	
	
	
}

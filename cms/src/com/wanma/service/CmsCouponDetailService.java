package com.wanma.service;

import java.util.List;

import com.wanma.model.TblActivity;
import com.wanma.model.TblCoupon;



public interface CmsCouponDetailService {

	long getCouponCount(TblCoupon tblCoupon);

	List<TblCoupon> getCouponList(TblCoupon tblCoupon);

	List<TblCoupon> getCouponType();

	List<TblCoupon> getActivityType();

	long selectCoupon(String id);

	void deteleCouponDetail(String id);



	
}

package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsCouponDetailMapper;
import com.wanma.model.TblCoupon;
import com.wanma.service.CmsCouponDetailService;

@Service
public class CmsCouponDetailServiceImpl implements CmsCouponDetailService {
	@Autowired
	private CmsCouponDetailMapper cmsCouponDetailMapper;

	@Override
	public long getCouponCount(TblCoupon tblCoupon) {
		return cmsCouponDetailMapper.getCouponCount(tblCoupon);
	}

	@Override
	public List<TblCoupon> getCouponList(TblCoupon tblCoupon) {
		return cmsCouponDetailMapper.getCouponList(tblCoupon);
	}

	@Override
	public List<TblCoupon> getCouponType() {
		return cmsCouponDetailMapper.getCouponType();
	}

	@Override
	public List<TblCoupon> getActivityType() {
		return cmsCouponDetailMapper.getActivityType();
	}

	@Override
	public long selectCoupon(String id) {
		return cmsCouponDetailMapper.selectCoupon(id);
	}

	@Override
	public void deteleCouponDetail(String id) {
		cmsCouponDetailMapper.deteleCouponDetail(id);

	}

}

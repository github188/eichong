package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsActivityMapper;
import com.wanma.dao.CmsCouponDetailMapper;
import com.wanma.model.TblActivity;
import com.wanma.model.TblCoupon;
import com.wanma.service.CmsCouponDetailService;

@Service
public class CmsCouponDetailServiceImpl implements CmsCouponDetailService {
	@Autowired
	private CmsCouponDetailMapper cmsCouponDetailMapper;
	@Autowired
	private CmsActivityMapper cmsActivityMapper;

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
	public List<TblCoupon> getActivityType(TblCoupon tblCoupon) {
		return cmsCouponDetailMapper.getActivityType(tblCoupon);
	}

	@Override
	public long selectCoupon(String id) {
		return cmsCouponDetailMapper.selectCoupon(id);
	}

	@Override
	public void deteleCouponDetail(String id) {
		cmsCouponDetailMapper.deteleCouponDetail(id);

	}

	@Override
	public List<TblActivity> getActivityList(Integer state) {
		return cmsActivityMapper.getActivitySelect(state);
	}

	@Override
	public List<Map<String, Object>> getCreateCountData(TblActivity activity) {
		return cmsCouponDetailMapper.getCreateCountData(activity);
	}

	@Override
	public List<Map<String, Object>> getUseCountData(TblActivity activity) {
		return cmsCouponDetailMapper.getUseCountData(activity);
	}
	
	@Override
	public List<TblCoupon> getCouponList_export(TblCoupon tblCoupon) {
		// TODO Auto-generated method stub
		return cmsCouponDetailMapper.getCouponList_export(tblCoupon);
	}

}

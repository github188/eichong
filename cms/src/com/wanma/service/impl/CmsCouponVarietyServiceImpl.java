package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsCarTypeMapper;
import com.wanma.dao.CmsCouponVarietyMapper;
import com.wanma.model.TblCouponVariety;
import com.wanma.service.CmsCarTypeService;
import com.wanma.service.CmsCouponVarietyService;
@Service
public class CmsCouponVarietyServiceImpl implements CmsCouponVarietyService {
	@Autowired
	private CmsCouponVarietyMapper cmsCouponVarietyMapper;
	@Override
	public List<TblCouponVariety> getCouponVarietyList(TblCouponVariety tblCouponVariety) {
		return cmsCouponVarietyMapper.getCouponVarietyList(tblCouponVariety);
	}
	@Override
	public long getCouponVarietyCount(TblCouponVariety tblCouponVariety) {
		return cmsCouponVarietyMapper.getCouponVarietyCount(tblCouponVariety);
	}
	@Override
	public void addCouponVariety(TblCouponVariety tblCouponVariety) {
		cmsCouponVarietyMapper.addCouponVariety(tblCouponVariety);
	}
	@Override
	public TblCouponVariety getCouponVarietyById(int pkCouponVariety) {
		return cmsCouponVarietyMapper.getCouponVarietyById(pkCouponVariety);
	}
	@Override
	public void changeCouponVariety(TblCouponVariety tblCouponVariety) {
		cmsCouponVarietyMapper.changeCouponVariety(tblCouponVariety);
	}
	@Override
	public int checkCouponVariety(int pkCouponVariety) {
		return cmsCouponVarietyMapper.checkCouponVariety(pkCouponVariety);
	}
	@Override
	public void removeCouponVariety(TblCouponVariety tblCouponVariety) {
		cmsCouponVarietyMapper.removeCouponVariety(tblCouponVariety);
	}
	@Override
	public void changeCovaStutas(TblCouponVariety tblCouponVariety) {
		cmsCouponVarietyMapper.changeCovaStutas(tblCouponVariety);
	}
	@Override
	public int getCovaStutas(Integer pkCouponVariety) {
		return cmsCouponVarietyMapper.getCovaStutas(pkCouponVariety);
	}
	@Override
	public void removeCouponByCouponVarietyId(Integer pkCouponVariety) {
		cmsCouponVarietyMapper.removeCouponByCouponVarietyId(pkCouponVariety);
	}
	@Override
	public int judgeCovaActivityName(String covaActivityName) {
		return cmsCouponVarietyMapper.judgeCovaActivityName(covaActivityName);
	}



}

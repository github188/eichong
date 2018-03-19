package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblCouponVarietyMapper;
import com.wanma.dubbox.model.TblCouponVariety;
import com.wanma.dubbox.service.TblCouponVarietyService;

/**
 * 优惠券品种查询接口
 * 
 * @author lhy
 *
 */
@Service
public class TblCouponVarietyServiceImpl implements TblCouponVarietyService {

	@Autowired
	TblCouponVarietyMapper tblCouponVarietyMapper;

	@Override
	public TblCouponVariety selectOne(TblCouponVariety model) {
		return tblCouponVarietyMapper.selectOne(model);
	}

	@Override
	public int delete(TblCouponVariety record) {
		return tblCouponVarietyMapper.delete(record);
	}

	/**
	 * 获取数据条数
	 * 
	 * @throws Exception
	 */
	@Override
	public int getCount(TblCouponVariety model) {
		return tblCouponVarietyMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * 
	 * @throws Exception
	 */
	@Override
	public List<TblCouponVariety> getList(TblCouponVariety model) {
		return tblCouponVarietyMapper.getList(model);
	}

}
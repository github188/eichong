package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblChargingOrderMapper;
import com.wanma.dubbox.model.TblChargingOrder;
import com.wanma.dubbox.service.TblChargingOrderService;
/**
 * 充电订单数据查询接口
 * @author lhy
 *
 */
@Service
public class TblChargingOrderServiceImpl implements TblChargingOrderService {

	@Autowired
	TblChargingOrderMapper tblChargingOrderMapper;

	@Override
	public int delete(TblChargingOrder model) {
		return tblChargingOrderMapper.delete(model);
	}

	@Override
	public int insert(TblChargingOrder record) {
		return tblChargingOrderMapper.insert(record);
	}

	@Override
	public TblChargingOrder selectOne(TblChargingOrder model) {
		return tblChargingOrderMapper.selectOne(model);
	}

	@Override
	public int update(TblChargingOrder model) {
		return tblChargingOrderMapper.update(model);
	}

	@Override
	public List<TblChargingOrder> getList(TblChargingOrder model) {
		return tblChargingOrderMapper.getList(model);
	}

	@Override
	public int getCount(TblChargingOrder model) {
		return tblChargingOrderMapper.getCount(model);
	}

	
}
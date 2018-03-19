package com.wanma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.SimpleOrderInfoMapper;
import com.wanma.model.simple.SimpleChargeOrder;
import com.wanma.service.SimpleOrderInfoService;

@Service
public class SimpleOrderInfoServiceImpl implements SimpleOrderInfoService{
	@Autowired
	private SimpleOrderInfoMapper soMapper;
	@Override
	public SimpleChargeOrder getSimpleOrderList(String chorCode) {
		return soMapper.getSimpleOrderList(chorCode);
	}
}

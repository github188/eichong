package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.ElectricpilePushMapper;
import com.wanma.dao.ElectricpileheadPushMapper;
import com.wanma.model.TcbElectric;
import com.wanma.model.cdzts.TblElectricpileheadForSH;
import com.wanma.service.ElectricPileListService;

@Service
public class ElectricPileListServiceImpl implements ElectricPileListService{

	@Autowired
	private ElectricpileheadPushMapper electricpileheadMapper;
	
	@Autowired
	private ElectricpilePushMapper electricpileMapper;
	
	@Override
	public List<TcbElectric> getElectricpileListByPsId(Integer pkPowerstation) {
		return electricpileMapper.getElectricpileListByPsId(pkPowerstation);
	}

	@Override
	public List<TblElectricpileheadForSH> getList(TblElectricpileheadForSH head) {
		return electricpileheadMapper.getList(head);
	}


}

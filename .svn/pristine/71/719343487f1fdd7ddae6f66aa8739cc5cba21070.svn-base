package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.ChargePileMapper;
import com.wanma.model.simple.TblChargePile;
import com.wanma.service.ChargePileService;

@Service
public class ChargePileServiceImpl implements ChargePileService{

	@Autowired
	private ChargePileMapper chargePileMapper;
	@Override
	public TblChargePile getBingGunList(Integer pkElectricpile) {
		return chargePileMapper.getBingGunList(pkElectricpile);
	}
	@Override
	public List<Map<String, Object>> getPkElectricPiles(String org) {
		return chargePileMapper.getPkElectricPiles(org);
	}
	
	@Override
	public TblChargePile getChargePileList(Integer pkElectricpile) {
		return chargePileMapper.getChargePileList(pkElectricpile);
	}	

}

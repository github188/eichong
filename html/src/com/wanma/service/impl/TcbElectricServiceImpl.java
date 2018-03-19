package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TcbElectricMapper;
import com.wanma.model.TcbElectric;
import com.wanma.model.TcbStation;
import com.wanma.service.TcbElectricService;

@Service
public class TcbElectricServiceImpl implements TcbElectricService{
	@Autowired
	private TcbElectricMapper  tcbElectricDao;
	
	@Override
	public List<TcbElectric> getElectricList(TcbElectric electric) {
		return tcbElectricDao.getElectricList(electric);
	}

	@Override
	public List<TcbStation> getStationList(TcbStation station) {
		return tcbElectricDao.getStationList(station);
	}

}

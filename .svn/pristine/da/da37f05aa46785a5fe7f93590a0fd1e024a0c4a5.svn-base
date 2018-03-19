package com.wanma.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.TblElectricpile;
import com.wanma.web.dao.WebElectricpileMapper;
import com.wanma.web.service.WebElectricpileService;

@Service
public class WebElectricpileServiceImpl implements WebElectricpileService {
	
	@Autowired
	WebElectricpileMapper electricpileMapper;

	@Override
	public void insertElectricpile(TblElectricpile tblElectricpile) {
		
		 electricpileMapper.insert(tblElectricpile);
		 
	}

}

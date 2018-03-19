package com.wanma.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.JudgeNullUtils;
import com.wanma.dao.TblGateserviceMapper;
import com.wanma.model.TblGateservice;
import com.wanma.service.GateService;

@Service
public class GateServiceImpl implements GateService{

	@Autowired
	private TblGateserviceMapper tblGateserviceMapper;

	@Override
	public List<TblGateservice> getGateList() {
		// TODO Auto-generated method stub
		return tblGateserviceMapper.find(null);
	}
	@Override
	public List<TblGateservice> getGateList1() {
		// TODO Auto-generated method stub
		return tblGateserviceMapper.find1(null);
	}
	@Override
	public void addGate(TblGateservice tblGateservice) {
		// TODO Auto-generated method stub
		tblGateservice.setGtseCreatetime(new Date());
		tblGateservice.setGtseUpdatetime(new Date());
		tblGateserviceMapper.insert(tblGateservice);
	}

	@Override
	public TblGateservice getGateById(int pkGateid) {
		// TODO Auto-generated method stub
		return tblGateserviceMapper.get(pkGateid);
	}

	@Override
	public void modifyGate(TblGateservice tblGateservice) {
		// TODO Auto-generated method stub
		TblGateservice tblGateservice1=tblGateserviceMapper.get(tblGateservice.getPkGateid());
		
		if(!StringUtils.isBlank(tblGateservice.getGtseGatename())){
			tblGateservice1.setGtseGatename(tblGateservice.getGtseGatename());
		}
		if(!StringUtils.isBlank(tblGateservice.getGtseGateip())){
			tblGateservice1.setGtseGateip(tblGateservice.getGtseGateip());
		}
		if(tblGateservice.getGtseGateport()>0){
			tblGateservice1.setGtseGateport(tblGateservice.getGtseGateport());
		}
		tblGateservice1.setGtseUpdatetime(new Date());
		
		tblGateserviceMapper.update(tblGateservice1);
	}

	@Override
	public void deleteGate(String pkGateid) {
		// TODO Auto-generated method stub
		if(pkGateid.lastIndexOf(",")>0){
			String gateIds[]=pkGateid.split(",");
			for (int i = 0; i < gateIds.length; i++) {
				tblGateserviceMapper.delete(JudgeNullUtils.nvlInteget(gateIds[i]));
			}
		}else{
			 tblGateserviceMapper.delete(JudgeNullUtils.nvlInteget(pkGateid));
		}
	}
	@Override
	public void updateFailState(Integer pkGateid) {
		// TODO Auto-generated method stub
		tblGateserviceMapper.updateFailState(pkGateid);
	}
}

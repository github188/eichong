package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsFeedbackMapper;
import com.wanma.dao.CmsFilterMapper;
import com.wanma.model.Cooperate;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblPowerstation;
import com.wanma.service.CmsFilterService;

@Service
public class CmsFilterServiceImpl implements CmsFilterService {

	@Autowired
	private CmsFilterMapper cmsFilterMapper;
	
	@Override
	public long getCooperateCount(Cooperate cooperate) {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getCooperateCount(cooperate);
	}

	@Override
	public List<Cooperate> getCooperateList(Cooperate cooperate) {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getCooperateList(cooperate);
	}

	@Override
	public List<Cooperate> getCpyCompany() {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getCpyCompany();
	}

	@Override
	public void ChangeCpyOperate(Map<String,String> map) {
		// TODO Auto-generated method stub
		cmsFilterMapper.ChangeCpyOperate(map);
	}

	@Override
	public long getFilterPwCount(TblPowerstation powerStation) {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getFilterPwCount(powerStation);
	}

	@Override
	public List<TblPowerstation> getFilterPwList(TblPowerstation powerStation) {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getFilterPwList(powerStation);
	}

	@Override
	public long getFilterAddPileCount(TblElectricpile tblElectricpile) {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getFilterAddPileCount(tblElectricpile);
	}

	@Override
	public List<TblElectricpile> getFilterAddPileList(
			TblElectricpile tblElectricpile) {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getFilterAddPileList(tblElectricpile);
	}

	@Override
	public long getFilterPileCount(TblElectricpile tblElectricpile) {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getFilterPileCount(tblElectricpile);
	}

	@Override
	public List<TblElectricpile> getFilterPileList(
			TblElectricpile tblElectricpile) {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getFilterPileList(tblElectricpile);
	}

	@Override
	public long getAllPwCount(TblPowerstation tblPowerstation) {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getAllPwCount(tblPowerstation);
	}

	@Override
	public List<TblPowerstation> getAllPwList(TblPowerstation tblPowerstation) {
		// TODO Auto-generated method stub
		return cmsFilterMapper.getAllPwList(tblPowerstation);
	}

	@Override
	public void addPwStation(String[] idArray,String companyId) {
		
		try{
			HashMap<String,String> map =new HashMap<String,String>();
		
			
			for (String id : idArray) {
				map.put("companyId", companyId);
				map.put("id", id);
				
				cmsFilterMapper.deletePwStation(map);
				cmsFilterMapper.addPwStation(map);

				}
			
		}catch (Exception e){
			throw e;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyCpyOperate(Map<String, String> map) {
		try{
			cmsFilterMapper.deleteComRela(map);
			cmsFilterMapper.ChangeCpyOperate(map);
			
		}catch(Exception e){
			throw e;
		}
		
	}

	@Override
	public void deletePileByPw(String[] idArray, String companyId) {
		HashMap<String,String> map =new HashMap<String,String>();
		try{
			for (String id : idArray) {
				map.put("companyId", companyId);
				map.put("id", id);
				cmsFilterMapper.deletePwStation(map);
			}
		}
		catch(Exception e){
			throw e;
		}
	}

	@Override
	public void deletePile(HashMap<String, String> map) {
		cmsFilterMapper.deletePile(map);
		
	}

	@Override
	public void savePile(String[] idArray, HashMap<String, String> map) {
		// TODO Auto-generated method stub

		try{
		
		for (String id : idArray) {
			map.put("id", id);
			cmsFilterMapper.savePile(map);
		}
		
		}catch(Exception e){
			throw e;
		}
	}

	

}

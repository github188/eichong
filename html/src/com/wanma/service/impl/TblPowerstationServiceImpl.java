/**     
 * @Title:  TblElectricpileServiceImpl.java   
 * @Package com.wanma.service.impl   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2015年11月19日 下午4:24:40   
 * @version V1.0     
 */  
package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblPowerstationMapper;
import com.wanma.model.TblPartner;
import com.wanma.model.TblPowerstation;
import com.wanma.service.TblPowerstationService;

/**
 * @author lhy
 *
 */
@Service(value="tblPowerstationServiceImpl")
public class TblPowerstationServiceImpl implements  TblPowerstationService{
	@Autowired
	private TblPowerstationMapper tblPowerstationMapper;

	@Override
	public List<TblPowerstation> getList(TblPowerstation model) {
		return tblPowerstationMapper.getList(model);
	}

	@Override
	public List<Map<String, String>> getPointsInfoByCityCode(
			TblPowerstation model) {
		return tblPowerstationMapper.getPointsInfoByCityCode(model);
	}

	@Override
	public Map<String, Object> selectDetail(TblPowerstation model) {
		return tblPowerstationMapper.selectDetail(model);
	}

	@Override
	public List<TblPowerstation> getNewlyUpdatedList(Map<String, Object> param) {
		return tblPowerstationMapper.getNewlyUpdatedList(param);
	}

	@Override
	public int getCount(TblPowerstation model) {
		return tblPowerstationMapper.getCount(model);
	}

	@Override
	public int getNewlyUpdatedCount(Map<String, Object> param) {
		return tblPowerstationMapper.getNewlyUpdatedCount(param);
	}

	@Override
	public int getPowerStationCount(Map<String, Object> param) {
		return tblPowerstationMapper.getPowerStationCount(param);
	}

	@Override
	public List<TblPowerstation> getPowerStationList(Map<String, Object> param) {
		return tblPowerstationMapper.getPowerStationList(param);
	}

	@Override
	public int getUpdatedCount(Map<String, Object> param) {
		return tblPowerstationMapper.getUpdatedCount(param);
	}

	@Override
	public List<TblPowerstation> getUpdatedList(Map<String, Object> param) {
		return tblPowerstationMapper.getUpdatedList(param);
	}

	@Override
	public Map<String, Object> getPartnerKeyList(Map<String, Object> map) {
		return tblPowerstationMapper.getPartnerKeyList(map);
	}

	@Override
	public Map<String, String> getcpyNumberById(Map<String, String> map) {
		return tblPowerstationMapper.getcpyNumberById(map);
	}

	@Override
	public TblPartner getPartnerList(String operatorID) {
		return tblPowerstationMapper.getPartnerList(operatorID);
	}

}

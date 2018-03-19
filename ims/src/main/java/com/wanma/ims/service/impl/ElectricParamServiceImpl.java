package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.ims.common.domain.ElectricParamDO;
import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.PowerStationDO;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.ElectricParamMapper;
import com.wanma.ims.mapper.ElectricPileMapper;
import com.wanma.ims.mapper.PowerStationMapper;
import com.wanma.ims.service.ElectricParamService;

@Service
public class ElectricParamServiceImpl implements ElectricParamService{

	@Autowired
	private ElectricParamMapper electricParamMapper;
	@Autowired
	private ElectricPileMapper electricPileMapper;
	@Autowired
	private PowerStationMapper powerStationMapper;
	
	@Override
	public Long countElectricParamList(ElectricParamDO electricParamDO) {
		ElectricPileDO electrciPileDO = init(electricParamDO);
		return electricPileMapper.countElectricPile(electrciPileDO);
	}

	@Override
	public List<ElectricParamDO> getElectricParamList(ElectricParamDO electricParamDO) {
		ElectricPileDO electrciPileDO = init(electricParamDO);
		List<ElectricPileDO> list = electricPileMapper.selectElectricPileList(electrciPileDO);
		return fillElectricParam(list);
	}
	
	
	@Override
	@Transactional
	public boolean setElectricParam(ElectricParamDO electricParamDO) throws Exception{
		ElectricParamDO domain = electricParamMapper.selectElectricParam(electricParamDO);
		if(null != domain){
			electricParamDO.setId(domain.getId());
		    return electricParamMapper.updateElectricParam(electricParamDO) > 0;
		}
		return electricParamMapper.insertElectricParam(electricParamDO) > 0;
	}
	
	private ElectricPileDO init(ElectricParamDO electricParamDO){
		List<Long> ids = powerStationMapper.selectPowerStationIdByName(electricParamDO.getPowerStationName());
		electricParamDO.setPowerStationIdList(ids);
		return convertParam(electricParamDO);
	}
	
	private ElectricPileDO convertParam(ElectricParamDO electricParamDO){
		ElectricPileDO electrcPileDO = new ElectricPileDO();
		electrcPileDO.setCode(electricParamDO.getElectricPileCode());
		electrcPileDO.setPowerStationIdList(electricParamDO.getPowerStationIdList());
		electrcPileDO.setProvinceCode(electricParamDO.getProvinceCode());
		electrcPileDO.setCityCode(electricParamDO.getCityCode());
		electrcPileDO.setAreaCode(electricParamDO.getAreaCode());
		electrcPileDO.setPager(electricParamDO.getPager());
		electrcPileDO.setStatus(WanmaConstants.ELECTRIC_PILE_STATUS_ONLINE);
		return electrcPileDO;
	}
	private List<ElectricParamDO> fillElectricParam(List<ElectricPileDO> list){
		List<ElectricParamDO> resultList = new ArrayList<ElectricParamDO>();
		Set<Long> ids = new HashSet<Long>();
		Set<String> codes = new HashSet<String>();
		for (ElectricPileDO electrciPileDO : list) {
			ids.add(electrciPileDO.getPowerStationId());
			codes.add(electrciPileDO.getCode());
		}
		
		Map<Long,String> psMap = new HashMap<Long,String>();
		Map<String,ElectricParamDO> offlineMap = new HashMap<String,ElectricParamDO>();
		Map<String,ElectricParamDO> socMap = new HashMap<String,ElectricParamDO>();
		Map<String,ElectricParamDO> timeChargeMap = new HashMap<String,ElectricParamDO>();
	    Map<Integer,String> commonStatusMap = new HashMap<Integer,String>();
	    commonStatusMap.put(0, "断开");
	    commonStatusMap.put(1, "连接中");
	    Map<Integer,String> chargeModeMap = new HashMap<Integer,String>();
	    chargeModeMap.put(5, "直流");
	    chargeModeMap.put(14, "交流");
	    Map<String,String> statusMap = new HashMap<String,String>();
	    statusMap.put("0", "关闭");
	    statusMap.put("1", "开启");
	    Map<String,String> issusMap = new HashMap<String,String>();
	    issusMap.put("0", "未下发");
	    issusMap.put("1", "已下发数据但未收到响应 ");
	    issusMap.put("2", "下发成功");
	    issusMap.put("3", "下发失败");
	    
		
		// 拼接充电点名称
		PowerStationDO powerStationDO = new PowerStationDO();
		List<Long> arrayList = new ArrayList<Long>();
		arrayList.addAll(ids);
		powerStationDO.setIds(arrayList);
		List<PowerStationDO> psList = powerStationMapper.selectPowerStationList(powerStationDO);
		for (PowerStationDO powerStationDO2 : psList) {
			psMap.put(powerStationDO2.getPowerstationId(), powerStationDO2.getPowerstationName());
		}
		// 拼接 最大离线充次数
		List<String> search = new ArrayList<String>();
		search.addAll(codes);
		List<ElectricParamDO> offlineList = electricParamMapper.selectOfflineList(search);
		for (ElectricParamDO electricParamDO : offlineList) {
			offlineMap.put(electricParamDO.getElectricPileCode(), electricParamDO);
		}
		// 拼接定时SOC
		List<ElectricParamDO> socList = electricParamMapper.selectSocList(search);
		for (ElectricParamDO electricParamDO : socList) {
			socMap.put(electricParamDO.getElectricPileCode(), electricParamDO);
		}
		// 拼接定时充电时间
		List<ElectricParamDO> timeChargeList = electricParamMapper.selectTimeChargeList(search);
		for (ElectricParamDO electricParamDO : timeChargeList) {
			timeChargeMap.put(electricParamDO.getElectricPileCode(), electricParamDO);
		}
		
		for (ElectricPileDO electrciPileDO : list) {
			ElectricParamDO electricParamDO = new ElectricParamDO();
			electricParamDO.setElectricPileCode(electrciPileDO.getCode());
			electricParamDO.setEpNum(electrciPileDO.getNum());
			electricParamDO.setPowerStationName(psMap.get(electrciPileDO.getPowerStationId()));
			if(null != offlineMap.get(electricParamDO.getElectricPileCode())){
				electricParamDO.setOfflineNum(offlineMap.get(electricParamDO.getElectricPileCode()).getArgValue());
				electricParamDO.setGmtModified(offlineMap.get(electricParamDO.getElectricPileCode()).getGmtModified());
			}
			if(null != socMap.get(electricParamDO.getElectricPileCode())){
				String num = socMap.get(electricParamDO.getElectricPileCode()).getArgValue();
				if(null != statusMap.get(num)){
					electricParamDO.setSocValue(statusMap.get(num));
				}
				
			}
			if(null != timeChargeMap.get(electricParamDO.getElectricPileCode())){
				String num = timeChargeMap.get(electricParamDO.getElectricPileCode()).getIssuedStatus();
				if(null != issusMap.get(num)){
					electricParamDO.setIssuedStatus(issusMap.get(num));
				}
				electricParamDO.setTime(timeChargeMap.get(electricParamDO.getElectricPileCode()).getArgValue());
			}
			if(null != commonStatusMap.get(electrciPileDO.getCommStatus())){
				electricParamDO.setCommonStatus(commonStatusMap.get(electrciPileDO.getCommStatus()));
			}
			if(null != chargeModeMap.get(electrciPileDO.getChargingMethod())){
				electricParamDO.setChargeMode(chargeModeMap.get(electrciPileDO.getChargingMethod()));
			}
			resultList.add(electricParamDO);
		}
		return resultList;
	}

	

}

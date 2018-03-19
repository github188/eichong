package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.ims.common.convert.Convert2CountAdminEpDO;
import com.wanma.ims.common.domain.CountAdminEpRelaDO;
import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.PowerStationDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.CountAdminEpRelaMapper;
import com.wanma.ims.mapper.ElectricPileMapper;
import com.wanma.ims.mapper.PowerStationMapper;
import com.wanma.ims.service.CommonRedisService;
import com.wanma.ims.service.CountAdminEpRelaService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountAdminEpRelaServiceImpl implements CountAdminEpRelaService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CountAdminEpRelaMapper countAdminEpRelaMapper;
	@Autowired
	private ElectricPileMapper electricPileMapper;
	@Autowired
	private PowerStationMapper powerStationMapper;
	@Autowired
	private CommonRedisService commonRedisService;

	@Override
	public Long countAdminEPList(CountAdminEpRelaDO countAdminEPRelaDO) {
		return countAdminEpRelaMapper.countAdminEPList(countAdminEPRelaDO);
	}

	@Override
	public List<CountAdminEpRelaDO> getCountAdminEPList(CountAdminEpRelaDO countAdminEPRelaDO) {
		List<CountAdminEpRelaDO> countAdminEpList = countAdminEpRelaMapper.selectCountAdminEPList(countAdminEPRelaDO);
		if(CollectionUtils.isEmpty(countAdminEpList)){
			return countAdminEpList;
		}
		Set<Long> epIds = new HashSet<Long>();
		Set<Long> psIds = new HashSet<Long>();
		for (CountAdminEpRelaDO adminEpDO : countAdminEpList) {
			epIds.add(adminEpDO.getElectricPileId());
			psIds.add(adminEpDO.getPowerStationId());
		}
		List<ElectricPileDO> epList = electricPileMapper.selectByElectricPileIds(new ArrayList<Long>(epIds));
		Map<Long, String> epMap = new HashMap<Long, String>();
		for (ElectricPileDO electricPileDO : epList) {
			epMap.put(electricPileDO.getId(), electricPileDO.getName());
		}
		PowerStationDO powerStationDO = new PowerStationDO();
		powerStationDO.setIds(new ArrayList<Long>(psIds));
		List<PowerStationDO> psList = powerStationMapper.selectPowerStationList(powerStationDO);
		Map<Long, String> psMap = new HashMap<Long, String>();
		for (PowerStationDO psDO : psList) {
			psMap.put(psDO.getPowerstationId(), psDO.getPowerstationName());
		}
		for (CountAdminEpRelaDO adminEpDO : countAdminEpList) {
			if (null != epMap.get(adminEpDO.getElectricPileId())) {
				adminEpDO.setElectricPileName(epMap.get(adminEpDO.getElectricPileId()));
			}
			if(null != psMap.get(adminEpDO.getPowerStationId())){
				adminEpDO.setPowerStationName(psMap.get(adminEpDO.getPowerStationId()));
			}
		}
		return countAdminEpList;
	}

	@Override
	@Transactional
	public BaseResultDTO addAdminEpByLoginId(UserDO loginUser, Long adminId, String electricPileCode){
		BaseResultDTO result = new BaseResultDTO();
		try {
			// 桩是否存在
			ElectricPileDO domain = electricPileMapper.selectByElectricPileCode(electricPileCode);
			if(null == domain){
				result.setSuccess(false);
				result.setErrorDetail(ResultCodeConstants.ERROR_MSG_NO_ELECTRICPILE);
				return result;
			}
			// 是否已经设置过权限
			CountAdminEpRelaDO countAdminEpRelaDO = new CountAdminEpRelaDO();
			countAdminEpRelaDO = countAdminEpRelaMapper.selectElectricPileByAdminIdAndEpCode(adminId, electricPileCode);
			if (null != countAdminEpRelaDO) {
				result.setSuccess(false);
				result.setErrorDetail(ResultCodeConstants.ERROR_MSG_ELECTRICPILE_REPEAT);
				return result;
			}
			// 当前登录人是否有权限
			if(loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_WORK){
				countAdminEpRelaDO = countAdminEpRelaMapper.selectElectricPileByAdminIdAndEpCode(loginUser.getUserId(), electricPileCode);
				if (null == countAdminEpRelaDO) {
					result.setSuccess(false);
					result.setErrorDetail(ResultCodeConstants.ERROR_MSG_NO_PERMISSION);
					return result;
				}
			}else{
				List<ElectricPileDO> epList = new ArrayList<ElectricPileDO>();
				epList.add(domain);
				List<CountAdminEpRelaDO> newList = Convert2CountAdminEpDO.convertEpList2AdminEpList(epList,adminId,loginUser);
				countAdminEpRelaDO = newList.get(0);
			}
			if (countAdminEpRelaMapper.insertCountAdminEpRela(countAdminEpRelaDO) <= 0) {
				result.setSuccess(false);
				result.setErrorDetail(ResultCodeConstants.ERROR_MSG_ADD_ADMIN_EP);
				return result;
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("系统异常!");
			LOGGER.error("CountAdminEpRelaServiceImpl called addElectricByLoginId failed", e);
		}
		return result;
	}

	@Override
	@Transactional
	public BaseResultDTO removeAdminEpByLoginId(Long adminId, List<Long> epIds, String modifier) {
		BaseResultDTO result = new BaseResultDTO();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("adminId", adminId);
			params.put("epIds", epIds);
			params.put("modifier", modifier);
			if (countAdminEpRelaMapper.updateCountAdminEpRela(params) < 0) {
				result.setSuccess(false);
				result.setErrorDetail(ResultCodeConstants.ERROR_MSG_REMOVE_ADMIN_EP);
				return result;
			}
			
			// 更新redis
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("系统异常!");
			LOGGER.error("CountAdminEpRelaServiceImpl called removeAdminEpByLoginId failed", e);
		}
		return result;
	}

	@Override
	@Transactional
	public BaseResultDTO addAdminEpByPsId(UserDO loginUser, Long adminId, Long powerStationId) {
		BaseResultDTO result = new BaseResultDTO();
		try {
			List<Long> psIds = new ArrayList<Long>();
			psIds.add(powerStationId);
			List<ElectricPileDO> epList = electricPileMapper.selectByPowerStationIdsForEpRela(psIds);
			if(CollectionUtils.isEmpty(epList)){
				result.setSuccess(false);
				result.setErrorDetail(ResultCodeConstants.ERROR_MSG_NO_POWERSTATION);
				return result;
			}
			// 当前登录人是否有权限
			List<CountAdminEpRelaDO> newList = new ArrayList<CountAdminEpRelaDO>();
			if(loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_WORK){
				CountAdminEpRelaDO countAdminEpRelaDO = countAdminEpRelaMapper.selectElectricPileByAdminIdAndPsId(loginUser.getUserId(), powerStationId);
				if (null == countAdminEpRelaDO) {
					result.setSuccess(false);
					result.setErrorDetail(ResultCodeConstants.ERROR_MSG_NO_PERMISSION);
					return result;
				}
			}else{
				countAdminEpRelaMapper.deleteElectricPileByAdminIdAndPsId(adminId, powerStationId);
				newList = Convert2CountAdminEpDO.convertEpList2AdminEpList(epList,adminId,loginUser);
			}
			if (countAdminEpRelaMapper.batchInsertCountAdminEpRela(newList) <= 0) {
				result.setSuccess(false);
				result.setErrorDetail(ResultCodeConstants.ERROR_MSG_ADD_ADMIN_PS);
				return result;
			}
		} catch (Exception e) {
			result.setSuccess(false);
			result.setErrorDetail("系统异常!");
			LOGGER.error("CountAdminEpRelaServiceImpl called addAdminEpByPsId failed", e);
		}
		return result;
	}

	@Override
	public List<Long> getElectricPileIdsByLoginId(Long loginId) {
		return countAdminEpRelaMapper.selectElectricPileIdsByAdminId(loginId);
	}

	@Override
	public List<Long> getPowerStationIdsByLoginId(Long loginId) {
		return countAdminEpRelaMapper.selectPowerStationIdsByAdminId(loginId);
	}
}

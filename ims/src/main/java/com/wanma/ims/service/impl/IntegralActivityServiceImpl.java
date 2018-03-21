package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.wanma.ims.constants.WanmaConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.wanma.ims.common.domain.CityDO;
import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.IntegralActivityDO;
import com.wanma.ims.common.domain.IntegralRulesDO;
import com.wanma.ims.common.domain.IntegralRulesExtensionsDO;
import com.wanma.ims.common.domain.PowerStationDO;
import com.wanma.ims.common.domain.ProvinceDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.bo.IntegralActivityAndRulesBO;
import com.wanma.ims.common.domain.bo.IntegralAreaBO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.mapper.InitialMapper;
import com.wanma.ims.mapper.IntegralActivityMapper;
import com.wanma.ims.mapper.IntegralRulesExtensionsMapper;
import com.wanma.ims.mapper.IntegralRulesMapper;
import com.wanma.ims.mapper.PowerStationMapper;
import com.wanma.ims.service.IntegralActivityService;
import com.wanma.ims.util.DateUtil;

@Service("integralActivityService")
public class IntegralActivityServiceImpl implements IntegralActivityService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
    private IntegralActivityMapper integralActivityMapper;
	
	@Autowired
    private IntegralRulesMapper integralRulesMapper;
	
	@Autowired
    private IntegralRulesExtensionsMapper integralRulesExtensionsMapper;

	@Autowired
	private InitialMapper initialMapper;

	@Autowired
	private PowerStationMapper powerStationMapper;

	@Override
	public Long getIntegralActivityCount(IntegralActivityDO integralActivity) {
		return integralActivityMapper.getIntegralActivityCount(integralActivity);
	}
	
	@Override
	public List<IntegralActivityAndRulesBO> getIntegralActivityList(IntegralActivityDO integralActivity) {
		List<IntegralActivityAndRulesBO> resultList = integralActivityMapper.getIntegralActivityList(integralActivity);

		List<IntegralActivityAndRulesBO> changeList = new ArrayList<IntegralActivityAndRulesBO>();
		for(IntegralActivityAndRulesBO bo : resultList){
			if(bo.getPkId().intValue() == WanmaConstants.INTEGRAL_RECHARGE){
				if(bo.getIsWhole() != null && bo.getIsWhole().intValue() == 1){
					bo.setAddressName("全国");
				}else {
					changeList.add(bo);
				}
			}else if(bo.getPkId().intValue() == WanmaConstants.INTEGRAL_CONSUME){
				if(bo.getIsWhole() != null && bo.getIsWhole().intValue() == 1){
					bo.setAddressName("全国");
				}else {
					IntegralRulesExtensionsDO qryDO = new IntegralRulesExtensionsDO();
					qryDO.setIntegralRulesId(bo.getIntegralRulesId());
					Long count = integralRulesExtensionsMapper.getIntegralRulesExtensionsCount(qryDO);
					if (count > 0) {
						bo.setAddressName("已关联电桩");
					}
				}
			}else{
				if(bo.getIsWhole() != null && bo.getIsWhole().intValue() == 1){
					bo.setAddressName("全国");
				}
			}
		}

		if(changeList.size() > 0) {
			generalCityName(changeList);
		}
		return resultList;
	}

	public void generalCityName(List<IntegralActivityAndRulesBO> changeList){
		List<String> integralRuleIdList = new ArrayList<String>();
		for(IntegralActivityAndRulesBO integralActivityAndRulesBO : changeList){
			integralRuleIdList.add(integralActivityAndRulesBO.getIntegralRulesId().toString());
		}
		List<IntegralRulesExtensionsDO> extensionsDOList = integralRulesExtensionsMapper.getIntegralListByIntegralIds(integralRuleIdList);
		Map<String,IntegralRulesExtensionsDO> extensionsMap = new HashMap<String,IntegralRulesExtensionsDO>();
		Set<String> provinceSet = new HashSet<String>();
		Set<String> citySet = new HashSet<String>();
		for(IntegralRulesExtensionsDO extensionsDO : extensionsDOList){
			provinceSet.add(extensionsDO.getProvinceId());
			citySet.add(extensionsDO.getCityId());

			extensionsMap.put(extensionsDO.getIntegralRulesId().toString(), extensionsDO);
		}
		List<String> provinceIdList = new ArrayList<String>();
		List<String> cityIdList = new ArrayList<String>();
		provinceIdList.addAll(provinceSet);
		cityIdList.addAll(citySet);
		List<ProvinceDO> provinceList = initialMapper.selectProvinceList(provinceIdList);
		List<CityDO> cityList = initialMapper.selectCityList(cityIdList,null);
		Map<String,String> provinceMap = new HashMap<String,String>();
		Map<String,String> cityMap = new HashMap<String,String>();
		for (ProvinceDO provinceDO : provinceList) {
			provinceMap.put(provinceDO.getProvinceId(), provinceDO.getProvinceName());
		}
		for (CityDO cityDO : cityList) {
			cityMap.put(cityDO.getCityId(),cityDO.getCityName());
		}
		List<PowerStationDO> powerStationList = powerStationMapper.selectPowerStationList(new PowerStationDO());
		Map<Long,String> powerStationMap = new HashMap<Long,String>();
		for (PowerStationDO powerStationDO : powerStationList) {
			powerStationMap.put(powerStationDO.getPowerstationId(), powerStationDO.getPowerstationName());
		}
		String provinceName = "", cityName = "", powerStationName = "";
		IntegralRulesExtensionsDO extensionsDO;
		for (IntegralActivityAndRulesBO bo : changeList) {
			if(extensionsMap.containsKey(bo.getIntegralRulesId().toString())){
				extensionsDO = extensionsMap.get(bo.getIntegralRulesId().toString());
				provinceName = provinceMap.get(extensionsDO.getProvinceId()) == null ? "" : provinceMap.get(extensionsDO.getProvinceId());
				cityName = cityMap.get(extensionsDO.getCityId()) == null ? "" : cityMap.get(extensionsDO.getCityId());
				powerStationName = powerStationMap.get(extensionsDO.getPowerStationId()) == null ? "" : powerStationMap.get(extensionsDO.getPowerStationId());
				bo.setAddressName(provinceName + cityName + powerStationName);
				bo.setProvinceId(extensionsDO.getProvinceId());
				bo.setCityId(extensionsDO.getCityId());
				bo.setPowerStationId(extensionsDO.getPowerStationId());
			}
		}
	}

	@Override
	public List<IntegralActivityAndRulesBO> getIntegralActivityAndRulesList(IntegralActivityDO integralActivity) {
		List<IntegralActivityAndRulesBO> resultList = integralActivityMapper.getIntegralActivityAndRulesList(integralActivity);
		generalCityName(resultList);
		return resultList;
	}

	@Override
	@Transactional
	public BaseResultDTO modifyIntegralActivity(IntegralActivityAndRulesBO integralActivityAndRulesBO) throws Exception{
		BaseResultDTO baseResultDTO = new BaseResultDTO();

		if (integralActivityAndRulesBO.getModifier() == null) {
			integralActivityAndRulesBO.setModifier("");
		}
		if (integralActivityAndRulesBO.getGmtModified() == null) {
			integralActivityAndRulesBO.setGmtModified(new Date());
		}

		int result =  integralActivityMapper.modifyIntegralActivity(integralActivityAndRulesBO);
		if (result != 1) {
			log.error(this.getClass() + ".modifyIntegralActivity() error : 修改积分活动扩展属性失败!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("修改积分活动扩展属性失败!");
		}

		if(integralActivityAndRulesBO.getStrStartDate() == null
				|| integralActivityAndRulesBO.getStrStartDate().equals("")){
			log.error(this.getClass() + ".addIntegralActivity() error : 积分活动开始时间不能为空!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("积分活动开始时间不能为空！");
			return baseResultDTO;
		}
		if(integralActivityAndRulesBO.getStrEndDate() == null
				|| integralActivityAndRulesBO.getStrEndDate().equals("")){
			log.error(this.getClass() + ".addIntegralActivity() error : 积分活动结束时间不能为空!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("积分活动结束时间不能为空！");
			return baseResultDTO;
		}
		Date startDate = DateUtil.parse(integralActivityAndRulesBO.getStrStartDate(), DateUtil.TYPE_COM_YMD);
		Date endDate = DateUtil.parse(integralActivityAndRulesBO.getStrEndDate() + " 23:59:59", DateUtil.TYPE_COM_YMDHMS);

		//节假日和积分兑换优惠券，需要验证活动时间是否有重叠
		if(integralActivityAndRulesBO.getPkId().intValue() == 3
				|| integralActivityAndRulesBO.getPkId().intValue() == 4
				|| integralActivityAndRulesBO.getPkId().intValue() == 5
				|| integralActivityAndRulesBO.getPkId().intValue() == 6
				|| integralActivityAndRulesBO.getPkId().intValue() == 8
				|| integralActivityAndRulesBO.getPkId().intValue() == 9) {
			IntegralRulesDO qryIntegralRulesDO = new IntegralRulesDO();
			qryIntegralRulesDO.setPkId(integralActivityAndRulesBO.getIntegralRulesId());
			qryIntegralRulesDO.setIntegralActivityId(integralActivityAndRulesBO.getPkId());
			qryIntegralRulesDO.setStartDate(startDate);
			qryIntegralRulesDO.setEndDate(endDate);
			Long num = integralRulesMapper.getRepeatIntegralRulesCount(qryIntegralRulesDO);
			if (num != null && num.intValue() > 0) {
				log.error(this.getClass() + ".addIntegralActivity() error : 积分活动时间与现有的积分活动时间重叠!");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("积分活动时间与现有的积分活动时间重叠！");
				return baseResultDTO;
			}
		}

		if(integralActivityAndRulesBO.getHighestPriority() != null
				&& integralActivityAndRulesBO.getHighestPriority() == 1){
			IntegralRulesDO qryIntegralRulesDO = new IntegralRulesDO();
			qryIntegralRulesDO.setIntegralActivityId(integralActivityAndRulesBO.getPkId());
			qryIntegralRulesDO.setHighestPriority(0);
			qryIntegralRulesDO.setPkId(integralActivityAndRulesBO.getIntegralRulesId());
			long num = integralRulesMapper.updateIntegralRules(qryIntegralRulesDO);
		}

		IntegralRulesDO qryIntegralRulesDO = new IntegralRulesDO();
		qryIntegralRulesDO.setPkId(integralActivityAndRulesBO.getIntegralRulesId());
		List<IntegralRulesDO> integralRulesList = integralRulesMapper.getIntegralRulesList(qryIntegralRulesDO);
		if(integralRulesList == null){
			log.error(this.getClass() + ".addIntegralActivity() error : 修改积分活动失败!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("修改积分活动失败！");
			return baseResultDTO;
		}else{
			IntegralRulesDO integralRulesDO = integralRulesList.get(0);
			integralRulesDO.setPkId(integralActivityAndRulesBO.getIntegralRulesId());
			integralRulesDO.setHighestPriority(integralActivityAndRulesBO.getHighestPriority());
			integralRulesDO.setActivityStatus(integralActivityAndRulesBO.getActivityStatus());
			integralRulesDO.setFixedIntegralValue(integralActivityAndRulesBO.getFixedIntegralValue());
			integralRulesDO.setRatioIntegralValue(integralActivityAndRulesBO.getRatioIntegralValue());
			integralRulesDO.setStartDate(startDate);
			integralRulesDO.setEndDate(endDate);
			integralRulesDO.setEffectiveTimes(integralActivityAndRulesBO.getEffectiveTimes());
			integralRulesDO.setIsWhole(integralActivityAndRulesBO.getIsWhole());
			integralRulesDO.setMinValue(integralActivityAndRulesBO.getMinValue());
			integralRulesDO.setIsChoice(integralActivityAndRulesBO.getIsChoice());
			integralRulesDO.setChoiceMoney(integralActivityAndRulesBO.getChoiceMoney());
			integralRulesDO.setIsShareChoice(integralActivityAndRulesBO.getIsShareChoice());
			integralRulesDO.setShareIntegralValue(integralActivityAndRulesBO.getShareIntegralValue());
			integralRulesDO.setShareChoice(integralActivityAndRulesBO.getShareChoice());
			integralRulesDO.setLimitIntegral(integralActivityAndRulesBO.getLimitIntegral());
			integralRulesDO.setContents(integralActivityAndRulesBO.getContents() == null ? "" : integralActivityAndRulesBO.getContents());
			integralRulesDO.setModifier(integralActivityAndRulesBO.getModifier());
			integralRulesDO.setGmtModified(integralActivityAndRulesBO.getGmtModified());
			result = integralRulesMapper.modifyIntegralRules(integralRulesDO);
			if (result != 1) {
				log.error(this.getClass() + ".addIntegralActivity() error : 修改积分活动-积分规则失败!");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("修改积分活动-积分规则失败！");
				return baseResultDTO;
			}

			//积分规则扩展属性
			Long pkId = integralActivityAndRulesBO.getPkId();
			List<IntegralRulesExtensionsDO> extList;
			IntegralRulesExtensionsDO qryIntegralRulesExtensionsDO = new IntegralRulesExtensionsDO();
			qryIntegralRulesExtensionsDO.setIntegralRulesId(integralActivityAndRulesBO.getIntegralRulesId());
			integralRulesExtensionsMapper.removeIntegralRulesExtensions(qryIntegralRulesExtensionsDO);

			if (pkId.intValue() == 1) {
				//充值送
				if(integralActivityAndRulesBO.getIsWhole().intValue() == 0) {
					extList = new ArrayList<IntegralRulesExtensionsDO>();
					IntegralRulesExtensionsDO extDO = new IntegralRulesExtensionsDO();
					extDO.setIntegralRulesId(integralRulesDO.getPkId());
					extDO.setProvinceId(integralActivityAndRulesBO.getProvinceId());
					extDO.setCityId(integralActivityAndRulesBO.getCityId());
					extList.add(extDO);
					integralRulesExtensionsMapper.batchAddIntegralRulesExtensions(extList);
				}
			}else if (pkId.intValue() == 2) {
				//充电消费
				if(integralActivityAndRulesBO.getElectricPileIds() != null
						&& !integralActivityAndRulesBO.getElectricPileIds().equals("")) {
					String[] electricPileIds = integralActivityAndRulesBO.getElectricPileIds().split(";");
					IntegralRulesExtensionsDO extDO;
					extList = new ArrayList<IntegralRulesExtensionsDO>();
					for (String strElectricPileId : electricPileIds) {
						String[] ids = strElectricPileId.split(",");
						if(ids.length != 4){
							log.error(this.getClass() + ".addIntegralActivity() error : " + integralActivityAndRulesBO.getElectricPileIds() + "数据长度有问题");
							continue;
						}
						extDO = new IntegralRulesExtensionsDO();
						extDO.setIntegralRulesId(integralRulesDO.getPkId());
						extDO.setProvinceId(ids[0]);
						extDO.setCityId(ids[1]);
						extDO.setPowerStationId(Long.parseLong(ids[2]));
						extDO.setElectricPileId(Long.parseLong(ids[3]));
						extList.add(extDO);
					}
					integralRulesExtensionsMapper.batchAddIntegralRulesExtensions(extList);
				}
			}else if (pkId.intValue() == 6) {
				//积分兑换
				Map<String, Object> map = integralActivityAndRulesBO.getMap();
				if(map != null && map.size() > 0) {
					IntegralRulesExtensionsDO extDO;
					extList = new ArrayList<IntegralRulesExtensionsDO>();

					extDO = new IntegralRulesExtensionsDO();
					extDO.setIntegralRulesId(integralRulesDO.getPkId());
					extDO.setCouponVarietyId(Integer.parseInt(map.get("couponVarietyId").toString()));
					extDO.setIntegralValue(Long.parseLong(map.get("integralValue").toString()));
					extList.add(extDO);

					integralRulesExtensionsMapper.batchAddIntegralRulesExtensions(extList);
				}
			}
		}

		return baseResultDTO;
	}

	@Override
	@Transactional
	public BaseResultDTO addIntegralActivity(IntegralActivityAndRulesBO integralActivityAndRulesBO) throws Exception {
		BaseResultDTO baseResultDTO = new BaseResultDTO();
		int result = 0;

		if(integralActivityAndRulesBO == null || integralActivityAndRulesBO.getPkId() == null){
			log.error(this.getClass() + ".addIntegralActivity() error : 积分活动id不能为空!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("积分活动id不能为空！");
			return baseResultDTO;
		}
		Long pkId = integralActivityAndRulesBO.getPkId();

		if(integralActivityAndRulesBO.getStrStartDate() == null
				|| integralActivityAndRulesBO.getStrStartDate().equals("")){
			log.error(this.getClass() + ".addIntegralActivity() error : 积分活动开始时间不能为空!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("积分活动开始时间不能为空！");
			return baseResultDTO;
		}
		if(integralActivityAndRulesBO.getStrEndDate() == null
				|| integralActivityAndRulesBO.getStrEndDate().equals("")){
			log.error(this.getClass() + ".addIntegralActivity() error : 积分活动结束时间不能为空!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("积分活动结束时间不能为空！");
			return baseResultDTO;
		}
		Date startDate = DateUtil.parse(integralActivityAndRulesBO.getStrStartDate(), DateUtil.TYPE_COM_YMD);
		Date endDate = DateUtil.parse(integralActivityAndRulesBO.getStrEndDate() + " 23:59:59", DateUtil.TYPE_COM_YMDHMS);

		//节假日和积分兑换优惠券，需要验证活动时间是否有重叠
		if(pkId.intValue() == 3 || pkId.intValue() == 4  || pkId.intValue() == 5
				|| pkId.intValue() == 6 || pkId.intValue() == 8 || pkId.intValue() == 9) {
			IntegralRulesDO qryIntegralRulesDO = new IntegralRulesDO();
			qryIntegralRulesDO.setIntegralActivityId(pkId);
			qryIntegralRulesDO.setStartDate(startDate);
			qryIntegralRulesDO.setEndDate(endDate);
			Long num = integralRulesMapper.getRepeatIntegralRulesCount(qryIntegralRulesDO);
			if (num != null && num.intValue() > 0) {
				log.error(this.getClass() + ".addIntegralActivity() error : 积分活动时间与现有的积分活动时间重叠!");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("积分活动时间与现有的积分活动时间重叠！");
				return baseResultDTO;
			}
		}

		if(DateUtil.compareNow(startDate) <= 0){
			log.error(this.getClass() + ".addIntegralActivity() error : 积分活动的开始时间不能小于等于系统当前时间!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("积分活动的开始时间不能小于等于系统当前时间！");
			return baseResultDTO;
		}

		if(integralActivityAndRulesBO.getHighestPriority() != null
				&& integralActivityAndRulesBO.getHighestPriority() == 1){
			IntegralRulesDO qryIntegralRulesDO = new IntegralRulesDO();
			qryIntegralRulesDO.setIntegralActivityId(pkId);
			qryIntegralRulesDO.setHighestPriority(0);
			integralRulesMapper.updateIntegralRules(qryIntegralRulesDO);
		}

		//积分规则
		IntegralRulesDO integralRulesDO = new IntegralRulesDO();
		integralRulesDO.setIntegralActivityId(pkId);
		integralRulesDO.setHighestPriority(integralActivityAndRulesBO.getHighestPriority());
		integralRulesDO.setActivityStatus(integralActivityAndRulesBO.getActivityStatus());
		integralRulesDO.setFixedIntegralValue(integralActivityAndRulesBO.getFixedIntegralValue());
		integralRulesDO.setRatioIntegralValue(integralActivityAndRulesBO.getRatioIntegralValue());
		integralRulesDO.setStartDate(startDate);
		integralRulesDO.setEndDate(endDate);
		integralRulesDO.setEffectiveTimes(integralActivityAndRulesBO.getEffectiveTimes());
		integralRulesDO.setIsWhole(integralActivityAndRulesBO.getIsWhole() == null ? 1 : integralActivityAndRulesBO.getIsWhole());
		integralRulesDO.setMinValue(integralActivityAndRulesBO.getMinValue());
		integralRulesDO.setIsChoice(integralActivityAndRulesBO.getIsChoice());
		integralRulesDO.setChoiceMoney(integralActivityAndRulesBO.getChoiceMoney());
		integralRulesDO.setIsShareChoice(integralActivityAndRulesBO.getIsShareChoice());
		integralRulesDO.setShareIntegralValue(integralActivityAndRulesBO.getShareIntegralValue());
		integralRulesDO.setShareChoice(integralActivityAndRulesBO.getShareChoice());
		integralRulesDO.setLimitIntegral(integralActivityAndRulesBO.getLimitIntegral());
		integralRulesDO.setContents(integralActivityAndRulesBO.getContents() == null ? "" : integralActivityAndRulesBO.getContents());
		integralRulesDO.setCreator(integralActivityAndRulesBO.getCreator());
		integralRulesDO.setModifier(integralActivityAndRulesBO.getModifier());
		integralRulesDO.setGmtCreate(integralActivityAndRulesBO.getGmtCreate());
		integralRulesDO.setGmtModified(integralActivityAndRulesBO.getGmtModified());
		result = integralRulesMapper.addIntegralRules(integralRulesDO);
		if (result != 1) {
			log.error(this.getClass() + ".addIntegralActivity() error : 增加积分活动-积分规则失败!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("增加积分活动-积分规则失败！");
			return baseResultDTO;
		}

		//积分规则扩展属性
		List<IntegralRulesExtensionsDO> extList;
		if (pkId.intValue() == 1) {
			//充值送
			if(integralActivityAndRulesBO.getIsWhole().intValue() == 0) {
				extList = new ArrayList<IntegralRulesExtensionsDO>();
				IntegralRulesExtensionsDO extDO = new IntegralRulesExtensionsDO();
				extDO.setIntegralRulesId(integralRulesDO.getPkId());
				extDO.setProvinceId(integralActivityAndRulesBO.getProvinceId());
				extDO.setCityId(integralActivityAndRulesBO.getCityId());
				extList.add(extDO);
				integralRulesExtensionsMapper.batchAddIntegralRulesExtensions(extList);
			}
		}else if (pkId.intValue() == 2) {
			//充电消费
			if(integralActivityAndRulesBO.getElectricPileIds() != null
					&& !integralActivityAndRulesBO.getElectricPileIds().equals("")) {
				String[] electricPileIds = integralActivityAndRulesBO.getElectricPileIds().split(";");
				IntegralRulesExtensionsDO extDO;
				extList = new ArrayList<IntegralRulesExtensionsDO>();
				for (String strElectricPileId : electricPileIds) {
					String[] ids = strElectricPileId.split(",");
					if(ids.length != 4){
						log.error(this.getClass() + ".addIntegralActivity() error : " + integralActivityAndRulesBO.getElectricPileIds() + "数据长度有问题");
						continue;
					}
					extDO = new IntegralRulesExtensionsDO();
					extDO.setIntegralRulesId(integralRulesDO.getPkId());
					extDO.setProvinceId(ids[0]);
					extDO.setCityId(ids[1]);
					extDO.setPowerStationId(Long.parseLong(ids[2]));
					extDO.setElectricPileId(Long.parseLong(ids[3]));
					extList.add(extDO);
				}
				integralRulesExtensionsMapper.batchAddIntegralRulesExtensions(extList);
			}
		}else if (pkId.intValue() == 6) {
			//积分兑换
			Map<String, Object> map = integralActivityAndRulesBO.getMap();
			if(map != null && map.size() > 0){
				IntegralRulesExtensionsDO extDO;
				extList = new ArrayList<IntegralRulesExtensionsDO>();

				extDO = new IntegralRulesExtensionsDO();
				extDO.setIntegralRulesId(integralRulesDO.getPkId());
				extDO.setCouponVarietyId(Integer.parseInt(map.get("couponVarietyId").toString()));
				extDO.setIntegralValue(Long.parseLong(map.get("integralValue").toString()));
				extList.add(extDO);

				integralRulesExtensionsMapper.batchAddIntegralRulesExtensions(extList);
			}
		}

		return baseResultDTO;
	}

	@Override
	@Transactional
	public BaseResultDTO deleteIntegralActivity(IntegralActivityDO integralActivity) throws Exception {
		BaseResultDTO baseResultDTO = new BaseResultDTO();
		
		int result =  integralActivityMapper.deleteIntegralActivity(integralActivity);
		if (result != 1) {
			log.error(this.getClass() + ".deleteIntegralActivity() error : 删除积分活动扩展属性失败!");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("删除积分活动扩展属性失败！");
		}
		return baseResultDTO;
	}

	@Override
	public List<ProvinceDO> getStationAndPile(IntegralActivityAndRulesBO integralActivityAndRulesBO,
											  UserDO loginUser) throws Exception {
		//选中的电桩
		List<IntegralActivityAndRulesBO> integralRulesDOList;
		Map<Long, IntegralActivityAndRulesBO> relaMap = new HashMap<Long, IntegralActivityAndRulesBO>();
		if(integralActivityAndRulesBO.getPkId() == null){
			integralRulesDOList = new ArrayList<IntegralActivityAndRulesBO>();
		}else {
			IntegralActivityDO qryIntegralActivity = new IntegralActivityDO();
			qryIntegralActivity.setPkId(integralActivityAndRulesBO.getPkId());
			qryIntegralActivity.setIntegralRulesId(integralActivityAndRulesBO.getIntegralRulesId());
			integralRulesDOList = integralActivityMapper.getIntegralActivityAndRulesList(qryIntegralActivity);
			if(integralRulesDOList != null && integralRulesDOList.size() > 0) {
				relaMap = Maps.uniqueIndex(integralRulesDOList, new Function<IntegralActivityAndRulesBO, Long>() {
					@Override
					public Long apply(IntegralActivityAndRulesBO input) {
						if(input.getElectricPileId() == null){
							return 0L;
						}
						return input.getElectricPileId();
					}
				});
			}
		}

		List<ProvinceDO> resultList = new ArrayList<>();
		Map<String, ProvinceDO> provinceMap = new HashMap<>();
		Map<String, CityDO> cityMap = new HashMap<>();
		ProvinceDO provinceDO;
		CityDO cityDO;
		List<IntegralAreaBO> tempProvinceList = integralRulesMapper.getIntegralRulesProvince(new IntegralRulesDO());
		List<IntegralAreaBO> tempCityList = integralRulesMapper.getIntegralRulesCity(new IntegralRulesDO());

		for (IntegralAreaBO province : tempProvinceList) {
			provinceDO = new ProvinceDO();
			provinceDO.setId(Long.parseLong(province.getProvinceCode()));
			provinceDO.setList(new ArrayList<CityDO>());
			provinceDO.setIsSelected(false);
			provinceDO.setName(province.getProvinceName());
			provinceMap.put(province.getProvinceCode(), provinceDO);
			resultList.add(provinceDO);
		}
		for (IntegralAreaBO city : tempCityList) {
			cityDO = new CityDO();
			cityDO.setId(Long.parseLong(city.getCityCode()));
			cityDO.setList(new ArrayList<PowerStationDO>());
			cityDO.setIsSelected(false);
			cityDO.setName(city.getCityName());
			cityMap.put(city.getCityCode(), cityDO);
			if(provinceMap.containsKey(city.getProvinceCode())){
				provinceMap.get(city.getProvinceCode()).getList().add(cityDO);
			}
		}

		List<PowerStationDO> tempPowerStationList = new ArrayList<PowerStationDO>();
		List<ElectricPileDO> tempElectricPileList = new ArrayList<ElectricPileDO>();
		IntegralAreaBO tempPowerStationDO;
		IntegralAreaBO tempElectricPileDO;
		PowerStationDO powerStationDO;
		ElectricPileDO electricPileDO;
		boolean isSelected = false;
		boolean selected = false;//前端需要
		int checkedNum;
		int i = 0;
		int j = 0;
		List<IntegralAreaBO> powerStationList = integralRulesMapper.getIntegralRulesPowerStation(new IntegralRulesDO());
		//选中的电站
		Map<Long, IntegralAreaBO> powerStationMap = Maps.uniqueIndex(powerStationList, new Function<IntegralAreaBO, Long>() {
			@Override
			public Long apply(IntegralAreaBO input) {
				return input.getPowerStationId();
			}
		});

		List<IntegralAreaBO> electricPileList = integralRulesMapper.getIntegralRulesElectricPile(new IntegralRulesDO());
		for (ProvinceDO tempProvince : resultList) {
			for (CityDO tempCity : tempProvince.getList()) {
				tempPowerStationList = new ArrayList<PowerStationDO>();
				for (; i < powerStationList.size(); i++) {
					tempPowerStationDO = powerStationList.get(i);
					if (tempCity.getId().equals(Long.parseLong(tempPowerStationDO.getCityCode()))) {
						powerStationDO = new PowerStationDO();
						powerStationDO.setId(tempPowerStationDO.getPowerStationId());
						powerStationDO.setName(tempPowerStationDO.getPowerStationName());
						powerStationDO.setIsSelected(false);
						tempElectricPileList = new ArrayList<ElectricPileDO>();
						checkedNum = 0;
						for (; j < electricPileList.size(); j++) {
							tempElectricPileDO = electricPileList.get(j);
							if (tempPowerStationDO.getPowerStationId().equals(tempElectricPileDO.getPowerStationId())) {
								electricPileDO = new ElectricPileDO();
								electricPileDO.setId(tempElectricPileDO.getElectricPileId());
								electricPileDO.setName(tempElectricPileDO.getElectricPileName());
								isSelected = false;
								if (relaMap.containsKey(tempElectricPileDO.getElectricPileId())) {
									checkedNum++;
									isSelected = true;
									selected = true;
								}
								electricPileDO.setIsSelected(isSelected);
								electricPileDO.setRemark(tempProvince.getId().toString() + ","
										+ tempCity.getId().toString() + "," + powerStationDO.getId().toString() + "," + electricPileDO.getId().toString());
								tempElectricPileList.add(electricPileDO);
							} else {
								if (powerStationMap.containsKey(powerStationDO.getId())
										&& checkedNum != 0) {
									powerStationDO.setIsSelected(true);
								}
								powerStationDO.setList(tempElectricPileList);
								checkedNum = 0;
								break;
							}
							powerStationDO.setList(tempElectricPileList);
						}
						tempPowerStationList.add(powerStationDO);
					} else {
						if(selected){
							tempCity.setIsSelected(selected);
						}
						tempCity.setList(tempPowerStationList);
						break;
					}
					if(selected){
						tempCity.setIsSelected(selected);
						tempProvince.setIsSelected(selected);
					}
					tempCity.setList(tempPowerStationList);
					selected = false;
				}
			}
		}

		return resultList;
	}

	public List<IntegralActivityAndRulesBO> getIntegralActivityForBatch(IntegralActivityDO integralActivity){
		return integralActivityMapper.getIntegralActivityForBatch(integralActivity);
	}
}
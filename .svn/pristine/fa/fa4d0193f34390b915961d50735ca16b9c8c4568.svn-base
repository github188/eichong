package com.wanma.ims.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.wanma.ims.common.domain.CityDO;
import com.wanma.ims.common.domain.CompanyDO;
import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.FinAccountSplitConfigDO;
import com.wanma.ims.common.domain.FinAccountSplitConfigHistoryDO;
import com.wanma.ims.common.domain.PowerStationDO;
import com.wanma.ims.common.domain.ProvinceDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.domain.bo.IntegralAreaBO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.CompanyMapper;
import com.wanma.ims.mapper.FinAccountSplitConfigHistoryMapper;
import com.wanma.ims.mapper.FinAccountSplitConfigMapper;
import com.wanma.ims.service.FinAccountSplitConfigService;

@Service("finAccountSplitConfigService")
public class FinAccountSplitConfigServiceImpl implements FinAccountSplitConfigService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private FinAccountSplitConfigMapper finAccountSplitConfigMapper;

	@Autowired
	private FinAccountSplitConfigHistoryMapper finAccountSplitConfigHistoryMapper;

	@Autowired
	private CompanyMapper companyMapper;

	public Long getFinAccountSplitConfigCount(FinAccountSplitConfigDO finAccountSplitConfig){
		return finAccountSplitConfigMapper.getFinAccountSplitConfigCount(finAccountSplitConfig);
	}

	public List<FinAccountSplitConfigDO> getFinAccountSplitConfigList(FinAccountSplitConfigDO finAccountSplitConfig){
		List<FinAccountSplitConfigDO> configList = finAccountSplitConfigMapper.getFinAccountSplitConfigList(finAccountSplitConfig);
		if(configList != null){
			List<CompanyDO> companyDOList = companyMapper.selectCompanyList(new CompanyDO());
			Map<Long, CompanyDO> cpyMap = new HashMap<Long, CompanyDO>();
			if(companyDOList != null && companyDOList.size() > 0) {
				cpyMap = Maps.uniqueIndex(companyDOList, new Function<CompanyDO, Long>() {
					@Override
					public Long apply(CompanyDO input) {
						if(input.getCpyId() == null){
							return 0L;
						}
						return input.getCpyId();
					}
				});
			}

			Map<Integer, String> splitModeMap = new HashMap<>();
			splitModeMap.put(0, "服务费&电费分成");
			splitModeMap.put(1, "电量*单价");
			splitModeMap.put(2, "优惠券");

			Long cpyId;
			BigDecimal servicesRatio;//服务费占比
			BigDecimal electricityRatio;//电费占比
			BigDecimal price;//单价
			StringBuffer cpyNameBuffer;
			StringBuffer servicesRatioBuffer;
			StringBuffer electricityRatioBuffer;
			StringBuffer priceBuffer;
			for(FinAccountSplitConfigDO config : configList){
				cpyNameBuffer = new StringBuffer();
				servicesRatioBuffer = new StringBuffer();
				electricityRatioBuffer = new StringBuffer();
				priceBuffer = new StringBuffer();

				String splitRules = config.getSplitRules();
				splitRules = splitRules.substring(8, (splitRules.length() - 1));
				JSONArray jsonArray = JSONArray.fromObject(splitRules);

				if (config.getSplitMode().intValue() == WanmaConstants.SPLIT_MODE_SERVICE) {
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						cpyId = Long.parseLong(jsonObject.get("cpyId").toString());
						servicesRatio = new BigDecimal(jsonObject.get("servicesRatio").toString());
						electricityRatio = new BigDecimal(jsonObject.get("electricityRatio").toString());

						if (cpyMap.containsKey(cpyId)) {
							if(config.getCpyId().intValue() != cpyId.intValue()){
								cpyNameBuffer.append(cpyMap.get(cpyId).getCpyName() + "，");
							}else{
								config.setCpyName(cpyMap.get(config.getCpyId()).getCpyName());
							}

							servicesRatioBuffer.append(servicesRatio.toString() + "，");
							electricityRatioBuffer.append(electricityRatio.toString() + "，");

							config.setSplitModeName(splitModeMap.get(config.getSplitMode()));
						}
					}
					config.setStrCpyName(cpyNameBuffer.substring(0, (cpyNameBuffer.length() - 1)).toString());
					config.setStrServicesRatio(servicesRatioBuffer.substring(0, (servicesRatioBuffer.length() - 1)).toString());
					config.setStrElectricityRatio(electricityRatioBuffer.substring(0, (electricityRatioBuffer.length() - 1)).toString());
				}else if (config.getSplitMode().intValue() == WanmaConstants.SPLIT_MODE_ELECTRIC) {
					for (int i = 0; i < jsonArray.size(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						cpyId = Long.parseLong(jsonObject.get("cpyId").toString());
						price = new BigDecimal(jsonObject.get("price").toString());

						if (cpyMap.containsKey(cpyId)) {
							if(config.getCpyId().intValue() != cpyId.intValue()){
								cpyNameBuffer.append(cpyMap.get(cpyId).getCpyName() + "，");
								priceBuffer.append(price.toString() + "，");
							}else{
								config.setCpyName(cpyMap.get(config.getCpyId()).getCpyName());
							}

							config.setSplitModeName(splitModeMap.get(config.getSplitMode()));
						}
					}
					config.setStrCpyName(cpyNameBuffer.substring(0, (cpyNameBuffer.length() - 1)).toString());
					config.setStrPrice(priceBuffer.substring(0, (priceBuffer.length() - 1)).toString());
				}
			}

			if(configList.size() == 1){
				FinAccountSplitConfigDO config = configList.get(0);
				FinAccountSplitConfigDO qryConfig = new FinAccountSplitConfigDO();
				qryConfig.setSplitRules(config.getSplitRules());
				List<FinAccountSplitConfigDO> list = finAccountSplitConfigMapper.getFinAccountSplitConfigList(qryConfig);
				StringBuffer buffer = new StringBuffer();
				String electricPileIds = "";
				for (FinAccountSplitConfigDO temp :list){
					buffer.append(temp.getElectricPileId()).append(",");
				}
				electricPileIds = buffer.substring(0, (buffer.length() - 1)).toString();
				config.setElectricPileIds(electricPileIds);
			}
		}

		return configList;
	}

	/**
	 * 验证参数
	 * @param finAccountSplitConfig
	 * @return
	 */
	private BaseResultDTO checkParams(FinAccountSplitConfigDO finAccountSplitConfig){
		BaseResultDTO baseResultDTO = new BaseResultDTO();
		if (finAccountSplitConfig == null) {
			log.error(this.getClass() + ".addFinAccountSplitConfig() error : 分账配置不允许为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("分账配置不允许为空！");
			return baseResultDTO;
		}

		if (finAccountSplitConfig.getSplitMode() == null) {
			log.error(this.getClass() + ".addFinAccountSplitConfig() error : 分账配置中分账方式不允许为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("分账配置中分账方式不允许为空！");
			return baseResultDTO;
		}

		if (finAccountSplitConfig.getCpyId() == null) {
			log.error(this.getClass() + ".addFinAccountSplitConfig() error : 分账配置中电桩资产公司不允许为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("分账配置中电桩资产公司不允许为空！");
			return baseResultDTO;
		}

		if (finAccountSplitConfig.getSplitRules() == null || finAccountSplitConfig.getSplitRules().equals("")) {
			log.error(this.getClass() + ".addFinAccountSplitConfig() error : 分账配置中规则不允许为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("分账配置中规则不允许为空！");
			return baseResultDTO;
		}
		return baseResultDTO;
	}

	/**
	 * 新增分账配置
	 * @param finAccountSplitConfig
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public BaseResultDTO addFinAccountSplitConfig(FinAccountSplitConfigDO finAccountSplitConfig, UserDO loginUser) throws Exception{
		BaseResultDTO baseResultDTO = this.checkParams(finAccountSplitConfig);

		if (finAccountSplitConfig.getElectricPileIds() == null || finAccountSplitConfig.getElectricPileIds().equals("")) {
			log.error(this.getClass() + ".addFinAccountSplitConfig() error : 分账配置中电桩不允许为空！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("分账配置中电桩不允许为空！");
			return baseResultDTO;
		}

		if(!baseResultDTO.isSuccess()){
			return baseResultDTO;
		}

		String electricPileIds = finAccountSplitConfig.getElectricPileIds();
		String[] electricPileId = electricPileIds.split(",");
		FinAccountSplitConfigDO qryFinAccountSplitConfig;
		Long count = 0L;
		StringBuffer msg = new StringBuffer();
		FinAccountSplitConfigDO configDO;

		for(String id : electricPileId){
			qryFinAccountSplitConfig = new FinAccountSplitConfigDO();
			qryFinAccountSplitConfig.setElectricPileId(Long.parseLong(id));
			count = finAccountSplitConfigMapper.getFinAccountSplitConfigCount(qryFinAccountSplitConfig);
			if(count > 0){
				msg = msg.append(id).append(",");
			}
		}

		if(msg.toString().length() > 0){
			String strMsg = msg.toString();
			log.error(this.getClass() + "电桩'" + strMsg.substring(0, (strMsg.length() - 1)) + "'已经有分账配置记录！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("存在相同电桩的分账配置！");
			return baseResultDTO;
		}

		List<FinAccountSplitConfigDO> finAccountSplitConfigList = new ArrayList<FinAccountSplitConfigDO>();
		for(String id : electricPileId){
			configDO = new FinAccountSplitConfigDO();
			configDO.setSplitMode(finAccountSplitConfig.getSplitMode());
			configDO.setCpyId(finAccountSplitConfig.getCpyId());
			configDO.setElectricPileId(Long.parseLong(id));
			configDO.setSplitRules(finAccountSplitConfig.getSplitRules());
			configDO.setCreator(loginUser.getUserAccount());
			configDO.setModifier(loginUser.getUserAccount());
			configDO.setGmtCreate(new Date());
			configDO.setGmtModified(new Date());
			finAccountSplitConfigList.add(configDO);
		}
		int result = finAccountSplitConfigMapper.batchAddFinAccountSplitConfig(finAccountSplitConfigList);
		if (result < 1) {
			log.error(this.getClass() + ".addFinAccountSplitConfig() error : " + ResultCodeConstants.ERROR_MSG_ERROR_ADD);
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail(ResultCodeConstants.ERROR_MSG_ERROR_ADD);
			return baseResultDTO;
		}

		return baseResultDTO;
	}

	/**
	 * 修改分账配置
	 * @param finAccountSplitConfig
	 * @param loginUser
	 * @return
	 * @throws Exception
	 */
	@Transactional
	public BaseResultDTO modifyFinAccountSplitConfig(FinAccountSplitConfigDO finAccountSplitConfig, UserDO loginUser) throws Exception{
		BaseResultDTO baseResultDTO = this.checkParams(finAccountSplitConfig);
		if(!baseResultDTO.isSuccess()){
			return baseResultDTO;
		}

		List<FinAccountSplitConfigDO> updateDatas = new ArrayList<FinAccountSplitConfigDO>();
		List<FinAccountSplitConfigDO> deleteDatas = new ArrayList<FinAccountSplitConfigDO>();
		List<FinAccountSplitConfigDO> addDatas = new ArrayList<FinAccountSplitConfigDO>();
		List<FinAccountSplitConfigHistoryDO> addHistoryDatas = new ArrayList<FinAccountSplitConfigHistoryDO>();
		FinAccountSplitConfigHistoryDO historyData;
		Long cpyId = finAccountSplitConfig.getCpyId();
		CompanyDO companyDO = companyMapper.selectCpyListById(cpyId);
		if(companyDO == null){
			log.error(this.getClass() + ".modifyFinAccountSplitConfig() error : 公司不存在！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("公司不存在！");
			return baseResultDTO;
		}

		FinAccountSplitConfigDO qryFinAccountSplitConfigDO = new FinAccountSplitConfigDO();
		qryFinAccountSplitConfigDO.setCpyId(cpyId);
		List<FinAccountSplitConfigDO> beforeChangedDataList = finAccountSplitConfigMapper.getFinAccountSplitConfigList(qryFinAccountSplitConfigDO);
		if(beforeChangedDataList == null){
			log.error(this.getClass() + ".modifyFinAccountSplitConfig() error :" + companyDO.getCpyName() + "没有维护分账配置数据！");
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail(companyDO.getCpyName() + "没有维护分账配置数据！");
			return baseResultDTO;
		}
		Map<String, FinAccountSplitConfigDO> beforeChangedDataMap = Maps.uniqueIndex(beforeChangedDataList, new Function<FinAccountSplitConfigDO, String>() {
			@Override
			public String apply(FinAccountSplitConfigDO input) {
				return input.getElectricPileId().toString();
			}
		});

		String updElectricPileIds;
		String addElectricPileIds;
		String delElectricPileIds;
		FinAccountSplitConfigDO configDO;
		if(finAccountSplitConfig.getAddElectricPileIds() != null && finAccountSplitConfig.getAddElectricPileIds() != ""){
			addElectricPileIds = finAccountSplitConfig.getAddElectricPileIds();
			String[] addElectricPileId = addElectricPileIds.split(",");
			FinAccountSplitConfigDO qryFinAccountSplitConfig;
			Long count = 0L;
			StringBuffer msg = new StringBuffer();
			for(String id : addElectricPileId){
				qryFinAccountSplitConfig = new FinAccountSplitConfigDO();
				qryFinAccountSplitConfig.setElectricPileId(Long.parseLong(id));
				count = finAccountSplitConfigMapper.getFinAccountSplitConfigCount(qryFinAccountSplitConfig);
				if(count > 0){
					msg = msg.append(id).append(",");
				}
			}

			if(msg.toString().length() > 0){
				String strMsg = msg.toString();
				log.error(this.getClass() + "电桩'" + strMsg.substring(0, (strMsg.length() - 1)) + "'已经有分账配置记录！");
				baseResultDTO.setSuccess(false);
				baseResultDTO.setErrorDetail("存在相同电桩的分账配置！");
				return baseResultDTO;
			}

			for(String id : addElectricPileId){
				configDO = new FinAccountSplitConfigDO();
				configDO.setSplitMode(finAccountSplitConfig.getSplitMode());
				configDO.setCpyId(finAccountSplitConfig.getCpyId());
				configDO.setElectricPileId(Long.parseLong(id));
				configDO.setSplitRules(finAccountSplitConfig.getSplitRules());
				configDO.setCreator(loginUser.getUserAccount());
				configDO.setModifier(loginUser.getUserAccount());
				configDO.setGmtCreate(new Date());
				configDO.setGmtModified(new Date());
				addDatas.add(configDO);
			}
		}


		if(finAccountSplitConfig.getUpdElectricPileIds() != null && finAccountSplitConfig.getUpdElectricPileIds() != ""){
			updElectricPileIds = finAccountSplitConfig.getUpdElectricPileIds();
			String[] updElectricPileId = updElectricPileIds.split(",");
			for(String id : updElectricPileId){
				if(beforeChangedDataMap.containsKey(id)){
					configDO = beforeChangedDataMap.get(id);

					historyData = new FinAccountSplitConfigHistoryDO();
					historyData.setSplitMode(configDO.getSplitMode());
					historyData.setCpyId(configDO.getCpyId());
					historyData.setElectricPileId(configDO.getElectricPileId());
					historyData.setSplitRules(configDO.getSplitRules());
					historyData.setRefId(configDO.getPkId());
					historyData.setCreator(loginUser.getUserAccount());
					historyData.setGmtCreate(new Date());
					addHistoryDatas.add(historyData);

					configDO.setSplitMode(finAccountSplitConfig.getSplitMode());
					configDO.setSplitRules(finAccountSplitConfig.getSplitRules());
					configDO.setModifier(loginUser.getUserAccount());
					configDO.setGmtModified(new Date());
					updateDatas.add(configDO);
				}
			}
		}

		if(finAccountSplitConfig.getDelElectricPileIds() != null && finAccountSplitConfig.getDelElectricPileIds() != ""){
			delElectricPileIds = finAccountSplitConfig.getDelElectricPileIds();
			String[] delElectricPileId = delElectricPileIds.split(",");
			for(String id : delElectricPileId){
				if(beforeChangedDataMap.containsKey(id)) {
					configDO = beforeChangedDataMap.get(id);

					historyData = new FinAccountSplitConfigHistoryDO();
					historyData.setSplitMode(configDO.getSplitMode());
					historyData.setCpyId(configDO.getCpyId());
					historyData.setElectricPileId(configDO.getElectricPileId());
					historyData.setSplitRules(configDO.getSplitRules());
					historyData.setRefId(configDO.getPkId());
					historyData.setCreator(loginUser.getUserAccount());
					historyData.setGmtCreate(new Date());
					addHistoryDatas.add(historyData);

					deleteDatas.add(configDO);
				}
			}
		}

		try {
			if(addDatas.size() > 0){
				finAccountSplitConfigMapper.batchAddFinAccountSplitConfig(addDatas);
			}

			if (updateDatas.size() > 0) {
//				finAccountSplitConfigMapper.batchModifyFinAccountSplitConfig(updateDatas);
				for (FinAccountSplitConfigDO splitConfigDO : updateDatas){
					finAccountSplitConfigMapper.modifyFinAccountSplitConfig(splitConfigDO);
				}
			}

			if (deleteDatas.size() > 0) {
				finAccountSplitConfigMapper.batchRemoveFinAccountSplitConfig(deleteDatas);
			}

			if(addHistoryDatas.size() > 0){
				finAccountSplitConfigHistoryMapper.batchAddFinAccountSplitConfigHistory(addHistoryDatas);
			}
		}catch(Exception e){
			log.error(this.getClass() + ".modifyFinAccountSplitConfig() error : " + e.getMessage());
			baseResultDTO.setSuccess(false);
			baseResultDTO.setErrorDetail("修改分账配置失败！");
			return baseResultDTO;
		}

		return baseResultDTO;
	}

	@Override
	public List<ProvinceDO> getStationAndPile(FinAccountSplitConfigDO finAccountSplitConfig,
											  UserDO loginUser) throws Exception {
		//选中的电桩
		List<FinAccountSplitConfigDO> finAccountSplitConfigList;
		FinAccountSplitConfigDO qryFinAccountSplitConfig;
		Map<Long, FinAccountSplitConfigDO> map = new HashMap<Long, FinAccountSplitConfigDO>();
		if(finAccountSplitConfig.getPkId() == null){
			finAccountSplitConfigList = new ArrayList<FinAccountSplitConfigDO>();
		}else {
			qryFinAccountSplitConfig = new FinAccountSplitConfigDO();
			qryFinAccountSplitConfig.setCpyId(finAccountSplitConfig.getCpyId());
			qryFinAccountSplitConfig.setSplitRules(finAccountSplitConfig.getSplitRules());
			finAccountSplitConfigList = finAccountSplitConfigMapper.getFinAccountSplitConfigList(qryFinAccountSplitConfig);
			if(finAccountSplitConfigList != null && finAccountSplitConfigList.size() > 0) {
				map = Maps.uniqueIndex(finAccountSplitConfigList, new Function<FinAccountSplitConfigDO, Long>() {
					@Override
					public Long apply(FinAccountSplitConfigDO input) {
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
		qryFinAccountSplitConfig = new FinAccountSplitConfigDO();
		qryFinAccountSplitConfig.setCpyId(finAccountSplitConfig.getCpyId());
		List<IntegralAreaBO> tempProvinceList = finAccountSplitConfigMapper.getProvince(qryFinAccountSplitConfig);
		List<IntegralAreaBO> tempCityList = finAccountSplitConfigMapper.getCity(qryFinAccountSplitConfig);

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
		List<IntegralAreaBO> powerStationList = finAccountSplitConfigMapper.getPowerStation(qryFinAccountSplitConfig);
		//选中的电站
		Map<Long, IntegralAreaBO> powerStationMap = Maps.uniqueIndex(powerStationList, new Function<IntegralAreaBO, Long>() {
			@Override
			public Long apply(IntegralAreaBO input) {
				return input.getPowerStationId();
			}
		});

		List<IntegralAreaBO> electricPileList = finAccountSplitConfigMapper.getElectricPile(qryFinAccountSplitConfig);
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
								if (map.containsKey(tempElectricPileDO.getElectricPileId())) {
									checkedNum++;
									isSelected = true;
									selected = true;
								}
								electricPileDO.setIsSelected(isSelected);
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
}

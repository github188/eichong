package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.wanma.ims.common.domain.CityDO;
import com.wanma.ims.common.domain.CompanyChargeRelaDO;
import com.wanma.ims.common.domain.CompanyDO;
import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.PowerStationDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.BatchResultDTO;
import com.wanma.ims.mapper.CompanyChargeRelaMapper;
import com.wanma.ims.mapper.CompanyMapper;
import com.wanma.ims.mapper.ElectricPileMapper;
import com.wanma.ims.service.CompanyChargeRelaService;
import com.wanma.ims.service.InitialService;
import com.wanma.ims.service.PowerStationService;
import com.wanma.ims.util.ImsBaseUtil;

/**
 * Created by xyc on 2017/7/25.
 * 充电范围逻辑处理接口
 */
@Service
public class CompanyChargeRelaServiceImpl implements CompanyChargeRelaService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CompanyChargeRelaMapper companyChargeRelaMapper;
    @Autowired
    private PowerStationService powerStationService;
    @Autowired
    private ElectricPileMapper electricPileMapper;
    @Autowired
    private InitialService initialService;
    @Autowired
    private CompanyMapper companyMapper;

    @Override
    public List<Integer> companyIndexChargeRela(CompanyChargeRelaDO searchModel) {
        List<Integer> result = new ArrayList<>();
        List<CompanyChargeRelaDO> relaList = companyChargeRelaMapper.getCompanyChargeRelaList(searchModel);
        Set<Long> powerStationIdSet = new HashSet<>();
        Set<Long> electricPileIdSet = new HashSet<>();

        for (CompanyChargeRelaDO rela : relaList) {
            powerStationIdSet.add(rela.getPowerStationId());
            electricPileIdSet.add(rela.getElectricPileId());
        }

        result.add(powerStationIdSet.size());
        result.add(electricPileIdSet.size());

        return result;
    }

    @Override
    public List<CityDO> getStationAndPile(Long cpyId, UserDO loginUser) {
        List<PowerStationDO> powerStationList = powerStationService.getAllPowerStationList(new PowerStationDO());

        Map<Long, PowerStationDO> powerStationMap = Maps.uniqueIndex(powerStationList, new Function<PowerStationDO, Long>() {
            @Override
            public Long apply(PowerStationDO input) {
                return input.getPowerstationId();
            }
        });
        List<ElectricPileDO> electricPileList = electricPileMapper.selectByPowerStationIds(new ArrayList<>(powerStationMap.keySet()));
        CompanyChargeRelaDO relaSearchModel = new CompanyChargeRelaDO();
        relaSearchModel.setCpyId(cpyId);
        List<CompanyChargeRelaDO> relaList = companyChargeRelaMapper.getCompanyChargeRelaList(relaSearchModel);
        Map<Long, CompanyChargeRelaDO> relaMap = Maps.uniqueIndex(relaList, new Function<CompanyChargeRelaDO, Long>() {
            @Override
            public Long apply(CompanyChargeRelaDO input) {
                return input.getElectricPileId();
            }
        });

        for (ElectricPileDO electricPile : electricPileList) {
            PowerStationDO powerStation = powerStationMap.get(electricPile.getPowerStationId());
            List<ElectricPileDO> electricPiles = powerStation.getList();
            if (electricPiles == null) {
                electricPiles = new ArrayList<>();
                powerStation.setList(electricPiles);
            }
            electricPiles.add(electricPile);

            if (relaMap.get(electricPile.getId()) == null) {
                electricPile.setIsSelected(false);
                continue;
            }

            electricPile.setIsSelected(true);

            Long selectedElectricPileNum = powerStation.getSelectedElectricPileNum() == null ? 1L : powerStation.getSelectedElectricPileNum() + 1L;
            powerStation.setSelectedElectricPileNum(selectedElectricPileNum);
            if (Objects.equals(powerStation.getElectricPileNum(), powerStation.getSelectedElectricPileNum())) {
                powerStation.setIsSelected(true);
            } else {
                powerStation.setIsSelected(false);
            }
        }

        List<CityDO> resultList = new ArrayList<>();
        Map<String, CityDO> cityMap = new HashMap<>();

        for (Long powerStationId : powerStationMap.keySet()) {
            PowerStationDO powerStation = powerStationMap.get(powerStationId);
            powerStation.setName(powerStation.getPowerstationName());
            CityDO city = cityMap.get(powerStation.getCityCode());
            if (city == null) {
                city = new CityDO();
                city.setCityId(powerStation.getCityCode());
                city.setList(new ArrayList<PowerStationDO>());
                city.setIsSelected(true);
                cityMap.put(powerStation.getCityCode(), city);
                resultList.add(city);
            }

            if (city.getIsSelected() && (powerStation.getIsSelected() == null || !powerStation.getIsSelected())) {
                powerStation.setIsSelected(false);
                city.setIsSelected(false);
            }

            city.getList().add(powerStation);
        }

        List<CityDO> cityList = initialService.getCityListByIds(new ArrayList<>(cityMap.keySet()),null);
        for (CityDO city : cityList) {
            cityMap.get(city.getCityId()).setName(city.getCityName());
        }

        return resultList;
    }

    @Override
    @Transactional
    public BaseResultDTO setCompanyChargeRela(Long cpyId, List<CompanyChargeRelaDO> relaList, UserDO loginUser) throws Exception {
        BaseResultDTO result = new BaseResultDTO();

        //删除旧的范围数据
        companyChargeRelaMapper.deleteByCpyId(cpyId);

        for (CompanyChargeRelaDO real : relaList) {
            real.setCpyId(cpyId);
            real.setCreator(loginUser.getUserAccount());
            real.setModifier(loginUser.getUserAccount());
            if (companyChargeRelaMapper.insertCompanyChargeRela(real) < 1) {
                LOGGER.warn(this.getClass() + "-setCompanyChargeRela is failed, insertCompanyChargeRela is error|companyChargeRela={}|loginUser={}", real, loginUser);
                throw new Exception("setCompanyChargeRela is failed, insertCompanyChargeRela is error");
            }
        }
        return result;
    }

	@Override
	public BatchResultDTO<CompanyChargeRelaDO> parseCompanyChargeRelaExcel(MultipartFile file) {
		BatchResultDTO<CompanyChargeRelaDO> result = new BatchResultDTO<CompanyChargeRelaDO>();
		result.setSuccess(false);
		try{
			Workbook workbook = ImsBaseUtil.createWorkbook(file);
			List<Map<String, String>> resultMapList = ImsBaseUtil.parseExcel(workbook, 6);
			if (null == resultMapList) {
				result.setErrorDetail("导入数据过多，超过允许的最大行数。");
				return result;
			}
			if (resultMapList.isEmpty()) {
				result.setErrorDetail("无有效数据");
				return result;
			}	
		   List<CompanyChargeRelaDO> list =  new ArrayList<CompanyChargeRelaDO>();
		   String errorPsLine = ""; // 充电点ID错误行
		   String errorNumberLine = ""; // 公司标识错误行
		   String errorPsEmpty=""; //充电点不存在
		   String errorNumberEmpty=""; //公司标识不存在
		   String errorPileEmpty=""; //桩信息不存在
		   Set<Long> psSet =  new HashSet<Long>();
		   Set<Integer> numberSet = new HashSet<Integer>();
		   for(int i=0; i<resultMapList.size(); i++){
			   int indexNum = i + 2;
			   Map<String,String> rowMap = resultMapList.get(i);
			   String powerStationId = rowMap.get("0");
			   String cpyNumber = rowMap.get("5");
			   if(StringUtils.isBlank(powerStationId)){
				   if (StringUtils.isNotBlank(errorPsLine)) {
					   errorPsLine += ";";
					}
				   errorPsLine += indexNum;
				   continue;
			   }
			   if(StringUtils.isBlank(cpyNumber)){
				   if (StringUtils.isNotBlank(errorNumberLine)) {
					   errorNumberLine += ";";
					}
				   errorNumberLine += indexNum;
				   continue;
			   }
			   psSet.add(new Long(powerStationId));
			   numberSet.add(Integer.parseInt(cpyNumber));
			   // 生成公司充电对象
			   CompanyChargeRelaDO temDO = new CompanyChargeRelaDO();
			   temDO.setIndexNum(indexNum);
			   temDO.setPowerStationId(new Long(powerStationId));
			   temDO.setCpyNumber(Integer.parseInt(cpyNumber));
			   list.add(temDO);
		   }
		   // 判断充电点是否存在
		   PowerStationDO powerStationDO = new PowerStationDO();
		   powerStationDO.setIds(new ArrayList<Long>(psSet));
		   List<PowerStationDO> psList = powerStationService.getAllPowerStationList(powerStationDO);
		   ElectricPileDO electricPileDO = new ElectricPileDO();
		   electricPileDO.setPowerStationIdList(new ArrayList<Long>(psSet));
		   List<ElectricPileDO> electricList = electricPileMapper.selectElectricPileList(electricPileDO);
		   Map<Long,List<ElectricPileDO>> electricMap = new HashMap<Long,List<ElectricPileDO>>();
		   for (ElectricPileDO electricPileDO2 : electricList) {
			   List<ElectricPileDO> pileList = electricMap.get(electricPileDO2.getPowerStationId());
			    if(null == pileList){
			    	pileList = new ArrayList<ElectricPileDO>();
			    }
			    pileList.add(electricPileDO2);
			    electricMap.put(electricPileDO2.getPowerStationId(), pileList);
		   }
		   Map<Long,PowerStationDO> psMap = new HashMap<Long,PowerStationDO>();
		   for (PowerStationDO powerStationDO2 : psList) {
			   psMap.put(powerStationDO2.getPowerstationId(), powerStationDO2);
		   }
		   
		   // 判断公司标识是否存在
		   CompanyDO companyDO = new CompanyDO();
		   companyDO.setCpyNumberList(new ArrayList<Integer>(numberSet));
		   List<CompanyDO> cpyList = companyMapper.selectCompanyList(companyDO);
		   Map<Integer,CompanyDO> cpyMap = new HashMap<Integer,CompanyDO>();
		   for (CompanyDO companyDO2 : cpyList) {
			   cpyMap.put(companyDO2.getCpyNumber(), companyDO2);
		   }
		   
		   List<CompanyChargeRelaDO> resultList = new  ArrayList<CompanyChargeRelaDO>();
		   for (CompanyChargeRelaDO chargeDO : list) {
			   Integer indexNum = chargeDO.getIndexNum();
			   if(null == psMap.get(chargeDO.getPowerStationId())){
				   if (StringUtils.isNotBlank(errorPsEmpty)) {
					   errorPsEmpty += ";";
					}
				   errorPsEmpty += indexNum;
				   continue; 
			   }
			   if(null == electricMap.get(chargeDO.getPowerStationId())){
				   if (StringUtils.isNotBlank(errorPileEmpty)) {
					   errorPileEmpty += ";";
					}
				   errorPileEmpty += indexNum;
				   continue; 
			   }
			   if(null == cpyMap.get(chargeDO.getCpyNumber())){
				   if (StringUtils.isNotBlank(errorNumberEmpty)) {
					   errorNumberEmpty += ";";
					}
				   errorNumberEmpty += indexNum;
				   continue; 
			   }
			   List<ElectricPileDO> temList = electricMap.get(chargeDO.getPowerStationId());
			   for (ElectricPileDO electricPileDO2 : temList) {
				   CompanyChargeRelaDO temDO = new CompanyChargeRelaDO();
				   temDO.setCpyId(cpyMap.get(chargeDO.getCpyNumber()).getCpyId());
				   temDO.setPowerStationId(chargeDO.getPowerStationId());
				   temDO.setElectricPileId(electricPileDO2.getId());
				   temDO.setElectricPileCode(electricPileDO2.getCode());
				   temDO.setGmtCreate(new Date());
				   temDO.setGmtModified(new Date());
				   temDO.setCreator("admin");
				   temDO.setModifier("admin");
				   temDO.setIsDel(1);
				   resultList.add(temDO);
		    	}
		   }
		   String allErrorCode = "";
			if (StringUtils.isNotBlank(errorPsLine)) {
				allErrorCode += "充电点ID不能为空错误行" + errorPsLine + "</br>";
			}
			if (StringUtils.isNotBlank(errorNumberLine)) {
				allErrorCode += "公司标识不能为空错误行" + errorNumberLine + "</br>";
			}
			if (StringUtils.isNotBlank(errorPsEmpty)) {
				allErrorCode += "充电点ID不存在错误行" + errorPsEmpty + "</br>";
			}
			if (StringUtils.isNotBlank(errorPileEmpty)) {
				allErrorCode += "充电点ID下没有桩错误行" + errorPileEmpty + "</br>";
			}
			if (StringUtils.isNotBlank(errorNumberEmpty)) {
				allErrorCode += "公司标识不存在错误行" + errorNumberEmpty + "</br>";
			}
			if (StringUtils.isNotBlank(allErrorCode)) {
				result.setErrorDetail(allErrorCode);
			}
		    result.setSuccess(true);
		    result.setModule(resultList);
		}catch(Exception e){
			result.setSuccess(false);
			result.setErrorDetail("系统异常!");
			LOGGER.error("CompanyChargeRelaServiceImpl called parseCompanyChargeRelaExcel failed", e);
		}
		return result;
	}

	@Override
	@Transactional
	public BaseResultDTO addCompanyChargeRela(List<CompanyChargeRelaDO> relaList) throws Exception {
		BaseResultDTO result = new BaseResultDTO();
        //删除旧的范围数据
		Map<String,CompanyChargeRelaDO> temp = new HashMap<String,CompanyChargeRelaDO>();
		for(CompanyChargeRelaDO ncr : relaList){
			String key = ncr.getCpyId() + "=" +ncr.getPowerStationId();
			if(null == temp.get(key)){
				temp.put(key, ncr);
			}
		} 
		for (CompanyChargeRelaDO cr : temp.values()) {
			companyChargeRelaMapper.deleteBypsIdAndCpyId(cr.getCpyId(),cr.getPowerStationId());
		}
        if(companyChargeRelaMapper.batchInsertCompanyChargeRela(relaList) <= 0){
        	result.setSuccess(false);
            result.setErrorDetail("导入失败");
        }
        return result;
	}
	
}

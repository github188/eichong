package com.wanma.ims.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.PowerStationDO;
import com.wanma.ims.common.domain.RateInfoDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.core.GlobalSystem;
import com.wanma.ims.mapper.CompanyChargeRelaMapper;
import com.wanma.ims.mapper.ElectricPileMapper;
import com.wanma.ims.mapper.InitialMapper;
import com.wanma.ims.mapper.PowerStationMapper;
import com.wanma.ims.mapper.RateInfoMapper;
import com.wanma.ims.service.ElectricPileService;
import com.wanma.ims.service.MultipartFileService;
import com.wanma.ims.service.PowerStationService;
import com.wanma.ims.util.ApiUtil;
import com.wanma.ims.util.HttpsUtil;

@Service
public class PowerStationServiceImpl implements PowerStationService {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PowerStationMapper powerStationMapper;
    @Autowired
    private ElectricPileService electricPileService;
    @Autowired
    private ElectricPileMapper electricPileMapper;
    @Autowired
    private RateInfoMapper rateInfoMapper;
    @Autowired
    private InitialMapper initialMapper;
    @Autowired
    private CompanyChargeRelaMapper companyChargeRelaMapper;
    @Autowired
    private MultipartFileService multipartFileService;
    
    ExecutorService cachedThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 180L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

    @Override
    public Long countPowerStationList(PowerStationDO powerStationDO,UserDO loginUser) {
    	if(loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_WORK){
    		if(CollectionUtils.isEmpty(powerStationDO.getIds())){
        		return 0L;
        	}
		}
        return powerStationMapper.countPowerStationList(powerStationDO);
    }

    @Override
    public List<PowerStationDO> getPowerStationList(PowerStationDO powerStationDO,UserDO loginUser) {
    	if(loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_WORK){
    		if(CollectionUtils.isEmpty(powerStationDO.getIds())){
    			return new ArrayList<PowerStationDO>();
        	}
		}
        List<PowerStationDO> powerStationList = powerStationMapper.selectPowerStationList(powerStationDO);

        Map<Long,PowerStationDO> powerStationMap = new HashMap<Long,PowerStationDO>();
        for (PowerStationDO powerStationDO2 : powerStationList) {
        	powerStationMap.put(powerStationDO2.getPowerstationId(),powerStationDO2);
		}

        if(CollectionUtils.isNotEmpty(powerStationMap.keySet())){
        	 List<PowerStationDO> electricPileCount = electricPileMapper.countByPowerStationIds(new ArrayList<>(powerStationMap.keySet()),1);

             for (PowerStationDO count : electricPileCount) {
                 PowerStationDO powerStation = powerStationMap.get(count.getPowerstationId());
                 if (powerStation != null) {
                     powerStation.setElectricPileNum(count.getElectricPileNum());
                 }
             }
        }
        return powerStationList;
    }

    @Override
    public PowerStationDO getPowerStationById(Long powerStationId) {
        PowerStationDO powerStationDO = powerStationMapper.selectPowerStationById(powerStationId);
        //省市区
        powerStationDO.setProvinceName(initialMapper.selectProvinceById(powerStationDO.getProvinceCode()));
        powerStationDO.setCityName(initialMapper.selectCityById(powerStationDO.getCityCode()));
        powerStationDO.setAreaName(initialMapper.selectAreaById(powerStationDO.getAreaCode()));
        //picImgList
        List<String> picImgList = multipartFileService.getAllMultiUrl(WanmaConstants.POWERSTATION_PIC, powerStationId+"");
        if (CollectionUtils.isNotEmpty(picImgList)) {
        	powerStationDO.setPicImgList(picImgList);
        }
        return powerStationDO;
    }

    @Override
    @Transactional
    public BaseResultDTO addPowerStation(PowerStationDO powerStationDO,MultipartFile[] file,UserDO loginUser) throws Exception{
    	BaseResultDTO result = new BaseResultDTO();
    	if(null != powerStationDO.getRateInfoId()){
    		RateInfoDO doamin = new RateInfoDO();
    		doamin.setPk_RateInformation(powerStationDO.getRateInfoId());
    		RateInfoDO info = rateInfoMapper.getRateInfoById(doamin);
    		if(null == info){
    			result.setSuccess(false);
    			result.setErrorDetail("费率不存在，请重新输入");
    			return result;
    		}
    	}
        if(powerStationMapper.insertPowerStation(powerStationDO) <= 0){
        	result.setSuccess(false);
			result.setErrorDetail("新增充电点失败");
			return result;
        }
        
        //上传充电点图片
        if(null != file && file.length > 0){
   		 String results = multipartFileService.saveMultiFile(file, WanmaConstants.POWERSTATION_PIC, powerStationDO.getPowerstationId()+"", false, loginUser);
            if (!WanmaConstants.PROCESS_STATUS_OK.equals(results)) {
             	 result.setSuccess(false);
                 result.setResultCode(ResultCodeConstants.FAILED);
                 result.setErrorDetail("上传图片失败！");
                 return result;
            } 
       }
        result.setSuccess(true);
        return result;
    }

    @Override
    @Transactional
    public BaseResultDTO modifyPowerStation(final PowerStationDO powerStationDO, List<ElectricPileDO> infoList,MultipartFile[] file,UserDO loginUser,String listImgUrl)  throws Exception{
        BaseResultDTO result = new BaseResultDTO();
        try {
            if (powerStationMapper.updatePowerStation(powerStationDO) < 0) {
            	result.setSuccess(false);
    			result.setErrorDetail("修改充电点失败");
    			return result;
            }
            //编辑充电点图片
            if(StringUtils.isNotEmpty(listImgUrl)){
            	List<String> delUrlList = Arrays.asList(listImgUrl.split(";"));
                if(CollectionUtils.isNotEmpty(delUrlList)){
                	multipartFileService.deleteMulti(delUrlList, WanmaConstants.POWERSTATION_PIC, powerStationDO.getPowerstationId()+"", loginUser);
                }
            }
            if(null != file && file.length > 0){
        		 String results = multipartFileService.saveMultiFile(file, WanmaConstants.POWERSTATION_PIC, powerStationDO.getPowerstationId()+"", false, loginUser);
 	            if (!WanmaConstants.PROCESS_STATUS_OK.equals(results)) {
 	             	 result.setSuccess(false);
 	                 result.setResultCode(ResultCodeConstants.FAILED);
 	                 result.setErrorDetail("上传图片失败！");
 	                 return result;
 	            } 
            }
            
            if(CollectionUtils.isNotEmpty(infoList)){
       		 loginUser.setModifier(powerStationDO.getModifier());
                BaseResultDTO dto = electricPileService.bandElectricPile(infoList, WanmaConstants.POWER_STATION_BIND_ELECTRIC_PILE, powerStationDO.getPowerstationId(), loginUser);
                if (!dto.isSuccess()) {
                    result.setSuccess(false);
                    result.setErrorDetail(dto.getErrorDetail());
                    return result;
                }
         	}
            cachedThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					   String htmlUrl = GlobalSystem.getConfig("htmlRoot");
					   String powerStationId = powerStationDO.getPowerstationId().toString();
					   try {
						LOGGER.error(htmlUrl + "/cdzts/powerStationPush.do?powerStationId=" + powerStationId + "&t="+ ApiUtil.getToken());
						String status = HttpsUtil.getResponseParam(htmlUrl + "/cdzts/powerStationPush.do?powerStationId=" + powerStationId + "&t="
									+ ApiUtil.getToken(), "status");
						if("2004".equals(status)){
							LOGGER.error(powerStationId+"推送失败");
						}else{
							LOGGER.error(powerStationId+"推送成功");
						}
					} catch (Exception e) {
						LOGGER.error(powerStationId+"推送失败");
					}
				}
			});
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrorDetail("修改充电桩信息失败");
            LOGGER.error("PowerStationServiceImpl called modifyPowerStation failed", e);
        }
        return result;
    }

    @Override
    @Transactional
    public boolean removePowerStation(Long powerStationId) throws Exception{
        if (powerStationMapper.deletePowerStationById(powerStationId) > 0) {
            Map<String, Long> map = new HashMap<String, Long>();
            map.put("powerStationId", powerStationId);
            if (electricPileMapper.updateElectricByForeignKey(map) >= 0) {
            	// 删除充电点充电范围
            	List<Long> psIds = new ArrayList<Long>();
            	psIds.add(powerStationId);
            	companyChargeRelaMapper.deleteByPsId(psIds);
                return true;
            }
        }
        return false;
    }

	@Override
	public List<PowerStationDO> getAllPowerStationList(PowerStationDO powerStationDO) {
		 List<PowerStationDO> powerStationList = powerStationMapper.selectPowerStationList(powerStationDO);
		 Map<Long,PowerStationDO> powerStationMap = new HashMap<Long,PowerStationDO>();
	        for (PowerStationDO powerStationDO2 : powerStationList) {
	        	powerStationMap.put(powerStationDO2.getPowerstationId(),powerStationDO2);
			}

	        if(CollectionUtils.isNotEmpty(powerStationMap.keySet())){
	        	 List<PowerStationDO> electricPileCount = electricPileMapper.countByPowerStationIds(new ArrayList<>(powerStationMap.keySet()),null);

	             for (PowerStationDO count : electricPileCount) {
	                 PowerStationDO powerStation = powerStationMap.get(count.getPowerstationId());
	                 if (powerStation != null) {
	                     powerStation.setElectricPileNum(count.getElectricPileNum());
	                 }
	             }
	        }
	        return powerStationList;
	}

	@Override
	public List<Map<String, String>> getPowerStationForList(PowerStationDO powerStationDO) {
		return powerStationMapper.getPowerStationForList(powerStationDO);
	}
	

}

package com.wanma.ims.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.PowerStationDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;

/**
 * <p>充电点接口<p>
 * @author zhangchunyan
 * @date 2017-6-23 
 */
public interface PowerStationService {

	/**
	 * 充电点-统计,根据参数统计充电点信息
	 * @param powerStationDO
	 * @return list
	 */
	public Long countPowerStationList(PowerStationDO powerStationDO,UserDO loginUser);
	
	/**
	 * 充电点-查询,根据参数查询充电点信息
	 * @param powerStationDO
	 * @return list
	 */
	public List<PowerStationDO> getPowerStationList(PowerStationDO powerStationDO,UserDO loginUser);
	
	/**
	 * 充电点-查询,根据参数查询充电点信息 不包括权限及桩总数
	 * @param powerStationDO
	 * @return list
	 */
	public List<PowerStationDO> getAllPowerStationList(PowerStationDO powerStationDO);
	
	/**
	 * 充电点-查询,根据ID查询充电点信息
	 * @param powerStationId
	 * @return PowerStationDO
	 */
	public PowerStationDO getPowerStationById(Long powerStationId);
	
	/**
	 * 充电点-新增
	 * @param powerStationDO
	 * @return boolean
	 */
	public BaseResultDTO addPowerStation(PowerStationDO powerStationDO,MultipartFile[] listFile,UserDO loginUser) throws Exception;
	
	/**
	 * 充电点-编辑
	 * @param powerStationDO
	 * @return boolean
	 */
	public BaseResultDTO modifyPowerStation(PowerStationDO powerStationDO, List<ElectricPileDO> infoList,MultipartFile[] file,UserDO loginUser,String listImgUrl) throws Exception;
	
	/**
	 * 充电点-删除
	 * @param powerStationId
	 * @return boolean
	 */
	public boolean removePowerStation(Long powerStationId) throws Exception;
	/**
	 * 优惠券 活动 省市区联动 获取电站
	 * @param powerStationDO
	 * @return List
	 */
	public List<Map<String, String>> getPowerStationForList(
			PowerStationDO powerStationDO);
}

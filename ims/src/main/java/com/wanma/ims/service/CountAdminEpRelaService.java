package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.CountAdminEpRelaDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;

/**
 * 
 * 数据权限<管理员与充电桩数据操作权限> 
 * @author zhangchunyan
 * @date 2017-6-26
 */
public interface CountAdminEpRelaService {
    
	
	/**
	 * 根据管理员查询电桩-统计
	 * @param adminId:当前管理员ID
	 * @param electricPileCode:桩编码
	 * @return Long
	 */
	public Long countAdminEPList(CountAdminEpRelaDO countAdminEPRelaDO);
	
	/**
	 * 根据管理员查询电桩
	 * @param adminId:当前管理员ID
	 * @param electricPileCode:桩编码
	 * @return list
	 */
	public List<CountAdminEpRelaDO> getCountAdminEPList(CountAdminEpRelaDO countAdminEPRelaDO);
	
	/**
	 * 根据当前登录人添加电桩
	 * @param loginId:当前登录人ID
	 * @param adminId:当前管理员ID
	 * @param electricPileCode:桩编码
	 * @return boolean
	 */
	public BaseResultDTO addAdminEpByLoginId(UserDO loginUser,Long adminId,String electricPileCode);
	
	/**
	 * 根据当前登录人删除电桩
	 * @param loginId:当前登录人ID
	 * @param adminId:当前管理员ID
	 * @param electricPileCode:桩编码
	 * @return boolean
	 */
	public BaseResultDTO removeAdminEpByLoginId(Long adminId,List<Long> epIds,String creator);
	
	
	/**
	 * 根据当前登录人添加电桩(批量)
	 * @param loginId:当前登录人ID
	 * @param adminId:当前管理员ID
	 * @param powerStationId:充电站ID
	 * @return boolean
	 */
	public BaseResultDTO addAdminEpByPsId(UserDO loginUser,Long adminId,Long powerStationId);
	
	/**
	 * 获取当前登录人的桩权限
	 * @param loginId:当前登录人ID
	 * @return boolean
	 */
	public List<Long> getElectricPileIdsByLoginId(Long loginId);
	
	/**
	 * 获取当前登录人的充电点权限
	 * @param loginId:当前登录人ID
	 * @return boolean
	 */
	public List<Long> getPowerStationIdsByLoginId(Long loginId);
	
	
	
}

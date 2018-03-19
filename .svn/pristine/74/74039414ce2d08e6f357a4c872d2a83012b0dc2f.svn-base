package com.wanma.ims.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wanma.ims.common.domain.BomListDO;
import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.TypeSpanDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BaseResultDTO;


/**
 * 产品型号
 */
public interface TypeSpanService {
	/**
	 * 获取产品型号数量
	 * @param typeSpanDO
	 * @return
	 */
	long getTypeSpanCount(TypeSpanDO typeSpanDO);
	/**
	 * 获取产品型号列表
	 * @param typeSpanDO
	 * @return
	 */
	List<TypeSpanDO> getTypeSpanList(TypeSpanDO typeSpanDO);
	/**
	 * 获取产品型号列表
	 * @param typeSpanDO
	 * @return
	 */
	int checkTsTypeSpan(String tsTypeSpan);
	/**
	 * 新增产品型号
	 * @param typeSpanDO
	 * @return
	 */
	boolean addTypeSpan(TypeSpanDO typeSpanDO);
	/**
	 * 根据产品型号主键获取
	 * @param typeSpanDO
	 * @return
	 */
	TypeSpanDO getTypeSpanById(TypeSpanDO typeSpanDO);
	/**
	 * 根据产品型号主键获取
	 * @param typeSpanDO
	 * @return
	 */
	List<BomListDO> getBomList(TypeSpanDO typeSpanDO);
	/**
	 * 修改产品型号
	 * @param typeSpanDO
	 * @return
	 */
	boolean updateTypeSpan(TypeSpanDO typeSpanDO);
	/**
	 * 修改产品型号
	 * @param electricPileDO
	 * @return
	 */
	long selectPileListCount(ElectricPileDO electricPileDO);
	List<Map<String, Object>> selectPileList(ElectricPileDO electricPileDO);
	/**
	 * 升级产品型号
	 * @param request 
	 * @param electricPileDO
	 * @return
	 */
	BaseResultDTO updateEpVision(String pkTypeSpanId, String pkBomListId, HttpServletRequest request,UserDO loginUser);
	List<Map<String, Object>> getCheckUpList(String item);
	List<Map<String, String>> getBomIdUpgrade(String items);
	/**
	 * 新增bomlist
	 * @param bomListDO
	 * @return
	 */
	boolean insertBomList(BomListDO bomListDO);
	List<TypeSpanDO> getTypeSpanForList(TypeSpanDO typeSpanDO);
	
	
	

}

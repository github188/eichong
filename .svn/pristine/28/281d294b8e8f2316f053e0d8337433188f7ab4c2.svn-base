package com.wanma.dao;

import java.util.Map;

import com.wanma.model.TblRateInformation;;


/**
 * @Description:费率信息DAO
 * @author Dongyu
 * @createTime 2015/12/08 12:43
 * @updater 
 * @updateTime    
 * @version v1.0
 */
public interface TblRateInformationMapper {

	/**
	 * 按照主键id查找TblRateInformation
	 */
	public TblRateInformation getById(Long pkRateInformation);

	public Double selectMinPriceByPsId(Integer pkPowerstation);
	
//	public List<TblRateInformation> findAll(TblRateInformation t);
	
	/**
	 * 根据电桩的费率id查询充电费以及服务费信息
	 * @param params
	 * @return
	 */
	public TblRateInformation getPriceById(Map<String, Object> params);

	/**
	 * 根据电桩编号以及公司标识查询电桩个性化费率
	 * @param params
	 * @return
	 */
	TblRateInformation queryByRateInfo(Map<String, Object> params);


	/**
	 * 根据电桩编号查询充电费以及服务费信息
	 * @param params
	 * @return
	 */
	 TblRateInformation queryByPriceId(Map<String, Object> params);


	
}

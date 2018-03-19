package com.wanma.ims.mapper;


import java.util.List;

import com.wanma.ims.common.domain.IntegralDO;
import com.wanma.ims.common.domain.bo.IntegralStatisticsBO;

/**
 * 积分表 
 * @author bingo
 * @date 2017-08-11
 */
public interface IntegralMapper {
	
	public Long getIntegralCount(IntegralDO integral);
	
	public List<IntegralDO> getIntegralList(IntegralDO integral);
	
	public Long addIntegral(IntegralDO integral);
	
	public int modifyIntegral(IntegralDO integral);
	
	public IntegralStatisticsBO doIntegralStatistics(IntegralDO integral);
	
	/** 通过pkId或userId查找积分 */
	public IntegralDO getIntegralById(IntegralDO integral);
}
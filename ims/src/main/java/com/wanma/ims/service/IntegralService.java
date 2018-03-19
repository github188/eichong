package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.IntegralDO;
import com.wanma.ims.common.domain.bo.IntegralStatisticsBO;
import com.wanma.ims.common.dto.BaseResultDTO;


/**
 * 积分
 * @author bingo
 * @date 2017-08-14
 */
public interface IntegralService {
	/**
	* <p>Description: 获取积分数量</p>
	* @param
	* @author bingo
	* @date 2017-08-14
	 */
	public Long getIntegralCount(IntegralDO integral);
	
	/**
	* <p>Description: 获取积分数据</p>
	* @param
	* @author bingo
	* @date 2017-08-14
	 */
	public List<IntegralDO> getIntegralList(IntegralDO integral);
	
	/**
	* <p>Description: 修改积分</p>
	* @param
	* @author bingo
	* @date 2017-08-14
	 */
	public BaseResultDTO modifyIntegral(IntegralDO integral) throws Exception;
	
	/**
	* <p>Description: 积分统计</p>
	* @param
	* @author bingo
	* @date 2017-08-17
	 */
	public IntegralStatisticsBO doIntegralStatistics(IntegralDO integral) throws Exception;
}

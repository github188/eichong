package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.IntegralDetailsDO;
import com.wanma.ims.common.domain.OrderDO;
import com.wanma.ims.common.domain.bo.IntegralActivityAndRulesBO;
import com.wanma.ims.common.domain.bo.IntegralBO;
import com.wanma.ims.common.dto.BaseResultDTO;


/**
 * 积分明细
 * @author bingo
 * @date 2017-08-14
 */
public interface IntegralDetailsService {
	/**
	* <p>Description: 获取积分明细数量</p>
	* @param
	* @author bingo
	* @date 2017-08-14
	 */
	public Long getIntegralDetailsCount(IntegralDetailsDO integralDetails);
	
	/**
	* <p>Description: 获取积分明细数据</p>
	* @param
	* @author bingo
	* @date 2017-08-14
	 */
	public List<IntegralDetailsDO> getIntegralDetailsList(IntegralDetailsDO integralDetails);
	
	/**
	* <p>Description: 增加积分明细数据</p>
	* @param
	* @author bingo
	* @date 2017-08-14
	 */
	public BaseResultDTO addIntegralDetails(IntegralBO integralBO, IntegralActivityAndRulesBO defaultActivity)  throws Exception;

	/**
	 * <p>Description: 通过订单处理积分</p>
	 * @param
	 * @author bingo
	 * @date 2017-12-8
	 */
	public void doIntegralForBatch(OrderDO orderDO, List<IntegralActivityAndRulesBO> activityList);
}

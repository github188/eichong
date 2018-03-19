package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.FinAccountSplitDetailsDO;
import com.wanma.ims.common.domain.OrderDO;


/**
 * 分账明细
 * @author bingo
 * @date 2017-11-27
 */
public interface FinAccountSplitDetailsService {
	/**
	 * <p>Description: 获取分账明细数量</p>
	 * @param
	 * @author bingo
	 * @date 2017-11-27
	 */
	public Long getFinAccountSplitDetailsCount(FinAccountSplitDetailsDO finAccountSplitDetails);

	/**
	 * <p>Description: 获取分账明细列表</p>
	 * @param
	 * @author bingo
	 * @date 2017-11-27
	 */
	public List<FinAccountSplitDetailsDO> getFinAccountSplitDetailsList(FinAccountSplitDetailsDO finAccountSplitDetails);

	/**
	 * <p>Description: 新增分账明细</p>
	 * @param
	 * @author bingo
	 * @date 2017-11-27
	 */
//	public BaseResultDTO addFinAccountSplitDetails(FinAccountSplitDetailsDO finAccountSplitDetails, UserDO loginUser) throws Exception;

	/**
	 * 通过订单处理分账明细
	 * @param orderDO
	 * @return
	 * @throws Exception
	 */
	public void doFinAccountSplitDetailsForBatch(OrderDO orderDO);
}

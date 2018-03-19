package com.wanma.ims.service;

import com.wanma.ims.common.domain.JpushRecordDO;
import com.wanma.ims.common.domain.OrderDO;


/**
 * 推送记录
 * @author bingo
 * @date 2017-12-15
 */
public interface JPushRecordService {

	/**
	 * <p>Description: 通过订单推送记录</p>
	 * @param
	 * @author bingo
	 * @date 2017-12-15
	 */
	public void doJPushRecordForBatch(OrderDO orderDO);

	/**
	 * <p>Description: 获取最大的推送记录id</p>
	 * @param
	 * @param jpushRecordDO 推送记录
	 * @author bingo
	 * @date 2018-1-4
	 */
	public String getMaxChargingOrderId(JpushRecordDO jpushRecordDO);
}

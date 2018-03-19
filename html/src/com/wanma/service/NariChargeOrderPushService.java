package com.wanma.service;

public interface NariChargeOrderPushService {
	
	/**
	 * 南京南瑞充电订单推送
	 * @param startTime
	 * @param endTime
	 * @return 
	 */
	public int nariChargeOrderPush(String startTime, String endTime) throws Exception;


}

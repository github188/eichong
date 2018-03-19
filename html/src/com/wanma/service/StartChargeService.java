package com.wanma.service;


/**
 * 角色业务处理接口
 * 
 * @version V1.0
 * @author gx
 * @date 
 */
public interface StartChargeService {
	
	/**
	 * 充电指令下发
	 * @param org  第三方标识
	 * @param outUserId  第三方用户id
	 * @param pileNo  爱充平台电桩编码
	 * @param gunId  枪编码
	 * @param userAmount  预充金额
	 * @param outOrderId  第三方订单编号
	 * @return code=1指令下发成功，其余均为失败
	 * 
	 */

	int startCharge(int org, String outUserId, String pileNo, int gunId,
			int userAmount, String outOrderId);
}

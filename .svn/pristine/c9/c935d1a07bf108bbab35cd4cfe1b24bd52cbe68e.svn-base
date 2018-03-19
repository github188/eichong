/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service;

import java.util.List;
import java.util.Map;

import com.wanma.model.ChargeOrderInfo;
import com.wanma.model.TblChargeinfo;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface ChargeService {

	/**
	 * 获取实时充电数据
	 */
	public TblChargeinfo getChangeInfo(TblChargeinfo tblChargeinfo);

	/**
	 * 新的获取实时充电数据接口
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getChangeInfoN(int userId);
	
	/**
	 * 结束充电返回数据
	 * 
	 * @param tblChargeinfo
	 * @return
	 */
	public List<ChargeOrderInfo> finishCharge(TblChargeinfo tblChargeinfo);

	/**
	 * 判断是否结束充电正常
	 */
	public boolean getChangeState(String machineCode);

	/**
	 * 更改电桩状态
	 */
	public void changeElectricState(TblChargeinfo tblChargeinfo);

	/**
	 * @Title: selectMoney
	 * @Description: 获取用户余额，冻结金额，电桩最小充电金额
	 * @param params
	 * @return
	 */
	public Boolean selectIsEnoughMoney(Map<String, Object> params);

	/**
	 * 根据用户id获取该用户未支付的订单数
	 * @param userId
	 * @return
	 */
	public int getUnpayOrder(int userId);
	/**
	 * @Title: updateAccountbalance
	 * @Description: 更新用户账户余额,充电订单状态为支付成功
	 * @param params
	 * @return
	 */
	public Map<String, Object> updateAccountbalance(Map<String, Object> params);
	
	/**
	 * 根据用户id 查询充电订单列表
	 * @param userId
	 * @return
	 */
	public List<Map<String, Object>> getChargeOrderList(Map<String, Object> params);
	
	/**
	 * 获取直流桩在充电前的自检状态
	 * @param params
	 * 			epCode 电桩编号 ，ephCode 枪口编号
	 * @return
	 */
	public int getDCSelfCheckStatus(Map<String, String> params);

	/**
	 * 获取订单详情
	 * @param coId
	 * @return
	 */
	public Map<String, Object> chargeOrderDetail(int coId);
}

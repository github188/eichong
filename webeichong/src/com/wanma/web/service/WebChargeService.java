/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service;

import java.util.List;

import com.wanma.model.ChargeOrderInfo;
import com.wanma.model.TblChargeinfo;

/**
 * 反馈信息业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface WebChargeService {

	/**
	 * 获取实时充电数据
	 */
	public List<TblChargeinfo> getChangeInfo(TblChargeinfo tblChargeinfo);
	/**
	 * 结束充电返回数据
	 * @param tblChargeinfo
	 * @return
	 */
	public List<ChargeOrderInfo> finishCharge(TblChargeinfo tblChargeinfo);
	/**
	 * 判断是否结束充电正常
	 */
	public boolean getChangeState(String machineCode) ;
	/**
	 * 更改电桩状态
	 */
	public void changeElectricState(TblChargeinfo tblChargeinfo);
}

/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service;

import com.wanma.model.TblChargingrecord;

/**
 * @Description: 充电记录业务处理接口
 * @author songjf
 * @createTime：2015-4-10 下午02:03:19
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppChargingrecordService {

	/**
	 * @Title: insertBeginRecord
	 * @Description: 新增开始充电记录
	 * @param params
	 * @return
	 */
	public int insertBeginRecord(TblChargingrecord tblChargingrecord);

	/**
	 * @Title: insertEndRecord
	 * @Description: 新增结束充电记录 根据流水号更新数据
	 * @param params
	 * @return
	 */
	public int insertEndRecord(TblChargingrecord tblChargingrecord);

	/**
	 * @Title: updatechReCode
	 * @Description: 根据交易流水号更新充电订单编号
	 * @param params
	 * @return
	 */
	public int updatechReCode(TblChargingrecord tblChargingrecord);

	/**
	 * @Title: getByTranNumber
	 * @Description: (根据流水号获取充电记录信息
	 * @param params
	 * @return
	 */
	public TblChargingrecord getByTranNumber(
			java.lang.String chreTransactionnumber);

	/**
	 * @Title: selectByChreCode
	 * @Description: (根据充电订单获取充电记录)
	 * @param params
	 * @return
	 */
	public TblChargingrecord selectByChreCode(String pkChargingOrder);
	
	/**
	 * 根据用户id判断该用户是否正在充电
	 * 根据充电记录中结束时间是否为默认值来判断，有更好的方式的话抛弃此方案
	 * @param userId
	 * @return
	 */
	public int ischarging(int userId);

}

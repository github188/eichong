package com.wanma.app.service;

import java.util.Map;

import com.wanma.model.TblChargingfaultrecord;

/**
 * @Description: 充电故障记录业务处理接口
 * @author songjf
 * @createTime：2015-4-14 下午08:56:22
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppChargingfaultrecordService {

	/**
	 * @Title: insertFaultRecord
	 * @Description: 新增充电故障记录
	 * @param params
	 * @return
	 */
	public int insertFaultRecord(TblChargingfaultrecord tblChargingfaultrecord);

	/**
	 * @Title: findCountByTranum
	 * @Description: 根据流水号获取故障数量 ,判断是否故障停止
	 * @param params
	 * @return
	 */
	public int findCountByTranum(String cfreTransactionnumber);

}

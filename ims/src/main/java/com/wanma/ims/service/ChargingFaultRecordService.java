package com.wanma.ims.service;


import com.wanma.ims.common.domain.ChargingFaultRecord;

import java.util.List;

/**
 * ccu充电故障记录
 */
public interface ChargingFaultRecordService {

	/**
	 * 获取充电故障列表
	 * @param chargingfaultrecord
	 * @return
	 */
	long getChargingFaultRecordCount(ChargingFaultRecord chargingfaultrecord);

	List<ChargingFaultRecord> getChargingFaultRecordList(ChargingFaultRecord chargingfaultrecord);
}

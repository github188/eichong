package com.wanma.ims.service.impl;

import com.wanma.ims.common.domain.ChargingFaultRecord;
import com.wanma.ims.mapper.ChargingFaultRecordMapper;
import com.wanma.ims.service.ChargingFaultRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("chargingFaultRecordService")
public class ChargingFaultRecordServiceImpl implements ChargingFaultRecordService{

	@Autowired
	private ChargingFaultRecordMapper chargingFaultRecordMapper;


	@Override
	public long getChargingFaultRecordCount(ChargingFaultRecord chargingfaultrecord) {
		return chargingFaultRecordMapper.getChargingFaultRecordCount(chargingfaultrecord);
	}

	@Override
	public List<ChargingFaultRecord> getChargingFaultRecordList(ChargingFaultRecord chargingfaultrecord) {
		return chargingFaultRecordMapper.getChargingFaultRecordList(chargingfaultrecord);
	}
}

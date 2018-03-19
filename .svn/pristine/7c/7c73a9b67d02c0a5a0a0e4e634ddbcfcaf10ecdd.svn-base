package com.wanma.app.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppChargingfaultrecordMapper;
import com.wanma.app.service.AppChargingfaultrecordService;
import com.wanma.model.TblChargingfaultrecord;

/**
 * @Description: 充电故障记录业务处理实现类
 * @author songjf
 * @createTime：2015-4-14 下午08:56:22
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service("chargingfaultrecordService")
public class AppChargingfaultrecordServiceImpl implements
		AppChargingfaultrecordService {
	// 充电故障记录操作dao
	@Autowired
	private AppChargingfaultrecordMapper faultrecordMapper;

	/**
	 * @Title: insertFaultRecord
	 * @Description: 新增充电故障记录
	 * @param params
	 * @return
	 */
	@Override
	public int insertFaultRecord(TblChargingfaultrecord tblChargingfaultrecord) {
		tblChargingfaultrecord.setCfreCreatedate(new Date());
		tblChargingfaultrecord.setCfreUpdatedate(new Date());
		return faultrecordMapper.insertFaultRecord(tblChargingfaultrecord);
	}

	/**
	 * @Title: findCountByTranum
	 * @Description: 根据流水号获取故障数量 ,判断是否故障停止
	 * @param params
	 * @return
	 */
	@Override
	public int findCountByTranum(String cfreTransactionnumber) {
		return faultrecordMapper.findCountByTranum(cfreTransactionnumber);
	}

}

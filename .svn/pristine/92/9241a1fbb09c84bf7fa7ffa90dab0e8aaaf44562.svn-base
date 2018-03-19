package com.wanma.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.TblChargingrecord;
import com.wanma.web.dao.WebChargingrecordMapper;
import com.wanma.web.service.WebChargingrecordService;

/**
 * @Description: 充电记录业务处理实现类
 * @author songjf
 * @createTime：2015-4-10 下午02:03:19
 * @updator：
 * @updateTime：
 * @version：V1.0
 */

@Service("chargingrecordService")
public class WebChargingrecordServiceImpl implements WebChargingrecordService {
	// 充电记录操作dao
	@Autowired
	private WebChargingrecordMapper chargingrecordMapper;

	/**
	 * @Title: insertBeginRecord
	 * @Description:新增开始充电记录
	 * @param params
	 * @return
	 */
	@Override
	public int insertBeginRecord(TblChargingrecord tblChargingrecord) {
		return chargingrecordMapper.insertBeginRecord(tblChargingrecord);
	}

	/**
	 * @Title: insertEndRecord
	 * @Description: 新增结束充电记录 根据流水号更新数据
	 * @param params
	 * @return
	 */
	@Override
	public int insertEndRecord(TblChargingrecord tblChargingrecord) {
		return chargingrecordMapper.insertEndRecord(tblChargingrecord);
	}

	/**
	 * @Title: updatechReCode
	 * @Description: 更新充电订单编号
	 * @param params
	 * @return
	 */
	@Override
	public int updatechReCode(TblChargingrecord tblChargingrecord) {
		return chargingrecordMapper.updatechReCode(tblChargingrecord);
	}

	/**
	 * @Title: getByTranNumber
	 * @Description: (根据流水号获取充电记录信息
	 * @param params
	 * @return
	 */
	@Override
	public TblChargingrecord getByTranNumber(String chreTransactionnumber) {
		return chargingrecordMapper.getByTranNumber(chreTransactionnumber);
	}

	/**
	 * @Title: selectByChreCode
	 * @Description: (根据充电订单获取充电记录)
	 * @param params
	 * @return
	 */
	@Override
	public TblChargingrecord selectByChreCode(String pkChargingOrder) {
		return chargingrecordMapper.selectByChreCode(pkChargingOrder);
	}

	/**
	 * 根据用户id判断该用户是否正在充电
	 * 根据实时电桩记录中的用户id来判断是否在充电
	 * @param userId
	 * @return
	 */
	public int ischarging(int userId){
		return chargingrecordMapper.ischarging(userId);
	}
}

package com.wanma.app.dao;

import java.util.List;
import java.util.Map;

import com.wanma.model.TblChargingfaultrecord;

/**
 * @Description: 充电故障记录操作dao接口
 * @author songjf
 * @createTime：2015-4-14 下午08:52:22
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface AppChargingfaultrecordMapper {
	public final static String PREFIX = AppChargingfaultrecordMapper.class
			.getName();

	public TblChargingfaultrecord get(java.lang.Integer pkChargingfaultrecord);

	public <K, V> Map<K, V> findOne(java.lang.Integer pkChargingfaultrecord);

	public <T, K, V> List<T> find(Map<K, V> params);

	/**
	 * @Title: insertFaultRecord
	 * @Description: 新增充电故障记录
	 * @param params
	 * @return
	 */
	public int insertFaultRecord(TblChargingfaultrecord tblChargingfaultrecord);

	public int update(TblChargingfaultrecord tblChargingfaultrecord);

	public int delete(java.lang.Integer pkChargingfaultrecord);

	/**
	 * @Title: findCountByTranum
	 * @Description: 根据流水号获取故障数量 ,判断是否故障停止
	 * @param params
	 * @return
	 */
	public int findCountByTranum(String cfreTransactionnumber);

}

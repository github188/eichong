package com.wanma.app.service;

import java.util.Map;

import com.wanma.model.TblElectricpilehead;

/**
 * @Description: 枪头业务处理接口
 * @author songjf
 * @createTime：2015-3-13 下午05:04:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public interface TblElectricpileheadService {

	/**
	 * @Title: updateHeadState
	 * @Description: 获取用户余额，冻结金额，电桩最小充电金额
	 * @param params
	 * @return
	 */
	public int updateHeadState(Map<String, Object> params);

	/**
	 * @Title: selectHeadInfo
	 * @Description: 根据电桩id和枪口编号获取枪口信息
	 * @param params
	 * @return
	 */
	public TblElectricpilehead selectHeadInfo(Map<String, Object> params);
}

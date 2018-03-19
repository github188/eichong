package com.wanma.app.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.TblElectricpileheadMapper;
import com.wanma.app.service.TblElectricpileheadService;
import com.wanma.model.TblElectricpilehead;

/**
 * @Description: 枪头业务处理实现类
 * @author songjf
 * @createTime：2015-3-13 下午05:04:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service("electricpileheadService")
public class TblElectricpileheadServiceImpl implements
		TblElectricpileheadService {
	/* 枪头操作dao */
	@Autowired
	private TblElectricpileheadMapper electricpileheadMapper;

	/**
	 * @Title: updateHeadState
	 * @Description: 获取用户余额，冻结金额，电桩最小充电金额
	 * @param params
	 * @return
	 */
	@Override
	public int updateHeadState(Map<String, Object> params) {
		return electricpileheadMapper.updateHeadState(params);
	}

	/**
	 * @Title: selectHeadInfo
	 * @Description: 根据电桩id和枪口编号获取枪口信息
	 * @param params
	 * @return
	 */
	@Override
	public TblElectricpilehead selectHeadInfo(Map<String, Object> params) {
		return electricpileheadMapper.selectHeadInfo(params);
	}

}

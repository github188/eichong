package com.wanma.app.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.TblRateinformationMapper;
import com.wanma.app.service.TblRateinformationService;
import com.wanma.model.TblRateinformation;

/**
 * @Description: 费率信息业务处理实现类
 * @author songjf
 * @createTime：2015-4-10 下午04:00:23
 * @updator：
 * @updateTime：
 * @version：V1.0
 */

@Service("rateinformationService")
public class TblRateinformationServiceImpl implements TblRateinformationService {
	// 费率信息操作dao
	@Autowired
	private TblRateinformationMapper rateinformationMapper;

	/**
	 * @Title: findRateInfo
	 * @Description: 根据电桩id获取电桩费率信息
	 * @param params
	 * @return
	 */
	@Override
	public TblRateinformation findRateInfo(Map<String, Object> params) {
		return rateinformationMapper.findRateInfo(params);
	}

	@Override
	public TblRateinformation getById(Long pkRateInformation) {
		TblRateinformation rateInformation = rateinformationMapper.getById(pkRateInformation);
		return rateInformation;
	}

}

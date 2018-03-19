package com.wanma.ims.mapper;


import java.util.List;

import com.wanma.ims.common.domain.RateInfoDO;

/**
 * 费率管理
 * @author bingo
 * @date 2017-06-26
 */
public interface RateInfoMapper {
	
	public Long getRateInfoCount(RateInfoDO rateInfo);
	
	public List<RateInfoDO> getRateInfoList(RateInfoDO rateInfo);
	
	public int addRateInfo(RateInfoDO rateInfo);
	
	public int modifyRateInfo(RateInfoDO rateInfo);
	
	public int removeRateInfo(Integer pk_RateInformation);
	
	public RateInfoDO getRateInfoById(RateInfoDO rateInfo);
}
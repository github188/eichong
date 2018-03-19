package com.wanma.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.log.SystemControllerLog;
import com.wanma.dao.CmsFeeLimitMapper;
@Service
public class CmsFeeLimitServiceImpl {

	@Autowired
	private CmsFeeLimitMapper cmsFeeLimitMapper;
	
	/**
	 * 查出城市费率数目
	 * @param params
	 * @return
	 */	
	public long searchServiceLimitCount(Map<String,Object> params){
		return cmsFeeLimitMapper.searchServiceLimitCount(params);
	}
	/**
	 * 查询出城市费率列表
	 * @param params
	 * @return
	 */
	public List<HashMap<String,Object>> searchServiceLimitList(Map<String,Object> params){
	
		return cmsFeeLimitMapper.searchServiceLimitList(params);
	};
	
	public List<HashMap<String,Object>>    findServiceLimit(Map<String,Object> params){
		return cmsFeeLimitMapper.findServiceLimit(params);
	}
	/**
	 * 更改城市汇率 
	 * @param params
	 */
	@SystemControllerLog(description = "更改城市汇率 ")
	public void  updateServiceLimitById (Map<String,Object> params){
		cmsFeeLimitMapper.updateServiceLimitById(params);
	};
	
	public boolean checkMaxHighFeel(HashMap<String,Object> params){
		return cmsFeeLimitMapper.getMaxHighCheckCount(params) == 0;
	}
}

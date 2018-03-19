package com.wanma.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CmsFeeLimitMapper {
	//城市费率数目
	 public long searchServiceLimitCount(Map<String,Object> params);	
	//查询出城市费率列表
	public List<HashMap<String,Object>> searchServiceLimitList(Map<String,Object> params);
	//根据城市ID查出城市费率详情
	public List<HashMap<String,Object>> findServiceLimit(Map<String,Object> params);
	//更改城市汇率 
	public void  updateServiceLimitById (Map<String,Object> params);
	
	public int getMaxHighCheckCount(HashMap<String, Object> params);
}

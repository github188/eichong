package com.wanma.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CmsPowerRateService {
	
	public int getPowerrateListCount();
	
	public void updatePowerrate(Map<String,Object> params);
	
	public List<HashMap<String,Object>>  getPowerrateList(Map<String,Object> params);

}

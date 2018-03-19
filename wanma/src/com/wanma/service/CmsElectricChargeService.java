package com.wanma.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CmsElectricChargeService {
	
	public int getElectricChargeCount();
	
	public void updateElectricCharge(Map<String,Object> params);
	
	public List<HashMap<String,Object>>  getElectricCharge(Map<String,Object> params);

	public void updateRateInfoByProvince(Map<String, Object> params);
}

package com.wanma.service;


public interface PileFilterService {

	boolean checkPileIsOk(String org, String stubId);
	/**
	 * 根据第三方组织机构编号检查该第三方可否充电
	 * @param org
	 * @return
	 */
	boolean checkOk(String org,String stubId);

	
}

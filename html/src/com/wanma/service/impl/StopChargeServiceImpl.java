/** 
 * FileName RoleServiceImpl.java
 * 
 * Version 1.0
 *
 * Create by gx 2016/7/1
 * 
 * Copyright 2000-2001 wanma. All Rights Reserved.
 */
package com.wanma.service.impl;

import org.springframework.stereotype.Service;

import com.wanma.service.StopChargeService;
import com.wanma.support.common.WanmaConstants;


/**
 * FileName RoleServiceImpl.java
 * 
 * Version 1.0
 * 
 * Create by gx 
 * 
 * 角色业务处理类
 */
@Service("stopChargeService")
public class StopChargeServiceImpl implements StopChargeService {

	
	
	@Override
	public int stopCharge(int org,String outUserId,String pileNo,int gunId) {

		try {
			int rtCode = WanmaConstants.cs.stopChange(pileNo,
					gunId, org, outUserId, "");// 下发结束充电命令

			return rtCode;
		} catch (Exception e) {
			e.printStackTrace();
			return 1000;
		}
	}
	
}

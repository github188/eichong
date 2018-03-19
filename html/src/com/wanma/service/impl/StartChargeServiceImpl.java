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

import com.wanma.service.StartChargeService;
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
@Service("startChargeService")
public class StartChargeServiceImpl implements StartChargeService {


	@Override
	public int startCharge(int org,String outUserId,String pileNo,int gunId,int AmtMoney,String outOrderId) {

		try {

			int rtCode = WanmaConstants.cs.startChange(org, outUserId,
					pileNo,gunId, new Short("1"),
					AmtMoney, 2, "", "", 0,
					outOrderId);
			// 下发充电命令 // LOGGER.info("下发充电命令结束！");
			return rtCode;
		} catch (Exception e) {
			e.printStackTrace();
			return 1000;
		}
	}
	
	
	

}

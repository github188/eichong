package com.wanma.ims.common.convert;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.wanma.ims.common.domain.CountAdminEpRelaDO;
import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.UserDO;

/**
 * DO对象转换 
 */
public class Convert2CountAdminEpDO {
    
	public static List<CountAdminEpRelaDO> convertEpList2AdminEpList(List<ElectricPileDO> epList,Long userId,UserDO loginUser){
		List<CountAdminEpRelaDO> list = new ArrayList<CountAdminEpRelaDO>();
		if(CollectionUtils.isNotEmpty(epList)){
			for (ElectricPileDO electricPileDO : epList) {
				CountAdminEpRelaDO domain = new CountAdminEpRelaDO();
				domain.setAdminId(userId);
				domain.setElectricPileId(electricPileDO.getId());
				domain.setElectricPileCode(electricPileDO.getCode());
				domain.setPowerStationId(electricPileDO.getPowerStationId());
				domain.setCreator(loginUser.getUserAccount());
				domain.setModifier(loginUser.getUserAccount());
				list.add(domain);
			}
		}
		return list;
	}
}

package com.wanma.ims.mapper;


import java.util.List;

import com.wanma.ims.common.domain.BomListDO;
import com.wanma.ims.common.domain.EquipmentVersionDO;
import com.wanma.ims.common.domain.TypeSpanDO;

public interface EquipmentVersionMapper {

	EquipmentVersionDO getBomById(String pkBomListId);

	void deleteByProductID(String evProductID);

	void insertEpVersion(EquipmentVersionDO epVersion);

	
	


}

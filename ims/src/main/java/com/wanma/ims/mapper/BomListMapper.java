package com.wanma.ims.mapper;


import java.util.List;

import com.wanma.ims.common.domain.BomListDO;
import com.wanma.ims.common.domain.TypeSpanDO;

public interface BomListMapper {


	List<BomListDO> getBomList(TypeSpanDO typeSpanDO);

	int insertBomList(BomListDO bomListDO);

	
	


}

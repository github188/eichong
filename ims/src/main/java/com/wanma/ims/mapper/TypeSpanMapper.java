package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.ElectricPileDO;
import com.wanma.ims.common.domain.TypeSpanDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;




public interface TypeSpanMapper {

	long getTypeSpanCount(TypeSpanDO typeSpanDO);

	List<TypeSpanDO> getTypeSpanList(TypeSpanDO typeSpanDO);

	int checkTsTypeSpan(String tsTypeSpan);

	int addTypeSpan(TypeSpanDO typeSpanDO);

	TypeSpanDO getTypeSpanById(TypeSpanDO typeSpanDO);

	List<TypeSpanDO> getTypeSpanByIds(@Param("ids") List<Long> ids);

	int updateTypeSpan(TypeSpanDO typeSpanDO);

	long selectPileListCount(ElectricPileDO electricPileDO);

	List<Map<String, Object>> selectPileList(ElectricPileDO electricPileDO);

	List<Map<String, Object>> getCheckUpList(String item);

	List<Map<String, String>> getBomIdUpgrade(String items);

	List<TypeSpanDO> getTypeSpanForList(TypeSpanDO typeSpanDO);

	


}

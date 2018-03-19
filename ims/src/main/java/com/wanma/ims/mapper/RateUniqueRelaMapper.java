package com.wanma.ims.mapper;


import java.util.List;

import com.wanma.ims.common.domain.RateInfoDO;
import com.wanma.ims.common.domain.RateUniqueRelaDO;

/**
 * 唯一费率管理
 * @author bingo
 * @date 2017-07-25
 */
public interface RateUniqueRelaMapper {
	
	public Long getRateUniqueRelaCount(RateUniqueRelaDO RateUniqueRela);
	
	public List<RateUniqueRelaDO> getRateUniqueRelaList(RateUniqueRelaDO RateUniqueRela);
	
	public int addRateUniqueRela(RateUniqueRelaDO RateUniqueRela);
	
	public int batchAddRateUniqueRela(List<RateUniqueRelaDO> rateUniqueRelaList);
	
	public int modifyRateUniqueRela(RateUniqueRelaDO RateUniqueRela);
	
	public int removeRateUniqueRela(RateUniqueRelaDO RateUniqueRela);
	
	public List<RateUniqueRelaDO> getRateUniqueRelaGroup(RateUniqueRelaDO RateUniqueRela);
	
	public List<RateUniqueRelaDO> getRateUniqueRelaCity(RateUniqueRelaDO RateUniqueRela);
	
	public List<RateUniqueRelaDO> getRateUniqueRelaPowerStation(RateUniqueRelaDO RateUniqueRela);
	
	public List<RateUniqueRelaDO> getRateUniqueRelaElectricPile(RateUniqueRelaDO RateUniqueRela);
	
	public int modifyRateUniqueRelaRateinfoId(RateUniqueRelaDO RateUniqueRela);

	List<String> getEpListByLevelAndCpy(RateUniqueRelaDO rateUniqueRela);

	RateInfoDO getRateInfoByEpId(Long id);

	RateInfoDO getRateInfoByRtId(Long rateinfoId);

	long countRateUniqueRelaRateinfoByEpId(Long id);
}
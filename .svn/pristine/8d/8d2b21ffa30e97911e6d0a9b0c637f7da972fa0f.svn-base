package com.wanma.ims.mapper;


import com.wanma.ims.common.domain.FinAccountSplitConfigDO;
import com.wanma.ims.common.domain.bo.IntegralAreaBO;

import java.util.List;

/**
 * 分账配置
 * @author bingo
 * @date 2017-11-27
 */
public interface FinAccountSplitConfigMapper {
	
	public Long getFinAccountSplitConfigCount(FinAccountSplitConfigDO finAccountSplitConfig);
	
	public List<FinAccountSplitConfigDO> getFinAccountSplitConfigList(FinAccountSplitConfigDO finAccountSplitConfig);
	
	public int addFinAccountSplitConfig(FinAccountSplitConfigDO finAccountSplitConfig);

	public int batchAddFinAccountSplitConfig(List<FinAccountSplitConfigDO> finAccountSplitConfigList);
	
	public int modifyFinAccountSplitConfig(FinAccountSplitConfigDO finAccountSplitConfig);

	public int batchModifyFinAccountSplitConfig(List<FinAccountSplitConfigDO> finAccountSplitConfigList);
	
	public int removeFinAccountSplitConfig(FinAccountSplitConfigDO finAccountSplitConfig);

	public int batchRemoveFinAccountSplitConfig(List<FinAccountSplitConfigDO> finAccountSplitConfigList);

	public List<IntegralAreaBO> getProvince(FinAccountSplitConfigDO finAccountSplitConfig);

	public List<IntegralAreaBO> getCity(FinAccountSplitConfigDO finAccountSplitConfig);

	public List<IntegralAreaBO> getPowerStation(FinAccountSplitConfigDO finAccountSplitConfig);

	public List<IntegralAreaBO> getElectricPile(FinAccountSplitConfigDO finAccountSplitConfig);
}
package com.wanma.ims.service;

import com.wanma.ims.common.domain.*;
import com.wanma.ims.common.dto.BaseResultDTO;

import java.util.List;


/**
 * 分账配置
 * @author bingo
 * @date 2017-11-27
 */
public interface FinAccountSplitConfigService {
	/**
	 * <p>Description: 获取分账配置数量</p>
	 * @param
	 * @author bingo
	 * @date 2017-11-27
	 */
	public Long getFinAccountSplitConfigCount(FinAccountSplitConfigDO finAccountSplitConfig);

	/**
	 * <p>Description: 获取分账配置列表</p>
	 * @param
	 * @author bingo
	 * @date 2017-11-27
	 */
	public List<FinAccountSplitConfigDO> getFinAccountSplitConfigList(FinAccountSplitConfigDO finAccountSplitConfig);

	/**
	 * <p>Description: 新增分账配置</p>
	 * @param
	 * @author bingo
	 * @date 2017-11-27
	 */
	public BaseResultDTO addFinAccountSplitConfig(FinAccountSplitConfigDO finAccountSplitConfig, UserDO loginUser) throws Exception;

	/**
	 * <p>Description: 修改分账配置</p>
	 * @param
	 * @author bingo
	 * @date 2017-11-27
	 */
	public BaseResultDTO modifyFinAccountSplitConfig(FinAccountSplitConfigDO finAccountSplitConfig, UserDO loginUser) throws Exception;

	public List<ProvinceDO> getStationAndPile(FinAccountSplitConfigDO finAccountSplitConfig,
											  UserDO loginUser) throws Exception;
}

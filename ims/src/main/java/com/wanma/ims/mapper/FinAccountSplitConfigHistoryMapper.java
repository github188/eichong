package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.FinAccountSplitConfigHistoryDO;

import java.util.List;

/**
 * 分账配置历史
 * @author bingo
 * @date 2017-11-27
 */
public interface FinAccountSplitConfigHistoryMapper {

	public Long getFinAccountSplitConfigHistoryCount(FinAccountSplitConfigHistoryDO finAccountSplitConfigHistory);

	public List<FinAccountSplitConfigHistoryDO> getFinAccountSplitConfigHistoryList(FinAccountSplitConfigHistoryDO finAccountSplitConfigHistory);

	public int addFinAccountSplitConfigHistory(FinAccountSplitConfigHistoryDO FinAccountSplitConfigHistoryDO);

	public int batchAddFinAccountSplitConfigHistory(List<FinAccountSplitConfigHistoryDO> FinAccountSplitConfigHistoryList);
}
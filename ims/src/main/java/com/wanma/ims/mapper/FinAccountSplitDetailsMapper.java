package com.wanma.ims.mapper;

import com.wanma.ims.common.domain.FinAccountSplitDetailsDO;

import java.util.List;

/**
 * 分账明细
 * @author bingo
 * @date 2017-11-27
 */
public interface FinAccountSplitDetailsMapper {
	
	public Long getFinAccountSplitDetailsCount(FinAccountSplitDetailsDO finAccountSplitDetails);
	
	public List<FinAccountSplitDetailsDO> getFinAccountSplitDetailsList(FinAccountSplitDetailsDO finAccountSplitDetails);
	
	public int addFinAccountSplitDetails(FinAccountSplitDetailsDO finAccountSplitDetails);

	public Long getDetailsCount(FinAccountSplitDetailsDO finAccountSplitDetails);

	public int deleteFinAccountSplitDetails(FinAccountSplitDetailsDO finAccountSplitDetails);

	public List<String> getFinAccountSplitDetailsForBatch(FinAccountSplitDetailsDO finAccountSplitDetails);
}
package com.wanma.ims.mapper;


import java.util.List;

import com.wanma.ims.common.domain.IntegralActivityDO;
import com.wanma.ims.common.domain.bo.IntegralActivityAndRulesBO;

/**
 * 积分活动表 
 * @author bingo
 * @date 2017-08-14
 */
public interface IntegralActivityMapper {
	
	public Long getIntegralActivityCount(IntegralActivityDO integralActivity);
	
	public List<IntegralActivityAndRulesBO> getIntegralActivityList(IntegralActivityDO integralActivity);
	
	public int addIntegralActivity(IntegralActivityDO integralActivity);
	
	public int modifyIntegralActivity(IntegralActivityAndRulesBO integralActivityAndRulesBO);
	
	public int deleteIntegralActivity(IntegralActivityDO integralActivity);
	
	public List<IntegralActivityAndRulesBO> getIntegralActivityAndRulesList(IntegralActivityDO integralActivity);
	
	public List<IntegralActivityAndRulesBO> getIntegralActivityForBatch(IntegralActivityDO integralActivity);
}
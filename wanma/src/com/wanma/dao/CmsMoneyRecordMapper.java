package com.wanma.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CmsMoneyRecordMapper {
/**
 * 查出充值总额
 * @return
 */
public HashMap<String,Object> getTotalRecharge();
/**
 * 查出消费总额
 * @return
 */
public HashMap<String,Object> getTotalPurchase();

/**
 * 查出总余额
 * @return
 */
public HashMap<String,Object> getTotalAccount();

/**
 * 查出用户消费充值情况
 * @param parems
 * @return
 */
public List<HashMap<String,Object>> getUserMoneyRecordList(Map<String,Object> params);

public long getUserMoneyRecordCount(Map<String,Object> params);


/**
 * 查出单个用户充值总额
 * @return
 */
public HashMap<String,Object> getUserTotalRecharge(Map<String,Object> params);
/**
 * 查出单个用户消费总额
 * @return
 */
public HashMap<String,Object> getUserTotalPurchase(Map<String,Object> params);

/**
 * 查出单个用户消费充值详情
 * @param parems
 * @return
 */

public List<HashMap<String,Object>> getMoneyRecordListByUserId(Map<String,Object> params);

public long getMoneyRecordCountByUserId(Map<String,Object> params);

/**
 * 查出合作商用户消费充值情况
 * @param parems
 * @return
 */
public long getPartnerMoneyRecordCount(Map<String, Object> params);
public List<HashMap<String, Object>> getPartnerMoneyRecordList(Map<String, Object> params);
/**
 * 查出合作商消费明细
 * 
 */
public long getMoneyRecordCountByCompanyNameNumber(Map<String, Object> params);
public List<HashMap<String, Object>> getMoneyRecordListByCompanyNameNumber(
		Map<String, Object> params);
/**
 * 财务退款
 * @param refundList
 * @return
 */
public int updateNormAccount(Map<String, Object> map);

/**
 * 获取退款记录--PuHiUserId
 * @param userAccount
 * @return
 */
public String getPuHiUserId(String userAccount);

/**
 * 退款成功后添加记录
 * @param puHiUserId
 * @param refundAccount
 * @return
 */
public int insertPurhistory(Map<String, Object> hiMap);
/**
 * 获取退款信息：真实姓名和余额
 * @param userId
 * @return
 */
public Map<String, Object> getRefundInfo(String userId);


}

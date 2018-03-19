package com.wanma.ims.mapper;


import java.util.List;
import java.util.Map;

import com.wanma.ims.common.domain.FinAccountDO;
import com.wanma.ims.common.domain.UserCardDO;

/**
 * 资金账户 
 * @author bingo
 * @date 2017-06-16
 */
public interface FinAccountMapper {
	
	public Long getFinAccountCount(FinAccountDO finAccount);
	
	public List<FinAccountDO> getFinAccountList(FinAccountDO finAccount);
	
	public int addFinAccount(FinAccountDO finAccount);
	
	public int modifyFinAccount(FinAccountDO finAccount);
	
	public int removeFinAccount(FinAccountDO finAccount);
	
	public List<FinAccountDO> getFinAccount4User(Map<String, Object> map);
	
	public Double getFinAccountBalance4User(Map<String, Object> map);
	
	public List<FinAccountDO> getFinAccount4Card(UserCardDO userCard);
	
	public int modifyFinAccountPwd(FinAccountDO finAccount);
	
	public FinAccountDO getFinAccountBalance4Cpy(String accountId);

	public void modifyFinAccountBalance(FinAccountDO userFinAccount);

	public FinAccountDO getFinAccountByAccountNO(String accountNO);
	
	public int modifyFinAccountWarn(FinAccountDO finAccount);
	
	public int updateFinAccountRefund(FinAccountDO finAccount);
	
	public FinAccountDO getFinAccountById(String accountId);
	
}
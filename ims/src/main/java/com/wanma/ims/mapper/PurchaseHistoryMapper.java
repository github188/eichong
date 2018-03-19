package com.wanma.ims.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wanma.ims.common.domain.PurchaseHistoryDO;
import com.wanma.ims.common.domain.bo.AccountBalanceBO;
import com.wanma.ims.common.domain.bo.UserAccountBO;

/**
 * 消费记录
 * @author bingo
 * @date 2017-07-31
 */
public interface PurchaseHistoryMapper {
	
	public Long getPurchaseHistoryCount(PurchaseHistoryDO purchaseHistory);
	
	public List<PurchaseHistoryDO> getPurchaseHistoryList(PurchaseHistoryDO purchaseHistory);

	public UserAccountBO getUserAccount(AccountBalanceBO balanceSearchModel);

	public UserAccountBO getCardAccount(String cardId);
	
	public Double countTotalConsume(Long userId);
	
	public List<String> selectTransNumberByInvoiceId(@Param("invoiceId")Long invoiceId);
	
	public int addPurchaseHistory(PurchaseHistoryDO purchaseHistory);

	public List<PurchaseHistoryDO> getPurchaseHistoryForBatch(PurchaseHistoryDO purchaseHistory);
}
package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsPurchasehistoryMapper;
import com.wanma.model.TblPurchasehistory;
import com.wanma.service.CmsPurchasehistoryService;
/**
 * 消费记录处理接口
 * 
 * @version V1.0
 * @author songjf
 * @date 2015年5月28日
 */
@Service
public class CmsPurchasehistoryServiceImpl implements CmsPurchasehistoryService {
	/**消费记录表操作dao*/
	@Autowired
	private CmsPurchasehistoryMapper purchasehistoryMapper;
	
	/**
	 * 取得消费记录列表
	 * 
	 * @return
	 */
	@Override
	public List<TblPurchasehistory> findPurchasehistory(
			TblPurchasehistory tblPurchasehistory) {
		return purchasehistoryMapper.findPurchasehistory(tblPurchasehistory);
	}

	/**
	 * 取得消费记录总数
	 * 
	 * @return
	 */
	@Override
	public long findPurchasehistoryCount(TblPurchasehistory tblPurchasehistory) {
		return purchasehistoryMapper.findPurchasehistoryCount(tblPurchasehistory);
	}
	
}

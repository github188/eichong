package com.wanma.dubbox.transaction.service.impl;


import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.dubbox.model.TblInvoice;
import com.wanma.dubbox.model.TblPurchasehistory;
import com.wanma.dubbox.service.TblInvoiceService;
import com.wanma.dubbox.service.TblPurchasehistoryService;
import com.wanma.dubbox.transaction.service.InvoiceTransactionService;

/**
 * 电桩事务控制接口
 * @author lhy
 *
 */
public class InvoiceTransactionServiceImpl implements InvoiceTransactionService {
	@Autowired
	TblInvoiceService tblInvoiceService;
	@Autowired
	TblPurchasehistoryService purchasehistoryService;
	
	@Override
	public void insert(TblInvoice ivcModel) {
	}

	@Override
	public void update(TblInvoice ivcModel,TblPurchasehistory phModel) {
		tblInvoiceService.update(ivcModel);
		purchasehistoryService.update(phModel);
	}
}

package com.wanma.ims.mapper;

import java.util.List;

import com.wanma.ims.common.domain.InvoiceDO;

public interface FinInvoiceMapper {

	public long getUserInvoiceCount(InvoiceDO invoice); 

	public List<InvoiceDO> getUserInvoiceList(InvoiceDO invoice);

	public InvoiceDO getFinInvoiceById(InvoiceDO invoice);

	public int confirmInvoice(InvoiceDO invoice);

	public int refuseInvoice(InvoiceDO invoice);

	public long getCpyRechargeInvoiceCount(InvoiceDO invoice);

	public long getCpyChargeInvoiceCount(InvoiceDO invoice);

	public List<InvoiceDO> getCpyRechargeInvoiceList(InvoiceDO invoice);

	public List<InvoiceDO> getCpyChargeInvoiceList(InvoiceDO invoice);

	public int addCpyInvoice(InvoiceDO invoice);

	public int modifyCpyInvoice(InvoiceDO invoice);

	public int modifyFinInvoice(InvoiceDO invoice);

}

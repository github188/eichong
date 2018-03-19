package com.wanma.ims.service;

import java.util.List;

import com.wanma.ims.common.domain.InvoiceDO;



public interface FinInvoiceService {
	/**
	 * 个人发票管理数量
	 * @param invoice
	 * @return
	 */
	long getUserInvoiceCount(InvoiceDO invoice);
	/**
	 * 个人发票管理列表
	 * @param invoice
	 * @return
	 */
	List<InvoiceDO> getUserInvoiceList(InvoiceDO invoice);
	/**
	 * 个人发票管理详情
	 * @param invoice
	 * @return
	 */
	InvoiceDO getFinInvoiceById(InvoiceDO invoice);
	/**
	 * 确认开票
	 * @param invoice
	 * @return
	 */
	boolean confirmInvoice(InvoiceDO invoice);
	/**
	 * 确认开票
	 * @param invoice
	 * @return
	 */
	boolean refuseInvoice(InvoiceDO invoice);
	/**
	 * 获取公司开票记录数量
	 * @param invoice
	 * @return
	 */
	long getCpyInvoiceCount(InvoiceDO invoice);
	/**
	 * 获取公司开票记录列表
	 * @param invoice
	 * @return
	 */
	List<InvoiceDO> getCpyInvoiceList(InvoiceDO invoice);
	/**
	 * 增加公司开票记录
	 * @param invoice
	 * @param ids 
	 * @return
	 * @throws Exception 
	 */
	boolean addCpyInvoice(InvoiceDO invoice, String ids) throws Exception;
	/**
	 * 编辑公司开票记录
	 * @param invoice
	 * @param ids 
	 * @return
	 * @throws Exception 
	 */
	boolean modifyCpyInvoice(InvoiceDO invoice, String ids);
	/**
	 * 编辑发票管理详情
	 * @param invoice
	 * @param ids 
	 * @return
	 * @throws Exception 
	 */
	boolean modifyFinInvoice(InvoiceDO invoice);
	



}

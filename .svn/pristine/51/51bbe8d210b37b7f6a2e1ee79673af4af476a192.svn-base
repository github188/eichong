package com.wanma.dubbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblInvoiceMapper;
import com.wanma.dubbox.model.TblInvoice;
import com.wanma.dubbox.service.TblInvoiceService;
/**
 * 开票数据查询接口
 * @author lhy
 *
 */
@Service
public class TblInvoiceServiceImpl implements TblInvoiceService {

	@Autowired
	TblInvoiceMapper tblInvoiceMapper;

	@Override
	public int insert(TblInvoice record) {
		return tblInvoiceMapper.insert(record);
	}

	@Override
	public TblInvoice selectOne(TblInvoice model) {
		return tblInvoiceMapper.selectOne(model);
	}

	@Override
	public int update(TblInvoice record) {
		return tblInvoiceMapper.update(record);
	}

	/**
	 * 获取数据条数
	 * @throws Exception 
	 */
	@Override
	public int getCount(TblInvoice model) {
		return tblInvoiceMapper.getCount(model);
	}

	/**
	 * 获取列表
	 * @throws Exception 
	 */
	@Override
	public List<TblInvoice> getList(TblInvoice model) {
		return tblInvoiceMapper.getList(model);
	}
	
}
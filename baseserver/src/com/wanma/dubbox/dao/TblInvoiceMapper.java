package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblInvoice;


/**
 * 数据访问接口
 *
 */
public interface TblInvoiceMapper {

    int insert(TblInvoice model);

    TblInvoice selectOne(TblInvoice model);

    int update(TblInvoice model);

	List<TblInvoice> getList(TblInvoice model);

	int getCount(TblInvoice model);
	
}



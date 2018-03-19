package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblPurchasehistory;


public interface TblPurchasehistoryMapper {

	List<TblPurchasehistory> getList(TblPurchasehistory model);

	int getCount(TblPurchasehistory model);

	int update(TblPurchasehistory model);
}

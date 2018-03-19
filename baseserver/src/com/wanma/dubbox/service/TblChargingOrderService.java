package com.wanma.dubbox.service;

import java.util.List;
import com.wanma.dubbox.model.TblChargingOrder;

public interface TblChargingOrderService {


	int insert(TblChargingOrder record);

	TblChargingOrder selectOne(TblChargingOrder model);
	
    int getCount(TblChargingOrder model);
    
    List<TblChargingOrder> getList(TblChargingOrder model);

	int delete(TblChargingOrder model);

	int update(TblChargingOrder model);
}
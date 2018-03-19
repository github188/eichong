package com.wanma.dubbox.service;

import java.util.List;

import com.wanma.dubbox.model.TblElectricPile;

public interface TblElectricPileService {

	int insert(TblElectricPile record);

	int update(TblElectricPile record);

	int updateMore(TblElectricPile record) throws Exception;

	int delete(TblElectricPile record) throws Exception;

	TblElectricPile selectOne(TblElectricPile record);

	int getCount(TblElectricPile model);

	List<TblElectricPile> getList(TblElectricPile model);

}
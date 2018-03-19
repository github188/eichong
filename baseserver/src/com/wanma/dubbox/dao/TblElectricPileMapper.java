package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblElectricPile;

public interface TblElectricPileMapper {

    int insert(TblElectricPile record);

    int update(TblElectricPile record);

	int delete(TblElectricPile record);

    TblElectricPile selectOne(TblElectricPile record);
    
	int getCount(TblElectricPile model);

	List<TblElectricPile> getList(TblElectricPile model);

	int updateMore(TblElectricPile record);
}
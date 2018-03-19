package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblMenu;

public interface TblMenuMapper {

    int insert(TblMenu record);

    int update(TblMenu record);

	int delete(TblMenu record);

    TblMenu selectOne(TblMenu record);
    
	int getCount(TblMenu model);

	List<TblMenu> getList(TblMenu model);
}
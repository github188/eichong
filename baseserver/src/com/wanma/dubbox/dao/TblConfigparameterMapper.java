package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblConfigparameter;

public interface TblConfigparameterMapper {

    int insert(TblConfigparameter record);

    int update(TblConfigparameter record);

	int delete(TblConfigparameter record);

    TblConfigparameter selectOne(TblConfigparameter record);
    
	int getCount(TblConfigparameter model);

	List<TblConfigparameter> getList(TblConfigparameter model);
}
package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblActivity;


public interface TblActivityMapper {
	
    TblActivity selectOne(TblActivity model);

    int delete(TblActivity model);

	List<TblActivity> getList(TblActivity model);

	int getCount(TblActivity model);
}

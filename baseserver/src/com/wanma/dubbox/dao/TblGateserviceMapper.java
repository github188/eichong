package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblGateservice;


public interface TblGateserviceMapper {

    int insert(TblGateservice model);

    int delete(TblGateservice model);

    int update(TblGateservice model);
	
    TblGateservice selectOne(TblGateservice model);

	List<TblGateservice> getList(TblGateservice model);

	int getCount(TblGateservice model);
}

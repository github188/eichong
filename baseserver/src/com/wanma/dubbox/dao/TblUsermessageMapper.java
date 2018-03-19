package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblUsermessage;

public interface TblUsermessageMapper {

    int insert(TblUsermessage record);

    int update(TblUsermessage record);

	int delete(TblUsermessage record);

    TblUsermessage selectOne(TblUsermessage record);
    
	int getCount(TblUsermessage model);

	List<TblUsermessage> getList(TblUsermessage model);
}
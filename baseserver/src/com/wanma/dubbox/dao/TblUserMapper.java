package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblUser;

public interface TblUserMapper {

	int delete(TblUser record);

    int insert(TblUser record);

	int update(TblUser record);

	TblUser selectOne(TblUser model);

	List<TblUser> getList(TblUser model);

	int getCount(TblUser model);
}
package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblUserNormal;

public interface TblUserNormalMapper {

	int delete(TblUserNormal record);

	int insert(TblUserNormal record);

	TblUserNormal selectOne(TblUserNormal record);

	int update(TblUserNormal record);

	int getCount(TblUserNormal record);
	
	List<TblUserNormal> getList(TblUserNormal record);
}
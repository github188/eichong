package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblUserBusiness;

public interface TblUserBusinessMapper {

	int delete(TblUserBusiness record);

	int insert(TblUserBusiness record);

	TblUserBusiness selectOne(TblUserBusiness record);

	int update(TblUserBusiness record);

	int getCount(TblUserBusiness record);
	
	List<TblUserBusiness> getList(TblUserBusiness record);
}
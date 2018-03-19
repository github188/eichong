package com.wanma.dubbox.dao;


import java.util.List;

import com.wanma.dubbox.model.TblUserAdmin;

public interface TblUserAdminMapper {

	int insert(TblUserAdmin record);

	TblUserAdmin selectOne(TblUserAdmin record);

	int update(TblUserAdmin record);

	int getCount(TblUserAdmin record);

	List<TblUserAdmin> getList(TblUserAdmin record);
}
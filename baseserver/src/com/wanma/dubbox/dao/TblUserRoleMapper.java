package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblUserRole;

/**
 * 数据访问接口
 *
 */
public interface TblUserRoleMapper {
	
	List<TblUserRole> getList(TblUserRole model);

	int delete(TblUserRole model);

	int insert(TblUserRole model);
}

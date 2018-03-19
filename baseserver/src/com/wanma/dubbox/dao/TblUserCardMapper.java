package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblUserCard;
import com.wanma.dubbox.model.TblUserNormal;

/**
 * 数据访问接口
 *
 */
public interface TblUserCardMapper {

	int insert(TblUserCard model);

	TblUserCard selectOne(TblUserCard model);

	int update(TblUserCard model);

	List<TblUserCard> getList(TblUserCard model);

	int getCount(TblUserCard model);

	int getQuicklyApplyCardUserListCount(TblUserCard model);

	List<TblUserNormal> getQuicklyApplyCardUserList(TblUserCard model);

	int getApplyCardUserListCount(TblUserCard model);

	List<TblUserNormal> getApplyCardUserList(TblUserCard model);

}

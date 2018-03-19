package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblProductcomment;

public interface TblProductcommentMapper {

    int insert(TblProductcomment model);

    int update(TblProductcomment model);

	List<TblProductcomment> getList(TblProductcomment model);

	int delete(TblProductcomment model);

	int getCount(TblProductcomment model);
}
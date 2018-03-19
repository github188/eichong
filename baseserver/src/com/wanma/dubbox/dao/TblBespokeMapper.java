package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblBespoke;

public interface TblBespokeMapper {

    int insert(TblBespoke model);

	TblBespoke selectOne(TblBespoke model);

    int update(TblBespoke model);

	List<TblBespoke> getList(TblBespoke model);

	int delete(TblBespoke model);

	int getCount(TblBespoke model);
}
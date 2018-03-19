package com.wanma.dubbox.dao;


import java.util.List;

import com.wanma.dubbox.model.TblHomepage;

public interface TblHomepageMapper {

	int insert(TblHomepage model);

	TblHomepage selectOne(TblHomepage model);

	int update(TblHomepage model);

	List<TblHomepage> getList(TblHomepage model);

	int getCount(TblHomepage model);
}
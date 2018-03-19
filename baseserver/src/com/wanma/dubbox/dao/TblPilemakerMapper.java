package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblPilemaker;


public interface TblPilemakerMapper {

    int insert(TblPilemaker model);

    int delete(TblPilemaker model);

    int update(TblPilemaker model);
	
    TblPilemaker selectOne(TblPilemaker model);

	List<TblPilemaker> getList(TblPilemaker model);

	int getCount(TblPilemaker model);
}

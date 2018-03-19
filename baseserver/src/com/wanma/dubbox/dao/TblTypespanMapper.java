package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblTypespan;


/**
 * 数据访问接口
 *
 */
public interface TblTypespanMapper {    
	
    int insert(TblTypespan model);

    TblTypespan selectOne(TblTypespan model);

    int update(TblTypespan model);

	List<TblTypespan> getList(TblTypespan model);

	int getCount(TblTypespan model);

}



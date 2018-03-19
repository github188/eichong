package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblBomList;


/**
 * 数据访问接口
 *
 */
public interface TblBomListMapper {    
	
    int insert(TblBomList model);

    int update(TblBomList model);

	List<TblBomList> getList(TblBomList model);

}



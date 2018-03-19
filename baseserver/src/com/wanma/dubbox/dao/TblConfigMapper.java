package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblConfig;


/**
 * 字典配置
 *
 */
public interface TblConfigMapper {    

	int delete(TblConfig model);

    int insert(TblConfig model);

    TblConfig selectOne(TblConfig model);

    int update(TblConfig model);

	List<TblConfig> getList(TblConfig model);

	int getCount(TblConfig model);

}



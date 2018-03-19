package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblProvince;


/**
 * 数据访问接口
 *
 */
public interface TblProvinceMapper {    

    int insert(TblProvince model);

    TblProvince selectOne(TblProvince model);

    int update(TblProvince model);

	List<TblProvince> getList(TblProvince model);

	int getCount(TblProvince model);

}



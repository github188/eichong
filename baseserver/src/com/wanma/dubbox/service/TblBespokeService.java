package com.wanma.dubbox.service;

import java.util.List;

import com.wanma.dubbox.model.TblBespoke;


public interface TblBespokeService {

    int delete(TblBespoke model);

    int insert(TblBespoke model);

    TblBespoke selectOne(TblBespoke model);

    int update(TblBespoke model);
    
    int getCount(TblBespoke model);
    
    List<TblBespoke> getList(TblBespoke model);
}
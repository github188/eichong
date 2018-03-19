package com.wanma.dubbox.service;

import java.util.List;

import com.wanma.dubbox.model.TblProductcomment;


public interface TblProductcommentService {

    int delete(TblProductcomment model);

    int insert(TblProductcomment model);

    int update(TblProductcomment model);
    
    int getCount(TblProductcomment model);
    
    List<TblProductcomment> getList(TblProductcomment model);
}
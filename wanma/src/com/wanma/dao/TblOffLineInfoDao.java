package com.wanma.dao;

import com.wanma.model.TblOffLineInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zangyaoyi on 2017/8/2
 */
@Repository
public interface TblOffLineInfoDao {
     List<TblOffLineInfo> getTblOffLineInfoList(TblOffLineInfo tblOffLineInfo);

}

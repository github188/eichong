package com.wanma.dubbox.dao;

import java.util.List;

import com.wanma.dubbox.model.TblFeedback;

public interface TblFeedbackMapper {

    int insert(TblFeedback record);

    int update(TblFeedback model);

	int delete(TblFeedback model);

	TblFeedback selectOne(TblFeedback model);

	int getCount(TblFeedback model);

	List<TblFeedback> getList(TblFeedback model);
}
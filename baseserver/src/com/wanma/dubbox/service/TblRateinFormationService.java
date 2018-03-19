package com.wanma.dubbox.service;

import java.util.List;

import com.wanma.dubbox.model.TblRateinFormation;

/**
 * @author gx
 *
 */
public interface TblRateinFormationService {

	int insert(TblRateinFormation model);

	int update(TblRateinFormation model);

	int delete(TblRateinFormation model);

	TblRateinFormation selectOne(TblRateinFormation model);

	int getCount(TblRateinFormation model);

	List<TblRateinFormation> getList(TblRateinFormation model);
	
}

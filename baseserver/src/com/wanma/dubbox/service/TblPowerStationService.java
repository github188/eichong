package com.wanma.dubbox.service;

import java.util.List;

import com.wanma.dubbox.model.TblPowerStation;

/**
 * @author lhy
 *
 */
public interface TblPowerStationService {

	TblPowerStation insert(TblPowerStation record);

	TblPowerStation selectOne(TblPowerStation record);

	List<TblPowerStation> getList(TblPowerStation model);

	int update(TblPowerStation model) throws Exception;

	int updateMore(TblPowerStation record) throws Exception;

	int getCount(TblPowerStation model);

	int delete(TblPowerStation model) throws Exception;

}

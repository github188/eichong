package com.wanma.dubbox.service;

import java.util.List;

import com.wanma.dubbox.model.TblElectricPileHead;

/**
 * @author lhy
 *
 */
public interface TblElectricPileHeadService {

	int insert(TblElectricPileHead model);

	int update(TblElectricPileHead model);

	int delete(TblElectricPileHead model) throws Exception;

	TblElectricPileHead selectOne(TblElectricPileHead model);

	int getCount(TblElectricPileHead model);

	List<TblElectricPileHead> getList(TblElectricPileHead model);

}

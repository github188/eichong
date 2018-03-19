package com.wanma.dubbox.transaction.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.dubbox.model.TblRateinFormation;
import com.wanma.dubbox.service.TblRateinFormationHistoryService;
import com.wanma.dubbox.service.TblRateinFormationService;
import com.wanma.dubbox.transaction.service.RateTransactionService;

/**
 * 集中器事务控制服务
 * 
 * @author lhy
 *
 */
public class RateTransactionServiceImpl implements RateTransactionService {
	@Autowired
	TblRateinFormationService rateinFormationService;
	@Autowired
	TblRateinFormationHistoryService tblRateinFormationHistoryService;

	@Override
	public void insert(TblRateinFormation model) {
		rateinFormationService.insert(model);
		tblRateinFormationHistoryService.insert(model);
	}

	@Override
	public void update(TblRateinFormation model) {
		rateinFormationService.update(model);
		tblRateinFormationHistoryService.insert(model);
	}

	@Override
	public void delete(TblRateinFormation model) {
	}

}

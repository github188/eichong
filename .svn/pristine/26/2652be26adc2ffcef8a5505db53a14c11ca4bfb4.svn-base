package com.wanma.dubbox.transaction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.wanma.dubbox.model.TblBomList;
import com.wanma.dubbox.model.TblTypespan;
import com.wanma.dubbox.service.TblBomListService;
import com.wanma.dubbox.service.TblTypespanService;
import com.wanma.dubbox.transaction.service.TypespanTransactionService;

/**
 * 产品型号事务控制接口
 * 
 * @author lhy
 *
 */
public class TypespanTransactionServiceImpl implements
		TypespanTransactionService {
	@Autowired
	TblTypespanService tblTypespanService;
	@Autowired
	TblBomListService tblBomListService;

	@Override
	public void insert(TblTypespan model) {
		tblTypespanService.insert(model);
		List<TblBomList> bmList = model.getBomList();
		if (bmList != null) {
			for (TblBomList b : bmList) {
				b.setTsId(model.getId());
				tblBomListService.insert(b);
			}
		}
	}

	@Override
	public void update(TblTypespan model) {
		tblTypespanService.update(model);
		List<TblBomList> bmList = model.getBomList();
		if (bmList != null) {
			for (TblBomList b : bmList) {
				if (b.getId() != null) {
					tblBomListService.update(b);
				} else {
					b.setTsId(model.getId());
					tblBomListService.insert(b);
				}
			}
		}
	}
}

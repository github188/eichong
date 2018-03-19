package com.wanma.ims.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.ims.common.domain.ReportOrderDO;
import com.wanma.ims.mapper.FinAccountConfigRelaMapper;
import com.wanma.ims.mapper.FinAccountMapper;
import com.wanma.ims.mapper.ReportOrderMapper;
import com.wanma.ims.service.ReportCpyOrderService;

@Service
public class ReportCpyOrderServiceImpl implements ReportCpyOrderService{

	@Autowired
	private ReportOrderMapper reportOrderMapper;
	@Autowired
	private FinAccountMapper finAccountMapper;
	@Autowired
	private FinAccountConfigRelaMapper finAccountConfigRelaMapper;
	
	@Override
	public Long countReportCpyOrder(ReportOrderDO orderReportDO) {
		return reportOrderMapper.countReportCpyOrder(orderReportDO);
	}

	@Override
	public List<ReportOrderDO> getReportCpyOrder(ReportOrderDO orderReportDO) {
		return reportOrderMapper.selectReportCpyOrder(orderReportDO);
	}

}

package com.wanma.ims.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wanma.ims.common.domain.ReportOrderDO;

@Service
public interface ReportCpyOrderService {
    
	public Long countReportCpyOrder(ReportOrderDO orderReportDO);
	
	public List<ReportOrderDO> getReportCpyOrder(ReportOrderDO orderReportDO);
	
}

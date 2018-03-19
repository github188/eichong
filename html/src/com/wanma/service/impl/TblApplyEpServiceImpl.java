package com.wanma.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblApplyEpMapper;
import com.wanma.model.TblApplyEp;
import com.wanma.service.TblApplyEpService;

/**
 * @desc 申请建桩ServiceImplimentation
 * @author cdy
 *
 */

@Service
public class TblApplyEpServiceImpl implements TblApplyEpService {

	private static Logger log = Logger.getLogger(TblApplyEpServiceImpl.class);
	
	@Autowired
	private TblApplyEpMapper tblApplyEpMapper;
	
	@Override
	public Long insertEp(TblApplyEp applyEp) {
		Long aepId = null;
		applyEp.setCreateDate(new Date());
		try {
			// 调用DAO执行电桩添加处理
			aepId = tblApplyEpMapper.insert(applyEp);
		} catch (Exception e) {
			log.error("Service: insertEp(applyEp) error:"+e.getLocalizedMessage());
		}
		return aepId;
	}
	
	@Override
	public List<TblApplyEp> getByAepUserId(TblApplyEp applyEp) {
		List<TblApplyEp> applyEpList = tblApplyEpMapper.getByAepUserId(applyEp);
		
		return applyEpList;
	}

}

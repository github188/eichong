package com.wanma.dubbox.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dubbox.dao.TblMultiMediaMapper;
import com.wanma.dubbox.model.TblMultiMedia;
import com.wanma.dubbox.service.TblMultiMediaService;
/**
 * 充电卡申请数据查询接口
 * @author lhy
 *
 */
@Service
public class TblMultiMediaServiceImpl implements TblMultiMediaService {

	@Autowired
	TblMultiMediaMapper tblMultiMediaMapper;

	@Override
	public int update(TblMultiMedia record) {
		return tblMultiMediaMapper.update(record);
	}
}
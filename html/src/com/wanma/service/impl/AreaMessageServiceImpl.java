package com.wanma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.AreaMessageMapper;
import com.wanma.model.cdzts.AreaMessage;
import com.wanma.service.AreaMessageService;

@Service
public class AreaMessageServiceImpl implements AreaMessageService{
	@Autowired
	private AreaMessageMapper msgMapper;
	
	@Override
	public AreaMessage getAllMessage(AreaMessage msg) {
		return msgMapper.getAllMessage(msg);
	}

}

package com.wanma.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.TblVersionMapper;
import com.wanma.app.service.TblVersionService;
import com.wanma.model.TblVersion;

/**
 * @ClassName: TblVersionServiceImpl
 * @Description: 版本信息服务接口实现类
 * @author chenb
 * @date 2015年3月15日 下午7:59:23
 */
@Service
public class TblVersionServiceImpl implements TblVersionService {
	/** 意见反馈业务操作DAO */
	@Autowired
	private TblVersionMapper tblVersionMapper;

	@Override
	public TblVersion getTblVersion(int versType) {
		// 根据设备类型获取设备号
		return tblVersionMapper.get(versType);
	}

}

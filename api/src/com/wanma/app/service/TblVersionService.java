package com.wanma.app.service;

import java.util.List;

import com.wanma.model.TblVersion;

/**
 * @ClassName: TblVersionService
 * @Description: 版本信息服务接口
 * @author chenb
 * @date 2015年3月15日 下午7:58:35
 */
public interface TblVersionService {
	/**
	 * @Title: getTblVersion
	 * @Description: 获取版本信息
	 * @return
	 * @throws
	 */
	public TblVersion getTblVersion(int versType);
}

/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.model.TblJpush;
import com.wanma.web.dao.WebJpushMapper;
import com.wanma.web.service.WebJpushService;

/**
 * @Description: 极光推送业务处理实现
 * @author songjf
 * @createTime：2015-3-15 下午05:12:46
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class WebJpushServiceImpl implements WebJpushService {

	/* 极光推送业务操作dao */
	@Autowired
	private WebJpushMapper jpushMapper;

	/**
	 * @Title: getByuserInfo
	 * @Description: 根据用户id获取用户推送信息
	 * @param params
	 * @return
	 */
	@Override
	public TblJpush getByuserInfo(int jpushUserInfo) {
		return jpushMapper.getByuserInfo(jpushUserInfo);
	}

	/**
	 * @Title: update
	 * @Description: 更新用户推送信息
	 * @param params
	 * @return
	 */
	@Override
	public int update(TblJpush tblJpush) {
		tblJpush.setJpushUpdatedate(new Date());
		return jpushMapper.update(tblJpush);
	}

	/**
	 * @Title: insert
	 * @Description: 新增用户推送信息
	 * @param params
	 * @return
	 */
	@Override
	public int insert(TblJpush tblJpush) {
		tblJpush.setJpushCreatedate(new Date());
		tblJpush.setJpushUpdatedate(new Date());
		return jpushMapper.insert(tblJpush);
	}

}

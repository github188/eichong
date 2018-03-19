/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.UsercollectMapper;
import com.wanma.app.service.UsercollectService;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.TblUsercollect;

/***
 *
 *   商品城，类目
  * @Description:
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime：2015-3-13 下午04:51:34 
  * @updator： 
  * @updateTime：   
  * @version：V1.0
 */
@Service
public class TblUsershareelecServiceImpl implements UsercollectService {

	@Autowired
	UsercollectMapper usercollectMapper;
	 
	@Override
	public void userFavorites(TblUsercollect tblUsercollect) {
		// TODO Auto-generated method stub
		usercollectMapper.insert(tblUsercollect);
	}

	@Override
	public void removeFavoritesList(String userCollectId) {
		// TODO Auto-generated method stubTblUsercollect tblUsercollect
		
		if(userCollectId.indexOf(",")>0){
			String [] collectIds=userCollectId.split(",");
			for (int i = 0; i < collectIds.length; i++) {
				TblUsercollect tblUsercollect=new TblUsercollect();
				tblUsercollect.setPkUsercollect(JudgeNullUtils.nvlInteget(collectIds[i]));
				usercollectMapper.delete(tblUsercollect);
			}
		}else{
			TblUsercollect tblUsercollect=new TblUsercollect();
			tblUsercollect.setPkUsercollect(Integer.parseInt(userCollectId));
			usercollectMapper.delete(tblUsercollect);
		}
	}
}

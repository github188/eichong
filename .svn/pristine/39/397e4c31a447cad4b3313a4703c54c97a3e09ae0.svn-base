/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.common.JudgeNullUtils;
import com.wanma.model.TblUsercollect;
import com.wanma.web.dao.WebUsercollectMapper;
import com.wanma.web.service.WebUsercollectService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.Response;
import com.wanma.web.support.common.ResultResponse;

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
public class WebUsershareelecServiceImpl implements WebUsercollectService {

	@Autowired
	WebUsercollectMapper usercollectMapper;
	 
	@Override
	public Response<?> userFavorites(TblUsercollect tblUsercollect) {
		List<TblUsercollect> list=usercollectMapper.find(tblUsercollect);
		if(list==null||list.isEmpty()){
			// TODO Auto-generated method stub
			tblUsercollect.setUscoAddtime(new Date());
			usercollectMapper.insert(tblUsercollect);
			return new ResultResponse();
		}else{
			return new FailedResponse("已收藏!");
		}
		
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
	
	/**
	 * 用户收藏
	 */
	public List<TblUsercollect> getUserCollect(TblUsercollect tblUsercollect){
		List<Map<String,Object>> list= usercollectMapper.getUserCollectDetail(tblUsercollect);
		List<TblUsercollect> collectList=new ArrayList<TblUsercollect>();
		TblUsercollect collect=null;
		int electricType=0;
		for (Map<String,Object> map:list) {
			collect = new TblUsercollect();
			collect.setPkUsercollect((Integer)map.get("pk_UserCollect"));
			collect.setUscoUserid((Integer)map.get("usCo_Userid"));
			collect.setUscoType((Integer)map.get("usCo_Type"));
			collect.setUscoObjectid((Integer)map.get("usCo_Objectid"));
			collect.setUscoAddtime((Date)map.get("usCo_AddTime"));
			collect.setObjName(JudgeNullUtils.nvlStr(map.get("objName")));
			collect.setObjAddress(JudgeNullUtils.nvlStr(map.get("objAddress")));
			collectList.add(collect);
		}
		return collectList;
	}
}

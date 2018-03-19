/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.TblUserCollectMapper;
import com.wanma.service.TblUserCollectService;

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
public class TblUserCollectServiceImpl implements TblUserCollectService {

	@Autowired
	TblUserCollectMapper userCollectMapper;

	@Override
	public List<Map<String, Object>> getCollectList(Map<String, Object> param) {
		return userCollectMapper.getUserCollectList(param);
	}


//	@Override
//	public Response<?> userFavorites(TblUserCollect tblUserCollect) {
//		List<TblUserCollect> list=usercollectMapper.find(tblUserCollect);
//		if(list==null||list.isEmpty()){
//			// TODO Auto-generated method stub
//			tblUserCollect.setUscoAddtime(new Date());
//			usercollectMapper.insert(tblUserCollect);
//			return new ResultResponse();
//		}else{
//			return new FailedResponse("已收藏!");
//		}
//		
//	}
//
//	@Override
//	public void removeFavoritesList(String userCollectId) {
//		// TODO Auto-generated method stubTblUsercollect tblUsercollect
//		
//		if(userCollectId.indexOf(",")>0){
//			String [] collectIds=userCollectId.split(",");
//			for (int i = 0; i < collectIds.length; i++) {
//				TblUserCollect tblUserCollect=new TblUserCollect();
//				tblUserCollect.setPkUsercollect(JudgeNullUtils.nvlInteget(collectIds[i]));
//				usercollectMapper.delete(tblUserCollect);
//			}
//		}else{
//			TblUserCollect tblUserCollect=new TblUserCollect();
//			tblUserCollect.setPkUsercollect(Integer.parseInt(userCollectId));
//			usercollectMapper.delete(tblUserCollect);
//		}
//	}
//	
//	/**
//	 * 用户收藏
//	 */
//	public List<TblUserCollect> getUserCollect(TblUserCollect tblUserCollect){
//		List<Map<String,Object>> list= usercollectMapper.getUserCollectDetail(tblUserCollect);
//		List<TblUserCollect> collectList=new ArrayList<TblUserCollect>();
//		TblUserCollect collect=null;
//		int electricType=0;
//		for (Map<String,Object> map:list) {
//			collect = new TblUserCollect();
//			collect.setPkUsercollect((Integer)map.get("pk_UserCollect"));
//			collect.setUscoUserid((Integer)map.get("usCo_Userid"));
//			collect.setUscoType((Integer)map.get("usCo_Type"));
//			collect.setUscoObjectid((Integer)map.get("usCo_Objectid"));
//			collect.setUscoAddtime((Date)map.get("usCo_AddTime"));
//			collect.setObjName(JudgeNullUtils.nvlStr(map.get("objName")));
//			collect.setObjAddress(JudgeNullUtils.nvlStr(map.get("objAddress")));
//			collectList.add(collect);
//		}
//		return collectList;
//	}
	
}

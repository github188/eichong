/**
 * FileName:AppFeedbackMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.ElctrcplscrnconfgurtnMapper;
import com.wanma.app.service.ElctrcplscrnconfgurtnService;
import com.wanma.common.JudgeNullUtils;
import com.wanma.model.ElectricPileList;
import com.wanma.model.TblElctrcplscrnconfgurtn;

/**
 * 反馈信息业务处理类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
@Service
public class ElctrcplscrnconfgurtnServiceImpl implements ElctrcplscrnconfgurtnService {

	/** 聊天主表操作用DAO */
	@Autowired
	ElctrcplscrnconfgurtnMapper elctrcplscrnconfgurtnMapper;

	@Override
	public List<TblElctrcplscrnconfgurtn> getScreenTypeList(
			TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn) {
		// TODO Auto-generated method stub
		List<TblElctrcplscrnconfgurtn> TblElctrcplscrnconfgurtnList=new ArrayList<TblElctrcplscrnconfgurtn>();
		List<TblElctrcplscrnconfgurtn> TblElctrcplscrnconfgurtnList1=elctrcplscrnconfgurtnMapper.find(tblElctrcplscrnconfgurtn);
		if(tblElctrcplscrnconfgurtn.getEpscType()==9){
			for (int i = 0; i < TblElctrcplscrnconfgurtnList1.size(); i++) {
				TblElctrcplscrnconfgurtn tconfgurtn=new TblElctrcplscrnconfgurtn();
			    Map<String,Object> confgurtnMap=(Map<String,Object>)TblElctrcplscrnconfgurtnList1.get(i);
			     String distanceString=JudgeNullUtils.nvlStr((confgurtnMap.get("epscName")));
			            distanceString=distanceString.substring(0, distanceString.lastIndexOf("km"));
			            tconfgurtn.setPkElctrcplscrnconfgurtn(Integer.parseInt(distanceString)*1000);
			            tconfgurtn.setEpscName(JudgeNullUtils.nvlStr(confgurtnMap.get("epscName")));
			    TblElctrcplscrnconfgurtnList.add(tconfgurtn);
			}
		}else{
			TblElctrcplscrnconfgurtnList=TblElctrcplscrnconfgurtnList1;
		}
		
		return TblElctrcplscrnconfgurtnList;
	}

	@Override
	public List<TblElctrcplscrnconfgurtn> getScreenStateList(
			TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn) {
		// TODO Auto-generated method stub
		return elctrcplscrnconfgurtnMapper.find(tblElctrcplscrnconfgurtn);
	}

	@Override
	public List<TblElctrcplscrnconfgurtn> getScreenRadiusList(
			TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn) {
		// TODO Auto-generated method stub
		return elctrcplscrnconfgurtnMapper.find(tblElctrcplscrnconfgurtn);
	}

	@Override
	public List<TblElctrcplscrnconfgurtn> getScreenSubscribeList(
			TblElctrcplscrnconfgurtn tblElctrcplscrnconfgurtn) {
		// TODO Auto-generated method stub
		return elctrcplscrnconfgurtnMapper.find(tblElctrcplscrnconfgurtn);
	}



}

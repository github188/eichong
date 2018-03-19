package com.wanma.app.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.wanma.app.dao.AppElecPileStarMapper;

@Service
public class AppElecPileStarServiceImpl {
	@Autowired
	private AppElecPileStarMapper epsMapper;
	
	/**
	 * 保存电桩星级评价
	 * @param param
	 */
	public void insertEpStar(Map<String, Object> param){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String date = dateFormat.format( now ); 
		param.put("createdate", date);
		
		if(StringUtils.isEmpty(param.get("uName"))){
			param.put("uName", "");
		}
		
		epsMapper.insert(param);
	}
	
	/**
	 * 根据桩id和用户id获取该用户对桩的星评数
	 * @param param
	 * @return
	 */
	public int getNumByUidEpid(Map<String, Object> param){
		return epsMapper.getNumByUidEpid(param);
	}
}

package com.wanma.app.service.impl;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.app.dao.AppElecPileCommentMapper;
import com.wanma.app.util.SensitiveWordUtil;
import com.wanma.common.WanmaConstants;

@Service
public class AppElecPileCommentServiceImpl {
	@Autowired
	private AppElecPileCommentMapper elcMapper;
	@Autowired
	private RedisService redisService;
	
	/**
	 * 获取电站评论分页列表
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> getEpCommentsPageList(Map<String, Object> params){
		return elcMapper.getEpCommentsPageList(params);	
	}
	
	/**
	 * 保存电站评论
	 * @param param
	 */
	/*public void insertEpCommnet(Map<String, Object> param){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String date = dateFormat.format( now ); 
		param.put("createdate", date);
		
		elcMapper.insert(param);
	}*/
	
	/**
	 * 判断敏感词，保存电桩评论
	 * 
	 * @param param
	 * @return
	 */
	public String insertEpCommnetWithPsCheck(Map<String, Object> param){
		String epContent = param.get("epContent").toString();
		List<String> psList=redisService.listGetAll(WanmaConstants.SENSITIVE_WORD_LIST);
		int errorSize = SensitiveWordUtil.getSensitiveWord(epContent,psList).size();
		if (errorSize >= 2)
			return "error.msg.pserror.parameter";
		else if (errorSize == 1)
			param.put("epContent", SensitiveWordUtil.replaceSensitiveWord(epContent, "*",psList));
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//可以方便地修改日期格式
		String date = dateFormat.format( now ); 
		param.put("createdate", date);
		
		elcMapper.insert(param);
		return "";
	}
}

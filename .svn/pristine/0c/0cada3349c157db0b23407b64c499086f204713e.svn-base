package com.wanma.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.common.SessionMgr;
import com.wanma.model.TblUsermessage;
import com.wanma.web.service.WebUsermessageService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.utils.JsonUtil;

@Controller
@RequestMapping("/web/usermessage")
public class WebUsermessageController {
	private static Log log = LogFactory.getLog(WebUsermessageController.class);

	@Autowired
	WebUsermessageService usermessageService;
	
	@RequestMapping("/getMessages")
	@ResponseBody
	public String getByUserId(@RequestParam Map<String, Object> params,HttpServletRequest request) throws UnsupportedEncodingException{
		log.info("******************获取消息信息-begin************************");
		Integer begin = null, pageSize = null;
        //获取分页参数
        if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
        	pageSize = Integer.valueOf((String) params.get("pageSize"));
        }
        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
        	begin = Integer.valueOf((String) params.get("pageNum"));
        }
        PageResponse<List<TblUsermessage>> pager = new PageResponse<List<TblUsermessage>>(begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			params.put("userId", SessionMgr.getUserId(request));
			List<TblUsermessage> usermessage= usermessageService.getByuserId(params);
            pager.setCountData(usermessageService.countUsermessage(params));
            pager.setDate(usermessage);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取消息信息错误", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************获取消息信息-end************************");
		// 返回处理成功信息
		return pager.toString();
	}
	
	@RequestMapping("/getSystemMessages")
	@ResponseBody
	public String getSystemMsg(@RequestParam Map<String, Object> params,HttpServletRequest request) throws UnsupportedEncodingException{
		log.info("******************获取系统消息信息-begin************************");
		Integer begin = null, pageSize = null;
        //获取分页参数
        if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
        	pageSize = Integer.valueOf((String) params.get("pageSize"));
        }
        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
        	begin = Integer.valueOf((String) params.get("pageNum"));
        }
        PageResponse<List<HashMap<String, Object>>> pager = new PageResponse<List<HashMap<String, Object>>>(begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			params.put("userId", SessionMgr.getUserId(request));
			List<HashMap<String, Object>> systemMessage= usermessageService.getSystemMsg(params);
            pager.setCountData(usermessageService.countSystemMessage(params));
            pager.setDate(systemMessage);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取系统消息信息错误", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************获取系统消息-end************************");
		// 返回处理成功信息
		return pager.toString();
	}
	
	
	@RequestMapping("/getUsermessageById")
	@ResponseBody
	public String getUsermessageById(HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(req.getParameter("id"));	
		boolean  flag=usermessageService.updateById(id);
		TblUsermessage usermessag = usermessageService.getUsermessageById(id);
		return JsonUtil.object2json(usermessag);		   
	}
	
	@RequestMapping("/updateById")
	@ResponseBody
	public void updateById(HttpServletRequest req,HttpServletResponse resp) throws UnsupportedEncodingException{
		req.setCharacterEncoding("UTF-8");
		int id = Integer.parseInt(req.getParameter("id"));
		if(usermessageService.updateById(id)){
			    return;
		}
		
	}
	
	
	@RequestMapping("/getPersonMessages")
	@ResponseBody
	public String getPersonMessages(@RequestParam Map<String, Object> params,HttpServletRequest request) throws UnsupportedEncodingException{
		log.info("******************获取私信-begin************************");
		Integer begin = null, pageSize = null;
        //获取分页参数
        if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
        	pageSize = Integer.valueOf((String) params.get("pageSize"));
        }
        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
        	begin = Integer.valueOf((String) params.get("pageNum"));
        }
        PageResponse<List<HashMap<String, Object>>> pager = new PageResponse<List<HashMap<String, Object>>>(begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {
			params.put("userId", SessionMgr.getUserId(request));
			List<HashMap<String, Object>> personMessage= usermessageService.getPersonMessage(params);
            pager.setCountData(usermessageService.countPersonMessage(params));
            pager.setDate(personMessage);
		} catch (Exception e) {
			// 保存错误LOG
			log.error(e.getLocalizedMessage());
			log.error("获取私信错误", e);
			// 返回登录信息错误信息
			return new FailedResponse("error.msg.invalid.parameter").toString();
		}
		log.info("******************获取私信-end************************");
		// 返回处理成功信息
		return pager.toString();
	}
	
	
}

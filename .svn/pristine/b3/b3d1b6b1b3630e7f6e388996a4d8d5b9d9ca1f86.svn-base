package com.wanma.web.controller;

import java.io.UnsupportedEncodingException;
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

import com.wanma.model.TblRecruit;
import com.wanma.model.WebList;
import com.wanma.web.service.WebTblRecruitService;
import com.wanma.web.support.common.FailedResponse;
import com.wanma.web.support.common.PageResponse;
import com.wanma.web.support.utils.JsonUtil;

@Controller
@RequestMapping("/web/tblRecuit")
public class WebTblRecuitController {
	private static Log log = LogFactory.getLog(WebTblRecuitController.class);
	
	@Autowired
	private  WebTblRecruitService webTblRecruitService;
	
	
	@RequestMapping("/findAll")
	@ResponseBody
	public String getAll(HttpServletRequest req,HttpServletResponse resp) {

		resp.setCharacterEncoding("UTF-8");
	    List<TblRecruit> list = null;
	    String s = "";
	  //  String releType = req.getParameter("releType");
		list = webTblRecruitService.findSome();
//		Map<String,List<WebList>> webListmap = new HashMap();
//		webListmap.put("list", list);
//		return new  ModelAndView ("uri",map);
//		return new ResultResponse<Map<String,Object>>(webListmap).toString();
		s = JsonUtil.list2json(list); 
		return s;	
		
	}
		
	@RequestMapping("/findByPk")
	@ResponseBody
	public String getByPk(HttpServletRequest req,HttpServletResponse resp) {
		 
		int pkRecruit = Integer.parseInt(req.getParameter("pkRecruit"));		
		resp.setCharacterEncoding("UTF-8");	    	
	    JsonUtil JsonUtil = new JsonUtil();
	    String s="";	   
	    TblRecruit tblRecruit = webTblRecruitService.findByPk(pkRecruit);	  
	    s=JsonUtil.object2json(tblRecruit);
  
		return s;
		}	
	@RequestMapping("/getAll")
	@ResponseBody
	public String getAll(@RequestParam Map<String, Object> params,HttpServletRequest request) throws UnsupportedEncodingException{
		log.info("******************获取消息信息-begin************************");
		Integer begin = null, pageSize = null;
        //获取分页参数
		  if (!StringUtils.isEmpty((String) params.get("pageSize"))) {
	        	pageSize = Integer.valueOf((String) params.get("pageSize"));
	        }
	        if (!StringUtils.isEmpty((String) params.get("pageNum"))) {
	        	begin = Integer.valueOf((String) params.get("pageNum"));
	        }
        PageResponse<List<TblRecruit>> pager = new PageResponse<List<TblRecruit>>(begin, pageSize);
		params.put("pageNum", pager.getCount());
		params.put("pageNumber", pager.getBegin());
		try {		
			List<TblRecruit> tblRecruit= webTblRecruitService.getAll(params);
            pager.setCountData(webTblRecruitService.countRecruit(params));
            pager.setDate(tblRecruit);
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
	
}

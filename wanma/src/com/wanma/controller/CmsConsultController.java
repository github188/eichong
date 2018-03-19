package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.wanma.common.WanmaConstants;
import com.wanma.model.Consult;
import com.wanma.service.CmsConsultService;

/**
 * 咨询表控制器
 * 
 * @author xiay
 *
 */
@Controller
@RequestMapping("/admin/consult")
public class CmsConsultController extends BaseController{
	
	//日志输出对象
	private static Logger log = Logger.getLogger(CmsConsultController.class);
	
	/** 咨询业务处理对象 */
	@Autowired
	private CmsConsultService consultService;
	
	/**
	 * 取得咨询列表处理
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	@RequestMapping(value = "/consultList")
	public String getConsultList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute Consult consult,Model model,HttpServletRequest request){
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}else{
			//咨询表信息
			List<Consult> consultList = null;
			//咨询总数
			long total = 0;
			if(consult == null){
				//取得咨询表
				consultList = consultService.getConsultList();
			}else {
				// 咨询总数
				total = consultService.searchConsultCount(consult);
				if (total <= pager.getOffset()) {
					pager.setPageNum(1L);
				}
				//设置分页对象
				consult.setPager(pager);
				
				//取得咨询表
				consultList = consultService.searchConsultList(consult);
				
				pager.setTotal(total);
			}
			
			//将咨询信息放到会话中
			model.addAttribute("consult", consult);
			model.addAttribute("consultList", consultList);
			model.addAttribute("pager", pager);
			
			//跳转至咨询表界面
			return "backstage/consult/listConsult";
		}	
	}
}
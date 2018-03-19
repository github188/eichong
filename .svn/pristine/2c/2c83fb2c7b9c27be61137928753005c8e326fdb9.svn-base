package com.wanma.controller;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.Feedback;
import com.wanma.model.TblUser;
import com.wanma.service.CmsFeedbackService;

/**
 * 意见反馈管理
 * 
 * @author xiay
 * 
 */
@Controller
@RequestMapping("/admin/feedback")
public class CmsFeedbackController extends BaseController{

	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsFeedbackController.class);

	/** 意见反馈处理器 */
	@Autowired
	private CmsFeedbackService feedbackService;
	
	
	/**
	 * 取得意见反馈列表处理
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	@RequestMapping(value = "/feedbackList")
	public String feedbackList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute Feedback feedback,HttpServletRequest request,
			Model model){
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}else{
			//将结束时间延伸到本日结束
			if(!StringUtils.isEmpty(feedback.getEndDate())){
				feedback.setEndDate(feedback.getEndDate() + " 23:59:59");
			}
			//意见反馈信息
			List<Feedback> feedbackList = null;
			// 意见反馈总数
		    long total = feedbackService.searchFeedbackCount(feedback);
			if(total<=pager.getOffset()){
				pager.setPageNum(1L);
			}
			//设置分页对象
			feedback.setPager(pager);
			
			//取得意见反馈信息
			feedbackList = feedbackService.searchFeedbackList(feedback);
			pager.setTotal(total);
			//使用完毕后将时间还原回去
			if(!StringUtils.isEmpty(feedback.getEndDate())){
				feedback.setEndDate(feedback.getEndDate().replace(" 23:59:59", ""));
			}
			//将意见反馈信息放到会话中
			model.addAttribute("feedback", feedback);
			model.addAttribute("feedbackList", feedbackList);
			model.addAttribute("pager", pager);
			//跳转至意见反馈信息
			return "backstage/feedback/listFeedback";
		}	
	}
	@RequestMapping("/updateFeedback")
	@ResponseBody
	public String updateFeedbackReason(Feedback feedback,
			HttpServletRequest request) {
		log.info("******************更新电动车品牌类型-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		TblUser tblUser = SessionMgr.getWebUser(request);
		if(feedback.getReason().length() <= 500){
			try {
				feedback.setFeBaUpdateUserId(tblUser.getUserId().toString());
				feedback.setReason(feedback.getReason().replaceAll("[\\n]", ""));
				feedbackService.updateFeedbackReason(feedback);
				dwzResult = new DwzAjaxResult("200", "编辑成功", "feedbackList",
						"closeCurrent", "");
			} catch (Exception e) {
				// 出错日志
				log.info(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "编辑失败", "feedbackList", "", "");
			}
		}else{
			    dwzResult = new DwzAjaxResult("300", "处理原因长度不超过500字符，编辑失败", "feedbackList", "", "");
		}	
		log.info("******************更新电动车品牌类型-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * @Title: editCarinfo
	 * @Description: 跳转至更新电动车品牌类型页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/editFeedback")
	public String editCarinfo(String feedbackId, Model model) {
		log.info("******************跳转至更新电动车品牌类型页面-begin************************");		
		// 根据id获取电动车品牌类型详情
		Feedback feedback = feedbackService.findFeedback(feedbackId);
		// 将查询结果显示到画面	
		model.addAttribute("feedback", feedback);
		log.info("******************跳转至更新电动车品牌类型页面-end************************");
		return "backstage/feedback/editFeedbackReason";
	}
}

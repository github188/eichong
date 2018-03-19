package com.wanma.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.CommonQuestions;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCommonQuestionsService;

/**
 * 常见问题表控制
 * 
 * @author xiay
 *
 */
@Controller
@RequestMapping("/admin/comquestion")
public class CmsCommonQuestionsController extends BaseController{

	//日志输出对象
	private static Logger log = Logger.getLogger(CmsCommonQuestionsController.class);
		
	/** 常见问题处理对象 */
	@Autowired
	private CmsCommonQuestionsService commonQuestionsService;
		
	/**
	 * 取得常见问题列表处理
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	@RequestMapping(value = "/problemHelp")
	public String getQuestionList(@ModelAttribute("pager") DwzPagerMySQL pager,
	@ModelAttribute CommonQuestions commonQuestions,
		Model model,HttpServletRequest request){
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}else{	
			//常见问题信息
			List<CommonQuestions> questionsList = null;

			//常见问题总数
			long total = 0;

			if(commonQuestions == null){
				//取得常见问题信息
				questionsList = commonQuestionsService.getQuestionList();
			}else {
				// 常见问题总数
				total = commonQuestionsService.searchQuestionCount(commonQuestions);
				if (total <= pager.getOffset()) {
					pager.setPageNum(1L);
				}
				//设置分页对象
				commonQuestions.setPager(pager);

				//取得常见问题信息
				questionsList = commonQuestionsService.searchQuestionList(commonQuestions);
				pager.setTotal(total);
			}
			// 取得登录用户信息
			TblUser loginUser = SessionMgr.getWebUser(request);
			//将常见问题信息放到会话中
			model.addAttribute("loginUserLevel", loginUser.getUserLevel());
			model.addAttribute("commonQuestions", commonQuestions);
			model.addAttribute("questionsList", questionsList);
			model.addAttribute("pager", pager);

			//跳转至常见问题信息
			return "backstage/consult/listQuestion";
		}
	}
	
	/**
	 * 添加常见问题
	 *
	 * @author xiay
	 * @param consult
	 * @return
	 */
	@RequestMapping(value = "/newQuestion")
	public String newQuestion(Model model){
		String questionId = "";
		CommonQuestions commonQuestions = new CommonQuestions();
		
		questionId = ProcessInfoCommon.getRandomKey();
		
		commonQuestions.setQuestionId(questionId);
		
		model.addAttribute("commonQuestions", commonQuestions);
		
		return "backstage/consult/newQuestion";
	} 
	
	
	/**
	 * 常见问题添加处理
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	@RequestMapping(value = "/saveCommonquestion", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String saveCommonquestion(@ModelAttribute("commonQuestions") CommonQuestions commonQuestions,
			BindingResult result, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "questionAddPage", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		try {

			// 执行常见问题添加处理，并取得添加成功的常见问题详细信息
			commonQuestionsService.addQuestion(commonQuestions);

			// 设置成功并返回常见问题一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "problemHelp",
					"closeCurrent", "");
			// 如果添加常见问题处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "questionAddPage",
					"", "");

		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 常见问题删除处理
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	@RequestMapping(value = "/deleteQueAll")
	@ResponseBody
	public String deleteQueAll(@RequestParam("questionIds") String questionIds){
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			if(questionIds.indexOf(",")>0){
				String qIds []=questionIds.split(",");
				for (int i = 0; i < qIds.length; i++) {
					CommonQuestions commonQuestions = new CommonQuestions();
					commonQuestions.setQuestionId(JudgeNullUtils.nvlStr(qIds[i]));
							
					// 执行删除处理
					commonQuestionsService.deleteQuestion(commonQuestions);
				}
			}else{
				CommonQuestions commonQuestions = new CommonQuestions();
				commonQuestions.setQuestionId(questionIds);
						
				// 执行删除处理
				commonQuestionsService.deleteQuestion(commonQuestions);
			}

			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "problemHelp", "", "");
			// 如果更新常见问题不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "problemHelp", "",
							"");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}

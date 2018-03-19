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

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.ProcessInfoCommon;
import com.base.common.SessionMgr;
import com.base.util.JudgeNullUtils;
import com.pub.controller.BaseController;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.model.CommonQuestions;
import com.wanma.service.CmsCommonQuestionsService;

/**
 * 常见问题表控制
 * 
 * @author xiay
 *
 */
@Controller
@RequestMapping("/admin/comquestion")
public class VCmsCommonQuestionsController extends BaseController {

	// 日志输出对象
	private static Logger log = Logger
			.getLogger(VCmsCommonQuestionsController.class);

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
	public String getQuestionList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute CommonQuestions commonQuestions, Model model,
			HttpServletRequest request) {
		// 常见问题信息
		List<CommonQuestions> questionsList = null;

		// 常见问题总数
		long total = 0;

		if (commonQuestions == null) {
			// 取得常见问题信息
			questionsList = commonQuestionsService.getQuestionList();
		} else {
			// 常见问题总数
			total = commonQuestionsService.searchQuestionCount(commonQuestions);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			commonQuestions.setPager(pager);

			// 取得常见问题信息
			questionsList = commonQuestionsService
					.searchQuestionList(commonQuestions);
			pager.setTotal(total);
		}
		// 取得登录用户信息
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 将常见问题信息放到会话中
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("commonQuestions", commonQuestions);
		model.addAttribute("questionsList", questionsList);
		model.addAttribute("pager", pager);

		// 跳转至常见问题信息
		return "backstage/consult/listQuestion";
	}

	/**
	 * 添加常见问题
	 *
	 * @author xiay
	 * @param consult
	 * @return
	 */
	@RequestMapping(value = "/newQuestion")
	public String newQuestion(Model model) {
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
	public String saveCommonquestion(
			@ModelAttribute("commonQuestions") CommonQuestions commonQuestions,
			BindingResult result, HttpServletRequest request)
			throws IOException {
		BaseResult baseResult = new BaseSuccess();
		if (result.hasErrors()) {
			return new BaseFail(5000).toString();
		}
		try {
			// 执行常见问题添加处理，并取得添加成功的常见问题详细信息
			commonQuestionsService.addQuestion(commonQuestions);
		} catch (Exception e) {
			log.error(this.getClass() + ".saveCommonquestion() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
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
	public String deleteQueAll(@RequestParam("questionIds") String questionIds) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			if (questionIds.indexOf(",") > 0) {
				String qIds[] = questionIds.split(",");
				for (int i = 0; i < qIds.length; i++) {
					CommonQuestions commonQuestions = new CommonQuestions();
					commonQuestions.setQuestionId(JudgeNullUtils
							.nvlStr(qIds[i]));

					// 执行删除处理
					commonQuestionsService.deleteQuestion(commonQuestions);
				}
			} else {
				CommonQuestions commonQuestions = new CommonQuestions();
				commonQuestions.setQuestionId(questionIds);

				// 执行删除处理
				commonQuestionsService.deleteQuestion(commonQuestions);
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".deleteQueAll() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		return baseResult.toString();
	}
}

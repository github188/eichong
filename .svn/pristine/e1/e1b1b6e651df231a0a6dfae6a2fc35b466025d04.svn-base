package com.wanma.controller;

import java.util.List;
 
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.SessionMgr;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.model.CommitLog;
import com.wanma.service.CmsCommitLogService;

/**
 * 日志管理
 * 
 * @author txd
 * 
 */
@Controller
@RequestMapping("/admin/commitlog")
public class VCmsCommitLogController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(VCmsCommitLogController.class);

	/** 日志处理器 */
	@Autowired
	private CmsCommitLogService commitLogService;

	@RequestMapping(value = "/commitlogList")
	public String getCommitLogList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute CommitLog commitLog, Model model,
			HttpServletRequest request) {

		// 获取登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		commitLog.setUserLevel(loginUser.getUserLevel().toString());
		// 日志信息
		List<CommitLog> commitLogList = null;
		// 日志总数
		long total = 0;
		if (commitLog == null) {
			// 取得日志信息
			commitLogList = commitLogService.getCommitLogList();
		} else {
			// 将当日包含进去
			if (StringUtils.isNotBlank(commitLog.getEnd_create_date()))
				commitLog.setEnd_create_date(commitLog.getEnd_create_date()
						+ " 23:59:59");
			total = commitLogService.searchCommitLogCount(commitLog);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			commitLog.setPager(pager);
			commitLogList = commitLogService.searchCommitLogList(commitLog);
			// 用完之后将时间设置回去
			if (StringUtils.isNotBlank(commitLog.getEnd_create_date()))
				commitLog.setEnd_create_date(commitLog.getEnd_create_date()
						.replace(" 23:59:59", ""));
			pager.setTotal(total);
		}

		// 将日志信息放到会话中
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("commitLog", commitLog);
		model.addAttribute("commitLogList", commitLogList);
		model.addAttribute("pager", pager);

		// 跳转至日志信息
		return "backstage/commitlog/listcommitlog";
	}

	/**
	 * 删除日志列表
	 * 
	 */
	@RequestMapping("/removecommitlog")
	@ResponseBody
	public String removeCommitLog(CommitLog commitLog) {
		log.info("删除日志列表");
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			// 执行删除处理
			commitLogService.deleteById(commitLog);
		} catch (Exception e) {
			log.error(this.getClass() + ".removeCommitLog() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * 批量删除日志列表
	 * 
	 */
	@RequestMapping("/removecommitlogs")
	@ResponseBody
	public String removecommitlogs(@RequestParam("ids") String commitLogs) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			commitLogService.deleteCommitLogs(commitLogs);
		} catch (Exception e) {
			log.error(this.getClass() + ".removecommitlogs() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
	}
}

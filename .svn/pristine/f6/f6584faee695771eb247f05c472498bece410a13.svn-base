package com.wanma.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.wanma.model.TblActivity;
import com.wanma.model.TblCoupon;
import com.wanma.service.CmsActivityService;
import com.wanma.service.CmsCouponDetailService;
  
@Controller
@RequestMapping("/admin/activity")
public class VCmsActivityController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(VCmsActivityController.class);

	@Autowired
	private CmsActivityService cmsActivityService;

	@Autowired
	private CmsCouponDetailService cmsCouponDetailService;

	/**
	 * 加载渠道活动列表
	 * 
	 * @param pager
	 * @param tblActivity
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listActivity")
	public String listActivity(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblActivity tblActivity, Model model,
			HttpServletRequest request) {

		try {
			// 首次进入页面,活动状态选择进行中
			if (tblActivity.getFirst() == "1"
					|| "1".equals(tblActivity.getFirst())) {
				tblActivity.setActStatus("2");
			}
			Date date = new Date();
			TblUser loginUser = SessionMgr.getWebUser(request);
			tblActivity.setActType(2);
			tblActivity.setCurrentDate(date);
			long total = cmsActivityService.getActivityCount(tblActivity);

			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			tblActivity.setPager(pager);
			List<TblActivity> activityList = cmsActivityService
					.getActivityList(tblActivity);
			pager.setTotal(total);
			List<TblActivity> channelType = null;
			channelType = cmsActivityService.getChannelType();

			for (TblActivity row : activityList) {

				if ("1".equals(row.getActStatus())) {
					row.setActStatus("1");
				}
				// 开始时间在当前时间之后,状态为未开始
				else if (row.getActBegindate().after(date)) {
					row.setActStatus("0");
				}
				// 当前时间处于开始时间和结束时间之间,状态为进行中
				else if (row.getActBegindate().before(date)
						&& row.getActEnddate().after(date)) {
					row.setActStatus("2");
				}
				// 结束时间在当前时间之后,状态为已结束
				else if (row.getActEnddate().before(date)) {
					row.setActStatus("3");
				}

			}
			model.addAttribute("activityList", activityList);
			model.addAttribute("pager", pager);
			model.addAttribute("TblActivity", tblActivity);
			model.addAttribute("ChannelType", channelType);
			model.addAttribute("loginUser", loginUser);

		} catch (Exception e) {
			log.error(this.getClass() + ".listActivity() error:"
					+ e.getLocalizedMessage());
			return new BaseFail(5001).toString();
		}
		// 跳转至管理员主页面
		return "backstage/activity/listActivity";
	}

	/**
	 * 终止活动
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/changeActivityState")
	@ResponseBody
	public String changeActivityState(@RequestParam Map<String, Object> params) {
		try {
			cmsActivityService.modifyActStatus(params);
		} catch (Exception e) {
			return "0";
		}
		// 返回处理结果信息
		return "1";
	}

	/**
	 * 跳转到增加渠道活动页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addChaActivity")
	public String addChaActivity(Model model, HttpServletRequest request) {
		// 获取优惠券品种
		List<TblCoupon> couponType = null;
		couponType = cmsCouponDetailService.getCouponType();
		DateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd");
		String nowday = format.format(new Date());
		List<TblActivity> channelType = null;
		channelType = cmsActivityService.getChannelType();
		model.addAttribute("ChannelType", channelType);
		model.addAttribute("nowday", nowday);
		model.addAttribute("couponType", couponType);
		return "backstage/activity/addChaActivity";
	}

	/**
	 * 新增渠道活动
	 * 
	 * @param tblActivity
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveActivity")
	@ResponseBody
	public String saveActivity(TblActivity tblActivity, HttpSession session,
			HttpServletRequest request) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();

		try {
			cmsActivityService.addActivity(tblActivity, request);
		} catch (Exception e) {
			log.error(this.getClass() + ".saveActivity() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * 获取用户活动列表
	 * 
	 * @param pager
	 * @param tblActivity
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listUserActivity")
	public String listUserActivity(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblActivity tblActivity, Model model,
			HttpServletRequest request) {
		if (tblActivity.getFirst() == "1" || "1".equals(tblActivity.getFirst())) {
			tblActivity.setActStatus("2");
		}
		try {
			TblUser loginUser = SessionMgr.getWebUser(request);
			Date date = new Date();
			// 设置活动类型为用户活动
			tblActivity.setActType(1);
			tblActivity.setCurrentDate(date);
			long total = cmsActivityService.getActivityCount(tblActivity);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			tblActivity.setPager(pager);
			List<TblActivity> activityUserList = cmsActivityService
					.getActivityList(tblActivity);
			for (TblActivity row : activityUserList) {

				if ("1".equals(row.getActStatus())) {
					row.setActStatus("1");
				}
				// 开始时间在当前时间之后,状态为未开始
				else if (row.getActBegindate().after(date)) {
					row.setActStatus("0");
				}
				// 当前时间处于开始时间和结束时间之间,状态为进行中
				else if (row.getActBegindate().before(date)
						&& row.getActEnddate().after(date)) {
					row.setActStatus("2");
				}
				// 结束时间在当前时间之后,状态为已结束
				else if (row.getActEnddate().before(date)) {
					row.setActStatus("3");
				}
			}
			pager.setTotal(total);

			model.addAttribute("activityUserList", activityUserList);
			model.addAttribute("pager", pager);
			model.addAttribute("TblActivity", tblActivity);
			model.addAttribute("loginUser", loginUser);
		} catch (Exception e) {
			log.error(this.getClass() + ".listUserActivity() error:"
					+ e.getLocalizedMessage());
			return new BaseFail(5001).toString();
		}
		// 跳转至管理员主页面
		return "backstage/activity/listUserActivity";
	}

	/**
	 * 跳转到增加用户活动页面
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addUserActivity")
	public String addUserActivity(Model model, HttpServletRequest request) {
		// 获取优惠券品种
		List<TblCoupon> couponType = null;
		couponType = cmsCouponDetailService.getCouponType();
		java.text.DateFormat format = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		String nowday = format.format(new Date());
		model.addAttribute("nowday", nowday);
		model.addAttribute("couponType", couponType);

		return "backstage/activity/addUserActivity";
	}

	/**
	 * 新增用户活动
	 * 
	 * @param tblActivity
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveUserActivity")
	@ResponseBody
	public String saveUserActivity(TblActivity tblActivity,
			HttpSession session, HttpServletRequest request) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			long count = cmsActivityService.checkActUnique(tblActivity);
			if (count > 0) {
				baseResult = new BaseFail("在此活动期间内,已存在同类型的活动,不能重复新增！");
			} else {
				cmsActivityService.addUserActivity(tblActivity, request);
			}

		} catch (Exception e) {
			log.error(this.getClass() + ".saveUserActivity() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
	}

}

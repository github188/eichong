package com.wanma.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblActivity;
import com.wanma.model.TblCarinfo;
import com.wanma.model.TblCoupon;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCouponDetailService;

@Controller
@RequestMapping("/admin/coupon")
public class CmsCouponDetailController {

	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsCouponDetailController.class);

	@Autowired
	private CmsCouponDetailService cmsCouponDetailService;

	/**
	 * 获取现金券明细列表
	 * 
	 * @param pager
	 * @param tblCoupon
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/couponDetail")
	public String couponDetail(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblCoupon tblCoupon, Model model,
			HttpServletRequest request) {

		try {
			if (tblCoupon.getFirst() == "1" || "1".equals(tblCoupon.getFirst())) {
				tblCoupon.setCpstates(3);
				tblCoupon.setCpActivitytype("2");
			}

			Date date = new Date();
			TblUser loginUser = SessionMgr.getWebUser(request);
			// 获取现金券品种下拉框数据
			List<TblCoupon> couponType = null;
			couponType = cmsCouponDetailService.getCouponType();
			// 获取活动下拉框数据
			List<TblCoupon> activityType = null;
			activityType = cmsCouponDetailService.getActivityType(tblCoupon);

			tblCoupon.setCurrentDate(date);

			long total = cmsCouponDetailService.getCouponCount(tblCoupon);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			tblCoupon.setPager(pager);
			List<TblCoupon> couponList = cmsCouponDetailService
					.getCouponList(tblCoupon);
			pager.setTotal(total);
/*
			for (TblCoupon row : couponList) {
				//1、未兑换未过期(未兑换：cp_userId未绑定用户;未过期：结束时间在当前时间之后)
				if (row.getCpUserid() == 0&& row.getCpEnddate().after(date)) {
					row.setCpstates(1);
				}
				//2、未兑换已过期(未兑换：cp_userId未绑定用户;已过期：结束时间在当前时间之前)
				if (row.getCpUserid() == 0&& row.getCpEnddate().before(date)) {
					row.setCpstates(2);
				}
				//3、已兑换已使用(已兑换：cp_userId绑定用户;已使用：cp_Status为1)
				if(row.getCpUserid() != 0 && row.getCpStatus() == 1 ){
					row.setCpstates(3);
				}
				//4、已兑换未使用未过期(已兑换：cp_userId绑定用户;未使用：cp_Status不为1;未过期：结束时间在当前时间之后)
				if(row.getCpUserid() != 0 && row.getCpStatus() != 1&& row.getCpEnddate().after(date) ){
					row.setCpstates(4);
				}
				//5、已兑换未使用已过期(已兑换：cp_userId绑定用户;未使用：cp_Status不为1;已过期：结束时间在当前时间之前)
				if(row.getCpUserid() != 0 && row.getCpStatus() != 1&& row.getCpEnddate().before(date) ){
					row.setCpstates(5);
				}
				
			}
*/
			model.addAttribute("couponList", couponList);
			model.addAttribute("couponType", couponType);
			model.addAttribute("activityType", activityType);
			model.addAttribute("pager", pager);
			model.addAttribute("TblCoupon", tblCoupon);
			model.addAttribute("loginUser", loginUser);

		} catch (Exception e) {

			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/coupon/listCoupon";
	}

	/**
	 * 删除现金券明细数据
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/removeCouponDetail")
	@ResponseBody
	public String removeCouponDetail(@RequestParam("ids") String ids) {
		// 处理结果信息
		DwzAjaxResult dwzResult = new DwzAjaxResult("200", "删除成功", "", "", "");
		try {
			String[] idArray = ids.split(",");

			for (String id : idArray) {

				long i = cmsCouponDetailService.selectCoupon(id);

				if (i == 1) {
					dwzResult = new DwzAjaxResult("300", "已使用现金券不能删除！", "", "",
							"");
					return dwzResult.toJSONString();
				}
			}

			// 不出错执行删除操作
			for (String id : idArray) {
				cmsCouponDetailService.deteleCouponDetail(id);

			}
			return dwzResult.toJSONString();
		} catch (Exception e) {
			// 出错日志
			log.error("操作失败", e);
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "操作失败:系统出错", "", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	@RequestMapping("searchActivityList")
	@ResponseBody
	public String searchCarinfoList(@RequestParam String state) {
		List<TblActivity> list = new ArrayList<TblActivity>();
		if (StringUtils.isBlank(state)) {
			return new AccessErrorResult().toString();
		}
		
		try {
			list = cmsCouponDetailService.getActivityList(new Integer(state));
		} catch (Exception e) {
			log.error(e.getMessage());
			// 返回活动列表错误信息
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取活动列表-end************************");
		return new AccessSuccessResult(list).toString();
	}
}

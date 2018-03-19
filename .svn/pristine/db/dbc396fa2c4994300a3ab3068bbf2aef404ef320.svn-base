package com.wanma.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.SessionMgr;
import com.base.common.WanmaConstants;
import com.base.util.DateUtil;
import com.base.util.ExcelUtil;
import com.pub.controller.BaseController;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.model.TblBespoke;
import com.wanma.service.CmsBespokeService;
 
/**
 * 运营管理-订单管理-预约订单
 * 
 * @author xiay
 * 
 */
@Controller
@RequestMapping("/admin/order")
public class CmsOrderBespokeController extends BaseController {

	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsOrderBespokeController.class);

	/** 预约处理器 */
	@Autowired
	private CmsBespokeService tblBespokeService;

	/**
	 * 跳转到预约列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listBespoke")
	public String listBespoke(HttpServletRequest request) {
		return "backstage/order/listBespoke";
	}

	/**
	 * 取得预约列表处理
	 * 
	 * @throws 无
	 */
	@RequestMapping(value = "/bespokeOrder")
	@ResponseBody
	public String bespokeOrder(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblBespoke tblBespoke, Model model,
			HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL) {// 个体商家
			tblBespoke.setBespUser(loginUser.getUserId() + "");
		} else if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_BUSINESS) {// 纯商家
			tblBespoke.setBespUser(loginUser.getUserAccount());
		}

		// 登录预约信息
		List<TblBespoke> bespokeList = null;
		// 预约总数
		long total = 0;

		if (tblBespoke == null) {
			// 取得预约列表
			bespokeList = tblBespokeService.getBespokeList();
		} else {
			if (("2").equals(tblBespoke.getBespOrderType())) {
				tblBespoke.setBespOrderType("");
				tblBespoke.setBespBespokestatus(7);
			}
			// 预约总数
			total = tblBespokeService.searchBespokeCount(tblBespoke);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblBespoke.setPager(pager);
			// 取得预约列表
			bespokeList = tblBespokeService.searchBespokeList(tblBespoke);
			if (tblBespoke.getBespBespokestatus() != null
					&& tblBespoke.getBespBespokestatus() == 7) {
				tblBespoke.setBespOrderType("2");
			}

			pager.setTotal(total);
		}

		baseResult = new BaseResult(bespokeList, pager);
		return baseResult.toString();
	}

	/**
	 * 跳转到预约收益
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listBespokeEarn")
	public String listBespokeEarn(HttpServletRequest request) {
		return "backstage/order/listBespokeEarn";
	}

	/**
	 * 取得预约列表处理 --纯商家
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/bespokeEarnOrder")
	@ResponseBody
	public String bespokeEarnOrder(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblBespoke tblBespoke, Model model,
			HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
		// 登录预约信息
		List<TblBespoke> firmBespokeList = null;
		// 预约总数
		long total = 0;
		if (("2").equals(tblBespoke.getBespOrderType())) {
			tblBespoke.setBespOrderType("");
			tblBespoke.setBespBespokestatus(7);
		}
		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		tblBespoke.setBespUser(loginUser.getUserId().toString());
		tblBespoke.setUserLevel(loginUser.getUserLevel().toString());
		// 预约总数
		total = tblBespokeService.searchFirmBespokeCount(tblBespoke);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置分页对象
		tblBespoke.setPager(pager);
		// 取得预约列表
		firmBespokeList = tblBespokeService.searchFirmBespokeList(tblBespoke);
		pager.setTotal(total);
		if (tblBespoke.getBespBespokestatus() != null
				&& tblBespoke.getBespBespokestatus() == 7) {
			tblBespoke.setBespOrderType("2");
		}

		baseResult = new BaseResult(firmBespokeList, pager);
		return baseResult.toString();
	}

	/**
	 * 预约收益导出
	 * 
	 * @param pager
	 * @param tblBespoke
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/bespokeEarnOrderExport")
	@ResponseBody
	public void bespokeEarnOrderExport(@ModelAttribute TblBespoke tblBespoke,
			Model model, HttpServletRequest request,
			HttpServletResponse response) {
		// 登录预约信息
		List<TblBespoke> firmBespokeList = null;
		if (("2").equals(tblBespoke.getBespOrderType())) {
			tblBespoke.setBespOrderType("");
			tblBespoke.setBespBespokestatus(7);
		}
		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		tblBespoke.setBespUser(loginUser.getUserId().toString());
		tblBespoke.setUserLevel(loginUser.getUserLevel().toString());
		// 取得预约列表
		firmBespokeList = tblBespokeService.searchFirmBespokeList(tblBespoke);

		// excel导出
		ExcelUtil eu = new ExcelUtil();
		// 转换成excel可用的数据格式
		List<String[]> dataList = new ArrayList<String[]>();
		String[] data = new String[] { "订单编号", "桩体编号", "电桩名称", "商家名称", 
				"收益", "预约开始时间", "预约结束时间", "实际预约结束时间", "订单状态" };
		dataList.add(data);

		for (TblBespoke obj : firmBespokeList) {
			data = new String[9];
			data[0] = obj.getBespResepaymentcode();
			data[1] = obj.getEleCode();
			data[2] = obj.getExtValue1();
			data[3] = obj.getComName();
			data[4] = obj.getBespBespokeprice().toString();
			data[5] = DateUtil.format(obj.getBespBeginTime());
			data[6] = DateUtil.format(obj.getBespEndTime());
			data[7] = DateUtil.format(obj.getBespRealityTime());
			data[8] = obj.getBespOrderStateName();
			dataList.add(data);
		}
		try {
			eu.export(dataList, response, "预约收益列表.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(this.getClass() + ".bespokeEarnOrderExport() error:"
					+ e.getLocalizedMessage());
		}
	}

	/**
	 * 取得预约列表处理 --个体商家
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/subsUnitOrder")
	public String getUnitBespokeList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblBespoke tblBespoke, Model model,
			HttpServletRequest request) {

		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		// if
		// (loginUser.getUserLevel().toString().equals(WanmaConstants.USER_LEVER_personl3))
		// {// 个体商家
		// tblBespoke.setBespUser(loginUser.getUserId()+"");
		// } else if (loginUser.getUserLevel().toString().equals(
		// WanmaConstants.USER_LEVER_merchant2)) {// 纯商家
		// tblBespoke.setBespUser(loginUser.getUserAccount());
		// }

		// 登录预约信息
		List<TblBespoke> unitBespokeList = null;
		// 预约总数
		long total = 0;

		if (tblBespoke == null) {
			// 取得预约列表
			unitBespokeList = tblBespokeService.getUnitBespokeList();
		} else {
			if (("2").equals(tblBespoke.getBespOrderType())) {
				tblBespoke.setBespOrderType("");
				tblBespoke.setBespBespokestatus(7);
			}
			tblBespoke.setBespUser(loginUser.getUserId().toString());
			tblBespoke.setUserLevel(loginUser.getUserLevel().toString());
			// 预约总数
			total = tblBespokeService.searchUnitBespokeCount(tblBespoke);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblBespoke.setPager(pager);
			// 取得预约列表
			unitBespokeList = tblBespokeService
					.searchUnitBespokeList(tblBespoke);
			pager.setTotal(total);
			if (tblBespoke.getBespBespokestatus() != null
					&& tblBespoke.getBespBespokestatus() == 7) {
				tblBespoke.setBespOrderType("2");
			}
		}

		// 将预约信息放到会话中
		model.addAttribute("tblBespoke", tblBespoke);
		model.addAttribute("unitBespokeList", unitBespokeList);
		model.addAttribute("pager", pager);

		// 跳转至管理员主页面
		return "backstage/UnitOrder/listUnitBespoke";
	}
}

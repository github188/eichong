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
import com.base.util.ExcelUtil;
import com.pub.controller.BaseController;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.model.TblChargingOrder;
import com.wanma.service.CmsChargOrderService;
 
/**
 * 运营管理-订单管理-充电订单
 * 
 * @author xiay
 *
 */
@Controller
@RequestMapping("/admin/order")
public class CmsOrderChargeController extends BaseController {

	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsOrderChargeController.class);

	/** 充电处理器 */
	@Autowired
	private CmsChargOrderService tblChargOrderService;

	/**
	 * 跳转到充电订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listCharge")
	public String listCharge(HttpServletRequest request) {
		return "backstage/order/listCharge";
	}

	/**
	 * 充电订单
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/chargeOrder")
	@ResponseBody
	public String chargeOrder(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblChargingOrder tblChargingOrder, Model model,
			HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL) {// 个体商家
			tblChargingOrder.setChorUser(loginUser.getUserId() + "");
		} else if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_BUSINESS) {// 纯商家
			tblChargingOrder.setChorUser(loginUser.getUserAccount());
		}
		// 登录充电消费信息
		List<TblChargingOrder> chargeList = null;
		// 充电消费总数
		long total = 0;

		if (tblChargingOrder == null) {
			// 取得充电消费列表
			chargeList = tblChargOrderService.getChargeList();
		} else {
			// 充电消费总数
			total = tblChargOrderService.searchChargeCount(tblChargingOrder);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblChargingOrder.setPager(pager);
			// 取得充电消费列表
			chargeList = tblChargOrderService
					.searchChargeList(tblChargingOrder);
			pager.setTotal(total);
		}

		baseResult = new BaseResult(chargeList, pager);
		return baseResult.toString();
	}

	@RequestMapping(value = "/chargeOrderDetailPage")
	public String chargeOrderDetailPage(HttpServletRequest request) {
		return "backstage/order/listChargeDetail";
	}

	@RequestMapping(value = "/chargeOrderDetail")
	@ResponseBody
	public String chargeOrderDetail(TblChargingOrder tblChargingOrder,
			HttpServletRequest request) {
		tblChargingOrder=tblChargOrderService.findCharge(tblChargingOrder.getPkChargingorder());
		tblChargingOrder.setExtValue2(WanmaConstants.getConfigName("3", tblChargingOrder.getExtValue2()));
		return new BaseResult(tblChargingOrder).toString();
	}

	/**
	 * 充电订单导出
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/chargeOrderExport")
	@ResponseBody
	public void chargeOrderExport(
			@ModelAttribute TblChargingOrder tblChargingOrder,
			HttpServletRequest request, HttpServletResponse response) {
		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL) {// 个体商家
			tblChargingOrder.setChorUser(loginUser.getUserId() + "");
		} else if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_BUSINESS) {// 纯商家
			tblChargingOrder.setChorUser(loginUser.getUserAccount());
		}
		// 登录充电消费信息
		List<TblChargingOrder> chargeList = null;
		if (tblChargingOrder == null) {
			// 取得充电消费列表
			chargeList = tblChargOrderService.getChargeList();
		} else {
			// 取得充电消费列表
			chargeList = tblChargOrderService
					.searchChargeList(tblChargingOrder);
		}

		// excel导出
		ExcelUtil eu = new ExcelUtil();
		// 转换成excel可用的数据格式
		List<String[]> dataList = new ArrayList<String[]>();
		String[] data = new String[] { "订单编号", "桩体编号", "电桩名称", "商家名称", "手机号",
				"金额", "电量", "实际优惠", "充电时间段", "订单状态" };
		dataList.add(data);

		for (TblChargingOrder obj : chargeList) {
			data = new String[10];
			data[0] = obj.getChorCode();
			data[1] = obj.getEleCode();
			data[2] = obj.getExtValue1();
			data[3] = obj.getOwnerShip();
			data[4] = obj.getUserPhone();
			data[5] = obj.getChorMoeny();
			data[6] = obj.getChorQuantityelectricity();
			data[7] = obj.getCouponMoney();
			data[8] = obj.getChorTimequantum();
			data[9] = obj.getChargingstatusName();
			dataList.add(data);
		}
		try {
			eu.export(dataList, response, "充电订单列表.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(this.getClass() + ".chargeOrderExport() error:"
					+ e.getLocalizedMessage());
		}
	}

	/**
	 * 跳转到充电收益列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listChargeEarn")
	public String listChargeEarn(HttpServletRequest request) {
		return "backstage/order/listChargeEarn";
	}

	/**
	 * 充电收益列表
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/chargeEarnOrder")
	@ResponseBody
	public String chargeEarnOrder(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblChargingOrder tblChargingOrder, Model model,
			HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 登录充电消费信息
		List<TblChargingOrder> ChargeFirmList = null;
		// 充电消费总数
		long total = 0;
		tblChargingOrder.setLoginUserId(loginUser.getUserId().toString());
		tblChargingOrder.setUserLevel(loginUser.getUserLevel().toString());
		// 充电消费总数
		total = tblChargOrderService.searchFirmChargeCount(tblChargingOrder);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置分页对象
		tblChargingOrder.setPager(pager);
		// 取得充电消费列表
		ChargeFirmList = tblChargOrderService
				.searchFirmChargeList(tblChargingOrder);
		pager.setTotal(total);

		baseResult = new BaseResult(ChargeFirmList, pager);
		return baseResult.toString();
	}

	/**
	 * 充电收益导出
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/chargeEarnOrderExport")
	@ResponseBody
	public void chargeEarnOrderExport(
			@ModelAttribute TblChargingOrder tblChargingOrder,
			HttpServletRequest request, HttpServletResponse response) {
		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 登录充电消费信息
		List<TblChargingOrder> chargeFirmList = null;
		tblChargingOrder.setLoginUserId(loginUser.getUserId().toString());
		tblChargingOrder.setUserLevel(loginUser.getUserLevel().toString());
		// 取得充电消费列表
		chargeFirmList = tblChargOrderService
				.searchFirmChargeList(tblChargingOrder);

		// excel导出
		ExcelUtil eu = new ExcelUtil();
		// 转换成excel可用的数据格式
		List<String[]> dataList = new ArrayList<String[]>();
		String[] data = new String[] { "订单编号", "桩体编号", "电桩名称", "商家名称", "收益",
				"电量", "充电时间段", "订单状态" };
		dataList.add(data);
		for (TblChargingOrder obj : chargeFirmList) {
			data = new String[10];
			data[0] = obj.getChorCode();
			data[1] = obj.getEleCode();
			data[2] = obj.getExtValue1();
			data[3] = obj.getComName();
			data[4] = obj.getChorChargemoney().toString();
			data[5] = obj.getChorQuantityelectricity();
			data[6] = obj.getChorTimequantum();
			data[7] = obj.getChargingstatusName();
			dataList.add(data);
		}
		try {
			eu.export(dataList, response, "充电收益列表.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(this.getClass() + ".chargeOrderExport() error:"
					+ e.getLocalizedMessage());
		}
	}

	/**
	 * 取得充电消费列表处理 -- 个体商家充电消费
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/elecUnitOrder")
	public String getUnitChargeList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblChargingOrder tblChargingOrder, Model model,
			HttpServletRequest request) {

		// 登陆用户
		// if
		// (loginUser.getUserLevel().toString().equals(WanmaConstants.USER_LEVER_personl3))
		// {// 个体商家
		// tblChargingOrder.setChorUser(loginUser.getUserId()+"");
		// } else if (loginUser.getUserLevel().toString().equals(
		// WanmaConstants.USER_LEVER_merchant2)) {// 纯商家
		// tblChargingOrder.setChorUser(loginUser.getUserAccount());
		// }

		// 登录充电消费信息
		List<TblChargingOrder> ChargeUnitList = null;
		// 充电消费总数
		long total = 0;

		if (tblChargingOrder == null) {
			// 取得充电消费列表
			ChargeUnitList = tblChargOrderService.getUnitChargeList();
		} else {
			TblUser loginUser = SessionMgr.getWebUser(request);
			tblChargingOrder.setLoginUserId(loginUser.getUserId().toString());
			tblChargingOrder.setUserLevel(loginUser.getUserLevel().toString());
			// 充电消费总数
			total = tblChargOrderService
					.searchUnitChargeCount(tblChargingOrder);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblChargingOrder.setPager(pager);
			// 取得充电消费列表
			ChargeUnitList = tblChargOrderService
					.searchUnitChargeList(tblChargingOrder);
			pager.setTotal(total);
		}

		// 将充电消费信息放到会话中
		model.addAttribute("tblChargingOrder", tblChargingOrder);
		model.addAttribute("ChargeUnitList", ChargeUnitList);
		model.addAttribute("pager", pager);

		// 跳转至充电消费主页面
		return "backstage/UnitOrder/listUnitCharge";
	}

}

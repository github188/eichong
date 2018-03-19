package com.wanma.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblChargingOrder;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wanma.model.TblUser;
import com.wanma.service.CmsChargOrderService;
import com.wanma.service.impl.CmsChargOrderExportServiceImpl;
import com.wanma.web.support.utils.HttpRequest;

/**
 * 充电处理控制器
 * 
 * @author xiay
 *
 */
@Controller
@RequestMapping("/admin/order")
public class CmsChargeOrderController extends BaseController {

	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsChargeOrderController.class);
	/** 导出合作商充电订单 */
	@Autowired
	private CmsChargOrderExportServiceImpl ChargOrderExportService;
	/** 充电处理器 */
	@Autowired
	private CmsChargOrderService tblChargOrderService;

	/**
	 * 取得充电消费列表处理
	 * 
	 * @author xiay
	 * @return
	 * @throws ParseException
	 * @throws 无
	 */
	@RequestMapping(value = "/elecOrder")
	public String getChargeList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblChargingOrder tblChargingOrder, Model model,
			HttpServletRequest request) throws ParseException {

		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_BUSINESS_NORMAL) {// 个体商家
			tblChargingOrder.setChorUser(loginUser.getUserId() + "");
		} else if (loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_BUSINESS) {// 纯商家
			tblChargingOrder.setChorUser(loginUser.getUserAccount());
		}

		// 登录充电消费信息
		List<TblChargingOrder> ChargeList = null;
		// 充电消费总数
		long total = 0;

		if (tblChargingOrder == null) {
			// 取得充电消费列表
			ChargeList = tblChargOrderService.getChargeList();
		} else {
			// 充电消费总数
			total = tblChargOrderService.searchChargeCount(tblChargingOrder);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblChargingOrder.setPager(pager);
			// 取得充电消费列表
			ChargeList = tblChargOrderService
					.searchChargeList(tblChargingOrder);
			pager.setTotal(total);
		}
		// 计算充电时长
		for (int i = 0; i < ChargeList.size(); i++) {
			String beginChargetime = ChargeList.get(i).getBeginChargetime();
			String endChargetime = ChargeList.get(i).getEndChargetime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginDate = sdf.parse(beginChargetime);
			Date endDate = sdf.parse(endChargetime);
			double c = (endDate.getTime() - beginDate.getTime()) / 60000d;
			String minute = String.format("%.2f", c);
			ChargeList.get(i).setChargeTimeMinute(minute);
		}
		// 将充电消费信息放到会话中
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		model.addAttribute("tblChargingOrder", tblChargingOrder);
		model.addAttribute("ChargeList", ChargeList);
		model.addAttribute("pager", pager);

		// 跳转至充电消费主页面
		return "backstage/order/listCharge";
	}

	/**
	 * 取得充电消费列表处理 -- 个体商家充电消费
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/elecUnitOrder")
	public String getUnitChargeList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
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

	/**
	 * 取得充电消费列表处理 -- 个体商家充电消费
	 * 
	 * @author xiay
	 * @return
	 * @throws ParseException
	 * @throws 无
	 */
	@RequestMapping(value = "/elecFirmOrder")
	public String getFirmChargeList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblChargingOrder tblChargingOrder, Model model,
			HttpServletRequest request) throws ParseException {

		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		// if
		// (loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS_NORMAL)
		// {// 个体商家
		// tblChargingOrder.setChorUser(loginUser.getUserId()+"");
		// } else if
		// (loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS) {//
		// 纯商家
		// tblChargingOrder.setChorUser(loginUser.getUserAccount());
		// }
		//
		// 登录充电消费信息
		List<TblChargingOrder> ChargeFirmList = null;
		// 充电消费总数
		long total = 0;

		if (tblChargingOrder == null) {
			// 取得充电消费列表
			ChargeFirmList = tblChargOrderService.getFirmChargeList();
		} else {
			tblChargingOrder.setLoginUserId(loginUser.getUserId().toString());
			tblChargingOrder.setUserLevel(loginUser.getUserLevel().toString());
			// 充电消费总数
			total = tblChargOrderService
					.searchFirmChargeCount(tblChargingOrder);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblChargingOrder.setPager(pager);
			// 取得充电消费列表
			ChargeFirmList = tblChargOrderService
					.searchFirmChargeList(tblChargingOrder);
			pager.setTotal(total);
		}
		// 计算充电时长
		for (int i = 0; i < ChargeFirmList.size(); i++) {
			String beginChargetime = ChargeFirmList.get(i).getBeginChargetime();
			String endChargetime = ChargeFirmList.get(i).getEndChargetime();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginDate = sdf.parse(beginChargetime);
			Date endDate = sdf.parse(endChargetime);
			double c = (endDate.getTime() - beginDate.getTime()) / 60000d;
			String minute = String.format("%.2f", c);
			ChargeFirmList.get(i).setChargeTimeMinute(minute);
		}
		// 将充电消费信息放到会话中
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		model.addAttribute("tblChargingOrder", tblChargingOrder);
		model.addAttribute("ChargeFirmList", ChargeFirmList);
		model.addAttribute("pager", pager);

		// 跳转至充电消费主页面
		return "backstage/pureBusiness/listBusCharge";
	}

	/**
	 * 取得充电消费列表处理 -- 合作商充电消费
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/elecPartnerOrder")
	public String elecPartnerOrder(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblChargingOrder tblChargingOrder, Model model,
			HttpServletRequest request) {

		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 登录充电消费信息
		List<TblChargingOrder> ChargePartnerList = null;
		// 充电消费总数
		long total = 0;
		tblChargingOrder.setLoginUserId(loginUser.getUserId().toString());
		tblChargingOrder.setUserLevel(loginUser.getUserLevel().toString());
		// 充电消费总数
		total = tblChargOrderService.searchPartnerChargeCount(tblChargingOrder);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置分页对象
		tblChargingOrder.setPager(pager);
		// 取得充电消费列表
		ChargePartnerList = tblChargOrderService
				.searchPartnerChargeList(tblChargingOrder);
		pager.setTotal(total);

		// 将充电消费信息放到会话中
		model.addAttribute("tblChargingOrder", tblChargingOrder);
		model.addAttribute("ChargePartnerList", ChargePartnerList);
		model.addAttribute("pager", pager);

		// 跳转至充电消费主页面
		return "backstage/partnerBussiness/listPartnerBusCharge";
	}

	/**
	 * 导出合作商充电消费订单
	 * 
	 * @param paramModel
	 * @param req
	 * @param res
	 */
	@RequestMapping(value = "/PartnerBusChargeExport")
	@ResponseBody
	public void PartnerBusChargeExport(
			@ModelAttribute("paramModel") TblChargingOrder paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出合作商充电消费订单报表");
		try {
			ChargOrderExportService.export(res, paramModel, "合作商充电消费订单报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("合作商充电消费订单报表，数据写入出错");
		}
	}

	@RequestMapping(value = "/getSettleInfo")
	@ResponseBody
	public String getSettleInfo(@RequestParam Map<String, String> params,
			HttpServletRequest request) throws ParseException {

		TblChargingOrder tbl = tblChargOrderService.getSettleInfo(params);
		Map<String, Object> data = new HashMap<String, Object>();

		data.put("commonStatus", tbl.getCommStatus());
		data.put("chReFrozenAmt", tbl.getChorChargemoney());
		data.put("chRePayMode", tbl.getChRePayMode());
		data.put("orderId", tbl.getPkChargingorder());
		// 如果订单不是待支付或支付成功，既订单是结算订单，则不进行下面操作
		if (!"2".equals(tbl.getChorChargingstatus())
				&& !"1".equals(tbl.getChorChargingstatus())) {
			// orderFlag=1 订单为已结算订单
			data.put("orderFlag", "1");
		} else {
			params.put("pk_ChargingOrder", tbl.getPkChargingorder());
			params.put("chOr_PileNumber", tbl.getChorPilenumber());
			params.put("chOr_Muzzle", String.valueOf(tbl.getChorMuzzle()));
			params.put("begin_charge_time", tbl.getBeginChargetime());
			params.put("chRe_FrozenAmt",
					String.valueOf(tbl.getChorChargemoney()));
			params.put("chargingMode", tbl.getChargingMode());
			MessageManager manager = MessageManager.getMessageManager();
			String result = HttpRequest.post(
					manager.getSystemProperties("hbaseUrl")
							+ "/getPersonSettle.do", params);
			Map<String, String> map = (Map<String, String>) JSON.parse(result);
			if ("0".equals(map.get("flag"))) {
				// orderFlag=0 未查到实时数据
				data.put("orderFlag", "0");
			} else if ("1".equals(map.get("flag"))) {
				// orderFlag=2 异常订单之后，该桩继续产生了订单，请慎重结算，
				data.put("orderFlag", map.get("flag"));
				data.put("money", map.get("money"));
				data.put("power", map.get("power"));

				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				Long time = Long.parseLong(map.get("charingTime"));
				String d = format.format(time);
				data.put("charingTime", d);
			}

		}
		// 跳转至充电消费主页面
		return JSONObject.toJSONString(data);
	}

	@RequestMapping(value = "/personSettle")
	@ResponseBody
	public String personSettle(@RequestParam Map<String, String> params,
			HttpServletRequest request) {
		String flag = "0";
		// 预充金额
		Double settleAmt = Double.valueOf(params.get("settleAmt"));
		// 消费金额
		Double settleMoney = Double.valueOf(params.get("settleMoney"));
		if (settleAmt < settleMoney) {
			// 返回预充金额小于消费金额，无法人工结算
			return "2";

		} else {
			params.put("returnMonney",
					String.format("%.4f", settleAmt - settleMoney));
			params.put("qutumTime",params.get("settleEnd").substring(5, 16));
			flag = tblChargOrderService.updateSettle(params);
		}

		/* System.out.println("....."); */
		// 跳转至充电消费主页面
		return flag;
	}

}

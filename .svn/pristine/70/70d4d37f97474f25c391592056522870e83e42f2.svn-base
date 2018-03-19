/**     
 * @Title:  CmsFinanceController.java   
 * @Package com.wanma.controller   
 * @Description:   财务报表
 * @author: Android_Robot     
 * @date:   2016年1月9日 上午10:25:12   
 * @version V1.0     
 */
package com.wanma.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblChargingOrder;
import com.wanma.model.TblInvoice;
import com.wanma.model.TblPurchasehistory;
import com.wanma.model.TblUser;
import com.wanma.service.CmsFinanceService;

/**
 * @author bc
 *
 */
@Controller
@RequestMapping("/admin/finance")
public class CmsFinanceController {

	@Autowired
	private CmsFinanceService cmsFinanceService;

	/**
	 * 个体商家消费明细表
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/personConsumeDetail")
	public String personConsumeDetail(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request, @RequestParam Map<String, Object> params) {
		// 获取地区信息
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		String proviceCode = (String) params.get("proviceCode");
		if (StringUtils.isNotBlank(proviceCode)) {
			List<Object> cityList = new ArrayList<Object>();
			for (String citycode : WanmaConstants.provinceCityMap
					.get(proviceCode)) {
				Map<String, Object> cityMap = WanmaConstants.cityMap;
				cityList.add(cityMap.get(citycode));
			}
			model.addAttribute("cityList", cityList);
		}
		setUser(params, request);
		long total = cmsFinanceService.personConsumeDetailCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		pager.setTotal(total);
		params.put("pager", pager);
		List<Map<String, Object>> list = cmsFinanceService
				.personConsumeDetail(params);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		return "backstage/finance/personConsumptionDetail";
	}

	/**
	 * 个体商家消费统计表
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/personConsumeStatistic")
	public String personConsumeStatistic(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request, @RequestParam Map<String, Object> params) {
		// 获取地区信息
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		String proviceCode = (String) params.get("proviceCode");
		if (StringUtils.isNotBlank(proviceCode)) {
			List<Object> cityList = new ArrayList<Object>();
			for (String citycode : WanmaConstants.provinceCityMap
					.get(proviceCode)) {
				Map<String, Object> cityMap = WanmaConstants.cityMap;
				cityList.add(cityMap.get(citycode));
			}
			model.addAttribute("cityList", cityList);
		}
		setUser(params, request);
		long total = cmsFinanceService.personConsumeStatisticCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		pager.setTotal(total);
		params.put("pager", pager);
		List<Map<String, Object>> list = cmsFinanceService
				.personConsumeStatistic(params);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		return "backstage/finance/personConsumeStatistic";
	}

	/**
	 * 个体商家充电收益明细表
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/personChargeDetail")
	public String personChargeDetail(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request, @RequestParam Map<String, Object> params) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		setUser(params, request);
		long total = cmsFinanceService.personChargeDetailCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		pager.setTotal(total);
		params.put("pager", pager);
		List<Map<String, Object>> list = cmsFinanceService
				.personChargeDetail(params);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		return "backstage/finance/personChargeDetail";
	}

	/**
	 * 个体商家预约收益明细表
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/personBespokeDetail")
	public String personBespokeDetail(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request, @RequestParam Map<String, Object> params) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		setUser(params, request);
		long total = cmsFinanceService.personBespokeDetailCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		pager.setTotal(total);
		params.put("pager", pager);
		List<Map<String, Object>> list = cmsFinanceService
				.personBespokeDetail(params);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		return "backstage/finance/personBespokeDetail";
	}

	/**
	 * 个体商家 充电收益统计表
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/personChargeStatistic")
	public String personChargeStatistic(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request, @RequestParam Map<String, Object> params) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		setUser(params, request);

		long total = cmsFinanceService.personChargeStatisticCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		pager.setTotal(total);
		params.put("pager", pager);

		List<Map<String, Object>> list = cmsFinanceService
				.personChargeStatistic(params);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		return "backstage/finance/personChargeStatistic";
	}

	/**
	 * 个体商家预约收益统计表
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/personBespokeStatistic")
	public String personBespokeStatistic(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request, @RequestParam Map<String, Object> params) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		setUser(params, request);

		long total = cmsFinanceService.personBespokeStatisticCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		pager.setTotal(total);
		params.put("pager", pager);

		List<Map<String, Object>> list = cmsFinanceService
				.personBespokeStatistic(params);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		return "backstage/finance/personBespokeStatistic";
	}

	/**
	 * 纯商家充电收益明细表
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/businessChargeDetail")
	public String businessChargeDetail(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request, @RequestParam Map<String, Object> params) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		setUser(params, request);
		long total = cmsFinanceService.businessChargeDetailCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		pager.setTotal(total);
		params.put("pager", pager);
		List<Map<String, Object>> list = cmsFinanceService
				.businessChargeDetail(params);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		return "backstage/finance/businessChargeDetail";
	}

	/**
	 * 纯商家预约收益明细表
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/businessBespokeDetail")
	public String businessBespokeDetail(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request, @RequestParam Map<String, Object> params) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		setUser(params, request);
		long total = cmsFinanceService.businessBespokeDetailCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		pager.setTotal(total);
		params.put("pager", pager);
		List<Map<String, Object>> list = cmsFinanceService
				.businessBespokeDetail(params);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		return "backstage/finance/businessBespokeDetail";
	}

	/**
	 * 纯商家 充电收益统计表
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/businessChargeStatistic")
	public String businessChargeStatistic(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request, @RequestParam Map<String, Object> params) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		setUser(params, request);
		long total = cmsFinanceService.businessChargeStatisticCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		pager.setTotal(total);
		params.put("pager", pager);
		List<Map<String, Object>> list = cmsFinanceService
				.businessChargeStatistic(params);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		return "backstage/finance/businessChargeStatistic";
	}

	/**
	 * 纯商家 预约收益统计表
	 * 
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/businessBespokeStatistic")
	public String businessBespokeStatistic(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			HttpServletRequest request, @RequestParam Map<String, Object> params) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		setUser(params, request);
		long total = cmsFinanceService.businessBespokeStatisticCount(params);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		pager.setTotal(total);
		params.put("pager", pager);
		List<Map<String, Object>> list = cmsFinanceService
				.businessBespokeStatistic(params);
		model.addAttribute("list", list);
		model.addAttribute("pager", pager);
		model.addAttribute("params", params);
		return "backstage/finance/businessBespokeStatistic";
	}

	private void setUser(Map<String, Object> params, HttpServletRequest request) {
		TblUser tblUser = SessionMgr.getWebUser(request);
		params.put("userLevel", tblUser.getUserLevel());
		params.put("userId", tblUser.getUserId());
		params.put("companyId", tblUser.getBusiCompanyId());
	}

	/**
	 * 发票申请列表查询
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/invoiceManage")
	public String billManage(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblInvoice TblInvoice, Model model,
			HttpServletRequest request) {

		try {

			TblUser loginUser = SessionMgr.getWebUser(request);
			// 电桩总数
			long total = cmsFinanceService.getInvoiceManageCount(TblInvoice);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			TblInvoice.setPager(pager);
			List<TblInvoice> invoiceList = cmsFinanceService
					.getInvoiceManageList(TblInvoice);
			pager.setTotal(total);

			model.addAttribute("invoiceList", invoiceList);
			model.addAttribute("pager", pager);
			model.addAttribute("TblInvoice", TblInvoice);
			model.addAttribute("loginUser", loginUser);

		} catch (Exception e) {

			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/finance/invoiceManage";
	}

	/**
	 * 发票申请详情页
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/invoiceDetail")
	public String invoiceDetail(@RequestParam("id") int pkInvoice, Model model) {

		TblInvoice tblInvoice = cmsFinanceService.getInvoiceById(pkInvoice);
		model.addAttribute("TblInvoice", tblInvoice);
		// 跳转至用户选择画面
		return "backstage/finance/invoiceDetail";
	}

	/***
	 * 修改发票号码
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeInvoiceDetail")
	@ResponseBody
	public String changeInvoiceDetail(@RequestParam Map<String, Object> params) {

		try {
			cmsFinanceService.changeIvNumberById(params);
			// tbl_purchasehistory

			cmsFinanceService.changePurHistoryById(params);

		} catch (Exception e) {
			return "0";
		}
		// 返回处理结果信息
		return "1";
	}

	/***
	 * 修改订单状态
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/orderAccounts")
	@ResponseBody
	public String orderAccounts(@RequestParam("ids") String pkChargingOrder,
			@RequestParam("type") String type,
			@RequestParam Map<String, Object> params) {
		try {

			return toAccounts(pkChargingOrder, params, type).toJSONString();

		} catch (Exception e) {
			// 返回处理结果信息
			return new DwzAjaxResult("300", "操作失败", type, "", "")
					.toJSONString();
		}

	}

	/**
	 * 个体、纯商家订单状态修改
	 * 
	 * @return
	 */
	private DwzAjaxResult toAccounts(String pkChargingOrder,
			Map<String, Object> params, String type) {

		DwzAjaxResult result = new DwzAjaxResult("200", "订单状态修改成功！", type, "",
				"");
		String[] pkchargingorder = null;
		if (pkChargingOrder.lastIndexOf(",") > 0) {// 多个审批
			pkchargingorder = pkChargingOrder.split(",");
		} else {
			pkchargingorder = new String[] { pkChargingOrder };
		}
		String errorCode = "";
		List<TblChargingOrder> tblChargingList = new ArrayList<TblChargingOrder>();
		// 校验所选择的数据是否为专属数据
		for (String code : pkchargingorder) {
			TblChargingOrder tblChargingOrder = new TblChargingOrder();
			tblChargingOrder.setPkChargingorder(code);
			tblChargingOrder = cmsFinanceService.getChargingOrderById(Integer
					.parseInt(code));
			tblChargingList.add(tblChargingOrder);
			if (!"3".equals(tblChargingOrder.getChorChargingstatus())) {
				DwzAjaxResult result_error = new DwzAjaxResult("300",
						"请选择订单状态为未结算的数据", type, "", "");
				return result_error;
			}

		}
		// 批量修改状态
		try {
			for (TblChargingOrder pile : tblChargingList) {
				params.clear();
				params.put("pkChargingOrder", pile.getPkChargingorder());
				params.put("chOrChargingStatus", "2");
				cmsFinanceService.updateChargingSate(params);
				TblPurchasehistory purchase = new TblPurchasehistory();
				purchase.setPuhiType(1);
				Date d = new Date();
				purchase.setPuhiPurchasehistorytime(d);
				BigDecimal s = pile.getChorServicemoney().add(
						pile.getChorChargemoney());
				purchase.setPuhiMonetary(s);
				purchase.setPuhiConsumerremark("-");
				purchase.setPuhiCreatedate(d);
				purchase.setPuhiUpdatedate(d);
				purchase.setPuhiUserid(Integer.parseInt(pile.getChorUserid()));
				purchase.setPuhiChargeType(pile.getChorOrdertype());
				purchase.setPuHiUserOrigin(pile.getChOrUserOrigin());
				purchase.setPuHiElectricPileCode(pile.getChorPilenumber());
				purchase.setPuHiExternalCardNumber(pile.getPkUserCard());
				purchase.setPuHiTransactionNumber(pile
						.getChorTransactionnumber());
				purchase.setPuHiBespokeNumber(pile.getChorAppointmencode());
				purchase.setPuHiPaymentNumber(pile.getChorCode());
				cmsFinanceService.insert(purchase);

			}
		} catch (Exception e) {
			result.setMessage("更改失败！" + StringUtils.removeEnd(errorCode, ","));
			result.setStatusCode("300");
			return result;
		}
		return result;
	}

	/**
	 * 大客户列表
	 * 
	 * @param pager
	 * @param params
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/customerBillManage")
	public String customerBillManage(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@RequestParam Map<String, Object> params, Model model,
			HttpServletRequest request) {
		try {

			long total = cmsFinanceService
					.getCustomerBillManageListCount(params);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			pager.setTotal(total);
			params.put("pager", pager);
			List<Map<String, Object>> billManageList = cmsFinanceService
					.getCustomerBillManageList(params);
			pager.setTotal(total);
			model.addAttribute("billManageList", billManageList);
			model.addAttribute("pager", pager);
		} catch (Exception e) {

			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/finance/customerBillManage";
	}

	/**
	 * 开票记录列表
	 * 
	 * @param pager
	 * @param userId
	 * @param params
	 * @param model
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/invoiceRecord")
	public String invoiceRecord(@ModelAttribute("pager") DwzPagerMySQL pager,
			String userId, @RequestParam Map<String, Object> params,
			Model model, HttpSession session, HttpServletRequest request) {
		try {
			if (userId != null) {
				params.put("userId", userId);
			}

			long total = cmsFinanceService.getinvoiceRecordListCount(params);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			pager.setTotal(total);
			params.put("pager", pager);
			List<Map<String, Object>> RecordList = cmsFinanceService
					.getinvoiceRecordList(params);
			pager.setTotal(total);
			model.addAttribute("RecordList", RecordList);
			model.addAttribute("pager", pager);
			model.addAttribute("params", params);
			model.addAttribute("userId", userId);
		} catch (Exception e) {

			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/finance/invoiceRecord";
	}

	/**
	 * 添加开票记录页面初始化
	 * 
	 * @param pager
	 * @param model
	 * @param userId
	 * @param PurchaseHistorys
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/newInvoiceRecord")
	public String newInvoiceRecord(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			String userId, String PurchaseHistorys, HttpServletRequest request) {
		model.addAttribute("PurchaseHistorys", PurchaseHistorys);
		model.addAttribute("userId", userId);
		// 跳转至添加页面
		return "backstage/finance/addInvoiceRecord";
	}

	/**
	 * 添加开票记录
	 * 
	 * @param pager
	 * @param userId
	 * @param tblInvoice
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/invoiceRecordAdd")
	@ResponseBody
	public String invoiceRecordAdd(
			@ModelAttribute("pager") DwzPagerMySQL pager, String userId,
			TblInvoice tblInvoice, String PurchaseHistorys,
			@RequestParam Map<String, Object> params, Model model,
			HttpServletRequest request) {
		DwzAjaxResult dwzResult = null;
		try {
			String[] PurchaseHistory = null;
			tblInvoice.setUserId(userId);
			cmsFinanceService.insertInvoiceRecord(tblInvoice);
			PurchaseHistory = PurchaseHistorys.split(",");
			params.put("pkInvoice", tblInvoice.getPkInvoice());
			params.put("PurchaseHistory", PurchaseHistory);
			cmsFinanceService.getModifyBillingStatus(params);

			dwzResult = new DwzAjaxResult("200", "新增成功", userId,
					"closeCurrent", "");
		} catch (Exception e) {
			dwzResult = new DwzAjaxResult("300", "新增失败", "", "", "");
		}
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 查看未开票订单
	 * 
	 * @param pager
	 * @param model
	 * @param userId
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/BillOrder")
	public String BillOrder(@ModelAttribute("pager") DwzPagerMySQL pager,
			Model model, String userId, String flag,
			@RequestParam Map<String, Object> params, HttpServletRequest request) {
		try {
			// userId 进行去重处理
			String aaArray[] = userId.split(",");
			HashSet<String> msi = new HashSet<String>();
			for (String s : aaArray) {
				msi.add(s);
			}
			String id = msi.toString().replace("[", "").replace("]", "");
			List<Map<String, Object>> orderList = cmsFinanceService
					.getBillOrderList(params);
			model.addAttribute("orderList", orderList);
			model.addAttribute("pager", pager);
			model.addAttribute("userId", id);
			model.addAttribute("params", params);
			model.addAttribute("proviceMap", WanmaConstants.provinceMap);

		} catch (Exception e) {
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return "backstage/finance/billingOrder";
	}

	/**
	 * 查看开票记录
	 * 
	 * @param pager
	 * @param pkInvoice
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/viewInvoiceRecord")
	public String viewInvoiceRecord(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@RequestParam("id") int pkInvoice, Model model,
			HttpServletRequest request) {
		TblInvoice tblInvoice = cmsFinanceService
				.getInvoiceRecordById(pkInvoice);
		model.addAttribute("tblInvoice", tblInvoice);
		return "backstage/finance/invoiceRecord-view";

	}

	/**
	 * 查看已开票订单
	 * 
	 * @param pager
	 * @param model
	 * @param userId
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/invoicedBillOrder")
	public String invoicedBillOrderlist(
			@ModelAttribute("pager") DwzPagerMySQL pager, Model model,
			String userId, @RequestParam Map<String, Object> params,
			String pkInvoice, HttpServletRequest request) {
		try {
			List<Map<String, Object>> invoicedOrderList = cmsFinanceService
					.getInvoicedOrderList(params);
			model.addAttribute("invoicedOrderList", invoicedOrderList);
			model.addAttribute("pkInvoice", pkInvoice);
			model.addAttribute("pager", pager);
			model.addAttribute("proviceMap", WanmaConstants.provinceMap);

		} catch (Exception e) {
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return "backstage/finance/invoicedBillOrder";

	}

	/**
	 * 编辑开票管理页面初始化
	 * 
	 * @param pkInvoice
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changeInvoiceRecordUi")
	public String changeInvoiceRecordUi(@RequestParam("id") int pkInvoice,
			String userId, Model model, HttpServletRequest request) {
		TblInvoice tblInvoice = cmsFinanceService
				.getInvoiceRecordById(pkInvoice);
		// userId 进行去重处理
		String aaArray[] = userId.split(",");
		HashSet<String> msi = new HashSet<String>();
		for (String s : aaArray) {
			msi.add(s);
		}
		String id = msi.toString().replace("[", "").replace("]", "");
		model.addAttribute("userId", id);
		model.addAttribute("tblInvoice", tblInvoice);
		return "backstage/finance/editInvoiceRecord";
	}

	/**
	 * 编辑开票记录
	 * 
	 * @param model
	 * @param session
	 * @param params
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/changeInvoiceRecord")
	@ResponseBody
	public String changeInvoiceRecord(Model model, HttpSession session,
			String userId, @RequestParam Map<String, Object> params,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			cmsFinanceService.changeInvoiceRecord(params);
			dwzResult = new DwzAjaxResult("200", "修改成功", userId,
					"closeCurrent", "");
		} catch (Exception e) {
			dwzResult = new DwzAjaxResult("300", "修改失败", userId, "", "");
		}

		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 删除开票记录
	 * 
	 * @param ids
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "/removeInvoiceRecord")
	@ResponseBody
	public String removeInvoiceRecord(@RequestParam("ids") String ids,
			String userId, @RequestParam Map<String, Object> params) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			String[] idArray = ids.split(",");
			TblInvoice tblInvoice = new TblInvoice();
			for (String id : idArray) {
				tblInvoice.setPkInvoice(JudgeNullUtils.nvlInteger(id));
				cmsFinanceService.removeInvoiceRecord(tblInvoice);
			}
			// userId 进行去重处理
			String aaArray[] = userId.split(",");
			HashSet<String> msi = new HashSet<String>();
			for (String s : aaArray) {
				msi.add(s);
			}
			userId = msi.toString().replace("[", "").replace("]", "");

			dwzResult = new DwzAjaxResult("200", "操作成功", userId, "", "");
		} catch (Exception e) {
			dwzResult = new DwzAjaxResult("300", "删除失败", userId, "", "");
		}

		return new JsonObject(dwzResult).toString();

	}

}

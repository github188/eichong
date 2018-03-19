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
import com.base.common.WanmaConstants;
import com.base.util.DateUtil;
import com.base.util.ExcelUtil;
import com.pub.controller.BaseController;
import com.pub.model.Pager;
import com.wanma.model.TblPurchasehistory;
import com.wanma.service.CmsPurchasehistoryService;

/**
 * 运营管理-订单管理-充值订单
 * 
 * @author 
 *
 */
@Controller
@RequestMapping("/admin/order")
public class CmsOrderPayController extends BaseController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsOrderPayController.class);

	@Autowired
	private CmsPurchasehistoryService purchasehistoryService;

	/**
	 * 跳转到充值订单列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listPayOrder")
	public String listPayOrder(HttpServletRequest request) {
		return "backstage/order/listPayOrder";
	}

	/**
	 * 取得充值订单列表处理
	 * 
	 * @author xiay
	 * @return
	 * @throws 无
	 */
	@RequestMapping(value = "/payOrder")
	@ResponseBody
	public String payOrder(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblPurchasehistory tblPurchasehistory, Model model) {
		BaseResult baseResult = new BaseFail(5001);
		// 充值订单信息
		List<TblPurchasehistory> payList = null;
		// 充值订单总数
		long total = 0;
		// 消费类型 1-充电消费 2-预约消费 3-购物消费 4-充值
		tblPurchasehistory.setPuhiType(4);
		// 充值订单总数
		total = purchasehistoryService
				.findPurchasehistoryCount(tblPurchasehistory);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置分页对象
		tblPurchasehistory.setPager(pager);
		// 取得充值订单列表
		payList = purchasehistoryService
				.findPurchasehistory(tblPurchasehistory);
		for (TblPurchasehistory entity : payList) {
			entity.setExtValue1(WanmaConstants.getConfigName("18", entity
					.getPuhiChargeType().toString()));
		}
		pager.setTotal(total);

		baseResult = new BaseResult(payList, pager);
		return baseResult.toString();
	}

	@RequestMapping(value = "/payOrderExport")
	@ResponseBody
	public void payOrderExport(
			@ModelAttribute TblPurchasehistory tblPurchasehistory, HttpServletResponse response) {
		List<TblPurchasehistory> payList = null;
		// 消费类型 1-充电消费 2-预约消费 3-购物消费 4-充值
		tblPurchasehistory.setPuhiType(4);
		// 取得充值订单列表
		payList = purchasehistoryService
				.findPurchasehistory(tblPurchasehistory);
		for (TblPurchasehistory entity : payList) {
			entity.setExtValue1(WanmaConstants.getConfigName("18", entity
					.getPuhiChargeType().toString()));
		}

		// excel导出
		ExcelUtil eu = new ExcelUtil();
		// 转换成excel可用的数据格式
		List<String[]> dataList = new ArrayList<String[]>();
		String[] data = new String[] { "手机号", "用户姓名", "充值金额", "充值时间", "充值渠道",
				"消费备注" };
		dataList.add(data);
		
		for (TblPurchasehistory obj : payList) {
			data = new String[6];
			data[0] = obj.getUserPhone();
			data[1] = obj.getUserName();
			data[2] = obj.getPuhiMonetary().toString();
			data[3] = DateUtil.format(obj.getPuhiCreatedate());
			data[4] = WanmaConstants.getConfigName("18", obj
					.getPuhiChargeType().toString());
			data[5] = obj.getPuhiPurchasecontent();
			dataList.add(data);
		}
		try {
			eu.export(dataList, response, "充值订单列表.xlsx");
		} catch (IOException e) {
			e.printStackTrace();
			log.error(this.getClass() + ".payOrderExport() error:"
					+ e.getLocalizedMessage());
		}

	}

}

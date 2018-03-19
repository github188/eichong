package com.wanma.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.wanma.model.TblPayOrder;
import com.wanma.model.TblPurchasehistory;
import com.wanma.service.CmsPayOrderService;
import com.wanma.service.CmsPurchasehistoryService;

/**
 * 充值订单控制器
 * 
 * @author xiay
 *
 */
@Controller
@RequestMapping("/admin/order")
public class CmsPayOrderController extends BaseController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsPayOrderController.class);
	
	@Autowired
	private CmsPurchasehistoryService purchasehistoryService;
	
	
	
	/**
	 * 取得充值订单列表处理
	 * 
	 * @author xiay
	 * @return 
	 * @throws 无
	 */
	@RequestMapping(value = "/rechOrder")
	public String getpayList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblPurchasehistory tblPurchasehistory, Model model) {

		// 充值订单信息
		List<TblPurchasehistory> payList = null;
		// 充值订单总数
		long total = 0;
			// 消费类型 1-充电消费 2-预约消费 3-购物消费 4-充值
			tblPurchasehistory.setPuhiType(4);
			// 充值订单总数
			total = purchasehistoryService.findPurchasehistoryCount(tblPurchasehistory);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblPurchasehistory.setPager(pager);
			// 取得充值订单列表
			payList = purchasehistoryService.findPurchasehistory(tblPurchasehistory);
			pager.setTotal(total);

		// 将充值订单信息放到会话中
		model.addAttribute("payList", payList);
		model.addAttribute("pager", pager);
		model.addAttribute("tblPurchasehistory", tblPurchasehistory);

		// 跳转至充值订单主页面
		return "backstage/order/listPayOrder";
	}
	
}

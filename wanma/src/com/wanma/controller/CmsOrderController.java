package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblOrder;
import com.wanma.model.TblOrderdetail;
import com.wanma.model.TblUser;
import com.wanma.service.CmsOrderService;

/**
 * 商城商品控制器
 * 
 * @author xiay
 * 
 */
@Controller
@RequestMapping("/admin/order")
public class CmsOrderController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsOrderController.class);

	@Autowired
	private CmsOrderService tblOrderService;

	/**
	 * 取得商城商品处理
	 * 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/subsShopOrder")
	public String getOrderList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblOrder tblOrder, Model model) {

		// 商城商品信息
		List<TblOrder> orderList = null;
		// 商城商品总数
		long total = 0;

		if (tblOrder == null) {
			// 取得商城商品列表
			orderList = tblOrderService.getOrderList();
		} else {
			// 商城商品总数
			total = tblOrderService.searchOrderCount(tblOrder);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblOrder.setPager(pager);
			// 取得商城商品列表
			orderList = tblOrderService.searchOrderList(tblOrder);
			pager.setTotal(total);
		}

		// 将商城商品放到会话中
		model.addAttribute("tblOrder", tblOrder);
		model.addAttribute("orderList", orderList);
		model.addAttribute("pager", pager);

		// 跳转至商城商品主页面
		return "backstage/order/listOrder";
	}

	/**
	 * 取得商城商品处理 --个体商家
	 * 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/ShopOrder")
	public String getShopList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblOrderdetail orderdetail, Model model,HttpServletRequest request) {
		
		// 登陆用户
				TblUser loginUser = SessionMgr.getWebUser(request);
				if (loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS_NORMAL) {// 个体商家
					orderdetail.setProdUser(loginUser.getUserId()+"");
				}
				
		
		// 商城商品信息
		List<TblOrderdetail> shopList = null;
		// 商城商品总数
		long total = 0;

		// 商城商品总数
		total = tblOrderService.searchShopCount(orderdetail);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
			// 设置分页对象
			orderdetail.setPager(pager);
			// 取得商城商品列表
			shopList = tblOrderService.searchShopList(orderdetail);
			pager.setTotal(total);
		

		// 将商城商品放到会话中
		model.addAttribute("orderdetail", orderdetail);
		model.addAttribute("shopList", shopList);
		model.addAttribute("pager", pager);

		// 跳转至商城商品主页面
		return "backstage/UnitOrder/listShop";
	}

	/**
	 * 取得商城商品处理 --纯商家
	 * 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/FirmShop")
	public String getFirmShopList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblOrderdetail orderdetail, Model model,HttpServletRequest request) {
		
		// 登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		if (loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS) {// 纯商家
			orderdetail.setProdUser(loginUser.getUserAccount());
		}
		
		// 商城商品信息
		List<TblOrderdetail> shopFirmList = null;
		// 商城商品总数
		long total = 0;
		// 商城商品总数
		total = tblOrderService.searchFirmShopCount(orderdetail);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
			// 设置分页对象
		orderdetail.setPager(pager);
			// 取得商城商品列表
			shopFirmList = tblOrderService.searchFirmShopList(orderdetail);
			pager.setTotal(total);

		// 将商城商品放到会话中
		model.addAttribute("orderdetail", orderdetail);
		model.addAttribute("shopFirmList", shopFirmList);
		model.addAttribute("pager", pager);

		// 跳转至商城商品主页面
		return "/backstage/pureBusiness/listBusShop";
	}

	/**
	 * 取得产品订单处理 --产品
	 * 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/proOrderList")
	public String getProOrderList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblOrder tblOrder, Model model) {

		// 产品信息
		List<TblOrder> proOrderList = null;
		// 产品总数
		long total = 0;

		if (tblOrder == null) {
			// 取得产品列表
			proOrderList = tblOrderService.getProOrderList();
		} else {
			// 产品总数
			total = tblOrderService.searcProOrderCount(tblOrder);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblOrder.setPager(pager);
			// 取得产品列表
			proOrderList = tblOrderService.searchProOrderList(tblOrder);
			pager.setTotal(total);
		}

		// 将产品放到会话中
		model.addAttribute("tblOrder", tblOrder);
		model.addAttribute("proOrderList", proOrderList);
		model.addAttribute("pager", pager);

		// 跳转至产品主页面
		return "backstage/product/listProOrder";
	}

	/**
	 * 显示详细订单信息
	 * 
	 * @author xiay
	 * @return
	 */
	@RequestMapping(value = "/viewProOrder", method = RequestMethod.GET)
	public String viewProOrder(@RequestParam("pkOrder") String pkOrder,
			Model model) {
		// 取得查看对象纯商家信息
		TblOrder tblOrder = tblOrderService.findProOrder(pkOrder);

		// 将纯商家信息设置到画面显示对象
		model.addAttribute("tblOrder", tblOrder);

		// 跳转至纯商家查看页面
		return "backstage/product/viewProOrder";
	}

	/**
	 * 更新个体商家订单状态
	 * 
	 * @author xiay
	 * @return
	 */
	@RequestMapping("/updateShopOrderStatus")
	@ResponseBody
	public String updateShopOrderStatus(TblOrder tblOrder) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			if (!tblOrder.getOrdeStatus().equals(
					WanmaConstants.ORDER_STATUS_TOBE_SEND_GOODS)) {
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "操作失败", "ShopOrder",
						"", "");
			}
			tblOrderService.updateOrderStatus(tblOrder);
			dwzResult = new DwzAjaxResult("200", "操作成功", "ShopOrder", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "更新失败", "ShopOrder", "",
					"");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 更新纯商家订单状态
	 * 
	 * @author xiay
	 * @return
	 */
	@RequestMapping("/updateFirmShopOrderStatus")
	@ResponseBody
	public String updateFirmShopOrderStatus(TblOrder tblOrder) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			tblOrderService.updateOrderStatus(tblOrder);
			dwzResult = new DwzAjaxResult("200", "操作成功", "subsShopOrder", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "更新失败", "subsShopOrder", "",
					"");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 根据订单id获取订单详情
	 * 
	 * @author xiay
	 * @return
	 */
	@RequestMapping("/findOrderdetail")
	public String findOrderdetail(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblOrderdetail orderdetail,Model model){
		// 商城商品信息
				List<TblOrderdetail> orderdetailList = null;
				long total = 0;
				// 商城商品总数
				total = tblOrderService.findCountByOrderId(orderdetail);
				if (total <= pager.getOffset()) {
					pager.setPageNum(1L);
				}
					// 设置分页对象
				orderdetail.setPager(pager);
					// 取得商城商品列表
				orderdetailList = tblOrderService.findByOrderId(orderdetail);
					pager.setTotal(total);

				// 将商城商品放到会话中
				model.addAttribute("orderdetailList", orderdetailList);
				model.addAttribute("pager", pager);

				// 跳转至商城商品主页面
				return "backstage/order/listOrderdetail";
	}
	
}

package com.wanma.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.wanma.model.TblAppointmentinstallationorder;
import com.wanma.model.TblInstalldetail;
import com.wanma.service.CmsInstallService;
import com.wanma.service.CmsInstalldetailService;

/**
 * 预约安装控制器
 * 
 * @author xiay
 * 
 */
@Controller
@RequestMapping("/admin/order")
public class CmsInstallController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsInstallController.class);

	@Autowired
	private CmsInstallService tblInsatallService;

	@Autowired
	private CmsInstalldetailService installdetailService;

	Integer pkUserinfoId = null;

	/**
	 * 取得预约安装处理
	 * 
	 * @return
	 * @throws
	 */
	@RequestMapping(value = "/subsInstall")
	public String getInstallList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblAppointmentinstallationorder tblInsatall,
			Model model) {

		// 预约安装信息
		List<TblAppointmentinstallationorder> installList = null;
		// 用户总数
		long total = 0;

		if (tblInsatall == null) {
			// 取得预约安装列表
			installList = tblInsatallService.getInstallList();
		} else {
			// 预约安装总数
			total = tblInsatallService.searchInstallCount(tblInsatall);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblInsatall.setPager(pager);
			// 取得预约安装列表
			installList = tblInsatallService.searchInstallList(tblInsatall);
			pager.setTotal(total);
		}

		// 将预约安装放到会话中
		model.addAttribute("tblInsatall", tblInsatall);
		model.addAttribute("installList", installList);
		model.addAttribute("pager", pager);

		// 跳转至预约安装主页面
		return "backstage/order/listInstall";
	}

	/**
	 * 取得预约安装详情信息
	 * 
	 * @return
	 * @throws
	 */
	@RequestMapping("/findInstalldetailList")
	public String findInstalldetailList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			TblInstalldetail tblInstalldetail, Model model) {
		// 获取预约安装订单详情总数
		long total = installdetailService
				.findInstalldetailCount(tblInstalldetail);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		tblInstalldetail.setPager(pager);
		// 获取安装订单详情列表
		List<TblInstalldetail> detailList = installdetailService
				.findInstalldetailList(tblInstalldetail);

		pager.setTotal(total);
		// 将查询结果显示到画面
		model.addAttribute("detailList", detailList);
		model.addAttribute("pager", pager);
		// 跳转至预约安装详情列表页面
		return "backstage/order/listInstallDetail";
	}
	
	/**
	 * 根据主键取得预约安装详情信息
	 * 
	 * @return
	 * @throws
	 */
	@RequestMapping("/findInstalldetail")
	public String findInstalldetail(Model model,int pkInstalldetail){
		
		TblInstalldetail installdetail = installdetailService.getInstalldetail(pkInstalldetail);
		// 将查询结果显示到画面
		model.addAttribute("installdetail", installdetail);
		// 跳转至预约安装详情页面
		return "backstage/order/installDetail";
	}

}

package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblConfigparameter;
import com.wanma.model.TblUser;
import com.wanma.service.CmsConfigparameterService;

/**
 * @Description: 配置参数控制层
 * @author songjf
 * @createTime：2015-4-2 上午11:01:36
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/invoiceParameter")
public class CmsInvoiceparameterController extends BaseController{
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsInvoiceparameterController.class);
	// 配置参数处理对象
	@Autowired
	private CmsConfigparameterService configparameterService;

	/**
	 * @Title: findConParaList
	 * @Description: 根据配置类型获取配置名称信息
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@RequestMapping("/findConParaList")
	public String findConParaList(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblConfigparameter configparameter, Model model,HttpServletRequest request) {
		log.info("******************根据配置类型获取配置名称信息-begin************************");
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}
		// 根据配置类型，获取其他配置参数中配置名称数量
		long total = configparameterService.findCount(configparameter);
		if(total<=pager.getOffset()){
			pager.setPageNum(1L);
		}
		// 设置查询参数
		configparameter.setPager(pager);
		// 获取配置参数列表
		configparameter.setCopaStatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		List<TblConfigparameter> confparaList = configparameterService
				.findConParaList(configparameter);
		pager.setTotal(total);
		
		// 取得登录用户信息
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 将查询结果显示到画面
		model.addAttribute("confparaList", confparaList);
		model.addAttribute("pager", pager);
		model.addAttribute("configparameter", configparameter);
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		log.info("******************根据配置类型获取配置名称信息-end************************");

		// 跳转至车型列表页面
		return "backstage/configInvoiceParameter/configParameter-list";
	}

	/**
	 * @Title: editConPara
	 * @Description: 跳转至配置参数编辑配置名称页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/editConPara")
	public String editConPara(@RequestParam("id") int pkConfigparameter,
			Model model) {
		log.info("******************跳转至配置参数编辑配置名称页面-begin************************");
		// 根据id获取配置参数信息
		TblConfigparameter confgConfigparameter = configparameterService
				.findConPara(pkConfigparameter);
		// 将查询结果显示到画面
		model.addAttribute("confgConfigparameter", confgConfigparameter);
		log.info("******************跳转至配置参数编辑配置名称页面-end************************");
		return "backstage/configInvoiceParameter/configParameter-edit";
	}

	/**
	 * @Title: updateConPara
	 * @Description: 配置参数更新配置名称
	 * @param paraconfig
	 * @return
	 */
	@RequestMapping("/updateConPara")
	@ResponseBody
	public String updateConPara(TblConfigparameter configparameter) {
		log.info("******************配置参数更新配置名称-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			configparameterService.updateConPara(configparameter);
			dwzResult = new DwzAjaxResult("200", "编辑成功", "findConParaList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "findConParaList", "", "");
		}
		log.info("******************配置参数更新配置名称-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}

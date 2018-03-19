package com.wanma.controller;

import java.util.List;

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
import com.bluemobi.product.utils.JsonObject;
import com.wanma.model.TblElctrcplscrnconfgurtn;
import com.wanma.service.CmsElctrcplscrnconfgurtnService;

/**
 * @Description: 桩体配置参数控制层
 * @author songjf
 * @createTime：2015-4-1 上午11:55:05
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/elctrcplscrnconfgurtn")
public class CmsElctrcplscrnconfgurtnController {
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsElctrcplscrnconfgurtnController.class);
	// 桩体配置参数处理对象
	@Autowired
	private CmsElctrcplscrnconfgurtnService elctrcplscrnconfgurtnService;

	/**
	 * @Title: epscTypeList
	 * @Description: 跳转至桩体配置参数设置页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/epscTypeList")
	public String epscTypeList() {
		log.info("******************跳转至桩体配置参数设置页面************************");
		return "backstage/ElctrcPlScrnConfgurtn/ElctrcPlScrnConfgurtn-index";
	}

	/**
	 * @Title: findByEpscType
	 * @Description: 根据配置类型获取桩体配置参数信息
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@RequestMapping("/findByEpscType")
	public String findByEpscType(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblElctrcplscrnconfgurtn elctrcplscrnconfgurtn, Model model) {
		log.info("******************根据配置类型获取桩体配置参数信息-begin************************");

		// 充电点总数
		long total = elctrcplscrnconfgurtnService
				.findCount(elctrcplscrnconfgurtn);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		elctrcplscrnconfgurtn.setPager(pager);
		// 获取充电点列表
		List<TblElctrcplscrnconfgurtn> elcList = elctrcplscrnconfgurtnService
				.findByEpscType(elctrcplscrnconfgurtn);
		pager.setTotal(total);
		// 将查询结果显示到画面
		model.addAttribute("elcList", elcList);
		model.addAttribute("pager", pager);
		model.addAttribute("elctrcplscrnconfgurtn", elctrcplscrnconfgurtn);

		log.info("******************根据配置类型获取桩体配置参数信息-begin************************");

		// 跳转至车型列表页面
		return "backstage/ElctrcPlScrnConfgurtn/ElctrcPlScrnConfgurtn-list";
	}

	/**
	 * @Title: newEpsc
	 * @Description: 跳转至桩体配置参数新增配置名称页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/newEpsc")
	public String newEpsc(TblElctrcplscrnconfgurtn elctrcplscrnconfgurtn,
			Model model) {
		log.info("******************跳转至桩体配置参数新增配置名称页面************************");
		model.addAttribute("elctrcplscrnconfgurtn", elctrcplscrnconfgurtn);
		return "backstage/ElctrcPlScrnConfgurtn/ElctrcPlScrnConfgurtn-add";

	}

	/**
	 * @Title: insertEpsc
	 * @Description: 桩体配置参数新增配置名称
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertEpsc")
	@ResponseBody
	public String insertEpsc(TblElctrcplscrnconfgurtn elctrcplscrnconfgurtn) {
		log.info("******************桩体配置参数新增配置名称-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			// 新增车型
			elctrcplscrnconfgurtnService.insertInfo(elctrcplscrnconfgurtn);
			dwzResult = new DwzAjaxResult("200", "新增成功", "findByEpscType",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "findByEpscType", "",
					"");
		}
		log.info("******************桩体配置参数新增配置名称-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除桩体配置参数中配置名称
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(@RequestParam("id") int pkElctrcplscrnconfgurtn) {
		log.info("******************删除桩体配置参数中配置名称-begin************************");
		DwzAjaxResult dwzResult = null;

		try {
			// 删除桩体配置参数中配置名称
			elctrcplscrnconfgurtnService.deleteById(pkElctrcplscrnconfgurtn);
			dwzResult = new DwzAjaxResult("200", "删除成功", "findByEpscType", "",
					"");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "findByEpscType", "",
					"");
		}

		log.info("******************删除桩体配置参数中配置名称-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: editEpsc
	 * @Description: 跳转至桩体配置参数编辑配置名称页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/editEpsc")
	public String editEpsc(@RequestParam("id") int pkElctrcplscrnconfgurtn,
			Model model) {
		log.info("******************跳转至桩体配置参数编辑配置名称页面-begin************************");
		// 根据id获取桩体配置参数信息
		TblElctrcplscrnconfgurtn epsc = elctrcplscrnconfgurtnService
				.findById(pkElctrcplscrnconfgurtn);
		// 将查询结果显示到画面
		model.addAttribute("epsc", epsc);
		log.info("******************跳转至桩体配置参数编辑配置名称页面-end************************");
		return "backstage/ElctrcPlScrnConfgurtn/ElctrcPlScrnConfgurtn-edit";
	}

	/**
	 * @Title: updateEpsc
	 * @Description: 桩体配置参数更新配置名称
	 * @param params
	 * @return
	 */
	@RequestMapping("/updateEpsc")
	@ResponseBody
	public String updateEpsc(TblElctrcplscrnconfgurtn elctrcplscrnconfgurtn) {
		log.info("******************桩体配置参数更新配置名称-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			elctrcplscrnconfgurtnService.updateById(elctrcplscrnconfgurtn);
			dwzResult = new DwzAjaxResult("200", "编辑成功", "findByEpscType",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "findByEpscType", "",
					"");
		}
		log.info("******************桩体配置参数更新配置名称-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteEpscs
	 * @Description: 桩体配置参数，批量删除配置名称
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteEpscs")
	@ResponseBody
	public String deleteEpscs(
			@RequestParam("ids") String pkElctrcplscrnconfgurtns) {
		log.info("******************桩体配置参数，批量删除配置名称-begin************************");

		DwzAjaxResult dwzResult = null;

		try {
			// 桩体配置参数，批量删除配置名称
			elctrcplscrnconfgurtnService.deleteEpscs(pkElctrcplscrnconfgurtns);
			dwzResult = new DwzAjaxResult("200", "删除成功", "findByEpscType", "",
					"");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "findByEpscType", "",
					"");
		}

		log.info("******************桩体配置参数，批量删除配置名称-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

}

package com.wanma.controller;

import java.util.List;
import java.util.Map;

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
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblConfigcontent;
import com.wanma.service.CmsConfigcontentService;

/**
 * @Description: 配置参数内容控制器
 * @author songjf
 * @createTime：2015-4-2 下午02:30:34
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/configContent")
public class CmsConfigcontentController {
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsConfigcontentController.class);
	// 配置参数内容处理对象
	@Autowired
	private CmsConfigcontentService configcontentService;
	
	/**
	 * @Title: findContentList
	 * @Description: 获取配置参数内容信息
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@RequestMapping("/findContentList")
	public String findContentList(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblConfigcontent configcontent, Model model) {
		log.info("******************获取配置参数内容信息-begin************************");

		// 根据配置类型，获取其他配置参数中配置名称数量
		long total = configcontentService.findCount(configcontent);
		if(total<=pager.getOffset()){
			pager.setPageNum(1L);
		}
		// 设置查询参数
		configcontent.setPager(pager);
		configcontent.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		// 获取配置参数内容列表
		List<TblConfigcontent> contentList = configcontentService
				.findContentList(configcontent);
		pager.setTotal(total);
		// 将查询结果显示到画面
		model.addAttribute("contentList", contentList);
		model.addAttribute("pager", pager);
		model.addAttribute("configcontent", configcontent);

		log.info("******************获取配置参数内容信息-end************************");

		// 跳转至配置参数内容列表页面
		return "backstage/configContent/configContent-list";
	}

	/**
	 * @Title: newConPara
	 * @Description: 跳转至新增配置参数内容页面
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@RequestMapping("/newContent")
	public String newConPara(TblConfigcontent configcontent, Model model) {
		log.info("******************跳转至新增配置参数内容页面************************");
		model.addAttribute("configcontent", configcontent);
		return "backstage/configContent/configContent-add";

	}

	/**
	 * @Title: insertContent
	 * @Description: 新增配置参数内容
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertContent")
	@ResponseBody
	public String insertContent(TblConfigcontent configcontent) {
		log.info("******************新增配置参数内容-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			// 新增配置参数内容
			configcontentService.insertContent(configcontent);
			dwzResult = new DwzAjaxResult("200", "新增成功", "findContentList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "findContentList", "", "");
		}
		log.info("******************新增配置参数内容-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除配置参数内容
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(TblConfigcontent configcontent) {
		log.info("******************删除配置参数内容-begin************************");
		DwzAjaxResult dwzResult = null;

		try {
			// 删除配置参数内容
			configcontentService.deleteById(configcontent);
			dwzResult = new DwzAjaxResult("200", "删除成功", "findContentList", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "findContentList", "", "");
		}

		log.info("******************删除配置参数内容-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: editContent
	 * @Description: 跳转至更新配置参数内容页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/editContent")
	public String editContent(@RequestParam("id") int pkConfigcontent,
			Model model) {
		log.info("******************跳转至配置参数内容页面-begin************************");
		// 根据id获取配置参数信息
		TblConfigcontent configcontent = configcontentService
				.findContent(pkConfigcontent);
		// 将查询结果显示到画面
		model.addAttribute("configcontent", configcontent);
		log.info("******************跳转至配置参数内容页面-end************************");
		return "backstage/configContent/configContent-edit";
	}

	/**
	 * @Title: updateContent
	 * @Description: 更新配置参数内容
	 * @param paraconfig
	 * @return
	 */
	@RequestMapping("/updateContent")
	@ResponseBody
	public String updateContent(TblConfigcontent configcontent) {
		log.info("******************更新配置参数内容-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			configcontentService.updateContent(configcontent);
			dwzResult = new DwzAjaxResult("200", "编辑成功", "findContentList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "findContentList", "", "");
		}
		log.info("******************更新配置参数内容-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	

}

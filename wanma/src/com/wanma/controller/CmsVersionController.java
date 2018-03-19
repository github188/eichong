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
import com.wanma.model.TblVersion;
import com.wanma.service.CmsVersionService;


/**
 * @Description: 版本controller
 * @author wubc
 * @createTime：2015-5-27 
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/version")
public class CmsVersionController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsVersionController.class);
	/* 版本service */
	@Autowired
	private CmsVersionService versionService;
	
	@RequestMapping("/versionList")
	public String findCarList(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblVersion	version, Model model) {
		log.info("******************获取版本列表-begin************************");
		// 版本总数
		long total = versionService.getCount(version);
		if(total<=pager.getOffset()){
			pager.setPageNum(1L);
		}
		// 设置查询参数
		version.setPager(pager);
		
		List<TblVersion> versionList = versionService.getAll(version);
		
		pager.setTotal(total);
		// 将查询结果显示到画面
		model.addAttribute("versionList", versionList);
		model.addAttribute("pager", pager);
		model.addAttribute("version", version);
		log.info("******************获取版本列表-end************************");
		// 跳转至版本列表页面
		return "backstage/version/version-list";
	}
	
	/**
	 * @Title: newversion
	 * @Description: 跳转至版本页面
	 * @param version
	 * @return
	 */
	@RequestMapping("/newVersion")
	public String newversion(Model model) {
		log.info("******************跳转至版本页面************************");
		return "backstage/version/version-add";

	}

	/**
	 * @Title: insertVersion
	 * @Description: 新增版本页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertVersion")
	@ResponseBody
	public String insertVersion(TblVersion version) {
		log.info("******************新增版本-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			// 新增电动车品牌类型
			versionService.insert(version);
			dwzResult = new DwzAjaxResult("200", "新增成功", "version",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "version", "", "");
		}
		log.info("******************新增版本-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除版本
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(TblVersion tblversion) {
		log.info("******************删除版本-begin************************");
		DwzAjaxResult dwzResult = null;

		try {
			// 删除其他配置参数中配置名称
			versionService.delete(tblversion.getPkVersion());
			dwzResult = new DwzAjaxResult("200", "删除成功", "version", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "version", "", "");
		}

		log.info("******************删除版本-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	

	/**
	 * @Title: deleteVersions
	 * @Description: 批量删除版本
	 * @param pkversions
	 *            主键，多个以英文逗号分隔
	 * @return
	 */
	@RequestMapping("/deleteVersions")
	@ResponseBody
	public String deleteVersions(@RequestParam("ids") String ids) {
		log.info("******************批量删除版本-begin************************");

		DwzAjaxResult dwzResult = null;

		try {
			// 批量删除版本
			versionService.deleteBatch(ids);

			dwzResult = new DwzAjaxResult("200", "删除成功", "version", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "version", "", "");
		}

		log.info("******************批量删除版本-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

}

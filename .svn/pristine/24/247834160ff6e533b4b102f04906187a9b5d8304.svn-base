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

import com.bluemobi.product.common.WebConst;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblProductcategory;
import com.wanma.model.TblUser;
import com.wanma.service.CmsProductcategoryService;

/**
 * @Description: 商品分类控制器
 * @author songjf
 * @createTime：2015-4-1 下午11:49:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/productCategory")
public class CmsProductcategoryController {
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsProductcategoryController.class);
	// 商品分类处理对象
	@Autowired
	private CmsProductcategoryService productcategoryService;

	/**
	 * @Title: categoryList
	 * @Description: 跳转至商品分类页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/categoryList")
	public String categoryList() {
		log.info("******************跳转至商品分类页面************************");

		return "backstage/productCategory/productCategory-index";
	}

	/**
	 * @Title: findCategoryList
	 * @Description: 获取商品分类信息
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@RequestMapping("/findCategoryList")
	public String findCategoryList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			TblProductcategory productcategory, Model model,
			HttpServletRequest request) {
		log.info("******************获取商品分类信息-begin************************");

		// 获取商品分类数量
		long total = productcategoryService.findCount(productcategory);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		productcategory.setPager(pager);
		// 获取商品分类信息
		List<TblProductcategory> categoryList = productcategoryService
				.findCategoryList(productcategory);
		pager.setTotal(total);

		// 取得登录用户信息
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 将查询结果显示到画面
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("pager", pager);
		model.addAttribute("productcategory", productcategory);
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());

		log.info("******************获取商品分类信息-end************************");

		// 跳转至车型列表页面
		return "backstage/productCategory/productCategory-list";
	}

	/**
	 * @Title: newCategory
	 * @Description: 跳转至新增商品分类页面
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@RequestMapping("/newCategory")
	public String newCategory(TblProductcategory productcategory, Model model) {
		log.info("******************跳转至新增商品分类页面************************");
		model.addAttribute("productcategory", productcategory);
		return "backstage/productCategory/productCategory-add";

	}

	/**
	 * @Title: insertCategory
	 * @Description: 新增商品分类
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertCategory")
	@ResponseBody
	public String insertCategory(TblProductcategory productcategory) {
		log.info("******************新增商品分类-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			// 新增商品分类
			productcategoryService.insertCategory(productcategory);
			dwzResult = new DwzAjaxResult("200", "新增成功", "findCategoryList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "findCategoryList",
					"", "");
		}
		log.info("******************新增商品分类-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除商品分类
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(@RequestParam("id") int pkProductcategory) {
		log.info("******************删除商品分类-begin************************");
		DwzAjaxResult dwzResult = null;

		try {
			// 删除商品分类
			productcategoryService.deleteById(pkProductcategory);
			dwzResult = new DwzAjaxResult("200", "删除成功", "findCategoryList",
					"", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "findCategoryList",
					"", "");
		}

		log.info("******************删除商品分类-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: editCategory
	 * @Description: 跳转至商品分类编辑页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/editCategory")
	public String editCategory(@RequestParam("id") int pkProductcategory,
			Model model) {
		log.info("******************跳转至商品分类编辑页面-begin************************");
		// 根据id获取其他配置参数信息
		TblProductcategory category = productcategoryService
				.findCategory(pkProductcategory);
		// 将查询结果显示到画面
		model.addAttribute("category", category);
		log.info("******************跳转至商品分类编辑页面-end************************");
		return "backstage/productCategory/productCategory-edit";
	}

	/**
	 * @Title: updateCategory
	 * @Description: 更新商品分类
	 * @param paraconfig
	 * @return
	 */
	@RequestMapping("/updateCategory")
	@ResponseBody
	public String updateCategory(TblProductcategory productcategory) {
		log.info("******************更新商品分类-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			productcategoryService.updateCategory(productcategory);
			dwzResult = new DwzAjaxResult("200", "编辑成功", "findCategoryList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "findCategoryList",
					"", "");
		}
		log.info("******************更新商品分类-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteCategorys
	 * @Description: 批量删除商品分类
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteCategorys")
	@ResponseBody
	public String deleteCategorys(@RequestParam("ids") String pkProductcategorys) {
		log.info("******************批量删除商品分类-begin************************");

		DwzAjaxResult dwzResult = null;

		try {
			// 其他配置参数，批量删除配置名称
			productcategoryService.deleteProductcategorys(pkProductcategorys);

			dwzResult = new DwzAjaxResult("200", "删除成功", "findCategoryList",
					"", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "findCategoryList",
					"", "");
		}

		log.info("******************批量删除商品分类-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

}

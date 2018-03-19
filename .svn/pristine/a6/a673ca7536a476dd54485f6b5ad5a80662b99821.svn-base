package com.wanma.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.product.common.ProcessInfoCommon;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.model.Homepage;
import com.wanma.web.service.CmsHomepageService;

/**
 * 首页图片管理
 * 
 * @author xiay
 * 
 */
@Controller
@RequestMapping("/web/advertise")
public class CmsHomepageController {
	
	//日志输出对象
	private static Logger log = Logger.getLogger(CmsHomepageController.class);

	/** 首页广告栏处理对象 */
	@Autowired
	private CmsHomepageService homepageService;
	
	/**
	 * 跳转首页广告页
	 * 
	 * @author taoxd
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/homePageList", method = RequestMethod.GET)
	public String editUser(Model model) {
		//获取首页广告图片
		List<Homepage> homePageList = homepageService.getHomepageList();
		Homepage homepage = new Homepage();
		// 将首页广告图片设置到画面显示对象
		homepage = homePageList.get(0);
		model.addAttribute("homePage1", homepage);
		homepage = homePageList.get(1);
		model.addAttribute("homePage2", homepage);
		homepage = homePageList.get(2);
		model.addAttribute("homePage3", homepage);
		homepage = homePageList.get(3);
		model.addAttribute("homePage4", homepage);
		homepage = homePageList.get(4);
		model.addAttribute("homePage5", homepage);
		// 跳转至用户编辑页面
		return "backstage/homepage/listHomepage";
	}
	
	/**
	 * 首页广告删除处理
	 * 
	 * @author taoxd
	 * @since Version 1.0
	 * @param homepageId
	 *            首页广告id
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/deleteHomePage")
	@ResponseBody
	public String deleteFilterWord(@RequestParam("homepageId") String homepageId){
		// 处理结果信息
		DwzAjaxResult dwzResult;

		try {
			// 执行删除处理
			homepageService.deleteHomePage(homepageId);

			// 设置处理结果信息
			dwzResult = new DwzAjaxResult("200", "删除成功", "homePageList", "", "");
			// 如果更新过滤字处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());

			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败:系统错误", "homePageList", "",
							"");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	
	/**
	 * 首页广告编辑处理
	 * 
	 * @author taoxd
	 * @since Version 1.0
	 * @param homepage
	 *            首页广告
	 * @param result
	 *            数据绑定结果
	 * @param request
	 *            画面请求信息
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/modifyHomePage")
	@ResponseBody
	public String modifyHomePage(@ModelAttribute("homepage") Homepage homepage,
			@RequestParam(value = "listImage", required = false) MultipartFile[] listImage)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			// 修改广告图片
			homepageService.changeHomepage(homepage, listImage);
			dwzResult = new DwzAjaxResult("200", "修改成功", "homePageList",
					"", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "修改失败", "homePageList", "",
					"");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * 查询过滤字
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/advertisePic")
	public String getPicList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute("homePage") Homepage homepage,
			Model model){
		//常见问题信息
		List<Homepage> homepageList = null;
		
		//常见问题总数
		long total = 0;
			
		if(homepage == null){
			//取得常见问题信息
			homepageList = homepageService.getHomepageList();
		}else {
			//设置分页对象
			homepage.setPager(pager);
			
			//取得常见问题信息
			homepageList = homepageService.searchHomepageList(homepage);
			
			// 用户总数
			total = homepageService.searchHomepageCount(homepage);
			pager.setTotal(total);
		}
			
		//将常见问题信息放到会话中
		model.addAttribute("homepage", homepage);
		model.addAttribute("homepageList", homepageList);
		model.addAttribute("pager", pager);
			
		//跳转至常见问题信息
		return "backstage/advertise/listAdvertise";
	}
	
	/**
	 * 添加首页广告栏图片
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/PicList")
	public String PicList(Model model){
		String advertisePicId = "";
		Homepage homepage = new Homepage();
		
		advertisePicId = ProcessInfoCommon.getRandomKey();
		
		homepage.setHomepageId(advertisePicId);
		
		model.addAttribute("homepage", homepage);
		
		return "backstage/advertise/listAdvertise";
	}
	
	/**
	 * 添加过滤字
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/savePic", produces = "plain/text; charset=UTF-8", method = RequestMethod.POST)
	@ResponseBody
	public String savePic(@ModelAttribute("homePage") Homepage homepage,
			BindingResult result, HttpServletRequest request)
			throws IOException {
		// 处理结果信息
		DwzAjaxResult dwzResult;

		// 如果数据绑定出错
		if (result.hasErrors()) {
			// 设置参数错误信息
			dwzResult = new DwzAjaxResult("300", "参数错误", "picAddPage", "", "");

			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}

		try {
			
			// 执行用户添加处理，并取得添加成功的用户详细信息
			homepageService.insertHomePage(homepage);
			
			
			// 设置成功并返回用户一览画面信息
			dwzResult = new DwzAjaxResult("200", "保存成功", "advertisePic",
					"closeCurrent", "");
			// 如果添加用户处理不成功
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置处理错误信息
			dwzResult = new DwzAjaxResult("300", "保存失败:系统错误", "advertisePic",
					"", "");

		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * 跳转首页广告页
	 * 
	 * @author taoxd
	 * @since Version 1.0
	 * @return String 画面跳转URI
	 * @throws 无
	 */
	@RequestMapping(value = "/homePageImages", method = RequestMethod.GET)
	@ResponseBody
	public String homePageImages(Model model) {
		//获取首页广告图片
		List<Homepage> homePageList = homepageService.getHomepageList();
		// 返回处理结果信息
		return new JsonObject(homePageList).toString();
	}
}















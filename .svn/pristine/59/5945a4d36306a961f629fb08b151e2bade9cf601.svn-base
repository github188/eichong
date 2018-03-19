package com.wanma.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblAdvertisement;
import com.wanma.model.TblUser;
import com.wanma.service.CmsAdvertisementService;

/**
 * @Description: 广告管理controller
 * @author lhy
 * @updateTime：
 * @version：V3.2.0
 */
@Controller
@RequestMapping("/admin/advertisement")
public class CmsAdvertisementController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsAdvertisementController.class);
	/* 制造厂商service */
	@Autowired
	private CmsAdvertisementService advService;

	/**
	 * 广告列表
	 * 
	 * @param pager
	 * @param advModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/listSplash")
	public String listSplash(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblAdvertisement advModel, Model model, HttpServletRequest request) {
		log.info("******************获取广告信息列表-begin************************");
		// 总数
		advModel.setAdType(1);
		long total = advService.getCount(advModel);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		advModel.setPager(pager);
		List<TblAdvertisement> advList = null;
		if (total > 0)
			advList = advService.getList(advModel);
		pager.setTotal(total);
		// 取得登录用户信息
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 将查询结果显示到画面
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("advList", advList);
		model.addAttribute("pager", pager);
		model.addAttribute("advModel", advModel);
		log.info("******************获取广告信息列表-end************************");
		// 跳转至广告列表页面
		return "backstage/advertisement/splash/listSplash";
	}
	/**
	 * 广告列表
	 * 
	 * @param pager
	 * @param advModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/listPlaque")
	public String listPlaque(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblAdvertisement advModel, Model model, HttpServletRequest request) {
		log.info("******************获取广告信息列表-begin************************");
		// 总数
		advModel.setAdType(2);
		long total = advService.getCount(advModel);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		advModel.setPager(pager);
		List<TblAdvertisement> advList = null;
		if (total > 0)
			advList = advService.getList(advModel);
		pager.setTotal(total);
		// 取得登录用户信息
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 将查询结果显示到画面
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("advList", advList);
		model.addAttribute("pager", pager);
		model.addAttribute("advModel", advModel);
		log.info("******************获取广告信息列表-end************************");
		// 跳转至广告列表页面
		return "backstage/advertisement/plaque/listPlaque";
	}

	/**
	 * 广告新增界面
	 * 
	 * @param pager
	 * @param advModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/addSplash")
	public String addSplash(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblAdvertisement advModel, Model model, HttpServletRequest request) {
		
	
		// 跳转至广告新增页面
		return "backstage/advertisement/splash/addSplash";
	}
	/**
	 * 广告新增界面
	 * 
	 * @param pager
	 * @param advModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPlaque")
	public String addPlaque(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblAdvertisement advModel, Model model, HttpServletRequest request) {
		
	
		// 跳转至广告新增页面
		return "backstage/advertisement/plaque/addPlaque";
	}

	/**
	 * 新增广告
	 * 
	 * @param advModel
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addAdvertisement")
	@ResponseBody
	public String saveActivity(TblAdvertisement advModel, HttpSession session,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			if (advService.getLimitCount(advModel) > 0){
				if(advModel.getAdType()==1)
					return new JsonObject(new DwzAjaxResult("300",
						"该时间段已经存在一个闪屏图片，请更换起止时间！", "", "", "")).toString();
				else 
					return new JsonObject(new DwzAjaxResult("300",
							"该位置，该时间段已经存在一个插屏图片，请更换起止时间！", "", "", "")).toString();
			}
			advModel.setFkUserId(Integer.valueOf(SessionMgr.getWebUser(request).getUserId().toString()));
			advService.insert(advModel);
			if(advModel.getAdType()==1){
				dwzResult = new DwzAjaxResult("200", "新增成功", "listSplash",
						"closeCurrent", "");
			}else{
				dwzResult = new DwzAjaxResult("200", "新增成功", "listPlaque",
					"closeCurrent", "");
			}
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 广告修改界面
	 * 
	 * @param pager
	 * @param advModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/editSplash")
	public String editSplash(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblAdvertisement advModel, Model model, HttpServletRequest request) {
		advModel = advService.selectOne(advModel);
		model.addAttribute("advModel", advModel);
		// 跳转至广告新增页面
		return "backstage/advertisement/splash/editSplash";
	}
	@RequestMapping("/editPlaque")
	public String editPlaque(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblAdvertisement advModel, Model model, HttpServletRequest request) {
		advModel = advService.selectOne(advModel);
		model.addAttribute("advModel", advModel);
		// 跳转至广告新增页面
		return "backstage/advertisement/plaque/editPlaque";
	}

	/**
	 * 修改广告
	 * 
	 * @param advModel
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateAdvertisement")
	@ResponseBody
	public String updateAdvertisement(TblAdvertisement advModel,
			HttpSession session, HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			if (advService.getLimitCount(advModel) > 0){
				if(advModel.getAdType()==1)
					return new JsonObject(new DwzAjaxResult("300",
						"该时间段已经存在一个闪屏图片，请更换起止时间！", "", "", "")).toString();
				else 
					return new JsonObject(new DwzAjaxResult("300",
							"该位置，该时间段已经存在一个插屏图片，请更换起止时间！", "", "", "")).toString();
			}
			advModel.setAdStatus(0);
			advModel.setFkUserId(Integer.valueOf(SessionMgr.getWebUser(request).getUserId().toString()));
			advService.update(advModel);
			if(advModel.getAdType()==1){
				dwzResult = new DwzAjaxResult("200", "修改成功", "listSplash",
						"closeCurrent", "");
			}else{
			dwzResult = new DwzAjaxResult("200", "修改成功", "listPlaque",
					"closeCurrent", "");
			}
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "修改失败", "", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 下架
	 * 
	 * @param advModel
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/downAdvertisement")
	@ResponseBody
	public String downAdvertisement(TblAdvertisement advModel,
			HttpSession session, HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			advModel.setEndAdTime(new Date());
			advModel.setAdStatus(1);
			advModel.setFkUserId(Integer.valueOf(SessionMgr.getWebUser(request).getUserId().toString()));
			advService.update(advModel);
			if(advModel.getAdType()==1){
				dwzResult = new DwzAjaxResult("200", "下架成功", "listSplash",
						"", "");
			}else{
			dwzResult = new DwzAjaxResult("200", "下架成功", "listPlaque",
					"", "");
			}
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "下架失败", "", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

}

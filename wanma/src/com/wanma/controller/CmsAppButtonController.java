package com.wanma.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblAppButton;
import com.wanma.model.TblBanner;
import com.wanma.model.TblUser;
import com.wanma.service.CmsAppButtonService;


/**
 * @Description: appButton管理controller
 * @author mb
 * @updateTime：
 * @version：V4.0
 */
@Controller
@RequestMapping("/admin/appButton")
public class CmsAppButtonController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsAppButtonController.class);
	/* 制造厂商service */
	@Autowired
	private  CmsAppButtonService appButtonService;
	
	/**
	 * button列表
	 * 
	 * @param pager
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/listAppButton")
	public String listAppButton(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblAppButton appButton, Model model, HttpServletRequest request) {
		log.info("******************获取button信息列表-begin************************");
		try {
			List<TblAppButton> buttonList = null;
			long total = appButtonService.getButtonListCount();
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			//设置查询参数
			pager.setTotal(total);
			appButton.setPager(pager);
			//获取button
			buttonList = appButtonService.getButtonList(appButton);
			//将数据放入会话
			model.addAttribute("appButton",appButton);
			model.addAttribute("buttonList",buttonList);
			model.addAttribute("pager",pager);
		} catch (Exception e) {
			log.error("获取button列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取button信息列表-end************************");
		// 跳转至banner列表页面
		return "backstage/appButton/appButtonList";
	}
	
	/**
	 * 跳转button新增
	 * 
	 */
	@RequestMapping("/addAppButtonUi")
	public String addAppButtonUi(Model model,HttpServletRequest request){
		//跳转button新增页面
		return "backstage/appButton/appButtonAdd";
	}
	/**
	 * 
	 * button新增
	 * 
	 */
	@RequestMapping("/addAppButton")
	@ResponseBody
	public String addAppButton(TblAppButton appButton,HttpServletRequest request,HttpSession session){
		// 处理结果信息
				DwzAjaxResult dwzResult = null;
		try {
			appButtonService.insertButton(appButton);
			
			
			dwzResult = new DwzAjaxResult("200", "新增成功", "listAppButton",
					"closeCurrent", "");
		} catch (Exception e) {
			log.error("新增banner失败", e);
			// 返回错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "buttonAddPage",
					"", "");	
		}
		// 返回处理结果信息
				return new JsonObject(dwzResult).toString();
	}
	/**
	 * 
	 * 跳转button编辑
	 */
	@RequestMapping("/editButtonUi")
	public String editButtonUi(@RequestParam("id") int pkButtonId ,Model model,HttpServletRequest request){
		TblAppButton appButton = appButtonService.getButtonById(pkButtonId);
		model.addAttribute("appButton",appButton);
		//跳转button新增页面
		return "backstage/appButton/appButtonEdit";
	}
	/**
	 * 
	 * button编辑
	 */
	@RequestMapping("/editAppButton")
	@ResponseBody
	public String editAppButton(TblAppButton appButton,HttpServletRequest request,HttpSession session){
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
			try {
				TblAppButton appButton2 = appButtonService.getButtonById(appButton.getPkButtonId());
				int oldButtonStatus = appButton2.getButtonStatus();
				int newButtonStatus = appButton.getButtonStatus();
				if(oldButtonStatus==3&&newButtonStatus==1){
					appButton.setButtonSort(99);
				}else{
					appButton.setButtonSort(appButton2.getButtonSort());
				}
				appButtonService.updateButton(appButton);
				
				
				dwzResult = new DwzAjaxResult("200", "新增成功", "listAppButton",
						"closeCurrent", "");
				
			} catch (Exception e) {
				log.error("编辑button失败", e);
				// 返回错误信息
				dwzResult = new DwzAjaxResult("300", "编辑失败", "buttonEditPage",
						"", "");
			}
			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		
	}
	/**
	 * button删除 
	 * 
	 */
	@RequestMapping("/deleteAppButton")
	@ResponseBody
	public String deleteAppButton(@RequestParam("id") int pkButtonId ,Model model,HttpServletRequest request){
		// 处理结果信息
				DwzAjaxResult dwzResult = null;
					try {
						appButtonService.deleteButtonById(pkButtonId);
						
						
						dwzResult = new DwzAjaxResult("200", "删除成功", "listAppButton",
								"", "");
						
					} catch (Exception e) {
						log.error("删除按钮失败", e);
						// 返回错误信息
						dwzResult = new DwzAjaxResult("300", "删除失败", "listAppButton",
								"", "");
					}
					// 返回处理结果信息
					return new JsonObject(dwzResult).toString();
	}
	/**
	 * button下架
	 * 
	 */
	@RequestMapping("/downAppButton")
	@ResponseBody
	public String downAppButton(@RequestParam("id") int pkButtonId ,Model model,HttpServletRequest request){
		// 处理结果信息
				DwzAjaxResult dwzResult = null;
					try {
						TblAppButton appButton2 = appButtonService.getButtonById(pkButtonId);
						if(appButton2.getButtonStatus()==3){
							dwzResult = new DwzAjaxResult("200", "已关闭的不能下架", "listAppButton",
									"", "");
						}else{
							appButtonService.downButtonById(pkButtonId);
							dwzResult = new DwzAjaxResult("200", "下架成功", "listAppButton",
									"", "");
						}
						
					} catch (Exception e) {
						log.error("下架按钮失败", e);
						// 返回错误信息
						dwzResult = new DwzAjaxResult("300", "下架失败", "listAppButton",
								"", "");
					}
					// 返回处理结果信息
					return new JsonObject(dwzResult).toString();
	}
	/**
	 * 修改轮播顺序
	 * @return
	 */
	@RequestMapping(value = "/editButtonOrder")
	@ResponseBody
	public String editButtonOrder(HttpServletRequest request) {
		DwzAjaxResult dwzResult=null;
		try {
			String pkButtonId=request.getParameter("pkButtonId");
			String newButtonSort=request.getParameter("newButtonSort");
			TblAppButton appButton = new TblAppButton();
			appButton.setPkButtonId(Integer.parseInt(pkButtonId));
			appButton.setButtonSort(Integer.parseInt(newButtonSort));
			appButtonService.changeButtonSort(appButton);
			dwzResult = new DwzAjaxResult("200", "修改成功", "",
					"", "");
		} catch (Exception e) {
			dwzResult = new DwzAjaxResult("300", "修改失败", "", "",
					"");
		}
		return new JsonObject(dwzResult).toString();
	}
	
}



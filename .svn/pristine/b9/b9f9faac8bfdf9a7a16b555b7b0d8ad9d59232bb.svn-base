package com.wanma.controller;

import java.util.List;
import java.util.Map;

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
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblGateservice;
import com.wanma.model.TblUser;
import com.wanma.service.GateService;

/**
 * 电桩管理
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime 2015-6-24 下午06:59:30
  * @updator： 
  * @updateTime：   
  * @version：V1.0
  * 其他可选例子：
 */
@Controller
@RequestMapping("/admin/gate/")
public class GateController extends BaseController{

	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(GateController.class);
	@Autowired
    private GateService gateService;
	
	/**
	 * 获取GATE列表模式数据
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/getGateList")
	public String getGateList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblGateservice tblGateservice, Model model,HttpServletRequest request) {
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}
		List<TblGateservice> gateList=gateService.getGateList();
		// 取得登录用户信息
		TblUser loginUser = SessionMgr.getWebUser(request);
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("gateList", gateList);
		// 跳转至管理员主页面
		return "backstage/gateService/gate-list";
	}
	
	/**
	 * GATE新增界面
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/addGateUi")
	public String addGateUi(Model model) {
		// 跳转至管理员主页面
		return "backstage/gateService/gate-add";
	}
	/**
	 * GATE新增界面
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/addGate")
    @ResponseBody
	public String addGate(@ModelAttribute TblGateservice tblGateservice,Model model,
			HttpServletRequest request) {
		
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			if(gateService.checkGateName(tblGateservice.getGtseGatename())){
				tblGateservice.setGtSeCreateUserId(SessionMgr.getWebUser(request).getUserId().toString());
				gateService.addGate(tblGateservice);            
				dwzResult = new DwzAjaxResult("200", "新增成功", "getGateList",
						"closeCurrent", "");
			}else{
				dwzResult = new DwzAjaxResult("300", "新增失败,名称重复", "addGateUi", "",
				"");
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.error("新增失败", e);
			dwzResult = new DwzAjaxResult("300", "新增失败", "addGateUi", "",
			"");
		}
		
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * GATE修改界面
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/chageGateUi")
	public String chageGateUi(Model model,@RequestParam(value = "gateId") String gateId) {
		
		TblGateservice tblGateservice=gateService.getGateById(JudgeNullUtils.nvlInteger(gateId));
		
		long gateCount=gateService.findElectricByGateCount(JudgeNullUtils.nvlInteger(gateId));
		model.addAttribute("tblGateservice", tblGateservice);
		model.addAttribute("gateCount", gateCount);
		// 跳转至管理员主页面
		return "backstage/gateService/gate-edit";
	}
	/**
	 * GATE修改
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/chageGate")
    @ResponseBody
	public String chageGate(@ModelAttribute TblGateservice tblGateservice,Model model) {
		
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			gateService.modifyGate(tblGateservice);
			dwzResult = new DwzAjaxResult("200", "修改成功", "getGateList",
					"closeCurrent", "");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("修改失败", e);
			dwzResult = new DwzAjaxResult("300", "修改失败", "chageGateUi", "",
			"");
		}
		
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/**
	 * GATE查看界面
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/showGateUi")
	public String showGateUi(Model model,@RequestParam(value = "gateId") String gateId) {
		
		TblGateservice tblGateservice=gateService.getGateById(JudgeNullUtils.nvlInteger(gateId));
		
		model.addAttribute("tblGateservice", tblGateservice);
		// 跳转至管理员主页面
		return "backstage/gateService/gate-show";
	}
	/**
	 * GATE删除
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/removeGate")
    @ResponseBody
	public String removeGate(Model model,@RequestParam(value = "ids") String ids) {
		
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			return gateService.deleteGate(ids).toJSONString();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("修改失败", e);
			dwzResult = new DwzAjaxResult("300", "删除失败", "getGateList", "",
			"");
		}
		
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}

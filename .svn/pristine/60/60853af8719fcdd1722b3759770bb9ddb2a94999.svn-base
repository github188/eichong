package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.app.controller.ElectricPileListController;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.model.TblCouponVariety;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCouponVarietyService;
import com.wanma.service.WebElectricPileMonitorService;

/**
 * 优惠券管理
 */
@Controller
@RequestMapping("/admin/couponVariety")
public class CmsCouponVarietyController {
	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(ElectricPileListController.class);
	@Autowired
	private CmsCouponVarietyService cmsCouponVarietyService;
	/**
	 * 获取现金券品种列表
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getCouponVarietyList")
	public String getCouponVarietyList(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblCouponVariety tblCouponVariety, Model model,
			HttpServletRequest request){
			List<TblCouponVariety> couponVarietyList = null;
			long total = 0;
			try {
				// 取得现金劵品种数量
				total = cmsCouponVarietyService.getCouponVarietyCount(tblCouponVariety);
				if (total <= pager.getOffset()) {
					pager.setPageNum(1L);
				}
				// 设置分页对象
				tblCouponVariety.setPager(pager);
				// 取得现金劵品种列表
				couponVarietyList = cmsCouponVarietyService.getCouponVarietyList(tblCouponVariety);
				pager.setTotal(total);
			} catch (Exception e) {
			}
			model.addAttribute("couponVarietyList", couponVarietyList);
			model.addAttribute("pager", pager);
			model.addAttribute("tblCouponVariety", tblCouponVariety);
			
			return "backstage/couponVariety/couponVarietyList";
	}
	/***
	 * 添加现金券品种初始化界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addCouponVarietyUi")
	public String addCouponVarietyUi(Model model, HttpServletRequest request) {
		return "backstage/couponVariety/addCouponVariety";
		
	}
	/***
	 * 添加现金券品种
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addCouponVariety")
	@ResponseBody
	public String addCouponVariety(TblCouponVariety tblCouponVariety,
			HttpSession session,HttpServletRequest request) {
		// 处理结果信息
				DwzAjaxResult dwzResult = null;
			try {
				//判断新增的现金劵品种名称是否重复
				if(cmsCouponVarietyService.judgeCovaActivityName(tblCouponVariety.getCovaActivityName())!=0){
					dwzResult = new DwzAjaxResult("300", "名称重复", "addCouponVariety", "", "");
				}else{
					//获取用户的id
					 TblUser loginUser = SessionMgr.getWebUser(request);
					 //创建现金劵品种的用户id
					 String covaCreateUserId = loginUser.getUserId().toString();
					 tblCouponVariety.setCovaCreateUserId(covaCreateUserId);
					//修改现金劵品种的用户id
					 String covaUpdateUserId = loginUser.getUserId().toString();
					 tblCouponVariety.setCovaUpdateUserId(covaUpdateUserId);
					cmsCouponVarietyService.addCouponVariety(tblCouponVariety);
					dwzResult = new DwzAjaxResult("200", "新增成功", "getCouponVarietyList",
							"closeCurrent", "");
				}
			} catch (Exception e) {
				// 出错日志
				log.error(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "新增失败", "addCouponVariety", "", "");
			}
		return new JsonObject(dwzResult).toString();
		
	}
	/***
	 * 跳转修改现金券品种界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeCouponVarietyUi")
	public String changeCouponVarietyUi(@RequestParam("id") int pkCouponVariety,HttpSession session,
			Model model, HttpServletRequest request) {
				TblCouponVariety tblCouponVariety = new TblCouponVariety();
				tblCouponVariety = cmsCouponVarietyService.getCouponVarietyById(pkCouponVariety);
				model.addAttribute("tblCouponVariety", tblCouponVariety);
				return "backstage/couponVariety/editCouponVariety";
	}
	
	/***
	 * 修改现金券品种
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeCouponVariety")
	@ResponseBody
	public String changeCouponVariety(
			TblCouponVariety tblCouponVariety,
			HttpSession session, HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			//获取用户的id
			 TblUser loginUser = SessionMgr.getWebUser(request);
			//修改现金劵品种的用户id
			 String covaUpdateUserId = loginUser.getUserId().toString();
			 tblCouponVariety.setCovaUpdateUserId(covaUpdateUserId);
			//获取页面用户的选择上架或者下架
			 int covaStutas = tblCouponVariety.getCovaStutas();
			 if(covaStutas == 0){
				 //修改现金劵品种
				 cmsCouponVarietyService.changeCouponVariety(tblCouponVariety);
			 }else if(covaStutas == 1){
				 //优惠券里绑定此现金劵品种的且还未兑换的状态变成已过期
				 cmsCouponVarietyService.removeCouponByCouponVarietyId(tblCouponVariety.getPkCouponVariety());
				 cmsCouponVarietyService.changeCouponVariety(tblCouponVariety);
			 }
			
			dwzResult = new DwzAjaxResult("200", "修改成功", "getCouponVarietyList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "修改失败", "changeCouponVariety", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
		
	}

}

package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
import com.wanma.model.TblEquipmentrepair;
import com.wanma.model.TblUser;
import com.wanma.service.CmsEquipmentrepairService;
import com.wanma.service.impl.CmsEquipmentrepairExcelServiceImpl;

/**
 * 设备报修管理
 * 
 * @author xiay
 *
 */
@Controller
@RequestMapping("/admin/device")
public class CmsEquipmentrepairController extends BaseController {
	
	//日志输出对象
		private static Logger log = Logger.getLogger(CmsEquipmentrepairController.class);
		
		/** 设备报修处理对象 */
		@Autowired
		private CmsEquipmentrepairService equipmentrepairService;
		
		/** 设备报修导出处理对象 */
		@Autowired
		private CmsEquipmentrepairExcelServiceImpl equipmentrepairExcelService;
		
		/**
		 * 取得设备报修列表
		 * 
		 * @author xiay
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/deviceList")
		public String getEquipList(@ModelAttribute("pager") DwzPagerMySQL pager,
				@ModelAttribute TblEquipmentrepair tblEquipmentrepair,
				Model model,HttpServletRequest request){
			log.info("************获取设备报修列表-begin************");
			
			 // 获取登陆用户
     		TblUser  loginUser = SessionMgr.getWebUser(request);
     		//纯商家和个体商家只能看到自己电桩或充电点的报修信息，超级管理员和管理员可以看到所有
     		if(loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS){//商家用户
     			tblEquipmentrepair.setUserName(loginUser.getUserAccount());
     		}else if(loginUser.getUserLevel()==WanmaConstants.USER_LEVEL_BUSINESS_NORMAL){//个体商家用户
     			tblEquipmentrepair.setUserName(loginUser.getUserId()+"");
     		}
     		//传进来的参数不包括时分秒，在这里补上
			if(!StringUtils.isEmpty(tblEquipmentrepair.getEnd_create_date())){
				tblEquipmentrepair.setEnd_create_date(tblEquipmentrepair.getEnd_create_date() + " 23:59:59");
			}
			//设备报修信息
			List<TblEquipmentrepair> equipList = null;
			
			//设备报修总数
			long total = 0;
				
				// 用户总数
				total = equipmentrepairService.searchEquipCount(tblEquipmentrepair);
				if(total<=pager.getOffset()){
					pager.setPageNum(1L);
				}
				//设置分页对象
				tblEquipmentrepair.setPager(pager);
				
				//取得设备报修信息
				equipList = equipmentrepairService.searchEquipList(tblEquipmentrepair);
				
				
				pager.setTotal(total);
			//使用完毕后将时间还原回去
			if(!StringUtils.isEmpty(tblEquipmentrepair.getEnd_create_date())){
				tblEquipmentrepair.setEnd_create_date(tblEquipmentrepair.getEnd_create_date().replace(" 23:59:59", ""));
			}
			//将设备报修信息放到会话中
			model.addAttribute("tblEquipmentrepair", tblEquipmentrepair);
			model.addAttribute("equipList", equipList);
			model.addAttribute("pager", pager);
			log.info("************获取设备报修列表-end************");	
			//跳转至设备报修信息
			return "backstage/device/listDevice";
		}
		
		/**
		 * 编辑设备报修状态
		 * 
		 * @author xiay
		 * @since Version 1.0
		 * @throws 无
		 */
		@RequestMapping("/updateEquipStage")
		@ResponseBody
		public String updateEquipStage(TblEquipmentrepair record) {
			log.info("************编辑用户状态-begin************");

			// 处理结果信息
			DwzAjaxResult dwzResult = null;

			try {
				equipmentrepairService.updateEquipStage(record);
				dwzResult = new DwzAjaxResult("200", "编辑成功", "deviceList", "closeCurrent", "");
			} catch (Exception e) {
				// 出错日志
				log.info(e.getLocalizedMessage());
				// 设置错误信息
				dwzResult = new DwzAjaxResult("300", "编辑失败", "deviceList", "", "");
			}
			log.info("************编辑用户状态-end************");
			// 返回处理结果信息
			return new JsonObject(dwzResult).toString();
		}


		@RequestMapping("/equipmentrepairExport")
		@ResponseBody
		public void equipmentrepairExport(
				@ModelAttribute("paramModel") TblEquipmentrepair paramModel,
				HttpServletRequest req, HttpServletResponse res) {
			log.info("开始导出设备报修清单信息");
			try {
				equipmentrepairExcelService.export(res, paramModel,"设备报修清单.xls");
			} catch (Exception e) {
				e.printStackTrace();
				log.info("导出设备报修清单信息出错，数据写入出错");
			}
		}

		/**
		 * 报修处理页面
		 */
		@RequestMapping(value = "/deviceDealUi")
		public String deviceDealUi(@RequestParam("id") String deviceId,
				Model model) {
			model.addAttribute("deviceId", deviceId);
			// 跳转至用户选择画面
			return "backstage/device/deviceDealUi";
		}
}

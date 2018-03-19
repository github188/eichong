package com.wanma.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.City;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblBanner;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblMessageInfo;
import com.wanma.model.TblNewsInfo;
import com.wanma.model.TblUser;
import com.wanma.service.CmsMessageInfoService;
/**
 * @Description: 首页消息管理controller
 * @author mb
 * @updateTime：
 * @version：V4.0
 */
@Controller
@RequestMapping("/admin/messageInfo")
public class CmsMessageInfoController  extends BaseController{
	// 日志输出对象
		private static Logger log = Logger.getLogger(CmsMessageInfoController.class);
		/* 制造厂商service */
		@Autowired
		private CmsMessageInfoService cmsMessageInfoService;
	
	/**
	 * 首页消息列表
	 * 
	 * @param pager
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/messageInfoList")
	public String messageInfoList(@ModelAttribute("pager") DwzPagerMySQL pager,Map<String, Object> params,TblMessageInfo messageInfo,
			String messageInfoCityCode,
			 Model model, HttpServletRequest request){
		log.info("******************获取首页消息列表-begin************************");
		try { 
			List<TblMessageInfo> messageInfoList = null;
			List<Map<String, Object>> cityList =new ArrayList<Map<String, Object>>();
			// 获取地区信息
			model.addAttribute("proviceMap", WanmaConstants.provinceMap);
			long total = cmsMessageInfoService.getMessageInfoListCount(messageInfo);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			//设置查询参数
			pager.setTotal(total);
			messageInfo.setPager(pager);
			messageInfoList = cmsMessageInfoService.getMessageInfoList(messageInfo);
			//把省市区号转换中中文
			 cityList =   cmsMessageInfoService.getCityName();
			for(int j=0;j<messageInfoList.size();j++){
				if(messageInfoList.get(j).getMessageInfoCityCode().isEmpty()){
					messageInfoList.get(j).setMessageInfoRegion("全国");
				}else{
					for(int i=0;i<cityList.size();i++){
						if(cityList.get(i).get("CITY_ID").equals(messageInfoList.get(j).getMessageInfoCityCode())){
							messageInfoList.get(j).setMessageInfoRegion(cityList.get(i).get("CITY_NAME").toString());
						}	
					}
				}
			}
			//将数据放入会话
			model.addAttribute("messageInfo",messageInfo);
			model.addAttribute("messageInfoList",messageInfoList);
			model.addAttribute("pager",pager);
		} catch (Exception e) {
			log.error("获取首页消息列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取首页消息列表-end************************");
		// 跳转至banner列表页面
		return "backstage/messageInfo/messageInfoList";
	}
	/**
	 * 新增首页消息
	 * 
	 */
	@RequestMapping("/addMessageInfoUi")
	public String addMessageInfoUi(Model model,HttpServletRequest request){
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		//跳转首页消息新增页面
		return "backstage/messageInfo/messageInfoAdd";
	}
	/**
	 * 动态关联充电点
	 * 
	 * @author xiay
	 * @return Object
	 * @throws 无
	 */
	@RequestMapping("/getPowerstation")
	@ResponseBody
	public Object getPowerstation(
			@RequestParam(value = "powerstationName") String powerstationName ,
			@RequestParam(value = "proviceId") String proviceId,
			@RequestParam(value = "cityId") String cityId )
	{
		try {
			Map<String,String> params=new HashMap<String, String>();
			params.put("powerstationName", powerstationName);
			params.put("proviceId", proviceId);
			params.put("cityId", cityId);
			List<Object> powerstation = cmsMessageInfoService.getpowerstation(params);
			return JSONObject.toJSONString(powerstation);
		} catch (Exception e) {
			System.out.print(e);
		}
		return null;
	}
	/**
	 * 
	 * 新增资讯首页消息
	 * 
	 */
	@RequestMapping("/addMessageInfo")
	@ResponseBody
	public String addMessageInfo(TblMessageInfo messageInfo,HttpServletRequest request,HttpSession session){
		// 处理结果信息
				DwzAjaxResult dwzResult = null;
		try {
			String[] pkPowerstation = request.getParameterValues("pkPowerstationList");
			if(pkPowerstation==null){
				dwzResult = new DwzAjaxResult("300", "未绑定电站", "messageInfoAddPage",
						"", "");
			}else{
				cmsMessageInfoService.insertMessageInfo(messageInfo,pkPowerstation);
				dwzResult = new DwzAjaxResult("200", "新增成功", "listMessageInfo",
						"closeCurrent", "");
			}
			
		} catch (Exception e) {
			log.error("新增资讯失败", e);
			// 返回错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "messageInfoAddPage",
					"", "");	
		}
		// 返回处理结果信息
				return new JsonObject(dwzResult).toString();
	}
	/**
	 * 编辑首页消息
	 * 
	 */
	@RequestMapping("/editMessageInfoUi")
	public String editMessageInfoUi(@RequestParam("id") String id ,Model model,HttpServletRequest request){
		String[] split = id.split(":");
		int pkMessageInfoId = Integer.parseInt(split[0]);
		TblMessageInfo messageInfo = cmsMessageInfoService.getMessageInfoById(pkMessageInfoId);
		List<Map<String, Object>> powerstationList =new ArrayList<Map<String, Object>>();
		powerstationList = cmsMessageInfoService.getPowerstationById(pkMessageInfoId);
		model.addAttribute("powerstationList", powerstationList);
		model.addAttribute("messageInfo", messageInfo);
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		//跳转首页消息新增页面
		return "backstage/messageInfo/messageInfoEdit";
	}
	/**
	 * 修改首页消息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editMessageInfo")
	@ResponseBody
	public String editMessageInfo(TblMessageInfo messageInfo,
			HttpSession session, HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			String messageInfoProvinceCode = messageInfo.getMessageInfoProvinceCode();
			if(messageInfoProvinceCode.isEmpty()){
				messageInfo.setMessageInfoCityCode("");
			}
			List<Map<String, Object>> oldPowerstationList =new ArrayList<Map<String, Object>>();
			oldPowerstationList = cmsMessageInfoService.getPowerstationById(messageInfo.getPkMessageInfoId());
			cmsMessageInfoService.updateMessageInfo(messageInfo);
			String[] newPkPowerstation = request.getParameterValues("pkPowerstationList");
			String[] oldPkPowerstation = new String[oldPowerstationList.size()];
			if(newPkPowerstation==null){
				dwzResult = new DwzAjaxResult("300", "未绑定电站", "messageInfoAddPage",
						"", "");
			}else{
				for(int j=0;j<oldPowerstationList.size();j++){
					oldPkPowerstation[j]=oldPowerstationList.get(j).get("pkPowerstation").toString();
				}
				for (String id : newPkPowerstation)
					{
						int flag =0;
					    for(int j=0;j<oldPkPowerstation.length;j++){
					    	if(id.trim().equals(oldPkPowerstation[j].trim())){
					    		flag++;
					    	}
					    }
					    if(flag==0){
					    	messageInfo.setPkPowerstation(Integer.parseInt(id));
					    	messageInfo.setMprName(cmsMessageInfoService.getMprNameByPkPowerstation(id));
					    	cmsMessageInfoService.relationPowerStation(messageInfo);
					    }
				}
				for(String oldId:oldPkPowerstation){
					int flag =0;
					for(int j=0;j<newPkPowerstation.length;j++){
					    	if(oldId.trim().equals(newPkPowerstation[j].trim())){
					    		flag++;
					    	}
					}
					if(flag==0){
				    	messageInfo.setPkPowerstation(Integer.parseInt(oldId));
				    	cmsMessageInfoService.removeRelationPowerStation(messageInfo);
				    }
			    	
			    }
				dwzResult = new DwzAjaxResult("200", "修改成功", "listMessageInfo",
						"closeCurrent", "");
			}
			
		} catch (Exception e) {
			log.error("编辑首页消息失败", e);
			// 返回错误信息
			dwzResult = new DwzAjaxResult("300", "修改失败", "messageInfoEditPage",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	/**
	 * 关闭首页消息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/closeMessageInfo")
	@ResponseBody
	public String closeMessageInfo(@RequestParam("id") String id) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
				String[] split = id.split(":");
				String status = split[1];
				int pkMessageInfoId = Integer.parseInt(split[0]);
				if(status.equals("已关闭")){
					dwzResult = new DwzAjaxResult("300", "已关闭的不能关闭", "listMessageInfo","", "");
				}else{
					cmsMessageInfoService.closeMessageInfoById(pkMessageInfoId);
					dwzResult = new DwzAjaxResult("200", "关闭成功", "listMessageInfo","", "");
				}
		} catch (Exception e) {
			log.error("关闭首页消息", e);
			// 返回错误信息
			dwzResult = new DwzAjaxResult("300", "关闭失败", "listMessageInfo",
					"", "");
		}
		
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	/**
	 * 删除首页消息
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteMessageInfo")
	@ResponseBody
	public String deleteMessageInfo(@RequestParam("id") String id) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			String[] split = id.split(":");
			int pkMessageInfoId = Integer.parseInt(split[0]);
				cmsMessageInfoService.deleteMessageInfoById(pkMessageInfoId);
				cmsMessageInfoService.deleteMessageInfoPointById(pkMessageInfoId);
				dwzResult = new DwzAjaxResult("200", "删除成功", "listMessageInfo","", "");
		} catch (Exception e) {
			log.error("关闭首页消息", e);
			// 返回错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "listMessageInfo",
					"", "");
		}
		
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
}

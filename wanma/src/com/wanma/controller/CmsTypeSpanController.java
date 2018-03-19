package com.wanma.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.app.controller.ElectricPileListController;
import com.wanma.common.WanmaConstants;
import com.wanma.dao.CmsCommitLogMapper;
import com.wanma.model.TblBomList;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblEquipmentVersion;
import com.wanma.model.TblTypespan;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsEquipmentVersionService;
import com.wanma.service.CmsTblTypespanService;

/**
 * FrontEndDispatcherCtrl 地图跳转
 * 
 * @author Haner
 */
@Controller
@RequestMapping("/admin/product")
public class CmsTypeSpanController {

	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(ElectricPileListController.class);
	
	@Autowired
	private CmsTblTypespanService tblTypespanService;

	@Autowired
	private CmsCommitLogMapper commitLogDao;
	@Autowired
	private CmsCommitLogService commitLogService;
	@Autowired
	private CmsEquipmentVersionService epVersionService;
	/**
	 * 产品型号列表
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("typeSpanList")
	public String electric(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblTypespan tblTypespan, Model model) {
		// 总数
		long total = tblTypespanService.getTblTypespanListCount(tblTypespan);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		tblTypespan.setPager(pager);
		List<Map<String, Object>> tblTypespanList = (List<Map<String, Object>>) tblTypespanService
				.getTblTypespanList(tblTypespan);
		pager.setTotal(total);

		model.addAttribute("tblTypespanList", tblTypespanList);
		model.addAttribute("pager", pager);
		model.addAttribute("tblTypespan", tblTypespan);
		return "backstage/typeSpan/typeSpan-list";
	}

	/**
	 * 产品型号新增UI
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("addTypeSpanUi")
	public String addTypeSpanUi(Model model) {
		return "backstage/typeSpan/typeSpan-add";
	}

	/***
	 * 添加产品型号
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addTypeSpan")
	@ResponseBody
	public String addTypeSpan(TblTypespan tblTypespan, HttpSession session,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		boolean flag = true;
		try {

			if(tblTypespanService.selectTsTypeSpan(tblTypespan.getTsTypeSpan())>0){
				dwzResult = new DwzAjaxResult("300", "该产品型号已存在", "addTypeSpan",
						"", "");
			}else{
				List<TblBomList> bomList = tblTypespan.getBomList();
				for(int i=0;i<bomList.size()-1;i++){
					for(int j=i+1;j<bomList.size();j++){
						if(bomList.get(i).getBlFileMd5().equals(bomList.get(j).getBlFileMd5())&&
						   bomList.get(i).getBlFirmwareNumber().equals(bomList.get(j).getBlFirmwareNumber())&&
						   bomList.get(i).getBlFirmwareVersion().equals(bomList.get(j).getBlFirmwareVersion())&&
						   bomList.get(i).getBlHardwareNumber().equals(bomList.get(j).getBlHardwareNumber())&&
						   bomList.get(i).getBlHardwareVersion().equals(bomList.get(j).getBlHardwareVersion())&&
						   bomList.get(i).getBlForceUpdate()==bomList.get(j).getBlForceUpdate()){
							flag = false;
						}
					}
				}
				if(flag){
					tblTypespanService.addTypeSpan(tblTypespan);
					dwzResult = new DwzAjaxResult("200", "新增成功", "typeSpanList",
							"closeCurrent", "");
				}
				
			}
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "addTypeSpan", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 产品型号修改UI
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("changeTypeSpanUi")
	public String changeTypeSpanUi(TblTypespan tblTypespan, Model model) {
		TblTypespan tblTypespanInfo = tblTypespanService.findOne(tblTypespan);
		List<TblBomList> bomList = tblTypespanService
				.getBomList(tblTypespanInfo);
		if (bomList.size() == 0)
			bomList.add(new TblBomList());
		model.addAttribute("typeSpan", tblTypespanInfo);
		model.addAttribute("bomList", bomList);
		return "backstage/typeSpan/typeSpan-edit";
	}

	/***
	 * 修改产品型号
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/changeTypeSpan")
	@ResponseBody
	public String changeTypeSpan(TblTypespan tblTypespan, HttpSession session,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			tblTypespanService.changeTypeSpan(tblTypespan);
			dwzResult = new DwzAjaxResult("200", "修改成功", "typeSpanList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.error(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "修改失败", "addTypeSpan", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 产品型号查看
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("viewTypeSpanUi")
	public String viewTypeSpanUi(TblTypespan tblTypespan, Model model) {
		TblTypespan tblTypespanInfo = tblTypespanService.findOne(tblTypespan);
		List<TblBomList> bomList = tblTypespanService
				.getBomList(tblTypespanInfo);
		if (bomList.size() == 0)
			bomList.add(new TblBomList());
		model.addAttribute("typeSpan", tblTypespanInfo);
		model.addAttribute("bomList", bomList);
		return "backstage/typeSpan/typeSpan-view";
	}

	/***
	 * 跳转电桩版本升级页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/updateEpVisionUi")
	public String updateEpVisionUi(@RequestParam("pkTypeSpanId") String ids,TblElectricpile electricpile,  Model model,@ModelAttribute("pager") DwzPagerMySQL pager) {
		TblTypespan tblTypespan = new TblTypespan();
 		electricpile.setElpiTypeSpanId(Integer.parseInt(ids.split(",")[0]));
		tblTypespan.setPkTypeSpanId(Integer.parseInt(ids.split(",")[0]));
		// 总数
		long total = tblTypespanService.getPileListByIdCount(electricpile);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		electricpile.setPager(pager);
		//tblTypespan.setPager(pager);
		List<Map<String, Object>>  electricList = tblTypespanService.getPileListById(electricpile);
		pager.setTotal(total);
		List<TblBomList> bomList = tblTypespanService.getBomList(tblTypespan);
		TblTypespan tblTypespanInfo = tblTypespanService.findOne(tblTypespan);
		model.addAttribute("electricList", electricList);
		model.addAttribute("bomList", bomList);
		model.addAttribute("tblTypespan", tblTypespanInfo);
		model.addAttribute("electricpile", electricpile);
		model.addAttribute("pager", pager);
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		// 跳转至管理员主页面
		return "backstage/typeSpan/typeSpan-upgrade";
	}
	/***
	 * 根据产品型号获取bom
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/getBomListByTypeSpanId")
	@ResponseBody
	public String getBomListByTypeSpanId(@RequestParam("pkTypeSpanId") Integer pkTypeSpanId) {
		TblTypespan tblTypespan = new TblTypespan();
		tblTypespan.setPkTypeSpanId(pkTypeSpanId);
		List<TblBomList> bomList = tblTypespanService.getBomList(tblTypespan);
	
		return new JsonObject(bomList).toString();
	}
	
	/***
	 * 电桩版本升级
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/updateEpVision")
	@ResponseBody
	public String updateEpVision(HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = new DwzAjaxResult("200", "已下发升级命令","typeSpanList", "", "");
		try {
			String pkElectricpiles[]= request.getParameter("pkElectricpiles").split(":");
			String pkElectricpile = "";//用来写日志
			String pkBomListId = request.getParameter("pkBomListId");
			String pkTypeSpanId = request.getParameter("pkTypeSpanId");
			MessageManager m = new MessageManager();
			String apiBaseUrl = m.getSystemProperties("apiRoot");
			TblEquipmentVersion epVersion = new TblEquipmentVersion();
			epVersion = epVersionService.getBomById(pkBomListId);
            for(String evProductID:pkElectricpiles){//修改桩的upgrade_flag
                pkElectricpile +=evProductID+",";
                epVersionService.deleteByProductID(evProductID);
                epVersion.setEvProductID(evProductID);
                epVersion.setEvProductType("3");
                epVersion.setPkBomListId(Integer.valueOf(pkBomListId));
                epVersionService.insertEpVersion(epVersion);
            }
				String result = tblTypespanService.updateEpVision(pkTypeSpanId,pkBomListId,apiBaseUrl);
				if ("100".equals(result)) {
                    commitLogService.insert("电桩程序升级成功，产品型号表id：" + pkTypeSpanId);
    				log.info("升级电桩"+pkElectricpile);
				} else {
					commitLogService.insert("电桩电桩升级失败，产品型号表id：" + pkTypeSpanId);
					TblTypespan tblTypespan = new TblTypespan();
					tblTypespan.setPkTypeSpanId(Integer.valueOf(pkTypeSpanId));
					TblTypespan tblTypespanInfo = tblTypespanService.findOne(tblTypespan);
					return new JsonObject(new DwzAjaxResult("300",
							tblTypespanInfo.getTsModelName()
									+ "升级命令下发失败:接口返回错误代码：" + result,
							"typeSpanList", "", "")).toString();
			}
		} catch (Exception e) {
			// 出错日志
			log.error("操作失败", e);
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "升级命令下发失败，系统出错："
					+ e.getMessage(), "typeSpanList", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	
	/***
	 * 查询并升级
	 * 
	 * @param model
	 * @return
	 */	
	@RequestMapping("/checkUpgradeVersion")
	@ResponseBody
	public String checkUpgradeVersion(HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = new DwzAjaxResult("200", "已下发升级命令","", "", "");
		try {	
			String pkElectricpiles[]= request.getParameter("pkElectricpiles").split(",");
			String pkElectricpile = "";//用来写日志
			String pkTypeSpanId = request.getParameter("pkTypeSpanId");
			MessageManager m = new MessageManager();
			String apiBaseUrl = m.getSystemProperties("apiRoot");
			for(String item:pkElectricpiles){
				List<Map<String,Object>> list=tblTypespanService.getCheckUpList(item);
				
				
				for (int i=0;i<list.size();i++){
					Map<String,Object>  map=list.get(i);
					String ugFirmwareNumber=map.get("ugFirmwareNumber").toString();
					String ugFirmwareVersion=map.get("ugFirmwareVersion").toString();
					String firmwareNumber=map.get("firmwareNumber").toString();
					String firmwareVersion=map.get("firmwareVersion").toString();
					
					
					if("".equals(ugFirmwareNumber)||"".equals(ugFirmwareVersion)){
						 dwzResult = new DwzAjaxResult("205", "请先升级！","typeSpanList", "", "");
						 return new JsonObject(dwzResult).toString(); 
					}else{
						if(ugFirmwareNumber.equals(firmwareNumber) && ugFirmwareVersion.equals(firmwareVersion)){
							dwzResult = new DwzAjaxResult("206", "请勿重复升级！","typeSpanList", "", "");
							 return new JsonObject(dwzResult).toString(); 
						}
					}
			 
			/*	Set  set= map.keySet();
				Iterator ir=set.iterator();
				String ugFirmwareNumber=null;
				String ugFirmwareVersion=null;
				String firmwareNumber=null;
				String firmwareVersion=null;
				while(ir.hasNext()){
					Object key=ir.next();
					
				if(key!=null&& ((String)key).equals("ugFirmwareNumber")){
				   ugFirmwareNumber=(String)map.get(key);
				}
					
				if(key!=null&& ((String)key).equals("ugFirmwareVersion")){
					  ugFirmwareVersion=(String)map.get(key);
					}
				if(key!=null&& ((String)key).equals("firmwareNumber")){
					   firmwareNumber=(String)map.get(key);
					}
				if(key!=null&& ((String)key).equals("firmwareVersion")){
					   firmwareVersion=(String)map.get(key);
					}
				
				
				}*/
			
			}
			}
		//	TblEquipmentVersion epVersion = new TblEquipmentVersion();
			String pkBomListId=null;
			String pkEp[]= request.getParameter("pkElectricpiles").split(",");
			
		     String	items=pkEp[0];
			
				List<Map<String,String>> list=tblTypespanService.getBomIdUpgrade(items);
	    
			
				
				if(list.size()==0){
					 dwzResult = new DwzAjaxResult("205", "电桩id为："+items+"先升级！","typeSpanList", "", "");
					 return new JsonObject(dwzResult).toString(); 
				}
				for (int i=0;i<list.size();i++){
					pkBomListId =list.get(i).get("pk_BomListId");
					
					
				//	epVersion = epVersionService.getBomById(pkBomListId);
					/*for(String evProductID:pkElectricpiles){//修改桩的upgrade_flag
		                pkElectricpile +=evProductID+",";
		                epVersionService.deleteByProductID(evProductID);
		                epVersion.setEvProductID(evProductID);
		                epVersion.setEvProductType("3");                          
		                epVersionService.insertEpVersion(epVersion);
		            }*/
						String result = tblTypespanService.updateEpVision(pkTypeSpanId,pkBomListId,apiBaseUrl);
						
						if ("100".equals(result)) {
		                    commitLogService.insert("电桩程序升级成功，产品型号表id：" + pkTypeSpanId);
		    				log.info("升级电桩"+pkElectricpile);
						} else {
							commitLogService.insert("电桩电桩升级失败，产品型号表id：" + pkTypeSpanId);
							TblTypespan tblTypespan = new TblTypespan();
							tblTypespan.setPkTypeSpanId(Integer.valueOf(pkTypeSpanId));
							TblTypespan tblTypespanInfo = tblTypespanService.findOne(tblTypespan);
							return new JsonObject(new DwzAjaxResult("300",
									tblTypespanInfo.getTsModelName()
											+ "升级命令下发失败:接口返回错误代码：" + result,
									"typeSpanList", "", "")).toString();
					}
				}
			
			
            
		} catch (Exception e) {
			// 出错日志
			log.error("操作失败", e);
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "升级命令下发失败，系统出错："
					+ e.getMessage(), "typeSpanList", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString(); 

	}
}

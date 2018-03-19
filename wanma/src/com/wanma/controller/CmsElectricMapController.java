package com.wanma.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.bluemobi.product.common.MessageManager;
import com.wanma.common.PageResponse;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.ElectricPileDetail;
import com.wanma.model.ElectricPileMonitor;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblElectricpilehead;
import com.wanma.model.TblUser;
import com.wanma.service.CmsElectricSearchService;
import com.wanma.service.WebElectricPileMonitorService;
import com.wanma.service.impl.PowerStationDetailServiceMapImpl;
import com.wanma.web.support.utils.HttpRequest;


/**
 * FrontEndDispatcherCtrl
 * 地图跳转
 * @author Haner
 */
@Controller
@RequestMapping("/admin/electricPileMonitor")
public class CmsElectricMapController {
	@Autowired
	private WebElectricPileMonitorService webElectricPileMonitorService;
	@Autowired
	private CmsElectricSearchService electricPileMapService;
	
	@Autowired
	private PowerStationDetailServiceMapImpl powerStationDetailService;
	 /**
     * 地图电桩查询
     *
     * @param path
     * @return
     */
    @RequestMapping("search")
    public String electric(@ModelAttribute TblElectricpile tblElectricpile, Model model) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		return "backstage/electric/electric-map";
    }
    /**
     * 地图信息查询
     * @param request
     * @param params
     * @param pager
     * @param model
     * @param electricPileMonitor
     * @return
     */
    @RequestMapping("getElectricPileForMap")
	@ResponseBody
	public String getElectricPileForMap(HttpServletRequest request,@RequestParam Map<String, Object> params) {
		TblUser loginUser = SessionMgr.getWebUser(request);
		params.put("userLevel", loginUser.getUserLevel());
		params.put("userId", loginUser.getUserId());
		List<ElectricPileMonitor> electricPileMonitorMapList = webElectricPileMonitorService.getElectricPileMonitorForMap(params);	
		ElectricPileMonitor electricPileMonitor = new ElectricPileMonitor();
		electricPileMonitor.setElectricPileMonitorMapList(electricPileMonitorMapList);
		return JSON.toJSONString(electricPileMonitor);
	}
    /**
     * 地图列表查询
     * @param request
     * @param params
     * @param pager
     * @param model
     * @param electricPileMonitor
     * @return
     */
    @RequestMapping("getElectricPileMapList")
	@ResponseBody
	public String getElectricPileMapList(HttpServletRequest request,@RequestParam Map<String, Object> params
			) {
		// 电桩充电点总数
		PageResponse<List<ElectricPileMonitor>> pager = new PageResponse<List<ElectricPileMonitor>>(
				Integer.valueOf((String) params.get("pageNum")), Integer.valueOf((String) params.get("pageSize")));
		params.put("pager", pager);
		TblUser loginUser = SessionMgr.getWebUser(request);
		params.put("userLevel", loginUser.getUserLevel());
		params.put("userId", loginUser.getUserId());
		long total = webElectricPileMonitorService.getElectricpileMapCount(params);
		List<ElectricPileMonitor> electricPileMonitorList = webElectricPileMonitorService.getElectricPileMonitorForList(params);
		pager.setCountData(total);
		pager.setDate(electricPileMonitorList);
		return JSON.toJSONString(pager);
	}
    /**
     * 电桩列表查询
     * @param request
     * @param params
     * @param pager
     * @param model
     * @param electricPileMonitor
     * @return
     */
    @RequestMapping("getElectricPileListByStationId")
	@ResponseBody
	public String getElectricPileListByStationId(HttpServletRequest request,@RequestParam Map<String, Object> params){
    	List<ElectricPileMonitor> electricPileListByStationId = webElectricPileMonitorService.getElectricPileListByStationId(params);
		return JSON.toJSONString(electricPileListByStationId);
	}
    /**
     * 点击查询
     * @param request
     * @param params
     * @param pager
     * @param model
     * @param electricPileMonitor
     * @return
     */
    @RequestMapping("getElectricPileDetail")
    @ResponseBody
    public String getElectricPileDetail(HttpServletRequest request,@RequestParam Map<String, String> params){
    	String id = params.get("electricId").toString();
    	ElectricPileDetail pileDetail=electricPileMapService.getCurrentPileDetail(id);
    	List<TblElectricpilehead> headList=electricPileMapService.getCurrentHeadList(id);
    	ElectricPileMonitor electricPileMonitor = new ElectricPileMonitor();
    	electricPileMonitor.setHeadList(headList);
    	electricPileMonitor.setPileDetail(pileDetail);
    	params.put("epCode", pileDetail.getElectricPileNo());
    	params.put("elPiChargingMode", pileDetail.getElectricPileChargingMode());
    	electricPileMonitor.setFengzhiHtml(powerStationDetailService.makeFengzhiStr(powerStationDetailService.getTblRateinformation(id)));
		MessageManager manager=MessageManager.getMessageManager();
		String submitResult = HttpRequest.post(manager.getSystemProperties("hbaseUrl")+"/getRealtimeData", params);
		if("".equals(submitResult))
	    	electricPileMonitor.setMonitorData(new Object());
		else
	    	electricPileMonitor.setMonitorData(JSON.parse(submitResult));
		return JSON.toJSONString(electricPileMonitor);
    }
    
    /**
     * 故障电桩列表查询
     * @param request
     * @param params
     * @param pager
     * @param model
     * @param electricPileMonitor
     * @return
     */
    @RequestMapping("queryErrorPile")
	@ResponseBody
	public String queryErrorPile(@RequestParam Map<String, Object> params){
    	List<ElectricPileMonitor> electricPileList = webElectricPileMonitorService.queryErrorPile(params);
		return JSON.toJSONString(electricPileList);
	}
    
    /**
     * 获得故障电桩数量
     */
    @RequestMapping("/pileErrorCount")
	@ResponseBody
	public String pileErrorCount(@RequestParam Map<String, Object> params) throws URISyntaxException {
		return JSON.toJSONString(webElectricPileMonitorService.pileErrorCount(params));
	}
    
    /**
     * 获得充点电数量
     */
    @RequestMapping("/getAllPileCount")
	@ResponseBody
	public String getAllPileCount(@RequestParam Map<String, Object> params) throws URISyntaxException {
		return JSON.toJSONString(webElectricPileMonitorService.getAllPileCount(params));
	}
    /**
     * 获取充电中的电桩数量
     */
	@RequestMapping("/getChargingCount")
	@ResponseBody
	public String getChargingCount(@RequestParam Map<String, Object> params) throws URISyntaxException {
		return JSON.toJSONString(webElectricPileMonitorService.getChargingCount(params));
	}
	/**
     *  获取预约中电桩数量
     */
	@RequestMapping("/getBespokeCount")
	@ResponseBody
	public String getBespokeCount(@RequestParam Map<String, Object> params) throws URISyntaxException {
		return JSON.toJSONString(webElectricPileMonitorService.getBespokeCount(params));
	}
	
	/**
     *  获取分享点数量
     */
	@RequestMapping("/getOnlineCount")
	@ResponseBody
	public String getOnlineCount(@RequestParam Map<String, Object> params) throws URISyntaxException {
		return JSON.toJSONString(webElectricPileMonitorService.getOnlineCount(params));
	}
	/**
     *  获取专属点数量
     */
	@RequestMapping("/getOfflineCount")
	@ResponseBody
	public String getOfflineCount(@RequestParam Map<String, Object> params) throws URISyntaxException {
		return JSON.toJSONString(webElectricPileMonitorService.getOfflineCount(params));
	}
}

package com.wanma.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.model.ElectricPileDetail;
import com.wanma.model.TblElectricpilehead;
import com.wanma.service.CmsElectricSearchService;


@Controller
@RequestMapping("/admin/electricCurrent")
public class CmsElectricCurrentDetailController {

	@Autowired
	private CmsElectricSearchService electricPileMapService;

	
	
	@RequestMapping("/getElectricPileDetail")
	public String getElectricPileMapPage(HttpServletRequest request,ModelMap map){
		String eid = RequestParamUtil.getEncodeParam(request, "eid");
		ElectricPileDetail pileDetail=electricPileMapService.getCurrentPileDetail(eid);
		List<TblElectricpilehead> headList=electricPileMapService.getCurrentHeadList(eid);
		map.put("pile", pileDetail);
		map.put("headList", headList);
		return "backstage/electricCurrent/electric_detail";
	}
	
	@RequestMapping("/getHeadDetail")
	@ResponseBody
	public String getHeadDetail(@RequestParam Map<String, String> params) {
		TblElectricpilehead head=electricPileMapService.getHeadDetail(params);
		return new JsonObject(head).toString();
	}
	
}
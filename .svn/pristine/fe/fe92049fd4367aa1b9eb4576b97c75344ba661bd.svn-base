package com.wanma.app.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.RequestParamUtil;
import com.wanma.app.service.ElectricMapService;
import com.wanma.model.ElectricMap;

@Controller
@RequestMapping("/app/seamap")
public class SearchMapController {
	private static Log log = LogFactory.getLog(SearchMapController.class);
	@Autowired
	private ElectricMapService mapService;

	/**
	 * 地图界面 、地图充电点列表数据获取
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("/map")
	@ResponseBody
	public String map(HttpServletRequest request)throws AppRequestErrorException {
		String longitude = RequestParamUtil.getEncodeParam(request, "longitude");
		String latitude = RequestParamUtil.getEncodeParam(request, "latitude");
		String dc = RequestParamUtil.getEncodeParam(request, "dc");
		String ac = RequestParamUtil.getEncodeParam(request, "ac");
		String freetime = RequestParamUtil.getEncodeParam(request, "freetime");
		String freeparking = RequestParamUtil.getEncodeParam(request,"freeparking");
		String cpynum = RequestParamUtil.getEncodeParam(request,"cpynum");
		if(cpynum==null||cpynum.equals("")){
			cpynum="1000";
		}
		String currentlat = RequestParamUtil.getEncodeParam(request, "currentlat");
		String currentlng  = RequestParamUtil.getEncodeParam(request, "currentlng");
		if ("".equals(longitude) || "".equals(latitude)) {

			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		ElectricMap mapModel = new ElectricMap();

		mapModel.setPoStLatitude(latitude);
		mapModel.setPoStLongitude(longitude);
		mapModel.setAc(ac);
		mapModel.setDc(dc);
		mapModel.setFreeparking(freeparking);
		mapModel.setFreetime(freetime);
        mapModel.setCpyNum(cpynum);
        mapModel.setCurrentlat(currentlat);
        mapModel.setCurrentlng(currentlng);
		List<ElectricMap> mapList;
		try {
			mapList = mapService.getElectricMap(mapModel);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(mapList).toString();
	}

	/**
	 * 搜索全国范围充电点
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping("/search")
	@ResponseBody
	public String search(HttpServletRequest request)
			throws AppRequestErrorException {
		String keyword = RequestParamUtil.getEncodeParam(request, "keyword");
		String longitude = RequestParamUtil.getEncodeParam(request, "longitude");
		String latitude = RequestParamUtil.getEncodeParam(request, "latitude");
		String cpynum = RequestParamUtil.getEncodeParam(request, "cpynum");
		if(cpynum==null||cpynum.equals("")){
			cpynum="1000";
		}
		if ("".equals(keyword)) {

			return new AccessErrorResult(1050, "error.msg.miss.parameter")
					.toString();
		}
		ElectricMap mapModel = new ElectricMap();
		mapModel.setKeyword(keyword);
		mapModel.setPoStLatitude(latitude);
		mapModel.setPoStLongitude(longitude);
		mapModel.setCpyNum(cpynum);
		
		List<ElectricMap> mapList;
		try {
			mapList = mapService.getElectricMapBysearch(mapModel);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		return new AccessSuccessResult(mapList).toString();
	}

}

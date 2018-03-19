package com.wanma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblUser;
import com.wanma.service.CmsTwoDiCodeService;
import com.wanma.service.impl.CmsRateInfoServiceImpl;

@Controller
@RequestMapping("/admin/TwoDiCode")
public class CmsTwoDiCodeController {
	
	  @Autowired
	 CmsTwoDiCodeService  twoDiCodeService;
	  
	  @Autowired
	 CmsRateInfoServiceImpl    rateInfoService;
	
	  @RequestMapping("/getTwoDiCodeList")
	  public String getTwoDiCodeList(@ModelAttribute("pager") DwzPagerMySQL pager,
				@ModelAttribute HashMap<String,Object> params,HttpServletRequest request,
				Model model){		
 		    String electricPileAddress  = request.getParameter("electricPileAddress");
		    String elPi_OwnProvinceCode = request.getParameter("elPi_OwnProvinceCode");
		    String elPi_OwnCityCode     = request.getParameter("elPi_OwnCityCode");
		    String elPi_OwnCountyCode   = request.getParameter("elPi_OwnCountyCode");		  
		    List<HashMap<String, Object>> TwoDiCodeList = new ArrayList<HashMap<String,Object>>();		    
		    model.addAttribute("provinceMap",WanmaConstants.provinceMap);			
			if(StringUtils.isNotBlank(elPi_OwnProvinceCode)){
				List<Object> cityList=new ArrayList<Object>();
				for(String citycode:WanmaConstants.provinceCityMap.get(elPi_OwnProvinceCode)){
					Map<String,Object> cityMap=WanmaConstants.cityMap;
					cityList.add(cityMap.get(citycode));
				}
				model.addAttribute("cityList",cityList);
			}
			if(StringUtils.isNotBlank(elPi_OwnCityCode)){
				List<Object> areaList=new ArrayList<Object>();
				for(String areacode:WanmaConstants.cityAreaMap.get(elPi_OwnCityCode)){
					Map<String,Object> areaMap=WanmaConstants.areaMap;
					areaList.add(areaMap.get(areacode));
				}
				model.addAttribute("areaList",areaList);
			}
			params.put("elPi_OwnProvinceCode", elPi_OwnProvinceCode);
			params.put("elPi_OwnCityCode", elPi_OwnCityCode);
			params.put("elPi_OwnCountyCode", elPi_OwnCountyCode);
		  	params.put("electricPileAddress", electricPileAddress);		  	
			params.put("offset", pager.getOffset());
			params.put("numPerPage", pager.getNumPerPage());	   			
		    long total = twoDiCodeService.getTwoDiCodeListCount(params);
			if(total<=pager.getOffset()){
				pager.setPageNum(1L);
				params.put("offset", 0);
			}
			TblUser loginUser = SessionMgr.getWebUser(request);
			params.put("elpiUserid", loginUser.getUserId());
			params.put("userLevel", loginUser.getUserLevel());
			TwoDiCodeList =  twoDiCodeService.getTwoDiCodeList(params);			
			pager.setTotal(total);				
			model.addAttribute("params", params);
			model.addAttribute("TwoDiCodeList", TwoDiCodeList);
			model.addAttribute("pager", pager);
			model.addAttribute("elPi_OwnProvinceCode", elPi_OwnProvinceCode);
			model.addAttribute("elPi_OwnCityCode", elPi_OwnCityCode);
			model.addAttribute("elPi_OwnCountyCode", elPi_OwnCountyCode);
			return "backstage/twoDiCode/twoDiCodelist";
		  
	  }
}

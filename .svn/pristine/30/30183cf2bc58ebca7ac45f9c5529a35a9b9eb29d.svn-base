package com.wanma.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.service.AreaService;
import com.bluemobi.product.service.CityService;
import com.bluemobi.product.utils.AccessErrorResult;
import com.wanma.app.controller.ElectricPileListController;
import com.wanma.app.dao.TblElectricpileheadMapper;
import com.wanma.app.service.ElectricPileListService;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblRateInfo;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCarmakerService;
import com.wanma.service.CmsConfigcontentService;
import com.wanma.service.CmsPureBusinessService;
import com.wanma.service.impl.CmsRateInfoServiceImpl;

/**
 * 
 * 电桩管理
 * 
 * @Description:
 * @author xiay
 * @createTime：2015-3-25 上午10:57:53
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/sendRate/")
public class CmsElectricRateController {

	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(ElectricPileListController.class);

	@Autowired
	private CmsConfigcontentService msConfigcontentService;
	@Autowired
	private ElectricPileListService electricPileListService;
	@Autowired
	private TblElectricpileheadMapper tblElectricpileheadMapper;
	/* 制造厂商service */
	@Autowired
	private CmsCarmakerService carmakerService;

	@Autowired
	private CmsRateInfoServiceImpl rateInfoService;
	@Autowired
	private CityService cityService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private CmsPureBusinessService pureBusinessService;
	
	@Autowired
	CmsRateInfoServiceImpl cmsRateInfoService;
	
	@RequestMapping("chooseElectricRate")
	public String chooseRateById(
			@ModelAttribute TblRateInfo tblRateInfo, HttpSession session,HttpServletRequest request,Model model){	
		String ids=request.getParameter("ids");
		String raIn_CityId = request.getParameter("rateCityId");
	//	String raIn_CityId = "110100";
		TblUser user = SessionMgr.getWebUser(request);
		tblRateInfo.setUserId(user.getUserId()+"");
		tblRateInfo.setRaIn_CityId(raIn_CityId);
		tblRateInfo.setUser_level(user.getUserLevel());
		//根据登录session中的用户id去获取费率列表
		List<Map<String, Object>> list = cmsRateInfoService.getRateInfoListByUserId(tblRateInfo);
		model.addAttribute("ids", ids);	
		model.addAttribute("rateInfoList", list);				
		return "backstage/electric/electric-chooseRate";
	}

	/**
	 * 获取电桩查找列表模式数据
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getElectricPileList")
	public String getElectricPileList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblElectricpile tblElectricpile, Model model,
			HttpServletRequest request) {

		try {

			/*if (!StringUtil.isEmpty(tblElectricpile.getElpiElectricpilecode())) {
				if (!isNum(tblElectricpile.getElpiElectricpilecode())) {
					tblElectricpile.setElpiElectricpilecode(null);
				}
			}*/

			// ------------|01：初始化筛选条件|-----------
			TblConfigcontent tblConfigcontent = new TblConfigcontent();
			tblConfigcontent
					.setCocoConfigpstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
			tblConfigcontent.setCocoConfigparameterid(3);
			// 获取充电方式
			List<TblConfigcontent> chargeList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("chargeList", chargeList);

			// 获取功率
			tblConfigcontent.setCocoConfigparameterid(4);
			List<TblConfigcontent> powerList = msConfigcontentService
					.findContentList(tblConfigcontent);	
			Collections.sort(powerList, new Comparator<TblConfigcontent>() {
				public int compare(TblConfigcontent o1,TblConfigcontent o2){
					if(Float.parseFloat(o1.getCocoContent().substring(0,o1.getCocoContent().indexOf("k"))) > Float.parseFloat(o2.getCocoContent().substring(0,o2.getCocoContent().indexOf("k")))){
						return 1;
					}
					if(Float.parseFloat(o1.getCocoContent().substring(0,o1.getCocoContent().indexOf("k"))) == Float.parseFloat(o2.getCocoContent().substring(0,o2.getCocoContent().indexOf("k")))){
						return 0;
					}
					return -1;
				}
			});
			model.addAttribute("powerList", powerList);

			// 获取电桩类型
			tblConfigcontent.setCocoConfigparameterid(1);
			List<TblConfigcontent> typeList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("typeList", typeList);

			// 获取接口标准
			tblConfigcontent.setCocoConfigparameterid(5);
			List<TblConfigcontent> connectorList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("connectorList", connectorList);

			// ------------|02：查询电桩业务处理|-----------
			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);

			// 个体/纯商家只能查询所属电桩
//			if (loginUser.getUserLevel().toString().equals(
//					WanmaConstants.USER_LEVER_merchant2)) {
//				tblElectricpile.setElPiUserName(loginUser.getUserAccount());
//			} else if (loginUser.getUserLevel().toString().equals(
//					WanmaConstants.USER_LEVER_personl3)) {
//				tblElectricpile.setElPiUserName(loginUser.getUserId()+"");
//			}
//			tblElectricpile.setUserLevel(loginUser.getUserLevel()+"");
			//获取地区信息
			model.addAttribute("proviceMap",WanmaConstants.provinceMap);
			String proviceCode=tblElectricpile.getElPiOwnProvinceCode();
			if(StringUtils.isNotBlank(proviceCode)){
				List<Object> cityList=new ArrayList<Object>();
				for(String citycode:WanmaConstants.provinceCityMap.get(proviceCode)){
					Map<String,Object> cityMap=WanmaConstants.cityMap;
					cityList.add(cityMap.get(citycode));
				}
				model.addAttribute("cityList",cityList);
			}
			String cityCode=tblElectricpile.getElPiOwnCityCode();
			if(StringUtils.isNotBlank(cityCode)){
				List<Object> areaList=new ArrayList<Object>();
				for(String areacode:WanmaConstants.cityAreaMap.get(cityCode)){
					Map<String,Object> areaMap=WanmaConstants.areaMap;
					areaList.add(areaMap.get(areacode));
				}
				model.addAttribute("areaList",areaList);
			}
			tblElectricpile.setElpiUserid(loginUser.getUserId().toString());
			tblElectricpile.setUserLevel(loginUser.getUserLevel().toString());
			// 电桩总数
			long total = electricPileListService
					.getRateElectricpileByConditionCount(tblElectricpile);
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			// 设置查询参数
			tblElectricpile.setPager(pager);
			List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
					.getRateElectricpileByCondition(tblElectricpile);
			pager.setTotal(total);

			model.addAttribute("electricList", electricList);
			model.addAttribute("pager", pager);
			model.addAttribute("tblElectricpile", tblElectricpile);
			model.addAttribute("loginUser", loginUser);
		} catch (Exception e) {
			log.error("获取电桩状态失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/electric/electric-sendRate";
	}
	
	public static boolean isNum(String str) {
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
}

package com.wanma.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.JsonObject;
import com.bluemobi.product.utils.MultipartFileUtil;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblBanner;
import com.wanma.model.TblUser;
import com.wanma.service.CmsAdvertisementService;
import com.wanma.service.CmsBannerService;


/**
 * @Description: banner管理controller
 * @author mb
 * @updateTime：
 * @version：V4.0
 */
@Controller
@RequestMapping("/admin/banner")
public class CmsBannerController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsBannerController.class);
	/* 制造厂商service */
	@Autowired
	CmsBannerService bannerService;
	/**
	 * banner列表
	 * 
	 * @param pager
	 * @param advModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/listBanner")
	public String listSplash(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblBanner banner, Model model, HttpServletRequest request) {
		log.info("******************获取banner信息列表-begin************************");
		try {
			List<TblBanner> bannerList = null;
			List<Map<String, Object>> cityList =new ArrayList<Map<String, Object>>();
			long total = bannerService.getBannerListCount();
			if (total <= pager.getOffset()) {
				pager.setPageNum(1L);
			}
			//设置查询参数
			pager.setTotal(total);
			banner.setPager(pager);
			//获取banner
			bannerList = bannerService.getBannerList(banner);
			//把省市区号转换中中文
			 cityList =   bannerService.getCityName();
			for(int j=0;j<bannerList.size();j++){
				if(bannerList.get(j).getBannerCityCode().isEmpty()){
					bannerList.get(j).setBannerRegion("全国");
				}else{
					for(int i=0;i<cityList.size();i++){
						if(cityList.get(i).get("CITY_ID").equals(bannerList.get(j).getBannerCityCode())){
							bannerList.get(j).setBannerRegion(cityList.get(i).get("CITY_NAME").toString());
						}	
					}
				}
			}
			//将数据放入会话
			model.addAttribute("banner",banner);
			model.addAttribute("bannerList",bannerList);
			model.addAttribute("pager",pager);
		} catch (Exception e) {
			log.error("获取banner列表失败", e);
			// 返回登录信息错误信息
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取banner信息列表-end************************");
		// 跳转至banner列表页面
		return "backstage/banner/bannerList";
	}
	/**
	 * 跳转banner新增界面
	 * 
	 * @param pager
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/addBannerUi")
	public String addBannerUi(Model model, HttpServletRequest request) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		// 跳转至banner新增页面
		return "backstage/banner/bannerAdd";
	}

	/**
	 * 新增banner
	 * 
	 * @param advModel
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addBanner")
	@ResponseBody
	public String addBanner(TblBanner banner, HttpSession session,
			HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			if(banner.getBannerProvinceCode().isEmpty()){
				banner.setBannerProvinceCode("");
				banner.setBannerCityCode("");
			}
			bannerService.insertBanner(banner);
			dwzResult = new DwzAjaxResult("200", "新增成功", "bannerList",
					"closeCurrent", "");
		} catch (Exception e) {
			log.error("新增banner失败", e);
			// 返回错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "bannerAddPage",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 跳转banner修改界面
	 * 
	 * @param pager
	 * @param advModel
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/editBannerUi")
	public String editBannerUi(@RequestParam("id") int bannerId ,Model model, HttpServletRequest request) {
		TblBanner banner = bannerService.getBannerById(bannerId);
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		model.addAttribute("banner", banner);
		// 跳转至banner修改页面
		return "backstage/banner/bannerEdit";
	}

	/**
	 * 修改banner
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editBanner")
	@ResponseBody
	public String editBanner(TblBanner banner,
			HttpSession session, HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			if(banner.getBannerProvinceCode().isEmpty()){
				banner.setBannerProvinceCode("");
				banner.setBannerCityCode("");
			}
			if(banner.getBannerStatus()==2){
				Calendar cal = Calendar.getInstance();
				Calendar cal2 = Calendar.getInstance();
		        cal.setTime(new Date());
		        cal.set(Calendar.HOUR_OF_DAY, 0);
		        cal.set(Calendar.MINUTE, 0);
		        cal.set(Calendar.SECOND, 0);
		        cal.set(Calendar.MILLISECOND, 0);
		        cal.add(Calendar.DAY_OF_MONTH, 1);
		        Date bannerEndTime = banner.getBannerEndTime();
		        cal2.setTime(bannerEndTime);
		        if(cal2.after(cal)||cal2.equals(cal)){
		        	banner.setBannerStatus(0);
		        	banner.setBannerSort(99);
		        }
			}
			bannerService.updateBanner(banner);
			dwzResult = new DwzAjaxResult("200", "修改成功", "bannerList",
					"closeCurrent", "");
		} catch (Exception e) {
			log.error("编辑banner失败", e);
			// 返回错误信息
			dwzResult = new DwzAjaxResult("300", "修改失败", "bannerEditPage",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 下架
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/downBanner")
	@ResponseBody
	public String downBanner(@RequestParam("id") int pkBannerId) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			TblBanner banner = bannerService.getBannerById(pkBannerId);
			Date bannerBeginTime = banner.getBannerBeginTime();
			Date bannerEndTime = banner.getBannerEndTime();
			Date now = new Date();
			if(bannerEndTime.before(now)){//判断是否为已结束
				dwzResult = new DwzAjaxResult("300", "已结束无法下架", "bannerList","", "");
			}else{
				banner.setBannerEndTime(now);//下架的时候把结束时间改变当前时间
				bannerService.offShelfBannerById(banner);
				dwzResult = new DwzAjaxResult("200", "下架成功", "bannerList","", "");
			}
		} catch (Exception e) {
			log.error("下架banner失败", e);
			// 返回错误信息
			dwzResult = new DwzAjaxResult("300", "下架失败", "bannerList",
					"", "");
		}
		
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	/**
	 * 删除banner
	 * 
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteBanner")
	@ResponseBody
	public String deleteBanner(@RequestParam("id") int pkBannerId) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			//软删除，修改bannerStatus
				bannerService.deleteBannerById(pkBannerId);
				dwzResult = new DwzAjaxResult("200", "删除成功", "bannerList",
						"", "");
		} catch (Exception e) {
			log.error("删除banner失败", e);
			// 返回错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "bannerList",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	/**
	 * 修改轮播顺序
	 * @return
	 */
	@RequestMapping(value = "/editBannerOrder")
	@ResponseBody
	public String editBannerOrder(HttpServletRequest request) {
		DwzAjaxResult dwzResult=null;
		try {
			String pkBannerId=request.getParameter("pkBannerId");
			String newBannerSort=request.getParameter("newBannerSort");
			TblBanner banner = new TblBanner();
			banner.setPkBannerId(Integer.parseInt(pkBannerId));
			banner.setBannerSort(Integer.parseInt(newBannerSort));
			bannerService.changeBannerSort(banner);
			dwzResult = new DwzAjaxResult("200", "修改成功", "",
					"", "");
		} catch (Exception e) {
			dwzResult = new DwzAjaxResult("300", "修改失败", "", "",
					"");
		}
		return new JsonObject(dwzResult).toString();
	}
}



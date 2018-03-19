package com.wanma.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.controller.BaseController;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblCarinfo;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCarCompanyService;
import com.wanma.service.CmsCarinfoService;
import com.wanma.service.CmsConfigcontentService;

/**
 * @Description: 电动车品牌类型详情控制层
 * @author songjf
 * @createTime：2015-4-1 上午11:55:05
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/carinfo")
public class CmsCarinfoController extends BaseController{
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsCarinfoController.class);
	/* 电动车品牌类型详情处理对象 */
	@Autowired
	private CmsCarinfoService carinfoService;
	@Autowired
	private CmsCarCompanyService carCompanyService;
	// 配置参数内容处理对象
	@Autowired
	private CmsConfigcontentService configcontentService;

	/**
	 * @Title: findCarList
	 * @Description: 获取电动车品牌类型详情列表
	 * @param elctrcplscrnconfgurtn
	 * @return
	 */
	@RequestMapping("/findCarList")
	public String findCarList(@ModelAttribute("pager") DwzPagerMySQL pager,
			TblCarinfo carinfo, Model model, HttpServletRequest request) {
		log.info("******************获取电动车品牌类型详情列表-begin************************");
		if(!checkOprateValid(request)){
			return WanmaConstants.ERROR_PAGE;
		}

		// 电动车品牌类型总数

		long total = carinfoService.findCount(carinfo);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		carinfo.setPager(pager);
		Map<String, Object> params = new HashMap<String, Object>();
		List<HashMap<String, Object>> carCompanyList = carCompanyService
				.findCarCompanyList(params);
		carinfo.setCarInfocarstatus(WanmaConstants.CONFIG_PARAMETER_EFFECTIVE);
		List<TblCarinfo> carList = carinfoService.findCarList(carinfo);
		pager.setTotal(total);

		// 取得登录用户信息
		TblUser loginUser =SessionMgr.getWebUser(request);
		// 将查询结果显示到画面
		model.addAttribute("carList", carList);
		model.addAttribute("pager", pager);
		model.addAttribute("carinfo", carinfo);
		model.addAttribute("carCompanyList", carCompanyList);
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		log.info("******************获取电动车品牌类型详情列表-end************************");

		// 跳转至车型列表页面
		return "backstage/carInfo/carInfo-list";
	}

	/**
	 * @Title: newCarinfo
	 * @Description: 跳转至新增电动车品牌类型页面
	 * @param carinfo
	 * @return
	 */
	@RequestMapping("/newCarinfo")
	public String newCarinfo(Model model) {
		log.info("******************跳转至新增电动车品牌类型页面************************");
		// 获取所有车品牌
		Map<String, Object> params = new HashMap<String, Object>();
		List<HashMap<String, Object>> carCompanyList = carCompanyService
				.findCarCompanyList(params);
		// 30:电池类型
		model.addAttribute("carCompanyList", carCompanyList);
		return "backstage/carInfo/carInfo-add";

	}

	/**
	 * @Title: insertCarinfo
	 * @Description: 新增电动车品牌类型页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/insertCarinfo")
	@ResponseBody
	public String insertCarinfo(TblCarinfo carinfo) {
		log.info("******************新增电动车品牌类型-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			//判断名字是否重复
			HashMap<String, Object> carInfoMap = new HashMap<String,Object>();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("CarInfo_StyleName", carinfo.getCarinfoStylename());	
			carInfoMap = carinfoService.getByProperty(params);
			if(carInfoMap==null||carInfoMap.isEmpty()){
				// 新增电动车品牌类型
				carinfoService.insertCarinfo(carinfo);
				dwzResult = new DwzAjaxResult("200", "新增成功", "findCarList",
						"closeCurrent", "");
			}else{
				dwzResult = new DwzAjaxResult("300", "新增失败,名称重复", "findCarList", "", "");				
			}
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "findCarList", "", "");
		}
		log.info("******************新增电动车品牌类型-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除电动车品牌类型
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(TblCarinfo tblCarinfo) {
		log.info("******************删除电动车品牌类型-begin************************");
		DwzAjaxResult dwzResult = null;

		try {
			// 删除其他配置参数中配置名称
			carinfoService.deleteById(tblCarinfo);
			dwzResult = new DwzAjaxResult("200", "删除成功", "findCarList", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "findCarList", "", "");
		}

		log.info("******************删除电动车品牌类型-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: editCarinfo
	 * @Description: 跳转至更新电动车品牌类型页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/editCarinfo")
	public String editCarinfo(TblCarinfo tblCarinfo, Model model) {
		log.info("******************跳转至更新电动车品牌类型页面-begin************************");
		// 获取所有车品牌
		Map<String, Object> params = new HashMap<String, Object>();
		List<HashMap<String, Object>> carCompanyList = carCompanyService
				.findCarCompanyList(params);
		model.addAttribute("carCompanyList", carCompanyList);


		// 根据id获取电动车品牌类型详情
		TblCarinfo carinfo = carinfoService.findById(tblCarinfo);
		// 将查询结果显示到画面
		model.addAttribute("carinfo", carinfo);
		model.addAttribute("carCompanyList", carCompanyList);
		log.info("******************跳转至更新电动车品牌类型页面-end************************");
		return "backstage/carInfo/carInfo-edit";
	}

	/**
	 * @Title: updateCarinfo
	 * @Description: 更新电动车品牌类型
	 * @param carinfo
	 * @return
	 */
	@RequestMapping("/updateCarinfo")
	@ResponseBody
	public String updateCarinfo(TblCarinfo carinfo) {
		log.info("******************更新电动车品牌类型-begin************************");
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			//判断名字是否重复
			HashMap<String, Object> carInfoMap = new HashMap<String,Object>();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("CarInfo_StyleName", carinfo.getCarinfoStylename());
			params.put("pk_CarInfo", carinfo.getPkCarinfo());
			carInfoMap = carinfoService.getByProperty(params);
			if(carInfoMap==null||carInfoMap.isEmpty()){
				carinfoService.updateCarinfo(carinfo);
				dwzResult = new DwzAjaxResult("200", "编辑成功", "findCarList",
						"closeCurrent", "");
			}else{
				dwzResult = new DwzAjaxResult("300", "修改失败,名称重复", "findCarList", "", "");				
			}
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败", "findCarList", "", "");
		}
		log.info("******************更新电动车品牌类型-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * @Title: deleteCarinfos
	 * @Description: 批量删除电动车品牌类型
	 * @param pkCarinfos
	 *            主键，多个以英文逗号分隔
	 * @return
	 */
	@RequestMapping("/deleteCarinfos")
	@ResponseBody
	public String deleteCarinfos(@RequestParam("ids") String pkCarinfos) {
		log.info("******************批量删除电动车品牌类型-begin************************");

		DwzAjaxResult dwzResult = null;

		try {
			dwzResult = carinfoService.checkDelete(pkCarinfos);
			if(dwzResult != null)
				return new JsonObject(dwzResult).toString();
			// 批量删除电动车品牌类型
			carinfoService.deleteCarinfos(pkCarinfos);

			dwzResult = new DwzAjaxResult("200", "删除成功", "findCarList", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "findCarList", "", "");
		}

		log.info("******************批量删除电动车品牌类型-end************************");
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	@RequestMapping("searchCarinfoList")
	@ResponseBody
	public String searchCarinfoList(@RequestParam String companyId) {
		List<TblCarinfo> list = new ArrayList<TblCarinfo>();
		if (StringUtils.isBlank(companyId)) {
			return new AccessErrorResult().toString();
		}
		try {
			list = carinfoService.searchCarinfoList(new Integer(companyId));
		} catch (Exception e) {
			log.error(e.getMessage());
			// 返回市级列表错误信息
			return new AccessErrorResult(2004, "error.msg.invalid.parameter")
					.toString();
		}
		log.info("******************获取市级列表-end************************");
		return new AccessSuccessResult(list).toString();
	}
}

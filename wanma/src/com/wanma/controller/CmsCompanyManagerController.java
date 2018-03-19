package com.wanma.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.exceptions.AppRequestErrorException;
import com.bluemobi.product.model.Area;
import com.bluemobi.product.model.City;
import com.bluemobi.product.model.Province;
import com.bluemobi.product.model.common.DwzAjaxResult;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.service.CityService;
import com.bluemobi.product.service.TblUserService;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.common.CacheEntity;
import com.wanma.common.CacheManager;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblCompany;
import com.wanma.model.TblUser;
import com.wanma.service.CmsPureBusinessService;
import com.wanma.service.CompanyManagerService;

/**
 * 公司管理
 * 
 * @author bruce cheng(http://blog.csdn.net/brucehome)
 * @createTime 2015-6-24 下午06:59:30
 * @updator：
 * @updateTime：
 * @version：V1.0 其他可选例子：
 */
@Controller
@RequestMapping("/admin/companyManager/")
public class CmsCompanyManagerController {

	/** 日志文件生成器 */
	private static Logger log = Logger
			.getLogger(CmsCompanyManagerController.class);
	@Autowired
	private CompanyManagerService companyManagerService;

	@Autowired
	private CmsPureBusinessService pureBusinessService;
	@Autowired
	private TblUserService userService;
	@Autowired
	private CityService cityService;

	/**
	 * 获取公司列表模式数据
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/getCompanyList")
	public String getCompanyList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblCompany tblCompany, Model model,
			HttpServletRequest request) {
		TblUser loginUser = SessionMgr.getWebUser(request);
		// 当前登录用户为商家用户,只能看所属公司用户
		if (loginUser.getUserLevel().equals(WanmaConstants.USER_LEVEL_BUSINESS)) {
			tblCompany.setPkCompanyid(loginUser.getBusiCompanyId());
		}
		long total = companyManagerService.getCompanyCount(tblCompany);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		tblCompany.setPager(pager);
		List<TblCompany> companyList = companyManagerService
				.getCompanyList(tblCompany);
		pager.setTotal(total);
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		model.addAttribute("pager", pager);
		model.addAttribute("tblCompany", tblCompany);
		model.addAttribute("companyList", companyList);
		// 跳转至管理员主页面
		return "backstage/company/company-list";
	}

	/**
	 * GATE新增界面
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/addCompanyUi")
	public String addGateUi(Model model) {
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		// 跳转至管理员主页面
		return "backstage/company/company-add";
	}

	/**
	 * 公司新增界面
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/addCompany")
	@ResponseBody
	public String addCompany(
			@ModelAttribute TblCompany tblCompany,
			Model model,
			@RequestParam(value = "IdUnitCardImage", required = false) MultipartFile[] IdUnitCardImage,
			@RequestParam(value = "LicenseImage", required = false) MultipartFile[] LicenseImage,
			@RequestParam(value = "AffairsImage", required = false) MultipartFile[] AffairsImage,
			@RequestParam(value = "AccreditImage", required = false) MultipartFile[] AccreditImage) {

		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			int num = companyManagerService.countByCompanyName(tblCompany
					.getCpyCompanyname());
			if (num > 0) {
				dwzResult = new DwzAjaxResult("300", "新增失败，企业名称重复",
						"addCompanyUi", "", "");
				return new JsonObject(dwzResult).toString();
			}
			if (tblCompany.getCpyPostcode() == null) {
				tblCompany.setCpyPostcode(0);
			}
			companyManagerService.addCompany(tblCompany, IdUnitCardImage,
					LicenseImage, AffairsImage, AccreditImage);
			dwzResult = new DwzAjaxResult("200", "新增成功", "getCompanyList",
					"closeCurrent", "");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("新增失败", e);
			dwzResult = new DwzAjaxResult("300", "新增失败", "addCompanyUi", "", "");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 公司修改界面
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/changeCompanyUI")
	public String changeCompanyUI(Model model,
			@RequestParam(value = "companyId") String pkCompanyid) {
		TblCompany tblCompany = new TblCompany();
		CacheEntity provinceCache = CacheManager
				.getCacheInfo("provinceCodeList");
		List<Province> provinceCodeList = (List<Province>) provinceCache
				.getValue();
		List<City> cities = cityService.getCityList1(tblCompany.getCpyCity());

		tblCompany = companyManagerService.getCompanyById(JudgeNullUtils
				.nvlInteger(pkCompanyid));
		model.addAttribute("provinceCodeList", provinceCodeList);
		model.addAttribute("cityList", cities);
		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
		model.addAttribute("tblCompany", tblCompany);
		// 跳转至管理员主页面
		return "backstage/company/company-edit";
	}

	/**
	 * 公司修改
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/changeCompany")
	@ResponseBody
	public String changeCompany(
			@ModelAttribute TblCompany tblCompany,
			Model model,
			@RequestParam(value = "IdUnitCardImage", required = false) MultipartFile[] IdUnitCardImage,
			@RequestParam(value = "LicenseImage", required = false) MultipartFile[] LicenseImage,
			@RequestParam(value = "AffairsImage", required = false) MultipartFile[] AffairsImage,
			@RequestParam(value = "AccreditImage", required = false) MultipartFile[] AccreditImage) {

		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			if (tblCompany.getCpyPostcode() == null) {
				tblCompany.setCpyPostcode(0);
			}
			companyManagerService.modifyCompany(tblCompany, IdUnitCardImage,
					LicenseImage, AffairsImage, AccreditImage);
			dwzResult = new DwzAjaxResult("200", "修改成功", "getCompanyList",
					"closeCurrent", "");
		} catch (Exception e) {
			// TODO: handle exception
			log.error("修改失败", e);
			dwzResult = new DwzAjaxResult("300", "修改失败", "changeCompanyUI", "",
					"");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
	/**
	 * 获取城市
	 * @param proviceId
	 * @return
	 */
	@RequestMapping("/getCityCode")
	@ResponseBody
	public Object getCityCode(
			@RequestParam(value = "proviceId") String proviceId) {
		try {
			// 身份格式02|上海 需特殊处理
			List<City> cities = cityService.getCityList1(proviceId);
			return JSONObject.toJSONString(cities);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print(e);
		}
		return null;
	}

	/**
	 * 公司查看界面
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/showCompanyUI")
	public String showCompanyUI(Model model,
			@RequestParam(value = "companyId") String companyId) {
		TblCompany tblCompany = new TblCompany();

		CacheEntity provinceCache = CacheManager
				.getCacheInfo("provinceCodeList");
		List<Province> provinceCodeList = (List<Province>) provinceCache
				.getValue();
		List<City> cities = cityService.getCityList1(tblCompany.getCpyCity());

		tblCompany = companyManagerService.getCompanyById(JudgeNullUtils
				.nvlInteger(companyId));
		model.addAttribute("provinceCodeList", provinceCodeList);
		model.addAttribute("cityList", cities);
		model.addAttribute("tblCompany", tblCompany);
		// 跳转至管理员主页面
		return "backstage/company/company-show";
	}

	/**
	 * 公司删除
	 * 
	 * @param request
	 * @return
	 * @throws AppRequestErrorException
	 */
	@RequestMapping(value = "/removeCompany")
	@ResponseBody
	public String removeCompany(Model model,
			@RequestParam(value = "ids") String ids) {

		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			// 判断是否有纯商家绑定此公司
			// int count = pureBusinessService.findByCompanyId(companyId);
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				TblUser user = new TblUser();
				user.setUserLevel(3);
				user.setBusiCompanyId(new Integer(id));
				List<TblUser> userList = userService.getUserList(user);
				if (userList.size() > 0) {
					dwzResult = new DwzAjaxResult("300", "此公司下已有用户，请先删除用户",
							"getCompanyList", "", "");
					return new JsonObject(dwzResult).toString();
				}
			}
			for (String id : idArray) {
				companyManagerService.deleteCompany(id);
				dwzResult = new DwzAjaxResult("200", "删除成功", "getCompanyList",
						"", "");
			}

		} catch (Exception e) {
			// TODO: handle exception
			log.error("修改失败", e);
			dwzResult = new DwzAjaxResult("300", "删除失败", "getCompanyList", "",
					"");
		}

		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}
}

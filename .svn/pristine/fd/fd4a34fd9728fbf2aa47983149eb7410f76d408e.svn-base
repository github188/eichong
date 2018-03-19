package com.wanma.ims.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.common.domain.CompanyDO;
import com.wanma.ims.common.domain.LevelDO;
import com.wanma.ims.common.domain.RoleDO;
import com.wanma.ims.common.domain.UserDO;
import com.wanma.ims.common.dto.BatchResultDTO;
import com.wanma.ims.constants.CompanyEnum;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.controller.vo.ButtonVO;
import com.wanma.ims.controller.vo.ParentMenuVO;
import com.wanma.ims.controller.vo.RoleVO;
import com.wanma.ims.controller.vo.TopMenuVO;
import com.wanma.ims.service.CommonRedisService;
import com.wanma.ims.service.CompanyService;
import com.wanma.ims.service.InitialService;
import com.wanma.ims.service.LevelService;
import com.wanma.ims.service.MenuService;
import com.wanma.ims.service.RoleService;

@Controller
@RequestMapping("/manage/init")
public class InitialController extends BaseController {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InitialService initialService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private LevelService levelService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private MenuService menuService;
	@Autowired
	private CommonRedisService commonReidsService;

	/**
	 * 省
	 */
	@RequestMapping(value = "/initProvinceList", method = RequestMethod.POST)
	@ResponseBody
	public void initProvinceList() {
		JsonResult result = new JsonResult();
		try {
			result.setDataObject(initialService.getProvinceListByIds(null));
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".initCityList is error", e);
			responseJson(new JsonException("查询省区列表失败！"));
		}
		responseJson(result);
	}

	/**
	 * 市
	 */
	@RequestMapping(value = "/initCityList", method = RequestMethod.POST)
	@ResponseBody
	public void initCityList() {
		JsonResult result = new JsonResult();
		try {
			String provinceCode = request.getParameter("provinceCode");
			result.setDataObject(initialService.getCityMap(provinceCode));
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".initCityList is error", e);
			responseJson(new JsonException("查询市区列表失败！"));
		}
		responseJson(result);
	}

	/**
	 * 区
	 */
	@RequestMapping(value = "/initAreaList", method = RequestMethod.POST)
	@ResponseBody
	public void initAreaList() {
		JsonResult result = new JsonResult();
		try {
			String cityCode = request.getParameter("cityCode");
			result.setDataObject(initialService.getAreaMap(cityCode));
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".initAreaList is error", e);
			responseJson(new JsonException("查询区列表失败！"));
		}
		responseJson(result);
	}
    
	/**
	 * 模糊匹配城市
	 */
	@RequestMapping(value = "/getCityListByName")
	@ResponseBody
	public void getCityListByName(){
		JsonResult result = new JsonResult();
		try {
			String cityName = request.getParameter("cityName");
			result.setDataObject(initialService.getCityListByName(cityName));
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".getCityListByName is error", e);
			responseJson(new JsonException("查询城市列表失败！"));
		}
		responseJson(result);
	}
	
	/**
	 * 公司
	 */
	@RequestMapping(value = "/initCompanyList", method = RequestMethod.POST)
	@ResponseBody
	public void initCompanyList(CompanyDO company) {
		JsonResult result = new JsonResult();
		try {
			company.setCpyStatus(WanmaConstants.COMPANY_STATE_ENABLE);
			List<CompanyDO> companyList = companyService.getCompanyList(company);
			result.setDataObject(companyList);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".initCompanyList is error", e);
			responseJson(new JsonException("查询公司列表失败！"));
		}
		responseJson(result);
	}

	/**
	 * 投资公司
	 */
	@RequestMapping(value = "/initInvestCompanyList", method = RequestMethod.POST)
	@ResponseBody
	public void initInvestCompanyList() {
		JsonResult result = new JsonResult();
		try {
			CompanyDO company = new CompanyDO();
			company.setCpyType(CompanyEnum.INVEST.getVal());
			company.setCpyStatus(WanmaConstants.COMPANY_STATE_ENABLE);
			List<CompanyDO> companyList = companyService.getCompanyList(company);
			result.setDataObject(companyList);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".initInvestCompanyList is error", e);
			responseJson(new JsonException("查询投资公司列表失败！"));
		}
		responseJson(result);
	}

	/**
	 * 投资公司+爱充
	 */
	@RequestMapping(value = "/initInvestAndEichongCompanyList", method = RequestMethod.POST)
	@ResponseBody
	public void initInvestAndEichongCompanyList() {
		JsonResult result = new JsonResult();
		try {
			CompanyDO company = new CompanyDO();
			company.setCpyType(CompanyEnum.INVEST.getVal());
			company.setCpyStatus(WanmaConstants.COMPANY_STATE_ENABLE);
			List<CompanyDO> companyList = companyService.getCompanyList(company);
			CompanyDO eichongCompany = new CompanyDO();
			eichongCompany.setCpyNumber(1000);
			eichongCompany = companyService.getCompanyByCpyInfo(eichongCompany);//根据公司标识查询爱充公司
			companyList.add(eichongCompany);//拼接投资公司加上爱充公司
			result.setDataObject(companyList);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".initInvestAndEichongCompanyList is error", e);
			responseJson(new JsonException("查询投资公司+爱充失败！"));
		}
		responseJson(result);
	}
	
	/**
	 * 合作公司
	 */
	@RequestMapping(value = "/initOperationCompanyList", method = RequestMethod.POST)
	@ResponseBody
	public void initOperationCompanyList() {
		JsonResult result = new JsonResult();
		try {
			List<CompanyDO> companyList = companyService.getOperateCompanyList();
			result.setDataObject(companyList);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".initOperationCompanyList is error", e);
			responseJson(new JsonException("查询合作公司列表失败！"));
		}
		responseJson(result);
	}

	/**
	 * 根据省、市 、管理员类型、渠道 查询权限范围内的渠道公司列表
	 */
	@RequestMapping(value = "/getCompanyListByUserLevel", method = RequestMethod.POST)
	@ResponseBody
	public void getCompanyListByUserLevel(String provinceCode, String cityCode) {
		JsonResult result = new JsonResult();
		UserDO loginUser = getCurrentLoginUser();
		try {
			BatchResultDTO<CompanyDO> dto = companyService.getCpyListByUserLevel(loginUser, provinceCode, cityCode);
			if (!dto.isSuccess()) {
				result.setSuccess(false);
				result.setStatus(ResultCodeConstants.ERROR_PARAM);
				result.setMsg(dto.getErrorDetail());
				return;
			}
			result.setDataObject(dto.getModule());
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".getCompanyListByUserLevel is error|provinceCode={}|cityCode={}|loginUser={}", provinceCode, cityCode, loginUser, e);
			responseJson(new JsonException("查询渠道公司列表失败！"));
		}
		responseJson(result);
	}

	/**
	 * 根据cpyId查询等级列表
	 */
	@RequestMapping(value = "/getLevelListByCpyId", method = RequestMethod.POST)
	@ResponseBody
	public void getLevelListByCpyId(Long cpyId) {
		JsonResult result = new JsonResult();
		try {
			List<LevelDO> levelList = levelService.getLevelListByCpyId(cpyId);
			result.setDataObject(levelList);
		} catch (Exception e) {
			LOGGER.debug(this.getClass() + ".getLevelListByCpyId() error : ", e);
			responseJson(new JsonException("根据渠道公司查询等级列表失败！"));
		}
		responseJson(result);
	}

	/**
	 * 初始化角色列表
	 */
	@RequestMapping(value = "/initRoleList", method = RequestMethod.POST)
	@ResponseBody
	public void initRoleList() {
		JsonResult result = new JsonResult();
		try {
			List<RoleDO> roleList = roleService.getAllRoleList();
			List<RoleVO> VOList = new ArrayList<RoleVO>();
			for (RoleDO roleDO : roleList) {
				VOList.add(new RoleVO(roleDO));
			}
			result.setDataObject(VOList);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".initRoleList is error", e);
			responseJson(new JsonException("初始化角色列表失败！"));
		}
		responseJson(result);
	}

	/**
	 * 菜单-树型菜单(角色新增、角色编辑)
	 */
	@RequestMapping(value = "/getMenuTree", method = RequestMethod.POST)
	@ResponseBody
	public void getMenuTree(String roleId) {
		JsonResult result = new JsonResult();
		try {
			if(StringUtils.isNotBlank(roleId)){ 
				UserDO loginUser = getCurrentLoginUser();
				if(loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_WORK || loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_ADMIN){
					List<String> roleList = roleService.getRoleIdsByUserId(getCurrentUserId());
					if(CollectionUtils.isEmpty(roleList)){
						return;
					}
					roleId = StringUtils.join(roleList, ",");
				}
			}
			TopMenuVO topMenuVO = menuService.getMenuTreeByRoleId(roleId);
			result.setDataObject(topMenuVO);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".getMeunTree() error:", e);
			responseJson(new JsonException("菜单管理-获取树型菜单失败"));
		}
		responseJson(result);
	}
	
	/**
	 * 菜单-树型菜单
	 */
	@RequestMapping(value = "/getSelfMeunTree", method = RequestMethod.POST)
	@ResponseBody
	public void getSelfMeunTree() {
		JsonResult result = new JsonResult();
		try {
			UserDO loginUser = getCurrentLoginUser();
			if(null == loginUser){
				result.setSuccess(false);
				result.setMsg("会话已过时，请重新登录");
				return;
			}
			String roleIds = "";
			if(loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_WORK || loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_ADMIN){
				List<String> roleList = roleService.getRoleIdsByUserId(getCurrentUserId());
				if(CollectionUtils.isEmpty(roleList)){
					return;
				}
				roleIds = StringUtils.join(roleList, ",");
			}
			List<ParentMenuVO> list = menuService.getSelfMenuTree(roleIds);
			result.setDataObject(list);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".getSelfMeunTree() error:", e);
			responseJson(new JsonException("菜单管理-获取树型菜单失败"));
		}
		responseJson(result);
	}
	
	/**
	 * 菜单-树型按钮
	 */
	@RequestMapping(value = "/getSelfButtonTree", method = RequestMethod.POST)
	@ResponseBody
	public void getSelfButtonTree(String menuId) {
		JsonResult result = new JsonResult();
		try {
			if(StringUtils.isBlank(menuId)){
				result.setSuccess(false);
				result.setMsg("请求参数不能为空");
				return;
			}
			UserDO loginUser = getCurrentLoginUser();
			if(null == loginUser){
				result.setSuccess(false);
				result.setMsg("会话已过时，请重新登录");
				return;
			}
			
			String roleIds = "";
			if(loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_WORK || loginUser.getUserLevel() == WanmaConstants.USER_LEVEL_ADMIN){
				List<String> roleList = roleService.getRoleIdsByUserId(getCurrentUserId());
				if(CollectionUtils.isEmpty(roleList)){
					return;
				}
				roleIds = StringUtils.join(roleList, ",");
			}
			List<ButtonVO> list = menuService.getSelfButtonTree(roleIds, menuId);
			result.setDataObject(list);
		} catch (Exception e) {
			LOGGER.error(this.getClass() + ".getSelfButtonTree() error:", e);
			responseJson(new JsonException("菜单管理-获取树型菜单失败"));
		}
		responseJson(result);
	}
}

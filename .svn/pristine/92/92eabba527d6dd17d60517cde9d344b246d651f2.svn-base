package com.wanma.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.JsonObject;
import com.wanma.app.dao.TblElectricpileMapper;
import com.wanma.app.service.ElectricPileListService;
import com.wanma.common.SessionMgr;
import com.wanma.common.WanmaConstants;
import com.wanma.dao.CmsConcentratorMapper;
import com.wanma.model.TblConcentrator;
import com.wanma.model.TblConfigcontent;
import com.wanma.model.TblElectricpile;
import com.wanma.model.TblUser;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsConcentratorService;
import com.wanma.service.CmsConfigcontentService;

/**
 * @Description: 集中器管理控制器
 * @author lhy
 * @createTime：2015-11-30
 * @updator：
 * @updateTime：
 * @version：V2.0
 */
@Controller
@RequestMapping("/admin/concentrator")
public class CmsConcentratorController {
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsConcentratorController.class);
	// 集中器处理对象
	@Autowired
	private CmsConcentratorService concentratorService;
	@Autowired
	private CmsConfigcontentService msConfigcontentService;
	@Autowired
	private ElectricPileListService electricPileListService;
	@Autowired
	private TblElectricpileMapper tblElectricpileMapper;
	@Autowired
	CmsConcentratorMapper concentratorMapper;
	@Autowired
	CmsCommitLogService commitLogService;

	/**
	 * @Title: powersList
	 * @Description: 集中器列表列表
	 * @param pager
	 * @param concentrator
	 * @param model
	 * @return
	 */
	@RequestMapping("/getConcentratorList")
	public String getPowersList(@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblConcentrator concentrator, Model model,
			HttpServletRequest request) {
		log.info("************获取集中器列表-start************");
		// 获取登陆用户
		TblUser loginUser = SessionMgr.getWebUser(request);
		if (concentrator == null)
			concentrator = new TblConcentrator();
		concentrator.setUserLevel(loginUser.getUserLevel().toString());
		concentrator.setCoctUserId(loginUser.getUserId());
		// 集中器总数
		long total = concentratorService.selectConcentratorCount(concentrator);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		concentrator.setPager(pager);
		// 获取集中器列表
		List<TblConcentrator> concentratorList = concentratorService
				.findConcentratorList(concentrator);
		pager.setTotal(total);
		// 将查询结果显示到画面
		model.addAttribute("loginUserLevel", loginUser.getUserLevel());
		model.addAttribute("concentratorList", concentratorList);
		model.addAttribute("concentrator", concentrator);
		model.addAttribute("pager", pager);

		log.info("************获取集中器列表-end************");
		// 跳转至车型列表页面
		return "backstage/concentrator/concentrator-list";
	}

	/***
	 * 添加集中器初始化界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addConcentratorUi")
	public String addConcentratorUi(Model model) {
		return "backstage/concentrator/concentrator-add";
	}

	/**
	 * 添加集中器
	 * 
	 * @param postEleids
	 * @param concentrator
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addConcentrator")
	@ResponseBody
	public String addConcentrator(
			@RequestParam("tblElectricpile.pkElectricpile") String postEleids,
			TblConcentrator concentrator, HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);
			concentrator.setCoctUserId(loginUser.getUserId());
			if (WanmaConstants.USER_LEVEL_SUPER == loginUser.getUserLevel()
					|| WanmaConstants.USER_LEVEL_ADMIN == loginUser
							.getUserLevel())
				concentrator.setCoctUserName("爱充网");
			else if (WanmaConstants.USER_LEVEL_BUSINESS == loginUser
					.getUserLevel())
				concentrator.setCoctUserName(loginUser.getBusiCompany());
			else
				concentrator.setCoctUserName(loginUser.getNormRealName());
			concentrator.setCoctState(1);
			concentrator.setPostEleids(postEleids);
			concentratorService.insert(concentrator);
			dwzResult = new DwzAjaxResult("200", "新增成功", "getConcentratorList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "新增失败", "getConcentratorList",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 绑定电桩列表
	 * 
	 * @param pager
	 * @param tblElectricpile
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getConcentratorElectricPileList")
	public String getPowersElectricPileList(
			@ModelAttribute("pager") DwzPagerMySQL pager,
			@ModelAttribute TblElectricpile tblElectricpile, Model model,
			HttpServletRequest request) {

		try {
			// ------------|01：初始化筛选条件|-----------
			TblConfigcontent tblConfigcontent = new TblConfigcontent();
			tblConfigcontent.setCocoConfigparameterid(3);
			// 获取充电方式
			List<TblConfigcontent> chargeList = msConfigcontentService
					.findContentList(tblConfigcontent);
			model.addAttribute("chargeList", chargeList);

			// ------------|02：查询电桩业务处理|-----------

			// 获取登陆用户
			TblUser loginUser = SessionMgr.getWebUser(request);
			tblElectricpile.setElpiUserid(loginUser.getUserId().toString());
			tblElectricpile.setUserLevel(loginUser.getUserLevel() + "");
			// 用户总数
			long total = electricPileListService
					.getElectricpileForConcentratorCount(tblElectricpile);
			if(total<=pager.getOffset()){
				pager.setPageNum(1L);
			}
			// 设置分页对象
			tblElectricpile.setPager(pager);
			List<Map<String, Object>> electricList = (List<Map<String, Object>>) electricPileListService
					.getElectricpileForConcentrator(tblElectricpile);
			pager.setTotal(total);

			model.addAttribute("electricList", electricList);
			model.addAttribute("pager", pager);
			model.addAttribute("tblElectricpile", tblElectricpile);
		} catch (Exception e) {
			log.error("获取电桩状态失败", e);
			return new AccessErrorResult(1000, "error.msg.invalid.parameter")
					.toString();
		}
		// 跳转至管理员主页面
		return "backstage/concentrator/concentrator-electric-list";
	}

	/***
	 * 编辑集中器初始化界面
	 * 
	 * @param model
	 * @param concentrator
	 * @return
	 */
	@RequestMapping(value = "/editConcentratorUi")
	public String editConcentratorUi(Model model, TblConcentrator concentrator) {
		concentrator = concentratorService.findOne(concentrator);
		model.addAttribute("concentrator", concentrator);
		return "backstage/concentrator/concentrator-edit";
	}

	/**
	 * 编辑集中器
	 * 
	 * @param postEleids
	 * @param concentrator
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editConcentrator")
	@ResponseBody
	public String editConcentrator(
			@RequestParam("tblElectricpile.pkElectricpile") String postEleids,
			TblConcentrator concentrator, HttpServletRequest request) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			concentrator.setCoctState(1);
			concentrator.setPostEleids(postEleids);
			// 新增车型
			concentratorService.updateConcentrator(concentrator);
			dwzResult = new DwzAjaxResult("200", "编辑成功", "getConcentratorList",
					"closeCurrent", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "编辑失败",
					"concentratorEditPage", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 解绑
	 * 
	 * @param unbindPkElectricpile
	 * @param reSortPkElectricpiles
	 * @return
	 */
	@RequestMapping(value = "/unbindElectricPile")
	@ResponseBody
	public String unbindElectricPile(
			@RequestParam("unbindPkElectricpile") String unbindPkElectricpile,
			@RequestParam("reSortPkElectricpiles") String reSortPkElectricpiles, @RequestParam("cId")String cId) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;

		try {
			concentratorService.unbindConcentrator(unbindPkElectricpile,
					reSortPkElectricpiles);
			concentratorService.sendUpdateConcentrator(cId, new MessageManager().getSystemProperties("apiRoot"));
			commitLogService.insert("集中器更新命令下发，主键：["
					+ cId + "]");
			dwzResult = new DwzAjaxResult("200", "解绑成功",
					"concentratorEditPage", "", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "解绑失败",
					"concentratorEditPage", "", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 删除集中器
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/removeConcentrator")
	@ResponseBody
	public String removeConcentrator(@RequestParam("ids") String ids) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			if (concentratorService.checkDelete(ids)){
				concentratorService.delete(ids);
				dwzResult = new DwzAjaxResult("200", "删除成功",
						"getConcentratorList", "", "");
			}else{
				dwzResult = new DwzAjaxResult("300", "存在已绑定电桩或已经上线的集中器，不可删除",
						"getConcentratorList", "", "");
			}
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "删除失败", "getConcentratorList",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

	/**
	 * 集中器离线
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/offLine")
	@ResponseBody
	public String offLine(@RequestParam("ids") String ids) {
		// 处理结果信息
		DwzAjaxResult dwzResult = null;
		try {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				TblConcentrator concentrator = new TblConcentrator();
				concentrator.setPkConcentratorID(Integer.valueOf(id));
				concentrator.setCoctState(0);
				concentratorMapper.update(concentrator);
			}
			dwzResult = new DwzAjaxResult("200", "离线成功", "getConcentratorList",
					"", "");
		} catch (Exception e) {
			// 出错日志
			log.info(e.getLocalizedMessage());
			// 设置错误信息
			dwzResult = new DwzAjaxResult("300", "离线失败", "getConcentratorList",
					"", "");
		}
		// 返回处理结果信息
		return new JsonObject(dwzResult).toString();
	}

//	/**
//	 * 集中器上线
//	 * 
//	 * @param ids
//	 * @return
//	 */
//	@RequestMapping(value = "/onLine")
//	@ResponseBody
//	public String onLine(@RequestParam("ids") String ids) {
//		// 处理结果信息
//		DwzAjaxResult dwzResult = null;
//		try {
//			String[] idArray = ids.split(",");
//			for (String id : idArray) {
//				TblConcentrator concentrator = new TblConcentrator();
//				concentrator.setPkConcentratorID(Integer.valueOf(id));
//				concentrator.setCoctState(1);
//				concentratorMapper.update(concentrator);
//			}
//			dwzResult = new DwzAjaxResult("200", "上线成功", "getConcentratorList",
//					"", "");
//		} catch (Exception e) {
//			// 出错日志
//			log.info(e.getLocalizedMessage());
//			// 设置错误信息
//			dwzResult = new DwzAjaxResult("300", "上线失败", "getConcentratorList",
//					"", "");
//		}
//		// 返回处理结果信息
//		return new JsonObject(dwzResult).toString();
//	}
}

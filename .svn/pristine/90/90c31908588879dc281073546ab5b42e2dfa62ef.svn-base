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

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.GlobalSystem;
import com.base.common.SessionMgr;
import com.base.common.WanmaConstants;
import com.base.util.StringUtil;
import com.pub.model.Pager;
import com.pub.model.TblUser;
import com.wanma.dao.CmsConcentratorMapper;
import com.wanma.dao.TblElectricpileMapper;
import com.wanma.model.TblConcentrator;
import com.wanma.model.TblElectricpile;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsConcentratorService;
import com.wanma.service.CmsConfigcontentService;
import com.wanma.service.ElectricPileListService;

/**
 * 运营管理-桩体管理-集中器管理
 * 
 * @author bc
 *
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

	@RequestMapping(value = "/concentratorListPage")
	public String concentratorListPage(HttpServletRequest request) {
		return "backstage/concentrator/concentratorList";
	}

	/**
	 * @Title: getConcentratorList
	 * @Description: 集中器列表列表
	 * @param pager
	 * @param concentrator
	 * @param model
	 * @return
	 */
	@RequestMapping("/getConcentratorList")
	@ResponseBody
	public String getPowersList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblConcentrator concentrator, Model model,
			HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
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
		for (TblConcentrator entity : concentratorList) {
			entity.setExtValue1(WanmaConstants.getConfigName("8", entity
					.getCoctSIMTYPE().toString()));
			entity.setExtValue2(WanmaConstants.getConfigName("9", entity
					.getCoctState().toString()));
		}
		pager.setTotal(total);
		baseResult = new BaseResult(concentratorList, pager);
		log.info("************获取集中器列表-end************");
		// 跳转至车型列表页面
		return baseResult.toString();
	}

	/***
	 * 添加集中器初始化界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/concentratorAddPage")
	public String addConcentratorUi(Model model) {
		return "backstage/concentrator/concentratorAdd";
	}

	/**
	 * 添加集中器
	 * 
	 * @param postEleids
	 * @param concentrator
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/concentratorSave")
	@ResponseBody
	public String concentratorSave(@RequestParam(value = "selectedIds[]",required=false) String[] selectedIds,
			TblConcentrator concentrator, HttpServletRequest request) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();

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
			concentrator.setPostEleids(StringUtil.concatArray(selectedIds));
			concentratorService.insert(concentrator);
		} catch (Exception e) {
			log.error(this.getClass() + ".concentratorSave() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/***
	 * 集中器详情界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/concentratorDetailPage")
	public String concentratorDetailPage() {
		return "backstage/concentrator/concentratorDetail";
	}

	/***
	 * 集中器编辑界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/concentratorEditPage")
	public String concentratorEditPage() {
		return "backstage/concentrator/concentratorEdit";
	}

	/***
	 * 集中器详情
	 * 
	 * @param concentrator
	 * @return
	 */
	@RequestMapping(value = "/concentratorDetail")
	@ResponseBody
	public String concentratorDetail(HttpServletRequest request,
			TblConcentrator concentrator) {
		try {
			concentrator = concentratorService.findOne(concentrator);
			List<TblElectricpile> electricList = (List<TblElectricpile>) tblElectricpileMapper
					.findBindedConcentratorElectricpileList(concentrator);
			for (TblElectricpile electric : electricList) {
				electric.setExtValue1(WanmaConstants.getConfigName("12",
						electric.getElpiState().toString()));
				electric.setExtValue2(WanmaConstants.getConfigName("3",
						electric.getElpiChargingmode().toString()));
				electric.setExtValue3(WanmaConstants.getConfigName("4",
						electric.getElpiPowersize().toString()));
			}
			concentrator.setPileList(electricList);
			if (request.getParameter("format") != null) {
				concentrator.setExtValue1(WanmaConstants.getConfigName("8",
						concentrator.getCoctSIMTYPE().toString()));
			}
			return new BaseResult(concentrator).toString();
		} catch (Exception e) {
			log.error(this.getClass() + ".concentratorDetail() error:"
					+ e.getLocalizedMessage());
			return new BaseFail(5001).toString();
		}
	}


	/**
	 * 编辑集中器
	 * 
	 * @param postEleids
	 * @param concentrator
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/concentratorModify")
	@ResponseBody
	public String concentratorModify(@RequestParam(value = "selectedIds[]",required=false) String[] selectedIds,TblConcentrator concentrator,
			HttpServletRequest request) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			
			concentrator.setCoctState(1);
			concentrator.setPostEleids(StringUtil.concatArray(selectedIds));
			concentratorService.updateConcentrator(concentrator);
		} catch (Exception e) {
			log.error(this.getClass() + ".concentratorModify() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * 解绑
	 * 
	 * @param unbindPkElectricpile
	 * @param reSortPkElectricpiles
	 * @return
	 */
	@RequestMapping(value = "/concentratorUnbindElectric")
	@ResponseBody
	public String unbindElectricPile(
			@RequestParam("unbindPkElectricpile") String unbindPkElectricpile,
			@RequestParam("reSortPkElectricpiles") String reSortPkElectricpiles,
			@RequestParam("cId") String cId) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();

		try {
			concentratorService.unbindConcentrator(unbindPkElectricpile,
					reSortPkElectricpiles);
			concentratorService.sendUpdateConcentrator(cId,
					GlobalSystem.getConfig("apiRoot"));
			commitLogService.insert("集中器更新命令下发，主键：[" + cId + "]");
		} catch (Exception e) {
			log.error(this.getClass() + ".unbindElectricPile() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 删除集中器
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/concentratorRemove")
	@ResponseBody
	public String concentratorRemove(@RequestParam("ids") String ids) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			if (concentratorService.checkDelete(ids)) {
				concentratorService.delete(ids);
			} else {
				baseResult = new BaseFail("存在已绑定电桩或已经上线的集中器，不可删除");
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".removeConcentrator() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 集中器离线
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/concentratorOffLine")
	@ResponseBody
	public String offLine(@RequestParam("ids") String ids) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			String[] idArray = ids.split(",");
			for (String id : idArray) {
				TblConcentrator concentrator = new TblConcentrator();
				concentrator.setPkConcentratorID(Integer.valueOf(id));
				concentrator.setCoctState(0);
				concentratorMapper.update(concentrator);
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".offLine() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

}

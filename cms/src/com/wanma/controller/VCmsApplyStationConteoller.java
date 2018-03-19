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

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.pub.model.Pager;
import com.wanma.model.TblApplyStation;
import com.wanma.service.CmsApplyStationService;
 
@Controller
@RequestMapping("/admin/applyStation")
public class VCmsApplyStationConteoller {

	// 日志输出对象
	private static Logger log = Logger
			.getLogger(VCmsApplyStationConteoller.class);
	@Autowired
	CmsApplyStationService cmsApplyStationService;

	/**
	 * 取得设备保修列表
	 * 
	 * @author xiay
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/applyStationList")
	public String getCmsApplyStationList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblApplyStation tblApplyStation, Model model,
			HttpServletRequest request) {

		// 申请加电列表信息
		List<TblApplyStation> applyStationList = null;

		// 申请加电列表总数
		long total = 0;

		// 用户总数
		total = cmsApplyStationService.getCmsApplyStationCount(tblApplyStation);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置分页对象
		tblApplyStation.setPager(pager);

		// 取得申请加电列表信息
		applyStationList = cmsApplyStationService
				.getCmsApplyStationList(tblApplyStation);
		pager.setTotal(total);

		// 将申请加电列表信息放到会话中
		model.addAttribute("tblApplyStation", tblApplyStation);
		model.addAttribute("applyStationList", applyStationList);
		model.addAttribute("pager", pager);
		// 跳转至申请加电列表信息
		return "backstage/applyStation/listApplyStation";
	}

	/**
	 * 编辑申请加电状态
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @throws 无
	 */
	@RequestMapping("/updateApplyStationState")
	@ResponseBody
	public String updateApplyStationState(Map<String, Object> params,
			String pkApplyStation, String apStProcessState) {
		log.info("************编辑状态-start************");
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		params.put("pkApplyStation", pkApplyStation);
		params.put("apStProcessState", apStProcessState);
		try {
			cmsApplyStationService.updateApplyStationState(params);
		} catch (Exception e) {
			log.error(this.getClass() + ".updateApplyStationState() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		log.info("************编辑状态-end************");
		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * @Title: changeApplyStationReason
	 * @Description: 跳转至编辑驳回原因页面
	 * @param params
	 * @return
	 */
	@RequestMapping("/changeApplyStationReason")
	public String changeApplyStationReason(String applyStationId, Model model) {
		log.info("******************跳转至编辑驳回原因页面-begin************************");
		Integer pkApplyStation = Integer.parseInt(applyStationId);
		// 根据id获取详情
		TblApplyStation tblApplyStation = cmsApplyStationService
				.getCmsApplyStationById(pkApplyStation);
		// 将查询结果显示到画面
		model.addAttribute("tblApplyStation", tblApplyStation);
		log.info("******************跳转至编辑驳回原因页面-end************************");
		return "backstage/applyStation/editStDealReason";
	}

	@RequestMapping("/updateApplyStationReason")
	@ResponseBody
	public String updateApplyStationReason(Map<String, Object> params,
			String pkApplyStation, String apStDealReason) {
		log.info("******************更新充电点申请记录-begin************************");
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		params.put("pkApplyStation", pkApplyStation);
		params.put("apStDealReason", apStDealReason);
		try {
			cmsApplyStationService.updateApplyStationReason(params);
		} catch (Exception e) {
			log.error(this.getClass() + ".updateApplyStationReason() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		log.info("******************更新充电点申请记录-end************************");
		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * @Title: deleteById
	 * @Description: 删除充电点申请记录
	 * @param params
	 * @return
	 */
	@RequestMapping("/deleteById")
	@ResponseBody
	public String deleteById(String applyStationId) {
		log.info("******************删除充电点申请记录-begin************************");
		BaseResult baseResult = new BaseSuccess();
		Integer pkApplyStation = Integer.parseInt(applyStationId);
		try {
			// 删除其他配置参数中配置名称
			cmsApplyStationService.deleteApplyStationById(pkApplyStation);
		} catch (Exception e) {
			log.error(this.getClass() + ".deleteById() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		log.info("******************删除充电点申请记录-end************************");
		// 返回处理结果信息
		return baseResult.toString();
	}

	/**
	 * @Title: deleteApplyStations
	 * @Description: 批量删除充电点申请记录
	 * @param pkCarinfos
	 *            主键，多个以英文逗号分隔
	 * @return
	 */
	@RequestMapping("/deleteApplyStations")
	@ResponseBody
	public String deleteApplyStations(@RequestParam("ids") String pkApplyStations) {
		log.info("******************批量删除充电点申请记录-begin************************");

		BaseResult baseResult = new BaseSuccess();

		try {
			// 批量删除充电点申请记录
			cmsApplyStationService.deleteApplyStationByIdS(pkApplyStations);
		} catch (Exception e) {
			log.error(this.getClass() + ".deleteApplyStations() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		log.info("******************批量删除充电点申请记录-end************************");
		// 返回处理结果信息
		return baseResult.toString();
	}

}

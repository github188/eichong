package com.wanma.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.base.common.WanmaConstants;
import com.pub.model.Pager;
import com.wanma.model.TblApplyBuildElecPile;
import com.wanma.service.CmsApplyBuildElecPileService;

@Controller
@RequestMapping("/admin/applyBuildElecPile")
public class VCmsApplyBuildElecPileConteoller {

	// 日志输出对象
	private static Logger log = Logger
			.getLogger(VCmsApplyBuildElecPileConteoller.class);
	@Autowired
	CmsApplyBuildElecPileService cmsApplyService;

	/**
	 * 取得设备保修列表
	 * 
	 * @author xiay
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/applyBuildElecPileList")
	public String getCmsApplyElecPileList(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblApplyBuildElecPile tblApplyBuildElecPile,
			Model model, HttpServletRequest request) {
//		// 获取地区信息
//		model.addAttribute("proviceMap", WanmaConstants.provinceMap);
//		String proviceCode = tblApplyBuildElecPile.getAepPCode();
//		if (StringUtils.isNotBlank(proviceCode)) {
//			List<Object> cityList = new ArrayList<Object>();
//			for (String citycode : WanmaConstants.provinceCityMap
//					.get(proviceCode)) {
//				Map<String, Object> cityMap = WanmaConstants.cityMap;
//				cityList.add(cityMap.get(citycode));
//			}
//			model.addAttribute("cityList", cityList);
//		}
//		String cityCode = tblApplyBuildElecPile.getAepCCode();
//		if (StringUtils.isNotBlank(cityCode)) {
//			List<Object> areaList = new ArrayList<Object>();
//			for (String areacode : WanmaConstants.cityAreaMap.get(cityCode)) {
//				Map<String, Object> areaMap = WanmaConstants.areaMap;
//				areaList.add(areaMap.get(areacode));
//			}
//			model.addAttribute("areaList", areaList);
//		}

		// 申请加电列表信息
		List<TblApplyBuildElecPile> applyBuildElecPileList = null;

		// 申请加电列表总数
		long total = 0;

		// 总数
		total = cmsApplyService
				.getCmsApplyBuildElecPileCount(tblApplyBuildElecPile);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置分页对象
		tblApplyBuildElecPile.setPager(pager);

		// 取得申请加电列表信息
		applyBuildElecPileList = cmsApplyService
				.getCmsApplyBuildElecPileList(tblApplyBuildElecPile);
		pager.setTotal(total);
		// 将申请加电列表信息放到会话中
		model.addAttribute("tblApplyBuildElecPile", tblApplyBuildElecPile);
		model.addAttribute("applyBuildElecPileList", applyBuildElecPileList);
		model.addAttribute("pager", pager);
		// 跳转至申请加电列表信息
		return "backstage/applyBuildElecPile/listApplyBuildElecPile";
	}

	/**
	 * 编辑申请加电状态
	 * 
	 * @author xiay
	 * @since Version 1.0
	 * @throws 无
	 */
	@RequestMapping("/updateApplyState")
	@ResponseBody
	public String updateApplyState(
			@ModelAttribute TblApplyBuildElecPile tblApplyBuildElecPile) {
		log.info("************编辑状态-start************");
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			cmsApplyService.updateApplyState(tblApplyBuildElecPile);
		} catch (Exception e) {
			log.error(this.getClass() + ".updateApplyState() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}

		log.info("************编辑状态-end************");
		// 返回处理结果信息
		return baseResult.toString();
	}

}

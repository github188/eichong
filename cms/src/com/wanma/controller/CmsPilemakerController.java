package com.wanma.controller;
 
import java.util.List;

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

import com.base.common.BaseFail;
import com.base.common.BaseResult;
import com.base.common.BaseSuccess;
import com.pub.controller.BaseController;
import com.pub.model.Pager;
import com.wanma.model.TblPilemaker;
import com.wanma.service.CmsPilemakerService;

/**
 * 运营管理-配置管理-电桩制造商
 * 
 * @author
 *
 */
@Controller
@RequestMapping("/admin/pilemaker")
public class CmsPilemakerController extends BaseController {
	// 日志输出对象
	private static Logger log = Logger.getLogger(CmsPilemakerController.class);
	/* 制造厂商service */
	@Autowired
	private CmsPilemakerService pilemakerService;

	@RequestMapping(value = "/pileMakerListPage")
	public String pileMakerListPage(HttpServletRequest request) {
		return "backstage/pilemaker/pileMakerList";
	}

	@RequestMapping("/getPileMakerList")
	@ResponseBody
	public String getPileMakerList(@ModelAttribute("pager") Pager pager,
			TblPilemaker carmaker, Model model, HttpServletRequest request) {
		BaseResult baseResult = new BaseFail(5001);
		// 制造厂商总数
		long total = pilemakerService.getCount(carmaker);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		carmaker.setPager(pager);

		List<TblPilemaker> carmakerList = pilemakerService.getAll(carmaker);

		pager.setTotal(total);
		baseResult = new BaseResult(carmakerList, pager);
		// 跳转至制造厂商列表页面
		return baseResult.toString();
	}

	@RequestMapping("/pilemakerSave")
	@ResponseBody
	public String pilemakerSave(TblPilemaker carMaker) {
		BaseResult baseResult = new BaseSuccess();
		int makerRemarkInt = Integer.parseInt(carMaker.getMakerRemark());
		try {
			if (makerRemarkInt < 100 && makerRemarkInt >= 0) {
				if (makerRemarkInt <= 9) {
					carMaker.setMakerRemark("0" + makerRemarkInt);
				}
				List<TblPilemaker> tempCarmakerList = pilemakerService
						.getByProperty(carMaker);
				if (tempCarmakerList == null || tempCarmakerList.isEmpty()) {
					pilemakerService.insert(carMaker);
				} else {
					baseResult = new BaseFail("新增失败,名称或标识重复");
				}
			} else {
				baseResult = new BaseFail("新增失败,标志不是0到99的数字");
			}

		} catch (Exception e) {
			log.error(this.getClass() + ".pilemakerSave() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	@RequestMapping("/pilemakerDetail")
	@ResponseBody
	public String pilemakerDetail(TblPilemaker tblcarMaker) {
		// 判断是否被电桩绑定
		if (pilemakerService
				.isBondWithElectricPile(tblcarMaker.getPkCarmaker())) {
			return new BaseFail("该制造厂商已被绑定，无法编辑").toString();
		}
		// 根据id获取制造厂商
		TblPilemaker pileMaker = pilemakerService.getOne(tblcarMaker);
		return new BaseResult(pileMaker).toString();
	}

	@RequestMapping("/pilemakerModify")
	@ResponseBody
	public String pilemakerModify(TblPilemaker carMaker) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		int makerRemarkInt = Integer.parseInt(carMaker.getMakerRemark());
		try {
			if (makerRemarkInt < 100 && makerRemarkInt >= 0) {
				if (makerRemarkInt <= 9) {
					carMaker.setMakerRemark("0" + makerRemarkInt);
				}
				List<TblPilemaker> tempCarmakerList = pilemakerService
						.getByProperty(carMaker);
				if (tempCarmakerList == null || tempCarmakerList.isEmpty()) {
					pilemakerService.update(carMaker);
				} else {
					baseResult = new BaseFail("编辑失败,名称或标识重复");
				}
			} else {
				baseResult = new BaseFail("编辑失败,标志不是0到99的数字");
			}

		} catch (Exception e) {
			log.error(this.getClass() + ".pilemakerModify() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	@RequestMapping("/pilemakerRemove")
	@ResponseBody
	public String pilemakerRemove(@RequestParam("ids") String pkcarMakers) {
		BaseResult baseResult = new BaseSuccess();
		try {
			// 判断是否被电桩绑定
			if (pkcarMakers != null) {
				String[] ids = pkcarMakers.split(",");
				if (ids != null && ids.length > 0) {
					String errorCode = "";
					for (String idstr : ids) {
						if (pilemakerService.isBondWithElectricPile(Integer
								.valueOf(idstr))) {
							errorCode += idstr + ",";
						}
					}
					errorCode = StringUtils.removeEnd(errorCode, ",");
					if (StringUtils.isNotBlank(errorCode)) {
						return new BaseFail("存在已绑定电桩的厂商:" + errorCode)
								.toString();
					}
				}

			}
			// 批量删除制造厂商
			pilemakerService.deleteBatch(pkcarMakers);
		} catch (Exception e) {
			log.error(this.getClass() + ".pilemakerRemove() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

}

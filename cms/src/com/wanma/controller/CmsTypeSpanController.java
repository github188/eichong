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
import com.base.common.GlobalSystem;
import com.pub.model.Pager;
import com.wanma.dao.CmsCommitLogMapper;
import com.wanma.model.TblBomList;
import com.wanma.model.TblTypespan;
import com.wanma.service.CmsCommitLogService;
import com.wanma.service.CmsTblTypespanService;

/**
 * 运营管理-配置管理-产品型号
 * @author bc
 *
 */
@Controller
@RequestMapping("/admin/typespan")
public class CmsTypeSpanController {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(CmsTypeSpanController.class);
	@Autowired
	private CmsTblTypespanService tblTypespanService;

	@Autowired
	private CmsCommitLogMapper commitLogDao;
	@Autowired
	private CmsCommitLogService commitLogService;

	
	@RequestMapping(value = "/typeSpanListPage")
	public String typeSpanListPage(HttpServletRequest request) {
		return "backstage/typespan/typeSpanList";
	}
	/**
	 * 产品型号列表
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("getTypeSpanList")
	@ResponseBody
	public String electric(@ModelAttribute("pager") Pager pager,
			@ModelAttribute TblTypespan tblTypespan, Model model) {
		BaseResult baseResult=new BaseFail(5001);
		long total = tblTypespanService.getTblTypespanListCount(tblTypespan);
		if (total <= pager.getOffset()) {
			pager.setPageNum(1L);
		}
		// 设置查询参数
		tblTypespan.setPager(pager);
		List<Map<String, Object>> tblTypespanList = (List<Map<String, Object>>) tblTypespanService
				.getTblTypespanList(tblTypespan);
		pager.setTotal(total);

		baseResult = new BaseResult(tblTypespanList, pager);
		return baseResult.toString();
	}


	/***
	 * 添加产品型号
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/typespanSave")
	@ResponseBody
	public String typespanSave(TblTypespan tblTypespan) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			tblTypespanService.addTypeSpan(tblTypespan);
		} catch (Exception e) {
			log.error(this.getClass() + ".typespanSave() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}

	/**
	 * 产品型号详情
	 *
	 * @param path
	 * @return
	 */
	@RequestMapping("typespanDetail")
	@ResponseBody
	public String changeTypeSpanUi(TblTypespan tblTypespan, Model model) {
		TblTypespan tblTypespanInfo = tblTypespanService.findOne(tblTypespan);
		List<TblBomList> bomList = tblTypespanService
				.getBomList(tblTypespanInfo);
		if (bomList.size() == 0)
			bomList.add(new TblBomList());
		tblTypespanInfo.setBomList(bomList);
		return new BaseResult(tblTypespanInfo).toString();
	}

	/***
	 * 修改产品型号
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/typespanModify")
	@ResponseBody
	public String typespanModify(TblTypespan tblTypespan) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			tblTypespanService.changeTypeSpan(tblTypespan);
		} catch (Exception e) {
			log.error(this.getClass() + ".typespanModify() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}


	/***
	 * 电桩版本升级
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/typespanUpgrade")
	@ResponseBody
	public String typespanUpgrade(@RequestParam("ids") String ids,
			HttpServletRequest request) {
		// 处理结果信息
		BaseResult baseResult = new BaseSuccess();
		try {
			String[] idArray = ids.split(",");
			String apiBaseUrl = GlobalSystem.getConfig("apiRoot");
			for (String id : idArray) {
				String result = tblTypespanService.updateEpVision(id,
						apiBaseUrl);
				if ("100".equals(result)) {
					commitLogService.insert("电桩程序升级成功，产品型号表id：" + id);
				} else {
					commitLogService.insert("电桩电桩升级失败，产品型号表id：" + id);
					TblTypespan tblTypespan = new TblTypespan();
					tblTypespan.setPkTypeSpanId(Integer.valueOf(id));
					TblTypespan tblTypespanInfo = tblTypespanService
							.findOne(tblTypespan);
					return new BaseFail(tblTypespanInfo.getTsModelName()
							+ "升级命令下发失败:接口返回错误代码：" + result).toString();
				}
			}
		} catch (Exception e) {
			log.error(this.getClass() + ".typespanUpgrade() error:"
					+ e.getLocalizedMessage());
			baseResult = new BaseFail(5001);
		}
		return baseResult.toString();
	}
}

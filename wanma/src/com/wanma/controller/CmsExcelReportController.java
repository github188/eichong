package com.wanma.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.model.excel.ExcelParamModel;
import com.wanma.service.impl.CmsCsjBespokeIncomeExcelServiceImpl;
import com.wanma.service.impl.CmsCsjBespokeIncomeExtExcelServiceImpl;
import com.wanma.service.impl.CmsCsjChargeIncomeExcelServiceImpl;
import com.wanma.service.impl.CmsCsjChargeIncomeExtExcelServiceImpl;
import com.wanma.service.impl.CmsGtsjBespokeIncomeExcelServiceImpl;
import com.wanma.service.impl.CmsGtsjBespokeIncomeExtExcelServiceImpl;
import com.wanma.service.impl.CmsGtsjChargeIncomeExcelServiceImpl;
import com.wanma.service.impl.CmsGtsjChargeIncomeExtExcelServiceImpl;
import com.wanma.service.impl.CmsGtsjConsumeExcelServiceImpl;
import com.wanma.service.impl.CmsGtsjConsumeExtExcelServiceImpl;
import com.wanma.service.impl.CmsWanmaBespokeIncomeExcelServiceImpl;
import com.wanma.service.impl.CmsWanmaBespokeIncomeExtExcelServiceImpl;
import com.wanma.service.impl.CmsWanmaChargeIncomeExcelServiceImpl;
import com.wanma.service.impl.CmsWanmaChargeIncomeExtExcelServiceImpl;

/**
 * @Description: excel报表controller
 * @author wubc
 * @createTime：2015-6-29
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/excelReport")
public class CmsExcelReportController {
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsExcelReportController.class);
	@Autowired
	private CmsGtsjConsumeExcelServiceImpl gtsjConsumeExcelService;
	@Autowired
	private CmsGtsjConsumeExtExcelServiceImpl gtsjConsumeExtExcelService;
	@Autowired
	private CmsGtsjChargeIncomeExtExcelServiceImpl gtsjChargeIncomeExtExcelService;
	@Autowired
	private CmsGtsjChargeIncomeExcelServiceImpl gtsjChargeIncomeExcelService;
	@Autowired
	private CmsGtsjBespokeIncomeExtExcelServiceImpl gtsjBespokeIncomeExtExcelService;
	@Autowired
	private CmsGtsjBespokeIncomeExcelServiceImpl gtsjBespokeIncomeExcelService;
	@Autowired
	private CmsCsjChargeIncomeExtExcelServiceImpl csjChargeIncomeExtExcelService;
	@Autowired
	private CmsCsjChargeIncomeExcelServiceImpl csjChargeIncomeExcelService;
	@Autowired
	private CmsCsjBespokeIncomeExtExcelServiceImpl csjBespokeIncomeExtExcelService;
	@Autowired
	private CmsCsjBespokeIncomeExcelServiceImpl csjBespokeIncomeExcelService;
	@Autowired
	private CmsWanmaChargeIncomeExtExcelServiceImpl wanmaChargeIncomeExtExcelService;
	@Autowired
	private CmsWanmaChargeIncomeExcelServiceImpl wanmaChargeIncomeExcelService;
	@Autowired
	private CmsWanmaBespokeIncomeExtExcelServiceImpl wanmaBespokeIncomeExtExcelService;
	@Autowired
	private CmsWanmaBespokeIncomeExcelServiceImpl wanmaBespokeIncomeExcelService;
	
	@RequestMapping("/getGtsjConsumeData")
	@ResponseBody
	public void getPileChargeData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出个体商家消费信息");
		try {
			gtsjConsumeExcelService.export(res, paramModel,"个体商家消费报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家消费信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getGtsjConsumeExtData")
	@ResponseBody
	public void getGtsjConsumeExtData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出个体商家消费明细信息");
		try {
			gtsjConsumeExtExcelService.export(res, paramModel,"个体商家消费明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家消费明细信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getGtsjChargeIncomeExtData")
	@ResponseBody
	public void getGtsjChargeIncomeExtData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出个体商家充电收益明细信息");
		try {
			gtsjChargeIncomeExtExcelService.export(res, paramModel,"个体商家充电收益明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家充电收益明细信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getGtsjChargeIncomeData")
	@ResponseBody
	public void getGtsjChargeIncomeData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出个体商家充电收益信息");
		try {
			gtsjChargeIncomeExcelService.export(res, paramModel,"个体商家充电收益报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家充电收益信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getGtsjBespokeIncomeExtData")
	@ResponseBody
	public void getGtsjBespokeIncomeExtData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出个体商家预约收益明细信息");
		try {
			gtsjBespokeIncomeExtExcelService.export(res, paramModel,"个体商家预约收益明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家预约收益明细信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getGtsjBespokeIncomeData")
	@ResponseBody
	public void getGtsjBespokeIncomeData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出个体商家预约收益信息");
		try {
			gtsjBespokeIncomeExcelService.export(res, paramModel,"个体商家预约收益报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家预约收益信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getCsjChargeIncomeExtData")
	@ResponseBody
	public void getCsjChargeIncomeExtData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出纯商家充电收益明细信息");
		try {
			csjChargeIncomeExtExcelService.export(res, paramModel,"纯商家充电收益明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出纯商家充电收益明细信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getCsjChargeIncomeData")
	@ResponseBody
	public void getCsjChargeIncomeData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出纯商家充电收益信息");
		try {
			csjChargeIncomeExcelService.export(res, paramModel,"纯商家充电收益报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出纯商家充电收益信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getCsjBespokeIncomeExtData")
	@ResponseBody
	public void getCsjBespokeIncomeExtData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出纯商家预约收益明细信息");
		try {
			csjBespokeIncomeExtExcelService.export(res, paramModel,"纯商家预约收益明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出纯商家预约收益明细信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getCsjBespokeIncomeData")
	@ResponseBody
	public void getCsjBespokeIncomeData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出纯商家预约收益信息");
		try {
			csjBespokeIncomeExcelService.export(res, paramModel,"纯商家预约收益报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出纯商家预约收益信息出错，数据写入出错");
		}
	}
	
	@RequestMapping("/getWanmaChargeIncomeExtData")
	@ResponseBody
	public void getWanmaChargeIncomeExtData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出万马自营充电收益明细信息");
		try {
			wanmaChargeIncomeExtExcelService.export(res, paramModel,"万马自营充电收益明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出万马自营充电收益明细信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getWanmaChargeIncomeData")
	@ResponseBody
	public void getWanmaChargeIncomeData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出万马自营充电收益信息");
		try {
			wanmaChargeIncomeExcelService.export(res, paramModel,"万马自营充电收益报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出万马自营充电收益信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getWanmaBespokeIncomeExtData")
	@ResponseBody
	public void getWanmaBespokeIncomeExtData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出万马自营预约收益明细信息");
		try {
			wanmaBespokeIncomeExtExcelService.export(res, paramModel,"万马自营预约收益明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出万马自营预约收益明细信息出错，数据写入出错");
		}
	}

	@RequestMapping("/getWanmaBespokeIncomeData")
	@ResponseBody
	public void getWanmaBespokeIncomeData(
			@ModelAttribute("paramModel") ExcelParamModel paramModel,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("开始导出万马自营预约收益信息");
		try {
			wanmaBespokeIncomeExcelService.export(res, paramModel,"万马自营预约收益报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出万马自营预约收益信息出错，数据写入出错");
		}
	}
}

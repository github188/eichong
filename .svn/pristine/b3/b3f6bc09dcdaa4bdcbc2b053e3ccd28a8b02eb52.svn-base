package com.wanma.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.common.SessionMgr;
import com.wanma.model.TblUser;
import com.wanma.service.impl.CmsConsumeStatisticExcelServiceImpl;
import com.wanma.service.impl.CmsExcelServiceImplExtend;
import com.wanma.service.impl.CmsFinanceServiceImpl;

/**
 * @Description: excel报表controller
 * @author wubc
 * @createTime：2015-6-29
 * @updateTime：
 * @version：V1.0
 */
@Controller
@RequestMapping("/admin/financeExcelReport")
public class CmsFinanceExcelReportController {
	// 日志输出对象
	private static Logger log = Logger
			.getLogger(CmsFinanceExcelReportController.class);
	@Autowired
	private CmsExcelServiceImplExtend excelServiceImpl;
	@Autowired
	private CmsConsumeStatisticExcelServiceImpl consumeStatisticExcelServiceImpl;
	@Autowired
	private CmsFinanceServiceImpl financeServiceImpl;
	
	@RequestMapping("/getPersonConsumeDetailData")
	@ResponseBody
	public void getPersonConsumeDetailData(
			@RequestParam Map<String, Object> params,
			HttpServletRequest req, HttpServletResponse res) {
		params.put("pager", null);
		setUser(params, req);
		log.info("开始导出个体商家消费明细信息");
		try {
			excelServiceImpl.export(res,financeServiceImpl.personConsumeDetail(params),"个体商家消费明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家消费明细信息出错，数据写入出错");
		}
	}
	
	@RequestMapping("/getPersonConsumeStatisticData")
	@ResponseBody
	public void getPersonConsumeStatisticData(
			@RequestParam Map<String, Object> params,
			HttpServletRequest req, HttpServletResponse res) {
		params.put("pager", null);
		setUser(params, req);
		log.info("开始导出个体商家消费统计信息");
		try {
			consumeStatisticExcelServiceImpl.export(res,financeServiceImpl.personConsumeStatistic(params),"个体商家消费统计报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家消费统计信息出错，数据写入出错");
		}
	}
	
	@RequestMapping("/getPersonChargeDetailData")
	@ResponseBody
	public void getPersonChargeDetailData(
			@RequestParam Map<String, Object> params,
			HttpServletRequest req, HttpServletResponse res) {
		params.put("pager", null);
		setUser(params, req);
		log.info("开始导出个体商家充电收益明细信息");
		try {
			excelServiceImpl.export(res,financeServiceImpl.personChargeDetail_ept(params),"个体商家充电收益明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家充电收益明细信息出错，数据写入出错");
		}
	}
	
	@RequestMapping("/getPersonBespokeDetailData")
	@ResponseBody
	public void getPersonBespokeDetailData(
			@RequestParam Map<String, Object> params,
			HttpServletRequest req, HttpServletResponse res) {
		params.put("pager", null);
		setUser(params, req);
		log.info("开始导出个体商家预约收益明细信息");
		try {
			excelServiceImpl.export(res,financeServiceImpl.personBespokeDetail(params),"个体商家预约收益明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家预约收益明细信息出错，数据写入出错");
		}
	}
	
	@RequestMapping("/getPersonChargeStatisticData")
	@ResponseBody
	public void getPersonChargeStatisticData(
			@RequestParam Map<String, Object> params,
			HttpServletRequest req, HttpServletResponse res) {
		params.put("pager", null);
		setUser(params, req);
		log.info("开始导出个体商家充电收益统计信息");
		try {
			excelServiceImpl.export(res,financeServiceImpl.personChargeStatistic(params),"个体商家充电收益统计报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家充电收益统计信息出错，数据写入出错");
		}
	}
	
	@RequestMapping("/getPersonBespokeStatisticData")
	@ResponseBody
	public void getPersonBespokeStatisticData(
			@RequestParam Map<String, Object> params,
			HttpServletRequest req, HttpServletResponse res) {
		params.put("pager", null);
		setUser(params, req);
		log.info("开始导出个体商家预约收益统计信息");
		try {
			excelServiceImpl.export(res,financeServiceImpl.personBespokeStatistic(params),"个体商家预约收益统计报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家预约收益统计信息出错，数据写入出错");
		}
	}
	
	@RequestMapping("/getBusinessChargeDetailData")
	@ResponseBody
	public void getBusinessChargeDetailData(
			@RequestParam Map<String, Object> params,
			HttpServletRequest req, HttpServletResponse res) {
		params.put("pager", null);
		setUser(params, req);
		log.info("开始导出纯商家充电收益明细信息");
		try {
			excelServiceImpl.export(res,financeServiceImpl.businessChargeDetail_ept(params),"纯商家充电收益明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出纯商家充电收益明细信息出错，数据写入出错");
		}
	}
	@RequestMapping("/getBusinessChargeStatisticData")
	@ResponseBody
	public void getBusinessChargeStatisticData(
			@RequestParam Map<String, Object> params,
			HttpServletRequest req, HttpServletResponse res) {
		params.put("pager", null);
		setUser(params, req);
		log.info("开始导出纯商家充电收益信息");
		try {
			excelServiceImpl.export(res,financeServiceImpl.businessChargeStatistic(params),"纯商家充电收益统计报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个纯商家充电收益信息出错，数据写入出错");
		}
	}
	
	@RequestMapping("/getBusinessBespokeDetailData")
	@ResponseBody
	public void getBusinessBespokeDetailData(
			@RequestParam Map<String, Object> params,
			HttpServletRequest req, HttpServletResponse res) {
		params.put("pager", null);
		setUser(params, req);
		log.info("开始导出个体商家消费信息");
		try {
			excelServiceImpl.export(res,financeServiceImpl.businessBespokeDetail(params),"纯商家预约明细报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家消费信息出错，数据写入出错");
		}
	}
	@RequestMapping("/getBusinessBespokeStatisticData")
	@ResponseBody
	public void getBusinessBespokeStatisticData(
			@RequestParam Map<String, Object> params,
			HttpServletRequest req, HttpServletResponse res) {
		params.put("pager", null);
		setUser(params, req);
		log.info("开始导出个体商家消费信息");
		try {
			excelServiceImpl.export(res,financeServiceImpl.businessBespokeStatistic(params),"纯商家预约统计报表.xls");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("导出个体商家消费信息出错，数据写入出错");
		}
	}
	
	private void setUser(Map<String, Object> params,HttpServletRequest request){
		TblUser tblUser = SessionMgr.getWebUser(request);
		params.put("userLevel", tblUser.getUserLevel());
		params.put("userId", tblUser.getUserId());
		params.put("companyId", tblUser.getBusiCompanyId());
	}
}

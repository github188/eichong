package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.util.StringUtil;
import com.wanma.dao.CmsExcelReportMapper;
import com.wanma.model.excel.ExcelParamModel;

@Service
public class CmsGtsjConsumeExtExcelServiceImpl extends CmsExcelServiceImpl {
	@Autowired
	private CmsExcelReportMapper excelReportMapper;
	private String[] columns = { "手机号码", "姓名","消费金额(元)","消费时间","消费类型" };//初始化列名

	/**
	 * 数据填充方法实现
	 */
	protected void makeExcelData(HSSFWorkbook wb, Object paramsModel) {
		HSSFSheet sheet = wb.createSheet("sheet1");
		List<Map<String, Object>> list = excelReportMapper
				.queryGtsjConsumeExtList((ExcelParamModel)paramsModel);
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> data = list.get(i);
			if (i == 0)
				makeHead(sheet, columns);
			HSSFRow row = sheet.createRow(i + 1);
			HSSFCell cell0 = row.createCell(0);
			cell0.setCellValue(StringUtil.nullToEmpty(data.get(columns[0])));
			cell0.setCellStyle(style);
			HSSFCell cell1 = row.createCell(1);
			cell1.setCellValue(StringUtil.nullToEmpty(data.get(columns[1])));
			cell1.setCellStyle(style);
			HSSFCell cell2 = row.createCell(2);
			cell2.setCellValue(StringUtil.nullToEmpty(data.get(columns[2])));
			cell2.setCellStyle(style);
			HSSFCell cell3 = row.createCell(3);
			cell3.setCellValue(StringUtil.nullToEmpty(data.get(columns[3])));
			cell3.setCellStyle(style);
			HSSFCell cell4 = row.createCell(4);
			cell4.setCellValue(StringUtil.nullToEmpty(data.get(columns[4])));
			cell4.setCellStyle(style);
		}
	}
}

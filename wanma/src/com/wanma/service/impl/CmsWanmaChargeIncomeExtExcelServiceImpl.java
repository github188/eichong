package com.wanma.service.impl;

import com.bluemobi.product.model.excel.ExcelParamModel;
import com.bluemobi.product.utils.StringUtil;
import com.wanma.dao.CmsExcelReportMapper;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CmsWanmaChargeIncomeExtExcelServiceImpl extends CmsExcelServiceImpl {
	@Autowired
	private CmsExcelReportMapper excelReportMapper;
	private String[] columns = { "纯商家名称", "桩体编号","枪头编号","充电订单编号","预约流水号","总电量(度数)","收益金额(元)","充电金额(元)","充电服务费(元)","支付状态","充电时间","创建时间" };//初始化列名

	/**
	 * 数据填充方法实现
	 */
	protected void makeExcelData(SXSSFWorkbook wb, Object paramsModel) {
		SXSSFSheet sheet = wb.createSheet("sheet1");
		List<Map<String, Object>> list = excelReportMapper
				.queryWanmaChargeIncomeExtList((ExcelParamModel)paramsModel);
		if(list != null && list.size() > 0){
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> data = list.get(i);
				if (i == 0)
					makeHead(sheet, columns);
				SXSSFRow row = sheet.createRow(i + 1);
				SXSSFCell cell0 = row.createCell(0);
				cell0.setCellValue(StringUtil.nullToEmpty(data.get(columns[0])));
				cell0.setCellStyle(style);
				SXSSFCell cell1 = row.createCell(1);
				cell1.setCellValue(StringUtil.nullToEmpty(data.get(columns[1])));
				cell1.setCellStyle(style);
				SXSSFCell cell2 = row.createCell(2);
				cell2.setCellValue(StringUtil.nullToEmpty(data.get(columns[2])));
				cell2.setCellStyle(style);
				SXSSFCell cell3 = row.createCell(3);
				cell3.setCellValue(StringUtil.nullToEmpty(data.get(columns[3])));
				cell3.setCellStyle(style);
				SXSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(StringUtil.nullToEmpty(data.get(columns[4])));
				cell4.setCellStyle(style);
				SXSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(StringUtil.nullToEmpty(data.get(columns[5])));
				cell5.setCellStyle(style);
				SXSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(StringUtil.nullToEmpty(data.get(columns[6])));
				cell6.setCellStyle(style);
				SXSSFCell cell7 = row.createCell(7);
				cell7.setCellValue(StringUtil.nullToEmpty(data.get(columns[7])));
				cell7.setCellStyle(style);
				SXSSFCell cell8 = row.createCell(8);
				cell8.setCellValue(StringUtil.nullToEmpty(data.get(columns[8])));
				cell8.setCellStyle(style);
				SXSSFCell cell9 = row.createCell(9);
				cell9.setCellValue(StringUtil.nullToEmpty(data.get(columns[9])));
				cell9.setCellStyle(style);
				SXSSFCell cell10 = row.createCell(10);
				cell10.setCellValue(StringUtil.nullToEmpty(data.get(columns[10])));
				cell10.setCellStyle(style);
				SXSSFCell cell11 = row.createCell(11);
				cell11.setCellValue(StringUtil.nullToEmpty(data.get(columns[11])));
				cell11.setCellStyle(style);
			}
		}else{
			makeHead(sheet, columns);
		}
	}
}

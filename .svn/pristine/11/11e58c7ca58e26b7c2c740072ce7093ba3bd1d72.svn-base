package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.StringUtil;
import com.wanma.dao.CmsFilterMapper;
import com.wanma.model.TblPowerstation;
@Service
public class CmsFilterExportServiceImpl  extends CmsExcelServiceImpl{

	@Autowired
	private CmsFilterMapper cmsFilterMapper;
	private String[] columns ={"充电点名称","充电点ID","城市","详细地址","充电点所属公司","充电桩编号","充电方式","功率(kw)","枪头数量","空闲枪头数量","制造厂商","电桩类型","连接状态"};
	protected void makeExcelData(HSSFWorkbook wb, Object paramsModel) {
		HSSFSheet sheet = wb.createSheet("sheet1");
		 List<Map<String,Object>> list = cmsFilterMapper.getFilterPwListExport((TblPowerstation)paramsModel);
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
		HSSFCell cell5 = row.createCell(5);
		cell5.setCellValue(StringUtil.nullToEmpty(data.get(columns[5])));
		cell5.setCellStyle(style);
		HSSFCell cell6 = row.createCell(6);
		String type=StringUtil.nullToEmpty(data.get(columns[6]));
		 if("5".equals(type))
			 cell6.setCellValue("直流");
		 if("14".equals(type))
			 cell6.setCellValue("交流");
		cell6.setCellStyle(style);
		HSSFCell cell7 = row.createCell(7);
		cell7.setCellValue(StringUtil.nullToEmpty(data.get(columns[7])));
		cell7.setCellStyle(style);
		HSSFCell cell8 = row.createCell(8);
		cell8.setCellValue(StringUtil.nullToEmpty(data.get(columns[8])));
		cell8.setCellStyle(style);
		HSSFCell cell9 = row.createCell(9);
		cell9.setCellValue(StringUtil.nullToEmpty(data.get(columns[9])));
		cell9.setCellStyle(style);
		HSSFCell cell10 = row.createCell(10);
		cell10.setCellValue(StringUtil.nullToEmpty(data.get(columns[10])));
		cell10.setCellStyle(style);
		HSSFCell cell11 = row.createCell(11);
		String status =StringUtil.nullToEmpty(data.get(columns[11]));
		if("0".equals(status))
			cell11.setCellValue("草稿");
		if("5".equals(status))
			cell11.setCellValue("提交审核");
		if("10".equals(status))
			cell11.setCellValue("专属");
		if("12".equals(status))
			cell11.setCellValue("分享申请中");
		if("15".equals(status))
			cell11.setCellValue("分享");
		cell11.setCellStyle(style);
		HSSFCell cell12 = row.createCell(12);
		String status2=StringUtil.nullToEmpty(data.get(columns[12]));
		if("0".equals(status2))
			cell12.setCellValue("未连接");
		if("1".equals(status2))
			cell12.setCellValue("已连接");
		cell12.setCellStyle(style);
	  }
		
	}

}

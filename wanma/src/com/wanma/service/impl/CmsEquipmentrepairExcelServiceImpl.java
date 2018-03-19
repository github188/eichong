package com.wanma.service.impl;

import com.bluemobi.product.utils.StringUtil;
import com.wanma.dao.CmsEquipmentrepairMapper;
import com.wanma.model.TblEquipmentrepair;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class CmsEquipmentrepairExcelServiceImpl extends CmsExcelServiceImpl {
	/** 调用Dao处理 */
	@Autowired
	private CmsEquipmentrepairMapper tblEquipmentrepairDao;
	private String[] columns = { "提交人", "电桩编号","充电点名称","充电点地址","充电点所属公司","提交时间","报修类型","问题描述","处理结果","处理状态" };//初始化列名

	/**
	 * 数据填充方法实现
	 */
	protected void makeExcelData(SXSSFWorkbook wb, Object paramsModel) {
		SXSSFSheet sheet = wb.createSheet("sheet1");
		List<Map<String, Object>> list = tblEquipmentrepairDao
				.searchLeadOutEquipList((TblEquipmentrepair)paramsModel);
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
			cell5.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(data.get(columns[5])));
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
			String status = StringUtil.nullToEmpty(data.get(columns[9]));
			if("1".equals(status))
				cell9.setCellValue("未处理");
			if("2".equals(status))
				cell9.setCellValue("处理中");
			if("3".equals(status))
				cell9.setCellValue("已处理");
			cell9.setCellStyle(style);
		}
	}
}

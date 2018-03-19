package com.wanma.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.springframework.stereotype.Service;

import com.base.util.StringUtil;

@Service
public class CmsConsumeStatisticExcelServiceImpl extends CmsExcelServiceImpl {
	public final static int sheetMaxDataCount = 60000;
	private String[] columns = { "城市", "手机号","姓名","月份","次数","消费金额(元)","充电电量","消费类型" };//初始化列名
	@SuppressWarnings("deprecation")
	@Override
	protected void makeExcelData(HSSFWorkbook wb, Object dataList) {
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> list = (List<Map<String, Object>>) dataList;
		double dataCount = list.size();
		double sheetCount = Math.ceil(dataCount/sheetMaxDataCount);
		for(int k = 0; k < sheetCount; k++){
			HSSFSheet sheet = wb.createSheet("sheet"+k);
			makeHead(sheet, columns);
			int rowIndex = 0;
			for (int i = k*sheetMaxDataCount; i < (k+1)*sheetMaxDataCount && i<dataCount; i++) {
				Map<String, Object> data = list.get(i);
				int rowNumUp = ++rowIndex;
				int rowNumDown = ++rowIndex;
				HSSFRow row0 = sheet.createRow(rowNumUp);
				HSSFRow row1 = sheet.createRow(rowNumDown);
				HSSFCell cell00 = row0.createCell(0);
				cell00.setCellValue(StringUtil.nullToEmpty(data.get("城市")));
				cell00.setCellStyle(style);
				HSSFCell cell01 = row0.createCell(1);
				cell01.setCellValue(StringUtil.nullToEmpty(data.get("手机号")));
				cell01.setCellStyle(style);
				HSSFCell cell02 = row0.createCell(2);
				cell02.setCellValue(StringUtil.nullToEmpty(data.get("姓名")));
				cell02.setCellStyle(style);
				HSSFCell cell03 = row0.createCell(3);
				cell03.setCellValue(StringUtil.nullToEmpty(data.get("月份")));
				cell03.setCellStyle(style);
				HSSFCell cell04 = row0.createCell(4);
				cell04.setCellValue(StringUtil.nullToZero(data.get("预约次数")));
				cell04.setCellStyle(style);
				HSSFCell cell05 = row0.createCell(5);
				cell05.setCellValue(StringUtil.nullToZero(data.get("预约金额(元)")));
				cell05.setCellStyle(style);
				HSSFCell cell06 = row0.createCell(6);
				cell06.setCellValue(StringUtil.nullToZero(data.get("预约电量")));
				cell06.setCellStyle(style);
				HSSFCell cell07 = row0.createCell(7);
				cell07.setCellValue("预约");
				cell07.setCellStyle(style);

				HSSFCell cell10 = row1.createCell(0);
				cell10.setCellValue(StringUtil.nullToEmpty(data.get("城市")));
				cell10.setCellStyle(style);
				HSSFCell cell11 = row1.createCell(1);
				cell11.setCellValue(StringUtil.nullToEmpty(data.get("手机号")));
				cell11.setCellStyle(style);
				HSSFCell cell12 = row1.createCell(2);
				cell12.setCellValue(StringUtil.nullToEmpty(data.get("姓名")));
				cell12.setCellStyle(style);
				HSSFCell cell13 = row1.createCell(3);
				cell13.setCellValue(StringUtil.nullToEmpty(data.get("月份")));
				cell13.setCellStyle(style);
				HSSFCell cell14 = row1.createCell(4);
				cell14.setCellValue(StringUtil.nullToZero(data.get("充电次数")));
				cell14.setCellStyle(style);
				HSSFCell cell15 = row1.createCell(5);
				cell15.setCellValue(StringUtil.nullToZero(data.get("充电金额(元)")));
				cell15.setCellStyle(style);
				HSSFCell cell16 = row1.createCell(6);
				cell16.setCellValue(StringUtil.nullToZero(data.get("充电电量")));
				cell16.setCellStyle(style);
				HSSFCell cell17 = row1.createCell(7);
				cell17.setCellValue("充电");
				cell17.setCellStyle(style);
				sheet.addMergedRegion(new CellRangeAddress(rowNumUp, rowNumDown, 0, 0));
				sheet.addMergedRegion(new CellRangeAddress(rowNumUp, rowNumDown, 1, 1));
				sheet.addMergedRegion(new CellRangeAddress(rowNumUp, rowNumDown, 2, 2));
				sheet.addMergedRegion(new CellRangeAddress(rowNumUp, rowNumDown, 3, 3));
			}
		}
	}
}

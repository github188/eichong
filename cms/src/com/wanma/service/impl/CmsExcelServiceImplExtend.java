package com.wanma.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import com.base.util.StringUtil;

@Service
public class CmsExcelServiceImplExtend extends CmsExcelServiceImpl {
	public final static int sheetMaxDataCount = 60000;
	@SuppressWarnings("unchecked")
	@Override
	protected void makeExcelData(HSSFWorkbook wb, Object dataList) {
		if(dataList != null){
			List<Map<String, Object>> list = (List<Map<String, Object>>) dataList;
			double dataCount = list.size();
			if(dataCount > 0){
				Object[] columns = list.get(0).keySet().toArray();
				double sheetCount = Math.ceil(dataCount/sheetMaxDataCount);
				for(int k = 0; k < sheetCount; k++){
					HSSFSheet sheet = wb.createSheet("sheet"+k);
					makeHead(sheet, columns);
					int rowIndex = 0;
					for (int i = k*sheetMaxDataCount; i < (k+1)*sheetMaxDataCount && i<dataCount; i++) {
						Map<String, Object> data = list.get(i);
						HSSFRow row = sheet.createRow(++rowIndex);
						for(int j = 0; j < columns.length; j++){
							HSSFCell cell = row.createCell(j);
							Object cellData = data.get(columns[j]);
							if(cellData != null && cellData.getClass().getName().equals("java.sql.Timestamp"))
								cellData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData);
							cell.setCellValue(StringUtil.nullToEmpty(cellData));
							cell.setCellStyle(style);
						}
					}
				}
			}
		}
	}
}

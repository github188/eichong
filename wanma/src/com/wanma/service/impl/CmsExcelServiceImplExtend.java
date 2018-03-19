package com.wanma.service.impl;

import com.bluemobi.product.utils.StringUtil;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class CmsExcelServiceImplExtend extends CmsExcelServiceImpl {
	public final static int sheetMaxDataCount = 60000;
	@SuppressWarnings("unchecked")
	@Override
	protected void makeExcelData(SXSSFWorkbook wb, Object dataList) {
		if(dataList != null){
			List<Map<String, Object>> list = (List<Map<String, Object>>) dataList;
			double dataCount = list.size();
			if(dataCount > 0){
				Object[] columns = list.get(0).keySet().toArray();
				double sheetCount = Math.ceil(dataCount/sheetMaxDataCount);
				for(int k = 0; k < sheetCount; k++){
					SXSSFSheet sheet = wb.createSheet("sheet"+k);
					makeHead(sheet, columns);
					int rowIndex = 0;
					DataFormat df = wb.createDataFormat();
					for (int i = k*sheetMaxDataCount; i < (k+1)*sheetMaxDataCount && i<dataCount; i++) {
						Map<String, Object> data = list.get(i);
						SXSSFRow row = sheet.createRow(++rowIndex);
						for(int j = 0; j < columns.length; j++){
							SXSSFCell cell = row.createCell(j);
							Object cellData = data.get(columns[j]);
							if(cellData != null && cellData.getClass().getName().equals("java.sql.Timestamp"))
								cellData = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cellData);
							if(columns[j].equals("总电量(度数)")||columns[j].equals("收益金额(元)")||columns[j].equals("充电金额(元)")||
									columns[j].equals("充电服务费(元)")||columns[j].equals("充电电费(元)")){
								style.setDataFormat(df.getFormat("0.00"));
								cell.setCellStyle(style);
								cell.setCellValue(Double.parseDouble(cellData.toString())); 
							}else if(columns[j].equals("总充电次数")){
								cell.setCellStyle(style);
								cell.setCellValue(Integer.parseInt(cellData.toString())); 
							}else{
								cell.setCellValue(StringUtil.nullToEmpty(cellData));
								cell.setCellStyle(style);
							}
							
						}
					}
				}
			}
		}
	}
}

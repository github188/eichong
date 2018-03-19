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
import com.wanma.app.dao.TblElectricpileMapper;
import com.wanma.model.TblElectricpile;
import com.wanma.service.impl.CmsExcelServiceImpl;
@Service
public  class ElectricPileExportServiceImpl   extends CmsExcelServiceImpl {
	@Autowired
	private TblElectricpileMapper tblElectricpileMapper;
	private String[] columns= {"桩体编码", "编号", "所属用户", "桩体状态", "充电方式", "类型", "功率", "枪头数量", "具体位置", "制造厂商", "费率ID","产品型号","创建时间","有功总电度","1号枪口电表读数","2号枪口电表读数","3号枪口电表读数","4号枪口电表读数"};// 初始化列名
	//数据填充
	 protected void makeExcelData(HSSFWorkbook wb, Object paramsModel) {
			HSSFSheet sheet = wb.createSheet("sheet1");
			List<Map<String,Object>> list = tblElectricpileMapper.getElectricpileExportList( (TblElectricpile) paramsModel);
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
				String status = StringUtil.nullToEmpty(data.get(columns[3]));
				if("0".equals(status))
					cell3.setCellValue("草稿");
				if("5".equals(status))
					cell3.setCellValue("提交审核");
				if("10".equals(status))
					cell3.setCellValue("专属");
				if("12".equals(status))
					cell3.setCellValue("分享申请中");
				if("15".equals(status))
					cell3.setCellValue("分享");
				cell3.setCellStyle(style);
				HSSFCell cell4 = row.createCell(4);
				cell4.setCellValue(StringUtil.nullToEmpty(data.get(columns[4])));
				cell4.setCellStyle(style);
				HSSFCell cell5 = row.createCell(5);
				cell5.setCellValue(StringUtil.nullToEmpty(data.get(columns[5])));
				cell5.setCellStyle(style);
				HSSFCell cell6 = row.createCell(6);
				cell6.setCellValue(StringUtil.nullToEmpty(data.get(columns[6])));
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
				cell11.setCellValue(StringUtil.nullToEmpty(data.get(columns[11])));
				cell11.setCellStyle(style);
				HSSFCell cell12= row.createCell(12);
				cell12.setCellValue(StringUtil.nullToEmpty(data.get(columns[12])));
				cell12.setCellStyle(style);
				HSSFCell cell13 = row.createCell(13);
				String totalmeter=StringUtil.nullToEmpty(data.get(columns[13]));
				if("0.000".equals(totalmeter)){
					cell13.setCellValue("");
				}else{
					cell13.setCellValue(totalmeter);
				}
				cell13.setCellStyle(style);
				//1-4
				HSSFCell cell14 = row.createCell(14);
				String gun1=StringUtil.nullToEmpty(data.get(columns[14]));
				if("0.000".equals(gun1)){
					cell14.setCellValue("");
				}else{
					cell14.setCellValue(gun1);
				}
				cell14.setCellStyle(style);
				HSSFCell cell15 = row.createCell(15);
				String gun2=StringUtil.nullToEmpty(data.get(columns[15]));
				if("0.000".equals(gun2)){
					cell15.setCellValue("");
				}else{
					cell15.setCellValue(gun2);
				}
				cell15.setCellStyle(style);
				HSSFCell cell16 = row.createCell(16);
				String gun3=StringUtil.nullToEmpty(data.get(columns[16]));
				if("0.000".equals(gun3)){
					cell16.setCellValue("");
				}else{
					cell16.setCellValue(gun3);
				}
				cell16.setCellStyle(style);
				HSSFCell cell17 = row.createCell(17);
				String gun4=StringUtil.nullToEmpty(data.get(columns[17]));
				if("0.000".equals(gun4)){
					cell17.setCellValue("");
				}else{
					cell17.setCellValue(gun4);
				}
				cell17.setCellStyle(style);
			}
		
		}
	
	}


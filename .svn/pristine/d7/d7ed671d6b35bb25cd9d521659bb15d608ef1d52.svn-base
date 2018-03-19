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
import com.wanma.dao.CmsChargOrderMapper;
import com.wanma.model.TblChargingOrder;

@Service
public class CmsChargOrderExportServiceImpl extends CmsExcelServiceImpl {
	@Autowired
	private CmsChargOrderMapper chargOrderMapper;
	private String[] columns = { "订单编号", "桩体编码", "充电点名称", "用户标示", "合作商",
			"金额(元)", "电量", "充电电费(元)", "充电服务费(元)", "充电时间段", "订单状态" };// 初始化列名
	protected void makeExcelData(HSSFWorkbook wb, Object paramsModel) {
		HSSFSheet sheet = wb.createSheet("sheet1");
		List<Map<String, Object>> list = chargOrderMapper
				.getPartnerBusChargeExport((TblChargingOrder) paramsModel);
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
			String status = StringUtil.nullToEmpty(data.get(columns[10]));
			if ("1".equals(status))
				cell10.setCellValue("未支付");
			if ("2".equals(status))
				cell10.setCellValue("支付成功");
			if ("3".equals(status))
				cell10.setCellValue("完成未结算");
			cell10.setCellStyle(style);
		}

	}

}

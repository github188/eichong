package com.wanma.service.impl;

import com.wanma.service.CmsExcelService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;


public abstract class CmsExcelServiceImpl implements CmsExcelService {
	protected CellStyle style;
	@Override
	public void export(HttpServletResponse response,Object data,String fileName) throws Exception {
		response.setContentType("application/vnd.ms-excel");
		try {
			//处理中文名称
			response.setHeader("Content-disposition",
					"attachment;filename="+new String(fileName.getBytes("gb2312"), "ISO8859-1"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        SXSSFWorkbook wb = new SXSSFWorkbook();
		//设置单元格格式
		style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		style.setWrapText(true);//自动换行
		makeExcelData(wb,data);//填充数据
		writeData(wb, response);//向页面写数据
	}
	
	/**
	 * 初始化表头
	 * @param sheet
	 * @param columns
	 */
	protected void makeHead(SXSSFSheet sheet,Object[] columns) {
        SXSSFRow row = sheet.createRow((int) 0);
		for (int i = 0; i < columns.length; i++) {
            SXSSFCell cell = row.createCell(i);
			cell.setCellValue(columns[i].toString());
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i,true);
			sheet.setColumnWidth(i, 3500);//设置列宽
		}
	}
	
	/**
	 * 向页面写数据
	 * @param wb
	 * @param response
	 * @throws Exception
	 */
	private void writeData(SXSSFWorkbook wb,HttpServletResponse response) throws Exception{
		OutputStream ouputStream = null;
		try {
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
		} catch (IOException e) {
			e.printStackTrace();
			throw new Exception("导出Excel时IO异常");
		}finally{
			try {
				ouputStream.flush();
				ouputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 数据填充
	 * @param wb
	 * @param paramsModel
	 */
	protected abstract void makeExcelData(SXSSFWorkbook wb,Object data);
}

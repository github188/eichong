package com.wanma.ims.util;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ImsBaseUtil {

    protected static Logger logger = Logger.getLogger(ImsBaseUtil.class);
    private static int MAX_NUM = 2000;// 导入行数的最大值
    public final static String EXTENSION_XLS = "xls";
    public final static String EXTENSION_XLSX = "xlsx";
     /**
     * Excel 创建导入文件
     */
    public static Workbook createWorkbook(MultipartFile file){
    	Workbook workbook = null;
    	try{
    		String importFileName = file.getOriginalFilename();
    		if (StringUtils.endsWithIgnoreCase(importFileName, ImsBaseUtil.EXTENSION_XLS)) {
    			workbook = new HSSFWorkbook(file.getInputStream());
    		} else if (StringUtils.endsWithIgnoreCase(importFileName, ImsBaseUtil.EXTENSION_XLSX)) {
    			workbook = new XSSFWorkbook(file.getInputStream());
    		}
    	}catch(Exception e){
    		logger.debug("创建workbook文件失败",e);
    		return workbook;
    	}
		return workbook;
    }
    
    /**
     * 解析Excel
     */
    public static List<Map<String, String>> parseExcel(org.apache.poi.ss.usermodel.Workbook workbook, int daCellNum) {
        List<Map<String, String>> resultMap = new ArrayList<Map<String, String>>();
        Sheet sheet = workbook.getSheetAt(0);
        // 导入的商品个数
        int firstRowIndex = sheet.getFirstRowNum();
        int rowNum = sheet.getLastRowNum();

        if (MAX_NUM < rowNum - 1) {
            return null;
        }
        // 获取第一行的列
        Row firstRow = sheet.getRow(firstRowIndex);
        int cellNum = 0;
        for (int i = firstRow.getFirstCellNum(); i < firstRow.getLastCellNum(); i++) {
            Cell cell = firstRow.getCell(i);
            if (null == cell) {
                continue;
            }
            String cellValue = getCellValueStr(cell, true).trim();// 去空格
            if (!"".equals(cellValue)) {
                cellNum++;
            }
        }
        if (cellNum != daCellNum) {
            return resultMap;
        }
        for (int i = 1; i <= rowNum; i++) {
            Row temRow = sheet.getRow(i);
            if (null == temRow) {
                continue;
            }
            int firstCellNum = temRow.getFirstCellNum();
            int lastCellNum = temRow.getLastCellNum();
            int rowEmpty = 0;
            if (lastCellNum == 0) {
                continue;
            }
            Map<String, String> rowMap = new HashMap<String, String>();
            for (int k = firstCellNum; k < lastCellNum; k++) {
                Cell cell = temRow.getCell(k);
                String value = getCellValueStr(cell, true).trim();// 去空格
                rowMap.put(k + "", value);
                if ("".equals(value)) {
                    rowEmpty++;
                }
            }
            // 去除为空的行
            if (daCellNum == rowEmpty || rowEmpty == (lastCellNum - firstCellNum)) {
                continue;
            }
            resultMap.add(rowMap);
        }
        return resultMap;
    }

    private static String getCellValueStr(Cell cell, boolean treatAsStr) {
        if (cell == null) {
            return "";
        }
        if (treatAsStr) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else {
            return String.valueOf(cell.getStringCellValue());
        }
    }

    /**
     * 导出Excel
     * headList 标题栏   格式：headList.add("userName(DO或VO对象属性)," + "用户名称(标题名) ");
     * bodyList 内容      格式：结果集List<Map<"属性名","属性值">
     * xlsName excel名称
     * sheetName sheet页名称
     */
    public static void responseExcel(HttpServletResponse response,List<String> headList, List<?> bodyList, String xlsName, String sheetName) {
    	response.setContentType("application/excel");
//        File file = null;
    	try {
            SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(xlsName.getBytes("gb2312"), "ISO8859-1") + f.format(new Date()) + ".xls\"");
    	} catch (UnsupportedEncodingException e) {
            logger.error("设置Excel输出类型失败", e);
        }
    	OutputStream os = null;
        SXSSFWorkbook wbook = null;
        try {
        	os= response.getOutputStream();  
        	wbook = new SXSSFWorkbook(1000);
            if (bodyList == null) {
                bodyList = new ArrayList<Map<String, ?>>();
            }
            float sn = (float) bodyList.size() / 60000;
            int n = bodyList.size() % 60000;
            if (sn > 1) {
                int sheetNum = n > 0 ? (int) sn + 1 : (int) sn;
                for (int i = 0; i < sheetNum; i++) {
                    int start = i * 60000;
                    createSheet(wbook, bodyList, headList, i, start, sheetName);
                }
            } else {
                createSheet(wbook, bodyList, headList, 0, 0, sheetName);
            }
            wbook.write(os);
        } catch (Exception e) {
            logger.error("导出EXCEL数据出错", e);
        } finally {
            if (wbook != null) {
                try {
                    os.close();
                    wbook.dispose();  
                    wbook = null;
                    bodyList = null;
                } catch (Exception e) {
                    logger.error("导出EXCEL数据出错", e);
                }
            }
        }
    }

    private static void createSheet(SXSSFWorkbook wbook, List<?> bodyList, List<String> headList, int sheetNum, int start, String sheetName)
            throws RowsExceededException, WriteException {
        sheetName = sheetNum > 0 ? sheetName + (sheetNum + "") : sheetName;
        Sheet sheet = wbook.createSheet(String.valueOf(sheetNum)); // sheet名称
        wbook.setSheetName(sheetNum, sheetName);
        String[] keys = new String[headList.size()];

        // 设置表格头
        Row headRow = sheet.createRow(0);
        for (int i = 0; i < headList.size(); i++) {
        	Cell cell = headRow.createCell(i);
        	cell.setCellType(XSSFCell.CELL_TYPE_STRING);
        	cell.setCellValue(headList.get(i).split(",")[1]);
            keys[i] = headList.get(i).split(",")[0];
        }

        // 设置表格内容
        int end = (start + 60000) > bodyList.size() ? bodyList.size() : start + 60000;
        // TODO 加一行标题
        for (int r = start; r < end; r++) {
            Map<?, ?> m = (Map<?, ?>) bodyList.get(r);
            Row row = sheet.createRow(r - sheetNum*60000  + 1);
            for (int c = 0; c < keys.length; c++) {
            	Cell cell = row.createCell(c);
            	cell.setCellType(XSSFCell.CELL_TYPE_STRING);
            	String value = null == m.get(keys[c]) ? "":m.get(keys[c]).toString();
            	cell.setCellValue(value);
            }
        }
    }
    
}

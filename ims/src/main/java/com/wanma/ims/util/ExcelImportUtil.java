package com.wanma.ims.util;


import com.google.common.base.Strings;
import com.wanma.ims.common.dto.BatchResultDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel文件导入
 * xyc
 */
public class ExcelImportUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelImportUtil.class);

    public static BatchResultDTO<List<String>> importListFromExcel(MultipartFile multipartFile) {
        Workbook workbook = getWorkbook(multipartFile);
        Sheet sheet = workbook.getSheetAt(0);
        // 解析公式结果
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

        BatchResultDTO<List<String>> result = new BatchResultDTO<>();
        List<List<String>> data = new ArrayList<>();

        int minRowNum = sheet.getFirstRowNum();
        int maxRowNum = sheet.getLastRowNum();
        if (maxRowNum - minRowNum <= 0) {
            result.setSuccess(false);
            result.setErrorDetail("excel格式错误,没有上传的数据");
            return result;
        }

        boolean isHeader = true;
        List<String> header = null;
        for (int rowNum = minRowNum; rowNum <= maxRowNum; rowNum++) {
            Row row = sheet.getRow(rowNum);
            if (row == null) {
                if (isHeader) {
                    result.setSuccess(false);
                    result.setErrorDetail("excel格式错误,标题行不能为空");
                    return result;
                } else {
                    continue; //空行不处理
                }
            }
            try {
                List<String> line = readRow(row, evaluator);
                if (!result.isSuccess()) {
                    return result;
                }
                if (isHeader) {
                    line = getAdjustHeader(line);
                    header = line;
                    if (CollectionUtils.isEmpty(header)) {
                        result.setSuccess(false);
                        result.setErrorDetail("excel格式错误,标题行不能为空");
                        return result;
                    }
                } else {
                    line = getAdjustContent(line, header.size());
                }
                if (CollectionUtils.isEmpty(line)) {
                    continue;
                }
                data.add(line);
                isHeader = false;
            } catch (Exception e) {
                LOGGER.error("get line encounter Exception ", e);
                result.setSuccess(false);
                result.setErrorDetail("excel格式错误,第" + rowNum + "行处理异常");
                return result;
            }
        }
        result.setModule(data);
        if (data.size() <= 1) {
            result.setSuccess(false);
            result.setErrorDetail("excel格式错误,上传数据内容不能为空");
            return result;
        }

        return result;
    }

    private static Workbook getWorkbook(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        Workbook workbook = null;
        try {
            if (fileName.endsWith("xlsx")) {
                workbook = new XSSFWorkbook(multipartFile.getInputStream());
            } else {
                workbook = new HSSFWorkbook(multipartFile.getInputStream());
            }
        } catch (Exception e) {
            LOGGER.error("getWorkbook encounter Exception ", e);
        }
        return workbook;
    }

    public static List<String> readRow(Row row, FormulaEvaluator evaluator) {
        short minCol = row.getFirstCellNum();
        short maxCol = row.getLastCellNum();
        //行是否为空
        boolean rowIsNull = true;

        List<String> list = new ArrayList<>();

        for (int col = minCol; col < maxCol; col++) {
            String value;
            Cell cell = row.getCell(col);
            CellValue cellValue = evaluator.evaluate(cell);
            if (cellValue == null) {
                list.add(null);
                continue;
            }

            DataFormatter formatter = new DataFormatter();
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    value = formatter.formatCellValue(cell);
                    break;
                case HSSFCell.CELL_TYPE_NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        value = "" + cell.getDateCellValue().getTime();
                    } else {
                        value = formatter.formatCellValue(cell);
                    }
                    break;
                case HSSFCell.CELL_TYPE_STRING:
                    value = cell.getRichStringCellValue().toString();
                    break;
                case Cell.CELL_TYPE_FORMULA:
                case Cell.CELL_TYPE_BLANK:
                case Cell.CELL_TYPE_ERROR:
                default:
                    value = null;
            }

            list.add(value);
            rowIsNull = false;
        }

        return rowIsNull ? new ArrayList<String>() : list;
    }

    private static List<String> getAdjustHeader(List<String> header) {
        if (CollectionUtils.isEmpty(header)) {
            return header;
        }
        int columnNo = header.size() - 1;
        for (int i = columnNo; i >= 0; i--) {
            columnNo = i;
            if (!Strings.isNullOrEmpty(header.get(i))) {
                break;
            }
        }
        columnNo = columnNo + 1;
        header = header.subList(0, columnNo);
        return header;
    }

    private static List<String> getAdjustContent(List<String> line, int headerLength) {
        if (CollectionUtils.isEmpty(line)) {
            return line;
        }

        for (int i = line.size(); i < headerLength; i++) {
            line.add(null);
        }

        if (line.size() > headerLength) {
            line = line.subList(0, headerLength);
        }

        return line;
    }

    public static Object getNotNullData(int lineNum, Object data, int columnNum, String className, String methodName, String importContent) throws Exception {
        if (data != null) {
            return data;
        } else {
            LOGGER.warn(className + methodName + "-getNotNullData is error, data is null|lineNum={}|columnNum={}", lineNum, columnNum);
            throw new Exception("导入" + importContent + "失败，第" + lineNum + "行第" + (columnNum + 1) + "列数据不正确！");
        }
    }

    public static Integer getInteger(int lineNum, String data, int columnNum, String className, String methodName, String importContent) throws Exception {
        try {
            return Integer.valueOf(data);
        } catch (Exception e) {
            LOGGER.warn(className + methodName + "-getInteger is error|lineNum={}|columnNum={}|data={}", lineNum, columnNum, data, e);
            throw new Exception("导入" + importContent + "失败，第" + lineNum + "行第" + (columnNum + 1) + "列数据不正确，无法转为整数！");
        }
    }

    public static BigDecimal getBigDecimal(int lineNum, List<String> line, int columnNum, String className, String methodName, String importContent) throws Exception {
        try {
            return new BigDecimal(line.get(columnNum));
        } catch (Exception e) {
            LOGGER.warn(className + methodName + "-getBigDecimal is error|lineNum={}|columnNum={}|line={}", lineNum, columnNum, line, e);
            throw new Exception("导入" + importContent + "失败，第" + lineNum + "行第" + (columnNum + 1) + "列数据不正确，无法转为小数！");
        }
    }
}



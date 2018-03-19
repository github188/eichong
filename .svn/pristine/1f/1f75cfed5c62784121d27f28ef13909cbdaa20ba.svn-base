package com.wanma.ims.util;

import com.google.common.base.Predicates;
import com.wanma.ims.service.impl.UserServiceImpl;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.reflections.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by xyc on 2016/2/3.
 * excel通过反射原理实现的导出工具
 */
public class ExcelExporterUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    public static <T> void exportExcel(HttpServletResponse response, List<String> attrList, List<String> header, List<T> models, Class<T> clazz, String fileName, String sheetName) {
        try {
            SXSSFWorkbook workbook = ExcelExporterUtil.exportExcelFromList(sheetName, models, attrList, header, clazz);
            fileName = new String(fileName.getBytes("gb2312"), "ISO8859-1");
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            workbook.write(response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            LOGGER.error("ExcelExporterUtil-exportExcel is error", e);
        }
    }

    public static <T> SXSSFWorkbook exportExcelFromList(String sheetName, List<T> objList, List<String> attr,
                                                        List<String> header, Class<T> clazz) {
        if (header.size() != attr.size()) {
            throw new RuntimeException("excel Header和传入的列数不匹配");
        }

        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(1000);
        Sheet sheet = sxssfWorkbook.createSheet(sheetName);
        sheet.setColumnWidth(17, 6000);
        int colNum = header.size();
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);

        for (int col = 0; col < colNum; col++) {
            row.createCell(col).setCellValue(header.get(col));
        }

        List<List<Object>> lists = getValueList(objList, clazz, attr);

        if (lists != null) {
            for (List<Object> list : lists) {
                row = sheet.createRow(rowNum++);
                for (int col = 0; col < colNum; col++) {
                    Cell cell = row.createCell(col);
                    writeCell(sxssfWorkbook, list.get(col), cell);
                }
            }
        }
        return sxssfWorkbook;
    }

    private static void writeCell(Workbook workbook, Object obj, Cell cell) {
        if (obj == null) {
            cell.setCellValue("");
        } else if (obj instanceof String) {
            cell.setCellValue((String) obj);
        } else if (obj instanceof Character) {
            cell.setCellValue((Character) obj);
        } else if (obj instanceof Long) {
            cell.setCellValue((Long) obj);
        } else if (obj instanceof Integer) {
            cell.setCellValue((Integer) obj);
        } else if (obj instanceof Short) {
            cell.setCellValue((Short) obj);
        } else if (obj instanceof Byte) {
            cell.setCellValue((Byte) obj);
        } else if (obj instanceof Float) {
            cell.setCellValue((Float) obj);
        } else if (obj instanceof Double) {
            cell.setCellValue((Double) obj);
        } else if (obj instanceof Boolean) {
            cell.setCellValue((Boolean) obj);
        } else if (obj instanceof Date) {
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/m/d h:mm:ss"));
            cell.setCellValue((Date) obj);
            cell.setCellStyle(cellStyle);
        }
    }

    public static <T> List<List<Object>> getValueList(List<T> objList, Class<T> clazz, List<String> attr) {
        Set<Method> methods = ReflectionUtils.getAllMethods(clazz, Predicates.and(ReflectionUtils.withModifier(Modifier.PUBLIC),
                ReflectionUtils.withPrefix("get")));
        Map<String, Method> methodMap = new HashMap<>();

        for (Method method : methods) {
            String attrName = getAttrNameFromMethod(method.getName());
            if (attr.contains(attrName)) {
                methodMap.put(attrName, method);
            }
        }

        if (methodMap.keySet().size() != attr.size()) {
            LOGGER.error("excel list and data is inconsistent");
            throw new RuntimeException("excel格式错误,列表和数据不一致,处理失败");
        }

        List<List<Object>> res = new ArrayList<>();
        for (T obj : objList) {
            List<Object> list = new ArrayList<>();
            for (String name : attr) {
                Method method = methodMap.get(name);
                try {
                    list.add(method.invoke(obj));
                } catch (Exception e) {
                    LOGGER.error("get value error");
                    throw new RuntimeException("excel格式错误,不能获取对应列的数据|" + name);
                }
            }
            res.add(list);
        }
        return res;
    }

    public static String getAttrNameFromMethod(String methodName) {
        if (methodName.length() < 4) {
            return null;
        }

        return Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
    }
}

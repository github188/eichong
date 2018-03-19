/**
 * FileName: CSVFileHandler.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.omg.CORBA.SystemException;


/**
 * CSV解析处理类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */

public class CsvFleOperator {

	/** 日志输出对象 */
	private static Logger log = Logger.getLogger(CsvFleOperator.class);

    /** CSV文件分割标志 */
    public static final String NAME_EXTENSION = ".CSV";

    private static char DELIM = ',';

    /** Response Head Name */
    private static String HEAD_NAME = "Content-disposition";

    /** Response Head Value */
    private static String HEAD_VALUE = "attachment;filename=";

    /** Response Content Type */
    private static String CONTENT_TYPE = "application/csv";
    
    /** encode */
    private static String encoding = "utf-8";

    /**
     * Write CSV
     * 
     * @param response
     * @param strFileName
     * @param Content
     * @throws SystemException
     */
    public static void writeCSVStream(HttpServletResponse response, String strFileName,
            String[][] Content) {
        response.reset();
        response.setContentType(CONTENT_TYPE);
        response.setHeader(HEAD_NAME, HEAD_VALUE + strFileName);
        
        try {
	        ByteArrayOutputStream baos = getCSVStream(Content);
	        baos.writeTo(response.getOutputStream());
	        baos.flush();
	        baos.close();
        }catch(Exception e){
        	log.error(e.getLocalizedMessage());;
        }
    }
    
    public static void writeCSVStream(HttpServletResponse response, String strFileName,
            List<String[]> Content) {
        response.reset();
        response.setContentType(CONTENT_TYPE);
        response.setHeader(HEAD_NAME, HEAD_VALUE + strFileName);
        
        try {
	        ByteArrayOutputStream baos = getCSVStream(Content);
	        baos.writeTo(response.getOutputStream());
	        baos.flush();
	        baos.close();
        }catch(Exception e){
        	log.error(e.getLocalizedMessage());;
        }
    }

    private static StringBuffer FormatCSVString(String[] line) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < line.length; i++) {
            String cell = line[i];
            if (cell == null) {
                cell = "";
            } else {
                //boolean needquot = false;
                if (cell.indexOf('\"') != -1) {
                    cell = cell.replaceAll("\"", "\"\"");
                    //needquot = true;
                }
                /*
                 * if (cell.indexOf(DELIM) != -1) { needquot = true; }
                 */
                //if (needquot) {
                cell = "\"" + cell + "\"";
                //}
            }
            sb.append(cell);
            sb.append(DELIM);
        }
        return sb;
    }

    private static String getFormatCSVContent(String[][] Contents) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < Contents.length; i++) {
            sb.append(FormatCSVString(Contents[i]));
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private static String getFormatCSVContent(List<String[]> Contents) {
        StringBuffer sb = new StringBuffer();
        for (String[] Content : Contents) {
            sb.append(FormatCSVString(Content));
            sb.append("\n");
        }
        return sb.toString();
    }
    
    private static int CountQuto(String cell, int position) {
        int pos = cell.indexOf(DELIM, position + 1);
        if (pos == -1) {
            return pos;
        }
        String strcell = cell.substring(0, pos);
        int idquto = 0;
        while (strcell.endsWith("\"")) {
            idquto++;
            strcell = strcell.substring(0, strcell.length() - 1);
        }
        if (idquto % 2 == 0) {
            pos = CountQuto(cell, pos + 1);
        }
        return pos;
    }

    private static void AnaCellData(List<String> lst, String line) {
        int pos = -1;
        boolean needFilter = false;
        if (line.startsWith("\"")) {
            needFilter = true;
            pos = CountQuto(line, 0);
        } else {
            pos = line.indexOf(DELIM);
        }
        String cell = null;
        if (pos != -1) {
            cell = line.substring(0, pos);
            lst.add(needFilter ? FilterQuto(cell) : cell);
            AnaCellData(lst, line.substring(pos + 1));
        } else {
            cell = line;
            lst.add(needFilter ? FilterQuto(cell) : cell);
        }

    }

    private static String FilterQuto(String cell) {
        String strret = cell.substring(1, cell.length());
        strret = strret.replaceAll("\"\"", "\"");
        if (strret.endsWith("\"")) {
            strret = strret.substring(0, strret.length() - 1);
        }
        return strret;
    }

    /**
     * get OutputStream for csv download
     * 
     * @param Content
     * @return OutputStream
     * @throws Exception
     */
    public static ByteArrayOutputStream getCSVStream(String[][] Content) throws Exception {
        byte[] bContent = getFormatCSVContent(Content).getBytes(encoding);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(bContent);
        return baos;
    }
    
    public static ByteArrayOutputStream getCSVStream(List<String[]> Content) throws Exception {
        byte[] bContent = getFormatCSVContent(Content).getBytes(encoding);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(bContent);
        return baos;
    }

    /**
     * read a csv file
     * 
     * @param strFileName
     * @return csvfile data
     * @throws Exception
     */
    public static String[][] readCSVFile(String strFileName) throws Exception {
        CSVFileHandler fh = new CSVFileHandler();
        String[] contents = fh.openFileGetContents(strFileName);
        fh.closeInputFile();
        return convTo2Arr(contents);
    }

    /**
     * @param is
     * @return csv file data
     * @throws Exception
     */
    public static String[][] readUpdateedCSVFileIS(InputStream is) throws Exception {
        CSVFileHandler fh = new CSVFileHandler();
        String[] contents = fh.openISGetContents(is);
        fh.closeInputFile();
        return convTo2Arr(contents);
    }

    /**
     * @param contents
     * @return 2C data
     * @throws Exception
     */
    private static String[][] convTo2Arr(String[] contents) throws Exception {
        if (contents == null) {
            return null;
        }

        if (contents.length == 0) {
            String[][] strZero = new String[0][0];
            return strZero;
        }
        List<List<String>> lines = new ArrayList<List<String>>();
        int cols = -1;
        for (int i = 0; i < contents.length; i++) {
            String line = contents[i];
            List<String> lst = new ArrayList<String>();
            AnaCellData(lst, line);
            cols = cols < lst.size() ? lst.size() : cols;
            lines.add(lst);
        }

        int size = lines.size();
        String[][] retcells = new String[size][cols];
        for (int i = 0; i < size; i++) {
            lines.get(i).toArray(retcells[i]);
        }
        return retcells;
    }

    /**
     * write a csv file
     * 
     * @param contents
     * @param strFileName
     * @throws Exception
     */
    public static void writeCSVFile(String[][] contents, String strFileName) throws Exception {
        if (!IsCSVFile(strFileName)) {
            return;
        }
        CSVFileHandler fh = new CSVFileHandler();
        fh.openOutputFile(strFileName);
        fh.writeLine(getFormatCSVContent(contents));
        fh.closeOutputFile();

    }

    /**
     * 取得文件的扩展名
     * 
     * @param argFileName
     *            文件名
     * @return 扩展名
     */
    public static boolean IsCSVFile(String argFileName) {
        String str = argFileName.substring(argFileName.length() - NAME_EXTENSION.length());
        return str.equalsIgnoreCase(NAME_EXTENSION);
    }

    /**
     * delete file
     * 
     * @param FileName
     * @return operate result
     * @throws Exception
     */
    public static boolean DeleteCSVFile(String FileName) throws Exception {
        if (!IsCSVFile(FileName)) {
            return false;
        }
        CSVFileHandler fh = new CSVFileHandler();
        return fh.deleteCVSFile(FileName);
    }
}
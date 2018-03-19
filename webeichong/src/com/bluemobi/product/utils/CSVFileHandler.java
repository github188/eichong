/**
 * FileName: CSVFileHandler.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV解析处理类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */

public class CSVFileHandler {
	private FileOutputStream fos = null;

	private OutputStreamWriter osw = null;

	private BufferedWriter bw = null;

	private FileInputStream fis = null;

	private InputStreamReader isr = null;

	private BufferedReader br = null;

	private String[] contents = null;

	private String encoding;

	/**
	 * 编码
	 * 
	 * @throws Exception
	 */
	protected CSVFileHandler() throws Exception {
		encoding = "utf-8";
	}

	@Override
	protected void finalize() {
		closeOutputFile();
		closeInputFile();
		contents = null;
	}

	/**
	 * 文件输出用方法。 <BR>
	 * 
	 * @param file
	 *            文件名
	 * @throws Exception
	 *             异常
	 */
	protected void openOutputFile(String file) throws Exception {
		try {
			fos = new FileOutputStream(file, true);
			osw = new OutputStreamWriter(fos, encoding);
			bw = new BufferedWriter(osw);
		} catch (Exception ex) {
			closeOutputFile();
			throw ex;
		}
	}

	/**
	 * 关闭流<BR>
	 */
	protected void closeOutputFile() {
		try {
			bw.flush();
		} catch (Exception ex) {
			// empty
		}
		try {
			osw.flush();
		} catch (Exception ex) {
			// empty
		}
		try {
			fos.close();
		} catch (Exception ex) {
			// empty
		}
		bw = null;
		fos = null;
		osw = null;
	}

	/**
	 * 文件输入用方法。 <BR>
	 * 
	 * @param file
	 *            文件名
	 * @throws Exception
	 *             异常
	 */
	protected void openInputFile(String file) throws Exception {
		try {
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis, encoding);
			br = new BufferedReader(isr);
		} catch (Exception ex) {
			closeInputFile();
			throw ex;
		}
	}

	/**
	 * @param is
	 * @throws Exception
	 */
	protected void openInputStream(InputStream is) throws Exception {
		try {
			isr = new InputStreamReader(is, encoding);
			br = new BufferedReader(isr);
		} catch (Exception ex) {
			closeInputFile();
			throw ex;
		}
	}

	/**
	 * 输入用文件关闭 <BR>
	 */
	protected void closeInputFile() {
		try {
			br.close();
		} catch (Exception ex) {
			// empty
		}
		try {
			isr.close();
		} catch (Exception ex) {
			// empty
		}
		try {
			fis.close();
		} catch (Exception ex) {
			// empty
		}
		br = null;
		fis = null;
		isr = null;
	}

	/**
	 * 文件输入用逐行读入 <BR>
	 * 
	 * @param line
	 *            行
	 * @throws Exception
	 *             异常
	 */
	protected void writeLine(String line) throws Exception {
		bw.write(line, 0, line.length());
	}

	/**
	 * 读入所有文件
	 * 
	 * @throws Exception
	 */
	private void readAllLines() throws Exception {
		if (br == null) {
			throw new Exception("File can't read.");
		}
		List<String> valuesList = new ArrayList<String>();
		String strValue = null;
		while ((strValue = br.readLine()) != null) {
			if (strValue.length() > 0) {
				valuesList.add(strValue);
			}
		}
		contents = new String[valuesList.size()];
		valuesList.toArray(contents);
		closeInputFile();
	}

	/**
	 * 取得csv文件内容
	 * 
	 * @return
	 */
	private String[] getContents() {
		return contents;
	}

	/**
	 * 打开文件并获得内容
	 * 
	 * @param file
	 *            文件名
	 * @return 内容
	 * @throws Exception
	 */
	protected String[] openFileGetContents(String file) throws Exception {
		this.openInputFile(file);
		this.readAllLines();
		return getContents();
	}

	/**
	 * @param is
	 * @return operate result
	 * @throws Exception
	 */
	protected String[] openISGetContents(InputStream is) throws Exception {
		this.openInputStream(is);
		this.readAllLines();
		return getContents();
	}

	protected boolean deleteCVSFile(String FileName) throws Exception {
		File file = new File(FileName);
		file.deleteOnExit();

		file = null;
		return true;
	}
}
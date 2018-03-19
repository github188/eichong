package com.wanma.web.support.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SensitiveWordUtil {
	/**
	 * 获取文字中的敏感词
	 * 
	 * @param txt
	 *            文字
	 * @return
	 * @version 1.0
	 */
	public static List<String> getSensitiveWord(String word,List<String> sensitiveWordList) {
		List<String> list = new ArrayList<String>();
		for (String s : sensitiveWordList) {
			if (word.contains(s)) {
				list.add(s);
			}
		}
		return list;
	}

	/**
	 * 替换敏感字字符
	 * 
	 * @param txt
	 * @param matchType
	 * @param replaceChar
	 *            替换字符，默认*
	 * @version 1.0
	 */
	public static String replaceSensitiveWord(String txt, String replaceChar,List<String> sensitiveWordList) {
		String resultTxt = txt;
		List<String> set = getSensitiveWord(txt,sensitiveWordList); // 获取所有的敏感词
		Iterator<String> iterator = set.iterator();
		String word = null;
		String replaceString = null;
		while (iterator.hasNext()) {
			word = iterator.next();
			replaceString = getReplaceChars(replaceChar, word.length());
			resultTxt = resultTxt.replaceAll(word, replaceString);
		}

		return resultTxt;
	}

	/**
	 * 获取替换字符串
	 * 
	 * @param replaceChar
	 * @param length
	 * @return
	 * @version 1.0
	 */
	private static String getReplaceChars(String replaceChar, int length) {
		String resultReplace = replaceChar;
		for (int i = 1; i < length; i++) {
			resultReplace += replaceChar;
		}

		return resultReplace;
	}
}

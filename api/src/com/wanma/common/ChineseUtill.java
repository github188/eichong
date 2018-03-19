package com.wanma.common;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 乱码检测工具
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime 2015-4-30 下午03:32:39
  * @updator： 
  * @updateTime：   
  * @version：V1.0
  * 其他可选例子：
 */
public class ChineseUtill {
	 private static boolean isChinese(char c) {  
	        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
	        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
	                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
	                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
	                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
	                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
	                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
	            return true;  
	        }  
	        return false;  
	    }  
	      
	    public static boolean isMessyCode(String strName) {  
	        Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");  
	        Matcher m = p.matcher(strName);  
	        String after = m.replaceAll("");  
	        String temp = after.replaceAll("\\p{P}", "");  
	        char[] ch = temp.trim().toCharArray();  
	        float chLength = 0 ;  
	        float count = 0;  
	        for (int i = 0; i < ch.length; i++) {  
	            char c = ch[i];  
	            if (!Character.isLetterOrDigit(c)) {  
	                if (!isChinese(c)) {  
	                    count = count + 1;  
	                }  
	                chLength++;   
	            }  
	        }  
	        float result = count / chLength ;  
	        if (result > 0.4) {  
	            return true;  
	        } else {  
	            return false;  
	        }  
	    }  
	      
	      
	    public static String toChinese(Object msg){  
	        String tempMsg =JudgeNullUtils.nvlStr(msg) ;  
	        if(isMessyCode(tempMsg)){  
	            try {  
	                return new String(tempMsg.getBytes("ISO8859-1"), "UTF-8");  
	            } catch (Exception e) {  
	            }  
	        }  
	        return tempMsg ;   
	    }  
	   
}

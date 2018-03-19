package com.wanma.web.support.utils;
import java.text.DecimalFormat;

public class DoubleUtil {
	public static final DecimalFormat df = new DecimalFormat("######0.00"); 
	
	public static String toFix2Float(Object value){
		return df.format(value);
	}

}

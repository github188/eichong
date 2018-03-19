package com.epcentre.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
/**
 * 处理字符串的公共类
 * @author haojian
 * Apr 24, 2012 11:03:00 AM
 */
public class StringUtil {
	
	
	/**
	 * 是不是不是一个数字 is not a number
	 * @author haojian
	 * Jun 19, 2013 3:48:49 PM
	 * @return
	 */
	public static boolean isNumber(String str){
		if(str==null){
			return false;
		}
		boolean isNumber=str.matches("[0-9]+");
		return isNumber;
		
	}

	
	/**将String类型转换成String*/
	public static String toString(String str){
		if(str==null||"null".equalsIgnoreCase(str)){
			return "";
		}
		return str;
	}
	
	/**
	 * 将String类型转换成Integer类型,如果字符串为null或者"",返回的Ingeter为null
	 * 由于老代码里面很多地方根据null来进行逻辑判断，所以一些数据还需用null，而不能用默认值0
	 * @author haojian
	 * Oct 11, 2012 1:57:49 PM
	 * @param str
	 * @return
	 */
	public static Integer stringToIntegerNullToNull(String str){
		if(str==null||"".equals(str)){
			return null;
		}
		return Integer.valueOf(str);
	}
	
	/**将String类型转换成int类型*/
	public static int stringToInt(String str){
		if(str==null||"".equals(str)||"null".equalsIgnoreCase(str)){
			return 0;
		}
		return Integer.valueOf(str);
	}
	
	/**将String类型转换成double类型*/
	public static double stringToDouble(String str){
		if(str==null||"".equals(str)||"null".equalsIgnoreCase(str)){
			return 0d;
		}
		return Double.valueOf(str);
	}
	
	/**将String类型转换成double类型*/
	public static Float stringToFloat(String str){
		if(str==null||"".equals(str)||"null".equalsIgnoreCase(str)){
			return 0f;
		}
		return Float.valueOf(str);
	}
	
	/**将String数组转换成int数组*/
	public static int[] stringArrayToIntArray(String[] ss){
		int[] ii = new int[ss.length];
		for(int i=0;i<ss.length;i++){
			ii[i] = stringToInt(ss[i]);
		}
		return ii;
	}
	
	/**将String数组转换成Float数组*/
	public static float[] stringArrayToFloatArray(String[] ss){
		float[] ff = new float[ss.length];
		for(int i=0;i<ss.length;i++){
			ff[i] = stringToFloat(ss[i]);
		}
		return ff;
	}
	
	/**将String数组转换成double数组*/
	public static double[] stringArrayToDoubleArray(String[] ss){
		double[] dd = new double[ss.length];
		for(int i=0;i<ss.length;i++){
			dd[i] = stringToDouble(ss[i]);
		}
		return dd;
	}
	
	/**
	 * 将一个list里面的内容转换成一个 用“,”隔开的字符串
	 * @author haojian
	 * Jun 24, 2013 3:45:07 PM
	 * @param list
	 * @return
	 */
	public static String listToString(List list){
		if(list==null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<list.size();i++){
			Object obj = list.get(i);
			if(i!=0){
				sb.append(",");
			}
			sb.append(obj.toString());
		}
		return sb.toString();
	}
	
	/**
	 * 将一个数组里面的内容转换成一个 用“,”隔开的字符串
	 * @author haojian
	 * Jun 24, 2013 3:45:07 PM
	 * @param list
	 * @return
	 */
	public static String intArrayToString(Object[] oo){
		if(oo==null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<oo.length;i++){
			Object obj = oo[i];
			if(i!=0){
				sb.append(",");
			}
			sb.append(obj.toString());
		}
		return sb.toString();
	}
	
	/**
	 * 将字符串变为整形数组
	 * @author haojian
	 * Jul 13, 2012 11:11:12 AM
	 * @param source  源字符串
	 * @param split 字符串中的分隔符
	 * @return
	 */
	public static int [] stringToIntArray(String source,String split){
		String [] spits = source.split(split);
		int length = spits.length;
		int [] dest = new int[length];
		for(int i = 0;i < length;i ++){
			dest[i] = stringToInt(spits[i]);
		}
		return dest;
	}
	
	/**将0,1转换成false true*/
	public static boolean toBoolean(String str){
		str = str.trim();
		return str.equals("1")?true:false;
	}
	
	/**将0,1转换成false true*/
	public static boolean intToBoolean(int i){
		return i==1?true:false;
	}
	
	/**将false true转换成1,0*/
	public static int booleanToInt(boolean b){
		return b?1:0;
	}
	
	/**
	 * 将字符串首字母变大写
	 * @author haojian
	 * Apr 3, 2013 1:38:48 PM
	 * @param str
	 * @return
	 */
	public static String firstToUpperCase(String str){
		String s = str.substring(0,1).toUpperCase()+str.substring(1); 
		return s;
	}
	
	/**
	 * 将字符串首字母变小写
	 * @author haojian
	 * Apr 7, 2013 9:59:37 AM
	 * @param str
	 * @return
	 */
	public static String firstToLowerCase(String str){
		String s = str.substring(0,1).toLowerCase()+str.substring(1); 
		return s;
	}
	
	
	/**
	 * 将int类型转换成二进制的字符串
	 * @author haojian
	 * Nov 8, 2012 5:30:16 PM
	 * @param num
	 * @return
	 */
	public static String intToBinaryString(int num) {
		StringBuilder builder = new StringBuilder();
		String s = "";
		for (int i = 0; i < 31; i++) {
			if ((num & 1) == 1) {
				s = "1";
			} else {
				s = "0";
			}
			builder.append(s);
			num = num >> 1;
		}
		return builder.reverse().toString();

	}

	/**
	 * 将二进制字符串转换成 int 类型
	 * @author haojian
	 * Nov 8, 2012 5:17:25 PM
	 * @param str
	 * @return
	 */
	public static int binaryStringToInt(String str){ 
		char[] cc = str.toCharArray();
		
		int num = 0;
		for(int i=0;i<cc.length;i++){
			num = num + (int)Math.pow(2, cc.length-1-i)*Integer.valueOf(String.valueOf(cc[i]));
		}
		return num;
	}
	
	public static final String toHex(byte hash[]) {
		StringBuffer buf = new StringBuffer(hash.length * 2);
		int i;

		for (i = 0; i < hash.length; i++) {
			if (((int) hash[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) hash[i] & 0xff, 16));
		}
		
		return buf.toString();
	}
	
	/**
	 * 功能：口令的MD5加密
	 * @param String inputstr(明文)
	 * @return String
	 */
	public synchronized static String md5(String inputStr)
    {
        String pwd = "";
        try {
        	MessageDigest digest = MessageDigest.getInstance("MD5");
			byte[] s = digest.digest(inputStr.getBytes("UTF-8"));
			pwd = toHex(s);
			return pwd;
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Failed to load the MD5 MessageDigest. "
					+ "Jive will be unable to function normally.");
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.err.println("Failed to load the MD5 MessageDigest. "
					+ "Jive will be unable to function normally.");
			e.printStackTrace();
			return null;
		}
        
    }
	
	
	
	/**
	 * MD5 hash
	 * @author haojian
	 * Dec 4, 2013 10:35:41 AM
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String md5HashValue(String value){
        MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        md.update(value.getBytes());
        return toHexString(md.digest());
    }

    private static String toHexString(byte[] in) {
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < in.length; i++) {
            String hex = Integer.toHexString(0xFF & in[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(StringUtil.isNumber("2223"));
		
	}

}

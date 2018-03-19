package com.wanma.util;
import java.util.Random;
public class RandomNumer {
	private static final char[] symbols;
	 
	  static {
	    StringBuilder tmp = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch)
	      tmp.append(ch);
	    for (char ch = 'a'; ch <= 'z'; ++ch)
	      tmp.append(ch);
	    for (char ch = 'A'; ch <= 'Z'; ++ch)
	      tmp.append(ch);
	 
	    // 添加一些特殊字符
	    tmp.append("!@#$%");
	    symbols = tmp.toString().toCharArray();
	  }
	 
	  private final Random random = new Random();
	 
	  private final char[] buf;
	 
	  public RandomNumer(int length) {
	    if (length < 1)
	      throw new IllegalArgumentException("length < 1: " + length);
	    buf = new char[length];
	  }
	 
	  public String nextString() {
	    for (int idx = 0; idx < buf.length; ++idx)
	      buf[idx] = symbols[random.nextInt(symbols.length)];
	    return new String(buf);
	  }
	  //初始密钥number，长度length
	  public static  String getUpperMd5Number(String number,int length){
		RandomNumer randomNumer = new RandomNumer(10);
		  String rNmunber = null;
		  do {
			  rNmunber = randomNumer.nextString();
		      } while (!rNmunber.matches(".*(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%]).*"));
		 //密钥为用户身份标识+10位随机字符串（包含特殊字符），md5之后的32位大写字符串
		String partnerKey =  MD5Util.Md5(number+rNmunber).toUpperCase();
		return partnerKey;
		  
	  }
	  public static void main(String[] args) {
			
			String aString = RandomNumer.getUpperMd5Number("1001",10);
			System.out.println(aString);
		}
		
}


package com.wanma.support.utils;  
  
import org.apache.commons.codec.digest.HmacUtils;




  
/** 
 * 验证签名 
 * 
 */  
public class CecSign {  
  
	 public static String sign(String SigSecret ,String OperatorID,String Data, Long TimeStamp,String Seq) {
	        
	        String sig = HmacUtils.hmacMd5Hex(SigSecret, OperatorID + Data + TimeStamp + Seq).toUpperCase();
	        return sig;
	    }
	 
	
}  
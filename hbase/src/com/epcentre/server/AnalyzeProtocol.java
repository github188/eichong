package com.epcentre.server;

import io.netty.channel.Channel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

import com.epcentre.server.AnalyzeConstant;

import com.epcentre.server.WmIce104Util;







public class AnalyzeProtocol{
	
    //心跳
    public static  byte[] do_heart(int revNum) {

		ByteArrayOutputStream bmsg = new ByteArrayOutputStream( AnalyzeConstant.EPGATE_SENDBUFFER);
		
		bmsg.write(AnalyzeConstant.HEAD_FLAG1);
		bmsg.write(AnalyzeConstant.HEAD_FLAG2);
		
		bmsg.write(5); //长度
		bmsg.write(0); //长度
		
		bmsg.write(1); //原因
		bmsg.write(01);//命令 
		bmsg.write(00);//命令
		try {
			bmsg.write(WmIce104Util.short2Bytes((short)revNum));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bmsg.toByteArray();
	}
 

   
}


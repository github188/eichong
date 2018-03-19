package com.epcentre.server;

public class AnalyzeConstant{
	
	public static final short EPGATE_SENDBUFFER=512;
	
	public static final byte  HEAD_FLAG1 = 0x45;
	
	public static final byte  HEAD_FLAG2 = 0x43;
	

	public static final short D_HEART= 1; 
//	单点信息
	public static final short U_ONE_BIT_YX = 2;
//	双点信息	
	public static final short U_TWO_BIT_YX= 3;
//	遥测
	public static final short U_YC = 4;
//	变长遥测	
	public static final short U_YC2 = 5;
	

	public static final int CONNECT_TIMEOUT = 60;
	public static final int ONTIME_SEND_HEART = 20;
		
}

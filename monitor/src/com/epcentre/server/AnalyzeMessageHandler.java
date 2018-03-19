 package com.epcentre.server;

import io.netty.channel.Channel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epcentre.service.AnalyzeCommClient;
import com.epcentre.service.AnalyzeService;
import com.epcentre.service.RealData;
import com.wanma.hbase.RealtimeUtil;


/**
 * 接受电桩客户端数据并处理
 */
public class AnalyzeMessageHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(AnalyzeMessageHandler.class);


	/**
	 * 接受电桩发送的消息进行处理
	 * 
	 * @author hly 2015-12-15
	 * @param channel
	 * @param message
	 */
	public static void handleMessage(Channel channel, AnalyzeMessage message) {

		 byte[] msg = message.getBytes();
		 try {
				InputStream in = new ByteArrayInputStream(msg);
				int cos = StreamUtil.read(in);
				byte typel = StreamUtil.read(in);
				byte typeh = StreamUtil.read(in);
				int type = typel + typeh * 0x100;

				boolean logMsg = true;
				
				int msgLen = msg.length-12;
				
				AnalyzeCommClient commClient= AnalyzeService.getCommClientByChannel(channel);
				
				if(commClient == null)
				{
					return;
				}
				java.util.Date dt = new Date(); 
				long now = dt.getTime() / 1000;
				//commClient.setLastUseTime(now);
				
				short temp = (short)(type& 0xffff);
				if(temp == 1)
				{
					commClient.setLastUseTime(now);
					handleHeart(channel,in,commClient.getRevNum());
					return;
				}
				if(msg.length<13)
				{
					return;
				}

				switch (type & 0xffff) {
	            case AnalyzeConstant.U_ONE_BIT_YX://单点遥信
	            case AnalyzeConstant.U_TWO_BIT_YX://双点遥信
	            case AnalyzeConstant.U_YC://遥测
	            case AnalyzeConstant.U_YC2://遥测2
				{
					commClient.setLastUseTime(now);
					int revNum=commClient.getRevNum();
					revNum++;
					commClient.setRevNum(revNum);
					
					String epCode = StreamUtil.readBCDWithLength(in, 8);
					int epGunNo=(int) StreamUtil.read(in);
					byte epType = StreamUtil.read(in);
					
					logger.info("epType:{},datatype:{}",epType,type);
					
					RealData realData = handleData(epCode,epGunNo,
							epType,type,in);
					RealtimeUtil.addRealtimeData(realData);
				}
				break;
	          
				default:
				break;
			  }
	   	   }
	       catch (IOException e) {
				e.printStackTrace();
		   }
	}
	
	
	
	private static void handleYc(Map<Integer,String> pointMap,InputStream in) 
    {
		if(pointMap ==null)
			return;
		 try
	    {
			 int num = StreamUtil.read(in);
			for(int i=0;i<num;i++)
			{
			   short addr = (short)StreamUtil.readUB2(in);
			   int value =(int)StreamUtil.readUB2(in);
			   logger.debug(" yc addr:{},value:{}",addr,value);
			   switch(addr)
			   {
			    case EpProtocolConstant.YC_WORKSTATUS://充电机状态
			    case EpProtocolConstant.YC_CAR_PLACE_LOCK: //车位地锁状态
			    case EpProtocolConstant.YC_SOC://soc
			    case EpProtocolConstant.YC_TOTAL_TIME://累计充电时间
			    case EpProtocolConstant.YC_REMAIN_TIME://估计剩余时间
			    case EpProtocolConstant.YC_BATTARY_TYPE://电池类型
			    case EpProtocolConstant.YC_BATTARY_RATED_CAPACITY://整车动力蓄电池系统额定容量
			    case EpProtocolConstant.YC_BATTARY_MAKE_YEAR://电池组生产日期年
			    case EpProtocolConstant.YC_BATTARY_MAKE_DATE://电池组生产日期月，日
			    case EpProtocolConstant.YC_BATTARY_CHARGE_TIME://电池组充电次数
			    {
			    	String str = ""+value;
			    	pointMap.put((int)addr,str);
			    }
			    break;
			    case EpProtocolConstant.YC_BATTRY_LOWEST_TEMP://电池组最低温度
			    case EpProtocolConstant.YC_BATTRY_HIGHEST_TEMP://电池组最高温度
			    case EpProtocolConstant.YC_OUT_VOL://充电机输出电压
			    case EpProtocolConstant.YC_A_VOL://A相电压
			    case EpProtocolConstant.YC_B_VOL://b相电压
			    case EpProtocolConstant.YC_C_VOL://c相电压
			    case EpProtocolConstant.YC_OUT_HIGH_VOL://最高输出电压
			    case EpProtocolConstant.YC_OUT_LOW_VOL://最低输出电压
			    case EpProtocolConstant.YC_CAR_BATTRY_TOTAL_VOL://整车动力电池总电压
			    case EpProtocolConstant.YC_SIGNLE_BATTRY_HIGH_VOL_GROUP://单体蓄电池最高电压和组号
			    case EpProtocolConstant.YC_EP_TEMPERATURE://电桩内部温度
			    case EpProtocolConstant.YC_SIGNLE_BATTRY_CAN_HIGH_VOL://单体蓄电池最高允许充电电压
			    case EpProtocolConstant.YC_BATTRY_TOTAL_POWER://动力蓄电池标称总能量
			    case EpProtocolConstant.YC_BATTRY_HIGH_VOL://最高允许充电总电压(额定总电压)
			    case EpProtocolConstant.YC_BATTRY_CAN_HIGH_TEMP://最高允许温度
			    {
			    	BigDecimal temp = new BigDecimal(value).multiply(Global.Dec1);
			    	String str = ""+temp.doubleValue();
			    	pointMap.put((int)addr,str);
			    }
			    break;
			    case EpProtocolConstant.YC_OUT_CURRENT://充电机输出电流
			    case EpProtocolConstant.YC_A_CURRENT://A相电流
			    case EpProtocolConstant.YC_B_CURRENT://b相电流
			    case EpProtocolConstant.YC_C_CURRENT://c相电流
			    case EpProtocolConstant.YC_OUT_HIGH_CURRENT://最大输出电流
			    case EpProtocolConstant.YC_SIGNLE_BATTRY_HIGH_CURRENT://最高允许充电电流
			    {
			    	BigDecimal temp = new BigDecimal(value).multiply(Global.Dec2);
			    	String str = ""+temp.doubleValue();
			    	pointMap.put((int)addr,str);
			    }
			    break;
			    default:
			    {
			    	if(addr>=EpProtocolConstant.YC_CHARGER_MOD_1_OUT_VOL)
			    	{
			    		String str;
			    		int tempAddr = (addr-EpProtocolConstant.YC_CHARGER_MOD_1_OUT_VOL)%9;
			    	    if(tempAddr==0||tempAddr==0||tempAddr==0||tempAddr==0)
			    	    {
			    	    	BigDecimal temp = new BigDecimal(value).multiply(Global.Dec1);
			    	    	str = ""+temp.doubleValue();
			    	    }
			    	    else 
			    	    {
			    	    	BigDecimal temp = new BigDecimal(value).multiply(Global.Dec2);
					        str = ""+temp.doubleValue();
			    	    }
			    	    pointMap.put((int)addr,str);
			    	}
			    }
			    break;
			    
			   }
			}
			
        } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
    }
	
	private static void handleChangeLenYc(Map<Integer,String> pointMap,InputStream in) 
    {
		if(pointMap == null)
			return;
		 try
	    {
			int num = StreamUtil.read(in);
			for (int i = 0; i < num; i++)
			{
				short addr = (short) StreamUtil.readUB2(in);
				byte len = StreamUtil.read(in);
			    
				switch (addr) 
				{
				case EpProtocolConstant.YC_VAR_ACTIVE_TOTAL_METERNUM: // 有功总电度
				case EpProtocolConstant.YC_VAR_CHARGED_METER_NUM:// 已充度数
				{
					int value =(int)StreamUtil.readInt(in);
			    	BigDecimal temp = new BigDecimal(value).multiply(Global.Dec3);
			    	String str = ""+temp.doubleValue();
			    	pointMap.put((int)addr,str);
			    	logger.debug(" ChangeLenYc addr:{},value:{}",addr,value);
				}
				break;
				case EpProtocolConstant.YC_VAR_CHARGED_COST: // 已充金额
				case EpProtocolConstant.YC_VAR_CHARGED_PRICE: // 单价
				{
					int value =(int)StreamUtil.readInt(in);
			    	BigDecimal temp = new BigDecimal(value).multiply(Global.Dec2);
			    	String str = ""+temp.doubleValue();
			    	pointMap.put((int)addr,str);
			    	logger.debug(" ChangeLenYc addr:{},value:{}",addr,value);
				}
				break;
				case EpProtocolConstant.YC_VAR_CAR_VIN: // 车辆识别码
				case EpProtocolConstant.YC_VAR_BATTARY_FACTORY: // 电池厂商
				{
					byte [] val =StreamUtil.readWithLength(in,len);
					String str = StreamUtil.getCString(val);
			    	pointMap.put((int)addr,str);
			    	logger.debug(" ChangeLenYc addr:{},value:{}",addr,str);
				}
				break;
				default:
				{
					byte [] val =StreamUtil.readWithLength(in,len);
				}
				break;
				}
			}

		} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
    }
	private static void handleHeart(Channel channel,InputStream in,int revNum) 
    {
		byte[] msg = AnalyzeProtocol.do_heart(revNum);
		AnalyzeMessageSender.sendMessage(channel, msg);
    }
	private static RealData handleData(String epCode,int epGunNo,int epType,int dataType,InputStream in) 
    {
		RealData realData	= new RealData();	
		 try{
			 realData.setEpCode(epCode);
			 realData.setEpGunNo(epGunNo);
			 realData.setEpId(epCode+epGunNo);
			 realData.setEpType(epType);
			 realData.setDataType(dataType-1);
				
			
			Map<Integer,String> pointMap = new ConcurrentHashMap<Integer,String>();
			
			switch(dataType)
			{
			   case 2:
			   case 3:
			   {
				  int num = (int) StreamUtil.read(in);
				for(int i=0;i<num;i++)
			    {
			        short addr = (short)StreamUtil.readUB2(in);
			        int value =(int)StreamUtil.read(in);
			        String str=""+value;
			        pointMap.put((int)addr, str);
			        logger.debug("yx addr:{},value:{}",addr,value);
			    }
		       }
			    break;
			   case 4:
			   {
				handleYc(pointMap,in);
			   }
			    break;
			   case 5:
			   {
				handleChangeLenYc(pointMap,in);
			   }
			    break;
			  default:
				break;
		   }
			realData.setPointMap(pointMap);
		 }
		 catch(IOException e)
		 {
			 e.printStackTrace();
		 }
		 return realData;	
	}
	
		 
}


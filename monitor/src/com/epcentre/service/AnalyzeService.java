package com.epcentre.service;

import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import io.netty.channel.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.epcentre.server.AnalyzeProtocol;
import com.epcentre.server.AnalyzeConstant;
import com.epcentre.server.AnalyzeMessageSender;




public class AnalyzeService {
	
	private static final Logger logger = LoggerFactory.getLogger(AnalyzeService.class);
	
	
	 /**
	 * Channel 2  通讯缓存对象
	 */
	public static Map<Channel,AnalyzeCommClient> mapChannel2Client = new ConcurrentHashMap<Channel, AnalyzeCommClient>();
	


	
	public static AnalyzeCommClient getCommClientByChannel(Channel ch)
	{
		return mapChannel2Client.get(ch);
	}
		
	
	
	 public static void removeChannel(Channel ch)
	 {
			
		 AnalyzeCommClient commClient = mapChannel2Client.get(ch);
		if(commClient != null)
		{
			logger.info("mapChannel2Client,size:"+mapChannel2Client.size()+"\n");
			
			mapChannel2Client.remove(ch);
			logger.info("mapChannel2Client,size:"+mapChannel2Client.size()+"\n");
		}
	}
	public static void addCommClient(Channel ch)
	{
		AnalyzeCommClient commClient = new AnalyzeCommClient();

		if(commClient==null)
			return;
		if( ch ==null)
			return ;
		
		commClient.setRevNum(0);
		java.util.Date dt = new Date(); 
		long now = dt.getTime() / 1000;
		commClient.setLastUseTime(now);
		commClient.setChannel(ch);
		mapChannel2Client.put(ch, commClient);
	}
	
		
	/**
	 * 电桩下线
	 * @author HeWeiwen
	 * 2014-12-1
	 * @param channel
	 */
	public static void offLine(Channel channel)
	{
		removeChannel(channel);		
	}
	
	
	
	public static void checkTimeOut(){
		
		Iterator iter = mapChannel2Client.entrySet().iterator();
		
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			
			java.util.Date dt = new Date(); 
			long now = dt.getTime() / 1000;
			
			AnalyzeCommClient commClient=(AnalyzeCommClient)entry.getValue();
			if(commClient==null)
			{
				continue;	
			}
			long diff = now-commClient.getLastUseTime();
			
			if(diff>AnalyzeConstant.CONNECT_TIMEOUT)//秒后更新状态
			{ 
				
				if(commClient.getChannel()!=null)
				{
					logger.info("diff>AnalyzeConstant.CONNECT_TIMEOUT");
					commClient.getChannel().close();
					//commClient.setChannel(null);
					removeChannel(commClient.getChannel());
					commClient.setRevNum(0);
				}					
			}
			else {
				diff = now - commClient.getLastSendHeartTime();// 定时发送心跳命令
				if (diff >= AnalyzeConstant.ONTIME_SEND_HEART) {
					byte[] msg = AnalyzeProtocol.do_heart(commClient.getRevNum());
					AnalyzeMessageSender.sendMessage(commClient.getChannel(), msg);
					commClient.setLastSendHeartTime(now);
					logger.info("setLastSendHeartTime");

				}		
		   }
		
	    }
	}
	
	
}

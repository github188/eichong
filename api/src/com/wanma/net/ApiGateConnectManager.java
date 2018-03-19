package com.wanma.net;

import io.netty.channel.Channel;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;




import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bluemobi.server.GameServer;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblGateservice;

public class ApiGateConnectManager {
	
	private static Log log = LogFactory.getLog(ApiGateConnectManager.class);
	
	public static ConcurrentMap<String,GateConnectObject> gateList=new ConcurrentHashMap<String,GateConnectObject>();//Gate服务器Id-Gate服务器对象对应信息
	
	public static GateConnectObject getGateConnectObject(String gateId)
	{
		return gateList.get(gateId);
	}
	
	public static void addGateConnectObject(String gateId,GateConnectObject object)
	{
		if(gateId==null || gateId.length()==0 || object==null)
		{
			log.error("addGateConnectObject fail");
			return;
		}
		gateList.put(gateId,object);
	}
	public static void removeGateConnectObject(String gateId)
	{
		gateList.remove(gateId);
		
	}
	public static int getSize()
	{
		return gateList.size();
	}
	public static void connectAllGate(List<TblGateservice> gateList)
	{
		//遍历内存在中有，但数据库中没有的
    	syncDb(gateList);
    	
    		
    	//判断数据库中增加和删除
		for (TblGateservice tblGateservice : gateList) {//限制次数
			try {
				String gateId= ""+tblGateservice.getPkGateid();
				int gateState = tblGateservice.getGateState();
				
				//log.info("changeGateChannelCache gateId:"+gateId+",gateState:"+gateState);
				//log.info("changeGateChannelCache GATE_TO_CHANNEL_INFO size:"+WanmaConstants.GATE_TO_CHANNEL_INFO.size());
				
				if( gateState==1 ){//创建一个Channel
					
					GateConnectObject  gateConnectionObject= ApiGateConnectManager.getGateConnectObject(gateId);
					//内存中没有的话，加上
					if(gateConnectionObject==null)
					{
						//log.info("changeGateChannelCache gateId:"+gateId+"gateConnectionObject==null");
						gateConnectionObject=new GateConnectObject();
						
						gateConnectionObject.setGateId(gateId);
						gateConnectionObject.setGateIp(tblGateservice.getGtseGateip());
						gateConnectionObject.setState(tblGateservice.getGateState());
						
						ApiGateConnectManager.addGateConnectObject(gateId, gateConnectionObject);
					}
					//log.info("changeGateChannelCache connect times:"+gateConnectionObject.getConnTimes());
					//失败次数大于6次不重连
					Channel  gateChannel= WanmaConstants.GATE_TO_CHANNEL_INFO.get(gateId);
					if(gateChannel == null && gateConnectionObject.getConnTimes() <6 )
    				{
						gateConnectionObject.addConnectTimes();
						GameServer.createNettyClient(tblGateservice);
    				}
					//在连接函数中增加到列表里
				}
				else//所有不等于1的，都认为关闭
				//if(gateState == 2)
				{
					Channel  gateChannel=WanmaConstants.GATE_TO_CHANNEL_INFO.get(gateId);
					if(gateChannel!=null)
					{
						//log.info("changeGateChannelCache gateChannel:"+gateChannel);
    					
						gateChannel.close();//关闭连接
    					WanmaConstants.GATE_TO_CHANNEL_INFO.remove(gateId);//移除MAP数据
					}
					else
					{
						//log.info("changeGateChannelCache GATE_TO_CHANNEL_INFO do not find gateId:"+gateId);
					}
					//
					GateConnectObject  gateConnectionObject= ApiGateConnectManager.getGateConnectObject(gateId);
					if(gateConnectionObject!=null)
					{
						//log.info("changeGateChannelCache connect times:"+gateConnectionObject.getConnTimes());
						ApiGateConnectManager.removeGateConnectObject(gateId);
					}
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.error("更新GATE-Channel异常：", e);
			}
			//log.info("changeGateChannelCache GATE_TO_CHANNEL_INFO size:"+WanmaConstants.GATE_TO_CHANNEL_INFO.size());
			//
			//log.info("changeGateChannelCache GateConnectObject list size():"+ApiGateConnectManager.getSize());
		}
	}
	
	public static void syncDb(List<TblGateservice> gateDbList)
	{
		@SuppressWarnings("rawtypes")
		Iterator iter = gateList.entrySet().iterator();
		while (iter.hasNext()) {
			@SuppressWarnings("rawtypes")
			Map.Entry entry = (Map.Entry) iter.next();
			GateConnectObject gateObject=(GateConnectObject) entry.getValue();
			if(null == gateObject)
			{
				continue;
			}
			String gateId = (String) entry.getKey();
			boolean find=false;
			for(TblGateservice gate:gateDbList){
				String dbGateId= ""+gate.getPkGateid();
				if(dbGateId.equals(gateId))
				{
					find=true;
					break;
				}
			}
			if(find==false)//移除掉
			{
				Channel gateChannel =  WanmaConstants.GATE_TO_CHANNEL_INFO.get(gateId);
				if(gateChannel!=null)
				{
					gateChannel.close();
				}
				WanmaConstants.GATE_TO_CHANNEL_INFO.remove(gateId);//移除MAP数据
				iter.remove();
				gateList.remove(gateId);      
			}
		}
		
	}
}

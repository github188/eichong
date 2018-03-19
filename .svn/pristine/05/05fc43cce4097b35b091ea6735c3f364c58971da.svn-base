/**
 * FileName: NetConnetionManager.java
 * Author: Administrator
 * Create: 2015年3月18日
 * Last Modified: 2015年3月18日
 * Version: V1.0 
 */
package com.wanma.net;

import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import com.bluemobi.sender.MessageSender;
import com.bluemobi.server.GameServer;
import com.wanma.common.JudgeNullUtils;
import com.wanma.common.WanmaConstants;

/**
 * 网络管理类
 * 
 * @version V1.0
 * @author yangweiran
 * @date 2015年3月18日
 */
public class NetConnetionManager {

	/** 日志处理对象 */
	private static final Logger logger = Logger.getLogger(NetConnetionManager.class);
	
	/**
	 * 通过电桩编号发送消息
	 * @param gateId
	 * @param potoNum
	 * @param bb
	 * @return
	 */
	public static ChannelFuture sendMessage(String elctricNo, Short potoNum, byte[] bb) {

		logger.debug("向服务器发送消息开始...,协议号为:" + potoNum);
		String gateId=WanmaConstants.ELECTRICPILE_TO_GATE_INFO.get(elctricNo) + "";
		// 取得当前连接Channel
		Channel channel = WanmaConstants.GATE_TO_CHANNEL_INFO.get(gateId);
		if(null == channel){
			logger.error("获取连接失败！电桩编号-Gate服务器Id："+gateId+"Channel为null");
			return null;
		}
		return NetConnetionManager.sendMessage(channel, potoNum, bb);
	
		
	}
	
	/**
	 * 通过gateId发送消息
	 * @param gateId
	 * @param potoNum
	 * @param bb
	 * @return
	 */
	public static ChannelFuture sendMessage(int gateId, Short potoNum, byte[] bb) {

		logger.debug("向服务器发送消息开始...,协议号为:" + potoNum);
		// 取得当前连接Channel
		Channel channel = (Channel)WanmaConstants.GATE_TO_CHANNEL_INFO.get(JudgeNullUtils.nvlStr(gateId));
		return NetConnetionManager.sendMessage(channel, potoNum, bb);
	
		
	}
	
	/**
	 * 通过channel发送消息
	 * @param gateId
	 * @param potoNum
	 * @param bb
	 * @return
	 */
	private static ChannelFuture sendMessage(Channel channel, Short potoNum, byte[] bb) {

		logger.debug("向服务器发送消息开始...,协议号为:" + potoNum);
		// 定义服务器处理结果
		ChannelFuture channelFuture = null;

		try {
			// 执行向服务器发送消息
			channelFuture = MessageSender.gameSendToPlayer(channel, 2, potoNum,	bb);
		} catch (Exception e) {
			// 异常处理
			logger.error(e.getLocalizedMessage());
		}

		logger.debug("向服务器发送消息结束...,协议号为:" + potoNum);

		// 返回服务器处理结果
		return channelFuture;
	
		
	}

	/**
	 * 批量发送消息
	 * @param potoNum
	 * @param bb
	 * @return
	 */
	public static void sendMessage(Short potoNum, byte[] bb) {
		logger.debug("向服务器发送消息开始...,协议号为:" + potoNum);
		
		Set<String> set = WanmaConstants.GATE_TO_CHANNEL_INFO.keySet();
		for(String key : set){
			NetConnetionManager.sendMessage(WanmaConstants.GATE_TO_CHANNEL_INFO.get(key), potoNum, bb);
		}
		
	}
	
	/**
	 * 向服务器发送消息   后期作废！！！！！
	 * 
	 * @param potoNum
	 *            协议号
	 * @param bb
	 *            消息体
	 * @return ChannelFuture 服务器处理结果
	 */
	/*public static ChannelFuture sendMessage(Short potoNum, byte[] bb) {
		logger.debug("向服务器发送消息开始...,协议号为:" + potoNum);
		// 取得当前连接Channel
		//Channel channel = GameServer.GateChannel;
		return NetConnetionManager.sendMessage(channel, potoNum, bb);
	}*/

}

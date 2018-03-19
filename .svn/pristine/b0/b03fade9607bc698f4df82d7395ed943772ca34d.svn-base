package com.bluemobi.sender;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.bluemobi.cache.GameWorld;
import com.bluemobi.model.Player;
/**
 * 发送消息必须通过此类
 * @author haojian
 * Apr 1, 2013 3:06:02 PM
 */
public class MessageSender {
	
	private static final Logger logger = Logger.getLogger(MessageSender.class);
	
	/**通信协议混淆参数1*/
	private static byte mask1 = 89;
	/**通信协议混淆参数2*/
	private static byte mask2 = 122;
	/**通信协议混淆参数3*/
	private static byte mask3 = 122;
	/**通信协议混淆参数4*/
	private static byte mask4 = 89;
	
	
	private static int MIN_COMPRESS_BYTE_NUM = 128;
	
	
	
	public static ChannelFuture gameSendToPlayer(Player player, short protocol){
		
		//获取gate的 channel
		Channel channel = GameWorld.gateChannelMap.get( player.getUser().getGateId() );
		
		List<Integer> userIdColl = new ArrayList<Integer>();
		userIdColl.add(player.getUserId());
		
		ChannelFuture future = MessageSender.gameSendToPlayer(channel, userIdColl, protocol, new byte[0]);
		
		return future;
	}
	
	public static ChannelFuture gameSendToPlayer(Player player, short protocol, byte[] bb){
		
		if(player==null){
			logger.error("没有player不能调用此方法发送消息！");
		}
		//获取gate的 channel
		Channel channel = GameWorld.gateChannelMap.get( player.getUser().getGateId() );
		
		List<Integer> userIdColl = new ArrayList<Integer>();
		userIdColl.add(player.getUserId());
		
		ChannelFuture future = MessageSender.gameSendToPlayer(channel, userIdColl, protocol, bb);
		
		return future;
	}
	
	public static ChannelFuture gameSendToPlayer1(Channel channel, short protocol, byte[] bb){
		
		if(channel==null){
			logger.error("没有player不能调用此方法发送消息！");
		}

		
		ChannelFuture future = MessageSender.gameSendToPlayer(channel, new ArrayList<Integer>(), protocol, bb);
		
		return future;
	}
	
	/**
	 * 给网关发消息
	 * @author haojian
	 * @date 2014-10-21 上午10:06:44 
	 * @param channel
	 * @param protocol
	 * @param bb
	 * @return
	 * @return ChannelFuture
	 */
	public static ChannelFuture gameSendToGate(Channel channel, short protocol, byte[] bb){

		ChannelFuture future = MessageSender.gameSendToPlayer(channel, 1, protocol, bb);
		
		return future;
	}
	
	/**
	 * 群发消息
	 * @author haojian
	 * Aug 29, 2013 5:24:10 PM
	 * @param coll
	 * @param protocol
	 * @param bb
	 */
	public static void sendMessage(Collection<Player> coll, short protocol, byte[] bb){
		/**老的群发消息*/
//		ByteBuf buffer = MessageSender.encode(protocol, bb);
//		for(Player player : coll){
//			MessageSender.sendMessage(player.getChannel(), buffer);
//		}
		/**t通过Gate服务器转发消息*/
		for(Player player : coll){
			//只有真实玩家才推送(0:是真实玩家，1：是假数据玩家)
				//获取gate的 channel
				Channel channel = GameWorld.gateChannelMap.get( player.getUser().getGateId() );
						
				List<Integer> userIdColl = new ArrayList<Integer>();
				userIdColl.add(player.getUserId());
						
				ChannelFuture future = MessageSender.gameSendToPlayer(channel, userIdColl, protocol, bb);
		}
		
	}
	
	/**
	 * 给网关的某个用户发消息
	 * @author haojian
	 * @date 2014-10-21 上午10:06:25 
	 * @param channel
	 * @param userId
	 * @param protocol
	 * @param bb
	 * @return
	 * @return ChannelFuture
	 */
	public static ChannelFuture gameSendToPlayer(Channel channel, int userId, short protocol, byte[] bb){
		
		List<Integer> userIdColl = new ArrayList<Integer>();
		userIdColl.add(userId);
		
		ChannelFuture future = MessageSender.gameSendToPlayer(channel, userIdColl, protocol, bb);
		
		return future;
	}
	
	/**
	 * Game 给 Gate 服发消息
	 *  消息格式： int（长度）	short（协议号）	long[](收消息的userId)	byte(是否压缩)	byte[]
	 * @author haojian
	 * @date 2014-9-27 下午3:16:10 
	 * @param channel
	 * @param protocolNum
	 * @param senderId
	 * @param bb
	 * @return
	 * @return ChannelFuture
	 */
	public static ChannelFuture gameSendToPlayer(Channel channel, Collection<Integer> userIdColl, short protocol, byte[] bb){
		
		//收消息用户数
		short userNum = (short)userIdColl.size();
		
		int len = 2 + 2 + userNum*4 + 1 + bb.length;
		
		ByteBuf buffer = Unpooled.buffer(4 + len);

		buffer.writeInt(len);//数据长度
		System.out.println("game->gate 消息长度:"+len);
		buffer.writeShort(protocol);//协议号
		
		buffer.writeShort(userNum);//用户id数量
		for(Integer userId : userIdColl){
			buffer.writeInt(userId);//用户id
		}
		
		buffer.writeByte(0);//是否压缩
		buffer.writeBytes(bb);//数据内容
		
		ChannelFuture future = MessageSender.sendMessage(channel, buffer);
		
		return future;
		
	}
	
	/**
	 * 组装消息
	 * @author haojian
	 * May 9, 2013 4:15:39 PM
	 * @param channel
	 * @param protocol
	 * @param bb
	 * @return
	 */
	public static ByteBuf encode(short protocol, byte[] bb){
		
		//将协议号和消息体合并到一起
		//byte[] bbProtocol = ByteUtil.shortToBytes(protocol);
		//bb = ByteUtil.mergeByteArray(bbProtocol, bb);
		
		byte isCompress = 0;
//		if(bb.length > MIN_COMPRESS_BYTE_NUM ){
//			isCompress = 1;
//			byte[] bbCompress = DeflaterAndInflater.compressBytes(bb);
//			logger.info("压缩前长度： "+bb.length+" ,压缩后长度： "+bbCompress.length);
//			bb = bbCompress;
//		}
		int len = bb.length;
		
		byte b1 = (byte)(System.nanoTime() & mask1);
		byte b2 = (byte)(System.nanoTime() & mask2);
		byte b3 = (byte)(System.nanoTime() & mask3);
		byte b4 = (byte)(System.nanoTime() & mask4);
		
		ByteBuf buffer = Unpooled.buffer(2 + 4 + 2 + 1 + len + 2);
		buffer.writeByte(b1);//混淆码1
		buffer.writeByte(b2);//混淆吗2
		buffer.writeInt(len);//数据长度
		buffer.writeShort(protocol);//协议号
		buffer.writeByte(isCompress);//是否压缩
		buffer.writeBytes(bb);//数据内容
		buffer.writeByte(b3);//混淆码3
		buffer.writeByte(b4);//混淆吗4
		
        return buffer;
        
	}
	
	/**
	 * 给公共服发送消息
	 * @author haojian
	 * @date 2014-10-15 下午2:09:42 
	 * @param channel
	 * @param protocol
	 * @param bb
	 * @return
	 * @return ChannelFuture
	 */
	public static ChannelFuture gameSendToPublic(Channel channel, short protocol, byte[] bb){
		
		ChannelFuture future = MessageSender.sendMessage(channel, bb);
		
		return future;
	}
	
	
	
	/**
	 * 发送消息
	 * @author haojian
	 * Apr 1, 2013 3:09:09 PM
	 * @param channel
	 * @param channelBuffer
	 * @return
	 */
	private static ChannelFuture sendMessage(Channel channel,Object object){
		if(channel==null || !channel.isWritable()){
			return null;
		}
		ChannelFuture future = channel.writeAndFlush(object);
		return future;
	}
	
	
	/**
	 * 发送字符串消息，消息格式： 4字节长度 + 字符串转换成的字节数组
	 * 可发送任意字符串，包括自定义字符串，json，xml等。
	 * @author haojian
	 * Sep 4, 2013 12:34:17 PM
	 * @param channel
	 * @param str
	 */
	public static void sendString(Channel channel, String str){
		
		MessageSender.sendMessage(channel, str);
		
	}
	
	
	
	
	
}

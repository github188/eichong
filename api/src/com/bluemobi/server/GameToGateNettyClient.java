package com.bluemobi.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;

import com.appCore.conf.CoreConfig;
import com.appCore.model.conf.ClientConfig;
import com.appCore.netty.buffer.DynamicByteBuffer;
import com.appCore.netty.client.AbstractNettyClient;
import com.appCore.netty.client.ClientChannelInitializer;
import com.appCore.netty.message.GateToGameMessage;
import com.bluemobi.GateMessageHandler;
import com.bluemobi.cache.GameWorld;
import com.bluemobi.config.GameConfig;
import com.bluemobi.constant.protocol.gateAndGame.Game2Gate;
import com.bluemobi.sender.MessageSender;
import com.wanma.common.WanmaConstants;
import com.wanma.net.ApiGateConnectManager;
import com.wanma.net.GateConnectObject;

public class GameToGateNettyClient extends AbstractNettyClient{
	
	//当前netty客户端连接的是哪个Gate
	private String gateId;
	
	public String getGateId() {
		return gateId;
	}

	public void setGateId(String gateId) {
		this.gateId = gateId;
	}

	private static final Logger logger = Logger.getLogger(GameToGateNettyClient.class);

	public GameToGateNettyClient(ClientConfig serverConfig,ByteToMessageDecoder decoder, MessageToByteEncoder encoder) {
		super(serverConfig, decoder, encoder);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void regiest(Channel channel) {
		DynamicByteBuffer byteBuffer = DynamicByteBuffer.allocate();
		byteBuffer.putInt(CoreConfig.gameConfig.getId());//游戏服Id
		byteBuffer.putString(GameConfig.mySecurity2);//登陆密钥
		byte[] bb = byteBuffer.getBytes();
		MessageSender.gameSendToGate(channel, Game2Gate.P_10101, bb);
		
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx) {
		//super.channelClosed(ctx);
		Channel channel = ctx.channel();
		//移除gate的channel
		GameWorld.removeGateFromGameWorld(channel);
		
		//断线重连
		try {
			this.reconnection();
		} catch (Exception e) {
			logger.error("netty5客户端【重连】出现异常！【" + e.getMessage() + "】");
			e.printStackTrace();
		}
	}
	
	public void reconnection() throws Exception{
		
		logger.info("10秒后进行断线重连...");
		try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("w开始断线重连...");
		try{
			GateConnectObject gateConnObject = ApiGateConnectManager.getGateConnectObject(this.getGateId());
			if(gateConnObject!=null && gateConnObject.getState()==1 && gateConnObject.getConnTimes()<6)
			{
				gateConnObject.addConnectTimes();
				this.createChannel();
			}
			else
			{
				if(gateConnObject==null)
				{
					logger.info("不能重连gateConnObject==null...this.getGateId()："+this.getGateId()+"\n");
				}
				else
				{
					logger.info("重连超过次数...this.getGateId()："+this.getGateId()+"\n");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx) {
		//super.channelConnected(ctx);
		//把自己注册到服务器
		Channel channel = ctx.channel();
		
		this.regiest(channel);
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		
		
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx,
			Object obj) {
		
		//System.out.println("client receive...");
		
		Channel channel = ctx.channel();
		GateToGameMessage message = (GateToGameMessage)obj;
		
		if(isStop){
			logger.error("服务器已经停止，不再处理消息！忽略的消息:【" + message + "】");
			return;
		}
		
		//在这里处理接收到的消息
		GateMessageHandler.handleMessage(channel, message);
		
		
	}
	
	/**
	 * 连接服务器
	 * @author haojian
	 * @date 2014-10-15 上午9:54:05 
	 * @return void
	 */
	public Channel createChannel() throws Exception{

		//启动netty5客户端
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		bootstrap = new Bootstrap();
		bootstrap.group(workerGroup);
		bootstrap.channel(NioSocketChannel.class);
		
		bootstrap.option(ChannelOption.TCP_NODELAY, true);
	    bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
	    bootstrap.option(ChannelOption.SO_REUSEADDR, true);
		
		bootstrap.handler(new ClientChannelInitializer(this));
		// Start the client.
		ChannelFuture future = bootstrap.connect(new InetSocketAddress(serverConfig.getIp(), serverConfig.getPort()))
				;//.sync();
		
	
		
		logger.info("开始连接！服务器【" + serverConfig.getIp() + ":" + serverConfig.getPort() + "】");
		
		future.addListener(
				new ChannelFutureListener(){
					@Override
					public void operationComplete(ChannelFuture future)
							throws Exception {
						if(future.isSuccess()){
							
							WanmaConstants.GATE_TO_CHANNEL_INFO.put(getGateId(), future.channel());
							
							logger.info("连接成功！服务器【" + serverConfig.getIp() + ":" + serverConfig.getPort() + "】");
						}else{
							String gateId = getGateId();
							GateConnectObject gateConnObject = ApiGateConnectManager.getGateConnectObject(gateId);
							if(null != gateConnObject && gateConnObject.getConnTimes() == 6){
								ApiGateConnectManager.removeGateConnectObject(gateId);
							}
							
							logger.info("连接失败！服务器【" + serverConfig.getIp() + ":" + serverConfig.getPort() + "】");
						}
						
					}
				}
			);
		
		Channel gateChannel=null;
		if(future.isSuccess()){
			gateChannel = future.channel();
		}
		
		
		
		return gateChannel;
        
		
	}
//1
}

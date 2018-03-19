package com.appCore.util;

import io.netty.channel.Channel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class ChannelUtil {
	
	/**
	 * 通过channel获取  "ip:端口"
	 * @author hao
	 * Mar 18, 2014 8:26:18 PM
	 * @param channel
	 * @return
	 */
	public static String getIpAndPortByChannel(Channel channel){
		//  /211.144.68.31:8008
		//  gs1.zsg.ecngame.com/114.141.131.229:9801
		
		String ipAndPort = "";
		
		SocketAddress socketAddress = channel.remoteAddress(); //可强转为 InetSocketAddress
		if(socketAddress!=null){
			String remoteAddress = channel.remoteAddress().toString();
			String[] ss = remoteAddress.split("/");
			if(ss[0].trim().equals("")){//使用ip
				ipAndPort = ss[1];
			}else{//使用域名
				String domainName = ss[0];
				String port = ss[1].split(":")[1];
				ipAndPort = domainName + ":" + port;
			}
			
		}else{
			System.out.println("socketAddress is null...");
		}
		
		return ipAndPort;
	}

	
	/**
	 * 通过channel获取  "ip:端口"
	 * @author haojian
	 * @date 2014-9-23 下午5:33:09 
	 * @param channel
	 * @return
	 * @return String
	 */
	public static String getIpAndPortByChannelNew(Channel channel){
		//  /211.144.68.31:8008
		//  gs1.zsg.ecngame.com/114.141.131.229:9801
		
		String ipAndPort = "";
		
		InetSocketAddress socketAddress = (InetSocketAddress)channel.remoteAddress(); //可强转为 InetSocketAddress
		if(socketAddress!=null){
			String ip = socketAddress.getAddress().getHostAddress();
			//String domainName = socketAddress.getHostName();
			int port = socketAddress.getPort();
			ipAndPort = ip + ":" + port;
		}else{
			System.out.println("socketAddress is null...");
		}
		
		return ipAndPort;
	}
	
	
}

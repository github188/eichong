package com.epcentre.util;

import io.netty.channel.Channel;

public class IPUtil {
	
	public static String getNameByChannel(Channel channel) {
		
		String ip = channel.remoteAddress().toString();
		ip = ip.substring(1, ip.indexOf(":"));
		String name = ip;

		name = "【" + name + "】";
		return name;
		
	}

}

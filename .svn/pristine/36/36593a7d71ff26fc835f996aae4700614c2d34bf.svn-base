package com.appCore.core.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * 关闭服务器的线程，需要关闭游戏服务器的时候，
 * 需要通过socket客户端发送关闭命令，只允许本机发送关闭命令
 * @author haojian
 * Mar 27, 2013 4:27:05 PM
 */
public class StopServerThread extends Thread {
	
	private static final Logger logger = Logger.getLogger(StopServerThread.class);
	
	private int shutdownPort;
	
	public StopServerThread(int shutdownPort){
		this.shutdownPort = shutdownPort;
	}
	
	@Override
	public void run(){
		try {
			ServerSocket serverSocket = new ServerSocket(shutdownPort);
			while(true){
				Socket socket = serverSocket.accept();
				String inetAddress = socket.getInetAddress().toString();
				
				if(inetAddress.indexOf("127.0.0.1")!=-1||inetAddress.indexOf("localhost")!=-1){
					InputStream is = socket.getInputStream();
					OutputStream os = socket.getOutputStream();
					byte[] bb = new byte[1024];
					int len = is.read(bb);
					String msg = new String(bb,0,len);
					if("shutdown".equals(msg)){
						byte[] bb2 = "正在关闭服务器,可能需要几秒钟，请等待......".getBytes();
						os.write(bb2);
						os.flush();
						
						System.exit(0);
					}else{
						byte[] bb2 = "非法的命令".getBytes();
						os.write(bb2);
						os.flush();
					}
					if(os!=null)os.close();
					if(is!=null)is.close();
					
				}else{
					logger.error("来自外网的非法命令："+inetAddress);
				}
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.epcentre.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 收消息，解码   
 * 
 * 消息结构：2字节协议头+ 2字节长度 (小端)+ 1字节原因+2字节命令类型  + byte[] 数据内容
 */


public class AnalyzeNettyDecoder extends ByteToMessageDecoder {

	private static final Logger logger = LoggerFactory.getLogger(AnalyzeNettyDecoder.class);

	
	
	@Override
	protected void decode(ChannelHandlerContext channelHandlerContext,
			ByteBuf byteBuf, List<Object> list) throws Exception {
		
		int readableBytes= byteBuf.readableBytes();
		if(readableBytes<7)//如果长度小于长度,不读
		{
			logger.info("decode dataCenter1 readableBytes:"+readableBytes+"\n");
			return;
		}
		
		int pos= byteBuf.bytesBefore(AnalyzeConstant.HEAD_FLAG1);//找到的位置
		int pos1= byteBuf.bytesBefore(AnalyzeConstant.HEAD_FLAG2);//找到的位置
		int discardLen=0;
		if(pos < 0 || pos1<0 || (pos1-pos)!=1)//没找到，全部读掉
		{
			discardLen = readableBytes;
			logger.info("decode not find flag header 0x4543,pos:"+pos+"readableBytes:"+readableBytes+",discardLen"+discardLen+"\n");

		}
		if(pos>0)
		{
			discardLen = pos;
			logger.info("decode find flag header 0x4543 at pos:"+pos +",discardLen"+discardLen+"\n");
		}
		if(discardLen>0)
		{
			byte[] dicardBytes= new byte[discardLen];
			byteBuf.readBytes(discardLen);//
			logger.info("discard msg:"+WmIce104Util.ConvertHex(dicardBytes, 4)+"\n");
			if(discardLen == readableBytes)
			{
				//没有数据可对，还回
				return;
			}
		}
		
		readableBytes= byteBuf.readableBytes();
		if(readableBytes<7)
		{
			logger.info("decode dataCenter1 readableBytes:"+readableBytes+"\n");
			return;
		}
		
		//1、先标记读索引（必须）
		byteBuf.markReaderIndex();
		
		short protocolhead = byteBuf.readShort();//读取协议头
		
		int lengL = byteBuf.readByte();
		int lengH = byteBuf.readByte();
	    
		int msg_len = lengL+lengH*0x100;
		
		int remain_len = byteBuf.readableBytes();

		if(remain_len<msg_len )
		{
			logger.info("remain_len:"+remain_len+"\n");
				
			byteBuf.resetReaderIndex();
			return ;
		}
		
		byte Msg[]= null;
		Msg= new byte[msg_len];
    	byteBuf.readBytes(Msg);
    
    	AnalyzeMessage message = new AnalyzeMessage();
    			
    	message.setLength(msg_len);
    	message.setProtocolHead(protocolhead);

    	message.setBytes(Msg);
    			
    	list.add(message);
		
	}

	

}

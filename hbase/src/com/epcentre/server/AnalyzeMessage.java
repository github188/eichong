package com.epcentre.server;




/**
 * 游戏服发给网关服的消息结构
 * +------------+-------------+----------------------+   
 * |   head  	| leng   	  |	  body (byte array)  |
 * +------------+-------------+----------------------+     
 * |   2 byte 	|   2 byte    |   n byte		     |
 * +------------+-------------+----------------------+
 * length 记录的是后续所有数据的总长度

 */
public class AnalyzeMessage {
	
	/**协议头*/
	private short protocolHead;
	/**消息长度(字节数)*/
	private int length;
	
	/**消息体*/
	private byte[] bytes;
	
	public AnalyzeMessage(){	
		
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public short getProtocolHead() {
		return protocolHead;
	}

	public void setProtocolHead(short protocolHead) {
		this.protocolHead = protocolHead;
	}


	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	@Override
	public String toString(){
		return  "[" + protocolHead + "]"+" len="+this.getProtocolHead()+" length="+this.getLength()+" Bytes="+this.getBytes(); 
	}

}

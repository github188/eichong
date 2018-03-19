package com.appCore.netty.message;


/**
 * 客户端发给服务端的 消息结构：4字节长度 + 2字节协议号 + ByteBuffer数据
 * +------------+-------------+------------------------+     
 * |   length  	| protocolNum |	 	body (byte array)  |
 * +------------+-------------+------------------------+       
 * |   4 byte 	|   2 byte    | 		n byte		   |
 * +------------+-------------+------------------------+ 
 * length 记录的是 2字节协议号 + ByteBuffer数据 一共占用的字节数
 * @author haojian
 * Apr 1, 2013 10:06:13 AM
 */
public class Message {
	
	/**消息体长度(字节数)*/
	private int length;
	/**协议号*/
	private short protocolNum;
	/**是否压缩*/
	private byte isCompress;
	/**协议号中的小类型*/
	private int MesType;
	/**8个暂时不使用的消息体*/
	private byte[] bytesTo8;
	/**消息体*/
	private byte[] bytes;
	
	public Message(){	
		
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
	
	public short getProtocolNum() {
		return protocolNum;
	}

	public void setProtocolNum(short protocolNum) {
		this.protocolNum = protocolNum;
	}
	
	public byte getIsCompress() {
		return isCompress;
	}

	public void setIsCompress(byte isCompress) {
		this.isCompress = isCompress;
	}
	
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public int getMesType() {
		return MesType;
	}

	public void setMesType(int mesType) {
		MesType = mesType;
	}

	public byte[] getBytesTo8() {
		return bytesTo8;
	}

	public void setBytesTo8(byte[] bytesTo8) {
		this.bytesTo8 = bytesTo8;
	}

	public String toString(){
		return  "[" + protocolNum + "]"+" len="+this.getLength()+" bytes.length=" + bytes.length; 
	}

}

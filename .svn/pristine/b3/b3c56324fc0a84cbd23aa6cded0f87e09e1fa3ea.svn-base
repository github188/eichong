package com.netCore.netty.buffer;

import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * 
 * @author haojian
 * Oct 11, 2011
 * 用此类创建动态扩充的缓冲区
 */
public class DynamicByteBuffer{
	
	
    static final int DEFAULT_INITIAL_CAPACITY = 256;
    
    static final int MINIMUM_CAPACITY = 16;

    static final int MAXIMUM_CAPACITY = 1 << 30;
    
    static final int MAXIMUM_LOOP_TIMES = 20;
    
	private ByteBuffer byteBuffer;
	
	private DynamicByteBuffer(){
	}
	
	public DynamicByteBuffer(ByteBuffer byteBuffer){
		this.byteBuffer = byteBuffer;
	}
	
	public static DynamicByteBuffer allocate(){
		DynamicByteBuffer buffer = new DynamicByteBuffer();
		buffer.byteBuffer = ByteBuffer.allocate(DEFAULT_INITIAL_CAPACITY);
		return buffer;
	}
	
	public static DynamicByteBuffer allocate(int capacity){
		DynamicByteBuffer buffer = new DynamicByteBuffer();
		if(capacity<MINIMUM_CAPACITY){
			capacity = MINIMUM_CAPACITY;
		}
		buffer.byteBuffer = ByteBuffer.allocate(capacity);
		return buffer;
	}
	
	public byte get() {
		return byteBuffer.get();
	}
	
	public boolean getBoolean() {
		byte b = byteBuffer.get();
		boolean bool = b==1?true:false;
		return bool;
	}
	
	public char getChar() {
		return byteBuffer.getChar();
	}

	public double getDouble() {
		return byteBuffer.getDouble();
	}

	public float getFloat() {		
		return byteBuffer.getFloat();
	}

	public int getInt() {		
		return byteBuffer.getInt();
	}

	public long getLong() {		
		return byteBuffer.getLong();
	}

	public short getShort() {	
		return byteBuffer.getShort();
	}
	
	//TODO
	public ByteBuffer put(byte b) {	
		int len = byteBuffer.remaining();
		if(len<1){
			byteBuffer.flip();
			byte[] bb = new byte[byteBuffer.limit()];
			byteBuffer.get(bb);
			byteBuffer = ByteBuffer.allocate(byteBuffer.capacity()<<1);
			byteBuffer.put(bb);
		}
		return byteBuffer.put(b);
	}
	
	//TODO
	public ByteBuffer putBoolean(boolean bool) {
		byte b = (byte)(bool?1:0);
		return this.put(b);
	}

	public ByteBuffer putChar(char value) {
		int len = byteBuffer.remaining();
		if(len<1){
			byteBuffer.flip();
			byte[] bb = new byte[byteBuffer.limit()];
			byteBuffer.get(bb);
			byteBuffer = ByteBuffer.allocate(byteBuffer.capacity()<<1);
			byteBuffer.put(bb);
		}
		return byteBuffer.putChar(value);
	}

	public ByteBuffer putDouble(double value) {	
		int len = byteBuffer.remaining();
		if(len<8){
			byteBuffer.flip();
			byte[] bb = new byte[byteBuffer.limit()];
			byteBuffer.get(bb);
			byteBuffer = ByteBuffer.allocate(byteBuffer.capacity()<<1);
			byteBuffer.put(bb);
		}
		return byteBuffer.putDouble(value);
	}

	public ByteBuffer putFloat(float value) {
		int len = byteBuffer.remaining();
		if(len<4){
			byteBuffer.flip();
			byte[] bb = new byte[byteBuffer.limit()];
			byteBuffer.get(bb);
			byteBuffer = ByteBuffer.allocate(byteBuffer.capacity()<<1);
			byteBuffer.put(bb);
		}
		return byteBuffer.putFloat(value);
	}
	
	public ByteBuffer putInt(int value) {
		int len = byteBuffer.remaining();
		if(len<4){
			byteBuffer.flip();
			byte[] bb = new byte[byteBuffer.limit()];
			byteBuffer.get(bb);
			byteBuffer = ByteBuffer.allocate(byteBuffer.capacity()<<1);
			byteBuffer.put(bb);
		}
		return byteBuffer.putInt(value);
	}
	
	public ByteBuffer putLong(long value) {	
		int len = byteBuffer.remaining();
		if(len<8){
			byteBuffer.flip();
			byte[] bb = new byte[byteBuffer.limit()];
			byteBuffer.get(bb);
			byteBuffer = ByteBuffer.allocate(byteBuffer.capacity()<<1);
			byteBuffer.put(bb);
		}
		return byteBuffer.putLong(value);
	}
	
	public ByteBuffer putShort(short value) {	
		int len = byteBuffer.remaining();
		if(len<2){
			byteBuffer.flip();
			byte[] bb = new byte[byteBuffer.limit()];
			byteBuffer.get(bb);
			byteBuffer = ByteBuffer.allocate(byteBuffer.capacity()<<1);
			byteBuffer.put(bb);
		}
		return byteBuffer.putShort(value);
	}

    public final int position() {	
    	return byteBuffer.position();	
    }
    
	public final Buffer position(int newPosition) {
		return byteBuffer.position(newPosition);
	}
	
    public final int capacity() {
    	return byteBuffer.capacity();	
    }
    
    public final int limit() { 	
    	return byteBuffer.limit();	
    }
    
    public final Buffer flip() {
    	return byteBuffer.flip();	
    }
    
    public final ByteBuffer put(byte[] src) {	
		int srcLen = src.length;
		int len = byteBuffer.remaining();
		if(len<srcLen){
			int position = byteBuffer.position();
			int capacity = byteBuffer.capacity();
			//TODO
			for(int i=0;i<MAXIMUM_LOOP_TIMES;i++){
				if(capacity-position<srcLen){
					capacity = capacity << 1;
				}else{
					break;
				}
			}
			byteBuffer.flip();
			byte[] bb = new byte[byteBuffer.limit()];
			byteBuffer.get(bb);
			byteBuffer = ByteBuffer.allocate(capacity);
			byteBuffer.put(bb);
			
		}
		return byteBuffer.put(src);
	}
    
	public ByteBuffer get(byte[] dst) {
		return byteBuffer.get(dst);
	}
	
	
	
	/**
	 * 将字符串写入到缓冲区，带两个字节的长度
	 * @author haojian
	 * Apr 1, 2013 1:19:55 PM
	 * @param str
	 */
	public void putString(String str){
		byte[] bb = null;
		try {
			bb = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		short len = (short)bb.length;
		putShort(len);
		put(bb);
	}
	
	
	/**
	 * 从缓冲区中读取一个字符串，字符串前面有两个字节的长度
	 * @author haojian
	 * Apr 1, 2013 1:20:26 PM
	 * @param byteBuffer
	 * @return
	 */
	public String getString(ByteBuffer byteBuffer){
		
		short len = byteBuffer.getShort();
		byte[] bb = new byte[len];
		byteBuffer.get(bb);
		String str = null;
		try {
			str = new String(bb,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	
	/**
	 * 将缓冲区中的数据转换成字节数组
	 * @author haojian
	 * Apr 1, 2013 1:17:21 PM
	 * @return
	 */
	public byte[] getBytes(){
		byteBuffer.flip();
		byte[] bb = new byte[byteBuffer.limit()];
		byteBuffer.get(bb);	
		return bb;
	}
	
	public String toString(){
		return byteBuffer.toString();
	}
	
}

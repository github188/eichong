package com.bluemobi.protocol;

import com.appCore.netty.buffer.DynamicByteBuffer;
import com.bluemobi.model.User;

public class UserProtocol {
	
	/**
	 * 通知gate T 用户下线
	 * @author haojian
	 * @date 2014-10-20 下午5:14:44 
	 * @param user
	 * @return
	 * @return byte[]
	 */
	public static byte[] forceUserOffline(User user){
		
		DynamicByteBuffer byteBuffer = DynamicByteBuffer.allocate();
		
		byteBuffer.putLong(user.getId());
		
		return byteBuffer.getBytes();
	}

}

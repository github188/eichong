package com.bluemobi.protocol;

import com.appCore.netty.buffer.DynamicByteBuffer;
import com.bluemobi.model.Player;

public class SystemProtocol {
    /**
     * 世界聊天消息
     *
     * @param player
     * @param msg
     * @author haojian
     * Jun 18, 2013 5:30:41 PM
     */
    public static byte[] chatMsg(Player player, String msg) {
        DynamicByteBuffer byteBuffer = DynamicByteBuffer.allocate();
        byteBuffer.putString(player.getName());//名称
        byteBuffer.putShort(player.getVipLevel().shortValue());//vip等级
        byteBuffer.putString(msg);//聊天消息
        return byteBuffer.getBytes();
    }
}

/*package com.bluemobi.service;

import io.netty.channel.Channel;

import java.nio.ByteBuffer;

import com.appCore.util.ByteUtil;
import com.bluemobi.cache.GameWorld;
import com.bluemobi.constant.protocol.S2C;
import com.bluemobi.model.Player;
import com.bluemobi.protocol.UtilProtocol;
import com.bluemobi.sender.MessageSender;
import com.bluemobi.sender.SystemSender;
import com.bluemobi.util.GameObjectUtil;
import com.wanma.app.util.DateUtil;

public class SystemService {
    *//**
     * 系统广播
     *
     * @param channel
     * @param byteBuffer
     * @author haojian
     * Jun 18, 2013 5:27:52 PM
     *//*
    public static void systemNotice(Channel channel, ByteBuffer byteBuffer) {
        System.out.println("系统广播...");
        String msg = ByteUtil.getString(byteBuffer);
        SystemSender.sendSystemNotice(msg);
    }

    *//**
     * 世界聊天
     *
     * @param channel
     * @param byteBuffer
     * @author haojian
     * Jun 18, 2013 5:31:57 PM
     *//*
    public static void chat(Channel channel, ByteBuffer byteBuffer) {
        Player player = GameObjectUtil.getPlayerByChannel(channel);
        String msg = ByteUtil.getString(byteBuffer);
        //5、广播消息
//        byte[] bb = SystemProtocol.chactMsg(player, msg);

//        MessageSender.sendWorldMessage(Protocol.P_906, bb);

    }

    *//**
     * 给客户端发送系统时间
     *//*
    public static void sendSystemTime(Player player) {
        int currentTime = DateUtil.currentTimeSecond();
        byte[] bb = ByteUtil.intToBytes(currentTime);
        MessageSender.gameSendToPlayer(player, S2C.P_10003_SYSTEM_TIME, bb);
    }

    *//**
     * 给客户端发送cfg版本号
     *//*
    public static void sendCfgVersion(Player player) {
        String version = GameWorld.getVersion();
        byte[] bb = UtilProtocol.stringToBytes(version);
        MessageSender.gameSendToPlayer(player, S2C.P_10004_CFG_VERSION, bb);
    }
}
*/
/*package com.bluemobi.sender;

import io.netty.channel.Channel;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.bluemobi.cache.GameWorld;
import com.bluemobi.constant.protocol.S2C;
import com.bluemobi.model.Player;
import com.bluemobi.protocol.UtilProtocol;
import com.bluemobi.util.GameObjectUtil;

public class SystemSender {

    *//**
     * 发送系统公告
     *
     * @param msg
     * @author haojian
     * Jun 17, 2013 3:37:35 PM
     *//*
    public static void sendSystemNotice(String msg) {
        byte[] bb = UtilProtocol.stringToBytes(msg);
        for (Player player:GameWorld.getPlayerMapKeyChannel().values()) {
        	MessageSender.gameSendToPlayer(player, S2C.P_10007_BROADCAST, bb);
		}
//        MessageSender.sendMessage(GameWorld.getPlayerMapKeyChannel().values(), S2C.P_10007_BROADCAST,  bb);
    }
    

    *//**
     * /**
     * 发送系统警告消息
     *
     * @param channel
     * @param msg
     * @author haojian
     * Apr 24, 2013 3:17:48 PM
     *//*
    public static void sendWarningMsg(Player player, String msg) {
        byte[] bb = UtilProtocol.stringToBytes(msg);
        MessageSender.gameSendToPlayer(player, S2C.P_10001_SYSTEM_WARNING, bb);
    }

    *//**
     * 发送提示消息
     *//*
    public static void sendTips(Channel channel, String msg) {
        byte[] bb = UtilProtocol.stringToBytes(msg);
        MessageSender.gameSendToGate(channel, S2C.P_10002_SYSTEM_TIPS, bb);
    }

    *//**
     * 发送世界提示消息（测试用）
     *
     * @param channel
     * @param msg
     * @author haojian
     * May 3, 2013 6:28:05 PM
     *//*
    public static void sendWorldMsg(Channel channel, String msg) {
        String senderName = "";
        Player player = GameObjectUtil.getPlayerByChannel(channel);
        if (player == null) {
            senderName = "系统";
        } else {
            senderName = player.getName();
        }
        msg = "[" + senderName + "] 说：" + msg;
        Collection<Player> set = GameWorld.getPlayerMapKeyChannel().values();
        for (Player p : set) {
            SystemSender.sendWarningMsg(p, msg);
        }
    }
}
*/
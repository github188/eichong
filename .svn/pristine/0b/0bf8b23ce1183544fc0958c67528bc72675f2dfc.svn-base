package com.bluemobi.util;

import io.netty.channel.Channel;

import com.bluemobi.cache.GameWorld;
import com.bluemobi.model.Player;
import com.bluemobi.model.User;
import com.wanma.app.util.DateUtil;

public class GameObjectUtil {
	
	/**
	 * 通过channel获取player
	 * @author haojian
	 * Apr 11, 2013 2:22:41 PM
	 * @param channel
	 * @return
	 */
	public static Player getPlayerById(int playerId){
		Player player = GameWorld.getPlayerMapKeyId().get(playerId);
		
		if(player!=null){
			//设置最后访问时间
			player.setLastAccessTime(DateUtil.currentTimeSecond());
		}
		
		return player;
	}
	
	/**
	 * 通过userId获取player
	 * @author haojian
	 * @date 2014-9-27 下午5:04:09 
	 * @param userId
	 * @return
	 * @return Player
	 */
	public static Player getPlayerByUserId(int userId){
		Player player = GameWorld.getPlayerMapKeyUserId().get(userId);
		
		if(player!=null){
			//设置最后访问时间
			player.setLastAccessTime(DateUtil.currentTimeSecond());
		}
		
		return player;
	}
	
	/**
	 * 通过channel获取user
	 * @author haojian
	 * Apr 11, 2013 2:22:53 PM
	 * @param channel
	 * @return
	 */
	public static User getUserByUserId(int userId){
		User user = GameWorld.getUserMapKeyUserId().get(userId);
		return user;
	}
	
	
	/**
	 * 通过playerId获取channel
	 * @author haojian
	 * Apr 19, 2013 10:11:47 AM
	 * @param playerId
	 * @return
	 */
	public static Channel getChannelById(Integer playerId){
		Player player = GameWorld.getPlayerMapKeyId().get(playerId);
		Channel channel = null;
		if(player!=null){
			channel = player.getChannel();
		}
		return channel;
	}
	

    /**
     * 通过channel获取player
     *
     * @param channel
     * @author haojian
     * Apr 11, 2013 2:22:41 PM
     */
    public static Player getPlayerByChannel(Channel channel) {
        Player player = GameWorld.getPlayerMapKeyChannel().get(channel);

        if (player != null) {
            //设置最后访问时间
            player.setLastAccessTime(DateUtil.currentTimeSecond());
        }

        return player;
    }

    /**
     * 通过channel获取user
     *
     * @param channel
     * @author haojian
     * Apr 11, 2013 2:22:53 PM
     */
    public static User getUserByChannel(Channel channel) {
        User user = GameWorld.getUserMapKeyChannel().get(channel);
        return user;
    }

    public static boolean isOnline(final int playerId) {
        Player player = getPlayerById(playerId);
        return player != null;
    }

    /**
     * 通过playerId获取 在线的 player
     *
     * @param playerId
     * @author haojian
     * Apr 19, 2013 10:08:11 AM
     */
    public static Player getPlayerById(Integer playerId) {

        Player player = GameWorld.getPlayerMapKeyId().get(playerId);

        if (player != null) {
            //设置最后访问时间
            player.setLastAccessTime(DateUtil.currentTimeSecond());
        }

        return player;
    }

}

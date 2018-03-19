package com.bluemobi.cache;

import io.netty.channel.Channel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.bluemobi.model.Player;
import com.bluemobi.model.User;

/**
 * 服务世界
 *
 * @author haojian
 *         Mar 29, 2013 3:52:38 PM
 */
public class GameWorld {

    private static final Logger logger = Logger.getLogger(GameWorld.class);


    /**网关map  key:网关服id,  value:channel */
	public static Map<Integer,Channel> gateChannelMap = new ConcurrentHashMap<Integer,Channel>();
	
	/**公共服的channel*/
	public static Channel publicChannel = null;
	/**key:用户id,   value:user */
	private static Map<Integer,User>  userMapKeyUserId =  new ConcurrentHashMap<Integer,User>();
	
	/**
     * key:用户id,   value:player
     */
    private static Map<Integer, Player> playerMapKeyId = new ConcurrentHashMap<Integer, Player>();
	
    /**key:用户id,   value:player */
	private static Map<Integer,Player>  playerMapKeyUserId =  new ConcurrentHashMap<Integer,Player>();
    /**
     * key:channel,  value:user
     */
    private static Map<Channel, User> userMapKeyChannel = new ConcurrentHashMap<Channel, User>();
    /**
     * key:channel, value:用户对象
     */
    private static Map<Channel, Player> playerMapKeyChannel = new ConcurrentHashMap<Channel, Player>();
    
    /**
     * key:用户名称,  value:player
     */
    private static Map<String, Player> playerMapKeyName = new ConcurrentHashMap<String, Player>();
    /**
     * 所有用户信息
     */
    private static Map<Integer, Player> allPlayerMap = new ConcurrentHashMap<Integer, Player>();
    
	

    static {
    	//1、开服时候，根据配置初始化机器人
//    	VirtualRobotService.createRobots();
        //初始化加载服务版本号
//        GmBackService.initVersion();
    }

    public static synchronized void addGateToGameWorld(int gateId, Channel channel){
		if(gateId!=0&&channel!=null)gateChannelMap.put(gateId, channel);
		logger.info("保存网关【" + gateId + "】的channel");
	}
    
    public static synchronized void removeGateFromGameWorld(Channel channel){
		if(channel==null){
			return;
		}
		for(int gateId : gateChannelMap.keySet()){
			Channel c = gateChannelMap.get(gateId);
			if(c.equals(channel)){
				gateChannelMap.remove(gateId);
				logger.info("移除网关【" + gateId + "】的channel");
			}
		}
		
	}
    
    public static String getVersion() {
        return version;
    }

    public static void setVersion(String version) {
        GameWorld.version = version;
    }

    private static String version;

    /**
     * 将user添加到服务世界
     *
     * @param channel
     * @param user
     * @author haojian
     * Apr 11, 2013 2:18:03 PM
     */
    public synchronized static void addUserToGameWorld(Channel channel, User user) {
        if (channel != null) userMapKeyChannel.put(channel, user);
    }

    /**
     * 将user从服务世界删除
     *
     * @param channel
     * @param user
     * @author haojian
     * Apr 11, 2013 2:18:12 PM
     */
    public synchronized static void removeUserFromGameWorld(Channel channel, User user) {
        if (channel != null) userMapKeyChannel.remove(channel);
    }
    
    public synchronized static void removeUserFromGameWorld(User user){
		
		if(user!=null)userMapKeyUserId.remove(user.getId());
		
	}
    
    public synchronized static void addPublicToGameWorld(Channel channel){
		
		if(channel!=null)publicChannel = channel;
		
	}
	
	public synchronized static void removePublicFromGameWorld(){
		
		publicChannel = null;
		
	}
	
	public synchronized static void addUserToGameWorld(User user){
		
		if(user!=null)userMapKeyUserId.put(user.getId(), user);
		
	}
	

	/**
	 * 将用户添加到服务世界
	 * 让player跟channel没有关联
	 * @author haojian
	 * Apr 8, 2013 5:36:10 PM
	 * @param channel
	 * @param player
	 */
	public synchronized static void addPlayerToGameWorld(Player player){
		
		playerMapKeyId.put(player.getId(), player);
		playerMapKeyUserId.put(player.getUserId(), player);
		playerMapKeyName.put(player.getName(), player);
		
		
		//将用户放到缓存
		allPlayerMap.put(player.getId(), player);
		
		logger.info("用户 " + player.getName() + "进入服务");
		
	}
	
    /**
     * 将用户添加到服务世界
     * 让player跟channel没有关联
     *
     * @param channel
     * @param player
     * @author haojian
     * Apr 8, 2013 5:36:10 PM
     */
    public synchronized static void addPlayerToGameWorld(Channel channel, Player player) {

        if (player == null || channel == null) {
            logger.error("将用户加入到服务世界的时候出错！player=" + player + ",channel=" + channel);
            throw new RuntimeException("将用户加入到服务世界的时候出错！");
        }

        if (playerMapKeyChannel.containsKey(channel)) {
            //SystemSender.sendErrorMsg(channel, "请先离线，然后再登陆服务！");
            logger.error("channel 已经存在！");
            throw new RuntimeException("channel 已经存在！");
        }
        if (playerMapKeyId.containsKey(player.getId())) {
            //SystemSender.sendErrorMsg(channel, "请先离线，然后再登陆服务！");
            logger.error("playerId 【" + player.getName() + "】 已经存在！");
            throw new RuntimeException("playerId 已经存在！");
        }

        playerMapKeyChannel.put(channel, player);
        player.setChannel(channel);
        playerMapKeyId.put(player.getId(), player);
        playerMapKeyName.put(player.getName(), player);
        //将用户放到缓存
        allPlayerMap.put(player.getId(), player);

        logger.info("用户" + player.getName() + "进入服务");

    }

    /**
	 * 从服务世界删除用户
	 * @author haojian
	 * Apr 8, 2013 5:36:23 PM
	 * @param channel
	 * @param player
	 */
	public synchronized static void removePlayerFromGameWorld(Player player){
		
		if(player!=null)player.setChannel(null);
		if(player!=null)playerMapKeyId.remove(player.getId());
		if(player!=null)playerMapKeyUserId.remove(player.getUserId());
		if(player!=null)playerMapKeyName.remove(player.getName());
		
	}
    
    /**
     * 从服务世界删除用户
     *
     * @param channel
     * @param player
     * @author haojian
     * Apr 8, 2013 5:36:23 PM
     */
    public synchronized static void removePlayerFromGameWorld(Channel channel, Player player) {

        if (player == null || channel == null) {
            logger.error("将用户从服务世界删除的时候出错！player=" + player + ",channel=" + channel);
            throw new RuntimeException("将用户从服务世界删除的时候出错！");
        }

        playerMapKeyChannel.remove(channel);
        player.setChannel(null);
        playerMapKeyId.remove(player.getId());
        playerMapKeyName.remove(player.getName());

        logger.info("用户 " + player.getName() +"离开服务");

    }

    public static Map<Channel, User> getUserMapKeyChannel() {
        return userMapKeyChannel;
    }

    public static Map<Channel, Player> getPlayerMapKeyChannel() {
        return playerMapKeyChannel;
    }

    public static Map<Integer, Player> getPlayerMapKeyId() {
        return playerMapKeyId;
    }

    public static Map<String, Player> getPlayerMapKeyName() {
        return playerMapKeyName;
    }

    public static Map<Integer, Player> getAllPlayerMap() {
        return allPlayerMap;
    }
    

	public static Map<Integer, Player> getPlayerMapKeyUserId() {
		return playerMapKeyUserId;
	}

	public static void setPlayerMapKeyUserId(Map<Integer, Player> playerMapKeyUserId) {
		GameWorld.playerMapKeyUserId = playerMapKeyUserId;
	}

	public static void setPlayerMapKeyId(Map<Integer, Player> playerMapKeyId) {
		GameWorld.playerMapKeyId = playerMapKeyId;
	}

	public static void setUserMapKeyChannel(Map<Channel, User> userMapKeyChannel) {
		GameWorld.userMapKeyChannel = userMapKeyChannel;
	}

	public static void setPlayerMapKeyChannel(
			Map<Channel, Player> playerMapKeyChannel) {
		GameWorld.playerMapKeyChannel = playerMapKeyChannel;
	}

	public static void setPlayerMapKeyName(Map<String, Player> playerMapKeyName) {
		GameWorld.playerMapKeyName = playerMapKeyName;
	}

	public static Map<Integer, User> getUserMapKeyUserId() {
		return userMapKeyUserId;
	}

	public static void setUserMapKeyUserId(Map<Integer, User> userMapKeyUserId) {
		GameWorld.userMapKeyUserId = userMapKeyUserId;
	}
	
	public static String ToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("在线角色数量=")
                .append(playerMapKeyChannel.size())
                .append(",")
                .append(playerMapKeyId.size())
                .append(",")
                .append(playerMapKeyName.size())
                .append(", 内存所有用户数量=")
                .append(allPlayerMap.size());
        return sb.toString();
    }
}

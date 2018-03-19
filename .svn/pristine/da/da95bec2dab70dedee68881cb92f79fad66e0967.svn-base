package com.bluemobi.service;

import io.netty.channel.Channel;

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;

/**
 * 用户相关服务
 *
 * @author haojian
 *         Apr 2, 2013 1:41:00 PM
 */
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);
    
    
    
    /**
	 * 玩家从网关服登陆成功
	 * @author haojian
	 * @date 2014-9-27 上午11:59:08 
	 * @param channel
	 * @param byteBuffer
	 * @return void
	 */
	/*public static void login(Channel channel, ByteBuffer byteBuffer){
		
		int serverId = byteBuffer.getInt();
		int userId = byteBuffer.getInt();
		boolean isReconnection = byteBuffer.get()==1?true:false;
		int gateId= byteBuffer.getInt();
		
//		int userId = byteBuffer.getInt();
//		boolean isReconnection = byteBuffer.get()==1?true:false;
//		int gateId = byteBuffer.getInt();
		
		UserService.loginSuccess(channel, isReconnection, userId, gateId);

	}*/
	
	/**
	 * 登陆 （登录成功后，跳转到这里）
	 * @author haojian
	 * @date 2014-10-15 上午11:34:30 
	 * @param channel
	 * @param isReconnection 是否是断线重连，若是断线重连，不在发送玩家数据；若不是，则按正常流程进入游戏
	 * @param userId 用户id
	 * @param gateId 用户所在的网关
	 * @return void
	 */
	/*private static void loginSuccess(Channel channel, boolean isReconnection, int userId, int gateId){
		//1、如果登陆校验成功 
		User user = DB.userService.findUserByUserId(userId);//.findUserByUserName(username, source);
		//设置用户的gate （重复？？）
		user.setGateId(gateId);
				
		//2、判断用户是否在线
		User oldUser = GameWorld.getUserMapKeyUserId().get(userId);
		if(oldUser!=null){
		//如果老用户跟新用户不在同一个网关，那么通知老用户所在的网关，T老用户下线
			if(oldUser.getGateId()!=gateId){
				byte[] bb = UserProtocol.forceUserOffline(oldUser);
				Channel oldChannel = GameWorld.gateChannelMap.get(oldUser.getGateId());
				MessageSender.gameSendToGate(oldChannel, Game2Gate.P_10103, bb);
				logger.info("用户【{}】被顶号...", new Object[]{userId} );
			}
		}
				
		//3、将用户保存到游戏世界
		GameWorld.addUserToGameWorld(user);
				

		//4、查找该用户是否创建了游戏角色
		Player player = DB.playerService.findPlayerByUserId(userId);
		if (player == null) {
			if (!isReconnection) {// 不是断线重连的时候，才发创建角色通知
				// 无角色，通知客户端创建角色
				MessageSender.gameSendToPlayer(channel, user.getId(), Game2Client.P_101,new byte[0]);
			}
			return;
		}

		//5、有用户和角色后，开始游戏
		player.setUser(user);
	}*/

    /**
     * 登陆
     *
     * @param channel
     * @param byteBuffer
     * @author haojian
     * Jun 24, 2013 9:35:27 AM
     */
//    public static void login(Channel channel, ByteBuffer byteBuffer) {
//        int source = byteBuffer.getInt();//玩家来源
//
//        if (source == LoginConstant.LOGIN_TYPE_TEST) {//PC版本
//            UserService.loginNoCheck(channel, byteBuffer, source);
//        } else { //默认走官网登录
//            logger.error("未识别的来源【{}】,走秀策官网登录...", new Object[]{source});
//            UserService.loginFromEcn(channel, byteBuffer, source);
//        }
//
//    }

    /**
     * 不校验密码的登陆
     */
    /*public static void loginNoCheck(Channel channel, ByteBuffer byteBuffer, int source) {
        boolean reconnect = byteBuffer.get() == 1;//是否重连
        String username = ByteUtil.getString(byteBuffer);//用户名
        String password = ByteUtil.getString(byteBuffer);//密码
        String token = ByteUtil.getString(byteBuffer);//token
        logger.info("用户通过免校验方式登陆, username=【{}】,token=【{}】，玩家来源【{}】", new Object[]{username, token, source});
        UserService.loginSuccess(channel, source, username, reconnect);
    }*/

    /**
     * 2、官网登录
     *
     * @param channel
     * @param byteBuffer
     * @author haojian
     * Jun 24, 2013 9:35:27 AM
     */
    /*private static void loginFromEcn(Channel channel, ByteBuffer byteBuffer, int source) {
        //是否重连
        //boolean isReConnection = byteBuffer.getInt()==1?true:false;

        String username = ByteUtil.getString(byteBuffer);//用户名
        String loginSig = ByteUtil.getString(byteBuffer);//要校验的loginSig

        //int source = byteBuffer.getInt();//玩家来源

        logger.info("用户从官网登录, username=【{}】,loginSig=【{}】,玩家来源【{}】", new Object[]{username, loginSig, source});

        //此处校验平台登陆是否有效
        boolean isSuccess = false;
        try {
            //错误码(1=成功)
            int status = EcnService.checkUserLogin(username, loginSig);

            if (status == 1) {
                isSuccess = true;
            } else {
                isSuccess = false;
                //通知客户端登陆错误码
                logger.info("用户【{}】从秀策平台登陆失败，错误码【{}】", new Object[]{username, status});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isSuccess) {
            logger.info("username = {} 登陆无效 ", username);
            return;
        }
        UserService.loginSuccess(channel, source, username, false);
    }*/

    /**
     * 登陆 （从平台登录成功后，跳转到这里）
     *
     * @param channel
     * @author haojian
     * Jun 24, 2013 9:43:16 AM
     */
    /*public static void loginSuccess(Channel channel, int source, String username, boolean isReconnect) {
        //如果登陆校验成功
        User user = null;
        user = DB.userService.findUserByUserName(username, source);
        //1、如果还没创建用户，给自动玩家创建用户
        if (user == null) {
            user = new User();
            user.setUsername(username);
            user.setCreateTime(DateUtil.currentTimeSecond());
            user.setCreateSource(source);
            user.setType(1);
            if (GameConfig.isNeedActivate) {
                user.setStatus(UserConstant.UNACTIVATED);
            } else {
                user.setStatus(UserConstant.NORMAL);
                user.setActivateTime(DateUtil.currentTimeSecond());
            }
            DB.userService.saveOrUpdate(user);
            logger.info("{}用户创建成功...", user.getUsername());
        }
        user.setLoginSource(source);
        //2、验证账号是否被封
        int userStatus = user.getStatus();
        if (userStatus == UserConstant.LONG_FREEZE) {//永久封号
//            SystemSender.sendWarningMsg(channel, "你的账号被永久封号了！");
            return;
        }
        if (userStatus == UserConstant.FREEZE) {//临时封号
            //判断时间是否到
            int nowTime = DateUtil.currentTimeSecond();
            if (nowTime <= user.getCanLoginTime()) {//临时封号时间未到
//                SystemSender.sendWarningMsg(channel, "你的账号被临时封号了！");
                return;
            }
            user.setStatus(UserConstant.NORMAL);
            DB.userService.saveOrUpdate(user);
        }
        //3、保存user到游戏世界
        GameWorld.addUserToGameWorld(channel, user);
        //4、如果游戏需要激活，检验激活码
        if (GameConfig.isNeedActivate) {
            //如果账号是未激活的
            if (user.getStatus() == UserConstant.UNACTIVATED) {
                //MessageSender.sendMessage(channel, Protocol.P_105);
                return;
            }
        }
        //5、查找该用户是否创建了游戏角色
        int userId = user.getId();
        Player player = DB.playerService.findPlayerByUserId(userId);
        if (player == null) {
            //无角色，通知客户端创建角色
            MessageSender.gameSendToGate(channel, S2C.P_101_CREATE_PLAYER,new byte[0]);
            return;
        }
        //6、判断用户是否在线
        Player oldPlayer = GameObjectUtil.getPlayerById(player.getId());
        if (oldPlayer != null) {
            //先将在线的玩家T下线
            Channel oldChannel = oldPlayer.getChannel();
            SystemSender.sendWarningMsg(oldPlayer, "主公，您的账号在其他设备上登陆了！");
            UserService.offLine(oldChannel);
            logger.info("玩家{}被顶号...", player.getName());
            //重新查询user 和 player,防止内存中数据跟数据库不一致
            user = DB.userService.findUserByUserName(username);
            GameWorld.addUserToGameWorld(channel, user);
            player = DB.playerService.findPlayerByUserId(userId);
        }
        player.setUser(user);
        //7、有用户和角色后，开始游戏
//        PlayerService.beginGame(isReconnect,player );
    }*/

    /**
     * 用户下线
     *
     * @param channel
     * @author haojian
     * Apr 11, 2013 2:20:41 PM
     */
    /*public static void offLine(Channel channel) {
        User user = GameObjectUtil.getUserByChannel(channel);
        Player player = GameObjectUtil.getPlayerByChannel(channel);
        if (user != null) {
            GameWorld.removeUserFromGameWorld(channel, user);
        }
        if (player != null) {
            //1、设置最后一次登出时间
            player.setLogoutTime(DateUtil.currentTimeSecond());

            //2、玩家登出，将玩家的channel从联盟下面移除

            //3、将玩家从GameWorld移除
            GameWorld.removePlayerFromGameWorld(channel, player);
            //4、保存玩家数据
            DB.playerService.saveOrUpdate(player);
            //记录玩家离线日志
            LogService.logWhenLogout(player);
        }

    }*/
    /**
     * 退出游戏操作
     * @author HeWeiwen
     * 2015-1-6
     * @param userId
     */
    /*public static void offLine(int userId){
		
		User user = GameObjectUtil.getUserByUserId(userId);
		
		Player player = GameObjectUtil.getPlayerByUserId(userId);
		
		if(user!=null){
			GameWorld.removeUserFromGameWorld(user);
		}
		
		if(player!=null){
			
			//1、设置最后一次登出时间
			player.setLogoutTime(DateUtil.currentTimeSecond());
			
			//2、玩家登出，将玩家的channel从联盟下面移除
			
			//3、将玩家从gameworld移除
			GameWorld.removePlayerFromGameWorld(player);
			
			//4、保存玩家数据
			DB.playerDao.saveOrUpdate(player);
			
			//5、记录玩家离线日志
			LogService.logWhenLogout(player);
			
			
			logger.info("玩家【{}】离开游戏",player.getName());
			
		}
		
	}*/

    /**
     * 激活账号
     *
     * @param channel
     * @param byteBuffer
     * @author haojian
     * Jun 27, 2013 9:32:44 AM
     */
    public static void activateUser(Channel channel, ByteBuffer byteBuffer) {/*

		User user = GameObjectUtil.getUserByChannel(channel);
		String activateCode = ByteUtil.getString(byteBuffer);
		
		int errorCode = ActivateService.activateAccount(user.getId(), activateCode);
		
		logger.info("用户【{}】激活游戏，激活码【{}】，激活结果【{}】", new Object[]{user.getUsername(), activateCode, errorCode} );
		
		//通知客户端激活状态
		byte[] bb = ByteUtil.intToBytes(errorCode);
		MessageSender.sendMessage(channel, Protocol.P_106, bb);
		
		if(errorCode==ActivateConstant.CODE_NOT_ACTIVATE_STATUS){
			//设置用户状态为已经激活
			user.setStatus(UserConstant.NORMAL);
			user.setActivateTime(TimeUtil.currentTimeSecond());
			DB.userService.saveOrUpdate(user);
			UserService.loginSuccess(channel, user.getLoginSource(), false, user.getUsername());
		}
	*/
    }
}

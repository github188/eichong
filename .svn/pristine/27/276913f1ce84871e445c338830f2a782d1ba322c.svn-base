package com.bluemobi;

import io.netty.channel.Channel;

import java.nio.ByteBuffer;

import org.apache.log4j.Logger;

import com.appCore.core.pool.ThreadPoolFactory;
import com.appCore.netty.message.GateToGameMessage;
import com.bluemobi.cache.GameWorld;
import com.bluemobi.constant.protocol.gateAndGame.Gate2Game;
import com.bluemobi.constant.threadPool.T;
import com.bluemobi.core.task.MessageTask;
import com.bluemobi.model.Player;
import com.bluemobi.util.GameObjectUtil;
import com.wanma.net.ElectricPieUtil;

/**
 * 接受客户端数据并处理
 * @author heweiwen
 * 2014-12-2 下午7:46:56
 */
public class GateMessageHandler {
	
	private static final Logger logger = Logger.getLogger(GateMessageHandler.class);
	
	/**
	 * 
	 * @author HeWeiwen
	 * 2014-12-2
	 * @param channel
	 * @param message
	 */
	public static void handleMessage(Channel channel, GateToGameMessage message){
		short protocolNum = message.getProtocolNum();
		int userId = message.getSenderId();
		//处理后台消息
		if (userId == 0) {
			MessageTask task = new MessageTask(channel, message);
			ThreadPoolFactory.submitToCached(T.PUBLIC_LOGIC_THREAD, task );
			return;
		}
		
		Player player = GameObjectUtil.getPlayerByUserId(userId);
		
		String playerName = "no player";
		//1、记录玩家的消息事件类型，便于知道玩家当前在做什么操作，记录产出消耗用。
		if(player!=null){
			player.setMsgEventType(protocolNum);
			playerName = player.getName();
		}
		
		logger.info( "角色名222:【"+playerName+"】:"+message.toString());
		
		MessageTask task = new MessageTask(channel, message);
		ThreadPoolFactory.submitToCached(T.PUBLIC_LOGIC_THREAD, task );
	}
	
	
	public static void processMessage(Channel channel, GateToGameMessage message){
		short protocolNum = message.getProtocolNum();
		int userId = message.getSenderId();
		byte[] bytes = message.getBytes();
		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
		
		int type = protocolNum/100;
		
		if (type == 1) {//1、用户
			//ElectricPieUtil electricPieUtil = new ElectricPieUtil();
            switch (protocolNum) {//从网关登录，就不从这里登陆
                case ElectricPieUtil.BACK_TYPE_101:
                    // 预约充电返回处理
                	ElectricPieUtil.bookElectricPieBack(byteBuffer);
                    break;
                case ElectricPieUtil.BACK_TYPE_102:
                    // 取消预约充电返回处理
                	ElectricPieUtil.cancelBookElectricPieBack(byteBuffer);
                    break;
                    // 以下注释有用，因测试需要暂时注释
                case ElectricPieUtil.BACK_TYPE_103:
                    // 开始充电返回处理
                	ElectricPieUtil.startChargeBack(byteBuffer);
                    break;
                case ElectricPieUtil.BACK_TYPE_104:
                    // 停止充电充电返回处理
                	ElectricPieUtil.stopChargeBack(byteBuffer);
                    break;
                case ElectricPieUtil.BACK_TYPE_105:
                	// 开始充电事件
                	ElectricPieUtil.beginChargeEvent(byteBuffer);
                case ElectricPieUtil.BACK_TYPE_106:	
                	//结束充电事件
                	ElectricPieUtil.endChargeEvent(byteBuffer);
                case ElectricPieUtil.BACK_TYPE_107:	
                	//消费记录数据
                	ElectricPieUtil.consumeRecord(byteBuffer);
                case ElectricPieUtil.BACK_TYPE_108:	
                	//充电余额告警处理
                	ElectricPieUtil.WarningBack(byteBuffer);
                case ElectricPieUtil.BACK_TYPE_109:
                	//取消预约事件
                	ElectricPieUtil.cancelBookElectricPieEvent(byteBuffer);
                case ElectricPieUtil.BACK_TYPE_110:
                	//修改电桩gate缓存
                	ElectricPieUtil.updateEpGate(byteBuffer);
                case ElectricPieUtil.BACK_TYPE_111:
                	//更新桩与gate的绑定
                	ElectricPieUtil.updateEpGate(byteBuffer);
            }
        } else if(type==101){//101、服务器之间内部消息
			switch (protocolNum) {
			case Gate2Game.P_10101: //登录网关服成功消息
				GateMessageHandler.loginGateSuccess(channel, byteBuffer);
				break;
			case Gate2Game.P_10102://玩家从网关登陆成功
				//UserService.login(channel, byteBuffer);
				break;
			case Gate2Game.P_10103://玩家从网关断开连接
				//UserService.offLine(userId);
				break;
			}
			
		}
	}
	
	public static void loginGateSuccess(Channel channel, ByteBuffer byteBuffer){
		
		int gateId = byteBuffer.getInt();
		
		GameWorld.addGateToGameWorld(gateId, channel);
		
		logger.info("game 登录 网关 成功，网关返回的gateId="+gateId);
		
	}
	
}

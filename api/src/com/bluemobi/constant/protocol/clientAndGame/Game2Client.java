package com.bluemobi.constant.protocol.clientAndGame;
/**
 * 服务端给客户端发送消息的协议号
 * @author hao
 * Feb 24, 2014 11:33:08 AM
 */
public class Game2Client {
	
	/**用户--通知客户端创建角色*/
	public static final short P_101 = 101;
	/**用户--充值--充值订单号*/
	public static final short P_102 = 102;
	/**用户--充值--充值成功*/
	public static final short P_103 = 103;
	/**用户--更新重连token*/
	public static final short P_104 = 104;
	
	
	
	/**玩家--创建角色时候名字被占用*/
	public static final short P_201 = 201;
	/**玩家--进入游戏*/
	public static final short P_202 = 202;
	/**玩家--更新玩家信息*/
	public static final short P_203 = 203;
	/**玩家--玩家升级通知*/
	public static final short P_204 = 204;
	/**玩家--其他玩家信息*/
	public static final short P_205 = 205;
	/**玩家--更新新手引导步骤成功*/
	public static final short P_206 = 206;
	
	
	/**英雄--批量增加、更新英雄*/
	public static final short P_301 = 301;
	/**英雄--英雄上阵、更换、换位成功*/
	public static final short P_302 = 302;
	/**英雄--点亮天赋成功*/
	public static final short P_303 = 303;
	/**英雄--英雄解锁*/
	public static final short P_304 = 304;
	
	/**装备--批量增加、更新装备*/
	public static final short P_401 = 401;
	/**装备--穿装备，换装备成功*/
	public static final short P_402 = 402;
	/**装备--卸下装备成功*/
	public static final short P_403 = 403;
	/**装备--强化装备成功*/
	public static final short P_404 = 404;
	/**装备--精练装备成功*/
	public static final short P_405 = 405;
	/**装备--出售装备成功*/
	public static final short P_406 = 406;
	/**装备--一键强化成功*/
	public static final short P_407 = 407;
	
	
	/**技能--批量增加、更新技能*/
	public static final short P_501 = 501;
	/**技能--升级技能成功*/
	public static final short P_502 = 502;

	
	
	/**道具--增加、更新道具*/
	public static final short P_601 = 601;
	/**道具--使用道具成功*/
	public static final short P_602 = 602;
	
	
	/**关卡--增加、更新关卡*/
	public static final short P_701 = 701;
	/**关卡--所有关卡信息*/
	public static final short P_702 = 702;
	/**关卡--闯关完成奖励*/
	public static final short P_703 = 703;
	/**关卡--领取父关卡奖励成功*/
	public static final short P_704 = 704;
	/**关卡--子关卡排行榜*/
	public static final short P_705 = 705;
	/**关卡--vip清除关卡挑战次数成功*/
	public static final short P_706 = 706;
	/**关卡--vip扫荡关卡成功*/
	public static final short P_707 = 707;
	
	
	/**好友--增加、更新好友*/
	public static final short P_801 = 801;
	/**好友--可加好友的玩家信息*/
	public static final short P_802 = 802;
	/**好友--添加好友请求发送成功*/
	public static final short P_803 = 803;
	/**好友--删除好友成功*/
	public static final short P_804 = 804;
	/**好友--通知客户端删除好友*/
	public static final short P_805 = 805;
	
	
	/**邮件--增加、更新邮件*/
	public static final short P_901 = 901;
	/**邮件--邮件列表*/
	public static final short P_902 = 902;
	/**邮件--发送玩家邮件成功*/
	public static final short P_903 = 903;
	/**邮件--标记邮件已读成功*/
	public static final short P_904 = 904;
	/**邮件--邮件领奖成功*/
	public static final short P_905 = 905;
	/**邮件--处理加好友请求邮件成功*/
	public static final short P_906 = 906;
	
	
	/**聊天--系统公告*/
	public static final short P_1001 = 1001;
	/**聊天--玩家聊天消息*/
	public static final short P_1002 = 1002;
	
	
	/**系统--系统提示*/
	public static final short P_1101 = 1101;
	/**系统--服务器时间*/
	public static final short P_1102 = 1102;
	/**系统--错误码*/
	public static final short P_1103 = 1103;
	/**系统--激活码领奖结果*/
	public static final short P_1104 = 1104;
	
	/**战魂--更新、添加战魂*/
	public static final short P_1201 = 1201;
	/**战魂--战魂觉醒*/
	public static final short P_1202 = 1202;
	/**战魂--装备战魂*/
	public static final short P_1203 = 1203;
	/**战魂--卸下战魂*/
	public static final short P_1204 = 1204;
	/**战魂--元魂兑换战魂*/
	public static final short P_1205 = 1205;
//	/**战魂--战魂分解元神*/
//	public static final short P_1206 = 1206;
	/**战魂--战魂升级*/
	public static final short P_1207 = 1207;
	/**战魂--多次觉醒战魂*/
	public static final short P_1208 = 1208;
	
	/**商城--更新、添加商品*/
	public static final short P_1401 = 1401;
	/**商城--商城动态信息*/
	public static final short P_1402 = 1402;
	/**商城--购买成功*/
	public static final short P_1403 = 1403;
	
	/**PVP--pvp玩家列表*/
	public static final short P_1301 = 1301;
	/**PVP--pvp领奖界面信息*/
	public static final short P_1302 = 1302;
	/**PVP--处理上报的pvp战斗结果: 1、正常处理 2、由于被挑战玩家排名比自己低，不处理*/
	public static final short P_1303 = 1303;
	/**PVP--兑换pvp奖励成功*/
	public static final short P_1304 = 1304;
	
	
	
	/**vip--vip购买体力成功*/
	public static final short P_1501 = 1501;
	/**vip--vip购买精力成功*/
	public static final short P_1502 = 1502;
	/**vip--vip改名结果*/
	public static final short P_1503 = 1503;
	
	
	/**活动--活动信息*/
	public static final short P_1601 = 1601;
	/**活动--沐浴成功*/
	public static final short P_1602 = 1602;
	/**活动--领取升级奖励成功*/
	public static final short P_1603 = 1603;
	
	/**宠物--更新、添加宠物*/
	public static final short P_1701 = 1701;
	/**宠物--英雄更换宠物*/
	public static final short P_1702 = 1702;
	/**宠物--卸下宠物*/
	public static final short P_1703 = 1703;
	/**宠物--宠物品质升级*/
	public static final short P_1704 = 1704;
	/**宠物--解锁宠物*/
//	public static final short P_1705 = 1705;
	
	
	/**支付宝安全支付 验证*/
	public static final short P_9801 = 9801;
	/**pp助手充值验证*/
	public static final short P_9802 = 9802;
	/**360充值验证通过给予元宝*/
	public static final short P_9803 = 9803;
	/**支付宝快捷支付 验证*/
	public static final short P_9804 = 9804;
	

}

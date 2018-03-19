/*package com.bluemobi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appCore.core.pool.ThreadPoolFactory;
import com.bluemobi.constant.LogConstant;
import com.bluemobi.core.task.LogOutputAndConsumeTask;
import com.bluemobi.db.DB;
import com.bluemobi.model.GmLogLoginDetail;
import com.bluemobi.model.GmLogOutputAndConsume;
import com.bluemobi.model.Player;
import com.wanma.app.util.DateUtil;

*//**
 * 日志记录服务
 *
 * @author xujianxin
 * @time Jul 3, 2013 11:07:31 AM
 *//*
public class LogService {

    private static Logger logger = LoggerFactory.getLogger(LogService.class);

    *//**
     * 玩家登录时候需要记录的日志
     *
     * @param player
     * @author haojian
     * Jul 8, 2013 1:29:53 PM
     *//*
    public static void logWhenLogin(Player player) {

        GmLogLoginDetail detail = new GmLogLoginDetail();

        detail.setPlayerId(player.getId());//玩家id
        detail.setType(LogConstant.LOGIN);//登陆
//        detail.setPlayerType(PlayerService.isNewPlayer(player) ? LogConstant.PLAYER_TYPE_NEW : LogConstant.PLAYER_TYPE_OLD);//玩家类型：1新 2老
        detail.setRecordTime(DateUtil.currentTimeSecond());//记录时间
        detail.setOnlineTime(0);//本次登陆在线时长
        detail.setSource(player.getUser().getLoginSource());//玩家登陆来源
        DB.gameObjectService.saveOrUpdate(detail);

    }

    *//**
     * 玩家离线时候需要记录的日志
     *
     * @param player
     * @author haojian
     * Jul 8, 2013 1:30:01 PM
     *//*
    public static void logWhenLogout(Player player) {
        GmLogLoginDetail detail = new GmLogLoginDetail();
        detail.setPlayerId(player.getId());//玩家id
        detail.setType(LogConstant.LOGOUT);//登出
//        detail.setPlayerType(PlayerService.isNewPlayer(player) ? LogConstant.PLAYER_TYPE_NEW : LogConstant.PLAYER_TYPE_OLD);//玩家类型：1新 2老
        detail.setRecordTime(DateUtil.currentTimeSecond());//记录时间
        int onlineTime = DateUtil.currentTimeSecond() - player.getLoginTime();
        detail.setOnlineTime(onlineTime);//本次登陆在线时长
        detail.setSource(player.getUser().getLoginSource());//玩家登陆来源
        DB.gameObjectService.saveOrUpdate(detail);
    }


    *//**
     * 开启另外的线程来 记录产出和消耗
     *
     * @param player 玩家
     * @param type   得到 或 消耗  （1、产出 2、消耗）
     * @param no     某个物品
     * @param num    多少个
     * @author haojian
     * Jul 9, 2013 1:55:45 PM
     *//*
    public static void logOutputAndConsume(Player player, int type, int no, int num) {
        //开启另外的线程来记录产出和消耗数据
        LogOutputAndConsumeTask logTask = new LogOutputAndConsumeTask(player, type, no, num);
        ThreadPoolFactory.submit(logTask.getClass().getName(), logTask);

    }

    *//**
     * 保存产出和消耗数据到数据库
     *
     * @param player 玩家
     * @param type   得到 或 消耗  （1、产出 2、消耗）
     * @param no     某个物品
     * @param num    多少个
     * @author haojian
     * Jul 9, 2013 1:55:45 PM
     *//*
    public static void saveOutputAndConsume(Player player, int type, int no, int num) {
        GmLogOutputAndConsume log = new GmLogOutputAndConsume();
        log.setPlayerId(player.getId());
        log.setEventType(player.getMsgEventType()); //在什么操作中
        log.setType(type);  //得到 或 消耗  （1、产出 2、消耗）
        log.setNo(no);  //物品
        log.setNum(num); //数量
        log.setRecordTime(DateUtil.currentTimeSecond());//记录时间
        DB.gameObjectService.saveOrUpdate(log);
    }


}
*/
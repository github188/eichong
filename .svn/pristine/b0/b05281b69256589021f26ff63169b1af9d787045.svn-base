
package com.wanma.ims.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class JPushUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JPushUtil.class);

    /**
     * 普通版账号与key
     */
    private static final String MASTER_SECRET = "6992bb3fc50f9c3343df1efa";
    private static final String APP_KEY = "ed4477671a9298e1599cc145";

    /**
     * 政企版账号与key
     */
    private static final String COMPANY_MASTER_SECRET = "68431bbbe318d8da915fa5d1";
    private static final String COMPANY_APP_KEY = "bd3cc14d3a8f657f0785a647";


    /**
     * @param registrationID 设备标识
     * @param alert          消息内容标题
     * @param msg            消息内容
     *                       极光推送手机唯一标示
     * @param type           消息类型   13意见反馈推送
     * @return
     * @Title: point2point
     * @Description: 点对点推送
     */
    public static BaseResultDTO jpushNotice(String alert, String msg, String registrationID, String type) {
        JPushClient jpush = new JPushClient(MASTER_SECRET, APP_KEY);
        PushResult result = null;
        try {
            PushPayload pushPayload = buildNotice_android_and_ios(registrationID, alert, alert, msg, type);
            result = jpush.sendPush(pushPayload);
        } catch (Exception e) {
            LOGGER.error("JPushUtil-jpushNotice is error|alert={}|msg={}|registrationID={}|type={}|result={}", alert, msg, registrationID, type, result, e);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "推送失败");
        }

        if (result.isResultOK()) {
            return new BaseResultDTO(true, ResultCodeConstants.SUCCESS, "推送成功");
        } else {
            LOGGER.error("JPushUtil-jpushNotice is fail|alert={}|msg={}|registrationID={}|type={}|result={}", alert, msg, registrationID, type, result);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "推送失败");
        }
    }

    /**
     * 通知推送
     *
     * @param registrationId     设备标识
     * @param notification_title 通知内容标题
     * @param msg_title          消息内容标题
     * @param msg_content        消息内容
     */
    public static PushPayload buildNotice_android_and_ios(String registrationId, String notification_title, String msg_title, String msg_content, String type) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.registrationId(registrationId))
                        .build())
                .setNotification(Notification.newBuilder()
                                .setAlert(notification_title)
                                .addPlatformNotification(AndroidNotification.newBuilder()
                                                .setAlert(msg_content)
                                                .setTitle(msg_title)
                                                        //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                                .addExtra("type", type)
                                                .build()
                                )
                                .addPlatformNotification(IosNotification.newBuilder()
                                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                                .setAlert(msg_content)
                                                        //此项是指定此推送的badge自动加1
                                                .incrBadge(1)
                                                .setSound("sound.caf")
                                                        //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                                .addExtra("type", type)
                                                .addExtra("msg", msg_content)
                                                .addExtra("title", notification_title)
                                                .addExtra("tm", String.valueOf(System.currentTimeMillis()))
                                                .build()
                                )
                                .build()
                )
//                    .setMessage(Message.newBuilder()
//                            .setMsgContent(msg_content)
//                            .setTitle(msg_title)
//                            .addExtra("extras",extras)
//                            .build())
                .setOptions(Options.newBuilder()
                                //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                                .setApnsProduction(false)
                                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
//                            .setSendno(100)
                                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                                .setTimeToLive(86400)
                                .build()
                )
                .build();
    }

    /**
     * @param alert 标题
     * @param msg   内容
     */
    public static Map<String, String> getBasicMsgMap(String alert, String msg) {
        Map<String, String> basicMsgMap = new HashMap<>(10);
        basicMsgMap.put("title", alert);
        basicMsgMap.put("tm", String.valueOf(System.currentTimeMillis()));
        basicMsgMap.put("msg", msg);
        return basicMsgMap;
    }

    /**
     * @param registrationID 极光推送手机唯一标示
     * @param type           消息类型  13：意见反馈推送
     * @return
     * @Title: point2point
     * @Description: 极光推送自定义消息
     */
    public static BaseResultDTO jpushCustom(Map<String, String> msgMap, String registrationID, String type, boolean isCompanyUser) {
        JPushClient jpush;
        if (isCompanyUser) {
            jpush = new JPushClient(COMPANY_MASTER_SECRET, COMPANY_APP_KEY);
        } else {
            jpush = new JPushClient(MASTER_SECRET, APP_KEY);
        }
        PushResult result = null;
        try {
            PushPayload pp = PushPayload.newBuilder()
                    .setPlatform(Platform.android_ios())
                    .setAudience(Audience.newBuilder()
                            .addAudienceTarget(AudienceTarget.registrationId(registrationID))
                            .build())
                    .setMessage(Message.newBuilder()
                            .setMsgContent(msgMap.toString())
                            .addExtra("type", type)
                            .build())
                    .build();
            result = jpush.sendPush(pp);
        } catch (Exception e) {
            LOGGER.error("JPushUtil-jpushCustom is error|msgMap={}|registrationID={}|type={}|result={}", msgMap, registrationID, type, result, e);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "推送失败");
        }
        if (result.isResultOK()) {
            return new BaseResultDTO(true, ResultCodeConstants.SUCCESS, "推送成功");
        } else {
            LOGGER.error("JPushUtil-jpushCustom is fail|msgMap={}|registrationID={}|type={}|result={}", msgMap, registrationID, type, result);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "推送失败");
        }
    }

    /**
     * 全推
     *
     * @param msg 推送内容
     * @throws APIRequestException
     * @throws APIConnectionException
     */
    public static void pushAll(String msg) throws APIConnectionException, APIRequestException {
        JPushClient jpush = new JPushClient(MASTER_SECRET, APP_KEY);
        jpush.sendNotificationAll(msg);
    }

    public static void main(String[] args) throws APIConnectionException, APIRequestException {
        Map<String, String> msgMap = getBasicMsgMap("地锁测试标题", "地锁测试内容");
        msgMap.put("errorCode", "100");
        msgMap.put("waitTime", "5");
        BaseResultDTO resultDTO = JPushUtil.jpushCustom(msgMap, "121c83f76006de24924", "14", false);
        System.out.println(SerializationUtil.gson2String(resultDTO));
    }
}

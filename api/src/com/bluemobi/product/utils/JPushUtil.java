
package com.bluemobi.product.utils;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;

import com.bluemobi.product.common.DataModel;

public class JPushUtil
{
    /**普通版账号与key*/
    private static final String Secret = "6992bb3fc50f9c3343df1efa";
    private static final String Key = "ed4477671a9298e1599cc145";
    /**政企版账号与key*/
    private static final String masterSecret = "68431bbbe318d8da915fa5d1";
    private static final String appKey = "bd3cc14d3a8f657f0785a647";

    /**
     * @Title: point2point
     * @Description: 点对点推送
     * @param alert
     *        标题
     * @param msg
     *         内容        
     * @param registrationID
     *         极光推送手机唯一标示
     * @param deviceType
     *           设备类型  1 安卓 2 ios  
     * @param type
     *           消息类型  1 充电结束推送    2 余额不足推送     3充电开始推送    4消费记录    5预约完成  6取消预约    7后台全推  8不同设备登陆      9系统通知   10新优惠券
     * @return 
     */
    public static DataModel point2point( String alert, String msg, String registrationID, String deviceType,String type)
    {
        JPushClient jpush = new JPushClient( masterSecret, appKey );
        PushResult result = null;
        try
        {
            /*if ( deviceType.equals( "1" ) ){
            	// type 推送消息
            	 Map<String, String> extras = new HashMap<String, String>();
                 extras.put("msg", msg );
                 extras.put("type", type );
            	 jpush.sendAndroidNotificationWithRegistrationID(alert, msg, extras, registrationID);
                //result = jpush.sendAndroidMessageWithRegistrationID( alert, msg, registrationID );
                
            } else{
                Map<String, String> extras = new HashMap<String, String>();
                extras.put( "msg", msg );
                extras.put("type", type );
                jpush.sendIosNotificationWithRegistrationID(msg, extras, registrationID );
                //result = jpush.sendIosMessageWithRegistrationID( alert, msg, registrationID );
                
            }*/
            PushPayload pp = PushPayload.newBuilder()
                    .setPlatform(Platform.android_ios())
                    .setAudience(Audience.newBuilder()
                            .addAudienceTarget(AudienceTarget.registrationId(registrationID))
                            .build())
                    .setMessage(Message.newBuilder()
                            .setMsgContent("")
                            .addExtra("type", type)
                            .build())
                    .build();
            result = jpush.sendPush(pp);
        } catch ( Exception e )
        {
            return new DataModel( "0", "推送失败", null );
        }
        if (result.isResultOK())            
        {
            return new DataModel( "1", "推送成功", null );
        } else {
            return new DataModel( "0", "推送失败", null );
        }
       

    }
    
    /**
     *  全推
     * @param msg 推送内容
     * @throws APIRequestException 
     * @throws APIConnectionException 
     */
    public static void pushAll( String alert,String msg) throws APIConnectionException, APIRequestException{
    	JPushClient jpush = new JPushClient( masterSecret, appKey );
    	jpush.sendNotificationAll(msg);
    }
    
    /**
     * 多设备登陆推送(政企版推送)
     * @param alert
     * @param msg
     * @param registrationID
     * @param deviceType 1android 2ios
     * @return
     */
    public static DataModel loginPushNotifyByRegId(String alert, String msg, String registrationID, String deviceType){
    	JPushClient jpush = new JPushClient( masterSecret, appKey );
        PushResult result = null;
        try{
        	PushPayload pp = buildPushObject_ios_audience_messageWithExtras(registrationID,"8");
        	//JPushClient jpush = new JPushClient( masterSecret, appKey );
        	result = jpush.sendPush(pp);
	    } catch ( Exception e ){
	        return new DataModel( "0", "推送失败", null );
	    }
        
	    if (result.isResultOK()){
	        return new DataModel( "1", "推送成功", null );
	    } else {
	        return new DataModel( "0", "推送失败", null );
	    }
    }
    /**
     * 多设备登陆推送(普通版推送)
     * @param alert
     * @param msg
     * @param registrationID
     * @param deviceType
     * @return
     */
    public static DataModel pushNotifyByRegId(String alert, String msg, String registrationID, String deviceType){
    	JPushClient jpush = new JPushClient(Secret, Key );
        PushResult result = null;
        try{
        	PushPayload pp = buildPushObject_ios_audience_messageWithExtras(registrationID,"8");
        	//JPushClient jpush = new JPushClient( masterSecret, appKey );
        	result = jpush.sendPush(pp);
	    } catch ( Exception e ){
	        return new DataModel( "0", "推送失败", null );
	    }
        
	    if (result.isResultOK()){
	        return new DataModel( "1", "推送成功", null );
	    } else {
	        return new DataModel( "0", "推送失败", null );
	    }
    }
    
    public static PushPayload buildPushObject_ios_audience_messageWithExtras(String registrationId,String typeValue) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.registrationId(registrationId))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent("")
                        .addExtra("type", typeValue)
                        .build())
                .build();
    }
    
    public static void main( String[] args ) throws APIConnectionException, APIRequestException
    {
        //JPushClient jpush = new JPushClient( masterSecret, appKey );
        //jpu.point2point( "苏荷酒吧", "测试", "", Constant.IOS);
        
//        JPushClient jpush = new JPushClient( masterSecret, appKey );
//        jpush.sendNotificationAll( "1111111/" );

        Map<String, String> extras = new HashMap<String, String>();
        extras.put( "msg", "333333333" );
        extras.put( "pushType", "1" );
        loginPushNotifyByRegId( "alert4444", "333333333", "0707e64e4e0", "8");
        JPushUtil.point2point("取消预约","neirong","0707e64e4e0","1", "6");
        //JPushClient jpush = new JPushClient( masterSecret, appKey );
        /*jpush.sendIosNotificationWithRegistrationID("title",extras, "0707e64e4e0" );
        jpush.sendAndroidMessageWithRegistrationID( "title", "message", "0707e64e4e0" );*/
        
        /*PushResult result = null;
        PushPayload pp = PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.registrationId("0707e64e4e0"))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent("")
                        .addExtra("type", 8)
                        .build())
                .build();
        result = jpush.sendPush(pp);*/
    }
}

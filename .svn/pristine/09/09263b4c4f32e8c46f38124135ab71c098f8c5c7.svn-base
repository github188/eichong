
package com.base.util;

import java.util.HashMap;
import java.util.Map;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;

import com.base.common.DataModel;

public class JPushUtil
{

	private static final String masterSecret = "6992bb3fc50f9c3343df1efa";
    private static final String appKey = "ed4477671a9298e1599cc145";

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
     *           消息类型  1 充电结束推送    2 余额不足推送     3充电开始推送    4消费记录    5预约完成  6取消预约          
     * @return 
     */
    public static DataModel point2point( String alert, String msg, String registrationID, String deviceType,String type)
    {
        JPushClient jpush = new JPushClient( masterSecret, appKey );
        PushResult result = null;
        try
        {
//            Map<String, String> map = new HashMap<String, String>();
//            map.put( "msg", msg );
//            map.put( "pushType", pushType );
//            String pushMsg = JSONObject.fromObject( map ).toString();
            if ( deviceType.equals( "1" ) )
            {
            	// type 推送消息
            	 Map<String, String> extras = new HashMap<String, String>();
                 extras.put("msg", msg );
                 extras.put("type", type );
            	 jpush.sendAndroidNotificationWithRegistrationID(alert, msg, extras, registrationID);
                result = jpush.sendAndroidMessageWithRegistrationID( alert, msg, registrationID );
                
            } else
            {
                Map<String, String> extras = new HashMap<String, String>();
                extras.put( "msg", msg );
                extras.put("type", type );
                jpush.sendIosNotificationWithRegistrationID(msg, extras, registrationID );
                result = jpush.sendIosMessageWithRegistrationID( alert, msg, registrationID );
            }
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
    
    public static void main( String[] args ) throws APIConnectionException, APIRequestException
    {
        //JPushClient jpush = new JPushClient( masterSecret, appKey );
        //jpu.point2point( "苏荷酒吧", "测试", "", Constant.IOS);
        
//        JPushClient jpush = new JPushClient( masterSecret, appKey );
//        jpush.sendNotificationAll( "1111111/" );

        Map<String, String> extras = new HashMap<String, String>();
        extras.put( "msg", "333333333" );
        extras.put( "pushType", "1" );
        point2point( "alert4444", "333333333", "0409412b689", "1","1");
       
        
    }
}

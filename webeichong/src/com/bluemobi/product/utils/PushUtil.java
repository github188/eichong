/*
 * Name: 推送工具类文件
 * Description: 使用百度推送实现的推送工具类
 * Author: John.liu
 * Create: 2014/05/20
 * Update: 2014/05/20
 */
package com.bluemobi.product.utils;

import org.apache.log4j.Logger;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
/**
 * 推送工具类
 * <pre>
 * 	使用推送方法之前需要调用该类静态方法setClientCrediential(String api_key, String secret_key)设置client信息
 * </pre>
 * @author John.liu
 * @date 2014/05/20
 * @version V1.0
 */
public class PushUtil {
	
	private static Logger log = Logger.getLogger(PushUtil.class);
	
	private static String apiKey;		//百度开发者应用apikey
	
	private static String secretKey;	//百度开发者应用secretkey
	/**
	 * WEB设备
	 */
	public static int DEVICE_WEB = 1;
	/**
	 * PC设备
	 */
	public static int DEVICE_PC = 2;
	/**
	 * 安卓设备
	 */
	public static int DEVICE_ANDROID = 3;
	/**
	 * IOS设备
	 */
	public static int DEVICE_IOS = 4;
	/**
	 * WindowsPhone设备
	 */
	public static int DEVICE_WP = 5;
	/**
	 * 消息类型-消息
	 */
	public static int MTYPE_MESSAGE = 0;
	/**
	 * 消息类型-通知
	 */
	public static int MTYPE_NOTIFICATION = 1;
	
	/**
	 * 设置推送api key和secret key
	 * @param api_key
	 * @param secret_key
	 */
	public static void setClientCrediential(String api_key, String secret_key){
		apiKey = api_key;
		secretKey = secret_key;
	}
	
	private static BaiduChannelClient getChannelClient(){
		//1. 设置developer平台的ApiKey/SecretKey
        ChannelKeyPair pair = new ChannelKeyPair(apiKey, secretKey);

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
                System.out.println("channel handler info: "+event.getMessage());
            }
        });
        return channelClient;
	}
	
	/**
	 * 推送广播消息
	 * @param message		消息		推送通知消息时，消息格式为：{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}
	 * @param deviceType	设备类型3.Android 4.IOS 5.WP
	 * @param messageType 	消息类型PushUtil.MTYPE_MESSAGE,PushUtil.MTYPE_NOTIFICATION
	 */
	public static void pushBroadCastMessage(String message, int deviceType, int messageType){
        try {
            // 创建请求类对象
        	PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            // 设置设备类型 1: web 2: pc 3:android 4:ios 5:wp
            request.setDeviceType(deviceType);
            // 设置消息类型
            request.setMessageType(messageType);
            // 设置消息
            request.setMessage(message);
            // 调用pushMessage接口
            PushBroadcastMessageResponse response = getChannelClient().pushBroadcastMessage(request);

            // 认证推送成功
            System.out.println("push amount : " + response.getSuccessAmount());
        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
        	log.error(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
	}
	/**
	 * 推送单个消息
	 * @param message		消息		推送通知消息时，消息格式为：{\"title\":\"Notify_title_danbo\",\"description\":\"Notify_description_content\"}
	 * @param deviceType	设备类型3.Android 4.IOS 5.WP
	 * @param channelId		通道ID	（设备端通过百度oauth2.0认证接口获取）
	 * @param userId		用户ID	（设备端通过百度oauth2.0认证接口获取）
	 * @param messageType	消息类型PushUtil.MTYPE_MESSAGE, PushUtil.MTYPE_NOTIFICATION
	 */
	public static void pushUniCastMessage(String message, int deviceType, int messageType, long channelId, String userId){
        try {
            // 创建请求类对象
        	PushUnicastMessageRequest request = new PushUnicastMessageRequest();
            // 设置设备类型 1: web 2: pc 3:android 4:ios 5:wp
            request.setDeviceType(deviceType);  
            // 设置消息类型
            request.setMessageType(messageType);
            // 设置消息
            request.setMessage(message);
            // 设置channel id
            request.setChannelId(channelId);
            // 设置user id
            request.setUserId(userId);
            // 调用pushMessage接口
            PushUnicastMessageResponse response = getChannelClient().pushUnicastMessage(request);
            // 认证推送成功
            System.out.println("push amount : " + response.getSuccessAmount());
        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
        	log.error(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            System.out.println(String.format(
                    "request_id: %d, error_code: %d, error_message: %s",
                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
	}
	public static void main(String[] args) {
		setClientCrediential("Y1wmSy7NkAyGQB6aLhXr9UPs", "iEuh4xz9PAv82g2e3Eydhab1pmNY4sLc");
		pushBroadCastMessage("蓝色互动2014测试推送", PushUtil.DEVICE_ANDROID, PushUtil.MTYPE_MESSAGE);
		pushBroadCastMessage("{\"title\":\"蓝色互动2014\",\"description\":\"蓝色互动2014测试推送\"}", PushUtil.DEVICE_ANDROID, PushUtil.MTYPE_NOTIFICATION);
		
		pushUniCastMessage("蓝色互动2014测试推送", PushUtil.DEVICE_ANDROID, PushUtil.MTYPE_MESSAGE, 3810773336004618116L, "2517512168");
		pushUniCastMessage("{\"title\":\"蓝色互动2014\",\"description\":\"蓝色互动2014测试推送\"}", PushUtil.DEVICE_ANDROID, PushUtil.MTYPE_NOTIFICATION, 3810773336004618116L, "2517512168");
	}
}

package com.wanma.common;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.wanma.app.service.impl.RedisService;

/***
 * @title 接口统计
 * @author wangfei
 *
 */

public class APIStatUtil {

	private static HashMap<String, String> apiNameMap = null;

	private static APIStatUtil uniqueInstance = null;

	public static APIStatUtil getInstance() {

		if (uniqueInstance == null) {
			uniqueInstance = new APIStatUtil();
			initApiNameMap();
		}
		return uniqueInstance;

	}

	// 接口类初始化
	private static void initApiNameMap() {
		
		apiNameMap = new HashMap<String, String>();

		apiNameMap.put("addTblEquipmentrepair", "添加设备报修");
		apiNameMap.put("userFavorites", "电桩、电站收藏添加");

		apiNameMap.put("getUserInfo", "获取用户信息");
		apiNameMap.put("modifyUser", "完善用户资料");
		apiNameMap.put("setPayPwd", "设置支付密码");
		apiNameMap.put("resetPasswrod", "修改密码");
		apiNameMap.put("getMyWallet", "我的钱包");
		apiNameMap.put("upgradeoUser", "一键升级商家");
		apiNameMap.put("removeMyCommentN", "删除评论");

		apiNameMap.put("selectBespokeById", "预约详情");
		apiNameMap.put("insertBespoke", "枪头预约、续约");
		apiNameMap.put("updateBespStatus", "取消预约");

		apiNameMap.put("getMyWallet", "我的预约");
		apiNameMap.put("upgradeoUser", "我的收藏");
		apiNameMap.put("removeMyCommentN", "收藏删除");
		apiNameMap.put("getMyFootPrint", "我的足迹");
		apiNameMap.put("appShare", "第三方分享	");
		apiNameMap.put("getVersionInfo", "版本更新");

		apiNameMap.put("aliSign", "获取支付宝sigin值");
		apiNameMap.put("wxTempOrder", "微信预支付	url");

		apiNameMap.put("addTblFeedBack", "意见反馈");
		apiNameMap.put("getVersionInfo", "获取版本信息");
		apiNameMap.put("carGarage/list", "车辆维修");
		apiNameMap.put("add", "发布桩体");
		apiNameMap.put("mylist", "我的消息");

	}

	//保存 接口调用
	public  void store(String url) {

		RedisService redisService = (RedisService) ApplicationContextUtils
				.getBean("redisService");
		
		String _url="";
		
		String[] urls= url.split("/");
		
		if(urls.length>0){
			 _url=urls[urls.length-1].replace(".do","");
		}
		
		Boolean url_isIn=isIn(_url);
		
		if(!url_isIn){
			return ;
		}else{
			// 接口访问次数统计
			String _urlVisitorCount = "app:visit:"+_url;
			redisService.strIncr(_urlVisitorCount);
			
			String s_urlVisitorCount=redisService.strGet(_urlVisitorCount);
			
		}

	}
	//获取客户端ip
	public static String getRemortIP(HttpServletRequest request) {
		if(null==request)return "";
		if (request.getHeader("x-forwarded-for") == null) {
			return request.getRemoteAddr();
		}
		return request.getHeader("x-forwarded-for");
	}
	
	public static Boolean isIn(String url){
		if(null==apiNameMap)	return false;
		Boolean isContainKey=apiNameMap.containsKey(url);
		return isContainKey;
	}
}

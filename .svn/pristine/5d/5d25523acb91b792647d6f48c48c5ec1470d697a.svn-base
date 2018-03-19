package com.wanma.model;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;

import com.pub.model.Entity;

/**
 * API接口日志
 * 
 * @Description:
 * @author wangfei
 * @createTime：2015-10-28 下午05:17:39
 */
public class ApiLog extends Entity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static HashMap<String, String> apiNameMap = null;
	

	public ApiLog(String url, String count) {
		initMap();
		this.url = url;
		this.apiname = apiNameMap.get(url);
		this.count = count;
	}

	private String url;// API接口

	private String apiname;// API接口名

	private String count;// 记录数

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getApiname() {
		return apiname;
	}

	public void setApiname(String apiname) {
		this.apiname = apiname;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	private void initMap() {
		apiNameMap = new HashMap<String, String>();

		apiNameMap.put("addTblEquipmentrepair", "添加设备报修");
		apiNameMap.put("userFavorites", "电桩、充电点收藏添加");

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
		apiNameMap.put("getFavoriteListN", "我的收藏");
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
	
	public static class ComparatorApiLog implements Comparator{  
        public int compare(Object o1, Object o2) {  
        		ApiLog	 log0 = (ApiLog) o1;  
        		ApiLog 	 log1 = (ApiLog) o2;  
        		return log1.getCount().compareTo(log0.getCount());  
        }  
    }   
}

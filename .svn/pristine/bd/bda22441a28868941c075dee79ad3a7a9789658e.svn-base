/** 
 * FileName PeaceLiveConsts.java
 * 
 * Version 1.0
 *
 * Create by yangwr 2014/8/9
 * 
 * Copyright 2000-2001 Bluemobi. All Rights Reserved.
 */

package com.base.common;


/**
 * FileName PeaceLiveConsts.java
 * 
 * Version 1.0
 * 
 * Create by yangwr 2014/8/9
 * 
 * 共有变量定义
 */

public interface ApplicationConsts  {

	/** 编号生成:用户ID */
	public static String SEQUENCE_USER = "SEQUENCE_USER";
	/** 编号生成:反馈ID */
	public static String SEQUENCE_FBACK = "SEQUENCE_FBACK";
	
	/**
	 * 电桩管理
	 */
	public class ElectricPileManager{
    
		/**
		 * mysql 通过两点经纬度，算两点之间距离(单位米)
		 * 第一点经纬度：@lng1 @lat1
                             第二点经纬度：@lng2 @lat2
		 */ 
		public static  String MYSQL_DISTANCE="round(6378.138*2*asin(sqrt(pow(sin( (@lat1*pi()/180-@lat2*pi()/180)/2),2)+cos(@lat1*pi()/180)*cos(@lat2*pi()/180)* pow(sin( (@lng1*pi()/180-@lng2*pi()/180)/2),2)))*1000)";
		public static final String ELECTRICPILE="1";//1：充电桩/充电树     
		public static final String POWERSTATION="2";// 2：充电点
		public static final String BYCILEPILE="3";//电动自行车
		//枪头状态 电桩枪头状态（0空闲中，3预约中，6充电中，9停用中）
		public static final int ELECTRIC_FREE=0;
		public static final int ELECTRIC_SUBSCRIBE=3;
		public static final int ELECTRIC_CHARGE=6;
		public static final int ELECTRIC_DISCARD=0;
		
		//评论类型 1：桩体评价 2：商城商品评价 3：充值评价 4：充电点
		public static final int COMMENT_ELECTRIC=1;
		public static final int ELECTRIC_STORE=2;
		public static final int ELECTRIC_RECHARGE=3;
		public static final int ELECTRIC_POWER=4;
		
		//页面订单状态 
		public static final int PAGE_ORDER_ALL=1;//搜索全部
		public static final int PAGE_ORDER_NO_PAY=2;//待支付
		public static final int PAGE_ORDER_NO_INSTALL=3;//待安装
		public static final int PAGE_ORDER_FINSH=4;//已完成
		
		//消费订单状态
		public static final int ORDER_SHOP_NOPAY=1;
		public static final int ORDER_SHOP_PAY=2;
		//安装订单状态
		public static final int ORDER_NO_INSTALL=1;
		public static final int ORDER_Install_FINSH=2;
		
		//用户状态  1：正常 2 ：冻结 3 ：商家升级审核中 4 普通用户
		public static final int USER_STATE_NORMAL=1;//  1：正常 
		public static final int USER_STATE_FREEZE=2;//  2 ：冻结
		public static final int USER_STATE_UPGRADE=3;//  3 ：商家升级审核中 4
		public static final int USER_STATE_GENERAL=4;//   4 普通用户
	}
}

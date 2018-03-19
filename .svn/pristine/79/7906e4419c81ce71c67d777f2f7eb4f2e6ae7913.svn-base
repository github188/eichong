package com.wanma.common;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.bluemobi.model.PinCode;
import com.wanma.app.service.ElectricPileListService;
import com.wanma.model.TblElectricpile;
/**
 * @Description: 万马公共CODE定义
 * @author songjf
 * @createTime：2015-3-15 下午08:36:31
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
public class WanmaConstants {

	/** 图片分类定义 */
	public static String MULTI_TYPE_USER_AVATAR = "userAvatar";// 用户头像
	public static String MULTI_TYPE_PRODUCT_IMAGE = "proImage";// 新能源商品列表图片
	public static String MULTI_TYPE_PRODUCT_DETAIL_IMAGE = "proDetailImage";// 新能源商品详细图片
	public static String MULTI_TYPE_PRODUCT_COMMENT_IMAGE = "proCommentImage";// 评论图片
	public static String MULTI_TYPE_TOTAL_Home_IMAGE = "homeImage";// 首页广告栏图片
	public static String MULTI_TYPE_ID_CARD_IMAGE = "IdCardImage";// 身份证附件
	public static String MULTI_UNITTYPE_ID_CARD_IMAGE = "IdUnitCardImage";// 纯商家身份证附件
	public static String MULTI_TYPE_UNIT_IMAGE = "UnitImage";// 个体商家图片
	public static String MULTI_TYPE_LICENSE_IMAGE = "LicenseImage";// 营业执照
	public static String MULTI_TYPE_AFFAIRS_IMAGE = "AffairsImage";// 税务登记证
	public static String MULTI_TYPE_ACCREDIT_IMAGE = "AccreditImage";// 授权证明
	public static String MULTI_TYPE_ELECTRICT_LIST_IMAGE = "electricListImage";// 电桩列表图片
	public static String MULTI_TYPE_ELECTRICT_DETAIL_IMAGE = "electricDetailImage";// 电桩详情图片
	public static String MULTI_TYPE_ELECTRICT_PUBLISH_IMG = "epPublishImage"; //电桩发布
	
	public static String MULTI_TYPE_CAR_FIX_IMAGE = "carfix";//车辆维修图片
	public static String MULTI_TYPE_POWER_LIST_IMAGE = "powerListImage";// 电站列表图片
	public static String MULTI_TYPE_POWER_DETAIL_IMAGE = "powerDetailImage";// 电站详情图片

	public static String MULTI_LIST_IMAGE = "ListImage";// 商品管理的列表图片
	public static String MULTI_DETAIL_IMAGE = "DetailImage";// 商品管理的列表图片
	public static String SENSITIVE_WORD_LIST="sensitiveWordList";//敏感词列表

	/* 安卓ios安装包 */
	public static String MULTI_TYPE_APK = "apk";// 安卓获取ios安卓包

	/** 评论类型 */
	public static String PRODUCT_COMMENT_MALL = "2";// 用户头像

	/** 枪头状态 */
	public static int ELECTRICPILEHEAD_FREE = 0;// 空闲中
	public static int ELECTRICPILEHEAD_APPOINTMENT = 3;// 预约中
	public static int ELECTRICPILEHEAD_BATTERY = 6;// 充电中
	public static int ELECTRICPILEHEAD_STOP = 9;// 停用
	public static int ELECTRICPILEHEAD_FAIL =12;//操作失败

	/** 预约状态 */
	public static int BESPOKE_CANCEL = 1;// 取消预约
	public static int BESPOKE_END = 2;// 结束预约
	public static int BESPOKE_CONTRACT = 3;// 续预约
	public static int BESPOKE_ING = 4;// 预约中
	public static int BESPOKE_AFFIRM_ING = 5;// 预约确认中
	public static int BESPOKE_AFFIRM_FAIL = 6;// 预约失败

	/** 订单状态 */
	public static int ORDER_STATUS_WAIT = 1;// 待支付
	public static int ORDER_STATUS_SUCCESS = 2;// 支付成功
	public static int ORDER_STATUS_DONE = 3;// 操作完成
	public static int ORDER_STATUS_DELETE = 0;// 订单删除
	
	/** 预约安装订单状态 */
	public static int ORDER_STATUS_INSTALL_WAIT = 1;// 待安装
	public static int ORDER_STATUS_INSTALL_SUCCESS = 2;// 安装成功
	public static int ORDER_STATUS_INSTALL_DONE = 3;// 操作完成

	/** 订单跟踪状态 */
	public static int ORDER_TRACK_BUY = 1;// 购买
	public static int ORDER_TRACK_PAY = 2;// 付款
	public static int ORDER_TRACK_APPEMENT = 3;// 预约安装
	public static int ORDER_TRACK_RECEIPT = 4;// 收货
	public static int ORDER_TRACK_INSTALL = 5;// 安装中
	public static int ORDER_TRACK_FINISH = 6;// 完成

	/**
	 * 电桩配置信息 配置类型 1-电桩状态 2-电桩枪口状态 3-电桩充电方式 4-电桩类型 5-电桩枪口连接方式 6-电桩额定功率 7-最大电流
	 * 8-桩体用途 9-搜索半径 10-支持预约
	 */
	public static int ELCTRC_CONFGURTN_STATE = 1;// 电桩状态
	public static int ELCTRC_CONFGURTN_MUZZLE = 2;// 电桩枪口状态
	public static int ELCTRC_CONFGURTN_CHARGE = 3;// 电桩充电方式
	public static int ELCTRC_CONFGURTN_TYPE = 4;// 电桩类型
	public static int ELCTRC_CONFGURTN_CONNECTOR = 5;// 电桩枪口连接方式
	public static int ELCTRC_CONFGURTN_POWER = 6;// 电桩额定功率
	public static int ELCTRC_CONFGURTN_ELECTRICITY = 7;// 最大电流
	public static int ELCTRC_CONFGURTN_USE = 8;// 桩体用途
	public static int ELCTRC_CONFGURTN_RADIO = 9;// 搜索半径
	public static int ELCTRC_CONFGURTN_MAKE = 10;// 支持预约

	public static int CONFIG_PARAMETER_EFFECTIVE = 0;// 启用
	public static int CONFIG_PARAMETER_INVALID = 1;// 禁用

	/**
	 * 充电交易类型*
	 */
	public static int CHARGE_TRANSTYPE_BROKEN = 1;// 断网上传
	public static int CHARGE_TRANSTYPE_ONLINE = 2;// 断网上传
	
	/**
	 * 充电时信息 工作状态*
	 */
	public static int CHARGEING_WORK_OFFLINE = 0;// 离线
	public static int CHARGEING_WORK_FAULT = 1;// 故障
	public static int CHARGEING_WORK_STANDBY = 2;// 待机
	public static int CHARGEING_WORKING = 3;// 工作
	public static int CHARGEING_WORK_UNDERVOLTAGE = 4;// 欠压故障
	public static int CHARGEING_WORK_OVERVOLTAGE = 5;// 过压故障
	public static int CHARGEING_WORK_FLOW = 6;// 过电流故障
	
	/**
	 * 充电时信息 连接确认开关状态*
	 */
	public static int CHARGEING_CONNECT_OPEN = 0;// 关
	public static int CHARGEING_CONNECT_CLOSE = 1;// 开
	
	/**
	 * 万马用户级别
	 *  备注：0：超级管理员 1：系统管理员 2:商家用户 3：个体商家用户
	 */
	public static String USER_LEVER_SUPER0 = "0";// 超级管理员
	public static String USER_LEVER_ADMIN1 = "1";// 系统管理员
	public static String USER_LEVER_merchant2 = "2";// 商家用户
	public static String USER_LEVER_personl3 = "3";// 个体商家用户

	public static String GEOCODE_MAP_KEY = "389880a06e3f893ea46036f030c94700";// 高德地图KEY
	public static String GEOCODE_WEBAPI_ROOTPATH = "http://restapi.amap.com/v3/geocode/";// 高德地图KEY
	public static Map<String, String> jwdMap = new HashMap<String, String>();
	
	public static ConcurrentMap<String,Object> bespkeState=new ConcurrentHashMap<String,Object>();//预约状态 key:预约主键  value:预约状态
	public static ConcurrentMap<String,Object> changeState=new ConcurrentHashMap<String,Object>();//充电状态 key:电桩编号|枪头编号  value:充电状态
	public static ConcurrentMap<String,Object> ELECTRICPILE_TO_GATE_INFO=new ConcurrentHashMap<String,Object>();//电桩编号-Gate服务器Id对应信息
	public static ConcurrentMap<String,Channel> GATE_TO_CHANNEL_INFO=new ConcurrentHashMap<String,Channel>();//Gate服务器Id-Gate服务器对象对应信息
	public static ConcurrentHashMap<String, PinCode> pinCodes = new ConcurrentHashMap<String, PinCode>();
	/**
	 * 已购商品安装地址状态 是否提交预约*
	 */
	public static int ORAD__BESPOKE_NO = 0;// 否
	public static int ORAD__BESPOKE_YES = 1;// 是
	
	/**
	 * 通过中国气象接口获取省份地区代码
	 */
	static {
		/*CacheEntity provinceCache=CacheManager.getCacheInfo("provinceCodeList");
		if(provinceCache==null){
			List<Map<String,Object>> provinceCodeList=CityUtil.getProvinceCodeByAarray();
			CacheManager.putCacheInfo("provinceCodeList", provinceCodeList);
		}*/
		/**
		 * 获取电桩GATE服务器列表
		 * //加载电桩状态为离线/上线的电桩
		 */
		ElectricPileListService electricPileListService=(ElectricPileListService)ApplicationContextUtils.getBean("electricPileListServiceImpl");
		List<TblElectricpile> eletricpileList=electricPileListService.getElectricPileGateList();
		for (TblElectricpile tblElectricpile : eletricpileList) {
			ELECTRICPILE_TO_GATE_INFO.put(tblElectricpile.getElpiElectricpilecode(), tblElectricpile.getGateId());
		}
	}
}

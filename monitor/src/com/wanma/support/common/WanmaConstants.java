package com.wanma.support.common;

import org.apache.commons.lang.StringUtils;

import com.wanma.support.utils.ApiUtil;

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
	public static String MULTI_TYPE_PARTNER_APPLY_IMG = "partnerApplyImage"; //合作伙伴申请页面图片
	public static String MULTI_TYPE_CAR_FIX_IMAGE = "carfix";//车辆维修图片
	public static String MULTI_TYPE_POWER_LIST_IMAGE = "powerListImage";// 电站列表图片
	public static String MULTI_TYPE_POWER_DETAIL_IMAGE = "powerDetailImage";// 电站详情图片

	public static String MULTI_LIST_IMAGE = "ListImage";// 商品管理的列表图片
	public static String MULTI_DETAIL_IMAGE = "DetailImage";// 商品管理的列表图片
	public static String SENSITIVE_WORD_LIST="sensitiveWordList";//敏感词列表
	public static String IMAGE_TEMP = "temp";// 图片临时区
	
	
	/**
	 * 电桩申购图片压缩 宽 高 400,400
	 */
	public static int PILE_APPLY_PICTRUE_W =400; 
	public static int PILE_APPLY_PICTRUE_H = 400;
	
	
	/**
	 * 用户头像图片压缩 宽 高 200,200
	 */
	public static int USER_HEAD_PICTRUE_W =200; 
	public static int USER_HEAD_PICTRUE_H = 200;
	
	/* 安卓ios安装包 */
	public static String MULTI_TYPE_APK = "apk";// 安卓获取ios安卓包

	/** 评论类型 */
	public static String PRODUCT_COMMENT_MALL = "2";// 用户头像

	/** 枪头状态 */
	public static int ELECTRICPILEHEAD_FREE = 0;// 空闲中
	public static int ELECTRICPILEHEAD_APPOINTMENT = 3;// 预约中
	public static int ELECTRICPILEHEAD_BATTERY = 6;// 充电中
	public static int ELECTRICPILEHEAD_STOP = 9;// 停用

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
	 * 已购商品安装地址状态 是否提交预约*
	 */
	public static int ORAD__BESPOKE_NO = 0;// 否
	public static int ORAD__BESPOKE_YES = 1;// 是
	
	public static String WANMA_PHONE="400-085-0006";//万马联系电话
	public static String API_TOKEN="";//api接口token值
	public static String API_ROOT="";
	static {
		if(StringUtils.isBlank(API_ROOT)){
			MessageManager manager=MessageManager.getMessageManager();
			API_ROOT=manager.getSystemProperties("apiRoot");
		}
		if(StringUtils.isBlank(API_TOKEN)){
			try {
				API_TOKEN=ApiUtil.getToken();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static int APPLY_EP_STATUS_APPLY=1;//申请建桩
	public static int APPLY_EP_STATUS_CHECK=2;//勘探现场 
	public static int APPLY_EP_STATUS_WORK=3;//现场施工
	public static int APPLY_EP_STATUS_SUCCESS=4;//建桩成功
	
	/**
	 *电桩地图查找 
	 */
	public static String API_URL_ELECTRIC_MAP="/app/electricPileMap/getElectricPileMapList.do";
	/**
	 * 电桩列表查找
	 */
	public static String API_URL_ELECTRIC_LIST="/app/electricPileList/getElectricPileListN.do";
	/**
	 * 电站详情
	 */
	public static String API_URL_POWERSTATION_DETAIL="/app/powerStationDetail/getPowerStationDetail.do";
	/**
	 *电桩详情
	 */
	public static String API_URL_ELECTRIC_DETAIL="/app/electricPileDetail/getElectricPileDetail.do";
	/**
	 * 电站描点详情
	 */
	public static String API_URL_ELECTRIC_DETAILMAP="/app/electricPileMap/getAnchorSummary.do";
	/**
	 * 预约/续约
	 */
	public static String API_URL_INSERT_BESPOKE="/app/bespoke/insertBespoke.do";
	/**
	 * 我的预约订单
	 */
	public static String API_URL_MYBESPOKE="/app/bespoke/selectBespokes.do";
	/**
	 * 预约详情
	 */
	public static String API_URL_BESPOKE_DETAIL="/app/bespoke/selectBespokeById.do";
	/**
	 * 取消预约
	 */
	public static String API_URL_CANCEL_BESPOKE="/app/bespoke/updateBespStatus.do";
	/**
	 * 获取设备保修类型
	 */
	public static String API_URL_FIND_CONFIGCONTENT="/app/paraconfig/findConfigContentList.do";
	/**
	 * 添加设备保修
	 */
	public static String API_URL_ADD_repair="/app/other/addTblEquipmentrepair.do";
	/**
	 * 获取电站评价
	 */
	public static String API_URL_POWERSTATION_COMMENTS="/app/psComment/findPsComments.do";
	/**
	 * 获取电桩评价
	 */
	public static String API_URL_ELECTRICPILE_COMMENTS="/app/epComment/findEpComments.do";
	/**
	 * 电站评价添加
	 */
	public static String API_URL_INSERT_PSCOMMENTS="/app/psComment/insertPsCommnet.do";
	/**
	 * 电桩评价添加
	 */
	public static String API_URL_INSERT_EPCOMMENTS="/app/epComment/insertEpCommnet.do";
	/**
	 * 电桩星评添加
	 */
	public static String API_URL_INSERT_EPSTAR="/app/epStar/insertEpStar.do";
	/**
	 * 电站星评添加
	 */
	public static String API_URL_INSERT_PSSTAR="/app/ psStar/insertPsStar.do";
	/**
	 * 我的收藏列表
	 */
	public static String API_URL_FAVORITE_LIST="/app/favorite/getFavoriteListN.do";
	/**
	 * 电桩、电站收藏添加 
	 */
	public static String API_URL_ADD_COLLECT="/app/usercollect/userFavorites.do";
	/**
	 * 收藏删除 
	 */
	public static String API_URL_DELETE_COLLECT="/app/favorite/removeFavorite.do";
	/**
	 * 扫描二维码 
	 */
	public static String API_URL_SELECT_PILEINFO="/app/electricPileDetail/selectPileInfo.do";
	/**
	 * 我的钱包
	 */
	public static String API_URL_MY_WALLET="/app/user/getMyWallet.do";
	/**
	 * 意见反馈增加
	 */
	public static String API_URL_ADD_FEEDBACK="/app/other/addTblFeedBack.do";
	/**
	 * 车辆维修列表
	 */
	public static String API_URL_CARGARAGE_LIST="/app/carGarage/list.do";
	/**
	 * 救援电话列表
	 */
	public static String API_URL_RESCUE_LIST="/app/rescue/list.do";
	/**
	 * 获取用户信息
	 */
	public static String API_URL_GETUSERINFO="/app/user/getUserInfo.do";
	/**
	 * 完善用户资料/申领充电卡
	 */
	public static String API_URL_MODIFYUSER="/app/user/modifyUser.do";
	/**
	 * 获取用户余额
	 */
	public static String API_URL_GET_USERAB="/app/user/getUserAB.do";
	/**
	 * 设置支付密码
	 */
	public static String API_URL_SET_PAYPWD="/app/user/setPayPwd.do";
	/**
	 * 检查支付密码有效性
	 */
	public static String API_URL_CHECK_PAYPWD="/app/user/checkPayPwd.do";
	/**
	 * 修改支付密码
	 */
	public static String API_URL_MOD_PAYPWD="/app/user/modPayPwd.do";
	/**
	 * 修改登陆密码
	 */
	public static String API_URL_MOD_PASSWORD="/app/user/modPassword.do";
	/**
	 * 充电订单列表/详情
	 */
	public static String API_URL_CHARGE_ORDERLIST="/app/chargeShow/chargeOrderList.do";
	/**
	 * 车型品牌获取
	 */
	public static String API_URL_FIND_PARACONFIG="/app/paraconfig/findParaconfigList.do";
	/**
	 * 车型获取
	 */
	public static String API_URL_FIND_CARINFO="/app/paraconfig/findCarinfoList.do";
	/**
	 * 活动列表页
	 */
	public static String API_URL_DYNAMIC_LIST="/app/dynamic/list.do";
	/**
	 * 我的反馈
	 */
	public static String API_URL_MY_FB="/app/other/getMyFB.do";
	/**
	 * 系统消息
	 */
	public static String API_URL_USERMESSAGE="/app/usermessge/mylist.do";
	/**
	 * 系统消息详情
	 */
	public static String API_URL_USERMESSAGE_DETAIL="/app/usemessage/myMessageContent.do";
	/**
	 * LED开关
	 */
	public static String API_URL_LED_CONTROL="/app/net/ledControl.do";
	/**
	 * 降地锁
	 */
	public static String API_URL_DOWN_PARKLOCK="/app/net/downParkLock.do";
	/**
	 * 版本更新
	 */
	public static String API_URL_VERSIONINFO="/app/other/getVersionInfo.do";
	/**
	 * 直流桩充电自检状态
	 */
	public static String API_URL_CHECKSTATUS="/app/chargeShow/dcSelfCheckStatus.do";
	/**
	 * 充电实时详情信息
	 */
	public static String API_URL_CHANGE_INFO="/app/chargeShow/getChangeInfoN.do";
	/**
	 * 立即充电
	 */
	public static String API_URL_BEGIN_CHARGE="/app/chargeShow/beginCharge.do";
	/**
	 * 结束充电
	 */
	public static String API_URL_FINISH_CHARGE="/app/chargeShow/finishCharge.do";
	/**
	 * 分享电桩
	 */
	public static String API_URL_PUBLISH_EP="/app/publishEp/add.do";
	/**
	 * 建桩申请列表
	 */
	public static String API_URL_APPLY_LIST="/app/apply/applyList.do";
	/**
	 * 充电结束页
	 */
	public static String API_URL_SELECT_CHARGE="/app/chargeShow/selectChargeData.do";
	/**
	 * 获取支付宝sigin值
	 */
	public static String API_URL_ALI_SIGN="/app/pay/aliSign.do";
	/**
	 * 微信预支付
	 */
	public static String API_URL_WX_TEMPORDER="/app/pay/wxTempOrder.do";
	/**
	 * 登录
	 */
	public static String API_URL_LOGIN="/app/user/login.do";
	/**
	 * 退出登录
	 */
	public static String API_URL_LOGOUT="/app/user/logout.do";
	/**
	 * 	注册
	 */
	public static String API_URL_REGIST="/app/user/regist.do";
	/**
	 * 	获取短信验证码
	 */
	public static String API_URL_GETAUTHCODE="/app/user/getAuthCode.do";
	
	/**
	 * 	判断短信验证码是否正确
	 */
	public static String API_URL_CHECKAUTHCODE="/app/user/checkAuthCode.do";
	/**
	 * 	检查手机号是否注册
	 */
	public static String API_URL_CHECKPHONE="/app/user/checkphone.do";
	/**
	 * 	找回密码
	 */
	public static String API_URL_RESETPASSWORD="/app/user/resetPasswrod.do";
	
}

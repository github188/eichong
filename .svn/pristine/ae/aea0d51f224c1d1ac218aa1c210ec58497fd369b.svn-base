/**
 * 全局配置文件
 * @type {{projectName: string, begin: number, pageSize: number}}
 */
var config = {
    projectName: '/wmacw',
    begin: 1, //当前第几页，从1开始
    pageSize: 9 //默认分页大小
};

/**
 * static value
 * @type {string}
 */
config.pageServer = location.protocol + '//' + location.host + config.projectName + '/wmacw/'; //页面服务器地址
config.imageServer = location.protocol + '//' + location.host + config.projectName;//图片存放地址
config.fileServer = location.protocol + '//' + location.host + config.projectName;//文件存放地址
config.commonInterfaceServer = location.protocol + '//' + location.host + config.projectName + '/app/'; //接口地址
config.webInterfaceServer = location.protocol + '//' + location.host + config.projectName + '/web/'; //接口地址
config.defaultImg = config.imageServer + '/static/images/default.jpg'; //默认图片配置
config.defaultUserImg = '/static/images/default-user-icon.jpg'; //默认图片配置
config.defaultLoadImg = config.imageServer + '/static/images/ajax-loader.gif'; //默认加载中图片
config.urlSuffix = '.do';
config.hbaseUrl = 'http://10.9.2.109/hbase/itf/hbase';
//config.hbaseUrl = 'http://10.9.2.109/hbase/itf/hbase';

//电桩模块
config.MElectricModel = config.pageServer + 'electric/';
//服务与支持模块
config.MSupportModel = config.pageServer + 'support/';
//新能源模块
config.MEnergyModel = config.pageServer + 'energy/';
//个人中心首页模块
config.MMyModel = config.pageServer + 'my/';

/*---------------------静态页面----------------*/

//首页
config.PIndex = createRootPageUrl('index');
//注册
config.PRegist = createRootPageUrl('regist');
//登录
config.PLogin = createRootPageUrl('login');
//忘记密码
config.PReset = createRootPageUrl('reset');

//选择城市
config.PChooseCity = createRootPageUrl('chooseCity');
//app下载
config.PAppDownload = '';


//搜电桩
config.PElectricResult = createModelPageUrl(config.MElectricModel, 'result');
//电桩查找
config.PElectricSearch = createModelPageUrl(config.MElectricModel, 'search');
//充电点详细
config.PElectricPlant = createModelPageUrl(config.MElectricModel, 'plant');
//电桩详细
config.PElectricPile = createModelPageUrl(config.MElectricModel, 'pile');
//评价详细
config.PElectricEvaluate = createModelPageUrl(config.MElectricModel, 'evaluate');
//预约详细
config.PElectricOrder = createModelPageUrl(config.MElectricModel, 'order');
//预约支付
config.PElectricPay = createModelPageUrl(config.MElectricModel, 'pay');
//预约成功
config.PElectricSucceed = createModelPageUrl(config.MElectricModel, 'succeed');


//我的资料
config.PMyCenter = createModelPageUrl(config.MMyModel, 'info/center');
//我的钱包
config.PMyPurse = createModelPageUrl(config.MMyModel, 'wallet/purse');
//消费明细
config.PMyPayDetail = createModelPageUrl(config.MMyModel, 'wallet/payDetail');
//我的预约
config.PMyOrder = createModelPageUrl(config.MMyModel, 'book/order');
//完善资料
config.PModifyInfo = createModelPageUrl(config.MMyModel, 'perfect');
//历史预约
config.PMyHisOrder = createModelPageUrl(config.MMyModel, 'book/hisOrder');
//积分商城
config.PMyIntegral = createModelPageUrl(config.MMyModel, 'integral');
//我的订单
config.PMyBill = createModelPageUrl(config.MMyModel, 'order/bill');
//我的收藏
config.PMyCollect = createModelPageUrl(config.MMyModel, 'collect/collect');
//我的评价
config.PMyEvaluate = createModelPageUrl(config.MMyModel, 'comment/evaluate');
//我的足迹
config.PMyFootprint = createModelPageUrl(config.MMyModel, 'footprint');


//新能源首页
config.PEnergyIndex = createModelPageUrl(config.MEnergyModel, 'index');
//新能源新品区,折扣区
config.PEnergyProductArea = createModelPageUrl(config.MEnergyModel, 'product');
//新能源新品,折扣产品详细
config.PEnergyProductDetail = createModelPageUrl(config.MEnergyModel, 'detail');
//获取用户收货地址
config.PAddress = createModelPageUrl(config.MEnergyModel, 'address');
//新增用户收货地址
config.PAddAddress = createModelPageUrl(config.MEnergyModel, 'addAdrs');
//使用帮助
config.PSupportHelp = '';
//使设备保修
config.PSupportRepair = createModelPageUrl(config.MSupportModel, 'repair');
//意见反馈
config.PSupportSuggest = createModelPageUrl(config.MSupportModel, 'suggest');
//正在建设中
config.Pbuilding = createModelPageUrl(config.MEnergyModel, 'building');
//加入购物车
config.PShopCar = createModelPageUrl(config.MEnergyModel, 'shopCar');
//立即购买
config.PPromptlyBuy = createModelPageUrl(config.MEnergyModel, 'confirmOrder');
//支付成功
config.PPaySucces = createModelPageUrl(config.MEnergyModel, 'bookFix');
//预约安装
config.POrderInstallDetail = createModelPageUrl(config.MEnergyModel, 'orderInstallDetail');
//预约成功
config.PInstallSuccess = createModelPageUrl(config.MEnergyModel, 'installSuccess');

/*---------------------接口地址----------------*/

//登录
config.ILogin = createWebInterfaceUrl('user/login');
//登出
config.ILogout = createWebInterfaceUrl('user/logout');
//注册
config.IRegist = createWebInterfaceUrl('user/regist');
//重置密码
config.IReset = createWebInterfaceUrl('user/resetPasswrod');
//完善资料
config.IUpdateUserInfo = createWebInterfaceUrl('user/updateUserInfo');
//验证码
config.IValidCode = createWebInterfaceUrl('user/getValidCode');
//获取用户信息
config.IGetUserInfo = createWebInterfaceUrl('user/getUserInfo');
//短信接口
config.ISendMsg = createWebInterfaceUrl('user/sendMsg');


//电桩查找-筛选条件
config.IFindScreen = createWebInterfaceUrl('configContent/getScreenTypeList');
//电桩搜索
config.IFindElectricResult = createWebInterfaceUrl('electricPileList/getSearchElectricList');
//电桩查找-列表模式
config.IFindElectricSearch = createWebInterfaceUrl('electricPileList/getElectricPileList');
//电桩查找-地图模式
config.IFindElectricMapSearch = createWebInterfaceUrl('electricPileMap/getElectricPileMapList');
//电桩查找-实时监控
config.ElectricMap = createWebInterfaceUrl('electricPileMonitor/getElectricPileMap');
//充电点详情
config.IGetElectricPlant = createWebInterfaceUrl('powerStationDetail/getPowerStationDetail');
//电桩详情
config.IGetElectricPile = createWebInterfaceUrl('electricPileDetail/getElectricPileDetail');
//评价列表
config.IFindElectricEvaluate = createWebInterfaceUrl('productComment/findProComments');
//新增评价
config.IInsertElectricEvaluate = createWebInterfaceUrl('productComment/insertProCommnet');
//预约详情
config.IGetElectricOrder = createWebInterfaceUrl('bespoke/selectBespoke');
//支付详情
config.IGetElectricPay = createWebInterfaceUrl('bespoke/selectElectInfo');
//预约支付
config.IInsertElectricPay = createWebInterfaceUrl('bespoke/insertBespoke');
//相关充电点
config.IGetRelated = createWebInterfaceUrl('electricPileList/getRelatedList');


//我的资料
config.IGetMyCenter = createWebInterfaceUrl('user/getUserInfo');
//一键升级
config.IUpdateMyCenter = createWebInterfaceUrl('user/upgradeoUser');
//我的钱包
config.IGetMyPurse = createWebInterfaceUrl('user/getMyWallet');
//消费明细
config.IFindMyPayDetail = createWebInterfaceUrl('user/findMyWallet');
//我的预约
config.IGetMyOrder = createWebInterfaceUrl('bespoke/getBespokes');
//历史预约
config.IFindMyHisOrder = createWebInterfaceUrl('bespoke/selectBespokes');
//取消预约
config.IUpdateMyHisOrder = createWebInterfaceUrl('bespoke/updateBespStatus');
//积分商城
config.IGetMyIntegral = createWebInterfaceUrl('user/getUserInfo');
//我的订单
config.IFindMyBill = createWebInterfaceUrl('myOrder/getMyOrder');
//我的收藏
config.IFindMyCollect = createWebInterfaceUrl('favorite/getFavoriteList');
//删除收藏
config.IRemoveMyCollect = createWebInterfaceUrl('favorite/removeFavorite');
//我的评价
config.IFindMyEvaluate = createWebInterfaceUrl('user/getMyComment');
//删除评价
config.IRemoveMyEvaluate = createWebInterfaceUrl('user/removeMyComment');
//我的足迹
config.IGetMyFootprint = createWebInterfaceUrl('user/getMyFootPrint');
//充值
config.IAddMoney = createWebInterfaceUrl('payRecharge/userRecharge');

//新能源热门分类列表
config.IEnergyCategory = createCommonInterfaceUrl('mallCategory/getHotRecommend');
//新能源新品,折扣区
config.IEnergyNewProductAndDiscount = createWebInterfaceUrl('product/findProducts');
//新品,打折产品详细
config.IEnergyProductDetail = createWebInterfaceUrl('product/findProductDetail');
//加入购物车
config.IInsertIntoCart = createWebInterfaceUrl('shoppingcart/insertIntoCart');
//我的购物车列表
config.IFindMyCart = createWebInterfaceUrl('shoppingcart/findMyCart');
// 获取订单中商品信息
config.selectProductsByOrderId = createWebInterfaceUrl('myOrder/selectProductsByOrderId');
//收藏
config.ICollect = createWebInterfaceUrl('favorite/insertFavorite');
//获取评论
config.ICommentList = createCommonInterfaceUrl('productComment/findProComments');
//热门推荐
config.IGetHotRecommendDetail = createWebInterfaceUrl('mallCategory/getHotRecommendDetail');
//购物车页面，结算新增订单
config.IOrder = createWebInterfaceUrl('myOrder/insertShopOrderInfo');
//商品详情页，立即购买，新增订单
config.IProductOrder = createWebInterfaceUrl('myOrder/insertOrderInfo');
// 获取默认收货地址
config.IAddress = createWebInterfaceUrl('userAddress/findDefault');
//获取所有收货地址
config.IAddresses = createWebInterfaceUrl('userAddress/findAddresses');
//根据主键获取地址
config.IFindAddress = createWebInterfaceUrl('userAddress/getAddress');
//新增用户地址
config.IAddAddress = createWebInterfaceUrl('userAddress/insertAddress');
//编辑用户地址
config.IEditAddress = createWebInterfaceUrl('userAddress/updateAddress');
//删除地址
config.IDeleteAddress = createWebInterfaceUrl('userAddress/deleteAddress');
//设置地址为默认地址
config.IUpdateIsDefault = createWebInterfaceUrl('userAddress/updateIsDefault');
//结算
config.IUpdateOrder = createWebInterfaceUrl('myOrder/updateOrder');
//订单跟踪信息
config.IFindOrderTracks = createWebInterfaceUrl('myOrder/findOrderTracks');
// 订单
config.IGetOrder = createWebInterfaceUrl('myOrder/getOrder');


//服务与支持
config.ISuggest = createWebInterfaceUrl('other/addTblFeedBack');
//设备保修信息提交
config.IRepair = createWebInterfaceUrl('other/addTblEquipmentrepair');
//获取车型号
config.ICarModel = createWebInterfaceUrl('paraconfig/findCarinfoList');


//省份
config.IProvinceList = createWebInterfaceUrl('city/getProvinces');
//城市
config.ICityList = createWebInterfaceUrl('city/getCityList');
//区域
config.IAreaList = createWebInterfaceUrl('city/getAreas');


//字典接口
config.IDictionary = createWebInterfaceUrl('paraconfig/findParaconfigList');

//获取所在地区
config.IGetPoint = createWebInterfaceUrl('point/getPoint');
//安装预约
config.IOrderInstall = createWebInterfaceUrl('appointment/insertAppointment');

/**
 * 创建静态页面url
 * @param url
 * @returns {string}
 */
function createRootPageUrl(url) {
    return config.pageServer + (url ? url : '') + config.urlSuffix;
}

/**
 * 创建模块url
 * @param model
 * @param url
 * @returns {string}
 */
function createModelPageUrl(model, url) {
    return ((model && url) ? model + url : '') + config.urlSuffix;
}

/**
 * 创建公用接口url
 * @param url
 * @returns {string}
 */
function createCommonInterfaceUrl(url) {
    return config.commonInterfaceServer + (url ? url : '') + config.urlSuffix;
}

/**
 * 创建web端接口url
 * @param url
 * @returns {string}
 */
function createWebInterfaceUrl(url) {
    return config.webInterfaceServer + (url ? url : '') + config.urlSuffix;
}

/**
 * 评论类型
 * @type {{}}
 */
var CommentType = {
    Electric: 1, /**电桩**/
    Mall: 2, /**商城**/
    Recharge: 3, /**充值**/
    ElectricPlant: 4 /**充电点**/
};

/**
 * 后台所有枚举
 * @type {{}}
 */
var dictionary = {
    /**
     * 配置类型
     */
    paraType: {
        param: 'paraType=',
        carCard: 1,
        radius: 2,
        repair: 3,
        userType: 4
    }
};
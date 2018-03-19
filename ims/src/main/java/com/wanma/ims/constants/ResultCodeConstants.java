package com.wanma.ims.constants;


/**
 * resultCode 公共类
 * version 1_0
 */
public class ResultCodeConstants {
	
	/**
	 * 返回状态码 ： 成功 1000   失败2001  参数请求错误 2002 
	 *         绑定错误,对象转换错误 2003 异常 5001 会话超时 9001 
	 */ 
	public static final String SUCCESS = "1000"; 
	public static final String FAILED = "2001";
	public static final String ERROR_PARAM  = "2002";
	public static final String EXCEPTION  = "5001";
	public static final String SESSION_OUT  = "9001";
	public static final String NO_PERMISSION = "5000";
	public static final String ERROR_PAGE = "400";
	
	/**
	 * 请求参数 
	 */
	public static final String ERROR_MSG_REQUEST_PARAM = "请求参数错误";

	/**
	 * 导出提示 
	 */
	public static final String ERROR_MSG_EXPORT_DATA_EMPTY = "没有可导出的数据";
	public static final String ERROR_MSG_EXPORT_MAX_DATA = "最多导出5W条数据";

	/**
	 * 登录校验信息-10000 用户名为空 -10001 密码为空 -10003 密码不正确 -10005 用户不存在 -10006 验证码不正确 -10007 登陆次数超过5次
	 */ 
	public static final String ERROR_MSG_EMPTY_USER = "-10000";
	public static final String ERROR_MSG_EMPTY_PASSWORD = "-10001";
	public static final String ERROR_MSG_INVALID_USER = "-10002";
	public static final String ERROR_MSG_INVALID_PASSWORD = "-10003";
	public static final String ERROR_MSG_INVALID_PARAMETER = "-10004";
	public static final String ERROR_MSG_INVALID_LOGIN = "-10005";
	public static final String ERROR_MSG_INVALID_AUTH_CODE = "-10006";
	public static final String ERROR_MSG_LOGIN_MAX_FIVE = "-10007";

	/** 账单科目/账户关系 */
	public static final String ERROR_MSG_EMPTY_SESSION_USER = "登录用户不存在！"; 
	public static final String ERROR_MSG_ERROR_ADD = "新增失败!"; 
	public static final String ERROR_MSG_ERROR_MODIFY = "修改失败!";
	public static final String ERROR_MSG_ERROR_REMOVE = "删除失败!"; 
	public static final String ERROR_MSG_EMPTY_USER_INFO = "用户信息不允许为空！"; 
	public static final String ERROR_MSG_REFUND_MONEY = "退款失败";
	
	/** 账单科目  */
	public static final String ERROR_MSG_EMPTY_BILL_ACCOUNT_NAME = "账单科目名称不允许为空!"; 
	public static final String ERROR_MSG_EMPTY_MODIFY_BILL_ACCOUNT = "修改账单科目不存在!";
	public static final String ERROR_MSG_REMOVE_BILL_ACCOUNT_CONFIG_RELA = "账单科目已经关联账务配置!";
	public static final String ERROR_MSG_REMOVE_BILL_ACCOUNT_RELATION = "账单科目已经关联账户关系!";
	
	/** 账户关系 */
	public static final String ERROR_MSG_EMPTY_MODIFY_ACCOUNT_RELATION = "修改账户关系不存在!";
	
	/** 账务配置 */
	public static final String ERROR_MSG_EMPTY_MODIFY_ACCOUNT_CONFIG_RELA = "修改账务配置不存在!";
	
	/** 资金账户 */
	public static final String ERROR_MSG_EMPTY_MODIFY_ACCOUNT = "修改资金账户不存在!";
	public static final String ERROR_MSG_EMPTY_ADD_ACCOUNT = "创建资金账户失败!";
	
	/** 费率管理 */
	public static final String ERROR_MSG_EMPTY_RATEINFO = "费率信息不能为空!";
	public static final String ERROR_MSG_EMPTY_MODIFY_RATEINFO = "修改费率信息不存在!";
	public static final String ERROR_MSG_ROMOVE_RELATION_RATEINFO = "删除失败，费率信息已经下发到桩!";
	
	
	/** 
	 * 资产管理 1.集中器管理   2.充电点管理  3.电桩管理
	 */
	public static final String ERROR_MSG_ADD_POWERSTATION = "新增充电点失败";
	public static final String ERROR_MSG_MODIFY_POWERSTATION = "修改充电点失败";
	public static final String ERROR_MSG_REMOVE_POWERSTATION = "删除充电点失败";
	
	public static final String ERROR_MSG_ADD_CONCENTRATOR = "新增集中器失败";
	public static final String ERROR_MSG_MODIFY_CONCENTRATOR = "修改集中器失败";
	public static final String ERROR_MSG_REMOVE_CONCENTRATOR = "删除集中器失败";
	
	/** 
	 * 公司管理
	 */
	public static final String ERROR_MSG_ADD_COMPANY = "新增公司失败";
	public static final String ERROR_MSG_MODIFY_COMPANY = "修改公司失败";
	public static final String ERROR_MSG_MODIFY_COMPANY_ACCOUNT = "修改公司资金账户失败";
	public static final String ERROR_MSG_MODIFY_COMPANY_FIN_ACCOUNT = "创建公司付费策略失败";
	public static final String ERROR_MSG_MODIFY_COMPANY_SAVING_ACCOUNT = "创建公司收款失败";
	public static final String ERROR_MSG_DISABLE_COMPANY = "禁用公司失败";
	public static final String ERROR_MSG_ENABLE_COMPANY = "解禁公司失败";
	public static final String ERROR_MSG_COMPANY_UNIQUE_NUMBER = "公司标识已存在";
	public static final String ERROR_MSG_COMPANY_UNIQUE_NAME = "公司名称已存在";
	
	/**
	 * 用户基表 
	 */
	public static final String ERROR_MSG_ADD_USER = "新增用户基表失败";
	/** 
	 * 管理员管理
	 */
	public static final String ERROR_MSG_ADD_USER_ADMIN = "新增管理员失败";
	public static final String ERROR_MSG_MODIFY_USER_ADMIN = "修改管理员失败";
	public static final String ERROR_MSG_DISABLE_USER_ADMIN = "禁用管理员失败";
	public static final String ERROR_MSG_ENABLE_USER_ADMIN = "解禁管理员失败";
	
	/**
	 * 管理员充电桩数据权限 
	 */
	public static final String ERROR_MSG_NO_PERMISSION = "您没有该权限";
	public static final String ERROR_MSG_NO_ELECTRICPILE = "桩编码不存在";
	public static final String ERROR_MSG_NO_POWERSTATION = "充电站不存在或该充电站下没有桩";
	public static final String ERROR_MSG_ELECTRICPILE_REPEAT = "您已经拥有了该权限";
	public static final String ERROR_MSG_ADD_ADMIN_EP = "管理员设置桩权限失败";
	public static final String ERROR_MSG_ADD_ADMIN_PS = "管理员批量设置桩权限失败";
	public static final String ERROR_MSG_ADD_ADMIN_COMPANY_EP = "管理员设置公司桩权限失败";
	public static final String ERROR_MSG_REMOVE_ADMIN_EP = "管理员解绑桩权限失败";
	
	/**
	 * 角色管理 
	 */
	public static final String ERROR_MSG_ADD_ROLE = "角色添加失败";
	public static final String ERROR_MSG_ADD_USER_ROLE = "添加管理员角色失败";
	public static final String ERROR_MSG_MODIFY_USER_ROLE = "管理员角色修改失败";
	
	/**
	 * 产品型号
	 */
	public static final String ERROR_MSG_UNIQUE_TYPESPAN = "产品型号已存在";
	public static final String ERROR_MSG_ADD_TYPESPAN = "新增产品型号失败";
	public static final String ERROR_MSG_REPEAT_BOMLIST = "新增产品型号失败";
	
	/**
	 * 等级
	 */
	public static final String ERROR_MSG_LEVEL_ISCOVER  = "8888";

	/**
	 * vin码
	 */
	public static final String ERROR_MSG_USER_VIN_RELA_DELECT = "删除用户vin码关系失败";
	
//	public static final String ERROR_MSG_COMPANY_UNIQUE_NUMBER = "公司标识已存在";
//	public static final String ERROR_MSG_COMPANY_UNIQUE_NAME = "公司名称已存在";
}
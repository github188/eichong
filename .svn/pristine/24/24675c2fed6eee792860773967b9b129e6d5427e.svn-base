/*
Navicat MySQL Data Transfer

Source Server         : 开发环境-ims
Source Server Version : 50625
Source Host           : 10.9.2.238:3306
Source Database       : ims

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2017-09-05 14:54:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for count_admin_ep_rela
-- ----------------------------
DROP TABLE IF EXISTS `count_admin_ep_rela`;
CREATE TABLE `count_admin_ep_rela` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `admin_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '管理员ID',
  `powerstation_id` bigint(11) DEFAULT '0' COMMENT '充电点ID',
  `electricpile_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '桩ID',
  `electricpile_code` varchar(50) NOT NULL DEFAULT '' COMMENT '电桩编码',
  `admin_area` varchar(32) NOT NULL DEFAULT '' COMMENT '管理范围 eg:海淀区',
  `is_del` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18390 DEFAULT CHARSET=utf8 COMMENT='渠道与桩数据权限表';

-- ----------------------------
-- Table structure for fav_account_favourable_rela
-- ----------------------------
DROP TABLE IF EXISTS `fav_account_favourable_rela`;
CREATE TABLE `fav_account_favourable_rela` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cpy_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '渠道ID',
  `bill_account_id` bigint(11) NOT NULL COMMENT '账单科目ID',
  `favourable_id` bigint(11) NOT NULL COMMENT '优惠ID',
  `priority` smallint(1) NOT NULL DEFAULT '0' COMMENT '优惠优先级 1-N 升序',
  `is_allowed` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否可用 0.否 1.是',
  `is_del` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  `gmt_creator` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账目优惠关系表';


-- ----------------------------
-- Table structure for fav_favourable
-- ----------------------------
DROP TABLE IF EXISTS `fav_favourable`;
CREATE TABLE `fav_favourable` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `favourable_code` varchar(32) NOT NULL COMMENT '优惠编码',
  `favourable_name` varchar(32) DEFAULT '' COMMENT '优惠',
  `favourable_type` smallint(1) NOT NULL DEFAULT '0' COMMENT '0.优惠券 1.包月 2.特别优惠',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改时间',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='优惠类型';

-- ----------------------------
-- Table structure for fin_account
-- ----------------------------
DROP TABLE IF EXISTS `fin_account`;
CREATE TABLE `fin_account` (
  `account_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `account_no` varchar(32) NOT NULL DEFAULT '' COMMENT '资金账户号',
  `account_pwd` varchar(32) NOT NULL DEFAULT '' COMMENT '支付密码',
  `account_balance` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '余额',
  `trade_type` int(4) NOT NULL DEFAULT '0' COMMENT '结算方式 1.后付费 2.预付费',
  `account_warn` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '预警金额',
  `account_status` int(4) NOT NULL DEFAULT '0' COMMENT '1.正常 2.冻结 3.删除',
  `is_del` int(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `index_account_no` (`account_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=30438 DEFAULT CHARSET=utf8 COMMENT='资金账户表';

-- ----------------------------
-- Table structure for fin_account_config_rela
-- ----------------------------
DROP TABLE IF EXISTS `fin_account_config_rela`;
CREATE TABLE `fin_account_config_rela` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cpy_id` bigint(11) NOT NULL COMMENT '渠道ID',
  `bill_account_id` bigint(11) NOT NULL COMMENT '账单科目ID',
  `payment_rule` smallint(1) NOT NULL DEFAULT '0' COMMENT '付费策略 1.扣大账户 2.扣自己 3.为小账户配资',
  `is_del` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pk_id`),
  KEY `idx_cpy_id` (`cpy_id`) USING BTREE,
  KEY `idx_bill_account_id` (`bill_account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=303 DEFAULT CHARSET=utf8 COMMENT='账务配置';

-- ----------------------------
-- Table structure for fin_account_relation
-- ----------------------------
DROP TABLE IF EXISTS `fin_account_relation`;
CREATE TABLE `fin_account_relation` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(11) NOT NULL COMMENT '用户ID',
  `bill_account_id` bigint(11) NOT NULL COMMENT '账单科目ID',
  `account_id` bigint(11) NOT NULL COMMENT '资金账户ID',
  `priority` smallint(1) NOT NULL DEFAULT '0' COMMENT '排序 1-N 1.优先执行 N.最后执行',
  `is_del` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pk_id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_bill_account_id` (`bill_account_id`) USING BTREE,
  KEY `idx_account_id` (`account_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='账目关系';

-- ----------------------------
-- Table structure for fin_bill_account
-- ----------------------------
DROP TABLE IF EXISTS `fin_bill_account`;
CREATE TABLE `fin_bill_account` (
  `pk_id` bigint(11) NOT NULL COMMENT '主键',
  `bill_account_code` varchar(32) NOT NULL DEFAULT '' COMMENT '账单科目编码',
  `bill_account_name` varchar(32) NOT NULL DEFAULT '' COMMENT '账单科目',
  `is_del` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.删除',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pk_id`),
  KEY `idx_bill_account_code` (`bill_account_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账单科目';

-- ----------------------------
-- Table structure for tbl_car_vin_fee
-- ----------------------------
DROP TABLE IF EXISTS `tbl_car_vin_fee`;
CREATE TABLE `tbl_car_vin_fee` (
  `pk_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `cv_vin_code` varchar(32) DEFAULT NULL COMMENT 'VIN码',
  `rate_id` bigint(11) DEFAULT '0' COMMENT '费率ID',
  `is_del` int(4) DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  `cv_license_number` varchar(32) DEFAULT '0' COMMENT '车牌号',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='VIN码优惠表';

-- ----------------------------
-- Table structure for tbl_car_vin_rela
-- ----------------------------
DROP TABLE IF EXISTS `tbl_car_vin_rela`;
CREATE TABLE `tbl_car_vin_rela` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键，唯一',
  `cv_vin_code` varchar(32) NOT NULL COMMENT 'VIN码',
  `cpy_id` bigint(11) NOT NULL COMMENT '公司ID',
  `is_used` int(4) NOT NULL DEFAULT '0' COMMENT '是否可充电 0.否 1.是',
  `is_del` int(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  `creator` varchar(32) DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pk_id`),
  UNIQUE KEY `index_cv_vin_code` (`cv_vin_code`) USING BTREE,
  KEY `index_cv_vin_code2` (`cv_vin_code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='VIN码鉴权表';

-- ----------------------------
-- Table structure for tbl_integral
-- ----------------------------

DROP TABLE IF EXISTS `tbl_integral`;
CREATE TABLE `tbl_integral` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '积分ID',
  `user_id` bigint(11) NOT NULL COMMENT '用户ID',
  `total_integrals` bigint(8) NOT NULL COMMENT '总积分',
  `available_integrals` bigint(8) NOT NULL COMMENT '可用积分',
  `used_integrals` bigint(8) DEFAULT '0' COMMENT '已用积分',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `modifier` varchar(32) DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_id`),
  KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='积分表';

##积分明细表
DROP TABLE IF EXISTS `tbl_integral_details`;
CREATE TABLE `tbl_integral_details` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '积分明细ID',
  `integral_id` bigint(11) NOT NULL COMMENT '积分ID',
  `integral_activity_id` bigint(11) NOT NULL COMMENT '积分活动ID',
  `money_involved` decimal(8,2) DEFAULT '0.00' COMMENT '涉及金额',
  `direction` smallint(1) NOT NULL DEFAULT '0' COMMENT '积分变化方向（0：增加；1：减少）',
  `integral_value` bigint(8) NOT NULL COMMENT '积分值',
  `integral_date` datetime NOT NULL COMMENT '积分创建时间',
  `charging_order_id` varchar(32) DEFAULT NULL COMMENT '关联订单Id（充电消费订单Id、兑吧流水号等）',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`pk_id`),
  KEY `idx_integral_activity_id` (`integral_activity_id`) USING BTREE,
  KEY `idx_integral_id` (`integral_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COMMENT='积分明细表';

##积分活动表
DROP TABLE IF EXISTS `tbl_integral_activity`;
CREATE TABLE `tbl_integral_activity` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '积分活动ID',
  `activity_name` varchar(50) NOT NULL COMMENT '活动名称',
  `direction` smallint(1) NOT NULL COMMENT '积分方向（0：获取；1：消耗）',
  `max_integrals` bigint(11) DEFAULT '0' COMMENT '活动上限积分',
  `launch_integrals` bigint(11) DEFAULT '0' COMMENT '活动送出积分',
  `residues_integrals` bigint(11) DEFAULT '0' COMMENT '活动剩余积分',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `modifier` varchar(32) DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='积分活动表';

##积分规则
DROP TABLE IF EXISTS `tbl_integral_rules`;
CREATE TABLE `tbl_integral_rules` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '积分规则ID',
  `integral_activity_id` bigint(8) NOT NULL COMMENT '积分活动ID',
  `highest_priority` smallint(1) DEFAULT '0' COMMENT '最高优先级（0：否；1：是）',
  `activity_status` smallint(1) NOT NULL COMMENT '活动状态（0：开启；1：关闭）',
  `fixed_integral_value` bigint(8) DEFAULT NULL COMMENT '固定积分值',
  `ratio_integral_value` bigint(8) DEFAULT NULL COMMENT '比例积分值',
  `start_date` datetime NOT NULL COMMENT '活动开始日期',
  `end_date` datetime NOT NULL COMMENT '活动结束日期',
  `effective_times` smallint(1) DEFAULT NULL COMMENT '有效次数（1：第一次时赠送积分；2：每次都赠送）',
  `is_whole` smallint(1) DEFAULT NULL COMMENT '是否全国有效（0：否；1：是）',
  `province_id` varchar(20) DEFAULT NULL COMMENT '省ID',
  `city_id` varchar(20) DEFAULT NULL COMMENT '市ID',
  `powerStation_id` bigint(11) DEFAULT NULL COMMENT '电站ID',
  `min_value` bigint(8) DEFAULT NULL COMMENT '充电消费满足最小金额，才开始赠送、可以积分抵扣',
  `is_choice` smallint(1) DEFAULT NULL COMMENT '按照充值/消费金额赠送抽奖机会（0：启用；1：未启用）',
  `choice_money` bigint(8) DEFAULT NULL COMMENT '每消费多少金额赠送一次抽奖机会',
  `is_share_choice` smallint(1) DEFAULT '0' COMMENT '充电消费分享赠送积分、抽奖机会（0：不赠送；1：首次分享； 2：每次分享）',
  `share_integral_value` bigint(8) DEFAULT NULL COMMENT '分享成功后赠送积分值',
  `share_choice` smallint(1) DEFAULT NULL COMMENT '分享成功后赠送一次抽奖机会（0：是；1：否）',
  `contents` varchar(256) NOT NULL COMMENT '积分规则内容',
  `creator` varchar(32) NOT NULL COMMENT '创建人',
  `modifier` varchar(32) DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_id`),
  KEY `idx_integral_activity_id` (`integral_activity_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='积分规则';


##积分规则扩展表
DROP TABLE IF EXISTS `tbl_integral_rules_extensions`;
CREATE TABLE `tbl_integral_rules_extensions` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '积分规则扩展ID',
  `integral_rules_id` bigint(8) NOT NULL COMMENT '积分规则ID',
  `electricPile_id` int(11) DEFAULT NULL COMMENT '桩ID',
  `coupon_variety_id` int(11) DEFAULT NULL COMMENT '优惠券品种Id',
  `integral_value` bigint(8) DEFAULT NULL COMMENT '消费的积分值',
  PRIMARY KEY (`pk_id`),
  KEY `idx_integral_rules_id` (`integral_rules_id`) USING BTREE,
  KEY `idx_electricPile_id` (`electricPile_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分规则扩展表';


-- ----------------------------
-- Table structure for tbl_level
-- ----------------------------
DROP TABLE IF EXISTS `tbl_level`;
CREATE TABLE `tbl_level` (
  `level_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `level_seq` varchar(32) NOT NULL DEFAULT '' COMMENT '序号',
  `level_name` varchar(32) NOT NULL DEFAULT '' COMMENT '等级名称',
  `is_default` int(4) NOT NULL DEFAULT '0' COMMENT '是否默认 0.否 1.是',
  `cpy_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '渠道ID',
  `is_del` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`level_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='等级表';

-- ----------------------------
-- Table structure for tbl_rate_unique_rela
-- ----------------------------
DROP TABLE IF EXISTS `tbl_rate_unique_rela`;
CREATE TABLE `tbl_rate_unique_rela` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cpy_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '渠道ID',
  `electricpile_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '桩ID',
  `level_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '级别ID',
  `rateinfo_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '费率ID',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=92 DEFAULT CHARSET=utf8 COMMENT='唯一费率表';

-- ----------------------------
-- Table structure for tbl_tag
-- ----------------------------
DROP TABLE IF EXISTS `tbl_tag`;
CREATE TABLE `tbl_tag` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `type` int(1) NOT NULL COMMENT '标签类型，0用户标签，1卡标签',
  `name` varchar(45) NOT NULL COMMENT '标签名',
  `is_del` int(1) NOT NULL COMMENT '是否删除，0 未删除 1 删除',
  `creator_id` bigint(11) NOT NULL COMMENT '创建人id',
  `creator` varchar(20) NOT NULL COMMENT '创建人名称',
  `modifier` varchar(20) NOT NULL COMMENT '修改人名称',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_user_black_white_rela
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_black_white_rela`;
CREATE TABLE `tbl_user_black_white_rela` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cpy_id` bigint(11) NOT NULL COMMENT '渠道ID',
  `user_id` bigint(11) NOT NULL COMMENT '用户ID',
  `electricpile_code` varchar(32) NOT NULL DEFAULT '0' COMMENT '桩编码',
  `type` int(4) NOT NULL DEFAULT '0' COMMENT '0.白名单 1.黑名单',
  `user_type` int(4) NOT NULL DEFAULT '0' COMMENT '0.用户ID 1.VIN码ID(保留字段)',
  `is_del` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.是',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`) USING BTREE,
  KEY `idx_cpy_id` (`cpy_id`) USING BTREE,
  KEY `idx_electricpile_id` (`electricpile_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8 COMMENT='黑白名单（优先级最高，适用于渠道用户或APP用户，user_id与card_id互斥）';

-- ----------------------------
-- Table structure for tbl_user_company
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_company`;
CREATE TABLE `tbl_user_company` (
  `user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '继承主表ID',
  `cpy_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '所属渠道ID',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户昵称',
  `user_cpy_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户名称',
  `user_cpy_phone` varchar(32) DEFAULT '' COMMENT '手机号',
  `user_cpy_sex` smallint(1) DEFAULT '0' COMMENT '性别 1.男 2.女',
  `user_device_id` varchar(64) DEFAULT NULL COMMENT '设备ID',
  `account_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '资金账户ID',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='渠道用户表';
-- ----------------------------
-- Table structure for tbl_user_tag
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_tag`;
CREATE TABLE `tbl_user_tag` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(11) NOT NULL COMMENT '用户ID',
  `tag_id` bigint(11) NOT NULL COMMENT '标签id',
  `is_del` int(1) NOT NULL COMMENT '是否删除，0 未删除 1 删除',
  `creator_id` bigint(11) NOT NULL COMMENT '创建人id',
  `creator` varchar(20) NOT NULL COMMENT '创建人名称',
  `modifier` varchar(20) NOT NULL COMMENT '修改人名称',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tbl_usercard
-- ----------------------------
DROP TABLE IF EXISTS `tbl_usercard`;
CREATE TABLE `tbl_usercard` (
  `uc_id` bigint(11) NOT NULL COMMENT '充电卡ID',
  `uc_InternalCardNumber` varchar(50) NOT NULL DEFAULT '' COMMENT '内卡号',
  `uc_ExternalCardNumber` varchar(20) NOT NULL DEFAULT '' COMMENT '外卡号',
  `uc_Status` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态 0正常 1挂失 2冻结 3.注销',
  `uc_type` smallint(1) NOT NULL DEFAULT '0' COMMENT '卡类型  10爱充普通公共储蓄卡，11爱充普通专属储蓄卡，12爱充企业信用卡,13爱充合作公共储蓄卡,14 爱充合作专属储蓄卡',
  `user_Id` bigint(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `new_user_id` bigint(11) DEFAULT '0' COMMENT '新用户',
  `cpy_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '渠道ID',
  `account_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '卡账户ID',
  `is_valid` smallint(4) NOT NULL DEFAULT '0' COMMENT '是否开启放盗刷 0.否 1.是',
  `is_app` smallint(4) NOT NULL DEFAULT '0' COMMENT '是否绑App 0.否 1.是 （用户跟卡建立关系，tbl_user_normal表里面的apply_card=1）',
  `is_credit` smallint(4) DEFAULT '0' COMMENT '0.信用金 1.余额',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
 PRIMARY KEY (`uc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=145 DEFAULT CHARSET=utf8 COMMENT='用户充电卡表';

-- ----------------------------
-- Table structure for tbl_company
-- ----------------------------
DROP TABLE IF EXISTS `tbl_company`;
CREATE TABLE `tbl_company` (
  `cpy_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cpy_number` int(4) NOT NULL DEFAULT '0' COMMENT '公司标识',
  `cpy_name` varchar(50) NOT NULL DEFAULT '' COMMENT '企业名称',
  `cpy_province` varchar(32) DEFAULT NULL COMMENT '省编码',
  `cpy_city` varchar(32) DEFAULT NULL COMMENT '市编码',
  `cpy_address` varchar(500) DEFAULT NULL COMMENT '企业地址',
  `cpy_phone` varchar(32) DEFAULT NULL COMMENT '联系方式',
  `cpy_email` varchar(50) DEFAULT NULL COMMENT '企业邮箱',
  `cpy_status` int(4) NOT NULL DEFAULT '0' COMMENT '状态 0.正常 1.禁用',
  `cpy_type` int(4) NOT NULL DEFAULT '0' COMMENT '类型 1.投资公司 2.渠道公司 3.桩主公司 4.业主公司',
  `cpy_parent_id` bigint(11) DEFAULT NULL COMMENT '父级',
  `cpy_parent_type` int(4) NOT NULL DEFAULT '0' COMMENT '0.组织上的上下级关系 1.账户上的上下级关系',
  `trade_type` int(4) NOT NULL DEFAULT '0' COMMENT '结算方式 2.预付费 1.后付费',
  `warn_money` decimal(10,4) DEFAULT '0.0000' COMMENT '预警金额',
  `is_valid` int(4) NOT NULL DEFAULT '0' COMMENT '是否开启盗刷校验 0.否 1.是',
  `slave_cpy_id` bigint(11) DEFAULT NULL COMMENT '管理单位，与cpy_parent_type一起使用',
  `account_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '账户ID',
  `is_del` int(4) NOT NULL DEFAULT '0' COMMENT '是否删除 0.否 1.禁用 ',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) DEFAULT '' COMMENT '修改人',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `account_no` varchar(32) DEFAULT '' COMMENT '资金账户（临时字段，不可作业务逻辑处理）',
  `pay_rule` int(4) DEFAULT NULL COMMENT '付费策略 1.大账户付费 2.个人付费 （字段用来作历史数据处理，不作业务操作）',
  `cpy_num` int(4) NOT NULL DEFAULT '0' COMMENT '公司离线充次数',
  PRIMARY KEY (`cpy_id`),
  KEY `index_cpy_number` (`cpy_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=329 DEFAULT CHARSET=utf8 COMMENT='公司表';

-- ----------------------------
-- Table structure for tbl_user_normal
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_normal`;
CREATE TABLE `tbl_user_normal` (
  `user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '用户主表ID，不自增',
  `norm_name` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户昵称',
  `norm_real_name` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户真实姓名',
  `norm_registe_type` smallint(1) NOT NULL DEFAULT '0' COMMENT '普通用户注册来源(1：管理后台 2：web 3：android 4：ios)',
  `norm_device_id` varchar(64) NOT NULL DEFAULT '' COMMENT '普通用户登录设备ID',
  `norm_email` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户邮箱',
  `norm_address` varchar(100) NOT NULL DEFAULT '' COMMENT '普通用户地址',
  `norm_phone` varchar(32) NOT NULL DEFAULT '' COMMENT '手机号',
  `norm_sex` smallint(1) NOT NULL DEFAULT '0' COMMENT '普通用户性别 (1:男 2：女)',
  `norm_birthday` varchar(20) NOT NULL DEFAULT '1900-01-01 00:00:00' COMMENT '普通用户生日',
  `norm_integrate` int(11) NOT NULL DEFAULT '0' COMMENT '普通用户积分',
  `norm_car_company_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '普通用户汽车品牌ID',
  `norm_car_type_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '普通用户汽车车型ID',
  `norm_vehicle_number` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户车架号',
  `norm_plate_num` varchar(10) NOT NULL DEFAULT '' COMMENT '车牌号',
  `norm_driving_licence` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户驾驶证号',
  `apply_card` smallint(4) NOT NULL DEFAULT '0' COMMENT '充电卡申请标志：0：无申请 1：申请充电卡 2：已发放充电卡',
  `norm_origin` smallint(4) NOT NULL DEFAULT '0' COMMENT '用户来源 1富士康;2吉利;3绿地;4;浙誉;5.车纷享; 以后根据情况再扩展或修改',
  `norm_invitePhone` varchar(11) NOT NULL DEFAULT '' COMMENT '邀请人号码',
  `account_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '资金账户ID',
  `cpy_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '渠道公司ID',
  `is_share` int(4) NOT NULL DEFAULT '0' COMMENT '是否分享 0.未分享 1.分享',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  KEY `index_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='普通用户表';



-- ----------------------------
-- Table structure for tbl_user_normal
-- ----------------------------
DROP TABLE IF EXISTS `order_fav_record`;
CREATE TABLE `order_fav_record` (
  `pk_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_code` varchar(32) NOT NULL DEFAULT '' COMMENT '订单号',
  `cpy_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '渠道ID',
  `user_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `bill_account_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '账单科目ID',
  `favourable_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '优惠类型ID',
  `account_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '被扣资金账户ID',
  `favourable_money` varchar(32) DEFAULT '' COMMENT '优惠费用（100 = 1元）',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) DEFAULT NULL COMMENT '修改人',
  `gmt_creator` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单优惠明细表';


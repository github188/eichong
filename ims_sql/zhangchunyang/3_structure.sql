#集中器
ALTER TABLE `tbl_concentrator`
CHANGE COLUMN `pk_concentratorID` `concentrator_id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT '集中器ID' FIRST ,
CHANGE COLUMN `coct_concentratorName` `concentrator_name`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '集中器名称' AFTER `concentrator_id`,
CHANGE COLUMN `coct_SIM_MAC` `SIM_MAC`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'SIM卡号' AFTER `concentrator_name`,
CHANGE COLUMN `coct_SIM_CODE` `SIM_CODE`  varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT 'SIM卡编号(电话号码)' AFTER `SIM_MAC`,
CHANGE COLUMN `coct_SIM_TYPE` `SIM_TYPE`  tinyint(4) NOT NULL DEFAULT 0 COMMENT 'SIM来源(1：联通,2：电信,3：移动)' AFTER `SIM_CODE`,
CHANGE COLUMN `coct_state` `state`  tinyint(4) NULL DEFAULT 1 COMMENT '集中器状态(0：离线,1：上线 2：无效)' AFTER `SIM_TYPE`,
CHANGE COLUMN `coct_TypeSpanId` `TypeSpanId`  bigint(11) NOT NULL DEFAULT 0 COMMENT '产品ID' AFTER `state`,
CHANGE COLUMN `coct_user_Id` `creator_id`  bigint(11) NULL DEFAULT 0 COMMENT '所属用户ID' AFTER `TypeSpanId`,
CHANGE COLUMN `coct_user_name` `creator`  varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '用户姓名' AFTER `creator_id`,
CHANGE COLUMN `coct_Createdate` `gmt_create`  datetime NOT NULL COMMENT '创建时间' AFTER `creator`,
CHANGE COLUMN `coct_Updatedate` `gmt_modified`  datetime NOT NULL COMMENT '修改时间' AFTER `gmt_create`,
ADD COLUMN `modifier`  varchar(32) NOT NULL DEFAULT '' COMMENT '修改人' AFTER `creator`,
ADD COLUMN `is_del`  smallint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0.否 1.是' AFTER `TypeSpanId`;

#充电点
ALTER TABLE `tbl_powerstation`
ADD COLUMN `rateInfo_id`  bigint(11) NOT NULL DEFAULT 0 COMMENT '默认费率ID',
ADD COLUMN `parking_fee`  varchar(30) NOT NULL DEFAULT '' COMMENT '停车费（15元一小时）',
ADD COLUMN `is_del`  smallint(1) NOT NULL DEFAULT 0 COMMENT '是否删除 0.否 1.是',
ADD COLUMN `modifier`  varchar(32) NOT NULL DEFAULT '' COMMENT '修改人';


ALTER TABLE `tbl_powerstation`
CHANGE COLUMN `pk_PowerStation` `powerStation_id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST ,
CHANGE COLUMN `poSt_Name` `powerStation_name` varchar(200) NOT NULL DEFAULT '' COMMENT '电站名称',
CHANGE COLUMN `poSt_address` `address` varchar(200) NOT NULL DEFAULT '' COMMENT '地址',
CHANGE COLUMN `poSt_Longitude` `longitude` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '经度',
CHANGE COLUMN `poSt_Latitude` latitude decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '纬度',
CHANGE COLUMN `poSt_Status` `status` int(4) NOT NULL DEFAULT '0' COMMENT '电站状态 10.离线 15.上线',
CHANGE COLUMN `poSt_Phone` `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
CHANGE COLUMN `poSt_Createdate` `gmt_create` datetime NULL DEFAULT NULL COMMENT '创建时间',
CHANGE COLUMN `poSt_Updatedate` `gmt_modified` datetime NULL DEFAULT NULL COMMENT '修改时间',
CHANGE COLUMN `poSt_Remark`  `remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '备注',
CHANGE COLUMN `poSt_PowerUser` `powerUser` int(1) NOT NULL DEFAULT 0 COMMENT '电桩用途(电动车、自行车) ',
CHANGE COLUMN `poSt_OnlineTime`  `onlineTime` varchar(100) NOT NULL DEFAULT '' COMMENT '开放时间',
CHANGE COLUMN `poSt_OwnProvinceCode` `province_code`  varchar(10) NOT NULL DEFAULT '' COMMENT '电站所属省份代码' ,
CHANGE COLUMN `poSt_OwnCityCode` `city_code`  varchar(10)  NOT NULL DEFAULT '' COMMENT '电站所属城市代码' ,
CHANGE COLUMN `poSt_OwnCountyCode` `area_code`  varchar(10)  NOT NULL DEFAULT '' COMMENT '电站所属区县代码' ,
CHANGE COLUMN `poSt_UserName` `creator` varchar(50) NOT NULL DEFAULT '' COMMENT '电站所属用户',
CHANGE COLUMN `poSt_createUserId` `creator_id` bigint(11) NOT NULL COMMENT '电站创建人',
CHANGE COLUMN `poSt_IsAppoint` `isAppoint` int(11) NOT NULL DEFAULT '0' COMMENT '电桩是否支持预约',
CHANGE COLUMN `poSt_RejectionReason` `rejectionReason` varchar(500) NOT NULL DEFAULT '' COMMENT '审核驳回原因',
CHANGE COLUMN `poSt_Eleids` `eleids` text NOT NULL COMMENT '电站绑定相关电桩，电桩id用逗号隔开';

ALTER TABLE `tbl_powerstation`
DROP COLUMN `eleids`,
DROP COLUMN `isAppoint`,
DROP COLUMN `rejectionReason`;
ALTER TABLE `tbl_powerstation`
MODIFY COLUMN `onlineTime`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '开放时间' AFTER `powerUser`,
MODIFY COLUMN `rateInfo_id`  bigint(11) NULL DEFAULT 0 COMMENT '默认费率ID' AFTER `area_code`,
MODIFY COLUMN `parking_fee`  varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '停车费（15元一小时）' AFTER `rateInfo_id`,
MODIFY COLUMN `phone`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '联系电话' AFTER `latitude`;

#桩体
alter table tbl_electricpile 
ADD COLUMN sim_name VARCHAR(32) NOT NULL DEFAULT '' COMMENT 'SIM运营商' AFTER sim_phone_num,
ADD COLUMN cpy_id bigint(11) NOT NULL COMMENT '公司ID' AFTER have_connect_line;
ALTER TABLE `tbl_electricpile`
MODIFY COLUMN `elPi_State`  int(11) NOT NULL DEFAULT 0 COMMENT '电桩状态: 0.离散 1线上' AFTER `elPi_PowerNumber`;



#费率
-- ALTER TABLE `tbl_rateinformation`
-- ADD COLUMN `raIn_TipTimeTariffMoney` decimal(8,4) DEFAULT NULL COMMENT '尖时段服务费', 
-- ADD COLUMN `raIn_Name` varchar(32) NOT NULL DEFAULT '' COMMENT '费率名称',  
-- ADD COLUMN `raIn_PeakElectricityMoney` decimal(8,4) DEFAULT NULL COMMENT '峰时间段服务费', 
-- ADD COLUMN `raIn_UsualMoney` decimal(8,4) DEFAULT NULL COMMENT '平时段服务费', 
-- ADD COLUMN `raIn_ValleyTimeMoney` decimal(8,4) DEFAULT NULL COMMENT '谷时段服务费';
-- ALTER TABLE `tbl_rateinformation` CHANGE COLUMN `raIn_TipTimeTariff` `raIn_TipTimeTariffPrice`  decimal(6,4) NULL DEFAULT NULL COMMENT '尖时段电价' AFTER `raIn_TimeMarker`;
-- ALTER TABLE `tbl_rateinformation` CHANGE COLUMN `userId` `creator_id` varchar(50) NOT NULL DEFAULT '' COMMENT '添加费率的用户，p_m_user表的id';
-- ALTER TABLE  `tbl_rateinformation` CHANGE `raIn_type` `raIn_type` SMALLINT(1) DEFAULT 1 NOT NULL COMMENT '1:费率2位，2：费率4位,3:个性化费率';



#电桩默认值
ALTER TABLE tbl_electricpile
  CHANGE `cpy_id` `cpy_id` BIGINT(11) DEFAULT 0  NULL   COMMENT '公司ID';
ALTER TABLE tbl_electricpile 
  CHANGE `company_number` `company_number` INT(11) DEFAULT 0  NULL   COMMENT '公司标识';
ALTER TABLE `tbl_electricpile`   
  CHANGE `owner_ship` `owner_ship` VARCHAR(40) CHARSET utf8 COLLATE utf8_general_ci DEFAULT ''  NULL   COMMENT '电桩所有权（公司名称）';
ALTER TABLE `tbl_electricpile`   
  CHANGE `elPi_ElectricPileName` `elPi_ElectricPileName` VARCHAR(50) CHARSET utf8 COLLATE utf8_general_ci DEFAULT ''  NULL   COMMENT '电桩名称';
ALTER TABLE `tbl_electricpile`   
  CHANGE `elPi_Carid` `elPi_Carid` text CHARSET utf8 COLLATE utf8_general_ci NULL  COMMENT '电桩适用车型，根据电动车品牌类型详情表关联，id用逗号隔开';
ALTER TABLE `tbl_electricpile`   
  CHANGE `elPi_RelevancePowerStation` `elPi_RelevancePowerStation` int(11) NULL DEFAULT 0 COMMENT '所属电站';
ALTER TABLE `tbl_electricpile`   
  CHANGE `elPi_RateInformationId` `elPi_RateInformationId` INT(11) DEFAULT 0  NULL   COMMENT '费率表id';
ALTER TABLE `tbl_electricpile`   
  CHANGE `pk_concentratorID` `pk_concentratorID` int(11) NULL DEFAULT 0  COMMENT '集中器id';




#产品型号
 ALTER TABLE `tbl_typespan` 
 ADD COLUMN `elPi_PowerNumber` int(11) NOT NULL DEFAULT '1' COMMENT '电桩枪口数量',
 ADD COLUMN `elPi_ChargingMode` int(11) NOT NULL DEFAULT '0' COMMENT '电桩充电方式（5-直流充电桩，14-交流充电桩）',
 ADD COLUMN `elPi_PowerSize` int(11) NOT NULL DEFAULT '0' COMMENT '电桩额定功率',
 ADD COLUMN `elPi_Maker` int(11) NOT NULL DEFAULT '0' COMMENT '电桩制造商',
 ADD COLUMN `elPi_Type` int(11) NOT NULL DEFAULT '0' COMMENT '电桩类型';

 alter table tbl_bomlist add bl_HardwareType smallint(6) NOT NULL  DEFAULT '1' COMMENT '1:计费单元 2:主控单元 3:显示屏 4:通讯模块';


#发票
 ALTER TABLE tbl_invoice 
ADD COLUMN `iv_refuseRejection` varchar(200) NOT NULL DEFAULT '' COMMENT '拒绝理由';



ALTER TABLE `eichong`.`tbl_company_rela`   
  ADD COLUMN `electricpile_code` VARCHAR(32) DEFAULT '0'  NOT NULL   COMMENT '桩code' AFTER `pk_ElectricPile`,
  ADD COLUMN `is_del` SMALLINT(4) DEFAULT 0  NOT NULL   COMMENT '是否删除 0.否 1.是' AFTER `electricpile_code`,
  ADD COLUMN `creator` VARCHAR(32) DEFAULT 'admin'  NOT NULL   COMMENT '创建人' AFTER `is_del`,
  ADD COLUMN `modifier` VARCHAR(32) DEFAULT 'admin'  NOT NULL   COMMENT '修改人' AFTER `creator`;
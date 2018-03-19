-- MySQL dump 10.13  Distrib 5.6.38, for Linux (x86_64)
--
-- Host: rm-uf6426u9v92z31yjprw.mysql.rds.aliyuncs.com    Database: eichong
-- ------------------------------------------------------
-- Server version	5.6.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED='1d9dee90-66bc-11e7-b61b-7cd30abeacf2:1-40450925,
81c2453c-1384-11e7-9776-7cd30abe9e36:1-14484,
9cae2eab-1384-11e7-9777-7cd30abda4fa:1-19579644';

--
-- Table structure for table `data_travel`
--

DROP TABLE IF EXISTS `data_travel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_travel` (
  `cpy_CompanyNumber` int(4) DEFAULT NULL,
  `cpy_CompanyName` varchar(50) DEFAULT NULL,
  `cpy_CompanyAddress` varchar(500) DEFAULT NULL,
  `cpy_province` varchar(32) DEFAULT NULL,
  `cpy_city` varchar(32) DEFAULT NULL,
  `cpy_paymod` smallint(4) DEFAULT NULL,
  `account_no` varchar(32) DEFAULT NULL,
  `flag` int(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `data_travels`
--

DROP TABLE IF EXISTS `data_travels`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `data_travels` (
  `id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_code_detail`
--

DROP TABLE IF EXISTS `p_m_code_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_code_detail` (
  `CODE_GROUP_ID` varchar(20) NOT NULL COMMENT 'CODE组',
  `CODE_ID` varchar(20) NOT NULL COMMENT 'CODE',
  `CODE_NAME` varchar(40) NOT NULL COMMENT 'CODE名称',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '最后更新人',
  PRIMARY KEY (`CODE_GROUP_ID`,`CODE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公共Code的详细定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_code_group`
--

DROP TABLE IF EXISTS `p_m_code_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_code_group` (
  `CODE_GROUP_ID` varchar(20) NOT NULL COMMENT 'CODE组',
  `CODE_GROUP_NAME` varchar(40) NOT NULL COMMENT 'CODE组名称',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '最后更新人',
  PRIMARY KEY (`CODE_GROUP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公共CODE组表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_company`
--

DROP TABLE IF EXISTS `p_m_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_company` (
  `company_id` varchar(32) NOT NULL COMMENT '公司ID',
  `company_type` varchar(3) DEFAULT NULL COMMENT '公司分类,默认02，参考公共CODE:037',
  `company_name` varchar(255) DEFAULT NULL COMMENT '公司名称',
  `province_id` varchar(20) DEFAULT NULL COMMENT '省份ID',
  `city_id` varchar(20) DEFAULT NULL COMMENT '城市ID',
  `area_id` varchar(20) DEFAULT NULL COMMENT '区县ID',
  `develop_company` varchar(255) DEFAULT NULL COMMENT '开发公司',
  `service_company` varchar(255) DEFAULT NULL COMMENT '物业公司',
  `service_phone` varchar(20) DEFAULT NULL COMMENT '物业服务电话',
  `service_time` varchar(50) DEFAULT NULL COMMENT '物业服务时间',
  `service_rule` varchar(1024) DEFAULT NULL COMMENT '物业保修范围与规定',
  `service_comment` varchar(1024) DEFAULT NULL COMMENT '物业服务内容与收费标准',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '物业服务内容与收费标准',
  `notes` varchar(1024) DEFAULT NULL COMMENT '基本信息',
  `detail` varchar(15000) DEFAULT NULL COMMENT '详情描',
  `delete_flag` varchar(1) DEFAULT '0' COMMENT '删除标识：默认0，0:未删除 1:删除',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `last_update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `last_update_user` varchar(50) DEFAULT NULL COMMENT '最后更新人',
  `responsible_depart` varchar(20) DEFAULT NULL COMMENT '责任部门',
  `responsible_company` varchar(20) DEFAULT NULL COMMENT '责任公司',
  PRIMARY KEY (`company_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_company_post`
--

DROP TABLE IF EXISTS `p_m_company_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_company_post` (
  `COMPANY_ID` varchar(20) NOT NULL COMMENT '公司ID',
  `POST_ID` varchar(20) NOT NULL COMMENT '职位ID',
  `POST_NAME` varchar(128) NOT NULL COMMENT '职位名称',
  `NOTES` varchar(255) DEFAULT NULL COMMENT '备注',
  `SORT_KEY` int(4) NOT NULL COMMENT '排序,排序可以作为职位大小区别用',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`COMPANY_ID`,`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公司职位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_department`
--

DROP TABLE IF EXISTS `p_m_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_department` (
  `COMPANY_ID` varchar(20) NOT NULL COMMENT '公司ID',
  `DEPARTMENT_ID` varchar(20) NOT NULL COMMENT '部门ID',
  `DEPARTMENT_NAME` varchar(128) NOT NULL COMMENT '部门名称',
  `ADDRESS1` varchar(255) DEFAULT NULL COMMENT '地址1',
  `ADDRESS2` varchar(255) DEFAULT NULL COMMENT '地址2',
  `TELEPHONE_NUMBER` varchar(64) DEFAULT NULL COMMENT '电话号码',
  `FAX_NUMBER` varchar(64) DEFAULT NULL COMMENT '传真号码',
  `EXTENSION_NUMBER` varchar(64) DEFAULT NULL COMMENT '分机号',
  `EXTENSION_FAX_NUMBER` varchar(64) DEFAULT NULL COMMENT '传真分机号',
  `COUNTRY_ID` varchar(20) DEFAULT NULL COMMENT '国家ID:ISO三位（扩展用，暂未在画面维护）',
  `ZIP_CODE` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `EMAIL_ADDRESS1` varchar(128) DEFAULT NULL COMMENT '邮件地址1',
  `EMAIL_ADDRESS2` varchar(128) DEFAULT NULL COMMENT '邮件地址2',
  `URL` varchar(255) DEFAULT NULL COMMENT '主页',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  `province_id` varchar(20) DEFAULT NULL COMMENT '行政省ID',
  `city_id` varchar(20) DEFAULT NULL COMMENT '城市ID',
  `area_id` varchar(20) DEFAULT NULL COMMENT '区域县ID',
  `responsible_depart` varchar(20) DEFAULT NULL COMMENT '责任部门',
  `responsible_company` varchar(20) DEFAULT NULL COMMENT '责任公司',
  PRIMARY KEY (`COMPANY_ID`,`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_department_inclusion`
--

DROP TABLE IF EXISTS `p_m_department_inclusion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_department_inclusion` (
  `COMPANY_ID` varchar(20) NOT NULL COMMENT '公司ID',
  `PARENT_DEPARTMENT_ID` varchar(20) NOT NULL COMMENT '父部门ID',
  `DEPARTMENT_ID` varchar(20) NOT NULL COMMENT '子部门ID',
  `DEPTH` int(5) NOT NULL COMMENT '深度,父部门到子部门的层次',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建者',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新者',
  PRIMARY KEY (`COMPANY_ID`,`PARENT_DEPARTMENT_ID`,`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_deploy_info`
--

DROP TABLE IF EXISTS `p_m_deploy_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_deploy_info` (
  `DEPLOY_URL` varchar(255) NOT NULL COMMENT '链接',
  `PARENT_PATH` varchar(255) NOT NULL COMMENT '文件路径',
  `image_ScanUrl` varchar(255) NOT NULL DEFAULT '' COMMENT '图片浏览地址',
  PRIMARY KEY (`DEPLOY_URL`,`PARENT_PATH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部署信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_item_department`
--

DROP TABLE IF EXISTS `p_m_item_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_item_department` (
  `TABLE_ID` varchar(50) NOT NULL COMMENT '表物理名',
  `COLUMN_ID` varchar(50) NOT NULL COMMENT '字段物理名',
  `COMPANY_ID` varchar(20) NOT NULL COMMENT '公司ID',
  `DEPARTMENT_ID` varchar(20) NOT NULL COMMENT '职位ID',
  `PROCESS_LEVEL` varchar(1) NOT NULL COMMENT '操作级别：0：修改 1：查看 3：不可查看',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`TABLE_ID`,`COLUMN_ID`,`COMPANY_ID`,`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库字段部门权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_menu`
--

DROP TABLE IF EXISTS `p_m_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_menu` (
  `MENU_ID` varchar(20) NOT NULL COMMENT '菜单ID',
  `MENU_TYPE` varchar(1) NOT NULL COMMENT '菜单分类',
  `CONTENTS` varchar(128) NOT NULL COMMENT '显示内容',
  `SORT_NUMBER` int(4) NOT NULL COMMENT '排序',
  `URL` varchar(255) DEFAULT NULL COMMENT '链接',
  `REL` varchar(20) DEFAULT NULL COMMENT '标签',
  `PARENT_MENU_ID` varchar(20) DEFAULT NULL COMMENT '父菜单ID',
  `NOTES` varchar(255) DEFAULT NULL COMMENT '备注',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_menu_department`
--

DROP TABLE IF EXISTS `p_m_menu_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_menu_department` (
  `MENU_ID` varchar(20) NOT NULL COMMENT '菜单ID',
  `COMPANY_ID` varchar(20) NOT NULL COMMENT '公司ID',
  `DEPARTMENT_ID` varchar(20) NOT NULL COMMENT '部门ID',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`MENU_ID`,`COMPANY_ID`,`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单部门权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_menu_post`
--

DROP TABLE IF EXISTS `p_m_menu_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_menu_post` (
  `MENU_ID` varchar(20) NOT NULL COMMENT '菜单ID',
  `COMPANY_ID` varchar(20) NOT NULL COMMENT '公司ID',
  `POST_ID` varchar(20) NOT NULL COMMENT '职位ID',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`MENU_ID`,`COMPANY_ID`,`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单职位权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_menu_role`
--

DROP TABLE IF EXISTS `p_m_menu_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_menu_role` (
  `MENU_ID` varchar(20) NOT NULL COMMENT '菜单ID',
  `ROLE_ID` varchar(20) NOT NULL COMMENT '角色ID',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`MENU_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`aichong`@`%`*/ /*!50003 TRIGGER `t_afterdelete_on_p_m_menu_role` AFTER DELETE ON `p_m_menu_role` FOR EACH ROW BEGIN
      insert into p_m_menu_role_trigger_del(MENU_ID,ROLE_ID,CREATE_USER,LAST_UPDATE_USER) VALUES(old.MENU_ID,old.ROLE_ID,1,1);

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `p_m_menu_role_trigger_del`
--

DROP TABLE IF EXISTS `p_m_menu_role_trigger_del`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_menu_role_trigger_del` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `MENU_ID` varchar(20) NOT NULL COMMENT '菜单ID',
  `ROLE_ID` varchar(20) NOT NULL COMMENT '角色ID',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9266 DEFAULT CHARSET=utf8 COMMENT='菜单角色权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_page`
--

DROP TABLE IF EXISTS `p_m_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_page` (
  `PAGE_ID` varchar(20) NOT NULL COMMENT '菜单ID',
  `PAGE_NAME` varchar(128) NOT NULL COMMENT '菜单名称',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`PAGE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='画面定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_role`
--

DROP TABLE IF EXISTS `p_m_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_role` (
  `ROLE_ID` varchar(20) NOT NULL COMMENT '角色ID',
  `ROLE_NAME` varchar(50) NOT NULL COMMENT '角色名称',
  `CATEGORY` varchar(255) DEFAULT NULL COMMENT '角色分类(普通管理员 2,纯商家3,商家子角色4,个体商家5)',
  `NOTES` varchar(255) DEFAULT NULL COMMENT '备注',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '最后更新人',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_role_inclusion`
--

DROP TABLE IF EXISTS `p_m_role_inclusion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_role_inclusion` (
  `PARENT_ROLE_ID` varchar(20) NOT NULL COMMENT '父角色ID',
  `ROLE_ID` varchar(20) NOT NULL COMMENT '子角色ID',
  `DEPTH` int(5) NOT NULL COMMENT '深度:父角色到子角色的层次',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '最后更新人',
  PRIMARY KEY (`PARENT_ROLE_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_table`
--

DROP TABLE IF EXISTS `p_m_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_table` (
  `TABLE_ID` varchar(50) NOT NULL COMMENT '表物理名',
  `TABLE_NAME` varchar(128) NOT NULL COMMENT '表逻辑名',
  `NOTES` varchar(255) DEFAULT NULL COMMENT '备注',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`TABLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库表定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_table_item`
--

DROP TABLE IF EXISTS `p_m_table_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_table_item` (
  `TABLE_ID` varchar(50) NOT NULL COMMENT '表物理名',
  `COLUMN_ID` varchar(50) NOT NULL COMMENT '字段物理名',
  `COMPANY_ID` varchar(20) NOT NULL COMMENT '公司ID',
  `POST_ID` varchar(20) NOT NULL COMMENT '职位ID',
  `PROCESS_LEVEL` varchar(1) NOT NULL COMMENT '标签',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`TABLE_ID`,`COLUMN_ID`,`COMPANY_ID`,`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据库表字段定义表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_user`
--

DROP TABLE IF EXISTS `p_m_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_user` (
  `USER_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `USER_NAME` varchar(50) NOT NULL COMMENT '用户名称',
  `USER_PASSWORD` varchar(255) DEFAULT NULL COMMENT '用户密码',
  `USER_LEVEL` varchar(1) DEFAULT NULL COMMENT '用户级别：0：超级管理员 1：系统管理员 2:商家用户 3：个体商家用户',
  `ADDRESS1` varchar(255) DEFAULT NULL COMMENT '地址1',
  `ADDRESS2` varchar(255) DEFAULT NULL COMMENT '地址2',
  `TELEPHONE_NUMBER` varchar(64) DEFAULT NULL COMMENT '电话号码',
  `MOBILE_NUMBER` varchar(64) DEFAULT NULL COMMENT '手机号码',
  `FAX_NUMBER` varchar(64) DEFAULT NULL COMMENT '传真号码',
  `EXTENSION_NUMBER` varchar(64) DEFAULT NULL COMMENT '分机号',
  `EXTENSION_FAX_NUMBER` varchar(64) DEFAULT NULL COMMENT '传真分机号',
  `COUNTRY_ID` varchar(20) DEFAULT NULL COMMENT '国家ID',
  `ZIP_CODE` varchar(20) DEFAULT NULL COMMENT '邮政编码',
  `EMAIL_ADDRESS1` varchar(128) DEFAULT NULL COMMENT '邮件地址1',
  `EMAIL_ADDRESS2` varchar(128) DEFAULT NULL COMMENT '邮件地址2',
  `MOBILE_EMAIL_ADDRESS` varchar(128) DEFAULT NULL COMMENT '手机邮件地址',
  `URL` varchar(255) DEFAULT NULL COMMENT '个人主页',
  `IMAGE` varchar(255) DEFAULT NULL COMMENT '个人图片',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '最后更新人',
  `birthdate` date DEFAULT NULL COMMENT '出生年月',
  `sex` varchar(3) DEFAULT NULL COMMENT '性别',
  `profession` varchar(3) DEFAULT NULL COMMENT '职业',
  `constellation` varchar(3) DEFAULT NULL COMMENT '星座',
  `sign` varchar(255) DEFAULT NULL COMMENT '签名',
  `hobbies` varchar(255) DEFAULT NULL COMMENT '真实姓名',
  `delete_flag` varchar(1) DEFAULT '0' COMMENT '兴趣爱好',
  `real_name` varchar(50) DEFAULT NULL COMMENT '删除标识:默认0,0:未删除 1:删除',
  `identity_no` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `company_id` int(11) NOT NULL DEFAULT '0' COMMENT '所属公司id',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_user_department`
--

DROP TABLE IF EXISTS `p_m_user_department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_user_department` (
  `USER_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `COMPANY_ID` varchar(20) NOT NULL COMMENT '公司ID',
  `DEPARTMENT_ID` varchar(20) NOT NULL COMMENT '部门ID',
  `POST_ID` varchar(20) DEFAULT NULL COMMENT '职位ID',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`USER_ID`,`COMPANY_ID`,`DEPARTMENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户部门表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_user_post`
--

DROP TABLE IF EXISTS `p_m_user_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_user_post` (
  `USER_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `COMPANY_ID` varchar(20) NOT NULL COMMENT '公司ID',
  `POST_ID` varchar(20) NOT NULL DEFAULT '' COMMENT '职位ID',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`USER_ID`,`COMPANY_ID`,`POST_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户职位表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `p_m_user_role`
--

DROP TABLE IF EXISTS `p_m_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `p_m_user_role` (
  `USER_ID` varchar(50) NOT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(20) NOT NULL COMMENT '角色ID',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(50) NOT NULL COMMENT '创建人',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后更新时间',
  `LAST_UPDATE_USER` varchar(50) NOT NULL COMMENT '最后更新人',
  PRIMARY KEY (`USER_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pan_finance_account_decrease`
--

DROP TABLE IF EXISTS `pan_finance_account_decrease`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pan_finance_account_decrease` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(30) NOT NULL DEFAULT '',
  `user_account` varchar(30) NOT NULL DEFAULT '' COMMENT '用户帐号',
  `user_recharge` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '充值金额',
  `user_purchase` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '平台消费金额',
  `user_account_balance` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '平台帐号余额',
  `user_frozenamt` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '实时预冻结资金',
  `user_diff` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '充值减去消费与帐号余额之和的差 数值',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '统计时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金帐号减少的用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pan_finance_account_increase`
--

DROP TABLE IF EXISTS `pan_finance_account_increase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pan_finance_account_increase` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(30) NOT NULL DEFAULT '',
  `user_account` varchar(30) NOT NULL DEFAULT '' COMMENT '用户帐号',
  `user_recharge` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '充值金额',
  `user_purchase` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '平台消费金额',
  `user_account_balance` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '平台帐号余额',
  `user_frozenamt` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '实时预冻结资金',
  `user_diff` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '充值减去消费与帐号余额之和的差 数值',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '统计时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资金帐号增加的用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_auth_code`
--

DROP TABLE IF EXISTS `tb_auth_code`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_auth_code` (
  `AUTH_CODE` varchar(20) NOT NULL,
  `MOBILE_NUMBER` varchar(20) NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_USER` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`AUTH_CODE`,`MOBILE_NUMBER`,`CREATE_DATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_m_area`
--

DROP TABLE IF EXISTS `tb_m_area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_m_area` (
  `area_id` varchar(20) NOT NULL,
  `province_id` varchar(20) NOT NULL,
  `city_id` varchar(20) NOT NULL,
  `area_name` varchar(40) NOT NULL,
  `hot_flag` varchar(1) DEFAULT '0',
  `delete_flag` varchar(1) DEFAULT '0',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user` varchar(50) DEFAULT NULL,
  `last_update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`area_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_m_city`
--

DROP TABLE IF EXISTS `tb_m_city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_m_city` (
  `PROVINCE_ID` varchar(20) NOT NULL,
  `CITY_ID` varchar(20) NOT NULL,
  `CITY_NAME` varchar(40) NOT NULL,
  `HOT_FLAG` varchar(1) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_USER` varchar(50) DEFAULT NULL,
  `serviceLimit` decimal(9,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`PROVINCE_ID`,`CITY_ID`),
  KEY `idx_tb_city` (`CITY_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_m_province`
--

DROP TABLE IF EXISTS `tb_m_province`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_m_province` (
  `PROVINCE_ID` varchar(20) NOT NULL,
  `PROVINCE_NAME` varchar(40) NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATE_USER` varchar(50) DEFAULT NULL,
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_USER` varchar(50) DEFAULT NULL,
  `Tip_Electricity` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '尖段电价',
  `Peak_Electricity` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '峰段电费',
  `Flat_Electricity` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '平段电费',
  `Valley_Electricity` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '谷段电费',
  PRIMARY KEY (`PROVINCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_m_sequence`
--

DROP TABLE IF EXISTS `tb_m_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_m_sequence` (
  `SEQUENCE_ID` varchar(20) NOT NULL,
  `SEQUENCE_NAME` varchar(128) NOT NULL,
  `SEQUENCE_YEAR` varchar(4) NOT NULL,
  `SEQUENCE_NOW` decimal(8,0) DEFAULT '0',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `CREATE_USER` varchar(50) DEFAULT 'system',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LAST_UPDATE_USER` varchar(50) DEFAULT 'system',
  PRIMARY KEY (`SEQUENCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_multi_media`
--

DROP TABLE IF EXISTS `tb_multi_media`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_multi_media` (
  `FILE_ID` varchar(32) NOT NULL COMMENT '文件ID',
  `BUSSINESS_TYPE` varchar(20) NOT NULL COMMENT '业务逻辑类型',
  `FILE_TYPE` varchar(20) DEFAULT '0' COMMENT '文件类型',
  `reference_id` varchar(32) DEFAULT NULL COMMENT '业务',
  `RELATIVE_PATH` varchar(20) NOT NULL COMMENT '文件存储文件夹（路径）',
  `FILE_NAME` varchar(255) NOT NULL COMMENT '文件名称',
  `TITLE_FLG` varchar(1) NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '文件创建时间',
  `CREATE_USER` varchar(50) DEFAULT NULL COMMENT '文件创建用户',
  `LAST_UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '文件修改时间',
  `LAST_UPDATE_USER` varchar(50) DEFAULT NULL COMMENT '文件修改用户',
  PRIMARY KEY (`FILE_ID`),
  KEY `index_FILE_ID` (`FILE_ID`) USING BTREE,
  KEY `index_BUSSINESS_TYPE` (`BUSSINESS_TYPE`) USING BTREE,
  KEY `index_reference_id` (`reference_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ftp图片上传表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_union_pay_info`
--

DROP TABLE IF EXISTS `tb_union_pay_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_union_pay_info` (
  `TransId` varchar(32) NOT NULL,
  `MerOrderId` varchar(32) NOT NULL,
  `TransCode` varchar(20) NOT NULL,
  `OrderTime` varchar(14) NOT NULL,
  `OrderDate` varchar(8) NOT NULL,
  `TransType` varchar(32) NOT NULL,
  `TransAmt` varchar(12) NOT NULL,
  `MerId` varchar(32) NOT NULL,
  `MerTermId` varchar(8) NOT NULL,
  `ChrCode` varchar(128) NOT NULL,
  `Reserve` varchar(256) DEFAULT NULL,
  `RespCode` varchar(256) NOT NULL,
  `RespMsg` varchar(256) NOT NULL,
  `Signature` varchar(256) NOT NULL,
  `EffectiveTime` decimal(12,0) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user` varchar(50) DEFAULT NULL,
  `last_update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`TransId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_union_pay_notice`
--

DROP TABLE IF EXISTS `tb_union_pay_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_union_pay_notice` (
  `OrderTime` varchar(14) NOT NULL,
  `OrderDate` varchar(8) NOT NULL,
  `MerOrderId` varchar(32) NOT NULL,
  `TransType` varchar(32) NOT NULL,
  `TransAmt` decimal(12,0) NOT NULL,
  `MerId` varchar(32) NOT NULL,
  `MerTermId` varchar(8) NOT NULL,
  `TransId` varchar(32) NOT NULL,
  `TransState` varchar(2) NOT NULL,
  `RefId` varchar(12) NOT NULL,
  `Account` varchar(30) NOT NULL,
  `TransDesc` varchar(256) DEFAULT NULL,
  `LiqDate` varchar(8) NOT NULL,
  `Reserve` varchar(256) DEFAULT NULL,
  `Signature` varchar(256) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `create_user` varchar(50) DEFAULT NULL,
  `last_update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_update_user` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`TransId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_activity`
--

DROP TABLE IF EXISTS `tbl_activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_activity` (
  `pk_Activity` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `act_ActivityName` varchar(50) NOT NULL DEFAULT '' COMMENT '活动名称',
  `act_Type` int(11) NOT NULL DEFAULT '0' COMMENT '活动类型：1-用户活动，2-大客户活动',
  `act_ChannelType` int(11) NOT NULL DEFAULT '0' COMMENT '渠道类型（tbl_company）',
  `act_Status` smallint(4) NOT NULL DEFAULT '0' COMMENT '状态（1-终止）',
  `act_ActivityRule` int(11) DEFAULT '0' COMMENT '活动规则（1-注册送现金券活动，2-首次体验享现金券，3-邀请注册返现金券活动，4-邀请首次消费返现金券活动,5-充值送，6-消费送，7-指定送 , 8.新手充值送）',
  `act_CreateUserId` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `act_UpdateUserId` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人',
  `act_Remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注（奖品发放规则）',
  `act_BeginDate` date NOT NULL DEFAULT '1900-01-01' COMMENT '活动开始时间',
  `act_EndDate` date NOT NULL DEFAULT '1900-01-01' COMMENT '活动结束时间',
  `act_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `act_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `act_top_money` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '单人最高充值金额',
  `act_CouponEndDate` date DEFAULT NULL COMMENT '优惠券到期时间',
  PRIMARY KEY (`pk_Activity`)
) ENGINE=InnoDB AUTO_INCREMENT=204 DEFAULT CHARSET=utf8 COMMENT='活动表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_activityschedule`
--

DROP TABLE IF EXISTS `tbl_activityschedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_activityschedule` (
  `pk_ActivitySchedule` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pk_Activity` int(11) NOT NULL DEFAULT '0' COMMENT '活动表主键',
  `pk_CouponVariety` int(11) NOT NULL DEFAULT '0' COMMENT '优惠券品种主键',
  `acsc_num` int(11) NOT NULL DEFAULT '0' COMMENT '优惠券数量',
  `acsc_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `acsc_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `act_single_money` varchar(50) NOT NULL DEFAULT '' COMMENT '单笔充值/消费/指定地点满就送金额限制',
  PRIMARY KEY (`pk_ActivitySchedule`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 COMMENT='活动附表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_admininfo`
--

DROP TABLE IF EXISTS `tbl_admininfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_admininfo` (
  `pk_AdminInfo` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `adIn_GroupID` int(11) DEFAULT NULL COMMENT '分组ID',
  `adIn_AdminName` varchar(100) NOT NULL COMMENT '管理员姓名',
  `adIn_AdminLogin` varchar(100) NOT NULL COMMENT '管理员帐号',
  `adIn_PassWord` varchar(20) NOT NULL COMMENT '管理员密码',
  `adIn_IDCard` varchar(20) NOT NULL COMMENT '身份号码',
  `adIn_Phone` varchar(20) NOT NULL COMMENT '联系电话',
  `adIn_State` int(11) NOT NULL COMMENT '1：开通 2 关闭',
  `adIn_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `adIn_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_AdminInfo`),
  KEY `FK_Reference_26` (`adIn_GroupID`) USING BTREE,
  CONSTRAINT `tbl_admininfo_ibfk_1` FOREIGN KEY (`adIn_GroupID`) REFERENCES `tbl_group` (`pk_Group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_advertisement`
--

DROP TABLE IF EXISTS `tbl_advertisement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_advertisement` (
  `pk_ad_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ad_name` varchar(50) NOT NULL DEFAULT '' COMMENT '广告名称',
  `ad_type` smallint(1) unsigned NOT NULL DEFAULT '1' COMMENT '广告类型,1: 闪屏 2 :插屏',
  `ad_is_goto` smallint(1) unsigned NOT NULL DEFAULT '0' COMMENT '是否跳转,0 :否 1: 是',
  `ad_desc` varchar(70) NOT NULL DEFAULT '' COMMENT '广告说明',
  `ad_URL` varchar(500) NOT NULL DEFAULT '' COMMENT 'URL地址',
  `ad_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否上架状态,0: 上架 1: 下架',
  `ad_time` smallint(1) unsigned NOT NULL DEFAULT '0' COMMENT '闪屏曝光时间',
  `ad_location` smallint(1) NOT NULL DEFAULT '0' COMMENT '插屏button位置 ,1: 左一button 2: 左二button 3:右二button 4:右一button',
  `ad_createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ad_updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `begin_ad_time` datetime NOT NULL COMMENT '广告开始时间',
  `end_ad_time` datetime NOT NULL COMMENT '广告结束时间',
  `fk_user_id` int(11) unsigned NOT NULL COMMENT '管理员信息表 user_id',
  PRIMARY KEY (`pk_ad_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='广告闪屏插屏管理表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_app_button_list`
--

DROP TABLE IF EXISTS `tbl_app_button_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_app_button_list` (
  `pk_abl_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `abl_name` varchar(70) NOT NULL DEFAULT '' COMMENT '按钮名称',
  `abl_desc` varchar(250) NOT NULL DEFAULT '' COMMENT '备注',
  `abl_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态默认0: 1 开启 2 删除 3 下架关闭',
  `abl_type` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态默认0: 1 内部 2外部连接',
  `abl_action` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态默认0: ',
  `abl_sort` smallint(1) unsigned NOT NULL DEFAULT '99' COMMENT '播放顺序',
  `abl_createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `abl_updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `abl_url` varchar(200) NOT NULL DEFAULT '' COMMENT '外部连接url',
  PRIMARY KEY (`pk_abl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COMMENT='app功能按钮表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_apply_ep`
--

DROP TABLE IF EXISTS `tbl_apply_ep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_apply_ep` (
  `aep_id` int(11) NOT NULL AUTO_INCREMENT,
  `aep_a_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '申请类型 1免费建桩2自费建桩',
  `aep_c_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '分类 1个人2企业',
  `aep_name` varchar(30) NOT NULL DEFAULT '' COMMENT '联系人姓名',
  `aep_phone` varchar(15) NOT NULL DEFAULT '' COMMENT '联系人电话',
  `aep_p_code` varchar(6) NOT NULL DEFAULT '' COMMENT '省份代码',
  `aep_c_code` varchar(6) NOT NULL DEFAULT '' COMMENT '城市代码',
  `aep_a_code` varchar(6) NOT NULL DEFAULT '' COMMENT '区县代码',
  `aep_addr` varchar(255) NOT NULL DEFAULT '' COMMENT '具体地址',
  `aep_h_park` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否自有车位 1是2否',
  `aep_park_num` tinyint(4) NOT NULL DEFAULT '0' COMMENT '车位数量 1少于等于10个 2大于10个',
  `aep_ep_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '设备需求 1直流2交流3交直流都有',
  `aep_i_agree` tinyint(4) NOT NULL DEFAULT '0' COMMENT '物业是否同意安装 1同意2不同意',
  `aep_i_report` tinyint(4) NOT NULL DEFAULT '0' COMMENT '电力是否通过报备 1通过2未通过',
  `aep_i_ground` tinyint(4) NOT NULL DEFAULT '0' COMMENT '安装地点 1地面2地下',
  `aep_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '申请用户id',
  `aep_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '申请状态 1提交申请2勘探现场3现场施工4建桩成功',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `aep_origin` tinyint(4) DEFAULT NULL COMMENT '用户来源 1富士康;2吉利;3绿地;4;浙誉;5.车纷享; 以后根据情况再扩展或修改',
  PRIMARY KEY (`aep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=147 DEFAULT CHARSET=utf8 COMMENT='申请建桩表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_applystation`
--

DROP TABLE IF EXISTS `tbl_applystation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_applystation` (
  `pk_ApplyStation` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `apSt_UserInfoId` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID (tbl_userinfo)',
  `apSt_UserFacticityName` varchar(50) NOT NULL DEFAULT '' COMMENT '用户真实姓名',
  `apSt_UserPhone` varchar(20) NOT NULL DEFAULT '' COMMENT '用户联系电话',
  `apSt_UserEmail` varchar(50) NOT NULL DEFAULT '' COMMENT '用户邮箱',
  `apSt_StationAddress` varchar(200) NOT NULL DEFAULT '' COMMENT '申请建站的地址',
  `apSt_PropertyNature` smallint(6) NOT NULL DEFAULT '0' COMMENT '物业性质（默认为0）',
  `apSt_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '申请时间',
  `apSt_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `apSt_Remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `apSt_ProcessState` smallint(6) NOT NULL DEFAULT '0' COMMENT '申请流程当前状态',
  `apSt_DealReason` varchar(150) NOT NULL DEFAULT '' COMMENT '申请加站驳回原因',
  PRIMARY KEY (`pk_ApplyStation`),
  KEY `userIn_Id` (`apSt_UserInfoId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户申请建加电站';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_appointmentinstallationorder`
--

DROP TABLE IF EXISTS `tbl_appointmentinstallationorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_appointmentinstallationorder` (
  `pk_AppointmentInstallationOrder` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `aLOr_InstallationOrderCode` varchar(30) NOT NULL COMMENT '预约安装订单编号',
  `aLOr_InstallationOrderProductID` varchar(20) DEFAULT NULL COMMENT '商品名称',
  `aLOr_InstallationOrderProductName` varchar(20) DEFAULT NULL COMMENT '商品编号',
  `aLOr_UserID` int(11) NOT NULL COMMENT '用户ID',
  `aLOr_TelLogin` varchar(20) DEFAULT NULL COMMENT '手机帐号',
  `aLOr_Money` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '金额',
  `aLOr_InstallationOrderStatus` int(11) NOT NULL COMMENT '1：待支付 2 支付成功 3 操作完成',
  `aLOr_BuyingTime` datetime NOT NULL COMMENT '购买时间',
  `aiOr_InstallationAddress` varchar(100) NOT NULL COMMENT '安装地址',
  `aLOr_InstallationPerson` varchar(20) NOT NULL COMMENT '联系人',
  `aLOr_LnstallationTel` varchar(20) NOT NULL COMMENT '联系电话',
  `aLOr_CommodityTotal` int(11) NOT NULL COMMENT '商品总数',
  `aLOr_CommodityShops` varchar(200) NOT NULL COMMENT '所属商铺',
  `aLor_OrderType` int(11) NOT NULL COMMENT '1：支付宝 2：银联支付',
  `aLor_OrderId` int(11) DEFAULT '0' COMMENT '订单表ID',
  PRIMARY KEY (`pk_AppointmentInstallationOrder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约安装订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_appointmentlockingrecords`
--

DROP TABLE IF EXISTS `tbl_appointmentlockingrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_appointmentlockingrecords` (
  `pk_AppointmentLockingRecords` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `aLRe_UsingMachineCode` int(11) NOT NULL COMMENT '使用终端机器编码',
  `aLRe_ElectricPileID` int(11) NOT NULL COMMENT '电桩ID',
  `aLRe_MuzzleNumber` int(11) NOT NULL COMMENT '枪口编号',
  `aLRe_AppointmentStarttime` datetime NOT NULL COMMENT '预约开始时间',
  `aLRe_AppointmentEndtime` datetime NOT NULL COMMENT '预约等待时间',
  `aLRe_UID` varchar(50) NOT NULL COMMENT 'UID',
  `aLRe_Card` varchar(50) NOT NULL COMMENT '卡号',
  `aLRe_ReservationNumber` varchar(50) NOT NULL COMMENT '预约号',
  `aLRe_CarNumber` varchar(50) NOT NULL COMMENT '车牌号',
  `aLRe_AppointmentStatus` int(11) NOT NULL COMMENT '1：预约锁定 2 预约取消 3：预约成功',
  `aLRe_PrepareExcuteResult` int(11) NOT NULL COMMENT '0：成功 ',
  `aLRe_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `aLRe_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_AppointmentLockingRecords`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约锁定记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_banner_list`
--

DROP TABLE IF EXISTS `tbl_banner_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_banner_list` (
  `pk_bl_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `bl_desc` varchar(250) NOT NULL DEFAULT '' COMMENT 'banner备注',
  `bl_url` varchar(500) NOT NULL DEFAULT '' COMMENT 'URL地址',
  `bl_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态默认0: 1 删除 2 下架',
  `bl_sort` smallint(1) unsigned NOT NULL DEFAULT '99' COMMENT '播放顺序',
  `bl_createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `bl_updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `bl_begin_time` datetime NOT NULL COMMENT '开始时间',
  `bl_end_time` datetime NOT NULL COMMENT '结束时间',
  `bl_provincecode` varchar(10) NOT NULL DEFAULT '' COMMENT '电站所属省份代码',
  `bl_citycode` varchar(10) NOT NULL DEFAULT '' COMMENT '电站所属城市代码',
  `bl_countycode` varchar(10) NOT NULL DEFAULT '' COMMENT '电站所属区县代码',
  PRIMARY KEY (`pk_bl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8 COMMENT='banner清单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_bespoke`
--

DROP TABLE IF EXISTS `tbl_bespoke`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_bespoke` (
  `pk_Bespoke` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `besp_ElectricPileId` int(11) NOT NULL COMMENT '预约电桩ID',
  `besp_BespokeTime` varchar(20) NOT NULL COMMENT '预约时间',
  `besp_BespokeRemark` varchar(200) DEFAULT NULL COMMENT '预约描述',
  `besp_BespokeTimes` varchar(100) DEFAULT NULL COMMENT '预约时间段',
  `besp_BespokePrice` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '预约单价',
  `besp_BespokeStatus` int(11) NOT NULL COMMENT '1：取消预约 2：预约结束(订单完成) 3：续预约中 4：预约中 5:预约确认中 6：预约失败 7:完成未结算',
  `besp_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预约状态',
  `besp_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '预约状态',
  `besp_ElectricPileHead` int(11) NOT NULL COMMENT '预约枪口id',
  `besp_UserInfo` int(11) NOT NULL COMMENT '用户id',
  `besp_ResePaymentCode` varchar(50) NOT NULL DEFAULT '' COMMENT '预约订单编号',
  `besp_FrozenAmt` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '实际金额',
  `besp_BeginTime` datetime NOT NULL COMMENT '用户开始时间',
  `besp_EndTime` datetime NOT NULL COMMENT '预约结束时间',
  `besp_RealityTime` datetime DEFAULT NULL COMMENT '实际预约结束时间',
  `besp_OrderType` int(11) NOT NULL DEFAULT '0' COMMENT '订单支付类型(0,未支付;1:订单结束)',
  `besp_appclientip` varchar(24) NOT NULL DEFAULT '0.0.0.0' COMMENT '手机后台IP',
  `besp_UserOrigin` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户来源 1:富士康; 2:吉利; 3:绿地; 4:浙誉; 5:车纷享; 以后根据情况再扩展或修改',
  `besp_OccurFrozenAmt` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '预约冻结金额',
  `besp_PayMode` int(11) NOT NULL DEFAULT '1' COMMENT '付费标识，1:先付费;2:后付费',
  `besp_OrgNo` int(11) NOT NULL DEFAULT '1000' COMMENT '充电伙伴标识，1000:爱充自己，其它',
  PRIMARY KEY (`pk_Bespoke`),
  KEY `index_bespoke_ElectricPileId` (`besp_ElectricPileId`) USING BTREE,
  KEY `index_bespoke_ElectricPileHead` (`besp_ElectricPileHead`) USING BTREE,
  KEY `index_bespoke_UserInfo` (`besp_UserInfo`) USING BTREE,
  KEY `index_besp_ResePaymentCode` (`besp_ResePaymentCode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=242 DEFAULT CHARSET=utf8 COMMENT='预约';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_blackrecord`
--

DROP TABLE IF EXISTS `tbl_blackrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_blackrecord` (
  `pk_BlackRecord` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `blRe_ElectricPileCode` int(11) NOT NULL COMMENT '充电桩编号',
  `blRe_SequenceNumber` int(11) DEFAULT NULL COMMENT '时间戳序号',
  `blRe_BlackNumber` int(11) DEFAULT NULL COMMENT '黑名单数量',
  `blRe_PhysicalCard` varchar(50) NOT NULL COMMENT '物理卡号',
  `blRe_BlackStatus` int(11) NOT NULL COMMENT '1：挂失 2：解挂 ',
  `blRe_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `blRe_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_BlackRecord`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='有卡黑名单记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_bomlist`
--

DROP TABLE IF EXISTS `tbl_bomlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_bomlist` (
  `pk_BomListId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键(序列号)',
  `pk_TypeSpanId` int(11) NOT NULL COMMENT '产品ID',
  `bl_HardwareNumber` varchar(30) NOT NULL DEFAULT '' COMMENT '硬件编号',
  `bl_HardwareVersion` varchar(30) NOT NULL DEFAULT '' COMMENT '硬件版本号',
  `bl_FirmwareNumber` varchar(30) NOT NULL DEFAULT '' COMMENT '固件编号',
  `bl_FirmwareVersion` varchar(30) NOT NULL DEFAULT '' COMMENT '固件版本号',
  `bl_ForceUpdate` smallint(6) DEFAULT '0' COMMENT '强制更新标识：0：不强制，1：强制更新 ',
  `bl_FileMd5` varchar(32) NOT NULL DEFAULT '' COMMENT '版本文件的md5值',
  `bl_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `bl_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_BomListId`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8 COMMENT='BOM清单表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_car_vin`
--

DROP TABLE IF EXISTS `tbl_car_vin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_car_vin` (
  `pk_car_vin` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cv_vin_code` varchar(20) NOT NULL COMMENT 'VIN码',
  `cv_name` varchar(50) NOT NULL DEFAULT '' COMMENT '汽车合作公司名字',
  `cv_servicemoney` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '服务费',
  `cv_isdelete` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否删除 默认 0 未删除 1 删除',
  `cv_createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cv_updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cv_license_number` varchar(10) NOT NULL DEFAULT '' COMMENT '车牌号',
  PRIMARY KEY (`pk_car_vin`),
  UNIQUE KEY `cv_vin_code` (`cv_vin_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=199 DEFAULT CHARSET=utf8 COMMENT='汽车VIN码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_carcompany`
--

DROP TABLE IF EXISTS `tbl_carcompany`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_carcompany` (
  `pk_carCompany` int(11) NOT NULL AUTO_INCREMENT COMMENT '汽车厂家主键',
  `carCompany_Name` varchar(200) NOT NULL DEFAULT '' COMMENT '汽车厂家名字',
  `carCompany_CreateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `carCompany_UpdateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `carCompany_Remark` varchar(200) NOT NULL DEFAULT '' COMMENT '备注字段',
  PRIMARY KEY (`pk_carCompany`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='汽车品牌';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_cardapplicationform`
--

DROP TABLE IF EXISTS `tbl_cardapplicationform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cardapplicationform` (
  `pk_CardApplicationForm` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请表ID',
  `caf_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户主表ID，不自增',
  `caf_phone` varchar(11) NOT NULL DEFAULT '0' COMMENT '联系电话',
  `caf_real_name` varchar(32) NOT NULL DEFAULT '' COMMENT '联系人姓名',
  `caf_address` varchar(64) NOT NULL DEFAULT '' COMMENT '邮寄地址',
  `caf_email` varchar(32) DEFAULT '' COMMENT '用户邮箱',
  `caf_idcard` varchar(32) DEFAULT '' COMMENT '用户身份证号',
  `caf_sex` smallint(1) DEFAULT '0' COMMENT '用户性别 (1:男 2：女)',
  `caf_birthday` varchar(20) DEFAULT '' COMMENT '用户生日',
  `caf_car_company_id` smallint(6) DEFAULT '0' COMMENT '用户汽车品牌ID',
  `caf_car_type_id` smallint(6) DEFAULT '0' COMMENT '用户汽车车型ID',
  `caf_vehicle_number` varchar(32) DEFAULT '' COMMENT '车架号',
  `caf_plate_num` varchar(10) DEFAULT '' COMMENT '车牌号',
  `caf_usercard` varchar(32) DEFAULT '' COMMENT '充电卡号',
  `caf_Status` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态 0：申请中，1：申请成功 , 2:申请失败',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_CardApplicationForm`)
) ENGINE=InnoDB AUTO_INCREMENT=480 DEFAULT CHARSET=utf8 COMMENT='充电卡申请表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_cargarage`
--

DROP TABLE IF EXISTS `tbl_cargarage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_cargarage` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(80) NOT NULL DEFAULT '' COMMENT '修理厂名称',
  `address` varchar(150) NOT NULL DEFAULT '' COMMENT '修理厂地址',
  `phone` varchar(30) NOT NULL DEFAULT '' COMMENT '电话',
  `longitude` varchar(20) NOT NULL DEFAULT '' COMMENT '经度',
  `latitude` varchar(20) NOT NULL DEFAULT '' COMMENT '维度',
  `img` varchar(50) NOT NULL DEFAULT '' COMMENT '图片',
  `note` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `edittime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8 COMMENT='修理厂信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_carinfo`
--

DROP TABLE IF EXISTS `tbl_carinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_carinfo` (
  `pk_CarInfo` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `CarInfo_StyleName` varchar(200) NOT NULL COMMENT '电动车类型名称',
  `CarInfo_MaxOdometer` decimal(6,2) NOT NULL COMMENT '电动车最大续航里程',
  `CarInfo_BatteryCapacity` decimal(6,2) DEFAULT NULL COMMENT '电动车电池容量',
  `CarInfo_CreateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `CarInfo_UpdateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `CarInfo_Remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `CarInfo_BrandIcon` varchar(500) DEFAULT NULL COMMENT '电动车品牌图标',
  `CarInfo_BrandName` varchar(200) DEFAULT NULL COMMENT '电动车品牌名称，根据配置参数内容表获取品牌名称',
  `CarInfo_CarStatus` int(11) NOT NULL COMMENT '状态(0启用，1禁用)',
  `CarInfo_ChargingTime` varchar(20) NOT NULL DEFAULT '' COMMENT '充电时间',
  `CarInfo_BatteryType` int(11) NOT NULL DEFAULT '0' COMMENT '电池类型',
  `CarInfo_CompanyId` int(11) NOT NULL DEFAULT '0' COMMENT '汽车厂家关联主键',
  `charging_mode` smallint(6) NOT NULL DEFAULT '0' COMMENT '充电模式 14交流5直流1交流直流',
  `power_interface` smallint(6) NOT NULL DEFAULT '0' COMMENT '接口标准 7国标20欧标19美标',
  PRIMARY KEY (`pk_CarInfo`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='电动车品牌类型详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_category`
--

DROP TABLE IF EXISTS `tbl_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_category` (
  `pk_Category` int(11) NOT NULL AUTO_INCREMENT,
  `cate_Name` varchar(30) NOT NULL COMMENT '商品名称',
  `cate_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cate_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_Category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品大分类表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_chargedeductionrecords`
--

DROP TABLE IF EXISTS `tbl_chargedeductionrecords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_chargedeductionrecords` (
  `pk_ChargeDeductionRecords` int(11) NOT NULL AUTO_INCREMENT,
  `cDRe_ElectricPileID` int(11) NOT NULL COMMENT '电桩ID(tbl_ElectricPile表中获取)',
  `cDRe_UsingMachineCode` varchar(50) NOT NULL COMMENT '使用终端机器编码',
  `cDRe_ChargingInterfaceIdentifier` int(50) NOT NULL DEFAULT '0' COMMENT '充电接口标识',
  `cDRe_DebitAmount` decimal(8,2) NOT NULL COMMENT '扣款金额',
  `cDRe_Balance` decimal(8,2) NOT NULL COMMENT '账户余额',
  `cDRe_Success` varchar(50) DEFAULT NULL COMMENT '1 成功 0 失败',
  `cDRe_Failure` varchar(500) DEFAULT NULL COMMENT '扣款失败原因',
  `cDRe_Code` varchar(50) NOT NULL COMMENT '充电订单编号与充电消费订单是主外键关系',
  `cDRe_TransactionNumber` varchar(50) NOT NULL COMMENT '交易流水号',
  PRIMARY KEY (`pk_ChargeDeductionRecords`)
) ENGINE=InnoDB AUTO_INCREMENT=154 DEFAULT CHARSET=utf8 COMMENT='充电扣款记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_chargeinfo`
--

DROP TABLE IF EXISTS `tbl_chargeinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_chargeinfo` (
  `pk_ChargeInfo` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `chIn_UsingmachineCode` varchar(50) NOT NULL DEFAULT '' COMMENT '使用端机器编号',
  `chIn_PowerStationId` int(11) NOT NULL DEFAULT '0' COMMENT '电站ID(tbl_PowerStation表中获取)',
  `chIn_InterfaceIdentification` int(11) NOT NULL DEFAULT '0' COMMENT '充电接口标识',
  `chIn_linkedStatus` int(11) NOT NULL COMMENT '0：关 1 开',
  `chIn_WorkingStatus` int(11) NOT NULL COMMENT '0：离线 1：故障 2：待机 3：工作 4：欠压故障 5 过压故障 6 过电流故障',
  `chIn_OutputVoltage` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '充电输出电压',
  `chIn_OutputCurrent` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '充电输出电流',
  `chIn_OutputStatus` int(11) NOT NULL COMMENT '0 关 1 开',
  `chIn_TotalActive` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '有功总电度',
  `chIn_TotalTime` int(11) NOT NULL DEFAULT '0' COMMENT '累计充电时间',
  `chIn_Type` int(11) NOT NULL DEFAULT '0' COMMENT '1：交流电 2：直流电',
  `chIn_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `chIn_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `chIn_UserInfo` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`pk_ChargeInfo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='充电时信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_chargeinfo_ac`
--

DROP TABLE IF EXISTS `tbl_chargeinfo_ac`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_chargeinfo_ac` (
  `pk_chargeinfo` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ep_code` varchar(50) NOT NULL DEFAULT '' COMMENT '充电桩编码',
  `station_id` int(11) NOT NULL DEFAULT '0' COMMENT '电站ID(tbl_PowerStation表中获取)',
  `output_voltage` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)充电输出电压',
  `output_current` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)充电输出电流',
  `ep_gun_no` smallint(2) NOT NULL DEFAULT '0' COMMENT '充电枪口编号(1,2)',
  `linked_status` int(11) NOT NULL DEFAULT '0' COMMENT '(毁弃)0：关 1 开',
  `working_status` int(11) NOT NULL DEFAULT '0' COMMENT '(毁弃)0：离线 1：故障 2：待机 3：工作 4：欠压故障 5 过压故障 6 过电流故障',
  `in_more_vol_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)交流输入过压告警,(0,不过压，1:过压)',
  `in_less_vol_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)交流输入欠压告警(0,不欠压，1欠压)',
  `loaded_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)交流电流过负荷告警(0:不过负荷,1:过负荷)',
  `out_relay_status` smallint(11) NOT NULL DEFAULT '0' COMMENT '输出继电器状态(0 关 1 开)',
  `total_active_dbnum` decimal(12,2) NOT NULL DEFAULT '0.00' COMMENT '有功总电度',
  `total_cd_time` int(11) NOT NULL DEFAULT '0' COMMENT '(毁弃)电桩累计总充电时间(从设立开始算起)',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `start_time` int(12) NOT NULL DEFAULT '0' COMMENT '开始充电时间(时间戳到秒)',
  `start_dl` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '开始充电度数',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `serialno` varchar(50) NOT NULL DEFAULT '' COMMENT '充电流水号',
  `ep_gun_close_status` smallint(6) NOT NULL DEFAULT '0' COMMENT '(毁弃)枪回收状态，0',
  `ep_gun_lid_status` smallint(6) NOT NULL DEFAULT '0' COMMENT '(毁弃)枪盖状态',
  `gun2car_comm_status` smallint(6) NOT NULL DEFAULT '0' COMMENT '(毁弃)车与桩通讯建立',
  `carplace_status` smallint(6) DEFAULT '0' COMMENT '(毁弃)车位占用情况；0：未使用，1：使用中',
  `chargePrice` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)当前价格',
  `chargedCost` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)已充金额',
  `chargedMeterNum` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '已充度数',
  `chargedTime` int(11) NOT NULL DEFAULT '0' COMMENT '(毁弃)已充时间(分钟)',
  `fronzeAmt` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '冻结金额',
  `cac_conn_battry` smallint(2) NOT NULL DEFAULT '0' COMMENT '是否连接电池（车辆）(0:正常;1：故障；2：未定义)',
  `cac_gun_close_status` smallint(2) NOT NULL DEFAULT '0' COMMENT '枪座状态(0:正常;1：故障；2：未定义)',
  `cac_gun_lid_status` smallint(2) NOT NULL DEFAULT '0' COMMENT '充电枪盖状态(0:正常;1：故障；2：未定义)',
  `cac_car_conn_pile` smallint(2) NOT NULL DEFAULT '0' COMMENT '车与桩建立通信信号(0:没有建立;1：建立；2：未定义)',
  `cac_carplace_status` smallint(2) NOT NULL DEFAULT '0' COMMENT '车位占用状态(雷达探测)(0:无车;1：有车；2：未定义)',
  `cac_card_reader_fault` smallint(2) NOT NULL DEFAULT '0' COMMENT '读卡器通讯故障(0:正常;1：故障；2：未定义)',
  `cac_urgent_stop_fault` smallint(2) NOT NULL DEFAULT '0' COMMENT '急停按钮动作故障(0:正常;1：故障；2：未定义)',
  `cac_arrester_fault` smallint(2) NOT NULL DEFAULT '0' COMMENT '避雷器故障(0:正常;1：故障；2：未定义)',
  `cac_insulation_check_fault` smallint(2) NOT NULL DEFAULT '0' COMMENT '绝缘检测故障(0:正常;1：故障；2：未定义)',
  `cac_gun_unconnect_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '充电枪未连接告警(0:正常;1：故障；2：未定义)',
  `cac_trans_record_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '交易记录已满告警(0:正常;1：故障；2：未定义)',
  `cac_meter_error` smallint(2) NOT NULL DEFAULT '0' COMMENT '电度表异常(0:正常;1：故障；2：未定义)',
  `cac_ac_in_vol_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '交流输入电压过压/欠压(0:正常;1:过压;2:欠压)',
  `cac_charge_over_temp` smallint(2) NOT NULL DEFAULT '0' COMMENT '充电机过温故障(0:正常;1:过温;2:不可信)',
  `cac_ac_current_over_load` smallint(2) NOT NULL DEFAULT '0' COMMENT '交流电流过负荷告警(0:正常;1:过负荷;2:欠负荷)',
  `cac_output_relay` smallint(2) NOT NULL DEFAULT '0' COMMENT '输出继电器状态(0:正常;1:正常;2:不可信)',
  `cac_charger_status` smallint(2) NOT NULL DEFAULT '0' COMMENT '充电机状态(0:正常;1：故障；2：未定义)',
  `cac_output_voltage` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '充电机输出电压',
  `cac_output_current` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '充电机输出电流',
  `cac_lock_status` smallint(11) NOT NULL DEFAULT '0' COMMENT '地锁状态(0：无法探测；1：降下；2：升起；3：运动中；4：故障 )',
  `cac_soc` int(11) NOT NULL DEFAULT '0' COMMENT 'SOC(电池容量)',
  `cac_charged_time` int(11) NOT NULL DEFAULT '0' COMMENT '累计充电时间,单位：分钟',
  `cac_charged_remain_time` int(11) NOT NULL DEFAULT '0' COMMENT '估计剩余时间,单位：分钟',
  `cac_total_active_meternum` decimal(8,3) NOT NULL DEFAULT '0.000' COMMENT '有功总电度数(精确到小数点后三位，倍数1000)',
  `cac_charged_cost` decimal(8,3) NOT NULL DEFAULT '0.000' COMMENT '已充金额',
  `cac_price` decimal(8,3) NOT NULL DEFAULT '0.000' COMMENT '单价(精确到小数点后三位，倍数1000)',
  PRIMARY KEY (`pk_chargeinfo`),
  KEY `index_chargeinfo_ac_code` (`ep_code`) USING BTREE,
  KEY `index_chargeinfo_ac_SID` (`station_id`) USING BTREE,
  KEY `index_chargeinfo_ac_UID` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2115 DEFAULT CHARSET=utf8 COMMENT='交流电充电实时信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_chargeinfo_dc`
--

DROP TABLE IF EXISTS `tbl_chargeinfo_dc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_chargeinfo_dc` (
  `pk_chargeinfo` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ep_code` varchar(50) NOT NULL DEFAULT '' COMMENT '充电桩编码',
  `station_id` int(11) NOT NULL DEFAULT '0' COMMENT '电站ID(tbl_PowerStation表中获取)',
  `output_voltage` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)充电输出电压',
  `output_current` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)充电输出电流',
  `soc` int(11) NOT NULL DEFAULT '0' COMMENT '(毁弃)SOC(电池容量)',
  `bp_lowest_temperature` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)电池组最低温度',
  `bp_highest_temperature` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)电池组最高温度',
  `ep_gun_no` smallint(2) NOT NULL DEFAULT '1' COMMENT '充电接口编号(1,2)',
  `linked_status` int(11) NOT NULL DEFAULT '0' COMMENT '(毁弃)0：关 1 开',
  `working_status` int(11) NOT NULL DEFAULT '0' COMMENT '(毁弃)0：离线 1：故障 2：待机 3：工作 4：欠压故障 5 过压故障 6 过电流故障',
  `out_more_vol_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)直流母线输出过压(0,不过压，1:过压)',
  `out_less_vol_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)直流母线输出欠压(0,不欠压，1欠压)',
  `loaded_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)蓄电池充电过流告警(0:不过流,1:过流)',
  `temperature_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)蓄电池模块采样点过温告警（0:不过温,1 过温）',
  `connect_battry` smallint(11) NOT NULL DEFAULT '0' COMMENT '(毁弃)是否连接电池(0 关 1 开)',
  `one_highest_vol` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)单体电池最高电压',
  `one_lowest_vol` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)单体电池最低电压',
  `total_active_dbnum` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '有功总电度',
  `bms_comm_exception` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)BMS 通信异常(0正常，1异常)',
  `total_cd_time` int(11) NOT NULL DEFAULT '0' COMMENT '(毁弃)电桩累计总充电时间(从设立开始算起)',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `start_time` int(12) NOT NULL DEFAULT '0' COMMENT '开始充电时间',
  `start_dl` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '开始充电度数',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `serialno` varchar(50) NOT NULL DEFAULT '' COMMENT '充电流水号',
  `carplace_status` smallint(6) DEFAULT '0' COMMENT '(毁弃)车位占用情况；0：未使用，1：使用中',
  `ep_gun_close_status` smallint(6) NOT NULL DEFAULT '0' COMMENT '(毁弃)枪回收状态，0',
  `ep_gun_lid_status` smallint(6) NOT NULL DEFAULT '0' COMMENT '(毁弃)枪盖状态',
  `gun2car_comm_status` smallint(6) NOT NULL DEFAULT '0' COMMENT '(毁弃)车与桩通讯建立',
  `chRe_SelfCheckStatus` tinyint(4) NOT NULL DEFAULT '0' COMMENT '(毁弃)自检状态 0:正常; 1:自检中; 2:自检完成; 3:自检失败',
  `urgentbtn_exception` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)急停按钮动作故障(0:正常,1:故障)',
  `readcard_comm_exception` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)读卡器通讯异常故障(0:正常,1:故障)',
  `meter_exception` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)直流电度表异常故障(0:正常,1:故障)',
  `insulation_exception` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)绝缘监测故障(0:正常,1:故障)',
  `battry_error_link` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)电池反接故障(0:正常,1:故障)',
  `arrester_exception` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)避雷器故障(0:正常,1:故障)',
  `gun_unlinked_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)充电枪未连接告警(0:正常,1:故障)',
  `charger_over_temp_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)充电机过温故障(0:正常,1:故障)',
  `fogs_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)烟雾报警告警(0:正常,1:故障)',
  `trans_record_full_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '(毁弃)交易记录已满告警(0:正常,1:故障)',
  `chargePrice` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)当前价格',
  `chargedCost` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '(毁弃)已充金额',
  `chargedMeterNum` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '已充度数',
  `chargedTime` int(11) NOT NULL DEFAULT '0' COMMENT '(毁弃)已充时间(分钟)',
  `fronzeAmt` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '冻结金额',
  `cdc_conn_battry` smallint(2) NOT NULL DEFAULT '0' COMMENT '是否连接电池（车辆）(0:正常;1：故障；2：未定义)',
  `cdc_gun_close_status` smallint(2) NOT NULL DEFAULT '0' COMMENT '枪座状态(0:正常;1：故障；2：未定义)',
  `cdc_gun_lid_status` smallint(2) NOT NULL DEFAULT '0' COMMENT '充电枪盖状态(0:正常;1：故障；2：未定义)',
  `cdc_car_conn_pile` smallint(2) NOT NULL DEFAULT '0' COMMENT '车与桩建立通信信号(0:没有建立;1：建立；2：未定义)',
  `cdc_carplace_status` smallint(2) NOT NULL DEFAULT '0' COMMENT '车位占用状态(雷达探测)(0:无车;1：有车；2：未定义)',
  `cdc_card_reader_fault` smallint(2) NOT NULL DEFAULT '0' COMMENT '读卡器通讯故障(0:正常;1：故障；2：未定义)',
  `cdc_urgent_stop_fault` smallint(2) NOT NULL DEFAULT '0' COMMENT '急停按钮动作故障(0:正常;1：故障；2：未定义)',
  `cdc_arrester_fault` smallint(2) NOT NULL DEFAULT '0' COMMENT '避雷器故障(0:正常;1：故障；2：未定义)',
  `cdc_insulation_check_fault` smallint(2) NOT NULL DEFAULT '0' COMMENT '绝缘检测故障(0:正常;1：故障；2：未定义)',
  `cdc_gun_unconnect_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '充电枪未连接告警(0:正常;1：故障；2：未定义)',
  `cdc_trans_record_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '交易记录已满告警(0:正常;1：故障；2：未定义)',
  `cdc_meter_error` smallint(2) NOT NULL DEFAULT '0' COMMENT '电度表异常(0:正常;1：故障；2：未定义)',
  `cdc_ac_in_vol_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '交流输入电压过压/欠压(0:正常;1:过压;2:欠压)',
  `cdc_charge_over_temp` smallint(2) NOT NULL DEFAULT '0' COMMENT '充电机过温故障(0:正常;1:过温;2:不可信)',
  `cdc_ac_current_over_load` smallint(2) NOT NULL DEFAULT '0' COMMENT '交流电流过负荷告警(0:正常;1:过负荷;2:欠负荷)',
  `cdc_output_relay` smallint(2) NOT NULL DEFAULT '0' COMMENT '输出继电器状态(0:正常;1:正常;2:不可信)',
  `cdc_charger_status` smallint(2) NOT NULL DEFAULT '0' COMMENT '充电机状态(0:正常;1：故障；2：未定义)',
  `cdc_output_voltage` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '充电机输出电压',
  `cdc_output_current` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '充电机输出电流',
  `cdc_lock_status` smallint(11) NOT NULL DEFAULT '0' COMMENT '地锁状态(0：无法探测；1：降下；2：升起；3：运动中；4：故障 )',
  `cdc_soc` int(11) NOT NULL DEFAULT '0' COMMENT 'SOC(电池容量)',
  `cdc_charged_time` int(11) NOT NULL DEFAULT '0' COMMENT '累计充电时间,单位：分钟',
  `cdc_charged_remain_time` int(11) NOT NULL DEFAULT '0' COMMENT '估计剩余时间,单位：分钟',
  `cdc_total_active_meternum` decimal(8,3) NOT NULL DEFAULT '0.000' COMMENT '有功总电度数(精确到小数点后三位，倍数1000)',
  `cdc_charged_cost` decimal(8,3) NOT NULL DEFAULT '0.000' COMMENT '已充金额',
  `cdc_price` decimal(8,3) NOT NULL DEFAULT '0.000' COMMENT '单价(精确到小数点后三位，倍数1000)',
  `cdc_battry_errlink` smallint(2) NOT NULL DEFAULT '0' COMMENT '电池反接故障(0:正常;1:故障)',
  `cdc_fogs_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '烟雾报警故障(0:正常;1:告警)',
  `cdc_bms_comm_exception` smallint(2) NOT NULL DEFAULT '0' COMMENT 'BMS 通信异常(0:正常;1:告警)',
  `cdc_dc_meter_exception` smallint(2) NOT NULL DEFAULT '0' COMMENT '直流电度表异常故障(0:正常;1:告警)',
  `cdc_out_over_current` smallint(2) NOT NULL DEFAULT '0' COMMENT '直流输出过流告警(0:正常;1:过流)',
  `cdc_car_charge_mode` smallint(2) NOT NULL DEFAULT '0' COMMENT '0:不可信;1:恒压;2:恒流',
  `cdc_soc_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '0:正常;1:过高;2:过低',
  `cdc_mod_sample_over_temp` smallint(2) NOT NULL DEFAULT '0' COMMENT '蓄电池模块采样点过温告警0:正常;1:过温;2:不可信',
  `cdc_out_linker_over_temp` smallint(2) NOT NULL DEFAULT '0' COMMENT '输出连接器过温0:正常;1:过温;2:不可信',
  `cdc_out_linker_status` smallint(2) NOT NULL DEFAULT '0' COMMENT '整车动力蓄电池组输出连接器连接状态0:正常;1:不正常;2:不可信',
  `cdc_whole_over_current_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '整车蓄电池充电过流告警0:正常;1:过温;2:不可信',
  `cdc_charge_vol_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT '直流母线输出过压/欠压(0:正常;1:过压;2:欠压)',
  `cdc_bms_vol_warn` smallint(2) NOT NULL DEFAULT '0' COMMENT 'BMS过压/欠压告警(0:正常;1:过压;2:欠压)',
  `cdc_single_battery_voltage` smallint(11) NOT NULL DEFAULT '0' COMMENT '单体蓄电池最高电压和组号',
  `cdc_bp_max_temp` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '蓄电池最高温度',
  `cdc_bp_min_temp` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '蓄电池最低温度',
  `cdc_sum_battery_voltage` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '整车动力电池总电压(精度0.1，单位v)',
  `cdc_a_voltage` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'A相电压(精度0.1，单位v)',
  `cdc_b_voltage` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'B相电压(精度0.1，单位v)',
  `cdc_c_voltage` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'C相电压(精度0.1，单位v)',
  `cdc_a_current` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'A相电流(精度0.1，单位A)',
  `cdc_b_current` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'B相电流(精度0.1，单位A)',
  `cdc_c_current` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'C相电流(精度0.1，单位A)',
  `cdc_max_out_vol` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '最高输出电压(精度0.1，单位v)',
  `cdc_min_out_vol` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '最低输出电压(精度0.1，单位v)',
  `cdc_max_out_current` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '最大输出电流(精度0.1，单位A)',
  `cdc_VIN` varchar(30) NOT NULL DEFAULT '0' COMMENT '车辆识别码',
  `cdc_ep_temperature` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '电桩内部温度',
  PRIMARY KEY (`pk_chargeinfo`),
  KEY `index_chargeinfo_dc_code` (`ep_code`) USING BTREE,
  KEY `index_chargeinfo_dc_SID` (`station_id`) USING BTREE,
  KEY `index_chargeinfo_dc_UID` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1886 DEFAULT CHARSET=utf8 COMMENT='直流电充电实时信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_chargingfaultrecord`
--

DROP TABLE IF EXISTS `tbl_chargingfaultrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_chargingfaultrecord` (
  `pk_ChargingFaultRecord` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cFRe_UsingMachineCode` varchar(50) NOT NULL DEFAULT '' COMMENT '使用终端机器编码',
  `cFRe_EphNo` smallint(6) NOT NULL DEFAULT '0' COMMENT '枪头编号',
  `cFRe_ElectricPileID` int(11) NOT NULL DEFAULT '0' COMMENT '电桩ID(tbl_ElectricPile表中获取)',
  `cFRe_ElectricPileName` varchar(50) NOT NULL DEFAULT '' COMMENT '电桩名称',
  `cFRe_ChargingSarttime` datetime NOT NULL COMMENT '故障开始时间',
  `cFRe_TransactionNumber` varchar(40) NOT NULL DEFAULT '' COMMENT '订单编号',
  `cFRe_FaultCause` varchar(500) NOT NULL DEFAULT '' COMMENT '故障原因',
  `cFRe_Createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `cFRe_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_ChargingFaultRecord`),
  KEY `idx_cfre_electricpileid` (`cFRe_ElectricPileID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=188611 DEFAULT CHARSET=utf8 COMMENT='充电故障记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_chargingorder`
--

DROP TABLE IF EXISTS `tbl_chargingorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_chargingorder` (
  `pk_ChargingOrder` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `chOr_Code` varchar(50) NOT NULL DEFAULT '' COMMENT '充电订单编号',
  `chOr_AppointmenCode` varchar(50) NOT NULL COMMENT '预约流水号',
  `chOr_PileNumber` varchar(50) NOT NULL COMMENT '桩体编号',
  `chOr_UserId` int(11) NOT NULL COMMENT '用户ID(企业ID)',
  `chOr_Type` int(11) NOT NULL COMMENT '1：普通用户 2：个体商家用户 3：纯商家用户',
  `chOr_Moeny` decimal(10,4) DEFAULT NULL,
  `chOr_tipPower` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '尖时段用电度数',
  `chOr_peakPower` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '峰时段用电度数',
  `chOr_usualPower` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '平时段用电度数',
  `chOr_valleyPower` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '谷时段用电度数',
  `chOr_QuantityElectricity` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '总电量',
  `chOr_TimeQuantum` varchar(50) NOT NULL COMMENT '12:00-13:00',
  `chOr_Muzzle` smallint(50) NOT NULL DEFAULT '0' COMMENT '枪口编号(1,2,3……)',
  `chOr_ChargingStatus` int(11) NOT NULL DEFAULT '0' COMMENT '1：待支付 ,2：支付成功 ,3: 完成操作,4: 异常订单,5:临时结算',
  `chOr_TransType` int(11) NOT NULL COMMENT '1：断网上传 2 在线上传',
  `chOr_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `chOr_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `chOr_UserName` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名称',
  `chOr_TransactionNumber` varchar(50) NOT NULL COMMENT '交易流水号',
  `chOr_OrderType` int(11) NOT NULL DEFAULT '0' COMMENT '1：支付宝 2：银联支付',
  `chOr_ChargeMoney` decimal(10,4) DEFAULT NULL,
  `chOr_ServiceMoney` decimal(10,4) DEFAULT NULL,
  `begin_charge_time` varchar(32) NOT NULL COMMENT '充电开始时间',
  `end_charge_time` varchar(32) NOT NULL COMMENT '充电结束时间',
  `start_soc` int(11) NOT NULL DEFAULT '0' COMMENT '开始SOC(电池容量)',
  `end_soc` int(11) NOT NULL DEFAULT '0' COMMENT '结束SOC(电池容量)',
  `chOr_UserOrigin` smallint(4) NOT NULL DEFAULT '0' COMMENT '用户来源 1:富士康; 2:吉利; 3:绿地; 4:浙誉; 5:车纷享; 以后根据情况再扩展或修改',
  `pk_UserCard` int(11) NOT NULL DEFAULT '0' COMMENT '卡主键',
  `chOr_OrgNo` int(11) NOT NULL DEFAULT '1000' COMMENT '充电伙伴标识，1000:爱充自己，其它',
  `chOr_CouponMoney` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '优惠券抵扣金额',
  `pk_Coupon` int(11) NOT NULL DEFAULT '0' COMMENT '第三方优惠信息主键',
  `chOr_Third_Type` smallint(1) unsigned NOT NULL DEFAULT '0' COMMENT ' 默认 0 无优惠  优惠券 1 VIN码 2',
  `chor_parter_user_logo` varchar(30) NOT NULL DEFAULT '' COMMENT '第三方合作方用户标识',
  `chor_parter_extradata` varchar(50) NOT NULL DEFAULT '' COMMENT '第三方流水号或者会话ID',
  PRIMARY KEY (`pk_ChargingOrder`),
  KEY `index_chargingorder_PileNumber` (`chOr_PileNumber`) USING BTREE,
  KEY `index_chargingorder_Code` (`chOr_Code`) USING BTREE,
  KEY `index_chor_TransactionNumber` (`chOr_TransactionNumber`) USING BTREE,
  KEY `idx_chor_userid` (`chOr_UserId`) USING BTREE,
  KEY `idx_chor_usercard` (`pk_UserCard`) USING BTREE,
  KEY `idx_chor_pk_coupon` (`pk_Coupon`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=637194 DEFAULT CHARSET=utf8 COMMENT='充电消费订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_chargingrecord`
--

DROP TABLE IF EXISTS `tbl_chargingrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_chargingrecord` (
  `pk_ChargingRecord` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `chRe_ElectricPileID` int(11) NOT NULL COMMENT '电桩ID(tbl_ElectricPile中获取)',
  `chRe_UsingMachineCode` varchar(50) NOT NULL DEFAULT '' COMMENT '使用终端机器编码',
  `chRe_TransactionNumber` varchar(50) NOT NULL DEFAULT '' COMMENT '交易流水号',
  `chRe_ReservationNumber` varchar(20) NOT NULL DEFAULT '' COMMENT '预约号',
  `chRe_StartDate` datetime NOT NULL COMMENT '充电开始时间',
  `chRe_ChargingNumber` int(11) NOT NULL COMMENT '充电抢编号',
  `chRe_ChargingMethod` int(11) NOT NULL COMMENT '1:自动充满 2:按金额充 3:按里程4:按度数',
  `chRe_RestTime` int(11) NOT NULL DEFAULT '0' COMMENT '充满电剩余时间',
  `chRe_EndDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束充电时间',
  `chRe_Code` varchar(50) NOT NULL COMMENT '充电订单编号与充电消费订单是主外键关系',
  `chRe_BeginShowsNumber` varchar(20) NOT NULL DEFAULT '' COMMENT '开始充电表示数',
  `chRe_EndShowsNumber` varchar(20) NOT NULL DEFAULT '' COMMENT '结束充电表示数',
  `user_phone` varchar(20) NOT NULL DEFAULT '' COMMENT '用户手机，与userinfo表里的手机号关联',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `chRe_UserOrigin` smallint(4) NOT NULL DEFAULT '0' COMMENT '用户来源 1富士康;2吉利;3绿地;4;浙誉;5.车纷享; 以后根据情况再扩展或修改',
  `chRe_Status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '充电状态,0:开始充电;1:结束充电;2:接受到充电客户的命令;3:充电失败,4:等待插枪;5:临时结算;6:结算;7:结算完成;',
  `chRe_StopCause` tinyint(4) NOT NULL DEFAULT '0' COMMENT '停止充电原因',
  `chRe_JPrice` decimal(8,4) DEFAULT NULL,
  `chRe_FPrice` decimal(8,4) DEFAULT NULL,
  `chRe_PPrice` decimal(8,4) DEFAULT NULL,
  `chRe_GPrice` decimal(8,4) DEFAULT NULL,
  `chRe_QuantumDate` varchar(500) NOT NULL DEFAULT '' COMMENT '时间段',
  `chRe_FrozenAmt` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '预冻结金额',
  `chRe_RealAmt` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '实际金额(服务器重启后记录)',
  `pk_UserCard` int(11) NOT NULL DEFAULT '0' COMMENT '卡主键',
  `chRe_PayMode` int(11) NOT NULL DEFAULT '1' COMMENT '付费标识，1:先付费;2:后付费',
  `chRe_OrgNo` int(11) NOT NULL DEFAULT '1000' COMMENT '充电伙伴标识，1000:爱充自己，其它',
  `chRe_ServiceCharge` decimal(8,4) DEFAULT NULL,
  `chRe_ThirdCode` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '第三方优惠表主键 默认0',
  `chRe_ThirdType` smallint(1) unsigned NOT NULL DEFAULT '0' COMMENT '优惠类型 默认 0 无优惠 VIN码 2',
  `chre_parter_user_logo` varchar(30) NOT NULL DEFAULT '' COMMENT '第三方合作方用户标识',
  `chre_parter_extradata` varchar(50) NOT NULL DEFAULT '' COMMENT '第三方流水号或者会话ID',
  `chre_usrgateip` varchar(15) NOT NULL DEFAULT '' COMMENT '用户网关IP',
  PRIMARY KEY (`pk_ChargingRecord`),
  KEY `index_chRe_Code` (`chRe_Code`) USING BTREE,
  KEY `index_chRe_UsingMachineCode` (`chRe_UsingMachineCode`) USING BTREE,
  KEY `index_chRe_TransactionNumber` (`chRe_TransactionNumber`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=690416 DEFAULT CHARSET=utf8 COMMENT='充电记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_checkinformation`
--

DROP TABLE IF EXISTS `tbl_checkinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_checkinformation` (
  `pk_checkinformation` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ci_UserID` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `ci_Invoicecheck` smallint(4) NOT NULL DEFAULT '0' COMMENT '开票说明查看类型：0-未查看 ，1-已查看',
  `ci_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ci_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_checkinformation`)
) ENGINE=InnoDB AUTO_INCREMENT=1348 DEFAULT CHARSET=utf8 COMMENT='信息查看记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_commitlog`
--

DROP TABLE IF EXISTS `tbl_commitlog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_commitlog` (
  `pk_CommitLog` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coLo_LogName` varchar(20) NOT NULL COMMENT '用户名称',
  `coLo_IpAddress` varchar(20) NOT NULL COMMENT 'IP地址',
  `coLo_LogContent` varchar(500) NOT NULL COMMENT '操作内容',
  `coLo_Status` int(11) NOT NULL COMMENT '0 显示 -1：已删除',
  `coLo_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `coLo_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `coLo_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '日志来源用户ID',
  PRIMARY KEY (`pk_CommitLog`)
) ENGINE=InnoDB AUTO_INCREMENT=45369 DEFAULT CHARSET=utf8 COMMENT='操作日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_commonquestions`
--

DROP TABLE IF EXISTS `tbl_commonquestions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_commonquestions` (
  `pk_CommonQuestions` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coQu_CommonQuestionsName` varchar(500) NOT NULL COMMENT '常见问题',
  `coQu_ReplyContent` varchar(500) DEFAULT NULL COMMENT '回复内容',
  `coQu_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `coQu_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_CommonQuestions`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='常见问题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_company`
--

DROP TABLE IF EXISTS `tbl_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_company` (
  `pk_CompanyId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cpy_CompanyName` varchar(50) NOT NULL DEFAULT '' COMMENT '企业名称',
  `cpy_CompanyAddress` varchar(500) NOT NULL DEFAULT '' COMMENT '企业地址',
  `cpy_CompanyEmail` varchar(50) NOT NULL DEFAULT '' COMMENT '企业邮箱',
  `cpy_PostCode` int(11) NOT NULL DEFAULT '0' COMMENT '邮编',
  `cpy_ScopeBusiness` varchar(500) NOT NULL DEFAULT '' COMMENT '经营范围',
  `cpy_AuthorizedName` varchar(50) NOT NULL DEFAULT '' COMMENT '授权人名称',
  `cpy_AuthorizedPhone` varchar(20) NOT NULL DEFAULT '' COMMENT '授权人联系电话',
  `cpy_AuthorizedCard` varchar(500) NOT NULL DEFAULT '' COMMENT '授权人身份证',
  `cpy_BusinessLicence` varchar(500) NOT NULL DEFAULT '' COMMENT '营业执照',
  `cpy_OrganizationCode` varchar(100) NOT NULL DEFAULT '' COMMENT '组织机构代码',
  `cpy_TorontoHospital` varchar(500) NOT NULL DEFAULT '' COMMENT '税务登记证',
  `cpy_Authorization` varchar(500) NOT NULL DEFAULT '' COMMENT '授权证明',
  `cpy_MailingAddress` varchar(500) NOT NULL DEFAULT '' COMMENT '邮寄地址',
  `cpy_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cpy_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `cpy_PicType` varchar(20) NOT NULL DEFAULT '' COMMENT '1:授权人身份证 2:营业执照 3:税务登记证 4:授权证明',
  `cpy_CompanyNumber` int(4) NOT NULL DEFAULT '0' COMMENT '公司标识',
  `cpy_num` int(4) NOT NULL DEFAULT '0' COMMENT '公司允许离线充电的最大数值',
  `cpy_operate` int(4) DEFAULT '0' COMMENT '是否是联营合作商 0.否 1是',
  `cpy_province` varchar(32) DEFAULT NULL COMMENT '省编码',
  `cpy_city` varchar(32) DEFAULT NULL COMMENT '市编码',
  PRIMARY KEY (`pk_CompanyId`)
) ENGINE=InnoDB AUTO_INCREMENT=342 DEFAULT CHARSET=utf8 COMMENT='公司表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_company_rela`
--

DROP TABLE IF EXISTS `tbl_company_rela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_company_rela` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pk_cpy_operate_id` int(11) NOT NULL DEFAULT '0' COMMENT '合作公司ID',
  `pk_PowerStation` int(11) DEFAULT NULL COMMENT '电站ID',
  `pk_ElectricPile` int(11) DEFAULT NULL COMMENT '桩ID',
  `createdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `flag` int(4) DEFAULT NULL COMMENT 'sql执行标志',
  PRIMARY KEY (`pk_id`),
  KEY `pk_cpy_operate_id` (`pk_cpy_operate_id`,`pk_PowerStation`,`pk_ElectricPile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=474108 DEFAULT CHARSET=utf8 COMMENT='合作公司充电权限关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_company_rela_copy`
--

DROP TABLE IF EXISTS `tbl_company_rela_copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_company_rela_copy` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pk_cpy_operate_id` int(11) NOT NULL DEFAULT '0' COMMENT '合作公司ID',
  `pk_PowerStation` int(11) DEFAULT NULL COMMENT '电站ID',
  `pk_ElectricPile` int(11) DEFAULT NULL COMMENT '桩ID',
  `createdate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `flag` int(4) DEFAULT NULL COMMENT 'sql执行标志',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=405965 DEFAULT CHARSET=utf8 COMMENT='合作公司充电权限关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_concentrator`
--

DROP TABLE IF EXISTS `tbl_concentrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_concentrator` (
  `pk_concentratorID` int(11) NOT NULL AUTO_INCREMENT COMMENT '集中器ID',
  `coct_concentratorName` varchar(50) NOT NULL DEFAULT '' COMMENT '集中器名称',
  `coct_SIM_MAC` varchar(20) NOT NULL DEFAULT '' COMMENT 'SIM卡号',
  `coct_SIM_CODE` varchar(11) NOT NULL DEFAULT '' COMMENT 'SIM卡编号(电话号码)',
  `coct_SIM_TYPE` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'SIM来源(1：联通,2：电信,3：移动)',
  `coct_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `coct_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `coct_user_Id` int(11) DEFAULT '0' COMMENT '所属用户ID',
  `coct_user_name` varchar(32) DEFAULT '' COMMENT '用户姓名',
  `coct_state` tinyint(4) DEFAULT '1' COMMENT '集中器状态(0：离线,1：上线 2：无效)',
  `coct_TypeSpanId` int(11) NOT NULL DEFAULT '0' COMMENT '产品ID',
  PRIMARY KEY (`pk_concentratorID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='集中器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_configcontent`
--

DROP TABLE IF EXISTS `tbl_configcontent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_configcontent` (
  `pk_ConfigContent` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coCo_ConfigParameterID` int(11) NOT NULL COMMENT '配置参数ID',
  `coCo_Content` varchar(500) NOT NULL COMMENT '内容',
  `coCo_ConfigPStatus` int(11) NOT NULL DEFAULT '0' COMMENT '状态(0启用，1禁用)',
  `coCo_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `coCo_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `coCo_memo` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`pk_ConfigContent`),
  KEY `FK_Reference_25` (`coCo_ConfigParameterID`) USING BTREE,
  CONSTRAINT `tbl_configcontent_ibfk_1` FOREIGN KEY (`coCo_ConfigParameterID`) REFERENCES `tbl_configparameter` (`pk_ConfigParameter`)
) ENGINE=InnoDB AUTO_INCREMENT=184 DEFAULT CHARSET=utf8 COMMENT='配置参数内容';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_configparameter`
--

DROP TABLE IF EXISTS `tbl_configparameter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_configparameter` (
  `pk_ConfigParameter` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coPa_Name` varchar(50) NOT NULL COMMENT '配置参数名称',
  `coPa_Type` int(11) NOT NULL COMMENT '1：桩体配置参数 2：商城配置参数 3：其他配置参数。等等‘’‘’‘’‘',
  `coPa_Status` int(11) NOT NULL DEFAULT '0' COMMENT '状态(0启用，1禁用)',
  `coPa_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `coPa_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `coCo_memo` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`pk_ConfigParameter`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='配置参数';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_consultinfo`
--

DROP TABLE IF EXISTS `tbl_consultinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_consultinfo` (
  `pk_ConsultInfo` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `coIn_User_Login` varchar(20) NOT NULL COMMENT '用户帐号',
  `coIn_Contact` varchar(20) NOT NULL COMMENT '联系人',
  `coIn_ConsultContent` varchar(500) NOT NULL COMMENT '咨询内容',
  `coIn_ConsultAddress` varchar(100) NOT NULL COMMENT '联系地址',
  `coIn_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `coIn_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_ConsultInfo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='咨询信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_consume_record`
--

DROP TABLE IF EXISTS `tbl_consume_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_consume_record` (
  `pk_consume_record` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ep_code` varchar(40) NOT NULL COMMENT '桩体编号',
  `ep_gun_no` smallint(4) NOT NULL DEFAULT '0' COMMENT '枪口编号(1,2,3……)',
  `transaction_number` varchar(40) NOT NULL COMMENT '交易流水号',
  `account_type` smallint(4) NOT NULL DEFAULT '0' COMMENT '账号类型',
  `user_origin` smallint(4) NOT NULL DEFAULT '0' COMMENT '用户来源',
  `user_account` varchar(50) DEFAULT NULL COMMENT '用户编号',
  `trans_type` smallint(4) NOT NULL COMMENT '离线交易类型',
  `begin_charge_time` int(11) NOT NULL DEFAULT '0' COMMENT '充电开始时间',
  `end_charge_time` int(11) NOT NULL DEFAULT '0' COMMENT '充电结束时间',
  `tip_power` int(11) NOT NULL DEFAULT '0' COMMENT '尖时段用电度数',
  `tip_money` int(11) NOT NULL DEFAULT '0' COMMENT '尖时段金额',
  `peak_power` int(11) NOT NULL DEFAULT '0' COMMENT '峰时段用电度数',
  `peak_money` int(11) NOT NULL DEFAULT '0' COMMENT '峰时段金额',
  `usual_power` int(11) NOT NULL DEFAULT '0' COMMENT '平时段用电度数',
  `usual_money` int(11) NOT NULL DEFAULT '0' COMMENT '平时段金额',
  `valley_power` int(11) NOT NULL DEFAULT '0' COMMENT '谷时段用电度数',
  `valley_money` int(11) NOT NULL DEFAULT '0' COMMENT '谷时段金额',
  `total_power` int(11) NOT NULL DEFAULT '0' COMMENT '总电量',
  `charge_money` int(11) NOT NULL DEFAULT '0' COMMENT '总充电金额',
  `service_money` int(11) NOT NULL DEFAULT '0' COMMENT '充电服务费',
  `start_meter_num` int(11) NOT NULL DEFAULT '0' COMMENT '开始充电总电量',
  `end_meter_num` int(11) NOT NULL DEFAULT '0' COMMENT '结束充电总电量',
  `stop_cause` smallint(4) NOT NULL DEFAULT '0' COMMENT '停止充电原因',
  `car_vin_code` varchar(40) DEFAULT NULL COMMENT 'VIN码',
  `start_soc` smallint(4) DEFAULT NULL COMMENT '开始SOC(电池容量)',
  `end_soc` smallint(4) DEFAULT NULL COMMENT '结束SOC(电池容量)',
  `calc_bit_type` smallint(1) unsigned NOT NULL DEFAULT '0' COMMENT '默认两位 0 四位 1 ',
  `opt_flag` smallint(4) NOT NULL DEFAULT '0' COMMENT '操作标志',
  `create_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`pk_consume_record`),
  KEY `index_consume_record_code` (`ep_code`),
  KEY `index_chor_transaction_number` (`transaction_number`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=245070 DEFAULT CHARSET=utf8 COMMENT='充电消费订单原始数据';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_coupon`
--

DROP TABLE IF EXISTS `tbl_coupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_coupon` (
  `pk_Coupon` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pk_Activity` int(11) NOT NULL DEFAULT '0' COMMENT '活动表主键',
  `pk_CouponVariety` int(11) NOT NULL DEFAULT '0' COMMENT '优惠券品种主键',
  `cp_Status` smallint(4) NOT NULL DEFAULT '0' COMMENT '优惠券状态（1-已使用）',
  `cp_Limitation` smallint(4) NOT NULL DEFAULT '0' COMMENT '电桩限制（1-仅限交流电桩，2-仅限直流电装，3-不限充电桩）',
  `cp_CouponValue` int(4) NOT NULL DEFAULT '0' COMMENT '优惠券面值',
  `cp_CouponCondition` int(4) NOT NULL DEFAULT '0' COMMENT '优惠券使用条件',
  `cp_CouponCode` varchar(10) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '优惠码',
  `cp_UserId` int(11) NOT NULL DEFAULT '0' COMMENT '持有人（用户ID）',
  `cp_BeginDate` date NOT NULL DEFAULT '1900-01-01' COMMENT '生效时间',
  `cp_EndDate` date NOT NULL DEFAULT '1900-01-01' COMMENT '失效时间',
  `cp_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cp_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间(使用时间)',
  PRIMARY KEY (`pk_Coupon`)
) ENGINE=InnoDB AUTO_INCREMENT=33298 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='优惠券表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_couponvariety`
--

DROP TABLE IF EXISTS `tbl_couponvariety`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_couponvariety` (
  `pk_CouponVariety` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `cova_ActivityName` varchar(50) NOT NULL DEFAULT '' COMMENT '活动名称',
  `cova_Limitation` smallint(4) NOT NULL DEFAULT '0' COMMENT '电桩限制（1-仅限交流电桩，2-仅限直流电装，3-不限充电桩）',
  `cova_Stutas` smallint(4) NOT NULL DEFAULT '0' COMMENT '状态（0-上架，1-下架）',
  `cova_CouponValue` int(4) NOT NULL DEFAULT '0' COMMENT '优惠券面值',
  `cova_CouponCondition` int(4) NOT NULL DEFAULT '0' COMMENT '优惠券使用条件',
  `cova_CouponTerm` int(4) NOT NULL DEFAULT '0' COMMENT '有效期（天）',
  `cova_CreateUserId` varchar(20) NOT NULL DEFAULT '' COMMENT '创建人',
  `cova_UpdateUserId` varchar(20) NOT NULL DEFAULT '' COMMENT '操作人',
  `cova_Remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `cova_Label` varchar(20) NOT NULL DEFAULT '' COMMENT '优惠券标签',
  `cova_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `cova_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_CouponVariety`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='优惠券品种表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_dispatchorder`
--

DROP TABLE IF EXISTS `tbl_dispatchorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_dispatchorder` (
  `pk_DispatchOrder` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `diOr_Code` varchar(30) NOT NULL COMMENT '主键',
  `diOr_OrderId` int(11) NOT NULL COMMENT '订单Id',
  `diOr_Express` varchar(30) NOT NULL COMMENT '快递公司',
  `diOr_Remark` varchar(200) DEFAULT NULL COMMENT '快递公司',
  `diOr_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `diOr_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_DispatchOrder`),
  KEY `FK_Reference_15` (`diOr_OrderId`) USING BTREE,
  CONSTRAINT `tbl_dispatchorder_ibfk_1` FOREIGN KEY (`diOr_OrderId`) REFERENCES `tbl_order` (`pk_Order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='运单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_earnings`
--

DROP TABLE IF EXISTS `tbl_earnings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_earnings` (
  `pk_Earnings` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ear_Type` int(11) NOT NULL COMMENT '收益类型',
  `ear_PurchaseHistoryTime` datetime NOT NULL COMMENT '收益时间',
  `ear_Monetary` decimal(8,2) NOT NULL COMMENT '收益金额',
  `ear_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收益时间',
  `ear_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `ear_Content` varchar(255) DEFAULT NULL COMMENT '收益内容',
  `ear_UserId` int(11) NOT NULL COMMENT '用户ID',
  `ear_ConsumerRemark` varchar(500) NOT NULL DEFAULT '' COMMENT '收益备注',
  PRIMARY KEY (`pk_Earnings`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='收益表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_elctrcplscrnconfgurtn`
--

DROP TABLE IF EXISTS `tbl_elctrcplscrnconfgurtn`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_elctrcplscrnconfgurtn` (
  `pk_ElctrcPlScrnConfgurtn` int(11) NOT NULL AUTO_INCREMENT,
  `ePSC_Name` varchar(100) NOT NULL COMMENT '配置名称',
  `ePSC_Type` int(11) NOT NULL COMMENT '配置类型 1-电桩状态 2-电桩枪口状态 3-电桩充电方式 4-电桩类型 5-电桩枪口连接方式 6-电桩额定功率 7-最大电流 8-桩体用途 9-搜索半径 10-支持预约',
  `ePSC_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ePSC_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_ElctrcPlScrnConfgurtn`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='电桩配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricpile`
--

DROP TABLE IF EXISTS `tbl_electricpile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricpile` (
  `pk_ElectricPile` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `elPi_ElectricPileName` varchar(50) NOT NULL DEFAULT '' COMMENT '电桩名称',
  `elPi_ElectricPileCode` varchar(50) NOT NULL DEFAULT '' COMMENT '桩体编号',
  `elPi_ElectricPileAddress` varchar(200) NOT NULL DEFAULT '' COMMENT '地址',
  `elPi_Longitude` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '电桩地址经度',
  `elPi_Latitude` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '电桩地址维度',
  `elPi_PowerNumber` int(11) NOT NULL DEFAULT '1' COMMENT '电桩枪口数量',
  `elPi_State` int(11) NOT NULL DEFAULT '0' COMMENT '电桩状态（0-草稿 3-已驳回 5-提交审核  10-专属 12-分享申请中 15-分享）',
  `elPi_RejectionReason` varchar(500) NOT NULL DEFAULT '' COMMENT '审核驳回原因',
  `elPi_Type` int(11) NOT NULL DEFAULT '0' COMMENT '电桩类型，配置参数内容的ID （1-落地式，2-壁挂式，11-一体式，12-分体式）',
  `elPi_PowerUser` int(11) NOT NULL DEFAULT '0' COMMENT '电桩用途，配置参数内容的ID（电动车，电动自行车，多功能（电动车、电动自行车、手机USB））',
  `elPi_ChargingMode` int(11) NOT NULL DEFAULT '0' COMMENT '电桩充电方式，配置参数内容的ID （5-直流充电桩，14-交流充电桩）',
  `elPi_PowerSize` int(11) NOT NULL DEFAULT '0' COMMENT '电桩额定功率，配置参数内容的ID（6-3.5KW 15-7KW 174-24KW 172-30KW 171-37.5KW 175 40KW 173-45KW 170-60KW 169-60.5KW 180-90KW 43-120KW 181-150KW 182-240KW）',
  `elPi_PowerInterface` int(11) NOT NULL DEFAULT '0' COMMENT '电桩接口方式，配置参数内容的ID（国标、欧标、美标）',
  `elPi_Maker` int(11) NOT NULL DEFAULT '0' COMMENT '电桩制造商，配置参数内容的ID （万马新能源，南京循道，北京三优，上海普天）',
  `elPi_OutputVoltage` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '电桩额定输出电压',
  `elPi_InputVoltage` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '电桩额定输入电压',
  `elPi_OutputCurrent` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '电桩额定输出电流',
  `elPi_InputCurrent` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '电桩额定输入电流',
  `elPi_UserType` int(11) NOT NULL DEFAULT '0' COMMENT '电桩所属用户类型',
  `elPi_UserId` varchar(50) NOT NULL DEFAULT '0' COMMENT '电桩所属用户ID',
  `elPi_Createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `elPi_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `elPi_Remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `elPi_Carid` text NOT NULL COMMENT '电桩适用车型，根据电动车品牌类型详情表关联，id用逗号隔开',
  `elPi_Binding` int(11) NOT NULL DEFAULT '0' COMMENT '电桩是否绑定电站（0-未绑定 1-已绑定）',
  `elPi_IsAppoint` int(11) NOT NULL DEFAULT '0' COMMENT '电桩是否支持预约（0不支持，1支持）',
  `elPi_PayStyle` int(11) NOT NULL DEFAULT '0' COMMENT '电桩充电支付方式，配置参数内容的ID （刷卡方式，手机方式，人工方式）',
  `elPi_MaxElectricity` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '最大电流',
  `elPi_PowerUserName` varchar(255) NOT NULL DEFAULT '' COMMENT '电桩用途名称',
  `elPi_ChargingModeName` varchar(255) NOT NULL DEFAULT '' COMMENT '电桩充电方式名称',
  `elPi_PowerInterfaceName` varchar(255) NOT NULL DEFAULT '' COMMENT '电桩接口方式名称',
  `elPi_RelevancePowerStation` int(11) NOT NULL DEFAULT '0' COMMENT '所属电站',
  `elPi_Tell` varchar(50) NOT NULL DEFAULT '' COMMENT '联系电话',
  `elPi_RateInformationId` int(11) NOT NULL DEFAULT '0' COMMENT '费率表id',
  `comm_status` int(11) NOT NULL DEFAULT '0' COMMENT '0断开；1连接中',
  `ep_num` int(11) NOT NULL DEFAULT '0' COMMENT '电桩排序号，在电站中的排序',
  `elPi_UserName` varchar(50) NOT NULL DEFAULT '' COMMENT '电桩所属用户名',
  `elPi_OnlineTime` varchar(100) NOT NULL DEFAULT '' COMMENT '电桩开放时间',
  `elPi_OwnProvinceCode` varchar(10) NOT NULL DEFAULT '' COMMENT '电桩所属省份代码',
  `elPi_OwnCityCode` varchar(10) NOT NULL DEFAULT '' COMMENT '电桩所属城市代码',
  `elPi_OwnCountyCode` varchar(10) NOT NULL DEFAULT '' COMMENT '电桩所属区县代码',
  `elPi_offlineTime` varchar(30) NOT NULL DEFAULT '' COMMENT '个体商家电桩下线时间',
  `elPi_GateId` int(11) NOT NULL DEFAULT '0' COMMENT 'GATE服务器ID',
  `delete_flag` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除标识 默认0（未删除），1 已删除',
  `elPi_OwnerCompany` smallint(2) NOT NULL DEFAULT '0' COMMENT '运营平台:0其他，1爱充网，2国网，3特斯拉',
  `sim_mac` varchar(20) NOT NULL DEFAULT '' COMMENT 'SIM卡卡号',
  `sim_phone_num` varchar(13) NOT NULL DEFAULT '' COMMENT 'SIM卡电话号码',
  `have_led_flash` smallint(4) NOT NULL DEFAULT '0' COMMENT 'LED灯(0:不支持;1:支持)',
  `have_gps` smallint(4) NOT NULL DEFAULT '0' COMMENT '是否有gps模块,0:没有，1:有',
  `pk_concentratorID` int(11) NOT NULL DEFAULT '0' COMMENT '集中器ID',
  `concentrator_num` int(11) NOT NULL DEFAULT '1' COMMENT '集中器内序号',
  `have_connect_line` smallint(4) NOT NULL DEFAULT '0' COMMENT '是否有连接线 0没有,1有',
  `company_number` int(11) NOT NULL DEFAULT '0' COMMENT '公司标识',
  `elpi_TypeSpanId` int(11) NOT NULL DEFAULT '0' COMMENT '产品ID',
  `owner_ship` varchar(40) NOT NULL DEFAULT '' COMMENT '电桩所有权（公司名称）',
  `elPi_Parking_Fee` varchar(30) NOT NULL DEFAULT '' COMMENT '停车费',
  PRIMARY KEY (`pk_ElectricPile`),
  KEY `index_electricpile_code` (`elPi_ElectricPileCode`) USING BTREE,
  KEY `index_electricpile_uid` (`elPi_UserId`) USING BTREE,
  KEY `index_electricpile_SID` (`elPi_RelevancePowerStation`) USING BTREE,
  KEY `idx_elpi_city_id` (`elPi_OwnCityCode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20891 DEFAULT CHARSET=utf8 COMMENT='电桩表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricpile_config`
--

DROP TABLE IF EXISTS `tbl_electricpile_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricpile_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `arg_code` varchar(10) NOT NULL COMMENT '参数编号',
  `arg_name` varchar(50) NOT NULL COMMENT '参数名称',
  `arg_type` smallint(4) NOT NULL DEFAULT '0' COMMENT '参数类型，桩重连时:0不下发，1下发',
  `is_valid` smallint(4) NOT NULL DEFAULT '1' COMMENT '有效标志1:有效;0:无效',
  PRIMARY KEY (`id`),
  KEY `index_arg_code` (`arg_code`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='电桩工作参数基础信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricpile_copy`
--

DROP TABLE IF EXISTS `tbl_electricpile_copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricpile_copy` (
  `pk_ElectricPile` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `elPi_ElectricPileName` varchar(50) NOT NULL DEFAULT '' COMMENT '电桩名称',
  `elPi_ElectricPileCode` varchar(50) NOT NULL DEFAULT '' COMMENT '桩体编号',
  `elPi_ElectricPileAddress` varchar(200) NOT NULL DEFAULT '' COMMENT '地址',
  `elPi_Longitude` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '电桩地址经度',
  `elPi_Latitude` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '电桩地址维度',
  `elPi_PowerNumber` int(11) NOT NULL DEFAULT '1' COMMENT '电桩枪口数量',
  `elPi_State` int(11) NOT NULL DEFAULT '0' COMMENT '电桩状态（0-草稿 3-已驳回 5-提交审核  10-专属 12-分享申请中 15-分享）',
  `elPi_RejectionReason` varchar(500) NOT NULL DEFAULT '' COMMENT '审核驳回原因',
  `elPi_Type` int(11) NOT NULL DEFAULT '0' COMMENT '电桩类型，配置参数内容的ID （1-落地式，2-壁挂式，11-一体式，12-分体式）',
  `elPi_PowerUser` int(11) NOT NULL DEFAULT '0' COMMENT '电桩用途，配置参数内容的ID（电动车，电动自行车，多功能（电动车、电动自行车、手机USB））',
  `elPi_ChargingMode` int(11) NOT NULL DEFAULT '0' COMMENT '电桩充电方式，配置参数内容的ID （5-直流充电桩，14-交流充电桩）',
  `elPi_PowerSize` int(11) NOT NULL DEFAULT '0' COMMENT '电桩额定功率，配置参数内容的ID（6-3.5KW 15-7KW 174-24KW 172-30KW 171-37.5KW 175 40KW 173-45KW 170-60KW 169-60.5KW 180-90KW 43-120KW 181-150KW 182-240KW）',
  `elPi_PowerInterface` int(11) NOT NULL DEFAULT '0' COMMENT '电桩接口方式，配置参数内容的ID（国标、欧标、美标）',
  `elPi_Maker` int(11) NOT NULL DEFAULT '0' COMMENT '电桩制造商，配置参数内容的ID （万马新能源，南京循道，北京三优，上海普天）',
  `elPi_OutputVoltage` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '电桩额定输出电压',
  `elPi_InputVoltage` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '电桩额定输入电压',
  `elPi_OutputCurrent` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '电桩额定输出电流',
  `elPi_InputCurrent` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '电桩额定输入电流',
  `elPi_UserType` int(11) NOT NULL DEFAULT '0' COMMENT '电桩所属用户类型',
  `elPi_UserId` varchar(50) NOT NULL DEFAULT '0' COMMENT '电桩所属用户ID',
  `elPi_Createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `elPi_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `elPi_Remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `elPi_Carid` text NOT NULL COMMENT '电桩适用车型，根据电动车品牌类型详情表关联，id用逗号隔开',
  `elPi_Binding` int(11) NOT NULL DEFAULT '0' COMMENT '电桩是否绑定电站（0-未绑定 1-已绑定）',
  `elPi_IsAppoint` int(11) NOT NULL DEFAULT '0' COMMENT '电桩是否支持预约（0不支持，1支持）',
  `elPi_PayStyle` int(11) NOT NULL DEFAULT '0' COMMENT '电桩充电支付方式，配置参数内容的ID （刷卡方式，手机方式，人工方式）',
  `elPi_MaxElectricity` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '最大电流',
  `elPi_PowerUserName` varchar(255) NOT NULL DEFAULT '' COMMENT '电桩用途名称',
  `elPi_ChargingModeName` varchar(255) NOT NULL DEFAULT '' COMMENT '电桩充电方式名称',
  `elPi_PowerInterfaceName` varchar(255) NOT NULL DEFAULT '' COMMENT '电桩接口方式名称',
  `elPi_RelevancePowerStation` int(11) NOT NULL DEFAULT '0' COMMENT '所属电站',
  `elPi_Tell` varchar(50) NOT NULL DEFAULT '' COMMENT '联系电话',
  `elPi_RateInformationId` int(11) NOT NULL DEFAULT '0' COMMENT '费率表id',
  `comm_status` int(11) NOT NULL DEFAULT '0' COMMENT '0断开；1连接中',
  `ep_num` int(11) NOT NULL DEFAULT '0' COMMENT '电桩排序号，在电站中的排序',
  `elPi_UserName` varchar(50) NOT NULL DEFAULT '' COMMENT '电桩所属用户名',
  `elPi_OnlineTime` varchar(100) NOT NULL DEFAULT '' COMMENT '电桩开放时间',
  `elPi_OwnProvinceCode` varchar(10) NOT NULL DEFAULT '' COMMENT '电桩所属省份代码',
  `elPi_OwnCityCode` varchar(10) NOT NULL DEFAULT '' COMMENT '电桩所属城市代码',
  `elPi_OwnCountyCode` varchar(10) NOT NULL DEFAULT '' COMMENT '电桩所属区县代码',
  `elPi_offlineTime` varchar(30) NOT NULL DEFAULT '' COMMENT '个体商家电桩下线时间',
  `elPi_GateId` int(11) NOT NULL DEFAULT '0' COMMENT 'GATE服务器ID',
  `delete_flag` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除标识 默认0（未删除），1 已删除',
  `elPi_OwnerCompany` smallint(2) NOT NULL DEFAULT '0' COMMENT '运营平台:0其他，1爱充网，2国网，3特斯拉',
  `sim_mac` varchar(20) NOT NULL DEFAULT '' COMMENT 'SIM卡卡号',
  `sim_phone_num` varchar(13) NOT NULL DEFAULT '' COMMENT 'SIM卡电话号码',
  `have_led_flash` smallint(4) NOT NULL DEFAULT '0' COMMENT 'LED灯(0:不支持;1:支持)',
  `have_gps` smallint(4) NOT NULL DEFAULT '0' COMMENT '是否有gps模块,0:没有，1:有',
  `pk_concentratorID` int(11) NOT NULL DEFAULT '0' COMMENT '集中器ID',
  `concentrator_num` int(11) NOT NULL DEFAULT '1' COMMENT '集中器内序号',
  `have_connect_line` smallint(4) NOT NULL DEFAULT '0' COMMENT '是否有连接线 0没有,1有',
  `company_number` int(11) NOT NULL DEFAULT '0' COMMENT '公司标识',
  `elpi_TypeSpanId` int(11) NOT NULL DEFAULT '0' COMMENT '产品ID',
  `owner_ship` varchar(40) NOT NULL DEFAULT '' COMMENT '电桩所有权（公司名称）',
  `elPi_Parking_Fee` varchar(30) NOT NULL DEFAULT '' COMMENT '停车费',
  PRIMARY KEY (`pk_ElectricPile`),
  KEY `index_electricpile_code` (`elPi_ElectricPileCode`) USING BTREE,
  KEY `index_electricpile_uid` (`elPi_UserId`) USING BTREE,
  KEY `index_electricpile_SID` (`elPi_RelevancePowerStation`) USING BTREE,
  KEY `idx_elpi_city_id` (`elPi_OwnCityCode`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=19971 DEFAULT CHARSET=utf8 COMMENT='电桩表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricpile_meternum`
--

DROP TABLE IF EXISTS `tbl_electricpile_meternum`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricpile_meternum` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `read_date` varchar(10) NOT NULL DEFAULT '' COMMENT '读表日期',
  `ep_code` varchar(50) NOT NULL DEFAULT '' COMMENT '桩体编号',
  `total_meter` varchar(20) NOT NULL DEFAULT '' COMMENT '桩体总电度',
  `gun1_meter` varchar(20) DEFAULT NULL COMMENT '枪口1总电度',
  `gun2_meter` varchar(20) DEFAULT NULL COMMENT '枪口2总电度',
  `gun3_meter` varchar(20) DEFAULT NULL COMMENT '枪口3总电度',
  `gun4_meter` varchar(20) DEFAULT NULL COMMENT '枪口4总电度',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_read_code` (`read_date`,`ep_code`)
) ENGINE=InnoDB AUTO_INCREMENT=56831 DEFAULT CHARSET=utf8 COMMENT='电桩有功总电度信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricpile_workarg`
--

DROP TABLE IF EXISTS `tbl_electricpile_workarg`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricpile_workarg` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ep_code` varchar(50) NOT NULL DEFAULT '' COMMENT '桩体编号',
  `arg_id` smallint(4) NOT NULL COMMENT '参数ID',
  `arg_value` varchar(20) NOT NULL COMMENT '参数值',
  `issued_status` smallint(4) NOT NULL COMMENT '下发给电桩状态（0：未下发；1：已下发数据但未收到响应 ；2：下发成功；3：下发失败）',
  `delete_flag` smallint(4) NOT NULL DEFAULT '0' COMMENT '删除标志',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `creator` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `modifier` varchar(32) NOT NULL DEFAULT '' COMMENT '修改人',
  PRIMARY KEY (`id`),
  KEY `index_ep_code` (`ep_code`,`arg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=utf8 COMMENT='电桩工作参数配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricpilecomment`
--

DROP TABLE IF EXISTS `tbl_electricpilecomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricpilecomment` (
  `pk_EpcComment` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `epc_EpId` int(11) NOT NULL DEFAULT '0' COMMENT '充电桩id',
  `epc_UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户Id(tbl_UserInfo表中获取)',
  `epc_UserName` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `epc_CommentPic` varchar(200) NOT NULL DEFAULT '' COMMENT '评论图片',
  `epc_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `epc_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `epc_Utatus` int(11) NOT NULL DEFAULT '0' COMMENT '0：显示 -1 已删除',
  `epc_Content` varchar(200) NOT NULL DEFAULT '' COMMENT '评论内容',
  `reply_count` int(8) NOT NULL DEFAULT '0' COMMENT '回复数量',
  `praise_count` int(8) NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `up_commentId` int(11) NOT NULL DEFAULT '0' COMMENT '上级评论ID',
  `epc_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '桩评论类型:1评价；2留言',
  PRIMARY KEY (`pk_EpcComment`),
  KEY `FK_Reference_5` (`epc_EpId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=627 DEFAULT CHARSET=utf8 COMMENT='电桩评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricpilecomment_praise`
--

DROP TABLE IF EXISTS `tbl_electricpilecomment_praise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricpilecomment_praise` (
  `pk_praiseId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ep_commentId` int(11) NOT NULL COMMENT '评论ID',
  `praise_UserId` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`pk_praiseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电桩点赞表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricpilehead`
--

DROP TABLE IF EXISTS `tbl_electricpilehead`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricpilehead` (
  `pk_ElectricpileHead` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pk_ElectricPile` int(11) NOT NULL DEFAULT '0' COMMENT '桩体id',
  `ePHe_ElectricpileHeadName` varchar(20) NOT NULL COMMENT '枪头名称',
  `ePHe_ElectricpileHeadState` int(11) NOT NULL DEFAULT '0' COMMENT '电桩枪头状态（0空闲中，3预约中，6充电中，9停用中,17等待充电）',
  `ePHe_ElectricpileHeadId` int(11) NOT NULL DEFAULT '0' COMMENT '枪头编号',
  `eph_num` varchar(10) NOT NULL DEFAULT '' COMMENT '枪口对应车位号',
  `delete_flag` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `have_gun_lid` tinyint(4) NOT NULL DEFAULT '0' COMMENT '枪盖(0:不支持;1:支持)',
  `have_gun_site_signal` tinyint(4) NOT NULL DEFAULT '0' COMMENT '枪座(0:不支持;1:支持）',
  `have_radar` tinyint(4) NOT NULL DEFAULT '0' COMMENT '雷达(0:不支持;1:支持)',
  `hava_car_place_lock` tinyint(4) NOT NULL DEFAULT '0' COMMENT '车位锁(地锁)(0:不支持;1:支持)',
  `have_bms_comm` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'BMS通讯设备支持(0:不支持;1:支持)',
  `total_charge_dl` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '累计充电度量',
  `total_charge_time` int(11) NOT NULL DEFAULT '0' COMMENT '累计充电时间，单位分钟',
  `total_charge_amt` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '累计充电费用',
  `ePHe_qr_codes` varchar(10) NOT NULL DEFAULT '' COMMENT '二维码编码',
  `ePHe_qrdate` bigint(20) DEFAULT '0' COMMENT '二维码过期时间',
  PRIMARY KEY (`pk_ElectricpileHead`),
  KEY `index_ElectricpileHead` (`pk_ElectricPile`) USING BTREE,
  CONSTRAINT `tbl_electricpilehead_ibfk_1` FOREIGN KEY (`pk_ElectricPile`) REFERENCES `tbl_electricpile` (`pk_ElectricPile`)
) ENGINE=InnoDB AUTO_INCREMENT=9697 DEFAULT CHARSET=utf8 COMMENT='枪头表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricpilehead_tmp`
--

DROP TABLE IF EXISTS `tbl_electricpilehead_tmp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricpilehead_tmp` (
  `pk_ElectricpileHead` int(11) NOT NULL DEFAULT '0' COMMENT '主键',
  `pk_ElectricPile` int(11) NOT NULL DEFAULT '0' COMMENT '桩体id',
  `ePHe_ElectricpileHeadName` varchar(20) NOT NULL COMMENT '枪头名称',
  `ePHe_ElectricpileHeadState` int(11) NOT NULL DEFAULT '0' COMMENT '电桩枪头状态（0空闲中，3预约中，6充电中，9停用中）',
  `ePHe_ElectricpileHeadId` int(11) NOT NULL DEFAULT '0' COMMENT '枪头编号',
  `eph_num` varchar(10) NOT NULL DEFAULT '' COMMENT '枪口对应车位号',
  `delete_flag` smallint(6) NOT NULL DEFAULT '0' COMMENT '删除标识',
  `have_gun_lid` tinyint(4) NOT NULL DEFAULT '0' COMMENT '枪盖(0:不支持;1:支持)',
  `have_gun_site_signal` tinyint(4) NOT NULL DEFAULT '0' COMMENT '枪座(0:不支持;1:支持）',
  `have_radar` tinyint(4) NOT NULL DEFAULT '0' COMMENT '雷达(0:不支持;1:支持)',
  `hava_car_place_lock` tinyint(4) NOT NULL DEFAULT '0' COMMENT '车位锁(地锁)(0:不支持;1:支持)',
  `have_bms_comm` tinyint(4) NOT NULL DEFAULT '0' COMMENT 'BMS通讯设备支持(0:不支持;1:支持)',
  `total_charge_dl` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '累计充电度量',
  `total_charge_time` int(11) NOT NULL DEFAULT '0' COMMENT '累计充电时间，单位分钟',
  `total_charge_amt` decimal(11,2) NOT NULL DEFAULT '0.00' COMMENT '累计充电费用',
  `ePHe_qr_codes` varchar(10) NOT NULL DEFAULT '' COMMENT '二维码编码',
  `ePHe_qrdate` bigint(20) DEFAULT '0' COMMENT '二维码过期时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricpilestar`
--

DROP TABLE IF EXISTS `tbl_electricpilestar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricpilestar` (
  `pk_EpsStar` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `eps_EpId` int(11) NOT NULL DEFAULT '0' COMMENT '充电桩id',
  `eps_UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户Id(tbl_UserInfo表中获取)',
  `eps_UserName` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `eps_CommentStar` double(11,0) NOT NULL DEFAULT '0' COMMENT '星级',
  `eps_Createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `eps_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `eps_Utatus` int(11) NOT NULL DEFAULT '0' COMMENT '0：显示 -1 已删除',
  PRIMARY KEY (`pk_EpsStar`),
  KEY `FK_Reference_5` (`eps_EpId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=576 DEFAULT CHARSET=utf8 COMMENT='电桩星级评分';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricuser`
--

DROP TABLE IF EXISTS `tbl_electricuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activateTime` int(11) DEFAULT NULL,
  `createSource` int(11) DEFAULT NULL,
  `createTime` int(11) DEFAULT NULL,
  `elPiElectricPileCode` varchar(255) DEFAULT NULL,
  `elPiElectricPileName` varchar(255) DEFAULT NULL,
  `gateId` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `rateid` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100020 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electricuser_copy`
--

DROP TABLE IF EXISTS `tbl_electricuser_copy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electricuser_copy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activateTime` int(11) DEFAULT NULL,
  `createSource` int(11) DEFAULT NULL,
  `createTime` int(11) DEFAULT NULL,
  `elPiElectricPileCode` varchar(255) DEFAULT NULL,
  `elPiElectricPileName` varchar(255) DEFAULT NULL,
  `gateId` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT '0',
  `rateid` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100020 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_electripile_offline_charge_info`
--

DROP TABLE IF EXISTS `tbl_electripile_offline_charge_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_electripile_offline_charge_info` (
  `pk_eoci_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `eoci_electricpilecode` varchar(50) NOT NULL DEFAULT '' COMMENT '桩体编号',
  `eoci_companynumber` int(4) NOT NULL DEFAULT '0' COMMENT '公司标识',
  `eoci_num` int(4) NOT NULL DEFAULT '0' COMMENT '状态默认0: 电桩当前保存最大离线可充数值',
  `eoci_createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `eoci_updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_eoci_id`),
  KEY `idx_eoci_elpcode` (`eoci_electricpilecode`) USING BTREE,
  KEY `idx_eoci_comnumber` (`eoci_companynumber`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8 COMMENT='电桩离线充电数值表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_ep_access`
--

DROP TABLE IF EXISTS `tbl_ep_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_ep_access` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `service_qq` varchar(20) NOT NULL DEFAULT '' COMMENT '客服QQ',
  `user_name` varchar(20) NOT NULL DEFAULT '' COMMENT '联系人姓名',
  `user_phone` varchar(20) NOT NULL DEFAULT '' COMMENT '联系人电话',
  `ep_addr` varchar(200) NOT NULL DEFAULT '' COMMENT '充电点地址',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '处理状态（1提交申请 2客户代表信息确认 3信息审核 4入网洽谈 5加入爱充）',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间（自动更新）',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `carcompany_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '汽车厂商ID',
  `ep_reason` varchar(150) NOT NULL DEFAULT '' COMMENT '电桩申请驳回原因',
  `carinfo_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '车型ID',
  `property_nature` smallint(6) NOT NULL DEFAULT '0' COMMENT '物业性质（1零售 2酒店 3度假村 4娱乐场所 5办公写字楼 6住宅 7汽车服务 8综合体 9市政 10收费停车场 11餐厅）',
  `standard_parking` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否为标准车 位(1是，2否)',
  `parking_place` smallint(6) NOT NULL DEFAULT '0' COMMENT '车位位置(1、室内  2、室外)',
  `open_share` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否对外开放(1、是2 、接受开放3、否)',
  `parking_hard` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否车位硬化(1、 是2、否)',
  `parking_owner` varchar(60) NOT NULL DEFAULT '' COMMENT '车位所属权（“1” 表示自有；2：表示租赁，3：其他）',
  `capacitance` varchar(20) NOT NULL DEFAULT '' COMMENT '电容量（单位KVA）',
  `power_position` varchar(60) NOT NULL DEFAULT '' COMMENT '电源位置(1、地 面2、地下3:其他)',
  `distance` varchar(60) NOT NULL DEFAULT '' COMMENT '电源到车位距离(1、小于 等于30米；2、大于30米3： 其他)',
  `parking_owner_text` varchar(20) NOT NULL DEFAULT '' COMMENT '车位所属手填内容，补充parking_owner字段',
  `power_position_text` varchar(50) NOT NULL DEFAULT '' COMMENT '电源位置手填内容,补充distance字段',
  `distance_text` varchar(40) NOT NULL DEFAULT '' COMMENT '电源到车位距离手填内容，补充distance字段',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='购桩申请表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_ep_timing_charge`
--

DROP TABLE IF EXISTS `tbl_ep_timing_charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_ep_timing_charge` (
  `PK_TIMING_CHARGE_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ELPI_ELECTRICPILECODE` varchar(50) NOT NULL COMMENT '桩体编号',
  `TIMING_CHARGE` time NOT NULL COMMENT '定时充电时间（HH:mm:ss）',
  `TIMING_CHARGE_STATUS` int(4) NOT NULL DEFAULT '0' COMMENT '是否开启定时功能（0：开；1：关；）',
  `ISSUED_STATUS` int(4) NOT NULL DEFAULT '0' COMMENT '下发给电桩状态（0：未下发定时；1：已下发数据但未收到响应 ；2：下发定时成功；3：下发定时失败）',
  `REMARK` varchar(200) DEFAULT NULL COMMENT '备注',
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `UPDATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  KEY `IDX_TIMING_CHARGE_ID` (`PK_TIMING_CHARGE_ID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='电桩定时充电';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_equipmentrepair`
--

DROP TABLE IF EXISTS `tbl_equipmentrepair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_equipmentrepair` (
  `pk_EquipmentRepair` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `eqRe_WarrantyTypeId` int(11) NOT NULL COMMENT '保修类型',
  `eqRe_Content` varchar(500) NOT NULL COMMENT '保修内容',
  `eqRe_UserId` int(11) NOT NULL COMMENT '用户ID',
  `eqRe_WarrantyStatus` int(11) NOT NULL COMMENT '1：未处理 2 处理中 3：已处理 ',
  `eqRe_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `eqRe_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `eqRe_Status` int(11) NOT NULL COMMENT '0：显示 -1已删除',
  `epid` int(11) NOT NULL DEFAULT '0' COMMENT '电桩、电站id',
  `device_type` int(11) NOT NULL DEFAULT '0' COMMENT '设备类型 1电桩2电站',
  `deal_result` varchar(500) NOT NULL DEFAULT '' COMMENT '处理结果',
  PRIMARY KEY (`pk_EquipmentRepair`)
) ENGINE=InnoDB AUTO_INCREMENT=578 DEFAULT CHARSET=utf8 COMMENT='设备报修';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_equipmentversion`
--

DROP TABLE IF EXISTS `tbl_equipmentversion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_equipmentversion` (
  `pk_EquipmentVersion` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ev_ProductID` varchar(30) NOT NULL DEFAULT '' COMMENT '产品ID：电桩/集中器ID',
  `ev_ProductType` int(11) NOT NULL DEFAULT '0' COMMENT '产品类型：1：电桩 2 集中器',
  `ev_FirmwareNumber` varchar(30) DEFAULT '' COMMENT '固件版本',
  `ev_FirmwareVersion` varchar(30) NOT NULL DEFAULT '' COMMENT '固件版本号',
  `ev_HardwareNumber` varchar(30) NOT NULL DEFAULT '' COMMENT '硬件编号',
  `ev_HardwareVersion` varchar(30) NOT NULL DEFAULT '' COMMENT '硬件版本号',
  `ev_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ev_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `ev_FileMd5` varchar(32) DEFAULT NULL COMMENT '升级文件的md5值',
  `ev_BomListId` int(11) DEFAULT NULL COMMENT '关联 tbl_bomlist  id字段',
  PRIMARY KEY (`pk_EquipmentVersion`)
) ENGINE=InnoDB AUTO_INCREMENT=16072 DEFAULT CHARSET=utf8 COMMENT='设备版本信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_export_sql`
--

DROP TABLE IF EXISTS `tbl_export_sql`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_export_sql` (
  `id` int(11) NOT NULL COMMENT '主键',
  `rp_type` smallint(4) DEFAULT NULL COMMENT 'excel类型 1:订单统计',
  `rp_name` varchar(50) DEFAULT NULL COMMENT '报表名称',
  `rp_sql` text COMMENT '导出sql',
  `is_export` smallint(4) DEFAULT NULL COMMENT '是否导出 1：导出 0：不导出',
  `rp_dsp` smallint(4) DEFAULT NULL COMMENT 'sheet排序',
  `rp_table` varchar(50) DEFAULT NULL COMMENT '导出报表名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_faultcode`
--

DROP TABLE IF EXISTS `tbl_faultcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_faultcode` (
  `pk_FaultCode` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fc_FaultID` varchar(30) NOT NULL DEFAULT '' COMMENT '故障ID',
  `fc_FaultCause` varchar(30) NOT NULL DEFAULT '' COMMENT '故障原因',
  `fc_CreateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fc_UpdateDate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_FaultCode`),
  KEY `idx_fc_faultid` (`fc_FaultID`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='充电故障代码表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_favorite`
--

DROP TABLE IF EXISTS `tbl_favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_favorite` (
  `pk_Favorite` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `favo_ProductId` int(11) NOT NULL COMMENT '产品Id',
  `favo_UserId` int(11) NOT NULL COMMENT '用户Id',
  `favo_CreateTime` datetime NOT NULL COMMENT '收藏时间',
  `favo_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `favo_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_Favorite`),
  KEY `FK_Reference_6` (`favo_ProductId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8 COMMENT='状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_feedback`
--

DROP TABLE IF EXISTS `tbl_feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_feedback` (
  `pk_FeedBack` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `feBa_Content` varchar(500) NOT NULL DEFAULT '' COMMENT '反馈内容',
  `feBa_UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID tbl_UserInfo中获取',
  `feBa_Createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `feBa_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `feBa_Status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否处理，0：插入，1：接收正在处理，2：处理完成',
  `feBa_Reason` varchar(500) NOT NULL DEFAULT '' COMMENT '回复内容',
  `feBa_update_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '处理人',
  PRIMARY KEY (`pk_FeedBack`)
) ENGINE=InnoDB AUTO_INCREMENT=530 DEFAULT CHARSET=utf8 COMMENT='意见反馈';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_filterwords`
--

DROP TABLE IF EXISTS `tbl_filterwords`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_filterwords` (
  `pk_FilterWords` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fiWo_WordsName` varchar(20) NOT NULL COMMENT '过滤字',
  `fiWo_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `fiWo_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `fiWo_Status` int(11) NOT NULL COMMENT '0：显示 -1：已删除',
  PRIMARY KEY (`pk_FilterWords`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='过滤字';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_footprint`
--

DROP TABLE IF EXISTS `tbl_footprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_footprint` (
  `pk_Footprint` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `foPr_Content` varchar(255) NOT NULL COMMENT '足迹内容',
  `foPr_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收益时间',
  `foPr_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `foPr_UserId` int(11) NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`pk_Footprint`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='足迹表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_gateservice`
--

DROP TABLE IF EXISTS `tbl_gateservice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_gateservice` (
  `pk_GateId` int(11) NOT NULL AUTO_INCREMENT,
  `gtSe_GateName` varchar(50) NOT NULL DEFAULT '' COMMENT 'Gate服务器名称',
  `gtSe_GateIp` varchar(50) NOT NULL DEFAULT '' COMMENT 'Gate服务器Ip',
  `gtSe_GatePort` int(6) NOT NULL DEFAULT '80' COMMENT 'Gate服务器端口',
  `gtSe_CreateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gtSe_UpdateTime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `gtSe_GateState` int(1) NOT NULL DEFAULT '0' COMMENT 'GATE服务器状态 1-正常 2-移除',
  `gtSe_FailTimes` int(2) NOT NULL DEFAULT '0' COMMENT 'GATE服务器重连失败次数',
  `gtSe_create_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '创建人',
  `gtSe_UsrGatePort` int(6) unsigned zerofill NOT NULL DEFAULT '000000' COMMENT 'dUsrGate端口',
  PRIMARY KEY (`pk_GateId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='Gate服务器表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_group`
--

DROP TABLE IF EXISTS `tbl_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_group` (
  `pk_Group` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grou_GroupName` varchar(200) NOT NULL COMMENT '分组名称',
  `grou_GroupReamrk` varchar(200) NOT NULL COMMENT '分组描述',
  `grou_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `grou_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_Group`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='分组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_grouppermission`
--

DROP TABLE IF EXISTS `tbl_grouppermission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_grouppermission` (
  `pk_GroupPermission` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `grPe_PermissionID` int(11) NOT NULL COMMENT '权限ID(tbl_Permission表中获取)',
  `grPe_GroupID` int(11) NOT NULL COMMENT '所属组ID(tbl_Group 表中获取)',
  `grPe_Remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `grPe_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `grPe_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_GroupPermission`),
  KEY `FK_Reference_23` (`grPe_PermissionID`) USING BTREE,
  KEY `FK_Reference_24` (`grPe_GroupID`) USING BTREE,
  CONSTRAINT `tbl_grouppermission_ibfk_1` FOREIGN KEY (`grPe_PermissionID`) REFERENCES `tbl_permission` (`pk_Permission`),
  CONSTRAINT `tbl_grouppermission_ibfk_2` FOREIGN KEY (`grPe_GroupID`) REFERENCES `tbl_group` (`pk_Group`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分组权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_homepage`
--

DROP TABLE IF EXISTS `tbl_homepage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_homepage` (
  `pk_Homepage` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `hoPa_HomePageName` varchar(200) NOT NULL COMMENT '首页广告名称',
  `hoPa_Image` varchar(200) NOT NULL COMMENT '首页图片',
  `hoPa_Sequence` int(11) NOT NULL COMMENT '轮播顺序',
  `hoPa_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `hoPa_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `hoPa_Status` int(11) NOT NULL COMMENT '0：显示 -1 已删除',
  `hoPa_Url` varchar(200) NOT NULL DEFAULT '' COMMENT '链接',
  PRIMARY KEY (`pk_Homepage`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='首页广告';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_inside_account`
--

DROP TABLE IF EXISTS `tbl_inside_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_inside_account` (
  `pk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `user_account` varchar(32) NOT NULL COMMENT '用户账号',
  `user_type` smallint(4) NOT NULL COMMENT '1:app用户 2：大客户',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='内部人员账号配置表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_installdetail`
--

DROP TABLE IF EXISTS `tbl_installdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_installdetail` (
  `pk_InstallDetail` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `inDe_ParentId` int(11) NOT NULL COMMENT '父订单Id',
  `inDe_ProductId` int(11) NOT NULL COMMENT '产品Id',
  `inDe_ProductName` varchar(50) NOT NULL COMMENT '产品名称',
  `inDe_Price` decimal(6,2) NOT NULL COMMENT '单价',
  `inDe_Quantity` int(11) NOT NULL COMMENT '数量',
  `inDe_TotalAmount` decimal(8,2) DEFAULT NULL COMMENT '总价',
  `inDe_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `inDe_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `inDe_ProductCode` varchar(50) NOT NULL,
  `inDe_InstallationAddress` varchar(100) NOT NULL DEFAULT '' COMMENT '安装地址',
  `inDe_InstallationPerson` varchar(20) NOT NULL DEFAULT '' COMMENT '联系人',
  `inDe_LnstallationTel` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
  PRIMARY KEY (`pk_InstallDetail`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_invoice`
--

DROP TABLE IF EXISTS `tbl_invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_invoice` (
  `pk_Invoice` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `iv_TaxpayerNumber` varchar(20) NOT NULL DEFAULT '' COMMENT '纳税人识别号',
  `iv_CompanyName` varchar(50) NOT NULL DEFAULT '' COMMENT '公司抬头',
  `iv_CompanyAddress` varchar(100) NOT NULL DEFAULT '' COMMENT '公司地址',
  `iv_PhoneNumber` varchar(20) NOT NULL DEFAULT '' COMMENT '电话',
  `iv_BankName` varchar(50) NOT NULL DEFAULT '' COMMENT '开户银行名称',
  `iv_BankAccount` varchar(50) NOT NULL DEFAULT '' COMMENT '开户银行账号',
  `iv_InvoiceAmount` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '开票金额',
  `iv_InvoiceNumber` varchar(100) NOT NULL DEFAULT '' COMMENT '发票号码',
  `iv_InvoiceContent` varchar(80) NOT NULL DEFAULT '' COMMENT '发票内容',
  `iv_TrackNumber` varchar(20) NOT NULL DEFAULT '' COMMENT '快递单号',
  `iv_FreightAmount` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '运费金额',
  `iv_Recipients` varchar(20) NOT NULL DEFAULT '' COMMENT '收件人姓名',
  `iv_ConsigneeAddress` varchar(50) NOT NULL DEFAULT '' COMMENT '收件人地址',
  `iv_RecipientsNumber` varchar(20) NOT NULL DEFAULT '' COMMENT '收件人手机号码',
  `iv_InvoiceType` smallint(4) NOT NULL DEFAULT '0' COMMENT '开票类型：0-个人开票 ，1-公司开票',
  `iv_InvoiceStatus` smallint(4) NOT NULL DEFAULT '0' COMMENT '发票申请状态：0-已申请， 1-已开票 ，2-已申请，未支付邮费',
  `iv_Pay_Freight` smallint(4) NOT NULL DEFAULT '0' COMMENT '支付类型：0-账户余额支付， 1-支付宝支付，2-微信支付，3-银联支付 ,4-货到付款',
  `iv_OwnProvinceCode` varchar(10) NOT NULL DEFAULT '' COMMENT '所属省份代码',
  `iv_OwnCityCode` varchar(10) NOT NULL DEFAULT '' COMMENT '所属城市代码',
  `iv_OwnCountyCode` varchar(10) NOT NULL DEFAULT '' COMMENT '所属区县代码',
  `iv_UserID` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `iv_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `iv_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `iv_ReceipType` smallint(4) DEFAULT '0' COMMENT '发票类型：0-普通发票，1-增值税专用发票',
  `iv_AccountType` smallint(4) NOT NULL DEFAULT '0' COMMENT '发票记录类型：0-app用户 ，1-企业用户',
  `iv_InvoiceName` varchar(50) DEFAULT NULL COMMENT '发票名称',
  PRIMARY KEY (`pk_Invoice`)
) ENGINE=InnoDB AUTO_INCREMENT=200 DEFAULT CHARSET=utf8 COMMENT='开票记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_jpush`
--

DROP TABLE IF EXISTS `tbl_jpush`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_jpush` (
  `pk_JPush` int(11) NOT NULL AUTO_INCREMENT,
  `jpush_UserInfo` int(11) NOT NULL COMMENT '用户id',
  `jpush_registrationID` varchar(50) NOT NULL COMMENT '唯一设备标示',
  `jpush_deviceType` varchar(2) NOT NULL COMMENT '设备类型 1安卓 2ios',
  `jpush_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jpush_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_JPush`)
) ENGINE=InnoDB AUTO_INCREMENT=15794 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_menu`
--

DROP TABLE IF EXISTS `tbl_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_menu` (
  `pk_Menu` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `menu_Name` varchar(50) NOT NULL COMMENT '菜单名称',
  `menu_Path` varchar(500) NOT NULL COMMENT '菜单路径',
  `menu_Remark` varchar(200) NOT NULL COMMENT '菜单描述',
  `menu_ParentId` int(11) DEFAULT NULL,
  `menu_Sort` int(11) DEFAULT NULL,
  `menu_PemissionId` int(11) DEFAULT NULL COMMENT '权限ID(tbl_Permission表中获取)',
  `menu_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `menu_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_Menu`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单(菜单存储结构为树形结构)';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_message`
--

DROP TABLE IF EXISTS `tbl_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_message` (
  `pk_Message` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mess_UserId` int(11) NOT NULL COMMENT '用户Id',
  `mess_Content` varchar(500) NOT NULL COMMENT '消息内容',
  `mess_ManagerId` int(11) NOT NULL COMMENT '商户Id',
  `mess_IsReplied` bit(1) NOT NULL COMMENT '是否已回复',
  `mess_IsFavorite` bit(1) NOT NULL COMMENT '是否星标消息',
  `mess_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mess_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_Message`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_message_info`
--

DROP TABLE IF EXISTS `tbl_message_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_message_info` (
  `pk_mei_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mei_name` varchar(70) NOT NULL DEFAULT '' COMMENT '资讯标题',
  `mei_content` varchar(250) NOT NULL DEFAULT '' COMMENT '资讯内容',
  `mei_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态默认0: 1 有效 2无效 3 删除',
  `mei_type` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态默认0: 1故障 2 新建',
  `mei_begin_time` datetime NOT NULL COMMENT '开始时间',
  `mei_end_time` datetime NOT NULL COMMENT '结束时间',
  `mei_createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mei_updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `mei_provincecode` varchar(10) NOT NULL DEFAULT '' COMMENT '电站所属省份代码',
  `mei_citycode` varchar(10) NOT NULL DEFAULT '' COMMENT '电站所属城市代码',
  `mei_countycode` varchar(10) NOT NULL DEFAULT '' COMMENT '电站所属区县代码',
  PRIMARY KEY (`pk_mei_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='首页故障和新建信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_message_point_rela`
--

DROP TABLE IF EXISTS `tbl_message_point_rela`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_message_point_rela` (
  `pk_mpr_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pk_mei_id` int(11) unsigned NOT NULL COMMENT '故障新建消息表主键',
  `pk_powerstation` int(11) NOT NULL COMMENT '充电点表主键',
  `mpr_name` varchar(200) NOT NULL DEFAULT '' COMMENT '电站名称',
  `mpr_createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mpr_updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_mpr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8 COMMENT='新建充电点关系表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_news_info`
--

DROP TABLE IF EXISTS `tbl_news_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_news_info` (
  `pk_nei_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nei_name` varchar(70) NOT NULL DEFAULT '' COMMENT '资讯标题',
  `nei_url` varchar(500) NOT NULL DEFAULT '' COMMENT 'URL地址',
  `nei_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态默认0: 1 有效 2已过期 3 删除',
  `nei_createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `nei_updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`pk_nei_id`)
) ENGINE=InnoDB AUTO_INCREMENT=248 DEFAULT CHARSET=utf8 COMMENT='首页资讯表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_nocardchargerecord`
--

DROP TABLE IF EXISTS `tbl_nocardchargerecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_nocardchargerecord` (
  `pk_NoCardChargeRecord` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nCCR_UsingMachineCode` int(11) NOT NULL COMMENT '使用终端机器编码',
  `nCCR_ElectricPileID` int(11) NOT NULL COMMENT '电桩ID',
  `nCCR_TransactionNumber` varchar(50) NOT NULL COMMENT '交易流水号',
  `nCCR_UserID` int(11) NOT NULL COMMENT '用户编号',
  `nCCR_OfflineTransactionType` int(11) NOT NULL COMMENT '离线交易类型',
  `nCCR_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '开始时间',
  `nCCR_Updatedate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间',
  `nCCR_TipUpIndication` decimal(8,2) NOT NULL COMMENT '尖起示值',
  `nCCR_TipStopIndication` decimal(8,2) NOT NULL COMMENT '尖止示值',
  `nCCR_PeakUpIndication` decimal(8,2) NOT NULL COMMENT '峰起示值',
  `nCCR_PeakStopIndication` decimal(8,2) NOT NULL COMMENT '峰止示值',
  `nCCR_PingUpIndication` decimal(8,2) NOT NULL COMMENT '平起示值',
  `nCCR_PingStoreIndication` decimal(8,2) NOT NULL COMMENT '平止示值',
  `nCCR_ValleyUpIndication` decimal(8,2) NOT NULL COMMENT '谷起示值',
  `nCCR_ValleyStoreIndication` decimal(8,2) NOT NULL COMMENT '谷止示值',
  `nCCR_MeasurementType` varchar(20) NOT NULL COMMENT '计量类型',
  `nCCR_MeasurementShows` int(11) NOT NULL COMMENT '本次计量示数',
  `nCCR_SharpPrice` decimal(8,2) NOT NULL COMMENT '尖单价',
  `nCCR_TipElectricity` decimal(8,2) NOT NULL COMMENT '尖电量',
  `nCCR_TipAmount` decimal(8,2) NOT NULL COMMENT '尖金额',
  `nCCR_PeakPrice` decimal(8,2) NOT NULL COMMENT '峰单价',
  `nCCR_PeakElectricity` decimal(8,2) NOT NULL COMMENT '峰电量',
  `nCCR_PeakAmount` decimal(8,2) NOT NULL COMMENT '峰金额',
  `nCCR_FlatPrice` decimal(8,2) NOT NULL COMMENT '平单价',
  `nCCR_FlatElectricity` decimal(8,2) NOT NULL COMMENT '平电量',
  `nCCR_FlatAmount` decimal(8,2) NOT NULL COMMENT '平金额',
  `nCCR_GrainPrice` decimal(8,2) NOT NULL COMMENT '谷单价',
  `nCCR_GrainElectricity` decimal(8,2) NOT NULL COMMENT '谷电量',
  `nCCR_GrainAmount` decimal(8,2) NOT NULL COMMENT '谷金额',
  `nCCR_SumAmount` decimal(8,2) NOT NULL COMMENT '总电量',
  `nCCR_ServiceType` int(11) NOT NULL COMMENT '业务类型',
  `nCCR_Consumption` decimal(8,2) NOT NULL COMMENT '消费数值',
  `nCCR_ConsumptionPrice` decimal(8,2) NOT NULL COMMENT '消费单价',
  `nCCR_ConsumptionAmount` decimal(8,2) NOT NULL COMMENT '消费金额',
  `nCCR_UniqueIdentifier` int(11) NOT NULL COMMENT '电动设备唯一标识',
  PRIMARY KEY (`pk_NoCardChargeRecord`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='无卡充电消费记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_nocardcharging`
--

DROP TABLE IF EXISTS `tbl_nocardcharging`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_nocardcharging` (
  `pk_NoCardCharging` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `nCCh_UsingMachineCode` int(11) NOT NULL COMMENT '使用终端机器编码',
  `nCCh_PowerStationID` int(11) NOT NULL COMMENT '电站ID',
  `nCCh_ElectricPileID` int(11) NOT NULL COMMENT '充电电桩ID',
  `nCCh_BuyMongy` decimal(8,2) NOT NULL COMMENT '购买金额',
  `nCCh_SuccessfulIdentification` varchar(200) NOT NULL COMMENT '鉴权成功标识',
  `nCCh_FailureCauses` varchar(500) DEFAULT NULL COMMENT '鉴权失败原因',
  `nCCh_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `nCCh_Updatedate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_NoCardCharging`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='无卡充电';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_order`
--

DROP TABLE IF EXISTS `tbl_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_order` (
  `pk_Order` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orde_Code` varchar(30) NOT NULL DEFAULT '0' COMMENT '编号',
  `orde_TotalAmount` decimal(10,0) NOT NULL DEFAULT '0' COMMENT '总金额',
  `orde_UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户Id',
  `orde_Status` int(11) NOT NULL COMMENT '1：待支付 2 支付成功 3 操作完成 0 删除',
  `orde_OrderType` int(11) NOT NULL COMMENT '1：购物消费',
  `orde_Remark` varchar(100) NOT NULL DEFAULT '' COMMENT '备注',
  `orde_ReceiveingName` varchar(20) NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `orde_ReceiveingAddress` varchar(100) NOT NULL DEFAULT '' COMMENT '收货地址',
  `orde_ReceiveingContact` varchar(15) NOT NULL DEFAULT '' COMMENT '联系方式',
  `orde_Createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `orde_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `orde_DeliveryWay` int(11) NOT NULL DEFAULT '0' COMMENT '1：包邮 2：不包邮',
  `orde_Express` varchar(200) NOT NULL DEFAULT '' COMMENT '快递公司',
  `orde_CommodityTotal` int(11) NOT NULL DEFAULT '0' COMMENT '订单商品总数',
  `orde_CommodityShops` varchar(200) NOT NULL DEFAULT '' COMMENT '所属商铺',
  `orde_TypeOrder` int(11) NOT NULL DEFAULT '0' COMMENT '1：支付宝 2：银联支付',
  PRIMARY KEY (`pk_Order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_order_address`
--

DROP TABLE IF EXISTS `tbl_order_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_order_address` (
  `pk_OrderAddress` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orAd_OrderDetail` int(11) NOT NULL DEFAULT '0' COMMENT '订单详情表主键',
  `orAd_ProductId` int(11) NOT NULL DEFAULT '0' COMMENT '产品Id',
  `orAd_ProductName` varchar(50) NOT NULL DEFAULT '' COMMENT '产品名称',
  `orAd_InstallAddress` int(11) NOT NULL DEFAULT '0' COMMENT '安装地址Id',
  `orAd_Quantity` int(11) NOT NULL DEFAULT '0' COMMENT '安装数量',
  `orAd_Status` int(2) NOT NULL DEFAULT '0' COMMENT '是否提交预约：0否 1是',
  `orAd_Createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `orAd_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_OrderAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='已购商品安装地址关联表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_orderdetail`
--

DROP TABLE IF EXISTS `tbl_orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_orderdetail` (
  `pk_OrderDetail` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orDe_ParentId` int(11) NOT NULL COMMENT '父订单Id',
  `orDe_ProductId` int(11) NOT NULL COMMENT '产品Id',
  `orDe_ProductName` varchar(50) NOT NULL COMMENT '产品名称',
  `orDe_Price` decimal(6,2) NOT NULL COMMENT '单价',
  `orDe_Quantity` int(11) NOT NULL COMMENT '数量',
  `orDe_TotalAmount` decimal(8,2) NOT NULL COMMENT '总价',
  `orDe_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `orDe_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_OrderDetail`),
  KEY `FK_Reference_14` (`orDe_ParentId`) USING BTREE,
  CONSTRAINT `tbl_orderdetail_ibfk_1` FOREIGN KEY (`orDe_ParentId`) REFERENCES `tbl_order` (`pk_Order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_ordertracking`
--

DROP TABLE IF EXISTS `tbl_ordertracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_ordertracking` (
  `pk_OrderTracking` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `orTr_Id` int(11) NOT NULL COMMENT '订单ID',
  `orTr_Status` int(11) NOT NULL COMMENT '订单状态 1购买 2付款 3预约安装 4收货 5安装中 6完成',
  `orTr_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `orTr_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`pk_OrderTracking`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8 COMMENT='订单跟踪';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_paraconfig`
--

DROP TABLE IF EXISTS `tbl_paraconfig`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_paraconfig` (
  `pk_paraConfig` int(11) NOT NULL AUTO_INCREMENT,
  `para_Name` varchar(100) NOT NULL COMMENT '配置名称',
  `para_Type` int(11) NOT NULL COMMENT '配置类型 1-车品牌 2-车型号 3-电桩搜索半径 4-设备报修类型 5-所属用户类型',
  `para_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `para_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_paraConfig`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='极光推送表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_partner`
--

DROP TABLE IF EXISTS `tbl_partner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_partner` (
  `pk_partner` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `partnerName` varchar(200) NOT NULL DEFAULT '' COMMENT '合格充电桩伙伴名字',
  `partnerKey` varchar(200) NOT NULL DEFAULT '' COMMENT '合格充电桩伙伴Key',
  `partnerToken` varchar(200) NOT NULL DEFAULT '' COMMENT '合格充电桩伙伴Token',
  `registerdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '合格充电桩伙伴注册日期',
  `partnerClientId` varchar(200) NOT NULL DEFAULT '' COMMENT '合格充电桩伙伴为我们分配的编号',
  `partnerUpdateCycleType` smallint(6) NOT NULL DEFAULT '1' COMMENT '1:天;2:小时',
  `partnerUpdateCycleValue` smallint(6) NOT NULL DEFAULT '0' COMMENT '数值',
  `partnerClientIp` varchar(200) NOT NULL DEFAULT '' COMMENT '合格充电桩伙伴API服务IP',
  `partnerClientPort` int(11) NOT NULL DEFAULT '0' COMMENT '合格充电桩伙伴API服务Port',
  `partnerClientKey` varchar(200) NOT NULL DEFAULT '' COMMENT '合格充电桩伙伴API服务Key',
  `valid` smallint(6) NOT NULL DEFAULT '1' COMMENT '合格充电桩伙伴是否有效;1:有效;0:无效,拒绝服务',
  `paymod` smallint(4) NOT NULL DEFAULT '0' COMMENT '先付费后付费(1:先付费,2:后付费)',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_partner`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='合格充电桩伙伴';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_partner_access`
--

DROP TABLE IF EXISTS `tbl_partner_access`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_partner_access` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `service_qq` varchar(20) NOT NULL DEFAULT '' COMMENT '客服QQ',
  `user_phone` varchar(20) NOT NULL DEFAULT '' COMMENT '联系人电话',
  `point_name` varchar(60) NOT NULL DEFAULT '' COMMENT '站点名称',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `partner_reason` varchar(150) NOT NULL DEFAULT '' COMMENT '驳回原因',
  `status` smallint(6) NOT NULL DEFAULT '0' COMMENT '处理状态 1提交申请提交申请 2信息审核 3入网洽谈 4入网成功申请成功',
  `user_name` varchar(80) NOT NULL DEFAULT '' COMMENT '联系人姓名',
  `station_addr` varchar(200) NOT NULL DEFAULT '' COMMENT '建桩地址',
  `station_type` varchar(30) NOT NULL DEFAULT '' COMMENT '站点类型（1、家庭充电 2、目的地充电点 3、其他）',
  `installation` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否已安装电桩(1、是 2、否)',
  `pile_num` varchar(20) NOT NULL DEFAULT '' COMMENT '电桩数量（1:交流 2:直流）',
  `open_time` varchar(60) NOT NULL DEFAULT '' COMMENT '开放时间段(1：工作日2：节假日)',
  `charging_cost` varchar(30) NOT NULL DEFAULT '' COMMENT '充电费用(1：电费 2：服务费 3：停车费)',
  `car_type_match` varchar(60) NOT NULL DEFAULT '' COMMENT '匹配车型',
  `user_email` varchar(50) NOT NULL DEFAULT '' COMMENT '联系邮箱',
  `cooperation_mode` smallint(6) NOT NULL DEFAULT '0' COMMENT '1、有地无桩 2、有桩不懂运营 3、其他合作模式',
  `partner_type` smallint(6) NOT NULL DEFAULT '0' COMMENT '1、个人 2、公司',
  `station_type_text` varchar(40) NOT NULL DEFAULT '' COMMENT '站点类型手填内容',
  `pile_num_ac` int(11) NOT NULL DEFAULT '0' COMMENT '交流充电桩数量，补充pile_num字段',
  `pile_num_dc` int(11) NOT NULL DEFAULT '0' COMMENT '直流充电桩数量，补充pile_num字段',
  `open_time_work` varchar(40) NOT NULL DEFAULT '' COMMENT '工作日开放时间段手填内容，补充open_time字段',
  `open_time_holiday` varchar(40) NOT NULL DEFAULT '' COMMENT '节假日开放时间手填字段，补充open_time字段',
  `charging_fee` varchar(40) NOT NULL DEFAULT '' COMMENT '电费手填内容，补充charging_cost字段',
  `service_fee` varchar(40) NOT NULL DEFAULT '' COMMENT '服务费手填内容，补充charging_cost字段',
  `parking_fee` varchar(40) NOT NULL DEFAULT '' COMMENT '停车费手填内容，补充charging_cost字段',
  `company_name` varchar(50) NOT NULL DEFAULT '' COMMENT '企业名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='电桩入网、合 作伙伴申请信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_partner_time`
--

DROP TABLE IF EXISTS `tbl_partner_time`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_partner_time` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新TOKEN时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='合格充电桩伙伴更新时间';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_payorder`
--

DROP TABLE IF EXISTS `tbl_payorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_payorder` (
  `pk_PayOrder` int(11) NOT NULL AUTO_INCREMENT,
  `payo_UserId` varchar(20) NOT NULL COMMENT '对应用户表主键ID\r\n',
  `payo_GroupId` varchar(20) NOT NULL COMMENT '对应分组表主键ID\r\n',
  `payo_TypePay` varchar(20) NOT NULL COMMENT '对应消费表主键ID\r\n',
  `payo_PayNo` varchar(20) DEFAULT NULL,
  `payo_PayMoney` varchar(20) DEFAULT NULL,
  `payo_Status` varchar(20) DEFAULT NULL COMMENT '1：未支付 2：已支付\r\n',
  `payo_OrderType` int(11) NOT NULL COMMENT '1：支付宝 2：银联支付',
  PRIMARY KEY (`pk_PayOrder`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_permission`
--

DROP TABLE IF EXISTS `tbl_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_permission` (
  `pk_Permission` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `perm_PermissionName` varchar(200) NOT NULL COMMENT '权限名称',
  `perm_PermissionRemark` varchar(200) DEFAULT NULL COMMENT '权限描述',
  `perm_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '权限描述',
  `perm_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_Permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配置权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_pilemaker`
--

DROP TABLE IF EXISTS `tbl_pilemaker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_pilemaker` (
  `pk_Carmaker` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `maker_Name` varchar(20) NOT NULL DEFAULT '' COMMENT '制造厂商名称',
  `maker_Remark` varchar(20) NOT NULL DEFAULT '' COMMENT '标识',
  `maker_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `maker_Updatedate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`pk_Carmaker`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='电桩生产厂商';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_powermodule`
--

DROP TABLE IF EXISTS `tbl_powermodule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_powermodule` (
  `pk_PowerModuleid` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pm_chargeSerialNo` varchar(40) NOT NULL COMMENT '充电流水号',
  `pm_PowerModuleName` varchar(50) NOT NULL DEFAULT '0' COMMENT '电源模块号',
  `pm_output_voltage` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '输出电压(精度0.1，单位v)',
  `pm_output_current` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '输出电流(精度0.1，单位A)',
  `pm_a_voltage` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'A相电压(精度0.1，单位v)',
  `pm_b_voltage` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'B相电压(精度0.1，单位v)',
  `pm_c_voltage` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'C相电压(精度0.1，单位v)',
  `pm_a_current` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'A相电流(精度0.1，单位A)',
  `pm_b_current` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'B相电流(精度0.1，单位A)',
  `pm_c_current` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT 'C相电流(精度0.1，单位A)',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `pm_temperature` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '电源模块温度',
  PRIMARY KEY (`pk_PowerModuleid`)
) ENGINE=InnoDB AUTO_INCREMENT=43429 DEFAULT CHARSET=utf8 COMMENT='电源模块表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_powerstation`
--

DROP TABLE IF EXISTS `tbl_powerstation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_powerstation` (
  `pk_PowerStation` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `poSt_Name` varchar(200) NOT NULL DEFAULT '' COMMENT '电站名称',
  `poSt_Address` varchar(200) NOT NULL DEFAULT '' COMMENT '地址',
  `poSt_Longitude` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '经度',
  `poSt_Latitude` decimal(10,6) NOT NULL DEFAULT '0.000000' COMMENT '纬度',
  `poSt_Phone` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
  `poSt_Eleids` text NOT NULL COMMENT '电站绑定相关电桩，电桩id用逗号隔开',
  `poSt_Status` int(11) NOT NULL DEFAULT '0' COMMENT '电站状态（0-草稿，3-已驳回，5-提交审核，10-离线，12-分享申请中，15-上线）',
  `poSt_Createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `poSt_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `poSt_Remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `poSt_PowerUser` int(1) NOT NULL DEFAULT '0' COMMENT '电桩用途(电动车、自行车) ',
  `poSt_IsAppoint` int(11) NOT NULL DEFAULT '0' COMMENT '电桩是否支持预约',
  `poSt_RejectionReason` varchar(500) NOT NULL DEFAULT '' COMMENT '审核驳回原因',
  `poSt_OnlineTime` varchar(100) NOT NULL DEFAULT '' COMMENT '开放时间',
  `poSt_UserName` varchar(50) NOT NULL DEFAULT '' COMMENT '电站所属用户',
  `poSt_createUserId` int(11) NOT NULL COMMENT '电站创建人',
  `poSt_OwnProvinceCode` varchar(10) NOT NULL DEFAULT '' COMMENT '电站所属省份代码',
  `poSt_OwnCityCode` varchar(10) NOT NULL DEFAULT '' COMMENT '电站所属城市代码',
  `poSt_OwnCountyCode` varchar(10) NOT NULL DEFAULT '' COMMENT '电站所属区县代码',
  PRIMARY KEY (`pk_PowerStation`)
) ENGINE=InnoDB AUTO_INCREMENT=1133 DEFAULT CHARSET=utf8 COMMENT='电站';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`aichong`@`%`*/ /*!50003 TRIGGER `tri_powerstation_update` AFTER UPDATE ON `tbl_powerstation` FOR EACH ROW begin
	set	@v_PowerSate=	new.poSt_Status;
	set	@v_PowerId=	new.pk_PowerStation;
if @v_PowerSate= 10	or @v_PowerSate= 15	 then	
		update tbl_electricpile	set	elPi_State=@v_PowerSate	 where elPi_Binding=1	and	elPi_RelevancePowerStation=@v_PowerId;
end	if;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `tbl_powerstationcomment`
--

DROP TABLE IF EXISTS `tbl_powerstationcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_powerstationcomment` (
  `pk_PsComment` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `psc_PsId` int(11) NOT NULL DEFAULT '0' COMMENT '充电站id',
  `psc_UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户Id(tbl_UserInfo表中获取)',
  `psc_UserName` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `psc_CommentPic` varchar(200) NOT NULL DEFAULT '' COMMENT '评论图片',
  `psc_Createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `psc_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `psc_Utatus` int(11) NOT NULL DEFAULT '0' COMMENT '0：显示 -1 已删除',
  `psc_Content` varchar(200) NOT NULL DEFAULT '' COMMENT '评论内容',
  `reply_count` int(8) NOT NULL DEFAULT '0' COMMENT '回复数量',
  `praise_count` int(8) NOT NULL DEFAULT '0' COMMENT '点赞数量',
  `up_commentId` int(11) NOT NULL DEFAULT '0' COMMENT '上级评论ID',
  PRIMARY KEY (`pk_PsComment`),
  KEY `FK_Reference_5` (`psc_PsId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8 COMMENT='充电站评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_powerstationcomment_praise`
--

DROP TABLE IF EXISTS `tbl_powerstationcomment_praise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_powerstationcomment_praise` (
  `pk_praiseId` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `ps_commentId` int(11) NOT NULL DEFAULT '0' COMMENT '评论ID',
  `praise_UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  PRIMARY KEY (`pk_praiseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电站点赞表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_powerstationstar`
--

DROP TABLE IF EXISTS `tbl_powerstationstar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_powerstationstar` (
  `pk_PsStar` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pss_PsId` int(11) NOT NULL DEFAULT '0' COMMENT '充电站id',
  `pss_UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户Id(tbl_UserInfo表中获取)',
  `pss_UserName` varchar(20) NOT NULL DEFAULT '' COMMENT '用户名',
  `pss_CommentStar` double(11,0) NOT NULL DEFAULT '0' COMMENT '星级评价',
  `pss_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `pss_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `pss_Utatus` int(11) NOT NULL DEFAULT '0' COMMENT '0：显示 -1 已删除',
  PRIMARY KEY (`pk_PsStar`),
  KEY `FK_Reference_5` (`pss_PsId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8 COMMENT='充电站星级评分';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_product`
--

DROP TABLE IF EXISTS `tbl_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_product` (
  `pk_Product` int(11) NOT NULL AUTO_INCREMENT,
  `prod_ProductCode` varchar(50) NOT NULL DEFAULT '' COMMENT '商品编号',
  `prod_ProductName` varchar(50) NOT NULL DEFAULT '' COMMENT '产品名称',
  `prod_CategoryId` int(11) NOT NULL DEFAULT '0' COMMENT '分类Id(从表tbl_ProductCategory中获取)',
  `prod_ProductPrice` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '成本价格',
  `prod_ProductDiscount` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '折扣率',
  `prod_DiscountPrice` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '成本价格*折扣率',
  `prod_MarketPrice` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '市场价格',
  `prod_StockQuantity` int(11) NOT NULL DEFAULT '0' COMMENT '库存数量',
  `prod_ProductlsAdd` int(11) NOT NULL DEFAULT '0' COMMENT '1：待上架 2：上架',
  `prod_SoldQuantity` int(11) NOT NULL DEFAULT '0' COMMENT '已售数量',
  `prod_BrowseNum` int(11) NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `prod_ProductTag` int(11) NOT NULL COMMENT '1：国家补贴 2：免税置购',
  `prod_QrCodePic` varchar(50) NOT NULL DEFAULT '' COMMENT '二维码图片',
  `prod_DetailImage` varchar(200) NOT NULL DEFAULT '' COMMENT '详细图片',
  `prod_ProductImage` varchar(200) NOT NULL DEFAULT '' COMMENT '商品图片',
  `prod_Remark` varchar(200) DEFAULT '' COMMENT '备注',
  `prod_ArtificialService` varchar(20) NOT NULL DEFAULT '' COMMENT '人工服务',
  `prod_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `prod_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `prod_status` int(11) NOT NULL DEFAULT '1' COMMENT '0 删除 1正常 ',
  `prod_Parameters` text,
  `prod_ChargingMode` int(100) DEFAULT NULL COMMENT '1-交流 2-直流',
  `prod_Feature` varchar(255) DEFAULT NULL COMMENT '产品特色',
  `prod_Brand` int(11) NOT NULL COMMENT '所属品牌',
  `prod_picType` varchar(255) DEFAULT '' COMMENT '图片类型',
  `prod_User` varchar(50) NOT NULL DEFAULT '' COMMENT '所属用户',
  `prod_PowerInterface` int(11) NOT NULL DEFAULT '0' COMMENT '接口标准',
  `prod_CarType` int(11) NOT NULL DEFAULT '0' COMMENT '电动车类型 1纯电动车 2插电式混合动力车',
  `prod_Subsidies` int(11) NOT NULL DEFAULT '0' COMMENT '政府补贴 1有 2无',
  `prod_Battery` int(11) NOT NULL DEFAULT '0' COMMENT '电池类型',
  `prod_Odometer` decimal(6,2) NOT NULL DEFAULT '0.00' COMMENT '续航里程',
  `prod_ChargingTime` varchar(20) NOT NULL DEFAULT '' COMMENT '充电时间',
  PRIMARY KEY (`pk_Product`),
  KEY `FK_Reference_3` (`prod_CategoryId`) USING BTREE,
  CONSTRAINT `tbl_product_ibfk_1` FOREIGN KEY (`prod_CategoryId`) REFERENCES `tbl_productcategory` (`pk_ProductCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='产品';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_productcategory`
--

DROP TABLE IF EXISTS `tbl_productcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_productcategory` (
  `pk_ProductCategory` int(11) NOT NULL AUTO_INCREMENT,
  `prCa_Name` varchar(30) NOT NULL COMMENT '名称',
  `prCa_ParentId` int(11) NOT NULL COMMENT '一级分类',
  `prCa_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `prCa_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `prCa_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pk_ProductCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='产品分类';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_productcomment`
--

DROP TABLE IF EXISTS `tbl_productcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_productcomment` (
  `pk_ProductComment` int(11) NOT NULL AUTO_INCREMENT,
  `prCo_ProductId` int(11) NOT NULL COMMENT '产品Id(tbl_Product表中获取)',
  `prCo_UserId` int(11) NOT NULL COMMENT '用户Id(tbl_UserInfo表中获取)',
  `prCo_UserName` varchar(20) NOT NULL COMMENT '用户名',
  `prCo_CreateTime` datetime NOT NULL COMMENT '评论时间',
  `prCo_CommentStart` double(11,0) NOT NULL COMMENT '星级',
  `prCo_CommentPic` varchar(200) DEFAULT NULL COMMENT '评论图片',
  `prCo_OrderID` int(11) DEFAULT NULL COMMENT '订单号',
  `prCo_Comment_type` int(11) NOT NULL COMMENT '1：桩体评价 2：商城商品评价 3：充值评价 4：电站评价',
  `prCo_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `prCo_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `prCo_Utatus` int(11) NOT NULL COMMENT '0：显示 -1 已删除',
  `prCo_Content` varchar(200) NOT NULL COMMENT '评论内容',
  PRIMARY KEY (`pk_ProductComment`),
  KEY `FK_Reference_5` (`prCo_ProductId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COMMENT='产品评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_productpicture`
--

DROP TABLE IF EXISTS `tbl_productpicture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_productpicture` (
  `pk_ProductPicture` int(11) NOT NULL AUTO_INCREMENT,
  `prPi_ProductId` int(11) NOT NULL COMMENT '产品Id(tbl_Product表中获取)',
  `prPi_Url` varchar(200) NOT NULL COMMENT '图片链接',
  `prPi_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `prPi_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_ProductPicture`),
  KEY `FK_Reference_2` (`prPi_ProductId`) USING BTREE,
  CONSTRAINT `tbl_productpicture_ibfk_1` FOREIGN KEY (`prPi_ProductId`) REFERENCES `tbl_product` (`pk_Product`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品图片';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_publish_ep`
--

DROP TABLE IF EXISTS `tbl_publish_ep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_publish_ep` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img` varchar(500) NOT NULL DEFAULT '' COMMENT '桩体图片',
  `address` varchar(100) NOT NULL DEFAULT '' COMMENT '所在地址',
  `longitude` varchar(20) NOT NULL DEFAULT '' COMMENT '经度',
  `latitude` varchar(20) NOT NULL DEFAULT '' COMMENT '维度',
  `parameter_note` varchar(800) NOT NULL DEFAULT '' COMMENT '参数说明',
  `note` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `status` smallint(1) NOT NULL DEFAULT '0' COMMENT '0未通过审核1通过审核',
  `edittime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=136 DEFAULT CHARSET=utf8 COMMENT='电桩发布表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_purchasehistory`
--

DROP TABLE IF EXISTS `tbl_purchasehistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_purchasehistory` (
  `pk_PurchaseHistory` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `puHi_Type` int(11) DEFAULT NULL COMMENT '1-充电消费 2-预约消费 3-购物消费 4-充值 5-发票邮费 6-优惠券 7-vin码优惠',
  `puHi_PurchaseHistoryTime` datetime NOT NULL COMMENT '消费时间',
  `puHi_Monetary` decimal(12,4) DEFAULT NULL COMMENT '金额（元）',
  `puHi_ConsumerRemark` varchar(500) NOT NULL DEFAULT '' COMMENT '消费备注',
  `puHi_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `puHi_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `puHi_PurchaseContent` varchar(255) DEFAULT NULL COMMENT '收益内容',
  `puHi_UserId` int(11) NOT NULL COMMENT '用户ID',
  `puHi_ChargeType` smallint(6) NOT NULL DEFAULT '0' COMMENT '充值类型，1-支付宝充值，2-微信充值，3-银联充值，4-充电卡现金充值 ，5-换卡转账 6-7月活动送',
  `puHi_UserOrigin` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户来源 1:富士康; 2:吉利; 3:绿地; 4:浙誉; 5:车纷享; 以后根据情况再扩展或修改',
  `puHi_ElectricPileCode` varchar(50) NOT NULL DEFAULT '' COMMENT '桩体编号',
  `puHi_ExternalCardNumber` varchar(20) NOT NULL DEFAULT '' COMMENT '卡号',
  `puHi_TransactionNumber` varchar(50) NOT NULL DEFAULT '' COMMENT '充电交易流水号',
  `puHi_BespokeNumber` varchar(50) NOT NULL DEFAULT '' COMMENT '预约订单编号',
  `puHi_PaymentNumber` varchar(50) NOT NULL DEFAULT '' COMMENT '充值订单编号',
  `puhi_InvoiceStatus` smallint(4) NOT NULL DEFAULT '0' COMMENT '开票状态：0：未开票 1：已提交 2.已开票',
  `pk_Invoice` bigint(20) NOT NULL DEFAULT '0' COMMENT '发票主键',
  PRIMARY KEY (`pk_PurchaseHistory`),
  KEY `index_puHi_UserId` (`puHi_UserId`) USING BTREE,
  KEY `index_PurchaseHistory` (`pk_PurchaseHistory`) USING BTREE,
  KEY `index_puHi_Type` (`puHi_Type`) USING BTREE,
  KEY `index_puhi_TransactionNumber` (`puHi_TransactionNumber`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=460828 DEFAULT CHARSET=utf8 COMMENT='消费记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_purebusiness`
--

DROP TABLE IF EXISTS `tbl_purebusiness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_purebusiness` (
  `pk_PureBusiness` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `puBu_GroupID` int(11) DEFAULT NULL COMMENT '分组ID',
  `puBu_CompanyName` varchar(50) NOT NULL COMMENT '企业名称',
  `puBu_NickName` varchar(50) NOT NULL COMMENT '平台昵称',
  `puBu_CompanyAddress` varchar(500) NOT NULL COMMENT '企业地址',
  `puBu_CompanyEmail` varchar(50) DEFAULT NULL COMMENT '企业邮箱',
  `puBu_PostCode` int(11) DEFAULT NULL COMMENT '邮编',
  `puBu_ScopeBusiness` varchar(500) NOT NULL COMMENT '经营范围',
  `puBu_AuthorizedName` varchar(50) NOT NULL COMMENT '授权人名称',
  `puBu_AuthorizedPhone` varchar(20) NOT NULL COMMENT '授权人联系电话',
  `puBu_AuthorizedCard` varchar(500) DEFAULT '' COMMENT '授权人身份证',
  `puBu_BusinessLicence` varchar(500) DEFAULT '' COMMENT '营业执照',
  `puBu_OrganizationCode` varchar(100) NOT NULL COMMENT '组织机构代码',
  `puBu_TorontoHospital` varchar(500) DEFAULT '' COMMENT '税务登记证',
  `puBu_Authorization` varchar(500) DEFAULT '' COMMENT '授权证明',
  `puBu_MailingAddress` varchar(500) NOT NULL COMMENT '邮寄地址',
  `puBu_PureBusinessStatus` int(11) NOT NULL COMMENT '0 未开通 1 开通',
  `puBu_LoveLogin` varchar(20) NOT NULL COMMENT '爱充网帐号',
  `puBu_LovePassword` varchar(255) DEFAULT NULL,
  `puBu_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `puBu_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `puBu_PicType` varchar(20) DEFAULT NULL COMMENT '1:授权人身份证 2:营业执照 3:税务登记证 4:授权证明',
  `puBu_ParentLoveLoginId` varchar(20) NOT NULL DEFAULT '' COMMENT '商加主账号登录ID，主账号为空，子账号为主商家登录ID',
  `puBu_CompanyId` int(11) NOT NULL DEFAULT '0' COMMENT '公司Id',
  PRIMARY KEY (`pk_PureBusiness`),
  KEY `FK_Reference_28` (`puBu_GroupID`) USING BTREE,
  CONSTRAINT `tbl_purebusiness_ibfk_1` FOREIGN KEY (`puBu_GroupID`) REFERENCES `tbl_group` (`pk_Group`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='纯商家信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_pushmessage`
--

DROP TABLE IF EXISTS `tbl_pushmessage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_pushmessage` (
  `pk_Pushmessage` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `puMe_Title` varchar(200) NOT NULL COMMENT '消息标题',
  `puMe_Addtime` datetime NOT NULL COMMENT '添加时间',
  `puMe_AddUser` varchar(20) DEFAULT NULL COMMENT '添加用户 管理员',
  `puMe_Content` text COMMENT '消息详情',
  PRIMARY KEY (`pk_Pushmessage`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统向用户发送消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_qrcode`
--

DROP TABLE IF EXISTS `tbl_qrcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_qrcode` (
  `pk_QRCode` int(11) NOT NULL AUTO_INCREMENT,
  `qRCo_MerchantID` int(11) NOT NULL COMMENT '商户ID',
  `qRCo_Pic` varchar(200) NOT NULL COMMENT '二维码地址',
  `qRCo_Date` datetime NOT NULL COMMENT '时间',
  `qRCo__Number` int(11) NOT NULL COMMENT '编号',
  PRIMARY KEY (`pk_QRCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='二维码';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_rateinformation`
--

DROP TABLE IF EXISTS `tbl_rateinformation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rateinformation` (
  `pk_RateInformation` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `raIn_ModelId` int(11) NOT NULL DEFAULT '0' COMMENT '计费模型ID',
  `raIn_EffectiveDates` datetime NOT NULL COMMENT '生效日期',
  `raIn_ExpiryDate` datetime NOT NULL COMMENT '失效日期',
  `raIn_FreezingMoney` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '预冻结金额',
  `raIn_MinFreezingMoney` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '最小冻结金额',
  `raIn_QuantumDate` varchar(500) NOT NULL DEFAULT '' COMMENT '采用JSON形式存储实际格式',
  `raIn_StartQuantumDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开始时间段',
  `raIn_EndQuantumDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间段',
  `raIn_TimeMarker` int(11) NOT NULL DEFAULT '0' COMMENT '时段标志',
  `raIn_TipTimeTariff` decimal(6,4) DEFAULT NULL COMMENT '尖时段电价',
  `raIn_PeakElectricityPrice` decimal(6,4) DEFAULT NULL COMMENT '峰时段电价',
  `raIn_UsualPrice` decimal(6,4) DEFAULT NULL COMMENT '平时段电价',
  `raIn_ValleyTimePrice` decimal(6,4) DEFAULT NULL COMMENT '谷时段电价',
  `raIn_ReservationRate` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '预约单价',
  `raIn_ServiceCharge` decimal(8,4) DEFAULT NULL,
  `raIn_WarnMoney` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '告警余额',
  `userId` varchar(50) NOT NULL DEFAULT '' COMMENT '添加费率的用户，p_m_user表的id',
  `raIn_area_id` varchar(20) NOT NULL DEFAULT '' COMMENT '区县编号',
  `raIn_city_id` varchar(20) NOT NULL DEFAULT '' COMMENT '市级编号',
  `raIn_province_id` varchar(20) NOT NULL DEFAULT '' COMMENT '省级编号',
  `raIn_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `raIn_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `raIn_remarks` varchar(50) NOT NULL DEFAULT '' COMMENT '备注',
  `raIn_type` smallint(1) NOT NULL DEFAULT '1' COMMENT '1:费率2位，2：费率4位',
  PRIMARY KEY (`pk_RateInformation`)
) ENGINE=InnoDB AUTO_INCREMENT=416 DEFAULT CHARSET=utf8 COMMENT='费率信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_rateinformation_history`
--

DROP TABLE IF EXISTS `tbl_rateinformation_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rateinformation_history` (
  `pk_Id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pk_RateInformation` int(11) NOT NULL DEFAULT '0' COMMENT '费率ID',
  `raIn_ModelId` int(11) NOT NULL DEFAULT '0' COMMENT '计费模型ID',
  `raIn_EffectiveDates` datetime NOT NULL COMMENT '生效日期',
  `raIn_ExpiryDate` datetime NOT NULL COMMENT '失效日期',
  `raIn_FreezingMoney` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '预冻结金额',
  `raIn_MinFreezingMoney` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '最小冻结金额',
  `raIn_QuantumDate` varchar(500) NOT NULL DEFAULT '' COMMENT '采用JSON形式存储实际格式',
  `raIn_StartQuantumDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '开始时间段',
  `raIn_EndQuantumDate` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '结束时间段',
  `raIn_TimeMarker` int(11) NOT NULL DEFAULT '0' COMMENT '时段标志',
  `raIn_TipTimeTariff` decimal(6,4) DEFAULT NULL,
  `raIn_PeakElectricityPrice` decimal(6,4) DEFAULT NULL,
  `raIn_UsualPrice` decimal(6,4) DEFAULT NULL,
  `raIn_ValleyTimePrice` decimal(6,4) DEFAULT NULL,
  `raIn_ReservationRate` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '预约单价',
  `raIn_ServiceCharge` decimal(8,4) DEFAULT NULL,
  `raIn_WarnMoney` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '告警余额',
  `userId` varchar(50) NOT NULL DEFAULT '' COMMENT '添加费率的用户，p_m_user表的id',
  `raIn_area_id` varchar(20) NOT NULL DEFAULT '' COMMENT '区县编号',
  `raIn_city_id` varchar(20) NOT NULL DEFAULT '' COMMENT '市级编号',
  `raIn_province_id` varchar(20) NOT NULL DEFAULT '' COMMENT '省级编号',
  `raIn_update_date` datetime NOT NULL COMMENT '修改日期',
  `raIn_update_user` int(11) NOT NULL DEFAULT '0' COMMENT '修改人',
  `raIn_type` smallint(1) NOT NULL DEFAULT '1' COMMENT '1:费率2位，2：费率4位',
  PRIMARY KEY (`pk_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=489 DEFAULT CHARSET=utf8 COMMENT='费率历史信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_recordupdatedkey`
--

DROP TABLE IF EXISTS `tbl_recordupdatedkey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_recordupdatedkey` (
  `pk_RecordUpdatedKey` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rUKe_UsingmachineCode` int(11) NOT NULL COMMENT '使用终端机器编号',
  `rUKe_OriginalKey` varchar(20) NOT NULL COMMENT '原始密钥',
  `rUKe_SuccessfulIdentification` int(11) NOT NULL COMMENT '成功标识',
  `rUKe_RandomTime` datetime NOT NULL COMMENT '随机时间',
  `rUKe_KeyData` varchar(20) NOT NULL COMMENT '密钥数据',
  `rUKe_Status` int(11) NOT NULL COMMENT '状态(0-成功 1：失败)',
  `rUKe_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `rUKe_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pk_RecordUpdatedKey`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='密钥更新记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_rescue`
--

DROP TABLE IF EXISTS `tbl_rescue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_rescue` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `com_name` varchar(50) NOT NULL DEFAULT '' COMMENT '企业名称',
  `com_phone` varchar(20) NOT NULL DEFAULT '' COMMENT '联系电话',
  `edittime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8 COMMENT='保险公司信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_reservationpayment`
--

DROP TABLE IF EXISTS `tbl_reservationpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_reservationpayment` (
  `pk_ReservationPayment` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rePa_UseId` int(11) DEFAULT NULL COMMENT '用户信_Id',
  `rePa_ReservationPaymentCode` varchar(50) NOT NULL COMMENT '预约订单编号',
  `rePa_ReservationContent` varchar(500) NOT NULL COMMENT '预约内容',
  `rePa_BuyTime` datetime NOT NULL COMMENT '预约买断时间',
  `rePa_FrozenAmt` decimal(8,2) DEFAULT NULL COMMENT '预约冻结金额',
  `rePa_Createdate` timestamp NULL DEFAULT NULL,
  `rePa_Updatedate` timestamp NULL DEFAULT NULL,
  `rePa_ActualAmount` decimal(8,2) DEFAULT NULL,
  `rePa_ElectricId` int(11) DEFAULT NULL COMMENT '电桩Id',
  `rePa_GroupId` int(11) DEFAULT NULL COMMENT '分组Id',
  `rePa_Type` int(11) DEFAULT NULL COMMENT '消费类型',
  PRIMARY KEY (`pk_ReservationPayment`),
  KEY `FK_Reference_19` (`rePa_UseId`) USING BTREE,
  CONSTRAINT `tbl_reservationpayment_ibfk_1` FOREIGN KEY (`rePa_UseId`) REFERENCES `tbl_userinfo` (`pk_UserInfo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预约支付';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_searchhistory`
--

DROP TABLE IF EXISTS `tbl_searchhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_searchhistory` (
  `pk_SearchHistory` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `seHi_SearchHistory` varchar(200) NOT NULL COMMENT '搜索名称',
  `seHi_Status` int(11) DEFAULT NULL COMMENT '状态',
  `seHi_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `seHi_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_SearchHistory`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8 COMMENT='搜索历史记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_share_ep`
--

DROP TABLE IF EXISTS `tbl_share_ep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_share_ep` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `img` varchar(100) NOT NULL DEFAULT '' COMMENT '桩体图片',
  `address` varchar(100) NOT NULL DEFAULT '' COMMENT '所在地址',
  `longitude` varchar(20) NOT NULL DEFAULT '' COMMENT '经度',
  `latitude` varchar(20) NOT NULL DEFAULT '' COMMENT '维度',
  `parameter_note` varchar(800) NOT NULL DEFAULT '' COMMENT '参数说明',
  `note` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  `status` smallint(1) NOT NULL DEFAULT '0' COMMENT '0未通过审核1通过审核',
  `edittime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='分享电桩表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_shoppingcart`
--

DROP TABLE IF EXISTS `tbl_shoppingcart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_shoppingcart` (
  `pk_ShoppingCart` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `shCa_UserId` int(11) NOT NULL COMMENT '用户Id',
  `shCa_ProductId` int(11) NOT NULL COMMENT '产品Id',
  `shCa_Count` int(11) NOT NULL COMMENT '数量',
  `shCa_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '数量',
  `shCa_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_ShoppingCart`),
  KEY `FK_Reference_8` (`shCa_ProductId`) USING BTREE,
  CONSTRAINT `tbl_shoppingcart_ibfk_1` FOREIGN KEY (`shCa_ProductId`) REFERENCES `tbl_product` (`pk_Product`)
) ENGINE=InnoDB AUTO_INCREMENT=142 DEFAULT CHARSET=utf8 COMMENT='购物车';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_threshod_warning`
--

DROP TABLE IF EXISTS `tbl_threshod_warning`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_threshod_warning` (
  `thw_pk_id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '预警阀值表主键',
  `thw_user_id` int(11) NOT NULL COMMENT ' tbl_user_normal表合作方用户ID',
  `thw_busi_name` varchar(50) NOT NULL COMMENT ' tbl_user_normal表 norm_real_name字段',
  `thw_threshold` int(11) unsigned NOT NULL DEFAULT '0' COMMENT '阀值设置 默认0',
  `thw_customer_phone` varchar(16) NOT NULL DEFAULT '' COMMENT '大客户预警通知手机号',
  `thw_cellphone` varchar(16) NOT NULL DEFAULT '' COMMENT '运营人员通知手机号',
  `thw_deletetag` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否删除 默认0 不删除  1 删除',
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`thw_pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家帐号资金池阀值告警表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_typespan`
--

DROP TABLE IF EXISTS `tbl_typespan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_typespan` (
  `pk_TypeSpanId` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `ts_TypeSpan` varchar(50) NOT NULL DEFAULT '' COMMENT '产品型号',
  `ts_ModelName` varchar(50) NOT NULL DEFAULT '' COMMENT '产品名称',
  `ts_FactTag` varchar(50) NOT NULL DEFAULT '' COMMENT '产品说明',
  `ts_FileName` varchar(50) NOT NULL DEFAULT '' COMMENT '文件ID和名称',
  `ts_ProductNumber` varchar(50) DEFAULT '' COMMENT '外部产品型号（其他公司产品型号）',
  `ts_Remarks` varchar(200) DEFAULT '' COMMENT '备注',
  `ts_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `ts_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_TypeSpanId`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8 COMMENT='产品型号表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `user_account` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户帐号',
  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
  `user_leval` smallint(1) NOT NULL DEFAULT '0' COMMENT '用户等级1：超级管理员 2：系统管理员 3:纯商家用户  5:个体商家用户 6:普通用户',
  `user_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '用户状态 1:正常 2:冻结  3:删除',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_account` (`user_account`) USING BTREE,
  KEY `index_user_id` (`user_id`) USING BTREE,
  KEY `idx_user_level` (`user_leval`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17540 DEFAULT CHARSET=utf8 COMMENT='用户汇总表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_user_admin`
--

DROP TABLE IF EXISTS `tbl_user_admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user_admin` (
  `user_id` int(11) NOT NULL,
  `admin_name` varchar(32) NOT NULL DEFAULT '' COMMENT '管理员姓名',
  `admin_phone` varchar(32) NOT NULL DEFAULT '' COMMENT '管理员手机号',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_user_business`
--

DROP TABLE IF EXISTS `tbl_user_business`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user_business` (
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户主表ID，不自增',
  `busi_company_id` int(6) NOT NULL DEFAULT '0' COMMENT '纯商家用户所属公司ID',
  `busi_parent_id` int(11) NOT NULL DEFAULT '0' COMMENT '纯商家用户父ID',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `busi_name` varchar(32) NOT NULL DEFAULT '' COMMENT '纯商家用户昵称',
  `busi_phone` varchar(32) NOT NULL DEFAULT '' COMMENT '纯商家用户手机号',
  PRIMARY KEY (`user_id`),
  KEY `index_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='纯商家用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `tbl_user_business_view`
--

DROP TABLE IF EXISTS `tbl_user_business_view`;
/*!50001 DROP VIEW IF EXISTS `tbl_user_business_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `tbl_user_business_view` AS SELECT 
 1 AS `user_id`,
 1 AS `user_account`,
 1 AS `user_password`,
 1 AS `user_leval`,
 1 AS `user_status`,
 1 AS `busi_company_id`,
 1 AS `busi_parent_id`,
 1 AS `busi_name`,
 1 AS `busi_phone`,
 1 AS `parent_user_account`,
 1 AS `parent_user_leval`,
 1 AS `parent_user_status`,
 1 AS `company_name`,
 1 AS `company_address`,
 1 AS `company_email`,
 1 AS `company_phone`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `tbl_user_message`
--

DROP TABLE IF EXISTS `tbl_user_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `to_user_id` int(11) unsigned zerofill NOT NULL DEFAULT '00000000000' COMMENT '收信人id (0为全部人接收，大于0为具体接收人id)',
  `from_user_id` int(11) NOT NULL DEFAULT '0' COMMENT '发送人id',
  `from_user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '发信人名称',
  `title` varchar(50) NOT NULL DEFAULT '' COMMENT '消息标题',
  `content` varchar(300) NOT NULL DEFAULT '' COMMENT '信息内容',
  `status` smallint(255) NOT NULL DEFAULT '0' COMMENT '收信息标识，0未读1已读2用户删除（数据库中保留）',
  `edittime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `createtime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_user_newcoupon`
--

DROP TABLE IF EXISTS `tbl_user_newcoupon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user_newcoupon` (
  `pk_NewCoupon` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `ac_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '交流新手券 0:未领取;1:领取',
  `dc_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '直流新手券 0:未领取;1:领取',
  PRIMARY KEY (`pk_NewCoupon`)
) ENGINE=InnoDB AUTO_INCREMENT=10149 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_user_normal`
--

DROP TABLE IF EXISTS `tbl_user_normal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user_normal` (
  `user_id` int(11) NOT NULL DEFAULT '0' COMMENT '用户主表ID，不自增',
  `norm_name` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户昵称',
  `norm_real_name` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户真实姓名',
  `norm_status` smallint(1) NOT NULL DEFAULT '0' COMMENT '普通用户状态:1：初始化 2 ：个体商家待审批 3：已审批通过',
  `norm_registe_type` smallint(1) NOT NULL DEFAULT '0' COMMENT '普通用户注册来源(1：管理后台 2：web 3：android 4：ios)',
  `norm_account_balance` decimal(10,2) DEFAULT '0.00' COMMENT '普通用户帐户余额',
  `norm_pay_password` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户支付密码',
  `norm_device_id` varchar(64) NOT NULL DEFAULT '' COMMENT '普通用户登录设备ID',
  `norm_email` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户邮箱',
  `norm_address` varchar(100) NOT NULL DEFAULT '' COMMENT '普通用户地址',
  `norm_id_card` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户身份证号',
  `norm_sex` smallint(1) NOT NULL DEFAULT '0' COMMENT '普通用户性别 (1:男 2：女)',
  `norm_birthday` varchar(20) NOT NULL DEFAULT '1900-01-01 00:00:00' COMMENT '普通用户生日',
  `norm_integrate` int(11) NOT NULL DEFAULT '0' COMMENT '普通用户积分',
  `norm_car_company_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '普通用户汽车品牌ID',
  `norm_car_type_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '普通用户汽车车型ID',
  `norm_vehicle_number` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户车架号',
  `norm_plate_num` varchar(10) NOT NULL DEFAULT '' COMMENT '车牌号',
  `norm_driving_licence` varchar(32) NOT NULL DEFAULT '' COMMENT '普通用户驾驶证号',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `apply_card` smallint(4) NOT NULL DEFAULT '0' COMMENT '充电卡申请标志：0：无申请 1：申请充电卡 2：已发放充电卡',
  `norm_origin` smallint(4) DEFAULT NULL COMMENT '用户来源 1富士康;2吉利;3绿地;4;浙誉;5.车纷享; 以后根据情况再扩展或修改',
  `norm_p_code` varchar(6) NOT NULL DEFAULT '' COMMENT '用户所在省份行政编码',
  `norm_c_code` varchar(6) NOT NULL DEFAULT '' COMMENT '用户所在城市行政编码',
  `norm_a_code` varchar(6) NOT NULL DEFAULT '' COMMENT '用户所在区县行政编码',
  `norm_invitePhone` varchar(11) NOT NULL DEFAULT '' COMMENT '邀请人号码',
  PRIMARY KEY (`user_id`),
  KEY `index_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='普通用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `tbl_user_normal_view`
--

DROP TABLE IF EXISTS `tbl_user_normal_view`;
/*!50001 DROP VIEW IF EXISTS `tbl_user_normal_view`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `tbl_user_normal_view` AS SELECT 
 1 AS `user_account`,
 1 AS `user_password`,
 1 AS `user_leval`,
 1 AS `user_status`,
 1 AS `user_id`,
 1 AS `norm_name`,
 1 AS `norm_real_name`,
 1 AS `norm_status`,
 1 AS `norm_registe_type`,
 1 AS `norm_account_balance`,
 1 AS `norm_pay_password`,
 1 AS `norm_device_id`,
 1 AS `norm_email`,
 1 AS `norm_address`,
 1 AS `norm_id_card`,
 1 AS `norm_sex`,
 1 AS `norm_birthday`,
 1 AS `norm_integrate`,
 1 AS `norm_car_company_id`,
 1 AS `norm_car_type_id`,
 1 AS `norm_vehicle_number`,
 1 AS `norm_driving_licence`,
 1 AS `createdate`,
 1 AS `updatedate`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `tbl_useraddress`
--

DROP TABLE IF EXISTS `tbl_useraddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_useraddress` (
  `pk_UserAddress` int(11) NOT NULL AUTO_INCREMENT,
  `prad_UserId` int(11) NOT NULL COMMENT '用户id',
  `prad_UserName` varchar(50) NOT NULL COMMENT '用户姓名',
  `prad_PhoneNumber` varchar(20) NOT NULL COMMENT '手机号',
  `prad_PostalCode` varchar(50) NOT NULL COMMENT '邮政编码',
  `prad_Address` varchar(100) NOT NULL COMMENT '收货地址',
  `prad_Street` varchar(100) NOT NULL COMMENT '街道',
  `prad_IsDefault` varchar(2) NOT NULL COMMENT '是否为默认地址 0否 1是',
  `prad_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `prad_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_UserAddress`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COMMENT='用户收货地址表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_usercard`
--

DROP TABLE IF EXISTS `tbl_usercard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_usercard` (
  `pk_UserCard` int(11) NOT NULL AUTO_INCREMENT COMMENT '充电卡ID',
  `uc_InternalCardNumber` varchar(50) NOT NULL DEFAULT '' COMMENT '内卡号',
  `uc_ExternalCardNumber` varchar(20) NOT NULL DEFAULT '' COMMENT '外卡号',
  `uc_Balance` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `uc_CompanyNumber` int(4) NOT NULL DEFAULT '0' COMMENT '公司标识',
  `uc_pay_mode` smallint(1) unsigned NOT NULL DEFAULT '0' COMMENT '支付方式 10爱充普通公共储蓄卡，11爱充普通专属储蓄卡，12爱充企业信用卡,13爱充合作公共储蓄卡,14 爱充合作专属储蓄卡',
  `uc_UserId` int(11) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `uc_Status` smallint(1) NOT NULL DEFAULT '0' COMMENT '状态 0：正常，1：挂失',
  `uc_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `uc_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_UserCard`),
  KEY `indx_uc_Exter_car_number` (`uc_ExternalCardNumber`) USING BTREE,
  KEY `indx_uc_user_id` (`uc_UserId`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2978 DEFAULT CHARSET=utf8 COMMENT='用户充电卡表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_usercollect`
--

DROP TABLE IF EXISTS `tbl_usercollect`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_usercollect` (
  `pk_UserCollect` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `usCo_Userid` int(10) NOT NULL COMMENT '用户id',
  `usCo_Type` tinyint(1) NOT NULL COMMENT '类型（1电桩，2电站）\r\n            2为电站',
  `usCo_Objectid` int(10) DEFAULT NULL COMMENT '对象id',
  `usCo_AddTime` datetime NOT NULL COMMENT '收藏的时间',
  PRIMARY KEY (`pk_UserCollect`)
) ENGINE=InnoDB AUTO_INCREMENT=881 DEFAULT CHARSET=utf8 COMMENT='记录用户收藏的电桩电站';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_userinfo`
--

DROP TABLE IF EXISTS `tbl_userinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_userinfo` (
  `pk_UserInfo` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `usIn_GroupID` int(11) DEFAULT NULL COMMENT '分组ID',
  `usIn_UserName` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `usIn_Phone` varchar(20) NOT NULL COMMENT '手机号码',
  `usIn_FacticityName` varchar(50) DEFAULT NULL COMMENT '真实姓名',
  `usIn_Sex` int(11) DEFAULT NULL COMMENT '性别 0 男 1 女',
  `usIn_ICcode` varchar(50) DEFAULT NULL COMMENT 'IC卡号',
  `usIn_AccountBalance` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '账户余额',
  `usIn_Birthdate` datetime DEFAULT NULL COMMENT '出生日期',
  `usIn_Email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `usIn_DrivingLicense` varchar(50) DEFAULT NULL COMMENT '驾驶证号',
  `usIn_Acura` varchar(100) DEFAULT NULL COMMENT '品牌车型，根据电动车品牌类型详情ID',
  `usIn_Integrate` int(11) DEFAULT NULL COMMENT '积分',
  `usIn_HeadImage` varchar(200) DEFAULT NULL COMMENT '头像',
  `usIn_MemberCode` varchar(20) DEFAULT NULL COMMENT '会员号码',
  `usIn_UserAddress` varchar(200) DEFAULT NULL COMMENT '联系地址',
  `usIn_UserStatus` int(11) NOT NULL DEFAULT '1' COMMENT '1：初始 2：冻结 3：未冻结 4：个体商家待审批 5：已审批通过',
  `usIn_Password` varchar(50) NOT NULL COMMENT '密码',
  `pay_password` varchar(32) NOT NULL DEFAULT '' COMMENT '支付密码',
  `usIn_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `usIn_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `usIn_PlateNumber` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `usIn_VehicleNumbe` varchar(20) DEFAULT NULL COMMENT '车架号',
  `usIn_PicType` varchar(10) DEFAULT NULL COMMENT '上传图片类型',
  `usIn_device_id` varchar(64) NOT NULL DEFAULT '' COMMENT '用户登录的设备唯一标识',
  `usIn_RegisteType` smallint(6) NOT NULL DEFAULT '0' COMMENT '用户注册来源:默认是0，1管理后台，2web，3android，4ios',
  `usIn_carCompany_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '汽车厂家ID，tb_carcompany',
  `usIn_carinfo_id` smallint(6) NOT NULL DEFAULT '0' COMMENT '汽车车型,tb_carinfo',
  PRIMARY KEY (`pk_UserInfo`),
  KEY `FK_Reference_27` (`usIn_GroupID`) USING BTREE,
  KEY `index_userinfo_Phone` (`usIn_Phone`) USING BTREE,
  CONSTRAINT `tbl_userinfo_ibfk_1` FOREIGN KEY (`usIn_GroupID`) REFERENCES `tbl_group` (`pk_Group`)
) ENGINE=InnoDB AUTO_INCREMENT=872 DEFAULT CHARSET=utf8 COMMENT='用户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_usershareelec`
--

DROP TABLE IF EXISTS `tbl_usershareelec`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_usershareelec` (
  `pk_UserShareElec` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `uSEl_Userid` int(10) NOT NULL DEFAULT '0' COMMENT '用户id',
  `uSEl_ElecAddress` varchar(100) NOT NULL COMMENT '桩体地址',
  `uSEl_Maker` int(10) NOT NULL DEFAULT '0' COMMENT '电桩制造商，配置参数内容的ID （万马新能源，南京循道，北京三优，上海普天）',
  `uSEl_PileChargingMode` int(10) NOT NULL DEFAULT '0' COMMENT '充电方式，配置参数内容的ID （直流充电桩，交流充电桩）',
  `uSEl_PowerInterface` int(10) NOT NULL DEFAULT '0' COMMENT '桩体接口，配置参数内容的ID（国标、欧标、美标）',
  `uSEl_Other` varchar(100) NOT NULL DEFAULT '' COMMENT '其它',
  `uSEl_Param` varchar(255) NOT NULL DEFAULT '' COMMENT '分享参数',
  `uSEl_Image` varchar(255) NOT NULL DEFAULT '' COMMENT '分享图片',
  PRIMARY KEY (`pk_UserShareElec`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录用户分享的桩体';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_vehiclebattery`
--

DROP TABLE IF EXISTS `tbl_vehiclebattery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_vehiclebattery` (
  `pk_VehicleBattery` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vb_chargeSerialNo` varchar(40) NOT NULL COMMENT '充电流水号',
  `vb_battery_type` smallint(4) NOT NULL DEFAULT '0' COMMENT '电池类型(1:铅酸电池;2:皋氢电池;3:磷酸铁锂电池;4:锰酸锂电池;5:钴酸锂电池;6:三元材料电池;7:聚合物锂离子电池;8钛酸锂电池)',
  `vb_battery_rated_capacity` int(11) NOT NULL DEFAULT '0' COMMENT '整车动力蓄电池系统额定容量(0~1000A.h)',
  `vb_Production_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '电池组生产日期',
  `vb_cycle_count` int(11) NOT NULL DEFAULT '0' COMMENT '电池组充电次数',
  `vb_single_max_vol` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '单体蓄电池最高允许充电电压(精度0.1，单位v,倍数10)',
  `vb_max_current` decimal(8,2) NOT NULL DEFAULT '0.00' COMMENT '最高允许充电电流(精度0.1，单位A,倍数10)',
  `vb_total_energy` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '动力蓄电池标称总能量(0.1kw/h，倍数10)',
  `vb_total_rated_voltage` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '最高允许充电总电压(额定总电压)(精度0.1，单位v,倍数10)',
  `vb_max_temperature` decimal(8,1) NOT NULL DEFAULT '0.0' COMMENT '最高允许温度(精度0.1，单位度(待定)，倍数10)',
  `vb_VIN` varchar(50) NOT NULL DEFAULT '0' COMMENT '车辆识别码',
  `vb_Battery_manufacturers` varchar(50) NOT NULL DEFAULT '0' COMMENT '电池厂商',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pk_VehicleBattery`),
  KEY `idx_vb_chargeSerialNo` (`vb_chargeSerialNo`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=214448 DEFAULT CHARSET=utf8 COMMENT='车辆蓄电池信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_version`
--

DROP TABLE IF EXISTS `tbl_version`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_version` (
  `pk_Version` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `vers_Number` varchar(10) NOT NULL DEFAULT '' COMMENT '版本号',
  `vers_Type` int(11) NOT NULL DEFAULT '0' COMMENT '1：安卓 2 IOS',
  `vers_Url` varchar(200) DEFAULT '' COMMENT '版本地址',
  `vers_Createdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `vers_Updatedate` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `vers_auto_update` smallint(6) NOT NULL DEFAULT '0' COMMENT '是否强制更新',
  `vers_Remark` varchar(500) DEFAULT '' COMMENT '版本描述',
  PRIMARY KEY (`pk_Version`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='版本信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_warrantytype`
--

DROP TABLE IF EXISTS `tbl_warrantytype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_warrantytype` (
  `pk_WarrantyType` int(11) NOT NULL AUTO_INCREMENT,
  `waTy_Warranty_type` varchar(50) NOT NULL COMMENT '保修类型名称',
  `waTy_Createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `waTy_Updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`pk_WarrantyType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保修类型';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_web_recruit`
--

DROP TABLE IF EXISTS `tbl_web_recruit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_web_recruit` (
  `pk_recruit` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rec_job` varchar(45) NOT NULL COMMENT '工作岗位名称',
  `rec_place` varchar(45) NOT NULL COMMENT '工作地点',
  `rec_number` int(1) NOT NULL COMMENT '招聘人数',
  `rec_createdate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `rec_updatedate` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `rec_usepk` varchar(45) DEFAULT NULL COMMENT '发布人ID',
  `rec_jobdescribe` text NOT NULL COMMENT '工作简介（招聘列表页面的简介）',
  `rec_jobrequire` text NOT NULL COMMENT '工作详情（包括职位描述，任职要求）',
  PRIMARY KEY (`pk_recruit`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_web_release`
--

DROP TABLE IF EXISTS `tbl_web_release`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_web_release` (
  `pk_release` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `rele_title` varchar(45) NOT NULL COMMENT '发布标题',
  `rele_content` text NOT NULL COMMENT '发布内容',
  `rele_createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `rele_updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `rele_usepk` varchar(45) DEFAULT NULL COMMENT '发布人',
  `rele_type` char(1) NOT NULL COMMENT '发布类型（1活动中心2企业动态3行业动态）',
  `rele_img` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否首图',
  `rele_order` smallint(2) NOT NULL DEFAULT '0' COMMENT '排序号',
  `valid_flag` smallint(1) NOT NULL DEFAULT '0' COMMENT '是否有效',
  `brief_introduction` varchar(50) NOT NULL DEFAULT '' COMMENT '新闻简介',
  PRIMARY KEY (`pk_release`)
) ENGINE=InnoDB AUTO_INCREMENT=597 DEFAULT CHARSET=utf8 COMMENT='发布类型（1活动中心2企业动态3行业动态）';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_wechat_deal`
--

DROP TABLE IF EXISTS `tbl_wechat_deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_wechat_deal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `out_trade_no` varchar(50) NOT NULL COMMENT '微信订单编号',
  `out_refund_no` varchar(50) DEFAULT NULL COMMENT '退款编号',
  `total_fee` int(11) NOT NULL DEFAULT '0' COMMENT '预充金额',
  `open_id` varchar(50) NOT NULL COMMENT '用户标识',
  `pileCode` varchar(50) NOT NULL COMMENT '电桩编号',
  `gunCode` varchar(10) NOT NULL COMMENT '枪头编号',
  `createDate` datetime NOT NULL COMMENT '下单时间',
  `updateDate` datetime DEFAULT NULL COMMENT '退款时间',
  `refund_fee` int(11) DEFAULT '0' COMMENT '退款金额 eg:100=1元',
  `refund_code` varchar(50) DEFAULT NULL COMMENT '退款标识码：SUCCESS为成功，其余都为失败',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1132 DEFAULT CHARSET=utf8 COMMENT='微信退款表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tbl_wechat_news`
--

DROP TABLE IF EXISTS `tbl_wechat_news`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_wechat_news` (
  `we_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键递增',
  `we_title` varchar(50) DEFAULT NULL COMMENT '标题',
  `we_content_url` varchar(100) DEFAULT NULL COMMENT '阅读全文链接',
  `we_picture_url` varchar(100) DEFAULT NULL COMMENT '图片显示链接',
  `description` varchar(255) DEFAULT NULL COMMENT '简介',
  `we_type` varchar(10) DEFAULT NULL COMMENT '1:关注',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`we_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='微信消息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tem_activity_record`
--

DROP TABLE IF EXISTS `tem_activity_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tem_activity_record` (
  `act_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '活动主键',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `user_account` varchar(32) DEFAULT NULL COMMENT '用户账号',
  `act_date` varchar(32) DEFAULT NULL COMMENT '充值日期',
  `act_give` int(11) DEFAULT NULL COMMENT '充值赠送（元）',
  `act_recharge` decimal(10,2) DEFAULT NULL COMMENT '充值总额',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建日期',
  PRIMARY KEY (`act_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2542 DEFAULT CHARSET=utf8 COMMENT='十一活动记录表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Temporary table structure for view `v_comment_start`
--

DROP TABLE IF EXISTS `v_comment_start`;
/*!50001 DROP VIEW IF EXISTS `v_comment_start`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_comment_start` AS SELECT 
 1 AS `prCo_CommentStart`,
 1 AS `prCo_ProductId`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `tbl_user_business_view`
--

/*!50001 DROP VIEW IF EXISTS `tbl_user_business_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`eichong`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `tbl_user_business_view` AS select `a`.`user_id` AS `user_id`,`a`.`user_account` AS `user_account`,`a`.`user_password` AS `user_password`,`a`.`user_leval` AS `user_leval`,`a`.`user_status` AS `user_status`,`b`.`busi_company_id` AS `busi_company_id`,`b`.`busi_parent_id` AS `busi_parent_id`,`b`.`busi_name` AS `busi_name`,`b`.`busi_phone` AS `busi_phone`,`d`.`user_account` AS `parent_user_account`,`d`.`user_leval` AS `parent_user_leval`,`d`.`user_status` AS `parent_user_status`,`c`.`cpy_CompanyName` AS `company_name`,`c`.`cpy_CompanyAddress` AS `company_address`,`c`.`cpy_CompanyEmail` AS `company_email`,`c`.`cpy_AuthorizedPhone` AS `company_phone` from (`tbl_user` `a` join ((`tbl_user_business` `b` left join `tbl_company` `c` on((`b`.`busi_company_id` = `c`.`pk_CompanyId`))) left join `tbl_user` `d` on((`b`.`busi_parent_id` = `d`.`user_id`)))) where (`a`.`user_id` = `b`.`user_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `tbl_user_normal_view`
--

/*!50001 DROP VIEW IF EXISTS `tbl_user_normal_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`eichong`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `tbl_user_normal_view` AS select `a`.`user_account` AS `user_account`,`a`.`user_password` AS `user_password`,`a`.`user_leval` AS `user_leval`,`a`.`user_status` AS `user_status`,`b`.`user_id` AS `user_id`,`b`.`norm_name` AS `norm_name`,`b`.`norm_real_name` AS `norm_real_name`,`b`.`norm_status` AS `norm_status`,`b`.`norm_registe_type` AS `norm_registe_type`,`b`.`norm_account_balance` AS `norm_account_balance`,`b`.`norm_pay_password` AS `norm_pay_password`,`b`.`norm_device_id` AS `norm_device_id`,`b`.`norm_email` AS `norm_email`,`b`.`norm_address` AS `norm_address`,`b`.`norm_id_card` AS `norm_id_card`,`b`.`norm_sex` AS `norm_sex`,`b`.`norm_birthday` AS `norm_birthday`,`b`.`norm_integrate` AS `norm_integrate`,`b`.`norm_car_company_id` AS `norm_car_company_id`,`b`.`norm_car_type_id` AS `norm_car_type_id`,`b`.`norm_vehicle_number` AS `norm_vehicle_number`,`b`.`norm_driving_licence` AS `norm_driving_licence`,`b`.`createdate` AS `createdate`,`b`.`updatedate` AS `updatedate` from (`tbl_user` `a` join `tbl_user_normal` `b`) where (`a`.`user_id` = `b`.`user_id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_comment_start`
--

/*!50001 DROP VIEW IF EXISTS `v_comment_start`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`eichong`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `v_comment_start` AS select 1 AS `prCo_CommentStart`,1 AS `prCo_ProductId` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-30 14:33:36

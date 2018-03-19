-- MySQL dump 10.13  Distrib 5.1.73, for redhat-linux-gnu (x86_64)
--
-- Host: rm-uf6426u9v92z31yjp.mysql.rds.aliyuncs.com    Database: eichong
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
-- Dumping routines for database 'eichong'
--
/*!50003 DROP FUNCTION IF EXISTS `getCodeDetailName` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`eichong`@`%`*/ /*!50003 FUNCTION `getCodeDetailName`( codeGroupId VARCHAR(255), codeId VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
BEGIN
  DECLARE codeName VARCHAR(255);
  DECLARE fullPathString VARCHAR(1024);

  select cdd.code_name 
  from p_m_code_group cdg,
            p_m_code_detail cdd 
  where cdg.code_group_id = cdd.code_group_id
  and cdg.code_group_id= codeGroupId
  and cdd.code_id = codeId
  INTO codeName;
  
  
  RETURN codeName;
  
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getDistance` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`eichong`@`%`*/ /*!50003 FUNCTION `getDistance`(  LngBeginP VARCHAR(100) , LngEnd REAL ,LatBeginP VARCHAR(100) , LatEnd REAL ) RETURNS double
BEGIN  
    DECLARE Distance REAL DEFAULT 0.0;  
    DECLARE EARTH_RADIUS REAL;  
    DECLARE RadLatBegin REAL;
    DECLARE RadLatEnd REAL;
    DECLARE RadLatDiff REAL;
    DECLARE RadLngDiff REAL; 
    DECLARE LatBegin REAL;
    DECLARE LngBegin REAL;
    IF LatBeginP = '' OR LngBeginP = '' then
        return Distance;
    END IF;
    Set LatBegin = LatBeginP;
    Set LngBegin = LngBeginP;

    SET EARTH_RADIUS = 6378.137;     
    SET RadLatBegin = LatBegin * PI() / 180.0;  
    SET RadLatEnd = LatEnd * PI() / 180.0;  
    SET RadLatDiff = RadLatBegin - RadLatEnd;  
    SET RadLngDiff = LngBegin * PI() / 180.0 - LngEnd * PI() / 180.0;  
      
    SET Distance = 2 * ASIN(SQRT(POWER(Sin(RadLatDiff / 2), 2) + COS(RadLatBegin) * COS(RadLatEnd) * POWER(SIN(RadLngDiff/2),2)));  
    SET Distance = Distance * EARTH_RADIUS ;  
    return ROUND(Distance,1);
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getFullPath` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`eichong`@`%`*/ /*!50003 FUNCTION `getFullPath`(fileType VARCHAR(255), referenceId VARCHAR(255)) RETURNS varchar(1024) CHARSET utf8
BEGIN
  DECLARE deployUrl VARCHAR(255);
  DECLARE parentPath VARCHAR(255);
  DECLARE v_imageScanUrl VARCHAR(255);
  DECLARE relativePath VARCHAR(255);
  DECLARE fileName VARCHAR(255);
  DECLARE fullPathString VARCHAR(1024);
  SELECT deploy_url,parent_path,image_ScanUrl FROM p_m_deploy_info  
  INTO deployUrl,parentPath,v_imageScanUrl;
  
 SELECT group_concat(CONCAT(v_imageScanUrl,relative_path,'/',file_name)) path FROM tb_multi_media 
  WHERE bussiness_type = fileType 
  AND reference_id = referenceId
  ORDER BY title_flg desc,create_date desc,file_name desc
 INTO fullPathString;  
  
  RETURN fullPathString;
  
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `getPathForServer` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`eichong`@`%`*/ /*!50003 FUNCTION `getPathForServer`( fileType VARCHAR(255), referenceId VARCHAR(255)) RETURNS varchar(1024) CHARSET utf8
BEGIN
  DECLARE deployUrl VARCHAR(255);
  DECLARE parentPath VARCHAR(255);
  DECLARE relativePath VARCHAR(255);
  DECLARE fileName VARCHAR(255);
  DECLARE fullPathString VARCHAR(1024);
  SELECT deploy_url,parent_path FROM p_m_deploy_info  
  INTO deployUrl,parentPath;
  
  SELECT relative_path,file_name FROM tb_multi_media 
  WHERE bussiness_type = fileType 
  AND reference_id = referenceId
  AND title_flg = '1'
  LIMIT 1
  INTO relativePath,fileName;
  
  IF relativePath IS NOT NULL AND fileName IS NOT NULL THEN
      SET fullPathString = CONCAT('/',parentPath,'/',relativePath,'/',fileName);
  END IF;
  
  RETURN fullPathString;
  
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `GET_FIRST_PINYIN_CHAR` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`eichong`@`%`*/ /*!50003 FUNCTION `GET_FIRST_PINYIN_CHAR`(PARAM VARCHAR(255)) RETURNS varchar(2) CHARSET utf8
BEGIN
    DECLARE V_RETURN VARCHAR(255);
    DECLARE V_FIRST_CHAR VARCHAR(2);
    SET V_FIRST_CHAR = UPPER(LEFT(PARAM,1));
    SET V_RETURN = V_FIRST_CHAR;
    IF LENGTH( V_FIRST_CHAR) <> CHARACTER_LENGTH( V_FIRST_CHAR ) THEN
    SET V_RETURN = ELT(INTERVAL(CONV(HEX(LEFT(CONVERT(PARAM USING gbk),1)),16,10),
        0xB0A1,0xB0C5,0xB2C1,0xB4EE,0xB6EA,0xB7A2,0xB8C1,0xB9FE,0xBBF7,
        0xBFA6,0xC0AC,0xC2E8,0xC4C3,0xC5B6,0xC5BE,0xC6DA,0xC8BB,
        0xC8F6,0xCBFA,0xCDDA,0xCEF4,0xD1B9,0xD4D1),
    'A','B','C','D','E','F','G','H','J','K','L','M','N','O','P','Q','R','S','T','W','X','Y','Z');
    END IF;
    RETURN V_RETURN;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insert_coupon_proc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`eichong`@`%`*/ /*!50003 PROCEDURE `insert_coupon_proc`(cpUserid int, pkActivity int)
BEGIN
    DECLARE pkCouponvariety INT;
    DECLARE covaLimitation INT;
    DECLARE covaCouponValue INT;
    DECLARE covaCouponCondition INT;
    DECLARE covaCouponTerm INT;
    DECLARE num INT DEFAULT 0;
    DECLARE acscNum INT;
    DECLARE no_more_record INT DEFAULT 0;
    DECLARE cur_record CURSOR FOR 
        SELECT pk_CouponVariety,acsc_num 
        FROM tbl_activityschedule
        WHERE pk_Activity = pkActivity;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET no_more_record = 1;
    
    OPEN cur_record;
    FETCH cur_record INTO pkCouponvariety, acscNum;
    WHILE no_more_record != 1 DO
        SET num = 0;
        SELECT cova_Limitation,cova_CouponValue,cova_CouponCondition,cova_CouponTerm
        INTO covaLimitation,covaCouponValue,covaCouponCondition,covaCouponTerm
        FROM tbl_couponvariety
        WHERE pk_CouponVariety = pkCouponvariety;

        WHILE num<acscNum DO
            INSERT INTO tbl_coupon(
                pk_activity,
                pk_couponvariety,
                cp_limitation,
                cp_couponvalue,
                cp_couponcondition,
                cp_userid,
                cp_begindate,
                cp_enddate)
            VALUES(
                pkActivity,
                pkCouponvariety,
                covaLimitation,
                covaCouponValue,
                covaCouponCondition,
                cpUserid,
                current_date,
                date_add(current_date, interval covaCouponTerm day));
            set num = num + 1;
        END WHILE;
        FETCH cur_record INTO pkCouponvariety, acscNum;
    END WHILE;
    CLOSE cur_record;

END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `role_menu_func` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = '' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50020 DEFINER=`eichong`@`%`*/ /*!50003 PROCEDURE `role_menu_func`(
	roleId VARCHAR (30),
	menuIds VARCHAR (10000)
)
BEGIN
	DELETE
FROM
	p_m_menu_role
WHERE
	ROLE_ID = roleId
AND MENU_ID NOT IN (menuIds);

INSERT INTO p_m_menu_role SELECT
	menu_id,
	roleId,
	now(),
	'system',
	now(),
	'system'
FROM
	p_m_menu
WHERE
	FIND_IN_SET(MENU_ID, menuIds)
AND MENU_ID NOT IN (
	SELECT
		MENU_ID
	FROM
		p_m_menu_role mr
	WHERE
		mr.ROLE_ID = roleId
);


END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-14 13:16:12

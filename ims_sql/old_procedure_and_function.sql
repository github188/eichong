DELIMITER ;;
CREATE FUNCTION `getCodeDetailName`( codeGroupId VARCHAR(255), codeId VARCHAR(255)) RETURNS varchar(255) CHARSET utf8
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
  
END;;

CREATE  FUNCTION `getPathForServer`( fileType VARCHAR(255), referenceId VARCHAR(255)) RETURNS varchar(1024) CHARSET utf8
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
END;;

CREATE  FUNCTION `getDistance`(  LngBeginP VARCHAR(100) , LngEnd REAL ,LatBeginP VARCHAR(100) , LatEnd REAL ) RETURNS double
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
END;;

CREATE  FUNCTION `GET_FIRST_PINYIN_CHAR`(PARAM VARCHAR(255)) RETURNS varchar(2) CHARSET utf8
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
END;;

CREATE  PROCEDURE `insert_coupon_proc`(cpUserid int, pkActivity int)
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

END;;

CREATE  FUNCTION `getFullPath`(fileType VARCHAR(255), referenceId VARCHAR(255)) RETURNS varchar(1024) CHARSET utf8
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
END;;

CREATE  PROCEDURE `role_menu_func`(
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
END;;

DELIMITER ;
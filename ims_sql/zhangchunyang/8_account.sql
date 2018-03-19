#处理公司、用户、卡的资金账号
ALTER TABLE `fin_account`
MODIFY COLUMN `account_balance`  decimal(10,2) NULL DEFAULT 0.00 COMMENT '普通用户帐户余额';


#存储过程
DELIMITER ;;
CREATE FUNCTION `fun_build_accountno`(sysType int, ids int) RETURNS varchar(32) CHARSET utf8
BEGIN
	DECLARE retAccountNo VARCHAR(32);     #返回的accountNo
  DECLARE strId 	 		 VARCHAR(50);     #id转换成字符串
	DECLARE strIdLen		 INT;							#id的长度
	DECLARE times				 VARCHAR(20);			#时间戳
	DECLARE randNum			 INT;							#随机三位数
	##########################################################################################################
	### 资金账户号规则:
	### FA + 标识位：1.用户  2.公司 3.卡 + 标识位对应的ID(需补足5位) + 年月日时分秒毫秒的时间戳 + 修改的版本号
	##########################################################################################################

	set strIdLen = 0;
	set retAccountNo = 'FA';
	set retAccountNo = CONCAT(retAccountNo, sysType);

	set strId = CAST(ids AS CHAR);
	set strIdLen = LENGTH(strId);

	IF(strIdLen = 1) then
		set strId = CONCAT('0000', strId);
	ELSEIF(strIdLen = 2) then
		set strId = CONCAT('000', strId);
	ELSEIF(strIdLen = 3) then
		set strId = CONCAT('00', strId);
	ELSEIF(strIdLen = 4) then
		set strId = CONCAT('0', strId);
	END IF;

	set retAccountNo = CONCAT(retAccountNo,strId);
	set randNum = CEILING(RAND()*500 + 500);
	set times = CONCAT(DATE_FORMAT(NOW(), '%Y%m%d%H%i%s'), randNum);
	set retAccountNo = CONCAT(retAccountNo, times);
  set retAccountNo = CONCAT(retAccountNo, '10');

  RETURN retAccountNo;
  
END;;
DELIMITER ;




DELIMITER ;;
CREATE PROCEDURE `proc_insert_company_account`()
BEGIN
		DECLARE cpyNumber				BIGINT(11);				
		DECLARE	pwd					VARCHAR(32);			
		DECLARE balance     DECIMAL(10, 2);		
		DECLARE warnMoney   DECIMAL(10, 4);		
	    DECLARE accountId		BIGINT(11);				
		DECLARE newAccountNo		VARCHAR(32);	
        DECLARE accountNo		VARCHAR(32);					
		DECLARE tradeType   INT;							
		DECLARE num BIGINT DEFAULT 0;					
		DECLARE i INT;												
		DECLARE count				int;
		DECLARE userId				BIGINT(11);

    
    DECLARE company_cursor CURSOR FOR
				SELECT account_no,cpy_number,trade_type
				FROM tbl_company;

		
		set i = 0;
		SELECT COUNT(1) INTO num FROM tbl_company;
		OPEN company_cursor;
			WHILE i < num DO
				fetch company_cursor into accountNo,cpyNumber, tradeType;

				SELECT count(1) into count
					FROM
						tbl_user t,
						tbl_user_normal_old normal
					WHERE
						t.user_id = normal.user_id
						AND LENGTH(t.user_account) = 12 
						and t.user_account = accountNo;
				
				SELECT fun_build_accountno(2, cpyNumber) INTO newAccountNo;

				IF count = 0 THEN
						INSERT INTO fin_account
								VALUES(null, newAccountNo, 'e10adc3949ba59abbe56e057f20f883e', 0, tradeType, 0, 1, 0, '', '', NOW(), NOW());
				ELSE
						SELECT
							normal.norm_account_balance,
							t.user_password 
						INTO balance, pwd
						FROM
						tbl_user t,
						tbl_user_normal_old normal
					WHERE
						t.user_id = normal.user_id
						AND LENGTH(t.user_account) = 12 
						and t.user_account = accountNo;

						IF pwd is null THEN
						SET pwd = 'e10adc3949ba59abbe56e057f20f883e';
						END IF;

						IF balance is null THEN
							SET balance = 0;
					  END IF;

						INSERT INTO fin_account VALUES(null, newAccountNo, pwd, balance, tradeType, 0, 1, 0, '', '', NOW(), NOW());
				END IF;

				select @@identity INTO accountId;

				update tbl_company set account_id = accountId where cpy_number = cpyNumber;
				SET i = i + 1;
			END WHILE;
		CLOSE company_cursor;
  END;;
 DELIMITER ;


DELIMITER ;;
CREATE PROCEDURE `proc_insert_fin_account`()
BEGIN
		DECLARE userId	    BIGINT(11);				
		DECLARE	pwd					VARCHAR(32);			
		DECLARE balance     DECIMAL(10, 2);		
		DECLARE warnMoney   DECIMAL(10, 4);		
		DECLARE ucId				BIGINT(11);				
		DECLARE cpyId				BIGINT(11);				
		DECLARE accountId		BIGINT(11);				
		DECLARE accountNo		VARCHAR(32);			
		DECLARE tradeType   INT;							
		DECLARE num BIGINT DEFAULT 0;					
		DECLARE i INT;												
		DECLARE cnt BIGINT DEFAULT 0;	
		
		DECLARE user_cursor CURSOR FOR
				SELECT user_id, norm_account_balance
				FROM tbl_user_normal_old;

        set i = 0;
		SELECT COUNT(1) INTO num FROM tbl_user_normal_old;
		OPEN user_cursor;
			WHILE i < num DO
				fetch user_cursor into userId, balance;

				SELECT trade_type INTO tradeType FROM tbl_company WHERE cpy_number = 1000;

				SELECT fun_build_accountno(1, userId) INTO accountNo;

                SELECT user_password into pwd from tbl_user WHERE user_id = userId;
                
				INSERT INTO fin_account
				VALUES(null, accountNo, pwd, balance, tradeType, 0, 1, 0, '', '', NOW(), NOW());

				select @@identity INTO accountId;

				insert into fin_account_relation VALUES(null, userId, 1, accountId, 1, 0, '', '', NOW(), NOW());

				update tbl_user_normal set account_id = accountId where user_id = userId;
				SET i = i + 1;
			END WHILE;
		CLOSE user_cursor;
 END;;
 DELIMITER ;



DELIMITER ;;
CREATE PROCEDURE `proc_gen_account_config_rela`()
BEGIN
		DECLARE cpyId				BIGINT(11);				
		DECLARE accountId		BIGINT(11);				
    DECLARE tradeType   INT;							
		DECLARE num BIGINT DEFAULT 0;					
		DECLARE i INT;												

		
		DECLARE cpy_cursor CURSOR FOR
				SELECT cpy_id, account_id, pay_rule
				FROM tbl_company;
		
		set i = 0;
		SELECT COUNT(1) INTO num FROM tbl_company;
		OPEN cpy_cursor;
			WHILE i < num DO
				fetch cpy_cursor into cpyId, accountId, tradeType;
				
				INSERT INTO fin_account_config_rela VALUES(null, cpyId, 1, tradeType, 0, '', '', NOW(), NOW());

				IF(tradeType = 1) THEN
					
				    CALL proc_insert_tuser(cpyId);
					
				END IF;

				SET i = i + 1;
			END WHILE;
		CLOSE cpy_cursor;

 END;;
 DELIMITER ;


DELIMITER ;;
CREATE PROCEDURE `proc_insert_tuser`(cpyId BIGINT(11))
 BEGIN
	
		DECLARE userAccount	    VARCHAR(32);				
		DECLARE num BIGINT  DEFAULT 0;				
		DECLARE i INT;												
		DECLARE resUserId   BIGINT(11);				
    DECLARE newUserId   BIGINT(11);				
    DECLARE cardId	    BIGINT(11);					
    DECLARE levelId   BIGINT(11);		
    DECLARE accountId   BIGINT(11);						
    DECLARE pwd   VARCHAR(32);	
		
		DECLARE card_cursor CURSOR FOR SELECT uc_id,CONCAT('1',RIGHT(uc_ExternalCardNumber,10)) FROM tbl_usercard WHERE cpy_id = cpyId 
                             AND new_user_id = 0 AND user_id = 0;

		SET resUserId = 0;
		SET i = 0;
		SELECT COUNT(1) INTO num FROM tbl_usercard WHERE cpy_id = cpyId AND new_user_id = 0 AND user_id = 0;
		OPEN card_cursor;
			WHILE i < num DO
				fetch card_cursor into cardId,userAccount;

        SELECT level_id into levelId from tbl_level WHERE cpy_id = cpyId;
        SELECT account_id into accountId from tbl_company WHERE cpy_id = cpyId;

        SELECT account_pwd into pwd FROM fin_account WHERE account_id = accountId;

        IF pwd is null THEN
			SET pwd = 'e10adc3949ba59abbe56e057f20f883e';
		END IF;

        insert into tbl_user (user_id,user_account,user_password,user_leval,user_status,gmt_create,gmt_modified,level_id)
                      values (null,userAccount,pwd,6,1,NOW(),NOW(),levelId);
				
        select @@identity INTO newUserId;
        
        insert into fin_account_relation VALUES(null, newUserId, 1, accountId, 1, 0, '', '', NOW(), NOW());

        update tbl_usercard set user_id = newUserId,account_id = accountId where uc_id = cardId;
	      
        set i = i + 1;
      END WHILE;
		CLOSE card_cursor;

 END;;
 DELIMITER ;

call proc_insert_company_account;
call proc_insert_fin_account;
call proc_gen_account_config_rela;

UPDATE fin_account SET account_pwd = 'e10adc3949ba59abbe56e057f20f883e' WHERE account_pwd = '';

#充值、充值退款账单科目：
##充值
INSERT INTO fin_account_relation 
SELECT
	NULL,
	c.user_id,
	(select a.pk_id from fin_bill_account a where a.bill_account_code = '100040') as bill_account_id,
	c.account_id,
	1,
	0,
	'',
	'',
	NOW(),
	NOW()
FROM
	tbl_user_company c;

##充值
INSERT INTO fin_account_relation 
SELECT
	NULL,
	n.user_id,
	(select a.pk_id from fin_bill_account a where a.bill_account_code = '100040') as bill_account_id,
	n.account_id,
	1,
	0,
	'',
	'',
	NOW(),
	NOW()
FROM
	tbl_user_normal n
inner join fin_account a on n.account_id = a.account_id;




UPDATE tbl_usercard u
SET u.account_id = (
	SELECT
		n.account_id
	FROM
		tbl_user_normal n
	WHERE
		n.user_id = u.new_user_id
)
WHERE
	u.account_id = 0
AND u.is_app > 0
AND u.cpy_id = 295;


UPDATE tbl_usercard set user_id = new_user_id where is_app = 1;

